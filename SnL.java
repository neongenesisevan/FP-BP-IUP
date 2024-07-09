/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 6
 * Members  :
 * 1. 5026231051 - Evan Danendra Pratama
 * 2. 5026231004 - Satria Pinandita
 * 3. 5999232027 - Chiyoung Lee (Tidak mengerjakan, keluar ITS sudah balik ke prancis)
 * ------------------------------------------------------
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SnL {
    
    //states, variable, or properties
    private int boardSize;
    private ArrayList<Player> players;
    private ArrayList<Snake>  snakes;
    private ArrayList<Ladder> ladders;
    private int gameStatus;
    private int currentTurn;

    /*      evan 1       */ 
    private Player winner;
    private int boardSideLength;
    private boolean isPreset;
    private int dieSides;
    private Double snakeProbability;
    private Double ladderProbability;
    private int totalMoves = 0;
    /*      ----       */
    
    //constructor
    public SnL (int size, boolean isPreset){
        this.boardSize = size;
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.players= new ArrayList<Player>();
        this.gameStatus = 0;

        /*      evan 2       */
        this.isPreset = isPreset;

        Double boardSideLengthDouble = Math.sqrt(size);;
        this.boardSideLength = (int) Math.floor(boardSideLengthDouble);

        if (boardSideLengthDouble % 1 != 0) {
            System.out.println(Pallette.ANSI_YELLOW + "[WARN] BOARD SIZE IS NOT SQUARE, PREPARE FOR UNEXPECTED CONSEQUENCES!" + Pallette.ANSI_RESET);
            this.boardSize = this.boardSideLength * this.boardSideLength;
        }
        /*      ----       */
    }
    
    public void initiateGame(){
        if (isPreset) {
            int [][] ladders = 
            {    {2, 23},
                {8, 34},
                {20, 77},
                {32,68},
                {41, 79},
                {74, 88},
                {82, 100},
                {85, 95}
            };
            setLadders(ladders);
            int [][] snakes = 
            {    {47, 5},
                {29, 9},
                {38, 15},
                {97,25},
                {53, 33},
                {92, 70},
                {86, 54},
                {97, 25}
            };
            setSnakes(snakes);   

        /*      evan 3        */
        } else {
            ArrayList<int []> snakesA = new ArrayList<int []>();
            ArrayList<int []> laddersA = new ArrayList<int []>();

            // set snakes
            for (int i = 0; i < boardSize; i++) {

                if (this.getTileType(i) != TileNames.tile || i <= this.boardSideLength) {
                    continue;
                }

                Double random = Math.random();
                if (random <= this.snakeProbability) {
                    int snakeHead = Functions.randInt(Math.max(1, i - this.boardSideLength * 3), i - this.boardSideLength);
                    int [] snake = {i, snakeHead};

                    snakesA.add(snake);
                    //System.out.println(Pallette.ANSI_GREEN + "[OK] Generated snake at " + i + " to " + snakeHead + Pallette.ANSI_RESET);
                }


            }

            // set ladders
            for (int i = 0; i < boardSize; i++) {

                if (this.getTileType(i) != TileNames.tile || i >= this.boardSize - this.boardSideLength) {
                    continue;
                }

                Double random = Math.random();
                if (random <= this.ladderProbability) {
                    int ladderTop = Functions.randInt(i + this.boardSideLength, Math.min(i + this.boardSideLength * 3, this.boardSize));
                    int [] ladder = {i, ladderTop};

                    laddersA.add(ladder);
                    //System.out.println(Pallette.ANSI_GREEN + "[OK] Generated ladder at " + i + " to " + ladderTop + Pallette.ANSI_RESET);
                }
            }

            //3.2
            int [][] snakes = new int[snakesA.size()][];
            int [][] ladders = new int[laddersA.size()][];

            snakes = snakesA.toArray(snakes);
            ladders = laddersA.toArray(ladders);

            setLadders(ladders);
            setSnakes(snakes);
        }
        /*      ----       */

    }
    
    public Player getTurn() {
     if (this.gameStatus == 0) {
         double r = Math.random();
         if (r < 0.5){
             this.currentTurn = 0;
             return this.players.get(0); 
         }
         else {
             this.currentTurn = 1;
             return this.players.get(1);
         }
         
         
     }   
     else {
        if (currentTurn == 0) {
        this.currentTurn = 1;
        return this.players.get(1);  
        }
       
        else {
            this.currentTurn = 0;
            return this.players.get(0); }
     }
    }
    
    //setter methods
    public void setSizeBoard(int size){
        this.boardSize = size;
    }
    public void addPlayer(Player p){
        this.players.add(p);
    }

    /*      evan 4       */
    public void setSnakeProbability(Double snakeProb) {
        this.snakeProbability = snakeProb;
    }
    public void setLadderProbability(Double ladderProb) {
        this.ladderProbability = ladderProb;
    }
    /*      ----       */

    public void setSnakes(int[][] snakes)
    {
        int s = snakes.length;
        for(int i = 0; i < s; i++){
            this.snakes.add(new Snake(snakes[i][0],snakes[i][1]));
        }
    }
    
    public void setLadders(int[][] ladders){
        int s = ladders.length;
        for(int i = 0; i < s; i++){
            this.ladders.add(new Ladder(ladders[i][0],ladders[i][1]));
        }
    }

    public int getBoardSize(){
        return this.boardSize;
    }
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    public ArrayList<Snake> getSnakes(){
        return this.snakes;
    }
    public ArrayList<Ladder> getLadders(){
        return this.ladders;
    }
    
    public int getGameStatus(){
        return this.gameStatus;
    }

    /*      evan 5       */
    private void recordResults(){
        String outputFile = "logs.txt";
        try{
            FileWriter fileWriter = new FileWriter(outputFile, true);
            
            fileWriter.write("\nWinner " + this.winner.getName() + "\n");
            fileWriter.write("Total number of turns : " + this.totalMoves + "\n");
            fileWriter.write("Dice sides : " + this.dieSides + "\n");
            fileWriter.write("Custom Board: " + (!this.isPreset) + "\n");
            fileWriter.write("Board size : " + this.boardSize + "\n");

            //5.1
            if (!this.isPreset) {
                fileWriter.write("Snake probability : " + this.snakeProbability + "\n");
                fileWriter.write("Ladder probability : " + this.ladderProbability + "\n");
            }

            // satria
            fileWriter.write("Players: -------------\n");
            for (Player player : players) {
                fileWriter.write(player.getName() + " on position " + player.getPosition() + " with " + player.getPoints() + " points.\n");
            }

            fileWriter.write("\n");
            
            fileWriter.close();
        } catch(IOException e) {
            System.out.println("An error occurred while writing the file.");
            System.out.println(e);
        }
    }
    
    public void play(){

        SoundPlayer gameSound; // satria------

        int randomMusic = Functions.randInt(1, 5);

        switch (randomMusic) {
            case 1:
                gameSound = new SoundPlayer("./sounds/music/nocturne1.wav");
                System.out.println("Music: Nocturne No. 1 in B-Flat Minor, Op. 9 No. 1");
                break;
            case 2:
                gameSound = new SoundPlayer("./sounds/music/nocturne2.wav");
                System.out.println("Music: Nocturne No. 2 in E-Flat Major, Op. 9 No. 2");
                break;
            case 3:
                gameSound = new SoundPlayer("./sounds/music/nocturne7.wav");
                System.out.println("Music: Nocturne No. 7 in C-Sharp Minor, Op. 27 No. 1");
                break;
            case 4:
                gameSound = new SoundPlayer("./sounds/music/nocturne11.wav");
                System.out.println("Music: Nocturne No. 11 in G Minor, Op. 37 No. 1");
                break;
            case 5:
                gameSound = new SoundPlayer("./sounds/music/nocturne20.wav");
                System.out.println("Music: Nocturne No. 20 in C-Sharp Minor, Op. Posth.");
                break;
            default:
                gameSound = new SoundPlayer("./sounds/music/nocturne1.wav");
                System.out.println("Music: Nocturne No. 1 in B-Flat Minor, Op. 9 No. 1");
                break;
        }

        gameSound.playContinuously();

        // -------------------------------------

        initiateGame();

        System.out.println("enter first player name:");
        Scanner sc= new Scanner(System.in);
        String firstPlayer= sc.nextLine();
        System.out.println("enter second player name:");
        String secondPlayer= sc.nextLine();

        /*      evan 6      */
        // get the sides of die
        while (true) {
            System.out.println("Please enter the number of dice sides.");
            try {
                this.dieSides = sc.nextInt();
                sc.nextLine();
            } catch(Exception e) {
                System.out.println("Error! Please enter a correct value!");
                sc.nextLine();
                continue;
            }
            if (this.dieSides <= 0) {
                System.out.println("Dice sides cannot be less than 1!");
            } else {
                break;
            }
        }
        /*      ----       */

        Player p1 = new Player(firstPlayer, Pallette.ANSI_BLUE);
        Player p2 = new Player(secondPlayer, Pallette.ANSI_PURPLE);
        
        addPlayer(p1);
        addPlayer(p2);
        
        Player nowPlaying;
        do{
            this.printBoard(); // evan 7
            System.out.println("----------------------------------------------");
            nowPlaying = getTurn();
            System.out.println("Now Playing: "+ nowPlaying.getName()+" the current position is "+nowPlaying.getPosition());
            System.out.println(nowPlaying.getName()+" it's your turn, please press enter to roll dice (" + "sides: " + dieSides + ")");
            System.out.println("(ENTER): Roll");
            System.out.println("(Q): Quit");
            
            String input= sc.nextLine();
            int x = 0;

            // get input roll is here
            if (input.isEmpty()){
                x = nowPlaying.rollDice(dieSides);

                // satria
                if (x > dieSides / 2) {
                    nowPlaying.addPoints();
                }

                SoundPlayer diceRollSound = new SoundPlayer("./sounds/effects/dice.wav"); //satria
                diceRollSound.playClip(); // satria
                
                this.totalMoves++; // evan 8
            } else if (input.equals("Q")) {
                gameSound.stopClip(); // satria
                return;
            }
            
            System.out.println(nowPlaying.getName()+ " is rolling dice and get number: "+x);

            movePlayer(nowPlaying, x);
            System.out.println(nowPlaying.getName()+ " new position is "+ nowPlaying.getPosition());

            Functions.wait(250); // satria
        } while(getGameStatus()!=2);
        
        System.out.println("The Game is Over, the winner is: "+nowPlaying.getName());
        winner = nowPlaying;
        this.recordResults(); // 8.1

        gameSound.stopClip(); // satria

        SoundPlayer winnerSound = new SoundPlayer("./sounds/effects/win.wav"); // satria
        winnerSound.playClip();
    }

    // evan 9
    public void checkStatusPlayer(Player p) {
        for(Ladder l: this.ladders){
            if(l.getFromPosition() == p.getPosition()){
                p.setPosition(l.getToPosition());
                System.out.println(Pallette.ANSI_GREEN + p.getName()+" got ladder so jumps to "+p.getPosition() + Pallette.ANSI_RESET);

                SoundPlayer ladderSound = new SoundPlayer("./sounds/effects/ladder.wav"); // satria
                ladderSound.playClip();
            }
        }
        
        for(Snake s: this.snakes){
            if(s.getHead() == p.getPosition()){
                p.setPosition(s.getTail());
                System.out.println(Pallette.ANSI_RED + p.getName()+" got snake so slide down to "+p.getPosition() + Pallette.ANSI_RESET);
                
                SoundPlayer snakeSound = new SoundPlayer("./sounds/effects/snake.wav"); // satria
                snakeSound.playClip();
            }
        }
        
        if (p.getPosition()==this.boardSize){
            this.gameStatus=2;
        }
    }
    
    public void movePlayer(Player p, int x){
        this.gameStatus=1;
        p.moveAround(x, this.boardSize);

        this.checkStatusPlayer(p);
    }

    // evan 10
    public String getTileType(int position) {
        for(Ladder l: this.ladders){
            if(l.getFromPosition() == position){
                return TileNames.ladder;
            }
        }
        
        for(Snake s: this.snakes){
            if(s.getHead() == position){
                return TileNames.snake;
            }
        }

        return TileNames.tile;
    }


    // evan 11
    public void printBoard() {
        final int spacesPerTile = 6;
        int currentTileNumber = boardSize;
        for (int y = 0; y < boardSideLength; y++) {
            for (int x = 0; x < boardSideLength; x++) {
                
                int currentTile = currentTileNumber;
                
                if (y % 2 == 1) {
                    currentTile = currentTileNumber + x - (boardSideLength - x) + 1;
                }
                String tileType = getTileType(currentTile);

                int spacesBetweenTile = spacesPerTile;

                //11.1
                boolean thereIsPlayer = false;
                ArrayList<Player> playersInTile = new ArrayList<Player>();
                for (Player player : players) {
                    if (player.getPosition() == currentTile) {
                        playersInTile.add(player);
                        thereIsPlayer = true;
                    }
                }

                //11.2
                for (Player player : playersInTile) {
                    int playerNameLength = spacesPerTile / playersInTile.size() - 1;

                    if (player.getName().length() < playerNameLength) {
                        playerNameLength = player.getName().length();
                    }

                    String playerName = player.getName().substring(0, playerNameLength);
                    System.out.print(player.getColour() + playerName + Pallette.ANSI_RESET);
                    spacesBetweenTile -= playerNameLength;
                }
                
                //11.3
                if (!thereIsPlayer) {
                    int digitsOfNumber = Functions.getDigits10(currentTile);
                    spacesBetweenTile = spacesBetweenTile - digitsOfNumber;
                    switch (tileType) {
                        case TileNames.snake:
                            System.out.print(Pallette.ANSI_RED + currentTile);
                            break;
                        case TileNames.ladder:
                            System.out.print(Pallette.ANSI_GREEN + currentTile);    
                            break;
                        default:
                            System.out.print(currentTile);
                            break;
                    }
                }
                Functions.printLoop(" ", spacesBetweenTile);
                System.out.print(Pallette.ANSI_RESET);

                currentTileNumber--;
            }
            System.out.println("\n");
        }
    }
    
}


    
