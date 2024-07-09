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

public class Snake{
    int head;
    int tail;
    
    Snake(int head, int tail){
        this.head = head;
        this.tail = tail;
    }
    
    void setTail(int tail){
        this.tail = tail;
    }
    void setHead(int head){
        this.head = head;
    }
    int getTail(){
        return this.tail;
    }
    int getHead(){
        return this.head;
    }
}
