package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.ReportDMS;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
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
public class RelatorioDMS extends FinanceInternalFrame implements IRepopulador {

    private long tela = Tela.TELA_RELATORIOS_DECLARACAO_MENSAL_DE_SERVICOS.getTela();
    private TituloPagarBO tituloPagarBO = new TituloPagarBO();

    public RelatorioDMS() throws SystemException {

        this.setLocation(250, 50);
        Controller.setQtdMovimentosHoje(0);
        initComponents();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        jFTPeriodoInicial = new org.jdesktop.swingx.JXDatePicker();
        jFTPeriodoFinal = new org.jdesktop.swingx.JXDatePicker();
        jPanel7 = new javax.swing.JPanel();
        jRSintetico = new javax.swing.JRadioButton();
        jRAnalitico = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Relatório DMS");
        setPreferredSize(new java.awt.Dimension(1100, 330));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 185));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Parametros"));
        jPanel6.setPreferredSize(new java.awt.Dimension(1050, 125));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true), "Período"));
        jPanel4.setPreferredSize(new java.awt.Dimension(304, 80));

        label2.setText("à");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Tipo"));
        jPanel7.setPreferredSize(new java.awt.Dimension(304, 80));

        jRSintetico.setFont(new java.awt.Font("Tahoma", 0, 12));
        jRSintetico.setSelected(true);
        jRSintetico.setText("Sintético");

        jRAnalitico.setFont(new java.awt.Font("Tahoma", 0, 12));
        jRAnalitico.setText("Analítico");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jRSintetico, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRAnalitico, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRAnalitico)
                    .addComponent(jRSintetico))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Formato"));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel2.setPreferredSize(new java.awt.Dimension(1080, 65));

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
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Relatório DMS";
    }

    public void repopularCombos() {

        comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        comboFormato.setSelectedIndex(0);

        jFTPeriodoFinal.setDate(Controller.getDataServidorBD());
        jFTPeriodoInicial.setDate(Controller.getDataServidorBD());
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


                new MovimentoDiarioBO().inserir(this.registroMovimento("Relatorio DMS", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do RELATORIO DMS ", null, "Impresso"));

                Map<String, Object> parametros = new HashMap<String, Object>();

                String dataInicial = PempecParse.dateToString(jFTPeriodoInicial.getDate());
                String dataFinal = PempecParse.dateToString(jFTPeriodoFinal.getDate());

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL_BARRAS, dataInicial);
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL_BARRAS, dataFinal);

                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                List<TituloPagarModel> coll = tituloPagarBO.obterNotaFiscaisPorPeriodo(organizacaoModel, PempecParse.dateToDateMinTime(jFTPeriodoInicial.getDate()), PempecParse.dateToDateMaxTime(jFTPeriodoFinal.getDate()));

                Collection<ReportDMS> collection = new ArrayList<ReportDMS>();

                for (TituloPagarModel tituloPagarModel : coll) {

                    ReportDMS bean = new ReportDMS();

                    bean.setRazaoSocial(organizacaoModel.getRazaoSocial());

                    bean.setCnpj(organizacaoModel.getCnpj());

                    bean.setAliquota(tituloPagarModel.getNotaFiscal().getAliquota());

                    bean.setCpfCnpj(tituloPagarModel.getCedente().getCpfCnpj());

                    bean.setEmitenteNotaFiscal(tituloPagarModel.getCedente().getNome());

                    bean.setInscricaoEstadual(tituloPagarModel.getCedente().getInscricaoEstadual());

                    bean.setInscricaoMunicipal(tituloPagarModel.getCedente().getInscricaoMunicipal());

                    bean.setDataEmissao(tituloPagarModel.getNotaFiscal().getDataEmissao());

                    bean.setDataRetencaoInss(tituloPagarModel.getNotaFiscal().getDataRetencao());

                    bean.setNumero(tituloPagarModel.getNotaFiscal().getNumero());

                    bean.setRetencao((tituloPagarModel.getNotaFiscal().getDataRetencao() == null ? "Não" : "Sim"));

                    bean.setSerie(tituloPagarModel.getNotaFiscal().getSerie());

                    bean.setSubSerie(tituloPagarModel.getNotaFiscal().getSubSerie());

                    bean.setTipoDocumento(tituloPagarModel.getNotaFiscal().getTipoDocumento().getDescricao());

                    bean.setValorIss(tituloPagarModel.getNotaFiscal().getValorIss());

                    bean.setValorServico(tituloPagarModel.getNotaFiscal().getValor());


                    collection.add(bean);

                }

                if (jRSintetico.isSelected()) {

                    switch (comboFormato.getSelectedIndex()) {

                        case 0:
                            new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_DMS, parametros, collection);
                            break;
                        case 1:
                            new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_DMS, parametros, collection);
                            break;
                        case 2:
                            new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_DMS, parametros, collection);
                            break;
                        case 3:

                            //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                            File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_DMS, parametros, collection);

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
                } else {

                    switch (comboFormato.getSelectedIndex()) {

                        case 0:
                            new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_DMS_ANALITICO, parametros, collection);
                            break;
                        case 1:
                            new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_DMS_ANALITICO, parametros, collection);
                            break;
                        case 2:
                            new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_DMS_ANALITICO, parametros, collection);
                            break;

                        case 3:

                            //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                            File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_DMS_ANALITICO, parametros, collection);

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
        }
}//GEN-LAST:event_comboFormatoItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoFinal;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoInicial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRAnalitico;
    private javax.swing.JRadioButton jRSintetico;
    private javax.swing.JLabel label2;
    // End of variables declaration//GEN-END:variables
}
