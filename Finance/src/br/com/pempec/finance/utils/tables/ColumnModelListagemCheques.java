/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author PEMPEC
 */
public class ColumnModelListagemCheques extends DefaultTableColumnModel {

    public ColumnModelListagemCheques(FontMetrics fm) {

        addColumn(criaPrimeiraColuna(0, 50, fm, false, "Marcar"));
        addColumn(criaColuna(1, 80, fm, false, "Data"));
        addColumn(criaColuna(2, 100, fm, false, "Valor"));
        addColumn(criaColuna(3, 130, fm, false, "Banco"));
        addColumn(criaColuna(4, 60, fm, false, "Agencia"));
        addColumn(criaColuna(5, 80, fm, false, "Conta"));
        addColumn(criaColuna(6, 90, fm, false, "Cheque"));
        addColumn(criaColuna(7, 300, fm, false, "Titular"));

        addColumn(criaColunaOculta(8, 0, fm, false, "")); //Guarda o ID do Cheque
        addColumn(criaColunaOculta(9, 0, fm, false, "")); //Guarda o ID do Cheque
        addColumn(criaColunaOculta(10, 0, fm, false, "")); //Guarda o ID do Cheque

    }

    private TableColumn criaColunaOculta(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new CellRendererListagemCheques());
        col.setPreferredWidth(largura);
        if (!resizable) {

            col.setMaxWidth(largura);
            col.setMinWidth(largura);
        }
        col.setResizable(resizable);

        return col;


    }

    private TableColumn criaPrimeiraColuna(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {

        int larguraTitulo = fm.stringWidth(titulo + "  ");
        if (largura < larguraTitulo) {
            largura = larguraTitulo;
        }

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new CheckBoxCellRenderer());
        col.setHeaderRenderer(null);
        col.setHeaderValue(titulo);
        col.setPreferredWidth(largura);


        if (!resizable) {

            col.setMaxWidth(largura);
            col.setMinWidth(largura);
        }
        col.setResizable(resizable);

        return col;

    }

    private TableColumn criaColuna(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {

        int larguraTitulo = fm.stringWidth(titulo + "  ");
        if (largura < larguraTitulo) {
            largura = larguraTitulo;
        }

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new CellRendererListagemCheques());
        col.setHeaderRenderer(null);
        col.setHeaderValue(titulo);
        col.setPreferredWidth(largura);
        if (!resizable) {

            col.setMaxWidth(largura);
            col.setMinWidth(largura);
        }
        col.setResizable(resizable);
        return col;
    }
}
