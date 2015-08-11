package com.aw.swing.mvp.action.types;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.cmp.pick.PickInfo;
import com.aw.swing.mvp.cmp.pick.PickManager;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 20/05/2009
 */
public class CleanFilterAction extends Action {

    public CleanFilterAction() {
        execBinding = false;
        execValidation = false;
        useMessageBlocker = false;
    }

    protected Object executeIntern() throws Throwable {
        Presenter pst = getPst();
        List<BindingComponent> bindings = pst.getAllBindings();
        List<String> readOnlyPicks = new ArrayList();
        for (BindingComponent binding : bindings) {
            if (!binding.isUiReadOnly() && binding.getJComponent().isEnabled()) {
                binding.cleanJComponentValues();
            } else {
                String pickName = (String) binding.getJComponent().getClientProperty(PickManager.PICK_NAME);
                if (StringUtils.hasText(pickName)) {
                    readOnlyPicks.add(pickName);
                }
            }
        }
        List<PickInfo> picks = pst.getPickMgr().getPicksInfo();
        for (PickInfo pickInfo : picks) {
            if (readOnlyPicks.indexOf(pickInfo.getActionCmd()) == -1) {
                pickInfo.getPick().cleanPickedValues();
            }
        }
        return null;
    }
}
