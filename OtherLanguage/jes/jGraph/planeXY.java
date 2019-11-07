   package jGraph;

   import java.util.*;
   import java.text.*;import jNeatCommon.*;
																public class planeXY {
   
	  // contiene oggetti Vertex per allineamento livelli
	  // dopo calcolo intra-connection
	  Vector    vVertex;
   
	  // contiene oggetti  edge che definiscono le connessioni
	  // intra-strato
	  Vector    vEdge;
   
	  // numero di righe reali
	  int dim_y; 
   
	  // numero di colonne reali
	  int dim_x;
   
	  // identificativo livello rappresentato
	  int level;
   
	  // riga iniziale dove sono stati posizionati i nodi
	  // di pari livelli tra loro collegati
	  int offset_y;
   
   
	  // matrice contenente oggetti Vertex disposti
	  // come in un piano XY 
	  Vertex [][]m;
   
   
   
   
   /**
   * planeXY constructor comment.
   */
	   public planeXY() {
		 super();
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	   public void view()
	  {
	  
	  //   String mask3 = " 000";
	  //   DecimalFormat  fmt3 = new DecimalFormat(mask3);
	  //   System.out.print(fmt3.format(_p.name));
	  
		 System.out.print("\n PLANE id["+level+"] , offset from top = "+offset_y+" NODES");
		 System.out.print("\n ----------------------------------------------------------");
	  
	  
		 Iterator itr_Vertex = null;
		 Vertex _p = null;
	  
		 itr_Vertex = vVertex.iterator();
		 while (itr_Vertex.hasNext()) 
		 {
			_p = ((Vertex) itr_Vertex.next());
			System.out.print("\n    node["+_p.name+"] lev="+_p.level+" , altitude="+_p.altitude); 
		 
		 }
	  
	  
		 System.out.print("\n ----------------------------------------------------------");
		 System.out.print("\n                       e d g e");
		 System.out.print("\n ----------------------------------------------------------");
	  
	  
		 Iterator itr_edge  = null;
		 Edge _e = null;
	  
		 itr_edge = vEdge.iterator();
		 while (itr_edge.hasNext()) 
		 {
			_e = (Edge) itr_edge.next();
			System.out.print("\n    edge["+_e.name+"] for link from node : "+_e.in_node.name+" --> "+_e.out_node.name);
		 
		 }
	  
		 System.out.print("\n ----------------------------------------------------------");
		 System.out.print("\n                       p l a n ");
		 System.out.print("\n ----------------------------------------------------------");
	  
	  
		 String mask3 = " 000";
		 DecimalFormat  fmt3 = new DecimalFormat(mask3);
	  
		 for (int j = 0; j < dim_y; j++) 
		 {
			System.out.print("\n row <" + j + "> ");
			for (int i = 0; i < dim_x; i++) 
			{
			
			   _p = m[j][i];
			   if (_p == null)
				  System.out.print("  ..");
			   else
				  System.out.print(fmt3.format(_p.name));
			}
		 }
	  
		 System.out.print("\n -------------------------------------\n\n");
	  
	  
	  
	  
	  
	  }
   
   
   
   
   
   
   
   
   
   
   
   
	   public void updateLevel(int _total_levels)
	  {
	  
		 Iterator itr_Vertex = null;
		 Vertex _p = null;
	  
		 itr_Vertex = vVertex.iterator();
		 while (itr_Vertex.hasNext()) 
		 {
			_p = ((Vertex) itr_Vertex.next());
			_p.level = level + offset_y;
			_p.altitude = _total_levels - (_p.level + 1);
		 
		 }
	  
	  }
   
   
	   public int getLevelOfNodes()
	  {
		 return (level + offset_y);
	  }
   
   
   /**
   * definisce la matrice di Vertex sul layer 'Lev'-esimo
   * nell'asse x vengono disposti i vari punti mentre
   * nell'asse y le estensioni per visualizzare le 
   * connessioni intrastrato;
   * alla fine ho un rettangolo con dimensione pari a :
   *   righe = numero di connessioni intrastrato
   *   colonne = numero di Vertexs;
   */
	   public planeXY(Vector v,int _level, int row) 
	  {
		 Vertex _p = null;
		 int _x = 0;
		 int _y = 0;
		 Iterator itr_Vertex = null;
		 itr_Vertex = v.iterator();
	  
		 vVertex = new Vector(1,0);
		 vEdge  = new Vector(1,0);
	  
		 dim_y = row;
		 level = _level;
	  
	  
	  
		 offset_y = (int) (row / 2);
	  //   if (offset_y == 0)
	  //	   offset_y = 1;
	  
		 itr_Vertex = v.iterator();
		 while (itr_Vertex.hasNext()) 
		 {
			_p = ((Vertex) itr_Vertex.next());
			if (_p.type == NeatConstant.OUTPUT)
			   offset_y = 0;
			vVertex.add(_p);	  
		 }
	  
	  
	  }
	   public int computeFreeLevel() 
	  {
	  
		 Vertex _p = null;
		 int _y = 0;
		 int axial = 0;
		 int rotore = 0;
		 boolean free;
	  
		 for (int j = 0; j < dim_y * 2; j++) 
		 {
			axial = GrafRoutine.spiral(j) + offset_y;
		 //	  System.out.print("\n  verifico se libero axial("+j+") = "+axial+  "    (con offset="+offset_y);
		 
			if ((axial >= 0) && (axial < dim_y))
			{
			//	 System.out.print("\n     okay axial nei limiti...");
			   free = true;
			   for (int i = 0; i < dim_x; i++) 
			   {
			   
				  _p = m[axial][i];
				  if (_p != null) 
				  {
					 free = false;
				  //				System.out.print("\n      riga "+axial+" occupata! vedo next...");
					 break;
				  }
			   }
			   if (free)
				  break;
			
			}
		 
		 
		 
		 
		 }
	  
	  
		 if (offset_y > axial)
			return -(offset_y-axial);
		 else
			return (axial-offset_y);
	  
	  
	  }
	   public void elimina()
	  {
		 vVertex.removeAllElements();
	  }
	   public void setPlane(int _r,Vertex [] _v)
	  {
	  
		 Vertex _p = null;
		 dim_x = _r;
		 int clev = 0;
	  
		 m = new Vertex[dim_y][dim_x];
	  
		 for (int j=0; j< dim_y; j++)
		 {
			for (int i=0; i < dim_x; i++)
			{
			   if ((j == offset_y) && (_v[i] != null))
			   {
				  m[j][i] = _v[i];
			   }
			   else
				  m[j][i] = null;
			
			}
		 }
	  
	  }}