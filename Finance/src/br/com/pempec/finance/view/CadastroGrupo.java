package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ConstantesBO;
import br.com.pempec.finance.businessObjects.GrupoActionBO;
import br.com.pempec.finance.businessObjects.GrupoBO;
import br.com.pempec.finance.businessObjects.TelaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ActionModel;
import br.com.pempec.finance.models.ConstantesModel;
import br.com.pempec.finance.models.GrupoActionIDModel;
import br.com.pempec.finance.models.GrupoActionModel;
import br.com.pempec.finance.models.GrupoModel;
import br.com.pempec.finance.models.TelaModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroUsuarioPermissoes;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.iterators.GrupoTextFilterator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.tables.TableModelCadastroUsuarioPermissoes;
import br.com.pempec.finance.utils.Tela;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroGrupo extends FinanceInternalFrame implements IRepopulador {

    private GrupoBO grupoBO = new GrupoBO();
    private TelaBO telaBO = new TelaBO();
    private GrupoActionBO grupoActionBO = new GrupoActionBO();
    private ConstantesBO constantesBO = new ConstantesBO();
    private long tela = Tela.TELA_SEGURANCA_GRUPOS.getTela();

    public CadastroGrupo() throws SystemException {

        initComponents();

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        labelDescricao = new javax.swing.JLabel();
        comboGrupo = new javax.swing.JComboBox();
        campoCodigo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePermissoes = new javax.swing.JTable();
        labelCedente3 = new javax.swing.JLabel();
        checkTodosCadastrar = new javax.swing.JCheckBox();
        checkTodosAlterar = new javax.swing.JCheckBox();
        checkTodosExcluir = new javax.swing.JCheckBox();
        checkTodosImpressao = new javax.swing.JCheckBox();
        checkTodosOutros = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Básico");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(NameObject()));

        labelDescricao.setText("Descrição:");

        comboGrupo.setFont(getFont());
        comboGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGrupoActionPerformed(evt);
            }
        });

        campoCodigo.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(comboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelDescricao))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro", jPanel3);

        tablePermissoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Cadastrar", "Alterar", "Excluir", "Impressão", "Outros"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablePermissoes);

        labelCedente3.setForeground(new java.awt.Color(255, 51, 51));
        labelCedente3.setText("Selecionar Todos:");

        checkTodosCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTodosCadastrarActionPerformed(evt);
            }
        });

        checkTodosAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTodosAlterarActionPerformed(evt);
            }
        });

        checkTodosExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTodosExcluirActionPerformed(evt);
            }
        });

        checkTodosImpressao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTodosImpressaoActionPerformed(evt);
            }
        });

        checkTodosOutros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTodosOutrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(labelCedente3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkTodosCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(checkTodosAlterar)
                        .addGap(19, 19, 19)
                        .addComponent(checkTodosExcluir)
                        .addGap(27, 27, 27)
                        .addComponent(checkTodosImpressao)
                        .addGap(27, 27, 27)
                        .addComponent(checkTodosOutros)
                        .addGap(41, 41, 41))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(checkTodosOutros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkTodosAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkTodosExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkTodosCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkTodosImpressao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCedente3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Permissões", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

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
                .addContainerGap()
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Grupos";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {

            Collection<GrupoModel> lColecao = grupoBO.obterTodos();

            final EventList<GrupoModel> lRegistros = GlazedLists.eventList(lColecao);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboGrupo, lRegistros, new GrupoTextFilterator());
            support.setFilterMode(TextMatcherEditor.STARTS_WITH);
            support.setStrict(false);

            List<TelaModel> lTela = telaBO.obterTodos();

            tablePermissoes.setAutoCreateColumnsFromModel(false);
            tablePermissoes.setModel(new TableModelCadastroUsuarioPermissoes(lTela));
            FontMetrics fm = tablePermissoes.getFontMetrics(tablePermissoes.getFont());
            tablePermissoes.setColumnModel(new ColumnModelCadastroUsuarioPermissoes(fm));
            tablePermissoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tablePermissoes.getTableHeader().setReorderingAllowed(false);

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
    private void comboGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGrupoActionPerformed

        String evento = evt.getActionCommand();
        int modifiers = evt.getModifiers();

        boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
        boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;


        if (comboGrupo.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {


            try {

                GrupoModel tab = new GrupoModel();
                tab.setDescricao(comboGrupo.getSelectedItem().toString());

                if (tab.getDescricao() != null) {

                    tab = grupoBO.consultarPorTemplate(tab);

                }

                if (tab != null && tab.getId() != null) {

                    botaoAlterar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                    botaoIncluir.setEnabled(false);

                    campoCodigo.setText(tab.getId().toString());

                    Collection<GrupoActionModel> grupoActionModels = grupoActionBO.obterPorGrupo(tab);

                    for (GrupoActionModel grupoActionModel : grupoActionModels) {

                        for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

                            if (grupoActionModel.getGrupoActionIDModel().getTela().getId().toString().equals(
                                    tablePermissoes.getValueAt(i, 6).toString())) {

                                String idAction = grupoActionModel.getGrupoActionIDModel().getAction().getId().toString();

                                if (idAction.equals(
                                        Action.CADASTRAR.getAction().toString())) {
                                    tablePermissoes.setValueAt(new Boolean(true), i, 1);
                                }

                                if (idAction.equals(
                                        Action.ALTERAR.getAction().toString())) {
                                    tablePermissoes.setValueAt(new Boolean(true), i, 2);
                                }

                                if (idAction.equals(
                                        Action.EXCLUIR.getAction().toString())) {
                                    tablePermissoes.setValueAt(new Boolean(true), i, 3);
                                }

                                if (idAction.equals(
                                        Action.IMPRESSAO.getAction().toString())) {
                                    tablePermissoes.setValueAt(new Boolean(true), i, 4);
                                }

                                if (idAction.equals(
                                        Action.OUTROS.getAction().toString())) {
                                    tablePermissoes.setValueAt(new Boolean(true), i, 5);
                                }

                                break;

                            }

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
        }

}//GEN-LAST:event_comboGrupoActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        botaoAlterar.setEnabled(false);
        botaoIncluir.setEnabled(true);
        campoCodigo.setText("");
        comboGrupo.setSelectedItem(null);
        checkTodosCadastrar.setSelected(false);
        checkTodosAlterar.setSelected(false);
        checkTodosExcluir.setSelected(false);
        checkTodosImpressao.setSelected(false);
        checkTodosOutros.setSelected(false);
        this.checkTodosCadastrarActionPerformed(evt);
        this.checkTodosAlterarActionPerformed(evt);
        this.checkTodosExcluirActionPerformed(evt);
        this.checkTodosImpressaoActionPerformed(evt);
        this.checkTodosOutrosActionPerformed(evt);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

        if (comboGrupo.getSelectedItem() != null) {
            valorCombo = comboGrupo.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {
                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                GrupoModel tab = new GrupoModel();

                tab.setId(PempecParse.stringToLong(campoCodigo.getText()));

                tab.setDescricao(valorCombo);

                tab.setMovimentoDiarioModel(registroMovimento("Deletar Grupo", valorCombo, tab.getDescricao(), null, "Deletado"));

                grupoBO.excluir(tab);

                Controller.setCollGrupoAction(null);

                ConstantesModel constantesModel = new ConstantesModel();

                constantesModel.setId(Constantes.CONSTANTES_PERMISSOES_MODIFICADA);

                constantesModel = constantesBO.consultarPorPk(constantesModel);

                constantesModel.setCodigo("1");

                constantesBO.alterar(constantesModel);

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

            exibeMensagemAviso("Campo Descrição é obrigatório.", null);
        }

    }//GEN-LAST:event_botaoExcluirActionPerformed
    private Boolean validaCampos() {

        if (comboGrupo.getSelectedItem() == null) {
            comboGrupo.requestFocus();
            return false;
        }

        return true;
    }
    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        String valorCombo = null;

        if (comboGrupo.getSelectedItem() != null) {
            valorCombo = comboGrupo.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {
                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCampos()) {

                    GrupoModel tab = new GrupoModel();
                    tab.setId(PempecKeyGenerator.generateKeyLong());

                    tab.setDescricao(valorCombo);

                    //Inserindo as permissoes para o usuario

                    Collection<GrupoActionModel> permissoes = new ArrayList<GrupoActionModel>();

                    GrupoActionIDModel checkID;
                    GrupoActionModel check;

                    for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

                        if ((Boolean) tablePermissoes.getValueAt(i, 1)) {

                            check = new GrupoActionModel();

                            checkID = new GrupoActionIDModel();

                            checkID.setId(PempecKeyGenerator.generateKeyLong());

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.CADASTRAR.getAction());

                            check.setGrupoActionIDModel(checkID);

                            permissoes.add(check);

                        }

                        if ((Boolean) tablePermissoes.getValueAt(i, 2)) {

                            check = new GrupoActionModel();

                            checkID = new GrupoActionIDModel();

                            checkID.setId(PempecKeyGenerator.generateKeyLong());

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.ALTERAR.getAction());

                            check.setGrupoActionIDModel(checkID);

                            permissoes.add(check);

                        }

                        if ((Boolean) tablePermissoes.getValueAt(i, 3)) {

                            check = new GrupoActionModel();

                            checkID = new GrupoActionIDModel();

                            checkID.setId(PempecKeyGenerator.generateKeyLong());

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.EXCLUIR.getAction());

                            check.setGrupoActionIDModel(checkID);

                            permissoes.add(check);

                        }

                        if ((Boolean) tablePermissoes.getValueAt(i, 4)) {

                            check = new GrupoActionModel();

                            checkID = new GrupoActionIDModel();

                            checkID.setId(PempecKeyGenerator.generateKeyLong());

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.IMPRESSAO.getAction());

                            check.setGrupoActionIDModel(checkID);

                            permissoes.add(check);

                        }

                        if ((Boolean) tablePermissoes.getValueAt(i, 5)) {

                            check = new GrupoActionModel();

                            checkID = new GrupoActionIDModel();

                            checkID.setId(PempecKeyGenerator.generateKeyLong());

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.OUTROS.getAction());

                            check.setGrupoActionIDModel(checkID);

                            permissoes.add(check);

                        }

                    }

                    tab.setListaPermissoes(permissoes);

                    tab.setMovimentoDiarioModel(registroMovimento("Inserir Grupo", valorCombo, tab.getDescricao(), null, "Inserido"));
                    grupoBO.inserir(tab);

                    Controller.setCollGrupoAction(null);

                    ConstantesModel constantesModel = new ConstantesModel();

                    constantesModel.setId(Constantes.CONSTANTES_PERMISSOES_MODIFICADA);

                    constantesModel = constantesBO.consultarPorPk(constantesModel);

                    constantesModel.setCodigo("1");

                    constantesBO.alterar(constantesModel);

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
            exibeMensagemAviso("Campo Descrição é obrigatório.", null);
        }
    }//GEN-LAST:event_botaoIncluirActionPerformed

