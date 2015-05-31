/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author PEMPEC
 */
public class CellRendererTelaAvisosPagarTitulos extends DefaultTableCellRenderer {

    private Color getCellColor() {
        return Color.BLACK;
    }

    public Component getTableCellRendererComponent(javax.swing.JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JButton button = new JButton("Pagar");

        button.setFont(new java.awt.Font("Arial", 0, 10));
        button.setHorizontalAlignment(JButton.CENTER);

        return button;

    }
}
