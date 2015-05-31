package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.reports.ReportSaldoBancario;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.ContaBancariaServices;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.SaldoBancario;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.tables.ColumnModelSaldoBancario;
import br.com.pempec.finance.utils.tables.TableModelSaldoBancario;
import java.awt.FontMetrics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class ContaBancariaSaldo extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private long tela = Tela.TELA_CONCILIACAO_SALDO_DAS_CONTAS.getTela();
    private List<SaldoBancario> listaSaldoBancario = new ArrayList<SaldoBancario>();
    private ContaBancariaServices services = new ContaBancariaServices();

    private String NameObject() {
        return (String) "Saldo Bancário";
    }

    public void repopularCombos() {

        comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

    }

    public ContaBancariaSaldo() throws SystemException {

        initComponents();

        // pegar as contas todas
        // para cada uma pegar o saldo
        Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

        try {

            lContas = contaBancariaBO.obterTodos(organizacaoModel);

        } catch (ApplicationException ex) {
            tratamentoExcecao(ex);
        }

        Double valorConsolidado = 0d;
        Double valorCheques = 0d;

        for (ContaBancariaModel contaBancariaModel : lContas) {

            // pegar todas as contas da colecao e consultar por template
            // pegar aquela  conta e setar no model SaldoBancario
            //pegar aquela conta e mandar obter o saldo dela e setar o saldo no model SaldoBancario
            //pegar todos cheques a compensar daquela conta

            if (contaBancariaModel.getPk().getId() != null) {

                contaBancariaModel.getPk().setOrganizacao(organizacaoModel);

                SaldoBancario saldoBancario = new SaldoBancario();
                saldoBancario.setContaBancariaModel(contaBancariaModel);
                saldoBancario.setSaldo(obterSaldoContaBancaria(contaBancariaModel));
                saldoBancario.setCheques(obterTotalChequesACompensar(contaBancariaModel));

                valorConsolidado += saldoBancario.getSaldo();

                valorCheques += saldoBancario.getCheques();


                listaSaldoBancario.add(saldoBancario);

            }



        }

        tableContas.setAutoCreateColumnsFromModel(false);
        tableContas.setModel(new TableModelSaldoBancario(listaSaldoBancario));
        FontMetrics fm = tableContas.getFontMetrics(tableContas.getFont());
        tableContas.setColumnModel(new ColumnModelSaldoBancario(fm));
        tableContas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableContas.getTableHeader().setReorderingAllowed(false);

        jValorConsolidado.setText(PempecParse.doubleToZero(valorConsolidado));
        jValorCheques.setText(PempecParse.doubleToZero(valorCheques));
        jValorDisponivel.setText(PempecParse.doubleToZero(valorConsolidado - valorCheques));

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaCompensarCheque = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableContas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jValorConsolidado = new br.com.pempec.componentes.JDoubleField();
        labelConsolidado1 = new javax.swing.JLabel();
        labelConsolidado2 = new javax.swing.JLabel();
        jValorCheques = new br.com.pempec.componentes.JDoubleField();
        jValorDisponivel = new br.com.pempec.componentes.JDoubleField();
        labelConsolidado = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Saldo Bancário");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

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

        botaoImprimir.setText("Imprimir");
        botaoImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        abaCompensarCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));

        tableContas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        tableContas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ));
        jScrollPane2.setViewportView(tableContas);

        javax.swing.GroupLayout abaCompensarChequeLayout = new javax.swing.GroupLayout(abaCompensarCheque);
        abaCompensarCheque.setLayout(abaCompensarChequeLayout);
        abaCompensarChequeLayout.setHorizontalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                .addContainerGap())
        );
        abaCompensarChequeLayout.setVerticalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Saldo das Contas", abaCompensarCheque);
        abaCompensarCheque.getAccessibleContext().setAccessibleName("abaCompensarCheque");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        labelConsolidado1.setText("Saldo Bancário");

        labelConsolidado2.setText("Cheques");

        labelConsolidado.setText("Disponível");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jValorConsolidado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelConsolidado1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jValorCheques, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelConsolidado2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelConsolidado)
                    .addComponent(jValorDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelConsolidado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jValorCheques, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jValorDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelConsolidado1)
                            .addComponent(labelConsolidado2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jValorConsolidado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        comboFormato.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Visualizar", "PDF", "EXCEL", "Mail" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Double obterSaldoContaBancaria(ContaBancariaModel conta) throws SystemException {

        Double saldo = 0d;

        try {

            services.setContaBancariaModel(conta);
            saldo = services.obterSaldoContaBancaria();

        } catch (ApplicationException ex) {
            tratamentoExcecao(ex);
        }

        return saldo;
    }

    private Double obterTotalChequesACompensar(ContaBancariaModel conta) throws SystemException {

        Double total = 0d;

        try {

            services.setContaBancariaModel(conta);
            total = services.obterTotalChequesACompensar();

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);
        }

        return total;
    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        for (int i = 0; i < tableContas.getRowCount(); i++) {
            tableContas.setValueAt(false, i, 0);
        }

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        boolean validaPreenchimento = false;

        for (int i = 0; i < tableContas.getRowCount(); i++) {
            if (tableContas.getValueAt(i, 0).toString().equals("true")) {
                validaPreenchimento = true;
                break;
            }
        }

        if (!validaPreenchimento) {
            exibeMensagemAviso("Selecione um Item!", null);
            return false;
        }

        return true;
    }

