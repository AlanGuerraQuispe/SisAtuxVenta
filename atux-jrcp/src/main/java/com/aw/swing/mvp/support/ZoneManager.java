package com.aw.swing.mvp.support;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.ui.AWJTabbedPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: gmc
 * Date: 16-ago-2007
 * Class used to manage the focus movement in the view
 */
public class ZoneManager {

    protected Presenter presenter;
    protected JTabbedPane currentJcmpParent;
    protected List<Zone> zones = new ArrayList();
    protected List<JTabbedPane> parents = new ArrayList();
    protected boolean initialized = false;


    public ZoneManager(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setCurrentJcmpParent(JTabbedPane currentJcmpParent) {
        this.currentJcmpParent = currentJcmpParent;
        parents.add(currentJcmpParent);
    }

    public Zone registerZone(JPanel jPanel, String name) {
        Zone zone = new Zone(presenter, currentJcmpParent, jPanel, name);
        zones.add(zone);
        return zone;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void decorateParents() {
        for (JTabbedPane parent : parents) {
            AWJTabbedPane.configureKeyActions(parent);
        }
    }

    public void relocateSelectedChildOnParents() {
        for (JTabbedPane parent : parents) {
            Component cmp = parent.getSelectedComponent();
            int searchFromCmpIdx = -1;
            for (int i = 0; i < zones.size(); i++) {
                Zone zone = zones.get(i);
                if (zone.getJPanel() == cmp) {
                    if (!zone.isEnabled()) {
                        searchFromCmpIdx = i;
                        break;
                    }
                }
            }
            if (searchFromCmpIdx != -1) {
                for (int i = 0; i < zones.size(); i++) {
                    Zone zone = zones.get(i);
                    if (zone.getParent() == parent) {
                        if (zone.isEnabled()) {
                            parent.setSelectedComponent(zone.getJPanel());
                            break;
                        }
                    }
                }
            }
        }
    }

    public void init() {
        initialized = true;
        List<Zone> zones = getZones();
        for (Zone zone : zones) {
            zone.setInitialized(true);
            zone.updateUIStatus();
        }
        decorateParents();
        relocateSelectedChildOnParents();
    }
}