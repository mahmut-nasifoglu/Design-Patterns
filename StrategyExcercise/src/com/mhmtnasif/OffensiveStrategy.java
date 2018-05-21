package com.mhmtnasif;

public class OffensiveStrategy extends Strategy {
    @Override
    public Move calculateNextMove(Board b) {
        int iteration = b.getSize() - b.getWin();
        for (int i = 0; i <= iteration; i++) {
            for (int j = 0; j <= iteration; j++) {
                Move deneme = check(i, j, b);
                if (deneme == null)
                    continue;
                else
                    return check(i, j, b);

            }
        }
        return null;
    }

    public Move check(int x0, int y0, Board b) {
        int x = x0;
        int y = y0;
        boolean temp = true;
        for (int i = x0; i < x0 + b.getWin(); i++) {
            temp = true;
            for (int j = y0; j < y0 + b.getWin(); j++) {
                if (b.get(i, j) == Board.PLAYER) {
                    temp = false;
                    break;
                }
            }
            y=y0;
            while (temp && y < y0 + b.getWin()) {
                if (b.get(i, y) == Board.FREE) {
                    return new Move(i, y);
                } else {
                    y++;
                }
            }

        }
         x = x0;
         y = y0;
        for (int i = y0; i < y0 + b.getWin(); i++) {
            temp = true;
            for (int j = x0; j < x0 + b.getWin(); j++) {
                if (b.get(j, i) == Board.PLAYER) {
                    temp = false;
                    break;
                }
            }
            x=x0;
            while (temp && x < x0 + b.getWin()) {
                if (b.get(x, i) == Board.FREE) {
                    return new Move(x, i);
                } else {
                    x++;
                }
            }

        }

        for (int i = x0; i < x0 + b.getWin(); i++) {
            temp = true;
            if (b.get(x, y) == Board.PLAYER) {
                temp = false;
                break;
            } else {
                x++;
                y++;
            }

        }
        x = x0;
        y = y0;
        while (temp && x < x0 + b.getWin()) {
            if (b.get(x, y) == Board.FREE) {
                return new Move(x, y);
            } else {
                x++;
                y++;
            }
        }
        x = x0;
        y = y0 + b.getWin() - 1;
        for (int i = x; i < x + b.getWin(); i++) {
            temp = true;
            if (!((y - 1 < 0)) && !(x + 1 >= b.getSize())) {
                if (b.get(x + 1, y - 1) == Board.PLAYER) {
                    temp = false;
                    break;
                } else {
                    x++;
                    y--;
                }

            }
        }
        x = x0;
        y = y0 + b.getWin() - 1;
        while (temp && x < x0 + b.getWin()) {
            if (b.get(x, y) == Board.FREE) {
                return new Move(x, y);
            } else {
                x++;
                y--;
            }
        }
        return null;

    }
}