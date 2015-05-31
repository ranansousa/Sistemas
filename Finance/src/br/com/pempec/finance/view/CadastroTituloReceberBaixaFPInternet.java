package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberBaixaInternetModel;
import br.com.pempec.finance.models.TituloReceberModel;
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
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroTituloReceberBaixaFPInternet extends FinanceInternalFrame implements IRepopuladorNew {

    private CadastroTituloReceberBaixa baixa;
    private TituloReceberModel titulo;
    private TituloReceberBaixaFormaPagamentoModel formaPagamento;
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private TipoOperacaoBancariaBO operacaoBancariaBO = new TipoOperacaoBancariaBO();

    private String NameObject() {
        return (String) "Recebimento Via Bank-Line";
    }

    public void repopularCombos(Tela tela, Object object) {

        try {

            int tipo = 2;

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

        if (jFTValorOperacao.getText().equals("0,00")) {
            jFTValorOperacao.requestFocus();
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

        if (jFTDataOperacao.getDate() == null) {
            jFTDataOperacao.requestFocus();
            return false;
        }

        if (!PempecUtil.validaPreenchimentoNumero(comboContaBancaria.getSelectedItem().toString())) {
            exibeMensagemAviso("A conta Bancária só pode conter número.", null);
            comboContaBancaria.requestFocus();
            return false;
        }

        return true;

    }

    public CadastroTituloReceberBaixaFPInternet(CadastroTituloReceberBaixa baixa, TituloReceberModel titulo, TituloReceberBaixaFormaPagamentoModel formaPagamento, String tipoPagamento) throws SystemException {

        this.baixa = baixa;

        this.titulo = titulo;

        this.formaPagamento = formaPagamento;

        String tipo = tipoPagamento;

        if (titulo.getSacado() != null) {
            SacadoModel sacado = titulo.getSacado();
            sacado.setAgencia(titulo.getSacado().getAgencia());
            sacado.setConta(titulo.getSacado().getConta());
            sacado.setBanco(titulo.getSacado().getBanco());
        }

        initComponents();

        this.repopularCombos(Tela.TELA_PRINCIPAL, null);

        //Aplicando tamanho maximo de caracteres do campo.
        jTDetalhamento.setDocument(new Documento(60));

        if (baixa.jFTDataPagamento.getDate() != null) {
            jFTDataOperacao.setDate(baixa.jFTDataPagamento.getDate());
        }

        String detalhe = "RCBTO " + titulo.getParcela() + " TÍT. " + titulo.getNumeroDocumento() + " " + titulo.getDescricao();

        if (detalhe.length() > 60) {

            jTDetalhamento.setText(detalhe.substring(0, 60));

        } else {

            jTDetalhamento.setText(detalhe);
        }

        if (tipo.equalsIgnoreCase("total")) {
            Double valorTitulo = PempecParse.stringToDouble(baixa.jFTValorReceber.getText());
            Double valorPago = PempecParse.stringToDouble(baixa.jFTValorRecebido.getText());
            Double total = valorTitulo - valorPago;
            jFTValorOperacao.setText(PempecParse.doubleToZero(total));
        }


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelConta = new javax.swing.JLabel();
        comboContaBancaria = new javax.swing.JComboBox();
        labelValorPagar = new javax.swing.JLabel();
        jFTValorOperacao = new br.com.pempec.componentes.JDoubleField();
        comboTipoOperacaoBancaria = new javax.swing.JComboBox();
        labelOperacaoBancaria = new javax.swing.JLabel();
        labelDataEmissao = new javax.swing.JLabel();
        labelPortador1 = new javax.swing.JLabel();
        jTDetalhamento = new javax.swing.JTextField();
        jFTDataOperacao = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        botaoSalvar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();

        setClosable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Recebimento Via Internet");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 102)));

        labelConta.setText("Conta Creditada");

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        comboContaBancaria.setForeground(new java.awt.Color(255, 153, 0));

        labelValorPagar.setText("Valor");

        jFTValorOperacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTValorOperacaoFocusLost(evt);
            }
        });

        labelOperacaoBancaria.setText("Operação Bancária");

        labelDataEmissao.setText("Data da Operação");

        labelPortador1.setText("Detalhamento");

        jTDetalhamento.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelConta)
                            .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTipoOperacaoBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOperacaoBancaria))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTDataOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelValorPagar)
                                .addComponent(jFTValorOperacao, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addComponent(labelDataEmissao))))
                    .addComponent(labelPortador1)
                    .addComponent(jTDetalhamento))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTValorOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelOperacaoBancaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTipoOperacaoBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTDataOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelDataEmissao))
                .addGap(18, 18, 18)
                .addComponent(labelPortador1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTDetalhamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
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
                .addGap(28, 28, 28)
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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
                        .addGap(59, 59, 59)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        jFTDataOperacao.setDate(baixa.jFTDataPagamento.getDate());
        jFTValorOperacao.setText("0,00");
        jTDetalhamento.setText("");
        comboContaBancaria.setSelectedIndex(0);
        comboTipoOperacaoBancaria.setSelectedIndex(0);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
//GEN-LAST:event_botaoSalvarActionPerformed
        TituloReceberBaixaInternetModel tituloReceberInternet = new TituloReceberBaixaInternetModel();

        if (comboContaBancaria.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk() != null) {

            try {
                if (validaCampos()) {

                    if (Controller.verificaParametroAtivo(Parametro.CBRFPI001.getCodigo())) {
                        if (jFTValorOperacao.getValue() > Controller.findByCodigo(Parametro.CBRFPI001.getCodigo()).getValor().doubleValue()) {
                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBRFPI001.getCodigo()), null);
                            return;
                        }
                    }

                    ContaBancariaModel conta = new ContaBancariaModel();

                    conta.setPk(new PKModel());
                    conta.getPk().setOrganizacao(organizacaoModel);
                    conta.getPk().setId(((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk().getId());
                    conta.setConta(comboContaBancaria.getSelectedItem().toString());

                    conta = contaBancariaBO.consultarPorTemplate(conta);

                    TipoOperacaoBancariaModel tipoOperacao = new TipoOperacaoBancariaModel();

                    tituloReceberInternet.setPk(new PKModel());
                    tituloReceberInternet.getPk().setId(PempecKeyGenerator.generateKey());
                    tituloReceberInternet.getPk().setOrganizacao(organizacaoModel);
                    tituloReceberInternet.setDataOperacao(jFTDataOperacao.getDate());
                    tituloReceberInternet.setValor(jFTValorOperacao.getValue());

                    //conta Bancaria
                    if (comboContaBancaria.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk() != null) {
                        tituloReceberInternet.setContaBancaria(conta);
                    }

                    //tipo operacao bancaria
                    if (comboTipoOperacaoBancaria.getSelectedItem() != null && ((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk() != null) {
                        tituloReceberInternet.setTipoOperacaoBancaria(tipoOperacao);
                        tituloReceberInternet.getTipoOperacaoBancaria().setPk(new PKModel());
                        tituloReceberInternet.getTipoOperacaoBancaria().getPk().setId(((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk().getId());

                    }

                    formaPagamento.setValor(jFTValorOperacao.getValue());

                    tituloReceberInternet.setDetalhamento(jTDetalhamento.getText().toUpperCase());

                    baixa.addCollecaoInternet(tituloReceberInternet, formaPagamento);

                    this.dispose();
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


    }

private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
    try {

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

private void jFTValorOperacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTValorOperacaoFocusLost

    Double valorOperacao = jFTValorOperacao.getValue();
    Double valorTitulo = titulo.getValorNominal();

    if (valorOperacao > 0) {
        if (valorOperacao > valorTitulo) {
            exibeMensagemAviso("O valor da operação não pode ser superior ao valor do título", null);
        }
    } else {
        exibeMensagemAviso("O valor deve ser superior a R$ 0,00.", null);
    }


}//GEN-LAST:event_jFTValorOperacaoFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboTipoOperacaoBancaria;
    private org.jdesktop.swingx.JXDatePicker jFTDataOperacao;
    private br.com.pempec.componentes.JDoubleField jFTValorOperacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTDetalhamento;
    private javax.swing.JLabel labelConta;
    private javax.swing.JLabel labelDataEmissao;
    private javax.swing.JLabel labelOperacaoBancaria;
    private javax.swing.JLabel labelPortador1;
    private javax.swing.JLabel labelValorPagar;
    // End of variables declaration//GEN-END:variables
}
