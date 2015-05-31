package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.LoteDepositoBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaChequeBO;
import br.com.pempec.finance.businessObjects.UsuarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.models.reports.ReciboDepositoCheques;
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
import java.util.Set;
import javax.swing.SwingUtilities;

/**
 *
 * @author Administrador
 */
public class RelatorioLoteDeposito extends FinanceInternalFrame implements IRepopulador {

    private static long tela = Tela.TELA_RELATORIOS_EXPORTACAO_MEGACONTABIL.getTela();
    private UsuarioBO usuarioBO = new UsuarioBO();
    private LoteDepositoBO loteDepositoBO = new LoteDepositoBO();
    private List<ReciboDepositoCheques> dadosExportados;
    private TituloReceberBaixaChequeBO tituloReceberBaixaChequeBO = new TituloReceberBaixaChequeBO();

    public RelatorioLoteDeposito() throws SystemException {
        initComponents();
        Controller.setQtdMovimentosHoje(0);

        campoLoteContabil.setVisible(false);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        comboLoteContabil = new javax.swing.JComboBox();
        campoLoteContabil = new javax.swing.JTextField();
        labelData = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelDataUltimaAtualizacao = new javax.swing.JLabel();
        labelSituacao = new javax.swing.JLabel();
        labelCheque = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Exportação Mega Contábil");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Formato"));

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
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Número do Lote");

        comboLoteContabil.setToolTipText("");
        comboLoteContabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoteContabilActionPerformed(evt);
            }
        });

        campoLoteContabil.setEditable(false);

        labelData.setFont(new java.awt.Font("Arial", 0, 10));
        labelData.setForeground(new java.awt.Color(0, 102, 102));
        labelData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelData.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Data Exportação"));

        labelUsuario.setFont(new java.awt.Font("Arial", 0, 10));
        labelUsuario.setForeground(new java.awt.Color(0, 102, 102));
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Usuário"));

        labelDataUltimaAtualizacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelDataUltimaAtualizacao.setForeground(new java.awt.Color(0, 102, 102));
        labelDataUltimaAtualizacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataUltimaAtualizacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Data Ult. Atualização"));

        labelSituacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelSituacao.setForeground(new java.awt.Color(0, 102, 102));
        labelSituacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSituacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Situação"));

        labelCheque.setFont(new java.awt.Font("Arial", 0, 10));
        labelCheque.setForeground(new java.awt.Color(102, 0, 102));
        labelCheque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Cheque"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataUltimaAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDataUltimaAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(labelCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(23, 23, 23)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Relatório Lote Depósito";
    }

    public void repopularCombos() {

        try {

            Collection<LoteDepositoModel> lLoteDeposito = new ArrayList<LoteDepositoModel>();

            LoteDepositoModel loteDepositoModel = new LoteDepositoModel();

            loteDepositoModel.setLote(" -> Selecione <- ");

            lLoteDeposito.add(loteDepositoModel);

            lLoteDeposito.addAll(loteDepositoBO.obterTodos(organizacaoModel));

            comboLoteContabil.setModel(new javax.swing.DefaultComboBoxModel(lLoteDeposito.toArray()));

            comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

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

    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        campoLoteContabil.setVisible(false);
        comboLoteContabil.setSelectedIndex(0);
        campoLoteContabil.setText("");
        labelData.setText("");
        labelDataUltimaAtualizacao.setText("");
        labelUsuario.setText("");
        labelCheque.setText("");
        labelSituacao.setText("");
        botaoGerar.setEnabled(false);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboLoteContabil.getSelectedIndex() == 0) {
            comboLoteContabil.requestFocus();
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

                new MovimentoDiarioBO().inserir(this.registroMovimento("REL LOTE DEP", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do relatorio lote deposito.", null, "Impresso"));

                Map<String, Object> parametros = new HashMap<String, Object>();

                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                this.dadosExportados = new ArrayList<ReciboDepositoCheques>();

                LoteDepositoModel loteDepositoModel = new LoteDepositoModel();

                loteDepositoModel.setPk(new PKModel());
                loteDepositoModel.getPk().setOrganizacao(Controller.getOrganizacao());
                loteDepositoModel.setLote(comboLoteContabil.getSelectedItem().toString());

                loteDepositoModel = loteDepositoBO.consultarPorTemplate(loteDepositoModel);

                Collection<TituloReceberBaixaChequeModel> collCheques = tituloReceberBaixaChequeBO.obterPorLoteDeposito(loteDepositoModel);


                for (TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel : collCheques) {

                    ReciboDepositoCheques bean = new ReciboDepositoCheques();

                    bean.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    bean.setCnpj(organizacaoModel.getCnpj());
                    bean.setLote(loteDepositoModel.getLote());
                    bean.setDataDeposito(loteDepositoModel.getDataRegistro());
                    bean.setAgencia(tituloReceberBaixaChequeModel.getAgencia());
                    bean.setBanco(tituloReceberBaixaChequeModel.getBanco().getNomeBanco());
                    bean.setCheque(tituloReceberBaixaChequeModel.getNumeroCheque());
                    bean.setConta(tituloReceberBaixaChequeModel.getConta());
                    bean.setData(tituloReceberBaixaChequeModel.getDataProtocolo());
                    bean.setTitular(tituloReceberBaixaChequeModel.getTitular());
                    bean.setValor(tituloReceberBaixaChequeModel.getValor());

                    dadosExportados.add(bean);

                }

                switch (comboFormato.getSelectedIndex()) {

                    case 0:
                        new RelatorioUtil().visualizar(RelatorioConstantes.RECIBO_DEPOSITOS_CHEQUES, parametros, dadosExportados);
                        break;
                    case 1:
                        new RelatorioUtil().exportarPDF(RelatorioConstantes.RECIBO_DEPOSITOS_CHEQUES, parametros, dadosExportados);
                        break;
                    case 2:
                        new RelatorioUtil().exportarXLS(RelatorioConstantes.RECIBO_DEPOSITOS_CHEQUES, parametros, dadosExportados);
                        break;

                    case 3:

                        //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                        File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RECIBO_DEPOSITOS_CHEQUES, parametros, dadosExportados);

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
        }
    }//GEN-LAST:event_comboFormatoItemStateChanged

    private void comboLoteContabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoteContabilActionPerformed

        if (comboLoteContabil.getSelectedIndex() != 0) {

            try {

                LoteDepositoModel lote = new LoteDepositoModel();
                lote.setPk(new PKModel());
                lote.getPk().setOrganizacao(organizacaoModel);
                lote.setLote(comboLoteContabil.getSelectedItem().toString());

                lote = loteDepositoBO.consultarPorTemplate(lote);

                if (lote != null && lote.getPk().getId() != null) {

                    if (lote.getStatus().equalsIgnoreCase("removido")) {
                        botaoGerar.setEnabled(false);
                    } else {
                        botaoGerar.setEnabled(true);
                    }

                    if (lote.getUsuario() != null) {
                        UsuarioModel usuarioModel = lote.getUsuario();

                        usuarioModel = usuarioBO.consultarPorTemplate(usuarioModel);
                        labelUsuario.setText(usuarioModel.getNome());
                    }

                    campoLoteContabil.setText(lote.getPk().getId());
                    labelData.setText(PempecParse.dateToString(lote.getDataRegistro()));
                    labelDataUltimaAtualizacao.setText(PempecParse.dateToString(lote.getDataAtualizacao()));
                    long qtdCheque = lote.getQtdCheque();
                    labelCheque.setText(String.valueOf(qtdCheque));
                    labelSituacao.setText(lote.getStatus());

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


        }
    }//GEN-LAST:event_comboLoteContabilActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoLoteContabil;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JComboBox comboLoteContabil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelCheque;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDataUltimaAtualizacao;
    private javax.swing.JLabel labelSituacao;
    private javax.swing.JLabel labelUsuario;
    // End of variables declaration//GEN-END:variables

    private Double calculaAcrescimoDeducoesPagar(Set<TituloPagarBaixaAcrescimoModel> acrescimos, Set<TituloPagarBaixaDeducaoModel> deducoes) {

        Double retorno = 0d;

        for (TituloPagarBaixaAcrescimoModel acrescimo : acrescimos) {

            retorno = retorno + acrescimo.getValor();

        }

        for (TituloPagarBaixaDeducaoModel deducao : deducoes) {

            retorno = retorno - deducao.getValor();

        }


        return retorno;

    }

    private Double calculaAcrescimoDeducoesReceber(Set<TituloReceberBaixaAcrescimoModel> acrescimos, Set<TituloReceberBaixaDeducaoModel> deducoes) {

        Double retorno = 0d;

        for (TituloReceberBaixaAcrescimoModel acrescimo : acrescimos) {

            retorno = retorno + acrescimo.getValor();

        }

        for (TituloReceberBaixaDeducaoModel deducao : deducoes) {

            retorno = retorno - deducao.getValor();

        }


        return retorno;

    }
}
