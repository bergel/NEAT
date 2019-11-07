   package gui;

   import java.awt.*;
   import java.awt.event.*;
   import java.awt.geom.*;
   import java.awt.image.*;
   import javax.swing.*;
   import javax.swing.event.*;
   import javax.swing.border.*;
   import java.util.*;
   import java.lang.*;
   import jneat.*;
   import javax.swing.text.*;
   import java.io.*;

   import jNeatCommon.*;


   import log.*;
				public class Session extends JPanel implements ActionListener {





   /* new definition  start */

	
   /* new definition stop */


   
   
	  Container contentPane;
	  protected HistoryLog   logger;
   
	  private volatile Thread lookupThread;
   
   
	  private JFrame f1;
   
	  public JPanel pmain;
   
   
	  JPanel p2; // pannello comandi
	  JPanel p3; // pannello source
	  JPanel p5; // pannello messaggi output
   
	  JButton b1;
	  JButton b2;
	  JButton b3;
	  JButton b4;
	  JButton b5;
	  JButton b6;
	  JButton b7;
	  JButton b8;
	  JButton b9;
	  JButton b10;
	  JButton b11;
	  JButton b12;
	  JButton b13;
//	  JButton b14;
   
	  JButton b99;
   
	  JScrollPane paneScroll1;
	  JTextPane textPane1;
   
	  String curr_fitness_class;
	  String curr_input_data;
	  String curr_output_data;
   
	  final static String[] My_keyword = 
	  {
		 ";", 
		 "activation", 
		 "data_from_file", 
		 "data_input", 
		 "data_target", 
		 "data_from_class", 
		 "class_compute_fitness", 
		 "start_from_genome", 
		 "genome_file", 
		 "start_from_random_population", 
		 "start_from_old_population", 
		 "population_file", 
		 "maximum_unit", 
		 "recursion", 
		 "probability_of_connection", 
		 "prefix_generation_file", 
		 "prefix_winner", 
		 "prefix_genome_random", 
		 "epoch", 
		 "public", 
		 "short", 
		 "float", 
		 "double", 
		 "int", 
		 "void", 
		 "class", 
		 "static", 
		 "if", 
		 "{", 
		 "}", 
		 "(", 
		 ")", 
		 "[", 
		 "]", 
		 "for", 
		 "new", 
		 "-", 
		 "+", 
		 "*", 
		 ">", 
		 "<=", 
		 ">=", 
		 "=", 
		 "<", 
		 ">", 
		 "/", 
		 "//", 
		 "%", 
		 "+=", 
		 "return"}; 
   
	  final static String[] default_source = 
	  {
		 "; \n", 
		 "; example of skeleton file  \n", 
		 ";  is a XOR simulation with input from file\n", 
		 ";data_from_file   Y\n", 
		 "data_from_class Y\n", 
		 "data_input       bin_inp\n", 
		 "data_target      xor_out\n", 
		 "class_compute_fitness xor_fit\n", 
		 "start_from_genome Y\n", 
		 "genome_file     genome\n", 
		 ";start_from_random_population Y\n", 
		 ";start_from_old_population Y\n", 
		 "population_file primitive\n", 
		 ";maximum_unit    5\n", 
		 ";recursion       N\n", 
		 ";probability_of_connection 20\n", 
		 "epoch 10\n", 
		 "activation 0\n", 
		 ";prefix_genome_random genome.rnd \n", 
		 "prefix_generation_file generation\n", 
		 "prefix_winner    winner\n"}; 
   
/*	  final static String[] My_styles = 
	  {"regular", "italic-green", "bold-red", "bold-blu", "small", "large"}; 
*/
	  final static String[] My_styles = 
	  {"normal", "italic", "bold", "bold-italic"}; 
	  
	  
	   
	  final static String[] initFitness = 
	  {
		 "public class xor_fit { \n",
		 " \n",
		 "  public static double getMaxFitness() { return Math.pow (4.0, 2); } \n", 
		 " \n",
		 "  public static double[]  computeFitness(int _sample, int _num_nodes, double _out[][], double _tgt[][]) \n", 
		 "  {",
		 "     double d[] = new double[3]; \n",
		 "     double errorsum = 0.0; \n",
		 "     double fitness; \n",
		 "     for ( int j = 0; j < _sample; j++) \n",
		 "        { \n",
		 "           errorsum  += ( double ) (Math.abs(_tgt[j][0] - _out[j][0])); \n",
		 "        } \n",
		 "     fitness = Math.pow ( ( 4.0 - errorsum ) , 2 ); \n",
		 " \n",
		 "     d[0] = fitness; \n",
		 "     d[1] = errorsum; \n",
		 "     d[2] = 0.0; \n",
		 " \n",
		 "     if ((_out[0][0] < 0.5) && (_out[1][0] >= 0.5) &&  \n",
		 "            (_out[2][0] >= 0.5) && (_out[3][0] < 0.5)) \n",
		 "        d[2] = 1.0; \n",
		 " \n",
		 "     return d; \n",
		 "  } \n",
		 "} \n" 
	  
	  }; 
   
	  final static String[] initDataClassInput = 
	  {
		 "public class bin_inp {\n", 
		 " \n", 
		 "   public static int getNumSamples() { return 4; } \n", 
		 " \n",
		 "   public static int getNumUnit()    { return 2; } \n", 
		 " \n", 
		 "   public static double getInput( int _plist[])\n", 
		 "   { \n", 
		 " \n", 
		 "      int _index = _plist[0]; \n", 
		 "      int _col   = _plist[1]; \n", 
		 " \n", 
		 "      if ( _index < 0 )  \n", 
		 "         _index = - _index; \n", 
		 " \n", 
		 "      if ( _index >= 4 ) \n", 
		 "         _index = _index % 4;  \n", 
		 " \n", 
		 "      double d[][] = new double[4][2];  \n", 
		 " \n", 
		 "      d[0][0] = 0; \n", 
		 "      d[0][1] = 0; \n", 
		 " \n", 
		 "      d[1][0] = 1; \n", 
		 "      d[1][1] = 0; \n", 
		 " \n", 
		 "      d[2][0] = 0; \n", 
		 "      d[2][1] = 1; \n", 
		 " \n", 
		 "      d[3][0] = 1; \n", 
		 "      d[3][1] = 1; \n", 
		 " \n", 
		 "      return d[_index][_col]; \n", 
		 " \n", 
		 "   } \n", 
		 " \n", 
		 "} \n"}; 
   
	  final static String[] initDataClassOutput = 
	  {
		 "public class xor_out {\n",
		 " \n",
		 "   public static int getNumUnit() { return 1; } \n", 
		 " \n",
		 "   public static double getTarget( int _plist[]) \n", 
		 "   { \n",
		 " \n",
		 "      int _index = _plist[0];  \n",
		 "      int _col   = _plist[1];  \n",
		 "  \n",
		 "      if ( _index < 0 ) \n",
		 "         _index = - _index; \n",
		 "  \n",
		 "      if ( _index >= 4 ) \n",
		 "         _index = _index % 4; \n",
		 " \n",
		 "      double d[] = new double[4]; \n",
		 " \n",
		 "      d[0] = 0; \n",
		 "      d[1] = 1; \n",
		 "      d[2] = 1; \n",
		 "      d[3] = 0; \n",
		 " \n",
		 "      return d[_index]; \n",
		 " \n",
		 "   } \n",
		 " \n",
		 "} \n" 
	  }; 
   
   /**
   * Session constructor comment.
   */
	   public Session() {
		 super();
	  }
   
	   public Session(JFrame _f) 
	  {









		  
		 logger = new HistoryLog();
	  
//		 Font fc = new Font("Dialog", Font.PLAIN, 12);
	  
		 GridBagLayout gbl;
		 GridBagConstraints limiti;
	  
		 curr_fitness_class = null;
		 curr_input_data = null;
		 curr_output_data = null;
	  
		 f1 = _f;
	  
		 p2 = new JPanel();
		 p3 = new JPanel();
	  
		 p5 = new JPanel();
		 p5.setLayout(new BorderLayout());
	  
		 b1 = new JButton(" Load sess default ");
		 b1.addActionListener(this);
	  
		 b2 = new JButton(" Load sess file....");
		 b2.addActionListener(this);
	  
		 b3 = new JButton(" Write sess        ");
		 b3.addActionListener(this);
	  
		 b4 = new JButton(" Write sess file...");
		 b4.addActionListener(this);
	  
		 b5 = new JButton(" Load class fitness");
		 b5.addActionListener(this);
	  
		 b6 = new JButton(" Load class data input");
		 b6.addActionListener(this);
	  
		 b7 = new JButton(" Load class data target");
		 b7.addActionListener(this);
	  
		 b8 = new JButton(" Set session file  skeleton ");
		 b8.addActionListener(this);
	  
		 b9 = new JButton(" Set fitness class skeleton ");
		 b9.addActionListener(this);
	  
		 b10 = new JButton(" Set data_inp class skeleton ");
		 b10.addActionListener(this);
	  
		 b11 = new JButton(" Set data_tgt class skeleton ");
		 b11.addActionListener(this);
	  
		 b12 = new JButton(" C H E C K  keyword ");
		 b12.addActionListener(this);
	  
		 b13 = new JButton(" C O M P I L E ");
		 b13.addActionListener(this);
	  
/*		 b14 = new JButton(" clear log-window");
		 b14.addActionListener(this);
	 */ 
		 b99 = new JButton(" E X I T ");
		 b99.addActionListener(this);

		 Font fc = new Font("Dialog", Font.BOLD, 12);
		 b1.setFont(fc);
		 b2.setFont(fc);
		 b3.setFont(fc);
		 b4.setFont(fc);
		 b5.setFont(fc);
		 b6.setFont(fc);
		 b7.setFont(fc);
		 b8.setFont(fc);
		 b9.setFont(fc);
		 b10.setFont(fc);
		 b11.setFont(fc);
		 b12.setFont(fc);
		 b13.setFont(fc);
//		 b14.setFont(fc);
		 b99.setFont(fc);
	  
		 p2.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder("Command options"), 
			BorderFactory.createEmptyBorder(10, 2, 2, 2))); 
	   
		 p3.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder(" Edit session "), 
			BorderFactory.createEmptyBorder(10, 10, 2, 2))); 
	  
		 p5.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder(" log messages.... "), 
			BorderFactory.createEmptyBorder(10, 10, 2, 2))); 
	  
		 gbl = new GridBagLayout();
		 limiti = new GridBagConstraints();
		 p2.setLayout(gbl);
	  
		 buildConstraints(limiti, 0, 1, 1, 2, 100, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b1, limiti);
		 p2.add(b1);
	  
		 buildConstraints(limiti, 0, 3, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b2, limiti);
		 p2.add(b2);
	  
		 buildConstraints(limiti, 0, 5, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b3, limiti);
		 p2.add(b3);
	  
		 buildConstraints(limiti, 0, 7, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b4, limiti);
		 p2.add(b4);
	  
		 buildConstraints(limiti, 0, 9, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b5, limiti);
		 p2.add(b5);
	  
		 buildConstraints(limiti, 0, 11, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b6, limiti);
		 p2.add(b6);
	  
		 buildConstraints(limiti, 0, 13, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b7, limiti);
		 p2.add(b7);
	  
		 buildConstraints(limiti, 0, 15, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b8, limiti);
		 p2.add(b8);
	  
		 buildConstraints(limiti, 0, 17, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b9, limiti);
		 p2.add(b9);
	  
		 buildConstraints(limiti, 0, 19, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b10, limiti);
		 p2.add(b10);
	  
		 buildConstraints(limiti, 0, 21, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b11, limiti);
		 p2.add(b11);
	  
		 buildConstraints(limiti, 0, 23, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b12, limiti);
		 p2.add(b12);
	  
		 buildConstraints(limiti, 0, 25, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b13, limiti);
		 p2.add(b13);
