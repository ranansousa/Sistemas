/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.utils.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pempec
 */
public class TableModelListagemCheques extends AbstractTableModel {

    private List<TituloReceberBaixaChequeModel> collection;

    public TableModelListagemCheques(List<TituloReceberBaixaChequeModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        TituloReceberBaixaChequeModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getDataProtocolo() != null ? PempecParse.dateToString(com.getDataProtocolo()) : "";
            case 2:
                return com.getValor() != null ? PempecParse.doubleToZero(com.getValor()) : "";
            case 3:
                return com.getBanco() != null ? com.getBanco().getNomeBanco() : "";
            case 4:
                return com.getAgencia() != null ? com.getAgencia() : "";
            case 5:
                return com.getConta() != null ? com.getConta() : "";
            case 6:
                return com.getNumeroCheque() != null ? com.getNumeroCheque() : "";
            case 7:
                return com.getTitular() != null ? com.getTitular() : "";

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public TituloReceberBaixaChequeModel getValues(int rowIndex) {
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

        TituloReceberBaixaChequeModel com = collection.get(row);

        com.setCheck(((Boolean) value).booleanValue());

        fireTableCellUpdated(row, col);

    }
}
