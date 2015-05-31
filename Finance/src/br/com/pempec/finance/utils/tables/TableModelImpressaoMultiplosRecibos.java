/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.ImpressaoMultiplosRecibosModel;
import br.com.pempec.finance.utils.PempecParse;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PEMPEC
 */
public class TableModelImpressaoMultiplosRecibos extends AbstractTableModel {

    private ArrayList<ImpressaoMultiplosRecibosModel> collection;

    public TableModelImpressaoMultiplosRecibos(ArrayList<ImpressaoMultiplosRecibosModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        ImpressaoMultiplosRecibosModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getTipo();
            case 2:
                return com.getDocumento();
            case 3:
                return com.getDescricao();
            case 4:
                return com.getNome();
            case 5:
                return PempecParse.doubleToZero(com.getValor());
            case 6:
                return com.getTituloPagar();
            case 7:
                return com.getTituloReceber();

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 8;
    }

    public ImpressaoMultiplosRecibosModel getValues(int rowIndex) {
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

        ImpressaoMultiplosRecibosModel com = collection.get(row);

        com.setCheck(((Boolean) value).booleanValue());

        fireTableCellUpdated(row, col);

    }

    public void removeByID(String id) {


        fireTableDataChanged();
    }
}
