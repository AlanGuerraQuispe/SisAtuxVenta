package com.sun.java.swing.plaf.nimbus;

/**
 * User: Juan Carlos Vergara
 * Date: 22/06/2009
 */
public class CustomButtonFactory {
    public static CustomButtonPainter createDefaultButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_DEFAULT);
    }

    public static CustomButtonPainter createDefaultFocusedButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_DEFAULT_FOCUSED);
    }

    public static CustomButtonPainter createEnabledButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_ENABLED);
    }

    public static CustomButtonPainter createFocusedButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_FOCUSED);
    }

    public static CustomButtonPainter createMouseOverButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_MOUSEOVER);
    }

    public static CustomButtonPainter createMouseOverDefaultButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_MOUSEOVER_DEFAULT);
    }

    public static CustomButtonPainter createMouseOverDefaultFocusedButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_MOUSEOVER_DEFAULT_FOCUSED);
    }

    public static CustomButtonPainter createMouseOverFocusedButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_MOUSEOVER_FOCUSED);
    }

    public static CustomButtonPainter createPressedButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_PRESSED);
    }

    public static CustomButtonPainter createPressedDefaultButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_PRESSED_DEFAULT);
    }

    public static CustomButtonPainter createPressedDefaultFocusedButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_PRESSED_DEFAULT_FOCUSED);
    }

    public static CustomButtonPainter createPressedFocusedButtonPainter() {
        return new CustomButtonPainter(CustomButtonPainter.BACKGROUND_PRESSED_FOCUSED);
    }
}
