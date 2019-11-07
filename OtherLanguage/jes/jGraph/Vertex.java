   package jGraph;

   import java.awt.geom.*;
   import java.util.*;
   import jNeatCommon.*;

																public class Vertex{
   
   // x coordinate
	  public int x;
   
   // y coordinate
	  public int y;
   
   // id_node ( is the same of NNode id) (era id)
	  public int name;
   
   // type of node 
	  public int  type;
   
   // used for valorize lambda of this Vertex
	  public int level;
   
   // flag for control if node is traversed
	  public boolean is_traversed;
   
   // depth from this node to sensor
	  public int altitude;	
   
   // vector with incoming node
	  public Vector neighbors_inp;	
   
   // vector with outgoing node
	  public Vector neighbors_out;
   
   
   
   
   // identificativo edge che collega questo Vertex	utilizzato solo dai nodi virtuali  
	  public int edge_id; 
   
   // baricentro
	  public int barycentre;
   
   // baricentro
	  public int gx;
   
   // oggetto di tipo Vertex a cui è riferito;
   // utilizzato per link recurrent 
	  public Vertex Vertex_ref;
   
   // oggetto di tipo edge a cui è riferito;
   // utilizzato in  link recurrent per sapere se il link
   // è o meno positivo e altre informazioni
	  public Edge edge_ref;
   
   // utlizzato per calcolare altitude e quote
	  int zeta; 
   
   /**
   * Vertex constructor comment.
   */
	   public Vertex() {
		 super();
		 level = -1;
		 gx = -1;
		 altitude =  -1;
		 Vertex_ref = null;
		 edge_ref = null;
		 zeta = -1;
	  }
   
   /**
   * Insert the method's description here.
   * Creation date: (18/02/2002 9.31.22)
   * @param x int
   * @param y int
   */
	   public Vertex(int _x, int _y) 
	  {
		 x = _x;
		 y = _y;
		 type = 0;
		 level = -1;
		 gx = -1;
		 Vertex_ref = null;
		 edge_ref = null;
	  
		 altitude = -1;
		 zeta = -1;
	  
	  
	  
	  
	  
	  }	 
   
   /**
   * Insert the method's description here.
   * Creation date: (18/02/2002 9.31.22)
   * @param x int
   * @param y int
   */
	   public Vertex(int _x, int _y,int _t)
	  {
		 x = _x;
		 y = _y;
		 type = _t;
		 level = -1;
		 gx = -1;
		 Vertex_ref = null;
		 edge_ref = null;
		 altitude = -1;
		 zeta = -1;
	  
	  
	  
	  }
   
   /**
   * costruttore 
   */
	   public Vertex(int _x, int _y, int _t, int _id) 
	  {
		 x = _x;
		 y = _y;
		 type = _t;
		 name = _id;
		 is_traversed = false;
		 altitude = -1;
		 neighbors_inp = new Vector(1, 0);
		 neighbors_out = new Vector(1, 0);
		 level = -1;
		 Vertex_ref = null;
		 edge_ref = null;
		 zeta = -1;
		 gx = -1;
	  
	  }
   
   
   /**
   * visualizzxa il nodo 
   */
	   public void view() {
		 Vertex _Vertex = null;
		 Iterator itr_Vertex = null;
	  
		 System.out.print("\n Vertex ID=" + name);
		 System.out.print(", (x=" + x + " , y=" + y);
		 System.out.print("), edge_id=" + edge_id);
		 if (type == NeatConstant.INPUT)
			System.out.print(", SENSOR/BIAS");
		 if (type == NeatConstant.HIDDEN)
			System.out.print(", HIDDEN");
		 if (type == NeatConstant.OUTPUT)
			System.out.print(", OUTPUT");
	  
		 if (type == CodeConstant.VERTEX_BOT_Y)
			System.out.print(", VIRTUAL-bottom");
		 if (type == CodeConstant.VERTEX_MID_Y)
			System.out.print(", VIRTUAL-middle");
		 if (type == CodeConstant.VERTEX_SNG_Y)
			System.out.print(", VIRTUAL-single");
		 if (type == CodeConstant.VERTEX_TOP_Y)
			System.out.print(", VIRTUAL-top");
		 if (type == CodeConstant.VERTEX_RECURRENT)
			System.out.print(", NODO RECURRENT");
	  
		 System.out.print(", lev=" + level);
		 System.out.print(", alt=" + altitude);
		 System.out.print(", zeta=" + zeta);
	  
		 if (neighbors_inp.size() > 0) {
			System.out.print(", input from : ");
			itr_Vertex = neighbors_inp.iterator();
			while (itr_Vertex.hasNext()) {
			   _Vertex = ((Vertex) itr_Vertex.next());
			   System.out.print(" n[" + _Vertex.name + "]");
			}
		 }
	  
	  }
   
   
   
   
   
   /**
   *
   *
   */
	   public int depth_back(int depth, int max_depth) 
	  {
	  
	  // control for loop 
		 if (depth > 100) 
		 {
		 //			System.out.print("\n  BACK : ** loop **");
			return 10;
		 }
	  //		 System.out.print("\n  BACK :  ---------  entro con depth "+depth+"  per input to node : "+name);
	  //
	  
	  
	  
		 if (type == NeatConstant.INPUT)
		 {
		 //			System.out.print("\n  BACK :    SENSOR  "+name+" has level  "+depth);
		 
			return depth;
		 }
	  
	  
	  
		 if ((neighbors_inp.size() == 0)  )
		 {
		 //			System.out.print("\n  BACK :    PSEUDO-SENSOR  "+name+" has level  "+depth);
		 
			return -1;
		 //			return max_depth;
		 }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
		 depth++;
	  
	  //		 System.out.print("\n  BACK :    ...search incoming in level "+depth);
	  // def iteratore per nodi di ingresso al presente
		 Iterator itr_Vertex = neighbors_inp.iterator();
	  
		 int current_depth = 0; //The depth of the current node
		 while (itr_Vertex.hasNext()) 
		 {
			Vertex _Vertex = ((Vertex) itr_Vertex.next());
		 
			if (!(_Vertex.is_traversed))
			{
			   _Vertex.is_traversed = true;
			   current_depth = _Vertex.depth_back(depth,max_depth);
			//			   System.out.print("\n  BACK :  curr dep return for "+_Vertex.name+" = "+current_depth);
			
			   if (current_depth > 0)
				  _Vertex.altitude = current_depth - depth;
			} 
			else
			   current_depth = depth + _Vertex.altitude;
			if (current_depth > max_depth)
			   max_depth = current_depth;
		 }
		 return max_depth;
	  
	  
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   /**
   *
   *
   */   
	   public int depth_forw(int depth,int max_depth) 
	  {
	  
	  // control for loop 
		 if (depth > 100) 
		 {
		 //			System.out.print("\n  BACK : ** loop **");
			return 10;
		 }
	  //		 System.out.print("\n  FORW :  ---------  entro con depth "+depth+"  per input to node : "+name);
	  //
		 if (type == NeatConstant.OUTPUT)
		 {
		 //			System.out.print("\n  BACK :    SENSOR  "+name+" has level  "+depth);
			return depth;
		 }
	  
	  
	  
		 if ( (neighbors_out.size() == 0) )
		 {
		 //			System.out.print("\n  BACK :    SENSOR  "+name+" has level  "+depth);
			return -1;
		 //			return depth; 
		 }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
		 depth++;
	  
	  //		 System.out.print("\n  BACK :    ...search incoming in level "+depth);
	  // def iteratore per nodi di ingresso al presente
		 Iterator itr_Vertex = neighbors_out.iterator();
	  
		 int current_depth = 0; //The depth of the current node
		 while (itr_Vertex.hasNext()) 
		 {
			Vertex _Vertex = ((Vertex) itr_Vertex.next());
		 
			if (!(_Vertex.is_traversed))
			{
			   _Vertex.is_traversed = true;
			   current_depth = _Vertex.depth_forw(depth,max_depth);
			//			   System.out.print("\n  BACK :  curr dep return for "+_Vertex.name+" = "+current_depth);
			   if (current_depth > 0)
				  _Vertex.level = current_depth - depth;
			} 
			else
			   current_depth = depth + _Vertex.level;
			if (current_depth > max_depth)
			   max_depth = current_depth;
		 }
		 return max_depth;
	  
	  }	
   
   
   
	   public boolean has_child(Vertex _p) 
	  {
	  
	  
		 for (int i = 0; i < neighbors_inp.size(); i++) 
		 {
			Vertex _x = (Vertex) neighbors_inp.elementAt(i);
			if ( _x.name == _p.name)
			   return true;
		 }
		 return false;
	  }
   
   
   
   
   
	   public boolean is_virtual() 
	  {
	  
		 if ((type == CodeConstant.VERTEX_BOT_Y) ||
		 (type == CodeConstant.VERTEX_MID_Y) ||
		 (type == CodeConstant.VERTEX_SNG_Y) ||
		 (type == CodeConstant.VERTEX_TOP_Y))
			return true;
	  
		 return false;     
	  } 
   
   
	   public boolean is_real() 
	  {
	  
		 if ((type == NeatConstant.SENSOR) ||
		 (type == NeatConstant.OUTPUT) ||
		 (type == NeatConstant.NEURON))
			return true;
	  
		 return false;     
	  }
   
   
	   public boolean is_recurrent() 
	  {
	  
		 if (type == CodeConstant.VERTEX_RECURRENT)
			return true;
	  
		 return false;     
	  }   /**
   * costruttore 
   */                                                                           
	   public Vertex(int _x, int _y, int _t, int _id, Vertex _p, Edge _e) {
		 x = _x;
		 y = _y;
		 type = _t;
		 name = _id;
		 is_traversed = false;
		 altitude = -1;
		 neighbors_inp = new Vector(1, 0);
		 neighbors_out = new Vector(1, 0);
		 level = -1;
		 Vertex_ref = _p;
		 edge_ref = _e;
		 gx = -1;
		 zeta = -1;
	  
	  }  }