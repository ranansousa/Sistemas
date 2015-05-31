/*
 * CadastroCurso.java
 *
 * Created on 8 de Outubro de 2007, 21:58
 */
package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Extenso;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.TesourariaDebitoTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

public class ReciboTesourariaDespesa extends FinanceInternalFrame implements IRepopulador {

    private TesourariaDebitoBO tesourariaDebitoBO = new TesourariaDebitoBO();
    private long tela = Tela.TELA_TESOURARIA_EMITIR_RECIBO_DESPESA.getTela();

    public ReciboTesourariaDespesa() throws SystemException {

        initComponents();
        Controller.setQtdMovimentosHoje(0);
        botaoGerar.setEnabled(false);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelNumeroDocumento = new javax.swing.JLabel();
        labelCedente = new javax.swing.JLabel();
        labelDescricao = new javax.swing.JLabel();
        labelValorPagar = new javax.swing.JLabel();
        labelDataVencimento = new javax.swing.JLabel();
        comboTituloDebito = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Recibo Despesas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        labelNumeroDocumento.setText("Número do Documento de Débito");

        labelCedente.setFont(new java.awt.Font("Arial", 0, 10));
        labelCedente.setForeground(new java.awt.Color(0, 102, 102));
        labelCedente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cedente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelDescricao.setFont(new java.awt.Font("Arial", 0, 10));
        labelDescricao.setForeground(new java.awt.Color(0, 102, 102));
        labelDescricao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descricao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelValorPagar.setForeground(new java.awt.Color(204, 0, 0));
        labelValorPagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelValorPagar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor da Despesa"));

        labelDataVencimento.setForeground(new java.awt.Color(204, 0, 0));
        labelDataVencimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataVencimento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Movimento"));

        comboTituloDebito.setToolTipText("");
        comboTituloDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloDebitoActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Formato"));

        comboFormato.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFormatoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, 0, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumeroDocumento, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboTituloDebito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCedente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNumeroDocumento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(169, Short.MAX_VALUE)))
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
                        .addGap(149, 149, 149)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Recibos de Débitos";
    }

    public void repopularCombos() {

        try {

            comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

            Collection<TesourariaDebitoModel> lColecaoDebito = tesourariaDebitoBO.obterTodos(organizacaoModel);

            final EventList<TesourariaDebitoModel> lRegistrosDebitos = GlazedLists.eventList(lColecaoDebito);

            if (supportDebito != null && supportDebito.getItemList() != null && supportDebito.getComboBox() != null) {
                supportDebito.uninstall();
            }
            supportDebito = AutoCompleteSupport.install(comboTituloDebito, lRegistrosDebitos, new TesourariaDebitoTextFilterator());
            supportDebito.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportDebito.setStrict(false);

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

        comboTituloDebito.setSelectedItem(null);
        labelCedente.setText("");
        labelDataVencimento.setText("  /  /    ");
        labelDescricao.setText("");
        labelValorPagar.setText("0,00");
        botaoGerar.setEnabled(false);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        return true;
    }

    private void botaoGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarActionPerformed
//GEN-LAST:event_botaoGerarActionPerformed
        if (validaCampos()) {

            try {
                long action = Action.IMPRESSAO.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (tesourariaDebitoModel != null) {

                    new MovimentoDiarioBO().inserir(this.registroMovimento("RCB TES DEB", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do recibo de debito tesouraria " + comboTituloDebito.getSelectedItem().toString(), PempecParse.stringToDouble(labelValorPagar.getText()), "Impresso"));

                    Map<String, Object> parametros = new HashMap<String, Object>();

                    OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                    parametros.put(RelatorioConstantes.PARAMETRO_RAZAO_SOCIAL, organizacaoModel.getRazaoSocial());
                    parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());
                    parametros.put(RelatorioConstantes.PARAMETRO_CNPJ, organizacaoModel.getCnpj());
                    parametros.put(RelatorioConstantes.PARAMETRO_CIDADE, organizacaoModel.getCidade() + ", " + PempecParse.dateToString(new Date()));
                    parametros.put(RelatorioConstantes.PARAMETRO_NUMERO_DOCUMENTO, tesourariaDebitoModel.getNumeroDocumento());
                    parametros.put(RelatorioConstantes.PARAMETRO_VALOR, tesourariaDebitoModel.getValorNominal());
                    parametros.put(RelatorioConstantes.PARAMETRO_VALOR_EXTENSO, new Extenso(tesourariaDebitoModel.getValorNominal()).toString().toUpperCase());
                    if (tesourariaDebitoModel.getCedenteModel() != null) {
                        parametros.put(RelatorioConstantes.PARAMETRO_FAVORECIDO, tesourariaDebitoModel.getCedenteModel().getNome());
                    }
                    parametros.put(RelatorioConstantes.PARAMETRO_DESCRICAO, tesourariaDebitoModel.getHistorico().getDescricao() + " " + tesourariaDebitoModel.getDescricao());

                    switch (comboFormato.getSelectedIndex()) {

                        case 0:
                            new RelatorioUtil().visualizar(RelatorioConstantes.RECIBO_TESOURARIA_DEBITO, parametros);
                            break;
                        case 1:
                            new RelatorioUtil().exportarPDF(RelatorioConstantes.RECIBO_TESOURARIA_DEBITO, parametros);
                            break;
                        case 2:
                            new RelatorioUtil().exportarXLS(RelatorioConstantes.RECIBO_TESOURARIA_DEBITO, parametros);
                            break;
                        case 3:

                            //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                            File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RECIBO_TESOURARIA_DEBITO, parametros);

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

    }

private void comboTituloDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloDebitoActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTituloDebito.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            TesourariaDebitoModel tab = new TesourariaDebitoModel();

            tab.setNumeroDocumento(comboTituloDebito.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = tesourariaDebitoBO.consultarPorTemplate(tab);

            CedenteModel cedente = new CedenteModel();
            cedente.setPk(new PKModel());
            cedente.getPk().setOrganizacao(Controller.getOrganizacao());
            cedente.getPk().setId(tab.getCedenteModel().getPk().getId());


            cedente = new CedenteBO().consultarPorPk(cedente);

            botaoGerar.setEnabled(true);

            tesourariaDebitoModel = tab;

            labelValorPagar.setText(PempecParse.doubleToZero(tab.getValorNominal()));
            labelDescricao.setText(tab.getHistorico() + " " + tab.getDescricao());
            labelDataVencimento.setText(PempecParse.dateToString(tab.getDataMovimento()));
            labelCedente.setText(cedente.getNome());



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
}//GEN-LAST:event_comboTituloDebitoActionPerformed

private void comboFormatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormatoItemStateChanged
    if (comboFormato.getSelectedItem() != null && ((FormatosRelatoriosModel) comboFormato.getSelectedItem()).getDescricao().equalsIgnoreCase(Constantes.ENVIAR_POR_EMAIL)) {
        botaoGerar.setText("Enviar");
    } else {
        botaoGerar.setText("Gerar");
    }    // TODO add your handling code here:
}//GEN-LAST:event_comboFormatoItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JComboBox comboTituloDebito;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelNumeroDocumento;
    private javax.swing.JLabel labelValorPagar;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportDebito;
    private TesourariaDebitoModel tesourariaDebitoModel;
}
