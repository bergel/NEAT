
   package jneat;

   import jNeatCommon.*;

/**
 * This class serves as a way to record innovations
 *  specifically, so that an innovation in one genome can be
 *  compared with other innovations in the same epoch, and if they
 *  Are the same innovation, they can both be assigned the same innnovation number.
 *  This class can encode innovations that represent a new link
 *  forming, or a new node being added.  In each case, two
 *  nodes fully specify the innovation and where it must have
 *  occured.  (Between them)
 *
 */
	public class Innovation extends Neat {
   /**
   * Either NEWNODE or NEWLINK 
   */
	  int innovation_type;
   
   /**
   * Two nodes specify where the innovation took place : this is the node input 
   */
	  int node_in_id;
   
   /**
   * Two nodes specify where the innovation took place : this is the node output
   */
	  int node_out_id;
   
   /**
   * The number assigned to the innovation
   */
	  double innovation_num1;
   
   /**
   * If this is a new node innovation,then there are 2 innovations (links)
   * added for the new node
   */
	  double innovation_num2;
   
   /**
   * If a link is added, this is its weight 
   */
	  double new_weight;
   
   /**
   * If a link is added, this is its connected trait 
   */
	  int new_traitnum;
   
   /**
   * If a new node was created, this is its node_id 
   */
	  int newnode_id;
   
   /**
   * If a new node was created, this is
   *  the innovnum of the gene's link it is being
   * stuck inside 
   */
	  double old_innov_num;
   
   /**
   * is recurrent ?
   */
	  boolean recur_flag;
   
	   public int getInnovation_type() {
		 return innovation_type;
	  }                                       
	   public void setInnovation_type(int innovation_type) {
		 this.innovation_type = innovation_type;
	  }                                       
	   public int getNode_in_id() {
		 return node_in_id;
	  }                                       
	   public void setNode_in_id(int node_in_id) {
		 this.node_in_id = node_in_id;
	  }                                       
	   public int getNode_out_id() {
		 return node_out_id;
	  }                                       
	   public void setNode_out_id(int node_out_id) {
		 this.node_out_id = node_out_id;
	  }                                       
	   public double getInnovation_num1() {
		 return innovation_num1;
	  }                                       
	   public void setInnovation_num1(double innovation_num1) {
		 this.innovation_num1 = innovation_num1;
	  }                                       
	   public double getInnovation_num2() {
		 return innovation_num2;
	  }                                       
	   public void setInnovation_num2(double innovation_num2) {
		 this.innovation_num2 = innovation_num2;
	  }                                       
	   public double getNew_weight() {
		 return new_weight;
	  }                                       
	   public void setNew_weight(double new_weight) {
		 this.new_weight = new_weight;
	  }                                       
	   public int getNew_traitnum() {
		 return new_traitnum;
	  }                                       
	   public void setNew_traitnum(int new_traitnum) {
		 this.new_traitnum = new_traitnum;
	  }                                       
	   public int getNewnode_id() {
		 return newnode_id;
	  }                                       
	   public void setNewnode_id(int newnode_id) {
		 this.newnode_id = newnode_id;
	  }                                       
	   public double getOld_innov_num() {
		 return old_innov_num;
	  }                                       
	   public void setOld_innov_num(double old_innov_num) {
		 this.old_innov_num = old_innov_num;
	  }                                       
	   public boolean getRecur_flag() {
		 return recur_flag;
	  }                                       
	   public void setRecur_flag(boolean recur_flag) {
		 this.recur_flag = recur_flag;
	  }                                       
   
   /**
   * Insert the method's description here.
   * Creation date: (24/01/2002 8.09.28)
   */
	   public Innovation() {}/**
   * Insert the method's description here.
   * Creation date: (23/01/2002 9.04.02)
   */                       
	   public Innovation(int nin,int nout,double num1,double w,int t) 
	  {
		 innovation_type=NeatConstant.NEWLINK;
		 node_in_id=nin;
		 node_out_id=nout;
		 innovation_num1=num1;
		 new_weight=w;
		 new_traitnum=t;
	  
	  //Unused parameters set to zero
		 innovation_num2=0;
		 newnode_id=0;
		 recur_flag=false;
	  }
   
   /**
   * Insert the method's description here.
   * Creation date: (24/01/2002 8.09.28)
   */
	   public Innovation(int nin,int nout,double num1,double num2,int newid,double oldinnov) 
	  {
		 innovation_type=NeatConstant.NEWNODE;
		 node_in_id=nin;
		 node_out_id=nout;
		 innovation_num1=num1;
		 innovation_num2=num2;
		 newnode_id=newid;
		 old_innov_num=oldinnov;
	  
	  //Unused parameters set to zero
		 new_weight=0;
		 new_traitnum=0;
		 recur_flag=false;
	  }
   
   }