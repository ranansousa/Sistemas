/*
 * CadastroCurso.java
 *
 * Created on 8 de Outubro de 2007, 21:58
 */
package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaChequeBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
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
import br.com.pempec.finance.utils.iterators.ChequeTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

public class ReciboCopiaCheque extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaChequeBO chequeBO = new ContaBancariaChequeBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private HistoricoBO historicoBO = new HistoricoBO();
    private TituloPagarBaixaChequeBO tituloPagarBaixaChequeBO = new TituloPagarBaixaChequeBO();
    private long tela = Tela.TELA_CONCILIACAO_COPIA_DE_CHEQUES.getTela();

    public ReciboCopiaCheque() throws SystemException {

        initComponents();
        Controller.setQtdMovimentosHoje(0);
        botaoGerar.setEnabled(false);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelPortador = new javax.swing.JLabel();
        comboContaBancaria = new javax.swing.JComboBox();
        labelConta = new javax.swing.JLabel();
        labelCheque = new javax.swing.JLabel();
        labelDataGeracao = new javax.swing.JLabel();
        labelEmissao = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        labelVencimento = new javax.swing.JLabel();
        labelValorCheque = new javax.swing.JLabel();
        comboCheque = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Recibo Cópia de Cheques");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        labelPortador.setFont(new java.awt.Font("Arial", 0, 10));
        labelPortador.setForeground(new java.awt.Color(0, 102, 102));
        labelPortador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Portador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 11));
        comboContaBancaria.setForeground(new java.awt.Color(255, 153, 0));
        comboContaBancaria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaBancariaItemStateChanged(evt);
            }
        });

        labelConta.setText("Conta");

        labelCheque.setText("Cheque");

        labelDataGeracao.setBackground(new java.awt.Color(222, 218, 210));
        labelDataGeracao.setFont(new java.awt.Font("Arial", 1, 10));
        labelDataGeracao.setForeground(new java.awt.Color(0, 153, 153));
        labelDataGeracao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gerado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelEmissao.setBackground(new java.awt.Color(222, 218, 210));
        labelEmissao.setFont(new java.awt.Font("Arial", 1, 10));
        labelEmissao.setForeground(new java.awt.Color(0, 153, 153));
        labelEmissao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Emissão", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelStatus.setBackground(new java.awt.Color(222, 218, 210));
        labelStatus.setFont(new java.awt.Font("Arial", 1, 10));
        labelStatus.setForeground(new java.awt.Color(0, 153, 153));
        labelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelVencimento.setBackground(new java.awt.Color(222, 218, 210));
        labelVencimento.setFont(new java.awt.Font("Arial", 1, 10));
        labelVencimento.setForeground(new java.awt.Color(0, 153, 153));
        labelVencimento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vencimento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelValorCheque.setBackground(new java.awt.Color(222, 218, 210));
        labelValorCheque.setFont(new java.awt.Font("Arial", 1, 10));
        labelValorCheque.setForeground(new java.awt.Color(0, 153, 153));
        labelValorCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        comboCheque.setFont(new java.awt.Font("Arial", 0, 10));
        comboCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboChequeActionPerformed(evt);
            }
        });

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
                .addComponent(comboFormato, 0, 262, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDataGeracao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(labelValorCheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(labelPortador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelConta))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCheque)
                                    .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(151, 151, 151))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelConta)
                    .addComponent(labelCheque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(labelPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDataGeracao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(labelValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
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
                .addGap(103, 103, 103)
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Cópia de Cheques";
    }

    public void repopularCombos() {

        try {
            //conta Bancaria

            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Selecione <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancaria.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

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

    private Boolean validaCampos() {

        if (comboContaBancaria.getSelectedIndex() == 0) {
            comboContaBancaria.requestFocus();
            return false;
        }

        if (comboCheque.getSelectedItem() == null) {
            comboCheque.requestFocus();
            return false;
        }

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

                if (contaBancariaChequeModel != null) {

                    new MovimentoDiarioBO().inserir(this.registroMovimento("COPIA CHEQUE", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao da copia do cheque " + comboCheque.getSelectedItem().toString(), PempecParse.stringToDouble(labelValorCheque.getText()), "Impresso"));

                    Map<String, Object> parametros = new HashMap<String, Object>();

                    OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                    parametros.put(RelatorioConstantes.PARAMETRO_RAZAO_SOCIAL, organizacaoModel.getRazaoSocial());
                    parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());
                    parametros.put(RelatorioConstantes.PARAMETRO_CNPJ, organizacaoModel.getCnpj());
                    parametros.put(RelatorioConstantes.PARAMETRO_CIDADE, organizacaoModel.getCidade() + ", " + PempecParse.dateToString(new Date()));
                    parametros.put(RelatorioConstantes.PARAMETRO_VALOR, contaBancariaChequeModel.getValor());
                    parametros.put(RelatorioConstantes.PARAMETRO_VALOR_EXTENSO, new Extenso(contaBancariaChequeModel.getValor()).toString().toUpperCase());
                    parametros.put(RelatorioConstantes.PARAMETRO_PORTADOR, contaBancariaChequeModel.getPortador());
                    parametros.put(RelatorioConstantes.PARAMETRO_NUMERO_CHEQUE, contaBancariaChequeModel.getNumeroCheque());
                    parametros.put(RelatorioConstantes.PARAMETRO_NUMERO_CONTA, contaBancariaChequeModel.getContaBancaria().getConta());
                    parametros.put(RelatorioConstantes.PARAMETRO_STATUS, contaBancariaChequeModel.getStatus().getDescricao());
                    parametros.put(RelatorioConstantes.PARAMETRO_DATA_EMISSAO, contaBancariaChequeModel.getDataEmissao());


                    //Pegando o título

                    List<TituloPagarBaixaChequeModel> titulos = tituloPagarBaixaChequeBO.obterPorCheque(contaBancariaChequeModel);

                    if (titulos != null && !titulos.isEmpty()) {
                        TituloPagarBaixaChequeModel tituloPagarBaixaChequeModel = titulos.listIterator().next();
                        parametros.put(RelatorioConstantes.PARAMETRO_NUMERO_DOCUMENTO, tituloPagarBaixaChequeModel.getTituloPagarBaixa().getTitulo().getNumeroDocumento());

                        HistoricoModel historicoModel = historicoBO.consultarPorPk(tituloPagarBaixaChequeModel.getTituloPagarBaixa().getTitulo().getHistorico());

                        parametros.put(RelatorioConstantes.PARAMETRO_DESCRICAO, historicoModel.getDescricao() + " " + tituloPagarBaixaChequeModel.getTituloPagarBaixa().getTitulo().getDescricao());
                        parametros.put(RelatorioConstantes.PARAMETRO_VALOR_TITULO, tituloPagarBaixaChequeModel.getTituloPagarBaixa().getTitulo().getValorNominal());

                    } else {

                        parametros.put(RelatorioConstantes.PARAMETRO_DESCRICAO, contaBancariaChequeModel.getObservacao());
                    }

                    switch (comboFormato.getSelectedIndex()) {

                        case 0:
                            new RelatorioUtil().visualizar(RelatorioConstantes.RECIBO_COPIA_CHEQUE, parametros);
                            break;
                        case 1:
                            new RelatorioUtil().exportarPDF(RelatorioConstantes.RECIBO_COPIA_CHEQUE, parametros);
                            break;
                        case 2:
                            new RelatorioUtil().exportarXLS(RelatorioConstantes.RECIBO_COPIA_CHEQUE, parametros);
                            break;
                        case 3:

                            //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                            File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RECIBO_COPIA_CHEQUE, parametros);

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

private void comboContaBancariaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaBancariaItemStateChanged

    if (comboContaBancaria.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk() != null) {

        try {
            //ativa o combo para escolher o cheque
            comboCheque.setEnabled(true);

            Collection<ContaBancariaChequeModel> lContaBancariaCheque = chequeBO.obterPorContaBancariaStatusEmitidoECompensado((ContaBancariaModel) comboContaBancaria.getSelectedItem());
            final EventList<ContaBancariaChequeModel> lRegistrosCheques = GlazedLists.eventList(lContaBancariaCheque);
            if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
                supportCheque.uninstall();
            }
            supportCheque = AutoCompleteSupport.install(comboCheque, lRegistrosCheques, new ChequeTextFilterator());
            supportCheque.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportCheque.setStrict(false);

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

        if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
            supportCheque.uninstall();
        }

        this.limpaCamposCheque();

    }

}//GEN-LAST:event_comboContaBancariaItemStateChanged

private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
    botaoGerar.setEnabled(false);
    comboCheque.setEnabled(false);
    comboContaBancaria.setSelectedIndex(0);

    if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
        supportCheque.uninstall();
    }
    this.limpaCamposCheque();

}//GEN-LAST:event_botaoLimparActionPerformed

private void comboFormatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormatoItemStateChanged
    if (comboFormato.getSelectedItem() != null && ((FormatosRelatoriosModel) comboFormato.getSelectedItem()).getDescricao().equalsIgnoreCase(Constantes.ENVIAR_POR_EMAIL)) {
        botaoGerar.setText("Enviar");
    } else {
        botaoGerar.setText("Gerar");
    }    // TODO add your handling code here:
}//GEN-LAST:event_comboFormatoItemStateChanged

private void comboChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboChequeActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboCheque.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        this.limpaCamposCheque();

        //ativa o botao para o relatorio
        botaoGerar.setEnabled(true);

        ContaBancariaChequeModel cheque = (ContaBancariaChequeModel) comboCheque.getSelectedItem();

        contaBancariaChequeModel = cheque;

        if (cheque.getDataRegistro() != null) {
            labelDataGeracao.setText(PempecParse.dateToString(cheque.getDataRegistro()));
        }

        if (cheque.getDataPrevisaoCompensacao() != null) {
            labelVencimento.setText(PempecParse.dateToString(cheque.getDataPrevisaoCompensacao()));
        }

        if (cheque.getDataEmissao() != null) {
            labelEmissao.setText(PempecParse.dateToString(cheque.getDataEmissao()));
        }

        if (cheque.getValor() != null) {
            labelValorCheque.setText(PempecParse.doubleToZero(cheque.getValor()));
        }

        if (cheque.getStatus() != null) {
            labelStatus.setText(cheque.getStatus().getDescricao());
        }

        if (cheque.getPortador() != null) {
            labelPortador.setText(cheque.getPortador().toUpperCase());
        }

    }


}//GEN-LAST:event_comboChequeActionPerformed

    private void limpaCamposCheque() {

        labelPortador.setText("");
        labelValorCheque.setText("0,00");
        labelEmissao.setText("");
        labelDataGeracao.setText("");
        labelStatus.setText("");
        labelVencimento.setText("");

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboCheque;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelCheque;
    private javax.swing.JLabel labelConta;
    private javax.swing.JLabel labelDataGeracao;
    private javax.swing.JLabel labelEmissao;
    private javax.swing.JLabel labelPortador;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelValorCheque;
    private javax.swing.JLabel labelVencimento;
    // End of variables declaration//GEN-END:variables
    private ContaBancariaChequeModel contaBancariaChequeModel;
    private AutoCompleteSupport supportCheque;
}
