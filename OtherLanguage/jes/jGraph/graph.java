   package jGraph;

   import java.awt.*;
   import java.awt.event.*;
   import java.awt.geom.*;
   import java.awt.image.*;
   import javax.swing.*;
   import javax.swing.event.*;
   import javax.swing.border.*;
   import java.util.*;
   import java.lang.*;

//   import jneat.*;  
   import jneat.*;

   import jNeatCommon.*;
																public class graph extends javax.swing.JPanel implements ActionListener {
	  chartXY mappa;
	  JPanel p2;
	  JPanel p3;
	  JButton b1;
	  JButton b2;
	  JButton b3;
	  JButton b4;
   	  JButton b5;
   /**
   * graph constructor comment.
   */
	   public graph() {
		 inizializza();
	  }
   
   /**
	  * Starts the application.
	  * @param args an array of command-line arguments
	  */
	   public static void main(java.lang.String[] args) {
	  /*
	  
	  JFrame jGrafo = null;
	  graph sp = null;
	  
	  try {
	  	jGrafo = new JFrame("graph subsystem");
	  
	  	sp = new graph(jGrafo);
	  	jGrafo.getContentPane().add(sp);
	  
	  	jGrafo.addWindowListener(new java.awt.event.WindowAdapter() {
	  		public void windowClosing(WindowEvent e) {
	  			System.exit(0);
	  		}
	  	});
	  
	  	jGrafo.pack();
	  	jGrafo.setSize(500, 500);
	  	jGrafo.setVisible(true);
	  } catch (Exception ex) {
	  	System.err.println("ERRORE");
	  }
	  
	  */
	  
		 JFrame jp = null;
		 graph pn1 = null;
	  
		 try {
			jp = new JFrame("  Test for graphXX");
			pn1 = new graph(jp);
		 //  pn1.mappa.setPreferredSize(new Dimension(500,500));
		 
		 //  jp.getContentPane().add(pn1); 
			jp.addWindowListener(
				   new java.awt.event.WindowAdapter() {
					  public void windowClosing(WindowEvent e) {
						System.exit(0);
					 }
				  });
		 
			jp.pack();
			jp.setSize(600, 600);
			jp.setVisible(true);
		 } 
			 catch (Exception ex) {
			   System.err.println("ERRORE");
			}
	  
	  }
   
	   public void inizializza() {
	  
		 mappa = new chartXY(" ");
		 mappa.setLayout(new BorderLayout());
	  
		 p2 = new JPanel();
	  
		 b1 = new JButton("Grafo");
		 b1.addActionListener(this);
		 p2.add(b1);
	  
		 b2 = new JButton("Curve");
		 b2.addActionListener(this);
		 p2.add(b2);
	  
		 b3 = new JButton("EXIT");
		 b3.addActionListener(this);
		 p2.add(b3);
	  
		 b4 = new JButton("Function");
		 b4.addActionListener(this);
		 p2.add(b4);



		 b5 = new JButton("Cartesio");
		 b5.addActionListener(this);
		 p2.add(b5);

	  
		 p2.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder("Command options"), 
			BorderFactory.createEmptyBorder(1, 1, 1, 1))); 
	  
		 p3 = new JPanel();
		 p3.setLayout(new BorderLayout());
		 p3.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder("grafo "), 
			BorderFactory.createEmptyBorder(1, 1, 1, 1))); 
	  
		 p3.add(mappa, BorderLayout.CENTER);
		 mappa.setBackground(Color.red);
		 p2.setBackground(Color.green);
		 p3.setBackground(Color.yellow);
	  
		 p3 = new JPanel();
		 p3.setLayout(new BorderLayout());
		 p3.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder("grafo "), 
			BorderFactory.createEmptyBorder(1, 1, 1, 1))); 
	  
		 p3.add(mappa, BorderLayout.CENTER);
	  
	  
		 Container contentPane = f1.getContentPane();
		 BorderLayout bl = new BorderLayout();
		 contentPane.setLayout(bl);
		 contentPane.add(p2, BorderLayout.NORTH);
		 contentPane.add(p3, BorderLayout.CENTER);
	  
	  }
   
	   public void actionPerformed(ActionEvent e) 
	  {
	  
		 JButton Pulsante = (JButton) e.getSource();
	  
		 if (e.getActionCommand().equals("EXIT")) 
		 {
			System.exit(0);
		 }
		  
		 if (e.getActionCommand().equals("Function")) 
		 {
			mappa.setTitle(" ");
//			double cx[] = new double[120];
			Vector v1 = new Vector(1, 0);
			double x1 = 0.0;
			double y1 = 0.0;

			v1.add(new code(10, 710 ,  " >> random generator example of string ", 3,CodeConstant.DESCRIPTOR_CURVE));
			 
			for (int j = 0; j < 20; j++) 
			{
			   double y2 = NeatRoutine.randint(300);
			   double x2 = j * 2;
			   v1.add(
				  new code((int) x1, (int) y1, (int) x2, (int) y2, CodeConstant.LINE_TYPE_1, +1));



			   x1 = x2;
			   y1 = y2;
			
			}
		 
			mappa.setScale(400, 700);
			mappa.setLabelValueY2(700);
			mappa.setLabelValueY(300);
			mappa.setLabelValueX(200);
			mappa.setAxis(" time of epoch's", "Error rate");
			mappa.setAxis(true);
			
//			mappa.setGrafo(v1);
		 
			for (int j = 21; j < 200; j++) {
			   double y2 = NeatRoutine.randint(700);
			   double x2 = j * 2;
			   double wx = 5 - NeatRoutine.randint(10);

			   if (wx > 0.0)
			    
			      v1.add( new code((int) x1, (int) y1, (int) x2, (int) y2, CodeConstant.LINE_TYPE_1, +1,wx));
			   else
			      v1.add( new code((int) x1, (int) y1, (int) x2, (int) y2, CodeConstant.LINE_TYPE_1, -1,wx));
			    
			   x1 = x2;
			   y1 = y2;
			
			}

			mappa.setGrafo(v1);
			mappa.setAxis(" time of epoch's", "Error rate X");
			mappa.setGrid(true);
			mappa.revalidate();
			mappa.repaint();
		 
		 }  
		 

		 
		 
		 
		 if (e.getActionCommand().equals("Cartesio")) 
		 {

			mappa.setScale(400, 400);
		 	mappa.initAzioni();
			
			mappa.setLabelValueY2(700);
			mappa.setLabelValueY(100);
			mappa.setLabelValueX(30);
			mappa.setAxis(" time of epoch's", "Error rate");
			mappa.setAxis(true);
			mappa.setGrid(true);
			mappa.revalidate();
			mappa.repaint();
		  
		 }  
		 

		 
		 
		 
		 
		 
		  
		 else  if (e.getActionCommand().equals("Curve")) 
		 {
			mappa.setTitle("> prova di scrittura titolo <");

//			String nameGraph = " test ....";
		 	mappa.initAzioni();
			mappa.setGrid(false);
			mappa.setAxis(false);

			Vector v1 = new Vector(1, 0);
		 
			v1.add(new code(150, 150, NeatConstant.HIDDEN, 151));
		 
		 //  nord
			v1.add(new code(150, 150, 150, 300, CodeConstant.LINE_SE, -1));
			v1.add(new code(150, 300, NeatConstant.HIDDEN, 999));
		 
		 //  sud
			v1.add(new code(150, 150, 150, 50, CodeConstant.LINE_SE, +1));
			v1.add(new code(150, 50, NeatConstant.HIDDEN, 888));
		 
		 //  est
			v1.add(new code(150, 150, 300, 150, CodeConstant.LINE_SE, +1));
			v1.add(new code(300, 150, NeatConstant.HIDDEN, 777));
		 
		 //  est
			v1.add(new code(150, 150, 50, 150, CodeConstant.LINE_SE, +1));
			v1.add(new code(50, 150, NeatConstant.HIDDEN, 666));

			mappa.setScale(400, 400);
			mappa.setGrafo(v1);
			mappa.revalidate();
			mappa.repaint();
		 
		 } 
		 
		 
		 else if (e.getActionCommand().equals("Grafo")) 
		 {
		 
			mappa.setTitle("Grafico struttura genoma");
			mappa.initAzioni();
			mappa.setGrid(false);
			mappa.setAxis(false);
			
			Vector v1 = new Vector(1, 0);
			String nameGraph = "c:\\jes\\gui\\data\\genome_multiplexor";

			v1.add(new code(10, 10, 400, 10, CodeConstant.LINE_SE, +1));
			v1.add(new code(10, 10, 10, 400, CodeConstant.LINE_SE, +1));
				
		 	// String nameGraph = "c:\\jneat\\dati\\genome.primis.g0000";
		 	// String nameGraph = "c:\\jneat\\dati\\genome.ex13";           // con lateral connection sx-> dx
		 	// String nameGraph = "c:\\jneat\\dati\\genome.ex14.txt";           // con lateral connection dx-> sx
		 	// String nameGraph = "c:\\jneat\\dati\\genome.ex20.txt";          // con lateral connection dx-> sx
		 	// String nameGraph = "c:\\jneat\\dati\\genome.ex.err3.txt";          // con lateral connection dx-> sx
		 	// String nameGraph = "c:\\jes\\gui\\data\\genome.ex.err5";          // con lateral connection dx-> sx
		 	// String nameGraph = "c:\\jneat\\dati\\genome.ex.err4.txt";          // con lateral connection dx-> sx
		 	// String nameGraph = "c:\\jneat\\dati\\genome.ex.err6.txt";          // con lateral connection dx-> sx
		 	// String nameGraph = "c:\\jneat\\dati\\genome.ex.err7.txt";          // con lateral connection dx-> sx
		 	// String nameGraph = "c:\\jneat\\dati\\genome.ex.err9.txt";          // con lateral connection dx-> sx
		 	// String nameGraph = "c:\\jneat\\dati\\genome.ex.err10.txt";          // con lateral connection dx-> sx
		 
			Structure sx = new Structure();
			
			sx.LoadGenome(nameGraph);
			sx.generate_Grafo();
			sx.compute_Coordinate(400, 400);


			

			
		 	//draw point
			Iterator itr_point = sx.vVertex.iterator();
			while (itr_point.hasNext()) {
			   Vertex _point = ((Vertex) itr_point.next());
			
			   if ((_point.x) != 0 && (_point.y != 0) && (_point.is_real()))
				  v1.add(new code(_point, CodeConstant.NODO_N));
			   if ((_point.x) != 0 && (_point.y != 0) && (_point.is_recurrent()))
				  v1.add(new code(_point, CodeConstant.NODO_R));
			
			}
		 
		 	//draw edge
			Iterator itr_edge = sx.vEdge.iterator();
			while (itr_edge.hasNext()) {
			   Edge _edge = ((Edge) itr_edge.next());
			
			   Vertex _inode = _edge.in_node;
			   Vertex _onode = _edge.out_node;
			   int type = _edge.type;
			   double weight_edge = _edge.weight;
			   int segno_weight = 0;
			   if (weight_edge >= 0 )
			      segno_weight = +1;
			   else
			       segno_weight = -1;
			      
			
			   if ((_inode.x) != 0
			   && (_inode.y != 0)
			   && (_onode.x) != 0
			   && (_onode.y != 0)
			   && (_edge.active)) {
				  int x1 = _inode.x;
				  int y1 = _inode.y;
				  int x2 = _onode.x;
				  int y2 = _onode.y;
			   
				  v1.add(new code(_inode, _onode, type, segno_weight, weight_edge));
			   
			   	//       v1.add(new code(x1,y1,x2,y2,type));
			   }
			}
		 
			v1.add(new code(20, 440, " stringa1..", new Color(255, 0, 0)));
			v1.add(new code(20, 425, " stringa2..", new Color(255, 0, 0)));
		 
			mappa.setScale(500, 500);
//			mappa.setAxis(true);
			mappa.setGrafo(v1, nameGraph);
			mappa.revalidate();
			mappa.repaint();
		 
		 }
	  
	  }
   
	  public javax.swing.JFrame f1;
	   public graph(JFrame _f) {
	  
		 inizializza(_f);
	  }
	   public void inizializza(JFrame _f) 
	  {
	  
		 f1 = _f;
		 mappa = new chartXY("Lista errori ");
		 mappa.setLayout(new BorderLayout());
	  
		 p2 = new JPanel();
		 p2.setLayout(new FlowLayout());
	  
		 b1 = new JButton("Grafo");
		 b1.addActionListener(this);
		 p2.add(b1);
	  
		 b2 = new JButton("Curve");
		 b2.addActionListener(this);
		 p2.add(b2);
	  
		 b3 = new JButton("EXIT");
		 b3.addActionListener(this);
		 p2.add(b3);
	  
		 b4 = new JButton("Function");
		 b4.addActionListener(this);
		 p2.add(b4);

		 b5 = new JButton("Cartesio");
		 b5.addActionListener(this);
		 p2.add(b5);
	  
		 p2.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder("Command options"), 
			BorderFactory.createEmptyBorder(5, 5, 5, 5))); 
	  
		 p3 = new JPanel();
		 p3.setLayout(new BorderLayout());
		 p3.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder("grafo "), 
			BorderFactory.createEmptyBorder(1, 1, 1, 1))); 
	  
		 p3.add(mappa, BorderLayout.CENTER);
	  
	  
		 Container contentPane = f1.getContentPane();
		 BorderLayout bl = new BorderLayout();
		 contentPane.setLayout(bl);
		 contentPane.add(p2, BorderLayout.NORTH);
		 contentPane.add(p3, BorderLayout.CENTER);
	  
	  }
   }