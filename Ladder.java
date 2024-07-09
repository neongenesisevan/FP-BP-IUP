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

public class Ladder{
    
    int fromPosition;
    int toPosition;
    
    Ladder(int from, int to){
        this.fromPosition = from;
        this.toPosition = to;
    }
    
    void setFromPosition(int from){
        this.fromPosition =  from;
        
    }
    
    void setToPosition(int to){
        this.toPosition = to;
        
    }
    
    int getFromPosition(){
        return fromPosition;
        
    }
    int getToPosition(){
        return toPosition;
    
    }
}
