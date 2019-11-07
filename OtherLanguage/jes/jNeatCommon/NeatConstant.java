   package jNeatCommon;

   import java.util.*;

																public class NeatConstant {
   
	  public static final int SIGMOID = 0;
   //gen_node_label
	  public static final int HIDDEN = 0;
	  public static final int INPUT = 1;
	  public static final int OUTPUT = 2;
	  public static final int BIAS = 3;
   // node type
	  public static final int NEURON = 0;
	  public static final int SENSOR = 1;
	  public static final int GAUSSIAN = 0; //This adds Gaussian noise to the weight
	  public static final int COLDGAUSSIAN = 1;
	  public static final int NEWNODE = 0;
	  public static final int NEWLINK = 1;
   
	  public static Random myRandom = new Random();
	  public static final double MAXINT = Math.pow(2, 31);
   
   
   
   // variables for experiment or other
	  public static final int COLD = 1;   	public static final int EMER = 2;
   
   
   /**
   * Commento del constructor NeatConstant.
   */
	   public NeatConstant() {
		 super();
	  }
   }