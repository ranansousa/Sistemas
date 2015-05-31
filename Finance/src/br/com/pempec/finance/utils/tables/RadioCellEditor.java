/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;

/**
 *
 * @author Adm
 */
public class RadioCellEditor extends DefaultCellEditor implements ItemListener {

    private JRadioButton button;

    public RadioCellEditor(JCheckBox checkBox) {
        super(checkBox);
    }

    @Override
    public Object getCellEditorValue() {
        button.removeItemListener(this);
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        if (value == null) {
            return null;
        }

        button = (JRadioButton) value;
        button.addItemListener(this);
        return (Component) value;
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}
