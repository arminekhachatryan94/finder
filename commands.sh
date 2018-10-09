javac -cp .:./lib/* main/*.java -d out
cd out
java -cp .:../lib/* -Dlog4j.configurationFile=../log4j.xml Finder