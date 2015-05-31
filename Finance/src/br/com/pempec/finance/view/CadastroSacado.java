package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BairroBO;
import br.com.pempec.finance.businessObjects.BancoBO;
import br.com.pempec.finance.businessObjects.CidadeBO;
import br.com.pempec.finance.businessObjects.ContaContabilBO;
import br.com.pempec.finance.businessObjects.EstadoBO;
import br.com.pempec.finance.businessObjects.SacadoBO;
import br.com.pempec.finance.businessObjects.TipoSacadoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BairroModel;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.CidadeModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.EstadoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TipoSacadoModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IPopUpContaContabil;
import br.com.pempec.finance.utils.IRepopuladorNew;
import br.com.pempec.finance.utils.MaskUtils;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.BairroTextFilterator;
import br.com.pempec.finance.utils.iterators.BancoTextFilterator;
import br.com.pempec.finance.utils.iterators.SacadoTextFilterator;
import br.com.pempec.finance.utils.iterators.CidadeTextFilterator;
import br.com.pempec.finance.utils.iterators.EstadoTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroSacado extends FinanceInternalFrame implements IPopUpContaContabil, IRepopuladorNew {

    private SacadoBO sacadoBO = new SacadoBO();
    private ContaContabilBO contaContabilBO = new ContaContabilBO();
    private TipoSacadoBO tipoSacadoBO = new TipoSacadoBO();
    private EstadoBO estadoBO = new EstadoBO();
    private CidadeBO cidadeBO = new CidadeBO();
    private BairroBO bairroBO = new BairroBO();
    private BancoBO bancoBO = new BancoBO();
    private Collection<SacadoModel> lColecao;
    private EventList<SacadoModel> lRegistros;
    private Collection<TipoSacadoModel> lTipoSacados;
    private Collection<BancoModel> lBanco;
    private EventList<BancoModel> lRegBancos;
    private Collection<EstadoModel> lEstados;
    private EventList<EstadoModel> lRegEstados;
    private Collection<CidadeModel> lCidades;
    private EventList<CidadeModel> lRegCidades;
    private Collection<BairroModel> lBairros;
    private EventList<BairroModel> lRegBairros;
    private long tela = Tela.TELA_PARAMETROS_SACADOS.getTela();
    String valorCombo = null;

    private String NameObject() {
        return (String) "Sacado";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }
    /*
     public void repopularCombos() {

     try {

     comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" Selecione",
     "Débito", "Crédito"
     }));

     Collection<SacadoModel> lColecao = sacadoBO.obterTodos(organizacaoModel);

     Collection<TipoSacadoModel> lTipoSacados = new ArrayList<TipoSacadoModel>();

     Collection<EstadoModel> lEstados = estadoBO.obterTodos();

     Collection<BairroModel> lBairros = bairroBO.obterTodos();

     Collection<CidadeModel> lCidades = cidadeBO.obterTodos();

     //Adicionando a opção de selecionar, caso o campo não seja obrigatório!
     TipoSacadoModel tipoSacadoModel = new TipoSacadoModel();

     tipoSacadoModel.setDescricao(" -> Selecione <- ");

     lTipoSacados.add(tipoSacadoModel);

     lTipoSacados.addAll(tipoSacadoBO.obterTodos(organizacaoModel));

     Collection<BancoModel> lBanco = bancoBO.obterTodos();

     comboTipoSacado.setModel(new javax.swing.DefaultComboBoxModel(lTipoSacados.toArray()));

     comboPersonalidade.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" -> Selecione <- ",
     "PF", "PJ"
     }));

     final EventList<SacadoModel> lRegistros = GlazedLists.eventList(lColecao);
     if (support != null && support.getItemList() != null && support.getComboBox() != null) {
     support.uninstall();
     }
     support = AutoCompleteSupport.install(comboSacado, lRegistros, new SacadoTextFilterator());
     support.setFilterMode(TextMatcherEditor.STARTS_WITH);
     support.setStrict(false);

     //comboBairro
     final EventList<BairroModel> lRegBairros = GlazedLists.eventList(lBairros);
     if (supportBairro != null && supportBairro.getItemList() != null && supportBairro.getComboBox() != null) {
     supportBairro.uninstall();
     }
     supportBairro = AutoCompleteSupport.install(comboBairro, lRegBairros, new BairroTextFilterator());
     supportBairro.setFilterMode(TextMatcherEditor.STARTS_WITH);
     supportBairro.setStrict(false);


     //comboCidade
     final EventList<CidadeModel> lRegCidades = GlazedLists.eventList(lCidades);
     if (supportCidade != null && supportCidade.getItemList() != null && supportCidade.getComboBox() != null) {
     supportCidade.uninstall();
     }
     supportCidade = AutoCompleteSupport.install(comboCidade, lRegCidades, new CidadeTextFilterator());
     supportCidade.setFilterMode(TextMatcherEditor.STARTS_WITH);
     supportCidade.setStrict(false);

     //comboEstado
     final EventList<EstadoModel> lRegEstados = GlazedLists.eventList(lEstados);
     if (supportEstado != null && supportEstado.getItemList() != null && supportEstado.getComboBox() != null) {
     supportEstado.uninstall();
     }
     supportEstado = AutoCompleteSupport.install(comboEstado, lRegEstados, new EstadoTextFilterator());
     supportEstado.setFilterMode(TextMatcherEditor.STARTS_WITH);
     supportEstado.setStrict(false);

     //comboBanco
     final EventList<BancoModel> lRegBancos = GlazedLists.eventList(lBanco);
     if (supportBanco != null && supportBanco.getItemList() != null && supportBanco.getComboBox() != null) {
     supportBanco.uninstall();
     }
     supportBanco = AutoCompleteSupport.install(comboBanco, lRegBancos, new BancoTextFilterator());
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
     */

    public void repopularCombos(Tela tela, Object object) {

        try {

            switch (tela) {

                case TELA_PARAMETROS_SACADOS:

                    SacadoModel sacadoModel = (SacadoModel) object;

                    if (sacadoModel != null && !existsInCombo(comboSacado, sacadoModel, true)) {

                        lRegistros.add(sacadoModel);

                        selecionaCombo(comboSacado, sacadoModel, true);

                    }

                    break;

                case TELA_PARAMETROS_TIPOS_DE_SACADOS:

                    TipoSacadoModel tipoSacadoModel = (TipoSacadoModel) object;

                    if (tipoSacadoModel != null && !existsInCombo(comboTipoSacado, tipoSacadoModel, false)) {

                        comboTipoSacado.addItem(tipoSacadoModel);

                        selecionaCombo(comboTipoSacado, tipoSacadoModel, false);

                    }

                    break;

                case TELA_PARAMETROS_BANCOS:

                    BancoModel bancoModel = (BancoModel) object;

                    if (bancoModel != null && !existsInCombo(comboBanco, bancoModel, true)) {

                        lRegBancos.add(bancoModel);

                        selecionaCombo(comboBanco, bancoModel, true);

                    }

                    break;

                case TELA_PARAMETROS_ESTADOS:

                    EstadoModel estadoModel = (EstadoModel) object;

                    if (estadoModel != null && !existsInCombo(comboEstado, estadoModel, true)) {

                        lRegEstados.add(estadoModel);

                        selecionaCombo(comboEstado, estadoModel, true);

                    }

                    break;

                case TELA_PARAMETROS_CIDADES:

                    CidadeModel cidadeModel = (CidadeModel) object;

                    if (cidadeModel != null && !existsInCombo(comboCidade, cidadeModel, true)) {

                        lRegCidades.add(cidadeModel);

                        selecionaCombo(comboCidade, cidadeModel, true);

                    }

                    break;

                case TELA_PARAMETROS_BAIRROS:

                    BairroModel bairroModel = (BairroModel) object;

                    if (bairroModel != null && !existsInCombo(comboBairro, bairroModel, true)) {

                        lRegBairros.add(bairroModel);

                        selecionaCombo(comboBairro, bairroModel, true);

                    }

                    break;

                default:

                    lColecao = sacadoBO.obterTodos(organizacaoModel);
                    lRegistros = GlazedLists.eventList(lColecao);
                    if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                        support.uninstall();
                    }
                    support = AutoCompleteSupport.install(comboSacado, lRegistros, new SacadoTextFilterator());
                    support.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    support.setStrict(false);

                    lTipoSacados = new ArrayList<TipoSacadoModel>();
                    tipoSacadoModel = new TipoSacadoModel();
                    tipoSacadoModel.setDescricao(" -> Selecione <- ");
                    lTipoSacados.add(tipoSacadoModel);
                    lTipoSacados.addAll(tipoSacadoBO.obterTodos(organizacaoModel));
                    comboTipoSacado.setModel(new javax.swing.DefaultComboBoxModel(lTipoSacados.toArray()));

                    lBanco = bancoBO.obterTodos();
                    lRegBancos = GlazedLists.eventList(lBanco);
                    if (supportBanco != null && supportBanco.getItemList() != null && supportBanco.getComboBox() != null) {
                        supportBanco.uninstall();
                    }
                    supportBanco = AutoCompleteSupport.install(comboBanco, lRegBancos, new BancoTextFilterator());
                    supportBanco.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportBanco.setStrict(false);

                    lEstados = estadoBO.obterTodos();
                    lRegEstados = GlazedLists.eventList(lEstados);
                    if (supportEstado != null && supportEstado.getItemList() != null && supportEstado.getComboBox() != null) {
                        supportEstado.uninstall();
                    }
                    supportEstado = AutoCompleteSupport.install(comboEstado, lRegEstados, new EstadoTextFilterator());
                    supportEstado.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportEstado.setStrict(false);

                    lCidades = cidadeBO.obterTodos();
                    lRegCidades = GlazedLists.eventList(lCidades);
                    if (supportCidade != null && supportCidade.getItemList() != null && supportCidade.getComboBox() != null) {
                        supportCidade.uninstall();
                    }
                    supportCidade = AutoCompleteSupport.install(comboCidade, lRegCidades, new CidadeTextFilterator());
                    supportCidade.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportCidade.setStrict(false);

                    lBairros = bairroBO.obterTodos();
                    lRegBairros = GlazedLists.eventList(lBairros);
                    if (supportBairro != null && supportBairro.getItemList() != null && supportBairro.getComboBox() != null) {
                        supportBairro.uninstall();
                    }
                    supportBairro = AutoCompleteSupport.install(comboBairro, lRegBairros, new BairroTextFilterator());
                    supportBairro.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportBairro.setStrict(false);

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

    public CadastroSacado() throws SystemException {

        initComponents();

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" Selecione",
            "Débito", "Crédito"
        }));

        comboPersonalidade.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" -> Selecione <- ",
            "PF", "PJ"
        }));

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);
        campoCodigoContato.setVisible(false);
        campoCodigoEndereco.setVisible(false);
        jFTCpfCpnj.setVisible(false);
        labelCpfCnpj.setVisible(false);

        campoCodigoContaContabil.setVisible(false);
        campoINSCMF.setVisible(false);
        campoGrau.setVisible(false);
        campoOrdemDipj.setVisible(false);
        campoRelacionamento.setVisible(false);
        campoNatureza.setVisible(false);

        //Aplicando tamanho maximo de caracteres do campo.
        jTLogradouro.setDocument(new Documento(60));
        jTComplemento.setDocument(new Documento(60));
        jTNumero.setDocument(new Documento(5));
        jFTTelFixo.setDocument(new Documento(10));
        jFTCel.setDocument(new Documento(10));
        jFTEMail.setDocument(new Documento(60));
        jFTMsn.setDocument(new Documento(60));
        jFTEMail.setDocument(new Documento(60));
        jFTMsn.setDocument(new Documento(60));
        jTDDDCel.setDocument(new Documento(2));
        jTDDDTelFixo.setDocument(new Documento(2));
        jTContaBancaria.setDocument(new Documento(20));

        jTAgencia.setDocument(new Documento(8));

        jTConta.setDocument(new Documento(14));
        jTDigitoConta.setDocument(new Documento(1));
        jTContaReduzida.setDocument(new Documento(8));
        jTDigitoContaReduzida.setDocument(new Documento(1));

        //Ver como tratar a questão de CPF ou CNPJ de sacado
        jFTCep.setFormatterFactory(MaskUtils.mascaraCep());
        jFTCel.setFormatterFactory(MaskUtils.mascaraTelefone());
        jFTTelFixo.setFormatterFactory(MaskUtils.mascaraTelefone());

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoImprimirFicha = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        labelSacado = new javax.swing.JLabel();
        comboSacado = new javax.swing.JComboBox();
        labelCpfCnpj = new javax.swing.JLabel();
        jFTCpfCpnj = new javax.swing.JFormattedTextField();
        labelTipoSacado = new javax.swing.JLabel();
        comboTipoSacado = new javax.swing.JComboBox();
        campoCodigo = new javax.swing.JTextField();
        comboPersonalidade = new javax.swing.JComboBox();
        labelTipoSacado1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTLogradouro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFTCep = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jTNumero = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        campoCodigoEndereco = new javax.swing.JTextField();
        jTComplemento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboBairro = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        comboCidade = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        campoCodigoContato = new javax.swing.JTextField();
        labelCpfCnpj1 = new javax.swing.JLabel();
        labelCpfCnpj2 = new javax.swing.JLabel();
        jTDDDTelFixo = new javax.swing.JTextField();
        labelCpfCnpj3 = new javax.swing.JLabel();
        labelCpfCnpj4 = new javax.swing.JLabel();
        jFTTelFixo = new javax.swing.JFormattedTextField();
        jFTCel = new javax.swing.JFormattedTextField();
        labelCpfCnpj5 = new javax.swing.JLabel();
        jFTEMail = new javax.swing.JFormattedTextField();
        labelCpfCnpj6 = new javax.swing.JLabel();
        jFTMsn = new javax.swing.JFormattedTextField();
        jTDDDCel = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        labelConta = new javax.swing.JLabel();
        labelNomeBanco = new javax.swing.JLabel();
        comboBanco = new javax.swing.JComboBox();
        labelAgencia = new javax.swing.JLabel();
        jTAgencia = new javax.swing.JTextField();
        jTContaBancaria = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        campoNatureza = new javax.swing.JTextField();
        campoRelacionamento = new javax.swing.JTextField();
        campoOrdemDipj = new javax.swing.JTextField();
        campoGrau = new javax.swing.JTextField();
        campoINSCMF = new javax.swing.JTextField();
        campoCodigoContaContabil = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lupaPesquisaContaContabilDebito = new javax.swing.JButton();
        labelDescContaContabil = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTConta = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jTDigitoConta = new br.com.pempec.componentes.JIntegerField();
        jLabel11 = new javax.swing.JLabel();
        jTContaReduzida = new br.com.pempec.componentes.JIntegerField();
        jLabel12 = new javax.swing.JLabel();
        jTDigitoContaReduzida = new br.com.pempec.componentes.JIntegerField();
        jLabel13 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Básico do Sacado");

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

        botaoImprimirFicha.setMnemonic('I');
        botaoImprimirFicha.setText("Imprimir");
        botaoImprimirFicha.setEnabled(false);
        botaoImprimirFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirFichaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoImprimirFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoImprimirFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelSacado.setText("Sacado");

        comboSacado.setFont(getFont());
        comboSacado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSacadoActionPerformed(evt);
            }
        });

        labelCpfCnpj.setText("CPF");

        labelTipoSacado.setFont(new java.awt.Font("Arial", 0, 12));
        labelTipoSacado.setText("Tipo Sacado");

        comboTipoSacado.setFont(getFont());

        campoCodigo.setEditable(false);

        comboPersonalidade.setFont(getFont());
        comboPersonalidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPersonalidadeItemStateChanged(evt);
            }
        });

        labelTipoSacado1.setFont(new java.awt.Font("Arial", 0, 12));
        labelTipoSacado1.setText("Personalidade");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSacado)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(comboSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboPersonalidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTipoSacado1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTipoSacado)
                            .addComponent(comboTipoSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelCpfCnpj)
                    .addComponent(jFTCpfCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSacado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelTipoSacado1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPersonalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelTipoSacado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTipoSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCpfCnpj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTCpfCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );

        jTabbedPane1.addTab("Sacado", jPanel5);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setPreferredSize(new java.awt.Dimension(512, 308));

        jLabel1.setText("Logradouro");

        jLabel3.setText("Cep");

        jLabel4.setText("Número");

        jLabel6.setText("Estado");

        comboEstado.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel7.setText("Complemento");

        campoCodigoEndereco.setEditable(false);

        jLabel2.setText("Bairro");

        comboBairro.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel5.setText("Cidade");

        comboCidade.setFont(new java.awt.Font("Arial", 0, 10));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTComplemento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jTLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoCodigoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(5, 5, 5)))
                        .addGap(17, 17, 17)))
                .addGap(189, 189, 189))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        jTabbedPane1.addTab("Endereço", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setPreferredSize(new java.awt.Dimension(512, 308));

        campoCodigoContato.setEditable(false);

        labelCpfCnpj1.setText("Telefone Fixo");

        labelCpfCnpj2.setText("DDD");

        jTDDDTelFixo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelCpfCnpj3.setText("DDD");

        labelCpfCnpj4.setText("Telefone Celular");

        labelCpfCnpj5.setText("E-mail");

        labelCpfCnpj6.setText("Messenger");

        jTDDDCel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCpfCnpj3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTDDDCel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpfCnpj2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTDDDTelFixo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jFTCel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(campoCodigoContato, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelCpfCnpj4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCpfCnpj1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFTTelFixo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpfCnpj6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFTMsn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpfCnpj5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFTEMail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(326, 326, 326))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelCpfCnpj2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDDDTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(labelCpfCnpj3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDDDCel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelCpfCnpj1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(labelCpfCnpj4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTCel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(labelCpfCnpj5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTEMail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCpfCnpj6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTMsn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Contato", jPanel4);

        jPanel1.setPreferredSize(new java.awt.Dimension(512, 308));

        labelConta.setText("Conta");

        labelNomeBanco.setText("Banco");

        comboBanco.setFont(getFont());

        labelAgencia.setText("Agência");

        jTAgencia.setFont(new java.awt.Font("Arial", 0, 12));

        jTContaBancaria.setFont(new java.awt.Font("Arial", 0, 12));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAgencia)
                    .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelConta)
                            .addComponent(jTContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNomeBanco)
                            .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNomeBanco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(labelAgencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Banco", jPanel1);

        jPanel6.setPreferredSize(new java.awt.Dimension(512, 308));

        campoNatureza.setEditable(false);

        campoRelacionamento.setEditable(false);

        campoOrdemDipj.setEditable(false);

        campoGrau.setEditable(false);

        campoINSCMF.setEditable(false);

        campoCodigoContaContabil.setEditable(false);

        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Pesquisar");

        lupaPesquisaContaContabilDebito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.jpg"))); // NOI18N
        lupaPesquisaContaContabilDebito.setBorderPainted(false);
        lupaPesquisaContaContabilDebito.setContentAreaFilled(false);
        lupaPesquisaContaContabilDebito.setDefaultCapable(false);
        lupaPesquisaContaContabilDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lupaPesquisaContaContabilDebitoActionPerformed(evt);
            }
        });

        labelDescContaContabil.setForeground(new java.awt.Color(0, 153, 153));
        labelDescContaContabil.setText("<<< --- Pesquise aqui!");
        labelDescContaContabil.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));

        jLabel9.setText("Conta");

        jLabel10.setText("Dg");

        jLabel11.setText("CT.  Reduzida");

        jLabel12.setText("Dg");

        jLabel13.setText("Tipo");

        comboTipo.setFont(new java.awt.Font("Arial", 0, 10));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(campoCodigoContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoINSCMF, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoGrau, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoOrdemDipj, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRelacionamento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNatureza, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(9, 9, 9))
                                    .addComponent(jTDigitoConta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTDigitoContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lupaPesquisaContaContabilDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(labelDescContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(labelDescContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lupaPesquisaContaContabilDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDigitoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(9, 9, 9)
                        .addComponent(jTDigitoContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCodigoContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoINSCMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoGrau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoOrdemDipj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoRelacionamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNatureza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Conta Contábil", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        this.botaoLimparActionPerformed(evt);
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        botaoExcluir.setEnabled(false);
        botaoAlterar.setEnabled(false);
        botaoImprimirFicha.setEnabled(false);
        botaoIncluir.setEnabled(true);

        limparCampos(jPanel5, jPanel3, jPanel4, jPanel1, jPanel6);

//
//        botaoAlterar.setEnabled(false);
//        botaoImprimirFicha.setEnabled(false);
//        botaoIncluir.setEnabled(true);
//        comboTipoSacado.setSelectedIndex(0);
//        jTContaBancaria.setText("");
//        jTAgencia.setText("");
//        campoCodigo.setText("");
//        //Somente setar null nos combos que possui o auto-complete
//        comboSacado.setSelectedItem(null);
//        comboEstado.setSelectedItem(null);
//        comboCidade.setSelectedItem(null);
//        comboBairro.setSelectedItem(null);
//        comboBanco.setSelectedItem(null);
//
//        //Limpando ABA de conta Contabil
//        limparAbaContaContabil();
//
//        comboPersonalidade.setSelectedIndex(0);
//
//        //Campo com mascara setar null no value.
//        jFTCpfCpnj.setValue(null);
//        campoCodigoContato.setText("");
//        jTDDDCel.setText("");
//        jTDDDTelFixo.setText("");
//        jFTCel.setValue(null);
//        jFTTelFixo.setValue(null);
//        jFTEMail.setText("");
//        jFTMsn.setText("");
//
//        campoCodigoEndereco.setText("");
//        jTLogradouro.setText("");
//
//        jTNumero.setText("");
//
//        jTComplemento.setText("");
//        jFTCep.setValue(null);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        if (comboSacado.getSelectedItem() != null) {
            valorCombo = comboSacado.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {
                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                if (campoCodigo.getText().equals(Controller.getOrganizacao().getId())) {
                    exibeMensagemAviso("Não é possível excluir este registro! \n Este sacado é padrão do sistema.", null);
                    return;
                }

                SacadoModel tab = new SacadoModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab.setNome(valorCombo);

                tab = sacadoBO.consultarPorTemplate(tab);

                tab.setMovimentoDiarioModel(registroMovimento("Deletar Sacado", tab.getPk().getId(), tab.getNome() + " - CPF/CNPJ : " + tab.getCpfCnpj(), null, "Excluído"));

                sacadoBO.excluir(tab);

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

    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        if (comboSacado.getSelectedItem() != null) {
            valorCombo = comboSacado.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                Component validateForm = validaCampos();

                if (validateForm == null) {

                    SacadoModel tab = new SacadoModel();
                    tab.setPk(new PKModel());
                    tab.getPk().setOrganizacao(organizacaoModel);
                    tab.getPk().setId(PempecKeyGenerator.generateKey());

                    //Tratamento de Nulidade.
                    //Tratamento tbm da opção Selecione
                    if (this.validaPreenchimentoCombo(comboTipoSacado, false)) {

                        TipoSacadoModel tps = new TipoSacadoModel();
                        tps.setPk(new PKModel());
                        tps.setDescricao(comboTipoSacado.getSelectedItem().toString());
                        tps.getPk().setOrganizacao(organizacaoModel);

                        tps = tipoSacadoBO.consultarPorTemplate(tps);

                        if (tps.getPk() != null && tps.getPk().getId() != null) {

                            tab.setTipoSacado(tps);
                        }

                    }

                    tab.setNome(valorCombo);

                    tab.setPersonalidade(((String) comboPersonalidade.getSelectedItem()).toString());

                    tab.setCpfCnpj(jFTCpfCpnj.getText());

                    if (jTConta.getText() != null && !jTConta.getText().isEmpty()) {

                        ContaContabilModel contaContabil = new ContaContabilModel();

                        contaContabil.setPk(new PKModel());

                        contaContabil.getPk().setOrganizacao(organizacaoModel);

                        contaContabil.getPk().setId(PempecKeyGenerator.generateKey());

                        contaContabil.setConta(jTConta.getText());

                        contaContabil = contaContabilBO.consultarPorTemplate(contaContabil);

                        if (contaContabil != null && contaContabil.getPk() != null && !contaContabil.getPk().getId().isEmpty()) {

                            tab.setContaContabil(contaContabil);

                        } else {

                            contaContabil = new ContaContabilModel();

                            contaContabil.setPk(new PKModel());

                            contaContabil.getPk().setOrganizacao(organizacaoModel);

                            contaContabil.getPk().setId(PempecKeyGenerator.generateKey());

                            contaContabil.setDescricao(tab.getNome());

                            contaContabil.setConta(jTConta.getText());

                            contaContabil.setDigitoConta(jTDigitoConta.getText());

                            contaContabil.setContaReduzida(jTContaReduzida.getText());

                            contaContabil.setDigitoContaReduzida(jTDigitoContaReduzida.getText());

                            contaContabil.setGrau("5");

                            if (comboTipo.getSelectedIndex() == 1) {

                                contaContabil.setTipo("D");

                            } else {

                                contaContabil.setTipo("C");
                            }

                            tab.setContaContabil(contaContabil);

                        }

                    }

                    if (!jTLogradouro.getText().isEmpty()) {

                        tab.setEndereco(new EnderecoModel());
                        tab.getEndereco().setPk(new PKModel());
                        tab.getEndereco().getPk().setId(PempecKeyGenerator.generateKey());
                        tab.getEndereco().setLogradouro(jTLogradouro.getText());
                        tab.getEndereco().setNumero(jTNumero.getText());
                        tab.getEndereco().setCep(jFTCep.getText());
                        tab.getEndereco().setComplemento(jTComplemento.getText());

                        if (this.validaPreenchimentoCombo(comboEstado, true)) {

                            tab.getEndereco().setEstado(new EstadoModel());
                            tab.getEndereco().getEstado().setId(((EstadoModel) comboEstado.getSelectedItem()).getId());

                        }

                        if (this.validaPreenchimentoCombo(comboBairro, true)) {

                            tab.getEndereco().setBairro(new BairroModel());
                            tab.getEndereco().getBairro().setId(((BairroModel) comboBairro.getSelectedItem()).getId());
                        }

                        if (this.validaPreenchimentoCombo(comboCidade, true)) {

                            tab.getEndereco().setCidade(new CidadeModel());
                            tab.getEndereco().getCidade().setId(((CidadeModel) comboCidade.getSelectedItem()).getId());
                        }
                    }

                    //Tratando que o contato não é obrigatório
                    //Na validação deve contemplar, caso o usuario digite o ddd, obrigatoriamente deva digitar o numero.
                    if (!jTDDDTelFixo.getText().isEmpty()) {

                        tab.setContato(new ContatoModel());
                        tab.getContato().setPk(new PKModel());
                        tab.getContato().getPk().setId(PempecKeyGenerator.generateKey());
                        tab.getContato().setTelefone(jTDDDTelFixo.getText() + jFTTelFixo.getText());
                        tab.getContato().setEmail(jFTEMail.getText());
                        tab.getContato().setMsn(jFTMsn.getText());
                        tab.getContato().setCelular(jTDDDCel.getText() + jFTCel.getText());

                    }

                    if (this.validaPreenchimentoCombo(comboBanco, true)) {
                        tab.setBanco(new BancoModel());
                        tab.getBanco().setId(((BancoModel) comboBanco.getSelectedItem()).getId());
                    }

                    tab.setAgencia(jTAgencia.getText());
                    tab.setConta(jTContaBancaria.getText());

                    tab.setMovimentoDiarioModel(registroMovimento("Inserir Sacado", tab.getPk().getId(), tab.getNome() + " - CPF/CNPJ : " + tab.getCpfCnpj(), new Double(0), "Inserido"));

                    sacadoBO.inserir(tab);

                    this.botaoLimparActionPerformed(evt);

                } else {

                    exibeMensagemAviso("Campo(s) obrigatório(s).", null);
                    jTabbedPane1.setSelectedComponent(validateForm.getParent());
                    validateForm.requestFocus();

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
    }//GEN-LAST:event_botaoIncluirActionPerformed

private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

    if (comboSacado.getSelectedItem() != null) {
        valorCombo = comboSacado.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {
            long action = Action.ALTERAR.getAction();
            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component validateForm = validaCampos();

            if (validateForm == null) {

                SacadoModel tab = new SacadoModel();
                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(organizacaoModel);
                tab.getPk().setId(campoCodigo.getText());

                //Tratamento de Nulidade.
                //Tratamento tbm da opção Selecione
                if (this.validaPreenchimentoCombo(comboTipoSacado, false)) {

                    tab.setTipoSacado(new TipoSacadoModel());
                    tab.getTipoSacado().setPk(new PKModel());
                    tab.getTipoSacado().getPk().setId(((TipoSacadoModel) comboTipoSacado.getSelectedItem()).getPk().getId());

                }

                tab.setNome(valorCombo);
                tab.setPersonalidade(((String) comboPersonalidade.getSelectedItem()).toString());
                tab.setCpfCnpj(jFTCpfCpnj.getText());

                if (jTConta.getText() != null && !jTConta.getText().isEmpty()) {

                    ContaContabilModel contaContabil = new ContaContabilModel();

                    contaContabil.setPk(new PKModel());

                    contaContabil.getPk().setOrganizacao(organizacaoModel);

                    contaContabil.setConta(jTConta.getText());

                    contaContabil = contaContabilBO.consultarPorTemplate(contaContabil);

                    if (contaContabil.getTipo().equalsIgnoreCase("D")) {

                        comboTipo.setSelectedIndex(1);

                    } else {

                        comboTipo.setSelectedIndex(2);
                    }

                    if (contaContabil != null && contaContabil.getPk() != null) {

                        if (comboTipo.getSelectedIndex() == 1) {

                            contaContabil.setTipo("D");

                        } else {

                            contaContabil.setTipo("C");
                        }

                        tab.setContaContabil(contaContabil);
                    } else {

                        contaContabil = new ContaContabilModel();

                        contaContabil.setPk(new PKModel());
                        contaContabil.getPk().setId(campoCodigoContaContabil.getText());
                        contaContabil.getPk().setOrganizacao(organizacaoModel);
                        contaContabil.setDescricao(tab.getNome());
                        contaContabil.setConta(jTConta.getText());
                        contaContabil.setDigitoConta(jTDigitoConta.getText());
                        contaContabil.setContaReduzida(jTContaReduzida.getText());
                        contaContabil.setDigitoContaReduzida(jTDigitoContaReduzida.getText());

                        if (comboTipo.getSelectedIndex() == 1) {

                            contaContabil.setTipo("D");

                        } else {

                            contaContabil.setTipo("C");
                        }

                        tab.setContaContabil(contaContabil);

                    }

                }

                if (!jTLogradouro.getText().isEmpty()) {

                    tab.setEndereco(new EnderecoModel());
                    tab.getEndereco().setPk(new PKModel());
                    tab.getEndereco().getPk().setId(campoCodigoEndereco.getText());
                    tab.getEndereco().setLogradouro(jTLogradouro.getText());
                    tab.getEndereco().setNumero(jTNumero.getText());

                    tab.getEndereco().setCep(jFTCep.getText());

                    tab.getEndereco().setComplemento(jTComplemento.getText());

                    if (this.validaPreenchimentoCombo(comboEstado, true)) {

                        tab.getEndereco().setEstado(new EstadoModel());
                        tab.getEndereco().getEstado().setId(((EstadoModel) comboEstado.getSelectedItem()).getId());

                    }

                    if (this.validaPreenchimentoCombo(comboBairro, true)) {

                        tab.getEndereco().setBairro(new BairroModel());
                        tab.getEndereco().getBairro().setId(((BairroModel) comboBairro.getSelectedItem()).getId());
                    }

                    if (this.validaPreenchimentoCombo(comboCidade, true)) {

                        tab.getEndereco().setCidade(new CidadeModel());
                        tab.getEndereco().getCidade().setId(((CidadeModel) comboCidade.getSelectedItem()).getId());
                    }

                }

                //Tratando o contato que nao seja obrigatório
                //Na validação deve contemplar, caso o usuario digite o ddd, obrigatoriamente deva digitar o numero.
                if (!jTDDDTelFixo.getText().isEmpty()) {

                    tab.setContato(new ContatoModel());
                    tab.getContato().setPk(new PKModel());
                    tab.getContato().getPk().setId(campoCodigoContato.getText());
                    tab.getContato().setTelefone(jTDDDTelFixo.getText() + jFTTelFixo.getText());

                    tab.getContato().setEmail(jFTEMail.getText());
                    tab.getContato().setMsn(jFTMsn.getText());
                    tab.getContato().setCelular(jTDDDCel.getText() + jFTCel.getText());

                }

                if (this.validaPreenchimentoCombo(comboBanco, true)) {
                    tab.setBanco(new BancoModel());
                    tab.getBanco().setId(((BancoModel) comboBanco.getSelectedItem()).getId());
                }

                tab.setAgencia(jTAgencia.getText());
                tab.setConta(jTContaBancaria.getText());

                tab.setMovimentoDiarioModel(registroMovimento("Alterar Sacado", tab.getPk().getId(), tab.getNome() + " - CPF/CNPJ : " + tab.getCpfCnpj(), new Double(0), "Alterado"));
                sacadoBO.alterar(tab);

                this.botaoLimparActionPerformed(evt);

            } else {

                exibeMensagemAviso("Campo(s) obrigatório(s).", null);
                jTabbedPane1.setSelectedComponent(validateForm.getParent());
                validateForm.requestFocus();

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

}//GEN-LAST:event_botaoAlterarActionPerformed

private void comboSacadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSacadoActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboSacado.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        valorCombo = comboSacado.getSelectedItem().toString().toUpperCase();

        try {

            SacadoModel tab = new SacadoModel();

            tab.setNome(valorCombo);

            tab.setPk(new PKModel());

            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = sacadoBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                botaoAlterar.setEnabled(true);
                botaoExcluir.setEnabled(true);
                botaoIncluir.setEnabled(false);
                botaoImprimirFicha.setEnabled(true);

                campoCodigo.setText(tab.getPk().getId());

                if (tab.getContaContabil() != null) {

                    ContaContabilModel contaContabil = tab.getContaContabil();

                    if (tab.getContaContabil().getTipo().equals("D")) {
                        comboTipo.setSelectedIndex(1);
                    } else {
                        if (tab.getContaContabil().getTipo().equals("C")) {
                            comboTipo.setSelectedIndex(2);
                        } else {
                            comboTipo.setSelectedIndex(0);
                        }
                    }

                    tab.setContaContabil(contaContabil);

                    campoCodigoContaContabil.setText(tab.getContaContabil().getPk().getId());
                    jTConta.setText(tab.getContaContabil().getConta());

                    if (tab.getContaContabil().getNatureza() != null) {
                        campoNatureza.setText(tab.getContaContabil().getNatureza().toString());
                    }
                    if (tab.getContaContabil().getOrdemDIPJ() != null) {
                        campoOrdemDipj.setText(tab.getContaContabil().getOrdemDIPJ().toString());
                    }
                    if (tab.getContaContabil().getRelacionamento() != null) {
                        campoRelacionamento.setText(tab.getContaContabil().getRelacionamento().toString());
                    }

                    jTDigitoConta.setValue(PempecParse.stringToInteger(tab.getContaContabil().getDigitoConta()));

                    jTContaReduzida.setValue(PempecParse.stringToInteger(tab.getContaContabil().getContaReduzida()));

                    jTDigitoContaReduzida.setValue(PempecParse.stringToInteger(tab.getContaContabil().getDigitoContaReduzida()));

                    campoINSCMF.setText(tab.getContaContabil().getInscricaoCMF());
                    campoGrau.setText(tab.getContaContabil().getGrau());
                    labelDescContaContabil.setText(tab.getContaContabil().getDescricao());

                }

                if (tab.getTipoSacado() != null) {

                    for (int i = 1; i < comboTipoSacado.getItemCount(); i++) {
                        if (tab.getTipoSacado().getPk().getId().equalsIgnoreCase(((TipoSacadoModel) comboTipoSacado.getItemAt(i)).getPk().getId())) {
                            comboTipoSacado.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getPersonalidade() != null) {

                    for (int i = 1; i < comboPersonalidade.getItemCount(); i++) {
                        if (tab.getPersonalidade().equalsIgnoreCase(((String) comboPersonalidade.getItemAt(i)).toString())) {
                            comboPersonalidade.setSelectedIndex(i);
                            break;
                        }
                    }
                }

                jFTCpfCpnj.setText(tab.getCpfCnpj());

                if (tab.getEndereco() != null && tab.getEndereco().getPk() != null && !tab.getEndereco().getPk().getId().isEmpty()) {

                    campoCodigoEndereco.setText(tab.getEndereco().getPk().getId());
                    jTLogradouro.setText(tab.getEndereco().getLogradouro());
                    jTNumero.setText(tab.getEndereco().getNumero());
                    jFTCep.setText(tab.getEndereco().getCep());
                    jTComplemento.setText(tab.getEndereco().getComplemento());

                    if (tab.getEndereco().getEstado() != null) {

                        for (int i = 0; i < comboEstado.getItemCount(); i++) {
                            if (tab.getEndereco().getEstado().getId().equalsIgnoreCase(((EstadoModel) comboEstado.getItemAt(i)).getId())) {
                                comboEstado.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    if (tab.getEndereco().getCidade() != null) {

                        for (int i = 0; i < comboCidade.getItemCount(); i++) {
                            if (tab.getEndereco().getCidade().getId().equalsIgnoreCase(((CidadeModel) comboCidade.getItemAt(i)).getId())) {
                                comboCidade.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    if (tab.getEndereco().getBairro() != null) {

                        for (int i = 0; i < comboBairro.getItemCount(); i++) {
                            if (tab.getEndereco().getBairro().getId().equalsIgnoreCase(((BairroModel) comboBairro.getItemAt(i)).getId())) {
                                comboBairro.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                }

                //Tratando que o contato não é obrigatório
                //Na validação deve contemplar, caso o usuario digite o ddd, obrigatoriamente deva digitar o numero.
                if (tab.getContato() != null && tab.getContato().getPk() != null && !tab.getContato().getPk().getId().isEmpty()) {

                    campoCodigoContato.setText(tab.getContato().getPk().getId());

                    if (!tab.getContato().getTelefone().isEmpty() && tab.getContato().getTelefone().length() >= 2) {
                        jTDDDTelFixo.setText(tab.getContato().getTelefone().substring(0, 2));
                    }

                    if (!tab.getContato().getTelefone().isEmpty() && tab.getContato().getTelefone().length() >= 2) {
                        jFTTelFixo.setText(tab.getContato().getTelefone().substring(2));
                    }

                    jFTEMail.setText(tab.getContato().getEmail());
                    jFTMsn.setText(tab.getContato().getMsn());

                    if (!tab.getContato().getCelular().isEmpty() && tab.getContato().getCelular().length() >= 2) {
                        jTDDDCel.setText(tab.getContato().getCelular().substring(0, 2));
                    }

                    if (!tab.getContato().getCelular().isEmpty() && tab.getContato().getCelular().length() >= 2) {
                        jFTCel.setText(tab.getContato().getCelular().substring(2));
                    }

                }

                if (tab.getBanco() != null && tab.getBanco().getId() != null && !tab.getBanco().getId().isEmpty()) {

                    for (int i = 0; i < comboBanco.getItemCount(); i++) {
                        if (tab.getBanco().getId().equalsIgnoreCase(((BancoModel) comboBanco.getItemAt(i)).getId())) {
                            comboBanco.setSelectedIndex(i);
                            break;
                        }
                    }

                    jTAgencia.setText(tab.getAgencia());
                    jTContaBancaria.setText(tab.getConta());
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

}//GEN-LAST:event_comboSacadoActionPerformed

private void comboPersonalidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPersonalidadeItemStateChanged

    if (((String) comboPersonalidade.getSelectedItem()).toString().equalsIgnoreCase("PF")) {
        jFTCpfCpnj.setVisible(true);
        labelCpfCnpj.setVisible(true);
        labelCpfCnpj.setText("CPF");
        jFTCpfCpnj.setFormatterFactory(MaskUtils.mascaraCpf());
        jFTCpfCpnj.setValue(null);
    } else {
        if (((String) comboPersonalidade.getSelectedItem()).toString().equalsIgnoreCase("PJ")) {
            jFTCpfCpnj.setVisible(true);
            labelCpfCnpj.setVisible(true);
            labelCpfCnpj.setText("CNPJ");
            jFTCpfCpnj.setValue(null);
            jFTCpfCpnj.setFormatterFactory(MaskUtils.mascaraCnpj());
        } else {
            jFTCpfCpnj.setVisible(false);
            labelCpfCnpj.setVisible(false);
            jFTCpfCpnj.setFormatterFactory(null);
            jFTCpfCpnj.setValue(null);
        }
    }


}//GEN-LAST:event_comboPersonalidadeItemStateChanged

private void botaoImprimirFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirFichaActionPerformed

    try {
        SacadoModel tab = new SacadoModel();
        tab.setPk(new PKModel());
        tab.getPk().setOrganizacao(organizacaoModel);
        tab.getPk().setId(campoCodigo.getText());

        tab = sacadoBO.consultarPorPk(tab);

        if (tab != null && tab.getPk() != null && !tab.getPk().getId().isEmpty()) {

            FichaCadastralSacado fichaCadastralSacado = new FichaCadastralSacado(tab);
            fichaCadastralSacado.setTitle("Ficha Sacado");
            fichaCadastralSacado.repopularCombos();
            TelaPrincipal.desktopPane.add(fichaCadastralSacado);
            fichaCadastralSacado.show();

        } else {

            exibeMensagemAviso("Problemas ao imprimir a ficha. ", "Ficha Sac");

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
}//GEN-LAST:event_botaoImprimirFichaActionPerformed

private void lupaPesquisaContaContabilDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lupaPesquisaContaContabilDebitoActionPerformed

    if (comboSacado.getSelectedItem() != null) {

        valorCombo = comboSacado.getSelectedItem().toString().toUpperCase();

    }

    if (valorCombo != null) {

        try {

            if (pesquisaContaContabilDebito == null || pesquisaContaContabilDebito.isClosed()) {
                pesquisaContaContabilDebito = new PesquisaContaContabil(this, "D", this.getLocation());
                TelaPrincipal.desktopPane.add(pesquisaContaContabilDebito);
                pesquisaContaContabilDebito.show();
            }

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
}//GEN-LAST:event_lupaPesquisaContaContabilDebitoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimirFicha;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoCodigoContaContabil;
    private javax.swing.JTextField campoCodigoContato;
    private javax.swing.JTextField campoCodigoEndereco;
    private javax.swing.JTextField campoGrau;
    private javax.swing.JTextField campoINSCMF;
    private javax.swing.JTextField campoNatureza;
    private javax.swing.JTextField campoOrdemDipj;
    private javax.swing.JTextField campoRelacionamento;
    private javax.swing.JComboBox comboBairro;
    private javax.swing.JComboBox comboBanco;
    private javax.swing.JComboBox comboCidade;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JComboBox comboPersonalidade;
    private javax.swing.JComboBox comboSacado;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JComboBox comboTipoSacado;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFormattedTextField jFTCel;
    private javax.swing.JFormattedTextField jFTCep;
    private javax.swing.JFormattedTextField jFTCpfCpnj;
    private javax.swing.JFormattedTextField jFTEMail;
    private javax.swing.JFormattedTextField jFTMsn;
    private javax.swing.JFormattedTextField jFTTelFixo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTAgencia;
    private javax.swing.JTextField jTComplemento;
    private javax.swing.JFormattedTextField jTConta;
    private javax.swing.JTextField jTContaBancaria;
    private br.com.pempec.componentes.JIntegerField jTContaReduzida;
    private javax.swing.JTextField jTDDDCel;
    private javax.swing.JTextField jTDDDTelFixo;
    private br.com.pempec.componentes.JIntegerField jTDigitoConta;
    private br.com.pempec.componentes.JIntegerField jTDigitoContaReduzida;
    private javax.swing.JTextField jTLogradouro;
    private javax.swing.JTextField jTNumero;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAgencia;
    private javax.swing.JLabel labelConta;
    private javax.swing.JLabel labelCpfCnpj;
    private javax.swing.JLabel labelCpfCnpj1;
    private javax.swing.JLabel labelCpfCnpj2;
    private javax.swing.JLabel labelCpfCnpj3;
    private javax.swing.JLabel labelCpfCnpj4;
    private javax.swing.JLabel labelCpfCnpj5;
    private javax.swing.JLabel labelCpfCnpj6;
    private javax.swing.JLabel labelDescContaContabil;
    private javax.swing.JLabel labelNomeBanco;
    private javax.swing.JLabel labelSacado;
    private javax.swing.JLabel labelTipoSacado;
    private javax.swing.JLabel labelTipoSacado1;
    private javax.swing.JButton lupaPesquisaContaContabilDebito;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private AutoCompleteSupport supportEstado;
    private AutoCompleteSupport supportCidade;
    private AutoCompleteSupport supportBairro;
    private AutoCompleteSupport supportBanco;
    private PesquisaContaContabil pesquisaContaContabilDebito;
    private ContaContabilModel contaContabilDebito;

    private Component validaCampos() throws ApplicationException, SystemException {

        if (!validaPreenchimentoCombo(comboTipoSacado, false)) {
            comboTipoSacado.requestFocus();
            return comboTipoSacado;
        }

        if (!jTConta.getText().isEmpty()) {

            if (jTConta.getText().isEmpty()) {
                jTConta.requestFocus();
                return jTConta;
            }

            if (jTDigitoConta.getText().isEmpty()) {
                jTDigitoConta.requestFocus();
                return jTDigitoConta;
            }

            if (jTContaReduzida.getText().isEmpty()) {
                jTContaReduzida.requestFocus();
                return jTContaReduzida;
            }

            if (jTDigitoContaReduzida.getText().isEmpty()) {
                jTDigitoContaReduzida.requestFocus();
                return jTDigitoContaReduzida;
            }

            if (comboTipo.getSelectedItem() == null) {
                comboTipo.requestFocus();
            }

        }

        if (jFTCpfCpnj.getText().isEmpty()
                || jFTCpfCpnj.getText().equals("   .   .   -  ") || jFTCpfCpnj.getText().equals("  .   .   /    -  ")) {
            jFTCpfCpnj.requestFocus();
            return jFTCpfCpnj;
        }

        if (!jTLogradouro.getText().isEmpty()) {

            if (!validaPreenchimentoCombo(comboEstado, true)) {
                comboEstado.requestFocus();
                return comboEstado;
            }

            if (!validaPreenchimentoCombo(comboCidade, true)) {
                comboCidade.requestFocus();
                return comboCidade;
            }

            if (!validaPreenchimentoCombo(comboBairro, true)) {
                comboBairro.requestFocus();
                return comboBairro;
            }

        }

//        //Validando existencia de conta e conta reduzida contabil
        if (jTConta.getText() != null && !jTConta.getText().isEmpty()) {

            ContaContabilModel contaContabil = new ContaContabilModel();

            contaContabil.setPk(new PKModel());

            contaContabil.getPk().setOrganizacao(organizacaoModel);

            contaContabil.setConta(jTConta.getText());

            contaContabil = contaContabilBO.consultarPorTemplate(contaContabil);

            if (contaContabil != null && contaContabil.getPk() != null && (campoCodigo.getText().isEmpty() || !campoCodigoContaContabil.getText().equals(contaContabil.getPk().getId()))) {

                SacadoModel sacadoAux = sacadoBO.consultarPorContaContabil(contaContabil);

                if (sacadoAux != null && sacadoAux.getPk() != null) {

                    exibeMensagemAviso("A conta: " + jTConta.getText() + " já existe!\nEstá associada ao " + sacadoAux.getTipoSacado().getDescricao() + " : " + sacadoAux.getNome(), "Validação Conta Contábil");
                    limparAbaContaContabil();
                    return jTConta;
                }

            }

        }

        return null;
    }

    public void limparAbaContaContabil() {

        campoCodigoContaContabil.setText("");
        jTConta.setText("");
        jTDigitoConta.setText("0");
        jTContaReduzida.setText("0");
        jTDigitoContaReduzida.setText("0");
        comboTipo.setSelectedIndex(0);
        campoINSCMF.setText("");
        campoGrau.setText("5");
        campoOrdemDipj.setText("");
        campoRelacionamento.setText("");
        campoNatureza.setText("");
        labelDescContaContabil.setText("<<< --- Pesquise aqui!");

    }

    public void setPesquisaContaContabil(ContaContabilModel contaContabil, String tipo) {
        if (tipo.equals("D")) {
            this.contaContabilDebito = contaContabil;

            if (contaContabilDebito != null && contaContabilDebito.getPk() != null) {

                labelDescContaContabil.setText(contaContabilDebito.getDescricao());

                jTConta.setText(contaContabilDebito.getConta());
                jTDigitoConta.setText(contaContabilDebito.getDigitoConta());

                jTContaReduzida.setText(contaContabilDebito.getContaReduzida());
                jTDigitoContaReduzida.setText(contaContabilDebito.getDigitoContaReduzida());

                if (contaContabilDebito.getTipo().equals("D")) {
                    comboTipo.setSelectedIndex(1);
                } else {
                    if (contaContabilDebito.getTipo().equals("C")) {
                        comboTipo.setSelectedIndex(2);
                    } else {
                        comboTipo.setSelectedIndex(0);
                    }
                }

            }
        }

    }
}
