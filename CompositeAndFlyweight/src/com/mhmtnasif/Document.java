package com.mhmtnasif;

import java.util.LinkedList;
import java.util.List;

public class Document implements PrintMethodInterface {
    List<PrintMethodInterface> chapter=new LinkedList<>();

    @Override
    public void print(Object param) {
        for (PrintMethodInterface p:getChapter()) {
            p.print(param);
        }
    }
    public void  addChapter(PrintMethodInterface chapter){
        getChapter().add(chapter);
    }

    public List<PrintMethodInterface> getChapter() {
        return chapter;
    }

}
