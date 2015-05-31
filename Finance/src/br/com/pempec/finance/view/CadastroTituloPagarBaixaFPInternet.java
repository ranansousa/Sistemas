package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BancoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.utils.ContaBancariaServices;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopuladorNew;
import br.com.pempec.finance.utils.MaskUtils;
import br.com.pempec.finance.utils.Parametro;
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
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroTituloPagarBaixaFPInternet extends FinanceInternalFrame implements IRepopuladorNew {

    private CadastroTituloPagarBaixa baixa;
    private TituloPagarModel titulo;
    private TituloPagarBaixaFormaPagamentoModel formaPagamento;
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private BancoBO bancoBO = new BancoBO();
    private TipoOperacaoBancariaBO operacaoBancariaBO = new TipoOperacaoBancariaBO();

    private String NameObject() {
        return (String) "Pagamento Via Bank-Line";
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

                case TELA_PARAMETROS_BANCOS:

                    Collection<BancoModel> lBanco = bancoBO.obterTodos();

                    //comboBanco
                    EventList<BancoModel> lRegBancos = GlazedLists.eventList(lBanco);
                    if (supportBanco != null && supportBanco.getItemList() != null && supportBanco.getComboBox() != null) {
                        supportBanco.uninstall();
                    }
                    supportBanco = AutoCompleteSupport.install(comboBancoDestino, lRegBancos, new BancoTextFilterator());
                    supportBanco.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportBanco.setStrict(false);

                    BancoModel bancoModel = (BancoModel) object;

                    if (bancoModel != null) {

                        for (int i = 0; i < comboBancoDestino.getItemCount(); i++) {
                            if (bancoModel.getId().equalsIgnoreCase(((BancoModel) comboBancoDestino.getItemAt(i)).getId())) {
                                comboBancoDestino.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                default:

                    lTipoOperacao = new ArrayList<TipoOperacaoBancariaModel>();
                    lColecao = new ArrayList<ContaBancariaModel>();
                    lBanco = bancoBO.obterTodos();

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

                    //comboBanco
                    lRegBancos = GlazedLists.eventList(lBanco);
                    if (supportBanco != null && supportBanco.getItemList() != null && supportBanco.getComboBox() != null) {
                        supportBanco.uninstall();
                    }
                    supportBanco = AutoCompleteSupport.install(comboBancoDestino, lRegBancos, new BancoTextFilterator());
                    supportBanco.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportBanco.setStrict(false);

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

        if (comboPersonalidade.getSelectedIndex() == 0) {
            comboPersonalidade.requestFocus();
            return false;
        }

        if (jFTValorOperacao.getText().equals("0,00")) {
            jFTValorOperacao.requestFocus();
            return false;
        }

        if (jTAgenciaDestino.getText().isEmpty()) {
            jTAgenciaDestino.requestFocus();
            return false;
        }

        if (jTContaDestino.getText().isEmpty()) {
            jTContaDestino.requestFocus();
            return false;
        }

        if (jTCorrentista.getText().isEmpty()) {
            jTCorrentista.requestFocus();
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

        if (comboBancoDestino.getSelectedItem() == null) {
            comboBancoDestino.requestFocus();
            return false;
        }

        if (jFTDataOperacao.getDate() == null && !jFTDataOperacao.getDate().equals(baixa.jFTDataPagamento.getDate())) {
            jFTDataOperacao.requestFocus();
            return false;
        }

        if (!PempecUtil.validaPreenchimentoNumero(comboContaBancaria.getSelectedItem().toString())) {
            exibeMensagemAviso("A conta Bancária só pode conter números.", null);
            comboContaBancaria.requestFocus();
            return false;
        }

        return true;

    }

    public CadastroTituloPagarBaixaFPInternet(CadastroTituloPagarBaixa baixa, TituloPagarModel titulo, TituloPagarBaixaFormaPagamentoModel formaPagamento, String tipoPagamento) throws SystemException {

        this.baixa = baixa;

        this.titulo = titulo;

        this.formaPagamento = formaPagamento;

        CedenteModel cedente = titulo.getCedente();
        cedente.setAgencia(titulo.getCedente().getAgencia());
        cedente.setConta(titulo.getCedente().getConta());
        cedente.setBanco(titulo.getCedente().getBanco());

        String tipo = tipoPagamento;

        initComponents();

        comboPersonalidade.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" -> Selecione <- ",
            "PF", "PJ"
        }));

        this.repopularCombos(Tela.TELA_PRINCIPAL, null);

        campoCodigoFPInternet.setVisible(false);

        //Aplicando tamanho maximo de caracteres do campo.
        jTDetalhamento.setDocument(new Documento(60));
        jTCorrentista.setDocument(new Documento(60));
        jFTCpfCpnj.setDocument(new Documento(20));
        jTAgenciaDestino.setDocument(new Documento(10));
        jTContaDestino.setDocument(new Documento(20));

        //formatar datas   

        jFTCpfCpnj.setVisible(false);
        labelCpfCnpj.setVisible(false);

        //carregando dados existentes
        jTCorrentista.setText(cedente.getNome().toUpperCase());
        comboPersonalidade.setSelectedItem(cedente.getPersonalidade().toString());
        jFTCpfCpnj.setText(cedente.getCpfCnpj());

        campoCodigoFPInternet.setText(PempecKeyGenerator.generateKey());

        if (baixa.jFTDataPagamento.getDate() != null) {

            jFTDataOperacao.setDate(baixa.jFTDataPagamento.getDate());

        }

        if (formaPagamento.getValor() != null) {
            jFTValorOperacao.setText(PempecParse.doubleToZero(formaPagamento.getValor()));
        }

        if (cedente.getBanco() != null) {

            for (int i = 1; i < comboBancoDestino.getItemCount(); i++) {
                if (cedente.getBanco().getId().equalsIgnoreCase(((BancoModel) comboBancoDestino.getItemAt(i)).getId())) {
                    comboBancoDestino.setSelectedIndex(i);
                    break;
                }
            }

            jTAgenciaDestino.setText(cedente.getAgencia());
            jTContaDestino.setText(cedente.getConta());
        }

        jTDetalhamento.setText("PG " + tipo.toString().toUpperCase() + " PCLA ." + titulo.getParcela() + " TÍT. " + titulo.getNumeroDocumento() + " " + titulo.getDescricao());

        if (tipo.equalsIgnoreCase("total")) {

            Double valorTitulo = PempecParse.stringToDouble(baixa.jFTValorPagar.getText());
            Double valorPago = PempecParse.stringToDouble(baixa.jFTValorPago.getText());
            Double total = valorTitulo - valorPago;
            jFTValorOperacao.setText(PempecParse.doubleToZero(total));
        }

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelConta = new javax.swing.JLabel();
        comboContaBancaria = new javax.swing.JComboBox();
        jTCorrentista = new javax.swing.JTextField();
        labelValorPagar = new javax.swing.JLabel();
        jFTValorOperacao = new br.com.pempec.componentes.JDoubleField();
        comboTipoOperacaoBancaria = new javax.swing.JComboBox();
        labelOperacaoBancaria = new javax.swing.JLabel();
        labelDataEmissao = new javax.swing.JLabel();
        labelPortador = new javax.swing.JLabel();
        labelOperacaoBancaria1 = new javax.swing.JLabel();
        comboBancoDestino = new javax.swing.JComboBox();
        labelObs1 = new javax.swing.JLabel();
        jTAgenciaDestino = new javax.swing.JTextField();
        labelCpfCnpj = new javax.swing.JLabel();
        labelPortador1 = new javax.swing.JLabel();
        jTDetalhamento = new javax.swing.JTextField();
        labelObs2 = new javax.swing.JLabel();
        jTContaDestino = new javax.swing.JTextField();
        labelTipoSacado1 = new javax.swing.JLabel();
        comboPersonalidade = new javax.swing.JComboBox();
        jFTCpfCpnj = new javax.swing.JFormattedTextField();
        campoCodigoFPInternet = new javax.swing.JTextField();
        jFTDataOperacao = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        botaoSalvar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();

        setClosable(true);
        setTitle("PEMPEC ENTERPRISE - Finance -  Pagamento Via Internet");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        labelConta.setText("Conta Origem");

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        comboContaBancaria.setForeground(new java.awt.Color(255, 153, 0));

        labelValorPagar.setText("Valor");

        jFTValorOperacao.setEditable(false);
        jFTValorOperacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTValorOperacaoFocusLost(evt);
            }
        });

        labelOperacaoBancaria.setText("Operação Bancária");

        labelDataEmissao.setText("Data da Operação");

        labelPortador.setText("Correntista");

        labelOperacaoBancaria1.setText("Banco Destino");

        labelObs1.setText("Agência Destino");

        labelCpfCnpj.setText("CPF");

        labelPortador1.setText("Detalhamento");

        jTDetalhamento.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        labelObs2.setText("Conta Destino");

        labelTipoSacado1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelTipoSacado1.setText("Personalidade");

        comboPersonalidade.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        comboPersonalidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPersonalidadeItemStateChanged(evt);
            }
        });

        campoCodigoFPInternet.setEditable(false);
        campoCodigoFPInternet.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPortador)
                    .addComponent(jTCorrentista, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelConta)
                            .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTipoOperacaoBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOperacaoBancaria)
                            .addComponent(comboBancoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOperacaoBancaria1))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTDataOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelValorPagar)
                            .addComponent(jFTValorOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataEmissao)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTAgenciaDestino, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelObs1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(labelPortador1)
                    .addComponent(jTDetalhamento, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCpfCnpj)
                    .addComponent(labelObs2)
                    .addComponent(jTContaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPersonalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoSacado1)
                    .addComponent(jFTCpfCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigoFPInternet, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTValorOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoFPInternet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelOperacaoBancaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTipoOperacaoBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelDataEmissao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelOperacaoBancaria1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBancoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelObs1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTAgenciaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addComponent(labelPortador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTCorrentista, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelObs2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTContaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTipoSacado1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPersonalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelPortador1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDetalhamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelCpfCnpj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTCpfCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jFTDataOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel2.setPreferredSize(new java.awt.Dimension(280, 65));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
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
                        .addGap(176, 176, 176)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        jFTDataOperacao.setDate(Controller.getDataServidorBD());
        jFTValorOperacao.setText("0,00");
        jTDetalhamento.setText("");
        campoCodigoFPInternet.setText("");

        comboBancoDestino.setSelectedItem(null);
        jTContaDestino.setText("");
        jTAgenciaDestino.setText("");
        jTCorrentista.setText("");
        jFTCpfCpnj.setValue(null);

        comboContaBancaria.setSelectedIndex(0);
        comboTipoOperacaoBancaria.setSelectedIndex(0);
        comboPersonalidade.setSelectedIndex(0);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
//GEN-LAST:event_botaoSalvarActionPerformed

        if (validarCombos("contaBancaria")) {

            try {
                if (validaCampos()) {

                    TituloPagarBaixaInternetModel tituloPagarInternet = new TituloPagarBaixaInternetModel();

                    ContaBancariaModel conta = new ContaBancariaModel();

                    tituloPagarInternet.setPk(new PKModel());
                    tituloPagarInternet.getPk().setOrganizacao(organizacaoModel);

                    if (!campoCodigoFPInternet.getText().isEmpty() && campoCodigoFPInternet.getText() != null) {
                        tituloPagarInternet.getPk().setId(campoCodigoFPInternet.getText());
                    } else {
                        tituloPagarInternet.getPk().setId(PempecKeyGenerator.generateKey());

                    }

                    tituloPagarInternet.setDataOperacao(jFTDataOperacao.getDate());

                    tituloPagarInternet.setValor(jFTValorOperacao.getValue());

                    //pq ta setando o valor aqui?
                    formaPagamento.setValor(jFTValorOperacao.getValue());
                                    
                //alterado em 26 de junho 14
                formaPagamento.setConta(comboContaBancaria.getSelectedItem().toString());
                
                

                    conta.setPk(new PKModel());

                    conta.getPk().setOrganizacao(organizacaoModel);

                    conta.getPk().setId(((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk().getId());

                    conta.setConta(comboContaBancaria.getSelectedItem().toString());

                    conta = contaBancariaBO.consultarPorTemplate(conta);


                    try {

                        Double valorSaldo = new ContaBancariaServices(conta).obterSaldoContaBancaria();

                        if (valorSaldo < jFTValorOperacao.getValue()) {

                            if (!exibeMensagemConfirmacao("A conta informada não possui saldo suficiente. Desejar continuar? \n\nSaldo: R$ " + PempecParse.doubleToZero(valorSaldo), "Saldo Insuficiente")) {
                                return;
                            }

                            if (Controller.verificaParametroAtivo(Parametro.CBPSFPI001.getCodigo())) {
                                exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPSFPI001.getCodigo()), null);
                                return;
                            }

                            if (Controller.verificaParametroAtivo(Parametro.CBPSFPI002.getCodigo())) {
                                if (valorSaldo < Controller.findByCodigo(Parametro.CBPSFPI002.getCodigo()).getValor().doubleValue()) {
                                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPSFPI002.getCodigo()), null);
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

                    tituloPagarInternet.setAgenciaDestino(jTAgenciaDestino.getText());

                    tituloPagarInternet.setContaDestino(jTContaDestino.getText());

                    tituloPagarInternet.setNomeCorrentista(jTCorrentista.getText());

                    tituloPagarInternet.setDetalhamento(jTDetalhamento.getText().toUpperCase());

                    if (validarCombos("contaBancaria")) {

                        tituloPagarInternet.setContaBancaria(conta);
                    }

                    if (validarCombos("tipoOperacao")) {

                        TipoOperacaoBancariaModel tipoOperacaoBancariaModel = new TipoOperacaoBancariaModel();

                        tipoOperacaoBancariaModel.setPk(new PKModel());
                        tipoOperacaoBancariaModel.getPk().setOrganizacao(organizacaoModel);
                        tipoOperacaoBancariaModel.getPk().setId(((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk().getId());
                        tipoOperacaoBancariaModel.setDescricao(((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getDescricao());

                        tipoOperacaoBancariaModel = new TipoOperacaoBancariaBO().consultarPorTemplate(tipoOperacaoBancariaModel);
                        tituloPagarInternet.setTipoOperacaoBancaria(tipoOperacaoBancariaModel);

                    }

                    if (validarCombos("banco")) {

                        tituloPagarInternet.setBancoDestino(new BancoModel());
                        tituloPagarInternet.getBancoDestino().setId(((BancoModel) comboBancoDestino.getSelectedItem()).getId());

                    }


                    if (validarCombos("personalidade")) {

                        tituloPagarInternet.setPersonalidadeCorrentista(((String) comboPersonalidade.getSelectedItem()).toString());
                    }
                    tituloPagarInternet.setCpfCpnjCorrentista(jFTCpfCpnj.getText());

                    baixa.addCollecaoInternet(tituloPagarInternet, formaPagamento);
                    this.botaoLimparActionPerformed(evt);
                    this.dispose();

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
        }


    }

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

private void jFTValorOperacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTValorOperacaoFocusLost

    Double valor = jFTValorOperacao.getValue();
    Double valorTitulo = titulo.getValorNominal();

    if (valor > 0) {

        if (valor > valorTitulo) {
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
    private javax.swing.JTextField campoCodigoFPInternet;
    private javax.swing.JComboBox comboBancoDestino;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboPersonalidade;
    private javax.swing.JComboBox comboTipoOperacaoBancaria;
    private javax.swing.JFormattedTextField jFTCpfCpnj;
    private org.jdesktop.swingx.JXDatePicker jFTDataOperacao;
    private br.com.pempec.componentes.JDoubleField jFTValorOperacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTAgenciaDestino;
    private javax.swing.JTextField jTContaDestino;
    private javax.swing.JTextField jTCorrentista;
    private javax.swing.JTextField jTDetalhamento;
    private javax.swing.JLabel labelConta;
    private javax.swing.JLabel labelCpfCnpj;
    private javax.swing.JLabel labelDataEmissao;
    private javax.swing.JLabel labelObs1;
    private javax.swing.JLabel labelObs2;
    private javax.swing.JLabel labelOperacaoBancaria;
    private javax.swing.JLabel labelOperacaoBancaria1;
    private javax.swing.JLabel labelPortador;
    private javax.swing.JLabel labelPortador1;
    private javax.swing.JLabel labelTipoSacado1;
    private javax.swing.JLabel labelValorPagar;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportBanco;

    private boolean validarCombos(String combo) {
        boolean valida = false;

        if (combo.equalsIgnoreCase("banco")) {
            if (comboBancoDestino.getSelectedItem() != null) {
                if (((BancoModel) comboBancoDestino.getSelectedItem()) != null) {
                    if (!((BancoModel) comboBancoDestino.getSelectedItem()).getId().isEmpty()) {
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



        if (combo.equalsIgnoreCase("tipoOperacao")) {
            if (comboTipoOperacaoBancaria.getSelectedItem() != null) {
                if (((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk() != null) {
                    if (!((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk().getId().isEmpty()) {
                        valida = true;
                    }

                }
            }
        }



        if (combo.equalsIgnoreCase("personalidade")) {
            if (comboPersonalidade.getSelectedItem() != null) {
                if (comboPersonalidade.getSelectedIndex() != 0) {
                    valida = true;
                }

            }
        }

        return valida;

    }
}
