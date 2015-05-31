/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.ContaContabilModel;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PEMPEC
 */
public class TableModelPesquisaContaContabil extends AbstractTableModel {

    private ArrayList<ContaContabilModel> collection;

    public TableModelPesquisaContaContabil(ArrayList<ContaContabilModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        ContaContabilModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getDescricao();
            case 2:
                return com.getConta() + "-" + com.getDigitoConta();
            case 3:
                return com.getContaReduzida() + "-" + com.getDigitoContaReduzida();
            case 4:
                return com.getTipo();
            case 5:
                return com;

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 6;
    }

    public ContaContabilModel getValues(int rowIndex) {
        return collection.get(rowIndex);
    }

    public boolean isCellEditable(int row, int column) {

        if (column == 0) {
            return true;
        }

        return false;

    }

    public Class getColumnClass(int column) {

        Class klass = String.class; // para todas as outras colunas use String
        // primeira coluna deve ser marcÃ¡vel
        if (column == 0) {
            klass = Boolean.class;
        }

        return klass;

    }

    public void setValueAt(Object value, int row, int col) {

        ContaContabilModel com = collection.get(row);

        com.setCheck(((Boolean) value).booleanValue());

        fireTableCellUpdated(row, col);

    }
}
