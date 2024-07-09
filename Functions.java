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

import java.util.InputMismatchException;
import java.util.Scanner;

 // evan 1.
public class Functions {
    public static void printLoop(String input, int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.print(input);
        }
    }

    public static int getDigits10(int input) {
        return (int) Math.log10(input) + 1;
    }

    public static int randInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static void startGame() {

        Scanner sc = new Scanner(System.in);

        //  evan
        String boardMode = "";
        int boardSize = 100;
        boolean isCustom = false;
        //1.1
        while (true) {
            System.out.println("Do you want to use the default 10x10 board with preset tiles, or create a custom one. (custom/default)");
            boardMode = sc.nextLine();

            if (boardMode.equalsIgnoreCase("custom")) {
                isCustom = true;
                break;
            } else if (boardMode.equalsIgnoreCase("default")) {
                break;
            } else {
                System.out.println("Please enter custom or default!");
            }
        }

        // setting board size 1.2
        if (isCustom) {
            while (true) {
                try {
                    System.out.println("Please enter the size of the board (min 64, max 961)");
                    boardSize = sc.nextInt();
                    sc.nextLine();
        
                    if (boardSize < 64 || boardSize > 961) {
                        System.out.println("Board size outside of boundary!");
                        continue;
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a correct input!");
                }
            }
        }

        //1.3
        SnL g1 = new SnL(boardSize, !isCustom);

        // setting the tile distribution probability
        if (isCustom) {
        String generationPresetString = "";
            while (true) {
                try {
                    System.out.println("Please enter the probabilty weights of tiles, separated with comma (snake, ladder). Default (.1,.1). Max (.4,.4)");
                    generationPresetString = sc.nextLine();

                    if (generationPresetString.isEmpty()) {
                        generationPresetString = ".1,.1";
                    }

                    //1.4
                    String presetsString [] = generationPresetString.split(",");

                    g1.setSnakeProbability(Math.max(Math.min(Double.parseDouble(presetsString[0]), .5), 0));
                    g1.setLadderProbability(Math.max(Math.min(Double.parseDouble(presetsString[1]), .5), 0));
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a correct input!");
                }
            }    
        }

        g1.play();
    }

    public static void printHistory() {
        Scanner scanner = new Scanner(Functions.class.getResourceAsStream("logs.txt"));

        System.out.println("---------------------HISTORY---------------------\n");

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

        System.out.println("\n");
    }

    // satria
    public static void wait(int amount) {
        try {
            Thread.sleep(amount);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
 }
