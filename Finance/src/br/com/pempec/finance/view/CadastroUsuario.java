package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ConstantesBO;
import br.com.pempec.finance.businessObjects.GrupoBO;
import br.com.pempec.finance.businessObjects.GrupoUsuarioBO;
import br.com.pempec.finance.businessObjects.OrganizacaoBO;
import br.com.pempec.finance.businessObjects.TelaBO;
import br.com.pempec.finance.businessObjects.UsuarioActionBO;
import br.com.pempec.finance.businessObjects.UsuarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ActionModel;
import br.com.pempec.finance.models.ConstantesModel;
import br.com.pempec.finance.models.GrupoModel;
import br.com.pempec.finance.models.GrupoUsuarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TelaModel;
import br.com.pempec.finance.models.UsuarioActionIDModel;
import br.com.pempec.finance.models.UsuarioActionModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroUsuarioGrupos;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroUsuarioPermissoes;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.MD5;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.tables.TableModelCadastroUsuarioGrupos;
import br.com.pempec.finance.utils.tables.TableModelCadastroUsuarioPermissoes;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.UsuarioTextFilterator;
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
public class CadastroUsuario extends FinanceInternalFrame implements IRepopulador {

    private UsuarioBO usuarioBO = new UsuarioBO();
    private OrganizacaoBO organizacaoBO = new OrganizacaoBO();
    private GrupoBO grupoBO = new GrupoBO();
    private TelaBO telaBO = new TelaBO();
    private GrupoUsuarioBO grupoUsuarioBO = new GrupoUsuarioBO();
    private UsuarioActionBO usuarioActionBO = new UsuarioActionBO();
    private ConstantesBO constantesBO = new ConstantesBO();
    private long tela = Tela.TELA_SEGURANCA_USUARIOS.getTela();

