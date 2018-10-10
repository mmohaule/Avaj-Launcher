compile:
	find  . -type f -name "*.java" > sources.txt
	javac -g -cp -sourcepath @sources.txt

run:
	java com/mmohaule/simulator/main/AircraftSimulator Scenario.txt

clean:
	find . -type f -name "*.class" -delete
	find . -type f -name "sources.txt" -delete
	find . -type f -name "simulation.txt" -delete

fclean: clean

re: fclean all