/*	  
		 buildConstraints(limiti, 0, 27, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b14, limiti);
		 p2.add(b14);
*/	  
		 buildConstraints(limiti, 0, 27, 1, 2, 0, 35);
		 limiti.anchor = GridBagConstraints.SOUTH;
		 limiti.fill = GridBagConstraints.HORIZONTAL;
		 limiti.ipady = 20;
		 gbl.setConstraints(b99, limiti);
		 p2.add(b99);
	  
		 textPane1 = new JTextPane();
		 textPane1.setEditable(true);
		 textPane1.setBackground(new Color(255, 252, 242));
	  
		 paneScroll1 = new JScrollPane(textPane1);
		 paneScroll1.setVerticalScrollBarPolicy(paneScroll1.VERTICAL_SCROLLBAR_ALWAYS);
		 paneScroll1.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createEmptyBorder(2, 2, 2, 2), 
			BorderFactory.createEtchedBorder())); 
	  
		 setStyleNew();
		 setSourceNew(default_source);
	  
		 gbl = new GridBagLayout();
		 limiti = new GridBagConstraints();
		 p3.setLayout(gbl);
	  
		 buildConstraints(limiti, 0, 0, 1, 1, 100, 100);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(paneScroll1, limiti);
		 p3.add(paneScroll1);
	  
	  
	  
	  
	  
	  
	  
	  
		 pmain = new JPanel();
		 gbl = new GridBagLayout();
		 pmain.setLayout(gbl);
	  
		 limiti = new GridBagConstraints();
		 buildConstraints(limiti, 0, 0, 1, 5, 0, 100);
		 limiti.anchor = GridBagConstraints.WEST;
		 limiti.fill = GridBagConstraints.VERTICAL;
		 pmain.add(p2);
		 gbl.setConstraints(p2, limiti);
	  
		 limiti = new GridBagConstraints();
		 buildConstraints(limiti, 1, 0, 4, 5, 100, 0);
		 limiti.anchor = GridBagConstraints.WEST;
		 limiti.fill = GridBagConstraints.BOTH;
		 pmain.add(paneScroll1);
		 gbl.setConstraints(paneScroll1, limiti);
	  
	  
	  // interface to main method of this class
		 contentPane = f1.getContentPane();
		 BorderLayout bl = new BorderLayout();
		 contentPane.setLayout(bl);
		 contentPane.add(pmain, BorderLayout.CENTER);
		 contentPane.add(logger,BorderLayout.SOUTH);
	  
	  
		 EnvConstant.OP_SYSTEM = System.getProperty("os.name");
		 EnvConstant.OS_VERSION = System.getProperty("os.version");
		 EnvConstant.JNEAT_DIR = System.getProperty("user.dir");
		 EnvConstant.OS_FILE_SEP = System.getProperty("file.separator");

		 
}
   
   /**
   * Starts the application.
   * @param args an array of command-line arguments
   */
	   public static void main(java.lang.String[] args) {
	  // Insert code to start the application here.
	  
		 JFrame jp = null;
		 Session pn1 = null;
	  
		 try {
			jp = new JFrame("  experiment ");
			pn1 = new Session(jp);
		 
		 //  jp.getContentPane().add(pn1);
			jp.addWindowListener(
				   new java.awt.event.WindowAdapter() {
					  public void windowClosing(WindowEvent e) {
						System.exit(0);
					 }
				  });
		 
			jp.pack();
			jp.setSize(800, 600);
			jp.setVisible(true);
		 } 
			 catch (Exception ex) {
			   System.err.println("ERRORE");
			}
	  
	  }
   
	   public void setStyle() 
	  {
	  
		 Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE); 
	  
		 Style regular = textPane1.addStyle("regular", def);
		 StyleConstants.setFontFamily(def, "Verdana");
		 
	  
		 Style s = textPane1.addStyle("italic-green", regular);
		 StyleConstants.setItalic(s, true);
	  
		 s = textPane1.addStyle("bold-red", regular);
		 StyleConstants.setBold(s, true);
		 StyleConstants.setForeground(s, Color.red);
	  
		 s = textPane1.addStyle("bold-blu", regular);
		 StyleConstants.setBold(s, true);
		 StyleConstants.setForeground(s, Color.black);
	  
		 s = textPane1.addStyle("small", regular);
		 StyleConstants.setFontSize(s, 10);
	  
		 s = textPane1.addStyle("large", regular);
		 StyleConstants.setFontSize(s, 16);

		 int nr = def.getAttributeCount();
		 
		 



		 
	  
	  }
   
	   public String[] convertToArray(String _text) 
	  {
	  
		 String s1 = _text;
		 StringTokenizer riga;
		 String elem;
		 int sz;
		 riga = new StringTokenizer(s1, "\n");
		 sz = riga.countTokens();
		 String[] source_new = new String[sz];
	  
		 for (int r = 0; r < sz; r++) 
		 {
			elem = (String) riga.nextToken();
		 //   System.out.print("\n conv.to.string --> elem["+r+"] --> "+elem);
			source_new[r] = new String(elem + "\n");
		 }
		 return source_new;
	  
	  }
   
	   public void actionPerformed(ActionEvent e) {
	  
		 String tmp1;
		 String tmp2;
		 String nomef;
	  
		 JButton Pulsante = (JButton) e.getSource();
	  
		 if (e.getActionCommand().equals(" E X I T ")) {
			System.exit(0);
		 } 
		 
		 
		 else if (e.getActionCommand().equals(" Load sess default ")) 
		 {
			logger.sendToStatus("wait....");
			EnvConstant.EDIT_STATUS = 0;
			nomef = EnvRoutine.getJneatSession();
			logger.sendToLog(" session: wait loading -> "+nomef);
			StringTokenizer st;
			String xline;
			IOseq xFile;
		 
			xFile = new IOseq(nomef);
			boolean rc = xFile.IOseqOpenR();
			if (rc) 
			{
			
			   StringBuffer sb1 = new StringBuffer("");
			   try 
			   {
				  xline = xFile.IOseqRead();
			   
				  while (xline != "EOF") 
				  {
					 sb1.append(xline + "\n");
					 xline = xFile.IOseqRead();
				  }
			   
				  textPane1.setText("");
				  String[] source_new = convertToArray(sb1.toString());
				  setSourceNew(source_new);
				  logger.sendToLog(" ok file loaded");
				  logger.sendToStatus("READY");
			   
			   
			   } 
			   
				   catch (Throwable e1) 
				  {
					 logger.sendToStatus("READY");
					 logger.sendToLog(" session: error during read "+e1);
				  }
			
			   xFile.IOseqCloseR();
			
			} 
			
			else
			{
			   logger.sendToStatus("READY");
			   logger.sendToLog(" session: file not found");
			}
		 
		 
		 
		 } 
		 
		 else if (e.getActionCommand().equals(" Load sess file....")) 
		 {
		 
			EnvConstant.EDIT_STATUS = 0;
		 
			FileDialog fd = new FileDialog(f1, "load session file", FileDialog.LOAD);
			fd.setVisible(true);
			tmp1 = fd.getDirectory();
			tmp2 = fd.getFile();
		 
			if (tmp1 != null && tmp2 != null) 
			{
			   logger.sendToStatus("wait....");
			   nomef = tmp1 + tmp2;
			   logger.sendToLog(" session: wait loading -> "+nomef);
			   StringTokenizer st;
			   String xline;
			   IOseq xFile;
			
			   xFile = new IOseq(nomef);
			   xFile.IOseqOpenR();
			   StringBuffer sb1 = new StringBuffer("");
			   try 
			   {
				  xline = xFile.IOseqRead();
				  while (xline != "EOF") 
				  {
					 sb1.append(xline + "\n");
					 xline = xFile.IOseqRead();
				  }
				  textPane1.setText("");
				  String[] source_new = convertToArray(sb1.toString());
				  setSourceNew(source_new);
				  logger.sendToLog(" ok file loaded");
				  logger.sendToStatus("READY");
			   } 
			   
				   catch (Throwable e1) 
				  {
					 logger.sendToStatus("READY");
					 logger.sendToLog(" session: error during read "+e1);
				  }
			
			   xFile.IOseqCloseR();
			}
		 
		 }
		 
		 else if (e.getActionCommand().equals(" Write sess        ")) 
		 {
		 
			EnvConstant.EDIT_STATUS = 0;
		 
			nomef = EnvRoutine.getJneatSession();
			logger.sendToStatus("wait....");
			logger.sendToLog(" session: wait writing -> "+nomef);
			IOseq xFile;
			xFile = new IOseq(nomef);
			xFile.IOseqOpenW(false);
		 
			try {
			
			   String s1 = textPane1.getText();
			   StringTokenizer riga;
			   String elem;
			   int sz;
			   riga = new StringTokenizer(s1, "\n");
			   sz = riga.countTokens();
			
			   for (int r = 0; r < sz; r++) {
				  elem = (String) riga.nextElement();
				  String elem1 = new String(elem); //+"\n");
				  xFile.IOseqWrite(elem);
			   }
			
			   logger.sendToLog(" ok file writed");
			
			
			
			} 
				catch (Throwable e1) 
			   {
				  logger.sendToStatus("READY");
				  logger.sendToLog(" session: error during write "+e1);
			   }
		 
			xFile.IOseqCloseW();
			logger.sendToStatus("READY");
		 
		 } 
		 
		 else if (e.getActionCommand().equals(" Write sess file...")) 
		 {
			EnvConstant.EDIT_STATUS = 0;
			FileDialog fd = new FileDialog(f1, "save session file", FileDialog.SAVE);
			fd.setVisible(true);
		 
			tmp1 = fd.getDirectory();
			tmp2 = fd.getFile();
		 
			if (tmp1 != null && tmp2 != null) 
			{
			
			   logger.sendToStatus("wait....");
			   nomef = tmp1 + tmp2;
			   logger.sendToLog(" session: wait writing -> "+nomef);
				//
				// write to file genome in native format (for re-read)
				//
			   IOseq xFile;
			   xFile = new IOseq(nomef);
			   xFile.IOseqOpenW(false);
			
			   try 
			   {
				  String s1 = textPane1.getText();
				  StringTokenizer riga;
				  String elem;
				  int sz;
				  riga = new StringTokenizer(s1, "\n");
				  sz = riga.countTokens();
			   
				  for (int r = 0; r < sz; r++) 
				  {
					 elem = (String) riga.nextElement();
					 String elem1 = new String(elem); //+"\n");
					 xFile.IOseqWrite(elem);
				  }
				  logger.sendToLog(" ok file writed");
			   
			   
			   } 
			   
				   catch (Throwable e1) 
				  {
					 logger.sendToStatus("READY");
					 logger.sendToLog(" session: error during write "+e1);
				  }
			
			   xFile.IOseqCloseW();
			   logger.sendToStatus("READY");
			
			
			}
		 
		 } 
		 
		 else if (e.getActionCommand().equals(" C H E C K  keyword ")) 
		 {
			logger.sendToStatus("wait...");
			String[] source_new = convertToArray(textPane1.getText());
			textPane1.setText("");
			setSourceNew(source_new);
			logger.sendToStatus("READY");
		 } 
		 
		 else if (e.getActionCommand().equals(" Set session file  skeleton ")) 
		 {
			logger.sendToStatus("wait...");
			EnvConstant.EDIT_STATUS = 0;
			textPane1.setText("");
			setSourceNew(default_source);
			logger.sendToLog(" session: set to default skeleton for session");
			logger.sendToStatus("READY");
		 } 
		 
		 else if (e.getActionCommand().equals(" Set fitness class skeleton ")) 
		 {
			logger.sendToStatus("wait...");
			EnvConstant.EDIT_STATUS = EnvConstant.EDIT_CLASS_FIT;
			textPane1.setText("");
		 
			setSourceNew(initFitness);
		 
			logger.sendToLog(" session: set to default skeleton for fitness");
			logger.sendToStatus("READY");
		 } 
		 
		 else if (e.getActionCommand().equals(" Set data_inp class skeleton ")) 
		 {
			logger.sendToStatus("wait...");
			EnvConstant.EDIT_STATUS = EnvConstant.EDIT_CLASS_INP;
			textPane1.setText("");
			setSourceNew(initDataClassInput);
			logger.sendToLog(" session: set to default skeleton for  class/dataset generate input");
			logger.sendToStatus("READY");
		 } 
		 
		 else if (e.getActionCommand().equals(" Set data_tgt class skeleton ")) 
		 {
			logger.sendToStatus("wait...");
			EnvConstant.EDIT_STATUS = EnvConstant.EDIT_CLASS_OUT;
			textPane1.setText("");
			setSourceNew(initDataClassOutput);
			logger.sendToLog(" session: set to default skeleton for  class/dataset generate output");
			logger.sendToStatus("READY");
		 } 
		 
		 else if (e.getActionCommand().equals(" Load class data target")) 
		 {
			logger.sendToStatus("wait...");
			EnvConstant.EDIT_STATUS = EnvConstant.EDIT_CLASS_OUT;
			if (curr_output_data != null) 
			{
			   load_from_disk_Class(curr_output_data,"data");
			}
			else
			    logger.sendToLog(" session: *warning* before load data-out , load the sesssion !");

			    
			logger.sendToStatus("READY");
		 } 
		 
		 else if (e.getActionCommand().equals(" Load class fitness")) 
		 {
			logger.sendToStatus("wait...");
			EnvConstant.EDIT_STATUS = EnvConstant.EDIT_CLASS_FIT;
			if (curr_fitness_class != null) 
			{
			   load_from_disk_Class(curr_fitness_class,"fitness");
			}
			else
			   logger.sendToLog(" session: *warning* before load fitness , load the sesssion !");

			logger.sendToStatus("READY");
		 } 
		 
		 else if (e.getActionCommand().equals(" Load class data input")) 
		 {
			logger.sendToStatus("wait...");
			EnvConstant.EDIT_STATUS = EnvConstant.EDIT_CLASS_INP;
			if (curr_input_data != null) 
			{
			   load_from_disk_Class(curr_input_data,"data");
			}
			else
			   logger.sendToLog(" session: *warning* before load data-in , load the sesssion !");

			logger.sendToStatus("READY");
		 } 

		 
		  
		 else if (e.getActionCommand().equals(" C O M P I L E ")) 
		 {
			if (EnvConstant.EDIT_STATUS == EnvConstant.EDIT_CLASS_FIT) 
			{
			   if (curr_fitness_class != null) 
			   {
				  EnvConstant.CURRENT_CLASS = curr_fitness_class;
				  Async_generationClass();
			   }
			}

			
		    else if ( EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_CLASS)
			{		 
			
				if (EnvConstant.EDIT_STATUS == EnvConstant.EDIT_CLASS_INP) 
				{
			   		if (curr_input_data != null) 
			   		{
				  		EnvConstant.CURRENT_CLASS = curr_input_data;
				  		Async_generationClass();
			   		}
				} 
			
				else if (EnvConstant.EDIT_STATUS == EnvConstant.EDIT_CLASS_OUT) 
				{
			   		if (curr_output_data != null) 
			   		{
				  		EnvConstant.CURRENT_CLASS = curr_output_data;
				  		Async_generationClass();
			   		}
				}

			 }



		    else if ( EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_FILE)
			{		 
			
				if (EnvConstant.EDIT_STATUS == EnvConstant.EDIT_CLASS_INP) 
				{
			   		if (curr_input_data != null) 
			   		{
				  		EnvConstant.CURRENT_FILE = curr_input_data;
				  		Async_generationFile();
			   		}
				} 
			
				else if (EnvConstant.EDIT_STATUS == EnvConstant.EDIT_CLASS_OUT) 
				{
			   		if (curr_output_data != null) 
			   		{
			
				  		EnvConstant.CURRENT_FILE = curr_output_data;
				  		Async_generationFile();
			
			   		}
				}

			 }




			

			
		 }










		 
	  
	  }
   
	   public void buildConstraints(
	   GridBagConstraints gbc, 
	   int gx, 
	   int gy, 
	   int gw, 
	   int gh, 
	   int wx, 
	   int wy) 
	  {
		 gbc.gridx = gx;
		 gbc.gridy = gy;
		 gbc.gridwidth = gw;
		 gbc.gridheight = gh;
		 gbc.weightx = wx;
		 gbc.weighty = wy;
	  }
   

   
   
   
   
   
	   public void setLog(HistoryLog _log) 
	  {
		 logger = _log;
	  }        
   
   
   




   
	   public void createClass(String _filename, String[] sourcecode) 
	  {
		 try 
		 {
			FileWriter aWriter = new FileWriter(_filename, false);
		 
			for (int r = 0; r < sourcecode.length; r++)
			   aWriter.write(sourcecode[r]);
		 
			aWriter.flush();
			aWriter.close();
		 } 
		 
			 catch (Exception e) {
			   e.printStackTrace();
			}
	  }
   







		   


	   public boolean compileClass(String _filename) 
	  {
	  
		 String[] source = { new String(_filename) };
		 PrintStream ps = System.err;
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 System.setErr(new PrintStream(baos));

/*
		 
	  // jdk 1.1.8
	  //
	  
		 new sun.tools.javac.Main(baos, source[0]).compile(source);
		 System.setErr(ps);
		 if (baos.toString().indexOf("error") == -1)
			return true;
		 else {
		 
			try {
			
			   logger.sendToLog(" session: *warning* error during compilation : ");
			   logger.sendToLog(" session: "+baos.toString());
			
			} 
				catch (Throwable e1) {
				  System.err.println(e1 + " session: error in try-compile  "+e1);
			   }
			return false;
		 }

		*/
	


	  // jdk 1.3.1_01
	  //
	  
	  com.sun.tools.javac.Main m1 = new com.sun.tools.javac.Main();
	  m1.compile(source);
	  System.setErr(ps);
	  
	  Date xdata = new Date();
	  if (baos.toString().indexOf("error")==-1)
	  	return true;
	  else
	  {
	  
	  
	  	try 
	  	{
		   	logger.sendToLog(" session: *warning* error during compilation : ");
		   	logger.sendToLog(" session: "+baos.toString());
	  
	  	} 
	  	
	  	catch (Throwable e1) 
	  	{
	  		System.err.println(e1 + " session: error in try-compile  "+e1);
	  	}
	  	return false;
	  }
	  
	  
	  
	  
	  
	  }
   







	   public void Async_generationClass() {
		 Runnable lookupRun = 
			 new Runnable() {
				public void run() {
				  generationClass();
			   }
			};
		 lookupThread = new Thread(lookupRun, " looktest");
		 lookupThread.start();
	  }
	   public void generationClass() 
	  {
	  
		 String _classname = EnvConstant.CURRENT_CLASS;
		 String nomef = null;
	  
		 logger.sendToStatus("wait....");
		 try {
		 
			logger.sendToLog(" session: start compile ->" + _classname + " in dir ->" + EnvConstant.JNEAT_DIR); 
		 // legge corrente nome source della classe da creare
		 //
			nomef = EnvRoutine.getJneatFile(_classname + ".java");
		 // converte da stringa unica a vettore di stringhe
			String[] source_new = convertToArray(textPane1.getText());
			logger.sendToLog(" session: creation source " + _classname + ".java");
		 // genera il source.java
		 //
			createClass(nomef, source_new);
			logger.sendToLog(" session: terminate creation source");
			logger.sendToLog(" session: creation class " + _classname + ".class");
		 // genera il file .class
		 //
			compileClass(nomef);
			logger.sendToLog(" session: terminate creation class " + _classname + ".class"); 
		 // riaggiorna il pannello con quello che ha appena scritto
			textPane1.setText("");
			setSourceNew(source_new);
			logger.sendToStatus("READY");
		 
		 } 
		 
			 catch (Throwable e1) 
			{
			   logger.sendToLog(" session: error during compile fitness "+e1);
			}
		 logger.sendToStatus("READY");
	  
	  }
