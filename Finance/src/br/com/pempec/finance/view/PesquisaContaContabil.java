package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaContabilBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IPopUpContaContabil;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.tables.ColumnModelPesquisaContaContabil;
import br.com.pempec.finance.utils.tables.TableModelPesquisaContaContabil;
import java.awt.FontMetrics;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class PesquisaContaContabil extends FinanceInternalFrame implements IRepopulador {

    private ContaContabilBO contaContabilBO = new ContaContabilBO();
    private IPopUpContaContabil origem;
    private String tipo;

    private String NameObject() {
        return (String) "Pesquisa Conta Contábil";
    }

    public void repopularCombos() {
    }

    public PesquisaContaContabil(IPopUpContaContabil origem, String tipo, Point point) throws SystemException {

        this.setLocation(point.x + 50, point.y + 35); //fica um pouco alem da tela que chamou
        Controller.setQtdMovimentosHoje(0);
        initComponents();

        this.origem = origem;
        this.tipo = tipo;

        jTDescricao.setDocument(new Documento(80));
        jTConta.setDocument(new Documento(14));
        jTContaReduzida.setDocument(new Documento(8));

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaCompensarCheque = new javax.swing.JPanel();
        labelDataVencimento = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableContaContabil = new javax.swing.JTable();
        jTConta = new javax.swing.JTextField();
        jTContaReduzida = new javax.swing.JTextField();
        labelDataVencimento2 = new javax.swing.JLabel();
        botaoPesquisar = new javax.swing.JButton();
        jTDescricao = new javax.swing.JTextField();
        labelDataVencimento3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoImprimir = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Pesquisa Conta Contábil");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        abaCompensarCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));

        labelDataVencimento.setText("Descrição");

        tableContaContabil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ));
        jScrollPane2.setViewportView(tableContaContabil);

        labelDataVencimento2.setText("Conta");

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        labelDataVencimento3.setText("Conta Reduzida");

        javax.swing.GroupLayout abaCompensarChequeLayout = new javax.swing.GroupLayout(abaCompensarCheque);
        abaCompensarCheque.setLayout(abaCompensarChequeLayout);
        abaCompensarChequeLayout.setHorizontalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataVencimento)
                            .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataVencimento2)
                            .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataVencimento3)
                            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                .addComponent(jTContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        abaCompensarChequeLayout.setVerticalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addComponent(labelDataVencimento)
                        .addGap(7, 7, 7)
                        .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addComponent(labelDataVencimento2)
                        .addGap(7, 7, 7)
                        .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addComponent(labelDataVencimento3)
                        .addGap(7, 7, 7)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pesquisa Conta Contábil", abaCompensarCheque);
        abaCompensarCheque.getAccessibleContext().setAccessibleName("abaCompensarCheque");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoImprimir.setMnemonic('I');
        botaoImprimir.setText("Selecionar");
        botaoImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirActionPerformed(evt);
            }
        });

        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        botaoLimpar.setMnemonic('L');
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(botaoImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        tableContaContabil.setAutoCreateColumnsFromModel(false);
        tableContaContabil.setModel(new TableModelPesquisaContaContabil(new ArrayList<ContaContabilModel>()));
        FontMetrics fm = tableContaContabil.getFontMetrics(tableContaContabil.getFont());
        tableContaContabil.setColumnModel(new ColumnModelPesquisaContaContabil(fm));
        tableContaContabil.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableContaContabil.getTableHeader().setReorderingAllowed(false);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        int cont = 0;

        for (int i = 0; i < tableContaContabil.getRowCount(); i++) {
            if (tableContaContabil.getValueAt(i, 0).toString().equals("true")) {
                cont++;
            }
        }

        if (cont == 0) {
            exibeMensagemAviso("Selecione um Item!", null);
            return false;
        }

        if (cont > 1) {
            exibeMensagemAviso("Selecione apenas 01(HUM) Item!", null);
            return false;
        }

        return true;
    }

private void botaoImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirActionPerformed

    if (validaCampos()) {

        for (int i = 0; i < tableContaContabil.getRowCount(); i++) {
            if (tableContaContabil.getValueAt(i, 0).toString().equals("true")) {
                origem.setPesquisaContaContabil((ContaContabilModel) tableContaContabil.getValueAt(i, 5), tipo);
                break;
            }
        }

        try {
            setClosed(true);
            dispose();
        } catch (Exception ex) {
        }

    } else {

        exibeMensagemAviso("Campo(s) obrigatório(s).", null);

    }

}//GEN-LAST:event_botaoImprimirActionPerformed

private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed

    try {

        ContaContabilModel contaContabil = new ContaContabilModel();

        contaContabil.setPk(new PKModel());
        contaContabil.getPk().setOrganizacao(organizacaoModel);
        contaContabil.setDescricao(jTDescricao.getText().trim().toUpperCase());
        contaContabil.setConta(jTConta.getText().trim().toUpperCase());
        contaContabil.setContaReduzida(jTContaReduzida.getText().trim().toUpperCase());
        contaContabil.setTipo(tipo);

        ArrayList<ContaContabilModel> lContaContabil = (ArrayList<ContaContabilModel>) contaContabilBO.obterPorFiltro(contaContabil);

        tableContaContabil.setAutoCreateColumnsFromModel(false);
        tableContaContabil.setModel(new TableModelPesquisaContaContabil(lContaContabil));
        FontMetrics fm = tableContaContabil.getFontMetrics(tableContaContabil.getFont());
        tableContaContabil.setColumnModel(new ColumnModelPesquisaContaContabil(fm));
        tableContaContabil.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableContaContabil.getTableHeader().setReorderingAllowed(false);

    } catch (ApplicationException ex) {

        tratamentoExcecao(ex);

    } catch (final SystemException ex) {

        final File file = PrintScreen.capture();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                tratamentoExcecao(ex, file);

            }
        });

    }


}//GEN-LAST:event_botaoPesquisarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTConta;
    private javax.swing.JTextField jTContaReduzida;
    private javax.swing.JTextField jTDescricao;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDataVencimento2;
    private javax.swing.JLabel labelDataVencimento3;
    private javax.swing.JTable tableContaContabil;
    // End of variables declaration//GEN-END:variables
}
