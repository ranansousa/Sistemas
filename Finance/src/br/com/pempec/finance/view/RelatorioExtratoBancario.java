package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaCheque;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaCredito;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaDebito;
import br.com.pempec.finance.models.reports.ReportExtratoBancario;
import br.com.pempec.finance.models.reports.ReportExtratoBancarioCheques;
import br.com.pempec.finance.models.reports.ReportExtratoBancarioCrossTab;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 * @author Equipe Pempec
 */
public class RelatorioExtratoBancario extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private TipoOperacaoBancariaBO operacaoBancariaBO = new TipoOperacaoBancariaBO();
    private long tela = Tela.TELA_CONCILIACAO_EXTRATO_BANCARIO.getTela();
    private ContaBancariaDebitoBO contaBancariaDebitoBO = new ContaBancariaDebitoBO();
    private ContaBancariaCreditoBO contaBancariaCreditoBO = new ContaBancariaCreditoBO();
    private ContaBancariaChequeBO contaBancariaChequeBO = new ContaBancariaChequeBO();

    public RelatorioExtratoBancario() throws SystemException {

        this.setLocation(250, 50);
        Controller.setQtdMovimentosHoje(0);
        initComponents();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        comboTipoOperacaoBancaria = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        comboResponsavel = new javax.swing.JComboBox();
        labelLocalPagamento = new javax.swing.JLabel();
        comboContaBancaria = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        jFTPeriodoInicial = new org.jdesktop.swingx.JXDatePicker();
        jFTPeriodoFinal = new org.jdesktop.swingx.JXDatePicker();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Relatório Extrato Bancário");
        setPreferredSize(new java.awt.Dimension(960, 480));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Filtros"));

        jLabel13.setText("Operação Bancária");

        comboTipoOperacaoBancaria.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel7.setText("Responsável");

        comboResponsavel.setFont(new java.awt.Font("Arial", 0, 10));

        labelLocalPagamento.setText("Conta Bancária");

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 11));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboTipoOperacaoBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLocalPagamento)
                    .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelLocalPagamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTipoOperacaoBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Parametros"));
        jPanel6.setPreferredSize(new java.awt.Dimension(1050, 125));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Período"));
        jPanel4.setPreferredSize(new java.awt.Dimension(304, 80));

        label2.setText("à");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Formato"));
        jPanel5.setPreferredSize(new java.awt.Dimension(304, 80));

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
                .addGap(27, 27, 27)
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoGerar.setMnemonic('G');
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
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Relatório Extrato Bancário";
    }

    public void repopularCombos() {

        try {

            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Selecione <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancaria.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            Collection<TipoOperacaoBancariaModel> lTipoOperacao = new ArrayList<TipoOperacaoBancariaModel>();

            TipoOperacaoBancariaModel tipoOPeracao = new TipoOperacaoBancariaModel();

            tipoOPeracao.setDescricao(" -> Selecione <- ");

            lTipoOperacao.add(tipoOPeracao);

            lTipoOperacao.addAll(operacaoBancariaBO.obterTodos(organizacaoModel));

            comboTipoOperacaoBancaria.setModel(new javax.swing.DefaultComboBoxModel(lTipoOperacao.toArray()));

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
        comboFormato.setSelectedIndex(0);
        comboContaBancaria.setSelectedIndex(0);
        comboResponsavel.setSelectedIndex(0);
        comboTipoOperacaoBancaria.setSelectedIndex(0);

        jFTPeriodoFinal.setDate(Controller.getDataServidorBD());
        jFTPeriodoInicial.setDate(Controller.getDataServidorBD());
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboContaBancaria.getSelectedIndex() == 0) {
            comboContaBancaria.requestFocus();
            return false;
        }

        if (jFTPeriodoFinal.getDate() == null) {
            jFTPeriodoFinal.requestFocus();
            return false;
        }

        if (jFTPeriodoInicial.getDate() == null) {
            jFTPeriodoInicial.requestFocus();
            return false;
        }

        if (jFTPeriodoInicial.getDate().after(jFTPeriodoFinal.getDate())) {
            exibeMensagemAviso("A Data Inicial não pode ser superior a data final", null);
            jFTPeriodoInicial.requestFocus();
            return false;
        }

        return true;
    }

    private void botaoGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarActionPerformed

        if (validaCampos()) {

            try {
                long action = Action.IMPRESSAO.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                new MovimentoDiarioBO().inserir(this.registroMovimento("REL EXT BCO", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do relatorio extrato bancario da conta : " + comboContaBancaria.getSelectedItem().toString(), null, "Impresso"));

                Map<String, Object> parametros = new HashMap<String, Object>();

                String dataInicial = PempecParse.dateToString(jFTPeriodoInicial.getDate());
                String dataFinal = PempecParse.dateToString(jFTPeriodoFinal.getDate());

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL_BARRAS, dataInicial);
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL_BARRAS, dataFinal);

                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                FilterReportContaBancariaCredito filterCredito = new FilterReportContaBancariaCredito();
                FilterReportContaBancariaDebito filterDebito = new FilterReportContaBancariaDebito();
                FilterReportContaBancariaCheque filterCheque = new FilterReportContaBancariaCheque();

                filterCredito.setOrganizacao(organizacaoModel.getId());
                filterDebito.setOrganizacao(organizacaoModel.getId());
                filterCheque.setOrganizacao(organizacaoModel.getId());

                filterCredito.setDataInicial(PempecParse.dateToDateMinTime(jFTPeriodoInicial.getDate()));
                filterDebito.setDataInicial(PempecParse.dateToDateMaxTime(jFTPeriodoInicial.getDate()));
                filterCheque.setDataInicial(PempecParse.dateToDateMaxTime(jFTPeriodoInicial.getDate()));

                filterCredito.setDataFinal(PempecParse.dateToDateMinTime(jFTPeriodoFinal.getDate()));
                filterDebito.setDataFinal(PempecParse.dateToDateMaxTime(jFTPeriodoFinal.getDate()));
                filterCheque.setDataFinal(PempecParse.dateToDateMaxTime(jFTPeriodoFinal.getDate()));

                if (comboContaBancaria.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk() != null) {
                    filterCredito.setContaBancaria(((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk().getId());
                    filterDebito.setContaBancaria(((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk().getId());
                    filterCheque.setContaBancaria(((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk().getId());
                }

                if (comboTipoOperacaoBancaria.getSelectedItem() != null && ((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk() != null) {
                    filterCredito.setTipoOperacaoBancaria(((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk().getId());
                    filterDebito.setTipoOperacaoBancaria(((TipoOperacaoBancariaModel) comboTipoOperacaoBancaria.getSelectedItem()).getPk().getId());
                }

                if (comboResponsavel.getSelectedItem() != null && ((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk() != null) {
                    filterCredito.setResponsavel(((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk().getId());
                    filterDebito.setResponsavel(((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk().getId());
                }

                Collection<ContaBancariaCreditoModel> collCreditos = contaBancariaCreditoBO.obterRelatorio(filterCredito);
                Collection<ContaBancariaDebitoModel> collDebitos = contaBancariaDebitoBO.obterRelatorio(filterDebito);

                Collection<ReportExtratoBancarioCrossTab> collCrossTab = new ArrayList<ReportExtratoBancarioCrossTab>();

                Collection<ReportExtratoBancarioCheques> collSubCheques = new ArrayList<ReportExtratoBancarioCheques>();

                Collection<ReportExtratoBancarioCheques> collSubChequesAvulso = new ArrayList<ReportExtratoBancarioCheques>();

                filterCheque.setStatus(Constantes.STATUS_CHEQUE_EMITIDO);

                Collection<ContaBancariaChequeModel> collCheques = contaBancariaChequeBO.obterRelatorio(filterCheque);

                filterCheque.setStatus(Constantes.STATUS_CHEQUE_AVULSO);

                Collection<ContaBancariaChequeModel> collChequesAvulso = contaBancariaChequeBO.obterRelatorio(filterCheque);

                Collection<ReportExtratoBancario> collection = new ArrayList<ReportExtratoBancario>();

                ReportExtratoBancario report = new ReportExtratoBancario();

                Double saldoDisponivel = 0d;
                Double totalCredito = 0d;
                Double totalDebito = 0d;

                totalCredito = contaBancariaCreditoBO.obterSaldoAnterior(filterCredito);

                totalDebito = contaBancariaDebitoBO.obterSaldoAnterior(filterDebito);

                report.setRazaoSocial(organizacaoModel.getRazaoSocial());

                report.setCnpj(organizacaoModel.getCnpj());

                if (comboContaBancaria.getSelectedItem() != null) {

                    ContaBancariaModel conta = ((ContaBancariaModel) comboContaBancaria.getSelectedItem());

                    conta = contaBancariaBO.consultarPorPk(conta);

                    if (conta != null && conta.getPk() != null) {

                        String[] aux = conta.getBanco().getNomeBanco().split(" ");

                        if (aux[1].equalsIgnoreCase("do")) {
                            report.setConta(conta.getConta() + " - " + aux[2].toUpperCase());
                        } else {
                            report.setConta(conta.getConta() + " - " + aux[1].toUpperCase());
                        }

                        report.setTitular(conta.getTitular());

                    } else {

                        report.setConta(((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getConta());

                        report.setTitular(((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getTitular());

                    }

                }

                report.setTotalCredito(totalCredito);

                report.setTotalDebito(totalDebito);

                ReportExtratoBancarioCrossTab aux = new ReportExtratoBancarioCrossTab();

                aux.setDataMovimento(PempecUtil.addDayDate(filterCredito.getDataInicial(), -1));

                aux.setDescricao("SALDO ANTERIOR");

                if ((totalCredito == null ? 0d : totalCredito) - (totalDebito == null ? 0d : totalDebito) >= 0) {

                    aux.setTipoLancamento("C");

                } else {

                    aux.setTipoLancamento("D");

                }

                aux.setValor(Math.abs((totalCredito == null ? 0d : totalCredito) - (totalDebito == null ? 0d : totalDebito)));

                saldoDisponivel = aux.getValor();

                collCrossTab.add(aux);

                for (ContaBancariaDebitoModel debito : collDebitos) {

                    ReportExtratoBancarioCrossTab tab = new ReportExtratoBancarioCrossTab();

                    tab.setDataMovimento(debito.getDataMovimento());

                    tab.setDescricao("Transação Nº " + debito.getIdentificacao() + "\n" + ((debito.getTituloPagar() != null && debito.getTituloPagar().getPk() != null && debito.getTituloPagar().getCedente() != null) ? debito.getDescricao() + "\n" + debito.getTituloPagar().getCedente().getNome() : debito.getDescricao()));

                    tab.setTipoLancamento(debito.getTipoLancamento());

                    tab.setValor(debito.getValor());

                    
                    saldoDisponivel -= debito.getValor();

                    collCrossTab.add(tab);

                }

                for (ContaBancariaCreditoModel credito : collCreditos) {

                    ReportExtratoBancarioCrossTab tab = new ReportExtratoBancarioCrossTab();

                    tab.setDataMovimento(credito.getDataMovimento());

                    tab.setDescricao("Transação Nº " + credito.getIdentificacao() + "\n" + ((credito.getTituloReceber() != null && credito.getTituloReceber().getPk() != null && credito.getTituloReceber().getSacado() != null) ? credito.getDescricao() + "\n" + credito.getTituloReceber().getSacado().getNome() : credito.getDescricao()));

                    tab.setTipoLancamento(credito.getTipoLancamento());

                    tab.setValor(credito.getValor());

                    saldoDisponivel += credito.getValor();

                    collCrossTab.add(tab);

                }

                for (ContaBancariaChequeModel cheque : collCheques) {

                    ReportExtratoBancarioCheques tab = new ReportExtratoBancarioCheques();

                    tab.setNumeroCheque(cheque.getNumeroCheque());
                    tab.setDataEmissao(cheque.getDataEmissao());
                    tab.setPortador(cheque.getPortador());
                    tab.setValor(cheque.getValor());

                    saldoDisponivel -= cheque.getValor();

                    collSubCheques.add(tab);

                }

                Double saldoDisponivelAvulso = 0d;
                
               if(saldoDisponivel != null ) {
                   
                   saldoDisponivelAvulso =saldoDisponivel; 
                   
               };

                for (ContaBancariaChequeModel cheque : collChequesAvulso) {

                    ReportExtratoBancarioCheques tab = new ReportExtratoBancarioCheques();

                    tab.setNumeroCheque(cheque.getNumeroCheque());
                    tab.setDataEmissao(cheque.getDataEmissao());
                    tab.setPortador(cheque.getPortador());
                    tab.setValor(cheque.getValor());

                    saldoDisponivelAvulso -= cheque.getValor();

                    collSubChequesAvulso.add(tab);

                }

                parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_CROSSTAB, collCrossTab);

                parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION, collSubCheques);

                parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_CHQ_AVULSO, collSubChequesAvulso);

                report.setSaldoDisponivel(saldoDisponivel);

                report.setSaldoDisponivelAvulso(saldoDisponivelAvulso);

                collection.add(report);

                switch (comboFormato.getSelectedIndex()) {

                    case 0:
                        new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_EXTRATO_BANCARIO, parametros, collection);
                        break;
                    case 1:
                        new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_EXTRATO_BANCARIO, parametros, collection);
                        break;
                    case 2:
                        new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_EXTRATO_BANCARIO, parametros, collection);
                        break;
                    case 3:

                        //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                        File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_EXTRATO_BANCARIO, parametros, collection);

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


}//GEN-LAST:event_botaoGerarActionPerformed

    private void comboFormatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormatoItemStateChanged
        if (comboFormato.getSelectedItem() != null && ((FormatosRelatoriosModel) comboFormato.getSelectedItem()).getDescricao().equalsIgnoreCase(Constantes.ENVIAR_POR_EMAIL)) {
            botaoGerar.setText("Enviar");
        } else {
            botaoGerar.setText("Gerar");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_comboFormatoItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JComboBox comboResponsavel;
    private javax.swing.JComboBox comboTipoOperacaoBancaria;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoFinal;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoInicial;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel labelLocalPagamento;
    // End of variables declaration//GEN-END:variables
}
