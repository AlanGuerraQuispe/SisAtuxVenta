package com.atux.dominio.reporte.text;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

/**
 * Created by IntelliJ IDEA.
 * User: Wilmer Segura
 * Date: 24/08/2009
 * Time: 09:54:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Parrafo extends Paragraph {

    private Font fontTexto = new Font(Font.HELVETICA, 12);
    private Font fontTextoNegrita = new Font(Font.HELVETICA, 12,Font.BOLD);
    private Font fontTitulo = new Font(Font.HELVETICA, 12, Font.BOLD);
    private Font fontLabel = new Font(Font.HELVETICA, 12);
    private Font fontEncabezado = new Font(Font.HELVETICA, 10);
    private Font fontNota = new Font(Font.HELVETICA, 8);
    private Font fontFecha = new Font(Font.HELVETICA, 10);
    private Font fontTituloSubrayado = new Font(Font.HELVETICA, 14, Font.UNDERLINE);
    private Font fontEmail = new Font(Font.COURIER, 12);

    public Parrafo() {

    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFontTexto() {
        return fontTexto;
    }

    public void setFontTexto(Font fontTexto) {
        this.fontTexto = fontTexto;
    }

    public Font getFontTitulo() {
        return fontTitulo;
    }

    public Font getFontFecha() {
        return fontFecha;
    }

    public void setFontFecha(Font fontFecha) {
        this.fontFecha = fontFecha;
    }

    public void setFontTitulo(Font fontTitulo) {
        this.fontTitulo = fontTitulo;
    }

    public Font getFontNota() {
        return fontNota;
    }

    public Font getFontTextoNegrita() {
        return fontTextoNegrita;
    }

    public void setFontTextoNegrita(Font fontTextoNegrita) {
        this.fontTextoNegrita = fontTextoNegrita;
    }

    public void setFontNota(Font fontNota) {
        this.fontNota = fontNota;
    }

    public Font getFontEncabezado() {
        return fontEncabezado;
    }

    public void setFontEncabezado(Font fontEncabezado) {
        this.fontEncabezado = fontEncabezado;
    }

    public Font getFontTituloSubrayado() {
        return fontTituloSubrayado;
    }

    public void setFontTituloSubrayado(Font fontTituloSubrayado) {
        this.fontTituloSubrayado = fontTituloSubrayado;
    }

    public Font getFontEmail() {
        return fontEmail;
    }

    public void setFontEmail(Font fontEmail) {
        this.fontEmail = fontEmail;
    }

    public Font getFontLabel() {
        return fontLabel;
    }

    public void setFontLabel(Font fontLabel) {
        this.fontLabel = fontLabel;
    }

    public Paragraph texto(String texto) {
        return new Paragraph(texto, getFontTexto());
    }

    public Paragraph encabezado(String texto) {
        return new Paragraph(texto, getFontEncabezado());
    }

    public Paragraph textoNegrita(String texto) {
        return new Paragraph(texto, getFontTextoNegrita());
    }

    public Paragraph titulo(String texto) {
        return new Paragraph(texto + "\n \n", getFontTitulo());
    }
    public Paragraph tituloSubrayado(String texto) {
        return new Paragraph(texto + "\n \n", getFontTituloSubrayado());
    }
    public Paragraph label(String texto) {
        return new Paragraph(texto, getFontLabel());
    }
    public Paragraph nota(String texto) {
        return new Paragraph(texto, getFontNota());
    }
    public Paragraph email(String texto) {
        return new Paragraph(texto, getFontEmail());
    }
     public Paragraph fecha(String texto) {
        return new Paragraph(texto + "\n \n", getFontFecha());
    }
}
