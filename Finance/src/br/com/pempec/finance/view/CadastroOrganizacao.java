package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BairroBO;
import br.com.pempec.finance.businessObjects.CidadeBO;
import br.com.pempec.finance.businessObjects.EstadoBO;
import br.com.pempec.finance.businessObjects.LocalPagamentoBO;
import br.com.pempec.finance.businessObjects.OrganizacaoBO;
import br.com.pempec.finance.businessObjects.TipoCedenteBO;
import br.com.pempec.finance.businessObjects.TipoCobrancaBO;
import br.com.pempec.finance.businessObjects.TipoNotaFiscalBO;
import br.com.pempec.finance.businessObjects.TipoSacadoBO;
import br.com.pempec.finance.businessObjects.TipoStatusBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BairroModel;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.CidadeModel;
import br.com.pempec.finance.models.EstadoModel;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.LocalPagamentoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TipoCedenteModel;
import br.com.pempec.finance.models.TipoCobrancaModel;
import br.com.pempec.finance.models.TipoNotaFiscalModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TipoSacadoModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.MD5;
import br.com.pempec.finance.utils.MaskUtils;
import br.com.pempec.finance.utils.iterators.OrganizacaoTextFilterator;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
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
public class CadastroOrganizacao extends FinanceInternalFrame implements IRepopulador {

    private OrganizacaoBO organizacaoBO = new OrganizacaoBO();
    private EstadoBO estadoBO = new EstadoBO();
    private CidadeBO cidadeBO = new CidadeBO();
    private BairroBO bairroBO = new BairroBO();
    private TipoSacadoBO tipoSacadoBO = new TipoSacadoBO();
    private TipoCedenteBO tipoCedenteBO = new TipoCedenteBO();
    private TipoCobrancaBO tipoCobrancaBO = new TipoCobrancaBO();
    private TipoStatusBO tipoStatusBO = new TipoStatusBO();
    private TipoNotaFiscalBO tipoNotaFiscalBO = new TipoNotaFiscalBO();
    private LocalPagamentoBO localPagamentoBO = new LocalPagamentoBO();
    private long tela = Tela.TELA_PARAMETROS_ORGANIZACOES.getTela();
    Collection<TipoCedenteModel> listaTipoCedente = new ArrayList<TipoCedenteModel>();
    Collection<TipoSacadoModel> listaTipoSacado = new ArrayList<TipoSacadoModel>();
    Collection<TipoNotaFiscalModel> listaTipoNF = new ArrayList<TipoNotaFiscalModel>();
    Collection<TipoStatusModel> listaTipoStatus = new ArrayList<TipoStatusModel>();
    Collection<LocalPagamentoModel> listaLP = new ArrayList<LocalPagamentoModel>();
    Collection<CentroCustoModel> listaCentroCusto = new ArrayList<CentroCustoModel>();
    Collection<TipoCobrancaModel> listaCobranca = new ArrayList<TipoCobrancaModel>();
    Collection<HistoricoModel> listaHistorico = new ArrayList<HistoricoModel>();
    Collection<FormaPagamentoModel> listaFP = new ArrayList<FormaPagamentoModel>();
    Collection<TipoOperacaoBancariaModel> listaOperacaoBco = new ArrayList<TipoOperacaoBancariaModel>();
    String razaoCadastro = "";