    private String NameObject() {
        return (String) "Usuários";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {

            Collection<UsuarioModel> lUsuario = usuarioBO.obterTodos();

            Collection<OrganizacaoModel> lOrganizacao = new ArrayList<OrganizacaoModel>();

            OrganizacaoModel org = new OrganizacaoModel();

            org.setRazaoSocial("--> Todas <---");

            lOrganizacao.add(org);

            lOrganizacao.addAll(organizacaoBO.obterTodos());

            comboOrganizacao.setModel(new javax.swing.DefaultComboBoxModel(lOrganizacao.toArray()));

            comboStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"INATIVO", "ATIVO"}));

            final EventList<UsuarioModel> lRegistros = GlazedLists.eventList(lUsuario);

            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboUsuario, lRegistros, new UsuarioTextFilterator());
            support.setFilterMode(TextMatcherEditor.STARTS_WITH);
            support.setStrict(false);

            List<GrupoModel> lGrupo = grupoBO.obterTodos();

            tableGrupos.setAutoCreateColumnsFromModel(false);
            tableGrupos.setModel(new TableModelCadastroUsuarioGrupos(lGrupo));
            FontMetrics fm = tableGrupos.getFontMetrics(tableGrupos.getFont());
            tableGrupos.setColumnModel(new ColumnModelCadastroUsuarioGrupos(fm));
            tableGrupos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableGrupos.getTableHeader().setReorderingAllowed(false);

            List<TelaModel> lTela = telaBO.obterTodos();

            tablePermissoes.setAutoCreateColumnsFromModel(false);
            tablePermissoes.setModel(new TableModelCadastroUsuarioPermissoes(lTela));
            fm = tablePermissoes.getFontMetrics(tablePermissoes.getFont());
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

    public CadastroUsuario() throws SystemException {

        initComponents();

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);

        //Aplicando tamanho maximo de caracteres do campo.
        jFTLogin.setDocument(new Documento(30));

        labelDataAcesso.setVisible(false);
        jFTDataUltimoAcesso.setVisible(false);



    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoSalvar = new javax.swing.JButton();
        botaoResetarSenha = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        labelCedente = new javax.swing.JLabel();
        comboUsuario = new javax.swing.JComboBox();
        labelCpf = new javax.swing.JLabel();
        jFTLogin = new javax.swing.JFormattedTextField();
        campoCodigo = new javax.swing.JTextField();
        comboStatus = new javax.swing.JComboBox();
        labelPersonalidade = new javax.swing.JLabel();
        jFTDataUltimoAcesso = new javax.swing.JFormattedTextField();
        labelDataAcesso = new javax.swing.JLabel();
        jCEhAdministrador = new javax.swing.JCheckBox();
        comboOrganizacao = new javax.swing.JComboBox();
        labelCpf1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGrupos = new javax.swing.JTable();
        labelCedente4 = new javax.swing.JLabel();
        checkTodosGrupos = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePermissoes = new javax.swing.JTable();
        labelCedente3 = new javax.swing.JLabel();
        checkTodosCadastrar = new javax.swing.JCheckBox();
        checkTodosAlterar = new javax.swing.JCheckBox();
        checkTodosExcluir = new javax.swing.JCheckBox();
        checkTodosImpressao = new javax.swing.JCheckBox();
        checkTodosOutros = new javax.swing.JCheckBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance -Cadastro Usuário");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoSalvar.setMnemonic('I');
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoResetarSenha.setMnemonic('A');
        botaoResetarSenha.setText("Resetar Senha");
        botaoResetarSenha.setEnabled(false);
        botaoResetarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoResetarSenhaActionPerformed(evt);
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
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoResetarSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoExcluir, botaoFechar, botaoLimpar, botaoSalvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoResetarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelCedente.setText("Usuário");

        comboUsuario.setFont(getFont());
        comboUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUsuarioActionPerformed(evt);
            }
        });

        labelCpf.setText("Login");

        campoCodigo.setEditable(false);

        comboStatus.setFont(new java.awt.Font("Arial", 0, 10));

        labelPersonalidade.setFont(new java.awt.Font("Arial", 0, 12));
        labelPersonalidade.setText("Status");

        jFTDataUltimoAcesso.setEditable(false);

        labelDataAcesso.setFont(new java.awt.Font("Tahoma", 0, 12));
        labelDataAcesso.setText("Último Acesso ao Sistema");

        jCEhAdministrador.setText("O Usuário será administrador do Sistema?");
        jCEhAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCEhAdministradorActionPerformed(evt);
            }
        });

        comboOrganizacao.setFont(new java.awt.Font("Arial", 0, 12));

        labelCpf1.setText("Organizações");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(labelDataAcesso)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jFTDataUltimoAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(129, 129, 129))
                        .addComponent(comboOrganizacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCpf1)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelCedente)
                                .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFTLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelCpf))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelPersonalidade)
                                .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jCEhAdministrador)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCedente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelPersonalidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCpf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelCpf1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboOrganizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCEhAdministrador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataAcesso)
                    .addComponent(jFTDataUltimoAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jTabbedPane1.addTab("Cadastro", jPanel5);

        tableGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Opção", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableGrupos);

        labelCedente4.setForeground(new java.awt.Color(255, 51, 51));
        labelCedente4.setText("Selecionar Todos:");

        checkTodosGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTodosGruposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(labelCedente4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkTodosGrupos)
                .addContainerGap(400, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelCedente4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkTodosGrupos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(224, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("Grupos", jPanel1);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(labelCedente3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkTodosCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(checkTodosAlterar)
                        .addGap(19, 19, 19)
                        .addComponent(checkTodosExcluir)
                        .addGap(27, 27, 27)
                        .addComponent(checkTodosImpressao)
                        .addGap(27, 27, 27)
                        .addComponent(checkTodosOutros)
                        .addGap(41, 41, 41))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
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

        jTabbedPane1.addTab("Permissões", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        comboOrganizacao.setSelectedIndex(0);
        botaoResetarSenha.setEnabled(false);
        botaoSalvar.setEnabled(true);

        comboUsuario.setSelectedItem(null);
        jFTLogin.setText("");

        campoCodigo.setText("");
        jFTDataUltimoAcesso.setText(null);
        labelDataAcesso.setVisible(false);
        jFTDataUltimoAcesso.setVisible(false);

        jCEhAdministrador.setSelected(false);

        checkTodosCadastrar.setSelected(false);
        checkTodosAlterar.setSelected(false);
        checkTodosExcluir.setSelected(false);
        checkTodosImpressao.setSelected(false);
        checkTodosOutros.setSelected(false);
        checkTodosGrupos.setSelected(false);
        this.checkTodosCadastrarActionPerformed(evt);
        this.checkTodosAlterarActionPerformed(evt);
        this.checkTodosExcluirActionPerformed(evt);
        this.checkTodosImpressaoActionPerformed(evt);
        this.checkTodosOutrosActionPerformed(evt);
        this.checkTodosGruposActionPerformed(evt);
        checkTodosGrupos.setEnabled(true);
        checkTodosAlterar.setEnabled(true);
        checkTodosCadastrar.setEnabled(true);
        checkTodosExcluir.setEnabled(true);
        checkTodosImpressao.setEnabled(true);
        checkTodosOutros.setEnabled(true);
        tableGrupos.setEnabled(true);
        tablePermissoes.setEnabled(true);


    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
//GEN-LAST:event_botaoExcluirActionPerformed
        String valorCombo = null;

        if (comboUsuario.getSelectedItem() != null) {
            valorCombo = comboUsuario.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                if (!Controller.checarPermissao(tela, Action.EXCLUIR.getAction())) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                UsuarioModel tab = (UsuarioModel) comboUsuario.getSelectedItem();

                if (tab.getUltimoAcesso() != null) {
                    exibeMensagemAviso("Usuário não pode ser Excluído.\n Associações existentes!", null);
                    return;
                }

                tab = usuarioBO.consultarPorTemplate(tab);

                tab.setMovimentoDiarioModel(registroMovimento("Deletar", tab.getLogin(), tab.getNome(), null, "Deletado"));

                usuarioBO.excluir(tab);

                Controller.setCollGrupoUsuario(null);
                Controller.setCollUsuarioAction(null);

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
            exibeMensagemAviso("Campo Nome obrigatório.", null);
        }


    }

    private Boolean validaCampos() {

        if (comboUsuario.getSelectedItem() == null) {
            comboUsuario.requestFocus();
            return false;
        }


        if (comboOrganizacao.getSelectedItem() == null) {
            comboOrganizacao.requestFocus();
            return false;
        }

        if (jFTLogin.getText().isEmpty()) {
            jFTLogin.requestFocus();
            return false;
        }

        return true;

    }

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed

        String valorCombo = null;

        if (comboUsuario.getSelectedItem() != null) {
            valorCombo = comboUsuario.getSelectedItem().toString().toUpperCase();
        }

        /**
         * Somente administradores podem criar ou alterar usuários.
         *
         */
        if (Controller.getUsuarioLogado().getEhAdministrador() == null || (Controller.getUsuarioLogado().getEhAdministrador() != null
                && Controller.getUsuarioLogado().getEhAdministrador().intValue() == 0)) {
            exibeMensagemAviso("Somente usuários administradores podem criar/alterar usuários.", null);
            return;
        }

        if (valorCombo != null) {

            try {

                if (validaCampos()) {

                    UsuarioModel tab = new UsuarioModel();

                    tab.setLogin(jFTLogin.getText());
                    tab.setNome(valorCombo.toUpperCase());

                    if (campoCodigo.getText().isEmpty()) {
                        tab.setSenha(MD5.criptografar(Constantes.SENHA_PADRAO));
                    } else {
                        tab.setId(PempecParse.stringToLong(campoCodigo.getText()));
                        tab = usuarioBO.consultarPorLogin(tab);
                    }

                    int status = 1;
                    if (comboStatus.getSelectedItem().toString().equalsIgnoreCase("INATIVO")) {
                        status = 0;
                    }

                    tab.setAtivo(status);

                    if (jCEhAdministrador.isSelected()) {

                        tab.setEhAdministrador(1);

                    } else {

                        tab.setEhAdministrador(0);

                    }

                    Collection<GrupoModel> grupos = new ArrayList<GrupoModel>();

                    for (int i = 0; i < tableGrupos.getRowCount(); i++) {

                        if ((Boolean) tableGrupos.getValueAt(i, 0)) {

                            GrupoModel grupoModel = new GrupoModel();

                            grupoModel.setId(PempecParse.stringToLong(tableGrupos.getValueAt(i, 2).toString()));

                            grupos.add(grupoModel);

                        }

                    }

                    tab.setListaGrupos(grupos);

                    Collection<UsuarioActionModel> permissoes = new ArrayList<UsuarioActionModel>();

                    UsuarioActionIDModel checkID;
                    UsuarioActionModel check;

                    for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

                        if ((Boolean) tablePermissoes.getValueAt(i, 1)) {

                            check = new UsuarioActionModel();

                            checkID = new UsuarioActionIDModel();

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.CADASTRAR.getAction());

                            check.setUsuarioActionIDModel(checkID);

                            permissoes.add(check);

                        }

                        if ((Boolean) tablePermissoes.getValueAt(i, 2)) {

                            check = new UsuarioActionModel();

                            checkID = new UsuarioActionIDModel();

                            checkID.setId(PempecKeyGenerator.generateKeyLong());

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.ALTERAR.getAction());

                            check.setUsuarioActionIDModel(checkID);

                            permissoes.add(check);

                        }

                        if ((Boolean) tablePermissoes.getValueAt(i, 3)) {

                            check = new UsuarioActionModel();

                            checkID = new UsuarioActionIDModel();

                            checkID.setId(PempecKeyGenerator.generateKeyLong());

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.EXCLUIR.getAction());

                            check.setUsuarioActionIDModel(checkID);

                            permissoes.add(check);

                        }

                        if ((Boolean) tablePermissoes.getValueAt(i, 4)) {

                            check = new UsuarioActionModel();

                            checkID = new UsuarioActionIDModel();

                            checkID.setId(PempecKeyGenerator.generateKeyLong());

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.IMPRESSAO.getAction());

                            check.setUsuarioActionIDModel(checkID);

                            permissoes.add(check);

                        }

                        if ((Boolean) tablePermissoes.getValueAt(i, 5)) {

                            check = new UsuarioActionModel();

                            checkID = new UsuarioActionIDModel();

                            checkID.setId(PempecKeyGenerator.generateKeyLong());

                            checkID.setTela(new TelaModel());

                            checkID.getTela().setId(PempecParse.stringToLong(tablePermissoes.getValueAt(i, 6).toString()));

                            checkID.setAction(new ActionModel());

                            checkID.getAction().setId(Action.OUTROS.getAction());

                            check.setUsuarioActionIDModel(checkID);

                            permissoes.add(check);

                        }

                    }



                    tab.setListaPermissoes(permissoes);

                    if (comboOrganizacao.getSelectedIndex() == 0) {

                        tab.setOrganizacao(null);

                    } else {

                        tab.setOrganizacao((OrganizacaoModel) comboOrganizacao.getSelectedItem());

                    }


                    if (tab.getId() == null && (campoCodigo.getText().isEmpty() || campoCodigo.getText() == null)) {

                        tab.setId(PempecParse.stringToLong(PempecKeyGenerator.generateIdUsuario()));

                    }


                    tab.setMovimentoDiarioModel(registroMovimento("Salvar", tab.getId().toString(), tab.getNome(), null, "Inserido"));

                    usuarioBO.salvar(tab);

                    Controller.setCollGrupoUsuario(null);
                    Controller.setCollUsuarioAction(null);

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
            exibeMensagemAviso("Campo Nome obrigatório.", null);
        }
}//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoResetarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoResetarSenhaActionPerformed

        String valorCombo = null;

        if (comboUsuario.getSelectedItem() != null) {
            valorCombo = comboUsuario.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                if (!Controller.checarPermissao(tela, Action.OUTROS.getAction())) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (Controller.getUsuarioLogado().getEhAdministrador() == null || (Controller.getUsuarioLogado().getEhAdministrador() != null
                        && Controller.getUsuarioLogado().getEhAdministrador().intValue() == 0)) {
                    exibeMensagemAviso("Somente usuários administradores do sistema podem resetar senha! \n Solicitar à um usuário administrador.", null);
                    return;

                }

                UsuarioModel tab = new UsuarioModel();
                tab.setOrganizacao(Controller.getOrganizacao());
                tab.setLogin(jFTLogin.getText());
                tab.setNome(valorCombo);
                tab.setId(PempecParse.stringToLong(campoCodigo.getText()));
                tab = usuarioBO.consultarPorLogin(tab);

                tab.setSenha(MD5.criptografar(Constantes.SENHA_PADRAO));

                tab.setMovimentoDiarioModel(registroMovimento("ResetarSenha", tab.getLogin(), tab.getNome(), null, "Padrão"));

                usuarioBO.resetarSenha(tab);

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
            exibeMensagemAviso("Campo Nome obrigatório.", null);
        }

}//GEN-LAST:event_botaoResetarSenhaActionPerformed

