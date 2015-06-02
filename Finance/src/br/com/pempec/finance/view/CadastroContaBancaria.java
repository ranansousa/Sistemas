package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BancoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.iterators.ContaBancariaTextFilterator;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IPopUpContaContabil;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.BancoTextFilterator;
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
public class CadastroContaBancaria extends FinanceInternalFrame implements IRepopulador, IPopUpContaContabil {

    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private BancoBO bancoBO = new BancoBO();
    private long tela = Tela.TELA_PARAMETROS_CONTAS_BANCARIAS.getTela();

    public CadastroContaBancaria() throws SystemException {

        initComponents();

        campoCodigo.setVisible(false);

        //Aplicando tamanho maximo de caracteres do campo.
        jTAgencia.setDocument(new Documento(8));
        jTDependente.setDocument(new Documento(60));
        jTTitular.setDocument(new Documento(60));

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        campoCodigo = new javax.swing.JTextField();
        labelTitular2 = new javax.swing.JLabel();
        labelAgencia = new javax.swing.JLabel();
        comboBanco = new javax.swing.JComboBox();
        labelNomeBanco = new javax.swing.JLabel();
        jTTitular = new javax.swing.JTextField();
        jTAgencia = new javax.swing.JTextField();
        labelConta = new javax.swing.JLabel();
        comboConta = new javax.swing.JComboBox();
        labelSaldoInicial = new javax.swing.JLabel();
        jFTSaldoInicial = new br.com.pempec.componentes.JDoubleField();
        labelDescricao1 = new javax.swing.JLabel();
        jTContaContabil = new javax.swing.JTextField();
        lupaPesquisaContaContabil = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelDependente = new javax.swing.JLabel();
        jTDependente = new javax.swing.JTextField();
        labelLimite = new javax.swing.JLabel();
        labelObservacao = new javax.swing.JLabel();
        jFTLimiteCredito = new br.com.pempec.componentes.JDoubleField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTObservacao = new javax.swing.JTextArea();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Contas Bancárias");
        setPreferredSize(new java.awt.Dimension(610, 499));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel2.setPreferredSize(new java.awt.Dimension(590, 65));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(590, 330));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        campoCodigo.setEditable(false);

        labelTitular2.setText("Titular");

        labelAgencia.setText("Agência");

        comboBanco.setFont(getFont());

        labelNomeBanco.setText("Banco");

        jTTitular.setFont(new java.awt.Font("Arial", 0, 10));

        jTAgencia.setFont(new java.awt.Font("Arial", 0, 12));

        labelConta.setText("Conta");

