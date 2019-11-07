   package jGraph;

   import jNeatCommon.*;
																																																																public class Edge {
   // peso della connesisone
	  public double weight;
   // inp node (only a ref)
	  public Vertex in_node;
   
   // out node (only a ref)
	  public Vertex out_node;
   
   // flag for recursion link
	  public boolean is_recursive;
   
   // flag for active
	  public boolean active;
   
   // flag type of connection 
	  public int type;
   
   // id of edge : unico per tutto il grafo
	  public int name;
   
   // differenza in livelli tra nodo in e nodo out
   // se > 1 allora rappresenta il numero di elementi
   // di questo gruppo
	  int distance;
   
   // id gruppo a cui appartiene
   // nel caso di link tra livelli adiacenti = 0
   // nel caso il link collega due nodi con distance > 1
   // identifica il nome del link originale
	  public int id_gruppo;
   
   // segno della connessione
	//  public int sign;
   
   
   /**
   * Edge constructor comment.
   */
	   public Edge() 
	  {
		 super();
		 in_node = null;
		 out_node = null;
		 is_recursive = false;
		 active = false;
		 name = 0;
		 type = 0;
		 weight = 0.0;
		 //sign = +1;
	  
		 id_gruppo = 0;
		 distance = 0;
	  
	  }
   
   
   
   
   /**
   * Insert the method's description here.
   * Creation date: (20/02/2002 9.15.15)
   */
	   public void view() 
	  {
		 System.out.print("\n Edge[" + name + "] from n[" + in_node.name);
		 System.out.print("] --> [" + out_node.name);
		 System.out.print("] , recurs=" + is_recursive);
		 System.out.print(", active=" + active);
	  
		 if (type == CodeConstant.LINE_FC)
			System.out.print(", first chain");
		 if (type == CodeConstant.LINE_MC)
			System.out.print(", middle chain");
		 if (type == CodeConstant.LINE_LC)
			System.out.print(", last chain");
		 if (type == CodeConstant.LINE_SE)
			System.out.print(", single element");
	  
		 if (id_gruppo > 0)
			System.out.print(", element of " + id_gruppo);
	  
		 if (distance > 0)
			System.out.print(" has a grp of" + distance + " link");
	  
	  }
   

	   public Edge(Vertex i, Vertex o, boolean _r, int t,double _w) 
	  {
		 in_node = i;
		 out_node = o;
		 is_recursive = _r;
		 active = true;
		 type = t;
		 name = 0;
		 id_gruppo = 0;
		 distance = 0;
		 weight = _w;
		
	  }}