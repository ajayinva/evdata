# evdata initial readme file

DB
1. After you clone the repo create the required tables using script.txt

1. Clone the repo
2. Open DataLoader.java
3. Uncomment the class definition so that it implements CommandLineRunner when running first time. This will insert the Dataset into the DB
4. Before subsequent runs please revert to the comment version of the DataLoader.java so that it no longer implements CommandLineRunner.java 