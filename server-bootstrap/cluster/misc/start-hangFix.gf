# This version can be used to fix the hang that occasionally still occurs on start up 
# of GemFire services (especially on Windows)
start locator --name=locator --properties-file=gemfire-server.properties --initial-heap=50m --max-heap=50m --J=-Dgemfire.OSProcess.ENABLE_OUTPUT_REDIRECTION=true

start server --name=server1 --server-port=0 --classpath=${SYS_CLASSPATH} --properties-file=gemfire-server.properties --initial-heap=50m --max-heap=50m --J=-Dgemfire.OSProcess.ENABLE_OUTPUT_REDIRECTION=true

start server --name=server2 --server-port=0 --classpath=${SYS_CLASSPATH} --properties-file=gemfire-server.properties --initial-heap=50m --max-heap=50m --J=-Dgemfire.OSProcess.ENABLE_OUTPUT_REDIRECTION=true

start server --name=server3 --server-port=0 --classpath=${SYS_CLASSPATH} --properties-file=gemfire-server.properties --initial-heap=50m --max-heap=50m --J=-Dgemfire.OSProcess.ENABLE_OUTPUT_REDIRECTION=true
