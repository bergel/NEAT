package gui;

/**
 * Inserire qui la descrizione del tipo.
 * Data di creazione: (13/06/02 18.10.25)
 * @author: Administrator
 */





   
   
   
   
   
   
   
   import java.util.*;
   import java.lang.*;







   
   import jneat.*;
   import jNeatCommon.*;


   import java.text.*;
   import jGraph.*;
   import java.lang.reflect.*;
   import log.*;



 
import javax.swing.*;import javax.swing.text.*;public class Execution {

 

 
   // dynamic definition for fitness
	  public Class  Class_fit;
	  public Object ObjClass_fit;
	  public Method Method_fit;
	  public Object ObjRet_fit;
   
   
   // dynamic definition for input class
	  public Class  Class_inp;
	  public Object ObjClass_inp;
	  public Method Method_inp;
	  public Object ObjRet_inp;
   
   // dynamic definition for target class
	  public Class  Class_tgt;
	  public Object ObjClass_tgt;
	  public Method Method_tgt;
	  public Object ObjRet_tgt;
   
   
	  public int     Xdepth; 
	  public Network Xnet;






	
/**
 * Commento del constructor Execution.
 */
public Execution() {
	super();
}








   
   
   
   
   









public boolean createNetwork() 
{

	StringTokenizer st;
	String curword = null;
	String xline;
	String fnamebuf;
	IOseq xFile;
	int id;
	Genome Xgenome  = null;
	Xnet = null;

	// System.out.print("\n Test Network with genome -> " + EnvConstant.NAME_OF_GENOME_FOR_EXECUTION);
	xFile = new IOseq(EnvConstant.NAME_OF_GENOME_FOR_EXECUTION);
	boolean ret = xFile.IOseqOpenR();

	if (!ret) 
		return false;
	if (ret) 
	{

		try 
		{

			xline = xFile.IOseqRead();
			st = new StringTokenizer(xline);
			curword = st.nextToken();

			while (curword.equalsIgnoreCase("/*")) 
			{                //              System.out.print("\n skip -> "+xline);
				EnvConstant.DESCRIPTION_GENOME = xline;
				xline = xFile.IOseqRead();
				st = new StringTokenizer(xline);
				curword = st.nextToken();
			}

			// 
			curword = st.nextToken();
			// 
			id = Integer.parseInt(curword);

			//generate genome from file
			Xgenome = new Genome(id, xFile);

			//setting the pointer of global genome

			EnvConstant.CURR_GENOME_RUNNING = Xgenome;

			//generate network
			Xgenome.genesis(id);

			// net phenotype
			Xnet = Xgenome.getPhenotype();

			// compute first the 'teorical' depth
			int lx = Xnet.max_depth();

			// compute . after, the 'pratical' depth passing
			// the virtual depth; 
			Xdepth = Xnet.is_stabilized(lx);

			// after reset all value of net
			Xnet.flush();

			if (Xdepth < lx)
				Xdepth = lx;

		} 
		
		
		catch (Throwable e) 
		{
			System.out.print("\n execution: : error during read " + EnvConstant.NAME_OF_GENOME_FOR_EXECUTION + " file "  + e); 
			return false;
		}

		xFile.IOseqCloseR();

	} 
	
	
	else 
	{
		System.out.print("\n generation: error in open genome " + EnvConstant.NAME_OF_GENOME_FOR_EXECUTION); 
		return false;

	}

	return true;

}public void createNetwork(Genome _genome) {

	StringTokenizer st;
	String curword = null;
	String xline;
	String fnamebuf;
	IOseq xFile;
	int id;
	Genome Xgenome = null;
	Xnet = null;

	//generate genome from file
	Xgenome = new Genome(_genome.genome_id, _genome.getTraits(), _genome.getNodes(), _genome.getGenes()); 

	//setting the pointer of global genome

	EnvConstant.CURR_GENOME_RUNNING = Xgenome;

	//generate network
	Xgenome.genesis(_genome.genome_id);

	// net phenotype
	Xnet = Xgenome.getPhenotype();

	// compute first the 'teorical' depth
	int lx = Xnet.max_depth();

	// compute . after, the 'pratical' depth passing
	// the virtual depth; 
	Xdepth = Xnet.is_stabilized(lx);

	// after reset all value of net
	Xnet.flush();

	if (Xdepth < lx)
		Xdepth = lx;

}public void executeForClass(JTextArea _textArea) 
{

	String mask1i = "0";
	DecimalFormat fmt1i = new DecimalFormat(mask1i);

	String mask2i = " 0000";
	DecimalFormat fmt2i = new DecimalFormat(mask2i);

	String mask4i = " 0000";
	DecimalFormat fmt4i = new DecimalFormat(mask4i);

	String mask6d = "  0.00000";
	DecimalFormat fmt6d = new DecimalFormat(mask6d);

	int nr_input = Xnet.getInputs().size() - 1;
	int nr_output = Xnet.getOutputs().size();

	boolean success = false;

	double fit_dyn = 0.0;
	double err_dyn = 0.0;
	double win_dyn = 0.0;

	double errorsum = 0.0;
	int net_depth = 0; //The max depth of the network to be activated
	int count = 0;
	int nr_execution = 0;

	try 
	{

		Class_inp = Class.forName(EnvConstant.DATA_INP);
		ObjClass_inp = Class_inp.newInstance();
		Method_inp = Class_inp.getMethod("getNumUnit", null);
		ObjRet_inp = Method_inp.invoke(ObjClass_inp, null);
		int class_input = Integer.parseInt(ObjRet_inp.toString());

		// number of samples
		//
		Method_inp = Class_inp.getMethod("getNumSamples", null);
		ObjRet_inp = Method_inp.invoke(ObjClass_inp, null);
		EnvConstant.NUMBER_OF_SAMPLES = Integer.parseInt(ObjRet_inp.toString());

		if (EnvConstant.NR_UNIT_INPUT != nr_input) {
			System.out.print("\n genome not compatible for wrong number of input unit");
			return;
		}

		Class_tgt = Class.forName(EnvConstant.DATA_OUT);
		ObjClass_tgt = Class_tgt.newInstance();
		Method_tgt = Class_tgt.getMethod("getNumUnit", null);
		ObjRet_tgt = Method_tgt.invoke(ObjClass_tgt, null);
		EnvConstant.NR_UNIT_OUTPUT = Integer.parseInt(ObjRet_tgt.toString());

		if (EnvConstant.NR_UNIT_OUTPUT != nr_output) {
			System.out.print("\n genome not compatible for wrong number of output unit");
			return;
		}

	    Class_fit = Class.forName(EnvConstant.CLASS_FITNESS);
		ObjClass_fit = Class_fit.newInstance();


	} 
	
	catch (Exception e2) 
	{
		System.out.print(
			"\n Error(1) generic in execution.executeForClass: code " + e2); 
		return;
	}

	double in[] = null;
	in = new double[nr_input + 1];

	// setting bias

	in[nr_input] = 1.0;

	double out[][] = null;
	out = new double[EnvConstant.NUMBER_OF_SAMPLES][nr_output];

	double tgt[][] = null;
	tgt = new double[EnvConstant.NUMBER_OF_SAMPLES][nr_output];

	Integer ns = new Integer(EnvConstant.NUMBER_OF_SAMPLES);

	net_depth = Xnet.max_depth();

	int xnn = Xnet.getAllnodes().size();
	Integer nn = new Integer(xnn);

	Class[] params = {int.class, int.class, double[][].class, double[][].class};
	Object paramsObj[] = new Object[] {ns, nn, out, tgt};

	_textArea.append("\n\nPOP.    FILE : " + EnvConstant.CURRENT_POPULATION_VIEW);
	_textArea.append("\nGENOME  FILE : " + EnvConstant.NAME_OF_GENOME_FOR_EXECUTION); 
	_textArea.append("\nINPUT   (class): " + EnvConstant.DATA_INP+ " )"); 
	_textArea.append("\nOUTPUT  (class): " + EnvConstant.DATA_OUT+ " )"); 
	_textArea.append("\nFITNESS (class): " + EnvConstant.CLASS_FITNESS+ " )"); 
	_textArea.append("\n");


	StringBuffer _textArea1 = new StringBuffer("");
	StringBuffer _textArea2 = new StringBuffer("");
	
	
	// case of input from class java

	try 
	{

		int plist_in[] = new int[2];
		Class[] params_inp = {int[].class};
		Object[] paramsObj_inp = new Object[] {plist_in};



		// 
		int plist_tgt[] = new int[2];
		Class[] params_tgt = {int[].class};
		Object[] paramsObj_tgt = new Object[] {plist_tgt};



		

		for (count = 0; count < EnvConstant.NUMBER_OF_SAMPLES; count++) 
		{

			
			plist_in[0] = count;
			
			// get from class the sample cout-esimo and input j-esimo
			for (int j = 0; j < nr_input; j++) 
			{
				plist_in[1] = j;
				Method_inp = Class_inp.getMethod("getInput", params_inp);
				ObjRet_inp = Method_inp.invoke(ObjClass_inp, paramsObj_inp);
				double v1 = Double.parseDouble(ObjRet_inp.toString());
				in[j] = v1;
			}

			_textArea1.append("\n\nSAMPLE[" + fmt4i.format(count) + "]");
			_textArea1.append("\n  inputs : ");
			for (int j = 0; j < (nr_input + 1); j++) 
			{
				_textArea1.append("\n    i[");
				_textArea1.append(fmt1i.format(j));
				_textArea1.append("]=");
				_textArea1.append(fmt6d.format(in[j]));
			}

			// move input sample in sensor of network
			//
			Xnet.load_sensors(in);




			
			nr_execution = 0;
			if (EnvConstant.ACTIVATION_PERIOD == EnvConstant.MANUAL) 
			{
				// if user has forced number of activation to fix value..
				// 
				for (int relax = 0; relax < EnvConstant.ACTIVATION_TIMES; relax++) 
				{
					success = Xnet.activate();
					nr_execution++;
				}
			} 
			
			else 
			{
				// first activation from sensor to next layer....
				//
				success = Xnet.activate();
				nr_execution++;
				
				// next activation while last level is reached !
				// use depth to ensure relaxation
				//
				for (int relax = 0; relax <= net_depth; relax++) 
				{
					success = Xnet.activate();
					nr_execution++;
				}
			}






			

			// for each sample save each output
			//
			for (int j = 0; j < nr_output; j++) 
			{
				double v1 = ((NNode) Xnet.getOutputs().elementAt(j)).getActivation();
				out[count][j] = v1;
			}


			plist_tgt[0] = count;
			for (int j = 0; j < nr_output; j++) 
			{
				plist_tgt[1] = j;
				Method_tgt = Class_tgt.getMethod("getTarget", params_tgt);
				ObjRet_tgt = Method_tgt.invoke(ObjClass_tgt, paramsObj_tgt);
				double v1 = Double.parseDouble(ObjRet_tgt.toString());
//				System.out.print(" ,  o[" + j + "] = " + v1);
				tgt[count][j] = v1;
			}

			//display output
			_textArea1.append("\n  output : ");
			for (int j = 0; j < nr_output; j++) {
				_textArea1.append("\n    o[");
				_textArea1.append(fmt1i.format(j));
				_textArea1.append("]=");
				_textArea1.append(fmt6d.format(out[count][j]));
				_textArea1.append("\n    (tgt=" + fmt6d.format(tgt[count][j]) + ") ");
			}
			

			// clear net 
			Xnet.flush();
		}

		_textArea1.append("\n   (# of cycle " + nr_execution + ") \n ");

		
	} 
	
	catch (Exception e2) 
	{
		System.out.print("\n Error(2) generic in execution.executeForClass: code " + e2); 
		return;
	}

	// compute the fitness
	
	try 
	{


		// now  i can compute the Fitness
		//
		

		Method_fit = Class_fit.getMethod("computeFitness", params);
		ObjRet_fit = Method_fit.invoke(ObjClass_fit, paramsObj);

		fit_dyn = Array.getDouble(ObjRet_fit, 0);
		err_dyn = Array.getDouble(ObjRet_fit, 1);
		win_dyn = Array.getDouble(ObjRet_fit, 2);

		_textArea2.append("\n   Fitness -> " + fmt6d.format(fit_dyn));
		_textArea2.append("\n   Error   -> " + fmt6d.format(err_dyn));
		
		if (win_dyn == 0)
	 	    _textArea2.append("\n   normal");
		if (win_dyn == 1)
	 	    _textArea2.append("\n   WINNER");
		if (win_dyn == 2)
	 	    _textArea2.append("\n   SUPER-WINNER");
		   
		_textArea2.append("\n\n");

		

	} 
	
	
	catch (Exception e2) 
	{
		System.out.print("\n Error(3) generic in execution.executeForClass: code " + e2); 
		return;
	}



	_textArea.append(_textArea2.toString());
	_textArea.append(_textArea1.toString());

	

}public void executeForFile(JTextArea _textArea) {

	String mask1i = "0";
	DecimalFormat fmt1i = new DecimalFormat(mask1i);

	String mask2i = " 0000";
	DecimalFormat fmt2i = new DecimalFormat(mask2i);

	String mask4i = " 0000";
	DecimalFormat fmt4i = new DecimalFormat(mask4i);

	String mask6d = "  0.00000";
	DecimalFormat fmt6d = new DecimalFormat(mask6d);

	int nr_input = Xnet.getInputs().size() - 1;
	int nr_output = Xnet.getOutputs().size();

	boolean success = false;

	double fit_dyn = 0.0;
	double err_dyn = 0.0;
	double win_dyn = 0.0;

	double errorsum = 0.0;
	int net_depth = 0; //The max depth of the network to be activated
	int count = 0;
	int nr_execution = 0;

	StringTokenizer riga = null;
	String xline = null;
	String elem = null;

	// get number of sample
	//

	String xnome = EnvRoutine.getJneatFile(EnvConstant.DATA_INP);
	EnvConstant.NUMBER_OF_SAMPLES = EnvRoutine.getNumberSamplesForFile(xnome);

	// open input file
	//
	IOseq xFile;
	xFile = new IOseq(EnvRoutine.getJneatFile(EnvConstant.DATA_INP));
//	System.out.print("\n open .." + EnvRoutine.getJneatFile(EnvConstant.DATA_INP));
	boolean ret = xFile.IOseqOpenR();

	// open tgt file
	//
	String nomef_tgt;
	nomef_tgt = EnvRoutine.getJneatFile(EnvConstant.DATA_OUT);
	IOseq xFile_tgt;
	xFile_tgt = new IOseq(nomef_tgt);
//	System.out.print("\n open .." + EnvRoutine.getJneatFile(EnvConstant.DATA_OUT));
	boolean ret_tgt = xFile_tgt.IOseqOpenR();

	if (!ret) 
	{
		System.out.print("\n Error(1) generic in execution.executeForFile");
		return;
	}


	try 
	{


	    Class_fit = Class.forName(EnvConstant.CLASS_FITNESS);
		ObjClass_fit = Class_fit.newInstance();


	} 
	
	catch (Exception e2) 
	{
		System.out.print("\n Error(1) generic in execution.executeForFile: code " + e2); 
		return;
	}

	
//	System.out.print("\n number of samples ->" + EnvConstant.NUMBER_OF_SAMPLES);

	double in[] = null;
	in = new double[nr_input + 1];

	// setting bias

	in[nr_input] = 1.0;

	double out[][] = null;
	out = new double[EnvConstant.NUMBER_OF_SAMPLES][nr_output];

	double tgt[][] = null;
	tgt = new double[EnvConstant.NUMBER_OF_SAMPLES][nr_output];

	Integer ns = new Integer(EnvConstant.NUMBER_OF_SAMPLES);

	net_depth = Xnet.max_depth();

	int xnn = Xnet.getAllnodes().size();
	Integer nn = new Integer(xnn);




	
	Class[] params = {int.class, int.class, double[][].class, double[][].class};
	Object paramsObj[] = new Object[] {ns, nn, out, tgt};

	_textArea.append("\n\nPOP.    FILE : " + EnvConstant.CURRENT_POPULATION_VIEW);
	_textArea.append("\nGENOME  FILE : " + EnvConstant.NAME_OF_GENOME_FOR_EXECUTION); 
	_textArea.append("\nINPUT   (file) : " + EnvConstant.DATA_INP + " )");
	_textArea.append("\nOUTPUT  (file) : " + EnvConstant.DATA_OUT + " )");
	_textArea.append("\nFITNESS (class): " + EnvConstant.CLASS_FITNESS + " )");
	_textArea.append("\n");
 
	StringBuffer _textArea1 = new StringBuffer("");
	StringBuffer _textArea2 = new StringBuffer("");
	
	xline = xFile.IOseqRead();

	while ((xline != "EOF")) 
	{

		while (xline.startsWith("//", 0))
			xline = xFile.IOseqRead();

		riga = new StringTokenizer(xline);
		int sz = riga.countTokens();
		if (sz != nr_input) 
		{
			System.out.print("\n *error* in rec " + count);
			System.out.print(" number of input = " + sz);
			System.out.print(" different from declared " + nr_input + " unit");
			System.out.print("\n correct and re-run;\n\t  Bye");
			return;
		}

		for (int j = 0; j < nr_input; j++) 
		{
			elem = riga.nextToken();
			double v1 = Double.parseDouble(elem);
			in[j] = v1;
		}

		_textArea1.append("\n\nSAMPLE[" + fmt4i.format(count) + "]");
		_textArea1.append("\n  inputs : ");
		for (int j = 0; j < (nr_input + 1); j++) 
		{
			_textArea1.append("\n    i[");
			_textArea1.append(fmt1i.format(j));
			_textArea1.append("]=");
			_textArea1.append(fmt6d.format(in[j]));
		}


//		System.out.print("\n lab1 ->"+_textArea1.toString());
		// move input sample in sensor of network
		//
		Xnet.load_sensors(in);

		// first activation of sensor
		Xnet.load_sensors(in);

		nr_execution = 0;
		if (EnvConstant.ACTIVATION_PERIOD == EnvConstant.MANUAL) 
		{
			// if user has forced number of activation to fix value..
			// 
			for (int relax = 0; relax < EnvConstant.ACTIVATION_TIMES; relax++) {
				success = Xnet.activate();
				nr_execution++;
			}
		} 
		
		else 
		{
			// first activation from sensor to next layer....
			//
			success = Xnet.activate();
			nr_execution++;

			// next activation while last level is reached !
			// use depth to ensure relaxation
			//
			for (int relax = 0; relax <= net_depth; relax++) 
			{
				success = Xnet.activate();
				nr_execution++;
			}
		}

		// for each sample save each output
		//
		for (int j = 0; j < nr_output; j++) 
		{
			double v1 = ((NNode) Xnet.getOutputs().elementAt(j)).getActivation();
			out[count][j] = v1;
		}

		xline = xFile_tgt.IOseqRead();
		while (xline.startsWith("//", 0))
			xline = xFile_tgt.IOseqRead();

		riga = new StringTokenizer(xline);
		sz = riga.countTokens();

		for (int j = 0; j < nr_output; j++) 
		{
			elem = riga.nextToken();
			double v1 = Double.parseDouble(elem);
			tgt[count][j] = v1;
		}

		//display output
		_textArea1.append("\n  output : ");
		for (int j = 0; j < nr_output; j++) {
			_textArea1.append("\n    o[");
			_textArea1.append(fmt1i.format(j));
			_textArea1.append("]=");
			_textArea1.append(fmt6d.format(out[count][j]));
			_textArea1.append("\n    (tgt=" + fmt6d.format(tgt[count][j]) + ") ");
		}

		// clear net 
		Xnet.flush();
		count++;

		xline = xFile.IOseqRead();

	}

	_textArea1.append("\n   (# of cycle " + nr_execution + ") \n ");
	xFile.IOseqCloseR();


	try 
	{


		// now  i can compute the Fitness
		//
		

		Method_fit = Class_fit.getMethod("computeFitness", params);
		ObjRet_fit = Method_fit.invoke(ObjClass_fit, paramsObj);

		fit_dyn = Array.getDouble(ObjRet_fit, 0);
		err_dyn = Array.getDouble(ObjRet_fit, 1);
		win_dyn = Array.getDouble(ObjRet_fit, 2);

		_textArea2.append("\n   Fitness -> " + fmt6d.format(fit_dyn));
		_textArea2.append("\n   Error   -> " + fmt6d.format(err_dyn));
		
		if (win_dyn == 0)
	 	    _textArea2.append("\n   normal");
		if (win_dyn == 1)
	 	    _textArea2.append("\n   WINNER");
		if (win_dyn == 2)
	 	    _textArea2.append("\n   SUPER-WINNER");
		   
		_textArea2.append("\n\n");

		

	} 
	
	
	catch (Exception e2) 
	{
		System.out.print("\n Error(3) generic in execution.executeForFile: code " + e2); 
		return;
	}



	_textArea.append(_textArea2.toString());
	_textArea.append(_textArea1.toString());



}}