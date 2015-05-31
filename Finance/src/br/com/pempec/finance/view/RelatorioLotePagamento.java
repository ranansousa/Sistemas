package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.LotePagamentoTituloBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.ReportPagamentoLote;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Extenso;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RelatorioLotePagamento extends FinanceInternalFrame implements IRepopulador {

    private LotePagamentoTituloBO lotePagamentoTituloBO = new LotePagamentoTituloBO();
    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private long tela = Tela.TELA_CONTAS_A_PAGAR_BAIXA_EM_LOTE.getTela();

    public RelatorioLotePagamento() throws SystemException {

        initComponents();
        Controller.setQtdMovimentosHoje(0);
        campoLotePagamento.setVisible(false);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        comboLotePagamento = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        campoLotePagamento = new javax.swing.JTextField();
        labelResponsavel = new javax.swing.JLabel();
        labelFormaPagamento = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelData = new javax.swing.JLabel();
        labelSituacao = new javax.swing.JLabel();
        labelTipoOperacao = new javax.swing.JLabel();
        labelContabancaria = new javax.swing.JLabel();
        labelCheque = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        labelValorTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Relatório Lote Pagamento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)), NameObject()));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, 0, 256, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        comboLotePagamento.setToolTipText("");
        comboLotePagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLotePagamentoActionPerformed(evt);
            }
        });

        jLabel1.setText("Número do Lote");

        campoLotePagamento.setEditable(false);

        labelResponsavel.setFont(new java.awt.Font("Arial", 0, 10));
        labelResponsavel.setForeground(new java.awt.Color(102, 0, 102));
        labelResponsavel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelResponsavel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Responsável"));

        labelFormaPagamento.setFont(new java.awt.Font("Arial", 0, 10));
        labelFormaPagamento.setForeground(new java.awt.Color(102, 0, 102));
        labelFormaPagamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFormaPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Forma de Pagamento"));

        labelUsuario.setFont(new java.awt.Font("Arial", 0, 10));
        labelUsuario.setForeground(new java.awt.Color(0, 102, 102));
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Usuário"));

        labelData.setFont(new java.awt.Font("Arial", 0, 10));
        labelData.setForeground(new java.awt.Color(0, 102, 102));
        labelData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelData.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Data Pagamento"));

        labelSituacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelSituacao.setForeground(new java.awt.Color(0, 102, 102));
        labelSituacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSituacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Situação"));

        labelTipoOperacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelTipoOperacao.setForeground(new java.awt.Color(102, 0, 102));
        labelTipoOperacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoOperacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Tipo Operação Bancária"));

        labelContabancaria.setFont(new java.awt.Font("Arial", 0, 10));
        labelContabancaria.setForeground(new java.awt.Color(102, 0, 102));
        labelContabancaria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelContabancaria.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Conta Bancária"));

        labelCheque.setFont(new java.awt.Font("Arial", 0, 10));
        labelCheque.setForeground(new java.awt.Color(102, 0, 102));
        labelCheque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Cheque"));

        labelTotal.setForeground(new java.awt.Color(51, 51, 255));
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Total Documentos"));

        labelValorTotal.setForeground(new java.awt.Color(51, 51, 255));
        labelValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelValorTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Valor Total"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboLotePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoLotePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelTipoOperacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                                .addComponent(labelFormaPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(labelContabancaria, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(labelCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLotePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoLotePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTipoOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelCheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelContabancaria, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

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
                .addGap(223, 223, 223)
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Relatório Lote Pagamento";
    }

    public void repopularCombos() {

        try {

            Collection<LotePagamentoTituloModel> lLote = new ArrayList<LotePagamentoTituloModel>();

            LotePagamentoTituloModel lotePagamentoTituloModel = new LotePagamentoTituloModel();

            lotePagamentoTituloModel.setLote(" -> Selecione <- ");

            lLote.add(lotePagamentoTituloModel);

            lLote.addAll(lotePagamentoTituloBO.obterTodos(organizacaoModel));

            comboLotePagamento.setModel(new javax.swing.DefaultComboBoxModel(lLote.toArray()));

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
        comboLotePagamento.setSelectedIndex(0);
        campoLotePagamento.setText("");
        labelData.setText("");
        labelUsuario.setText("");
        labelFormaPagamento.setText("");
        labelResponsavel.setText("");
        labelCheque.setText("");
        labelTipoOperacao.setText("");
        labelTotal.setText("");
        labelSituacao.setText("");
        labelContabancaria.setText("");
        labelValorTotal.setText("");
        botaoGerar.setEnabled(false);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboLotePagamento.getSelectedIndex() == 0) {
            comboLotePagamento.requestFocus();
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
                    JOptionPane.showMessageDialog(this, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                new MovimentoDiarioBO().inserir(this.registroMovimento("REL LOT PAG", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do relatorio lote de pagamento.", null, "Impresso"));

                Map<String, Object> parametros = new HashMap<String, Object>();

                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                Collection<ReportPagamentoLote> collRel = new ArrayList<ReportPagamentoLote>();

                LotePagamentoTituloModel lotePagamentoTituloModel = new LotePagamentoTituloModel();

                lotePagamentoTituloModel.setPk(new PKModel());
                lotePagamentoTituloModel.getPk().setOrganizacao(Controller.getOrganizacao());
                lotePagamentoTituloModel.getPk().setId(campoLotePagamento.getText());
                lotePagamentoTituloModel.setLote(comboLotePagamento.getSelectedItem().toString());

                lotePagamentoTituloModel = lotePagamentoTituloBO.consultarPorTemplate(lotePagamentoTituloModel);

                Collection<TituloPagarModel> lTitulos = tituloPagarBO.obterTitulosLotePagamento(lotePagamentoTituloModel);

                for (TituloPagarModel tituloPagar : lTitulos) {

                    ReportPagamentoLote bean = new ReportPagamentoLote();

                    bean.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    bean.setCnpj(organizacaoModel.getCnpj());
                    bean.setLote(lotePagamentoTituloModel.getLote());
                    bean.setDataPagamento(lotePagamentoTituloModel.getDataPagamento());
                    bean.setNumeroDocumento(tituloPagar.getNumeroDocumento());
                    bean.setCedente(tituloPagar.getCedente().getNome());
                    bean.setDataVencimento(tituloPagar.getDataVencimento());
                    bean.setHistorico(tituloPagar.getHistorico().getDescricao() + " " + tituloPagar.getDescricao());
                    bean.setValor(tituloPagar.getValorNominal());

                    String forma = lotePagamentoTituloModel.getFormaPagamentoModel().getDescricao();

                    String aux = forma;

                    if (Constantes.FORMA_PAGAMENTO_ESPECIE.equals(forma)) {
                        bean.setFormaPagamento(forma);
                        bean.setTipoPagamento("CARTEIRA");
                    } else {

                        bean.setTipoPagamento("BANCO");

                        if (lotePagamentoTituloModel.getContaBancariaModel() != null) {
                            if (Constantes.FORMA_PAGAMENTO_CHEQUE.equals(forma)) {
                                aux += " Nº " + lotePagamentoTituloModel.getCheque().getNumeroCheque();
                            }
                            aux += " CONTA: " + lotePagamentoTituloModel.getContaBancariaModel().getConta();
                        }

                        bean.setFormaPagamento(aux);

                    }

                    collRel.add(bean);

                    String texto = "Autorizamos a V.Sas. resgatarem o(s) título(s) acima relacionado(s), " + collRel.size()
                            + " BOLETO(S) BANCÁRIO(S) de nossa responsabilidade levando a débito de nossa conta corrente, o valor de R$ " + PempecParse.doubleToZero(lotePagamentoTituloModel.getValor())
                            + "( " + new Extenso(lotePagamentoTituloModel.getValor())
                            + " ) na respectiva data de vencimento sob aviso.";

                    parametros.put("texto", texto);

                }

                switch (comboFormato.getSelectedIndex()) {

                    case 0:
                        new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_PAGAMENTO_LOTE, parametros, collRel);
                        break;
                    case 1:
                        new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_PAGAMENTO_LOTE, parametros, collRel);
                        break;
                    case 2:
                        new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_PAGAMENTO_LOTE, parametros, collRel);
                        break;
                    case 3:

                        //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                        File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_PAGAMENTO_LOTE, parametros, collRel);

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

            JOptionPane.showMessageDialog(null, "Campo(s) Obrigatório(s)!");

        }

    }

private void comboFormatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormatoItemStateChanged
    if (comboFormato.getSelectedItem() != null && ((FormatosRelatoriosModel) comboFormato.getSelectedItem()).getDescricao().equalsIgnoreCase(Constantes.ENVIAR_POR_EMAIL)) {
        botaoGerar.setText("Enviar");
    } else {
        botaoGerar.setText("Gerar");
    }
}//GEN-LAST:event_comboFormatoItemStateChanged

