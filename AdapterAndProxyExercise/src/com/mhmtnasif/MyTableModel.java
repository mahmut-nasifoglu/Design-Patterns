package com.mhmtnasif;

import javax.swing.table.AbstractTableModel;


class MyTableModel extends AbstractTableModel {

    private String[] columnNames = {"İndex", "Value"};
    private Data data;
    public Object[][] values;

    public MyTableModel(Base base) {
        data = (Data) base.getElementAt(base.getIndexOfAr());
        values = new Object[data.size()][2];
        deneme();
    }

    public void deneme() {
        for (int i = 0; i < data.size(); i++) {
            values[i][0] = i;
            values[i][1] = data.get(i);
        }
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
        data.set(rowIndex, Integer.parseInt(aValue.toString()));
        values[rowIndex][columnIndex] = Integer.parseInt(aValue.toString());
        fireTableStructureChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    @Override
    public int getRowCount() {
        return values.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return values[row][column];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        } else {
            return true;
        }
    }

}
