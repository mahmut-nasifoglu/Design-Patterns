package com.mhmtnasif;

public class Character implements PrintMethodInterface {

    private char character;

    public Character(char character) {
        this.character = character;
    }

    @Override
    public void print(Object param) {
        if (((Boolean) param).booleanValue()) {
            System.out.print((char)(((int)character)-32));
        }else{
            System.out.print(character);
        }
    }
}
