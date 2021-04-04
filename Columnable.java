package com.javarush.task.task25.task2503;

public interface Columnable {


    String getColumnName();

    boolean isShown();


    void hide();

    /**
     * return: order constant in enum
     */
    int ordinal();
}
