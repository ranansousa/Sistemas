package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.FilterReportTituloPagar;
import br.com.pempec.finance.models.reports.ReportTituloPagar;
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
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class RelatorioCartao extends FinanceInternalFrame implements IRepopulador {

    private CedenteModel cedenteModel = new CedenteModel();
    private Date vencimento = new Date();
    private TipoStatusModel tipoStatusModel = new TipoStatusModel();
    private long tela = Tela.TELA_RELATORIOS_CONTA_A_PAGAR.getTela();

    public RelatorioCartao(CedenteModel cedente, Date vcto, TipoStatusModel status) throws SystemException {

        initComponents();
        Controller.setQtdMovimentosHoje(0);
        cedenteModel = cedente;
        vencimento = PempecParse.dateToDate(vcto);
        tipoStatusModel = status;

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Relatório Cartão");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Formato"));

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
                .addContainerGap()
                .addComponent(comboFormato, 0, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return "Relatório Cartão de Crédito";
    }

    public void repopularCombos() {

        comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));
    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        comboFormato.setSelectedIndex(0);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboFormato.getSelectedItem() == null) {
            comboFormato.requestFocus();
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

                new MovimentoDiarioBO().inserir(this.registroMovimento("REL CARTAO", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do relatorio cartões.", null, "Impresso"));

                FilterReportTituloPagar filtros = new FilterReportTituloPagar();

                Map<String, Object> parametros = new HashMap<String, Object>();

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL_BARRAS, PempecParse.dateToString(vencimento));
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL_BARRAS, PempecParse.dateToString(vencimento));

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL, PempecUtil.converteDataYYYYMMDDTracosHorasMinima(PempecParse.dateToString(vencimento)));
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL, PempecUtil.converteDataYYYYMMDDTracosHorasMaxima(PempecParse.dateToString(vencimento)));

                parametros.put(RelatorioConstantes.PARAMETRO_ID_ORGANIZACAO, organizacaoModel.getId());

                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                filtros.setDataInicial(PempecParse.dateToDateMinTime(vencimento));
                filtros.setDataFinal(PempecParse.dateToDateMaxTime(vencimento));

                filtros.setOrganizacao(organizacaoModel.getId());

                filtros.setStatus(tipoStatusModel.getPk().getId());



                //Pegada dos Filtros - Combos.
                if (cedenteModel != null && cedenteModel.getNome().toString().length() > 0 && cedenteModel.getPk() != null) {
                    filtros.setCedente(cedenteModel.getPk().getId());
                }

                String tipoRel = cedenteModel.getNome().toUpperCase();


                if (tipoStatusModel != null && tipoStatusModel.getPk() != null && !tipoStatusModel.getPk().getId().isEmpty()) {

                    filtros.setStatus(tipoStatusModel.getPk().getId());

                    if (tipoStatusModel.getPk().getId().equalsIgnoreCase(Constantes.STATUS_TITULO_NOVO)) {

                        tipoRel += "  POSIÇÂO DE PAGTO :> " + " EM " + filtros.getStatus();

                    } else {

                        tipoRel += "  POSIÇÂO DE PAGTO :> " + " " + filtros.getStatus();
                    }

                    parametros.put(RelatorioConstantes.PARAMETRO_ID_TIPO_STATUS, tipoRel);

                } else {

                    tipoRel += "   POSIÇÂO DE PAGTO :>  CONSOLIDADO!";
                    parametros.put(RelatorioConstantes.PARAMETRO_ID_TIPO_STATUS, tipoRel);
                }

                List<TituloPagarModel> coll = new TituloPagarBO().obterRelatorio(filtros);

                if (coll.size() < 1) {

                    exibeMensagemAviso("Não existem registros", "Registros");


                } else {

                    Collection<ReportTituloPagar> collection = new ArrayList<ReportTituloPagar>();

                    for (TituloPagarModel tituloPagarModel : coll) {

                        ReportTituloPagar bean = new ReportTituloPagar();

                        bean.setCedente(tituloPagarModel.getCedente().getNome());

                        bean.setCentroCusto(tituloPagarModel.getCentroCusto().getDescricao());

                        bean.setCnpj(organizacaoModel.getCnpj());

                        bean.setDataVencimento(tituloPagarModel.getDataVencimento());

                        bean.setDescricao(tituloPagarModel.getDescricao());

                        bean.setHistorico(tituloPagarModel.getHistorico().getDescricao());

                        bean.setNumeroDocumento(tituloPagarModel.getNumeroDocumento());

                        bean.setParcela(tituloPagarModel.getParcela());

                        bean.setRazaoSocial(organizacaoModel.getRazaoSocial());

                        if (tituloPagarModel.getStatus().getPk().getId().equals(Constantes.STATUS_TITULO_PARCIAL)) {

                            bean.setValorNominal(tituloPagarModel.getValorAntecipado());

                        } else {

                            bean.setValorNominal(tituloPagarModel.getValorNominal());
                        }

                        collection.add(bean);

                    }

                    if (comboFormato.getSelectedItem() != null) {

                        switch (comboFormato.getSelectedIndex()) {

                            case 0:
                                new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_CONTAS_PAGAR, parametros, collection);
                                break;
                            case 1:
                                new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_CONTAS_PAGAR, parametros, collection);
                                break;
                            case 2:
                                new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_CONTAS_PAGAR, parametros, collection);
                                break;

                            case 3:

                                //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                                File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_CONTAS_PAGAR, parametros, collection);

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

                        exibeMensagemAviso("Selecion um formato", "Formato");
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private AutoCompleteSupport supportCedente;
    private AutoCompleteSupport supportHistorico;
}