private void comboLotePagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLotePagamentoActionPerformed

    if (comboLotePagamento.getSelectedIndex() != 0) {

        try {

            LotePagamentoTituloModel lotePagamentoTituloModel = new LotePagamentoTituloModel();
            lotePagamentoTituloModel.setPk(new PKModel());
            lotePagamentoTituloModel.getPk().setOrganizacao(organizacaoModel);
            lotePagamentoTituloModel.setLote(comboLotePagamento.getSelectedItem().toString());

            lotePagamentoTituloModel = lotePagamentoTituloBO.consultarPorTemplate(lotePagamentoTituloModel);

            if (lotePagamentoTituloModel != null && lotePagamentoTituloModel.getPk() != null && lotePagamentoTituloModel.getPk().getId() != null) {

                if (lotePagamentoTituloModel.getStatus().equals(Constantes.LOTE_PAGAMENTO_REMOVIDO)) {
                    botaoGerar.setEnabled(false);
                } else {
                    botaoGerar.setEnabled(true);
                }

                labelTotal.setText(String.valueOf(lotePagamentoTituloModel.getQtdTituloPagar()));

                if (lotePagamentoTituloModel.getUsuario() != null) {
                    labelUsuario.setText(lotePagamentoTituloModel.getUsuario().getNome());
                }

                campoLotePagamento.setText(lotePagamentoTituloModel.getPk().getId());
                labelData.setText(PempecParse.dateToString(lotePagamentoTituloModel.getDataPagamento()));
                labelSituacao.setText(lotePagamentoTituloModel.getStatus());

                if (lotePagamentoTituloModel.getContaBancariaModel() != null) {
                    labelContabancaria.setText(lotePagamentoTituloModel.getContaBancariaModel().getConta());
                }

                if (lotePagamentoTituloModel.getTipoOperacaoBancariaModel() != null) {
                    labelTipoOperacao.setText(lotePagamentoTituloModel.getTipoOperacaoBancariaModel().getDescricao());
                }

                if (lotePagamentoTituloModel.getFormaPagamentoModel() != null) {
                    labelFormaPagamento.setText(lotePagamentoTituloModel.getFormaPagamentoModel().getDescricao());
                }

                if (lotePagamentoTituloModel.getResponsavel() != null) {
                    labelResponsavel.setText(lotePagamentoTituloModel.getResponsavel().getNome());
                }

                if (lotePagamentoTituloModel.getCheque() != null) {
                    labelCheque.setText(lotePagamentoTituloModel.getCheque().getNumeroCheque());
                }

                labelValorTotal.setText(PempecParse.doubleToZero(lotePagamentoTituloModel.getValor()));

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
}//GEN-LAST:event_comboLotePagamentoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoLotePagamento;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JComboBox comboLotePagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelCheque;
    private javax.swing.JLabel labelContabancaria;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelFormaPagamento;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelSituacao;
    private javax.swing.JLabel labelTipoOperacao;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelValorTotal;
    // End of variables declaration//GEN-END:variables
}