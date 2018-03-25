package model;

import gameinterface.Location;

public class MyLocation implements Location {
    private int row;
    private int col;

    public MyLocation(int row, int col) {//row,col start from 0
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }
}
