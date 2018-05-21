package com.mhmtnasif;

import java.util.ArrayList;

public class Stack {

    private ArrayList<Integer> stackData = new ArrayList<>();
    private ArrayList<Command> undoCommandList = new ArrayList<>();
    private ArrayList<Command> redoCommandList = new ArrayList<>();

    public void push(Integer data) {
        stackData.add(data);
        undoCommandList.add(new PushCommand(data));
        redoCommandList.clear();
    }

    public  void  pop() {
        undoCommandList.add(new PopCommand(stackData.get(stackData.size()-1)));
        stackData.remove(stackData.size()-1);
        redoCommandList.clear();

    }


    public void undo() {
        undoCommandList.get(undoCommandList.size()-1).undo();
        redoCommandList.add(undoCommandList.get(undoCommandList.size()-1));
        undoCommandList.remove(undoCommandList.size()-1);

    }

    public void redo() {
        if (!redoCommandList.isEmpty()){
            redoCommandList.get(redoCommandList.size()-1).redo();
            undoCommandList.add(redoCommandList.get(redoCommandList.size()-1));
            redoCommandList.remove(redoCommandList.size()-1);
        }

    }

    public void print(){
        System.out.println();
        for (Integer a:stackData) {
            System.out.print(a+" ");
        }
    }




    class PopCommand implements Command {

        private Integer data;

        public PopCommand(Integer data) {
            this.data=data;
        }

        @Override
        public void undo() {
            stackData.add(data);
        }

        @Override
        public void redo() {
            stackData.remove(data);
        }
    }

    class PushCommand implements Command {

        private Integer data;

        public PushCommand(Integer data) {
            this.data = data;
        }

        @Override
        public void undo() {
            stackData.remove(data);
        }

        @Override
        public void redo() {
            stackData.add(data);
        }
    }

}
