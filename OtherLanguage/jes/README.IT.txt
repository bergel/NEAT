NOTE: Most of this document is in Italian


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
Questa applicazione è un simulatore java del pacchetto NEAT , sviluppato
da Kenneth Stanley;
E' stato testato sia in ambiente linux , red-hat 7.2 , sia in ambiente windows , win200,win98;
Non sono necessarie classpath e il solo requisito è il JDK 1.3.1_01 di Sun o 
il JDK 1.2.2 di IBM;
In questo documento è incluso in piccolo esempio per la simulazione della rete 
che si 'configura' per emulare la funzione di XOR;
Viene incluso anche un mini grafo esplicativo di come sono strutturati i vari package
che compongono l'applicazione tramite il prodotto DIA.

NOTE (Ken): It should work with later versions of Java as well, but you must
  change the classpaths.  See QUICKSTART.txt for more info.

Note:
1) In linux  è stata usata l'authority 'root' per usare i comandi :  compile, run, compilall


Installazione sotto Windows :
--------------------------

1) fare la unzip (tramite winzip o prodotti similari)  di
	 jneat.1.2.win     su   c:\

2) aprire la finestra msdos (START->PROGRAMMI->ACCESSORI-> PROMPT DEI COMANDI in win2000) e digitare
      cd \
      cd c:\jes\gui


3) dalla libreria c:\jes\gui dare il seguente comando
      compall
   (Questo comando compila tutte le classi necessarie per la simulazione )


4) Per fare eseguire l'applicazione , posizionarsi su  c:\jes\gui\ e digitare :
      run  gui.MainGui







             S t r u t t u r a       d e l      P a c k a g e
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


In tutte le directory sono presenti seguenti membri per la compilazione :

-compilazione della singola classe :
   compila.bat for Windows;
   compila.sh  for Linux;

-esecuzione del singolo package:
   run.bat for Windows;
   run.sh  for Linux;


-compilazioone di tutte le classi nella corrente directory:
   compilall.bat for Windows;
   compilall.sh  for Linux;


gui:
====
Questa directory contiene il programma principale , MainGui , che richiama
, in base alle richieste,  i vari moduli di simulazione :

  Parameter.java  -> settaggio dei parametri di funzionamento del package neat
  Session.java    -> settaggio dei parametri della sessione di simulazione
  Generation.java -> esecuzione vera e propria della simulazione
  Grafi.java      -> visualizzazione in forma di grafo del singolo genoma o di una popolazione
		     di genomi sia generici che winner;




jNeatCommon:
============
Tutte le variabili / costanti  globali usate, routines , etc...

jneat:
======
il 'kernel' vero : jneat 

jGraph:
=======
il package per la visualizzazione grafica che conprende una classe main, jgraph, per il testing , visualizzazione
, debug, grafi in forma di genoma;

log:
====
piccola applicazione per il 'logging' di quello che succede durante il funzionamento;
Simile ad una log di sistema (simile ad una 'console' di sistema read-only)

testcase:
=========
In questa classe sono rimaste due classi per la verifica dei font e il test
stand-alone  dell'applicazione jneat;
Tale classe è ormai obsoleta e potrà essere tolta in futuro



