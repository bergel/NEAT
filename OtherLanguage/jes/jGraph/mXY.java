   package jGraph;


   import java.text.*;import java.util.*;
   import jNeatCommon.*;
																public class mXY {
   
	  // matrice contenente oggetti Vertex
	  // per calcolo automatico coordinate
	  Vertex [][]m;
   
	  // numero di righe
	  int dim_y; 
   
	  // numero di colonne
	  int dim_x; 
   
   
   
   /**
   * definisce la matrice di Vertex e
   * inizializza tutti i Vertexer a 0 (prima posizione
   * disponibile sia su X che su Y
   */
	   public mXY(int row,int col) 
	  {
	  
		 dim_y = row;
		 dim_x = col;
		 m = new Vertex[row][col];
	  
	  }
   
   
	   public mXY() 
	  {
		 super();
	  }
   
   /**
   * imposta il ref di _Vertex alle coordinate passate
   */
	   public void setElement(Vertex _Vertex,int r,int c)
	  {
		 m[r][c] = _Vertex; 
	  }
   
   
   
   
   
   
   
   
   /**
   * visualizza il contenuto della matrice
   * e i vari Vertexer
   */
	   public void view()
	  {
		 Vertex _Vertex = null;
		 System.out.print("\n ------------matrix mXY---------------");
		 for (int j=0; j < dim_y; j++)
		 {
			System.out.print("\n riga <"+j+"> = ");
			for (int i=0; i < dim_x; i++)
			{
			   _Vertex = m[j][i];
			   if (_Vertex != null)
			   {
				  System.out.print("  e["+i+"]="+_Vertex.name);
			   //  	_Vertex.view_x();
			   }
			
			}
		 }
	  
	  
		 System.out.print("\n -------------------------------------");
	  }
   
   
   
   
   /**
   * definisce la matrice di Vertex e
   * inizializza tutti i Vertexer a 0 (prima posizione
   * disponibile sia su X che su Y
   */
	   public mXY(mXY _mat) 
	  {
	  
		 dim_y = _mat.dim_y;
		 dim_x = _mat.dim_x;
		 m = new Vertex[dim_y+1][dim_x*2+1];
	  
	  
	  
		 for (int j=0; j < dim_y; j++)
		 {
			for (int i=0; i < dim_x*2; i++)
			   m[j][i] = _mat.m[j][i];
		 }
	  
	  }
   
   
   
   
   
   
   
   
   
   /**
   *  step-1 : calcola massima larghezza in base a
   *               quanto calcolato da expand_left;
   *               viene tenuto conto anche di eventuali spazi vuoti.
   *  step-2 : punta alla riga max , la prima che incontra se + di una ,
   *                 a partire dall'alto e la chiama Lc;
   *           quindi crea tre righe virtuali :
   *                 La,Lb,Lc
   *           copiando su La <-- riga(0)   
   *           copiando su Lb <-- riga(1)  
   *           copiando su Lc <-- riga(max)
   * 
   *  step-3 : punta alla prima riga La , alla seconda Lb;
   *  step-4 : scorre La(i1) e per ogni elemento :
   * 
   *  step-5 : scorre Lb(i2) e per ogni elemento verifica eventuali link
   *           spostandosi su La ogni 2 elementi su Lb;
   *           
   *  step.6 : alla fine gli elementi su La e Lb dovranno essere centrati;
   *  step.7 : quindi per ogni elemento su Lb verifica eventuali nodi virtuali
   *           con len > 1 ; se esistono li prolunga in verticale
   *           ipotecando i posti occupati e marcando i nodi
   */  
	   public void computeMacro(Vector vEdge) 
	  {
		 int minimo;
		 Vertex _py_optimum = null;
		 Vertex _o = null;
		 Vertex _py = null; 
		 Vertex _p_real = null;
		 Vertex _p_virt = null;
		 Vertex _pb = null;
	  
		 int pos = 0;
		 int size_inp = 0;
		 int lev_next = 0; 
		 int center = 0;
		 int j = 0;
		 int i = 0;
		 int iA = 0;
		 int iB = 0;
	  
		 int La = 0;
		 int Lb = 0;
		 int s = 0;
	  
		 int fnd = 0;
		 int _i_real = 0;
		 int _i_virt = 0;
		 double dx = 0;
		 double sp = 0;
		 int pA = 0;
		 int pB = 0;
		 int pC = 0;
		 int raggio = 0;
		 boolean fatto = false;
		 boolean error = false;
		 int tenta = 0;
		 int tot_sensor = 0;
	  
	  
	  
	  // il numero di colonne deve essere l'indice della
	  // massima riga + 1
	  
	  
	  //
	  // alloca la matrice che conterrà i nodi e marca i punti
	  // come m'not traversed'
	  //
	  
	  
	  
	  
		 Vertex[][] matrix = new Vertex[dim_y][dim_x];
	  
		 for (j = 0; j < dim_y; j++) 
		 {
			for (i = 0; i < dim_x; i++) 
			{
			   matrix[j][i] = null;
			   if (m[j][i] != null)
				  m[j][i].is_traversed = false;
			}
		 }
	  
	  
	  
	  // calcola quanti nodi 'utili' ci sono nella prima riga
	  // per disporre tali nodi sulla prima riga in alto
	  //
		 La = 0;
		 Lb = 1;
		 for (i = 0; i < dim_x; i++) 
		 {
			if (m[La][i] != null)
			   pA++;
		 }
	  
	  
	  /*
	  // calcola quanti nodi 'utili' ci sono nell'ultima riga
	  // per disporli
	  //
	  La = (dim_y - 1);
	  tot_sensor = 0;
	  for (i = 0; i < dim_x; i++) 
	  {
	  if (m[La][i] != null)
	   tot_sensor++;
	  }
	  
	  System.out.print("\n Layer = "+dim_y+" , -> numero di sensori = "+tot_sensor);
	  System.out.print("\n -----> numero di sensori = "+tot_sensor);
	  
	  // in base al numero nodi e alla larghezza della matrice
	  // calcola il 'dx' tra i nodi
	  //
	  dx = 0;
	  sp = 0;
	  dx = (int) (dim_x /tot_sensor); // ogni dx-unit piazzo un Va[i];
	  sp = (int) (dx / 2.0); // spiazzamento iniziale
	  pos = 0;   
	  for (iA = 0; iA < dim_x; iA++) 
	  {
	  _o = m[La][iA];
	  if (_o != null) 
	  {
	  
		 _o.barycentre = (int) (sp + pos * dx);
		 _o.is_traversed = true;
	   pos++;
	   matrix[La][_o.barycentre] = _o;
	   System.out.print("\n piazzo Vertex=" + _o.name + " su colonna x= " + _o.barycentre);
	  }
	  } 
	  
	  
	  */
	  
	  
	  
	  
	  
	  
		 La = 0;
		 Lb = 1;
	  
	  // in base al numero nodi e alla larghezza della matrice
	  // calcola il 'dx' tra i nodi
	  //
		 dx = 0;
		 sp = 0;
		 dx = (int) (dim_x / pA); // ogni dx-unit piazzo un Va[i];
		 sp = (int) (dx / 2.0); // spiazzamento iniziale
	  
	  //
	  // Questa prima fase sistema il primo link tra out e out-1
	  // per fare ciò legge la riga e ogni volta che trova un nodo
	  // 
	  // -moltiplica un progressivo per lo spostamento (dx);
	  // -somma a tale valore lo spiazzamento iniziale
	  // -imposta il baricentro del nodo in oggetto al valore calcolato
	  // -posiziona il nodo nella matrice nel baricentro
	  // -incrementa il progressivo
	  //
	  
		 pos = 0;   
		 for (iA = 0; iA < dim_x; iA++) 
		 {
			_o = m[La][iA];
			if (_o != null) {
			
			// calcola il baricentro  e lo imposta nel nodo
			// quindi imposta il nodo come marcato
			//
			   _o.barycentre = (int) (sp + pos * dx);
			   _o.is_traversed = true;
			//         _o.barycentre = (int) (sp + iA * dx);
			   pos++;
			// piazza il Vertex nel vettore
			//
			   matrix[La][_o.barycentre] = _o;
			//		 System.out.print("\n piazzo Vertex=" + _o.name + " su colonna x= " + _o.barycentre); 
			
			// cerca il primo nodo discendente e lo piazza su stessa ascissa
			// provando a mettere prima un nodo reale ;
			// se non lo trova tenta con uno virtuale;
			// se non lo trova non fà nulla e passa al nodo successivo
			// 
			   size_inp = _o.neighbors_inp.size();
			
			   _i_real = 0;
			   _i_virt = 0;
			   _p_real = null;
			   _p_virt = null;
			
			   if (size_inp > 0) 
			   {
				  for (iB = 0;(iB < dim_x) && (fnd < 2); iB++) 
				  {
				  
					 _py = m[Lb][iB];
					 if ((_py != null) && (_o.has_child(_py)) && !(_py.is_traversed)) 
					 {
						if ((!_py.is_virtual()) && (_p_real == null)) 
						//				  if ((_py.type != 4) && (_p_real == null)) 
						{
						   _p_real = _py;
						   _i_real = iB;
						}
						if ((_py.is_virtual()) && (_p_virt == null)) 
						//				  if ((_py.type == 4) && (_p_virt == null)) 
						{
						   _p_virt = _py;
						   _i_virt = iB;
						}
					 }
				  
				  }
			   }
			
			
			
			
			//
			// se esiste un nodo real lo imposta esattamente sotto il nodo
			// e lo marca come già fatto.
			//
			   if (_p_real != null) 
			   {
			   
				  _p_real.barycentre = _o.barycentre;
				  _p_real.is_traversed = true;
			   
				  matrix[Lb][_o.barycentre] = _p_real;
			   //			System.out.print("\n sotto tale nodo metto nodo " + matrix[Lb][_o.barycentre].name + " reale"); 
			   
			   } 
			   
			   //
			   // se non esiste un nodo real e trova un nodo virtuale lo posiziona sotto il nodo
			   // e lo marca come già fatto.
			   //
			   else if (_p_virt != null) 
			   {
				  _p_virt.barycentre = _o.barycentre;
				  _p_virt.is_traversed = true;
				  matrix[Lb][_o.barycentre] = _p_virt;
			   //			   System.out.print("\n sotto tale nodo metto nodo "+ matrix[Lb][_o.barycentre].name + " virtuale"); 
			   } 
			   
			   //
			   // questo è il caso in cui il nodo di output non ha connessioni
			   // verso alcuno per cui lo lascia stare come è!
			   //
			   else 
			   {
			   //			   System.out.print("\n no nodes : skip link to node " + _o.name);
			   }
			
			}
		 
		 }
	  
	  
	  
	  
	  // 
	  //
	  // era dim_y-1 ora è dim_y
	  //
		 for (int layer = 0; layer < (dim_y-1) ; layer++) 
		 {
			La = layer;
			Lb = La + 1;
		 
		 //	  System.out.print("\n  ------------ INIZIO ANALISI LAYER :  La=" + La + " Lb=" + Lb + " ------------" );
		 
		 //
		 // Questa seconda fase sistema per ogni nodo di out i restanti link
		 // agendo a partire dalla coordinata del nodo di out e procedendo a spirale
		 // sia  a destra che a sinistra;
		 // viene letto il nodo su layer  La;
		 // se diverso da null:
		 //   -verifica se ha figli
		 //    se non passa al nodo successivo
		 //    se si :
		 //       verifica i figli e al primo figlio , cioè nodo nel layer
		 //       successivo , che trova come non marcato, cerca un posto
		 //       partendo dalla posizione immediatamente sotto il nodo e
		 //       procedendo a spirale fino a trovare un buco disponibile a
		 //       destra o a sinistra del nodo
		 //       una volta ppiazzato il figlio , passa di nuovo al layer 0 e piazza i restanti
		 //       nodi ;
		 // Il motivo per cui non piazza subito tutti i nodi è che se si agisse in tale senso
		 // non sarebbe facile , poi , distribuire i nodi in modo uniforme sotto i propri padri.
		 // 
		 
			fatto = false;
			tenta = 0;
		 
			while (!fatto) 
			{
			
			   fatto = true;
			   tenta++;
			
			   for (iA = 0; iA < dim_x; iA++) 
			   {
				  _o = m[La][iA];
			   
			   
			   
			   // a questo punto una prima euristica potrebbe fare quanto segue :
			   //   genera un vettore di nodi figli ordinato per distanza con solo i figli
			   //   non ancora piazzati ; l'ordine prevede prima i figli con minore
			   //   distanza e poi i figli con maggiore distanza ;
			   // in questo modo  eventuali nodi reali che sono stati splittati fuori
			   // potrebbero tornare ad essere prioritari
			   // quindi  la prima cosa da fare è :
			   // in base al nodo  , alla lista dei figli, alla lista dei nodi successivi :
			   //   per ogni nodo successivo che sia figlio e sia libero determina
			   //   path fino al primo nodo virtuale
			   // quindi memorizza il nodo con path minore
			   
			   
			   
			   
			   
			   //
			   
			   // una ulteriore euristica potrebbe riguardare il fatto di tracciare prima i nodi reali con il
			   // path minore dal layer in alto e via via , scendendo , fissare gli altri nodi
			   // virtuali rispettando eventuali obblighi di stessa ascissa.
			   
			   //			System.out.print("\n   ..ciclo " + tenta + " con indice nodo su layer(" + La + ") = " + iA);
			   //			view(matrix);
			   
			   
				  if (_o != null) 
				  {
				  
				  //			   System.out.print("\n cerco link rimasti verso il nodo -> " + _o.name);
					 size_inp = _o.neighbors_inp.size();
					 center = _o.barycentre;
				  
					 if (size_inp > 0) 
					 {
					 
					 // Cerca tra i figli di _o, il primo nodo discendente su layer successivo
					 // da piazzare
					 //
					 
					 
						boolean done = false;
					 
					 
					 
						minimo = 999;
					 
						_py_optimum = null;
					 
						for (iB = 0;iB < dim_x; iB++) 
						{
						   _py = m[Lb][iB];
						   if ((_py != null) && (!_py.is_traversed)&& (_o.neighbors_inp.contains(_py)) && (_o.has_child(_py) || _py.has_child(_o)) )
						   {
						   //						System.out.print("\n     trovato possibile successore di "+_o.name+ " = "+ _py.name);
						   //						System.out.print(" ( tipo --> "+_py.type+" )");
						   
							  int ll2 = GrafRoutine.hamming(_py,vEdge,0);
						   //						System.out.print("\n     distanza da nodo reale = "+ ll2);
							  if (ll2 < minimo)
							  {
								 minimo = ll2;
								 _py_optimum = _py;
								 done = true;
							  } 
						   
						   
						   
						   }
						
						
						
						}	 
					 
					 
					 
					 
						if (done)
						{
						   _py = _py_optimum;
						   fatto = false;
						
						}
					 
					 
					 
					 
					 
					 
					 
					 //				  boolean done = false;
						for (iB = 0;(iB < dim_x) && (!done); iB++) 
						{
						   _py = m[Lb][iB];
						   if (_py != null) 
						   {
						   //						System.out.print("\n     analisi nodo " + _py.name + " mark=" + _py.is_traversed);
						   
						   
							  boolean pare1 = _o.has_child(_py);
							  boolean pare2 = _py.has_child(_o);
						   
						   
							  if ((pare1 || pare2) && !(_py.is_traversed)) 
							  //						if ((_o.has_child(_py)) && !(_py.is_traversed)) 
							  {
							  //						   System.out.print("\n okay : il nodo " + _py.name + " deve essere piazzato!");
								 done = true;
								 fatto = false;
								 break;
							  } 
						   
						   /*						else if (!(_o.has_child(_py)))
						   System.out.print("\n    -> scarto " + _py.name + " perchè non è figlio di " + _o.name); 
						   else if (_py.is_traversed)
						   System.out.print("\n    -> scarto " + _py.name + " perchè già fatto");
						   */
						   }
						
						}
					 
					 
					 
					 
						if (done) 
						{
						   error = true;
						// se trova un figlio da piazzare tra La e Lb , cerca un intorno del baricentro del padre 
						// in cui mettere l'elemento;
						// per fare questo procede a spirale con posizione di partenza = al baricentro del padre
						// sommata alla funzione spirale; 
						// dato che per costruzione il numero di colonne = al massimo numero di nodi il posto
						// deve essere trovato;
						// se così non è allora esiste un errore nel calcolo o del baricentro o dell'assegnazione dei
						// nodi
						//
						   int max_range = dim_x * 2;
						   for (int k = 0; k < max_range ; k++) 
						   {
						   // calcolo posizione iniziale
						   //
							  raggio = GrafRoutine.spiral(k) + center;
						   
						   //						System.out.print("\n vedo se su matrix["+Lb+"]["+raggio + "] c'è posto!");
						   
						   // verifico che il raggio sia corretto
						   //
							  if ((raggio >= 0) && (raggio < dim_x)) 
							  {
							  
							  // se raggio è nei limiti allora verifico se il posto è disponibile
							  //
							  //						   System.out.print("\n    okay indice " + raggio+" corretto : verifico se matrix["+raggio+"] è libero!");
								 _pb = matrix[Lb][raggio];
								 if (_pb == null) 
								 {
								 //							  System.out.print("\n      okay : posto disponibile!");
								 //							  System.out.print(" :  posiziono "+_py.name+" su matrix["+Lb+"]["+raggio+"] ");
									_py.is_traversed = true;
									_py.barycentre = raggio;
									matrix[Lb][raggio] = _py;
									error = false;
									break;
								 }
							  }
						   }
						
						
						
						
						} // fine gestione se trovato figlio
					 
						if (error && _py != null)
						{
						//					 System.out.print("\n *ERROR LOGIC * non trovato posto posto per nodo "+_py.name);
						}
					 } // ciclo se _o ha figli
				  } // end ciclo se _o != null
			   } //end for
			
			} // end while
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 // sistemazione degli orfani , vale a dire dei nodi
		 // che non sono figli del padre in questa generazione
		 // per quanto possibile bengono messi in una posizione
		 // più centrale possibile
		 
		 
			for (iB = 0; iB < dim_x; iB++) 
			{
			   _py = m[Lb][iB];
			   if (_py != null) 
			   {
				  if (!_py.is_traversed) 
				  {
				  //				System.out.print("\n ++++++++++++++++++++++ OKAY   : SISTEMARE L'ORFANO : "+_py.name);
					 center = (int) dim_x / 2;
					 int max_range = dim_x  * 2;
					 for (int k = 0; k < max_range ; k++) 
					 {
					 // calcolo posizione iniziale
					 //
						raggio = GrafRoutine.spiral(k) + center;
					 
					 //						System.out.print("\n vedo se su matrix["+Lb+"]["+raggio + "] c'è posto!");
					 
					 // verifico che il raggio sia corretto
					 //
						if ((raggio >= 0) && (raggio < dim_x)) 
						{
						
						// se raggio è nei limiti allora verifico se il posto è disponibile
						//
						//						   System.out.print("\n    okay indice " + raggio+" corretto : verifico se matrix["+raggio+"] è libero!");
						   _pb = matrix[Lb][raggio];
						   if (_pb == null) 
						   {
						   //							  System.out.print("\n      okay : posto disponibile!");
						   //							  System.out.print(" :  posiziono "+_py.name+" su matrix["+Lb+"]["+raggio+"] ");
							  _py.is_traversed = true;
							  _py.barycentre = raggio;
							  matrix[Lb][raggio] = _py;
							  error = false;
							  break;
						   }
						}
					 }
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  }
			   }
			} 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 //
		 // A questo punto ipoteca i nodi virtuali estendendo le coordinate verso il basso
		 // In pratica cerca i nodi virtuali su Lb e se il nodo è una estensione di un link
		 // con len > 1 , lo propaga su matrix, con la stessa ascissa e ordintata crescente
		 // verso il basso , fino alla sua origine
		 //
		 
		 //	  System.out.print("\n SECONDA FASE..............");
		 
			for (iB = 0; iB < dim_x; iB++) 
			{
			   _o = m[Lb][iB];
			   if (_o != null) 
			   {
			   // il nodo è virtuale? 
			   //
				  if (_o.is_virtual()) 
				  //			if (_o.type == 4) 
				  {
				  // okay nodo virtuale , vedo se fà parte un gruppo di nodi virtuali
				  // che rappresentano un lato di len > 1
				  //
				  //			   System.out.print("\n calcola figli virtuali di  ->" + _o.name);
					 Vector Lista = new Vector(1, 0);
					 Vector l = GrafRoutine.L(_o, vEdge,Lista);
				  //			   System.out.print("\n provo a calcolare i figli..");
				  //			   System.out.print(" (lista ha len = " + l.size()+" )");
					 if (l.size() > 0) 
					 {
					 // dato che len > 1 , mi posiziono sul padre in Lb , e calcolo
					 // il baricentro;
					 // quindi scorro la lista dei nodi virtuali e se ne esiste qualcuno
					 // non marcato , lo estendo
					 //
					 //		  System.out.print("\n (lista ha len = " + l.size()+" )");
						center = _o.barycentre;
						for (i = 0; i < l.size(); i++) 
						{
						   Vertex e = (Vertex) l.elementAt(i);
						//					 System.out.print("\n leggo elem(" + i + ") = " + e.name);
						// se il nodo non è stato marcato lo imposta come marcato e
						// imposta il suo baricentro
						//
						   if (!e.is_traversed) 
						   {
							  e.is_traversed = true;
							  e.barycentre = center;
							  matrix[Lb + i + 1][center] = e;
						   //						System.out.print("\n okay , piazzato ->" + e.name);
						   }
						}
					 }
				  }
			   }
			}
		 
		 //	  System.out.print("\n++  prima di next levels++");
		 //	  view(matrix);
		 
		 }
	  
	  
	  
	  
	  //   System.out.print("\n++++++1111111+++++++++++  alla fine ho ++++++++++++++++++++++++++"); 
	  //   view(matrix);
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  /*
	  
	  
	  
	  
	  // a questo punto cerca il primo elemento utile in matrix
	  //
	  int first_elem = 9999;
	  for (j = 0; j < dim_y; j++) 
	  {
	  //	  System.out.print("\n riga .."+j);
	  for (i = 0; i < dim_x; i++) 
	  {
	  //		 System.out.print("\n     colonna.."+i);
	  
	   if (matrix[j][i] != null)
	   {
	  	 
	  	 if (first_elem > i)
	  	 {
	  		 if (i==0)
	  		 {
	  			 first_elem = 0;
	  			 break;
	  		 }
	  		 else
	  		 {
	  			 first_elem = i;
	  			 break;
	  		 }
	  	 }
	  
	  
	  	 
	   }
	  }
	  
	  if (first_elem == 0)
		break;
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  System.out.print("\n primo elemento utile a colonna +"+first_elem);
	  
	  // quindi l'ultimo elemento utile
	  //
	  int last_elem = 0;
	  for (j = 0; j < dim_y ; j++) 
	  {
	  //	  System.out.print("\n riga .."+j);
	  for (i = (dim_x-1); i > 0; i--) 
	  {
	  //		 System.out.print("\n     colonna.."+i);
	   _o = matrix[j][i];
	   if (_o != null) 
	   {
	  	if (i == (dim_x-1)) 
	  	{
	  	   last_elem = i;
	  	   break;
	  	}
	  	else
	  	{
	  	   if (last_elem < i) 
	  	   {
	  	      last_elem = i;
	  	      break;
	  	   }
	  	}	 
	   }
	  }
	  
	  if (last_elem == (dim_x-1))
	   break;
	  
	  
	  }
	  
	  System.out.print("\n ultimo elemento in colonna +"+last_elem);
	  
	  
	  
	  
	  
	  */
	  
	  
		 for (j = 0; j < dim_y ; j++) 
		 {
			for (i = 0; i < dim_x; i++)
			{
			   _o = matrix[j][i];
			   if (_o != null)
				  _o.gx = i;
			   m[j][i] = _o;
			}
		 }
	  
	  //   System.out.print("\n  terminato ---------------------mat =  +"+last_elem);
	  
	  }
   
   
   
   
   
   
	   public void view(Vertex v[][]) 
	  {
	  
		 String mask3 = " 000";
		 DecimalFormat fmt3 = new DecimalFormat(mask3);
	  
		 System.out.print("\n -matrix-");
		 for (int j = 0; j < dim_y; j++) 
		 {
			System.out.print("\n row <" + j + "> ");
			for (int i = 0; i < v[j].length; i++) 
			{
			
			   Vertex _p = v[j][i];
			   if (_p == null)
				  System.out.print("  ..");
			   else
				  System.out.print(fmt3.format(_p.name));
			}
		 }
	  
	  }/**
   * definisce la matrice di Vertex e
   * inizializza tutti i Vertexer a 0 (prima posizione
   * disponibile sia su X che su Y
   */                        
	   public mXY(int row,int col, Vector _v) 
	  {
	  
		 int []p = new int[row];
		 int px;
		 int py;
	  
		 for (int j=0; j < row; j++)
			p[j] = 0;
	  
		 m = new Vertex[row][col];
	  
		 dim_y = row;
		 dim_x = col; 
	  
	  
		 Iterator itr_Vertex = null;
		 Vertex _Vertex = null;
	  
	  
		 itr_Vertex = _v.iterator();
		 while (itr_Vertex.hasNext()) 
		 {
			_Vertex = ((Vertex) itr_Vertex.next());
			if (_Vertex.type != CodeConstant.VERTEX_RECURRENT)
			{
			   py = _Vertex.level;
			   px = p[py];
			   m[py][px] = _Vertex;
			   p[py]++;
			}
		 }
	  
	  }}