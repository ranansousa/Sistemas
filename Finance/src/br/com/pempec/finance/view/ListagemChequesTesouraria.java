package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaChequeBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.reports.ReciboDepositoCheques;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.tables.ColumnModelListagemCheques;
import br.com.pempec.finance.utils.tables.TableModelListagemCheques;
import java.awt.FontMetrics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class ListagemChequesTesouraria extends FinanceInternalFrame implements IRepopulador {

    private TesourariaDebitoBO tesourariaDebitoBO = new TesourariaDebitoBO();
    private TituloReceberBaixaChequeBO tituloReceberBaixaChequeBO = new TituloReceberBaixaChequeBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    //Variavel que ira guardar os cheques selecionados para impressao no relatorio.
    private Collection<TituloReceberBaixaChequeModel> collCheques;

    private String NameObject() {
        return (String) "Listagem de Cheques";
    }

    public void repopularCombos() {

        try {
            // responsavel
            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Selecione <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancaria.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            List<TituloReceberBaixaChequeModel> lCheques = tituloReceberBaixaChequeBO.obterDepositaveis(organizacaoModel);

            tableCheques.setAutoCreateColumnsFromModel(false);
            tableCheques.setModel(new TableModelListagemCheques(lCheques));
            FontMetrics fm = tableCheques.getFontMetrics(tableCheques.getFont());
            tableCheques.setColumnModel(new ColumnModelListagemCheques(fm));
            tableCheques.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableCheques.getTableHeader().setReorderingAllowed(false);

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

    public ListagemChequesTesouraria() throws SystemException {

        initComponents();
        jFTDataMovimento.setDate(Controller.getDataServidorBD());
        panelDadosDeposito.setVisible(false);
        botaoImprimir.setEnabled(false);
        botaoDepositar.setEnabled(false);
        panelFormato.setVisible(false);
        jFTDataMovimento.setEnabled(true);
        comboContaBancaria.setEnabled(true);
        comboFuncionario.setEnabled(true);
        tableCheques.setEnabled(true);
        jCSelTodos.setEnabled(true);

        jLoteDeposito.setVisible(false); // apenas guarda o valor do lote

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoDepositar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaCompensarCheque = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCheques = new javax.swing.JTable();
        panelDeposito = new javax.swing.JPanel();
        jFTDataMovimento = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboFuncionario = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        comboContaBancaria = new javax.swing.JComboBox();
        jCSelTodos = new javax.swing.JCheckBox();
        jLoteDeposito = new javax.swing.JLabel();
        panelDadosDeposito = new javax.swing.JPanel();
        labelTitularContaBancaria = new javax.swing.JLabel();
        labelAgencia = new javax.swing.JLabel();
        labelConta = new javax.swing.JLabel();
        labelBanco = new javax.swing.JLabel();
        panelFormato = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Depósito de Cheques");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));

        botaoDepositar.setMnemonic('I');
        botaoDepositar.setText("Depositar");
        botaoDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDepositarActionPerformed(evt);
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
                .addComponent(botaoDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        abaCompensarCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));

        tableCheques.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        tableCheques.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ));
        jScrollPane2.setViewportView(tableCheques);

        panelDeposito.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)), "Dados do Depósito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        jLabel1.setText("Data Depósito");

        jLabel7.setText("Responsável");

        comboFuncionario.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel10.setText("Conta Bancária");

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 10));
        comboContaBancaria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaBancariaItemStateChanged(evt);
            }
        });

        jCSelTodos.setText("Selecionar Todos Cheques");
        jCSelTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCSelTodosActionPerformed(evt);
            }
        });

        jLoteDeposito.setBackground(new java.awt.Color(0, 153, 102));
        jLoteDeposito.setBorder(javax.swing.BorderFactory.createTitledBorder("Lote"));

        javax.swing.GroupLayout panelDepositoLayout = new javax.swing.GroupLayout(panelDeposito);
        panelDeposito.setLayout(panelDepositoLayout);
        panelDepositoLayout.setHorizontalGroup(
            panelDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDepositoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFTDataMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(panelDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(panelDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(panelDepositoLayout.createSequentialGroup()
                        .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCSelTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLoteDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelDepositoLayout.setVerticalGroup(
            panelDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDepositoLayout.createSequentialGroup()
                .addGroup(panelDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDepositoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCSelTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDepositoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDepositoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(7, 7, 7)
                        .addComponent(jFTDataMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDepositoLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLoteDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout abaCompensarChequeLayout = new javax.swing.GroupLayout(abaCompensarCheque);
        abaCompensarCheque.setLayout(abaCompensarChequeLayout);
        abaCompensarChequeLayout.setHorizontalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarChequeLayout.createSequentialGroup()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaCompensarChequeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(panelDeposito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        abaCompensarChequeLayout.setVerticalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Depósito de Cheques", abaCompensarCheque);
        abaCompensarCheque.getAccessibleContext().setAccessibleName("abaCompensarCheque");

        panelDadosDeposito.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)), "Dados Depósito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelTitularContaBancaria.setFont(new java.awt.Font("Arial", 1, 10));
        labelTitularContaBancaria.setForeground(new java.awt.Color(0, 102, 102));
        labelTitularContaBancaria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitularContaBancaria.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 1, true), "Correntista", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelAgencia.setFont(new java.awt.Font("Arial", 1, 10));
        labelAgencia.setForeground(new java.awt.Color(0, 102, 102));
        labelAgencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAgencia.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 1, true), "Agência", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelConta.setFont(new java.awt.Font("Arial", 1, 10));
        labelConta.setForeground(new java.awt.Color(0, 102, 102));
        labelConta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 1, true), "Conta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelBanco.setFont(new java.awt.Font("Arial", 1, 10));
        labelBanco.setForeground(new java.awt.Color(0, 102, 102));
        labelBanco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBanco.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 1, true), "Banco", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        javax.swing.GroupLayout panelDadosDepositoLayout = new javax.swing.GroupLayout(panelDadosDeposito);
        panelDadosDeposito.setLayout(panelDadosDepositoLayout);
        panelDadosDepositoLayout.setHorizontalGroup(
            panelDadosDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosDepositoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDadosDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDadosDepositoLayout.createSequentialGroup()
                        .addComponent(labelBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelConta, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadosDepositoLayout.createSequentialGroup()
                        .addComponent(labelTitularContaBancaria, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(labelAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDadosDepositoLayout.setVerticalGroup(
            panelDadosDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosDepositoLayout.createSequentialGroup()
                .addGroup(panelDadosDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelConta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDadosDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTitularContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelFormato.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Formato"));

        comboFormato.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFormatoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelFormatoLayout = new javax.swing.GroupLayout(panelFormato);
        panelFormato.setLayout(panelFormatoLayout);
        panelFormatoLayout.setHorizontalGroup(
            panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormatoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, 0, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelFormatoLayout.setVerticalGroup(
            panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormatoLayout.createSequentialGroup()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(panelFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addComponent(panelDadosDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(48, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDadosDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed


        jFTDataMovimento.setDate(Controller.getDataServidorBD());
        panelDadosDeposito.setVisible(false);
        botaoImprimir.setEnabled(false);
        botaoDepositar.setEnabled(false);
        panelFormato.setVisible(false);
        jFTDataMovimento.setEnabled(true);
        comboContaBancaria.setEnabled(true);
        comboFuncionario.setEnabled(true);
        tableCheques.setEnabled(true);
        jCSelTodos.setEnabled(true);
        jCSelTodos.setSelected(false);

        this.jCSelTodosActionPerformed(evt);

        comboFuncionario.setSelectedIndex(0);
        comboContaBancaria.setSelectedIndex(0);
        jLoteDeposito.setText("");

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (jFTDataMovimento == null) {
            jFTDataMovimento.requestFocus();
            return false;
        }

        if (comboFuncionario.getSelectedIndex() == 0) {
            comboFuncionario.requestFocus();
            return false;
        }

        if (comboContaBancaria.getSelectedIndex() == 0) {
            comboContaBancaria.requestFocus();
            return false;
        }

        if (jFTDataMovimento.getDate() != null) {

            if (jFTDataMovimento.getDate().after(PempecParse.dateToDate(Controller.getDataServidorBD()))) {
                exibeMensagemAviso("A data do depósito não pode ser maior que hoje.", null);
                jFTDataMovimento.requestFocus();
                return false;
            }


        }

        boolean validaPreenchimento = false;

        for (int i = 0; i < tableCheques.getRowCount(); i++) {
            if (tableCheques.getValueAt(i, 0).toString().equals("true")) {
                validaPreenchimento = true;
                break;
            }
        }

        if (!validaPreenchimento) {
            exibeMensagemAviso("Selecione um Item!", null);
            return false;
        }

        return true;
    }

    private void comboContaBancariaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaBancariaItemStateChanged

        if (comboContaBancaria.getSelectedItem() != null
                && ((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk() != null) {

            panelDadosDeposito.setVisible(true);
            botaoDepositar.setEnabled(true);

            try {
                ContaBancariaModel contaBancariaModel = ((ContaBancariaModel) comboContaBancaria.getSelectedItem());
                contaBancariaModel = contaBancariaBO.consultarPorTemplate(contaBancariaModel);

                labelTitularContaBancaria.setText(contaBancariaModel.getTitular());
                labelAgencia.setText(contaBancariaModel.getAgencia());
                labelConta.setText(contaBancariaModel.getConta());
                labelBanco.setText(contaBancariaModel.getBanco().getNomeBanco());

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

            panelDadosDeposito.setVisible(false);

        }

}//GEN-LAST:event_comboContaBancariaItemStateChanged

private void jCSelTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCSelTodosActionPerformed

    for (int i = 0; i < tableCheques.getRowCount(); i++) {
        tableCheques.setValueAt(jCSelTodos.isSelected(), i, 0);
    }

}//GEN-LAST:event_jCSelTodosActionPerformed

private void botaoDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDepositarActionPerformed

    if (this.validaCampos()) {

        try {

            jLoteDeposito.setText(PempecUtil.geraNumeroLoteDeposito()); // lote do deposito

            collCheques = new ArrayList<TituloReceberBaixaChequeModel>(); //cheques que serao depositados

            LoteDepositoModel loteDepositoModel = new LoteDepositoModel();

            loteDepositoModel.setPk(new PKModel());
            loteDepositoModel.getPk().setOrganizacao(organizacaoModel);
            loteDepositoModel.getPk().setId(PempecKeyGenerator.generateKey());
            loteDepositoModel.setLote(jLoteDeposito.getText());
            loteDepositoModel.setDataRegistro(Controller.getDataServidorBD());
            loteDepositoModel.setDataAtualizacao(Controller.getDataServidorBD());
            loteDepositoModel.setUsuario(Controller.getUsuarioLogado());
            loteDepositoModel.setStatus("DEPOSITADO");

            long qtdChqSel = 0l;

            Double valorDeposito = 0d; //recebe a soma dos cheques selecionados
            //Percorrendo os selecionados.
            for (int i = 0; i < tableCheques.getRowCount(); i++) {

                if ((Boolean) tableCheques.getValueAt(i, 0)) {

                    // pega a coluna q tem o numero do cheque. Precisa para Obter
                    String numeroCheque = tableCheques.getValueAt(i, 6).toString();

                    TituloReceberBaixaChequeModel chequeRecebido = new TituloReceberBaixaChequeModel();

                    chequeRecebido.setPk(new PKModel());
                    chequeRecebido.getPk().setOrganizacao(organizacaoModel);
                    chequeRecebido.setNumeroCheque(numeroCheque);

                    chequeRecebido = tituloReceberBaixaChequeBO.consultarPorTemplate(chequeRecebido);


                    // altera dados do cheque
                    chequeRecebido.setLoteDeposito(loteDepositoModel);
                    chequeRecebido.setDataDeposito(Controller.getDataServidorBD());

                    //soma o valor dos cheques
                    valorDeposito += chequeRecebido.getValor();

                    collCheques.add(chequeRecebido);

                    qtdChqSel++;

                }


            }


            // lancamento na conta bancaria escolhida
            ContaBancariaCreditoModel ctbCr = new ContaBancariaCreditoModel(); // conta que sera depositada o lote
            ctbCr.setPk(new PKModel());

            ctbCr.getPk().setOrganizacao(organizacaoModel);
            ctbCr.getPk().setId(PempecKeyGenerator.generateKey());
            ctbCr.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaCredito());

            ctbCr.setValor(valorDeposito);

            if (comboContaBancaria.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk().getId() != null) {
                ContaBancariaModel contaBancariaModel = (ContaBancariaModel) comboContaBancaria.getSelectedItem();
                contaBancariaModel = contaBancariaBO.consultarPorTemplate(contaBancariaModel);
                ctbCr.setContaBancaria(contaBancariaModel);
            }

            if (comboFuncionario.getSelectedItem() != null && ((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk().getId() != null) {
                FuncionarioModel funcionarioModel = (FuncionarioModel) comboFuncionario.getSelectedItem();
                funcionarioModel = funcionarioBO.consultarPorTemplate(funcionarioModel);

                ctbCr.setResponsavel(funcionarioModel);

            }

            ctbCr.setDataContabil(Controller.getDataServidorBD());
            ctbCr.setDataRegistro(Controller.getDataServidorBD());
            ctbCr.setDataMovimento(PempecParse.dateToDate(jFTDataMovimento.getDate()));

            ctbCr.setTipoLancamento("C");

            TipoOperacaoBancariaModel tipoOPeracao = new TipoOperacaoBancariaModel();
            tipoOPeracao.setPk(new PKModel());
            tipoOPeracao.getPk().setOrganizacao(organizacaoModel);
            tipoOPeracao.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_TESOURARIA_DEPOSITO);

            tipoOPeracao = new TipoOperacaoBancariaBO().consultarPorPk(tipoOPeracao);

            ctbCr.setTipoOperacaoBancaria(tipoOPeracao);

            ctbCr.setUsuario(Controller.getUsuarioLogado());

            ctbCr.setDescricao("DEP CHEQUE LOTE NUM. : " + jLoteDeposito.getText());
            ctbCr.setMovimentoDiarioModel(registroMovimento("Deposito CH", jLoteDeposito.getText(), ctbCr.getDescricao(), ctbCr.getValor(), "Depositado"));

            jCSelTodos.setEnabled(false); //evitar que o usuario marque depois que apertou botao depositar
            jFTDataMovimento.setEnabled(false);
            comboContaBancaria.setEnabled(false);
            comboFuncionario.setEnabled(false);

            loteDepositoModel.setQtdCheque(qtdChqSel);

            new MovimentoDiarioBO().inserir(this.registroMovimento("DEP CHEQUES", null, Controller.getUsuarioLogado().getNome() + " solicitou deposito de cheques da tesouraria.", valorDeposito, "Depositado"));

            tesourariaDebitoBO.depositarChequesBanco(collCheques, ctbCr, loteDepositoModel);

            botaoDepositar.setEnabled(false);
            botaoImprimir.setEnabled(true); //libera botao p usuario imprimir listagem
            panelFormato.setVisible(true);
            tableCheques.setEnabled(false); //Na hora de limpar - Desbloqueia

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



}//GEN-LAST:event_botaoDepositarActionPerformed

private void botaoImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirActionPerformed

    Collection<ReciboDepositoCheques> collRel = new ArrayList<ReciboDepositoCheques>();

    for (TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel : collCheques) {

        ReciboDepositoCheques bean = new ReciboDepositoCheques();

        bean.setRazaoSocial(organizacaoModel.getRazaoSocial());
        bean.setCnpj(organizacaoModel.getCnpj());
        bean.setLote(jLoteDeposito.getText());
        bean.setDataDeposito(jFTDataMovimento.getDate());
        bean.setAgencia(tituloReceberBaixaChequeModel.getAgencia());
        bean.setBanco(tituloReceberBaixaChequeModel.getBanco().getNomeBanco());
        bean.setCheque(tituloReceberBaixaChequeModel.getNumeroCheque());
        bean.setConta(tituloReceberBaixaChequeModel.getConta());
        bean.setData(tituloReceberBaixaChequeModel.getDataProtocolo());
        bean.setTitular(tituloReceberBaixaChequeModel.getTitular());
        bean.setValor(tituloReceberBaixaChequeModel.getValor());

        collRel.add(bean);


    }

    try {

        switch (comboFormato.getSelectedIndex()) {

            case 0:
                new RelatorioUtil().visualizar(RelatorioConstantes.RECIBO_DEPOSITOS_CHEQUES, new HashMap<String, Object>(), collRel);
                break;
            case 1:
                new RelatorioUtil().exportarPDF(RelatorioConstantes.RECIBO_DEPOSITOS_CHEQUES, new HashMap<String, Object>(), collRel);
                break;
            case 2:
                new RelatorioUtil().exportarXLS(RelatorioConstantes.RECIBO_DEPOSITOS_CHEQUES, new HashMap<String, Object>(), collRel);
                break;
            case 3:

                //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RECIBO_DEPOSITOS_CHEQUES, new HashMap<String, Object>(), collRel);

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

}//GEN-LAST:event_botaoImprimirActionPerformed

private void comboFormatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormatoItemStateChanged
    if (comboFormato.getSelectedItem() != null && ((FormatosRelatoriosModel) comboFormato.getSelectedItem()).getDescricao().equalsIgnoreCase(Constantes.ENVIAR_POR_EMAIL)) {
        botaoImprimir.setText("Enviar");
    } else {
        botaoImprimir.setText("Imprimir");
    }
}//GEN-LAST:event_comboFormatoItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque;
    private javax.swing.JButton botaoDepositar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JComboBox comboFuncionario;
    private javax.swing.JCheckBox jCSelTodos;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLoteDeposito;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAgencia;
    private javax.swing.JLabel labelBanco;
    private javax.swing.JLabel labelConta;
    private javax.swing.JLabel labelTitularContaBancaria;
    private javax.swing.JPanel panelDadosDeposito;
    private javax.swing.JPanel panelDeposito;
    private javax.swing.JPanel panelFormato;
    private javax.swing.JTable tableCheques;
    // End of variables declaration//GEN-END:variables
}
