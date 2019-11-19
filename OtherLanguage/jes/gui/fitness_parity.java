public class fitness_parity { 
 
  public static double getMaxFitness() { return Math.pow (8.0, 2); } 
 
  public static double[]  computeFitness(int _sample, int _num_nodes, double _out[][], double _tgt[][]) 
  {     double d[] = new double[3]; 
     double errorsum = 0.0; 
     double fitness; 
     for ( int j = 0; j < _sample; j++) 
        { 
           errorsum  += ( double ) (Math.abs(_tgt[j][0] - _out[j][0])); 
        } 
     fitness = Math.pow ( ( 8.0 - errorsum ) , 2 ); 
 
     d[0] = fitness; 
     d[1] = errorsum; 
     d[2] = 0.0; 
 
     if ((_out[0][0] < 0.5) && (_out[1][0] >= 0.5) &&  
            (_out[2][0] >= 0.5) && (_out[3][0] < 0.5) &&
            (_out[4][0] >= 0.5) && (_out[5][0]  < 0.5) &&  
            (_out[6][0] <  0.5) && (_out[7][0] >= 0.5)) 
        d[2] = 1.0; 
 
     return d; 
  } 
} 
