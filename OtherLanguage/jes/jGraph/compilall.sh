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
echo '  Compile chartXY....'
$JAVA_LOCATION/bin/javac  chartXY.java
echo '  Compile code....'
$JAVA_LOCATION/bin/javac  code.java
echo '  Compile Edge....'
$JAVA_LOCATION/bin/javac  Edge.java
echo '  Compile GrafRoutine....'
$JAVA_LOCATION/bin/javac  GrafRoutine.java
echo '  Compile graph....'
$JAVA_LOCATION/bin/javac  graph.java
echo '  Compile mXY....'
$JAVA_LOCATION/bin/javac  mXY.java
echo '  Compile order_code....'
$JAVA_LOCATION/bin/javac  order_code.java
echo '  Compile order_inner....'
$JAVA_LOCATION/bin/javac  order_inner.java
echo '  Compile planeXY....'
$JAVA_LOCATION/bin/javac  planeXY.java
echo '  Compile Structure....'
$JAVA_LOCATION/bin/javac  Structure.java
echo '  Compile Vertex....'
$JAVA_LOCATION/bin/javac  Vertex.java
echo '  Compilazione terminata ' 