private void botaoImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirActionPerformed

    try {

        long action = Action.OUTROS.getAction();

        if (!Controller.checarPermissao(tela, action)) {
            exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
            return;
        }

        if (validaCampos()) {

            Map<String, Object> parametros = new HashMap<String, Object>();

            parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

            Collection<ReportSaldoBancario> collRel = new ArrayList<ReportSaldoBancario>();

            for (SaldoBancario saldoBancario : listaSaldoBancario) {

                if (saldoBancario.getCheck()) {

                    ReportSaldoBancario bean = new ReportSaldoBancario();

                    bean.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    bean.setCnpj(organizacaoModel.getCnpj());
                    bean.setConta(saldoBancario.getContaBancariaModel().getConta());
                    bean.setTitular(saldoBancario.getContaBancariaModel().getTitular());
                    bean.setData(Controller.getDataServidorBD());
                    bean.setLimite(saldoBancario.getContaBancariaModel().getLimiteCredito());
                    bean.setSaldo(saldoBancario.getSaldo());
                    bean.setSaldoTotal(saldoBancario.getSaldo() + saldoBancario.getContaBancariaModel().getLimiteCredito());
                    bean.setTotalCheques(saldoBancario.getCheques());

                    collRel.add(bean);

                }

            }

            try {


                switch (comboFormato.getSelectedIndex()) {

                    case 0:
                        new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_SALDO_BANCARIO, parametros, collRel);
                        break;
                    case 1:
                        new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_SALDO_BANCARIO, parametros, collRel);
                        break;
                    case 2:
                        new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_SALDO_BANCARIO, parametros, collRel);
                        break;
                    case 3:

                        //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                        File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_SALDO_BANCARIO, parametros, collRel);

                        try {

                            TelaEnvioEmail envioEmail = new TelaEnvioEmail(anexo);
                            TelaPrincipal.desktopPane.add(envioEmail);
                            envioEmail.show();

                        } catch (final SystemException ex) {

                            final File file = PrintScreen.capture();

                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {

                                    tratamentoExcecao(ex, file);

                                }
                            });

                        }
                        break;

                }

            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }

        } else {

            exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

        }

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




}//GEN-LAST:event_botaoImprimirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private br.com.pempec.componentes.JDoubleField jValorCheques;
    private br.com.pempec.componentes.JDoubleField jValorConsolidado;
    private br.com.pempec.componentes.JDoubleField jValorDisponivel;
    private javax.swing.JLabel labelConsolidado;
    private javax.swing.JLabel labelConsolidado1;
    private javax.swing.JLabel labelConsolidado2;
    private javax.swing.JTable tableContas;
    // End of variables declaration//GEN-END:variables
}
