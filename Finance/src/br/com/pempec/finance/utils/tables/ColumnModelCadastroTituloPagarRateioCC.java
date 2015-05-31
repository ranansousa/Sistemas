package br.com.pempec.finance.utils.tables;

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author PEMPEC
 */
public class ColumnModelCadastroTituloPagarRateioCC extends DefaultTableColumnModel {

    public ColumnModelCadastroTituloPagarRateioCC(FontMetrics fm) {

        addColumn(criaPrimeiraColuna(0, 80, fm, false, "Marcar"));
        addColumn(criaColuna(1, 100, fm, false, "Código"));
        addColumn(criaColuna(2, 370, fm, false, "Descrição"));
        addColumn(criaColuna(3, 110, fm, false, "Valor"));
        addColumn(criaColunaOculta(4, 0, fm, false, "")); //Guarda o ID do centro de custo

    }

    private TableColumn criaColunaOculta(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {

        TableColumn col = new TableColumn(columnIndex);
        col.setCellRenderer(new CellRendererCadastroTituloPagarRateioCC());
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
        col.setCellRenderer(new CellRendererCadastroTituloPagarRateioCC());
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
