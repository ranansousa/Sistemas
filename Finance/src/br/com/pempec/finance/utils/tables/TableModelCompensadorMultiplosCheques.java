/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.utils.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adm
 */
public class TableModelCompensadorMultiplosCheques extends AbstractTableModel {

    private List<TituloPagarBaixaChequeModel> collection;

    public TableModelCompensadorMultiplosCheques(List<TituloPagarBaixaChequeModel> collection) {
        this.collection = collection;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        TituloPagarBaixaChequeModel com = collection.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return com.getCheck();
            case 1:
                return com.getContaBancariaCheque() != null ? com.getContaBancariaCheque().getNumeroCheque() : "";
            case 2:
                return com.getContaBancariaCheque() != null ? PempecParse.dateToString(com.getContaBancariaCheque().getDataPrevisaoCompensacao()) : "";
            case 3:
                return com.getContaBancariaCheque() != null ? PempecParse.doubleToZero(com.getContaBancariaCheque().getValor()) : "";
            case 4:
                return (com.getTituloPagarBaixa() != null && com.getTituloPagarBaixa().getTitulo() != null && com.getTituloPagarBaixa().getTitulo().getHistorico() != null) ? com.getTituloPagarBaixa().getTitulo().getHistorico().getDescricao() + " " + com.getTituloPagarBaixa().getTitulo().getDescricao() : "";
            case 5:
                return (com.getTituloPagarBaixa() != null && com.getTituloPagarBaixa().getTitulo() != null && com.getTituloPagarBaixa().getTitulo().getCedente() != null) ? com.getTituloPagarBaixa().getTitulo().getCedente().getNome() : "";
            case 6:
                return com.getContaBancariaCheque() != null ? com.getContaBancariaCheque() : "";
            case 7:
                return com.getTipoOperacaoBancaria() != null ? com.getTipoOperacaoBancaria() : null;
            case 8:
                return (com.getTituloPagarBaixa() != null && com.getTituloPagarBaixa().getTitulo() != null) ? com.getTituloPagarBaixa().getTitulo() : null;

        }

        return null;

    }

    public int getRowCount() {

        return collection.size();
    }

    public int getColumnCount() {
        return 9;
    }

    public TituloPagarBaixaChequeModel getValues(int rowIndex) {
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

        TituloPagarBaixaChequeModel com = collection.get(row);

        com.setCheck(((Boolean) value).booleanValue());

        fireTableCellUpdated(row, col);

    }
}
