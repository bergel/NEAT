   package jGraph;

/**
 * Insert the type's description here.
 * Creation date: (20/02/2002 11.52.57)
 * @author: Administrator
 */
	public class order_inner implements java.util.Comparator {
      	  	  	  	  	  	  	  	  /**
   * order_inner constructor comment.
   */
	   public order_inner() {
		 super();
	  }
   
	   public int compare(Object o1, Object o2) 
	  {
	  
		 Vertex _ox = (Vertex) o1;
		 Vertex _oy = (Vertex) o2;
	  
		 if (_ox.altitude <  _oy.altitude)
			return -1;
		 if (_ox.altitude >  _oy.altitude)
			return +1;
		 return 0;
	  }
   }