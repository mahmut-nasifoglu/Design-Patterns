package com.mhmtnasif;

import java.util.*;

public class PrototypeDatabase {
    private Map<Integer, List<Integer>> data;
    private List<Integer> prototype;

    public PrototypeDatabase(List<Integer> prototype) {
        this.prototype = prototype;
        this.data = new HashMap<Integer, List<Integer>>();
    }


    public void put(int number1, int number2) {
        if (data.containsKey(number1)) {
            List<Integer> temp = data.get(number1);
            temp.add(number2);
            data.put(number1, temp);
        } else {
            List newList =clone();
            newList.add(number2);
            data.put(number1, newList);
        }
    }

    public List clone() {
        try {
            List newList = (List) (prototype.getClass().getMethod("clone", new Class[0]).invoke(prototype, new Object[0]));
            return newList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            System.out.print("This the number of pair did not found on database");
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
}

class PrototypeDatabaseTest {
    public static void main(String[] args) {
        PrototypeDatabase prototypeDatabase=new PrototypeDatabase(new ArrayList<Integer>());
        prototypeDatabase.put(1,1);
        prototypeDatabase.printMap();
        prototypeDatabase.put(1,2);
        prototypeDatabase.printMap();
        prototypeDatabase.put(2,2);
        prototypeDatabase.printMap();
        prototypeDatabase.put(2,1);
        prototypeDatabase.printMap();
        System.out.println("-------------");
        prototypeDatabase.remove(2,2);
        prototypeDatabase.printMap();
        System.out.println("-------------");
        prototypeDatabase.remove(1,2);
        prototypeDatabase.printMap();
        System.out.println(prototypeDatabase.contains(1,1));
        System.out.println(prototypeDatabase.contains(2,1));
        System.out.println(prototypeDatabase.contains(1,2));





    }
}