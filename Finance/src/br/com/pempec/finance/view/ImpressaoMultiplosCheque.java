package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.reports.FolhaCheque;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Extenso;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.ChequeTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class ImpressaoMultiplosCheque extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaChequeBO contaBancariaChequeBO = new ContaBancariaChequeBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private long tela = Tela.TELA_CONCILIACAO_IMPRESSAO_DE_CHEQUES.getTela();
    String chequeInicial = "";
    String chequeFinal = "";

    private String NameObject() {
        return (String) "Impressão de Multiplos Cheques";
    }

    public void repopularCombos() {

        try {

            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta("    -> Selecione <-   ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboConta.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));


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

    public ImpressaoMultiplosCheque() throws SystemException {

        this.setLocation(250, 50);
        Controller.setQtdMovimentosHoje(0);
        initComponents();

        comboCheque.setEnabled(false);
        comboChequeFinal.setEnabled(false);
        jLabelContaTitularidade.setVisible(false);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        comboConta = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        comboCheque = new javax.swing.JComboBox();
        comboChequeFinal = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelContaTitularidade = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Impressão de Múltiplos Cheques");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

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

        botaoImprimir.setMnemonic('I');
        botaoImprimir.setText("Imprimir");
        botaoImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Conta Bancária"));

        comboConta.setFont(new java.awt.Font("Arial", 0, 10));
        comboConta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Numeração"));

        comboCheque.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        comboCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboChequeActionPerformed(evt);
            }
        });

        comboChequeFinal.setFont(new java.awt.Font("Arial", 0, 10));
        comboChequeFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboChequeFinalActionPerformed(evt);
            }
        });

        jLabel2.setText("Inicial");

        jLabel1.setText("Final");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(comboChequeFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(83, 83, 83))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboChequeFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabelContaTitularidade.setForeground(new java.awt.Color(0, 102, 102));
        jLabelContaTitularidade.setText("jLabel3");
        jLabelContaTitularidade.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Agência / Conta / Titularidade "));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelContaTitularidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelContaTitularidade, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Impressão de Mútiplos Cheques", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, 0, 580, Short.MAX_VALUE)
                        .addGap(11, 11, 11))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        comboConta.setSelectedIndex(0);

        comboCheque.setSelectedItem(null);
        comboChequeFinal.setSelectedItem(null);

        botaoImprimir.setEnabled(false);
        comboCheque.setEnabled(false);
        comboChequeFinal.setEnabled(false);

        jLabelContaTitularidade.setVisible(false);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboConta.getSelectedIndex() == 0) {
            comboConta.requestFocus();
            return false;
        }


        if (comboCheque.getSelectedItem() == null) {
            comboCheque.requestFocus();
            return false;
        }


        if (comboChequeFinal.getSelectedItem() == null) {
            comboChequeFinal.requestFocus();
            return false;
        }

        if (comboCheque.getSelectedItem() != null && comboChequeFinal.getSelectedItem() != null) {

            Long chFinal = PempecParse.stringToLong(comboChequeFinal.getSelectedItem().toString());

            Long cheque = PempecParse.stringToLong(comboCheque.getSelectedItem().toString());

            if (chFinal < cheque) {
                exibeMensagemAviso("O cheque inicial não pode ser maior que o cheque final!\nVerifique a sua seleção", "Erro de seleção!");
                comboChequeFinal.setSelectedIndex(0);
                return false;
            }

        }

        return true;

    }

