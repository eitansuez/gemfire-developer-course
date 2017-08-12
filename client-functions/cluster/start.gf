start locator --name=locator --properties-file=gemfire.properties --initial-heap=50m --max-heap=50m

set variable --name=CP --value=../../build/classes/java/main
start server --name=server1 --server-port=0 --classpath=${CP} --properties-file=gemfire.properties --initial-heap=50m --max-heap=50m
start server --name=server2 --server-port=0 --classpath=${CP} --properties-file=gemfire.properties --initial-heap=50m --max-heap=50m
