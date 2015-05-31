package br.com.pempec.finance.utils.tables;

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author PEMPEC
 */
public class ColumnModelListagemTitulosReceber extends DefaultTableColumnModel {

    public ColumnModelListagemTitulosReceber(FontMetrics fm) {

        addColumn(criaPrimeiraColuna(0, 50, fm, false, "Marcar"));
        addColumn(criaColuna(1, 80, fm, false, "Vencimento"));
        addColumn(criaColuna(2, 110, fm, false, "N. Documento"));
        addColumn(criaColuna(3, 110, fm, false, "Valor"));
        addColumn(criaColuna(4, 260, fm, false, "Cedente"));
        addColumn(criaColuna(5, 300, fm, false, "Histórico/Descrição"));
        addColumn(criaColunaOculta(6, 0, fm, false, "")); //Guarda o ID do Cheque
        addColumn(criaColunaOculta(7, 0, fm, false, "")); //Guarda o ID do Cheque
        addColumn(criaColunaOculta(8, 0, fm, false, "")); //Guarda o ID do Cheque

    }

    private TableColumn criaColunaOculta(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new CellRendererListagemTitulosReceber());
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
        col.setCellRenderer(new CellRendererListagemTitulosReceber());
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
