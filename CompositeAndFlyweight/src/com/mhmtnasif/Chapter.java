package com.mhmtnasif;

import java.util.LinkedList;
import java.util.List;

public class Chapter implements PrintMethodInterface {

    List<PrintMethodInterface> paragraph=new LinkedList<>();
    @Override
    public void print(Object param) {
        for (PrintMethodInterface p:getParagraph()) {
            p.print(param);
        }
    }
    public void  addParagraph(PrintMethodInterface paragraph){
        getParagraph().add(paragraph);
    }

    public List<PrintMethodInterface> getParagraph() {
        return paragraph;
    }
}
