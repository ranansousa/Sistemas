package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.utils.*;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PEMPEC
 */
public class TableModelExcluirTituloReceberBaixaAC extends AbstractTableModel {

    private ArrayList<TituloReceberBaixaAcrescimoModel> collection;

    public TableModelExcluirTituloReceberBaixaAC(ArrayList<TituloReceberBaixaAcrescimoModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        TituloReceberBaixaAcrescimoModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getTipoAcrescimo() != null ? com.getTipoAcrescimo().getDescricao() : "";
            case 2:
                return com.getValor() != null ? PempecParse.doubleToZero(com.getValor()) : "";

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public TituloReceberBaixaAcrescimoModel getValues(int rowIndex) {
        return collection.get(rowIndex);
    }

    public boolean isCellEditable(int row, int column) {

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

        TituloReceberBaixaAcrescimoModel com = collection.get(row);

        com.setCheck(((Boolean) value).booleanValue());

        fireTableCellUpdated(row, col);

    }
}
