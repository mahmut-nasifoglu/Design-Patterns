package com.mhmtnasif;

import java.util.*;

public abstract class FMDatabase {
    private Map<Integer, List<Integer>> data;

    public FMDatabase() {
        data = new HashMap<Integer, List<Integer>>();
    }

    public void put(int number1, int number2) {
        if (data.containsKey(number1)) {
            List<Integer> temp = data.get(number1);
            temp.add(number2);
            data.put(number1, temp);
        } else {
            List newList = createList();
            newList.add(number2);
            data.put(number1, newList);
        }
    }

    public void remove(int number1, int number2) {
        if (contains(number1, number2)) {
            List<Integer> temp = data.get(number1);
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i) == number2) {
                    temp.remove(i);
                }
            }
            data.put(number1, temp);
        } else {
            System.out.println("This the number of pair did not found on database");
        }
    }

    public boolean contains(int number1, int number2) {
        if (data.isEmpty()) {
            return false;
        } else {
            List<Integer> temp;
            if (data.containsKey(number1)) {
                temp = data.get(number1);
                for (int a : temp) {
                    if (a == number2) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        }

    }

    public void printMap() {
        if (data.isEmpty()) {
            System.out.print("HashMap is empty");
        } else {
            for (Map.Entry entry : data.entrySet()) {
                System.out.println(entry.getKey() + ", " + entry.getValue());
            }
        }
    }


    protected abstract List createList();
}

class ALDatabase extends FMDatabase {

    @Override
    protected List createList() {

        return new ArrayList();
    }
}

class LLDatabase extends FMDatabase {
    @Override
    protected List createList() {
        return new LinkedList();
    }
}

class FMDatabaseTest {

    public static void main(String[] args) {
        FMDatabase fmDatabase = new ALDatabase();
        System.out.println(fmDatabase.contains(1, 1));
        fmDatabase.put(1, 1);
        System.out.println(fmDatabase.contains(1, 1));
        fmDatabase.put(2, 1);
        fmDatabase.put(2, 2);
        fmDatabase.printMap();
        fmDatabase.remove(2, 2);
        fmDatabase.printMap();
    }
}
