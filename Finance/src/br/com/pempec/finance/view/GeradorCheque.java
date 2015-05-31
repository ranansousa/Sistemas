package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.TipoStatusBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
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
import javax.swing.SwingUtilities;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author PEMPEC
 */
public class GeradorCheque extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaChequeBO contaBancariaChequeBO = new ContaBancariaChequeBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private TipoStatusBO tipoStatusBO = new TipoStatusBO();
    private long tela = Tela.TELA_CONCILIACAO_GERAR_CHEQUES.getTela();

    private String NameObject() {
        return (String) "Gerador de Cheques";
    }

    public void repopularCombos() {

        try {

            // responsavel
            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            //conta Bancaria
            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Selecione <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancaria.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            comboConta.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            //Status
            comboStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" -> Selecione <- ", Constantes.STATUS_CHEQUE_AVULSO, Constantes.STATUS_CHEQUE_COMPENSADO, Constantes.STATUS_CHEQUE_EMITIDO, Constantes.STATUS_CHEQUE_EXCLUIDO, Constantes.STATUS_CHEQUE_NOVO}));

            //Adicionando o status padrão - Novo ou Aberto.
            TipoStatusModel tipoStatusModel = new TipoStatusModel();

            tipoStatusModel.setPk(new PKModel());

            tipoStatusModel.getPk().setOrganizacao(organizacaoModel);

            tipoStatusModel.getPk().setId(Constantes.STATUS_CHEQUE_NOVO);

            tipoStatusModel = tipoStatusBO.consultarPorPk(tipoStatusModel);

            labelSituacaoCheque.setText(tipoStatusModel.getDescricao());

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

    public GeradorCheque() throws SystemException {

        this.setLocation(250, 50);
        initComponents();

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);

        campoCodigoContaBancariaCheque.setVisible(false);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoStatus = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaManterCheque = new javax.swing.JPanel();
        labelSacado = new javax.swing.JLabel();
        campoCodigo = new javax.swing.JTextField();
        labelResponsavel = new javax.swing.JLabel();
        labelValorCheque = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboConta = new javax.swing.JComboBox();
        comboContaBancariaCheque = new javax.swing.JComboBox();
        jFTDataRegistroCheque = new javax.swing.JLabel();
        jFTDataEmissao = new javax.swing.JLabel();
        jFTDataCompensado = new javax.swing.JLabel();
        labelSituacaoContaBancariaCheque = new javax.swing.JLabel();
        jFTDataPrevisao = new javax.swing.JLabel();
        jFTDataEstornado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTObservacao = new org.jdesktop.swingx.JXEditorPane();
        jFTQtdPrinter = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jPanelOBS = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTOBS = new org.jdesktop.swingx.JXEditorPane();
        AbaGerarCheque = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comboContaBancaria = new javax.swing.JComboBox();
        campoCodigoContaBancariaCheque = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboResponsavel = new javax.swing.JComboBox();
        jFTNumeroChequeInicial = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFTNumeroFolhas = new javax.swing.JFormattedTextField();
        labelSituacaoCheque = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        botaoGerarCheque = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Manutenção de Cheques");

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

        botaoStatus.setMnemonic('E');
        botaoStatus.setText("Status");
        botaoStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoStatusActionPerformed(evt);
            }
        });

        botaoExcluir.setMnemonic('E');
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoExcluir, botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        abaManterCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        abaManterCheque.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                abaManterChequeComponentShown(evt);
            }
        });

        labelSacado.setText("Cheque");

        campoCodigo.setEditable(false);

        labelResponsavel.setBackground(new java.awt.Color(222, 218, 210));
        labelResponsavel.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        labelResponsavel.setForeground(new java.awt.Color(0, 153, 153));
        labelResponsavel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Responsável", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        labelValorCheque.setBackground(new java.awt.Color(222, 218, 210));
        labelValorCheque.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        labelValorCheque.setForeground(new java.awt.Color(0, 153, 153));
        labelValorCheque.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelValorCheque.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor"));

        jLabel9.setText("Observação");

        jLabel10.setText("Conta Bancária");

        comboConta.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        comboConta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaItemStateChanged(evt);
            }
        });

        comboContaBancariaCheque.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        comboContaBancariaCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContaBancariaChequeActionPerformed(evt);
            }
        });

        jFTDataRegistroCheque.setBackground(new java.awt.Color(222, 218, 210));
        jFTDataRegistroCheque.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jFTDataRegistroCheque.setForeground(new java.awt.Color(0, 153, 153));
        jFTDataRegistroCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gerado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        jFTDataEmissao.setBackground(new java.awt.Color(222, 218, 210));
        jFTDataEmissao.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jFTDataEmissao.setForeground(new java.awt.Color(0, 153, 153));
        jFTDataEmissao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Emitido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        jFTDataCompensado.setBackground(new java.awt.Color(222, 218, 210));
        jFTDataCompensado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jFTDataCompensado.setForeground(new java.awt.Color(0, 153, 153));
        jFTDataCompensado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Compensado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        labelSituacaoContaBancariaCheque.setBackground(new java.awt.Color(222, 218, 210));
        labelSituacaoContaBancariaCheque.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelSituacaoContaBancariaCheque.setForeground(new java.awt.Color(204, 0, 0));
        labelSituacaoContaBancariaCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        jFTDataPrevisao.setBackground(new java.awt.Color(222, 218, 210));
        jFTDataPrevisao.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jFTDataPrevisao.setForeground(new java.awt.Color(0, 153, 153));
        jFTDataPrevisao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Previsão", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        jFTDataEstornado.setBackground(new java.awt.Color(222, 218, 210));
        jFTDataEstornado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jFTDataEstornado.setForeground(new java.awt.Color(0, 153, 153));
        jFTDataEstornado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estornado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        jScrollPane1.setViewportView(jTObservacao);

        jFTQtdPrinter.setBackground(new java.awt.Color(222, 218, 210));
        jFTQtdPrinter.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jFTQtdPrinter.setForeground(new java.awt.Color(0, 153, 153));
        jFTQtdPrinter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jFTQtdPrinter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Qtd Imp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 10))); // NOI18N

        comboStatus.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel11.setText("Status do Cheque");

        jPanelOBS.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.red));
        jPanelOBS.setFont(getFont());

        jTOBS.setEditable(false);
        jTOBS.setFont(getFont());
        jTOBS.setForeground(new java.awt.Color(204, 0, 0));
        jScrollPane2.setViewportView(jTOBS);

        javax.swing.GroupLayout jPanelOBSLayout = new javax.swing.GroupLayout(jPanelOBS);
        jPanelOBS.setLayout(jPanelOBSLayout);
        jPanelOBSLayout.setHorizontalGroup(
            jPanelOBSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOBSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanelOBSLayout.setVerticalGroup(
            jPanelOBSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOBSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout abaManterChequeLayout = new javax.swing.GroupLayout(abaManterCheque);
        abaManterCheque.setLayout(abaManterChequeLayout);
        abaManterChequeLayout.setHorizontalGroup(
            abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaManterChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelOBS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(abaManterChequeLayout.createSequentialGroup()
                        .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaManterChequeLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(labelSituacaoContaBancariaCheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaManterChequeLayout.createSequentialGroup()
                        .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaManterChequeLayout.createSequentialGroup()
                                .addComponent(jFTDataRegistroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10)
                            .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelSacado, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboContaBancariaCheque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(abaManterChequeLayout.createSequentialGroup()
                                .addComponent(jFTDataPrevisao, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFTDataCompensado, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelValorCheque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(abaManterChequeLayout.createSequentialGroup()
                                        .addComponent(jFTDataEstornado, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFTQtdPrinter, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(68, 68, 68))
        );
        abaManterChequeLayout.setVerticalGroup(
            abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaManterChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(abaManterChequeLayout.createSequentialGroup()
                            .addComponent(labelSacado)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboContaBancariaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(abaManterChequeLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jFTDataRegistroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTDataPrevisao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTDataCompensado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTDataEstornado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTQtdPrinter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(labelSituacaoContaBancariaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaManterChequeLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaManterChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelOBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manutenção de Cheque", abaManterCheque);

        AbaGerarCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        AbaGerarCheque.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                AbaGerarChequeComponentShown(evt);
            }
        });

        jLabel6.setText("Conta Bancária");

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        campoCodigoContaBancariaCheque.setEditable(false);

        jLabel5.setText("Responsável");

        comboResponsavel.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel1.setText("Numero Inicial");

        jLabel2.setText(" Folhas");

        labelSituacaoCheque.setBackground(new java.awt.Color(222, 218, 210));
        labelSituacaoCheque.setFont(new java.awt.Font("Arial", 2, 10)); // NOI18N
        labelSituacaoCheque.setForeground(new java.awt.Color(255, 0, 0));
        labelSituacaoCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 204, 204)));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoGerarCheque.setMnemonic('G');
        botaoGerarCheque.setText("Gerar Cheques");
        botaoGerarCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarChequeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoGerarCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(botaoGerarCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout AbaGerarChequeLayout = new javax.swing.GroupLayout(AbaGerarCheque);
        AbaGerarCheque.setLayout(AbaGerarChequeLayout);
        AbaGerarChequeLayout.setHorizontalGroup(
            AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                                .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(campoCodigoContaBancariaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelSituacaoCheque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AbaGerarChequeLayout.createSequentialGroup()
                                    .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jFTNumeroChequeInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(28, 28, 28)
                                    .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jFTNumeroFolhas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AbaGerarChequeLayout.setVerticalGroup(
            AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTNumeroChequeInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AbaGerarChequeLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AbaGerarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTNumeroFolhas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoContaBancariaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSituacaoCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Gerar Cheque", AbaGerarCheque);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 553, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        comboContaBancaria.setSelectedIndex(0);
        comboConta.setSelectedIndex(0);
        if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
            supportCheque.uninstall();
        }

        comboResponsavel.setSelectedIndex(0);
        campoCodigo.setText("");
        //Campo com mascara setar null no value.
        jFTDataCompensado.setText("");
        jFTDataEmissao.setText("");
        jFTDataRegistroCheque.setText("");
        jFTNumeroChequeInicial.setText("");
        jFTNumeroFolhas.setText("");
        jTObservacao.setText("");
        labelValorCheque.setText("");
        labelResponsavel.setText("");
        labelSituacaoContaBancariaCheque.setText("");
        jFTDataPrevisao.setText("");
        jFTQtdPrinter.setText("0");

        botaoStatus.setEnabled(false);

        jPanelOBS.setVisible(false);

        jTOBS.setText("");


    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

        if (comboContaBancariaCheque.getSelectedItem() != null) {
            valorCombo = comboContaBancariaCheque.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {
                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                ContaBancariaChequeModel tab = (ContaBancariaChequeModel) comboContaBancariaCheque.getSelectedItem();

                TipoStatusModel tipoStatusModel = new TipoStatusModel();
                tipoStatusModel.setPk(new PKModel());
                tipoStatusModel.getPk().setId(Constantes.STATUS_CHEQUE_EXCLUIDO);

                tab.setStatus(tipoStatusModel);

                tab.setMovimentoDiarioModel(registroMovimento("Cancelar CH", "Cheque " + tab.getNumeroCheque(), "Cancelamento de Cheque ", PempecParse.stringToDouble(labelValorCheque.getText()), "Cancelado"));

                contaBancariaChequeBO.excluir(tab);

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
            exibeMensagemAviso("Campo Nome obrigatório.", null);
        }

    }//GEN-LAST:event_botaoExcluirActionPerformed

    private Boolean validaCampos() {

        if (comboContaBancaria.getSelectedIndex() == 0) {
            comboContaBancaria.requestFocus();
            return false;
        }

        if (comboResponsavel.getSelectedIndex() == 0) {
            comboResponsavel.requestFocus();
            return false;
        }

        if (jFTNumeroChequeInicial.getText().isEmpty()) {
            jFTNumeroChequeInicial.requestFocus();
            return false;
        }

        if (jFTNumeroFolhas.getText().isEmpty()) {
            jFTNumeroFolhas.requestFocus();
            return false;
        }

        if (!PempecUtil.validaPreenchimentoNumero(jFTNumeroChequeInicial.getText())) {
            exibeMensagemAviso("Campo número só permite números!", null);
            jFTNumeroChequeInicial.requestFocus();
            return false;
        }

        if (!PempecUtil.validaPreenchimentoNumero(jFTNumeroFolhas.getText())) {
            exibeMensagemAviso("Campo número só permite números!", null);
            jFTNumeroChequeInicial.requestFocus();
            return false;
        }

        return true;
    }

private void botaoGerarChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarChequeActionPerformed

    String valorComboContaBancaria = null;

    if (comboContaBancaria.getSelectedItem() != null) {
        valorComboContaBancaria = comboContaBancaria.getSelectedItem().toString();
    }

    if (valorComboContaBancaria != null) {

        try {

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            if (validaCampos()) {

                ContaBancariaChequeModel cheque = new ContaBancariaChequeModel();

                cheque.setPk(new PKModel());
                cheque.getPk().setOrganizacao(organizacaoModel);
                cheque.getPk().setId(PempecKeyGenerator.generateKey());

                if (comboContaBancaria.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk() != null) {
                    cheque.setContaBancaria(new ContaBancariaModel());
                    cheque.getContaBancaria().setPk(new PKModel());
                    cheque.getContaBancaria().getPk().setId(((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk().getId());
                    cheque.getContaBancaria().getPk().setOrganizacao(organizacaoModel);
                    cheque.getContaBancaria().setConta(((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getConta());
                }

                cheque.setDataRegistro(Controller.getDataServidorBD());

                cheque.setUsuario(Controller.getUsuarioLogado());

                cheque.setQtdImpressao(0);

                TipoStatusModel tipoStatusModel = new TipoStatusModel();
                tipoStatusModel.setPk(new PKModel());
                tipoStatusModel.getPk().setId(Constantes.STATUS_CHEQUE_NOVO);

                cheque.setStatus(tipoStatusModel);

                if (comboResponsavel.getSelectedItem() != null && ((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk() != null) {
                    cheque.setResponsavel(new FuncionarioModel());
                    cheque.getResponsavel().setPk(new PKModel());
                    cheque.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk().getId());
                }

                cheque.setNumeroCheque(Long.valueOf(jFTNumeroChequeInicial.getText()).toString());

                Long numeroCheque = Long.valueOf(cheque.getNumeroCheque());

                int qtd = Integer.parseInt(jFTNumeroFolhas.getText());

                String numeroChequeFinal = String.valueOf(numeroCheque + qtd);

                Collection<ContaBancariaChequeModel> chequesExistentes = contaBancariaChequeBO.obterPorContaBancariaTodosNumeroCheque(cheque.getContaBancaria(), jFTNumeroChequeInicial.getText(), numeroChequeFinal);

                StringBuilder builder = new StringBuilder();

                int cont = 0;

                builder.append("Conta Bancária: ").append(cheque.getContaBancaria().getConta());

                builder.append("\nCheques Existentes: \n");

                for (ContaBancariaChequeModel chequeExistente : chequesExistentes) {

                    builder.append(chequeExistente.getNumeroCheque() + ", ");

                    if ((cont++) == 15) {

                        cont = 0;

                        builder.append("\n");

                    }
                }

                builder.append("\nCheques Gerados: \n");

                Collection<ContaBancariaChequeModel> chequesGerados = new ArrayList<ContaBancariaChequeModel>();

                Collection<ContaBancariaChequeModel> chequesGeradosAux = new ArrayList<ContaBancariaChequeModel>();

                if ((qtd < 1) || (qtd > 999)) {

                    exibeMensagemAviso("Quantidade de cheques inválida! Valor não pode ser inferior a ZERO ou superior a 999", null);

                    return;
                }

                chequesGerados.add(cheque); //o primeiro

                ContaBancariaChequeModel clone = null;

                int i = 2;

                try {

                    clone = (ContaBancariaChequeModel) BeanUtils.cloneBean(cheque);

                    while (i <= qtd) {

                        String novoNumeroCheque = null;

                        numeroCheque++;

                        novoNumeroCheque = String.valueOf(numeroCheque);

                        clone.setPk(new PKModel());

                        clone.getPk().setOrganizacao(cheque.getPk().getOrganizacao());

                        clone.getPk().setId(PempecKeyGenerator.generateKey());

                        clone.setNumeroCheque(novoNumeroCheque);

                        clone.setQtdImpressao(0);

                        chequesGerados.add(clone);

                        clone = (ContaBancariaChequeModel) BeanUtils.cloneBean(clone);

                        i++;

                    }

                } catch (final Exception ex) {

                    final File file = PrintScreen.capture();

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {

                            tratamentoExcecao(new SystemException(ex), file);

                        }
                    });

                }

                for (ContaBancariaChequeModel chequeGerado : chequesGerados) {

                    boolean existe = false;

                    for (ContaBancariaChequeModel chequeExistente : chequesExistentes) {
                        if (chequeGerado.getNumeroCheque().equals(chequeExistente.getNumeroCheque())) {
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {
                        chequesGeradosAux.add(chequeGerado);
                    }

                }

                cont = 0;

                for (ContaBancariaChequeModel chequeGerado : chequesGeradosAux) {
                    builder.append(chequeGerado.getNumeroCheque() + ", ");
                    if ((cont++) == 15) {
                        cont = 0;
                        builder.append("\n");
                    }
                }

                if (chequesGeradosAux.isEmpty()) {

                    builder.append("Nenhum cheque gerado!");

                } else {

                    cheque.setMovimentoDiarioModel(registroMovimento("CH Gerado ", "Conta :" + cheque.getContaBancaria(), "Num Cheque Inicial " + cheque.getNumeroCheque(), null, "Gerado"));
                    cheque.getMovimentoDiarioModel().setObservacao("Folhas Geradas  -> " + chequesGeradosAux.size());

                    contaBancariaChequeBO.gerarCheques(chequesGeradosAux);

                }

                exibeMensagemAviso(builder.toString().trim(), null);

                this.botaoLimparActionPerformed(evt);

            } else {

                exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

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
        exibeMensagemAviso("A descrição é obrigatória.", null);
    }

}//GEN-LAST:event_botaoGerarChequeActionPerformed

private void AbaGerarChequeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_AbaGerarChequeComponentShown
    botaoExcluir.setVisible(false);//GEN-LAST:event_AbaGerarChequeComponentShown
        botaoLimpar.setVisible(true);
    }

private void abaManterChequeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaManterChequeComponentShown
    botaoExcluir.setVisible(true);
    botaoLimpar.setVisible(true);
}//GEN-LAST:event_abaManterChequeComponentShown

    public void limparCamposCheque() {

        jFTDataCompensado.setText("");
        jFTDataEstornado.setText("");
        jFTDataEmissao.setText("");
        jFTDataRegistroCheque.setText("");
        jTObservacao.setText("");
        labelValorCheque.setText("");
        labelResponsavel.setText("");
        labelSituacaoContaBancariaCheque.setText("");
        campoCodigo.setText("");
        comboStatus.setSelectedIndex(0);
    }

private void comboContaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaItemStateChanged

    if (comboConta.getSelectedItem() != null
            && ((ContaBancariaModel) comboConta.getSelectedItem()).getPk() != null) {

        this.limparCamposCheque();

        try {

            Collection<ContaBancariaChequeModel> lContaBancariaCheque = contaBancariaChequeBO.obterPorContaBancaria((ContaBancariaModel) comboConta.getSelectedItem());

            final EventList<ContaBancariaChequeModel> lRegistrosCheques = GlazedLists.eventList(lContaBancariaCheque);
            if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
                supportCheque.uninstall();
            }
            supportCheque = AutoCompleteSupport.install(comboContaBancariaCheque, lRegistrosCheques, new ChequeTextFilterator());
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

        this.limparCamposCheque();

        if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
            supportCheque.uninstall();
        }

    }


}//GEN-LAST:event_comboContaItemStateChanged

private void comboContaBancariaChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContaBancariaChequeActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboContaBancariaCheque.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        this.limparCamposCheque();
        comboStatus.setEnabled(false);

        ContaBancariaChequeModel cheque = (ContaBancariaChequeModel) comboContaBancariaCheque.getSelectedItem();

        if (cheque.getStatus().getPk().getId().equalsIgnoreCase(Constantes.STATUS_CHEQUE_NOVO)) {

            botaoExcluir.setEnabled(true);

            botaoStatus.setEnabled(false);

        } else {

            botaoExcluir.setEnabled(false);

            if (cheque.getStatus().getPk().getId().equalsIgnoreCase(Constantes.STATUS_CHEQUE_EXCLUIDO)) {

                //botaoStatus.setEnabled(true);
                botaoStatus.setEnabled(false);
                comboStatus.setEnabled(false);

                jPanelOBS.setVisible(true);

                String texto = " ";//"ATENÇÂO! VOCE PODE MUDAR O STATUS DO CHEQUE";
                //texto = texto + "\nESTE  PROCEDIMENTO PODE INTERFERIR NO FUNCIONAMENTO DO SISTEMA.";
                //texto = texto + "\nTENHA CERTEZA QUE O NUMERO DO CHEQUE ESTÁ CORRETO.";
                texto = "\"ATENÇÂO! VOCÊ NÃO PODE MUDAR O STATUS DO CHEQUE\";";
                texto = texto + "\"CANCELANDO O PAGAMENTO, O CHEQUE MUDARÁ O STATUS.\";";

                jTOBS.setText(texto);

            } else {

                botaoStatus.setEnabled(false);
                jPanelOBS.setVisible(false);
            }
        }

        if (cheque.getDataCompensado() != null) {

            jFTDataCompensado.setText(PempecParse.dateToString(cheque.getDataCompensado()));
        }

        if (cheque.getDataEstornado() != null) {
            jFTDataEstornado.setText(PempecParse.dateToString(cheque.getDataEstornado()));
        }

        if (cheque.getDataRegistro() != null) {
            jFTDataRegistroCheque.setText(PempecParse.dateToString(cheque.getDataRegistro()));
        }

        if (cheque.getDataPrevisaoCompensacao() != null) {
            jFTDataPrevisao.setText(PempecParse.dateToString(cheque.getDataPrevisaoCompensacao()));
        }

        if (cheque.getDataEmissao() != null) {
            jFTDataEmissao.setText(PempecParse.dateToString(cheque.getDataEmissao()));
        }

        if (cheque.getValor() != null) {
            labelValorCheque.setText(PempecParse.doubleToZero(cheque.getValor()));
        }

        if (cheque.getResponsavel() != null) {
            labelResponsavel.setText(cheque.getResponsavel().getNome());
        }

        if (cheque.getStatus() != null) {

            if (cheque.getQtdImpressao() != null && cheque.getQtdImpressao() > 0) {

                labelSituacaoContaBancariaCheque.setText(cheque.getStatus().getDescricao() + " IMPRESSO " + cheque.getQtdImpressao() + " VEZ(ES).");

            } else {

                labelSituacaoContaBancariaCheque.setText(cheque.getStatus().getDescricao());

            }

            for (int i = 0; i < comboStatus.getItemCount(); i++) {
                if (cheque.getStatus().getPk().getId().equalsIgnoreCase(((String) comboStatus.getItemAt(i)).toString())) {
                    comboStatus.setSelectedIndex(i);
                    break;
                }
            }
        }

        jTObservacao.setText(cheque.getObservacao());

        jFTQtdPrinter.setText(cheque.getQtdImpressao().toString());

    }

}//GEN-LAST:event_comboContaBancariaChequeActionPerformed

private void botaoStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoStatusActionPerformed

    String valorCombo = null;

    if (comboContaBancariaCheque.getSelectedItem() != null) {
        valorCombo = comboContaBancariaCheque.getSelectedItem().toString();
    }

    if (valorCombo != null) {

        try {

            long action = Action.EXCLUIR.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            ContaBancariaChequeModel tab = (ContaBancariaChequeModel) comboContaBancariaCheque.getSelectedItem();

            tab = contaBancariaChequeBO.consultarPorTemplate(tab);

            if (comboStatus.getSelectedItem() != null && comboStatus.getSelectedIndex() > 0) {

                TipoStatusModel tipoStatusModel = new TipoStatusModel();
                tipoStatusModel.setPk(new PKModel());
                tipoStatusModel.getPk().setId(comboStatus.getSelectedItem().toString());

                tab.setStatus(tipoStatusModel);

                tab.setObservacao(tab.getObservacao() + " " + "\nCh alt stat por " + Controller.getUsuarioLogado().getNome());

            } else {

                exibeMensagemAviso("Selecione um Status para o cheque", null);

            }

            tab.setMovimentoDiarioModel(registroMovimento("ALT ST CH", tab.getNumeroCheque(), "ALT STATUS CH " + tab.getNumeroCheque(), tab.getValor(), "ST ALT"));

            if (tab.getStatus().equals(Constantes.STATUS_CHEQUE_EMITIDO) && (tab.getValor() ==null) || tab.getValor() ==0d) {
                
                exibeMensagemAviso("Cheque sem valor não pode ter status EMITIDO.", null);

            } else {

                contaBancariaChequeBO.alterar(tab);
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
        exibeMensagemAviso("Campo Numero do Cheque obrigatório.", null);
    }


}//GEN-LAST:event_botaoStatusActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AbaGerarCheque;
    private javax.swing.JPanel abaManterCheque;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerarCheque;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoStatus;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoCodigoContaBancariaCheque;
    private javax.swing.JComboBox comboConta;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboContaBancariaCheque;
    private javax.swing.JComboBox comboResponsavel;
    private javax.swing.JComboBox comboStatus;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jFTDataCompensado;
    private javax.swing.JLabel jFTDataEmissao;
    private javax.swing.JLabel jFTDataEstornado;
    private javax.swing.JLabel jFTDataPrevisao;
    private javax.swing.JLabel jFTDataRegistroCheque;
    private javax.swing.JFormattedTextField jFTNumeroChequeInicial;
    private javax.swing.JFormattedTextField jFTNumeroFolhas;
    private javax.swing.JLabel jFTQtdPrinter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelOBS;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXEditorPane jTOBS;
    private org.jdesktop.swingx.JXEditorPane jTObservacao;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelSacado;
    private javax.swing.JLabel labelSituacaoCheque;
    private javax.swing.JLabel labelSituacaoContaBancariaCheque;
    private javax.swing.JLabel labelValorCheque;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportCheque;
}
