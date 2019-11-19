   package testcase;



   import jneat.Genome;
   import java.util.*;
   import jneat.Network;
   import jNeatCommon.IOseq;

   import java.text.*;import jneat.NNode;
																public class testnet {
   
   
	  jneat.Network net;
	  int depth;
   /**
   * testnet constructor comment.
   */
	   public testnet() {
		 super();
	  }
   
   
	   public testnet(String _FileGenome) 
	  {
	  
		 StringTokenizer st;
		 String curword;
		 String xline;
		 String fnamebuf;
		 IOseq xFile;
		 int id;
		 Genome g1 = null;
		 net = null;
		 System.out.print("\n Test Network with genome -> " + _FileGenome);
		 xFile = new IOseq(_FileGenome);
		 boolean ret = xFile.IOseqOpenR();
	  
		 if (ret) {
		 
			try {
			
			   System.out.print("\n  read genome..");
			
			// 
			   xline = xFile.IOseqRead();
			// 
			   st = new StringTokenizer(xline);
			// 
			   curword = st.nextToken();
			// 
			   curword = st.nextToken();
			// 
			   id = Integer.parseInt(curword);
			
			//signal id genome readed
			   System.out.print(" ok!  id -> "+id);
			
			//generate genome from file
			   g1 = new Genome(id, xFile);
			
			//generate network
			   g1.genesis(id);
			
			// net phenotype
			   net = g1.getPhenotype();
			
			// compute first the 'teorical' depth
			   int lx = net.max_depth();
			
			// compute . after, the 'pratical' depth passing
			// the virtual depth; 
			   depth = net.is_stabilized(lx);
			
			// after reset all value of net
			   net.flush();
			
			   System.out.print("\n this genome has max depth virtuale=" + lx); 
			   System.out.print(", max depth reale=" + depth);
			
			   if (depth != lx)
				  System.out.print("\n  *ALERT*  This net is   NOT   S T A B L E ");
			
			} 
			
				catch (Throwable e) 
			   {
				  System.err.print(e + " : error during read " + _FileGenome);
			   }
			xFile.IOseqCloseR();
		 
		 } 
		 
		 
		 
		 else
			System.err.print("\n : error during open " + _FileGenome);
	  
		 System.out.print("\n\n end of test.");
	  
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   /**
   * usage : testcase.testnet 'id_genome' 'input_data' 'output_data'
   */
	   public static void main(java.lang.String[] args) 
	  {
	  
		 if (args.length != 3) 
		 {
			System.out.print("\n USAGE :\n");
			System.out.print(
			   "\n run testcase.testnet 'file_genome' 'file_input' 'file_output'"); 
			System.out.print("\n correct the command and re-run !");
			System.exit(8);
		 }
		 testnet Xnet = new testnet(args[0]);
		 System.out.print("\n p[0] = "+args[0]);
		 System.out.print("\n p[1] = "+args[1]);	
		 System.out.print("\n p[2] = "+args[2]);
		 Xnet.execute(args[1], args[2]);
	  }
	   public void execute(String _filein, String _fileout) 
	  {
	  //input unit are -1 because the bias is excluded
	  //
		 int nr_input = net.getInputs().size() - 1;
		 int nr_output = net.getOutputs().size();
		 int count = 0;
		 boolean success = false;
		 StringBuffer s2 = null;
	  
		 String mask2i = " 0000";
		 DecimalFormat fmt2i = new DecimalFormat(mask2i);
	  
		 String mask4i = " 0000";
		 DecimalFormat fmt4i = new DecimalFormat(mask4i);
	  
		 String mask6d = "  0.00000";
		 DecimalFormat fmt6d = new DecimalFormat(mask6d);
	  
		 System.out.print("\n found " + nr_input + " input,  " + nr_output + " output;");
	  
		 StringTokenizer riga;
		 String xline;
		 String elem = null;
		 IOseq xFile;
		 IOseq xFile_out;
		 xFile     = new IOseq(_filein);
		 xFile_out = new IOseq(_fileout);
	  
		 System.out.print("\n write result to ->"+_fileout);
	  
	  
		 boolean ret = xFile.IOseqOpenR();
		 xFile_out.IOseqOpenW(false);
	  
	  
		 if (ret)
		 {
			double in[] = new double[nr_input+1];
			in[nr_input] = 1.0;
		 
			double out[] = new double[nr_output];
		 
			xline = xFile.IOseqRead();
			count = 0;
			while ((xline != "EOF")) 
			{
			   if (!(xline.startsWith("//", 0))) 
			   {
				  riga = new StringTokenizer(xline);
				  int sz = riga.countTokens();
				  if (sz != nr_input)
				  {
					 System.out.print("\n *error* in rec "+ count);
					 System.out.print(" number of input = "+ sz);
					 System.out.print(" different from declared "+nr_input+" unit");
					 System.out.print("\n correct and re-run;\n\t  Bye");
					 System.exit(9);
				  }
			   
				  for (int j = 0; j < nr_input; j++) 
				  {
					 elem = riga.nextToken();
					 double v1 = Double.parseDouble(elem);
					 in[j] = v1;
				  }
			   
				  System.out.print("\n at time ->"+count);
				  for(int j=0; j < (nr_input+1); j++)
					 System.out.print("  inp["+j+"]= "+in[j]);
			   
			   // first activation of sensor
				  net.load_sensors(in);
			   
			   // first activation from sensor to next layer....
				  success = net.activate();
			   
			   // next activation while last level is reached !
			   // use depth to ensure relaxation
				  for (int relax = 1; relax <= depth; relax++)
					 success = net.activate();
			   
			   // ok : the propagation is completed 
			   // for each sample save each output	
				  for( int j=0; j < nr_output; j++)
				  {
					 out[j] = ((NNode) net.getOutputs().elementAt(j)).getActivation();
				  }
			   
			   //display output
			   
			   
				  s2 = new StringBuffer("// time : ");
				  s2.append(fmt4i.format(count));
				  s2.append("  nr-output from 1 -> ");
				  s2.append(fmt2i.format(nr_output));
				  xFile_out.IOseqWrite(s2.toString());
			   
				  s2 = new StringBuffer("");
				  for(int j=0; j < (nr_output); j++)
				  {
					 s2.append(fmt6d.format(out[j]));
				  }
			   
				  xFile_out.IOseqWrite(s2.toString());
			   //	          System.out.print("  -> out["+j+"]= "+out[j]);
				  net.flush();
			   
			   
				  count++;
			   
			   }
			   
			   else
			   {
				  System.out.print("\n skip :"+xline);
			   }
			   xline = xFile.IOseqRead();
			
			}
		 
		 
		 
		 
		 }
	  
		 xFile_out.IOseqCloseW();
		 xFile.IOseqCloseR();
	  
	  
	  }}