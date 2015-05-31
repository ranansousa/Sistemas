package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BairroBO;
import br.com.pempec.finance.businessObjects.BancoBO;
import br.com.pempec.finance.businessObjects.CartaoCreditoBO;
import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.CidadeBO;
import br.com.pempec.finance.businessObjects.ContaContabilBO;
import br.com.pempec.finance.businessObjects.EstadoBO;
import br.com.pempec.finance.businessObjects.TipoCedenteBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BairroModel;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.CartaoCreditoModel;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.CidadeModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.EstadoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoCedenteModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.iterators.CedenteTextFilterator;
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
import br.com.pempec.finance.utils.iterators.CartaoCreditoTextFilterator;
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
 * @author PEMPEC
 */
public class CadastroCedente extends FinanceInternalFrame implements IPopUpContaContabil, IRepopuladorNew {

    private CedenteBO cedenteBO = new CedenteBO();
    private ContaContabilBO contaContabilBO = new ContaContabilBO();
    private TipoCedenteBO tipoCedenteBO = new TipoCedenteBO();
    private EstadoBO estadoBO = new EstadoBO();
    private CidadeBO cidadeBO = new CidadeBO();
    private BairroBO bairroBO = new BairroBO();
    private BancoBO bancoBO = new BancoBO();
    private CartaoCreditoBO cartaoCreditoBO = new CartaoCreditoBO();
    private Collection<CedenteModel> lColecao;
    private EventList<CedenteModel> lRegistros;
    private Collection<CartaoCreditoModel> lCartoes;
    private EventList<CartaoCreditoModel> lRegCartaoCreditos;
    private Collection<TipoCedenteModel> lTipoCedentes;
    private Collection<BancoModel> lBanco;
    private EventList<BancoModel> lRegBancos;
    private Collection<EstadoModel> lEstados;
    private EventList<EstadoModel> lRegEstados;
    private Collection<CidadeModel> lCidades;
    private EventList<CidadeModel> lRegCidades;
    private Collection<BairroModel> lBairros;
    private EventList<BairroModel> lRegBairros;
    private long tela = Tela.TELA_PARAMETROS_CEDENTES.getTela();

