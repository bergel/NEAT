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

   import jGraph.*;

   import jneat.*;
   import jNeatCommon.*;
   import log.*;
      public class Grafi extends JPanel implements ActionListener, ListSelectionListener, ItemListener
   
   {
	  private JFrame f1;
	  public JPanel pmain;
   
   
	  JPanel p2; // pannello comandi
	  JPanel p3; // pannello grafico
   
//	  JPanel p5; // pannello statistiche
	  JButton b1;
	  JButton b2;
	  JButton b3;

	  // run the network....
	  JButton b4; 

	  
	  JButton b5;
   
	  Vector listdata;
	  JList jList1;
	  JScrollPane paneScroll1;
	  JScrollPane paneScroll2;
	  chartXY mappa;
/*	  chartXY mappaFirst;
	 */ int quadro_x;
	  int quadro_y;
	  Population Popx;		

	  
	  Genome GxView;
	   
	  JTextArea textArea;
   
	  Container contentPane;
	  protected HistoryLog   logger;
   
   
   	  ButtonGroup ck_group;
	  JRadioButton ck1;
	  JRadioButton ck2;

   	  ButtonGroup ck_group_tipo;
	  JRadioButton ck_tipo_normal;
	  JRadioButton ck_tipo_spessore;
   
   
   
   /**
   * 
   */
	   public Grafi(JFrame _f) 
	  {
	  
		 GridBagLayout gbl;
		 GridBagConstraints limiti;
	  
		 logger = new HistoryLog();

		 GxView = null;

		 
		 f1 = _f;
	  
		 quadro_x = 450;
		 quadro_y = 450;
	  
		 p2 = new JPanel();
	  
		 p3 = new JPanel();
		 p3.setLayout(new BorderLayout());

//		       p3.setLayout(new GridLayout(1,1));

		 
	  
/*		 p5 = new JPanel();
		 p5.setLayout(new BorderLayout());
	 */ 
		 b1 = new JButton(" Load genome ");
		 b1.addActionListener(this);
	  
		 b2 = new JButton(" Load Pop. ");
		 b2.addActionListener(this);
	  
		 b3 = new JButton(" Load winner from Pop.");
		 b3.addActionListener(this);

		 
		 b4 = new JButton("exec single genome");
		 b4.addActionListener(this);


		 ck_group = new ButtonGroup();
		 ck1 = new JRadioButton("view and exec", true);
		 ck1.setActionCommand("view and exec");
	 	 ck1.addItemListener(this);
	 	 
		 ck2 = new JRadioButton("view only", false);
		 ck2.setActionCommand("view only");
		 ck2.addItemListener(this);
	  
		 ck_group.add(ck1);
		 ck_group.add(ck2);







		 ck_group_tipo = new ButtonGroup();
		 ck_tipo_normal = new JRadioButton("link normal", true);
		 ck_tipo_normal.setActionCommand("link normal");
		 ck_tipo_normal.addItemListener(this);
		 
		  
		 ck_tipo_spessore = new JRadioButton("link with 'width'", false);
		 ck_tipo_spessore.setActionCommand("link with 'width'");
		 ck_tipo_spessore.addItemListener(this);
	  
		 ck_group_tipo.add(ck_tipo_normal);
		 ck_group_tipo.add(ck_tipo_spessore);




		 
		 
		  
		 b5 = new JButton(" E X I T ");
		 b5.addActionListener(this);



		 Font fc = new Font("Dialog", Font.BOLD,12);
		 b1.setFont(fc);
		 b2.setFont(fc);
		 b3.setFont(fc);
		 b4.setFont(fc);
		 b5.setFont(fc);




		 
	  
		 gbl = new GridBagLayout();
		 limiti = new GridBagConstraints();
		 p2.setLayout(gbl);
	  
		 p2.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder("Command options"), 
			BorderFactory.createEmptyBorder(10, 2, 2, 2))); 

		 textArea = new JTextArea("", 10, 30);
		 Font fontArea = new Font("Courier", Font.PLAIN, 11);
		 textArea.setFont(fontArea);
		 textArea.setLineWrap(true);
		 textArea.setEditable(false);
		 textArea.setOpaque(true);
		 textArea.setWrapStyleWord(true);
		 textArea.setVisible(true);
