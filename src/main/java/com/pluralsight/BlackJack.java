package com.pluralsight;

import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Number of players: ");
        int playerNum = Integer.parseInt(myScanner.nextLine());

        String[] playerNames = new String[playerNum];
        Hand[] playerHands = new Hand[playerNum];


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
            System.out.println(playerHands[i].getValue());
        }

        int closest = getClosestTo21(handValues);
        System.out.println(closest);
    }

    public static Integer getClosestTo21(int[] handValues) {
        int closestTo21 = 0;
        for (int handValue : handValues) {
            if (handValue <= 21) {
                if (closestTo21 == 0 || handValue > closestTo21) {
                    closestTo21 = handValue;
                }
            }
        }
        return closestTo21;
    }
}
