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

public class Player{
    //states
    private String name;
    private int position;
    private String colour;

    //satria
    private int points = 0;
    
    //constructor method
    public Player (String name, String col){
        this.name=name;
        this.colour =col;
    }
    //setter methods
    
    public void setName (String name){
      this.name=name;
    }
    
    public void setPosition(int position){
      this.position = position;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // satria
    public void addPoints() {
        this.points++;
    }
    
    //getter methods
    public String getName() {
        return this.name;
    }
    
    public int getPosition() {
        return this.position;
    }
    public String getColour() {
        return this.colour;
    }

    // satria
    public int getPoints() {
        return this.points;
    }
    
    //another method
    public int rollDice(int diceSides)
    {
        return (int)((Math.random()*diceSides)+1);
    }
    
    public void moveAround(int x, int boardSize)
    {
        if (this.position + x > boardSize){
          this.position = (boardSize - this.position) + (boardSize - x); 
        }
        else {
            this.position = this.position + x;
           
        }
    }
}
