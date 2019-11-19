   package jNeatCommon;

import java.awt.*;import java.util.*;   /**
 * Insert the type's description here.
 * Creation date: (09/04/2002 16.18.41)
 * @author: Administrator
 */
	public class EnvRoutine {
   /**
   * Routine constructor comment.
   */
	   public EnvRoutine() {
		 super();
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	   public static String getJneatFile(String _fname)
	  {
	  
		 String n1 = EnvConstant.JNEAT_DIR;
		 String n2 = n1 + EnvConstant.OS_FILE_SEP + _fname;
		 return n2;
	  }
	   public static String getJneatFileData(String _fname) 
	  {
	  
		 String n1 = EnvConstant.JNEAT_DIR;
		 String n2 = n1 + EnvConstant.OS_FILE_SEP + EnvConstant.DSN_DIR_DATA+EnvConstant.OS_FILE_SEP + _fname;
		 return n2;
	  }
	   public static String getJneatGenomeBaseOld()
	  {
	  
		 String n1 = EnvConstant.JNEAT_DIR;
		 String n2 = n1 + EnvConstant.OS_FILE_SEP + EnvConstant.DSN_DIR_DATA+EnvConstant.OS_FILE_SEP + EnvConstant.NAME_GENOMEA;
		 return n2;
	  }
	   public static String getJneatParameter() 
	  {
	  
		 String n1 = EnvConstant.JNEAT_DIR;
		 String n2 = n1 + EnvConstant.OS_FILE_SEP + EnvConstant.NAME_PARAMETER;
	  //   EnvConstant.DSN_PARAMETER = n2;
		 return n2;
	  }
	   public static String getJneatSession() 
	  {
	  
		 String n1 = EnvConstant.JNEAT_DIR;
		 String n2 = n1 + EnvConstant.OS_FILE_SEP + EnvConstant.NAME_SESSION;
	  //   EnvConstant.DSN_SESSION = n2;
		 return n2;
	  }
	   public static String getJneatClass(String _fname)
	  {
	  
		 String n1 = EnvConstant.JNEAT_DIR;
		 String n2 = n1 + EnvConstant.OS_FILE_SEP + _fname;
		 return n2;
	  }   	   public static Color GetForeColorPlot(int _codeColor) 
	  {
		 Color c = new Color(0,0,0);
		 if (_codeColor < 0 )
			c = new Color(196,0,0);

		 if (_codeColor > 0 )
			c = new Color(132,0,128);

		 if (_codeColor == 1)
			c = new Color(0,32,128);

		 if (_codeColor == 2)
			c = new Color(255,255,200);
		 return c;		 
	  }
		  
/**
 * Inserire qui la descrizione del metodo.
 * Data di creazione: (15/06/02 12.12.47)
 */
	   public static int  getNumberSamplesForFile(String _file)
	  {
		 String nomef;
		 StringTokenizer riga;
		 String xline;
		 int rc = 0;
		 IOseq xFile;
		 xFile = new IOseq(_file);
		 boolean ret = xFile.IOseqOpenR();
	  
		 if (ret)
		 {
		 
			xline = xFile.IOseqRead();
			boolean done = false;
			while ((xline != "EOF") && (!done)) 
			{
			
			   if (!(xline.startsWith("//", 0))) 
			   {
				  rc++;
			   }
			   xline = xFile.IOseqRead();
			
			}
		 }
		 
		 else
		 {
			System.out.print("\n error in open ->" + _file);
			System.out.print("\n correct and re-run! \n\t Bye");
			System.exit(8);
		 
		 }
	  
		 return rc;
	  
	  }/**
 * Inserire qui la descrizione del metodo.
 * Data di creazione: (13/06/02 17.57.20)
 */
	   public static void getSession() 
	  {
	  
		 String nomef;
		 String elem;
		 StringTokenizer riga;
		 int sff = 0;
		 int sfc = 0;
	  
		 nomef = EnvRoutine.getJneatSession();
	  
		 StringTokenizer st;
		 String curword;
		 String xline;
		 String xinp;
		 String xout;
	  
		 IOseq xFile;
		 xFile = new IOseq(nomef);
		 boolean ret = xFile.IOseqOpenR();
	  
		 if (ret)
		 {
		 
		 
		 
			sff = 0;
			sfc = 0;
		 
		 
			try 
			{
			
			   xline = xFile.IOseqRead();
			   while (xline != "EOF") 
			   {
			   
				  if (! xline.startsWith(";", 0)) 
				  {
				  
					 riga = new StringTokenizer(xline);
					 int sz = riga.countTokens();
					 int step = 0;
					 for (int r = 0; r < sz; r++) 
					 {
						elem = riga.nextToken();
						if (elem.equalsIgnoreCase("data_from_file"))
						   sff = 1;
						if (elem.equalsIgnoreCase("Y") && sff == 1)
						   sff = 2;
					 
						if (elem.equalsIgnoreCase("data_from_class"))
						   sfc = 1;
						if (elem.equalsIgnoreCase("Y") && sfc == 1)
						   sfc = 2;
					 
						if (elem.equalsIgnoreCase("data_input"))
						{
						   EnvConstant.DATA_INP = riga.nextToken();
						   r++;
						}	
					 
						if (elem.equalsIgnoreCase("data_target"))
						{
						   EnvConstant.DATA_OUT = riga.nextToken();
						   r++;
						}	
					 
						if (elem.equalsIgnoreCase("epoch"))
						{
						   EnvConstant.NUMBER_OF_EPOCH = Integer.parseInt(riga.nextToken());
						   r++;
						}	
					 
					 
						if (elem.equalsIgnoreCase("maximum_unit"))
						{
						   EnvConstant.NR_UNIT_MAX = Integer.parseInt(riga.nextToken());
						   r++;
						}	
					 
						if (elem.equalsIgnoreCase("class_compute_fitness"))
						{
						   EnvConstant.CLASS_FITNESS = riga.nextToken();
						   r++;
						}	
					 
						if (elem.equalsIgnoreCase("start_from_genome"))
						{
						   curword = riga.nextToken();
						   r++;
						   if (curword.equalsIgnoreCase("Y"))
							  EnvConstant.TYPE_OF_START = EnvConstant.START_FROM_GENOME;
						}	
					 
						if (elem.equalsIgnoreCase("genome_file"))
						{
						   EnvConstant.NAME_GENOMEA = riga.nextToken();
						   r++;
						}
					 
					 
						if (elem.equalsIgnoreCase("start_from_random_population"))
						{
						   curword = riga.nextToken();
						   r++;
						   if (curword.equalsIgnoreCase("Y"))
							  EnvConstant.TYPE_OF_START = EnvConstant.START_FROM_NEW_RANDOM_POPULATION;
						}	
					 
						if (elem.equalsIgnoreCase("start_from_old_population"))
						{
						   curword = riga.nextToken();
						   r++;
						   if (curword.equalsIgnoreCase("Y"))
							  EnvConstant.TYPE_OF_START = EnvConstant.START_FROM_OLD_POPULATION;
						}	
					 
						if (elem.equalsIgnoreCase("population_file"))
						{
						   EnvConstant.NAME_CURR_POPULATION = riga.nextToken();
						   r++;
						}
					 
						if (elem.equalsIgnoreCase("recursion"))
						{
						   curword = riga.nextToken();
						   r++;
						   if (curword.equalsIgnoreCase("Y"))
							  EnvConstant.RECURSION = true;
						   else
							  EnvConstant.RECURSION = false;
						}
					 
						if (elem.equalsIgnoreCase("probability_of_connection"))
						{
						   EnvConstant.PROBABILITY_OF_CONNECTION = Double.parseDouble(riga.nextToken());
						   r++;
						}
					 
					 
						if (elem.equalsIgnoreCase("prefix_generation_file"))
						{
						   EnvConstant.PREFIX_SPECIES_FILE = riga.nextToken();
						   r++;
						}
					 
						if (elem.equalsIgnoreCase("prefix_winner_file"))
						{
						   EnvConstant.PREFIX_WINNER_FILE = riga.nextToken();
						   r++;
						}
					 

						if (elem.equalsIgnoreCase("prefix_genome_random"))
						{
						   EnvConstant.PREFIX_GENOME_RANDOM = riga.nextToken();
						   r++;
						}
						
						if (elem.equalsIgnoreCase("activation"))
						{
						   curword = riga.nextToken();
						   
						   if (curword.equalsIgnoreCase("0"))
	                           EnvConstant.ACTIVATION_PERIOD = EnvConstant.AUTOMATIC;
						      
						   else 
	                       {
	                           EnvConstant.ACTIVATION_PERIOD = EnvConstant.MANUAL;
	                           EnvConstant.ACTIVATION_TIMES = Integer.parseInt(curword);
	                       }
	                       r++;

	                       
						}
			
						
						 
					 
					 }
				  
					 if ((sff == 2) && (sfc < 2)) 
					 {
						EnvConstant.TYPE_OF_SIMULATION = EnvConstant.SIMULATION_FROM_FILE;
					 } 
					 
					 else if ((sff < 2) && (sfc == 2)) 
					 {
						EnvConstant.TYPE_OF_SIMULATION = EnvConstant.SIMULATION_FROM_CLASS;
					 }
				  
				  
				  }
			   
			   
				  xline = xFile.IOseqRead();
			   
			   } // end cycle
			
			
			} 
			
			
				catch (Throwable e1) 
			   {
				  System.out.print(" routine: error during open " + nomef+" : "+e1);
			   }
		 
			xFile.IOseqCloseR();
		 }
	  
	  }}