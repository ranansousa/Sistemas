/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author PEMPEC
 */
public class CellRendererImpressaoMultiplosRecibos extends DefaultTableCellRenderer {

    private Color getCellColor() {

        return Color.BLACK;
    }

    public Component getTableCellRendererComponent(javax.swing.JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        Color corFundoZebrado = new Color(240, 240, 240);

        label.setFont(new java.awt.Font("Arial", 0, 12));

        if ((row % 2) == 0) {
            label.setBackground(Color.WHITE);
        } else {
            label.setBackground(corFundoZebrado);
        }

        if (column == 0) {
            label.setHorizontalAlignment(CENTER);
        } else if (column == 1) {
            label.setHorizontalAlignment(CENTER);
        } else if (column == 2) {
            label.setHorizontalAlignment(CENTER);
        } else if (column == 3) {
            label.setHorizontalAlignment(LEFT);
        } else if (column == 4) {
            label.setHorizontalAlignment(LEFT);
        } else if (column == 5) {
            label.setHorizontalAlignment(RIGHT);
        }

        return label;

    }
}
