package com.mhmtnasif;

public class Test {
    public static void main(String[] args) {
        Stack s=new Stack();
        s.push(2);
        s.push(4);
        s.push(6);
        s.print();
        s.undo();
        s.print();
        s.undo();
        s.print();
        s.redo();
        s.print();
        s.push(10);
        s.print();
        s.redo();
        s.print();
        s.pop();
        s.print();
        s.undo();
        s.print();
        s.redo();
        s.print();
    }
}
