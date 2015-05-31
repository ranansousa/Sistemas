package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.reports.FilterReportFluxoCaixa;
import br.com.pempec.finance.models.reports.ReportFluxoCaixa;
import br.com.pempec.finance.models.reports.ReportFluxoCaixaPagar;
import br.com.pempec.finance.models.reports.ReportFluxoCaixaReceber;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class RelatorioFluxoCaixa extends FinanceInternalFrame implements IRepopulador {

    private long tela = Tela.TELA_RELATORIOS_FLUXO_DE_CAIXA.getTela();

    public RelatorioFluxoCaixa() throws SystemException {

        Controller.setQtdMovimentosHoje(0);
        initComponents();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        jFTPeriodoInicial = new org.jdesktop.swingx.JXDatePicker();
        jFTPeriodoFinal = new org.jdesktop.swingx.JXDatePicker();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Relatório Fluxo Caixa");
        setPreferredSize(new java.awt.Dimension(960, 330));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));
        jPanel1.setPreferredSize(new java.awt.Dimension(939, 180));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Parametros"));
        jPanel6.setPreferredSize(new java.awt.Dimension(1050, 125));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Período"));
        jPanel4.setPreferredSize(new java.awt.Dimension(304, 80));

        label2.setText("à");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Formato"));
        jPanel5.setPreferredSize(new java.awt.Dimension(304, 80));

        comboFormato.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFormatoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel2.setPreferredSize(new java.awt.Dimension(939, 65));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Relatório Fluxo de Caixa";
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

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (jFTPeriodoFinal.getDate() == null) {
            jFTPeriodoFinal.requestFocus();
            return false;
        }

        if (jFTPeriodoInicial.getDate() == null) {
            jFTPeriodoInicial.requestFocus();
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


                new MovimentoDiarioBO().inserir(this.registroMovimento("FLuxo Caixa", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do relatorio fluxo de caixa.", null, "Impresso"));

                FilterReportFluxoCaixa filtros = new FilterReportFluxoCaixa();

                Map<String, Object> parametros = new HashMap<String, Object>();

                String dataInicial = PempecParse.dateToString(jFTPeriodoInicial.getDate());
                String dataFinal = PempecParse.dateToString(jFTPeriodoFinal.getDate());
                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL_BARRAS, dataInicial);
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL_BARRAS, dataFinal);

                //Pegada para a Data funcionar em Qualquer Banco de Dados
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL, PempecUtil.converteDataYYYYMMDDTracosHorasMinima(dataInicial));
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL, PempecUtil.converteDataYYYYMMDDTracosHorasMaxima(dataFinal));

                filtros.setDataInicial(PempecParse.dateToDateMinTime(jFTPeriodoInicial.getDate()));
                filtros.setDataFinal(PempecParse.dateToDateMaxTime(jFTPeriodoFinal.getDate()));

                filtros.setOrganizacao(organizacaoModel.getId());

                //Pegada dos Filtros - Combos.

                List<TituloPagarModel> collPagar = new TituloPagarBO().obterRelatorioFluxo(filtros);
                List<TituloReceberModel> collReceber = new TituloReceberBO().obterRelatorioFluxo(filtros);

                Collection<ReportFluxoCaixa> collection = new ArrayList<ReportFluxoCaixa>();

                Collection<ReportFluxoCaixaPagar> collectionPagar = new ArrayList<ReportFluxoCaixaPagar>();

                Collection<ReportFluxoCaixaReceber> collectionReceber = new ArrayList<ReportFluxoCaixaReceber>();

                ReportFluxoCaixa bean = new ReportFluxoCaixa();

                bean.setCnpj(organizacaoModel.getCnpj());

                bean.setRazaoSocial(organizacaoModel.getRazaoSocial());

                collection.add(bean);

                for (TituloPagarModel tituloPagarModel : collPagar) {

                    ReportFluxoCaixaPagar beanPagar = new ReportFluxoCaixaPagar();

                    beanPagar.setCentroCustoPagar(tituloPagarModel.getCentroCusto().getDescricao());

                    beanPagar.setDataVencimentoPagar(tituloPagarModel.getDataVencimento());

                    beanPagar.setDescricaoPagar(tituloPagarModel.getDescricao());

                    beanPagar.setHistoricoPagar(tituloPagarModel.getHistorico().getDescricao());

                    beanPagar.setNumeroDocumentoPagar(tituloPagarModel.getNumeroDocumento());

                    beanPagar.setParcelaPagar(tituloPagarModel.getParcela());

                    beanPagar.setValorNominalPagar(tituloPagarModel.getValorNominal());

                    beanPagar.setLocalPagamentoPagar(tituloPagarModel.getLocalPagamento().getDescricao());

                    beanPagar.setTipoCobrancaPagar(tituloPagarModel.getTipoCobranca().getDescricao());

                    collectionPagar.add(beanPagar);

                }

                for (TituloReceberModel tituloReceberModel : collReceber) {

                    ReportFluxoCaixaReceber beanReceber = new ReportFluxoCaixaReceber();

                    beanReceber.setCentroCustoReceber(tituloReceberModel.getCentroCusto().getDescricao());

                    beanReceber.setDataVencimentoReceber(tituloReceberModel.getDataVencimento());

                    beanReceber.setDescricaoReceber(tituloReceberModel.getDescricao());

                    beanReceber.setHistoricoReceber(tituloReceberModel.getHistorico().getDescricao());

                    beanReceber.setNumeroDocumentoReceber(tituloReceberModel.getNumeroDocumento());

                    beanReceber.setParcelaReceber(tituloReceberModel.getParcela());

                    beanReceber.setValorNominalReceber(tituloReceberModel.getValorNominal());

                    beanReceber.setLocalPagamentoReceber(tituloReceberModel.getLocalPagamento().getDescricao());

                    beanReceber.setTipoCobrancaReceber(tituloReceberModel.getTipoCobranca().getDescricao());

                    collectionReceber.add(beanReceber);

                }

                parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_RECEBER, collectionReceber);
                parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_PAGAR, collectionPagar);

                switch (comboFormato.getSelectedIndex()) {

                    case 0:
                        new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_FLUXO_CAIXA, parametros, collection);
                        break;
                    case 1:
                        new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_FLUXO_CAIXA, parametros, collection);
                        break;
                    case 2:
                        new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_FLUXO_CAIXA, parametros, collection);
                        break;
                    case 3:

                        //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                        File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_FLUXO_CAIXA, parametros, collection);

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

    private void comboFormatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormatoItemStateChanged
        if (comboFormato.getSelectedItem() != null && ((FormatosRelatoriosModel) comboFormato.getSelectedItem()).getDescricao().equalsIgnoreCase(Constantes.ENVIAR_POR_EMAIL)) {
            botaoGerar.setText("Enviar");
        } else {
            botaoGerar.setText("Gerar");
        }        // TODO add your handling code here:
}//GEN-LAST:event_comboFormatoItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoFinal;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoInicial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel label2;
    // End of variables declaration//GEN-END:variables
}
