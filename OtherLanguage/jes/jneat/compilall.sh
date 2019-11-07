#!/bin/sh
echo '  Start compile all members in this directory : '  
      pwd
echo ' wait please .......'
JNEAT_LOCATION=/home/jes
JAVA_LOCATION=/home/jdk1.3.1_01
CLASSPATH=$JNEAT_LOCATION
CLASSPATH=$CLASSPATH:$JAVA_LOCATION'/jre/lib/rt.jar'
CLASSPATH=$CLASSPATH:$JAVA_LOCATION'/lib/tools.jar'
export CLASSPATH
echo '  Curr location of java :  ' $JAVA_LOCATION
echo '  Curr classpath :  ' $CLASSPATH
echo '  Compile jConsoleNeat..'
$JAVA_LOCATION/bin/javac  jConsoleNeat.java
echo '  Compile Gene....'
$JAVA_LOCATION/bin/javac  Gene.java
echo '  Compile Innovation....'
$JAVA_LOCATION/bin/javac  Innovation.java
echo '  Compile Evolution....'
$JAVA_LOCATION/bin/javac  Evolution.java
echo '  Compile Trait....'
$JAVA_LOCATION/bin/javac  Trait.java
echo '  Compile Link....'
$JAVA_LOCATION/bin/javac  Link.java
echo '  Compile Organism....'
$JAVA_LOCATION/bin/javac  Organism.java
echo '  Compile Population....'
$JAVA_LOCATION/bin/javac  Population.java
echo '  Compile Network....'
$JAVA_LOCATION/bin/javac  Network.java
echo '  Compile order_species....'
$JAVA_LOCATION/bin/javac  order_species.java
echo '  Compile order_inner....'
$JAVA_LOCATION/bin/javac  order_inner.java
echo '  Compile Species....'
$JAVA_LOCATION/bin/javac  Species.java
echo '  Compile NNode....'
$JAVA_LOCATION/bin/javac  NNode.java
echo '  Compile Neat....'
$JAVA_LOCATION/bin/javac  Neat.java
echo '  Compilazione terminata ' 





