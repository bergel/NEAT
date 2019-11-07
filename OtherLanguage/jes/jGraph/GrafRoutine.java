   package jGraph;

   import java.util.*;
																public class GrafRoutine {
   	  	  /**
   * GrafRoutine constructor comment.
   */
	   public GrafRoutine() {
		 super();
	  }
   
   
   /**
   * restituisce per n = 0,1,2,3,4,........
   * i valori : 0 , 1 , -1 , 2 , -2 , 3 , -3 ..............
   *
   */
	   public static int spiral(int n) 
	  {
		 int m = (int) Math.pow((double) -1,(double) n) * ((int) ((n+1)/2));
		 return m;	   
	  }
   
   
   
	   public static Vector L(Vertex _p, Vector _v,Vector _l) 
	  {
		 Edge _edge = null;
		 Vertex _tmp;
		 int real_group_id = 0;
	  
	  // il nodo passato è virtuale.
	  // dato che i nodi virtuali hanno uno e un solo lato a loro connessi viene
	  // calcolata tutta la catena di vertici virtuali fino al nodo reale ,(che viene escluso).
	  // per fare questo , viene calcolato l'id del lato reale, inactive, che è stato
	  // sostituito dai lati virtuali , vale a dire .'id di gruppo.
	  // quindi in base all'id  di gruppo viene calcolato il nodo predecessore del nodo
	  // corrente e ricorsivamente viene scorsa la catena fino ad arrivare al nodo reale.
	  
		 real_group_id = _p.edge_id;
	  
	  
		 if (real_group_id == 0)
		 {
		 //	   System.out.print("\n **L** : il nodo passato è reale ......");
			return _l;
		 }
	  
	  
		 _tmp = _p;
	  
		 for (int j = 0; j < _v.size(); j++) 
		 {
			_edge = (Edge) _v.elementAt(j);
			if (_edge.id_gruppo == real_group_id)
			{
			   if ((_edge.out_node == _tmp) && (_edge.in_node.is_virtual())) 
			   {
				  _l.add(_edge.in_node);
				  _tmp = _edge.in_node;
				  return L(_tmp, _v, _l);
			   }
			
			// add real node
			/*	 else if ((_edge.out_node == _tmp) && (!_edge.in_node.is_virtual())) 
			{
			_l.add(_edge.in_node);
			return _l;
			}
			*/
			
			
			
			
			
			}
		 }
	  
		 return _l;
	  
	  }
   
   
   
   
   
	   public static int hamming(Vertex _p, Vector _v,int len) 
	  {
		 Edge  _edge = null;
		 Vertex _tmp;
		 int   real_group_id;
	  
		 real_group_id = _p.edge_id;
	  
	  // il nodo passato può essere reale o virtuale ;
	  // nel caso sia reale il group_id = 0 , e di conseguenza la distanza vale 0;
	  // (vengono così privilegiati i nodi reali a distanza 1 e poi i nodi virtuali!).
	  // Se invece il punto _p , è virtuale, tale routine  , viene chiamata solo
	  // per i nodi che sono su distanze > 1; 
	  // dato che il punto _p ,  identifica un gruppo , in base all'id  di gruppo
	  // viene calcolato il nodo predecessore del nodo corrente e ricorsivamente viene 
	  // scorsa la catena fino ad arrivare al nodo reale;
	  // dato che ad ogni chiamata ricorsiva viene incrementato il path-length alla fine viene
	  // ritornato proprio tale valore che è la distanza tra il nodo iniziale e quello 
	  // all'estremità opposta.
	  
		 if (real_group_id == 0)
		 {
		 // System.out.print("\n **HAMMING**  found real group , thus distance = 0");
			return 0;
		 } 
		 _tmp = _p;
	  
		 for (int j = 0; j < _v.size(); j++) 
		 {
			_edge = (Edge) _v.elementAt(j);
			if (_edge.id_gruppo == real_group_id)
			{
			   if ((_edge.out_node == _tmp) && (_edge.in_node.is_virtual())) 
			   {
				  _tmp = _edge.in_node;
				  return hamming(_tmp, _v, len+1);
			   }
			}
		 }
		 return len;
	  
	  }
   
   
   }