    private String NameObject() {
        return (String) "Cedentes";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos(Tela tela, Object object) {

        try {

            switch (tela) {

                case TELA_PARAMETROS_CEDENTES:

                    CedenteModel cedenteModel = (CedenteModel) object;

                    if (cedenteModel != null) {

                        lRegistros.add(cedenteModel);

                        selecionaCombo(comboCedente, cedenteModel, true);

                    }

                    break;

                case TELA_PARAMETROS_TIPOS_DE_CEDENTES:

                    TipoCedenteModel tipoCedenteModel = (TipoCedenteModel) object;

                    if (tipoCedenteModel != null && !existsInCombo(comboTipoCedente, tipoCedenteModel, false)) {

                        comboTipoCedente.addItem(tipoCedenteModel);

                        selecionaCombo(comboTipoCedente, tipoCedenteModel, false);

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

                case TELA_PARAMETROS_CARTAO_CREDITO:

                    CartaoCreditoModel cartaoCreditoModel = (CartaoCreditoModel) object;

                    if (cartaoCreditoModel != null && !existsInCombo(comboCartaoCredito, cartaoCreditoModel, true)) {

                        lRegCartaoCreditos.add(cartaoCreditoModel);

                        selecionaCombo(comboCartaoCredito, cartaoCreditoModel, true);

                    }

                    break;

                default:

                    lColecao = cedenteBO.obterTodos(organizacaoModel);
                    lRegistros = GlazedLists.eventList(lColecao);
                    if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                        support.uninstall();
                    }
                    support = AutoCompleteSupport.install(comboCedente, lRegistros, new CedenteTextFilterator());
                    support.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    support.setStrict(false);

                    lTipoCedentes = new ArrayList<TipoCedenteModel>();
                    tipoCedenteModel = new TipoCedenteModel();
                    tipoCedenteModel.setDescricao(" -> Selecione <- ");
                    lTipoCedentes.add(tipoCedenteModel);
                    lTipoCedentes.addAll(tipoCedenteBO.obterTodos(organizacaoModel));
                    comboTipoCedente.setModel(new javax.swing.DefaultComboBoxModel(lTipoCedentes.toArray()));

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

                    lCartoes = cartaoCreditoBO.obterTodos(organizacaoModel);
                    lRegCartaoCreditos = GlazedLists.eventList(lCartoes);
                    if (supportCartao != null && supportCartao.getItemList() != null && supportCartao.getComboBox() != null) {
                        supportCartao.uninstall();
                    }
                    supportCartao = AutoCompleteSupport.install(comboCartaoCredito, lRegCartaoCreditos, new CartaoCreditoTextFilterator());
                    supportCartao.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportCartao.setStrict(false);

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

    public CadastroCedente() throws SystemException {

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
        campoCodigoCartaoCredito.setVisible(false);

        //jFTCpfCpnj.setVisible(false);
        jFTCpfCpnj.setEnabled(false);
        labelCpf.setVisible(false);

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

        jFTCga.setDocument(new Documento(20));
        jFTInscEstadual.setDocument(new Documento(20));

        jTConta.setDocument(new Documento(14));
        jTDigitoConta.setDocument(new Documento(1));
        jTContaReduzida.setDocument(new Documento(8));
        jTDigitoContaReduzida.setDocument(new Documento(1));

        //Aplicando mascara em campos.
        //mascara
        //Ver como tratar a questão de CPF ou CNPJ de cedente
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
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        labelCedente = new javax.swing.JLabel();
        comboCedente = new javax.swing.JComboBox();
        labelCpf = new javax.swing.JLabel();
        jFTCpfCpnj = new javax.swing.JFormattedTextField();
        labelTipoCedente = new javax.swing.JLabel();
        comboTipoCedente = new javax.swing.JComboBox();
        campoCodigo = new javax.swing.JTextField();
        comboPersonalidade = new javax.swing.JComboBox();
        labelPersonalidade = new javax.swing.JLabel();
        labelCpf1 = new javax.swing.JLabel();
        jFTCga = new javax.swing.JFormattedTextField();
        jFTInscEstadual = new javax.swing.JFormattedTextField();
        jFTInscMunicipal = new javax.swing.JFormattedTextField();
        labelCpf3 = new javax.swing.JLabel();
        labelCpf4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelLog = new javax.swing.JLabel();
        jTLogradouro = new javax.swing.JTextField();
        labelCep = new javax.swing.JLabel();
        jFTCep = new javax.swing.JFormattedTextField();
        labelNum = new javax.swing.JLabel();
        jTNumero = new javax.swing.JTextField();
        labelEst = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox();
        labelComp = new javax.swing.JLabel();
        campoCodigoEndereco = new javax.swing.JTextField();
        jTComplemento = new javax.swing.JTextField();
        labelBai = new javax.swing.JLabel();
        comboBairro = new javax.swing.JComboBox();
        labelCid = new javax.swing.JLabel();
        comboCidade = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        campoCodigoContato = new javax.swing.JTextField();
        labelTelFixo = new javax.swing.JLabel();
        labelDDDTel = new javax.swing.JLabel();
        jTDDDTelFixo = new javax.swing.JTextField();
        labelDDDCel = new javax.swing.JLabel();
        jTDDDCel = new javax.swing.JTextField();
        labelCel = new javax.swing.JLabel();
        jFTTelFixo = new javax.swing.JFormattedTextField();
        jFTCel = new javax.swing.JFormattedTextField();
        labelMail = new javax.swing.JLabel();
        jFTEMail = new javax.swing.JFormattedTextField();
        labelMsn = new javax.swing.JLabel();
        jFTMsn = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        labelConta = new javax.swing.JLabel();
        labelNomeBanco = new javax.swing.JLabel();
        comboBanco = new javax.swing.JComboBox();
        labelAgencia = new javax.swing.JLabel();
        jTAgencia = new javax.swing.JTextField();
        jTContaBancaria = new javax.swing.JTextField();
        comboCartaoCredito = new javax.swing.JComboBox();
        labelNomeBanco1 = new javax.swing.JLabel();
        campoCodigoCartaoCredito = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        campoNatureza = new javax.swing.JTextField();
        campoRelacionamento = new javax.swing.JTextField();
        campoOrdemDipj = new javax.swing.JTextField();
        campoGrau = new javax.swing.JTextField();
        campoINSCMF = new javax.swing.JTextField();
        campoCodigoContaContabil = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lupaPesquisaContaContabilCredito = new javax.swing.JButton();
        labelDescContaContabil = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTConta = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jTDigitoConta = new br.com.pempec.componentes.JIntegerField();
        jLabel2 = new javax.swing.JLabel();
        jTContaReduzida = new br.com.pempec.componentes.JIntegerField();
        jLabel6 = new javax.swing.JLabel();
        jTDigitoContaReduzida = new br.com.pempec.componentes.JIntegerField();
        jLabel12 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Cedente");

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
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoImprimirFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setPreferredSize(new java.awt.Dimension(512, 308));

        labelCedente.setText("Cedente");

        comboCedente.setFont(getFont());
        comboCedente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCedenteActionPerformed(evt);
            }
        });

        labelCpf.setText("CPF");

        labelTipoCedente.setFont(new java.awt.Font("Arial", 0, 12));
        labelTipoCedente.setText("Tipo Cedente");

        comboTipoCedente.setFont(getFont());

        campoCodigo.setEditable(false);

        comboPersonalidade.setFont(getFont());
        comboPersonalidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPersonalidadeItemStateChanged(evt);
            }
        });

        labelPersonalidade.setFont(new java.awt.Font("Arial", 0, 12));
        labelPersonalidade.setText("Personalidade");

        labelCpf1.setText("CGA");

        labelCpf3.setText("Insc. Municipal");

        labelCpf4.setText("Insc. Estadual");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCedente)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(comboCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPersonalidade)
                            .addComponent(comboPersonalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTipoCedente)
                            .addComponent(comboTipoCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTCpfCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCpf)
                            .addComponent(jFTCga, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCpf1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCpf4)
                            .addComponent(jFTInscEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCpf3)
                            .addComponent(jFTInscMunicipal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(82, 82, 82))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCedente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelPersonalidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboPersonalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelTipoCedente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTipoCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCpf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTCpfCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCpf1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTCga, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCpf3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTInscMunicipal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCpf4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTInscEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cedente", jPanel5);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setPreferredSize(new java.awt.Dimension(512, 308));

        labelLog.setText("Logradouro");

        labelCep.setText("Cep");

        labelNum.setText("Número");

        labelEst.setText("Estado");

        comboEstado.setFont(getFont());

        labelComp.setText("Complemento");

        campoCodigoEndereco.setEditable(false);

        labelBai.setText("Bairro");

        comboBairro.setFont(getFont());

        labelCid.setText("Cidade");

        comboCidade.setFont(getFont());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelComp)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTComplemento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelLog)
                                        .addComponent(jTLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNum)
                                        .addComponent(jTNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(17, 17, 17)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelBai)
                            .addComponent(labelEst)
                            .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(campoCodigoEndereco))
                            .addComponent(labelCid)
                            .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCep, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(114, 114, 114))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLog)
                    .addComponent(labelNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelComp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelEst)
                        .addGap(6, 6, 6)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelCid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelBai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelCep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(76, 76, 76))
        );

        jTabbedPane1.addTab("Endereço", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setPreferredSize(new java.awt.Dimension(512, 308));

        campoCodigoContato.setEditable(false);

        labelTelFixo.setText("Telefone Fixo");

        labelDDDTel.setText("DDD");

        jTDDDTelFixo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelDDDCel.setText("DDD");

        jTDDDCel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelCel.setText("Telefone Celular");

        labelMail.setText("E-mail");

        labelMsn.setText("Live Messenger");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDDDTel)
                    .addComponent(jTDDDCel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDDDCel)
                    .addComponent(jTDDDTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTelFixo)
                    .addComponent(labelCel)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jFTCel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(campoCodigoContato, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFTTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMsn)
                    .addComponent(labelMail)
                    .addComponent(jFTEMail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTMsn, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelDDDTel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDDDTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDDDCel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDDDCel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelTelFixo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTCel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelMail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTEMail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMsn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTMsn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Contato", jPanel4);

        jPanel1.setPreferredSize(new java.awt.Dimension(512, 308));

        labelConta.setText("Conta");

        labelNomeBanco.setText("Banco");

        comboBanco.setFont(getFont());

        labelAgencia.setText("Agência");

        jTAgencia.setFont(new java.awt.Font("Arial", 0, 12));

        jTContaBancaria.setFont(new java.awt.Font("Arial", 0, 12));

        comboCartaoCredito.setFont(getFont());
        comboCartaoCredito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCartaoCreditoItemStateChanged(evt);
            }
        });

        labelNomeBanco1.setText("Cartão Crédito");

        campoCodigoCartaoCredito.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelConta)
                    .addComponent(jTContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAgencia)
                    .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboCartaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNomeBanco)
                    .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNomeBanco1))
                .addGap(122, 122, 122))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(campoCodigoCartaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(423, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNomeBanco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(labelAgencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelNomeBanco1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCartaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(campoCodigoCartaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
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

        lupaPesquisaContaContabilCredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.jpg"))); // NOI18N
        lupaPesquisaContaContabilCredito.setBorderPainted(false);
        lupaPesquisaContaContabilCredito.setContentAreaFilled(false);
        lupaPesquisaContaContabilCredito.setDefaultCapable(false);
        lupaPesquisaContaContabilCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lupaPesquisaContaContabilCreditoActionPerformed(evt);
            }
        });

        labelDescContaContabil.setForeground(new java.awt.Color(0, 153, 153));
        labelDescContaContabil.setText("<<< --- Pesquise aqui!");
        labelDescContaContabil.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));

        jLabel1.setText("Conta");

        jLabel4.setText("Dg");

        jLabel2.setText("CT.  Reduzida");

        jLabel6.setText("Dg");

        jLabel12.setText("Tipo");

        comboTipo.setFont(new java.awt.Font("Arial", 0, 10));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addComponent(campoNatureza, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoCodigoContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoINSCMF, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoGrau, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoOrdemDipj, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoRelacionamento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(9, 9, 9))
                            .addComponent(jTDigitoConta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTDigitoContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lupaPesquisaContaContabilCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelDescContaContabil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lupaPesquisaContaContabilCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelDescContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDigitoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(jTDigitoContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(31, 31, 31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        botaoExcluir.setEnabled(false);
        botaoAlterar.setEnabled(false);
        botaoImprimirFicha.setEnabled(false);
        botaoIncluir.setEnabled(true);

        limparCampos(jPanel5, jPanel3, jPanel4, jPanel1, jPanel6);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

        if (comboCedente.getSelectedItem() != null) {
            valorCombo = comboCedente.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                if (campoCodigo.getText().equals(Controller.getOrganizacao().getId())) {
                    exibeMensagemAviso("Não é possível excluir este registro! \n Este Cedente é padrão do sistema.", null);
                    Controller.sendMailInterno("CadCedenteBTNEXC", "Usuario " + Controller.getUsuarioLogado().getNome() + " tentou excluir o cedente padrao.", null);
                    return;
                }

                CedenteModel tab = new CedenteModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab.setNome(valorCombo);

                tab = cedenteBO.consultarPorTemplate(tab);

                tab.setMovimentoDiarioModel(registroMovimento("Deletar Cedente", tab.getCpfCnpj(), ((TipoCedenteModel) comboTipoCedente.getSelectedItem()).getDescricao() + " " + tab.getNome(), null, "Deletado"));

                cedenteBO.excluir(tab);

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

        if (comboCedente.getSelectedItem() != null) {
            valorCombo = comboCedente.getSelectedItem().toString().toUpperCase();
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

                    CedenteModel tab = new CedenteModel();
                    tab.setPk(new PKModel());
                    tab.getPk().setOrganizacao(organizacaoModel);
                    tab.getPk().setId(PempecKeyGenerator.generateKey());

                    if (this.validaPreenchimentoCombo(comboBanco, true)) {
                        tab.setBanco((BancoModel) comboBanco.getSelectedItem());
                    }

                    tab.setAgencia(jTAgencia.getText());
                    tab.setConta(jTContaBancaria.getText());

                    //tratando o fornecedor associado a um cartao de credito
                    if (comboCartaoCredito.getSelectedItem() != null && !comboCartaoCredito.getSelectedItem().toString().isEmpty()) {
                        tab.setCartaoCreditoModel((CartaoCreditoModel) comboCartaoCredito.getSelectedItem());
                    }
                    //Tratamento de Nulidade.
                    //Tratamento tbm da opção Selecione                    

                    if (this.validaPreenchimentoCombo(comboTipoCedente, false)) {

                        TipoCedenteModel tpc = new TipoCedenteModel();
                        tpc.setPk(new PKModel());
                        tpc.setDescricao(comboTipoCedente.getSelectedItem().toString());
                        tpc.getPk().setOrganizacao(organizacaoModel);

                        tpc = tipoCedenteBO.consultarPorTemplate(tpc);

                        if (tpc.getPk() != null && tpc.getPk().getId() != null) {

                            tab.setTipoCedente(tpc);
                        }

                        // tab.setTipoCedente((TipoCedenteModel) comboTipoCedente.getSelectedItem());
                    }

                    tab.setNome(valorCombo);

                    tab.setPersonalidade(((String) comboPersonalidade.getSelectedItem()).toString());

                    tab.setCpfCnpj(jFTCpfCpnj.getText());

                    tab.setCga(jFTCga.getText());

                    tab.setInscricaoEstadual(jFTInscEstadual.getText());

                    tab.setInscricaoMunicipal(jFTInscMunicipal.getText());

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

                    //Considerando que o endereço é obrigatório.
                    //ATENCAO: o endereco nao é obrigatorio.
                    if (!jTLogradouro.getText().isEmpty()) {

                        tab.setEndereco(new EnderecoModel());
                        tab.getEndereco().setPk(new PKModel());
                        tab.getEndereco().getPk().setId(PempecKeyGenerator.generateKey());
                        tab.getEndereco().setLogradouro(jTLogradouro.getText());
                        tab.getEndereco().setNumero(jTNumero.getText());
                        tab.getEndereco().setCep(jFTCep.getText());
                        tab.getEndereco().setComplemento(jTComplemento.getText());

                        if (this.validaPreenchimentoCombo(comboEstado, true)) {
                            tab.getEndereco().setEstado((EstadoModel) comboEstado.getSelectedItem());
                        }

                        if (this.validaPreenchimentoCombo(comboBairro, true)) {
                            tab.getEndereco().setBairro((BairroModel) comboBairro.getSelectedItem());
                        }

                        if (this.validaPreenchimentoCombo(comboCidade, true)) {
                            tab.getEndereco().setCidade((CidadeModel) comboCidade.getSelectedItem());
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
                    }

                    tab.setMovimentoDiarioModel(registroMovimento("Inserir Cedente", tab.getCpfCnpj(), ((TipoCedenteModel) comboTipoCedente.getSelectedItem()).getDescricao() + " " + tab.getNome(), null, "Inserido"));

                    cedenteBO.inserir(tab);

                    this.botaoLimparActionPerformed(evt);

                } else {

                    exibeMensagemAviso("Campo(s) obrigatório(s).", null);
                    jTabbedPane1.setSelectedComponent(validateForm.getParent());
                    validateForm.requestFocus();

                }

            } catch (ApplicationException ex) {

                tratamentoExcecao(ex);

            } catch (final SystemException ex) {

                ex.printStackTrace();

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
    }//GEN-LAST:event_botaoIncluirActionPerformed

private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

    String valorCombo = null;

    if (comboCedente.getSelectedItem() != null) {
        valorCombo = comboCedente.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {

            if (!Controller.checarPermissao(tela, Action.ALTERAR.getAction())) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            Component validateForm = validaCampos();

            if (validateForm == null) {

                CedenteModel tab = new CedenteModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab = cedenteBO.consultarPorPk(tab);

                //Remove do combo
                removeItemComboByName(lRegistros, tab.getNome(), true);

                //Tratamento de Nulidade.
                //Tratamento tbm da opção Selecione
                if (this.validaPreenchimentoCombo(comboTipoCedente, false)) {
                    tab.setTipoCedente((TipoCedenteModel) comboTipoCedente.getSelectedItem());
                }

                tab.setNome(valorCombo.toUpperCase());

                tab.setPersonalidade(((String) comboPersonalidade.getSelectedItem()).toString());

                tab.setCpfCnpj(jFTCpfCpnj.getText());

                tab.setCga(jFTCga.getText());

                tab.setInscricaoEstadual(jFTInscEstadual.getText());

                tab.setInscricaoMunicipal(jFTInscMunicipal.getText());

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

                        //contaContabil.getPk().setId(campoCodigoContaContabil.getText());
                        contaContabil.getPk().setId(PempecKeyGenerator.generateKey());

                        //aqui nao era p ser uma chave nova?
                        contaContabil.getPk().setOrganizacao(organizacaoModel);

                        contaContabil.setDescricao(tab.getNome());

                        contaContabil.setConta(jTConta.getText());

                        contaContabil.setDigitoConta(jTDigitoConta.getText());

                        contaContabil.setContaReduzida(jTContaReduzida.getText());

                        contaContabil.setDigitoContaReduzida(jTDigitoContaReduzida.getText());

                        contaContabil.setGrau("5");

                        contaContabil.setInscricaoCMF("0");

                        contaContabil.setNatureza(PempecParse.stringToInteger(contaContabil.getConta().substring(0, 0)));

                        contaContabil.setOrdemDIPJ(0);

                        contaContabil.setRelacionamento(0);

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
                    tab.getEndereco().setLogradouro(jTLogradouro.getText().toUpperCase());
                    tab.getEndereco().setNumero(jTNumero.getText());

                    tab.getEndereco().setCep(jFTCep.getText());

                    tab.getEndereco().setComplemento(jTComplemento.getText().toUpperCase());

                    if (this.validaPreenchimentoCombo(comboEstado, true)) {
                        tab.getEndereco().setEstado((EstadoModel) comboEstado.getSelectedItem());
                    }

                    if (this.validaPreenchimentoCombo(comboBairro, true)) {
                        tab.getEndereco().setBairro((BairroModel) comboBairro.getSelectedItem());
                    }

                    if (this.validaPreenchimentoCombo(comboCidade, true)) {
                        tab.getEndereco().setCidade((CidadeModel) comboCidade.getSelectedItem());
                    }

                }

                //Tratando que o contato não é obrigatório
                //Na validação deve contemplar, caso o usuario digite o ddd, obrigatoriamente deva digitar o numero.
                if (!jTDDDTelFixo.getText().isEmpty()) {

                    tab.setContato(new ContatoModel());
                    tab.getContato().setPk(new PKModel());
                    tab.getContato().getPk().setId(campoCodigoContato.getText());
                    tab.getContato().setTelefone(jTDDDTelFixo.getText() + jFTTelFixo.getText());

                    tab.getContato().setEmail(jFTEMail.getText().toUpperCase());
                    tab.getContato().setMsn(jFTMsn.getText().toUpperCase());
                    tab.getContato().setCelular(jTDDDCel.getText() + jFTCel.getText());

                }
                if (this.validaPreenchimentoCombo(comboBanco, true)) {
                    tab.setBanco((BancoModel) comboBanco.getSelectedItem());
                }

                tab.setAgencia(jTAgencia.getText());
                tab.setConta(jTContaBancaria.getText());

                //tratando o fornecedor associado a um cartao de credito
                if (comboCartaoCredito.getSelectedItem() != null && !comboCartaoCredito.getSelectedItem().toString().isEmpty()) {
                    tab.setCartaoCreditoModel((CartaoCreditoModel) comboCartaoCredito.getSelectedItem());
                }

                tab.setMovimentoDiarioModel(registroMovimento("Alterar Cedente", tab.getCpfCnpj(), ((TipoCedenteModel) comboTipoCedente.getSelectedItem()).getDescricao() + " " + tab.getNome(), null, "Alterado"));

                cedenteBO.alterar(tab);

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
        exibeMensagemAviso("Campo Nome é obrigatório.", null);
    }

}//GEN-LAST:event_botaoAlterarActionPerformed

private void comboCedenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCedenteActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboCedente.getSelectedItem() != null && comboCedente.getSelectedItem() instanceof CedenteModel && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            CedenteModel tab = cedenteBO.consultarPorPk((CedenteModel) comboCedente.getSelectedItem());

            if (tab != null && tab.getPk() != null) {

                botaoAlterar.setEnabled(true);
                botaoImprimirFicha.setEnabled(true);
                botaoExcluir.setEnabled(true);
                botaoIncluir.setEnabled(false);

                limparCamposExcept(comboCedente, jPanel5, jPanel3, jPanel4, jPanel1, jPanel6);

                campoCodigo.setText(tab.getPk().getId());

                selecionaCombo(comboTipoCedente, tab.getTipoCedente(), false);

                selecionaCombo(comboPersonalidade, tab.getPersonalidade(), false);

                jFTCpfCpnj.setText(tab.getCpfCnpj());

                jFTCga.setText(tab.getCga());

                jFTInscEstadual.setText(tab.getInscricaoEstadual());

                jFTInscMunicipal.setText(tab.getInscricaoMunicipal());

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

                if (tab.getEndereco() != null && tab.getEndereco().getPk() != null && !tab.getEndereco().getPk().getId().isEmpty()) {

                    campoCodigoEndereco.setText(tab.getEndereco().getPk().getId());
                    jTLogradouro.setText(tab.getEndereco().getLogradouro());
                    jTNumero.setText(tab.getEndereco().getNumero());
                    jFTCep.setText(tab.getEndereco().getCep());
                    jTComplemento.setText(tab.getEndereco().getComplemento());

                    if (tab.getEndereco().getEstado() != null) {
                        selecionaCombo(comboEstado, tab.getEndereco().getEstado(), true);
                    }

                    if (tab.getEndereco().getCidade() != null) {
                        selecionaCombo(comboCidade, tab.getEndereco().getCidade(), true);
                    }

                    if (tab.getEndereco().getBairro() != null) {
                        selecionaCombo(comboBairro, tab.getEndereco().getBairro(), true);
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

                if (tab.getBanco() != null && !tab.getBanco().getId().isEmpty()) {
                    selecionaCombo(comboBanco, tab.getBanco(), true);
                }

                jTAgencia.setText(tab.getAgencia());

                jTContaBancaria.setText(tab.getConta());

                if (tab.getCartaoCreditoModel() != null && tab.getCartaoCreditoModel().getPk().getId() != null) {
                    selecionaCombo(comboCartaoCredito, tab.getCartaoCreditoModel(), true);
                    campoCodigoCartaoCredito.setText(tab.getCartaoCreditoModel().getPk().getId());
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

}//GEN-LAST:event_comboCedenteActionPerformed

private void comboPersonalidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPersonalidadeItemStateChanged

    if (((String) comboPersonalidade.getSelectedItem()).toString().equalsIgnoreCase("PF")) {

//        jFTCpfCpnj.setVisible(true);
//        labelCpf.setVisible(true);
//
        jFTCpfCpnj.setEnabled(true);
        labelCpf.setVisible(true);

        labelCpf.setText("CPF");
        jFTCpfCpnj.setFormatterFactory(MaskUtils.mascaraCpf());
        jFTCpfCpnj.setValue(null);
    } else {
        if (((String) comboPersonalidade.getSelectedItem()).toString().equalsIgnoreCase("PJ")) {
            jFTCpfCpnj.setEnabled(true);
            labelCpf.setVisible(true);
            labelCpf.setText("CNPJ");
            jFTCpfCpnj.setValue(null);
            jFTCpfCpnj.setFormatterFactory(MaskUtils.mascaraCnpj());
        } else {
            jFTCpfCpnj.setEnabled(true);
            labelCpf.setVisible(false);
            jFTCpfCpnj.setFormatterFactory(null);
            jFTCpfCpnj.setValue(null);
        }
    }


}//GEN-LAST:event_comboPersonalidadeItemStateChanged

private void botaoImprimirFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirFichaActionPerformed

    try {

        CedenteModel tab = new CedenteModel();
        tab.setPk(new PKModel());
        tab.getPk().setOrganizacao(organizacaoModel);
        tab.getPk().setId(campoCodigo.getText());

        tab = cedenteBO.consultarPorPk(tab);

        if (tab != null && tab.getPk() != null && !tab.getPk().getId().isEmpty()) {

            FichaCadastralCedente fichaCadastralCedente = new FichaCadastralCedente(tab);
            fichaCadastralCedente.setTitle("Ficha Cedente");
            fichaCadastralCedente.repopularCombos();
            TelaPrincipal.desktopPane.add(fichaCadastralCedente);
            fichaCadastralCedente.show();

        } else {

            exibeMensagemAviso("Problemas ao imprimir a ficha. ", "Ficha Ced");

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

private void comboCartaoCreditoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCartaoCreditoItemStateChanged
    if (comboCartaoCredito.getSelectedItem() != null) {

        String idCartao = ((CartaoCreditoModel) comboCartaoCredito.getSelectedItem()).getPk().getId();

        campoCodigoCartaoCredito.setText(idCartao);

    }
}//GEN-LAST:event_comboCartaoCreditoItemStateChanged

private void lupaPesquisaContaContabilCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lupaPesquisaContaContabilCreditoActionPerformed

    String valorCombo = null;

    if (comboCedente.getSelectedItem() != null) {

        valorCombo = comboCedente.getSelectedItem().toString().toUpperCase();

    }

    if (valorCombo != null) {

        try {

            if (pesquisaContaContabilCredito == null || pesquisaContaContabilCredito.isClosed()) {
                pesquisaContaContabilCredito = new PesquisaContaContabil(this, "C", this.getLocation());
                TelaPrincipal.desktopPane.add(pesquisaContaContabilCredito);
                pesquisaContaContabilCredito.show();
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


}//GEN-LAST:event_lupaPesquisaContaContabilCreditoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimirFicha;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoCodigoCartaoCredito;
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
    private javax.swing.JComboBox comboCartaoCredito;
    private javax.swing.JComboBox comboCedente;
    private javax.swing.JComboBox comboCidade;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JComboBox comboPersonalidade;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JComboBox comboTipoCedente;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFormattedTextField jFTCel;
    private javax.swing.JFormattedTextField jFTCep;
    private javax.swing.JFormattedTextField jFTCga;
    private javax.swing.JFormattedTextField jFTCpfCpnj;
    private javax.swing.JFormattedTextField jFTEMail;
    private javax.swing.JFormattedTextField jFTInscEstadual;
    private javax.swing.JFormattedTextField jFTInscMunicipal;
    private javax.swing.JFormattedTextField jFTMsn;
    private javax.swing.JFormattedTextField jFTTelFixo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JLabel labelBai;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCel;
    private javax.swing.JLabel labelCep;
    private javax.swing.JLabel labelCid;
    private javax.swing.JLabel labelComp;
    private javax.swing.JLabel labelConta;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelCpf1;
    private javax.swing.JLabel labelCpf3;
    private javax.swing.JLabel labelCpf4;
    private javax.swing.JLabel labelDDDCel;
    private javax.swing.JLabel labelDDDTel;
    private javax.swing.JLabel labelDescContaContabil;
    private javax.swing.JLabel labelEst;
    private javax.swing.JLabel labelLog;
    private javax.swing.JLabel labelMail;
    private javax.swing.JLabel labelMsn;
    private javax.swing.JLabel labelNomeBanco;
    private javax.swing.JLabel labelNomeBanco1;
    private javax.swing.JLabel labelNum;
    private javax.swing.JLabel labelPersonalidade;
    private javax.swing.JLabel labelTelFixo;
    private javax.swing.JLabel labelTipoCedente;
    private javax.swing.JButton lupaPesquisaContaContabilCredito;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private AutoCompleteSupport supportEstado;
    private AutoCompleteSupport supportCidade;
    private AutoCompleteSupport supportBairro;
    private AutoCompleteSupport supportBanco;
    private AutoCompleteSupport supportCartao;

    private Component validaCampos() throws ApplicationException, SystemException {

        if (!validaPreenchimentoCombo(comboTipoCedente, false)) {
            return comboTipoCedente;
        }

        if (jFTCpfCpnj.getText().isEmpty()
                || jFTCpfCpnj.getText().equals("   .   .   -  ") || jFTCpfCpnj.getText().equals("  .   .   /    -  ")) {
            return jFTCpfCpnj;
        }

        if (!jTLogradouro.getText().isEmpty()) {

            if (!validaPreenchimentoCombo(comboEstado, true)) {
                return comboEstado;
            }

            if (!validaPreenchimentoCombo(comboCidade, true)) {
                return comboCidade;
            }

            if (!validaPreenchimentoCombo(comboBairro, true)) {
                return comboBairro;
            }

        }

        if (!jTConta.getText().isEmpty()) {

            if (jTConta.getText().isEmpty()) {
                return jTConta;
            }

            if (jTDigitoConta.getText().isEmpty()) {
                return jTDigitoConta;
            }

            if (jTContaReduzida.getText().isEmpty()) {
                return jTContaReduzida;
            }

            if (jTDigitoContaReduzida.getText().isEmpty()) {
                return jTDigitoContaReduzida;
            }

            if (comboTipo.getSelectedItem() == null) {
                comboTipo.requestFocus();
            }

        }

        //Validando existencia de conta e conta reduzida contabil
        if (jTConta.getText() != null && !jTConta.getText().isEmpty()) {

            ContaContabilModel contaContabil = new ContaContabilModel();

            contaContabil.setPk(new PKModel());

            contaContabil.getPk().setOrganizacao(organizacaoModel);

            contaContabil.setConta(jTConta.getText());

            contaContabil = contaContabilBO.consultarPorTemplate(contaContabil);

            if (contaContabil != null && contaContabil.getPk() != null && (campoCodigo.getText().isEmpty() || !campoCodigoContaContabil.getText().equals(contaContabil.getPk().getId()))) {

                CedenteModel cedenteAux = cedenteBO.consultarPorContaContabil(contaContabil);

                if (cedenteAux != null && cedenteAux.getPk() != null) {

                    exibeMensagemAviso("A conta: " + jTConta.getText() + " já existe!\nEstá associada ao " + cedenteAux.getTipoCedente().getDescricao() + " : " + cedenteAux.getNome(), "Validação Conta Contábil");

                    limparAbaContaContabil();

                    return jTConta;

                }

            }

        }

        return null;

    }
    private PesquisaContaContabil pesquisaContaContabilCredito;
    private ContaContabilModel contaContabilCredito;

    public void setPesquisaContaContabil(ContaContabilModel contaContabil, String tipo) {

        if (tipo.equals("C")) {

            this.contaContabilCredito = contaContabil;

            if (contaContabilCredito != null && contaContabilCredito.getPk() != null) {

                labelDescContaContabil.setText(contaContabilCredito.getDescricao());

                jTConta.setText(contaContabilCredito.getConta());
                jTDigitoConta.setText(contaContabilCredito.getDigitoConta());

                jTContaReduzida.setText(contaContabilCredito.getContaReduzida());
                jTDigitoContaReduzida.setText(contaContabilCredito.getDigitoContaReduzida());

                if (contaContabilCredito.getTipo().equals("D")) {
                    comboTipo.setSelectedIndex(1);
                } else {
                    if (contaContabilCredito.getTipo().equals("C")) {
                        comboTipo.setSelectedIndex(2);
                    } else {
                        comboTipo.setSelectedIndex(0);
                    }
                }

            }

        }

    }

    public void limparAbaContaContabil() {

        campoCodigoContaContabil.setText("");
        jTConta.setText("");
        jTDigitoConta.setText("0");
        jTContaReduzida.setText("0");
        jTDigitoContaReduzida.setText("0");

        campoINSCMF.setText("");
        campoGrau.setText("5");
        campoOrdemDipj.setText("");
        campoRelacionamento.setText("");
        campoNatureza.setText("");

        labelDescContaContabil.setText("<<< --- Pesquise aqui!");
        comboTipo.setSelectedIndex(0);

    }
}
