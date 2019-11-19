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
echo '  Compile Generation....'
$JAVA_LOCATION/bin/javac  Generation.java
echo '  Compile Grafi....'
$JAVA_LOCATION/bin/javac  Grafi.java
echo '  Compile MainGui....'
$JAVA_LOCATION/bin/javac  MainGui.java
echo '  Compile Parameter....'
$JAVA_LOCATION/bin/javac  Parameter.java
echo '  Compile Session....'
$JAVA_LOCATION/bin/javac  Session.java
echo '  Compile Execution....'
$JAVA_LOCATION/bin/javac  Execution.java
echo '  Compilazione terminata ' 





