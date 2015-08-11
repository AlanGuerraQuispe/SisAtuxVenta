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
package com.aw.swing.mvp;

import com.aw.core.business.Executer;
import com.aw.core.domain.AWBusinessException;
import com.aw.core.domain.model.UserInfo;
import com.aw.core.exception.AWException;
import com.aw.core.exception.AWSystemException;
import com.aw.core.util.TimeObserver;
import com.aw.core.view.ViewMode;
import com.aw.support.reflection.AttributeAccessor;
import com.aw.swing.SecurityHelper;
import com.aw.swing.mvp.action.Action;
import com.aw.swing.mvp.action.*;
import com.aw.swing.mvp.action.types.CancelAction;
import com.aw.swing.mvp.action.types.PickSelectItemAction;
import com.aw.swing.mvp.action.types.ShowAuditingInfoAction;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.BindingManager;
import com.aw.swing.mvp.binding.component.BndIJTextField;
import com.aw.swing.mvp.binding.component.support.MainAuditColumn;
import com.aw.swing.mvp.cmp.calendar.CalendarManager;
import com.aw.swing.mvp.cmp.pick.PickAction;
import com.aw.swing.mvp.cmp.pick.PickInfo;
import com.aw.swing.mvp.cmp.pick.PickManager;
import com.aw.swing.mvp.focus.AWFocusManager;
import com.aw.swing.mvp.focus.FocusZone;
import com.aw.swing.mvp.grid.GridProvider;
import com.aw.swing.mvp.grid.GridProviderManager;
import com.aw.swing.mvp.security.SecurityManager;
import com.aw.swing.mvp.support.ZoneManager;
import com.aw.swing.mvp.ui.common.ProcessMsgBlocker;
import com.aw.swing.mvp.ui.painter.PainterMessages;
import com.aw.swing.mvp.validation.ValidatorManager;
import com.aw.swing.mvp.validation.support.AWInputVerifier;
import com.aw.swing.mvp.view.IPView;
import com.aw.swing.mvp.view.ViewManager;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class used to manage the communication between the viev and the model.
 *
 * @author gmateo
 *         08/10/2004
 */
public class Presenter<E> {
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * View Manager used to process the actions related with the View.
     * For instance open, close
     */
    protected ViewManager viewMgr = new ViewManager(this);
    /**
     * In charge of managing the cursor's navigation between the components
     */
    //    protected NavigationManager navigationMgr = new NavigationManager(this);
    /**
     * It is in charge of managing the focus
     */
    protected AWFocusManager focusMgr = new AWFocusManager(this);
    /**
     * It is in charge of managing the binding between the components and the bean
     */
    protected BindingManager bindingMgr = new BindingManager(this);

    protected FunctionFieldsManager functionFieldsMgr = new FunctionFieldsManager(this);
    /**
     * Manage the actions that will be used in this presenter
     */
    protected ActionResolver actionRsr = new ActionResolver(this);

    protected GridProviderManager gridProviderMgr = new GridProviderManager(this);

    protected InputProviderManager inputProviderMgr = new InputProviderManager(this);

    protected ValidatorManager validatorMgr = new ValidatorManager(this);

    protected PickManager pickMgr = new PickManager(this);

    protected CalendarManager calendarMgr = new CalendarManager(this);

    protected ZoneManager zonekMgr = new ZoneManager(this);


    /**
     * Object that will be shown
     */
    protected E backBean;
    /**
     * Object that will be shown
     */
    protected String viewMode;

    /*
     * forces binding all components
     */
    protected boolean forceBindingAllComponents = false;

    protected boolean automaticBinding = true;

    protected boolean showUserInfo = true;

    protected boolean showAuditInfo = true;

    protected boolean showCloseButton = false;

    protected boolean showCancelMsgConfirmation = true;

    protected boolean isInitializing = true;

    protected String securityCode = "";

    protected SecurityManager securityMgr = new SecurityManager(this);

    protected boolean embeddedView = false;

    protected Map<String, Executer> executers = new HashMap();
    protected Map<String, Object> backBeanAttributes = new HashMap();

