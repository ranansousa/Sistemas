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
public class ColumnModelCadastroTituloReceberBaixaAC extends DefaultTableColumnModel {

    public ColumnModelCadastroTituloReceberBaixaAC(FontMetrics fm) {

        addColumn(criaPrimeiraColuna(0, 80, fm, false, "Opções"));
        addColumn(criaColuna(1, 370, fm, false, "Acréscimo"));
        addColumn(criaColuna(2, 100, fm, false, "Valor"));
        addColumn(criaColunaOculta(3, 0, fm, false, "")); //Guarda o ID da Tabela Titulo_pagar_baixa_fp
//        addColumn(criaColunaOculta(4, 0, fm, false, "")); // Guarda o ID da Tabela Forma_pagamento


    }

    private TableColumn criaColunaOculta(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new CellRendererCadastroTituloReceberBaixaAC());
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
        col.setCellRenderer(new CellRendererCadastroTituloReceberBaixaAC());
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
