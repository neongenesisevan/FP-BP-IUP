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

import java.util.Scanner;

public class Menu {

    private void display() {
        System.out.println("\n\nMAIN MENU\n");
        System.out.println("(A): Start");
        System.out.println("(B): Show History");
        System.out.println("(Q): Exit");
    }

    public void selection() {
        Scanner sc = new Scanner(System.in);

        boolean doPlayMusic = true; // satria

        SoundPlayer menuSound = new SoundPlayer("./sounds/music/gymnopedie.wav"); 
        while (true) {
            this.display();

            if (doPlayMusic) {
                menuSound = new SoundPlayer("./sounds/music/gymnopedie.wav");  // satria
                menuSound.playContinuously(); // satria
            }

            doPlayMusic = true;

            String selection = sc.nextLine();
            switch (selection) {
                case "A":
                    menuSound.stopClip();
                    Functions.startGame();
                    break;
                case "B":
                    doPlayMusic = false;
                    Functions.printHistory();
                    break;
                case "Q":
                    System.exit(0);
                    break;
                default:
                    doPlayMusic = false;
                    break;
            }
        }
    }
 }
