package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TesourariaCreditoBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.reports.FilterReportExtratoTesouraria;
import br.com.pempec.finance.models.reports.ReportExtratoTesouraria;
import br.com.pempec.finance.models.reports.ReportExtratoTesourariaCrossTab;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 * * @author PEMPEC
 */
public class ExtratoTesouraria extends FinanceInternalFrame implements IRepopulador {

    private long tela = Tela.TELA_TESOURARIA_EXTRATO_TESOURARIA.getTela();
    private TesourariaCreditoBO tesourariaCreditoBO = new TesourariaCreditoBO();
    private TesourariaDebitoBO tesourariaDebitoBO = new TesourariaDebitoBO();

    public ExtratoTesouraria() throws SystemException {

        initComponents();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jRCreditos = new javax.swing.JRadioButton();
        jRDebitos = new javax.swing.JRadioButton();
        jRTodos = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        jFTPeriodoInicial = new org.jdesktop.swingx.JXDatePicker();
        jFTPeriodoFinal = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Relatório Extrato Tesouraria");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 1, true), "Tipo Lançamento"));

        buttonGroup1.add(jRCreditos);
        jRCreditos.setText("Créditos");

        buttonGroup1.add(jRDebitos);
        jRDebitos.setText("Débitos");

        buttonGroup1.add(jRTodos);
        jRTodos.setSelected(true);
        jRTodos.setText("Todos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jRCreditos)
                .addGap(18, 18, 18)
                .addComponent(jRDebitos)
                .addGap(18, 18, 18)
                .addComponent(jRTodos)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRCreditos)
                    .addComponent(jRDebitos)
                    .addComponent(jRTodos))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Formato"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, 0, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 1, true), "Período"));

