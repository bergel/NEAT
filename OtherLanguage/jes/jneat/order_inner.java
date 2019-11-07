   package jneat;

/**
 * 
 * 
 * 
 */
	public class order_inner implements java.util.Comparator {
   	  	  /**
   * order_inner constructor comment.
   */
	   public order_inner() {
		 super();
	  }
   
	   public int compare(Object o1, Object o2) {
	  
		 NNode _ox =  (NNode) o1;
		 NNode _oy = (NNode) o2;
	  
		 if (_ox.inner_level <  _oy.inner_level)
			return -1;
		 if (_ox.inner_level >  _oy.inner_level)
			return +1;
		 return 0;
	  }
   }