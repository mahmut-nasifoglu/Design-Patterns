package com.mhmtnasif;

import javax.swing.*;
import java.awt.*;


class Fly {
    private State state;
    private final double k = 0.01;
    private double x, y; // fly's position
    private double vx, vy; // fly's velocity
    private int temp = 0;
    private int time = 0;

    public Fly() {
        x = Math.random();
        y = Math.random();
        vx = k * (Math.random() - Math.random());
        vy = k * (Math.random() - Math.random());
        setState(new FlyNormalState());
    }

    public void draw(Graphics g) {
        if (getState().getClass().getSimpleName().equals(FlyFrozenState.class.getSimpleName()))
            g.setColor(Color.yellow);
        else if (time > 0)
            g.setColor(Color.red);
        else
            g.setColor(Color.black);
        Rectangle rc = g.getClipBounds();
        int a = (int) (x * rc.getWidth()),
                b = (int) (y * rc.getHeight());
        g.fillOval(a, b, 5, 5);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        System.out.println(state.getClass().getSimpleName());
    }

    private class FlyNormalState implements State {

        @Override
        public void move() {
            if (time > 0) {
                x += vx / 2;
                y += vy / 2;
                --time;
            } else {
                x += vx;
                y += vy;
            }
            if (x < 0) {
                x = -x;
                vx = -vx;
                time += 100;
                setState(new FlyDizzyState());
            }
            if (x > 1) {
                x = 2 - x;
                vx = -vx;
                time += 100;
                setState(new FlyDizzyState());
            }
            if (y < 0) {
                y = -y;
                vy = -vy;
                time += 100;
                setState(new FlyDizzyState());
            }
            if (y > 1) {
                y = 2 - y;
                vy = -vy;
                time += 100;
                setState(new FlyDizzyState());
            }
        }
    }

    private class FlyDizzyState implements State {
        public FlyDizzyState() {
            temp = time;
        }

        @Override
        public void move() {
            if (time > 0) {
                x += vx / 2;
                y += vy / 2;
                --time;
            } else {
                setState(new FlyFrozenState());
                x += vx;
                y += vy;
            }
            if (x < 0) {
                x = -x;
                vx = -vx;
                time += 100;
            }
            if (x > 1) {
                x = 2 - x;
                vx = -vx;
                time += 100;
            }
            if (y < 0) {
                y = -y;
                vy = -vy;
                time += 100;
            }
            if (y > 1) {
                y = 2 - y;
                vy = -vy;
                time += 100;
            }
        }
    }

    private class FlyFrozenState implements State {
        @Override
        public void move() {
            if (temp > 0) {
                --temp;
            } else {
                setState(new FlyNormalState());
            }
        }
    }
}
interface State {
    public void move();
}
public class FlyStates extends JPanel implements Runnable {
    private Fly[] ar;

    public FlyStates() {
        this.setPreferredSize(new Dimension(640, 480));
        ar = new Fly[30];
        for (int i = 0; i < ar.length; ++i)
            ar[i] = new Fly();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < ar.length; ++i)
            ar[i].draw(g);
    }

    public void run() {
        while (true) {
            for (int i = 0; i < ar.length; ++i)
                ar[i].getState().move();
            repaint();
            try {
                Thread.currentThread().sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlyStates fs = new FlyStates();
        frame.getContentPane().add(fs);
        frame.pack();
        frame.setVisible(true);
        new Thread(fs).start();
    }
}