        label2.setText("à");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label2)
                .addGap(18, 18, 18)
                .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoGerar.setMnemonic('I');
        botaoGerar.setText("Gerar");
        botaoGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarActionPerformed(evt);
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {

        return (String) "Relatório Extrato Tesouraria";

    }

    public void repopularCombos() {

        comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        jFTPeriodoFinal.setDate(Controller.getDataServidorBD());

        jFTPeriodoInicial.setDate(Controller.getDataServidorBD());

        comboFormato.setSelectedIndex(0);

        jRTodos.setSelected(true);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (jFTPeriodoInicial.getDate() == null) {
            jFTPeriodoInicial.requestFocus();
            return false;
        }

        if (jFTPeriodoFinal.getDate() == null) {
            jFTPeriodoFinal.requestFocus();
            return false;
        }

        if (jFTPeriodoInicial.getDate().after(jFTPeriodoFinal.getDate())) {
            exibeMensagemAviso("Data Inicial não pode ser superior a data final", null);
            jFTPeriodoInicial.requestFocus();
            return false;
        }

        return true;
    }

    private void botaoGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarActionPerformed

        if (validaCampos()) {

            try {

                long action = Action.IMPRESSAO.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                String tipo = "T";

                if (jRCreditos.isSelected()) {

                    tipo = "C";
                } else if (jRDebitos.isSelected()) {

                    tipo = "D";
                }


                new MovimentoDiarioBO().inserir(this.registroMovimento("EXT TESOURARIA", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do extrato tesouraria ", null, "Impresso"));

                Map<String, Object> parametros = new HashMap<String, Object>();

                String dataInicial = PempecParse.dateToString(jFTPeriodoInicial.getDate());

                String dataFinal = PempecParse.dateToString(jFTPeriodoFinal.getDate());

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL_BARRAS, dataInicial);

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL_BARRAS, dataFinal);

                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                FilterReportExtratoTesouraria filter = new FilterReportExtratoTesouraria();

                filter.setOrganizacao(organizacaoModel.getId());


                filter.setDataInicial(PempecParse.dateToDateMinTime(jFTPeriodoInicial.getDate()));
                
                filter.setDataFinal(PempecParse.dateToDateMaxTime(jFTPeriodoFinal.getDate()));
                
                
                Collection<TesourariaCreditoModel> collCreditos = tesourariaCreditoBO.obterRelatorioExtratoTesouria(filter);

                Collection<TesourariaDebitoModel> collDebitos = tesourariaDebitoBO.obterRelatorioExtratoTesouria(filter);

                Collection<ReportExtratoTesourariaCrossTab> collCrossTab = new ArrayList<ReportExtratoTesourariaCrossTab>();

                Collection<ReportExtratoTesouraria> collection = new ArrayList<ReportExtratoTesouraria>();

                ReportExtratoTesouraria report = new ReportExtratoTesouraria();

                Double totalCredito = tesourariaCreditoBO.obterSaldoAnterior(filter);

                Double totalDebito = tesourariaDebitoBO.obterSaldoAnterior(filter);

                report.setRazaoSocial(organizacaoModel.getRazaoSocial());

                report.setCnpj(organizacaoModel.getCnpj());

                report.setTotalCredito(totalCredito);

                report.setTotalDebito(totalDebito);

                collection.add(report);

                ReportExtratoTesourariaCrossTab aux = new ReportExtratoTesourariaCrossTab();

                aux.setDataMovimento(PempecUtil.dimDayDate(filter.getDataInicial(), 1));

                aux.setDescricao("Saldo Anterior");

                aux.setHistorico("");

                if ((totalCredito == null ? 0d : totalCredito) - (totalDebito == null ? 0d : totalDebito) >= 0) {

                    aux.setTipoLancamento("C");

                } else {

                    aux.setTipoLancamento("D");
                }

                aux.setValor((totalCredito == null ? 0d : totalCredito) - (totalDebito == null ? 0d : totalDebito));

                collCrossTab.add(aux);

                if ("T".equals(tipo) || "C".equals(tipo)) {

                    for (TesourariaCreditoModel credito : collCreditos) {

                        ReportExtratoTesourariaCrossTab tab = new ReportExtratoTesourariaCrossTab();

                        tab.setDataMovimento(credito.getDataMovimento());

                        tab.setDescricao((credito.getSacadoModel() != null && credito.getSacadoModel().getPk() != null) ? credito.getDescricao() + "\n" + credito.getSacadoModel().getNome() : credito.getDescricao());

                        tab.setHistorico((credito.getHistorico() != null && credito.getHistorico().getPk() != null) ? credito.getHistorico().getDescricao() : "");

                        tab.setTipoLancamento(credito.getTipoLancamento());

                        tab.setValor(credito.getValorNominal());

                        collCrossTab.add(tab);

                    }

                }

                if ("T".equals(tipo) || "D".equals(tipo)) {

                    for (TesourariaDebitoModel debito : collDebitos) {

                        ReportExtratoTesourariaCrossTab tab = new ReportExtratoTesourariaCrossTab();

                        tab.setDataMovimento(debito.getDataMovimento());

                        tab.setDescricao((debito.getCedenteModel() != null && debito.getCedenteModel().getPk() != null) ? debito.getDescricao() + "\n" + debito.getCedenteModel().getNome() : debito.getDescricao());

                        tab.setHistorico((debito.getHistorico() != null && debito.getHistorico().getPk() != null) ? debito.getHistorico().getDescricao() : "");

                        tab.setTipoLancamento(debito.getTipoLancamento());

                        tab.setValor(debito.getValorNominal());

                        collCrossTab.add(tab);

                    }

                }

                parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_CROSSTAB, collCrossTab);

                switch (comboFormato.getSelectedIndex()) {

                    case 0:
                        new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_EXTRATO_TESOURARIA, parametros, collection);
                        break;
                    case 1:
                        new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_EXTRATO_TESOURARIA, parametros, collection);
                        break;
                    case 2:
                        new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_EXTRATO_TESOURARIA, parametros, collection);
                        break;
                    case 3:

                        //Fazer tela de selecionar os destinatários. Podem ser múltiplos
                        File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_EXTRATO_TESOURARIA, parametros, collection);

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

        } else {

            exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);
        }

}//GEN-LAST:event_botaoGerarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboFormato;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoFinal;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoInicial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRCreditos;
    private javax.swing.JRadioButton jRDebitos;
    private javax.swing.JRadioButton jRTodos;
    private javax.swing.JLabel label2;
    // End of variables declaration//GEN-END:variables
}