    private String NameObject() {
        return (String) "Organização";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {

            EstadoModel estadoModel = new EstadoModel();

            estadoModel.setDescricao(" -> Selecione <- ");

            Collection<EstadoModel> lEstados = new ArrayList<EstadoModel>();

            lEstados.add(estadoModel);

            lEstados.addAll(estadoBO.obterTodos());

            //Bairro
            Collection<BairroModel> lBairro = new ArrayList<BairroModel>();

            BairroModel bairroModel = new BairroModel();

            bairroModel.setDescricao(" -> Selecione <- ");

            lBairro.add(bairroModel);

            Collection<CidadeModel> lCidade = new ArrayList<CidadeModel>();

            CidadeModel cidadeModel = new CidadeModel();

            cidadeModel.setDescricao(" -> Selecione <- ");

            lCidade.add(cidadeModel);

            comboCidade.setModel(new javax.swing.DefaultComboBoxModel(lCidade.toArray()));

            comboBairro.setModel(new javax.swing.DefaultComboBoxModel(lBairro.toArray()));

            comboEstado.setModel(new javax.swing.DefaultComboBoxModel(lEstados.toArray()));

            //Organizacao
            Collection<OrganizacaoModel> lOrganizacoes = new ArrayList<OrganizacaoModel>();

            lOrganizacoes.addAll(organizacaoBO.obterTodos());

            final EventList<OrganizacaoModel> lRegistros = GlazedLists.eventList(lOrganizacoes);

            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboOrganizacao, lRegistros, new OrganizacaoTextFilterator());
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

    public CadastroOrganizacao() throws SystemException {
        initComponents();

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);
        comboCidade.setEnabled(false);
        comboBairro.setEnabled(false);

        //Aplicando tamanho maximo de caracteres do campo.

        jFTIp.setDocument(new Documento(15));
        jFTNomeServidor.setDocument(new Documento(60));
        jTSigla.setDocument(new Documento(10));
        jFTInscEstadual.setDocument(new Documento(20));
        jFTInscMunicipal.setDocument(new Documento(20));
        jTFantasia.setDocument(new Documento(50));
        jFTCpnj.setFormatterFactory(MaskUtils.mascaraCnpj());
        jFTCep.setFormatterFactory(MaskUtils.mascaraCep());
        jTLogradouro.setDocument(new Documento(60));
        jTNumero.setDocument(new Documento(6));
        jTComplemento.setDocument(new Documento(60));

        //aplicando maskara
        // jFTIp.setFormatterFactory(MaskUtils.mascaraIpAdress());


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        labelCedente = new javax.swing.JLabel();
        comboOrganizacao = new javax.swing.JComboBox();
        campoCodigo = new javax.swing.JTextField();
        labelCedente1 = new javax.swing.JLabel();
        jTFantasia = new javax.swing.JTextField();
        jTSigla = new javax.swing.JTextField();
        labelCedente2 = new javax.swing.JLabel();
        labelCNPJ = new javax.swing.JLabel();
        jFTCpnj = new javax.swing.JFormattedTextField();
        jFTInscEstadual = new javax.swing.JFormattedTextField();
        jFTInscMunicipal = new javax.swing.JFormattedTextField();
        labelCNPJ1 = new javax.swing.JLabel();
        labelInscM = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jFTIp = new javax.swing.JFormattedTextField();
        labelInscM1 = new javax.swing.JLabel();
        jFTNomeServidor = new javax.swing.JFormattedTextField();
        labelInscM2 = new javax.swing.JLabel();
        jFTVinculoContabil = new javax.swing.JFormattedTextField();
        labelInscM3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labelLog = new javax.swing.JLabel();
        jTLogradouro = new javax.swing.JTextField();
        labelCep = new javax.swing.JLabel();
        jFTCep = new javax.swing.JFormattedTextField();
        labelNum = new javax.swing.JLabel();
        jTNumero = new javax.swing.JTextField();
        labelEst = new javax.swing.JLabel();
        labelComp = new javax.swing.JLabel();
        jTComplemento = new javax.swing.JTextField();
        labelBai = new javax.swing.JLabel();
        labelCid = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox();
        comboCidade = new javax.swing.JComboBox();
        comboBairro = new javax.swing.JComboBox();
        labelVersao = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Organização");
        setFont(getFont());
        setPreferredSize(new java.awt.Dimension(555, 600));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoIncluir.setMnemonic('I');
        botaoIncluir.setText("Nova");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(560, 460));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 51))));

        labelCedente.setText("Razão Social");

        comboOrganizacao.setFont(getFont());
        comboOrganizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrganizacaoActionPerformed(evt);
            }
        });

        campoCodigo.setEditable(false);

        labelCedente1.setText("Fantasia");

        labelCedente2.setText("Sigla");

        labelCNPJ.setText("CNPJ");

        labelCNPJ1.setText("Insc. Estadual");

        labelInscM.setText("Insc. Municipal");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 102, 255), null), "Base de Dados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(415, 95));

        labelInscM1.setText("Endereço IP");

        labelInscM2.setText("Nome Servidor");

        labelInscM3.setText("Sistema Contabil");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFTIp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInscM1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFTNomeServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInscM2))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInscM3)
                    .addComponent(jFTVinculoContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelInscM2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTNomeServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelInscM3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTVinculoContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelInscM1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTIp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCedente)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCedente1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCedente2)
                            .addComponent(jTSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(comboOrganizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCNPJ)
                            .addComponent(jFTCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTInscEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCNPJ1))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelInscM)
                            .addComponent(jFTInscMunicipal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelCedente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboOrganizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCedente1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCedente2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCNPJ)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelInscM)
                            .addComponent(labelCNPJ1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTInscMunicipal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTInscEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro", jPanel5);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));

        labelLog.setText("Logradouro");

        labelCep.setText("Cep");

        labelNum.setText("Número");

        labelEst.setText("Estado");

        labelComp.setText("Complemento");

        labelBai.setText("Bairro");

        labelCid.setText("Cidade");

        comboEstado.setFont(new java.awt.Font("Arial", 0, 10));
        comboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEstadoItemStateChanged(evt);
            }
        });

        comboCidade.setFont(new java.awt.Font("Arial", 0, 10));
        comboCidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCidadeItemStateChanged(evt);
            }
        });

        comboBairro.setFont(new java.awt.Font("Arial", 0, 10));

        labelVersao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelVersao.setText("V");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelComp)
                        .addGap(462, 462, 462))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelLog)
                                    .addComponent(jTLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNum)
                                    .addComponent(jTNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTComplemento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(106, 106, 106))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelBai)
                        .addContainerGap(454, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboBairro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelEst))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCid)
                                    .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCep, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(355, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelLog)
                            .addComponent(labelNum))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelComp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEst)
                    .addComponent(labelCid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelBai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(labelVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jTabbedPane1.addTab("Endereço", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed


        botaoAlterar.setEnabled(false);
        botaoIncluir.setEnabled(true);
        labelVersao.setVisible(false);
        labelVersao.setText("1234");

        comboOrganizacao.setSelectedItem(null);
        jTSigla.setText("");
        jFTInscEstadual.setText("");
        jFTInscMunicipal.setText("");
        jFTCpnj.setValue(null);
        jTFantasia.setText("");
        campoCodigo.setText("");
        jTLogradouro.setText("");
        jTNumero.setText("");
        jTComplemento.setText("");
        jFTVinculoContabil.setText("");

        //jFTIp.setFormatterFactory(MaskUtils.mascaraIpAdress());

        jFTIp.setText("");

        jFTNomeServidor.setText("");
        jFTCep.setValue(null);
        comboBairro.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        comboCidade.setSelectedIndex(0);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (jFTCpnj.getText().isEmpty()
                || jFTCpnj.getText().equals("  .   .   /    -  ")) {
            jFTCpnj.requestFocus();
            return false;
        }

        if (jTSigla.getText().isEmpty()) {
            jTSigla.requestFocus();
            return false;
        }

        //campos obrigatorio em ENDERECO
        if (jTLogradouro.getText().isEmpty()) {
            jTLogradouro.requestFocus();
            return false;
        }

        if (jTNumero.getText().isEmpty()) {
            jTNumero.requestFocus();
            return false;
        }
        if (comboEstado.getSelectedIndex() == 0) {
            comboEstado.requestFocus();
            return false;
        }

        if (comboCidade.getSelectedIndex() == 0) {
            comboCidade.requestFocus();
            return false;
        }

        if (comboBairro.getSelectedIndex() == 0) {
            comboBairro.requestFocus();
            return false;
        }

        return true;

    }

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        String valorCombo = null;

        if (comboOrganizacao.getSelectedItem() != null) {
            valorCombo = comboOrganizacao.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCampos()) {

                    OrganizacaoModel tab = new OrganizacaoModel();

                    tab.setRazaoSocial(valorCombo);

                    tab.setFantasia(jTFantasia.getText());

                    tab.setCnpj(jFTCpnj.getText());

                    tab.setInscricaoEstadual(jFTInscEstadual.getText());

                    tab.setInscricaoMunicipal(jFTInscMunicipal.getText());

                    tab.setIpServidor(jFTIp.getText());

                    tab.setNomeServidor(jFTNomeServidor.getText().toUpperCase());

                    tab.setSigla(jTSigla.getText());

                    tab.setId(tab.getSigla() + PempecKeyGenerator.generateKeyOrganizacao(tab));

                    tab.setSistemaContabil(jFTVinculoContabil.getText());

                    tab.setSistemaContabil("NINF");

                    tab.setCodigo_web(organizacaoModel.getCodigo_web());

                    tab.setCodinome(organizacaoModel.getCodinome());

                    tab.setHost_ip(organizacaoModel.getHost_ip());

                    tab.setHost_name(organizacaoModel.getHost_name());

                    tab.setLicenca(organizacaoModel.getLicenca());

                    tab.setSocket_web(organizacaoModel.getSocket_web());

                    tab.setSerial_cliente(organizacaoModel.getSerial_cliente());

                    tab.setSerial_hd(organizacaoModel.getSerial_hd());

                    tab.setVersao(Controller.getOrganizacao().getVersao());

                    if (!jTLogradouro.getText().isEmpty()) {

                        tab.setLogradouro(jTLogradouro.getText());
                        tab.setNumero(jTNumero.getText());
                        tab.setCep(jFTCep.getText());
                        tab.setComplemento(jTComplemento.getText());

                        if (validarCombos("estado")) {
                            tab.setEstado(((EstadoModel) comboEstado.getSelectedItem()));
                        }
                        if (validarCombos("cidade")) {
                            tab.setCidade(((CidadeModel) comboCidade.getSelectedItem()));
                        }
                        if (validarCombos("bairro")) {
                            tab.setBairro(((BairroModel) comboBairro.getSelectedItem()));
                        }

                    }


//                    ContaContabilModel cct = new ContaContabilModel();
//                    cct.setPk(new PKModel());
//                    cct.getPk().setOrganizacao(tab);
//                    cct.getPk().setId("CAIXA");

                    //lista de LocalPagamento
                    listaLP = localPagamentoBO.obterTodos(organizacaoModel);
                    tab.setLLocalPG(listaLP);

                    //lista de centro de custos

                    CentroCustoModel cc = new CentroCustoModel();
                    cc.setPk(new PKModel());
                    cc.getPk().setOrganizacao(tab);
                    cc.getPk().setId(PempecKeyGenerator.generateKey());
                    cc.setCodigo(1);
                    cc.setDescricao("Administracao");
                    listaCentroCusto.add(cc);

                    cc = new CentroCustoModel();
                    cc.setPk(new PKModel());
                    cc.getPk().setOrganizacao(tab);
                    cc.getPk().setId(PempecKeyGenerator.generateKey());
                    cc.setCodigo(2);
                    cc.setDescricao("Veiculo");
                    listaCentroCusto.add(cc);

                    cc = new CentroCustoModel();
                    cc.setPk(new PKModel());
                    cc.getPk().setOrganizacao(tab);
                    cc.getPk().setId(PempecKeyGenerator.generateKey());
                    cc.setCodigo(3);
                    cc.setDescricao("Sede");
                    listaCentroCusto.add(cc);

                    cc = new CentroCustoModel();
                    cc.setPk(new PKModel());
                    cc.getPk().setOrganizacao(tab);
                    cc.getPk().setId(PempecKeyGenerator.generateKey());
                    cc.setCodigo(4);
                    cc.setDescricao("TI.Telefonia");
                    listaCentroCusto.add(cc);

                    cc = new CentroCustoModel();
                    cc.setPk(new PKModel());
                    cc.getPk().setOrganizacao(tab);
                    cc.getPk().setId(PempecKeyGenerator.generateKey());
                    cc.setCodigo(5);
                    cc.setDescricao("TI.Software");
                    listaCentroCusto.add(cc);

                    cc = new CentroCustoModel();
                    cc.setPk(new PKModel());
                    cc.getPk().setOrganizacao(tab);
                    cc.getPk().setId(PempecKeyGenerator.generateKey());
                    cc.setCodigo(6);
                    cc.setDescricao("TI.Hardware");
                    listaCentroCusto.add(cc);

                    tab.setLCentroCusto(listaCentroCusto);

                    //lista tipo cobranca
                    listaCobranca = tipoCobrancaBO.obterTodos(organizacaoModel);
                    tab.setLCobranca(listaCobranca);

                    //TIPO CEDENTE
                    listaTipoCedente = tipoCedenteBO.obterTodos(organizacaoModel);
                    tab.setLTipoCedente(listaTipoCedente);

                    //TIPO SACADO
                    listaTipoSacado = tipoSacadoBO.obterTodos(organizacaoModel);
                    tab.setLTipoSacado(listaTipoSacado);

                    //TIPO NF
                    listaTipoNF = tipoNotaFiscalBO.obterTodos(organizacaoModel);
                    tab.setLTipoNotaFiscal(listaTipoNF);

                    // TIPO STATUS
                    listaTipoStatus = tipoStatusBO.obterTodos(organizacaoModel);
                    tab.setLStatus(listaTipoStatus);


                    //TIPO OPERACAO BANCARIA
                    listaOperacaoBco = new ArrayList<TipoOperacaoBancariaModel>();

                    TipoOperacaoBancariaModel tpo = new TipoOperacaoBancariaModel();
                    tpo.setPk(new PKModel());
                    tpo.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_CONTAS);
                    tpo.getPk().setOrganizacao(tab);
                    tpo.setDescricao(Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_CONTAS);

                    listaOperacaoBco.add(tpo);

                    tpo = new TipoOperacaoBancariaModel();
                    tpo.setPk(new PKModel());
                    tpo.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_DEPOSITO_ESPECIE);
                    tpo.getPk().setOrganizacao(tab);
                    tpo.setDescricao(Constantes.TIPO_OPERACAO_BANCARIA_DEPOSITO_ESPECIE);

                    listaOperacaoBco.add(tpo);

                    tpo = new TipoOperacaoBancariaModel();
                    tpo.setPk(new PKModel());
                    tpo.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_DEPOSITO_CHEQUE);
                    tpo.getPk().setOrganizacao(tab);
                    tpo.setDescricao(Constantes.TIPO_OPERACAO_BANCARIA_DEPOSITO_CHEQUE);

                    listaOperacaoBco.add(tpo);

                    tpo = new TipoOperacaoBancariaModel();
                    tpo.setPk(new PKModel());
                    tpo.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_TED);
                    tpo.getPk().setOrganizacao(tab);
                    tpo.setDescricao(Constantes.TIPO_OPERACAO_BANCARIA_TED);

                    listaOperacaoBco.add(tpo);

                    tpo = new TipoOperacaoBancariaModel();
                    tpo.setPk(new PKModel());
                    tpo.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_DOC);
                    tpo.getPk().setOrganizacao(tab);
                    tpo.setDescricao(Constantes.TIPO_OPERACAO_BANCARIA_DOC);

                    listaOperacaoBco.add(tpo);

                    tpo = new TipoOperacaoBancariaModel();
                    tpo.setPk(new PKModel());
                    tpo.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_QUITACAO_NET);
                    tpo.getPk().setOrganizacao(tab);
                    tpo.setDescricao(Constantes.TIPO_OPERACAO_BANCARIA_QUITACAO_NET);

                    listaOperacaoBco.add(tpo);

                    tpo = new TipoOperacaoBancariaModel();
                    tpo.setPk(new PKModel());
                    tpo.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_TESOURARIA_DEPOSITO);
                    tpo.getPk().setOrganizacao(tab);
                    tpo.setDescricao(Constantes.TIPO_OPERACAO_BANCARIA_TESOURARIA_DEPOSITO);

                    listaOperacaoBco.add(tpo);

                    tpo = new TipoOperacaoBancariaModel();
                    tpo.setPk(new PKModel());
                    tpo.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_TESOURARIA);
                    tpo.getPk().setOrganizacao(tab);
                    tpo.setDescricao(Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_TESOURARIA);

                    listaOperacaoBco.add(tpo);

                    if (listaOperacaoBco.size() > 1) {

                        tab.setLOperacaoBancaria(listaOperacaoBco);

                    } else {

                        listaOperacaoBco = new ArrayList<TipoOperacaoBancariaModel>();
                    }


                    //FORMAS DE PAGAMENTO

                    listaFP = new ArrayList<FormaPagamentoModel>();

                    FormaPagamentoModel fp = new FormaPagamentoModel();
                    fp.setPk(new PKModel());
                    fp.getPk().setId(Constantes.FORMA_PAGAMENTO_CHEQUE);
                    fp.getPk().setOrganizacao(tab);
                    fp.setDescricao(Constantes.FORMA_PAGAMENTO_CHEQUE);

                    listaFP.add(fp);

                    fp = new FormaPagamentoModel();
                    fp.setPk(new PKModel());
                    fp.getPk().setId(Constantes.FORMA_PAGAMENTO_INTERNET);
                    fp.getPk().setOrganizacao(tab);
                    fp.setDescricao(Constantes.FORMA_PAGAMENTO_INTERNET);

                    listaFP.add(fp);

                    fp = new FormaPagamentoModel();
                    fp.setPk(new PKModel());
                    fp.getPk().setId(Constantes.FORMA_PAGAMENTO_ESPECIE);
                    fp.getPk().setOrganizacao(tab);
                    fp.setDescricao(Constantes.FORMA_PAGAMENTO_ESPECIE);
                    // fp.setContaContabil(cct);

                    listaFP.add(fp);

                    if (listaFP.size() > 1) {

                        tab.setLFormaPG(listaFP);

                    } else {

                        listaFP = new ArrayList<FormaPagamentoModel>();
                    }


                    // HISTORICO

                    listaHistorico = new ArrayList<HistoricoModel>();

                    HistoricoModel historico = new HistoricoModel();
                    historico.setPk(new PKModel());
                    historico.getPk().setId(Constantes.HISTORICO_TESOURARIA_CHEQUE_RECEBIDO);
                    historico.getPk().setOrganizacao(tab);
                    historico.setCodigo(0);
                    historico.setDescricao("Recebimento Cheque por Título");
                    historico.setTipo("R");
                    // historico.setContaContabil(cct);

                    listaHistorico.add(historico);

                    historico = new HistoricoModel();
                    historico.setPk(new PKModel());
                    historico.getPk().setId(Constantes.HISTORICO_TESOURARIA_ESPECIE_PAGO);
                    historico.getPk().setOrganizacao(tab);
                    historico.setCodigo(0);
                    historico.setDescricao("Pagto Espécie por Título");
                    historico.setTipo("P");
                    // historico.setContaContabil(cct);


                    listaHistorico.add(historico);

                    historico = new HistoricoModel();
                    historico.setPk(new PKModel());
                    historico.getPk().setId(Constantes.HISTORICO_TESOURARIA_ESPECIE_RECEBIDO);
                    historico.getPk().setOrganizacao(tab);
                    historico.setCodigo(0);
                    historico.setDescricao("Recebimento Espécie por Título");
                    historico.setTipo("R");
                    //historico.setContaContabil(cct);

                    listaHistorico.add(historico);

                    historico = new HistoricoModel();
                    historico.setPk(new PKModel());
                    historico.getPk().setId(Constantes.HISTORICO_TESOURARIA_DEPOSITO);
                    historico.getPk().setOrganizacao(tab);
                    historico.setCodigo(0);
                    historico.setDescricao("Depósito Tesouraria para Banco");
                    historico.setTipo("R");
                    // historico.setContaContabil(cct);

                    listaHistorico.add(historico);

                    historico = new HistoricoModel();
                    historico.setPk(new PKModel());
                    historico.getPk().setId(Constantes.HISTORICO_TESOURARIA_TRANSFERE_BCO_TESOURARIA);
                    historico.getPk().setOrganizacao(tab);
                    historico.setCodigo(0);
                    historico.setDescricao("TRANSFERE BANCO/TESOURARIA");
                    historico.setTipo("R");
                    //  historico.setContaContabil(cct);

                    listaHistorico.add(historico);


                    if (listaHistorico.size() > 1) {

                        tab.setLHistorico(listaHistorico);

                    } else {

                        listaHistorico = new ArrayList<HistoricoModel>();
                    }

                    /**
                     * Criando Cedente e Sacado com ID da Organização.
                     * Posteriormente será clonado os mesmo e funcionários
                     */
                    Collection<CedenteModel> listaCedente = new ArrayList<CedenteModel>();

                    CedenteModel cedenteModel = new CedenteModel();

                    cedenteModel.setPk(new PKModel());
                    cedenteModel.getPk().setId(tab.getId());
                    cedenteModel.getPk().setOrganizacao(tab);
                    cedenteModel.setNome(tab.getRazaoSocial());
                    cedenteModel.setCpfCnpj(tab.getCnpj());
                    cedenteModel.setPersonalidade("PJ");

                    listaCedente.add(cedenteModel);

                    tab.setLCedente(listaCedente);


                    Collection<SacadoModel> listaSacado = new ArrayList<SacadoModel>();

                    SacadoModel sacadoModel = new SacadoModel();

                    sacadoModel.setPk(new PKModel());
                    sacadoModel.getPk().setId(tab.getId());
                    sacadoModel.getPk().setOrganizacao(tab);
                    sacadoModel.setNome(tab.getRazaoSocial());
                    sacadoModel.setCpfCnpj(tab.getCnpj());
                    sacadoModel.setPersonalidade("PJ");

                    listaSacado.add(sacadoModel);

                    tab.setLSacado(listaSacado);

                    UsuarioModel usuarioModel = new UsuarioModel();
                    usuarioModel.setId(PempecParse.stringToLong(PempecKeyGenerator.generateIdUsuario()));
                    usuarioModel.setAtivo(1);
                    usuarioModel.setLogin(tab.getFantasia().toLowerCase());
                    usuarioModel.setSenha(MD5.criptografar(Constantes.SENHA_PADRAO));
                    usuarioModel.setNome(tab.getFantasia());
                    usuarioModel.setEhAdministrador(1);
                    usuarioModel.setOrganizacao(tab);

                    tab.setUsuarioPadrao(usuarioModel);

                    tab.setMovimentoDiarioModel(registroMovimento("Inserir Organização", valorCombo, tab.getFantasia(), null, "Inserido"));


                    if (Controller.getOrganizacao().getCodinome().equalsIgnoreCase("administrador")
                            || Controller.getUsuarioLogado().getLogin().equalsIgnoreCase("ranan")) {

                        organizacaoBO.inserir(tab);
                       // new ValidaLicencaFinance().genuineFinance();


                    } else {

                        exibeMensagemAviso("Você precisa ter autorização para criar nova Organização!", "Cadastro Nova");
                        //new ValidaLicencaFinance().genuineFinance();
                    }

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

            exibeMensagemAviso("Razão Social é obrigatório.", null);

        }


    }//GEN-LAST:event_botaoIncluirActionPerformed

