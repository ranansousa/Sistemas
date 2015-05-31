package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.PermissaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PEMPEC
 */
public class TableModelCadastroUsuario extends AbstractTableModel {

    private ArrayList<PermissaoModel> collection;

    public TableModelCadastroUsuario(ArrayList<PermissaoModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        PermissaoModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getDescricao();
            case 1:
                return com.getCheckCadastrar();
            case 2:
                return com.getCheckAlterar();
            case 3:
                return com.getCheckExcluir();
            case 4:
                return com.getCheckImpressao();
            case 5:
                return com.getCheckOutros();

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public PermissaoModel getValues(int rowIndex) {
        return collection.get(rowIndex);
    }

    public boolean isCellEditable(int row, int column) {

        if (column == 1
                || column == 2
                || column == 3
                || column == 4
                || column == 5) {
            return true;
        }

        return false;

    }

    public Class getColumnClass(int column) {

        Class klass = String.class; // para todas as outras colunas use String
        // primeira coluna deve ser marcÃ¡vel

        if (column == 1
                || column == 2
                || column == 3
                || column == 4
                || column == 5) {
            klass = Boolean.class;
        }

        return klass;

    }

    public void setValueAt(Object value, int row, int col) {

        PermissaoModel com = collection.get(row);

        switch (col) {

            case 1:
                com.setCheckCadastrar(((Boolean) value).booleanValue());
                break;
            case 2:
                com.setCheckAlterar(((Boolean) value).booleanValue());
                break;
            case 3:
                com.setCheckExcluir(((Boolean) value).booleanValue());
                break;
            case 4:
                com.setCheckImpressao(((Boolean) value).booleanValue());
                break;
            case 5:
                com.setCheckOutros(((Boolean) value).booleanValue());
                break;
        }

        fireTableCellUpdated(row, col);

    }
}
