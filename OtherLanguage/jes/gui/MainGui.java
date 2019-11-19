   package gui;

/*import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
import javax.accessibility.*;
*/





   import javax.swing.JTabbedPane;
   import javax.swing.ImageIcon;
   import javax.swing.JLabel;
   import javax.swing.JPanel;
   import javax.swing.JFrame;

   import java.awt.*;
   import java.awt.event.*;
   import log.*;



								import javax.swing.*;								public class MainGui extends JPanel
   {
   
	  JFrame f1;	 
   
	  private Parameter   a_parameter;
	  private Session     a_session;
	  private Generation  a_generation;
	  private Grafi       a_grafi;
   
	  JTabbedPane jtabbedPane1;
   
   
   
   
   
	   public static void main(String[] args) 
	  {
		 JFrame jp = null;
		 MainGui pn1 = null;
	  
	  
		 try 
		 {
			jp = new JFrame("  J N E A T   Java simulator for   NeuroEvolution of Augmenting Topologies  ");
			pn1 = new MainGui(jp); 
		 
		 //	  jp.getContentPane().add(pn1);
			jp.addWindowListener(
				   new java.awt.event.WindowAdapter() 
				  {
					  public void windowClosing(WindowEvent e) 
					 {
						System.exit(0);
					 }
				  }
			   );
		 
			jp.pack();
			jp.setSize(800, 600);
			jp.setVisible(true);
		 
		 } 
		 
			 catch (Exception ex) 
			{
			   System.err.println("ERRORE");
			}
	  
	  
	  
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	   public MainGui(JFrame _f) 
	  { 
	  
		 f1 = _f;
	  
		 a_parameter   = new Parameter(_f);
		 a_session     = new Session(_f);
		 a_generation  = new Generation(_f);
		 a_grafi       = new Grafi(_f);
	  
	  
		 logger        = new HistoryLog(); 
	  
		 a_parameter.setLog(logger);
		 a_session.setLog(logger);
		 a_generation.setLog(logger);
		 a_grafi.setLog(logger);
	  
		 jtabbedPane1 = new JTabbedPane();
		 jtabbedPane1.addTab("jneat parameter",a_parameter.pmain);
		 jtabbedPane1.addTab("session parameter",a_session.pmain);
		 jtabbedPane1.addTab("start simulation",a_generation.pmain);
		 jtabbedPane1.addTab("view graph",a_grafi.pmain);
		 jtabbedPane1.setSelectedIndex(0);

		 
		 /* 
	  
	  
		 Container contentPane = f1.getContentPane(); 
		 contentPane.setLayout(new BorderLayout());
		 contentPane.add(jtabbedPane1,BorderLayout.CENTER);
		 contentPane.add(logger, BorderLayout.SOUTH);

*/



		 Container contentPane = f1.getContentPane(); 
		 contentPane.setLayout(new BorderLayout());




		 JSplitPane paneSplit1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jtabbedPane1, logger);
		 paneSplit1.setOneTouchExpandable(true);
		 paneSplit1.setContinuousLayout(true);
		 paneSplit1.setDividerSize(10);
		 jtabbedPane1.setMinimumSize(new Dimension(400, 50));
		 logger.setMinimumSize(new Dimension(100, 50));
	  
		 paneSplit1.setDividerLocation(410);
	  
		 paneSplit1.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createEmptyBorder(2, 2, 2, 2), 
			BorderFactory.createEmptyBorder(2, 2, 2, 2))); 



		 contentPane.add(paneSplit1,BorderLayout.CENTER);










		 
	  
	  }																																																																protected HistoryLog logger;}