private void comboUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUsuarioActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboUsuario.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {


        try {

            UsuarioModel tab = new UsuarioModel();
            tab.setNome(comboUsuario.getSelectedItem().toString());

            tab = usuarioBO.consultarPorTemplate(tab);

            if (tab != null && tab.getId() != null) {

                botaoResetarSenha.setEnabled(true);
                botaoSalvar.setEnabled(true);

                if (tab.getUltimoAcesso() != null) {
                    botaoExcluir.setEnabled(false);
                } else {
                    botaoExcluir.setEnabled(true);
                }


                jFTDataUltimoAcesso.setVisible(false);
                labelDataAcesso.setVisible(false);

                campoCodigo.setText(tab.getId().toString());
                jFTLogin.setText(tab.getLogin());

                if (tab.getUltimoAcesso() != null) {
                    jFTDataUltimoAcesso.setText(PempecParse.dateHourToString(tab.getUltimoAcesso()));
                    jFTDataUltimoAcesso.setVisible(true);
                    labelDataAcesso.setVisible(true);
                }


                String auxSatus = "ATIVO";
                if (tab.getAtivo() == 0) {
                    auxSatus = "INATIVO";
                }

                if (tab.getOrganizacao() == null) {

                    comboOrganizacao.setSelectedIndex(0);

                } else {

                    if (tab.getOrganizacao() != null && tab.getOrganizacao().getId() != null) {
                        for (int i = 1; i < comboOrganizacao.getItemCount(); i++) {
                            if (tab.getOrganizacao().getId().equalsIgnoreCase(((OrganizacaoModel) comboOrganizacao.getItemAt(i)).getId())) {
                                comboOrganizacao.setSelectedIndex(i);
                                break;
                            }
                        }
                    }




                }



                for (int i = 1; i < comboStatus.getItemCount(); i++) {
                    if (auxSatus.equalsIgnoreCase(((String) comboStatus.getItemAt(i)).toString())) {
                        comboStatus.setSelectedIndex(i);
                        break;
                    }
                }

                checkTodosCadastrar.setSelected(false);
                checkTodosAlterar.setSelected(false);
                checkTodosExcluir.setSelected(false);
                checkTodosImpressao.setSelected(false);
                checkTodosOutros.setSelected(false);
                checkTodosGrupos.setSelected(false);
                this.checkTodosCadastrarActionPerformed(evt);
                this.checkTodosAlterarActionPerformed(evt);
                this.checkTodosExcluirActionPerformed(evt);
                this.checkTodosImpressaoActionPerformed(evt);
                this.checkTodosOutrosActionPerformed(evt);
                this.checkTodosGruposActionPerformed(evt);

                if (tab.getEhAdministrador() != null && tab.getEhAdministrador().intValue() == 1) {

                    jCEhAdministrador.setSelected(true);

                    checkTodosCadastrar.setSelected(false);
                    checkTodosAlterar.setSelected(false);
                    checkTodosExcluir.setSelected(false);
                    checkTodosImpressao.setSelected(false);
                    checkTodosOutros.setSelected(false);
                    checkTodosGrupos.setSelected(false);
                    this.checkTodosCadastrarActionPerformed(evt);
                    this.checkTodosAlterarActionPerformed(evt);
                    this.checkTodosExcluirActionPerformed(evt);
                    this.checkTodosImpressaoActionPerformed(evt);
                    this.checkTodosOutrosActionPerformed(evt);
                    this.checkTodosGruposActionPerformed(evt);
                    checkTodosGrupos.setEnabled(false);
                    checkTodosAlterar.setEnabled(false);
                    checkTodosCadastrar.setEnabled(false);
                    checkTodosExcluir.setEnabled(false);
                    checkTodosImpressao.setEnabled(false);
                    checkTodosOutros.setEnabled(false);
                    tableGrupos.setEnabled(false);
                    tablePermissoes.setEnabled(false);

                }

                Collection<GrupoUsuarioModel> gruposAssociados = grupoUsuarioBO.obterPorUsuario(tab);

                for (GrupoUsuarioModel grupoUsuarioModel : gruposAssociados) {

                    String idGrupo = grupoUsuarioModel.getGrupoUsuarioIDModel().getGrupo().getId().toString();

                    for (int i = 0; i < tableGrupos.getRowCount(); i++) {

                        if (tableGrupos.getValueAt(i, 2).toString().equals(idGrupo)) {

                            tableGrupos.setValueAt(new Boolean(true), i, 0);

                        }

                    }

                }

                Collection<UsuarioActionModel> usuarioActionModels = usuarioActionBO.obterPorUsuario(tab);

                for (UsuarioActionModel usuarioActionModel : usuarioActionModels) {

                    for (int i = 0; i < tablePermissoes.getRowCount(); i++) {

                        if (usuarioActionModel.getUsuarioActionIDModel().getTela().getId().toString().equals(
                                tablePermissoes.getValueAt(i, 6).toString())) {

                            String idAction = usuarioActionModel.getUsuarioActionIDModel().getAction().getId().toString();

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



}//GEN-LAST:event_comboUsuarioActionPerformed

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

private void checkTodosGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTodosGruposActionPerformed

    for (int i = 0; i < tableGrupos.getRowCount(); i++) {

        tableGrupos.setValueAt(checkTodosGrupos.isSelected(), i, 0);

    }

}//GEN-LAST:event_checkTodosGruposActionPerformed

private void jCEhAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCEhAdministradorActionPerformed

    if (jCEhAdministrador.isSelected()) {

        checkTodosCadastrar.setSelected(false);
        checkTodosAlterar.setSelected(false);
        checkTodosExcluir.setSelected(false);
        checkTodosImpressao.setSelected(false);
        checkTodosOutros.setSelected(false);
        checkTodosGrupos.setSelected(false);
        this.checkTodosCadastrarActionPerformed(evt);
        this.checkTodosAlterarActionPerformed(evt);
        this.checkTodosExcluirActionPerformed(evt);
        this.checkTodosImpressaoActionPerformed(evt);
        this.checkTodosOutrosActionPerformed(evt);
        this.checkTodosGruposActionPerformed(evt);
        checkTodosGrupos.setEnabled(false);
        checkTodosAlterar.setEnabled(false);
        checkTodosCadastrar.setEnabled(false);
        checkTodosExcluir.setEnabled(false);
        checkTodosImpressao.setEnabled(false);
        checkTodosOutros.setEnabled(false);
        tableGrupos.setEnabled(false);
        tablePermissoes.setEnabled(false);

    } else {

        checkTodosGrupos.setEnabled(true);
        checkTodosAlterar.setEnabled(true);
        checkTodosCadastrar.setEnabled(true);
        checkTodosExcluir.setEnabled(true);
        checkTodosImpressao.setEnabled(true);
        checkTodosOutros.setEnabled(true);
        tableGrupos.setEnabled(true);
        tablePermissoes.setEnabled(true);

    }


}//GEN-LAST:event_jCEhAdministradorActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoResetarSenha;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JCheckBox checkTodosAlterar;
    private javax.swing.JCheckBox checkTodosCadastrar;
    private javax.swing.JCheckBox checkTodosExcluir;
    private javax.swing.JCheckBox checkTodosGrupos;
    private javax.swing.JCheckBox checkTodosImpressao;
    private javax.swing.JCheckBox checkTodosOutros;
    private javax.swing.JComboBox comboOrganizacao;
    private javax.swing.JComboBox comboStatus;
    private javax.swing.JComboBox comboUsuario;
    private javax.swing.JCheckBox jCEhAdministrador;
    private javax.swing.JFormattedTextField jFTDataUltimoAcesso;
    private javax.swing.JFormattedTextField jFTLogin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCedente3;
    private javax.swing.JLabel labelCedente4;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelCpf1;
    private javax.swing.JLabel labelDataAcesso;
    private javax.swing.JLabel labelPersonalidade;
    private javax.swing.JTable tableGrupos;
    private javax.swing.JTable tablePermissoes;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
}
