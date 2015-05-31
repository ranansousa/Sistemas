/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.utils.PempecParse;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adm
 */
public class TableModelCadastroTituloReceberRateioCC extends AbstractTableModel {

    private ArrayList<TituloReceberRateioCCModel> collection;

    public TableModelCadastroTituloReceberRateioCC(ArrayList<TituloReceberRateioCCModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        TituloReceberRateioCCModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getCentroCustoModel().getCodigo() != null ? com.getCentroCustoModel().getCodigo() : "";
            case 2:
                return com.getCentroCustoModel().getDescricao() != null ? com.getCentroCustoModel().getDescricao() : "";
            case 3:
                return com.getValor() != null ? PempecParse.doubleToZero(com.getValor()) : "";
            case 4:
                return com.getCentroCustoModel().getPk() != null ? com.getCentroCustoModel().getPk().getId() : "";

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 4;//era 3
    }

    public TituloReceberRateioCCModel getValues(int rowIndex) {
        return collection.get(rowIndex);
    }

    public boolean isCellEditable(int row, int column) {

        if (column == 0 || column == 3) {
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

        TituloReceberRateioCCModel com = collection.get(row);

        if (col == 0) {
            com.setCheck(((Boolean) value).booleanValue());
        } else {
            if (col == 3) {
                com.setValor(PempecParse.stringToDouble((String) value));
            }
        }

        fireTableCellUpdated(row, col);

    }

    public void removeByCentroCusto(CentroCustoModel object) {

        for (TituloReceberRateioCCModel tituloReceberRateioCCModel : collection) {

            if (tituloReceberRateioCCModel.getCentroCustoModel().getPk().getId().equals(object.getPk().getId())) {
                collection.remove(tituloReceberRateioCCModel);
            }

        }

        fireTableDataChanged();
    }
}