    private List<Component> componentsWithCustomFocusPolicy;

    public Presenter() {

    }

    /**
     * Init the presenter
     */
    public final void init() {
        if(SwingUtilities.isEventDispatchThread()){
            initInternal();
        }else{
            try {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run(){
                        initInternal();
                    }
                });
            } catch (Throwable e) {
                throw new AWBusinessException ("Problems initializing the presenter:"+this,e);
            }
        }
    }
    protected void initInternal() {
        TimeObserver time = new TimeObserver("iniciar presenter");
        time.empezar();
        configure();
        afterConfiguration();

        try {
            initSecurity();
        } catch (AWException ex) {
            logger.error("AW Exception:", ex);
            PainterMessages.paintException(ex);
            return;
        }

        initialize();
        initializeSpecialFields();

        afterInitialing();
        time.terminar();
        // todo ver donde colocar eso
        AWInputVerifier.getInstance().enable();
        isInitializing = false;
        initView();
        ProcessMsgBlocker.instance().removeMessage();
    }

    private void initializeSpecialFields() {
        List<BndIJTextField> functionFields = bindingMgr.getFunctionFields();
        functionFieldsMgr.setFunctionFields(functionFields);
        functionFieldsMgr.init();

    }

    protected void afterInitialing() {
        settingDefaultActionForGridIfApply();
        actionRsr.initializeKeyTriggers();
        disableActionsBasedOnViewMode();
        if (ViewMode.MODE_PICK.equals(getViewMode())) {
            ActionIdentifier idPickSelect = ActionIdentifier.getActionIdentifier(ActionNames.ACTION_PICK_SELECT, 0);
            actionRsr.getAction(idPickSelect).setAsDefaultAction();
        }

    }

    private void settingDefaultActionForGridIfApply() {
        List<GridProvider> gridProviders = gridProviderMgr.getGridProviders();
        for (GridProvider gdp : gridProviders) {
            Action defaultAction = actionRsr.getDefaultActionFor(gdp);
            if (defaultAction == null) {
                Action viewAction = actionRsr.getAction(ActionIdentifier.getActionIdentifier(ActionNames.ACTION_VIEW, gdp.getGridIndex()));
                if (viewAction != null) {
                    viewAction.setAsDefaultAction();
                }
            }
        }
    }

    private void disableActionsBasedOnViewMode() {
        if (ViewMode.MODE_PICK.equals(getViewMode())) {
            disableActionOnPickMode();
        }
    }

    protected void disableActionOnPickMode() {
        List<Action> gridActions = actionRsr.getActionsFor(getGridProvider());
        ActionIdentifier idPickSelect = ActionIdentifier.getActionIdentifier(ActionNames.ACTION_PICK_SELECT, 0);
        for (Action gridAction : gridActions) {
            if (!gridAction.getId().equals(idPickSelect)) {
                gridAction.setVisible(false);
            }
        }
    }

    private void initSecurity() {
        securityMgr.init();
    }

    public void initialize() {
        initCmps();
        afterInitComponents();

        showValues();
        afterShowValues();
    }

    public void configure() {
        configureElements();
        configureActions();
    }

    private void initCalendars() {
        List<BndIJTextField> dateFields = bindingMgr.getAllDateBindings();
        calendarMgr.addAll(dateFields);                                
    }

    public void afterConfiguration() {

    }

    public void initBinding() {
        logger.info(" Initializing binding");

        validatorMgr.buildBasicValidationIfNotDefined();
        bindingMgr.init();
    }

    public void configureElements() {
        TimeObserver time = new TimeObserver("Configuring the Presenter elements");
        time.empezar();

        TimeObserver timeInput = new TimeObserver("register InputProviders");
        timeInput.empezar();
        registerInputProviders();
        timeInput.terminar();

        TimeObserver timeBinding = new TimeObserver("register Binding");
        timeBinding.empezar();
        registerBinding();
        timeBinding.terminar();

        if (automaticBinding) {
            bindingMgr.executeAutomaticBinding();
        }
        TimeObserver timePick = new TimeObserver("register Binding");
        timePick.empezar();
        registerPicks();
        timePick.terminar();

        TimeObserver timeGridProviders = new TimeObserver("register GridProviders");
        timeGridProviders.empezar();
        registerGridProviders();
        timeGridProviders.terminar();

        TimeObserver timeValidations = new TimeObserver("register validations");
        timeValidations.empezar();
        registerValidations();
        timeValidations.terminar();

        registerZones();
        time.terminar();
    }

    protected void registerZones() {

    }

    public void configureActions() {
        TimeObserver time = new TimeObserver("Configuring the Presenter actions");
        time.empezar();
//        logger.debug("Configuring the Presenter actions");
        createActionBaseOnPicksInfo();
        registerActions();
        registerFwActions();
        time.terminar();
    }

    /**
     * Create all the needed actions for each registered pick
     */
    private void createActionBaseOnPicksInfo() {
        for (PickInfo pickInfo : pickMgr.getPicksInfo()) {
            String pickName = pickInfo.getActionCmd();
            PickAction pickAction = getPickAction();
            pickAction.setName(pickName);
            pickAction.setPick(pickInfo.getPick());
            actionRsr.registerAction(pickName, pickAction)
                    .noUseMessageBlocker()
                    .setTargetPstClass(pickInfo.getPick().getTargetPstClass());
        }
    }

    protected PickAction getPickAction() {
        return new PickAction();
    }


    /**
     * Initialize the components and managers
     */
    public void initCmps() {

        TimeObserver time = new TimeObserver("Initializing the Components Presenter");
        time.empezar();

        initComponents();
        if (backBean != null) {
            bindingMgr.setDefaultBean(backBean);
        }
        initBinding();
        initCalendars();
        initGridProviders();
        initActions();
        initZones();
        time.terminar();
    }

    private void initZones() {
        zonekMgr.init();
    }


    private void initActions() {
        registerFwGridActions();

        List<com.aw.swing.mvp.action.Action> actions = actionRsr.getAllActions();
        for (com.aw.swing.mvp.action.Action action : actions) {
            linkActionToComponent(action);
            action.updateUIStatus();
        }

        List<Action> showAuditingActions = actionRsr.getActionsOfType(ShowAuditingInfoAction.class);
        for (Action showAuditingAction : showAuditingActions) {
            int gridIndex = showAuditingAction.getId().getGridIndex();
            GridProvider gdp = gridProviderMgr.getGridProvider(gridIndex);
            MainAuditColumn mainAuditColumn = gdp.getMainAuditColumn();
            if (mainAuditColumn == null) {
                throw new IllegalStateException("Main Audit Column has not been declared in the grid:<" + gridIndex + ">");
            }
            mainAuditColumn.setAction((ShowAuditingInfoAction) showAuditingAction);
        }

    }

    private void linkActionToComponent(com.aw.swing.mvp.action.Action action) {
        JButton button = (JButton) action.getJComponent();
        if (action.isNeedVisualComponent() && button == null)
            throw new IllegalArgumentException("Boton " + "btn" + action.getId().asStringWithoutSeparator() + " no existe");
        if (button != null) {
            addActionToJButton(button, action);
        }

    }

    private void addActionToJButton(JButton jButton, final com.aw.swing.mvp.action.Action action) {
        jButton.putClientProperty(BindingComponent.ATTR_ACTION,action.getId().asStringWithoutSeparator());
        jButton.setMultiClickThreshhold(500);
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeAction(action);
            }
        });
    }

    public void executeAction(Action action) {
        ActionManager.instance().executeAction(action);
    }


    public void checkBasicConditionsFor(Action action) {

    }


    protected void registerBinding() {

    }

    protected void registerPicks() {

    }


    /**
     * Overwrite in order to set a specific focus traversal order
     *
     * @return
     */
    protected Component[] getFocusTraversalOrder() {
        return null;
    }


    /**
     * Sets and manage8 the focus order on the controls
     * If many traversal order are registetred in this method,
     * ensure that the one that you wanted to be used to select the default
     * initial jComponent that get the focus (on screen show)
     * is registered at the end. In other words register as last the traversal
     * focus that you want to be the initial showed or used
     */
