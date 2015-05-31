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
public class ColumnModelPesquisaContaContabil extends DefaultTableColumnModel {

    public ColumnModelPesquisaContaContabil(FontMetrics fm) {

        addColumn(criaPrimeiraColuna(0, 40, fm, false, "Opções"));
        addColumn(criaColuna(1, 275, fm, false, "Descrição"));
        addColumn(criaColuna(2, 100, fm, false, "Conta"));
        addColumn(criaColuna(3, 50, fm, false, "Conta Reduziada"));
        addColumn(criaColuna(4, 30, fm, false, "Tipo"));
        addColumn(criaColunaOculta(5, 0, fm, false, ""));

    }

    private TableColumn criaColunaOculta(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new CellRendererPesquisaContaContabil());
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
        col.setCellRenderer(new CellRendererPesquisaContaContabil());
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
