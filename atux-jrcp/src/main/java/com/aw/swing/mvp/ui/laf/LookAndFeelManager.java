package com.aw.swing.mvp.ui.laf;

import com.aw.swing.g2d.LinearGradientTypeLoader;
import com.aw.swing.mvp.util.GUIUtilities;
import com.sun.java.swing.plaf.nimbus.CustomButtonFactory;
import com.sun.java.swing.plaf.nimbus.CustomComboBoxPainter;
import org.jdesktop.fuse.ResourceInjector;
import org.jdesktop.fuse.TypeLoaderFactory;
import org.jdesktop.fuse.swing.SwingModule;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * User: Juan Carlos Vergara
 * Date: 01/05/2009
 */
public class LookAndFeelManager {
    public static final String TITLE_GRADIENT_COLOR1_KEY = "titleGradientColor1";
    public static final String TITLE_GRADIENT_COLOR2_KEY = "titleGradientColor2";

    public static final Color COLOR_BKG_TITLE = new Color(157, 187, 232);

    public static final Color COLOR_BKG_SELECTED = new Color(255, 197, 78);

    public static final String TITLE_FONT_KEY = "titleFont";

    private static final ServiceLoader<LookAndFeel> LOOK_AND_FEEL_LOADER
            = ServiceLoader.load(LookAndFeel.class);

    public void initialize() {
//        overrideDefaultValues();
//        setupCustomColors();

//        setupFontSize();
//        setupScrollPane();
//        setupPanel();
//        setupTables();
//        setupTabbedPane();

        try {
            //todo gmc disable

//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");



            setupPanel();
            initButtonLAF();
            initComboLAF();
            initTextFieldLAF();

//            paintUIDefaults();

            initFuse();

        } catch (Exception ex) {
            // not catestrophic
        }
    }

    private void initTextFieldLAF(){
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("TextField.contentMargins", new InsetsUIResource(5, 6, 6, 6));
    }

    private void initButtonLAF() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();

        defaults.put("customButton", new Color(251,218,64, 255));
        defaults.put("customDefaultButton", new Color(255, 197, 78, 255));