//    public void initFocusTraversalOrder() {
//        Component[] components = getFocusTraversalOrder();
//        if (components != null) {
//            navigationMgr.buildTabPolicy(components);
//            return;
//        }
//        navigationMgr.buildTabPolicy(bindingMgr);
//    }
    protected void registerValidations() {
    }

    public List getDynamicValidations() {
        return new ArrayList();
    }

    /**
     * Called after initializing the components
     */
    protected void afterInitComponents() {

    }

    /**
     * Set the bean's values to the JComponents
     */
    public void showValues() {
        setValuesToJComponent();
        gridProviderMgr.setValueToJComponentAtBeginning();
    }


    /**
     * Called after setting the values to the JComponents
     */
    protected void afterShowValues() {

    }


    public void setValuesToJComponent() {
        bindingMgr.setValuesToJComponent();
    }


    private void initGridProviders() {
        gridProviderMgr.init();
    }


    /**
     * Used to register the gridProvides that will be used in this presenter
     */
    protected void registerGridProviders() {

    }

    /**
     * Used to register the gridProvides that will be used in this presenter
     */
    protected void registerInputProviders() {

    }

    /**
     * Used to register the actions that will be used in this presenter
     */
    protected void registerActions() {

    }

    protected void registerFwActions() {
        boolean existCancel = actionRsr.getActions().containsKey(ActionIdentifier.getActionIdentifier(ActionNames.ACTION_CANCEL));
        if (!existCancel) {
            com.aw.swing.mvp.action.Action cancelAction = new CancelAction();
            if (this instanceof FindPresenter || !showCancelMsgConfirmation) {
                cancelAction.setConfirmMsg("");
            }
            JComponent jComponent = (JComponent) getIpView().getComponent("btnCancel");
            if (jComponent != null) {
                jComponent.setVerifyInputWhenFocusTarget(false);
            }
            actionRsr.registerAction(ActionNames.ACTION_CANCEL, cancelAction);
        }
        if (ViewMode.MODE_PICK.equals(getViewMode())) {
            actionRsr.registerAction(ActionNames.ACTION_PICK_SELECT, new PickSelectItemAction(), getGridProvider());
        }
    }

    private void registerFwGridActions() {
        List<GridProvider> gridProviders = gridProviderMgr.getGridProviders();
//        for (GridProvider gdp : gridProviders) {
//            if (!gdp.isEditable()) {
//                actionRsr.registerAction(ActionNames.ACTION_SORT_BY_COLUMN, new SortByColumnAction(), gdp);
//            }
//        }
    }


    /**
     * Create the view that will be used by this prst to show its data
     */
    public Object createView() {
        try {
            Field field = AttributeAccessor.getField(this.getClass(), "vsr");
            Object fieldValue = field.getType().newInstance();
            AttributeAccessor.set(this, field, fieldValue);
            return fieldValue;
        } catch (Throwable e) {
            logger.error("",e);
            throw new AWSystemException("Problems creating the viewSrc for:" + this, e);
        }
    }

    private Class<E> backBeanClass;

    public E createBackBean() {
        if (logger.isDebugEnabled()) {
            logger.debug("Creating new back bean]");
        }
        return (E) org.springframework.beans.BeanUtils.instantiateClass(getBackBeanClass());
    }

    public Class<E> getBackBeanClass() {
        if (backBeanClass == null) {
            this.backBeanClass = ((Class<E>) ((ParameterizedType) getParameterizedType()
                    .getGenericSuperclass()).getActualTypeArguments()[0]);
        }
        return backBeanClass;
    }

    public Class getParameterizedType() {
        Class parameterizedType = getClass();
        while (!(parameterizedType.getGenericSuperclass() instanceof ParameterizedType)) {
            parameterizedType = parameterizedType.getSuperclass();
        }
        return parameterizedType;
    }


    /**
     * Init the components of the view
     */
    protected void initComponents() {
        logger.debug("Initializing the Components");
    }


    /**
     * Init the view in order to be shown appropiatelly
     */
    public void initView() {
        viewMgr.initView();
    }

    /**
     * Invoked when a window has been opened.
     */
    protected void onWindowsOpened(WindowEvent e) {

    }

    public AWFocusManager getAWFocusManager() {
        return focusMgr;
    }

    public IPView getIpView() {
        return viewMgr.getIpView();
    }


    public E getBackBean() {
        return backBean;
    }

    public Presenter setBackBean(E backBean) {
        return this.setBackBean(backBean, false);
    }
    public Presenter setBackBeanClear(E backBean) {
        return this.setBackBean(backBean, true);
    }

    private Presenter setBackBean(E backBean, boolean clear) {
        TimeObserver time = new TimeObserver("setting backBean");
        time.empezar();
        this.backBean = backBean;
        initializeBackBean(backBean, clear);
        if (backBeanAttributes.size() > 0) {
            try {
                BeanUtils.populate(backBean, backBeanAttributes);
            } catch (Throwable e) {
                new AWSystemException("Problems setting attributes to:<" + backBean + ">", e);
            }
        }
        time.terminar();
        return this;
    }

    /**
     *
     * @param backBean back bean
     * @param clear indica si se esta llamando desde clear
     */
    protected void initializeBackBean(E backBean, boolean clear) {

    }

    public ViewManager getViewMgr() {
        return viewMgr;
    }

    public ActionResolver getActionRsr() {
        return actionRsr;
    }

    /**
     * Method call before setting the values to the bean.
     */
    protected void beforeSettingValuesToBean() {
    }

    /**
     * Binds control's values to Bean
     */
    public void setValuesToBean() {
        beforeSettingValuesToBean();
        bindingMgr.setValuesToBean();
    }

    /**
     * Initialize the focusZone and add to the AWFocusManager
     *
     * @param focusZone
     */
    public void addFocusZone(FocusZone focusZone) {
        focusZone.init();
        focusMgr.addFocusZone(focusZone);
    }


    public GridProvider getGridProvider() {
        return getGridProvider(0);
    }

    public GridProvider getGridProvider(int gridIndex) {
        return gridProviderMgr.getGridProvider(gridIndex);
    }

    public Boolean onEnterKeyPressed(Component component) {
        Boolean valueToReturn = null;
        if (component instanceof JTextComponent) {
            Boolean execute = (Boolean) ((JComponent) component).getClientProperty(BindingComponent.ATTR_EXECUTE_PICK);
            if (Boolean.FALSE.equals(execute)) {
                return Boolean.TRUE;
            }
        }
        valueToReturn = executePickIfApply(component);
        return valueToReturn;
    }

    /**
     * Execute the pick if it is necessary
     *
     * @param component
     * @return NULL  If this is not resposible for the componenty that fire the pick
     *         TRUE  pick sucess, row selected
     *         FALSE row not found or not selected
     */
    protected Boolean executePickIfApply(Component component) {
//        Boolean valueToReturn = pickMgr.executePickIfApply(component);
//        return valueToReturn;
        return null;
    }


    public void validate() {
        validatorMgr.validate();
        gridProviderMgr.validate();
    }


    public GridProviderManager getGridProviderMgr() {
        return gridProviderMgr;
    }


    public ValidatorManager getValidatorMgr() {
        return validatorMgr;
    }


    public BindingManager getBindingMgr() {
        return bindingMgr;
    }

    public List getAllBindingsName() {
        return getBindingMgr().getAllBindingsName();
    }

    public List<BindingComponent> getAllBindings() {
        return getBindingMgr().getAllBindings();
    }

    public void closeView() {
        viewMgr.closeView();
        try {
            Field field = AttributeAccessor.getField(this.getClass(), "vsr");
            AttributeAccessor.set(this, field, null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();  
        }
    }

    public PickManager getPickMgr() {
        return pickMgr;
    }

    public Object getView() {
        return getViewMgr().getView();
    }

    /**
     * Get the component that will be received the focus on windows open
     * As default the focus will be put at the first component.
     */
    public JComponent getFistCmpToBeFocused() {
        return null;
    }

    public GridProvider getGridProviderFor(JTable jtable) {
        return gridProviderMgr.getGridProviderFor(jtable);
    }

    public boolean isShowUserInfo() {
        return showUserInfo;
    }

    public void setShowUserInfo(boolean showUserInfo) {
        this.showUserInfo = showUserInfo;
    }

    public boolean isForceBindingAllComponents() {
        return forceBindingAllComponents;
    }

    public void setForceBindingAllComponents(boolean forceBindingAllComponents) {
        this.forceBindingAllComponents = forceBindingAllComponents;
    }

    /**
     * Override in order to set the order that has to be used to move the focus between zonex
     *
     * @return List of {#see InputCmpMgr}
     */
    public List getOrderBetweenInputCmpMgrAndGridProv() {
        return null;
    }

    public void configureAsReanOnly() {
        bindingMgr.setAllAsReadOnly();
        actionRsr.disableAllActionsExcept(getEnabledActionsOnReadOnly());
    }

    protected boolean isReadOnly = false;

    public boolean isReadOnly() {
        return isReadOnly || ViewMode.MODE_READONLY.equals(getViewMode());
    }

    public List getEnabledActionsOnReadOnly() {
        List list = new ArrayList();
        list.add(actionRsr.getAction(ActionIdentifier.getActionIdentifier(ActionNames.ACTION_CANCEL)));
        list.addAll(actionRsr.getAllAlwaysEnabledActions());
        return list;
    }

    public boolean allowWindowActivatedWorkAround() {
        return true;
    }

    protected void setAutomaticBinding(boolean automaticBinding) {
        this.automaticBinding = automaticBinding;
    }

    public boolean isShowCancelMsgConfirmation() {
        return showCancelMsgConfirmation;
    }

    public void setShowCancelMsgConfirmation(boolean showCancelMsgConfirmation) {
        this.showCancelMsgConfirmation = showCancelMsgConfirmation;
    }

    public String getViewMode() {
        return viewMode;
    }

    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }

    public ZoneManager getZonekMgr() {
        return zonekMgr;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public SecurityManager getSecurityMgr() {
        return securityMgr;
    }

    public boolean isEmbeddedView() {
        return embeddedView;
    }

    public void setEmbeddedView(boolean embeddedView) {
        this.embeddedView = embeddedView;
    }

    public UserInfo getApplicationUser() {
        Object appUser = securityMgr.getApplicationUser();
        if (appUser == null) {
            return new UserInfo() {
                public String getUsername() {
                    String user = SecurityHelper.instance().getUsername();
                    return user==null?"Anonymous":user;
                }
            };
        }
        return ((UserInfo) appUser);
    }

    public void setReadOnly(boolean readOnly) {
        isReadOnly = readOnly;
    }

    public boolean isShowAuditInfo() {
        return showAuditInfo;
    }

    public void setShowAuditInfo(boolean showAuditInfo) {
        this.showAuditInfo = showAuditInfo;
    }

    public boolean isShowCloseButton() {
        return showCloseButton;
    }

    public void setShowCloseButton(boolean showCloseButton) {
        this.showCloseButton = showCloseButton;
    }

    public void putExecuter(String nameExecuter, Executer executer) {
        executers.put(nameExecuter, executer);
    }

    public void putBackBeanField(String fieldName, Object value) {
        backBeanAttributes.put(fieldName, value);
    }

    public boolean isInitializing() {
        return isInitializing;
    }

    public void onWindowsOpenedInternalOnlyForAWFW(WindowEvent e) {
    }

    public List<Component> getComponentsWithCustomFocusPolicy() {
        return componentsWithCustomFocusPolicy;
    }

    public void setComponentsWithCustomFocusPolicy(List<Component> componentsWithCustomFocusPolicy) {
        this.componentsWithCustomFocusPolicy = componentsWithCustomFocusPolicy;
    }
}
