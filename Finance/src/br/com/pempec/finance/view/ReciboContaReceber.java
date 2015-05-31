package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.EnderecoBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.SacadoBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberBaixaInternetModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.reports.ReciboTituloReceberCrossTab;
import br.com.pempec.finance.models.reports.ReciboTituloReceberSubForma;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Extenso;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.TituloReceberTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaMap;

public class ReciboContaReceber extends FinanceInternalFrame implements IRepopulador {

    private TituloReceberBO tituloReceberBO = new TituloReceberBO();
    private TituloReceberBaixaBO tituloReceberBaixaBO = new TituloReceberBaixaBO();
    private SacadoBO sacadoBO = new SacadoBO();
    private EnderecoBO enderecoBO = new EnderecoBO();
    private long tela = Tela.TELA_CONTAS_A_RECEBER_EMITIR_RECIBO.getTela();
    private TituloReceberBaixaModel baixa;

    public ReciboContaReceber() throws SystemException {

        initComponents();
        Controller.setQtdMovimentosHoje(0);
        botaoGerar.setEnabled(false);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        comboTitulo = new javax.swing.JComboBox();
        labelNumeroDocumento = new javax.swing.JLabel();
        labelSacado = new javax.swing.JLabel();
        labelDescricao = new javax.swing.JLabel();
        labelParcela = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        labelDataVencimento = new javax.swing.JLabel();
        labelObservacao = new javax.swing.JLabel();
        labelDataRecebimento = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Recibo Título Receber");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)), NameObject()));

        comboTitulo.setToolTipText("");
        comboTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloActionPerformed(evt);
            }
        });

        labelNumeroDocumento.setText("Número do Documento");

        labelSacado.setFont(new java.awt.Font("Arial", 0, 10));
        labelSacado.setForeground(new java.awt.Color(0, 102, 102));
        labelSacado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sacado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelDescricao.setFont(new java.awt.Font("Arial", 0, 10));
        labelDescricao.setForeground(new java.awt.Color(0, 102, 102));
        labelDescricao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descricao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelParcela.setForeground(new java.awt.Color(0, 102, 102));
        labelParcela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelParcela.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Parcela"));

        labelValor.setForeground(new java.awt.Color(0, 102, 153));
        labelValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelValor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor Recebido"));

        labelDataVencimento.setForeground(new java.awt.Color(102, 102, 102));
        labelDataVencimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataVencimento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Vencimento"));

        labelObservacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelObservacao.setForeground(new java.awt.Color(0, 102, 102));
        labelObservacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelDataRecebimento.setForeground(new java.awt.Color(102, 102, 102));
        labelDataRecebimento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataRecebimento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Recebimento"));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Formato"));

        comboFormato.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFormatoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, 0, 256, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelDescricao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNumeroDocumento, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelObservacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelSacado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataRecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(145, 145, 145))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNumeroDocumento)
                .addGap(10, 10, 10)
                .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDataRecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(labelParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(labelSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));

        botaoGerar.setMnemonic('I');
        botaoGerar.setText("Gerar");
        botaoGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarActionPerformed(evt);
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
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Recibo de Título a Receber";
    }

    public void repopularCombos() {

        try {

            Collection<TituloReceberModel> lColecao = tituloReceberBO.obterTodosRecibo(organizacaoModel);

            final EventList<TituloReceberModel> lRegistros = GlazedLists.eventList(lColecao);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboTitulo, lRegistros, new TituloReceberTextFilterator());
            support.setFilterMode(TextMatcherEditor.STARTS_WITH);
            support.setStrict(false);

            comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));


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

        comboTitulo.setSelectedItem(null);
        labelSacado.setText("");
        labelDataVencimento.setText("  /  /    ");
        labelDescricao.setText("");
        labelObservacao.setText("");
        labelParcela.setText("");
        labelDataRecebimento.setText("");
        labelValor.setText("0,00");
        botaoGerar.setEnabled(true);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (labelSacado.getText().isEmpty()) {
            comboTitulo.requestFocus();
            return false;
        }

        return true;
    }

    private void botaoGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarActionPerformed
