package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.ParticionadorTituloPagarModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.TituloPagarTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.Collection;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.SwingUtilities;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author PEMPEC
 */
public class ParticionadorTituloPagar extends FinanceInternalFrame implements IRepopulador {

    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private long tela = Tela.TELA_CONTAS_A_PAGAR_PARTICIONAMENTO.getTela();

    private String NameObject() {
        return (String) "Particionar Título a Pagar";
    }

    public ParticionadorTituloPagar() throws SystemException {

        initComponents();

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);
        botaoPagar.setVisible(false);

        //Aplicando mascara em campos.
        //Ver como tratar a questão de CPF ou CNPJ de sacado
        jFTDataVencimentoNovoTitulo.setDate(Controller.getDataServidorBD());

    }

    public void repopularCombos() {

        try {


            Collection<TituloPagarModel> lColecao = tituloPagarBO.obterTodosAPagar(organizacaoModel);

            final EventList<TituloPagarModel> lRegistros = GlazedLists.eventList(lColecao);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboTitulo, lRegistros, new TituloPagarTextFilterator());
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBotoes = new javax.swing.JPanel();
        botaoParticionar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoPagar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        campoCodigo = new javax.swing.JTextField();
        labelCedente = new javax.swing.JLabel();
        labelNumeroDocumento = new javax.swing.JLabel();
        labelValorPagar = new javax.swing.JLabel();
        labelDataVencimento = new javax.swing.JLabel();
        labelParcela = new javax.swing.JLabel();
        comboTitulo = new javax.swing.JComboBox();
        labelDescricao = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelDataNovoVencimento = new javax.swing.JLabel();
        jFTValorPagarNovoTitulo = new br.com.pempec.componentes.JDoubleField();
        labelDataEmissao1 = new javax.swing.JLabel();
        jFTDataVencimentoNovoTitulo = new org.jdesktop.swingx.JXDatePicker();
        labelDataRegistro = new javax.swing.JLabel();
        labelDataEmissao = new javax.swing.JLabel();
        labelDataProtocolo = new javax.swing.JLabel();
        labelTituloGerador = new javax.swing.JLabel();
        labelValorAntecipado = new javax.swing.JLabel();
        labelObservacao = new javax.swing.JLabel();
        labelMoeda = new javax.swing.JLabel();
        labelResponsavel = new javax.swing.JLabel();
        labelTipoCobranca = new javax.swing.JLabel();
        labelLocalPagamento = new javax.swing.JLabel();
        labelSituacaoTitulo = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - PARTICIONAMENTO DE TÍTULO A PAGAR");
        setPreferredSize(new java.awt.Dimension(900, 530));

        jPanelBotoes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoParticionar.setMnemonic('I');
        botaoParticionar.setText("Particionar");
        botaoParticionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoParticionarActionPerformed(evt);
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

        botaoPagar.setMnemonic('B');
        botaoPagar.setText("Baixar");
        botaoPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotoesLayout = new javax.swing.GroupLayout(jPanelBotoes);
        jPanelBotoes.setLayout(jPanelBotoesLayout);
        jPanelBotoesLayout.setHorizontalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoParticionar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBotoesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanelBotoesLayout.setVerticalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoParticionar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 153, 0)), NameObject()));

        campoCodigo.setEditable(false);

        labelCedente.setFont(new java.awt.Font("Arial", 0, 10));
        labelCedente.setForeground(new java.awt.Color(0, 102, 102));
        labelCedente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cedente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelNumeroDocumento.setText("Número do Documento");

        labelValorPagar.setForeground(new java.awt.Color(204, 0, 0));
        labelValorPagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelValorPagar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor a Pagar"));

        labelDataVencimento.setForeground(new java.awt.Color(204, 0, 0));
        labelDataVencimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataVencimento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Vencimento"));

        labelParcela.setForeground(new java.awt.Color(0, 102, 102));
        labelParcela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelParcela.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Parcela"));

        comboTitulo.setToolTipText("");
        comboTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloActionPerformed(evt);
            }
        });

        labelDescricao.setFont(new java.awt.Font("Arial", 0, 10));
        labelDescricao.setForeground(new java.awt.Color(0, 102, 102));
        labelDescricao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descricao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2), "Dados do Novo Título"));

        labelDataNovoVencimento.setText("Data Vencimento");

        jFTValorPagarNovoTitulo.setForeground(new java.awt.Color(102, 102, 102));
        jFTValorPagarNovoTitulo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFTValorPagarNovoTitulo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTValorPagarNovoTituloFocusLost(evt);
            }
        });

        labelDataEmissao1.setText("Valor a Pagar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDataNovoVencimento)
                    .addComponent(jFTDataVencimentoNovoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDataEmissao1)
                    .addComponent(jFTValorPagarNovoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataNovoVencimento)
                    .addComponent(labelDataEmissao1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTValorPagarNovoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTDataVencimentoNovoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        labelDataRegistro.setForeground(new java.awt.Color(0, 102, 102));
        labelDataRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataRegistro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Registro"));

        labelDataEmissao.setForeground(new java.awt.Color(0, 102, 102));
        labelDataEmissao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataEmissao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Emissão"));

        labelDataProtocolo.setForeground(new java.awt.Color(0, 102, 102));
        labelDataProtocolo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataProtocolo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Recepção"));

        labelTituloGerador.setFont(new java.awt.Font("Arial", 0, 10));
        labelTituloGerador.setForeground(new java.awt.Color(0, 102, 102));
        labelTituloGerador.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Título Vinculado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelValorAntecipado.setForeground(new java.awt.Color(0, 0, 153));
        labelValorAntecipado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelValorAntecipado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor Antecipado"));

        labelObservacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelObservacao.setForeground(new java.awt.Color(0, 102, 102));
        labelObservacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelMoeda.setForeground(new java.awt.Color(0, 102, 102));
        labelMoeda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMoeda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Moeda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelResponsavel.setFont(new java.awt.Font("Arial", 0, 10));
        labelResponsavel.setForeground(new java.awt.Color(0, 102, 102));
        labelResponsavel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Responsável", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelTipoCobranca.setFont(new java.awt.Font("Arial", 0, 10));
        labelTipoCobranca.setForeground(new java.awt.Color(0, 102, 102));
        labelTipoCobranca.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo Cobrança", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelLocalPagamento.setFont(new java.awt.Font("Arial", 0, 10));
        labelLocalPagamento.setForeground(new java.awt.Color(0, 102, 102));
        labelLocalPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Local Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelSituacaoTitulo.setBackground(new java.awt.Color(222, 218, 210));
        labelSituacaoTitulo.setFont(new java.awt.Font("Arial", 0, 10));
        labelSituacaoTitulo.setForeground(new java.awt.Color(0, 102, 102));
        labelSituacaoTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSituacaoTitulo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNumeroDocumento)
                    .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelValorAntecipado, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelTituloGerador, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelTipoCobranca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelObservacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDescricao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLocalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSituacaoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSituacaoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelValorAntecipado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTituloGerador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDataProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTipoCobranca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLocalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNumeroDocumento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );

        jPanel3.getAccessibleContext().setAccessibleName("tituloNovo");

        jTabbedPane1.addTab("Dados Básicos", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        botaoParticionar.setEnabled(true);
        campoCodigo.setText("");
        comboTitulo.setSelectedItem(null);
        labelSituacaoTitulo.setText("");
        labelCedente.setText("");
        labelDataNovoVencimento.setText("");
        labelDataVencimento.setText("");
        labelDescricao.setText("");
        labelParcela.setText("");
        labelValorPagar.setText("");
        jFTDataVencimentoNovoTitulo.setDate(Controller.getDataServidorBD());
        jFTValorPagarNovoTitulo.setText("0,00");
        labelValorAntecipado.setText("0,00");
        labelDataEmissao.setText("");
        labelDataProtocolo.setText("");
        labelDataRegistro.setText("");
        labelTituloGerador.setText("");
        labelObservacao.setText("");
        labelTipoCobranca.setText("");
        labelMoeda.setText("");
        labelResponsavel.setText("");
        labelLocalPagamento.setText("");

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (jFTValorPagarNovoTitulo.getValue() == 0) {
            jFTValorPagarNovoTitulo.requestFocus();
            return false;
        }

        //Validando Datas.

        if (jFTDataVencimentoNovoTitulo.getDate() == null) {
            jFTDataVencimentoNovoTitulo.requestFocus();
            return false;
        }

        if (!PempecUtil.validaPreenchimentoNumero(comboTitulo.getSelectedItem().toString())) {
            exibeMensagemAviso("O título só pode conter número.", null);
            comboTitulo.requestFocus();
            return false;
        }

        return true;

    }

    private void botaoParticionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoParticionarActionPerformed

        String valorCombo = null;

        if (comboTitulo.getSelectedItem() != null) {
            valorCombo = comboTitulo.getSelectedItem().toString();
        }

        if (Controller.verificaParametroAtivo(Parametro.CCADP001.getCodigo())) {
            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCADP001.getCodigo()), null);
            return;
        }

        try {

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {
                TituloPagarModel tab = new TituloPagarModel();

                ParticionadorTituloPagarModel particionadorTituloPagar = new ParticionadorTituloPagarModel();

                particionadorTituloPagar.setUsuario(Controller.getUsuarioLogado());

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab.setNumeroDocumento(valorCombo);

                tab = tituloPagarBO.consultarPorTemplateAPagar(tab);

                if (tab.getLoteContabil() == null) {


                    TituloPagarModel clone = null;


                    try {

                        clone = (TituloPagarModel) BeanUtils.cloneBean(tab);

                    } catch (final Exception ex) {

                        final File file = PrintScreen.capture();

                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {

                                tratamentoExcecao(new SystemException(ex), file);

                            }
                        });

                    }

                    clone.setPk(new PKModel());

                    clone.getPk().setOrganizacao(tab.getPk().getOrganizacao());

                    clone.getPk().setId(PempecKeyGenerator.generateKey());

                    clone.setCodigoBarras(null);

                    clone.setNotaFiscal(null);

                    clone.setDataEmissao(Controller.getDataServidorBD());

                    clone.setDataPagamento(null);

                    clone.setObservacao("Título Desmembrado do Título :" + tab.getNumeroDocumento());

                    clone.setUsuario(Controller.getUsuarioLogado());

                    clone.setDataVencimento(jFTDataVencimentoNovoTitulo.getDate());

                    clone.setValorNominal(jFTValorPagarNovoTitulo.getValue());

                    if (tab.getCollCentroCustosRateio() == null || tab.getCollCentroCustosRateio().size() == 0) {

                        clone.setCollCentroCustosRateio(null);

                    } else {

                        Set<TituloPagarRateioCCModel> collRateio = tab.getCollCentroCustosRateio();
                        for (TituloPagarRateioCCModel rateios : tab.getCollCentroCustosRateio()) {
                            collRateio.add(rateios);

                        }

                        clone.setCollCentroCustosRateio(collRateio);
                    }



                    clone.setTituloAnterior(tab);

                    if (clone.getParcela().contains("/")) {

                        String[] auxParcela = clone.getParcela().split("/");

                        if (auxParcela.length > 2) {

                            Integer novoNumero = PempecParse.stringToInteger(auxParcela[0]);

                            clone.setParcela(++novoNumero + "/" + auxParcela[1].trim() + "/" + auxParcela[2].trim());

                        } else {

                            clone.setParcela("1/" + clone.getParcela());

                        }

                    } else {

                        clone.setParcela("1/" + clone.getParcela());

                    }

                    //Reajustando o Novo numero do Documento
                    if (clone.getParcela().contains("/")) {

                        StringTokenizer tokenizer = new StringTokenizer(clone.getParcela(), "/");

                        StringBuilder numeroDoc = new StringBuilder();

                        numeroDoc.append(clone.getNumeroDocumento());
                        numeroDoc.append("00");

                        while (tokenizer.hasMoreTokens()) {
                            numeroDoc.append(tokenizer.nextToken().trim());
                        }

                        clone.setNumeroDocumento(numeroDoc.toString().trim());

                    } else {
                        String[] auxParcela = clone.getParcela().split("/");

                        clone.setNumeroDocumento(clone.getNumeroDocumento() + "00" + auxParcela[0]);
                    }

                    TituloPagarModel tituloDuplicidade = tituloPagarBO.consultarPorTemplate(clone);

                    if (tituloDuplicidade != null && !tituloDuplicidade.getPk().getId().isEmpty()) {

                        String[] auxParcela = clone.getParcela().split("/");

                        clone.setNumeroDocumento(clone.getNumeroDocumento() + "0" + auxParcela[0]);
                    }


                    clone.setUsuario(particionadorTituloPagar.getUsuario());

                    clone.setMovimentoDiarioModel(registroMovimento("Particionar TP", tab.getNumeroDocumento(), "Título Particionado : " + tab.getNumeroDocumento(), clone.getValorNominal(), "Particionado"));
                    clone.getMovimentoDiarioModel().setObservacao("Novo Título -> " + clone.getNumeroDocumento());

                    Double valorAnterior = tab.getValorNominal();

                    tab.setValorNominal(valorAnterior - clone.getValorNominal());

                    if (tituloPagarBO.consultarPorTemplateAPagar(clone) != null) {
                        String novoNumero = PempecKeyGenerator.generateNumeroDocumentoTituloPagar();
                        clone.setNumeroDocumento(novoNumero);

                    }

                    particionadorTituloPagar.setTituloNovo(clone);
                    particionadorTituloPagar.setTituloAnterior(tab);


                    tituloPagarBO.particionar(particionadorTituloPagar);

                    this.botaoLimparActionPerformed(evt);

                } else {

                    exibeMensagemAviso("O título não pode ser particionado. Lote enviado :" + tab.getLoteContabil(), null);

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


}//GEN-LAST:event_botaoParticionarActionPerformed

