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
public class ColumnModelCompensadorMultiplosCheques extends DefaultTableColumnModel {

    public ColumnModelCompensadorMultiplosCheques(FontMetrics fm) {

        addColumn(criaPrimeiraColuna(0, 50, fm, false, "Opções"));
        addColumn(criaColuna(1, 80, fm, false, "Nº"));
        addColumn(criaColuna(2, 80, fm, false, "Previsão"));
        addColumn(criaColuna(3, 80, fm, false, "Valor"));
        addColumn(criaColuna(4, 300, fm, false, "Título"));
        addColumn(criaColuna(5, 250, fm, false, "Portador"));
        addColumn(criaColunaOculta(6, 0, fm, false, "")); //Guarda o ID do Cheque
        addColumn(criaColunaOculta(7, 0, fm, false, "")); //Guarda o ID do Cheque
        addColumn(criaColunaOculta(8, 0, fm, false, "")); //Guarda o ID do Cheque

    }

    private TableColumn criaColunaOculta(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new CellRendererCompensadorMultiplosCheques());
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
        col.setCellRenderer(new CellRendererCompensadorMultiplosCheques());
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