private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

    String valorCombo = null;

    if (comboGrupo.getSelectedItem() != null) {
        valorCombo = comboGrupo.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {

                GrupoModel tab = new GrupoModel();
                tab.setId(PempecParse.stringToLong(campoCodigo.getText()));

                tab.setDescricao(valorCombo);

                //Inserindo as permissoes para o usuario

                Collection<GrupoActionModel> permissoes = new ArrayList<GrupoActionModel>();

                GrupoActionIDModel checkID;
                GrupoActionModel check;

                for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

                    if ((Boolean) tablePermissoes.getValueAt(i, 1)) {

                        check = new GrupoActionModel();

                        checkID = new GrupoActionIDModel();

                        checkID.setId(PempecKeyGenerator.generateKeyLong());

                        checkID.setTela(new TelaModel());

                        checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                        checkID.setAction(new ActionModel());

                        checkID.getAction().setId(Action.CADASTRAR.getAction());

                        check.setGrupoActionIDModel(checkID);

                        permissoes.add(check);

                    }

                    if ((Boolean) tablePermissoes.getValueAt(i, 2)) {

                        check = new GrupoActionModel();

                        checkID = new GrupoActionIDModel();

                        checkID.setId(PempecKeyGenerator.generateKeyLong());

                        checkID.setTela(new TelaModel());

                        checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                        checkID.setAction(new ActionModel());

                        checkID.getAction().setId(Action.ALTERAR.getAction());

                        check.setGrupoActionIDModel(checkID);

                        permissoes.add(check);

                    }

                    if ((Boolean) tablePermissoes.getValueAt(i, 3)) {

                        check = new GrupoActionModel();

                        checkID = new GrupoActionIDModel();

                        checkID.setId(PempecKeyGenerator.generateKeyLong());

                        checkID.setTela(new TelaModel());

                        checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                        checkID.setAction(new ActionModel());

                        checkID.getAction().setId(Action.EXCLUIR.getAction());

                        check.setGrupoActionIDModel(checkID);

                        permissoes.add(check);

                    }

                    if ((Boolean) tablePermissoes.getValueAt(i, 4)) {

                        check = new GrupoActionModel();

                        checkID = new GrupoActionIDModel();

                        checkID.setId(PempecKeyGenerator.generateKeyLong());

                        checkID.setTela(new TelaModel());

                        checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                        checkID.setAction(new ActionModel());

                        checkID.getAction().setId(Action.IMPRESSAO.getAction());

                        check.setGrupoActionIDModel(checkID);

                        permissoes.add(check);

                    }

                    if ((Boolean) tablePermissoes.getValueAt(i, 5)) {

                        check = new GrupoActionModel();

                        checkID = new GrupoActionIDModel();

                        checkID.setId(PempecKeyGenerator.generateKeyLong());

                        checkID.setTela(new TelaModel());

                        checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                        checkID.setAction(new ActionModel());

                        checkID.getAction().setId(Action.OUTROS.getAction());

                        check.setGrupoActionIDModel(checkID);

                        permissoes.add(check);

                    }

                }

                tab.setListaPermissoes(permissoes);


                tab.setMovimentoDiarioModel(registroMovimento("Alterar Grupo", valorCombo, tab.getDescricao(), null, "Alterado"));
                grupoBO.alterar(tab);

                Controller.setCollGrupoAction(null);

                ConstantesModel constantesModel = new ConstantesModel();

                constantesModel.setId(Constantes.CONSTANTES_PERMISSOES_MODIFICADA);

                constantesModel = constantesBO.consultarPorPk(constantesModel);

                constantesModel.setCodigo("1");

                constantesBO.alterar(constantesModel);

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
        exibeMensagemAviso("Campo Descrição é obrigatório.", null);
    }

}//GEN-LAST:event_botaoAlterarActionPerformed

