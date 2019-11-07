package testcase;


import java.awt.*;

/**
 * Insert the type's description here.
 * Creation date: (24/04/2002 7.15.35)
 * @author: Administrator
 */
public class testfont extends testnet {
/**
 * testfont constructor comment.
 */
public testfont() {
	super();
}
/**
 * testfont constructor comment.
 * @param _FileGenome java.lang.String
 */
public testfont(String _FileGenome) {
	super(_FileGenome);
}
/**
 * Starts the application.
 * @param args an array of command-line arguments
 */
public static void main(java.lang.String[] args) {


   String fontlist[];
   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
   fontlist = ge.getAvailableFontFamilyNames();
   System.out.print("\n In questo sistema le famiglie di font disponibili sono :");
   System.out.print("\n --------------------------------------------------------");
   for (int i=0; i < fontlist.length; i++)
   {
	   String fam = "\n Family :"+fontlist[i];
	   System.out.print(fam);
   }

   Font [] vFont = ge.getAllFonts();
   Font f = null;
   System.out.print("\n i Font disponibili sono :");
   System.out.print("\n -------------------------");
   

   for (int i=0; i < vFont.length; i++)
   {
	   f = vFont[i];
	   String r1 = "\n Family :"+f.getFamily();
	   String r2 = " ,  name:"+f.getName();
	   String r3 = " , size:"+f.getSize();
	   String r4 = " , style:"+f.getStyle();

	   String riga = r1+r2+r3+r4;
	   System.out.print(riga);
   }







   
}
}