//GEN-LAST:event_botaoGerarActionPerformed

        if (validaCampos()) {

            try {

                long action = Action.IMPRESSAO.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (tituloReceberModel != null) {

                    if (Controller.verificaParametroAtivo(Parametro.CRR003.getCodigo())) {
                        if (tituloReceberModel.getValorNominal().doubleValue() > Controller.findByCodigo(Parametro.CRR003.getCodigo()).getValor().doubleValue()) {
                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CRR003.getCodigo()), null);
                            return;
                        }
                    }

                    Map<String, Object> parametros = new HashMap<String, Object>();

                    parametros.put(RelatorioConstantes.PARAMETRO_ID_ORGANIZACAO, organizacaoModel.getId());
                    parametros.put(RelatorioConstantes.PARAMETRO_RAZAO_SOCIAL, organizacaoModel.getRazaoSocial());
                    parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());
                    parametros.put(RelatorioConstantes.PARAMETRO_CNPJ, organizacaoModel.getCnpj());

                    parametros.put(RelatorioConstantes.PARAMETRO_NUMERO_DOCUMENTO, tituloReceberModel.getNumeroDocumento());
                    parametros.put(RelatorioConstantes.PARAMETRO_USER_LOGADO, "Caixa: " + Controller.getUsuarioLogado().getNome());

                    //parametros.put(RelatorioConstantes.PARAMETRO_CIDADE, organizacaoModel.getCidade() + ", " + PempecParse.dateToString(new Date()));

                    if (tituloReceberModel.getDataPagamento() != null) {

                        parametros.put(RelatorioConstantes.PARAMETRO_CIDADE, organizacaoModel.getCidade() + ", " + PempecParse.dateToString(tituloReceberModel.getDataPagamento()));

                    } else {

                        parametros.put(RelatorioConstantes.PARAMETRO_CIDADE, organizacaoModel.getCidade() + ", " + PempecParse.dateToString(Controller.getDataServidorBD()));
                    }




                    SacadoModel sacado = sacadoBO.consultarPorPk(tituloReceberModel.getSacado());

                    if (sacado != null && sacado.getPk() != null) {

                        if (sacado.getTipoSacado() != null && sacado.getTipoSacado().getPk() != null) {

                            if (sacado.getNome() != null && !sacado.getNome().isEmpty()) {

                                parametros.put(RelatorioConstantes.PARAMETRO_FAVORECIDO, sacado.getTipoSacado().getDescricao() + " : " + sacado.getNome());

                            } else {

                                parametros.put(RelatorioConstantes.PARAMETRO_FAVORECIDO, sacado.getNome());
                            }


                        } else {

                            parametros.put(RelatorioConstantes.PARAMETRO_FAVORECIDO, " XX ");

                        }



                        if (sacado.getEndereco() != null && sacado.getEndereco().getCidade() != null && sacado.getEndereco().getBairro() != null && sacado.getEndereco().getEstado() != null) {

                            EnderecoModel endereco = enderecoBO.consultarPorPk(sacado.getEndereco());

                            parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO_FAVORECIDO,
                                    endereco.getLogradouro().toUpperCase() + ", Nº " + endereco.getNumero() + " - " + endereco.getBairro().getDescricao().toUpperCase() + " - CEP : " + endereco.getCep());


                            //mandando cidade e estado com complemento do endereco
                            //faz o teste e envia a string corretamente
                            if (endereco.getComplemento() != null && !endereco.getComplemento().isEmpty()) {

                                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO_FAVORECIDO_CIDADE_ESTADO,
                                        endereco.getComplemento() + " - " + endereco.getCidade().getDescricao().toUpperCase() + " - " + endereco.getEstado().getSigla().toUpperCase());

                            } else {

                                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO_FAVORECIDO_CIDADE_ESTADO,
                                        endereco.getCidade().getDescricao().toUpperCase() + " - " + endereco.getEstado().getSigla().toUpperCase());
                            }

                        } else {

                            parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO_FAVORECIDO, " Cadastro incompleto. Falta endereço. ");

                            parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO_FAVORECIDO_CIDADE_ESTADO, " Complete o cadastro.");



                        }

                        parametros.put(RelatorioConstantes.PARAMETRO_CNPJ_CPF_FAVORECIDO, sacado.getPersonalidade().contains("CPF") ? "CPF: " : "CNPJ: " + sacado.getCpfCnpj());

                        parametros.put(RelatorioConstantes.PARAMETRO_DESCRICAO, tituloReceberModel.getHistorico().getDescricao() + " " + tituloReceberModel.getDescricao());

                        if (sacado.getContato() != null) {
                            parametros.put(RelatorioConstantes.PARAMETRO_CONTATO_FAVORECIDO, sacado.getContato().getTelefone());
                        }

                    }

                    List<TituloReceberModel> collTitulos = tituloReceberBO.obterTitulosFilhos(tituloReceberModel);

                    collTitulos.remove(tituloReceberModel);

                    Collection<DynaBean> collection = new ArrayList<DynaBean>();

                    Collection<ReciboTituloReceberCrossTab> collCrossTab = new ArrayList<ReciboTituloReceberCrossTab>();

                    Collection<ReciboTituloReceberSubForma> collFormas = new ArrayList<ReciboTituloReceberSubForma>();

                    if (Controller.verificaParametroAtivo(Parametro.CRR001.getCodigo()) && baixa == null) {
                        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CRR001.getCodigo()), null);
                        return;
                    }

                    Double valorPago = 0d;

                    Double totalAcrescimos = 0d;

                    Double totalDeducoes = 0d;

                    if (baixa != null) {



                        String descricaoFormaInternet = Constantes.FORMA_PAGAMENTO_INTERNET;
                        String descricaoFormaCheque = Constantes.FORMA_PAGAMENTO_CHEQUE;

                        if (baixa.getFormasPagamento() != null && !baixa.getFormasPagamento().isEmpty()) {

                            for (TituloReceberBaixaFormaPagamentoModel forma : baixa.getFormasPagamento()) {
                                if (Controller.verificaParametroAtivo(Parametro.CRR002.getCodigo())) {
                                    if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {
                                        if (!Controller.verificaParametroAtivo(Parametro.CRR00201.getCodigo())) {
                                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CRR00201.getCodigo()), null);
                                            return;
                                        }
                                    } else {
                                        if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE)) {
                                            if (!Controller.verificaParametroAtivo(Parametro.CRR00202.getCodigo())) {
                                                exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CRR00202.getCodigo()), null);
                                                return;
                                            } else {
                                                if (!Controller.verificaParametroAtivo(Parametro.CRR00203.getCodigo())) {
                                                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CRR00203.getCodigo()), null);
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }

                                if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                                    ReciboTituloReceberSubForma formaEspecie = new ReciboTituloReceberSubForma();

                                    formaEspecie.setValor(forma.getValor());

                                    formaEspecie.setIdForma(Constantes.FORMA_PAGAMENTO_ESPECIE);

                                    formaEspecie.setForma(forma.getForma().getDescricao());

                                    collFormas.add(formaEspecie);

                                } else {

                                    if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE)) {

                                        descricaoFormaCheque = forma.getForma().getDescricao();

                                    } else {

                                        descricaoFormaInternet = forma.getForma().getDescricao();
                                    }

                                }

                                valorPago += forma.getValor();

                            }

                            for (TituloReceberBaixaChequeModel cheque : baixa.getTituloReceberBaixaCheque()) {

                                ReciboTituloReceberSubForma formaCheque = new ReciboTituloReceberSubForma();

                                formaCheque.setValor(cheque.getValor());

                                formaCheque.setIdForma(Constantes.FORMA_PAGAMENTO_CHEQUE);

                                formaCheque.setForma(descricaoFormaCheque);

                                formaCheque.setNumeroCheque(cheque.getNumeroCheque());

                                formaCheque.setConta(cheque.getAgencia() + "/" + cheque.getConta());

                                collFormas.add(formaCheque);

                            }

                            for (TituloReceberBaixaInternetModel internet : baixa.getTituloReceberBaixaInternet()) {

                                ReciboTituloReceberSubForma formaInternet = new ReciboTituloReceberSubForma();

                                formaInternet.setValor(internet.getValor());

                                formaInternet.setIdForma(Constantes.FORMA_PAGAMENTO_INTERNET);

                                formaInternet.setForma(descricaoFormaInternet);

                                formaInternet.setContaDestino(internet.getContaBancaria().getConta());

                                formaInternet.setDescricao(internet.getTipoOperacaoBancaria().getDescricao());

                                collFormas.add(formaInternet);

                            }

                        }

                        if (baixa.getAcrescimos() != null && !baixa.getAcrescimos().isEmpty()) {

                            for (TituloReceberBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                                ReciboTituloReceberCrossTab crossTab = new ReciboTituloReceberCrossTab();

                                crossTab.setTipo("Acréscimos");

                                crossTab.setDescricao(acrescimo.getTipoAcrescimo().getDescricao());

                                crossTab.setValor(acrescimo.getValor());

                                collCrossTab.add(crossTab);

                                totalAcrescimos += acrescimo.getValor();

                            }

                        }

                        if (baixa.getDeducoes() != null && !baixa.getDeducoes().isEmpty()) {

                            for (TituloReceberBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                                ReciboTituloReceberCrossTab crossTab = new ReciboTituloReceberCrossTab();

                                crossTab.setTipo("Deduções");

                                crossTab.setDescricao(deducao.getTipoDeducao().getDescricao());

                                crossTab.setValor(deducao.getValor());

                                collCrossTab.add(crossTab);

                                totalDeducoes += deducao.getValor();

                            }

                        }

                    }

                    parametros.put(RelatorioConstantes.PARAMETRO_VALOR, tituloReceberModel.getValorNominal());

                    if (baixa != null) {

                        parametros.put("EXISTEBAIXA", true);

                        parametros.put("VALORPAGO", valorPago);

                        if (totalAcrescimos != 0d) {
                            parametros.put("EXISTEACRES", true);
                        }

                        parametros.put("TOTALACRESCIMOS", totalAcrescimos);

                        if (totalDeducoes != 0d) {
                            parametros.put("EXISTEDED", true);
                        }

                        parametros.put("TOTALDEDUCOES", totalDeducoes);

                        parametros.put(RelatorioConstantes.PARAMETRO_VALOR_EXTENSO, new Extenso(valorPago).toString().toUpperCase());

                    } else {

                        parametros.put(RelatorioConstantes.PARAMETRO_VALOR_EXTENSO, new Extenso(tituloReceberModel.getValorNominal()).toString().toUpperCase());

                    }

                    Double valorPagoFilhos = 0d;

                    for (TituloReceberModel titulo : collTitulos) {

                        baixa = tituloReceberBaixaBO.consultarPorTitulo(titulo);

                        Double totalAcrescimosFilhos = 0d;

                        Double totalDeducoesFilhos = 0d;

                        if (baixa != null) {

                            if (baixa.getFormasPagamento() != null && !baixa.getFormasPagamento().isEmpty()) {

                                for (TituloReceberBaixaFormaPagamentoModel forma : baixa.getFormasPagamento()) {

                                    valorPagoFilhos += forma.getValor();

                                }

                            }

                            if (baixa.getAcrescimos() != null && !baixa.getAcrescimos().isEmpty()) {

                                for (TituloReceberBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                                    totalAcrescimosFilhos += acrescimo.getValor();

                                }

                            }

                            if (baixa.getDeducoes() != null && !baixa.getDeducoes().isEmpty()) {

                                for (TituloReceberBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                                    totalDeducoesFilhos += deducao.getValor();

                                }

                            }

                            DynaBean lazy = new LazyDynaMap();
                            lazy.set(RelatorioConstantes.PARAMETRO_NUMERO_DOCUMENTO, titulo.getNumeroDocumento());
                            lazy.set(RelatorioConstantes.PARAMETRO_VALOR, valorPagoFilhos);
                            lazy.set(RelatorioConstantes.PARAMETRO_DATA_PAGAMENTO, titulo.getDataPagamento());
                            lazy.set(RelatorioConstantes.PARAMETRO_TOTAL_ACRESCIMOS, totalAcrescimosFilhos);
                            lazy.set(RelatorioConstantes.PARAMETRO_TOTAL_DEDUCOES, totalDeducoesFilhos);

                            collection.add(lazy);

                        }

                    }

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION, collection);

                    if (collCrossTab.isEmpty()) {
                        collCrossTab.add(new ReciboTituloReceberCrossTab());
                    }

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_CROSSTAB, collCrossTab);

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_FORMAS, collFormas);

                    new MovimentoDiarioBO().inserir(this.registroMovimento("Imprimir Recibo", tituloReceberModel.getNumeroDocumento(), Controller.getUsuarioLogado().getNome() + " solicitou impressao do recibo do titulo RCB " + tituloReceberModel.getNumeroDocumento(), tituloReceberModel.getValorNominal(), "Impresso"));

                    switch (comboFormato.getSelectedIndex()) {

                        case 0:

                            if (Controller.verificaParametroAtivo(Parametro.G005.getCodigo())) {

                                new RelatorioUtil().visualizar(RelatorioConstantes.RECIBO_CONTAS_RECEBER, parametros);

                            } else {

                                new RelatorioUtil().visualizar(RelatorioConstantes.RECIBO_CONTAS_RECEBER_LOGO, parametros);
                            }
                            break;
                        case 1:

                            new RelatorioUtil().exportarPDF(RelatorioConstantes.RECIBO_CONTAS_RECEBER, parametros);
                            break;
                        case 2:
                            new RelatorioUtil().exportarXLS(RelatorioConstantes.RECIBO_CONTAS_RECEBER, parametros);
                            break;
                        case 3:

                            //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                            File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RECIBO_CONTAS_RECEBER, parametros);

                            try {

                                TelaEnvioEmail envioEmail = new TelaEnvioEmail(anexo);
                                TelaPrincipal.desktopPane.add(envioEmail);
                                envioEmail.show();

                            } catch (final SystemException ex) {

                                final File file = PrintScreen.capture();

                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {

                                        tratamentoExcecao(ex, file);

                                    }
                                });

                            }
                            break;

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

        } else {

            exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

        }

    }

