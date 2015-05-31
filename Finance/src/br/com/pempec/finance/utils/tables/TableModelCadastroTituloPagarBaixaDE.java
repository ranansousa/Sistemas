/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.utils.PempecParse;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adm
 */
public class TableModelCadastroTituloPagarBaixaDE extends AbstractTableModel {

    private ArrayList<TituloPagarBaixaDeducaoModel> collection;

    public TableModelCadastroTituloPagarBaixaDE(ArrayList<TituloPagarBaixaDeducaoModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        TituloPagarBaixaDeducaoModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getTipoDeducao() != null ? com.getTipoDeducao().getDescricao() : "";
            case 2:
                return com.getValor() != null ? PempecParse.doubleToZero(com.getValor()) : "";
            case 3:
                return com.getPk().getId();

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public TituloPagarBaixaDeducaoModel getValues(int rowIndex) {
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

        TituloPagarBaixaDeducaoModel com = collection.get(row);

        com.setCheck(((Boolean) value).booleanValue());

        fireTableCellUpdated(row, col);

    }

    public void removeByID(String id) {

        for (TituloPagarBaixaDeducaoModel baixaDeducaoModel : collection) {

            if (baixaDeducaoModel.getPk().getId().equals(id)) {
                collection.remove(baixaDeducaoModel);
            }

        }

        fireTableDataChanged();
    }
}
