/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipe Pempec
 */
public class PempecJOptionPane extends JOptionPane {

    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);


    }

    @Override
    public void setToolTipText(String text) {
        super.setToolTipText(text);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
    }

    @Override
    public void setComponentOrientation(ComponentOrientation o) {
        super.setComponentOrientation(o);
    }
}