        defaults.put("Button[Enabled].backgroundPainter", CustomButtonFactory.createDefaultButtonPainter());
        defaults.put("Button[Default+Focused+MouseOver].backgroundPainter", CustomButtonFactory.createMouseOverDefaultFocusedButtonPainter());
        defaults.put("Button[Default+Focused+Pressed].backgroundPainter", CustomButtonFactory.createPressedFocusedButtonPainter());
        defaults.put("Button[Default+Focused].backgroundPainter", CustomButtonFactory.createFocusedButtonPainter());
        defaults.put("Button[Default+MouseOver].backgroundPainter", CustomButtonFactory.createMouseOverButtonPainter());
        defaults.put("Button[Default+Pressed].backgroundPainter", CustomButtonFactory.createPressedButtonPainter());
        defaults.put("Button[Default].backgroundPainter", CustomButtonFactory.createDefaultButtonPainter());
        defaults.put("Button[Focused+MouseOver].backgroundPainter", CustomButtonFactory.createMouseOverFocusedButtonPainter());
        defaults.put("Button[Focused+Pressed].backgroundPainter", CustomButtonFactory.createPressedFocusedButtonPainter());
        defaults.put("Button[Focused].backgroundPainter", CustomButtonFactory.createFocusedButtonPainter());
        defaults.put("Button[MouseOver].backgroundPainter", CustomButtonFactory.createMouseOverButtonPainter());
        defaults.put("Button[Pressed].backgroundPainter", CustomButtonFactory.createPressedButtonPainter());
    }

    private void initComboLAF() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();

        defaults.put("ComboBox[Enabled].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_MOUSEOVER));
        defaults.put("ComboBox[Pressed].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_PRESSED));
        defaults.put("ComboBox[MouseOver].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_MOUSEOVER));
        defaults.put("ComboBox:\"ComboBox.renderer\"[Disabled].textForeground", new ColorUIResource(Color.BLACK));

//        defaults.put("ComboBox[Editable+Focused].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_FOCUSED_EDITABLE));
//        defaults.put("ComboBox[Enabled+Selected].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_ENABLED_SELECTED));
//        defaults.put("ComboBox[Editable+Pressed].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_PRESSED_EDITABLE));
//        defaults.put("ComboBox[Focused+Pressed].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_FOCUSED));
//        defaults.put("ComboBox[Disabled+Pressed].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_DISABLED_PRESSED));
//        defaults.put("ComboBox[Disabled+Editable].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_DISABLED_EDITABLE));
//        defaults.put("ComboBox[Disabled].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_DISABLED));
//        defaults.put("ComboBox[Editable+MouseOver].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_MOUSEOVER_EDITABLE));
//        defaults.put("ComboBox[Focused].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_FOCUSED));
//        defaults.put("ComboBox[Editable+Enabled].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_ENABLED_EDITABLE));
//        defaults.put("ComboBox[Focused+MouseOver].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_MOUSEOVER_FOCUSED));
    }

    private void setupPanel() {
//        UIManager.put("Panel[Enabled].background", new Color(223, 232, 246));
//        UIManager.put("Panel[Enabled].background", new ColorUIResource(223,235,245));
    }

    private void setupScrollPane() {
//        UIManager.put("ScrollPane[Enabled].opaque", true);
        UIManager.put("ScrollPane[Enabled].background", new Color(223, 232, 246));
        UIManager.put("ScrollPane[Enabled].disabled", new Color(223, 232, 246));
//        UIManager.put("ScrollPane[Enabled].borderPainter", COLOR_BKG_TITLE);
    }

    private void overrideDefaultValues() {
        UIManager.put("Nimbus.Overrides.InheritDefaults", false);
        UIManager.put("control", new Color(223, 232, 246));
    }

    private void setupTabbedPane() {
//        UIManager.put("TabbedPane[Enabled]:TabbedPaneTab[Enabled].backgroundPainter", null);

        UIManager.put("TabbedPane[Enabled].background", new Color(223, 232, 246));
//        UIManager.put("TabbedPane[Enabled]:TabbedPaneTabArea[Enabled].backgroundPainter", new Color(49, 105, 156));
//        UIManager.put("TabbedPane[Enabled]:TabbedPaneTab[Pressed+Selected].backgroundPainter", new Color(49, 105, 156));
//        UIManager.put("TabbedPane[Enabled]:TabbedPaneTab[Focused+Pressed+Selected].backgroundPainter", new Color(49, 105, 156));
//        UIManager.put("TabbedPane[Enabled]:TabbedPaneTab[Enabled+Pressed].backgroundPainter", new Color(49, 105, 156));
//        UIManager.put("TabbedPane[Enabled]:TabbedPaneTab[Disabled+Selected].backgroundPainter", new Color(140, 174, 222));
    }

    private void setupTables() {
//        UIManager.put("Table[Enabled].background", new Color(255, 255, 255));
//        UIManager.put("Table[Enabled].gridColor", new Color(221, 221, 221));
//
//        UIManager.put("Table[Enabled].showGrid", true);
        UIManager.put("SplitPane[Enabled].background", new Color(255, 255, 255));
//        UIManager.put("Table.editor.background", new Color(255, 255, 255));
//        UIManager.put("TableHeader[Enabled].background", new Color(179, 179, 179));
    }

    private void setupFontSize() {
        UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Tahoma", Font.BOLD, 26));

        UIManager.put("TextField[Enabled].font", new Font("Tahoma", Font.PLAIN, 10));
        UIManager.put("TextArea[Enabled].font", new Font("Tahoma", Font.PLAIN, 10));
        UIManager.put("Label[Enabled].font", new Font("Tahoma", Font.PLAIN, 11));
        UIManager.put("ComboBox[Enabled].font", new Font("Tahoma", Font.PLAIN, 11));
//        UIManager.put("Table[Enabled].font", new Font("Tahoma", Font.PLAIN, 11));
        UIManager.put("TabbedPane[Enabled].font", new Font("Tahoma", Font.PLAIN, 11));
        UIManager.put("Button[Enabled].font", new Font("Tahoma", Font.PLAIN, 11));
//        UIManager.put("TableHeader[Enabled].font", new Font("Tahoma", Font.PLAIN, 11));
    }

    private void setupCustomColors() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("nimbusCustomButton", new ColorUIResource(new Color(252, 240, 178, 255)));


    }


    private void paintUIDefaults() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();

        for (Iterator it = defaults.keySet().iterator(); it.hasNext();) {
            Object key = it.next();
            Object value = defaults.get(key);
            // do something with the key and the value
            if (key instanceof String && ((String) key).startsWith("Combo"))
                System.out.println(key + "=" + value);
        }
    }

    private void initFuse() {
        // Calculate gradient colors for title panels
        Color titleColor = UIManager.getColor(usingNimbus() ? "nimbusBase" : "activeCaption");

        float hsb[] = Color.RGBtoHSB(
                titleColor.getRed(), titleColor.getGreen(), titleColor.getBlue(), null);

          UIManager.put(TITLE_GRADIENT_COLOR1_KEY,
                new Color(2,140,200));

            UIManager.put(TITLE_GRADIENT_COLOR2_KEY,
                new Color(3,153,217));

        Color controlColor = UIManager.getColor("control");
        UIManager.put(GUIConstants.CONTROL_MID_SHADOW_KEY,
                GUIUtilities.deriveColorHSB(controlColor, 0, 0, -0.5f));

        ResourceInjector.get().load(LookAndFeelManager.class, "/jrcp-uitheme.properties");
        TypeLoaderFactory.addTypeLoader(new LinearGradientTypeLoader());
        ResourceInjector.addModule(new SwingModule());


        Font labelFont = UIManager.getFont("Label.font");
        UIManager.put(TITLE_FONT_KEY, labelFont.deriveFont(Font.BOLD, labelFont.getSize() + 4f));
    }

    public static boolean usingNimbus() {
        return UIManager.getLookAndFeel().getName().equals("Nimbus");
    }

    public static void setLookAndFeel(JTable jTable) {
        jTable.setSelectionBackground(LookAndFeelManager.COLOR_BKG_SELECTED);
        jTable.setRowHeight(24);
        jTable.setSelectionForeground(Color.BLACK);
        jTable.setIntercellSpacing(new Dimension(2, 2));
    }

    public static void setLookAndFeelWhenDisabled(JComboBox cmb) {
        UIDefaults cmbDefaults = new UIDefaults();
        cmbDefaults.put("ComboBox[Enabled].background", new Color(255, 255, 255));
        cmb.putClientProperty("Nimbus.Overrides", cmbDefaults);
        cmb.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
    }

    public static void assignRequiredPainter(JComboBox comboBox) {
        UIDefaults cmbDefaults = new UIDefaults();
        cmbDefaults.put("ComboBox[Enabled].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_REQUIRED_ENABLED));
//        cmbDefaults.put("ComboBox[Pressed].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_REQUIRED_PRESSED));
        cmbDefaults.put("ComboBox[MouseOver].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_REQUIRED_MOUSEOVER));
        cmbDefaults.put("ComboBox[Focused+Pressed].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_REQUIRED_PRESSED_FOCUSED));
        cmbDefaults.put("ComboBox[Focused].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_REQUIRED_FOCUSED));
        cmbDefaults.put("ComboBox[Focused+MouseOver].backgroundPainter", new CustomComboBoxPainter(CustomComboBoxPainter.BACKGROUND_REQUIRED_FOCUSED_MOUSEOVER));

        comboBox.putClientProperty("Nimbus.Overrides", cmbDefaults);
        comboBox.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
    }

    public static void clearRequiredPainter(JComboBox jComboBox) {
        jComboBox.putClientProperty("Nimbus.Overrides", null);
    }
}
