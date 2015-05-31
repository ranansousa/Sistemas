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
public class ColumnModelTelaAvisosTitulosReceber extends DefaultTableColumnModel {

    public ColumnModelTelaAvisosTitulosReceber(FontMetrics fm) {

        addColumn(criaColunaLink(0, 80, fm, false, "Ações"));
        addColumn(criaColuna(1, 100, fm, false, "Vencimento"));
        addColumn(criaColuna(2, 150, fm, false, "Nº Documento"));
        addColumn(criaColuna(3, 390, fm, false, "Histórico/Descrição"));
        addColumn(criaColuna(4, 102, fm, false, "Valor"));


    }

    private TableColumn criaColunaLink(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {

        int larguraTitulo = fm.stringWidth(titulo + "  ");
        if (largura < larguraTitulo) {
            largura = larguraTitulo;
        }

        TableColumn col = new TableColumn(columnIndex);
        col.setCellEditor(new CellEditorTelaAvisosReceberTitulos());
        col.setCellRenderer(new CellRendererTelaAvisosReceberTitulos());
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
        col.setCellRenderer(new CellRendererTelaAvisosTitulos());
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
