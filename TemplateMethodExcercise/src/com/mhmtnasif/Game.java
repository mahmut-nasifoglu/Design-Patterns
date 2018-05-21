package com.mhmtnasif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class Game extends JFrame {
    private Board board;
    MyButton buttons[][];
    private class MyActionListener implements ActionListener {
        private void check() {
            int ckeck=board.checkWon();
            switch (ckeck) {
                case Board.COMPUTER:
                    JOptionPane.showMessageDialog(null, "Computer won !");
                    System.exit(0);
                    break;
                case Board.PLAYER:
                    JOptionPane.showMessageDialog(null, "You won !");
                    System.exit(0);
                    break;
                case Board.BLOCKED:
                    JOptionPane.showMessageDialog(null, "Nobody won !");
                    System.exit(0);
                    break;
            }
        }
        public void actionPerformed(ActionEvent evt) {
            MyButton bt=(MyButton)(evt.getSource());
            bt.setText(" O ");
            board.set(bt.row, bt.col, Board.PLAYER);
            bt.removeActionListener(this);
            check();
            Move m=strategy();
            bt=buttons[m.row][m.col];
            bt.setText(" X ");
            bt.removeActionListener(this);
            board.set(bt.row, bt.col, Board.COMPUTER);
            check();
        }
    }
    public Game(Board b) {
        board=b;
        int s=board.getSize();
        buttons=new MyButton[s][s];
        ActionListener al=new MyActionListener();
        getContentPane().setLayout(new GridLayout(s,s));
        for (int i=0;i<s;++i)
            for (int j=0;j<s;++j) {
                buttons[i][j]=new MyButton("   ", i, j);
                buttons[i][j].addActionListener(al);
                add(buttons[i][j]);
            }
        pack();
    }

    public abstract Move calculateNextMove(Board b);

    public final Move strategy(){
        return calculateNextMove(board);
    }
}
