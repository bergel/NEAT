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
echo '  Compile EnvConstant..'
$JAVA_LOCATION/bin/javac  EnvConstant.java
echo '  Compile NeatConstant....'
$JAVA_LOCATION/bin/javac  NeatConstant.java
echo '  Compile EnvRoutine....'
$JAVA_LOCATION/bin/javac  EnvRoutine.java
echo '  Compile CodeConstant....'
$JAVA_LOCATION/bin/javac  CodeConstant.java
echo '  Compile IOseq....'
$JAVA_LOCATION/bin/javac  IOseq.java
echo '  Compile NeatRoutine....'
$JAVA_LOCATION/bin/javac  NeatRoutine.java
echo '  Compilazione terminata ' 