private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

    String valorCombo = null;

    if (comboOrganizacao.getSelectedItem() != null) {
        valorCombo = comboOrganizacao.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }
            OrganizacaoModel tab = new OrganizacaoModel();

            tab.setId(campoCodigo.getText());

            tab.setCnpj(jFTCpnj.getText());

            tab.setFantasia(jTFantasia.getText());

            tab.setInscricaoEstadual(jFTInscEstadual.getText());

            tab.setInscricaoMunicipal(jFTInscMunicipal.getText());



            if (valorCombo.equalsIgnoreCase(razaoCadastro)) {

                tab.setRazaoSocial(valorCombo);

            } else {

                if (exibeMensagemConfirmacao("Você tem certeza que deseja alterar a razão social?", "Razao Social")) {

                    tab.setRazaoSocial(valorCombo);

                } else {

                    tab.setRazaoSocial(razaoCadastro);

                }
            }

            tab.setSigla(jTSigla.getText());

            tab.setIpServidor(jFTIp.getText());

            tab.setNomeServidor(jFTNomeServidor.getText().toUpperCase());

            tab.setSistemaContabil(jFTVinculoContabil.getText());

            tab.setCodigo_web(organizacaoModel.getCodigo_web());

            tab.setCodinome(organizacaoModel.getCodinome());

            tab.setHost_ip(organizacaoModel.getHost_ip());

            tab.setHost_name(organizacaoModel.getHost_name());

            tab.setLicenca(organizacaoModel.getLicenca());

            tab.setSocket_web(organizacaoModel.getSocket_web());

            tab.setSerial_cliente(organizacaoModel.getSerial_cliente());

            tab.setSerial_hd(organizacaoModel.getSerial_hd());

            tab.setVersao(labelVersao.getText());

            if (!jTLogradouro.getText().isEmpty()) {

                tab.setLogradouro(jTLogradouro.getText());
                tab.setNumero(jTNumero.getText());
                tab.setCep(jFTCep.getText());
                tab.setComplemento(jTComplemento.getText());

                if (validarCombos("estado")) {
                    tab.setEstado(((EstadoModel) comboEstado.getSelectedItem()));
                }
                if (validarCombos("cidade")) {
                    tab.setCidade(((CidadeModel) comboCidade.getSelectedItem()));
                }
                if (validarCombos("bairro")) {
                    tab.setBairro(((BairroModel) comboBairro.getSelectedItem()));
                }
            }

            tab.setMovimentoDiarioModel(registroMovimento("Alterar Organizacao", valorCombo, valorCombo + " " + tab.getCnpj(), null, "Alterado"));

            organizacaoBO.alterar(tab);

            if (tab.getIpServidor() != null && tab.getNomeServidor() != null) {

                Controller.setIpServidorBD(jFTIp.getText());

                Controller.setNameServidorBD(jFTNomeServidor.getText());

                if (tab.getId().equals(Controller.getOrganizacao().getId())) {

                    Controller.getOrganizacao().setIpServidor(jFTIp.getText());

                    Controller.getOrganizacao().setNomeServidor(jFTNomeServidor.getText());

                    Controller.getOrganizacao().setSistemaContabil(jFTVinculoContabil.getText());

                }

            }

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
        exibeMensagemAviso("Razão Social é campo obrigatório.", null);
    }

}//GEN-LAST:event_botaoAlterarActionPerformed