private void botaoImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirActionPerformed

    if (validaCampos()) {

        try {

            long action = Action.IMPRESSAO.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            ContaBancariaModel contaBancaria = new ContaBancariaModel();
            contaBancaria.setPk(new PKModel());
            contaBancaria.getPk().setOrganizacao(organizacaoModel);

            if (comboConta.getSelectedItem() != null) {

                contaBancaria.setConta(comboConta.getSelectedItem().toString());
            }

            contaBancaria = contaBancariaBO.consultarPorTemplate(contaBancaria);

            if (comboCheque.getSelectedItem() != null && comboChequeFinal.getSelectedItem() != null) {

                chequeInicial = comboCheque.getSelectedItem().toString();
                chequeFinal = comboChequeFinal.getSelectedItem().toString();

            }
            Collection<ContaBancariaChequeModel> collCheque = new ArrayList<ContaBancariaChequeModel>();

            collCheque = contaBancariaChequeBO.obterPorContaBancariaNumeroCheque(contaBancaria, chequeInicial, chequeFinal);

            if (collCheque == null || collCheque.isEmpty()) {

                exibeMensagemAviso("Nenhum CHEQUE encontrado de Numeração : " + chequeInicial + " a " + chequeFinal + " \nConta bancária : " + contaBancaria.getConta(), null);

                return;
            }

            Map<String, Object> parametros = new HashMap<String, Object>();

            Collection<FolhaCheque> collection = new ArrayList<FolhaCheque>();

            for (ContaBancariaChequeModel cheque : collCheque) {

                if (cheque.getQtdImpressao() == null) {

                    cheque.setQtdImpressao(0);

                }

                Integer qtdImpressao = cheque.getQtdImpressao();

                cheque.setQtdImpressao(qtdImpressao++);

                parametros.put(RelatorioConstantes.PARAMETRO_ID_ORGANIZACAO, organizacaoModel.getId());

                FolhaCheque bean = new FolhaCheque();

                if (Controller.getOrganizacao().getCidade() != null) {
                    bean.setCidade(Controller.getOrganizacao().getCidade().getDescricao());
                }

                String extenso = "( " + new Extenso(cheque.getValor()).toString();

                if (extenso.length() < 100) {

                    extenso += " ) ";

                    bean.setExtenso(extenso);

                } else {

                    char aux = extenso.charAt(92);

                    if (aux == ' ') {

                        bean.setExtenso(extenso.substring(0, 92));

                        bean.setExtenso2(extenso.substring(93) + " ) ");

                    } else {

                        int pos = extenso.lastIndexOf(" ", 92);

                        bean.setExtenso(extenso.substring(0, pos));

                        bean.setExtenso2(extenso.substring(pos + 1) + " ) ");

                    }

                }

                bean.setDia(PempecParse.dateToString(cheque.getDataEmissao()).substring(0, 2));
                bean.setMes(PempecUtil.getMesExtenso(cheque.getDataEmissao()));
                bean.setAno(PempecParse.dateToString(cheque.getDataEmissao()).substring(6, 10));
                bean.setPortador(cheque.getPortador());
                bean.setValor(cheque.getValor());

                collection.add(bean);

            }

            if (!contaBancaria.getBanco().getNomeBanco().isEmpty() && contaBancaria.getBanco().getNomeBanco() != null) {

                Integer codigoBanco = Integer.parseInt(contaBancaria.getBanco().getCodigoBanco());

                switch (codigoBanco) {

                    case 237:
                        new RelatorioUtil().imprimirImpressoraCollection(RelatorioConstantes.IMPRESSAO_CHEQUE_BRADESCO, parametros, collection);
                        break;

                    case 001:
                        new RelatorioUtil().imprimirImpressoraCollection(RelatorioConstantes.IMPRESSAO_CHEQUE_BRASIL, parametros, collection);
                        break;

                    case 104:
                        new RelatorioUtil().imprimirImpressoraCollection(RelatorioConstantes.IMPRESSAO_CHEQUE_CEF, parametros, collection);
                        break;

                    default:
                        new RelatorioUtil().imprimirImpressoraCollection(RelatorioConstantes.IMPRESSAO_CHEQUE, parametros, collection);
                        break;
                }


            }

            contaBancariaChequeBO.alterar(collCheque);

            //19/05/2012    new MovimentoDiarioBO().inserir(this.registroMovimento("Impressao Mult Cheque", contaBancaria.getConta(), Controller.getUsuarioLogado().getNome() + " solic impr mult cheq " + "de " + chequeInicial + " até " + chequeFinal + " da conta : " + contaBancaria.getConta(), null, "Impresso"));

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

        exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

    }

}//GEN-LAST:event_botaoImprimirActionPerformed

