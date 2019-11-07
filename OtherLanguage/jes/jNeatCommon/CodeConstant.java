   package jNeatCommon;

/**
 * Inserire qui la descrizione del tipo.
 * Data di creazione: (13/01/03 20.35.03)
 * @author: Administrator
 */
	public class CodeConstant {
   
	  public static final int NODO_N     = 0;
	  public static final int NODO_R     = 1;
	  public static final int DESCRIPTOR = 2;
   
   // type of edge
   // ogni linea può essere unica, o maggiore di 1
   //
   
	  public static final int LINE_FC = 10; // line first in sequence   
	  public static final int LINE_H_FC = 20; // line first in sequence   
	  public static final int LINE_H_LC = 22; // line last in sequence == line outer   
	  public static final int LINE_H_MC = 21; // line middle   
	  public static final int LINE_H_SE = 23; // single element  == line outer   
	  public static final int LINE_LC = 12; // line last in sequence == line outer   
	  public static final int LINE_MC = 11; // line middle   
	  public static final int LINE_SE = 13; // single element  == line outer   
	  public static final int VERTEX_BOT_Y = 7; //   vertice + in basso   
	  public static final int VERTEX_MID_Y = 6; //   vertice intermedio   
   // type of virtual node
	  public static final int VERTEX_SNG_Y = 4; //   vertice unico   
	  public static final int VERTEX_TOP_Y = 5; //   vertice + in alto
	  public static final int VERTEX_RECURRENT = 8; //   vertice unico   
   
   
   // type of lines for plotting
	  public static final int LINE_TYPE_1 = 32;
   
   
   
   /**
   * Commento del constructor CodeConstant.
   */
	   public CodeConstant() {
		 super();
	  }
   	  public static final int DESCRIPTOR_CURVE = 3;}