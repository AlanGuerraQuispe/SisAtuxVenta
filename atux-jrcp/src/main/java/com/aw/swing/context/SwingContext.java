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
package com.aw.swing.context;

import com.aw.support.collection.ListUtils;
import com.aw.swing.mvp.focus.AWKeyboardFocusManagerDecorator;
import com.aw.swing.mvp.ui.msg.MessageDisplayer;
import com.aw.swing.mvp.ui.msg.MessageDisplayerImpl;
import com.aw.swing.mvp.ui.painter.ErrorBackgroundPainter;
import com.aw.swing.mvp.ui.painter.ErrorBorderPainter;
import com.aw.swing.mvp.ui.painter.ErrorPainter;
import org.fest.swing.fixture.DialogFixture;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;

//import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;

/**
 * User: gmc
 * Date: 21-ago-2007
 */
public class SwingContext {

    private static MessageDisplayer msgDisplayer;

//    private static Stack presenters = new Stack();

    private static boolean usePresenterAsReadOnly = false;

    private static List msgBaseNames;

    private static DialogFixture fixture;

    public static void setFixture(DialogFixture fixture) {
        SwingContext.fixture = fixture;
    }

    public static DialogFixture getFixture() {
        return SwingContext.fixture;
    }

    /**
     * Used to resise window in a MDI enclosing window
     */
//    protected static Frame frame;

    public static void init() {
        initMsgDisplayer();
        initActionSupport();
        initErrorPainter();
    }

    private static void initMsgDisplayer() {
        if (msgBaseNames == null) {
            msgBaseNames = new ArrayList();
        }
        msgBaseNames.add("com.aw.swing.context.swing-msg");
        ResourceBundleMessageSource bundleMessageSourcer = new ResourceBundleMessageSource();
        bundleMessageSourcer.setBasenames(ListUtils.toArray(msgBaseNames));
        msgDisplayer = new MessageDisplayerImpl(new MessageSourceImpl(bundleMessageSourcer));
    }

    static ErrorPainter errorPainter = null;
    public static final int ERROR_IN_BORDER = 0;
    public static final int ERROR_IN_COMPONENT = 1;
    //    public static final int TYPE_SHOW = ERROR_IN_BORDER;
    public static final int TYPE_SHOW = ERROR_IN_COMPONENT;

    public static void initErrorPainter() {
        switch (SwingContext.TYPE_SHOW) {
            case SwingContext.ERROR_IN_BORDER:
                errorPainter = new ErrorBorderPainter();
                break;
            case SwingContext.ERROR_IN_COMPONENT:
                errorPainter = new ErrorBackgroundPainter();
                break;
        }
    }

    public static void initActionSupport() {
        AWKeyboardFocusManagerDecorator.init();
        AWKeyboardFocusManagerDecorator.decorate();
    }

    public static void desactivarActionSupport() {
        AWKeyboardFocusManagerDecorator.unDecorate();
    }

    /**
     * @deprecated
     * @return
     */
    public static MessageDisplayer getMsgDisplayer() {
        return msgDisplayer;
    }

    public static ErrorPainter getErrorPainter() {
        return errorPainter;
    }

    public static void setMsgBaseNames(List msgBaseNames) {
        SwingContext.msgBaseNames = msgBaseNames;
    }

    //START JCV L&F
    public static void initPLAF() {
//        ResourceInjector.addModule(new SwingModule());
//        ResourceInjector.get().load(SwingContext.class, "/jrcp-uitheme.properties");
//        TypeLoaderFactory.addTypeLoader(new LinearGradientTypeLoader());

//        try {
//            UIManager.setLookAndFeel(new SubstanceDefaultLookAndFeel());
//            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
//            UIManager.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());
//            UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());

//            Font f = (Font) UIManager.get("Label.font");
//            UIManager.put("Label.font", new Font(f.getFontName(), Font.BOLD, f.getSize()));
////            UIManager.put("Label.foreground", new ColorUIResource(255, 255, 255));
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }

    public static boolean isUsePresenterAsReadOnly() {
        return usePresenterAsReadOnly;
    }

    public static void setUsePresenterAsReadOnly(boolean usePresenterAsReadOnly) {
        SwingContext.usePresenterAsReadOnly = usePresenterAsReadOnly;
    }
}