//		 textArea.setBackground(new Color(196, 200, 200));
		 		 textArea.setBackground(new Color(255, 252, 242));

	   
		 paneScroll2 = new JScrollPane(textArea);
		 paneScroll2.setVerticalScrollBarPolicy(
			paneScroll2.VERTICAL_SCROLLBAR_AS_NEEDED); 
		 paneScroll2.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createEmptyBorder(2, 2, 2, 2), 
			BorderFactory.createEtchedBorder())); 
//		 p5.add(paneScroll2);

		 buildConstraints(limiti, 0, 1, 1, 2, 120, 4);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b1, limiti);
		 p2.add(b1);
	  
		 buildConstraints(limiti, 0, 3, 1, 2, 0, 4);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b2, limiti);
		 p2.add(b2);
	  
	  
		 buildConstraints(limiti, 0, 5, 1, 2, 0, 4);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b3, limiti);
		 p2.add(b3);

		 
		 buildConstraints(limiti, 0, 7, 1, 2, 0, 4);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b4, limiti);
		 p2.add(b4);





		 
		 buildConstraints(limiti, 0, 9, 1, 1, 0, 4);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(ck1, limiti);
		 p2.add(ck1);

		 buildConstraints(limiti, 0, 10, 1, 1, 0, 4);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(ck2, limiti);
		 p2.add(ck2);




		 
		 
		 buildConstraints(limiti, 0, 11, 1, 1, 0, 4);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(ck_tipo_normal, limiti);
		 p2.add(ck_tipo_normal);

		 buildConstraints(limiti, 0, 12, 1, 1, 0, 4);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(ck_tipo_spessore, limiti);
		 p2.add(ck_tipo_spessore);












		 
		  
		 listdata = new Vector(1, 0);
		 listdata.add(" ");
		 jList1 = new JList(listdata);
		 jList1.setBackground(new Color(255, 252, 242));
		 jList1.addListSelectionListener(this);
	  
		 paneScroll1 = new JScrollPane(jList1);
		 paneScroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	  
		 buildConstraints(limiti, 0, 13, 1, 6, 0, 14);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(paneScroll1, limiti);
		 p2.add(paneScroll1);
	  
		 buildConstraints(limiti, 0, 20, 1, 2, 0, 4);
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b5, limiti);
		 p2.add(b5);
		 
		  
		 mappa = new chartXY(" ");
		 mappa.setLayout(new BorderLayout());


		 p3.setMinimumSize(new Dimension(100, 100)); //100,100
		 p3.setPreferredSize(new Dimension(400, 400)); //300,200



		 
//		 mappa.setScale(p3.getWidth(), p3.getHeight());
		 p3.add(mappa, BorderLayout.CENTER);
		 p3.add(paneScroll2, BorderLayout.EAST);

   
		 


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
		 buildConstraints(limiti, 1, 1, 4, 4, 100, 0);
		 limiti.anchor = GridBagConstraints.WEST;
		 limiti.fill = GridBagConstraints.BOTH;
