//Extra game feature is code asks if the user want to see the map instead of going in any direction, it costs 2 hp and the user can see the map at the place they are currently at
//I hope this can get me 20 extra point T_T
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static Actor hero = new Actor();
    public static ArrayList<Actor> monster = new ArrayList<>();
    public static char[][] cataComb;
    public static int dungeonSize;

    /**
     * Extra feature in the game to show user the catacomb
     * This is my method for printing the catacomb just a nested for loop
     * @return void
     */
    public static void printDungeon() {
        for (int i = 0; i < cataComb.length; i++) {
            for (int j = 0; j < cataComb[i].length; j++) {
                System.out.print(cataComb[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Main method
    public static void main(String[] args) throws Exception {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What is your name heroic adventurer? ");
        String heroName = inputScanner.nextLine();
        hero.setName(heroName);
        getDungeonSize(inputScanner);
        makeDungeon();
        makeMonsters();
        
        boolean validity = true;
        while (validity) {

            System.out.println(heroName + " at " + hero.getRow() + ", " + hero.getCol() + " with " + hero.getHealth()
                    + " health.");
            System.out.println("You smell " + smellMonsters() + " monsters nearby.");
            makeMove(inputScanner);
            if (hero.getRow() == dungeonSize - 1 && hero.getCol() == dungeonSize - 1) {

                System.out.println("You won");
                validity = false;
            } else if (hero.getHealth() < 0) {
                System.out.println("You died");
                validity = false;
            }
            hero.setHealth(hero.getHealth() - 2);
            
        }
        
    

        inputScanner.close();
    }

    public Actor makeActor() {
        Actor actorA = new Actor();
        return actorA;
    }
/**
 * Makes the image of a dungeon when dungeon is printed by the print dungeon method.
 */
    public static void makeDungeon() {
        cataComb = new char[dungeonSize][dungeonSize];
        for (int i = 0; i < cataComb.length; i++) {
            for (int j = 0; j < cataComb[i].length; j++) {
                cataComb[i][j] = '|';
            }
        }
        cataComb[0][0] = 'A';
        hero.setPosition(0, 0);
    }
/**
 * promps user for how big of a dungeon they want between 5-10
 * @param inputScanner
 */
    public static void getDungeonSize(Scanner inputScanner) {
        Boolean valid = true;
        String tempSize = "";
        while (valid) {
            System.out.println("How wide of a catacomb do you want to face (5-10)?");
            tempSize = inputScanner.nextLine();
            try {
                dungeonSize = Integer.parseInt(tempSize);
            } catch (Exception error) {
            }
            if (dungeonSize >= 5 && dungeonSize <= 10) {
                valid = false;
            } else {
                System.out.println("That is not a valid catacomb size!");
            }

        }

    }
    /**
     * Makes so the monsters are 1 in every 6 box as required by the project
     * 
     */

    public static void makeMonsters() {
        int monsterNum = (dungeonSize * dungeonSize) / 6;
        int row;
        int col;
        for (int i = 0; i <= monsterNum; i++) {
            String mon = "Monster " + i;
            Random randomInt = new Random();

            do {
                row = randomInt.nextInt(0, dungeonSize);
                col = randomInt.nextInt(0, dungeonSize);
            } while (row == 0 && col == 0);

            if (row != dungeonSize && col != dungeonSize) {
                if (cataComb[row][col] != 'M') {
                    cataComb[row][col] = 'M';
                    monster.add(storeMonster(mon, row, col));
                }
            }
            
        }
        //This is a test case for loop to represent where my monsters are placed 

        // for (Actor testMonster : monster) {
        //     System.out.println(testMonster.getName() + " " + testMonster.getRow() + " " + testMonster.getCol());
        // }
    }
/**
 * Stores the monster into the areeay list 
 * @param mon monster
 * @param row
 * @param col
 * @return monsters as an actor
 */
    public static Actor storeMonster(String mon, int row, int col) {
        Actor actor1 = new Actor();
        actor1.setName(mon);
        actor1.setPosition(row, col);
        actor1.setHealth(25);
        actor1.setAttack(5);
        return actor1;
    }

    public void fight(Actor actorA, ArrayList<Actor> monsters) {

    }
/**
 * method for checking that the hero stays inside of the catacomb size
 * @param int types row and col to set boundaries and check if hero position is inside the catacomb
 * @return boolean true or false depending upon if the hero would be inside the catacomb after where ever the user wants them to go
 */
    public static Boolean validMove(int row, int col) {

        if (row <= dungeonSize - 1 && col <= dungeonSize - 1 && row >= 0 && col >= 0) {
            return true;
        } else {
            return false;
        }
    }
/**
 * method that takes in user input on where the hero should move.
 * @param inputScanner takes in the user input 
 * @return void
 */
    public static void makeMove(Scanner inputScanner) {
        System.out.println(" Which way do you want to go?(north, south, east, west, map)");
        String input = inputScanner.nextLine();
        if (input.equalsIgnoreCase("north")) {
            moveNorth();

        } else if (input.equalsIgnoreCase("south")) {
            moveSouth();

        } else if (input.equalsIgnoreCase("east")) {
            moveEast();

        } else if (input.equalsIgnoreCase("west")) {
            moveWest();

        } else if (input.equalsIgnoreCase("map")) {
            printDungeon();
        }


        checkAttackMonster();
    }
//method for moving east
    public static void moveEast() {
        int row = hero.getRow();
        int col = hero.getCol() + 1;

        if (validMove(row, col)) {
            hero.setPosition(row, col);
            cataComb[row][col] = 'A';
            cataComb[row][col - 1] = '|';
        } else {
            System.out.println("You can't move that way!");
        }
    }
//method for moving west
    public static void moveWest() {
        int row = hero.getRow();
        int col = hero.getCol() - 1;

        if (validMove(row, col)) {
            hero.setPosition(row, col);
            cataComb[row][col] = 'A';
            cataComb[row][col + 1] = '|';
        } else {
            System.out.println("You can't move that way!");
        }
    }
//method for moving north
    public static void moveNorth() {
        
        int row = hero.getRow() - 1;
        int col = hero.getCol();

        if (validMove(row, col)) {
            hero.setPosition(row, col);
            cataComb[row][col] = 'A';
            cataComb[row + 1][col] = '|';
        } else {
            System.out.println("You can't move that way!");
        }
    }

    //method for moving south

    public static void moveSouth() {
        int row = hero.getRow() + 1;
        int col = hero.getCol();

        if (validMove(row, col)) {
            hero.setPosition(row, col);
            cataComb[row][col] = 'A';
            cataComb[row - 1][col] = '|';
        } else {
            System.out.println("You can't move that way!");
        }
    }

    /**
     * Method for counting how many monsters are 1 box away from the hero.
     * It is a bunch of if else statements to check for cases like if the hero is in the middle or at a cornor.
     * 
     * @return an int value counter which counts how many monsters there are next to the hero.
     */

    public static int smellMonsters() {
        int counter = 0;

        if (hero.getRow() == dungeonSize - 1 && hero.getCol() > 0) {
            if (cataComb[hero.getRow() - 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() + 1] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() - 1] == 'M') {
                counter++;
            }

            

        }
        else if(hero.getRow() == dungeonSize - 1 && hero.getCol() == 0){
            if (cataComb[hero.getRow() - 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() + 1] == 'M') {
                counter++;
            }
            }

        else if(hero.getRow() == 0  && hero.getCol() == dungeonSize - 1){
            if (cataComb[hero.getRow() + 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() - 1] == 'M') {
                counter++;
            }
            }
            

        else if (hero.getCol() == dungeonSize - 1 && hero.getRow() > 0) {
            if (cataComb[hero.getRow() + 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow() - 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() - 1] == 'M') {
                counter++;
            }
        } else if (hero.getRow() == 0 && hero.getCol() > 0) {
            if (cataComb[hero.getRow()][hero.getCol() - 1] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow() + 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() + 1] == 'M') {
                counter++;
            }
        }

        else if (hero.getCol() == 0 && hero.getRow() > 0) {
            if (cataComb[hero.getRow() - 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow() + 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() + 1] == 'M') {
                counter++;
            }
            
        }


        else if (hero.getCol() == 0 && hero.getRow() == 0) {
            if (cataComb[hero.getRow() + 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() + 1] == 'M') {
                counter++;
            }
        } else {
            if (cataComb[hero.getRow() + 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow() - 1][hero.getCol()] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() + 1] == 'M') {
                counter++;
            }
            if (cataComb[hero.getRow()][hero.getCol() - 1] == 'M') {
                counter++;
            }
        }
        return counter;
    }

    /**
     *  Checks if the hero is at a location where a monster is present and then runs the attackMonster method.
     * 
     * @returns void
    */
    public static void checkAttackMonster(){
        
        
        for(Actor mon: monster){
            if(hero.getRow() == mon.getRow() && hero.getCol() == mon.getCol()){
                System.out.println(hero.getName() + " at " + hero.getRow() + ", " + hero.getCol() + " with " + hero.getHealth() + " health versus Monster " + mon.getPosition() + " at " + mon.getRow() + ", " + mon.getCol() + " with " + mon.getHealth() + " Health");
                attackMonster(mon);
            }
        }
        
    }

    /**
     *  This Method is used insid ethe checkAttackMonster. So when inside of checkAttackMonster monster and hero are at the same place inside the catacomb this method runs.
     * subtracts hero health by monster hit and monster health by hero hit
     * @param Actor mon is the monster which the hero fights
     * @return void
     */

    public static void attackMonster(Actor mon){
        
        
        while(hero.getHealth() > 0 && mon.getHealth() > 0){
            int monsterHit = mon.hit();
            int heroHit = hero.hit();
            hero.setHealth(hero.getHealth() - monsterHit);
            mon.setHealth(mon.getHealth() - heroHit);
            System.out.println("You hit for " + heroHit);
            System.out.println("You get hit for " + monsterHit);
        }
        System.out.println("Monster" + mon.getPosition() + " at " + mon.getRow() + ", " + mon.getCol() + " has been defeated!");
        
    }


}
