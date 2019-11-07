
The NEAT software is based on the NeuroEvolution of Augmenting Topologies
method of evolving artificial neural networks, by Stanley and
Miikkulainen (2001).  Papers describing the method and some associated
findings are available at:

http://www.cs.utexas.edu/users/kstanley

The web site includes a list of NEAT-related papers, and eventually we hope to
provide a FAQ as well.

Also, see:
http://www.cs.utexas.edu/users/nn/pages/research/ne-methods.html#NEAT

The major paper on the NEAT method is:

@Article{stanley:ec02,
  author       = "Kenneth O. Stanley and Risto Miikkulainen",
  title        = "Evolving Neural Networks Through Augmenting
                  Topologies",
  journal      = "Evolutionary Computation",
  publisher    = "MIT Press",
  editor       = "Darrell Whitley",
  year         = 2002,
  volume       = 10,
  number       = 2,
  pages        = "99--127",
  bibauthor    = "kstanley",
}

Please consider the file QUICKSTART.txt, or USAGE.txt for more
detailed information.  Note that QUICKSTART.txt was written by
Kenneth Stanley, while other documentation is written by Ugo Vierucci,
who wrote the Java version based on the original version in C++
by Kenneth Stanley.  Ugo did a great job writing documentation
in English even though his native language is Italian, so please
understand this while reading files other than QUICKSTART.txt.

This program is free software; you can redistribute it and/or modify it
under the terms of the GNU General Public License version 2 as published
by the Free Software Foundation. This program is distributed in the hope
that it will be useful, but without any warranty; without even the
implied warranty of merchantability or fitness for a particular purpose.
See the GNU General Public License for more details.

We hope that this software will be a useful starting point for your own
explorations in Neuroevolution. The software is provided as is,
however, we will do our best to maintain it and accommodate
suggestions. If you want to be notified of future releases of the
software or have questions, comments, bug reports or suggestions, send
email to kstanley@cs.utexas.edu.  You may also e-mail ugo.vierucci@virgilio.it.
I suggest e-mailing us together for JNEAT-related questions.  For general
NEAT methodological questions, please e-mail kstanley@cs.utexas.edu.

24 June 2002

README
====================================================================================================
This application is a java Simulator  of package N.E.A.T. ( N.E.A.T. has writed by Kenneth Stanley);
It has tested in Win2000/win98 and Linux red-hat 7.2 without problems;
No classpath modification has needed;
The only prerequisite is  SUN-JDK 1.3.1_01   or  IBM-JDK 1.2.2
In the  document i have included a shorter example for use jneat
Again : is included a little documentation graphics writed with program DIA (linux or 
Windows)

NOTE (Ken): It should work with later versions of Java as well, but you must
  change the classpaths.  See QUICKSTART.txt for more info.

the name of this documentation graph is 
   diagram.*

(I have included many type of file : dia,wmf,tex.....)


Note:
In linux  I have used the root autority for all commands :  compile, run, compilall





Windows install and start
--------------------------

1) uncompress file from directory   
	 \windows\jneat.1.1.win     to  c:\
   with unzip or winzip

2) open a msdos windows ,  enter command  
      cd \
      cd c:\jes\gui


3) when you are in c:\jes\gui  enter the command :
      compall

   (This command compile all class needed for Simulator package)

4) for run the application go in c:\jes\gui\  and give this command:
      run  gui.MainGui



Linux install and start
------------------------

For Linux are two files : 
     jes.1.1.tar
     jes.1.1.tar.gz
Those files are identical but are compressed in two different mode.

1) uncompress file from directory 
	 \linux\jes.1.0.tar or \linux\jes.1.0.tar.gz   to    /home/jes

2) after you must verify those members in library /home/jes/gui :
      'compilall.sh' ,'run.sh','compile.sh'
   after,  you must  verify the location for this package and for current java location 
   (in these members i have supposed  the package jneat  in : /home/jes' and
    java location in '/home/jdk1.3.1_01';
   
3) if your java location isn't correct, you must modify in the above members
   the value for variable JAVA_LOCATION; 
   (same action if you change the location for installing jneat).
 
4) go to dir   '/home/jes/gui'  and from a console window  give this command: 
      ./compilall.sh
   (This command compile all class needed for jneat)

5) for run the application go in '/home/jes/gui' and give this command :
      ./run.sh   gui.MainGui






             S t r u c t u r e       o f      P a c k a g e
=========================================================================


      jes+
         !
         +----->gui+
         !       !
         !       +-----> gui.Generation        
         !       +-----> gui.Session       
         !       +-----> gui.Parameter       
         !       +-----> gui.Graph       
         !
         +--->jGraph
         !       !
         !       +-----> jGraph.graph
         ! 
         !
         +--->jneat
         !       !
         !       +-----> jneat.jConsoleNeat
         ! 
         !
         +--->jNeatCommon
         ! 
         !
         +----->log
         !       !
         !       +-----> log.HistoryLog
         ! 
         !
         +--->testcase
                 !
                 +-----> testcase.testnet


In all dir are present the members for 

-compiling a single class : 
   compila.bat for Windows;
   compila.sh  for Linux;

-run  a single class : 
   run.bat for Windows;
   run.sh  for Linux;


-compile all class in current dir : 
   compilall.bat for Windows;
   compilall.sh  for Linux;


gui:
====
This directory contains the main program  MainGui for call all classes  of  GUI interface:

  Parameter.java  -> for settings the neat parameter
  Session.java    -> for settings the session parameter
  Generation.java -> for starting the simulation of package;
  Grafi.java      -> for draw the graph single or a group of genome (population)

All class are called in a main class :
  
  MainGui.java


If you want start a single class , you must digit :
  Windows:
        run    gui.XXXXX      XXXXX = name of class   
  Linux:
       ./run.sh gui.XXXXX      XXXXX = name of class   

if you are in jGrapg dir you can digit , only for debugging :
         run  | ./run.sh   jGraph.graph    



The other class contains :

jNeatCommon:
============
All globals constants used , routines , varie , environment variables etc...

jneat:
======
the package jneat  -> 'THE KERNEL' <-
(I haven't modify Evolution class because can be considered only for debug and internal test)

jGraph:
=======
the  package jGraph , for testing  ,view,  experimenting and  debug the graph of genome;

log:
====
Is the class for realize the : 'log of action's '

testcase:
=========
There are two class : One in order to view the font present in systems (can be eliminated!) ,
the other in order to  test the genome in a batch environment;





  
               


