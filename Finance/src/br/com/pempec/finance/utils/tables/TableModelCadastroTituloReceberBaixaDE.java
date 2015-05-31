package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.utils.*;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PEMPEC
 */
public class TableModelCadastroTituloReceberBaixaDE extends AbstractTableModel {

    private ArrayList<TituloReceberBaixaDeducaoModel> collection;

    public TableModelCadastroTituloReceberBaixaDE(ArrayList<TituloReceberBaixaDeducaoModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        TituloReceberBaixaDeducaoModel com = collection.get(rowIndex);

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

    public TituloReceberBaixaDeducaoModel getValues(int rowIndex) {
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

        TituloReceberBaixaDeducaoModel com = collection.get(row);

        com.setCheck(((Boolean) value).booleanValue());

        fireTableCellUpdated(row, col);

    }

    public void removeByID(String id) {

        for (TituloReceberBaixaDeducaoModel baixaDeducaoModel : collection) {

            if (baixaDeducaoModel.getPk().getId().equals(id)) {
                collection.remove(baixaDeducaoModel);
            }

        }

        fireTableDataChanged();
    }
}
