package com.aw.swing.mvp.cmp.pick;

import java.util.Map;

/**
 * User: gmc
 * Date: 17/05/2009
 */
public interface PickListener<E> {
    /**
     * Calls after setting the back bean values
     * @param selectedObj   Selected row in the pick view
     * @param valuesSet     Values that were set in the back bean
     * @return
     */
    public Map valuesSet(E selectedObj, Map valuesSet);

}
