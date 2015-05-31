/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.utils.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pempec
 */
public class TableModelSaldoBancario extends AbstractTableModel {

    private List<SaldoBancario> collection;

    public TableModelSaldoBancario(List<SaldoBancario> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        SaldoBancario com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getContaBancariaModel() != null ? com.getContaBancariaModel().getConta().toString() : "";
            case 2:
                return com.getContaBancariaModel() != null ? com.getContaBancariaModel().getTitular().toString() : "";
            case 3:
                return com.getSaldo() != null ? PempecParse.doubleToZero(com.getSaldo()) : "";
            case 4:
                return com.getCheques() != null ? PempecParse.doubleToZero(com.getCheques()) : "";
            case 5:
                return com.getContaBancariaModel() != null ? PempecParse.doubleToZero(com.getContaBancariaModel().getLimiteCredito()) : "";
            case 6:
                return com.getSaldo() != null ? PempecParse.doubleToZero((com.getSaldo() - com.getCheques()) + com.getContaBancariaModel().getLimiteCredito()) : "";



        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public SaldoBancario getValues(int rowIndex) {
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

        SaldoBancario com = collection.get(row);

        com.setCheck(((Boolean) value).booleanValue());

        fireTableCellUpdated(row, col);

    }
}
