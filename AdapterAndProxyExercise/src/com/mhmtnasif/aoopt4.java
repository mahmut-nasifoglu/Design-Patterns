package com.mhmtnasif;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


class RealData implements Data {

    private int size;
    public List<Integer> integerList = new ArrayList<Integer>();


    public RealData(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            integerList.add(0);
        }
    }


    @Override
    public int get(int idx) {
        return integerList.get(idx);
    }

    @Override
    public void set(int idx, int value) {
        integerList.set(idx, value);

    }

    @Override
    public int size() {
        return size;
    }
}

class RealDataProxy implements Data {
    RealData realData;
    int size;

    public RealDataProxy(int size) {
        this.size = size;
    }

    @Override
    public int get(int idx) {
        return 0;
    }

    @Override
    public void set(int idx, int value) {
        if (realData == null) {
            realData = new RealData(size);
            realData.set(idx, value);
        }


    }

    @Override
    public int size() {
        return size;
    }
}

// database - collection of Data
class Base extends AbstractListModel {
    private ArrayList<Data> ar = new ArrayList<Data>();
    private int indexOfAr;


    public int getIndexOfAr() {
        return indexOfAr;
    }

    public void setIndexOfAr(int indexOfAr) {
        this.indexOfAr = indexOfAr;
    }

    public void add(Data tab) {
        ar.add(tab);
        fireIntervalAdded(ar, 0, getSize());
    }

    public void remove(int idx) {
        ar.remove(idx);
        fireIntervalRemoved(ar, 0, getSize());
    }

    @Override
    public int getSize() {
        return ar.size();
    }

    @Override
    public Object getElementAt(int index) {
        return ar.get(index);
    }

}


public class aoopt4 {

    public static void main(String[] args) {

        final Base base = new Base();

        final JFrame frame = new JFrame("Exercise 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JSplitPane splitPane = new JSplitPane();

        final JList list = new JList(base);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Tables: "));
        splitPane.setLeftComponent(scrollPane);
        Data realDataProxy=new RealDataProxy(10);
        base.add(realDataProxy);
        JTable table = new JTable(new MyTableModel(base));
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Content: "));
        splitPane.setRightComponent(scrollPane);

        frame.getContentPane().add(splitPane);

        JMenuBar bar = new JMenuBar();
        JButton add = new JButton("Add a table");
        JButton del = new JButton("Remove the table");
        bar.add(add);
        bar.add(del);

        frame.setJMenuBar(bar);

        frame.setSize(600, 450);
        frame.setVisible(true);

        splitPane.setDividerLocation(0.5);

        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String value = JOptionPane.showInputDialog(frame,
                        "Give the table size",
                        "Add",
                        JOptionPane.INFORMATION_MESSAGE);
                try {
                    int size = Integer.parseInt(value);
                    Data realDataProxy=new RealDataProxy(size);
                    base.add(realDataProxy);
                    System.out.println(base.getSize());
                } catch (Exception ex) {
                }
                ;
            }
        });
        del.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int idx = list.getSelectedIndex();
                try {
                    base.remove(idx);
                    System.out.println(base.getSize());
                } catch (Exception ex) {
                }
                ;
            }
        });

        // change on the list will refresh the table
        JScrollPane finalScrollPane = scrollPane;
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int idx = list.getSelectedIndex();
                if (idx >= 0) {
                    base.setIndexOfAr(idx);
                    finalScrollPane.getViewport().add(new JTable(new MyTableModel(base)));
                }
            }
        });
    }
}