private void comboOrganizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrganizacaoActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboOrganizacao.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            OrganizacaoModel tab = new OrganizacaoModel();
            tab.setRazaoSocial(comboOrganizacao.getSelectedItem().toString());

            tab = organizacaoBO.consultarPorTemplate(tab);

            if (tab != null && tab.getId() != null) {

                razaoCadastro = tab.getRazaoSocial();

                botaoAlterar.setEnabled(true);

                botaoIncluir.setEnabled(false);

                campoCodigo.setText(tab.getId());

                jFTCpnj.setText(tab.getCnpj());

                jTFantasia.setText(tab.getFantasia());

                jTSigla.setText(tab.getSigla());

                jFTInscEstadual.setText(tab.getInscricaoEstadual());

                jFTInscMunicipal.setText(tab.getInscricaoMunicipal());

                jFTVinculoContabil.setText(tab.getSistemaContabil());

                if (tab.getVersao() != null) {
                    labelVersao.setText(tab.getVersao());
                } else {

                    labelVersao.setText("HEXA123");

                }


                if (tab.getIpServidor() != null) {
                    jFTIp.setText(tab.getIpServidor());
                } else {

                    jFTIp.setText("0.0.0.0");
                }

                if (tab.getNomeServidor() != null) {

                    jFTNomeServidor.setText(tab.getNomeServidor().toUpperCase());

                } else {

                    jFTNomeServidor.setText("SEM SVD BD");

                }
                if (tab.getLogradouro() != null) {

                    jTLogradouro.setText(tab.getLogradouro());

                    jTNumero.setText(tab.getNumero());

                    jFTCep.setText(tab.getCep());

                    jTComplemento.setText(tab.getComplemento());
                }

                if (tab.getEstado() != null && !tab.getEstado().getId().isEmpty()) {

                    for (int i = 1; i < comboEstado.getItemCount(); i++) {
                        if (tab.getEstado().getId().equalsIgnoreCase(((EstadoModel) comboEstado.getItemAt(i)).getId())) {
                            comboEstado.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getCidade() != null && !tab.getCidade().getId().isEmpty()) {

                    for (int i = 1; i < comboCidade.getItemCount(); i++) {
                        if (tab.getCidade().getId().equalsIgnoreCase(((CidadeModel) comboCidade.getItemAt(i)).getId())) {
                            comboCidade.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getBairro() != null && !tab.getBairro().getId().isEmpty()) {

                    for (int i = 1; i < comboBairro.getItemCount(); i++) {
                        if (tab.getBairro().getId().equalsIgnoreCase(((BairroModel) comboBairro.getItemAt(i)).getId())) {
                            comboBairro.setSelectedIndex(i);
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


}//GEN-LAST:event_comboOrganizacaoActionPerformed

private void comboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEstadoItemStateChanged

    if (comboEstado.getSelectedItem() != null && ((EstadoModel) comboEstado.getSelectedItem()).getId() != null) {
        comboCidade.setEnabled(true);

        try {

            Collection<CidadeModel> lCidade = new ArrayList<CidadeModel>();

            CidadeModel cidadeModel = new CidadeModel();

            cidadeModel.setDescricao(" -> Selecione <- ");

            lCidade.add(cidadeModel);

            lCidade.addAll(cidadeBO.obterPorEstado((EstadoModel) comboEstado.getSelectedItem()));

            comboCidade.setModel(new javax.swing.DefaultComboBoxModel(lCidade.toArray()));

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

        Collection<CidadeModel> lCidade = new ArrayList<CidadeModel>();

        CidadeModel cidadeModel = new CidadeModel();

        cidadeModel.setDescricao(" -> Selecione <- ");

        lCidade.add(cidadeModel);

        comboCidade.setModel(new javax.swing.DefaultComboBoxModel(lCidade.toArray()));

        Collection<BairroModel> lBairro = new ArrayList<BairroModel>();

        BairroModel bairroModel = new BairroModel();

        bairroModel.setDescricao(" -> Selecione <- ");

        lBairro.add(bairroModel);

        comboBairro.setModel(new javax.swing.DefaultComboBoxModel(lBairro.toArray()));

    }

}//GEN-LAST:event_comboEstadoItemStateChanged

private void comboCidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCidadeItemStateChanged

    if (comboCidade.getSelectedItem() != null && ((CidadeModel) comboCidade.getSelectedItem()).getId() != null) {
        comboBairro.setEnabled(true);
        try {

            Collection<BairroModel> lBairro = new ArrayList<BairroModel>();

            BairroModel bairroModel = new BairroModel();

            bairroModel.setDescricao(" -> Selecione <- ");

            lBairro.add(bairroModel);

            lBairro.addAll(bairroBO.obterPorCidade((CidadeModel) comboCidade.getSelectedItem()));

            comboBairro.setModel(new javax.swing.DefaultComboBoxModel(lBairro.toArray()));

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

        Collection<BairroModel> lBairro = new ArrayList<BairroModel>();

        BairroModel bairroModel = new BairroModel();

        bairroModel.setDescricao(" -> Selecione <- ");

        lBairro.add(bairroModel);

        comboBairro.setModel(new javax.swing.DefaultComboBoxModel(lBairro.toArray()));

    }
}//GEN-LAST:event_comboCidadeItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JComboBox comboBairro;
    private javax.swing.JComboBox comboCidade;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JComboBox comboOrganizacao;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JFormattedTextField jFTCep;
    private javax.swing.JFormattedTextField jFTCpnj;
    private javax.swing.JFormattedTextField jFTInscEstadual;
    private javax.swing.JFormattedTextField jFTInscMunicipal;
    private javax.swing.JFormattedTextField jFTIp;
    private javax.swing.JFormattedTextField jFTNomeServidor;
    private javax.swing.JFormattedTextField jFTVinculoContabil;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTComplemento;
    private javax.swing.JTextField jTFantasia;
    private javax.swing.JTextField jTLogradouro;
    private javax.swing.JTextField jTNumero;
    private javax.swing.JTextField jTSigla;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelBai;
    private javax.swing.JLabel labelCNPJ;
    private javax.swing.JLabel labelCNPJ1;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCedente1;
    private javax.swing.JLabel labelCedente2;
    private javax.swing.JLabel labelCep;
    private javax.swing.JLabel labelCid;
    private javax.swing.JLabel labelComp;
    private javax.swing.JLabel labelEst;
    private javax.swing.JLabel labelInscM;
    private javax.swing.JLabel labelInscM1;
    private javax.swing.JLabel labelInscM2;
    private javax.swing.JLabel labelInscM3;
    private javax.swing.JLabel labelLog;
    private javax.swing.JLabel labelNum;
    private javax.swing.JLabel labelVersao;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;

    private boolean validarCombos(String combo) {
        boolean valida = false;

        if (combo.equalsIgnoreCase("bairro")) {
            if (comboBairro.getSelectedItem() != null) {
                if (!((BairroModel) comboBairro.getSelectedItem()).getId().isEmpty()) {
                    valida = true;
                }
            }
        }

        if (combo.equalsIgnoreCase("cidade")) {
            if (comboCidade.getSelectedItem() != null) {
                if (!((CidadeModel) comboCidade.getSelectedItem()).getId().isEmpty()) {
                    valida = true;
                }
            }
        }

        if (combo.equalsIgnoreCase("estado")) {
            if (comboEstado.getSelectedItem() != null) {
                if (!((EstadoModel) comboEstado.getSelectedItem()).getId().isEmpty()) {
                    valida = true;
                }
            }
        }

        return valida;

    }
}
