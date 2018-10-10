# finder
## Run Finder
chmod +x ./commands.sh
./commands.sh

## Test
cd out
javac -cp .:../lib/junit-4.10.jar ../test/PreferenceTest.java ../main/Preference.java -d ./
java -cp .:../lib/junit-4.10.jar org.junit.runner.JUnitCore PreferenceTest