//		 pmain.add(paneSplit1);
		 pmain.add(p3);
		 gbl.setConstraints(p3, limiti);

		 
	  
	  // interface to main method of this class
		 contentPane = f1.getContentPane();
		 BorderLayout bl = new BorderLayout();
		 contentPane.setLayout(bl);
		 contentPane.add(pmain, BorderLayout.CENTER);
		 contentPane.add(logger, BorderLayout.SOUTH);
	  
		 EnvConstant.OP_SYSTEM = System.getProperty("os.name");
		 EnvConstant.OS_VERSION = System.getProperty("os.version");
		 EnvConstant.JNEAT_DIR = System.getProperty("user.dir");
		 EnvConstant.OS_FILE_SEP = System.getProperty("file.separator");
	  
	  }
   
   
   
   
   
   
	   public void setLog(HistoryLog _log) 
	  {
		 logger = _log;
	  }                          
   
   
   
   
   
   
   
   
   
   /**
   * Starts the application.
   * @param args an array of command-line arguments
   */
	   public static void main(java.lang.String[] args) {
	  
		 JFrame jp = null;
		 Grafi pn1 = null;
	  
	  
		 try 
		 {
			jp = new JFrame("  n e a t    view graph of genomes ");
			pn1 = new Grafi(jp); 
		 
		 
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
	  
	  
	  
	  // Insert code to start the application here.
	  }
   
   
   
	   public void actionPerformed(ActionEvent e) {
		 Object o1 = null;
		 Object o2 = null;
		 String xret = null;
		 String name = null;
		 String tmp1;
		 String tmp2;
		 Organism _ox = null;
		 Genome _g1 = null;
		 int _id = 0;
		 int   n = 0;
		 String _s = null;

		 JButton Pulsante = (JButton) e.getSource();
		 String ckx = ck_group.getSelection().getActionCommand();
	     String cky = ck_group_tipo.getSelection().getActionCommand();

		 
	  
		 if (e.getActionCommand().equals(" E X I T ")) 
		 {
			System.exit(0);
		 } 
		 
		 
		 else if (e.getActionCommand().equals(" Load genome ")) 
		 {
		 
			FileDialog fd = new FileDialog(f1, "load genome file", FileDialog.LOAD);
			fd.setVisible(true);
		 
			tmp1 = fd.getDirectory();
			tmp2 = fd.getFile();
		 
			if (tmp1 != null && tmp2 != null) 
			{
			   name = tmp1 + tmp2;
			   EnvConstant.NAME_GENOMEA = name;
			   EnvConstant.NAME_OF_GENOME_FOR_EXECUTION = name;
			   
			   boolean rc = false;
			
			   if (rc)
				  xret = new String("  OK");
			   else
				  xret = new String("  *ERROR*");


			   textArea.setText("");
			   
			   if (ckx.equalsIgnoreCase("view and exec"))
			      execCurrGenome();
			   else
			      ViewGraph(name);

			      
			       
			}
		 } 
		 
		 
		 else if (e.getActionCommand().equals(" Load Pop. ")) 
		 {
			FileDialog fd =  new FileDialog(f1, "load a population in listbox ", FileDialog.LOAD); 
			fd.setVisible(true);
		 
			tmp1 = fd.getDirectory();
			tmp2 = fd.getFile();
		 
			if (tmp1 != null && tmp2 != null) 
			{
			   name = tmp1 + tmp2;
//			   EnvConstant.NAME_CURR_POPULATION = name;
			   EnvConstant.CURRENT_POPULATION_VIEW = name;
			   EnvConstant.NAME_OF_GENOME_FOR_EXECUTION = null;
			   Popx = new Population(EnvConstant.CURRENT_POPULATION_VIEW);
			//   Popx = new Population(EnvConstant.NAME_CURR_POPULATION);
			   n = Popx.getOrganisms().size();
			   listdata.clear();
			   for (int j = 0; j < n; j++) 
			   {
				  _ox = (Organism) Popx.getOrganisms().elementAt(j);
				  _g1 = _ox.getGenome();
				  _id = _g1.getGenome_id();
				  _s = " Genome_" + _id + " ";
				  listdata.addElement(_s);
			   }
			
			   jList1.setListData(listdata);
			   paneScroll1.revalidate();
			   paneScroll1.repaint();
			   p2.repaint();
			
			}
		 }
		 
		 else if (e.getActionCommand().equals(" Load winner from Pop.")) 
		 {
			FileDialog fd =  new FileDialog(f1, "load all genome winner in population ", FileDialog.LOAD); 
			fd.setVisible(true);
		 
			tmp1 = fd.getDirectory();
			tmp2 = fd.getFile();
		 
			if (tmp1 != null && tmp2 != null) 
			{
			   name = tmp1 + tmp2;
//			   EnvConstant.NAME_CURR_POPULATION = name;
			   EnvConstant.CURRENT_POPULATION_VIEW = name;

		       EnvConstant.NAME_OF_GENOME_FOR_EXECUTION = null;

			   Popx = new Population(EnvConstant.CURRENT_POPULATION_VIEW,true);
//			   Popx = new Population(EnvConstant.NAME_CURR_POPULATION,true);
			   n = Popx.getOrganisms().size();
			   listdata.clear();
			   for (int j = 0; j < n; j++) 
			   {
				  _ox = (Organism) Popx.getOrganisms().elementAt(j);
				  _g1 = _ox.getGenome();
				  _id = _g1.getGenome_id();
				  _s = " Genome_" + _id + " ";
				  listdata.addElement(_s);
			   }
			
			   jList1.setListData(listdata);
			   paneScroll1.revalidate();
			   paneScroll1.repaint();
			   p2.repaint();
			
			}
		 }
	  

		  
		 else if (e.getActionCommand().equals("exec single genome")) 
		 {
			  execCurrGenome();
			  
	     }


		 
	   }
   
   
   
   
	   public void valueChanged(ListSelectionEvent event) 
	  {
		 int i1 = 0;
		 String s2 = null;
		 String s3 = null;
		 Organism _ox = null;
		 Genome _g1 = null;
		 String stringValue = null;
		 String ckx = ck_group.getSelection().getActionCommand();

	  
	  
	  // See if this is a listbox selection and the
	  // event stream has settled
		 if (event.getSource() == jList1 && !event.getValueIsAdjusting()) 
		 {
		 // Get the current selection and place it in the
		 // edit field
			stringValue = (String) jList1.getSelectedValue();
			if (stringValue != null) 
			{
			
			   i1 = jList1.getSelectedIndex();
			   s2 = jList1.getSelectedValue().toString();
			   _ox = (Organism) Popx.getOrganisms().elementAt(i1);
			   _g1 = _ox.getGenome();
			   EnvConstant.NAME_OF_GENOME_FOR_EXECUTION = s2;
			   EnvConstant.CURR_GENOME_RUNNING = _g1;

			   
			   textArea.setText("");

			   if (ckx.equalsIgnoreCase("view and exec"))

			   	  execCurrGenome(_g1);
			   else
			      ViewGraph(_g1);


			   s3 = new String(" loaded genome -> "+s2);
			   if (_g1.notes !=  null)
			   {
				  String h2 = s3+_g1.notes;
				  logger.sendToLog(" grafi: "+h2);
			   }
			
			}
		 
		 }
	  }                                                                                                                                                   
   
   
   
   
   
   
	   public void buildConstraints(GridBagConstraints gbc, int gx,int gy,int gw,int gh ,int wx,int wy) 
	  {
		 gbc.gridx = gx;
		 gbc.gridy = gy;
		 gbc.gridwidth = gw;
		 gbc.gridheight = gh;
		 gbc.weightx = wx;
		 gbc.weighty = wy;
	  }
   
   
public void ViewGraph(String name) {

	Vector v1 = new Vector(1, 0);
	Structure sx = new Structure();

	mappa.setAxis(false);
	mappa.setGrid(false);
	mappa.setBackground(Color.lightGray);
	mappa.setTitle("  ");
	mappa.initAzioni();
	sx.LoadGenome(name);

	ViewCommonPart(v1,sx);


}        
public void ViewGraph(Genome _g1) {

	Vector v1 = new Vector(1, 0);
	Structure sx = new Structure();
	
	mappa.setAxis(false);
	mappa.setGrid(false);
	mappa.setBackground(Color.lightGray);
	mappa.setTitle("  ");
	mappa.initAzioni();
	sx.LoadGenome(_g1);
	
	ViewCommonPart(v1,sx);


}/**
 * Inserire qui la descrizione del metodo.
 * Data di creazione: (15/06/02 10.56.06)
 */      
public void execCurrGenome()
{
			textArea.setText("");
		    logger.sendToStatus("Wait please...");
			logger.sendToLog(" grafi: start reading session file ->"+EnvRoutine.getJneatSession());
			EnvRoutine.getSession();
			logger.sendToLog(" grafi: end read session file");
			Execution expm = new Execution();
			boolean rc = expm.createNetwork();

			if (!rc)
			{
		       logger.sendToLog("select a single genome for this request");
		       logger.sendToStatus("READY");
		       return;
			}
							   
			
			if (EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_FILE)
			{
			   logger.sendToLog(" grafi: execution from file...");
			   expm.executeForFile(textArea); 
			}
			
			else if (EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_CLASS)
			{
			   logger.sendToLog(" grafi: execution from class...");
		 	   expm.executeForClass(textArea); 
			}


			
			ViewGraph((Genome) EnvConstant.CURR_GENOME_RUNNING);
		    logger.sendToStatus("READY");
}/**
 * Inserire qui la descrizione del metodo.
 * Data di creazione: (15/06/02 10.56.06)
 */          
public void execCurrGenome(Genome _genome)
{
			textArea.setText("");
		    logger.sendToStatus("Wait please...");
			logger.sendToLog(" grafi: start reading session file ->"+EnvRoutine.getJneatSession());
			EnvRoutine.getSession();
			logger.sendToLog(" grafi: end read session file");
			Execution expm = new Execution();
			expm.createNetwork(_genome);

			if (EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_FILE)
			{
			   logger.sendToLog(" grafi: execution from file...");
			   expm.executeForFile(textArea); 

			}
			
			else if (EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_CLASS)
			{
			   logger.sendToLog(" grafi: execution from class...");
		 	   expm.executeForClass(textArea); 
			}

			ViewGraph((Genome) EnvConstant.CURR_GENOME_RUNNING);
		    logger.sendToStatus("READY");
}



public void ViewCommonPart(Vector v1, Structure sx) 
{

	String ckx = ck_group_tipo.getSelection().getActionCommand();

	
	sx.generate_Grafo();
	sx.compute_Coordinate(p3.getWidth(), p3.getHeight());

	// store point to interpreter
	Iterator itr_point = sx.vVertex.iterator();
	while (itr_point.hasNext()) {
		Vertex _point = ((Vertex) itr_point.next());
		if ((_point.x) != 0 && (_point.y != 0) && (_point.is_real()))
			v1.add(new code(_point, CodeConstant.NODO_N));
		if ((_point.x) != 0 && (_point.y != 0) && (_point.is_recurrent()))
			v1.add(new code(_point, CodeConstant.NODO_R));

	}

	// store edge for interpreter
	Iterator itr_edge = sx.vEdge.iterator();
	while (itr_edge.hasNext()) 
	{
		Edge _edge = ((Edge) itr_edge.next());

		Vertex _inode = _edge.in_node;
		Vertex _onode = _edge.out_node;
		int type = _edge.type;
		double weight_edge = _edge.weight;
		int sign_edge;
		if (weight_edge >= 0)
			sign_edge = +1;
		else
			sign_edge = -1;

		if ((_inode.x) != 0 && (_inode.y != 0) && (_onode.x) != 0 && (_onode.y != 0) && (_edge.active)) 
		{
			int x1 = _inode.x;
			int y1 = _inode.y;
			int x2 = _onode.x;
			int y2 = _onode.y;

			if (ckx.equalsIgnoreCase("link with 'width'"))
			   v1.add(new code(_inode, _onode, type, sign_edge,weight_edge));
			else
			   v1.add(new code(_inode, _onode, type, sign_edge));

			   
		}
	}

	mappa.setScale(p3.getWidth(), p3.getHeight());
	mappa.updateConstant();

	mappa.setAxis(false);

	logger.sendToLog(" grafi: load genome " + EnvConstant.NAME_GENOMEA);

	mappa.setGrafo(v1);
	mappa.validate();
	mappa.repaint();


	textArea.append(sx.getSource().toString());
	textArea.setForeground(new Color(0, 48, 128));
	textArea.setOpaque(false);
	textArea.setCaretPosition(0);

	p3.repaint();
}	   public void itemStateChanged(ItemEvent e) {

		 JRadioButton cb = (JRadioButton) e.getItem();
		 String ckx = cb.getActionCommand();
		 String ckg1 = ck_group.getSelection().getActionCommand();  
		 
		 if (ckx.equalsIgnoreCase("view and exec"))

		 {
			 if  (!((Genome) EnvConstant.CURR_GENOME_RUNNING  == null))
			 {
			   textArea.setText("");
			   execCurrGenome((Genome) EnvConstant.CURR_GENOME_RUNNING);
			   
	             
			 }
			 
		 }


		 if (ckx.equalsIgnoreCase("view only"))
		 {
			 if  (!((Genome) EnvConstant.CURR_GENOME_RUNNING  == null))
			 {
			   textArea.setText("");
			   ViewGraph((Genome) EnvConstant.CURR_GENOME_RUNNING);
	             
			 }
			 
		 }




		 if    ((ckx.equalsIgnoreCase("link with 'width'")) || (ckx.equalsIgnoreCase("link normal"))  )
		 {

 
		 if (ckg1.equalsIgnoreCase("view and exec"))

		 {
			 if  (!((Genome) EnvConstant.CURR_GENOME_RUNNING  == null))
			 {
			   textArea.setText("");
			   execCurrGenome((Genome) EnvConstant.CURR_GENOME_RUNNING);

	             
			 }
			 
		 }


		 if (ckg1.equalsIgnoreCase("view only"))
		 {
			 if  (!((Genome) EnvConstant.CURR_GENOME_RUNNING  == null))
			 {
			   textArea.setText("");
			   ViewGraph((Genome) EnvConstant.CURR_GENOME_RUNNING);
	             
			 }
			 
		 }






			 
		 }


		 













		 

	   }}