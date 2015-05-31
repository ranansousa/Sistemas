package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.ContaBancariaServices;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopuladorNew;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.ChequeTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroTituloPagarBaixaLoteFPCheque extends FinanceInternalFrame implements IRepopuladorNew {

    private CadastroTituloPagarBaixaLote baixa;
    private TituloPagarBaixaFormaPagamentoModel formaPagamento;
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private ContaBancariaChequeBO chequeBO = new ContaBancariaChequeBO();
    private TipoOperacaoBancariaBO operacaoBancariaBO = new TipoOperacaoBancariaBO();

    private String NameObject() {
        return (String) "Pagamento em Cheque";
    }

    public void repopularCombos(Tela tela, Object object) {

        try {

            int tipo = 1;

            switch (tela) {

                case TELA_PARAMETROS_TIPO_DE_OPERACOES_BANCARIAS:

                    Collection<TipoOperacaoBancariaModel> lTipoOperacao = new ArrayList<TipoOperacaoBancariaModel>();
                    TipoOperacaoBancariaModel tipoOPeracao = new TipoOperacaoBancariaModel();
                    tipoOPeracao.setDescricao(" -> Selecione <- ");
                    lTipoOperacao.add(tipoOPeracao);

                    lTipoOperacao.addAll(operacaoBancariaBO.obterTodosPorTipo(organizacaoModel, tipo));

                    comboTipoOperacaoBancaria.setModel(new javax.swing.DefaultComboBoxModel(lTipoOperacao.toArray()));

                    tipoOPeracao = (TipoOperacaoBancariaModel) object;

                    if (tipoOPeracao != null) {

                        for (int i = 1; i < comboTipoOperacaoBancaria.getItemCount(); i++) {
                            if (tipoOPeracao.getPk().getId().equalsIgnoreCase(((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getItemAt(i)).getPk().getId())) {
                                comboTipoOperacaoBancaria.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_CONTAS_BANCARIAS:

                    Collection<ContaBancariaModel> lColecao = new ArrayList<ContaBancariaModel>();

                    ContaBancariaModel contaModel = new ContaBancariaModel();
                    contaModel.setConta(" -> Selecione <- ");
                    lColecao.add(contaModel);
                    lColecao.addAll(contaBancariaBO.obterTodos(organizacaoModel));

                    comboContaBancaria.setModel(new javax.swing.DefaultComboBoxModel(lColecao.toArray()));

                    contaModel = (ContaBancariaModel) object;

                    if (contaModel != null) {

                        for (int i = 1; i < comboContaBancaria.getItemCount(); i++) {
                            if (contaModel.getPk().getId().equalsIgnoreCase(((ContaBancariaModel) comboContaBancaria.getItemAt(i)).getPk().getId())) {
                                comboContaBancaria.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                default:

                    lTipoOperacao = new ArrayList<TipoOperacaoBancariaModel>();
                    lColecao = new ArrayList<ContaBancariaModel>();

                    tipoOPeracao = new TipoOperacaoBancariaModel();
                    tipoOPeracao.setDescricao(" -> Selecione <- ");
                    lTipoOperacao.add(tipoOPeracao);

                    lTipoOperacao.addAll(operacaoBancariaBO.obterTodosPorTipo(organizacaoModel, tipo));

                    comboTipoOperacaoBancaria.setModel(new javax.swing.DefaultComboBoxModel(lTipoOperacao.toArray()));

                    contaModel = new ContaBancariaModel();
                    contaModel.setConta(" -> Selecione <- ");
                    lColecao.add(contaModel);
                    lColecao.addAll(contaBancariaBO.obterTodos(organizacaoModel));

                    comboContaBancaria.setModel(new javax.swing.DefaultComboBoxModel(lColecao.toArray()));

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

    }

    private Boolean validaCampos() {

        if (jFTValorCheque.getText().equals("0,00")) {
            jFTValorCheque.requestFocus();
            return false;
        }

        if (comboTipoOperacaoBancaria.getSelectedIndex() == 0) {
            comboTipoOperacaoBancaria.requestFocus();
            return false;
        }

        if (comboContaBancaria.getSelectedIndex() == 0) {
            comboContaBancaria.requestFocus();
            return false;
        }

        if (comboCheque.getSelectedItem() == null) {
            comboCheque.requestFocus();
            return false;
        }

        if (jFTDataEmissao.getDate() == null) {
            jFTDataEmissao.requestFocus();
            return false;
        }

        if (jFTDataPrevisaoCompensacao.getDate() == null) {
            jFTDataPrevisaoCompensacao.requestFocus();
            return false;
        }


        //Validando Datas.


        if (jFTDataEmissao.getDate() != null) {
            Date dataInformada = jFTDataEmissao.getDate();
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data de Emissão não pode ser superior a data de HOJE.!", null);
                jFTDataEmissao.requestFocus();
                return false;
            }
        }


        if (!PempecUtil.validaPreenchimentoNumero(comboContaBancaria.getSelectedItem().toString())) {
            exibeMensagemAviso("A conta Bancária só pode conter número.", null);
            comboContaBancaria.requestFocus();
            return false;
        }

        return true;

    }

    public CadastroTituloPagarBaixaLoteFPCheque(CadastroTituloPagarBaixaLote baixa, TituloPagarBaixaFormaPagamentoModel formaPagamento, CedenteModel cedenteModel) throws SystemException {

        this.baixa = baixa;

        this.formaPagamento = formaPagamento;

        initComponents();

        this.repopularCombos(Tela.TELA_PRINCIPAL, null);

        //Aplicando tamanho maximo de caracteres do campo.
        jTObservacao.setDocument(new Documento(60));
        jTPortador.setDocument(new Documento(60));

        if (baixa.jFTDataPagamento.getDate() != null) {

            jFTDataEmissao.setDate(baixa.jFTDataPagamento.getDate());

        }

        jFTDataPrevisaoCompensacao.setDate(PempecUtil.addDayDate(jFTDataEmissao.getDate(), 2));

        jFTValorCheque.setText(PempecParse.doubleToZero(PempecParse.stringToDouble(baixa.jFTValorTotal.getText())));

        CedenteModel cedente = cedenteModel;

        if (cedente != null && cedente.getNome() != null) {

            try {

                cedente = new CedenteBO().consultarPorTemplate(cedente);

            } catch (ApplicationException ex) {

                ex.printStackTrace();
            }

            if (cedente != null && cedente.getPk() != null && cedente.getPk().getId() != null) {

                jTPortador.setText(cedente.getNome());

            }
        } else {

            jTPortador.setText(Controller.getOrganizacao().getRazaoSocial());
        }
        jTObservacao.setText("Pagto Lote : " + this.baixa.jTNumeroLote.getText());

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelConta = new javax.swing.JLabel();
        jTPortador = new javax.swing.JTextField();
        labelDataVencimento = new javax.swing.JLabel();
        labelValorPagar = new javax.swing.JLabel();
        jFTValorCheque = new br.com.pempec.componentes.JDoubleField();
        comboCheque = new javax.swing.JComboBox();
        jTObservacao = new javax.swing.JTextField();
        comboTipoOperacaoBancaria = new javax.swing.JComboBox();
        labelCheque = new javax.swing.JLabel();
        labelOperacaoBancaria = new javax.swing.JLabel();
        labelDataEmissao = new javax.swing.JLabel();
        labelPortador = new javax.swing.JLabel();
        labelObs = new javax.swing.JLabel();
        comboContaBancaria = new javax.swing.JComboBox();
        jFTDataEmissao = new org.jdesktop.swingx.JXDatePicker();
        jFTDataPrevisaoCompensacao = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        botaoLimpar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();

        setClosable(true);
        setTitle("PEMPEC ENTERPRISE - Finance -  Pagamento em Cheque");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        labelConta.setText("Conta");

        labelDataVencimento.setText("Previão Compensação");

        labelValorPagar.setText("Valor");

        jFTValorCheque.setEditable(false);

        comboCheque.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        labelCheque.setText("Cheque");

        labelOperacaoBancaria.setText("Operação Bancária");

        labelDataEmissao.setText("Data Emissão");

        labelPortador.setText("Portador");

        labelObs.setText("Observação");

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        comboContaBancaria.setForeground(new java.awt.Color(255, 153, 0));
        comboContaBancaria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaBancariaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPortador)
                    .addComponent(jTObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelObs)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelConta)
                                    .addComponent(comboTipoOperacaoBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelOperacaoBancaria)
                                    .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelDataEmissao)
                                    .addComponent(comboCheque, 0, 143, Short.MAX_VALUE)
                                    .addComponent(labelCheque)
                                    .addComponent(jFTDataEmissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jTPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTDataPrevisaoCompensacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFTValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataVencimento)
                            .addComponent(labelValorPagar))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelCheque)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelValorPagar)
                            .addComponent(labelConta))
                        .addGap(31, 31, 31)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelOperacaoBancaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTipoOperacaoBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTDataPrevisaoCompensacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(labelPortador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDataVencimento)
                        .addComponent(labelDataEmissao)))
                .addGap(18, 18, 18)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoSalvar.setMnemonic('I');
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoLimpar.setMnemonic('L');
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoLimpar, botaoSalvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(163, 163, 163)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
//GEN-LAST:event_botaoSalvarActionPerformed
        //Faz o processo de salvar e retorna o BEAN populado com os dados do cheque para tela da BAIXA.
        TituloPagarBaixaChequeModel tituloPagarCheque = new TituloPagarBaixaChequeModel();

        if (validaCampos()) {

            if (validarCombos("contaBancaria")) {

                tituloPagarCheque.setPk(new PKModel());
                tituloPagarCheque.getPk().setOrganizacao(organizacaoModel);
                tituloPagarCheque.getPk().setId(PempecKeyGenerator.generateKey());

                ContaBancariaChequeModel cheque = new ContaBancariaChequeModel();

                if (validarCombos("cheque")) {
                    cheque = (ContaBancariaChequeModel) comboCheque.getSelectedItem();
                }

                try {

                    Double valorSaldo = new ContaBancariaServices((ContaBancariaModel) comboContaBancaria.getSelectedItem()).obterSaldoContaBancaria();

                    if (valorSaldo < jFTValorCheque.getValue()) {

                        if (!exibeMensagemConfirmacao("A conta informada não possui saldo suficiente. Desejar continuar? \n\nSaldo: R$ " + PempecParse.doubleToZero(valorSaldo), "Saldo Insuficiente")) {
                            return;
                        }

                        if (Controller.verificaParametroAtivo(Parametro.CBPLFPC001.getCodigo())) {
                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPLFPC001.getCodigo()), null);
                            return;
                        }

                        if (Controller.verificaParametroAtivo(Parametro.CBPLFPC002.getCodigo())) {
                            if (valorSaldo < Controller.findByCodigo(Parametro.CBPLFPC002.getCodigo()).getValor().doubleValue()) {
                                exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPLFPC002.getCodigo()), null);
                                return;
                            }
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

                cheque.getStatus().getPk().setId(Constantes.STATUS_CHEQUE_EMITIDO);

                cheque.setDataPrevisaoCompensacao(jFTDataPrevisaoCompensacao.getDate());
                cheque.setDataEmissao(jFTDataEmissao.getDate());
                cheque.setPortador(jTPortador.getText().toUpperCase());
                cheque.setValor(jFTValorCheque.getValue());
                cheque.setObservacao(jTObservacao.getText().toUpperCase());

                formaPagamento.setValor(jFTValorCheque.getValue());
                //alterado em 26 de junho 14
                formaPagamento.setConta(comboContaBancaria.getSelectedItem().toString());
                formaPagamento.setCheque(comboCheque.getSelectedItem().toString());
                

                tituloPagarCheque.setValor(jFTValorCheque.getValue());

                tituloPagarCheque.setObservacao(jTObservacao.getText().toUpperCase());

                cheque.setUsuario(Controller.getUsuarioLogado());

                if (validarCombos("tipoOperacao")) {

                    TipoOperacaoBancariaModel tipoOperacao = (TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem();
                    tituloPagarCheque.setTipoOperacaoBancaria(tipoOperacao);

                }

                tituloPagarCheque.setContaBancariaCheque(cheque);

                baixa.addCollecaoCheques(tituloPagarCheque, formaPagamento);
                this.botaoLimparActionPerformed(evt);
                this.dispose();

            }

        } else {

            exibeMensagemAviso("Campo(s) obrigatório(s).", null);

        }



    }

