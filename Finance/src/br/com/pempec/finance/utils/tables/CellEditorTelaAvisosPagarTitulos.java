/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.tables;

import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.view.CadastroTituloPagarBaixa;
import br.com.pempec.finance.view.TelaPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author PEMPEC
 */
public class CellEditorTelaAvisosPagarTitulos extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    JButton button = new JButton("Pagar");
    String numero;
    TituloPagarModel titulo;
    CadastroTituloPagarBaixa baixa;

    public CellEditorTelaAvisosPagarTitulos() {
        button.addActionListener(this);
        button.setBorderPainted(false);
        button.setFont(new java.awt.Font("Arial", 0, 12));
        button.setHorizontalAlignment(JButton.CENTER);
    }

    public Object getCellEditorValue() {
        return Color.BLACK;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        numero = (String) table.getValueAt(row, 2);

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        try {

            if (titulo == null || !numero.equals(titulo.getNumeroDocumento())) {

                titulo = new TituloPagarModel();

                titulo.setPk(new PKModel());
                titulo.getPk().setOrganizacao(Controller.getOrganizacao());
                titulo.setNumeroDocumento(numero);

                titulo = tituloPagarBO.consultarPorTemplate(titulo);

                baixa = new CadastroTituloPagarBaixa(titulo);
                TelaPrincipal.desktopPane.add(baixa);
                baixa.show();

            } else {

                baixa.setVisible(true);
                baixa.moveToFront();
                baixa.requestFocus();

            }

        } catch (ApplicationException ex) {
            ex.printStackTrace();
        } catch (SystemException ex) {
            ex.printStackTrace();
        }


    }
}
