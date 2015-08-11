package com.aw.swing.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: JCM
 * Date: 20/11/2007
 */
public class RptFilterSectionSimpleImpl extends RptFilterSection {
    List<RptJCmpsGroup> groups = new ArrayList<RptJCmpsGroup>();

    protected List<RptJCmpsGroup> getGroups() {
        return groups;
    }

    public boolean isEmptySection() {
        return groups.size()==0;
    }

    public RptFilterSectionSimpleImpl addFilter(RptFilterField... rptFilterFields) {
        RptJCmpsGroup group = new RptJCmpsGroup();
        group.getJCmps().addAll(Arrays.asList(rptFilterFields));
        groups.add(group);
        return this;
    }
}