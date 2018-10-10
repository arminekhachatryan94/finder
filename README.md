# finder
## Run Finder
chmod +x ./commands.sh
./commands.sh

## Test
cd out
javac -cp .:../lib/junit-4.10.jar ../test/PreferenceTest.java -d ./
java -cp .:../lib/junit-4.10.jar org.junit.runner.JUnitCore PreferenceTest

javac -cp .:../lib/junit-4.10.jar ../test/FindReplaceTest.java -d ./
java -cp .:../lib/junit-4.10.jar org.junit.runner.JUnitCore FindReplaceTest
