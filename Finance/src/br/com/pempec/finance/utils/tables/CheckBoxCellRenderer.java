/*
 * CheckBoxCellRenderer.java
 *
 * Created on 13 de Agosto de 2007, 16:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author PEMPEC
 */
public class CheckBoxCellRenderer extends JCheckBox implements TableCellRenderer {

    public CheckBoxCellRenderer() {
        super();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Boolean newSelectedValue = (Boolean) value;

        setSelected(newSelectedValue.booleanValue());

        this.setHorizontalAlignment(this.CENTER);

        Color corFundoZebrado = new Color(240, 240, 240);

        if ((row % 2) == 0) {
            this.setBackground(Color.WHITE);
        } else {
            this.setBackground(corFundoZebrado);
        }

        return this;
    }

    /**
     * Overridden for performance reasons
     */
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
        // do nothing
    }

    /**
     * Overridden for performance reasons
     */
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        // do nothing
    }

    /**
     * Overridden for performance reasons
     */
    public void repaint(long tm, int x, int y, int width, int height) {
        // do nothing
    }

    /**
     * Overridden for performance reasons
     */
    public void repaint(Rectangle r) {
        // do nothing
    }

    /**
     * Overridden for performance reasons
     */
    public void revalidate() {
        // do nothing
    }

    /**
     * Overridden for performance reasons
     */
    public void validate() {
        // do nothing
    }
}