private void comboContaBancariaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaBancariaItemStateChanged

    if (validarCombos("contaBancaria")) {

        try {

            TipoStatusModel status = new TipoStatusModel();
            status.setPk(new PKModel());
            status.getPk().setId(Constantes.STATUS_CHEQUE_NOVO);

            Collection<ContaBancariaChequeModel> lContaBancariaCheque = chequeBO.obterPorContaBancariaStatus((ContaBancariaModel) comboContaBancaria.getSelectedItem(), status);

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

    }



}//GEN-LAST:event_comboContaBancariaItemStateChanged

private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

    jFTDataEmissao.setDate(baixa.jFTDataPagamento.getDate());
    jFTDataPrevisaoCompensacao.setDate(PempecUtil.addDayDate(jFTDataEmissao.getDate(), 2));

    jTObservacao.setText("");
    jTPortador.setText("");

    if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
        supportCheque.uninstall();
    }

    comboCheque.setSelectedItem(null);
    comboContaBancaria.setSelectedIndex(0);
    comboTipoOperacaoBancaria.setSelectedIndex(0);
    jTPortador.setText("");
}//GEN-LAST:event_botaoLimparActionPerformed

private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
    try {
        this.botaoLimparActionPerformed(evt);
        setClosed(true);

    } catch (final Exception ex) {

        final File file = PrintScreen.capture();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                tratamentoExcecao(new SystemException(ex), file);

            }
        });

    }
}//GEN-LAST:event_botaoFecharActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    protected final javax.swing.JButton botaoSalvar = new javax.swing.JButton();
    private javax.swing.JComboBox comboCheque;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboTipoOperacaoBancaria;
    private org.jdesktop.swingx.JXDatePicker jFTDataEmissao;
    private org.jdesktop.swingx.JXDatePicker jFTDataPrevisaoCompensacao;
    private br.com.pempec.componentes.JDoubleField jFTValorCheque;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTObservacao;
    private javax.swing.JTextField jTPortador;
    private javax.swing.JLabel labelCheque;
    private javax.swing.JLabel labelConta;
    private javax.swing.JLabel labelDataEmissao;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelOperacaoBancaria;
    private javax.swing.JLabel labelPortador;
    private javax.swing.JLabel labelValorPagar;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportCheque;

    private boolean validarCombos(String combo) {
        boolean valida = false;

        if (combo.equalsIgnoreCase("cheque")) {
            if (comboCheque.getSelectedItem() != null) {
                if (((ContaBancariaChequeModel) comboCheque.getSelectedItem()).getPk() != null) {
                    if (!((ContaBancariaChequeModel) comboCheque.getSelectedItem()).getPk().getId().isEmpty()) {
                        valida = true;
                    }

                }
            }
        }

        if (combo.equalsIgnoreCase("contaBancaria")) {
            if (comboContaBancaria.getSelectedItem() != null) {
                if (((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk() != null) {
                    if (!((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk().getId().isEmpty()) {
                        valida = true;
                    }

                }
            }
        }


        if (combo.equalsIgnoreCase("tipoOPeracao")) {
            if (comboTipoOperacaoBancaria.getSelectedItem() != null) {
                if (((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk() != null) {
                    if (!((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk().getId().isEmpty()) {
                        valida = true;
                    }

                }
            }
        }



        return valida;

    }
}
