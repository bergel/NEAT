   package jGraph;

   import java.awt.*;
   import java.awt.event.*;
   import java.awt.geom.*;
   import java.awt.image.*;
   import javax.swing.*;
   import javax.swing.event.*;
   import javax.swing.border.*;
   import java.util.*;




   import jNeatCommon.*;
	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	  	   public class chartXY  extends JPanel {
   


		  	  	  	  	  	  	  	  
	 // constant value for extra space in
	 // plot diagram (axis, descr,value curve)
	 //  from bottom,top,right,left
	 
	  private double ky_bottom;
	  private double ky_top;
	  private double kx_right;		  	  	  	  	  	  	  	  
	  private double kx_left;		  	  	  	  	  	  	  	  
		  	
	  private double plot_kx;
	  private double plot_ky;

	  
	  
	  
	  
	   
	  
	   
	  public double LabelValueX;
	  public double LabelValueY;

	  public double LabelValueY2;
   
   // interprete azioni da fare	
	  public Vector azioni;
   
   // titolo del grafo
	  String titolo;
   
   // description axis x
	  String d_axis_x;
   // description axis y
	  String d_axis_y;
   
   // internal use
	  Graphics2D g2 = null;
   
   // lunghezza asse x in pixel
	  double axis_x;
   // lunghezza asse y in pixel
	  double axis_y;


	  
   // lunghezza asse x in pixel per i grafi
	  double graph_axis_x;
   // lunghezza asse y in pixel per i grafi
	  double graph_axis_y;

	  double graph_kx_span; 
	  double graph_ky_span;

	  double graph_axis;
	  double graph_k;

	  
	  
	  
	  
	  
	   
	  
   // ascissa  origine traslata (in pixel) 
	  double ox;
   // ordinata origine traslata (in pixel) 
	  double oy;
   
   // massimo valore visualizzabile su ascissa
	  double max_x;
   // massimo valore visualizzabile su ordinata
	  double max_y;
   
   // fattore di scala per x
	  double kx;
   // fattore di scala per y
	  double ky;
   
   // visualizza assi cartesiani o meno
	  boolean flag_axis;
   
   // visualizza griglia
	  boolean flag_grid;
   
   
   
   
   
   /**
   * constructor
   */
	   public chartXY() {
	  /*
	  super();
	  //		setOpaque(false); // we don't paint all our bits
	  setLayout(new BorderLayout());
	  setBorder(BorderFactory.createLineBorder(Color.black));
	  initialize();
	  */
	  //	        super();
	  //		setOpaque(false); // we don't paint all our bits
	  //		setLayout(new BorderLayout());
	  //		setBorder(BorderFactory.createLineBorder(Color.red));
	  
	  
		 setDoubleBuffered(true);
		 initialize();
	  
	  
	  
	  }
   




	   
   
public void updateConstant() 
{
		 double ma,mb;
		 Font f = null;
		 FontMetrics fm = null;
		 int heightS = 0;
		 // now compute extra space external to graph plotted or
		 // curve draw
		  
		 f = new Font("Verdana ", Font.PLAIN, 12);
		 g2.setFont(f);
		 fm = g2.getFontMetrics();
		 heightS = fm.getHeight();

		 kx_left   = heightS * 3 + 2 ;	  
		 kx_right  = heightS + 2 ;	  
		 ky_bottom = heightS * 2 - 3;
		 ky_top    = heightS * 2;

		 ox = 0;
		 oy = 0;

		 //compute what is dimension minor in 'hardware' screen
		 // sottrae alla dim del pannello i pixel reali
		 // per extra space.
		 // da questro momento ogni volta che scrive
		 // deve essere sommato a x -> kx_left ed a y -> ky_bottom

		 axis_x = getWidth() - (kx_left + kx_right);
		 axis_y = getHeight() - (ky_top + ky_top);
		 
		 if (axis_x > axis_y)
			ma = axis_y;
		 else
			ma = axis_x;
			
		 if (max_x > max_y)
			mb = max_y;
		 else
			mb = max_x;

		
	     // vengono calcolati 2 tipi tali di coefficienti di proporzionalità
	     // 1) plot_k* per le curve
	     // 2) k* per i grafi in cui si forza x == y
	     // (nella curva avremo : 
	     // px =  x1 * plot_kx + kx_left;
	     // py = (ky_top + ky_bottom + axis_y) - (y1 * plot_ky + ky_bottom);


	     
		 plot_kx =  axis_x / max_x;
		 plot_ky =  axis_y / max_y;






		 // calcola 5 pixel da lati
		 //
		 graph_kx_span = 5; 
		 graph_ky_span = 5;
		 
		 graph_axis_x = getWidth()  - (graph_kx_span + graph_kx_span);
		 graph_axis_y = getHeight() - (graph_ky_span + graph_ky_span);
		 
		 if (graph_axis_x > graph_axis_y)
		 {
			ma = graph_axis_y;
		 }
		 else
		 {
			ma = graph_axis_x;
		 }
			

		 if (max_x > max_y)
			mb = max_x;
		 else
			mb = max_y;

		 graph_axis = ma;
		 graph_k = mb;
		  
		 kx =  ma / mb;
		 ky =  ma / mb;
		  
	  }
   
   /**
   * Starts the application.
   * @param args an array of command-line arguments
   */
	   public static void main(java.lang.String[] args) 
	  {
	  
		 WindowListener l = 
			 new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
				  System.exit(0);
			   }
				public void windowClosed(WindowEvent e) {
				  System.exit(0);
			   }
			};
	  
		 JFrame f = new JFrame("");
		 f.addWindowListener(l);
		 chartXY Mx = new chartXY(" Error Rate");
		 Mx.setAxis("asse 1","asse 2");
		 Mx.setAxis(true);
	  
	  //   Mx.repaint();
	  
		 f.getContentPane().add(Mx);
		 f.pack();
		 f.setSize(new Dimension(500, 500));
		 f.setBackground(Color.gray);
		 f.show();
	  
	  }
   /**
   * Called whenever the part throws an exception.
   * @param exception java.lang.Throwable
   */
	   private void handleException(java.lang.Throwable exception) {
	  
	  /* Uncomment the following lines to print uncaught exceptions to stdout */
	  // System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	  // exception.printStackTrace(System.out);
	  }
   
   
   
   /**
   * Initialize the class.
   */   
   
	   private void initialize() {
	  /*
	  try {
	  setSize(400,400);
	  d_axis_x = " axis  X";
	  d_axis_y = " axis  Y";
	  
	  } catch (java.lang.Throwable ivjExc) {
	  handleException(ivjExc);
	  }
	  */
	  
	  
	  //  	   mappa.setScale(p3.getWidth() + 50, p3.getHeight() + 50);
	  
	  
	  
		 d_axis_x = " axis  X";
		 d_axis_y = " axis  Y";
	  
	  
		 Insets insets = getInsets();
		 axis_x  = getWidth(); // - insets.left - insets.right;
		 axis_y  = getHeight(); // - insets.top - insets.bottom;
	  
	  
	  
	  //   titolo = new String(" no title :default");
		 titolo = new String(" ");
		 azioni = new Vector();
	  //   azioni = new Vector(1,0);
		 ox = 0;
		 oy = 0;

		 LabelValueY = 100;
		 LabelValueX = 100;
		 
		 max_x = axis_x;
		 max_y = axis_y;



		 
		 double ma,mb;
		 if (axis_x > axis_y)
			ma = axis_y;
		 else
			ma = axis_x;
	  
		 if (max_x > max_y)
			mb = max_y;
		 else
			mb = max_x;
	  
	  
		 kx =  ma / mb;
		 ky =  ma / mb;
	  
		 flag_axis = false;
	     flag_grid = false;
	  
	  
	  }
   
   
   /**
   * grafo constructor comment.
   */
	   public chartXY(String s) {
	  //	        super();
	  //		setOpaque(false); // we don't paint all our bits
	  //		setLayout(new BorderLayout());
	  //		setBorder(BorderFactory.createLineBorder(Color.red));
		 setDoubleBuffered(true);
		 initialize();
		 titolo = new String(s);
	  
	  }
   
   /**
   * Insert the method's description here.
   * Creation date: (16/02/2002 8.39.54)
   */    
	   public double convertXtoScreen(double x) 
	  {
		 return x;
	  }
   
	   public double convertYtoScreen(double y) 
	  {
		 return (graph_axis_y - y);
	  }
   
   
   
   
	   public void setTitle(String t)
	  {
	  		 titolo = new String(t);
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	   public void setScale(int _mx,int _my) 
	  {
		 max_x = _mx;	
		 max_y = _my;
//		 updateConstant();
	  } 
   
   
   
   	   public void setGrid(boolean _f)
	  {
		 flag_grid = _f;
	  //   repaint();
	  }
   
   
   
   
   
   /**
   * carica grafo 
   */
	   public void setGrafo(Vector v,String s)
	  {
	  
		 azioni.clear();
	  //	  azioni.removeAllElements();
	  
		 titolo =  " Graph -> "+s;
		 for (int j=0; j < v.size(); j++)
		 {
			code _c = (code) v.elementAt(j);
			azioni.add(_c);
		 }
	  
	  
	  
	  //  repaint();
	  }/**
   * traccia assi cartesiani di default 
   */                                                                                                            
   
   /**
   * imposta il valore da visualizzare sugli assi
   */
	   public void setAxis(String xs, String ys)
	  {
	  
		 d_axis_x = xs;	
		 d_axis_y = ys;
	  //  repaint();
	  }/**
   * se true gli assi sono visualizzati altrimenti no
   * per default gli assi sono visualizzati
   */                                                                                                    
	   public void setAxis(boolean _f)
	  {
		 flag_axis = _f;
	  //   repaint();
	  }/**
   * carica grafo 
   */                                                                                          
	   public void setGrafo(Vector v)
	  {
	  
		 azioni.clear();
	  //   azioni.removeAllElements();
		 for (int j=0; j < v.size(); j++)
		 {
			code _c = (code) v.elementAt(j);
			azioni.add(_c);
		 }
	  
	  
	  
	  //  repaint();
	  //   paintComponent()
	  }/**
   * traccia assi cartesiani di default 
   */                                                                                              
   
public void addLineToGrafo(code _code) {

	azioni.add(_code);

}                                                                            
	   public void initAzioni()
	  {
		 azioni.clear();
	  }/*
   *
   */                                                                             
	   public void paintComponent(Graphics g) 
	  {
		 super.paintComponent(g);
	  
		 g2 = (Graphics2D) g;
		 AffineTransform saveXform = new AffineTransform(g2.getTransform());


		 
		 updateConstant();
	  
	  
	  // calcola e traccia gli assi cartesiani
		 if (flag_axis)
		 {
			drawAxis( d_axis_x, 1);
			drawAxis( d_axis_y, 2);
		 }
	  
	  // calcola e traccia gli assi cartesiani
		 if (flag_grid)
		 {
			drawGrid();
		 }
	  // calcola e traccia il titolo (centrato in alto)
		 drawTitle( );
	  
	  
	  // interpreta in base agli oggetti cosa deve fare :
	  // punti,linee,linee orientate, cerchi,quadrati (per nodi)
	  //
		 drawCode( );
	  
	  // restore original setting
		 g2.setTransform(saveXform);   
	  
	  
	  }	   				   			 	   				   			  	   				   			 	   				   			   	   				   			 	   				   			  	   				   			 	   				   					   				   			 	   				   			  	   				   			 	   				   			   	   				   			 	   				   			  	   				   			 	   				  
   
   
   
   
public void drawAxis( String s, int tipo) 
	  {
	  
	  
		 AffineTransform saveXform = g2.getTransform();

		 
		 int ang = 0;
		 double px = 100;
		 double py = 100;
		 int x1 = 10;
		 int y1 = 10;
		 int x2 = 100;
		 int y2 = 100;
		 int lengthS;
		 int heightS;

		 int lls;
	     int lls1;
	  
	  
		 Font f = new Font("Verdana ", Font.PLAIN, 12);
		 g2.setColor(Color.darkGray);
		 g2.setFont(f);
	  
	  
		 FontMetrics fm = g2.getFontMetrics();
		 lengthS = fm.stringWidth(s);
		 heightS = fm.getHeight();

		 lls =  fm.stringWidth(s);
	     lls1 = Math.round((float) lls / 2);

		 
		  
		 if (tipo == 1) {
			px = (int) (kx_left + (double) ( (max_x * plot_kx ) / 2.0)) - lls1 ;
			py = (int) (ky_top + plot_ky * max_y + ky_bottom) ;
			ang = 0;
			x1 = (int) (kx_left);
			x2 = (int) (kx_left + max_x * plot_kx);
			
			y1 = (int) (ky_top + plot_ky * max_y);
			y2 = (int) (ky_top + plot_ky * max_y);
	
			g2.drawLine(x1, y1, x2, y2);
		 }



		 if (tipo == 2) {
			 
			px = (int) (kx_left / 3);
			py = (int) (ky_top + (double) ( (max_y * plot_ky ) / 2.0)) +lls1 ;
			ang = -90;
			x1 = (int) (kx_left);
			x2 = (int) (kx_left);
		 
			y1 = (int) (ky_top + plot_ky * max_y);
			y2 = (int) (ky_top);
			g2.drawLine(x1, y1, x2, y2);
	  
		 }
		 
		 
			
	  
	  
	  //   AffineTransform at = new AffineTransform();
		 AffineTransform at = new AffineTransform(g2.getTransform());
		 at.translate(0.0,0.0);
		 at.rotate(Math.toRadians(ang), px, py);
		 g2.setTransform(at);
		 g2.drawString(s, (int) px,(int) py);
		 at.translate(0.0,0.0);
		 g2.setTransform(at);
	  
	  // restore original 
		 g2.setTransform(saveXform);
	  
	  
	  }
	   public void drawCode( ) 
	  {
	  
	  
		 double modulo = 0;
		 double px = 10;
		 double py = 10;
		 double r = 14.0;
		 double x1;
		 double y1;
		 double x2;
		 double y2;
		 double x3;
		 double y3;
		 double xm;
		 double ym; 
		 double mx;
		 double ang;
		 double ang1 = 0;
		 double ang2 = 0;
	  
		 double x1s;
		 double y1s;
		 double x2s;
		 double y2s;
		 double xa;
		 double ya;
		 double xb;
		 double yb;
		 double xc;
		 double yc;
		 double tgx;
	  
		 double xs;
		 double ys;
		 int rx;
		 int ry;
		 int lengthS;
		 int lengthC;
		 int heightS;
		 int raggio_base;
	  
		 double _cos_alfa;
		 double _sin_alfa;
		 double nx2;
		 double ny2;
	  
	  
	  
		 AffineTransform saveXform = g2.getTransform();
		 g2.setColor(Color.black);
	  
		 Font f = new Font("Verdana ", Font.PLAIN, 12);

		 g2.setFont(f);
		 FontMetrics fm = g2.getFontMetrics();
	  
		 Comparator cmp = new order_code();
		 Collections.sort(azioni,cmp);
	  

/*		 System.out.print("\n ox= "+ox);
		 System.out.print("\n oy= "+oy);
		 System.out.print("\n graph_axis= "+graph_axis);
		 System.out.print("\n graph_k   = "+graph_k);
		 System.out.print("\n graph_kx   = "+kx);
		 System.out.print("\n graph_ky   = "+ky);
*/
		 
		 raggio_base = fm.stringWidth("555");
	  
		 for (int j=0;  j < azioni.size(); j++)
		 {


//			System.out.print("\n interpret code "+j);
			 
			code _e = (code) azioni.elementAt(j);
			Vertex _p1 = _e.getVertex(0);
		 
			g2.setColor(_e.c);
		 
		 
			if (_e.tipo == CodeConstant.DESCRIPTOR)
			{
				
//			   System.out.print("\n -> normal descriptor ");
/*			   px = convertXtoScreen(_e.getx() * kx + ox);
			   py = convertYtoScreen(_e.gety() * ky + oy);
*/

			   px = graph_kx_span  + _e.getx() * kx ;                 ;  // + nx2*kx;
			   py = (graph_ky_span + graph_axis_y) - _e.gety() * ky   ;  // - ny2 ;

			   
			   String sx = _e.getString(); 
			   g2.drawString(sx, (int) px,(int) py);
			
			}


			
			if (_e.tipo == CodeConstant.DESCRIPTOR_CURVE)
			{

//			   System.out.print("\n -> curve descriptor ");
			   px = (kx_left + _e.getx() * plot_kx);
			   py = (ky_top + max_y * plot_ky)  -  _e.gety() * plot_ky;
			   String sx = _e.getString(); 
			   g2.drawString(sx, (int) px,(int) py);
			
			}
			
			 
		 
			if (_e.tipo == CodeConstant.NODO_R)
			{
//			   System.out.print("\n -> nodo_r ");
			   rx = raggio_base; 
			   ry = raggio_base;
			   double rx1 = raggio_base * 1.0;
			   double ry1 = ry * 1.8;
			   double ry2 = ry1 / 2;

			   px = graph_kx_span  + _e.getx() * kx  - rx/8;                 ;  // + nx2*kx;
			   py = (graph_ky_span + graph_axis_y) - (_e.gety() * ky + ry2)  ;  // - ny2 ;

			   
			   g2.drawArc((int) px,(int) py,(int) rx1,(int) ry1 ,180,-360);
			
			
			}
		 

			
				
			 
			if (_e.tipo == CodeConstant.NODO_N)
			{
//			   System.out.print("\n -> nodo_n ");

			   String id_node = String.valueOf(_p1.name);
			   lengthS = fm.stringWidth(id_node);
			   heightS = fm.getHeight();
			
			   rx = raggio_base; 
			   ry = raggio_base;
			
			   Color co = new Color(126,177,218);
			   Color ci = new Color(224,85,106);
			
			   
			   px = graph_kx_span  + _e.getx() * kx  - rx/2;                ;  // + nx2*kx;
			   py = (graph_ky_span + graph_axis_y) - _e.gety() * ky - ry/2  ;  // - ny2 ;
			   	

			   xs = graph_kx_span  + _e.getx() * kx  - rx/2 + ((rx - lengthS) / 2);                ;  // + nx2*kx;
			   ys = (graph_ky_span + graph_axis_y) - _e.gety() * ky + 4 ;  // - ny2 ;
			   
			   
			   
			   	
			   if (_e.is_empty)
				  g2.drawOval((int) px,(int) py,(int) raggio_base,(int) raggio_base);
			   else
			   {
				  g2.setColor(_e.c);
				  g2.fillOval((int) px,(int) py,(int) raggio_base,(int) raggio_base);
			   
				  if ( _p1.type == NeatConstant.HIDDEN)
				  {
					 g2.setColor(Color.lightGray);
					 g2.fillArc((int) px,(int) py,(int) raggio_base,(int) raggio_base,0,180);
				  }
			   
			   
				  g2.setColor(Color.darkGray);
				  g2.drawOval((int) px,(int) py,(int) raggio_base,(int) raggio_base);
				  g2.setColor(Color.black);
				  g2.drawString(id_node,(int)xs,(int)ys);
			   }


			   
			
			}
		 
		 
		 
		 
		
		 
		 //
		 // simple line type 1)
		 //
			if (_e.tipo == CodeConstant.LINE_TYPE_1)
			{
//			   System.out.print("\n -> line_type 1 ");
			   xa = _e.getx(0);
			   ya = _e.gety(0);
			   xb = _e.getx(1);
			   yb=  _e.gety(1);

			   x1 = kx_left + xa * plot_kx;
			   y1 = (ky_top + max_y * plot_ky) - ya * plot_ky;
			   x2 = kx_left + xb * plot_kx;
			   y2 = (ky_top + max_y * plot_ky) - yb * plot_ky;


			   Stroke korig = g2.getStroke();
			   setSpessore(korig, _e);
			   g2.drawLine((int) x1,(int) y1,(int) x2,(int) y2);
	           resetSpessore(korig);
	           
			   
			   	
			}
		 
		 
		 //
		 // middle chain
		 //
			if ((_e.tipo == CodeConstant.LINE_MC) || (_e.tipo == CodeConstant.LINE_H_MC))
			{
//			   System.out.print("\n -> line_m1 ");
			   Vertex _p2 = _e.getVertex(1);
			   xa = _e.getx(0);
			   ya = _e.gety(0);
			   xb = _e.getx(1);
			   yb=  _e.gety(1);
			
			   x1 = graph_kx_span  + xa * kx;
			   y1 = (graph_ky_span + graph_axis_y) - ya* ky;
			   x2 = graph_kx_span  + xb * kx;                
			   y2 = (graph_ky_span + graph_axis_y) - yb* ky; 

			   Stroke korig = g2.getStroke();
			   setSpessore(korig, _e);
			   	
			   g2.drawLine((int) x1,(int) y1,(int) x2,(int) y2);

	           resetSpessore(korig);

			   
			}
		 
		 
		 
		 
		 //
		 // 
		 // 
			if ((_e.tipo == CodeConstant.LINE_FC) || (_e.tipo == CodeConstant.LINE_H_FC))
			{
//			   System.out.print("\n -> line_fc ");
			   Vertex _p2 = _e.getVertex(1);
			   xa = _e.getx(0);
			   ya = _e.gety(0);
			   xb = _e.getx(1);
			   yb=  _e.gety(1);
			
			
			
			// se _p2 = VIRTUAL_SINGLE allora ho un link con 1 solo livello
			//	
			
			
			   modulo = Math.sqrt(Math.pow((xb - xa),2) + Math.pow((yb - ya),2));
			   _cos_alfa = (xb-xa) / modulo;
			   _sin_alfa = (yb-ya) / modulo;
			
			   nx2 = Math.cos(Math.acos(_cos_alfa)) * (raggio_base / 2) ;
			   ny2 = Math.sin(Math.asin(_sin_alfa)) * (raggio_base / 2) ;
			
			   x1 = graph_kx_span  + xa * kx                  + nx2;
			   y1 = (graph_ky_span + graph_axis_y) - (ya* ky  + ny2);
			   x2 = graph_kx_span  + xb * kx;                   
			   y2 = (graph_ky_span + graph_axis_y) - yb* ky; 

			   
			   
			   
			   Stroke korig = g2.getStroke();
			   setSpessore(korig, _e);
			   
			   	
			   g2.drawLine((int) x1,(int) y1,(int) x2,(int) y2);

	           resetSpessore(korig);

			   
			}
		 
		 
		 
		 // draw   line with direction
			if ((_e.tipo == CodeConstant.LINE_SE) || (_e.tipo == CodeConstant.LINE_H_SE))
			{
			
//			   System.out.print("\n -> line_se ");
			   
			   Vertex _p2 = _e.getVertex(1);
			   x1 = _e.getx(0);
			   y1 = _e.gety(0);
			   x2 = _e.getx(1);
			   y2 = _e.gety(1);
			   
			   modulo = Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
			   _cos_alfa = (x2-x1) / modulo;
			   _sin_alfa = (y2-y1) / modulo;
			
			   nx2 = Math.cos(Math.acos(_cos_alfa)) * (raggio_base / 2) ;
			   ny2 = Math.sin(Math.asin(_sin_alfa)) * (raggio_base / 2) ;
			
			   double k_lato = 6;
			   double [][] Triangle = new double[3][2];
			   Triangle[0][0] = -k_lato;
			   Triangle[0][1] = -k_lato;
			   Triangle[1][0] = 0;
			   Triangle[1][1] = 0;
			   Triangle[2][0] = -k_lato;
			   Triangle[2][1] = k_lato;
			
			   for (int l=0; l < 3; l++)
			   {
				  double xx1 = (Triangle[l][0] * _cos_alfa -  Triangle[l][1] * _sin_alfa + x2) ;
				  double yy1 = (Triangle[l][0] * _sin_alfa +  Triangle[l][1] * _cos_alfa + y2) ;
				  Triangle[l][0] = 	graph_kx_span + xx1 * kx                   - nx2 ;
				  Triangle[l][1] = (graph_ky_span + graph_axis_y) - (yy1 * ky  - ny2);
			   } 
			

			   x1s = graph_kx_span  + x1 * kx                   + nx2;
			   y1s = (graph_ky_span + graph_axis_y) - (y1* ky   + ny2);
			   x2s = graph_kx_span  + x2 * kx                   - nx2;
			   y2s = (graph_ky_span + graph_axis_y) - (y2* ky   - ny2);

			   Stroke korig = g2.getStroke();
			   setSpessore(korig, _e);

			   
			   g2.drawLine((int)x1s,(int)y1s,(int)x2s,(int)y2s);

			   
	           resetSpessore(korig);
			
			   GeneralPath triangle = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
			   triangle.moveTo((int)Triangle[2][0],(int)Triangle[2][1]);
			   triangle.lineTo((int)Triangle[0][0],(int)Triangle[0][1]);
			   triangle.lineTo((int)Triangle[1][0],(int)Triangle[1][1]);
			   triangle.closePath();
			   g2.fill(triangle);
			
			}
		 
		 
		 
		 
		 
		 
		 
		 // draw   line with direction
			if ((_e.tipo == CodeConstant.LINE_LC) || (_e.tipo == CodeConstant.LINE_H_LC))
			{
			//   System.out.print("\n -> line_lc ");
			   Vertex _p2 = _e.getVertex(1);
			
			   x1 = _e.getx(0);
			   y1 = _e.gety(0);
			   x2 = _e.getx(1);
			   y2 = _e.gety(1);
			
			   modulo = Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
			   _cos_alfa = (x2-x1) / modulo;
			   _sin_alfa = (y2-y1) / modulo;
			
			   nx2 = Math.cos(Math.acos(_cos_alfa)) * (raggio_base / 2) ;
			   ny2 = Math.sin(Math.asin(_sin_alfa)) * (raggio_base / 2) ;
			
			
			
			
			   double k_lato = 6;
			   double [][] Triangle = new double[3][2];
			   Triangle[0][0] = -k_lato;
			   Triangle[0][1] = -k_lato;
			   Triangle[1][0] = 0;
			   Triangle[1][1] = 0;
			   Triangle[2][0] = -k_lato;
			   Triangle[2][1] = k_lato;
			
			   for (int l=0; l < 3; l++)
			   {
				  double xx1 = (Triangle[l][0] * _cos_alfa -  Triangle[l][1] * _sin_alfa + x2) ;
				  double yy1 = (Triangle[l][0] * _sin_alfa +  Triangle[l][1] * _cos_alfa + y2) ;
				  Triangle[l][0] = 	graph_kx_span + xx1 * kx                   - nx2 ;
				  Triangle[l][1] = (graph_ky_span + graph_axis_y) - (yy1 * ky  - ny2);
			   } 
			
			
			   x1s = graph_kx_span  + x1 * kx                   ;
			   y1s = (graph_ky_span + graph_axis_y) - (y1* ky  );
			   x2s = graph_kx_span  + x2 * kx                   - nx2;
			   y2s = (graph_ky_span + graph_axis_y) - (y2* ky   - ny2);

			   
			   Stroke korig = g2.getStroke();
			   setSpessore(korig, _e);

			    
			   g2.drawLine((int)x1s,(int)y1s,(int)x2s,(int)y2s);

			   resetSpessore(korig);
			
			
			   GeneralPath triangle = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
			   triangle.moveTo((int)Triangle[2][0],(int)Triangle[2][1]);
			   triangle.lineTo((int)Triangle[0][0],(int)Triangle[0][1]);
			   triangle.lineTo((int)Triangle[1][0],(int)Triangle[1][1]);
			   triangle.closePath();
			   g2.fill(triangle);
			}
		 
		 } 	  
	  
	  
	  
		 g2.setTransform(saveXform);
	  
	  
	  
	  }
	   public void drawTitle( ) 
	  {
	  
		 AffineTransform saveXform = g2.getTransform();
	  
		 int ang = 0;
		 int px = 100; 
		 int py = 100;
	  	 int lls;
		 int lls1;
		  
		 AffineTransform at = new AffineTransform(g2.getTransform());
		 at.translate(0.0,0.0);
		 g2.setTransform(at);
	  
	  
		 Font f = new Font("Verdana ", Font.PLAIN, 12);
		 g2.setColor(Color.black);
		 g2.setFont(f);
		 FontMetrics fm = g2.getFontMetrics();
	  

		 lls =  fm.stringWidth(titolo);
	     lls1 = Math.round((float) lls / 2);
	     
/*	      
 		 px = (int) (kx_left + (double) ( (max_x * kx ) / 2.0)) - lls1 ;
		 py = (int)  ky_top;
*/
		 double ddm = ((max_x * kx ) / 2.0) - lls1;

		 
/*		 System.out.print("\n ddm = "+ddm);
		 System.out.print("\n max_x = "+max_x);
		 System.out.print("\n kx = "+kx);
*/
		 
	     px = (int) (graph_kx_span  + ddm); 
		 py = (int) (graph_ky_span+10);


		 
		 ang = 0;
		 at.translate(0.0,0.0);
		 g2.setTransform(at);
		 g2.drawString(titolo, px, py);
		 at.translate(0.0,0.0);
		 g2.setTransform(at);
	  
	  // restore original 
		 g2.setTransform(saveXform);
	  
	  }	   public void drawGrid() 
{

	AffineTransform saveXform = g2.getTransform();

	double px = 100;
	double px2 = 100;
	double py = 100;
	double py2 = 100;
	int x1 = 10;
	int y1 = 10;
	int x2 = 100;
	int y2 = 100;
	double offx = 0;
	double offy = 0;
	double offy2 = 0;
	int min_space_y = 0;
	int min_space_x = 0;
	double ddy = 0.0;
	double ddx = 0.0;
	String s = null;
	int xvalue;

	int lengthS;
	int heightS;


	int qx;
	int qy;
	
	Stroke korig = g2.getStroke();

	Stroke stroke = 
		new BasicStroke(
			1, 
			BasicStroke.CAP_BUTT, 
			BasicStroke.JOIN_BEVEL, 
			0, 
			new float[] {1, 2}, 
			0); 
	g2.setStroke(stroke);

	// calcola quanto spazio è occupato dalle label numeriche
	//  di riferimento ai valori delle curve tracciate

	FontMetrics fm = g2.getFontMetrics();
	lengthS = fm.stringWidth("M");
	heightS = fm.getHeight();

	// calcolo  minimo spazio per label su asse y
	min_space_y = heightS * 4;
	// calcolo  minimo spazio per label su asse x
	min_space_x = (lengthS + 2) * 6;

	// calcolo quanto devono essere larghe le bande
	// della griglia 

	ddx = (axis_x) / min_space_x;
	ddy = (axis_y) / min_space_y;

	qx = (int) Math.floor(ddx);
	qy = (int) Math.floor(ddy);

	min_space_x = (int) ((axis_x) / (double) qx);
	min_space_y = (int) ((axis_y) / (double) qy);

	ddx = qx;
	ddy = qy;

	g2.setColor(Color.gray);

	AffineTransform orig = g2.getTransform();

	offy = ((double) LabelValueY) / ddy;
	offy2 = ((double) LabelValueY2) / ddy;

	for (int j = 0; j <= (int) ddy; j++) 
	{

		px = (kx_left / 3) * 2 - 3;
		px2 = (kx_left) - 3;

		if (j > 0) 
		{

			x1 = (int) (kx_left);
			x2 = (int) (kx_left + max_x * plot_kx);

			if (j == ddy) 
			{
				y1 = (int) (ky_top);
				y2 = (int) (ky_top);
			} 
			
			else 
			{
				y1 = (int) (ky_top + max_y * plot_ky - j * min_space_y);
				y2 = (int) (ky_top + max_y * plot_ky - j * min_space_y);
			}

			g2.drawLine(x1, y1, x2, y2);

			if (j == ddy)
				xvalue = (int) LabelValueY;
			else
				xvalue = (int) (offy * j);
			s = "" + xvalue;

			int lls = fm.stringWidth(s);
			int lls1 = Math.round((float) lls / 2);

			py = (int) (ky_top + max_y * plot_ky - j * min_space_y + lls1);
			AffineTransform at = new AffineTransform(orig);
			at.translate(0.0, 0.0);
			at.rotate(Math.toRadians(-90.0), px, py);
			g2.setTransform(at);
			g2.drawString(s, (int) px, (int) py);
			at.translate(0.0, 0.0);
			g2.setTransform(orig);
		}

		if (j > 0) 
		{

			if (j == ddy)
				xvalue = (int) LabelValueY2;
			else
				xvalue = (int) (offy2 * j);
			s = "" + xvalue;
			;
			int lls = fm.stringWidth(s);
			int lls1 = Math.round((float) lls / 2);

			py = (int) (ky_top + max_y * plot_ky - j * min_space_y + lls1);
			AffineTransform at = new AffineTransform(orig);
			at.translate(0.0, 0.0);
			at.rotate(Math.toRadians(-90.0), px2, py);
			g2.setTransform(at);
			g2.drawString(s, (int) px2, (int) py);
			at.translate(0.0, 0.0);
			g2.setTransform(orig);
		}

		if (j == 0) 
		{
			s = "fitness";
			int lls = fm.stringWidth(s);
			int lls1 = Math.round((float) lls / 2);

			py = (int) (ky_top + max_y * plot_ky - j * min_space_y + lls1);
			AffineTransform at = new AffineTransform(orig);
			at.translate(0.0, 0.0);
			at.rotate(Math.toRadians(-90.0), px, py);
			g2.setTransform(at);
			g2.drawString(s, (int) px, (int) py);
			at.translate(0.0, 0.0);
			g2.setTransform(orig);

			s = "specie";
			lls = fm.stringWidth(s);
			lls1 = Math.round((float) lls / 2);

			py = (int) (ky_top + max_y * plot_ky - j * min_space_y + lls1);
			at = new AffineTransform(orig);
			at.translate(0.0, 0.0);
			at.rotate(Math.toRadians(-90.0), px2, py);
			g2.setTransform(at);
			g2.drawString(s, (int) px2, (int) py);
			at.translate(0.0, 0.0);
			g2.setTransform(orig);

		}

	}

	offx = ((double) LabelValueX) / ddx;

	for (int j = 0; j <= (int) ddx; j++) 
	{

		py = (int) (ky_top + max_y * plot_ky + ky_bottom / 2);

		if (j > 0) 
		{

			x1 = (int) (kx_left + j * min_space_x);
			x2 = (int) (kx_left + j * min_space_x);

			y1 = (int) (ky_top + max_y * plot_ky);
			y2 = (int) (ky_top);

			g2.drawLine(x1, y1, x2, y2);

			if (j == ddx)
				xvalue = (int) LabelValueX;
			else
				xvalue = (int) (offx * j);

			s = "" + xvalue;
			int lls = fm.stringWidth(s);
			int lls1 = Math.round((float) lls / 2);

			px = (int) (kx_left + j * min_space_x - lls1);
			AffineTransform at = new AffineTransform(orig);
			at.translate(0.0, 0.0);
			at.rotate(Math.toRadians(0.0), px, py);
			g2.setTransform(at);
			g2.drawString(s, (int) px, (int) py);
			at.translate(0.0, 0.0);
			g2.setTransform(orig);
		}

	}

	g2.setStroke(korig);
	g2.setTransform(saveXform);

}	  public void setLabelValueX(double _v)
	  {
		  // max ascissa 0 -> _v
		  LabelValueX = _v;
	  }	  public void setLabelValueY(double _v)
	  {
		  // max ordinata 0 -> _v

		  LabelValueY = _v;
	  }public void setLabelValueY2(double _v)
	  {
		  // max ordinata(external)  0 -> _v

		  LabelValueY2 = _v;
	  }	   	   	   public void drawCode_old( ) 
	  {
	  
	  
		 double modulo = 0;
		 double px = 10;
		 double py = 10;
		 double r = 14.0;
		 double x1;
		 double y1;
		 double x2;
		 double y2;
		 double x3;
		 double y3;
		 double xm;
		 double ym; 
		 double mx;
		 double ang;
		 double ang1 = 0;
		 double ang2 = 0;
	  
		 double x1s;
		 double y1s;
		 double x2s;
		 double y2s;
		 double xa;
		 double ya;
		 double xb;
		 double yb;
		 double xc;
		 double yc;
		 double tgx;
	  
		 double xs;
		 double ys;
		 int rx;
		 int ry;
		 int lengthS;
		 int lengthC;
		 int heightS;
		 int raggio_base;
	  
		 double _cos_alfa;
		 double _sin_alfa;
		 double nx2;
		 double ny2;
	  
	  
	  
		 AffineTransform saveXform = g2.getTransform();
		 g2.setColor(Color.black);
	  
		 Font f = new Font("Verdana ", Font.PLAIN, 12);

		 g2.setFont(f);
		 FontMetrics fm = g2.getFontMetrics();
	  
		 Comparator cmp = new order_code();
		 Collections.sort(azioni,cmp);
	  

/*		 System.out.print("\n ox= "+ox);
		 System.out.print("\n oy= "+oy);
		 System.out.print("\n graph_axis= "+graph_axis);
		 System.out.print("\n graph_k   = "+graph_k);
		 System.out.print("\n graph_kx   = "+kx);
		 System.out.print("\n graph_ky   = "+ky);
*/
		 
		 raggio_base = fm.stringWidth("555");
	  
		 for (int j=0;  j < azioni.size(); j++)
		 {


//			System.out.print("\n interpret code "+j);
			 
			code _e = (code) azioni.elementAt(j);
			Vertex _p1 = _e.getVertex(0);
		 
			g2.setColor(_e.c);
		 
		 
			if (_e.tipo == CodeConstant.DESCRIPTOR)
			{
				
//			   System.out.print("\n -> normal descriptor ");
/*			   px = convertXtoScreen(_e.getx() * kx + ox);
			   py = convertYtoScreen(_e.gety() * ky + oy);
*/

			   px = graph_kx_span  + _e.getx() * kx ;                 ;  // + nx2*kx;
			   py = (graph_ky_span + graph_axis_y) - _e.gety() * ky   ;  // - ny2 ;

			   
			   String sx = _e.getString(); 
			   g2.drawString(sx, (int) px,(int) py);
			
			}


			
			if (_e.tipo == CodeConstant.DESCRIPTOR_CURVE)
			{

//			   System.out.print("\n -> curve descriptor ");
			   px = (kx_left + _e.getx() * plot_kx);
			   py = (ky_top + max_y * plot_ky)  -  _e.gety() * plot_ky;
			   String sx = _e.getString(); 
			   g2.drawString(sx, (int) px,(int) py);
			
			}
			
			 
		 
			if (_e.tipo == CodeConstant.NODO_R)
			{
//			   System.out.print("\n -> nodo_r ");
			   rx = raggio_base; 
			   ry = raggio_base;
			   double rx1 = raggio_base * 1.0;
			   double ry1 = ry * 1.8;
			   double ry2 = ry1 / 2;

			   px = graph_kx_span  + _e.getx() * kx  - rx/8;                 ;  // + nx2*kx;
			   py = (graph_ky_span + graph_axis_y) - (_e.gety() * ky + ry2)  ;  // - ny2 ;

			   
			   g2.drawArc((int) px,(int) py,(int) rx1,(int) ry1 ,180,-360);
			
			
			}
		 

			
				
			 
			if (_e.tipo == CodeConstant.NODO_N)
			{
//			   System.out.print("\n -> nodo_n ");

			   String id_node = String.valueOf(_p1.name);
			   lengthS = fm.stringWidth(id_node);
			   heightS = fm.getHeight();
			
			   rx = raggio_base; 
			   ry = raggio_base;
			
			   Color co = new Color(126,177,218);
			   Color ci = new Color(224,85,106);
			
			   
			   px = graph_kx_span  + _e.getx() * kx  - rx/2;                ;  // + nx2*kx;
			   py = (graph_ky_span + graph_axis_y) - _e.gety() * ky - ry/2  ;  // - ny2 ;
			   	

			   xs = graph_kx_span  + _e.getx() * kx  - rx/2 + ((rx - lengthS) / 2);                ;  // + nx2*kx;
			   ys = (graph_ky_span + graph_axis_y) - _e.gety() * ky + 4 ;  // - ny2 ;
			   
			   
			   
			   	
			   if (_e.is_empty)
				  g2.drawOval((int) px,(int) py,(int) raggio_base,(int) raggio_base);
			   else
			   {
				  g2.setColor(_e.c);
				  g2.fillOval((int) px,(int) py,(int) raggio_base,(int) raggio_base);
			   
				  if ( _p1.type == NeatConstant.HIDDEN)
				  {
					 g2.setColor(Color.lightGray);
					 g2.fillArc((int) px,(int) py,(int) raggio_base,(int) raggio_base,0,180);
				  }
			   
			   
				  g2.setColor(Color.darkGray);
				  g2.drawOval((int) px,(int) py,(int) raggio_base,(int) raggio_base);
				  g2.setColor(Color.black);
				  g2.drawString(id_node,(int)xs,(int)ys);
			   }


			   
			
			}
		 
		 
		 
		 
		
		 
		 //
		 // simple line type 1)
		 //
			if (_e.tipo == CodeConstant.LINE_TYPE_1)
			{
//			   System.out.print("\n -> line_type 1 ");
			   xa = _e.getx(0);
			   ya = _e.gety(0);
			   xb = _e.getx(1);
			   yb=  _e.gety(1);

			   x1 = kx_left + xa * plot_kx;
			   y1 = (ky_top + max_y * plot_ky) - ya * plot_ky;
			   x2 = kx_left + xb * plot_kx;
			   y2 = (ky_top + max_y * plot_ky) - yb * plot_ky;


			   Stroke korig = g2.getStroke();
			   Stroke stroke = null;
			   if (_e.weight == 0.0) 
	              stroke = new BasicStroke(1);
			   else
			   {
	               int weigh_line = (int) Math.abs(Math.rint(_e.weight));
	               stroke = new BasicStroke(weigh_line);
			   }
	               
	           g2.setStroke(stroke);
			   
			   g2.drawLine((int) x1,(int) y1,(int) x2,(int) y2);
	           g2.setStroke(korig);
			   
			   	
			}
		 
		 
		 //
		 // middle chain
		 //
			if ((_e.tipo == CodeConstant.LINE_MC) || (_e.tipo == CodeConstant.LINE_H_MC))
			{
//			   System.out.print("\n -> line_m1 ");
			   Vertex _p2 = _e.getVertex(1);
			   xa = _e.getx(0);
			   ya = _e.gety(0);
			   xb = _e.getx(1);
			   yb=  _e.gety(1);
			
			   x1 = graph_kx_span  + xa * kx;
			   y1 = (graph_ky_span + graph_axis_y) - ya* ky;
			   x2 = graph_kx_span  + xb * kx;                
			   y2 = (graph_ky_span + graph_axis_y) - yb* ky; 

			   	
			   g2.drawLine((int) x1,(int) y1,(int) x2,(int) y2);
			}
		 
		 
		 
		 
		 //
		 // 
		 // 
			if ((_e.tipo == CodeConstant.LINE_FC) || (_e.tipo == CodeConstant.LINE_H_FC))
			{
//			   System.out.print("\n -> line_fc ");
			   Vertex _p2 = _e.getVertex(1);
			   xa = _e.getx(0);
			   ya = _e.gety(0);
			   xb = _e.getx(1);
			   yb=  _e.gety(1);
			
			
			
			// se _p2 = VIRTUAL_SINGLE allora ho un link con 1 solo livello
			//	
			
			
			   modulo = Math.sqrt(Math.pow((xb - xa),2) + Math.pow((yb - ya),2));
			   _cos_alfa = (xb-xa) / modulo;
			   _sin_alfa = (yb-ya) / modulo;
			
			   nx2 = Math.cos(Math.acos(_cos_alfa)) * (raggio_base / 2) ;
			   ny2 = Math.sin(Math.asin(_sin_alfa)) * (raggio_base / 2) ;
			
			   x1 = graph_kx_span  + xa * kx                  + nx2;
			   y1 = (graph_ky_span + graph_axis_y) - (ya* ky  + ny2);
			   x2 = graph_kx_span  + xb * kx;                   
			   y2 = (graph_ky_span + graph_axis_y) - yb* ky; 

			   
			   
			   
			   
			   	
			   g2.drawLine((int) x1,(int) y1,(int) x2,(int) y2);
			}
		 
		 
		 
		 // draw   line with direction
			if ((_e.tipo == CodeConstant.LINE_SE) || (_e.tipo == CodeConstant.LINE_H_SE))
			{
			
//			   System.out.print("\n -> line_se ");
			   
			   Vertex _p2 = _e.getVertex(1);
			   x1 = _e.getx(0);
			   y1 = _e.gety(0);
			   x2 = _e.getx(1);
			   y2 = _e.gety(1);
			   
			   modulo = Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
			   _cos_alfa = (x2-x1) / modulo;
			   _sin_alfa = (y2-y1) / modulo;
			
			   nx2 = Math.cos(Math.acos(_cos_alfa)) * (raggio_base / 2) ;
			   ny2 = Math.sin(Math.asin(_sin_alfa)) * (raggio_base / 2) ;
			
			   double k_lato = 6;
			   double [][] Triangle = new double[3][2];
			   Triangle[0][0] = -k_lato;
			   Triangle[0][1] = -k_lato;
			   Triangle[1][0] = 0;
			   Triangle[1][1] = 0;
			   Triangle[2][0] = -k_lato;
			   Triangle[2][1] = k_lato;
			
			   for (int l=0; l < 3; l++)
			   {
				  double xx1 = (Triangle[l][0] * _cos_alfa -  Triangle[l][1] * _sin_alfa + x2) ;
				  double yy1 = (Triangle[l][0] * _sin_alfa +  Triangle[l][1] * _cos_alfa + y2) ;
				  Triangle[l][0] = 	graph_kx_span + xx1 * kx                   - nx2 ;
				  Triangle[l][1] = (graph_ky_span + graph_axis_y) - (yy1 * ky  - ny2);
			   } 
			

			   x1s = graph_kx_span  + x1 * kx                   + nx2;
			   y1s = (graph_ky_span + graph_axis_y) - (y1* ky   + ny2);
			   x2s = graph_kx_span  + x2 * kx                   - nx2;
			   y2s = (graph_ky_span + graph_axis_y) - (y2* ky   - ny2);

			   g2.drawLine((int)x1s,(int)y1s,(int)x2s,(int)y2s);
			
			   GeneralPath triangle = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
			   triangle.moveTo((int)Triangle[2][0],(int)Triangle[2][1]);
			   triangle.lineTo((int)Triangle[0][0],(int)Triangle[0][1]);
			   triangle.lineTo((int)Triangle[1][0],(int)Triangle[1][1]);
			   triangle.closePath();
			   g2.fill(triangle);
			
			}
		 
		 
		 
		 
		 
		 
		 
		 // draw   line with direction
			if ((_e.tipo == CodeConstant.LINE_LC) || (_e.tipo == CodeConstant.LINE_H_LC))
			{
			//   System.out.print("\n -> line_lc ");
			   Vertex _p2 = _e.getVertex(1);
			
			   x1 = _e.getx(0);
			   y1 = _e.gety(0);
			   x2 = _e.getx(1);
			   y2 = _e.gety(1);
			
			   modulo = Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
			   _cos_alfa = (x2-x1) / modulo;
			   _sin_alfa = (y2-y1) / modulo;
			
			   nx2 = Math.cos(Math.acos(_cos_alfa)) * (raggio_base / 2) ;
			   ny2 = Math.sin(Math.asin(_sin_alfa)) * (raggio_base / 2) ;
			
			
			
			
			   double k_lato = 6;
			   double [][] Triangle = new double[3][2];
			   Triangle[0][0] = -k_lato;
			   Triangle[0][1] = -k_lato;
			   Triangle[1][0] = 0;
			   Triangle[1][1] = 0;
			   Triangle[2][0] = -k_lato;
			   Triangle[2][1] = k_lato;
			
			   for (int l=0; l < 3; l++)
			   {
				  double xx1 = (Triangle[l][0] * _cos_alfa -  Triangle[l][1] * _sin_alfa + x2) ;
				  double yy1 = (Triangle[l][0] * _sin_alfa +  Triangle[l][1] * _cos_alfa + y2) ;
				  Triangle[l][0] = 	graph_kx_span + xx1 * kx                   - nx2 ;
				  Triangle[l][1] = (graph_ky_span + graph_axis_y) - (yy1 * ky  - ny2);
			   } 
			
			
			   x1s = graph_kx_span  + x1 * kx                   ;
			   y1s = (graph_ky_span + graph_axis_y) - (y1* ky  );
			   x2s = graph_kx_span  + x2 * kx                   - nx2;
			   y2s = (graph_ky_span + graph_axis_y) - (y2* ky   - ny2);
			   
			   g2.drawLine((int)x1s,(int)y1s,(int)x2s,(int)y2s);
			
			
			   GeneralPath triangle = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
			   triangle.moveTo((int)Triangle[2][0],(int)Triangle[2][1]);
			   triangle.lineTo((int)Triangle[0][0],(int)Triangle[0][1]);
			   triangle.lineTo((int)Triangle[1][0],(int)Triangle[1][1]);
			   triangle.closePath();
			   g2.fill(triangle);
			}
		 
		 } 	  
	  
	  
	  
		 g2.setTransform(saveXform);
	  
	  
	  
	  }/**
 * Inserire qui la descrizione del metodo.
 * Data di creazione: (16/06/02 15.22.01)
 */
public void resetSpessore(Stroke korig)
{
	g2.setStroke(korig);
}/**
 * Inserire qui la descrizione del metodo.
 * Data di creazione: (16/06/02 15.22.01)
 */
public void setSpessore(Stroke korig, code _e) 
{
	korig = g2.getStroke();
	Stroke stroke = null;
	if (_e.weight == 0.0)
		stroke = new BasicStroke(1);
	else {
		int weigh_line = (int) Math.abs(Math.rint(_e.weight));
		if ((weigh_line >= 0 ) && (weigh_line < 1 ))
		   weigh_line = 1;
		if ((weigh_line >= 20 ) && (weigh_line < 1 ))
		   weigh_line = 20;
		stroke = new BasicStroke(weigh_line);
	}

	g2.setStroke(stroke);
}}