        comboConta.setFont(getFont());
        comboConta.setForeground(new java.awt.Color(255, 153, 0));
        comboConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContaActionPerformed(evt);
            }
        });

        labelSaldoInicial.setText("Saldo Inicial");

        labelDescricao1.setText("Conta Contabil");

        jTContaContabil.setEditable(false);

        lupaPesquisaContaContabil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.jpg"))); // NOI18N
        lupaPesquisaContaContabil.setBorderPainted(false);
        lupaPesquisaContaContabil.setContentAreaFilled(false);
        lupaPesquisaContaContabil.setDefaultCapable(false);
        lupaPesquisaContaContabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lupaPesquisaContaContabilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelConta)
                    .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAgencia)
                    .addComponent(labelSaldoInicial)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jFTSaldoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitular2)
                    .addComponent(jTTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNomeBanco)
                    .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lupaPesquisaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelDescricao1))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(labelConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelAgencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(labelSaldoInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTSaldoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelNomeBanco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTitular2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(labelDescricao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lupaPesquisaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(160, 160, 160))
        );

        jTabbedPane1.addTab("Dados Básicos", jPanel4);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        labelDependente.setText("Dependente");

        jTDependente.setFont(new java.awt.Font("Arial", 0, 12));

        labelLimite.setText("Limite de Crédito");

        labelObservacao.setText("Observação");

        jTObservacao.setColumns(20);
        jTObservacao.setLineWrap(true);
        jTObservacao.setRows(5);
        jTObservacao.setAlignmentX(0.0F);
        jTObservacao.setAlignmentY(0.0F);
        jScrollPane1.setViewportView(jTObservacao);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDependente)
                    .addComponent(labelLimite)
                    .addComponent(jTDependente, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(labelObservacao)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelLimite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTLimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelDependente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTDependente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(labelObservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(475, 475, 475))
        );

        jTabbedPane1.addTab("Complementar", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Conta Bancária";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {

            Collection<ContaBancariaModel> lColecao = contaBancariaBO.obterTodos(organizacaoModel);

            Collection<BancoModel> lBancos = bancoBO.obterTodos();

            final EventList<ContaBancariaModel> lRegistros = GlazedLists.eventList(lColecao);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboConta, lRegistros, new ContaBancariaTextFilterator());
            support.setFilterMode(TextMatcherEditor.STARTS_WITH);
            support.setStrict(false);

            final EventList<BancoModel> lRegistrosBcos = GlazedLists.eventList(lBancos);
            if (supportBanco != null && supportBanco.getItemList() != null && supportBanco.getComboBox() != null) {
                supportBanco.uninstall();
            }
            supportBanco = AutoCompleteSupport.install(comboBanco, lRegistrosBcos, new BancoTextFilterator());
            supportBanco.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportBanco.setStrict(false);

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
        botaoAlterar.setEnabled(false);
        botaoIncluir.setEnabled(true);
        campoCodigo.setText("");
        comboBanco.setSelectedItem(null);
        comboConta.setSelectedItem(null);
        jTAgencia.setText("");
        jTDependente.setText("");
        jTTitular.setText("");
        jTObservacao.setText("");
        jFTLimiteCredito.setText("0,00");
        jFTSaldoInicial.setText("0,00");
        this.contaContabil = null;
        jTContaContabil.setText("");

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

        if (comboConta.getSelectedItem() != null) {
            valorCombo = comboConta.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                ContaBancariaModel tab = new ContaBancariaModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab.setConta(valorCombo);

                tab = contaBancariaBO.consultarPorTemplate(tab);
                tab.setMovimentoDiarioModel(registroMovimento("Deletar Conta Bancaria", valorCombo, tab.getTitular() + " -> " + tab.getConta(), null, "Deletado"));
                //Criar para todos a opção de excluir apenas um elemento.
                contaBancariaBO.excluir(tab);

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
            exibeMensagemAviso("Campo Conta é obrigatório.", null);
        }

    }//GEN-LAST:event_botaoExcluirActionPerformed
    private Boolean validaCampos() {

        if (!PempecUtil.validaPreenchimentoNumero(comboConta.getSelectedItem().toString())) {
            exibeMensagemAviso("A conta Bancária só pode conter números.", null);
            comboConta.requestFocus();
            return false;
        }

        if (!this.validaPreenchimentoCombo(comboBanco, true)) {
            comboBanco.requestFocus();
            return false;
        }

        if (contaContabil == null) {
            jTContaContabil.requestFocus();
            return false;
        }

        if (jFTSaldoInicial.getValue() == 0) {
            jFTSaldoInicial.requestFocus();
            return false;
        }

        if (jTAgencia.getText().isEmpty()) {
            jTAgencia.requestFocus();
            return false;
        }

        if (jTTitular.getText().isEmpty()) {
            jTTitular.requestFocus();
            return false;
        }

        return true;
    }
    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        String valorCombo = null;

        if (comboConta.getSelectedItem() != null) {
            valorCombo = comboConta.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCampos()) {

                    ContaBancariaModel tab = new ContaBancariaModel();
                    tab.setPk(new PKModel());
                    tab.getPk().setOrganizacao(organizacaoModel);
                    tab.getPk().setId(PempecKeyGenerator.generateKey());

                    //pegando o banco que foi selecionado
                    if (this.validaPreenchimentoCombo(comboBanco, true)) {
                        tab.setBanco(new BancoModel());
                        tab.getBanco().setId(((BancoModel) comboBanco.getSelectedItem()).getId());
                    }

                    tab.setAgencia(jTAgencia.getText());
                    tab.setConta(valorCombo);
                    tab.setTitular(jTTitular.getText().toUpperCase());
                    tab.setDependente(jTDependente.getText().toUpperCase());
                    tab.setLimiteCredito(jFTLimiteCredito.getValue());
                    tab.setSaldoInicial(jFTSaldoInicial.getValue());
                    tab.setObservacao(jTObservacao.getText());
                    tab.setContaContabil(this.contaContabil);


                    tab.setMovimentoDiarioModel(registroMovimento("Inserir Conta Bancaria", valorCombo, tab.getTitular() + " -> " + tab.getConta(), null, "Inserido"));
                    contaBancariaBO.inserir(tab);

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
            exibeMensagemAviso("Campo Conta é obrigatório.", null);
        }
    }//GEN-LAST:event_botaoIncluirActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

        String valorCombo = null;

        if (comboConta.getSelectedItem() != null) {
            valorCombo = comboConta.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.ALTERAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCampos()) {

                    ContaBancariaModel tab = new ContaBancariaModel();

                    tab.setPk(new PKModel());

                    tab.getPk().setOrganizacao(organizacaoModel);

                    tab.getPk().setId(campoCodigo.getText());

                    if (this.validaPreenchimentoCombo(comboBanco, true)) {
                        tab.setBanco(new BancoModel());
                        tab.getBanco().setId(((BancoModel) comboBanco.getSelectedItem()).getId());
                    }

                    tab.setAgencia(jTAgencia.getText());
                    tab.setConta(valorCombo);
                    tab.setTitular(jTTitular.getText().toUpperCase());
                    tab.setDependente(jTDependente.getText().toUpperCase());
                    tab.setLimiteCredito(jFTLimiteCredito.getValue());
                    tab.setSaldoInicial(jFTSaldoInicial.getValue());
                    tab.setObservacao(jTObservacao.getText());
                    tab.setContaContabil(this.contaContabil);

                    tab.setMovimentoDiarioModel(registroMovimento("Alterar Conta Bancaria", valorCombo, tab.getTitular() + " -> " + tab.getConta(), null, "Alterado"));

                    contaBancariaBO.alterar(tab);

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

            exibeMensagemAviso("Campo Conta é obrigatório.", null);
        }

    }//GEN-LAST:event_botaoAlterarActionPerformed

