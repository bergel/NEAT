   package jNeatCommon;


   import java.util.*;
   import java.io.*;
   import jNeatCommon.*;

																public class NeatRoutine {
   
   /**
   * Insert the method's description here.
   * Creation date: (16/01/2002 16.19.56)
   */
	   public static double fsigmoid(double activesum,double slope,double constant) 
	  {
		 return (1/(1+(Math.exp(-(slope*activesum))))); //Compressed
	  }
   
   
   
   
   
   /**
   * neatRoutine constructor comment.
   */
	   public NeatRoutine() 
	  {
		 super();
	  }      
   
	   public static double randfloat() 
	  {
		 return NeatConstant.myRandom.nextDouble();
	  }      
	   public static int randposneg() 
	  {
	  
		 int n = NeatConstant.myRandom.nextInt();
		 if ((n % 2) == 0)
			return -1;
		 else
			return 1;
	  
	  }      
   /**
   * Insert the method's description here.
   * Creation date: (24/01/2002 11.15.43)
   */
   
	   public static double gaussrand() 
	  {
		 return NeatConstant.myRandom.nextGaussian();
	  } 
	   public static int randint(int x, int y) 
	  {
		 int n = NeatConstant.myRandom.nextInt(y - x + 1);
		 return (n + x);
	  }
	   public static double randgauss() 
	  {
		 double  n = NeatConstant.myRandom.nextGaussian();
		 return (n);
	  }
	   public static int randint(int x) 
	  {
		 int n = NeatConstant.myRandom.nextInt(x-1);
		 return (n+1);
	  }}