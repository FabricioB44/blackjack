package com.pluralsight;

import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int playerNum;
        while (true) {
            try {
                System.out.print("Number of players: ");
                playerNum = Integer.parseInt(myScanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again!");
            }
        }

        String[] playerNames = new String[playerNum];
        Hand[] playerHands = new Hand[playerNum];
        System.out.println();


        for (int i = 0; i < playerNum; i++){
            System.out.printf("Player %d Name: ", i+1);
            playerNames[i] = myScanner.nextLine();
            playerHands[i] = new Hand();
        }

        Deck deck = new Deck();
        deck.shuffle();

        for (int round = 0; round < 2; round++) {
            for (int i = 0; i < playerNum; i++) {
                playerHands[i].deal(deck.deal());
            }
        }

        int[] handValues = new int[playerNum];

        for (int i = 0; i < playerNum; i++){
            handValues[i] = playerHands[i].getValue();
            System.out.println();
            System.out.print(playerNames[i] + "'s ");
            playerHands[i].print();
            System.out.println("Total amount in hand: " + playerHands[i].getValue());
        }

        for (int i = 0; i < playerNum; i++){
            while (true) {
                try {
                    System.out.println();
                    System.out.print(playerNames[i] + " would you like to hit or stand? (H/S):  ");
                    String playerAnswer = myScanner.nextLine();
                    if (playerAnswer.equalsIgnoreCase("H")){
                        System.out.println("Choose to hit\n");
                        Card newCard = deck.deal();
                        System.out.println(newCard.toString());
                        System.out.println();
                        playerHands[i].deal(newCard);
                        System.out.print(playerNames[i] + "'s ");
                        playerHands[i].print();
                        int totalAmount = playerHands[i].getValue();
                        System.out.println("\nTotal amount in hand: " + totalAmount);
                        if (totalAmount > 21) {
                            System.out.println(playerNames[i] + " busted!");
                            break;
                        }
                    } else if (playerAnswer.equalsIgnoreCase("S")) {
                        System.out.println("Choose to stand");
                        break;
                    } else {
                        System.out.println("Invalid input, try again!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, try again!");
                }
            }
        }

        System.out.println();
        int winnerIndex = getClosestTo21(handValues);
        System.out.printf("%s wins with %d points!", playerNames[winnerIndex], playerHands[winnerIndex].getValue());
    }

    public static Integer getClosestTo21(int[] handValues) {
        int closestTo21 = 0;
        int winnerIndex = -1;
        for (int i = 0; i < handValues.length; i++) {
            if (handValues[i] <= 21) {
                if (closestTo21 == 0 || handValues[i] > closestTo21) {
                    closestTo21 = handValues[i];
                    winnerIndex = i;
                }
            }
        }
        return winnerIndex;
    }
}
