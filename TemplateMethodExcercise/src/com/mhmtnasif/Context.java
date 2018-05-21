package com.mhmtnasif;

public class Context extends Game {

    public Context(Board b) {
        super(b);
    }

    @Override
    public Move calculateNextMove(Board board) {
        Move temp = new DefensiveStrategy(board).strategy();
        Move temp2 = new OffensiveStrategy(board).strategy();
        if (temp != null) {
            return temp;
        } else if (temp2 != null) {
            return temp2;
        } else {
            return new RandomStrategy(board).strategy();
        }

    }

}