public void setSourceNew(String[] _source) {
	StringTokenizer riga;
	String elem;
	int sz;
	String prev_word;
	boolean fnd;
	Document doc = textPane1.getDocument();

	try {
		for (int i = 0; i < _source.length; i++) 
		{
			// search name for fitness class;
			// search i/o class or files for input/target signal
			//

			int b1[] = new int[_source[i].length()];
			for (int j = 0; j < b1.length; j++)
				b1[j] = 0;

			String zriga = _source[i];
			int pos = 0;
			
			for (int k = 0; k < My_keyword.length; k++) {
				String ckey = My_keyword[k];
				pos = zriga.indexOf(ckey, 0);
				if (pos != -1) 
				{
					for (int k1 = 0; k1 < ckey.length(); k1++)
						b1[pos + k1] = 1;
					boolean done = false;
					int offset = pos + ckey.length();
					while (!done) 
					{
						pos = zriga.indexOf(ckey, offset);
						if (pos != -1) 
						{
							for (int k1 = 0; k1 < ckey.length(); k1++)
								b1[pos + k1] = 1;
							offset = pos + ckey.length();
						}
						
						else
							done = true;
							
					}

				}
			}

			int n1 = 0;
			int n2 = 0;
			int v1 = 0;
			int v2 = 0;
			int k2 = 0;

			boolean comment = false;
			for (int k1 = 0; k1 < b1.length; k1++) {
				v1 = b1[k1];
				if (v1 == 1) 
					{
				        if (zriga.substring(k1,k1+1).equals(";"))
				        {
					      comment = true;
					      break; 
				        }
				        else
				          comment = false;
				          break;
					}     
			}
				

			if (comment)
			{
			   doc.insertString(doc.getLength(), zriga, textPane1.getStyle(My_styles[1]));
			}

			else
			{
			

			// cerca fino a che non trova n1 != n2;
			//int lun = 0;
			boolean again = true;
			for (int k1 = 0; k1 < b1.length; k1++) {
				v1 = b1[n1];
				n2 = n1;
				again = false;
				for (k2 = n1 + 1; k2 < b1.length; k2++) {
					v2 = b1[k2];
					if (v2 != v1) {
						again = true;
						break;
					}
					n2 = k2;
				}

				elem = zriga.substring(n1, n2 + 1);

				if (v1 == 0)
					doc.insertString(doc.getLength(), elem, textPane1.getStyle(My_styles[0]));
				else
					doc.insertString(doc.getLength(), elem, textPane1.getStyle(My_styles[2]));
				//System.out.print("\n n1="+n1+" n2="+n2+" found elem ->"+elem+"<- size("+elem.length()+")");
				k1 = k2;
				n1 = k2;

			}

			if (again) {
				elem = zriga.substring(b1.length - 1, b1.length);
				if (b1[b1.length - 1] == 0)
					doc.insertString(doc.getLength(), elem, textPane1.getStyle(My_styles[0]));
				else
					doc.insertString(doc.getLength(), elem, textPane1.getStyle(My_styles[2]));

				//   System.out.print("\n **WW* found elem ->"+elem+"<- size("+elem.length()+")");

			}

			riga = new StringTokenizer(zriga);

			sz = riga.countTokens();
			prev_word = null;
			for (int r = 0; r < sz; r++) 
			{
				elem = riga.nextToken();
				fnd = false;
				for (int k = 0; k < My_keyword.length; k++) 
				{
					if (My_keyword[k].equalsIgnoreCase(elem)) 
					{
						fnd = true;
						break;
					}
				}





				if ((prev_word != null) && (prev_word.equalsIgnoreCase("data_from_file"))) 
				{
			       if ((!comment) && elem.equalsIgnoreCase("Y"))
			       {
				      EnvConstant.TYPE_OF_SIMULATION = EnvConstant.SIMULATION_FROM_FILE;
				   }
				}


				if ((prev_word != null) && (prev_word.equalsIgnoreCase("data_from_class"))) 
				{
			       if ((!comment) && elem.equalsIgnoreCase("Y"))
			       {
				      EnvConstant.TYPE_OF_SIMULATION = EnvConstant.SIMULATION_FROM_CLASS;
			       }
				}




				
				if ((prev_word != null)
					&& (prev_word.equalsIgnoreCase("class_compute_fitness"))) {
					curr_fitness_class = new String(elem);
				}

				if ((prev_word != null) && (prev_word.equalsIgnoreCase("data_input"))) {
					curr_input_data = new String(elem);
				}

				if ((prev_word != null) && (prev_word.equalsIgnoreCase("data_target"))) {
					curr_output_data = new String(elem);
				}
				prev_word = elem;

			}

		}


		}    

		textPane1.setCaretPosition(1);

	} catch (Exception e1) {
		logger.sendToStatus(" session: Couldn't insert initial text.:" + e1);
	}

}
   
   
   	   public void Async_generationFile() {
		 Runnable lookupRun = 
			 new Runnable() {
				public void run() {
				  generationFile();
			   }
			};
		 lookupThread = new Thread(lookupRun, " looktest");
		 lookupThread.start();
	  }	   public void generationFile() 
	   {
	        String _fname = EnvConstant.CURRENT_FILE;

		    logger.sendToStatus("wait....");
			logger.sendToLog(" session: start write file " + EnvRoutine.getJneatFile(_fname));
			IOseq xFile;
			xFile = new IOseq(EnvRoutine.getJneatFile(_fname));
			xFile.IOseqOpenW(false);
		 
			try 
			{
			
			   String s1 = textPane1.getText();
			   StringTokenizer riga;
			   String elem;
			   int sz;
			   riga = new StringTokenizer(s1, "\n");
			   sz = riga.countTokens();
			
			   for (int r = 0; r < sz; r++) {
				  elem = (String) riga.nextElement();
				  String elem1 = new String(elem); //+"\n");
				  xFile.IOseqWrite(elem);
			   }
			
			   logger.sendToLog(" ok file writed");
			
			
			
			} 
				
			
			catch (Throwable e1) 
			   {
				  logger.sendToStatus("READY");
				  logger.sendToLog(" session: error during write "+e1);
			   }
		 
			xFile.IOseqCloseW();
			logger.sendToStatus("READY");

	     
	     
	  
	  }	   public void load_from_disk_Class(String _filename,String _type) 
	  {
		 String nomef = null;

		 if  (_type.equalsIgnoreCase("fitness"))
			 nomef = EnvRoutine.getJneatFile(_filename + ".java");

		 else
		 { 
		     if ( EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_CLASS)
		        nomef = EnvRoutine.getJneatFile(_filename + ".java");
		     else
 		        nomef = EnvRoutine.getJneatFile(_filename);
		 } 

 		    
		 StringTokenizer st;
		 String xline;
		 IOseq xFile;
	  
		 xFile = new IOseq(nomef);
		 boolean exist = xFile.IOseqOpenR();
	  
		 if (exist) 
		 {
		 
			StringBuffer sb1 = new StringBuffer("");
			try 
			{
			
			   logger.sendToStatus(" session: wait....");
			   logger.sendToLog("  session: wait loading " + nomef + "...");
			   xline = xFile.IOseqRead();
			
			   while (xline != "EOF") {
				  sb1.append(xline + "\n");
				  xline = xFile.IOseqRead();
			   }
			
			   textPane1.setText("");
			   String[] source_new = convertToArray(sb1.toString());
			   setSourceNew(source_new);
			
			   logger.sendToLog(" session: wait loaded " + nomef);
			   logger.sendToStatus("READY");
			
			} 
			
				catch (Throwable e1) 
			   {
			      logger.sendToLog(" session: error during read " + nomef + " " + e1);
			   }
		 
			xFile.IOseqCloseR();
			logger.sendToStatus("READY");
		 
		 } // exist cycle
		 
		 else 
		 {
		 
			try 
			{
			   logger.sendToLog("  session: warning : file  " + nomef + " not exist!");
			} 
			
				catch (Throwable e2) {
				  System.err.println(e2 + " session: error during text processing " + e2);
			   }
		 
			logger.sendToStatus("READY");
		 
		 }
	  
	  }	


			 public void setStyleNew() 
	  {


		 StyleContext      stylecontext = StyleContext.getDefaultStyleContext();
		 Style             defstyle     = stylecontext.getStyle(StyleContext.DEFAULT_STYLE);
		 
		 Style             style        = textPane1.addStyle("normal", defstyle);
		 StyleConstants.setFontFamily(style, "Verdana ");
		 StyleConstants.setFontSize(style, 12);


		 style = textPane1.addStyle("italic", defstyle);
//		 StyleConstants.setForeground(style, new Color(24, 35, 87));
		 StyleConstants.setItalic(style, true);
		 StyleConstants.setFontSize(style, 11);
		

		 style = textPane1.addStyle("bold", defstyle);
//		 StyleConstants.setForeground(style, new Color(24, 35, 87));
		 StyleConstants.setBold(style, true);
		 StyleConstants.setFontSize(style, 13);


		 style = textPane1.addStyle("bold-italic", defstyle);
		 StyleConstants.setItalic(style, false);
		 StyleConstants.setBold(style, false);
		 StyleConstants.setFontSize(style, 12);

		 
	  
	  }


}