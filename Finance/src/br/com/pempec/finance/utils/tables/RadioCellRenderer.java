/*
 * CheckBoxCellRenderer.java
 *
 * Created on 13 de Agosto de 2007, 16:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author PEMPEC
 */
public class RadioCellRenderer implements TableCellRenderer {

    public RadioCellRenderer() {
        super();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (value == null) {
            return null;
        }

        return (Component) value;

    }
}
