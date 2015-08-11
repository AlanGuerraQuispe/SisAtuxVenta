package com.aw.swing.mvp;

import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.binding.component.support.table.function.GridFunction;
import com.aw.swing.mvp.binding.component.support.table.function.GridFunctionModelListener;
import com.aw.swing.mvp.grid.GridProvider;

import java.util.List;

/**
 * User: gmc
 * Date: 20/07/2009
 */
public class FunctionFieldsManager {
    protected Presenter presenter;
    protected List<BndIJTextField> functionFields;

    public FunctionFieldsManager(Presenter presenter) {
        this.presenter = presenter;
    }

    public void init() {
        if (!existFunctionFields()) {
            return;
        }
        for (BndIJTextField functionField : functionFields) {
            init(functionField);
        }
    }

    private void init(BndIJTextField functionField) {
        GridFunction gridFunction = functionField.getGridFunction();
        int gridIdx = functionField.getGridFunctionIdx();
        GridProvider gridProvider = presenter.getGridProvider(gridIdx);
        gridFunction.setGdp(gridProvider);

        GridFunctionModelListener modelListener = new GridFunctionModelListener();
        modelListener.setBndIJTextField(functionField);

        gridProvider.getJTable().getModel().addTableModelListener(modelListener);
        // to force the function's execution the firs time 
        modelListener.tableChanged(null);
    }

    private boolean existFunctionFields() {
        return functionFields != null && functionFields.size() > 0;
    }

    public void setFunctionFields(List<BndIJTextField> functionFields) {
        this.functionFields = functionFields;
    }
}
