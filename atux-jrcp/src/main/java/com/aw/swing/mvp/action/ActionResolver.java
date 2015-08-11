/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp.action;

import com.aw.support.collection.ListUtils;
import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.action.key.AWActionKeyManager;
import com.aw.swing.mvp.action.types.ReflectionAction;
import com.aw.swing.mvp.grid.GridProvider;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * User: gmc
 * Date: 11-sep-2007
 */
public class ActionResolver {
    protected Log logger = LogFactory.getLog(getClass());
    protected Presenter presenter;
    protected Map<ActionIdentifier, Action> actions = new HashMap();

    protected Object currentTarget;


    public ActionResolver(Presenter presenter) {
        this.presenter = presenter;
        currentTarget = presenter;
    }

    public boolean containsActionFor(ActionIdentifier actionIdentifier) {
        return actions.containsKey(actionIdentifier);
    }

    public Action getAction(ActionIdentifier actionIdentifier) {
        return (Action) actions.get(actionIdentifier);
    }

    public void setCurrentTarget(Object currentTarget) {
        this.currentTarget = currentTarget;
    }

    public ActionConfig registerAction(String actionCmd, String methodName) {
        return registerAction(actionCmd, currentTarget, methodName);
    }

    public ActionConfig registerAction(String actionCmd, Object target, String methodName) {
        settingValuesOnTarget(target);
        Action action = new ReflectionAction(target, methodName);
        return registerAction(actionCmd, action);
    }

    private void settingValuesOnTarget(Object target) {
//        if (!(target instanceof Presenter) && !(target instanceof Pick) && !(target instanceof PresenterMgr)) {
//            AttributeAccessor.set(target, Constants.PST, presenter);
//            AttributeAccessor.set(target, Constants.BKN, presenter.getBackBean());
//            AttributeAccessor.set(target, Constants.DLG, presenter.getViewMgr().getViewSrc());
//        }
    }

    public <E extends Action> E registerAction(String actionCmd, E action) {
        ActionIdentifier actionIdentifier = ActionIdentifier.getActionIdentifier(actionCmd);
        return registerAction(actionIdentifier, action);
    }

    private <E extends Action>  E registerAction(ActionIdentifier actionIdentifier, E action) {
        //settingValuesOnTarget(action); //JCM adicionado
        actions.put(actionIdentifier, action);
        action.setPst(presenter);
        action.setId(actionIdentifier);
        if (action.getAdditionalMethodsTarget() == null) {
            action.setAdditionalMethodsTarget(currentTarget);
        }
        return action;
    }


    /**
     * @param actionCmd
     * @param methodName If this contains "(" then it will be asumme that the method must
     *                   receive the selected row as parameter
     * @param gdp
     * @return
     */
    public ActionConfig registerAction(String actionCmd, String methodName, GridProvider gdp) {
        return registerAction(actionCmd, currentTarget, methodName, gdp);
    }

    public ActionConfig registerAction(String actionCmd, Object target, String methodName, final GridProvider gdp) {
        settingValuesOnTarget(target);
        Action action = new ReflectionAction(target, methodName);
        return registerAction(actionCmd, action, gdp);
    }

    public ActionConfig registerAction(String actionCmd, Action action, GridProvider gdp) {
        settingValuesOnTarget(currentTarget);
        ActionIdentifier actionIdentifier = ActionIdentifier.getActionIdentifier(actionCmd, gdp.getGridIndex());
        return registerAction(actionIdentifier, action);
    }


    public Action getDefaultActionFor(GridProvider gridProvider) {
        List actions = getActionsFor(gridProvider);
        for (int i = 0; i < actions.size(); i++) {
            Action action = (Action) actions.get(i);
            if (action.isDefaultAction()) {
                return action;
            }
        }
        return null;
    }

    public Action getDefaultPstAction() {
        Action defPresenterAction = null;
        Collection<Action> actions = this.actions.values();
        for (Action action : actions) {
            if (action == null || !action.isDefaultAction() || action.isGridAction()) continue;
            defPresenterAction = action;
        }
        return defPresenterAction;
    }

    public List getActionsFor(GridProvider gridProvider) {
        List gridActions = new ArrayList();
        Collection allActions = actions.values();
        for (Iterator iterator = allActions.iterator(); iterator.hasNext();) {
            Action action = (Action) iterator.next();
            if (action.isGridAction()) {
                if (action.getId().getGridIndex() == gridProvider.getGridIndex()) {
                    gridActions.add(action);
                }
            }
        }
        return gridActions;
    }

    public List<Action> getAllActions() {
        List<Action> allActionsList = new ArrayList();
        Collection allActions = actions.values();
        for (Iterator iterator = allActions.iterator(); iterator.hasNext();) {
            allActionsList.add((Action) iterator.next());
        }
        return allActionsList;

    }

    public List<Action> getMainActions() {
        List<Action> mainActions = new ArrayList();
        Collection allActions = actions.values();
        for (Iterator iterator = allActions.iterator(); iterator.hasNext();) {
            Action action = (Action) iterator.next();
            if (!(action.isGridAction())) {
                mainActions.add(action);
            }
        }
        return mainActions;
    }

    public Set<ActionIdentifier> getActionIdentifiers() {
        return actions.keySet();
    }

    public Map getActions() {
        return actions;
    }

    public void disableActions() {
        for (Iterator iterator = actions.values().iterator(); iterator.hasNext();) {
            Action action = (Action) iterator.next();
            action.setEnabled(false);
        }
    }

    public Action getActionToBeExecutedOnClickFor(GridProvider gridProvider) {
        List actions = getActionsFor(gridProvider);
        int actionIndex = ListUtils.indexOf(actions, new UnaryPredicate() {
            public boolean test(Object o) {
                Action action = (Action) o;
                return action.hasToBeExecutedOnClick();
            }
        });
        return (Action) (actionIndex != -1 ? actions.get(actionIndex) : null);
    }

    public void disableAllActionsExcept(List actionsToBeExcluded) {
        for (Iterator iterator = actions.values().iterator(); iterator.hasNext();) {
            Action action = (Action) iterator.next();
            if (actionsToBeExcluded.indexOf(action) == -1) {
                action.setEnabled(false);
            }
        }
    }

    public List<Action> getActionsOfType(Class clazz) {
        List<Action> allActionsOfType = new ArrayList();
        Collection allActions = actions.values();
        for (Iterator iterator = allActions.iterator(); iterator.hasNext();) {
            Action action = (Action) iterator.next();
            if (clazz.isInstance(action)){
                allActionsOfType.add(action);
            }
        }
        return allActionsOfType;
    }

    public void initializeKeyTriggers() {
        AWActionKeyManager.initializeKeyTriggersFor(getAllActions());            
    }

    public List getAllAlwaysEnabledActions() {
        List allAlwaysEnabledActions = new ArrayList();
        Collection<Action> allActions = actions.values();
        for (Action action : allActions) {
            if (action.isAlwaysEnabled()){
                allAlwaysEnabledActions.add(action);
            }
        }
        return allAlwaysEnabledActions;
    }
}
