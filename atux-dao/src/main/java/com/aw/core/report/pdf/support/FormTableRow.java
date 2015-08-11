package com.aw.core.report.pdf.support;

import com.aw.core.exception.AWSystemException;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 01/10/2009
 */
public class FormTableRow {
    private FormColumnInfo[] columnsInfo;
    private List<FormCell> cells = new ArrayList();
    private FormCell currentCell;
    private int totalColumnsFilled = 0;
    private int rowIdx = -1;


    public FormTableRow(FormColumnInfo[] columnInfos) {
        this.columnsInfo = columnInfos;
    }

    public boolean isFull() {
        return cells.size() == columnsInfo.length || getIndexForFollowingFormColumn() == -1;
    }


    public FormTableRow add(FormCell formCell) {
        if (formCell.hasPosition()) {
            throw new AWSystemException("Cell with position set is not Supported yet", null);
        }
        addInternal(formCell);
        return this;
    }

    private void addInternal(FormCell formCell) {
        int columnIdx = cells.size();
        int formColumnIdx = getIndexForFollowingFormColumn();
        for (int i = columnIdx; i < formColumnIdx; i++) {
            addCell(new SeparatorCell(i));
        }
        addCell(formCell.setColumn(formColumnIdx));
        int colSpan = formCell.getColSpan();
        for (int i = 1; i < colSpan; i++) {
            addCell(new EmptyCell(formColumnIdx + i));
        }
        if (cells.size() > columnsInfo.length) {
            throw new AWSystemException("Cells size is greater than columns size<" + cells.size() + "," + columnsInfo.length + ">", null);
        }
        formColumnIdx = getIndexForFollowingFormColumn();
        if (formColumnIdx == -1) {
            fillRow();
        }
    }

    private void fillRow() {
        int columnIdx = cells.size();
        for (int i = columnIdx; i < columnsInfo.length; i++) {
            addCell(new SeparatorCell(i));
        }
    }

    private void addCell(FormCell cell) {
        cell.setRow(rowIdx);
        cells.add(cell);
    }

    private int getIndexForFollowingFormColumn() {
        int initIdx = cells.size();
        for (int i = initIdx; i < columnsInfo.length; i++) {
            FormColumnInfo formColumnInfo = columnsInfo[i];
            if (!(formColumnInfo instanceof SeparatorFormColumnInfo)) {
                return i;
            }
        }
        return -1;
    }


    public FormCell getNextCell() {
        if (totalColumnsFilled == columnsInfo.length) {
            return null;
        }
        if (currentCell == null) {
            currentCell = cells.get(0);
        } else {
            currentCell = getNextCellFrom(currentCell);
        }
        totalColumnsFilled += currentCell.getColSpan();
        return currentCell;
    }

    private FormCell getNextCellFrom(FormCell formCell) {
        int nextCellIdx = formCell.getColumn() + currentCell.getColSpan();
        return cells.get(nextCellIdx);
    }

    public List<FormCell> getCells() {
        return cells;
    }

    public int getRowIdx() {
        return rowIdx;
    }

    public void setRowIdx(int rowIdx) {
        this.rowIdx = rowIdx;
    }
}