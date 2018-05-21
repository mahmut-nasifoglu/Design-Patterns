package com.mhmtnasif;

import java.lang.Character;
import java.util.LinkedList;
import java.util.List;


public class Paragraph implements PrintMethodInterface {
    List<PrintMethodInterface> character = new LinkedList<>();
    boolean[] bigAndSmallLetters;;
    public Paragraph(String paragraph) {
        bigAndSmallLetters=new boolean[paragraph.length()];
        for (int i = 0; i < paragraph.length(); i++) {
            getCharacter().add(CharacterPool.getCharacterPoolInstance().getCharacter(paragraph.charAt(i)));
            if (Character.isUpperCase(paragraph.charAt(i))){
                bigAndSmallLetters[i]=true;
            }else{
                bigAndSmallLetters[i]=false;
            }
        }
    }

    @Override
    public void print(Object param) {
        PrintMethodInterface p;
        for (int i=0;i<bigAndSmallLetters.length;i++){
             p=getCharacter().get(i);
             p.print(bigAndSmallLetters[i]);
        }


    }

    public void addCharacter(PrintMethodInterface character) {
        getCharacter().add(character);
    }

    public List<PrintMethodInterface> getCharacter() {
        return character;
    }

}
