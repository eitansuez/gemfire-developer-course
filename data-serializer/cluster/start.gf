start locator --name=locator
set variable --name=CP --value=../../build/classes/java/main
start server --server-port=0 --properties-file=gemfire.properties --name=server1 --classpath=${CP}
start server --server-port=0 --properties-file=gemfire.properties --name=server2 --classpath=${CP}
