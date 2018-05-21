package com.mhmtnasif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

class Tile {
    // tile state
    private boolean value = false;
    // colors
    private static final Color on = new Color(0xffd700),
            off = new Color(0x1e90ff);

    public Color getColor() {
        return value ? on : off;
    }

    //change color
    public void flip() {
        value = !value;
    }
}

//tile matrix
class Board extends JPanel {
    private static int index;
    private Tile[][] matrix;
    private int tilesize;
    // higlighted (with mouse) tile
    private int hx = -1, hy = -1;

    // matrix initialization
    public Board(int cols, int rows, int tilesize) {
        this.setPreferredSize(new Dimension(cols * tilesize, rows * tilesize));
        this.tilesize = tilesize;
        matrix = new Tile[rows][cols];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                matrix[i][j] = new Tile();
            }
        }
    }

    // matrix drawing (including the highlighted tile)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (i == hy && j == hx) {
                    g.setColor(matrix[i][j].getColor().brighter());
                } else {
                    g.setColor(matrix[i][j].getColor());
                }
                g.fillRect(j * tilesize, i * tilesize + 1, tilesize - 1, tilesize - 1);
            }
        }
    }

    public void highlight(int x, int y) {
        hx = x;
        hy = y;
        repaint();
    }

    // intead - add the following method to get the iterator
    public Iterator<Tile> iterator(int currentIndexX, int currentIndexY) {
        if (index==0){
            index++;
            return new IteratorImpl(currentIndexX, currentIndexY);
        }else if (index==1){
            index++;
            if (index<=2){
                index=0;
            }
            return new IteratorImplReverse(currentIndexX, currentIndexY);
        }else{
          return null;
        }
    }

    private class IteratorImpl implements Iterator<Tile> {
        private int currentIndexX;
        private int currentIndexY;

        public IteratorImpl(int currentIndexX, int currentIndexY) {
            this.currentIndexX = currentIndexX;
            this.currentIndexY = currentIndexY;
        }

        @Override
        public boolean hasNext() {

            if (currentIndexX < matrix.length && currentIndexY < matrix[0].length) {
                return true;
            } else if (currentIndexX <= matrix.length && currentIndexY <= matrix[0].length) {
                currentIndexX++;
                currentIndexY = 0;
                if (currentIndexX < matrix.length)
                    return true;
                else
                    return false;
            } else {
                return false;
            }

        }

        @Override
        public Tile next() {
            if (currentIndexX < matrix.length) {
                if (currentIndexY < matrix[0].length) {
                    return matrix[currentIndexX][currentIndexY++];
                }
            }
            return null;
        }
    }

    private class IteratorImplReverse implements Iterator<Tile> {
        private int currentIndexX;
        private int currentIndexY;

        public IteratorImplReverse(int currentIndexX, int currentIndexY) {
            this.currentIndexX = currentIndexX;
            this.currentIndexY = currentIndexY;
        }

        @Override
        public boolean hasNext() {

            if (currentIndexX < matrix.length && currentIndexX >= 0) {
                if (currentIndexY < matrix[0].length && currentIndexY >= 0) {
                    return true;
                } else if (currentIndexX < matrix.length && currentIndexY ==-1) {
                    currentIndexX--;
                    currentIndexY = matrix[0].length-1;
                    if (currentIndexX<0)
                        return false;
                        else
                    return true;
                } else {
                    return false;
                }
            }else{
                return false;
            }

        }

        @Override
        public Tile next() {
            if (currentIndexX < matrix.length) {
                if (currentIndexY < matrix[0].length) {
                    return matrix[currentIndexX][currentIndexY--];
                }
            }
            return null;
        }
    }
}

// in this version this thread doesn't use the iterator - change it
class WorkerThread implements Runnable {

    private Board p;
    private int x, y;

    // x, y - initial position of iteration
    public WorkerThread(Board k, int x, int y) {
        this.p = k;
        this.x = x;
        this.y = y;
    }

    public void run() {
        Iterator<Tile> iterator = p.iterator(y, x);
        while (iterator.hasNext()) {
            iterator.next().flip();
            p.repaint();
            try {    //wait
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class SnakeIterator {

    static final int TILESIZE = 40;

    public static void main(String[] args) {

        // window construction
        JFrame frame = new JFrame("Iterator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Board Board = new Board(16, 9, TILESIZE);
        frame.getContentPane().add(Board);
        frame.pack();
        frame.setVisible(true);

        // mouse clicking starts the thread with iteration
        Board.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / TILESIZE;
                int y = e.getY() / TILESIZE;
                new Thread(new WorkerThread(Board, x, y)).start();
            }
        });
        // mouse moving - highlight a tile under the cursor
        Board.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX() / TILESIZE;
                int y = e.getY() / TILESIZE;
                Board.highlight(x, y);
            }
        });
    }
}