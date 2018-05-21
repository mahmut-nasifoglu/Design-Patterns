package com.mhmtnasif;

public class Context {

    public Move executeStrategy(Board board) {
        Move temp = new DefensiveStrategy().calculateNextMove(board);
        Move temp2 = new OffensiveStrategy().calculateNextMove(board);
        if (temp != null) {
            System.out.println("defensıve");
            return temp;
        } else if (temp2 != null) {
            System.out.println("offensıce");
            return temp2;
        } else {
            System.out.println("random");
            return new RandomStrategy().calculateNextMove(board);
        }
    }
}