private void checkTodosCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTodosCadastrarActionPerformed

    for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

        tablePermissoes.setValueAt(checkTodosCadastrar.isSelected(), i, 1);

    }

}//GEN-LAST:event_checkTodosCadastrarActionPerformed

private void checkTodosAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTodosAlterarActionPerformed

    for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

        tablePermissoes.setValueAt(checkTodosAlterar.isSelected(), i, 2);

    }
}//GEN-LAST:event_checkTodosAlterarActionPerformed

private void checkTodosExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTodosExcluirActionPerformed
    for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

        tablePermissoes.setValueAt(checkTodosExcluir.isSelected(), i, 3);

    }
}//GEN-LAST:event_checkTodosExcluirActionPerformed

private void checkTodosImpressaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTodosImpressaoActionPerformed
    for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

        tablePermissoes.setValueAt(checkTodosImpressao.isSelected(), i, 4);

    }
}//GEN-LAST:event_checkTodosImpressaoActionPerformed

private void checkTodosOutrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTodosOutrosActionPerformed
    for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

        tablePermissoes.setValueAt(checkTodosOutros.isSelected(), i, 5);

    }
}//GEN-LAST:event_checkTodosOutrosActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JCheckBox checkTodosAlterar;
    private javax.swing.JCheckBox checkTodosCadastrar;
    private javax.swing.JCheckBox checkTodosExcluir;
    private javax.swing.JCheckBox checkTodosImpressao;
    private javax.swing.JCheckBox checkTodosOutros;
    private javax.swing.JComboBox comboGrupo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCedente3;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JTable tablePermissoes;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
}
