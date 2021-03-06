/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.utils.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pempec
 */
public class TableModelListagemTituloPagar extends AbstractTableModel {

    private List<TituloPagarModel> collection;

    public TableModelListagemTituloPagar(List<TituloPagarModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        TituloPagarModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getDataVencimento() != null ? PempecParse.dateToString(com.getDataVencimento()) : "";
            case 2:
                return com.getNumeroDocumento() != null ? com.getNumeroDocumento() : "";
            case 3:
                return com.getValorNominal() != null ? PempecParse.doubleToZero(com.getValorNominal()) : "";
            case 4:
                return com.getCedente() != null ? com.getCedente().getNome() : "";
            case 5:
                return (com.getHistorico() != null && com.getDescricao() != null) ? com.getHistorico().getDescricao().toString() + " " + com.getDescricao().toString() : "";

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 3;
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
        // primeira coluna deve ser marcÃ¡vel
        if (column == 0) {
            klass = Boolean.class;
        }

        return klass;

    }

    public void setValueAt(Object value, int row, int col) {

        TituloPagarModel com = collection.get(row);

        com.setCheck(((Boolean) value).booleanValue());

        fireTableCellUpdated(row, col);

    }
}
