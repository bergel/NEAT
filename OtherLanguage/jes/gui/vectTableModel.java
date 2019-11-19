   package gui;


   import javax.swing.*;
   import javax.swing.table.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.text.*;


   import java.util.*;

																public class vectTableModel extends AbstractTableModel{
   
   
   
	  public Vector data;
	  public int rows;
   
	  public static final String columnNames[] = {" Parameter ", " Value "};
   
   
   
   
	   public vectTableModel(Vector _data) {
		 data = _data;
		 rows = -1;
	  }
   
	   public vectTableModel() {
		 data = new Vector();
		 rows = -1;
	  }	  	  
   
   
   
   
	   public int getColumnCount() {
		 return columnNames.length;
	  }
   
   
   
	   public int getRowCount() {
		 return data.size();
	  }
   
	   public String getColumnName(int col) {
		 return columnNames[col];
	  }
   
	   public Object getValueAt(int row, int col) {
		 switch (col) {
			case 0:
			   return ((ParamValue)data.elementAt(row)).o1;
			case 1:
			   return ((ParamValue)data.elementAt(row)).o2;
		 }
		 System.out.print("\n *ERRORE* su get.jtable");
		 return null;
	  
	  
	  }
   
   /*		
   	public Class getColumnClass(int c) 
   	{
   //		    System.out.print("\n ritorno Obj ");
   		return getValueAt(0, c).getClass();
   	}
   */
	   public boolean isCellEditable(int row, int col) {
		 if (col == 1 ) { 
			return true;
		 } 
		 return false;
	  
	  }
   
   
   
   
   
	   public void setValueAt(Object value, int row, int col) 
	  {
		 Object ox;
	  
		 if (row > rows) 
		 {
		 //				System.out.print("\n creato elem per riga "+row);
			data.setSize(row);              
			rows = row;
			ParamValue s = new ParamValue();
			data.insertElementAt(s, row);
		 //				System.out.print("\n s= "+s);
		 }
	  
	  //			System.out.print("\n setValueAt: ");
	  //			System.out.print(" row = "+row+" , rows = "+ rows);
	  //			System.out.print(" col = "+col);
		 ParamValue s1 = (ParamValue) data.elementAt(row);
	  //			System.out.print("\n s1= "+s1);
	  
		 switch (col) {
			case 0:
			   s1.o1 = (String) value;
			case 1:
			   {
				  ox = value;
			   
				  if (s1.o2 != null)
				  
				  {
				  //					   System.out.print("\n valore = "+value+" per "+s1.o2);
					 if (s1.o2 instanceof Integer)   
					 {
					 //						   System.out.print(" integer ");
						ox = new Integer(value.toString());
					 }	
					 else  if (s1.o2 instanceof Double)   
					 {  
					 //						   System.out.print(" double ");
						ox =  new Double(value.toString());
					 }
				  }
				  s1.o2 = (Object) ox;
			   
			   
			   }
		 }
	  
	  
		 fireTableCellUpdated(row, col);
	  
	  
	  }
   
   
   
   
	   private void printDebugData() {
		 int numRows = getRowCount();
		 int numCols = getColumnCount();
	  
		 for (int i=0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			ParamValue v = (ParamValue) data.elementAt(i);
			System.out.print("  " + v.toString());
			System.out.print("  " + v.toString());
			System.out.println();
		 }
		 System.out.println("--------------------------");
	  }
   
   
   
   
	   public  void insert(ParamValue s) {
		 data.addElement(s);
		 rows++;
	  }
   
   
   
   }