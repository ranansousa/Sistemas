/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.utils.PempecParse;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pempec
 */
public class TableModelTelaAvisosTitulosReceber extends AbstractTableModel {

    private ArrayList<TituloReceberModel> collection;

    public TableModelTelaAvisosTitulosReceber(ArrayList<TituloReceberModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        TituloReceberModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return "Receber";
            case 1:
                return PempecParse.dateToString(com.getDataVencimento());
            case 2:
                return com.getNumeroDocumento();
            case 3:
                return com.getSacado().getNome() + "  "+  com.getHistorico().getDescricao() + "  " + com.getDescricao();
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

    public TituloReceberModel getValues(int rowIndex) {
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

        TituloReceberModel com = collection.get(row);

        fireTableCellUpdated(row, col);

    }
}