private void comboContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContaActionPerformed
    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboConta.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            ContaBancariaModel tab = new ContaBancariaModel();
            tab.setConta(comboConta.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());
            tab = contaBancariaBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                botaoAlterar.setEnabled(true);
                botaoExcluir.setEnabled(true);
                botaoIncluir.setEnabled(false);

                campoCodigo.setText(tab.getPk().getId());

                for (int i = 1; i < comboBanco.getItemCount(); i++) {
                    if (tab.getBanco().getId().equalsIgnoreCase(((BancoModel) comboBanco.getItemAt(i)).getId())) {
                        comboBanco.setSelectedIndex(i);
                        break;
                    }
                }
                jTAgencia.setText(tab.getAgencia());
                jTTitular.setText(tab.getTitular());
                jTDependente.setText(tab.getDependente());
                jFTLimiteCredito.setText(tab.getLimiteCredito().toString());
                jFTSaldoInicial.setText(PempecParse.doubleToZero(tab.getSaldoInicial()));
                jTObservacao.setText(tab.getObservacao());

                if (tab.getContaContabil() != null) {

                    this.contaContabil = tab.getContaContabil();

                    jTContaContabil.setText(this.contaContabil.getConta());

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
    }
}//GEN-LAST:event_comboContaActionPerformed

private void lupaPesquisaContaContabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lupaPesquisaContaContabilActionPerformed

    try {

        if (pesquisaContaContabil == null || pesquisaContaContabil.isClosed()) {
            pesquisaContaContabil = new PesquisaContaContabil(this, null, this.getLocation());
            TelaPrincipal.desktopPane.add(pesquisaContaContabil);
            pesquisaContaContabil.show();
        } else {
            pesquisaContaContabil.setVisible(true);
            pesquisaContaContabil.moveToFront();
            pesquisaContaContabil.requestFocus();
        }

    } catch (final SystemException ex) {

        final File file = PrintScreen.capture();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                tratamentoExcecao(ex, file);

            }
        });

    }

}//GEN-LAST:event_lupaPesquisaContaContabilActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JComboBox comboBanco;
    private javax.swing.JComboBox comboConta;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private br.com.pempec.componentes.JDoubleField jFTLimiteCredito;
    private br.com.pempec.componentes.JDoubleField jFTSaldoInicial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTAgencia;
    private javax.swing.JTextField jTContaContabil;
    private javax.swing.JTextField jTDependente;
    private javax.swing.JTextArea jTObservacao;
    private javax.swing.JTextField jTTitular;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAgencia;
    private javax.swing.JLabel labelConta;
    private javax.swing.JLabel labelDependente;
    private javax.swing.JLabel labelDescricao1;
    private javax.swing.JLabel labelLimite;
    private javax.swing.JLabel labelNomeBanco;
    private javax.swing.JLabel labelObservacao;
    private javax.swing.JLabel labelSaldoInicial;
    private javax.swing.JLabel labelTitular2;
    private javax.swing.JButton lupaPesquisaContaContabil;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private AutoCompleteSupport supportBanco;
    private PesquisaContaContabil pesquisaContaContabil;
    private ContaContabilModel contaContabil;

    public void setPesquisaContaContabil(ContaContabilModel contaContabil, String tipo) {
        this.contaContabil = contaContabil;
        jTContaContabil.setText(contaContabil.getConta());
    }
}
