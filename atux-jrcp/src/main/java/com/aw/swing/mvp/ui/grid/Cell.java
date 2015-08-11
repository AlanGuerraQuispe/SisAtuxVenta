package com.aw.swing.mvp.ui.grid;

/**
 * User: gmc
 * Date: 06/06/2009
 */
public class Cell {
    protected int row;
    protected int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
