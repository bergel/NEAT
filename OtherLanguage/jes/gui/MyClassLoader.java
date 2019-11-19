package gui;

import java.io.*;
public class MyClassLoader extends ClassLoader {
/**
 * MyClassLoader constructor comment.
 */
public MyClassLoader() {
	super();
}

public synchronized Class loadClass(String _name)
{
	FileInputStream fi = null;
	try
	{
		String _fname = jNeatCommon.EnvRoutine.getJneatFile(_name);
		String path = _fname.replace('.','/');
		System.out.print(" step1 = "+path);
		
		fi = new FileInputStream(path+".class");
		System.out.print(" step2 = "+path);
		byte[]  classBytes = new byte[fi.available()];
		System.out.print(" step3 = "+path);
		fi.read(classBytes);
		System.out.print(" step4 = "+path);
		return defineClass(_name,classBytes,0,classBytes.length);

		
	}
	catch (Exception e)
	
	{
		System.out.print("\n errore su loadclass"+_name+" : \n"+e);
		System.exit(8);
		return null;
	}
	
}





}