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
echo '  Compile testnet....'
$JAVA_LOCATION/bin/javac  testnet.java
echo '  Compile testfont...'
$JAVA_LOCATION/bin/javac  testfont.java
echo '  Compilazione terminata ' 





