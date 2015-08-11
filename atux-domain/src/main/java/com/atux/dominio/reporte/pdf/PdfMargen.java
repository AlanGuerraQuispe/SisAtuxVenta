package com.atux.dominio.reporte.pdf;

/**
 * Created by IntelliJ IDEA.
 * User: Wilmer Segura
 * Date: 12/09/2009
 * Time: 05:17:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class PdfMargen {
    private float margenLeft;
    private float margenRigth;
    private float margenTop;
    private float margenBottom;

    public PdfMargen() {
        margenLeft = 10;
        margenRigth = 10;
        margenTop = 54;
        margenBottom = 45;
    }

    public float getMargenLeft() {
        return margenLeft;
    }

    public void setMargenLeft(float margenLeft) {
        this.margenLeft = margenLeft;
    }

    public float getMargenRigth() {
        return margenRigth;
    }

    public void setMargenRigth(float margenRigth) {
        this.margenRigth = margenRigth;
    }

    public float getMargenTop() {
        return margenTop;
    }

    public void setMargenTop(float margenTop) {
        this.margenTop = margenTop;
    }

    public float getMargenBottom() {
        return margenBottom;
    }

    public void setMargenBottom(float margenBottom) {
        this.margenBottom = margenBottom;
    }
}
