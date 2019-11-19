
   package jneat;


	        /**
 * Link is a connection from one node to another with an associated weight; It can be marked as recurrent;
 * Its parameters are made public for efficiency.
 */
	public class Link extends Neat {
   /** is a real value of weight of connection(link) */
	  double weight;
   /** is a reference to an  input node */
	  NNode in_node;
   
   /** is a reference to a output node; */
	  NNode out_node;
   
   /** this is a flag; if TRUE the connection(link) is recurrent; FALSE otherwise; */
	  boolean is_recurrent;
   
   /** this is a flag; if TRUE the connection(link) is tapped(delayed); FALSE otherwise; */
	  boolean time_delay;
   
   /**
   * Points to a trait of parameters for genetic creation.
   * Is  link-related parameters that change during Hebbian type learning.
   */
	  Trait linktrait;
   
   /** The amount of weight adjustment */
	  double added_weight;
   
   /** Is  link-related parameters that change during Hebbian type learning. */
	  double[] params = new double[Neat.p_num_trait_params];
   
	   public double getWeight() {
		 return weight;
	  }                                    
   
	   public void setWeight(double weight) {
		 this.weight = weight;
	  }                                    
   
	   public NNode getIn_node() {
		 return in_node;
	  }                                    
   
	   public void setIn_node(NNode in_node) {
		 this.in_node = in_node;
	  }                                    
   
	   public NNode getOut_node() {
		 return out_node;
	  }                                    
   
	   public void setOut_node(NNode out_node) {
		 this.out_node = out_node;
	  }                                    
   
	   public boolean getIs_recurrent() {
		 return is_recurrent;
	  }                                    
   
	   public void setIs_recurrent(boolean is_recurrent) {
		 this.is_recurrent = is_recurrent;
	  }                                    
   
	   public boolean getTime_delay() {
		 return time_delay;
	  }                                    
   
	   public void setTime_delay(boolean time_delay) {
		 this.time_delay = time_delay;
	  }                                    
   
	   public double getAdded_weight() {
		 return added_weight;
	  }                                    
   
	   public void setAdded_weight(double added_weight) {
		 this.added_weight = added_weight;
	  }                                    
   
	   public double[] getParams() {
		 return params;
	  }                                    
   
	   public void setParams(double[] params) {
		 this.params = params;
	  }                                    
   
	   public Trait getLinktrait() {
		 return linktrait;
	  }                                    
   
	   public void setLinktrait(Trait linktrait) {
		 this.linktrait = linktrait;
	  }                                    
   
   /**
	* Insert the method's description here.
	* Creation date: (12/01/2002 10.41.28)
	* @param lt jneat.Trait
	* @param w double
	* @param inode jneat.NNode
	* @param onode jneat.NNode
	* @param recur boolean
	*/
	   public Link(Trait lt, double w, NNode inode, NNode onode, boolean recur) 
	  {
	  
		 weight = w;
		 in_node = inode;
		 out_node = onode;
		 is_recurrent = recur;
		 added_weight = 0.0;
		 linktrait = lt;
		 time_delay = false;
	  }         
   /**
   * Insert the method's description here.
   * Creation date: (15/01/2002 7.53.27)
   * @param c int
   */
	   public Link(double w, NNode inode, NNode onode, boolean recur) 
	  {
		 weight = w;
		 in_node = inode;
		 out_node = onode;
		 is_recurrent = recur;
		 added_weight = 0.0;
		 linktrait = null;
		 time_delay = false;
	  
	  } /**
   * Insert the method's description here.
   * Creation date: (15/01/2002 8.05.44)
   */                     
	   public void derive_trait(Trait t) 
	  {
		 if (t != null) 
		 {
			for (int count = 0; count < Neat.p_num_trait_params; count++)
			   params[count] = t.getParams(count);
		 } 
		 else 
		 {
			for (int count = 0; count < Neat.p_num_trait_params; count++)
			   params[count] = 0.0;
		 }
	  }          
   
	   public void viewtext() 
	  {
		 System.out.print("\n +LINK : ");
		 System.out.print("weight=" + weight);
		 System.out.print(", weight-add=" + added_weight);
		 System.out.print(", i(" + in_node.getNode_id());
		 System.out.print(")--<CONNECTION>--o(");
		 System.out.print(out_node.getNode_id() + ")");
		 System.out.print(", recurrent=" + is_recurrent);
		 System.out.print(", tapped=" + time_delay);
	  
		 if (linktrait != null)
			linktrait.viewtext("\n         (linktrait)-> ");
		 else
			System.out.print("\n         *warning* linktrait for this gene is null ");
	  }         
   /** this is a flag for compute depth; if TRUE the connection(link) is already analyzed; FALSE otherwise; */
	  boolean is_traversed = false;
	   public boolean getIs_traversed() {
		 return is_traversed;
	  }/**
   * Insert the method's description here.
   * Creation date: (18/01/2002 9.16.45)
   * @param newIs_traversed boolean
   */                       
	   public void setIs_traversed(boolean is_traversed) {
		 this.is_traversed = is_traversed;
	  }}