private void botaoPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPagarActionPerformed
//GEN-LAST:event_botaoPagarActionPerformed
    }

private void comboTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTitulo.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            TituloPagarModel tab = new TituloPagarModel();
            //o valor do combo nao eh descricao.

            tab.setNumeroDocumento(comboTitulo.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = tituloPagarBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                campoCodigo.setText(tab.getPk().getId());

                if (tab.getCedente() != null) {

                    labelCedente.setText(tab.getCedente().getNome());

                }

                if (tab.getHistorico() != null) {

                    labelDescricao.setText(tab.getHistorico().getDescricao().toUpperCase() + " " + tab.getDescricao().toUpperCase());

                }

                if (tab.getDataVencimento() != null) {

                    labelDataVencimento.setText(PempecParse.dateToString(tab.getDataVencimento()));

                }

                if (tab.getDataRegistro() != null) {

                    labelDataRegistro.setText(PempecParse.dateToString(tab.getDataRegistro()));

                }

                if (tab.getDataProtocolo() != null) {

                    labelDataProtocolo.setText(PempecParse.dateToString(tab.getDataProtocolo()));

                }

                if (tab.getDataEmissao() != null) {

                    labelDataEmissao.setText(PempecParse.dateToString(tab.getDataEmissao()));

                }

                if (tab.getStatus() != null) {

                    labelSituacaoTitulo.setText(tab.getStatus().getDescricao());

                }

                if (tab.getParcela() != null) {

                    labelParcela.setText(tab.getParcela());
                }

                if (tab.getValorNominal() != null) {

                    labelValorPagar.setText(PempecParse.doubleToZero(tab.getValorNominal()));
                }

                if (tab.getValorAntecipado() != null) {

                    labelValorAntecipado.setText(PempecParse.doubleToZero(tab.getValorAntecipado()));

                }

                if (tab.getTituloAnterior() != null) {

                    labelTituloGerador.setText(tab.getTituloAnterior().getNumeroDocumento());
                } else {

                    labelTituloGerador.setText("Sem vínculo.");

                }

                if (tab.getObservacao() != null) {

                    labelObservacao.setText(tab.getObservacao().toUpperCase());
                }

                if (tab.getMoeda() != null) {

                    labelMoeda.setText(tab.getMoeda());
                }

                if (tab.getResponsavel() != null) {

                    labelResponsavel.setText(tab.getResponsavel().getNome().toUpperCase());
                }

                if (tab.getTipoCobranca() != null) {

                    labelTipoCobranca.setText(tab.getTipoCobranca().getDescricao().toUpperCase());
                }

                if (tab.getLocalPagamento() != null) {

                    labelLocalPagamento.setText(tab.getLocalPagamento().getDescricao().toUpperCase());

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

}//GEN-LAST:event_comboTituloActionPerformed

private void jFTValorPagarNovoTituloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTValorPagarNovoTituloFocusLost
    Double valorPagar = PempecParse.stringToDouble(labelValorPagar.getText());
    Double valorNovo = PempecParse.stringToDouble(jFTValorPagarNovoTitulo.getText());

    if (valorPagar == 0) {
        botaoParticionar.setEnabled(false);
        exibeMensagemAviso("O título não pode ser particionado porque o valor a pagar é insuficiente.", null);
        return;
    }

    if ((valorPagar > 0)) {
        if ((valorNovo > (valorPagar * (.80)))) {
            exibeMensagemAviso("O título só pode ser desmembrado em até 80% do seu valor.\n O valor máximo permitido é  R$ " + Math.round(valorPagar * (.79)), null);
            return;
        }

        if (valorNovo > valorPagar) {
            exibeMensagemAviso("O valor do título desmembrado deve ser menor que o  título antigo.", null);
            return;
        }
    }
}//GEN-LAST:event_jFTValorPagarNovoTituloFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoPagar;
    private javax.swing.JButton botaoParticionar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JComboBox comboTitulo;
    private org.jdesktop.swingx.JXDatePicker jFTDataVencimentoNovoTitulo;
    private br.com.pempec.componentes.JDoubleField jFTValorPagarNovoTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelDataEmissao;
    private javax.swing.JLabel labelDataEmissao1;
    private javax.swing.JLabel labelDataNovoVencimento;
    private javax.swing.JLabel labelDataProtocolo;
    private javax.swing.JLabel labelDataRegistro;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelLocalPagamento;
    private javax.swing.JLabel labelMoeda;
    private javax.swing.JLabel labelNumeroDocumento;
    private javax.swing.JLabel labelObservacao;
    private javax.swing.JLabel labelParcela;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelSituacaoTitulo;
    private javax.swing.JLabel labelTipoCobranca;
    private javax.swing.JLabel labelTituloGerador;
    private javax.swing.JLabel labelValorAntecipado;
    private javax.swing.JLabel labelValorPagar;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
}
