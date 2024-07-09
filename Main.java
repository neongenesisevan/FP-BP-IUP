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

public class Main {

    public static void main(String[] args) {

        //System.out.println(Files.exists(Paths.get("./sounds/sus.mp3"), LinkOption.NOFOLLOW_LINKS));

        System.out.println("Welcome to snakes and ladders.\n");

        Menu menu = new Menu();
        menu.selection();
    }
}
