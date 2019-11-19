   package jGraph;

/**
 * Insert the type's description here.
 * Creation date: (19/02/2002 11.59.12)
 * @author: Administrator
 */
	public class order_code implements java.util.Comparator {
      	  	  	  	  	  	  	  /**
   * order_code constructor comment.
   */
	   public order_code() {
		 super();
	  }
   
	   public int compare(Object o1, Object o2) {
		 code _ox = (code) o1;
		 code _oy = (code) o2;
	  
		 if (_ox.tipo > _oy.tipo)
			return -1;
		 if (_ox.tipo < _oy.tipo)
			return +1;
		 return 0;
	  
	  }
   }