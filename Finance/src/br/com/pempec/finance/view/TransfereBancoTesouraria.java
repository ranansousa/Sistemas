package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.SacadoBO;
import br.com.pempec.finance.businessObjects.TesourariaCreditoBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.businessObjects.TipoStatusBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.ChequeTextFilterator;
import br.com.pempec.finance.utils.iterators.ContaBancariaDebitoTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class TransfereBancoTesouraria extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaDebitoBO contaBancariaDebitoBO = new ContaBancariaDebitoBO();
    private ContaBancariaChequeBO contaBancariaChequeBO = new ContaBancariaChequeBO();
    private TesourariaCreditoBO tesourariaCreditoBO = new TesourariaCreditoBO();
    private SacadoBO sacadoBO = new SacadoBO();
    private HistoricoBO historicoBO = new HistoricoBO();
    private TipoOperacaoBancariaBO tipoOperacao = new TipoOperacaoBancariaBO();
    private TipoStatusBO tipoStatusBO = new TipoStatusBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private long tela = Tela.TELA_TESOURARIA_TRANSFERIR_BANCO.getTela();

    private String NameObject() {
        return "Transferência de Valores do Banco p/ Tesouraria";
    }

    public TransfereBancoTesouraria() throws SystemException {

        initComponents();

        jFTDataMovimento.setDate(Controller.getDataServidorBD());

    }

    public void repopularCombos() {

        try {

            TipoOperacaoBancariaModel operacao = new TipoOperacaoBancariaModel();
            operacao.setPk(new PKModel());
            operacao.getPk().setOrganizacao(organizacaoModel);
            operacao.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_TESOURARIA);

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            ContaBancariaModel contaBancariaModel = new ContaBancariaModel();

            contaBancariaModel.setConta(" -> Selecione <- ");

            lContas.add(contaBancariaModel);
            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancaria.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            comboContaCheque.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));
            comboResponsavelCheque.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            Collection<ContaBancariaDebitoModel> lTransferencias = contaBancariaDebitoBO.obterTodosPorOperacaoBancaria(organizacaoModel, operacao);

            final EventList<ContaBancariaDebitoModel> lRegistrosTransferencias = GlazedLists.eventList(lTransferencias);
            if (supportTransferencia != null && supportTransferencia.getItemList() != null && supportTransferencia.getComboBox() != null) {
                supportTransferencia.uninstall();
            }
            supportTransferencia = AutoCompleteSupport.install(comboTransferencia, lRegistrosTransferencias, new ContaBancariaDebitoTextFilterator());
            supportTransferencia.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportTransferencia.setStrict(false);

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

        jButton3 = new javax.swing.JButton();
        panelCheques = new javax.swing.JTabbedPane();
        abaTransfEspecie1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        comboContaCheque = new javax.swing.JComboBox();
        labelSacado = new javax.swing.JLabel();
        comboCheque = new javax.swing.JComboBox();
        jFTDataCheque = new org.jdesktop.swingx.JXDatePicker();
        labelDataProtocolo1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFValorCheque = new br.com.pempec.componentes.JDoubleField();
        labelResponsavel1 = new javax.swing.JLabel();
        comboResponsavelCheque = new javax.swing.JComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        botaoFechar1 = new javax.swing.JButton();
        botaoLimparCheque = new javax.swing.JButton();
        botaoTransfereCheque = new javax.swing.JButton();
        abaTransfEspecie = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoTransfere = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jValorTransfEspecie = new br.com.pempec.componentes.JDoubleField();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboContaBancaria = new javax.swing.JComboBox();
        labelDataProtocolo = new javax.swing.JLabel();
        jFTDataMovimento = new org.jdesktop.swingx.JXDatePicker();
        labelResponsavel = new javax.swing.JLabel();
        comboResponsavel = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        comboTransferencia = new javax.swing.JComboBox();
        labelValorTransf = new javax.swing.JLabel();
        labelContaOrigemTransf = new javax.swing.JLabel();
        labelChequeTransf = new javax.swing.JLabel();
        labelDataTransf = new javax.swing.JLabel();
        labelResponsavelTransf = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        botaoFecharTransf = new javax.swing.JButton();
        botaoLimparTransf = new javax.swing.JButton();
        botaoEstornarTransf = new javax.swing.JButton();

        jButton3.setText("jButton3");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Transfere do Banco para Tesouraria");

        abaTransfEspecie1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(), javax.swing.BorderFactory.createCompoundBorder()));

        jSeparator3.setForeground(new java.awt.Color(51, 51, 0));
        jSeparator3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.blue, null));

        jLabel11.setText("Conta Bancária");

        comboContaCheque.setFont(new java.awt.Font("Arial", 0, 10));
        comboContaCheque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaChequeItemStateChanged(evt);
            }
        });

        labelSacado.setText("Cheque");

        comboCheque.setFont(new java.awt.Font("Arial", 0, 10));

        labelDataProtocolo1.setText("Data Movimento");

        jLabel3.setText("Valor");

        labelResponsavel1.setText("Responsável");

        comboResponsavelCheque.setFont(new java.awt.Font("Arial", 0, 11));

        jSeparator4.setForeground(new java.awt.Color(51, 51, 0));
        jSeparator4.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.blue, null));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoFechar1.setMnemonic('F');
        botaoFechar1.setText("Fechar");
        botaoFechar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFechar1ActionPerformed(evt);
            }
        });

        botaoLimparCheque.setMnemonic('L');
        botaoLimparCheque.setText("Limpar");
        botaoLimparCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparChequeActionPerformed(evt);
            }
        });

        botaoTransfereCheque.setMnemonic('L');
        botaoTransfereCheque.setText("Transfere");
        botaoTransfereCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoTransfereChequeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoTransfereCheque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(botaoLimparCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoTransfereCheque, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoLimparCheque, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout abaTransfEspecie1Layout = new javax.swing.GroupLayout(abaTransfEspecie1);
        abaTransfEspecie1.setLayout(abaTransfEspecie1Layout);
        abaTransfEspecie1Layout.setHorizontalGroup(
            abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                                .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(comboContaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSacado)
                                    .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDataProtocolo1)
                                    .addComponent(jFTDataCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                                            .addGap(461, 461, 461)
                                            .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jFValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jSeparator4))
                                    .addComponent(labelResponsavel1)
                                    .addComponent(comboResponsavelCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        abaTransfEspecie1Layout.setVerticalGroup(
            abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jFValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(31, 31, 31))
                    .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                        .addComponent(labelSacado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboContaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaTransfEspecie1Layout.createSequentialGroup()
                        .addComponent(labelDataProtocolo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTDataCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelResponsavel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboResponsavelCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(abaTransfEspecie1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        panelCheques.addTab("Transferência em Cheque", abaTransfEspecie1);

        abaTransfEspecie.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(), javax.swing.BorderFactory.createCompoundBorder()));

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

        botaoTransfere.setMnemonic('L');
        botaoTransfere.setText("Transfere");
        botaoTransfere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoTransfereActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoTransfere)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoTransfere, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSeparator2.setForeground(new java.awt.Color(51, 51, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.blue, null));

        jLabel2.setText("Valor");

        jLabel10.setText("Conta Bancária");

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 10));

        labelDataProtocolo.setText("Data Movimento");

        labelResponsavel.setText("Responsável");

        comboResponsavel.setFont(new java.awt.Font("Arial", 0, 11));

        javax.swing.GroupLayout abaTransfEspecieLayout = new javax.swing.GroupLayout(abaTransfEspecie);
        abaTransfEspecie.setLayout(abaTransfEspecieLayout);
        abaTransfEspecieLayout.setHorizontalGroup(
            abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTransfEspecieLayout.createSequentialGroup()
                .addGroup(abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaTransfEspecieLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaTransfEspecieLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaTransfEspecieLayout.createSequentialGroup()
                                .addGroup(abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDataProtocolo)
                                    .addComponent(jFTDataMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jValorTransfEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelResponsavel)
                                    .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        abaTransfEspecieLayout.setVerticalGroup(
            abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTransfEspecieLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaTransfEspecieLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaTransfEspecieLayout.createSequentialGroup()
                        .addComponent(labelDataProtocolo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaTransfEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTDataMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jValorTransfEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaTransfEspecieLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(31, 31, 31))
                    .addGroup(abaTransfEspecieLayout.createSequentialGroup()
                        .addComponent(labelResponsavel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        panelCheques.addTab("Transferência em Espécie", abaTransfEspecie);

        jLabel12.setText("Transferência");

        comboTransferencia.setToolTipText("");
        comboTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTransferenciaActionPerformed(evt);
            }
        });

        labelValorTransf.setForeground(new java.awt.Color(204, 0, 0));
        labelValorTransf.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelValorTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor"));

        labelContaOrigemTransf.setFont(new java.awt.Font("Arial", 0, 10));
        labelContaOrigemTransf.setForeground(new java.awt.Color(0, 102, 102));
        labelContaOrigemTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Conta Bancária Origem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelChequeTransf.setFont(new java.awt.Font("Arial", 0, 10));
        labelChequeTransf.setForeground(new java.awt.Color(0, 102, 102));
        labelChequeTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cheque Vinculado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelDataTransf.setForeground(new java.awt.Color(204, 0, 0));
        labelDataTransf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Movimento"));

        labelResponsavelTransf.setFont(new java.awt.Font("Arial", 0, 10));
        labelResponsavelTransf.setForeground(new java.awt.Color(0, 102, 102));
        labelResponsavelTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Responsável", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoFecharTransf.setMnemonic('F');
        botaoFecharTransf.setText("Fechar");
        botaoFecharTransf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharTransfActionPerformed(evt);
            }
        });

        botaoLimparTransf.setMnemonic('L');
        botaoLimparTransf.setText("Limpar");
        botaoLimparTransf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparTransfActionPerformed(evt);
            }
        });

        botaoEstornarTransf.setMnemonic('L');
        botaoEstornarTransf.setText("Estornar");
        botaoEstornarTransf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstornarTransfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoEstornarTransf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(botaoLimparTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFecharTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoEstornarTransf, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharTransf, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoLimparTransf, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelContaOrigemTransf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboTransferencia, javax.swing.GroupLayout.Alignment.LEADING, 0, 280, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelValorTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDataTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelChequeTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelResponsavelTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelValorTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelContaOrigemTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addComponent(labelChequeTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelResponsavelTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        panelCheques.addTab("Estornar Transferências", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(panelCheques, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCheques, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        jFTDataMovimento.setDate(Controller.getDataServidorBD());
        comboResponsavel.setSelectedIndex(0);
        comboContaBancaria.setSelectedIndex(0);
        jValorTransfEspecie.setText("0,00");

}//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoTransfereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoTransfereActionPerformed

        if (comboContaBancaria.getSelectedItem() != null && !comboContaBancaria.getSelectedItem().toString().isEmpty()) {

            String valorCombo = comboContaBancaria.getSelectedItem().toString();

            if (validaCamposTransferenciaEspecie()) {

                try {

                    long action = Action.OUTROS.getAction();

                    if (!Controller.checarPermissao(tela, action)) {
                        exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                        return;
                    }

                    SacadoModel sacado = new SacadoModel();
                    sacado.setPk(new PKModel());
                    sacado.getPk().setOrganizacao(organizacaoModel);
                    sacado.getPk().setId(Controller.getOrganizacao().getId());

                    sacado = sacadoBO.consultarPorPk(sacado);

                    if (sacado == null || sacado.getPk().getId().isEmpty()) {
                        if (exibeMensagemConfirmacao("Sacado não localizado. \nDeseja que o sistema insira automaticamente ?", "Erro de Sacado")) {

                            sacado = inserirSacadoOrganizacao();

                        } else {

                            sacado = null;
                        }
                    }

                    if (sacado == null || sacado.getPk().getId().isEmpty()) {

                        exibeMensagemAviso("Sacado não localizado. \nErro do Sacado igual a Organizacao", "Erro de Sacado");
                        return;
                    }

                    HistoricoModel historico = new HistoricoModel();
                    historico.setPk(new PKModel());
                    historico.getPk().setOrganizacao(organizacaoModel);
                    historico.getPk().setId(Constantes.HISTORICO_TESOURARIA_TRANSFERE_BCO_TESOURARIA);

                    historico = historicoBO.consultarPorPk(historico);

                    if (historico == null || historico.getPk().getId().isEmpty()) {
                        exibeMensagemAviso("Historico não localizado.", "Erro de Historico");
                        return;
                    }

                    TipoOperacaoBancariaModel operacaoBancaria = new TipoOperacaoBancariaModel();
                    operacaoBancaria.setPk(new PKModel());
                    operacaoBancaria.getPk().setOrganizacao(organizacaoModel);
                    operacaoBancaria.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_TESOURARIA);

                    operacaoBancaria = tipoOperacao.consultarPorPk(operacaoBancaria);

                    if (operacaoBancaria == null || operacaoBancaria.getPk().getId().isEmpty()) {

                        exibeMensagemAviso("Operação Bancária não localizada.", "Erro de Operação Bancária");
                        return;
                    }

                    ContaBancariaModel contaBancariaModel = new ContaBancariaModel();
                    contaBancariaModel.setPk(new PKModel());
                    contaBancariaModel.getPk().setOrganizacao(organizacaoModel);
                    contaBancariaModel.setConta(valorCombo);

                    contaBancariaModel = contaBancariaBO.consultarPorTemplate(contaBancariaModel);

                    FuncionarioModel responsavel = new FuncionarioModel();
                    responsavel.setPk(new PKModel());
                    responsavel.getPk().setOrganizacao(organizacaoModel);
                    responsavel.setNome(comboResponsavel.getSelectedItem().toString());

                    responsavel = funcionarioBO.consultarPorTemplate(responsavel);

                    if (responsavel == null || responsavel.getPk().getId().isEmpty()) {
                        exibeMensagemAviso("Responsavel não localizado.", "Erro de Responsavel");
                        return;
                    }

                    if (contaBancariaModel.getPk().getId() != null) {

                        TesourariaCreditoModel tesouraria = new TesourariaCreditoModel();

                        tesouraria.setDataMovimento(PempecParse.dateToDate(jFTDataMovimento.getDate()));

                        if (responsavel.getPk().getId() != null) {
                            tesouraria.setResponsavel(responsavel);
                        }

                        if (historico.getPk().getId() != null) {
                            tesouraria.setHistorico(historico);
                        }

                        if (sacado.getPk().getId() != null) {
                            tesouraria.setSacadoModel(sacado);
                        }

                        tesouraria.setPk(new PKModel());
                        tesouraria.getPk().setOrganizacao(organizacaoModel);
                        tesouraria.getPk().setId(PempecKeyGenerator.generateKey());
                        tesouraria.setNumeroDocumento(PempecKeyGenerator.generateNumeroDocumentoTesourariaCredito());
                        tesouraria.setValorNominal(PempecParse.stringToDouble(jValorTransfEspecie.getText()));
                        tesouraria.setDataContabil(PempecParse.dateToDate(jFTDataMovimento.getDate()));
                        tesouraria.setDataMovimento(PempecParse.dateToDate(jFTDataMovimento.getDate()));
                        tesouraria.setDataRegistro(Controller.getDataServidorBD());
                        //tesouraria.setDescricao(Constantes.DESCRICAO_BANCO_TESOURARIA_CR);
                        tesouraria.setDescricao("DA CONTA  " + contaBancariaModel.getConta()); //+ " Nº " + tesouraria.getNumeroDocumento());

                        tesouraria.setTipoLancamento("C");

                        // parte do debito na conta
                        ContaBancariaDebitoModel contaDebito = new ContaBancariaDebitoModel();
                        contaDebito.setPk(new PKModel());
                        contaDebito.getPk().setOrganizacao(organizacaoModel);
                        contaDebito.getPk().setId(PempecKeyGenerator.generateKey());
                        contaDebito.setContaBancaria(contaBancariaModel);

                        contaDebito.setDataContabil(PempecParse.dateToDate(jFTDataMovimento.getDate()));
                        contaDebito.setDataRegistro(Controller.getDataServidorBD());
                        contaDebito.setDataMovimento(PempecParse.dateToDate(jFTDataMovimento.getDate()));

                        contaDebito.setDescricao("TRANSF. DO BCO P/ TES.  ");
                        contaDebito.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaDebito());
                        contaDebito.setResponsavel(responsavel);
                        contaDebito.setTipoLancamento("D");

                        contaDebito.setTipoOperacaoBancaria(operacaoBancaria);

                        contaDebito.setValor(tesouraria.getValorNominal());
                        contaDebito.setUsuario(Controller.getUsuarioLogado());

                        tesouraria.setContaBancariaDebitoModel(contaDebito);

                        contaBancariaDebitoBO.transfereBancoTesouraria(contaDebito, tesouraria);

                        this.repopularCombos();
                        
                        this.botaoLimparActionPerformed(evt);

                        
                        
                    } else {

                        exibeMensagemAviso("Problemas com a conta bancária", "Erro ao transferir");
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
                exibeMensagemAviso("Campo(s) obrigatório(s)!", null);

            }

        }

}//GEN-LAST:event_botaoTransfereActionPerformed

    private void comboContaChequeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaChequeItemStateChanged

        if (comboContaCheque.getSelectedItem() != null
                && ((ContaBancariaModel) comboContaCheque.getSelectedItem()).getPk() != null) {

            try {

                TipoStatusModel status = new TipoStatusModel();
                status.setPk(new PKModel());
                status.getPk().setId(Constantes.STATUS_CHEQUE_NOVO);

                Collection<ContaBancariaChequeModel> lContaBancariaCheque = contaBancariaChequeBO.obterPorContaBancariaStatus((ContaBancariaModel) comboContaCheque.getSelectedItem(), status);

                final EventList<ContaBancariaChequeModel> lRegistrosCheques = GlazedLists.eventList(lContaBancariaCheque);
                if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
                    supportCheque.uninstall();
                }
                supportCheque = AutoCompleteSupport.install(comboCheque, lRegistrosCheques, new ChequeTextFilterator());
                supportCheque.setFilterMode(TextMatcherEditor.STARTS_WITH);
                supportCheque.setStrict(false);

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

            if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
                supportCheque.uninstall();
            }

        }

}//GEN-LAST:event_comboContaChequeItemStateChanged

    private void botaoLimparChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparChequeActionPerformed

        comboContaCheque.setSelectedIndex(0);
        comboTransferencia.setSelectedItem(null);

        if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
            supportCheque.uninstall();
        }

        jFTDataCheque.setDate(null);
        jFValorCheque.setText("0,00");
        comboResponsavelCheque.setSelectedIndex(0);

        labelChequeTransf.setText("");
        labelContaOrigemTransf.setText("");
        labelDataTransf.setText("");
        labelResponsavelTransf.setText("");
        labelValorTransf.setText("");


    }//GEN-LAST:event_botaoLimparChequeActionPerformed

    private void botaoFechar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFechar1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFechar1ActionPerformed

    private void botaoTransfereChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoTransfereChequeActionPerformed

        if (comboContaCheque.getSelectedItem() != null && !comboContaBancaria.getSelectedItem().toString().isEmpty()) {

            String valorCombo = comboContaCheque.getSelectedItem().toString();

            if (comboCheque.getSelectedItem() != null && !comboCheque.getSelectedItem().toString().isEmpty()) {

                String numeroCheque = comboCheque.getSelectedItem().toString();

                if (validaCamposTransferenciaCheque()) {

                    try {

                        long action = Action.OUTROS.getAction();

                        if (!Controller.checarPermissao(tela, action)) {
                            exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                            return;
                        }

                        SacadoModel sacado = new SacadoModel();
                        sacado.setPk(new PKModel());
                        sacado.getPk().setOrganizacao(organizacaoModel);
                        sacado.getPk().setId(Controller.getOrganizacao().getId());

                        sacado = sacadoBO.consultarPorPk(sacado);

                        if (sacado == null || sacado.getPk().getId().isEmpty()) {
                            if (exibeMensagemConfirmacao("Sacado não localizado. \nDeseja que o sistema insira automaticamente ?", "Erro de Sacado")) {

                                sacado = inserirSacadoOrganizacao();

                            } else {

                                sacado = null;
                            }
                        }

                        if (sacado != null && !sacado.getPk().getId().isEmpty()) {

                            if (!Controller.getOrganizacao().getId().equalsIgnoreCase(sacado.getPk().getId())) {
                                exibeMensagemAviso("Problemas com o sacado. \nErro do Sacado igual a Organizacao", "Erro de Sacado");
                                return;
                            }
                        }

                        if (sacado == null || sacado.getPk().getId().isEmpty()) {
                            exibeMensagemAviso("Sacado não localizado. \nErro do Sacado igual a Organizacao", "Erro de Sacado");
                            return;
                        }

                        HistoricoModel historico = new HistoricoModel();
                        historico.setPk(new PKModel());
                        historico.getPk().setOrganizacao(organizacaoModel);
                        historico.getPk().setId(Constantes.HISTORICO_TESOURARIA_TRANSFERE_BCO_TESOURARIA);

                        historico = historicoBO.consultarPorPk(historico);

                        if (historico == null || historico.getPk().getId().isEmpty()) {
                            exibeMensagemAviso("Historico não localizado.", "Erro de Historico");
                            return;
                        }

                        TipoOperacaoBancariaModel operacaoBancaria = new TipoOperacaoBancariaModel();
                        operacaoBancaria.setPk(new PKModel());
                        operacaoBancaria.getPk().setOrganizacao(organizacaoModel);
                        operacaoBancaria.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_TESOURARIA);

                        operacaoBancaria = tipoOperacao.consultarPorPk(operacaoBancaria);

                        if (operacaoBancaria == null || operacaoBancaria.getPk().getId().isEmpty()) {
                            exibeMensagemAviso("Operacao Bancaria não localizada.", "Erro de Operacao Bancaria");
                            return;
                        }

                        ContaBancariaChequeModel cheque = new ContaBancariaChequeModel();
                        cheque.setPk(new PKModel());
                        cheque.getPk().setOrganizacao(organizacaoModel);
                        cheque.setNumeroCheque(numeroCheque);

                        cheque = contaBancariaChequeBO.consultarPorTemplate(cheque);

                        if (cheque == null || cheque.getPk().getId().isEmpty()) {
                            exibeMensagemAviso("Cheque não localizado.", "Erro de emissão do cheque.");
                            return;
                        }

                        ContaBancariaModel contaBancariaModel = new ContaBancariaModel();
                        contaBancariaModel.setPk(new PKModel());
                        contaBancariaModel.getPk().setOrganizacao(organizacaoModel);
                        contaBancariaModel.setConta(valorCombo);

                        contaBancariaModel = contaBancariaBO.consultarPorTemplate(contaBancariaModel);

                        FuncionarioModel responsavel = new FuncionarioModel();
                        responsavel.setPk(new PKModel());
                        responsavel.getPk().setOrganizacao(organizacaoModel);
                        responsavel.setNome(comboResponsavelCheque.getSelectedItem().toString());

                        responsavel = funcionarioBO.consultarPorTemplate(responsavel);

                        if (responsavel == null || responsavel.getPk().getId().isEmpty()) {
                            exibeMensagemAviso("Responsavel não localizado.", "Erro de Responsavel");
                            return;
                        }

                        TipoStatusModel status = new TipoStatusModel();
                        status.setPk(new PKModel());
                        status.getPk().setOrganizacao(organizacaoModel);
                        status.getPk().setId(Constantes.STATUS_CHEQUE_COMPENSADO);
                        status = tipoStatusBO.consultarPorPk(status);

                        if (status == null || status.getPk().getId().isEmpty()) {
                            exibeMensagemAviso("Cheque não compensado.", "Erro de status do cheque.");
                            return;
                        }

                        if (contaBancariaModel.getPk().getId() != null) {

                            TesourariaCreditoModel tesouraria = new TesourariaCreditoModel();

                            tesouraria.setDataMovimento(jFTDataCheque.getDate());

                            if (responsavel.getPk().getId() != null) {
                                tesouraria.setResponsavel(responsavel);
                            }

                            if (historico.getPk().getId() != null) {
                                tesouraria.setHistorico(historico);
                            }

                            if (sacado.getPk().getId() != null) {
                                tesouraria.setSacadoModel(sacado);
                            }

                            tesouraria.setPk(new PKModel());
                            tesouraria.getPk().setOrganizacao(organizacaoModel);
                            tesouraria.getPk().setId(PempecKeyGenerator.generateKey());
                            tesouraria.setNumeroDocumento(PempecKeyGenerator.generateNumeroDocumentoTesourariaCredito());
                            tesouraria.setValorNominal(PempecParse.stringToDouble(jFValorCheque.getText()));

                            tesouraria.setDataContabil(PempecParse.dateToDate(jFTDataCheque.getDate()));
                            tesouraria.setDataMovimento(PempecParse.dateToDate(jFTDataCheque.getDate()));

                            tesouraria.setDataRegistro(Controller.getDataServidorBD());

                            tesouraria.setDescricao("CHEQUE " + numeroCheque + " DA CONTA  " + contaBancariaModel.getConta());

                            tesouraria.setTipoLancamento("C");

                            // parte do debito na conta
                            ContaBancariaDebitoModel contaDebito = new ContaBancariaDebitoModel();
                            contaDebito.setPk(new PKModel());
                            contaDebito.getPk().setOrganizacao(organizacaoModel);
                            contaDebito.getPk().setId(PempecKeyGenerator.generateKey());
                            contaDebito.setContaBancaria(contaBancariaModel);

                            contaDebito.setDataContabil(PempecParse.dateToDate(jFTDataCheque.getDate()));

                            contaDebito.setDataRegistro(Controller.getDataServidorBD());

                            contaDebito.setDataMovimento(PempecParse.dateToDate(jFTDataCheque.getDate()));

                            contaDebito.setDescricao("TRANSF. DO BANCO PARA TESOURARIA ");
                            contaDebito.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaDebito());
                            contaDebito.setResponsavel(responsavel);
                            contaDebito.setTipoLancamento("D");

                            contaDebito.setTipoOperacaoBancaria(operacaoBancaria);

                            contaDebito.setValor(tesouraria.getValorNominal());
                            contaDebito.setUsuario(Controller.getUsuarioLogado());

                            // compensando o cheque
                            cheque.setDataCompensado(PempecParse.dateToDate(jFTDataCheque.getDate()));
                            cheque.setDataEmissao(PempecParse.dateToDate(jFTDataCheque.getDate()));
                            cheque.setDataPrevisaoCompensacao(PempecParse.dateToDate(jFTDataCheque.getDate()));
                            cheque.setObservacao(" TRANSF DO BCO P/ TES.  TES.CRE : " + tesouraria.getNumeroDocumento() + " CONTA BCO DEB : " + contaDebito.getIdentificacao());
                            cheque.setUsuario(Controller.getUsuarioLogado());
                            cheque.setStatus(status);
                            cheque.setPortador(sacado.getNome().toUpperCase());
                            cheque.setValor(jFValorCheque.getValue());

                            contaDebito.setContaBancariaCheque(cheque);

                            tesouraria.setContaBancariaDebitoModel(contaDebito);

                            contaBancariaDebitoBO.transfereBancoTesouraria(contaDebito, tesouraria);

                            this.repopularCombos();
                            this.botaoLimparChequeActionPerformed(evt);

                        } else {

                            exibeMensagemAviso("Problemas com a conta bancária", "Erro ao transferir");
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

                    exibeMensagemAviso("Campo(s) obrigatório(s)!", null);

                }

            }

        }
    }//GEN-LAST:event_botaoTransfereChequeActionPerformed

    private void comboTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTransferenciaActionPerformed

        String evento = evt.getActionCommand();
        int modifiers = evt.getModifiers();

        boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
        boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

        if (comboTransferencia.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

            try {

                ContaBancariaDebitoModel tab = new ContaBancariaDebitoModel();

                ContaBancariaChequeModel cheque = new ContaBancariaChequeModel();
                cheque.setPk(new PKModel());
                cheque.getPk().setOrganizacao(Controller.getOrganizacao());

                tab.setIdentificacao(comboTransferencia.getSelectedItem().toString());
                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(Controller.getOrganizacao());

                tab = contaBancariaDebitoBO.consultarPorPk((ContaBancariaDebitoModel) comboTransferencia.getSelectedItem());

                if (tab != null && tab.getPk() != null) {

                    if (tab.getResponsavel() != null && tab.getResponsavel().getNome() != null) {

                        labelResponsavelTransf.setText(tab.getResponsavel().getNome().toUpperCase());
                    }

                    if (tab.getContaBancariaCheque() != null) {

                        cheque = tab.getContaBancariaCheque();

                        cheque = contaBancariaChequeBO.consultarPorTemplate(cheque);

                        labelChequeTransf.setText("Cta " + cheque.getContaBancaria().getConta() + " " + "Ch " + cheque.getNumeroCheque());

                    } else {

                        labelChequeTransf.setText("Lançamento em espécie");
                    }

                    labelValorTransf.setText(PempecParse.doubleToZero(tab.getValor()));

                    labelDataTransf.setText(PempecParse.dateToString(tab.getDataMovimento()));

                    labelContaOrigemTransf.setText(tab.getContaBancaria().getConta());

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
}//GEN-LAST:event_comboTransferenciaActionPerformed

    private void botaoFecharTransfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharTransfActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharTransfActionPerformed

    private void botaoLimparTransfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparTransfActionPerformed

        comboTransferencia.setSelectedItem(null);
        labelChequeTransf.setText("");
        labelContaOrigemTransf.setText("");
        labelDataTransf.setText("");
        labelResponsavelTransf.setText("");
        labelValorTransf.setText("");

}//GEN-LAST:event_botaoLimparTransfActionPerformed

    private void botaoEstornarTransfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstornarTransfActionPerformed

        if (comboTransferencia.getSelectedItem() != null) {

            try {

                long action = Action.OUTROS.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                TipoStatusModel status = new TipoStatusModel();
                status.setPk(new PKModel());
                status.getPk().setOrganizacao(organizacaoModel);
                status.getPk().setId(Constantes.STATUS_CHEQUE_NOVO);
                status = tipoStatusBO.consultarPorPk(status);

                ContaBancariaDebitoModel tab = new ContaBancariaDebitoModel();

                TesourariaCreditoModel tesouraria = new TesourariaCreditoModel();
                tesouraria.setPk(new PKModel());
                tesouraria.getPk().setOrganizacao(Controller.getOrganizacao());

                tab.setIdentificacao(comboTransferencia.getSelectedItem().toString());
                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(Controller.getOrganizacao());

                tab = contaBancariaDebitoBO.consultarPorPk((ContaBancariaDebitoModel) comboTransferencia.getSelectedItem());

                if (tab != null && tab.getPk() != null) {

                    tesouraria.setContaBancariaDebitoModel(tab);

                    if (tab.getContaBancariaCheque() != null && tab.getContaBancariaCheque().getPk() != null) {

                        tesouraria = tesourariaCreditoBO.consultarPorTemplate(tesouraria);

                        tab.getContaBancariaCheque().setValor(null);
                        tab.getContaBancariaCheque().setDataCompensado(null);
                        tab.getContaBancariaCheque().setDataEstornado(Controller.getDataServidorBD());
                        tab.getContaBancariaCheque().setDataPrevisaoCompensacao(null);
                        tab.getContaBancariaCheque().setPortador(null);
                        tab.getContaBancariaCheque().setObservacao("");

                        //temporariamente o cheque podera ser utilizado
                        tab.getContaBancariaCheque().setStatus(status);

                    }

                    if (tesouraria != null && tesouraria.getContaBancariaDebitoModel() != null) {

                        contaBancariaDebitoBO.estornoTransferenciaBancoTesouraria(tab, tesouraria);

                    } else {

                        exibeMensagemAviso("Impossível estornar lançamento! \n Falta de parâmetros!", "Erro ao estornar");
                    }

                    this.botaoLimparChequeActionPerformed(evt);

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

            exibeMensagemAviso("Campo(s) obrigatório(s)!", null);
        }

}//GEN-LAST:event_botaoEstornarTransfActionPerformed

    private Boolean validaCamposTransferenciaEspecie() {

        if (jValorTransfEspecie.getText().equals("0,00")) {
            jValorTransfEspecie.requestFocus();
            return false;
        }

        if (comboContaBancaria.getSelectedIndex() == 0) {
            comboContaBancaria.requestFocus();
            return false;
        }

        if (comboResponsavel.getSelectedIndex() == 0) {
            comboResponsavel.requestFocus();
            return false;
        }

        if (jFTDataMovimento.getDate() == null) {
            jFTDataMovimento.requestFocus();
            return false;
        }

        //validar data
        if (jFTDataMovimento.getDate().after(Controller.getDataServidorBD())) {
            exibeMensagemAviso("Data do movimento não pode ser superior a hoje!", null);
            jFTDataMovimento.requestFocus();
            return false;
        }

        return true;

    }

    private SacadoModel inserirSacadoOrganizacao() {
        SacadoModel sacado = new SacadoModel();
        try {

            sacado.setPk(new PKModel());
            sacado.getPk().setOrganizacao(organizacaoModel);
            sacado.getPk().setId(Controller.getOrganizacao().getId());
            sacado.setNome(Controller.getOrganizacao().getRazaoSocial());
            sacado.setCpfCnpj(Controller.getOrganizacao().getCnpj());

            sacadoBO.inserir(sacado);

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
        return sacado;
    }

    private Boolean validaCamposTransferenciaCheque() {

        if (jFValorCheque.getText().equals("0,00")) {
            jFValorCheque.requestFocus();
            return false;
        }

        if (comboContaCheque.getSelectedIndex() == 0) {
            comboContaCheque.requestFocus();
            return false;
        }

        if (comboCheque.getSelectedItem() == null) {
            comboCheque.requestFocus();
            return false;
        }

        if (comboResponsavelCheque.getSelectedIndex() == 0) {
            comboResponsavelCheque.requestFocus();
            return false;
        }

        if (jFTDataCheque.getDate() == null) {
            jFTDataCheque.requestFocus();
            return false;
        }

        //validar data
        if (jFTDataCheque.getDate().after(Controller.getDataServidorBD())) {
            exibeMensagemAviso("Data do movimento não pode ser superior a hoje!", null);
            jFTDataCheque.requestFocus();
            return false;
        }

        return true;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaTransfEspecie;
    private javax.swing.JPanel abaTransfEspecie1;
    protected javax.swing.JButton botaoEstornarTransf;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoFechar1;
    private javax.swing.JButton botaoFecharTransf;
    protected javax.swing.JButton botaoLimpar;
    protected javax.swing.JButton botaoLimparCheque;
    protected javax.swing.JButton botaoLimparTransf;
    protected javax.swing.JButton botaoTransfere;
    protected javax.swing.JButton botaoTransfereCheque;
    private javax.swing.JComboBox comboCheque;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboContaCheque;
    private javax.swing.JComboBox comboResponsavel;
    private javax.swing.JComboBox comboResponsavelCheque;
    private javax.swing.JComboBox comboTransferencia;
    private javax.swing.JButton jButton3;
    private org.jdesktop.swingx.JXDatePicker jFTDataCheque;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimento;
    private br.com.pempec.componentes.JDoubleField jFValorCheque;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private br.com.pempec.componentes.JDoubleField jValorTransfEspecie;
    private javax.swing.JLabel labelChequeTransf;
    private javax.swing.JLabel labelContaOrigemTransf;
    private javax.swing.JLabel labelDataProtocolo;
    private javax.swing.JLabel labelDataProtocolo1;
    private javax.swing.JLabel labelDataTransf;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelResponsavel1;
    private javax.swing.JLabel labelResponsavelTransf;
    private javax.swing.JLabel labelSacado;
    private javax.swing.JLabel labelValorTransf;
    private javax.swing.JTabbedPane panelCheques;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportCheque;
    private AutoCompleteSupport supportTransferencia;

}
