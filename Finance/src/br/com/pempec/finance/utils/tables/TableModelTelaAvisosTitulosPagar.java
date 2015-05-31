/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.utils.*;
import br.com.pempec.finance.models.TituloPagarModel;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pempec
 */
public class TableModelTelaAvisosTitulosPagar extends AbstractTableModel {

    private ArrayList<TituloPagarModel> collection;

    public TableModelTelaAvisosTitulosPagar(ArrayList<TituloPagarModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        TituloPagarModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return "Pagar";
            case 1:
                return PempecParse.dateToString(com.getDataVencimento());
            case 2:
                return com.getNumeroDocumento();
            case 3:
                return com.getHistorico().getDescricao() + " " + com.getDescricao();
            case 4:
                return com.getValorNominal() != null ? PempecParse.doubleToZero(com.getValorNominal()) : "";

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public TituloPagarModel getValues(int rowIndex) {
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

        return klass;

    }

    public void setValueAt(Object value, int row, int col) {

        TituloPagarModel com = collection.get(row);

        fireTableCellUpdated(row, col);

    }
}
