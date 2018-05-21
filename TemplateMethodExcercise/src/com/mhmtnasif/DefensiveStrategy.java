package com.mhmtnasif;

class DefensiveStrategy extends Game {


    public DefensiveStrategy(Board b) {
        super(b);
    }

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
        int sayac = 0;
        int x = x0;
        int y = y0;
        for (int i = x0; i < x0 + b.getWin(); i++) {
            if (b.get(i, y0) == Board.FREE) {
                sayac = 0;
                for (int j = y0 + 1; j < y0 + b.getWin(); j++) {
                    if (b.get(i, j) == Board.PLAYER) {
                        sayac++;
                    } else {
                        break;
                    }
                }
                if (sayac == b.getWin() - 1) {
                    if (b.get(i, y0) == Board.FREE)
                        return new Move(i, y0);

                }
            } else if (b.get(i, y0) == Board.PLAYER) {
                sayac = 1;
                for (int j = y0 + 1; j < y0 + b.getWin(); j++) {
                    if (b.get(i, j) == Board.PLAYER) {
                        sayac++;
                    } else {
                        break;
                    }
                }
                if (sayac == b.getWin() - 1) {
                    if (b.get(i, y0 + b.getWin() - 1) == Board.FREE)
                        return new Move(i, y0 + b.getWin() - 1);
                }
            }
        }

        for (int i = y0; i < y0 + b.getWin(); i++) {
            if (b.get(x0, i) == Board.FREE) {
                sayac = 0;
                for (int j = x0 + 1; j < x0 + b.getWin(); j++) {
                    if (b.get(j, i) == Board.PLAYER) {
                        sayac++;
                    } else {
                        break;
                    }
                }
                if (sayac == b.getWin() - 1) {
                    if (b.get(x0, i) == Board.FREE)
                        return new Move(x0, i);
                }
            } else if (b.get(x0, i) == Board.PLAYER) {
                sayac = 1;
                for (int j = x0 + 1; j < x0 + b.getWin(); j++) {
                    if (b.get(j, i) == Board.PLAYER) {
                        sayac++;
                    } else {
                        break;
                    }
                }
                if (sayac == b.getWin() - 1) {
                    if (b.get(x0 + b.getWin() - 1, i) == Board.FREE)
                        return new Move(x0 + b.getWin() - 1, i);
                }
            }
        }

        if (b.get(x, y) == Board.FREE) {
            sayac = 0;
            for (int i = x0; i < x0 + b.getWin() - 1; i++) {
                if (b.get(x + 1, y + 1) == Board.PLAYER) {
                    sayac++;
                    x++;
                    y++;
                } else {
                    break;
                }

            }
            if (sayac == b.getWin() - 1) {
                if (b.get(x0, y0) == Board.FREE)
                    return new Move(x0, y0);
            }
        } else if (b.get(x, y) == Board.PLAYER) {
            sayac = 1;
            while (x < x + b.getWin()) {
                if (b.get(x + 1, y + 1) == Board.PLAYER) {
                    sayac++;
                    x++;
                    y++;
                } else {
                    break;
                }

            }
            if (sayac == b.getWin() - 1) {
                if (b.get(x0 + b.getWin() - 1, y0 + b.getWin() - 1) == Board.FREE)
                    return new Move(x0 + b.getWin() - 1, y0 + b.getWin() - 1);
            }
        }
        x = x0;
        y = y0 + b.getWin() - 1;
        if (b.get(x, y) == Board.FREE) {
            sayac = 0;
            for (int i = x; i < x + b.getWin() - 1; i++) {
                if (!((y - 1 < 0)) && !(x + 1 >= b.getSize())) {
                    if (b.get(x + 1, y - 1) == Board.PLAYER) {
                        sayac++;
                        x++;
                        y--;
                    } else {
                        break;
                    }

                }
            }
            if (sayac == b.getWin() - 1) {
                if (b.get(x0, y0 + b.getWin() - 1) == Board.FREE)
                    return new Move(x0, y0 + b.getWin() - 1);
            }
        } else if (b.get(x, y) == Board.PLAYER) {
            sayac = 1;
            for (int i = x; i < x + b.getWin() - 1; i++) {
                if (b.get(x + 1, y - 1) == Board.PLAYER) {
                    sayac++;
                    x++;
                    y--;
                } else {
                    break;
                }

            }
            if (sayac == b.getWin() - 1) {
                if (b.get(x0 + b.getWin() - 1, y0) == Board.FREE)
                    return new Move(x0 + b.getWin() - 1, y0);
            }
        }
        return null;

    }
}