private void comboContaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaItemStateChanged

    if (comboConta.getSelectedItem() != null
            && ((ContaBancariaModel) comboConta.getSelectedItem()).getPk() != null) {

        try {

            jLabelContaTitularidade.setVisible(true);

            ContaBancariaModel conta = new ContaBancariaModel();

            conta.setPk(new PKModel());
            conta.getPk().setOrganizacao(organizacaoModel);
            conta.setConta(comboConta.getSelectedItem().toString());

            conta = contaBancariaBO.consultarPorTemplate(conta);

            if (conta != null && conta.getPk() != null) {

                String banco = conta.getBanco().getSiglaBanco();

                if (banco == null || banco.isEmpty()) {

                    banco = "#";
                }

                String ag = conta.getAgencia();

                String cc = conta.getConta();

                String titular = conta.getTitular();

                jLabelContaTitularidade.setText(banco + " / " + ag + " / " + cc + " / " + titular);

                TipoStatusModel status = new TipoStatusModel();
                status.setPk(new PKModel());
                status.getPk().setOrganizacao(organizacaoModel);
                status.getPk().setId(Constantes.STATUS_CHEQUE_EMITIDO);


//                Collection<ContaBancariaChequeModel> lContaBancariaCheque = new ArrayList<ContaBancariaChequeModel>();
//
//                ContaBancariaChequeModel cheque = new ContaBancariaChequeModel();
//
//                cheque.setNumeroCheque("    -> Selecione <-   ");
//
//                lContaBancariaCheque.add(cheque);
//
//                lContaBancariaCheque.addAll(contaBancariaChequeBO.obterPorContaBancariaStatus(conta, status));
//
//                comboCheque.setModel(new javax.swing.DefaultComboBoxModel(lContaBancariaCheque.toArray()));
//
//                comboChequeFinal.setModel(new javax.swing.DefaultComboBoxModel(lContaBancariaCheque.toArray()));


                Collection<ContaBancariaChequeModel> lContaBancariaCheque = contaBancariaChequeBO.obterPorContaBancariaStatus((ContaBancariaModel) comboConta.getSelectedItem(), status);

                final EventList<ContaBancariaChequeModel> lRegistrosCheques = GlazedLists.eventList(lContaBancariaCheque);

                if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
                    supportCheque.uninstall();
                }
                supportCheque = AutoCompleteSupport.install(comboCheque, lRegistrosCheques, new ChequeTextFilterator());
                supportCheque.setFilterMode(TextMatcherEditor.STARTS_WITH);
                supportCheque.setStrict(false);



                if (supportChequeFinal != null && supportChequeFinal.getItemList() != null && supportChequeFinal.getComboBox() != null) {
                    supportChequeFinal.uninstall();
                }
                supportChequeFinal = AutoCompleteSupport.install(comboChequeFinal, lRegistrosCheques, new ChequeTextFilterator());
                supportChequeFinal.setFilterMode(TextMatcherEditor.STARTS_WITH);
                supportChequeFinal.setStrict(false);

                //comboCheque.setSelectedIndex(0);
                //comboChequeFinal.setSelectedIndex(0);

                comboCheque.setSelectedItem(null);
                comboChequeFinal.setSelectedItem(null);

                comboCheque.setEnabled(true);
                comboChequeFinal.setEnabled(true);

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

        exibeMensagemAviso("Erro ao carregar!\nInforme ao suporte.", "Erro Grave");

    }
}//GEN-LAST:event_comboContaItemStateChanged

private void comboChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboChequeActionPerformed


    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboCheque.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        if (this.validaCampos()) {

            botaoImprimir.setEnabled(true);

        } else {

            botaoImprimir.setEnabled(false);
        }

    }

}//GEN-LAST:event_comboChequeActionPerformed

private void comboChequeFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboChequeFinalActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboChequeFinal.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        if (this.validaCampos()) {

            botaoImprimir.setEnabled(true);

        } else {

            botaoImprimir.setEnabled(false);
        }

    }

}//GEN-LAST:event_comboChequeFinalActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboCheque;
    private javax.swing.JComboBox comboChequeFinal;
    private javax.swing.JComboBox comboConta;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelContaTitularidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportCheque;
    private AutoCompleteSupport supportChequeFinal;
}
