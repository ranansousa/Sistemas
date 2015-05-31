package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.GrupoModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PEMPEC
 */
public class TableModelCadastroUsuarioGrupos extends AbstractTableModel {

    private List<GrupoModel> collection;

    public TableModelCadastroUsuarioGrupos(List<GrupoModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        GrupoModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getDescricao();
            case 2:
                return com.getId();

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public GrupoModel getValues(int rowIndex) {
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

        GrupoModel com = collection.get(row);

        if (col == 0) {
            com.setCheck(((Boolean) value).booleanValue());
        }

        fireTableCellUpdated(row, col);

    }
}
