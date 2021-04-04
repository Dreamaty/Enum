package com.javarush.task.task25.task2503;

import java.util.LinkedList;
import java.util.List;

public enum Column implements Columnable{
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * ЗSets a new display order, which stored in an array realOrder.
     * realOrder[index in enum] = display order; -1, if column not displayed.
     *
     * @param newOrder New order of column
     * @throws IllegalArgumentException if duplicate column
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Display list of Columns
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();
        for (int i = 0; i < realOrder.length ; i++) {
            if(realOrder[i] != -1)
                result.add(Column.values()[i]);
        }
        result.sort((column, t1) -> (realOrder[column.ordinal()] >= realOrder[t1.ordinal()])? 1: -1);
        return result;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public boolean isShown() {
        for (Column column :
                getVisibleColumns()) {
            if (this.equals(column)) return true;
        }
        return false;
    }

    @Override
    public void hide() {
        realOrder[this.ordinal()] = -1;
    }

}
