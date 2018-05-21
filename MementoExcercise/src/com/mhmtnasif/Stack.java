package com.mhmtnasif;

import java.util.ArrayList;

public class Stack {

    private ArrayList<Integer> stackData = new ArrayList<>();
    private ArrayList<Memento> firstListOfMementos = new ArrayList<>();
    private ArrayList<Memento> secondListOfMementos = new ArrayList<>();

    public void push(Integer data) {
        firstListOfMementos.add(createMemento());
        stackData.add(data);
        secondListOfMementos.clear();
    }

    public void pop() {
        firstListOfMementos.add(createMemento());
        stackData.remove(stackData.size() - 1);
        secondListOfMementos.clear();
    }


    public void undo() {
        secondListOfMementos.add(createMemento());
        stackData.clear();
        stackData = firstListOfMementos.get(firstListOfMementos.size() - 1).getStackData();
        firstListOfMementos.remove(firstListOfMementos.size() - 1);
    }

    public void redo() {
        if (!secondListOfMementos.isEmpty()) {
            firstListOfMementos.add(createMemento());
            stackData.clear();
            stackData = secondListOfMementos.get(secondListOfMementos.size() - 1).getStackData();
            secondListOfMementos.remove(secondListOfMementos.size() - 1);
        }

    }

    public Memento createMemento() {
        return new Memento((ArrayList<Integer>) stackData.clone());
    }

    public void print() {
        System.out.println();
        for (Integer a : stackData) {
            System.out.print(a + " ");
        }
    }
}

class Memento {

    private ArrayList<Integer> stackData;

    public Memento(ArrayList<Integer> stackData) {
        this.stackData = stackData;
    }

    public ArrayList<Integer> getStackData() {
        return stackData;
    }

}
