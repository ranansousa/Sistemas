package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CartaoCreditoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CartaoCreditoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.CartaoCreditoTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroCartaoCredito extends FinanceInternalFrame implements IRepopulador {

    private CartaoCreditoBO cartaoCreditoBO = new CartaoCreditoBO();
    private long tela = Tela.TELA_PARAMETROS_BANCOS.getTela();

    private String NameObject() {
        return (String) "CartaoCredito";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {


            Collection<CartaoCreditoModel> lColecao = cartaoCreditoBO.obterTodos(organizacaoModel);


            final EventList<CartaoCreditoModel> lRegistros = GlazedLists.eventList(lColecao);

            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboCartaoCredito, lRegistros, new CartaoCreditoTextFilterator());
            support.setFilterMode(TextMatcherEditor.STARTS_WITH);
            support.setStrict(false);






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

    public CadastroCartaoCredito() throws SystemException {

        this.setLocation(250, 50);
        initComponents();

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);

//tamanho dos campos
        jFTitular.setDocument(new Documento(200));
        jFTNumeroCartao.setDocument(new Documento(60));
        jFDiaVcto.setDocument(new Documento(2));
        jFDiaCompra.setDocument(new Documento(2));
        jFCodigoSeguranca.setDocument(new Documento(4));


        //Aplicando mascara em campos.
        //mascara


        //  jFTCep.setFormatterFactory(MaskUtils.mascaraCep());


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        labelCedente = new javax.swing.JLabel();
        comboCartaoCredito = new javax.swing.JComboBox();
        labelCpf = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        labelCpf1 = new javax.swing.JLabel();
        labelCpf2 = new javax.swing.JLabel();
        labelCpf3 = new javax.swing.JLabel();
        jFTDataValidade = new org.jdesktop.swingx.JXDatePicker();
        labelCpf4 = new javax.swing.JLabel();
        jFTLimite = new br.com.pempec.componentes.JDoubleField();
        labelCpf5 = new javax.swing.JLabel();
        jFTitular = new javax.swing.JTextField();
        jFTNumeroCartao = new javax.swing.JTextField();
        jFCodigoSeguranca = new javax.swing.JTextField();
        jFDiaVcto = new javax.swing.JTextField();
        comboBandeira = new javax.swing.JComboBox();
        labelCpf6 = new javax.swing.JLabel();
        jFDiaCompra = new javax.swing.JTextField();
        labelCpf7 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro de Cartões de Créditos");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoIncluir.setMnemonic('I');
        botaoIncluir.setText("Incluir");
        botaoIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirActionPerformed(evt);
            }
        });

        botaoAlterar.setMnemonic('A');
        botaoAlterar.setText("Alterar");
        botaoAlterar.setEnabled(false);
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
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

        botaoExcluir.setMnemonic('E');
        botaoExcluir.setText("Excluir");
        botaoExcluir.setEnabled(false);
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelCedente.setText("Cartão");

        comboCartaoCredito.setFont(getFont());
        comboCartaoCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCartaoCreditoActionPerformed(evt);
            }
        });

        labelCpf.setText("Número");

        campoCodigo.setEditable(false);

        labelCpf1.setText("Titular");

        labelCpf2.setText("Cod.Segurança");

        labelCpf3.setText("Validade");

        labelCpf4.setText("Vencimento");

        labelCpf5.setText("Limite");

        comboBandeira.setFont(getFont());
        comboBandeira.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--> Selecione <--", "Amex", "C&A", "Hipercard", "Mastercard", "Renner", "Riachuelo", "Visa" }));

        labelCpf6.setText("Bandeira");

        labelCpf7.setText("Dia de Compra");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCedente)
                    .addComponent(labelCpf1)
                    .addComponent(comboCartaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpf)
                    .addComponent(jFTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelCpf4)
                                .addComponent(jFDiaVcto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(70, 70, 70)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelCpf7)
                                .addComponent(jFDiaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jFTNumeroCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelCpf2)
                    .addComponent(jFCodigoSeguranca, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCpf3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jFTLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpf5)
                    .addComponent(comboBandeira, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpf6)
                    .addComponent(jFTDataValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCedente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCartaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(labelCpf1)
                        .addGap(1, 1, 1)
                        .addComponent(jFTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(labelCpf))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCpf3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTDataValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCpf5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTNumeroCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCpf6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBandeira, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCpf2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFCodigoSeguranca, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(labelCpf7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFDiaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(labelCpf4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFDiaVcto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        jTabbedPane1.addTab("Cartão de Crédito", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        botaoAlterar.setEnabled(false);
        botaoIncluir.setEnabled(true);

        campoCodigo.setText("");

        //Somente setar null nos combos que possui o auto-complete
        comboCartaoCredito.setSelectedItem(null);

        comboBandeira.setSelectedIndex(0);

        jFTNumeroCartao.setText("");

        jFTitular.setText("");

        jFDiaVcto.setText("");

        jFDiaCompra.setText("");

        jFCodigoSeguranca.setText("");

        jFTDataValidade.setDate(null);

        jFTNumeroCartao.setText("");

        jFTLimite.setText("0,00");

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

        if (comboCartaoCredito.getSelectedItem() != null) {
            valorCombo = comboCartaoCredito.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                if (campoCodigo.getText().equals(Controller.getOrganizacao().getId())) {
                    exibeMensagemAviso("Não é possível excluir este registro! \n Este cartão é padrão do sistema.", null);
                    return;
                }

                CartaoCreditoModel tab = new CartaoCreditoModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab.setCartao(valorCombo);

                tab = cartaoCreditoBO.consultarPorTemplate(tab);

                tab.setMovimentoDiarioModel(registroMovimento("Deletar Cartao Credito", tab.getCartao(), tab.getNumero(), null, "Deletado"));

                cartaoCreditoBO.excluir(tab);

                this.botaoLimparActionPerformed(evt);

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

            exibeMensagemAviso("Campo Nome é obrigatório.", null);
        }

    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        String valorCombo = null;


        if (comboCartaoCredito.getSelectedItem() != null) {
            valorCombo = comboCartaoCredito.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                if (validaCampos()) {

                    CartaoCreditoModel tab = new CartaoCreditoModel();

                    tab.setPk(new PKModel());

                    tab.getPk().setOrganizacao(organizacaoModel);

                    tab.getPk().setId(PempecKeyGenerator.generateKey());

                    tab.setCartao(valorCombo.toUpperCase());

                    tab.setCodigoSeguranca(jFCodigoSeguranca.getText());

                    tab.setDiaVencimento(jFDiaVcto.getText());

                    tab.setLimite(jFTLimite.getValue());

                    tab.setNumero(jFTNumeroCartao.getText());

                    tab.setTitular(jFTitular.getText().toUpperCase());

                    tab.setValidade(PempecParse.dateToDate(jFTDataValidade.getDate()));

                    tab.setDiaCompra(jFDiaCompra.getText());

                    tab.setBandeira(comboBandeira.getSelectedItem().toString());

                    tab.setMovimentoDiarioModel(registroMovimento("Inserir Cartao Credito", tab.getCartao(), tab.getNumero(), null, "Inserido"));
                    cartaoCreditoBO.inserir(tab);

                    this.botaoLimparActionPerformed(evt);


                } else {

                    exibeMensagemAviso("Campo(s) obrigatório(s).", null);

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
            exibeMensagemAviso("Campo Cartão é obrigatório.", null);
        }
    }//GEN-LAST:event_botaoIncluirActionPerformed

private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

    String valorCombo = null;

    if (comboCartaoCredito.getSelectedItem() != null) {

        valorCombo = comboCartaoCredito.getSelectedItem().toString().toUpperCase();

    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);

                return;

            }

            if (validaCampos()) {

                CartaoCreditoModel tab = new CartaoCreditoModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab = cartaoCreditoBO.consultarPorPk(tab);

                tab.setCartao(valorCombo.toUpperCase());

                tab.setCodigoSeguranca(jFCodigoSeguranca.getText());

                tab.setDiaVencimento(jFDiaVcto.getText());

                tab.setLimite(jFTLimite.getValue());

                tab.setNumero(jFTNumeroCartao.getText());

                tab.setTitular(jFTitular.getText().toUpperCase());

                tab.setValidade(PempecParse.dateToDate(jFTDataValidade.getDate()));

                tab.setDiaCompra(jFDiaCompra.getText());

                tab.setBandeira(comboBandeira.getSelectedItem().toString());


                tab.setMovimentoDiarioModel(registroMovimento("Alterar Cartao Credito", tab.getCartao(), tab.getNumero(), null, "Alterado"));

                cartaoCreditoBO.alterar(tab);

                this.botaoLimparActionPerformed(evt);

            } else {

                exibeMensagemAviso("Campo(s) obrigatório(s).", null);

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
        exibeMensagemAviso("Campo Nome é obrigatório.", null);
    }

}//GEN-LAST:event_botaoAlterarActionPerformed

private void comboCartaoCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCartaoCreditoActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboCartaoCredito.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            CartaoCreditoModel tab = new CartaoCreditoModel();
            tab.setCartao(comboCartaoCredito.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());


            tab = cartaoCreditoBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                botaoAlterar.setEnabled(true);
                botaoExcluir.setEnabled(true);
                botaoIncluir.setEnabled(false);

                campoCodigo.setText(tab.getPk().getId());

                jFCodigoSeguranca.setText(tab.getCodigoSeguranca());

                jFDiaVcto.setText(tab.getDiaVencimento());

                jFDiaCompra.setText(tab.getDiaCompra());

                jFTDataValidade.setDate(PempecParse.dateToDate(tab.getValidade()));

                jFTLimite.setText(PempecParse.doubleToZero(tab.getLimite()));

                jFTNumeroCartao.setText(tab.getNumero());

                jFTitular.setText(tab.getTitular());

                comboBandeira.setSelectedItem(tab.getBandeira());


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

}//GEN-LAST:event_comboCartaoCreditoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JComboBox comboBandeira;
    private javax.swing.JComboBox comboCartaoCredito;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JTextField jFCodigoSeguranca;
    private javax.swing.JTextField jFDiaCompra;
    private javax.swing.JTextField jFDiaVcto;
    private org.jdesktop.swingx.JXDatePicker jFTDataValidade;
    private br.com.pempec.componentes.JDoubleField jFTLimite;
    private javax.swing.JTextField jFTNumeroCartao;
    private javax.swing.JTextField jFTitular;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelCpf1;
    private javax.swing.JLabel labelCpf2;
    private javax.swing.JLabel labelCpf3;
    private javax.swing.JLabel labelCpf4;
    private javax.swing.JLabel labelCpf5;
    private javax.swing.JLabel labelCpf6;
    private javax.swing.JLabel labelCpf7;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;

    private Boolean validaCampos() throws ApplicationException, SystemException {

        if (jFCodigoSeguranca.getText() == null || jFCodigoSeguranca.getText().isEmpty()) {

            jFCodigoSeguranca.requestFocus();

            return false;
        }


        if (comboBandeira.getSelectedIndex() == 0) {

            comboBandeira.requestFocus();

            return false;
        }

        if (jFDiaVcto.getText() == null || jFDiaVcto.getText().isEmpty()) {

            jFDiaVcto.requestFocus();

            return false;
        }

        if (jFDiaCompra.getText() == null || jFDiaCompra.getText().isEmpty()) {

            jFDiaCompra.requestFocus();

            return false;
        }


        if (jFTDataValidade.getDate() == null) {

            jFTDataValidade.requestFocus();

            return false;
        }

        if (jFTitular.getText() == null || jFTitular.getText().isEmpty()) {

            jFTitular.requestFocus();

            return false;
        }


        if (jFTNumeroCartao.getText() == null || jFTNumeroCartao.getText().isEmpty()) {

            jFTNumeroCartao.requestFocus();

            return false;
        }


        if (jFTLimite.getValue() == 0) {

            jFTLimite.requestFocus();

            return false;
        }






        return true;

    }
}
