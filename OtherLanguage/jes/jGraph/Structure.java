   package jGraph;

   import java.util.*;

   import jneat.*;
   import jNeatCommon.*;																
																import java.text.*;																public class Structure {
   
	 // vettore contenente ref a oggetti Vertex
	  public Vector vVertex;
   
	 // vettore contenente ref a oggetti di tipo connection
	  public Vector vEdge;
   
	 // vettore con ref a nodi output
	  Vector vOut;
   
	 // vettore con ref a nodi sensor
	  Vector vInp;
   
	 // numero di livelli ( = altitude + 1)
	  int depth_y;
   
	 // numero massimo di elementi su uno stesso livello
	  int depth_x;
   
	 // primo nome nodo disponibile
	  int hi_node_name;
   
	 // primo nome edge disponibile
	  int hi_edge_name;
   
   
	 // vettore contenente ref a oggetti plane
	 // tale vettore una volta costruito servirà per comporre
	 // la matrice di rappresentazione del grafo
	  Vector vPlane;
   
	 // vettore contenente oggetti stringa 
	  Vector vString;
   
   
   
   
   
   
   
   
   
   
   
   /**
   * Structure constructor 
   */
	   public Structure() {
		 super();
		 vVertex  = new Vector(1,0);
		 vEdge   = new Vector(1,0);
		 vOut    = new Vector(1,0);
		 vInp    = new Vector(1,0);
		 vPlane  = new Vector(1,0);
		 vString = new Vector(1,0);
		 depth_y= 0;
	  
	  }
   
   /**
   * crea in base alle connection definite
   * le liste incoming - outgoing per 
   * ogni nodo
   */
   
	   public void genesis() 
	  {
		 Iterator itr_edge = null;
		 Edge _edge = null;
		 Vertex iVertex = null;
		 Vertex oVertex = null;
	  
	  // reset precedenti incoming-outgoing link presenti
	  //         unmarkNeighbors();
	  
		 itr_edge = vEdge.iterator();
		 while (itr_edge.hasNext()) 
		 {
			_edge = ((Edge) itr_edge.next());
			if (_edge.active) 
			{
			   iVertex = _edge.in_node;
			   oVertex = _edge.out_node;
			
			   if (iVertex != oVertex)
			   {
				  oVertex.neighbors_inp.add(iVertex);
				  iVertex.neighbors_out.add(oVertex);
			   }
			   else
			   {
			   //			System.out.print("\n skip node "+iVertex.name+" cause recursion");
			   }
			}
		 }
	  
		 Comparator cmp = new order_inner();
		 Collections.sort(vVertex, cmp);
	  
	  }
	   public void LoadGenome(String nomeFile) {
	  
		 StringTokenizer st;
		 String curword;
		 String xline;
		 String fnamebuf;
		 IOseq xFile;
//		 int  sign_of_link = 0;
		 int _genome_id = 0;
		 int _type = 0;
		 int _id = 0;
		 int progr = 1;
		 double weight = 0.0;
		 hi_node_name = 0;
		 Iterator itr_Vertex = null;
	     boolean done;	  
		 xFile = new IOseq(nomeFile);
		 boolean ret = xFile.IOseqOpenR();
	  
		 if (ret)
		 {
		 
		 
		 
			try 
			{

			   // read genome but wait the end of comment in
			   // if present

				 done = false;
		         while (!done) 
		         {
			           xline = xFile.IOseqRead();
		               st = new StringTokenizer(xline);
			           curword = st.nextToken();
			           if (curword.equalsIgnoreCase("genomestart")) 
			           {
			              //    id of genome can be readed
			              curword = st.nextToken();
			              //	System.out.print("\n curword = "+curword);
			              _genome_id = Integer.parseInt(curword);
			              done = true;
			            }

			
			            else if (curword.equals("/*")) 
			            {
			                curword = st.nextToken();
			                while (!curword.equals("*/"))
				                 curword = st.nextToken();
			            }
		         }
		         
		         
			   progr = 1;
			   done = false;
			
  			   
			   while (!done) 
			   {
			   
				  xline = xFile.IOseqRead();
				  st = new StringTokenizer(xline);
				  curword = st.nextToken();
			   
				  if (curword.equalsIgnoreCase("genomeend")) 
				  {
					 curword = st.nextToken();
					 if (Integer.parseInt(curword) != _genome_id)
						System.out.println(" *ERROR* id mismatch in genome");
					 done = true;
				  } 
				  
				  else if (curword.equals("/*")) 
				  {
					 curword = st.nextToken();
					 while (!curword.equals("*/")) 
					 {
						System.out.print(" " + curword);
						curword = st.nextToken();
					 }
					 System.out.print("\n");
				  
				  } 
				  
				  else if (curword.equals("node")) 
				  {
					 Vertex newVertex;
				  //Get the node parameters
					 curword = st.nextToken();
					 _id = Integer.parseInt(curword);
				  //skip trait
					 curword = st.nextToken();
				  //get type of node
					 curword = st.nextToken();
					 int type1 = Integer.parseInt(curword);
				  //get genetic type of node
					 curword = st.nextToken();
					 int type2 = Integer.parseInt(curword);
				  
				  
				  
					 if (type1 == NeatConstant.SENSOR)
					 {
						_type =  NeatConstant.INPUT;
					 }
					 else 
					 {
						if (type2 == NeatConstant.OUTPUT)
						   _type =  NeatConstant.OUTPUT;
						else
						   _type =  NeatConstant.HIDDEN;
					 }
					 newVertex = new Vertex(0, 0, _type, _id);
				  
					 if (_id > hi_node_name)
						hi_node_name = _id;
				  
					 vVertex.addElement(newVertex);
					 if (_type == NeatConstant.SENSOR)
						vInp.add(newVertex);
					 else if (_type == NeatConstant.OUTPUT)
						vOut.add(newVertex); 
				  
				  
				  } // end block Vertex
				  
				  else if (curword.equals("gene")) 
				  {
				  
					 Vertex iVertex = null;
					 Vertex oVertex = null;
				  
				  //skip trait_id
					 curword = st.nextToken();
				  
				  //Get input node
					 curword = st.nextToken();
					 int inode_num = Integer.parseInt(curword);
				  
				  //Get output node
					 curword = st.nextToken();
					 int onode_num = Integer.parseInt(curword);
				  
				  //get weight
					 curword = st.nextToken();
					 weight = Double.parseDouble(curword);
				  
				  //Get recur
					 curword = st.nextToken();
					 boolean recur = Integer.parseInt(curword) == 1 ? true : false;
				  
				  //skip innovation num
					 curword = st.nextToken();
				  
				  //skip mutation num
					 curword = st.nextToken();
				  
				  //Get enable
					 curword = st.nextToken();
					 boolean enable = Integer.parseInt(curword) == 1 ? true : false;



				  // attention : only the link enabled as traced	 
				  // ===========================================
				  if (enable) 
					 {
						int fnd = 0;
						itr_Vertex = vVertex.iterator();
						while (itr_Vertex.hasNext() && fnd < 2) 
						{
						   Vertex _Vertex = ((Vertex) itr_Vertex.next());
						   if (_Vertex.name == inode_num) 
						   {
							  iVertex = _Vertex;
							  fnd++;
						   }
						   if (_Vertex.name == onode_num) 
						   {
							  oVertex = _Vertex;
							  fnd++;
						   }
						}
						Edge newedge;
					 
						newedge = new Edge(iVertex, oVertex, recur,CodeConstant.LINE_SE,weight);
					 //		 iVertex.edge_id = progr;
					 //		 oVertex.edge_id = progr;
					 
						iVertex.edge_id = 0;
						oVertex.edge_id = 0;
						newedge.name = progr++;
					 
						vEdge.addElement(newedge);
					 }
				  
				  } // end block gene
			   
			   
			   } // end  block while
			
			
			} 
				catch (Throwable exc) {
				  System.err.println(exc + " : error during open " + nomeFile);
			   }
		 
			xFile.IOseqCloseR();
		 
		 // primo nome disponibile per nodi virtual
			hi_node_name++;
			hi_edge_name = progr;
		 
		 }
		 
		 
		 else
			System.err.print("\n : error during open " + nomeFile);
	  
	  
	  
	  
	  
	  
	  
	  
	  }
   
   
   /**
   * Insert the method's description here.
   * Creation date: (20/02/2002 11.56.44)
   */
   
	   public void view() 
	  {
		 Iterator itr_Vertex = null;
		 Iterator itr_edge = null;		 
		 Iterator itr_plane = null;
		 Edge _edge  = null;
		 Vertex _Vertex = null;
		 planeXY _plane = null;
	  
	  
		 System.out.print("\n                     NODES IN NET:");
		 System.out.print("\n  ---------------------------------------------------------");
		 itr_Vertex = vVertex.iterator();
		 while (itr_Vertex.hasNext()) 
		 {
			_Vertex = ((Vertex) itr_Vertex.next());
			_Vertex.view();
		 }
	  
	  
		 System.out.print("\n                     EDGE IN NET  ");
		 System.out.print("\n  ---------------------------------------------------------");
		 itr_edge = vEdge.iterator();
		 while (itr_edge.hasNext()) 
		 {
			_edge = ((Edge) itr_edge.next());
			_edge.view();
		 }
	  
	  
	  
		 System.out.print("\n                     PLANE IN NET:");
		 System.out.print("\n  ---------------------------------------------------------");
		 itr_plane = vPlane.iterator();
		 while (itr_plane.hasNext()) 
		 {
			_plane = (planeXY) itr_plane.next();
			_plane.view();
		 
		 }
	  
	  
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   /**
   * Insert the method's description here.
   * Creation date: (21/02/2002 15.54.06)
   */        
	   public void unMarkAll()
	  {
		 Vertex _Vertex = null;
		 for (int j=0; j < vVertex.size(); j++) 
		 {
			_Vertex = (Vertex) vVertex.elementAt(j);
			_Vertex.level = -1;
			_Vertex.altitude = -1;
			_Vertex.zeta = -1;
			_Vertex.is_traversed = false;
		 }
	  }
   
   /**
   * Insert the method's description here.
   * Creation date: (21/02/2002 15.54.06)
   */
	   public void unMarkSingle()
	  {
		 Vertex _Vertex = null;
		 for (int j=0; j < vVertex.size(); j++) 
		 {
			_Vertex = (Vertex) vVertex.elementAt(j);
			_Vertex.is_traversed = false;
		 }
	  }
   
   
   /**
   * Insert the method's description here.
   * Creation date: (21/02/2002 15.54.06)
   */
	   public void unmarkNeighbors()
	  {
		 Vertex _Vertex = null;
		 for (int j=0; j < vVertex.size(); j++) 
		 {
			_Vertex = (Vertex) vVertex.elementAt(j);
			_Vertex.neighbors_inp.clear();
			_Vertex.neighbors_out.clear();
		 }
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	   public void generate_virtual_Vertex_Edge_vertical() 
	  {
	  
		 Iterator itr_edge = null;
		 Edge _edge = null;
		 Vertex _inode = null;
		 Vertex _onode = null;
		 Vertex from_node = null;
		 Vertex to_node = null;
		 Edge virtE = null;
		 Vertex virtP = null;
		 int diff_level = 0;
		 int curr_edge_id = 0;
		 Iterator itr_plane;
		 planeXY _plane;
	  
		 boolean found;
	  
		 Vector tmp = new Vector(1, 0);
	  
	  
	  
		 itr_edge = vEdge.iterator();
	  
	  
		 while (itr_edge.hasNext()) 
		 {
			_edge = ((Edge) itr_edge.next());
			_inode = _edge.in_node;
			_onode = _edge.out_node;
			curr_edge_id = _edge.name;
		 
		 //	  System.out.print("\n ho letto edge con inode = " + _inode.name + " lev=" + _inode.level); 
		 //	  System.out.print(" , onode = " + _onode.name + " lev=" + _onode.level);
		 
			diff_level = Math.abs(_inode.level - _onode.level);
		 
		 
			if (diff_level == 0 )
			{
			
			
			
			//	      System.out.print("\n ** DISATTIVO ** link tra inode = " + _inode.name + " lev=" + _inode.level); 
			//	      System.out.print(" , onode = " + _onode.name + " lev=" + _onode.level);
			   _edge.active = false;
			
			
			   if (_inode.name == _onode.name)
			   {
				  virtP = new Vertex(0, 0, 4, hi_node_name++,_inode,_edge);
				  virtP.edge_id = 0;
				  virtP.level = 0;
				  virtP.altitude = 0;
				  virtP.type = CodeConstant.VERTEX_RECURRENT;
			   //			  System.out.print("\n creato recurrent node : "+virtP.name+" per nodo "+_inode.name);
				  vVertex.add(virtP);
			   //		      _edge.active = true;
			   }
			   
			   else
			   {
			   
			   
				  itr_plane = vPlane.iterator();
				  found = false;
				  _plane = null;
				  while (itr_plane.hasNext()) 
				  {
					 _plane = (planeXY) itr_plane.next();
					 if (_plane.getLevelOfNodes() == _inode.level)
					 {
						found = true;
						break;
					 }    
				  }
			   
				  if (!found)
				  {
					 System.out.print("\n *ALERT* : non trovato laver per nodo :"+_inode.name);
				  }
				  
				  else
				  {
				  //			 System.out.print("\n okay  add edge : "+_edge.name);
					 _plane.vEdge.add(_edge);			 
				  }
			   
			   
			   
			   
			   
			   }
			
			
			} 
			
			
			
			else if (diff_level == 1) 
			{
			   _edge.type = CodeConstant.LINE_SE;
			} 
			
			else if (diff_level > 1)
			
			{
			   _edge.active = false;
			   _edge.distance = diff_level;
			//		 System.out.print("\n diff = " + diff_level);
			
			
			// curr_edge_id = nome del lato che si stà analizzando
			   curr_edge_id = _edge.name;
			
			
			
			// tratta il caso di nodi forw
			//
			   if (_inode.level > _onode.level) 
			   {
			   //			System.out.print("\n Tratto  LINK NORMALE FORWARDING");
				  from_node = _inode;
				  to_node = _onode;
			   
				  Vertex last_to_node = to_node;
			   
				  boolean first = true;
				  for (int livello = (to_node.level + 1); livello < from_node.level; livello++) 
				  {
				  
					 virtP = new Vertex(0, 0, 4, hi_node_name++);
				  
				  // dato che il nodo è connesso in una catena singola ed unica
				  // il group id dei vertici è il nome del lato che li connette
					 virtP.edge_id = curr_edge_id;
				  
					 virtP.level = livello;
					 virtP.altitude = last_to_node.altitude - 1;
				  
				  //			   System.out.print("\n added node virtual[" + curr_edge_id + "] con level=" + virtP.level); 
				  //			   System.out.print(", altitudine=" + virtP.altitude);
				  //			   System.out.print(", type=" + virtP.type);
					 vVertex.add(virtP);
				  
					 if (first) 
					 {
					 //				  System.out.print("\n    added outer link " + curr_edge_id);
						virtP.type = CodeConstant.VERTEX_TOP_Y;
						virtE = new Edge(virtP, last_to_node, false, 0,_edge.weight);
						virtE.type = CodeConstant.LINE_LC;
						virtE.name = hi_edge_name++;
					 
					 // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
					 // deve essere quello del lato che viene sostituito
						virtE.id_gruppo = _edge.name;
						first = false;
					 } 
					 
					 else 
					 
					 {
					 //				  System.out.print("\n    added outer link " + curr_edge_id);
						virtP.type = CodeConstant.VERTEX_MID_Y;
						virtE = new Edge(virtP, last_to_node, false, 0,_edge.weight);
						virtE.type = CodeConstant.LINE_MC;
					 
					 // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
					 // deve essere quello del lato che viene sostituito
						virtE.name = hi_edge_name++;
						virtE.id_gruppo = _edge.name;
					 }
				  
					 tmp.add(virtE);
				  //			   System.out.print("\n    added new link from " + virtP.name + " --> " + last_to_node.name); 
					 last_to_node = virtP;
				  }
			   
			   
				  if (diff_level == 2)
					 virtP.type = CodeConstant.VERTEX_SNG_Y;
				  else
					 virtP.type = CodeConstant.VERTEX_BOT_Y;
			   
			   
				  virtE = new Edge(from_node, last_to_node, false, 0,_edge.weight);
				  virtE.type = CodeConstant.LINE_FC;
			   
			   // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
			   // deve essere quello del lato che viene sostituito
				  virtE.name = hi_edge_name++;
				  virtE.id_gruppo = _edge.name;
			   //			System.out.print("\n    added last new link from " + from_node.name + " --> " + last_to_node.name); 
				  tmp.add(virtE);
			   //			System.out.print("\n    okay");
			   }
			   
			   // tratta il caso di nodi recurrent
			   //
			   else 
			   
			   {
			   //			System.out.print("\n Tratto  LINK RECURRENT");
				  from_node = _inode;
				  to_node = _onode;
			   
				  Vertex last_to_node = from_node;
			   
				  boolean first = true;
				  for (int livello = (from_node.level + 1); livello < to_node.level; livello++) 
				  {
					 virtP = new Vertex(0, 0, 4, hi_node_name++);
				  
				  // dato che il nodo è connesso in una catena singola ed unica
				  // il group id dei vertici è il nome del lato che li connette
					 virtP.edge_id = curr_edge_id;
				  
					 virtP.level = livello;
					 virtP.altitude = last_to_node.altitude - 1;
				  //			   System.out.print("\n added node virtual[" + curr_edge_id + "] con level=" + virtP.level); 
				  //			   System.out.print(", altitudine=" + virtP.altitude);
					 vVertex.add(virtP);
				  
					 if (first) 
					 {
					 //				  System.out.print("\n    added inner link " + curr_edge_id);
						virtP.type = CodeConstant.VERTEX_TOP_Y;
						virtE = new Edge(last_to_node, virtP, false, 0,_edge.weight);
						virtE.type = CodeConstant.LINE_FC;
					 
					 // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
					 // deve essere quello del lato che viene sostituito
						virtE.name = hi_edge_name++;
						virtE.id_gruppo = _edge.name;
						first = false;
					 } 
					 
					 else 
					 {
					 //				  System.out.print("\n    added outer link " + curr_edge_id);
						virtP.type = CodeConstant.VERTEX_MID_Y;
						virtE = new Edge(last_to_node, virtP, false, 0,_edge.weight);
						virtE.type = CodeConstant.LINE_MC;
					 
					 // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
					 // deve essere quello del lato che viene sostituito
						virtE.name = hi_edge_name++;
						virtE.id_gruppo = _edge.name;
					 }
				  
					 tmp.add(virtE);
				  //			   System.out.print("\n    added new link from " + last_to_node.name + " --> " + virtP.name); 
					 last_to_node = virtP;
				  }
			   
			   
			   
				  if (diff_level == 2)
					 virtP.type = CodeConstant.VERTEX_SNG_Y;
				  else
					 virtP.type = CodeConstant.VERTEX_BOT_Y;
			   
				  virtE = new Edge(last_to_node, to_node, false, 0,_edge.weight);
			   
			   // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
			   // deve essere quello del lato che viene sostituito
				  virtE.type = CodeConstant.LINE_LC;
				  virtE.name = hi_edge_name++;
				  virtE.id_gruppo = _edge.name;
			   //			System.out.print("\n    added last new link from " + last_to_node.name + " --> " + to_node.name); 
				  tmp.add(virtE);
			   //          System.out.print("\n    okay");
			   
			   }
			
			}
		 }
	  
	  
	  
	  //   System.out.print("\n ********************* TERMINATO VIRTUAL **************************");
	  
	  // add new link
		 itr_edge = tmp.iterator();
		 while (itr_edge.hasNext()) {
			_edge = ((Edge) itr_edge.next());
			vEdge.add(_edge);
		 }
	  
	  }	                         
	   public void generate_planeXY() 
	  {
		 Vector tmp = new Vector(1,0);
		 int xdim;
	  
		 int ydim;
		 Iterator itr_plane = null;	
		 itr_plane = vPlane.iterator();
		 planeXY _plane = null;
	  
	  
	  //   System.out.print("\n genera i piani XY in base alla matXY appena generata");
		 while (itr_plane.hasNext()) {
			_plane = ((planeXY) itr_plane.next());
		 
			if (_plane.vEdge.size() > 0)
			{
			   xdim = v_mXY.dim_x;
			   ydim = _plane.getLevelOfNodes();
			
			//	     System.out.print("\n Il piano di livello "+_plane.level +" ha "+_plane.dim_y+" righe  e avrà "+xdim+" colonne");
			   _plane.setPlane(xdim,v_mXY.m[ydim]);
			}
			else
			{
			   _plane.elimina();
			   tmp.add(_plane);
			}
		 }
	  
	  
	  // clear plane with non intra-connection
		 itr_plane = tmp.iterator();
		 while (itr_plane.hasNext()) {
			_plane = ((planeXY) itr_plane.next());
			vPlane.remove(_plane);
		 }
	  
	  //   System.out.print("\n generazione piani terminata");
	  
	  
	  
	  }
	   public void update_new_level() 
	  {
		 Iterator itr_plane = null;	
		 itr_plane = vPlane.iterator();
		 planeXY _plane = null;
	  
		 while (itr_plane.hasNext()) {
			_plane = ((planeXY) itr_plane.next());
			_plane.updateLevel(depth_y);
		 }
	  
	  }	   
   
	   public void generate_virtual_Vertex_Edge_horizontal() 
	  {
	  
	  
		 Iterator itr_plane = null;
		 Iterator itr_edge = null;
		 itr_plane = vPlane.iterator();
	  
		 planeXY _plane = null;
		 Vector tmp = new Vector(1, 0);
		 int diff_level;
		 Edge _edge = null;
		 Edge virtE = null;
		 Vertex _inode = null;
		 Vertex _onode = null;
		 int curr_edge_id;
		 Vertex last_to_node;
		 Vertex from_node;
		 Vertex to_node;
		 Vertex virtP;
		 int delta;  
		 boolean first;
	  
	  //   System.out.print("\n GENERAZIONE    VIRTUAL Vertex    ORIZZONTALI");
		 while (itr_plane.hasNext()) {
			_plane = ((planeXY) itr_plane.next());
			if (_plane.vEdge.size() > 0)
			{
			
			   itr_edge = _plane.vEdge.iterator();
			   while (itr_edge.hasNext()) 
			   {
				  _edge = ((Edge) itr_edge.next());
				  _inode = _edge.in_node;
				  _onode = _edge.out_node;
			   
			   //	        System.out.print("\n ho letto edge con inode = " + _inode.name); 
			   //	        System.out.print(" , onode = " + _onode.name + " x=" + _onode.gx);
				  diff_level = Math.abs(_inode.gx - _onode.gx);
			   //	        System.out.print("\n diff = "+diff_level);
			   
				  if (diff_level == 1)
				  {
					 _edge.active = false;
				  }		    
			   
				  if (diff_level > 1)
				  {
					 _edge.active = false;
					 _edge.distance = diff_level;
				  //		       System.out.print("\n diff = " + diff_level);
				  
					 delta = _plane.computeFreeLevel();
				  
				  //		       System.out.print("\n delta calcolato = "+delta);
				  
				  // curr_edge_id = nome del lato che si stà analizzando
					 curr_edge_id = _edge.name;
					 virtP = null;
				  
				  // tratta il caso di nodi da sinistra a destra
				  //
					 if (_inode.gx < _onode.gx)
					 {
					 //			      System.out.print("\n Tratto  da sinistra a destra");
						from_node = _inode;
						to_node = _onode;
						last_to_node = from_node;
					 
						first = true;
						for (int colonna = (from_node.gx + 1); colonna < to_node.gx; colonna++) 
						{
						
						   virtP = new Vertex(0, 0, 4, hi_node_name++);
						// dato che il nodo è connesso in una catena singola ed unica
						// il group id dei vertici è il nome del lato che li connette
						   virtP.edge_id = curr_edge_id;
						   virtP.gx = colonna;
						
						   virtP.level = from_node.level + delta;
						   virtP.altitude = from_node.altitude - delta;
						
						//			          System.out.print("\n added node virtual Horizontal[" + virtP.name + "] con level=" + virtP.level); 
						//			          System.out.print(", altitudine=" + virtP.altitude);
						//			          System.out.print(", gx=" + virtP.gx);
						   _plane.m[delta+_plane.offset_y][colonna] = virtP;
						
						
						
						   if (first) 
						   {
						   //				        System.out.print("\n    added inner link " + curr_edge_id);
							  virtP.type = CodeConstant.VERTEX_BOT_Y;
							  virtE = new Edge(last_to_node, virtP,false, 0,_edge.weight);
							  virtE.type = CodeConstant.LINE_H_FC;
							  virtE.name = hi_edge_name++;
						   
						   // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
						   // deve essere quello del lato che viene sostituito
							  virtE.id_gruppo = _edge.name;
							  first = false;
						   } 
						   
						   else
						   {
						   //				        System.out.print("\n    added inner link " + curr_edge_id);
							  virtP.type = CodeConstant.VERTEX_MID_Y;
							  virtE = new Edge(last_to_node, virtP,false, 0,_edge.weight);
							  virtE.type = CodeConstant.LINE_H_MC;
							  virtE.name = hi_edge_name++;
						   
						   // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
						   // deve essere quello del lato che viene sostituito
							  virtE.id_gruppo = _edge.name;
						   } 
						
						
						
						   tmp.add(virtE);
						//			          System.out.print("\n    added new link from "+   last_to_node.name  + " ---> "+ virtP.name + " --> ");
						   last_to_node = virtP;
						
						}
					 
					 
						if (diff_level == 2)
						   virtP.type = CodeConstant.VERTEX_SNG_Y;
						else
						   virtP.type = CodeConstant.VERTEX_TOP_Y;
					 
					 
						virtE = new Edge(last_to_node,to_node ,false, 0,_edge.weight);
						virtE.type = CodeConstant.LINE_H_LC;
					 
					 // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
					 // deve essere quello del lato che viene sostituito
						virtE.name = hi_edge_name++;
						virtE.id_gruppo = _edge.name;
					 //			     System.out.print("\n    added last new link from " + last_to_node.name+ " -->"+ to_node.name);
						tmp.add(virtE);
					 //			     System.out.print("\n    okay");
					 
					 }   // end case x1 < x2
					 
					 
					 
					 
					 
					 // tratta il caso di nodi da sinistra a destra
					 //
					 else if (_inode.gx > _onode.gx)
					 {
					 //			      System.out.print("\n Tratto  da destra a sinistra");
						from_node = _inode;
						to_node = _onode;
						last_to_node = from_node;
					 
						first = true;
						for (int colonna = (from_node.gx -1 ); colonna > to_node.gx; colonna--) 
						{
						
						   virtP = new Vertex(0, 0, 4, hi_node_name++);
						// dato che il nodo è connesso in una catena singola ed unica
						// il group id dei vertici è il nome del lato che li connette
						   virtP.edge_id = curr_edge_id;
						   virtP.gx = colonna;
						
						   virtP.level = from_node.level + delta;
						   virtP.altitude = from_node.altitude - delta;
						
						//			          System.out.print("\n added node virtual Horizontal[" + virtP.name + "] con level=" + virtP.level); 
						//			          System.out.print(", altitudine=" + virtP.altitude);
						//			          System.out.print(", gx=" + virtP.gx);
						   _plane.m[delta+_plane.offset_y][colonna] = virtP;
						
						
						
						   if (first) 
						   {
						   //				        System.out.print("\n    added inner link " + curr_edge_id);
							  virtP.type = CodeConstant.VERTEX_BOT_Y;
							  virtE = new Edge(last_to_node, virtP,false, 0,_edge.weight);
							  virtE.type = CodeConstant.LINE_H_FC;
							  virtE.name = hi_edge_name++;
						   
						   // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
						   // deve essere quello del lato che viene sostituito
							  virtE.id_gruppo = _edge.name;
							  first = false;
						   } 
						   
						   else
						   {
						   //				        System.out.print("\n    added inner link " + curr_edge_id);
							  virtP.type = CodeConstant.VERTEX_MID_Y;
							  virtE = new Edge(last_to_node, virtP,false, 0,_edge.weight);
							  virtE.type = CodeConstant.LINE_H_MC;
							  virtE.name = hi_edge_name++;
						   
						   // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
						   // deve essere quello del lato che viene sostituito
							  virtE.id_gruppo = _edge.name;
						   } 
						
						
						
						   tmp.add(virtE);
						//			          System.out.print("\n    added new link from "+   last_to_node.name  + " ---> "+ virtP.name + " --> ");
						   last_to_node = virtP;
						
						}
					 
					 
						if (diff_level == 2)
						   virtP.type = CodeConstant.VERTEX_SNG_Y;
						else
						   virtP.type = CodeConstant.VERTEX_TOP_Y;
					 
					 
						virtE = new Edge(last_to_node,to_node ,false, 0,_edge.weight);
						virtE.type = CodeConstant.LINE_H_LC;
					 
					 // il nome di gruppo dell'edge virtuale per i vertuali nodi aggiunti
					 // deve essere quello del lato che viene sostituito
						virtE.name = hi_edge_name++;
						virtE.id_gruppo = _edge.name;
					 //			     System.out.print("\n    added last new link from " + last_to_node.name+ " -->"+ to_node.name);
						tmp.add(virtE);
					 //			     System.out.print("\n    okay");
					 
					 }   // end case x1 < x2
				  
				  } // end for dist > 1
			   
			   
			   }
			
			}
		 }
	  
	  
	  
	  // add new link
		 itr_edge = tmp.iterator();
		 while (itr_edge.hasNext()) {
			_edge = ((Edge) itr_edge.next());
			vEdge.add(_edge);
		 }
	  
	  
	  
	  }	  // matrice dei punti rappresentanti visto per piani ;
	 // traccia solo la parte orizzontale 
	  mXY h_mXY;	  // matrice dei punti rappresentanti    il grafo
	 // rappresenta solo la parte in verticale
	  mXY v_mXY; /**
   *    ordina i nodi su vVertex per level
   *    battezza per ogni livello y, i nodi in numero crescente da 1 ad n
   *    dove n =  numero di nodi per il corrente livello y
   */                                       
	   public void compute_depth_x() 
	  {
		 Vertex _Vertex = null;
	  
		 int[] vx = new int[depth_y+1];
		 int lev_curr = 0;
		 int max_x = 0;
		 for (int j = 0; j <= depth_y; j++)
			vx[j] = 0;
	  
	  
	  
		 for (int j = 0; j < vVertex.size(); j++) 
		 {
		 
			_Vertex = (Vertex) vVertex.elementAt(j);
		 //  	  System.out.print("\n Vertex = "+_Vertex.name+" , lev = "+_Vertex.level);
			lev_curr = _Vertex.level;
			vx[lev_curr] += 1;
			if (vx[lev_curr] > max_x)
			   max_x = vx[lev_curr];
		 }
	  
		 depth_x = max_x;
	  
	  }/**
   * calcola il numero massimo di k-levels
   * per determinare quante righe dovranno essere definite su mXY
   * alla fine , quindi , è determinato depth_y = al numero REALE di livelli
   * che quindi si estenderanno tra 0 e (depth_y-1)
   */                                   
   
	   public void compute_depth_y() 
	  {
		 Vertex _Vertex;
		 Vertex _Vertey;
		 Edge   _Edge;
		 Iterator itr_Vertex;
		 Iterator itr_Vertey;
		 Iterator itr_Edge;
	  
		 int cur_depth = 0;
		 int max = 0;
		 int max_b = 0;
		 int max_f = 0;
		 int nr_delete = 0;
	  
	  // azzera  su ogni nodo lo stato is traversed
		 unMarkAll();
	  
	  //calcola altitude and max depth partendo da out-node
		 itr_Vertex = vOut.iterator();
		 while (itr_Vertex.hasNext()) 
		 {
			_Vertex = ((Vertex) itr_Vertex.next());
			cur_depth = _Vertex.depth_back(0, max);
			if (cur_depth > max_b)
			   max_b = cur_depth;
		 }
	  
	  
	  
	  /*   
	  
	  System.out.print("\n Dopo primo calcolo da out verso input la situazione è : "+max_b);
	  System.out.print("\n ******************************************************");
	  view();
	  
	  */   
	  
	  // reset su ogni nodo lo stato is traversed
		 unMarkSingle();
	  
	  //compute altitude and max depth starting from input node
		 itr_Vertex = vInp.iterator();
		 max = 0;
		 while (itr_Vertex.hasNext()) 
		 {
			_Vertex = ((Vertex) itr_Vertex.next());
			cur_depth = _Vertex.depth_forw(0, max);
			if (cur_depth > max_f)
			   max_f = cur_depth;
		 }
	  
	  
	  
	  
	  
	  /*   
	  
	  System.out.print("\n Dopo primo calcolo da inp  verso output  la situazione è : "+max_b);
	  System.out.print("\n ******************************************************");
	  //	  view();
	  System.out.print("\n ******* compute.depth.y : max altitude_b =  "+max_b);
	  System.out.print("\n ******* compute.depth.y : max altitude_f =  "+max_f);
	  
	  */
	  
	  
	  // reset su ogni nodo lo stato is traversed
		 unMarkSingle();
	  
	  
		 if (max_b >= max_f)
		 {
		 
		 //	   System.out.print("\n blocco altitude -> level");
			max = max_b;
			depth_y = max + 1;
		 
		 // update out node 
			itr_Vertex = vOut.iterator();
			while (itr_Vertex.hasNext()) 
			{ 
			   _Vertex = ((Vertex) itr_Vertex.next());
			   _Vertex.altitude = max;
			   _Vertex.level = 0;
			}
		 
		 // force inp node to zero in altitude
			itr_Vertex = vInp.iterator();
			while (itr_Vertex.hasNext()) 
			{ 
			   _Vertex = ((Vertex) itr_Vertex.next());
			   _Vertex.altitude = 0;
			
			}
		 
		 
		 
		 
		 
		 
		 // align all nodes without level ( level =  -1)
		 //
			itr_Vertex = vVertex.iterator();
			while (itr_Vertex.hasNext()) 
			{
			   _Vertex = ((Vertex) itr_Vertex.next());
			   if (_Vertex.level == -1 && _Vertex.altitude != -1) 
				  _Vertex.level = (depth_y - 1) - _Vertex.altitude;
			   if (_Vertex.altitude == -1 && _Vertex.level != -1) 
				  _Vertex.altitude = (depth_y - 1) - _Vertex.level;
			}
		 
		 
		 
		 
		 
		 
		 
		 }
		 else
		 {
		 //	   System.out.print("\n blocco level -> altitude");
			max = max_f;   
			depth_y = max + 1;
		 
		 // update inp node 
			itr_Vertex = vInp.iterator();
			while (itr_Vertex.hasNext()) 
			{ 
			   _Vertex = ((Vertex) itr_Vertex.next());
			   _Vertex.level = max;
			   _Vertex.altitude = 0;
			}
		 
		 // update out node 
			itr_Vertex = vOut.iterator();
			while (itr_Vertex.hasNext()) 
			{ 
			   _Vertex = ((Vertex) itr_Vertex.next());
			   _Vertex.level = 0;
			
			}
		 
		 
		 
		  // align all nodes without level ( level =  -1)
		 //
			itr_Vertex = vVertex.iterator();
			while (itr_Vertex.hasNext()) 
			{
			   _Vertex = ((Vertex) itr_Vertex.next());
			   if (_Vertex.altitude == -1 && _Vertex.level != -1) 
				  _Vertex.altitude = (depth_y - 1) - _Vertex.level;
			   if (_Vertex.level == -1 && _Vertex.altitude != -1) 
				  _Vertex.level = (depth_y - 1) - _Vertex.altitude;
			}
		 
		 
		 
		 
		 }
	  
	  
	  
	  
	  /*   
	  
	  System.out.print("\n ********************* SITUAZIONE DOPO PRINCIPALI SETTING   ***********");
	  view();   
	  System.out.print("\n **********************************************************************");
	  
	  */
	  
	  // a questo punto si esaminano i nodi dall'alto verso il basse e viceversa
	  // per ogni nodo non impostato :
	  // se il predecessore  non è  settato cioè se  non ha valori impostati cioe level = -1 e altitude = -1
	  // al predecessore viene assegnato il livello in funzione del suo predecessore , si flagga
	  // una variabile come changed e si ricomincia
	  // stessa cosa andando in senso opposto
	  // si esce solo se alla fine non ci sono variazioni
	  // gli eventuali nodi che restano a -1,-1 dovranno essere eliminati in quanto non fanno
	  // parte della nostra rete.
	  
	  
	  
	  
	  //	 System.out.print("\n ********* vertical and horizontal adj*************");
	  
		 boolean changed = true;
		 while (changed)
		 {
			changed = false;
		 
			itr_Vertex = vVertex.iterator();
			while (itr_Vertex.hasNext()) 
			{
			   _Vertex = ((Vertex) itr_Vertex.next());
			//  	       System.out.print("\n verifica vertice " + _Vertex.name);
			//  	       System.out.print(" che ha level = "+ _Vertex.level+" altitude = "+_Vertex.altitude);
			
			
			// cerca il nodo più in basso verso cui il nodo è collegato
			// per fare questo esamino i nodi in uscita e vedo quale tra questi ha il
			// altitude più bassa o livello più alto;
			
			   if ((_Vertex.level == -1 ) && (_Vertex.altitude == -1))
			   {
			   //  	          System.out.print("\n    provo ad aggiustare.. " + _Vertex.name);
			   
				  boolean found = false;
				  int xmin = 9999;
				  Vertex _Vz = null;
				  itr_Vertey = _Vertex.neighbors_out.iterator();
				  while (itr_Vertey.hasNext()) 
				  {
					 _Vertey = ((Vertex) itr_Vertey.next());
				  //			     System.out.print("\n       trovato vertice successore =  " + _Vertey.name);
				  //  	             System.out.print(" a level = "+ _Vertey.level+" altitude = "+_Vertey.altitude);
				  
					 if ((_Vertey.altitude > 0) && (_Vertey.level < max))
					 {
						found = true;
						if (_Vertey.altitude < xmin)
						{
						   xmin =  _Vertey.altitude;
						   _Vz = _Vertey;
						}
					 }
				  }
			   // se found = true allora il nodo _Vz è il più vicino
			   // per cui _Vertex viene collegato a tale nodo ad una
			   // quota = quota - 1 
				  if (found)
				  {
					 _Vertex.altitude = _Vz.altitude - 1;
					 _Vertex.level= _Vz.level + 1;
					 changed = true;
				  //				  System.out.print("\n     okay SCELGO " + _Vz.name);
				  //				  System.out.print("\n     okay impostato " + _Vertex.name);
				  //  	              System.out.print(" a level = "+ _Vertex.level+" altitude = "+_Vertex.altitude);
				  
				  }
				  
				  // se non ci sono stati cambiamenti riprovo in senso opposto
				  else 
				  {
				  
				  //			  System.out.print("\n       dato che non ho trovato alcun successore provo con un predecessore ");
				  
					 found = false;
					 xmin = 999;
					 _Vz = null;
					 itr_Vertey = _Vertex.neighbors_inp.iterator();
					 while (itr_Vertey.hasNext()) 
					 {
						_Vertey = ((Vertex) itr_Vertey.next());
					 //			     System.out.print("\n       trovato vertice =  " + _Vertey.name);
					 //  	             System.out.print(" a level = "+ _Vertey.level+" altitude = "+_Vertey.altitude);
					 
						if ((_Vertey.altitude >= 0) && (_Vertey.altitude < max))
						{
						   found = true;
						   if (_Vertey.altitude < xmin)
						   {
							  xmin =  _Vertey.altitude;
							  _Vz = _Vertey;
						   }
						}
					 }
				  
					 if (found)
					 {
						_Vertex.altitude = _Vz.altitude + 1;
						_Vertex.level= _Vz.level - 1;
						changed = true;
					 //				  System.out.print("\n     okay SCELGO " + _Vz.name);
					 //				  System.out.print("\n     okay impostato " + _Vertex.name);
					 //  	              System.out.print(" a level = "+ _Vertex.level+" altitude = "+_Vertex.altitude);
					 
					 }
				  
				  
				  
				  }
			   
			   
			   
			   
			   
			   
			   
			   } 
			
			
			
			
			
			
			
			
			
			
			
			
			
			}
		 
		 
		 }
	  
	  
	  
	  /*
	  
	  System.out.print("\n ********************* SITUAZIONE FINALE ***********************************");
	  view();   
	  System.out.print("\n **********************************************************************");
	  */
	  
	  
	  
	  
	  
		 Vector vv = new Vector(1,0);
		 Vector ve = new Vector(1,0);
	  
	  
	  // align all nodes without level or altitude
	  //
		 itr_Vertex = vVertex.iterator();
		 while (itr_Vertex.hasNext()) 
		 {
			_Vertex = ((Vertex) itr_Vertex.next());
			if ((_Vertex.level == -1) && (_Vertex.altitude == -1))
			{
			//		  System.out.print("\n ATTENZIONE  : vertex isolato e messo in lista per la cancellazione: "+_Vertex.name);
			   vv.addElement(_Vertex);
			   itr_Edge = vEdge.iterator();
			   while (itr_Edge.hasNext()) 
			   {
				  _Edge = ((Edge) itr_Edge.next());
				  if ((_Edge.in_node == _Vertex) || (_Edge.out_node == _Vertex))
				  {
				  //		          System.out.print("\n               edge eliminato : "+_Edge.name);
					 ve.addElement(_Edge);
				  }
			   }
			
			}
		 
		 }
	  
	  
	  
	  
	  // per il momento non vengono visualizzati nodi che sono al di sotto dei sensori anche perchè
	  // non si attiveranno mai in quanto nessuno gli passa un valore
	  // stessa cosa per i nodi che si estandono oltre i nodi di output
	  // per fare questo si suppone che dopo quanto fatto sopra restino proprio tali nodi
	  // o al massimo i nodi isolati da tutti o reti interne isolate che per
	  // tale applicazione al momento non sembrano essenziali.
	  // (potrebbero cioè esistere delle sub-network internamente alla rete funzionante;
	  // tali sub-network potrebbero essere "l'inconscio" della rete e in presenza di
	  // crossing con altre reti alterare profondamente il compostamento della rete)
	  // procediamo ad eliminare sia i nodi senza coordinate , segnalandoli, sia i link
	  // che li collegano e che si estenderebbero oltre i sensori o i motori.
	  //
	  
		 nr_delete = 0;
		 itr_Vertex = vv.iterator();
		 while (itr_Vertex.hasNext()) 
		 {
			_Vertex = ((Vertex) itr_Vertex.next());
			vVertex.removeElement(_Vertex);
			nr_delete++;
		 
		 }
	  
	  //   System.out.print("\n structure.compute.depth_y Elementi eliminati -> "+nr_delete);
	  
	  
		 nr_delete = 0;
		 itr_Edge = ve.iterator();
		 while (itr_Edge.hasNext()) 
		 {
			_Edge = ((Edge) itr_Edge.next());
			vEdge.removeElement(_Edge);
			nr_delete++;
		 
		 }
	  
	  //   System.out.print("\n structure.compute.depth_y Edge eliminati -> "+nr_delete);
	  
	  
	  }
	   public void create_Horiz_mXY() 
	  {
	  
		 Vertex _p = null;	
		 h_mXY = new mXY(depth_y, depth_x);
	  
		 Iterator itr_plane = null;
		 itr_plane = vPlane.iterator();
		 planeXY _plane = null;
	  
		 while (itr_plane.hasNext()) 
		 {
			_plane = ((planeXY) itr_plane.next());
			for (int j=0; j < _plane.dim_y; j++)
			{
			   for(int i=0; i < _plane.dim_x; i++)
			   {
			   
				  _p = _plane.m[j][i];
				  if (_p != null)
				  {
					 int offset = _plane.level;
				  //	         System.out.print("\n livello rappresentato = "+offset);
					 h_mXY.m[j+offset][i] = _p;
				  }
			   }
			
			}
		 }
	  
	  }/**
   * legge le connesioni e se trova una connessione con nodi di pari livello
   * allora tale connessione è intrastrato
   * in questo caso tutti i nodi di quel livello sono memorizzati nel piano
   */                                
	   public void create_plane() 
	  {
		 Iterator itr_edge = null;
		 Edge _edge = null;
		 Vertex _inode = null;
		 Vertex _onode = null;
		 Iterator itr_Vertex = null;
		 Vertex _p = null;
		 int count_aux_row = 0;
		 int new_level;
	  
		 new_level = 0;
		 for (int l = 0; l < depth_y; l++) 
		 {
		 
		 // conta quante connessioni intra-strato esistono per layer 'l'
		 //
			itr_edge = vEdge.iterator();
			count_aux_row = 1;
			while (itr_edge.hasNext()) 
			{
			   _edge = ((Edge) itr_edge.next());
			   _inode = _edge.in_node;
			   _onode = _edge.out_node;
			
			// se due nodi in una connessione hanno lo stesso livello
			// allora esiste una connessione intrastrato
			//
			   if ((_onode.level == _inode.level) && (l == _inode.level)) 
				  count_aux_row++;
			}
		 
		 // in base alle connessioni genera un piano XY con tante righe quante sono le connessioni
		 // intra-strato , memorizzando anche i nodi per i quali dovrà essere ricalcolato il livello
		 // in quanto dovranmno fare posto alle connessioni  intra-strato
		 // per fare ciò legge i nodi del livello trovato e li memorizza su un vettore;
		 
		 
			Vector vx = new Vector(1, 0);
		 
			itr_Vertex = vVertex.iterator();
			while (itr_Vertex.hasNext()) 
			{
			   _p = ((Vertex) itr_Vertex.next());
			   if ((_p != null) && (_p.level == l))
			   {
			   //			 System.out.print("\n add to plane Vertex = "+_p.name+" , for level="+_p.level);
				  vx.add(_p);
			   }
			}
		 
		 
		 //	  System.out.print("\n  LAYER : "+ l +" nr.rows = " + count_aux_row + " totals and " + depth_x + " column's");
		 //	  System.out.print(" ==> livello ricalcolato = "+new_level);
			planeXY px = new planeXY(vx, new_level, count_aux_row);
			vPlane.add(px);
			new_level = new_level + count_aux_row;
		 }
	  
	  
	  //aggiorna DEPTH con il numero corrent di livelli esatto.
		 depth_y = new_level;
	  
	  }
	   public void create_Vert_mXY() 
	  {
	  
		 v_mXY = new mXY(depth_y, depth_x, vVertex);
	  
	  } 
	   public int  generate_Grafo()
	  {
	  
	   // reset tutte le incoming - outgoing connection's
		 unmarkNeighbors();
	  
	  
	   // leggendo il vettore edge genera su ogni nodo le connessioni in ingresso
	   // e in uscita
		 genesis();
	  
	   // reset su ogni nodo lo stato is traversed
		 unMarkAll();
	  
	  
	  //		 System.out.print("\n sono su generate.grafo.step 01");
	  
	  
		// calcola,  attraversando il grafo, le quote di ciascun nodo
		// definisce il valore depth_y = numero di livelli
		// riallinea in tutti i Vertex il livello in base alle quote calcolate
		 compute_depth_y();
	  
	  
	  // 		 System.out.print("\n sono su generate.grafo.step 02");
	  
	  
	   // calcolati tutti i livelli calcola depth_x  vale a dire il numero massimo
	   // di nodi per un singolo layer
	  
		 compute_depth_x();
	  
	  
	  
	  
	  
	  
	  
	  //		 System.out.print("\n sono su generate.grafo.step 1");
	  
	  
	   // legge i nodi e genera per ogni livello un oggetto di tipo
	   // plainXY che rappresenta il singolo layer con i nodi del relativo livello
	   // in fase di creazione imposta il livello riallineandolo in base ai livelli
	   // aggiuntivi che saranno aggiunti in base alle connessioni intra-strato;
		 create_plane();
	  
	  
	  // 		 System.out.print("\n sono su generate.grafo.step 2");
	  
	   // rilegge tutti i piani e in base al nuovo livello memorizzato
	   // nel piano, riallinea i livelli di tutti i nodi del piano in oggetto
		 update_new_level();
	  
	  
	  //		 System.out.print("\n sono su generate.grafo.step 3");
	  
	  
	   // genera i vertici virtuali e gli edge virtuali che sostituiscono connessioni
	   // verticali distanti più di un livello
		 generate_virtual_Vertex_Edge_vertical();
	  
	  
	  //		 System.out.print("\n sono su generate.grafo.step 4");
	  
	  
		// reset all incoming - outgoing connection's per rigenerare
		// le connessioni in cui questa volta ci saranno i nodi virtuali
		 unmarkNeighbors();
	  
		// re-generate connection's for update with new virtual node
		 genesis();
	  
	  
	  //		 System.out.print("\n sono su generate.grafo.step 5");
	  
	  
	  
		// ricalcola depth_x  vale a dire il numero massimo di nodi per un singolo layer
		// tenendo conto  quindi , dei nodi virtuali
		 compute_depth_x();
	  
	  
	  //		 System.out.print("\n sono su generate.grafo.step 6");
	  
	  
	  
	   // crea matrice con coordinate
		 create_Vert_mXY();
	  
	  
	  //		 System.out.print("\n sono su generate.grafo.step 7");
	  
	  
	  
	  
	  
	   // calcola e posiziona i nodi secondo le verticali
		 v_mXY.computeMacro(vEdge);
	  
	  
	  
	  //		 System.out.print("\n sono su generate.grafo.step 8");
	  
	  
	   // calcola in base alla matrice presente,  le eventuali matrici 
	   // intra-strato ; eventuali planeXY senza connessioni intra-strato
	   // verranno eliminate
		 generate_planeXY();
	  
	  
	  // 		 System.out.print("\n sono su generate.grafo.step 9");
	  
	  
	  
	   // genera vertex/edge virtuali nelle matrici planXY
	   // per le connessioni  intrastrato orizzontali
		 generate_virtual_Vertex_Edge_horizontal();
	  
	  
	  
	  //		 System.out.print("\n sono su generate.grafo.step 10");
	  
	  
	  
	   // genera la matrice con i nuovi vertici virtuali
	   //create_matPlainXY();		 
		 create_Horiz_mXY();
	  
	  // 		 System.out.print("\n sono su generate.grafo.step 11");
	  
	  
		 return 0;
	  
	  }
   
   
   
   
   
   /**
   *    ordina i nodi su vVertex per level
   *    battezza per ogni livello y, i nodi in numero crescente da 1 ad n
   *    dove n =  numero di nodi per il corrente livello y
   */                                                       
	   public void update_node_recurrent() 
	  {
		 Iterator itr_Vertex = null;	
		 itr_Vertex = vVertex.iterator();
		 Vertex _Vertex;
	  
	  //   System.out.print("\n aggiorna sui nuovi livelli ");
		 while (itr_Vertex.hasNext()) {
			_Vertex = ((Vertex) itr_Vertex.next());
			if (_Vertex.type == CodeConstant.VERTEX_RECURRENT)
			{
			   _Vertex.x = _Vertex.Vertex_ref.x;
			   _Vertex.y = _Vertex.Vertex_ref.y;
			}
		 }
	  
	  }                                                    
	   public void compute_Coordinate(int xm,int ym) 
	  {
		 try {
		 
		 
			Iterator itr_Vertex = null; 
			int lx;   
			Vertex _v_Vertex;
			Vertex _h_Vertex;
			Vertex _v_Vertex_prec;
		 
			int dist_x = 46;
			int dist_vx = 12;
		 
			int dist_vy = 25;
			boolean found;
			boolean found1;
			boolean found2;
			boolean found3;
		 
			double [] aX = new double[depth_x+1];
			double [] aT = new double[depth_y+1];
			double [] aY = new double[depth_y+1];
		 
		 
			boolean only_virtual;
			boolean only_virtual1;
			boolean only_virtual2;
		 
		 
		 //   v_mXY.view();
		 //   h_mXY.view(); 
		 
		 // compute le possibili ascisse dei nodi
		 //
			int kost_dx;
			int delta_x = 5;
		 //   int delta_x = 0;
		 
		 
		 
		 //v_mXY.dim_x
		 //   dist_x =   xm / (depth_x-1) ;
		    dist_x =   xm / (depth_x+1) ;
		 //   dist_vx =  dist_x ;
		
		 
		 
		 
		 
		 
		 
		 
		  
//		 System.out.print("\n xm = "+xm+", depth_x = "+depth_x+", dist_x = "+dist_x);
		 
		 
		 
		 
		 
		 
		 
		 
		 //   System.out.print("\n ascisse  = "+v_mXY.dim_x);
		 
			for (int col = 0; col < v_mXY.dim_x; col++) 
			{
			   only_virtual = true;
			   kost_dx = dist_x;
			
			   for (int rx = 0; rx < v_mXY.dim_y; rx++) 
			   {
				  if( col > 0)
				  {
				  
				  //			System.out.print("\n analisi colonna : "+col);
					 _v_Vertex = v_mXY.m[rx][col];
					 _v_Vertex_prec = v_mXY.m[rx][col-1];
				  
					 if ((_v_Vertex != null) && !_v_Vertex.is_virtual()) 
					 {
						only_virtual = false;
					 //  			   System.out.print("\n    .found real in col current  at row : "+rx);
						break;
					 
					 }
					 if ((_v_Vertex_prec != null) && !_v_Vertex_prec.is_virtual()) 
					 {
						only_virtual = false;
					 //  			   System.out.print("\n    .found real in col previous : "+rx);
						break;
					 }
				  }
			   }
			
			   if (only_virtual)
				  kost_dx = dist_vx;
			
			   delta_x = delta_x + kost_dx;
			   aX[col] = delta_x;
			
			}
		 
		 
		 
		 // calcola le possibili ordinate dei nodi
		 //
		 
			double dy = ym / (depth_y) ;
			int dist_y = (int) dy;
			int kost_dy;
			int delta_y = 0;
		 
		 
			for (int row = 0; row < h_mXY.dim_y; row++) 
			{
			
			
			   kost_dy = dist_y;
			
			// verifica se tutti i nodi della row precedente sono tutti 
			// virtuali e se ne esiste almeno uno nella corrente row
			
			   only_virtual = true;
			   found = false;
			   found1 = false;
			   for (int cx = 0; cx < h_mXY.dim_x; cx++) 
			   {
				  if( row > 0)
				  {
					 _v_Vertex_prec = h_mXY.m[row-1][cx];
					 _v_Vertex      = h_mXY.m[row][cx];
				  
					 if (_v_Vertex_prec != null)
					 {
						found = true;
					 //			    System.out.print("\n attivo caso 1 per row "+row);
						if (!_v_Vertex_prec.is_virtual()) 
						{
						   only_virtual = false;
						   break;
						}
					 }
				  
					 if (_v_Vertex != null)
					 {
						found1= true;
					 }
				  
				  
				  
				  }
			   }
			
			
			
			
			
			
			
			
			
			
			// verifica se tutti i nodi della row coorente sono virtuali
			// se si allora la distanza dalla precedente row è minore
			
			   only_virtual2 = true;
			   found2 = false;
			   for (int cx = 0; cx < h_mXY.dim_x; cx++) 
			   {
				  if( row > 0)
				  {
					 _v_Vertex      = h_mXY.m[row][cx];
				  
					 if (_v_Vertex != null)
					 {
						found2 = true;
					 //			    System.out.print("\n attivo caso 2 per row "+row);
						if (!_v_Vertex.is_virtual()) 
						{
						   only_virtual2 = false;
						   break;
						}
					 }
				  
				  
				  }
			   }
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			   if (( found)  && (only_virtual) && (found1))
			   {
			   //		 System.out.print("imposta per filtro 1)"); 
				  kost_dy = dist_vy;
			   }
			   if ((only_virtual2) && (found2))
			   {
			   //		 System.out.print("imposta per filtro 2)"); 
				  kost_dy = dist_vy;
			   }
			//	  else
			//		 kost_dy = dist_y;
			
			
			
			
			
			
			
			   delta_y = delta_y + kost_dy;
			   aT[row] = delta_y;
			//	  System.out.print("\n aY[" + row + "] = " + delta_y);		  
			
			}
		 
		 
			for (int row = 0; row < depth_y; row++) 
			   aY[depth_y - row - 1 ] = ym - aT[row] + 10;
//			   aY[depth_y - row - 1 ] = ym - aT[row] + dy / 2 ;
		 
		 
			for (int row = 0; row < depth_y; row++) 
			{
			
			   for (int col = 0; col < depth_x; col++) 
			   {
			   
				  _v_Vertex = v_mXY.m[row][col];
				  _h_Vertex = h_mXY.m[row][col];
			   
				  if (_v_Vertex != null)
				  {
				  //			System.out.print("\n il nodo"  +_v_Vertex.name+ " prende le coord : ");
					 _v_Vertex.x = (int) aX[_v_Vertex.gx];
					 int y1 = _v_Vertex.altitude;
					 _v_Vertex.y = (int) aY[y1];
				  
				  //		    System.out.print(" x="  +_v_Vertex.x+" , y="+_v_Vertex.y);
				  
				  }
			   
				  if (_h_Vertex != null)
				  {
				  //			System.out.print("\n il nodo"  +_v_Vertex.name+ " prende le coord : ");
					 _h_Vertex.x = (int) aX[_h_Vertex.gx];
					 int y1 = _h_Vertex.altitude;
					 _h_Vertex.y = (int) aY[y1];
				  
				  //		    System.out.print(" x="  +_h_Vertex.x+" , y="+_h_Vertex.y);
				  
				  }
			   
			   
			   }
			}
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 // una volta creato la mappa legge i il vettore vVertex e se
		 // trova vertex_recurrent aggiorna la loro posizione  x,y
		 // con quella del nodo a cui si riferiscono;
			update_node_recurrent();
		 
		 
		 
		 
		 
		 
		 
		 }
		 
		 
			 catch ( Exception e)
			{
			   System.out.print("\n *alert* error in computation of coordinate"+e); 
			}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  }/**
   * scorre tutti i livelli a alla fine ritorna il numero massimo
   * di nodi che ha trovato in un singolo livello
   */                                                                   
 	   public void LoadGenome(Genome _g) 
	  {
	  
		 StringTokenizer st;
		 String curword;
		 String xline;
		 String fnamebuf;
		 IOseq xFile;
//		 int sign_of_link = 0;
		 int _genome_id;
		 int _type = 0;
		 int _id = 0;
		 int progr = 1;
		 double weight = 0.0;
		 hi_node_name = 0;
		 Iterator itr_Vertex = null;
	  
	  //   System.out.println(" Start experiment 2");
	  //   System.out.println(" Read start genome..");
	  
		 progr = 1;
	  //id of genome 
		 _genome_id = _g.getGenome_id();
	  
	  //   System.out.print("\n genome cloned has id " + _genome_id);
	  
		 Vertex newVertex;
		 Iterator itr_node = _g.getNodes().iterator();
		 while (itr_node.hasNext()) 
		 {
			NNode _node = ((NNode) itr_node.next());
			_id = _node.getNode_id();
			int type1 = _node.getType();
			int type2 = _node.getGen_node_label();
		 
			if (type1 == NeatConstant.SENSOR) 
			{
			   _type = NeatConstant.INPUT;
			} 
			
			else 
			{
			   if (type2 == NeatConstant.OUTPUT)
				  _type = NeatConstant.OUTPUT;
			   else
				  _type = NeatConstant.HIDDEN;
			}
		 
			newVertex = new Vertex(0, 0, _type, _id);
		 
			if (_id > hi_node_name)
			   hi_node_name = _id;
		 
			vVertex.addElement(newVertex);
			if (_type == NeatConstant.SENSOR)
			   vInp.add(newVertex);
			else
			   if (_type == NeatConstant.OUTPUT)
				  vOut.add(newVertex);
		 
		 } // end block Vertex
	  
	  
	  
	  
		 Iterator itr_gene = _g.getGenes().iterator();
		 
		 while (itr_gene.hasNext()) 
		 {
			Vertex iVertex = null;
			Vertex oVertex = null;
		 
			Gene _gene = ((Gene) itr_gene.next());
			int inode_num = _gene.getLnk().getIn_node().getNode_id();
			int onode_num = _gene.getLnk().getOut_node().getNode_id();
		 
			weight = _gene.getLnk().getWeight();

			boolean recur;
			if (_gene.getLnk().getIs_recurrent())
			   recur = true;
			else
			   recur = false;
		 
			boolean enable;
		 
			if (_gene.getEnable())
			   enable = true;
			else
			   enable = false;
		 
			if (enable) 
			{
			   int fnd = 0;
			   itr_Vertex = vVertex.iterator();
			   while (itr_Vertex.hasNext() && fnd < 2) 
			   {
				  Vertex _Vertex = ((Vertex) itr_Vertex.next());
				  if (_Vertex.name == inode_num) 
				  {
					 iVertex = _Vertex;
					 fnd++;
				  }
				  if (_Vertex.name == onode_num) 
				  {
					 oVertex = _Vertex;
					 fnd++;
				  }
			   }
			   Edge newedge;
			
			   newedge = new Edge(iVertex, oVertex, recur, CodeConstant.LINE_SE, weight); 
			
			   iVertex.edge_id = 0;
			   oVertex.edge_id = 0; 
			   newedge.name = progr++;
			
			   vEdge.addElement(newedge);
			}
		 
		 } // end block gene
	  
	  // primo nome disponibile per nodi virtual
		 hi_node_name++;
		 hi_edge_name = progr;
	  
	  }/**
   * Insert the method's description here.
   * Creation date: (20/02/2002 11.56.44)
   */                                                                     
   
	   public StringBuffer getSource() 
	  {
		 String mask4 = " 0.000";
		 DecimalFormat fmt4 = new DecimalFormat(mask4);

		  
		 StringBuffer _s = new StringBuffer("");
	  
		 Iterator itr_Vertex = null;
		 Iterator itr_edge = null;
		 Iterator itr_plane = null;
		 Edge _edge = null;
		 Vertex _Vertex = null;
		 planeXY _plane = null;
	  
		 _s.append("\n NODES IN GENOME:");
		 _s.append("\n");
		 itr_Vertex = vVertex.iterator();
		 while (itr_Vertex.hasNext()) 
		 {
			_Vertex = ((Vertex) itr_Vertex.next());
		 
			if (_Vertex.is_real())
			{
			   _s.append("\n Node id[" + _Vertex.name+"] ");
			   if (_Vertex.type == NeatConstant.INPUT)
				  _s.append(", SENSOR/BIAS");
			   if (_Vertex.type == NeatConstant.HIDDEN)
				  _s.append(", HIDDEN");
			   if (_Vertex.type == NeatConstant.OUTPUT)
				  _s.append(", OUTPUT");
			}
		 }
	  
		 _s.append("\n\n GENES IN GENOME:");
		 _s.append("\n"); 
		 itr_edge = vEdge.iterator();
		 while (itr_edge.hasNext()) 
		 {
			_edge = ((Edge) itr_edge.next());
			if (_edge.id_gruppo == 0)
			{
			   _s.append("\n link node["); 
			   _s.append(_edge.in_node.name+"] to");
			   _s.append(" ->["+_edge.out_node.name+"] ");
			   _s.append("\n    recurs="+_edge.is_recursive);
	 		   _s.append("\n    weight="+fmt4.format(_edge.weight));
			   
			   
			}
		 }
		 return _s;
	  
	  }}