/*
 * Documento.java
 *
 * Created on 24 de Agosto de 2007, 08:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Classe responsável por delimitar o tamanho máximo de caracteres dos campos
 * JTextField e JFormattedTextField
 *
 * @author PEMPEC
 */
public class Documento extends PlainDocument {

    private int maximo;

    public Documento(int max) {

        this.maximo = max;

    }

    public void insertString(int offs, String str, AttributeSet a) {
        try {

            if ((getLength() + str.length()) <= maximo) {

                super.insertString(offs, str, a);
            }

        } catch (BadLocationException e) {

            e.printStackTrace();

        }

    }
}
