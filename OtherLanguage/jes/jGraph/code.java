   package jGraph;

   import java.awt.*;
   import java.util.*;
   import java.awt.geom.*;

   import jNeatCommon.*;

																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																public class code {
   
   // contiene gli oggetti di tipo nodo o line
	  public Vector p;
   
   // tipo ereditato dall'oggetto o forzato in caso di oggetto dummy : Vertex, line...
	  public int tipo;
   
   // name dell'oggetto originale 
	  public int name;
   
   // colore dell'oggetto
	  public Color c;
   
   // oggetto da riempire o meno  
	  public boolean is_empty;
   
   // oggetto da riempire o meno  
	  public double weight;
   
   
	   code() 
	  {
		 p = new Vector(1, 0);
		 p.add(new Vertex(0, 0));
		 tipo = CodeConstant.NODO_N;
		 c = new Color(0, 0, 0);
		 weight = 0.0;
		 name = 0;
	
		 is_empty = true;
	  // is_directional = false;
	  }   
   
   
   
   
   /**
   *  costruttore di un Vertex dummy di prova
   */
   
	
code(int x1, int y1, int t, int _name) 
	  {
		 p = new Vector(2, 0);
	  
		 Vertex _tmp = new Vertex(x1, y1);
		 _tmp.name = _name;
		 p.add(_tmp);
		 tipo = t;
		 name = 0;
		 c = new Color(249, 249, 219);
		 is_empty = false;
	  
	  }

	  
   
   /**
   * legge coordinata x (si suppone per un punto)
   */
	   public double getx() 
	  {
		 Vertex _p = (Vertex) p.elementAt(0);
		 return _p.x;
	  
	  } 
   
   /**
   * legge coordinata x(i) (si suppone per un link )
   */
   
	   public double getx(int i)
	  {
		 Vertex _p = (Vertex) p.elementAt(i);
		 return _p.x;
	  
	  } 
   
   /**
   * legge coordinata y (si suppone per un punto)
   */
	   public double gety() 
	  {
		 Vertex _p = (Vertex) p.elementAt(0);
		 return _p.y;
	  
	  } 
   
   /**
   * legge coordinata y(i) (si suppone per un link )
   */
	   public double gety(int i) 
	  {
		 Vertex _p = (Vertex) p.elementAt(i);
		 return _p.y;
	  }
   
   
   
   /**
   *  costruttore di un code di tipo nodo passando
   * riferimento a Vertex passato
   *
   */
	   public code(Vertex _p, int t) 
	  {
		 p = new Vector(1, 0);
		 p.add(_p);
		 tipo = t;

		 
		 c = new Color(249, 249, 219);
	  
		 if (t == CodeConstant.NODO_R) 
		 {
			Edge _ed = _p.edge_ref;
			
			if (_ed.weight >= 0)
			   c = EnvRoutine.GetForeColorPlot(1);
			else
			   c = EnvRoutine.GetForeColorPlot(-1);

/*			   
			if (_ed.weight >= 0)
			   c = new Color(0, 32, 128);
			else
			   c = new Color(196, 0, 0);
		*/ 
		 }
		 name = 0;
		 is_empty = false;

		 
		 
		 } 
   
   
   /**
   * restituisce l'oggetto di tipo Vertex 
   */
	   public Vertex getVertex(int i) 
	  {
		 Vertex _p = (Vertex) p.elementAt(i);
		 return _p;
	  }
   
   
   /**
   * costruttore di un edge dummy di prova
   */
   
	   public code(int x1, int y1, int x2, int y2, int t,int _sign) 
	  {
		 p = new Vector(2, 0);
		 p.add(new Vertex(x1, y1));
		 p.add(new Vertex(x2, y2));
		 tipo = t;

		 c = EnvRoutine.GetForeColorPlot(_sign);

		 
/*		 if (sign >= 0)
			c = new Color(0,32,128);*/
		 name = 0;
		 is_empty = true;
	  }/**
   *  costruttore di un Vertex dummy di prova
   */                                                                        
	   public code(int x1, int y1, String _s,Color _colore) 
	  {
		 p = new Vector(2, 0);
		 Vertex _tmp = new Vertex(x1, y1);
		 _tmp.name = 0;
		 p.add(_tmp);
		 tipo = CodeConstant.DESCRIPTOR;
		 c = _colore;
		 p.add( new String(_s));
	  }
   
   
   
   
   
   
	   public code(Vertex _p1, Vertex _p2, int t,int sign) 
	  {
		 p = new Vector(2, 0);
		 p.add(_p1);
		 p.add(_p2);
		 tipo = t;
		 name = 0;

		 c = EnvRoutine.GetForeColorPlot(sign);

/*		 
		 if (sign >= 0)
			c = new Color(0,32,128);
		 else
			c = new Color(196,0,0);
*/
			
		 is_empty = true;
	  }/**
   * restituisce l'oggetto di tipo Vertex 
   */                                           
	   public String getString() 
	  {
		 return (String) p.elementAt(1);
	  }
   
   
   	   public code(int x1, int y1, String _s,int _codeColor) 
	  {
		 p = new Vector(2, 0);
		 Vertex _tmp = new Vertex(x1, y1);
		 _tmp.name = 0;
		 p.add(_tmp);
		 tipo = CodeConstant.DESCRIPTOR;

		 c = EnvRoutine.GetForeColorPlot(_codeColor);

/*		 
	     if (_codeColor >= 0)
			   c = new Color(0, 32, 128);
			else
			   c = new Color(196, 0, 0);
		*/

		 
		 p.add( new String(_s));
	  }   	   public code(int x1, int y1, String _s,int _codeColor,int _tipo) 
	  {
		 p = new Vector(2, 0);
		 Vertex _tmp = new Vertex(x1, y1);
		 _tmp.name = 0;
		 p.add(_tmp);
		 tipo = _tipo;

		 c = EnvRoutine.GetForeColorPlot(_codeColor);

	 
		 p.add( new String(_s));
	  }   /**
   * costruttore di un edge dummy di prova
   */
   
	   public code(int x1, int y1, int x2, int y2, int t,int _codecolor,double _weight) 
	  {
		 p = new Vector(2, 0);
		 p.add(new Vertex(x1, y1));
		 p.add(new Vertex(x2, y2));
		 tipo = t;
		 weight = _weight;
		 c = EnvRoutine.GetForeColorPlot(_codecolor);
		 name = 0;
		 is_empty = true;
	  }/**
   *  costruttore di un Vertex dummy di prova
   */         	   public code(Vertex _p1, Vertex _p2, int t,int sign,double _weight) 
	  {
		 p = new Vector(2, 0);
		 p.add(_p1);
		 p.add(_p2);
		 tipo = t;
		 name = 0;
		 weight = _weight;

		 c = EnvRoutine.GetForeColorPlot(sign);

/*		 
		 if (sign >= 0)
			c = new Color(0,32,128);
		 else
			c = new Color(196,0,0);
*/

			
		 is_empty = true;
	  }/**
   * restituisce l'oggetto di tipo Vertex 
   */         }