private void comboTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloActionPerformed
    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTitulo.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            TituloReceberModel tab = new TituloReceberModel();


            tab.setNumeroDocumento(comboTitulo.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = tituloReceberBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                tituloReceberModel = tab;
                botaoGerar.setEnabled(true);

                baixa = tituloReceberBaixaBO.consultarPorTitulo(tab);

                labelSacado.setText(tab.getSacado().getNome());
                labelDataVencimento.setText(PempecParse.dateToString(tab.getDataVencimento()));
                labelDataRecebimento.setText(PempecParse.dateToString(tab.getDataPagamento()));
                labelDescricao.setText(tab.getHistorico() + " " + tab.getDescricao());
                labelObservacao.setText(tab.getObservacao());
                labelParcela.setText(tab.getParcela());

                if (baixa != null && baixa.getPk() != null) {

                    labelValor.setText(PempecParse.doubleToZero(baixa.getValorPago()));

                } else {

                    labelValor.setText(PempecParse.doubleToZero(tab.getValorNominal()));
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

private void comboFormatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormatoItemStateChanged
    if (comboFormato.getSelectedItem() != null && ((FormatosRelatoriosModel) comboFormato.getSelectedItem()).getDescricao().equalsIgnoreCase(Constantes.ENVIAR_POR_EMAIL)) {
        botaoGerar.setText("Enviar");
    } else {
        botaoGerar.setText("Gerar");
    }    // TODO add your handling code here:
}//GEN-LAST:event_comboFormatoItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JComboBox comboTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelDataRecebimento;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelNumeroDocumento;
    private javax.swing.JLabel labelObservacao;
    private javax.swing.JLabel labelParcela;
    private javax.swing.JLabel labelSacado;
    private javax.swing.JLabel labelValor;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private TituloReceberModel tituloReceberModel;
}
