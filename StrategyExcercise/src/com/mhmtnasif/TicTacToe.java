package com.mhmtnasif;


import javax.swing.*;

class NotFreeException extends RuntimeException {}






public class TicTacToe {
    public static void main (String[] args) {
        JFrame frame = new JFrame();
        String size = JOptionPane.showInputDialog(frame, "size of the board:");
        String win = JOptionPane.showInputDialog(frame, "length of the winning sequence :");
        Board board=new Board(Integer.parseInt(size),Integer.parseInt(win));
        new Game(board).setVisible(true);

    }
}