package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.LayoutChequeBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LayoutChequeModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.iterators.ContaBancariaTextFilterator;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class LayoutCheque extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private LayoutChequeBO layoutChequeBO = new LayoutChequeBO();
    private OrganizacaoModel organizacaoModel = Controller.getOrganizacao();
    private long tela = Tela.TELA_PARAMETROS_LAYOUT_DE_CHEQUE.getTela();

    private String NameObject() {
        return (String) "Layout Cheques";
    }

    public void repopularCombos() {

        try {

            Collection<ContaBancariaModel> lColecao = contaBancariaBO.obterTodos(organizacaoModel);

            final EventList<ContaBancariaModel> lRegistros = GlazedLists.eventList(lColecao);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboConta, lRegistros, new ContaBancariaTextFilterator());
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

    public LayoutCheque() throws SystemException {

        initComponents();

        campoCodigo.setVisible(false);


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        abaCompensarCheque1 = new javax.swing.JPanel();
        campoCodigo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboConta = new javax.swing.JComboBox();
        labelTitular = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtExtensoLinha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtExtensoCompLinha = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtPortadorLinha = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtCidadeLinha = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtDiaLinha = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtMesLinha = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jtAnoLinha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtValorLinha = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jtValorColuna = new javax.swing.JTextField();
        jtExtensoColuna = new javax.swing.JTextField();
        jtExtensoCompColuna = new javax.swing.JTextField();
        jtPortadorColuna = new javax.swing.JTextField();
        jtCidadeColuna = new javax.swing.JTextField();
        jtDiaColuna = new javax.swing.JTextField();
        jtMesColuna = new javax.swing.JTextField();
        jtAnoColuna = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Layout de Cheques");

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

        botaoSalvar.setMnemonic('L');
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoAlterar.setMnemonic('L');
        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        abaCompensarCheque1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        campoCodigo.setEditable(false);

        jLabel11.setText("Conta Bancária");

        comboConta.setFont(new java.awt.Font("Arial", 0, 10));
        comboConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContaActionPerformed(evt);
            }
        });

        labelTitular.setBackground(new java.awt.Color(222, 218, 210));
        labelTitular.setFont(new java.awt.Font("Arial", 0, 12));
        labelTitular.setForeground(new java.awt.Color(0, 102, 255));
        labelTitular.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 51)), "Correntista", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 153)), "Coordenadas das Linhas"));

        jLabel3.setText("Extenso");

        jLabel5.setText("Extenso");

        jLabel7.setText("Portador");

        jLabel9.setText("Cidade");

        jLabel12.setText("Dia");

        jLabel14.setText("Mes");

        jLabel16.setText("Ano");

        jLabel1.setText("Valor");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jtValorLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jtExtensoLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jtExtensoCompLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jtPortadorLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jtCidadeLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jtDiaLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtMesLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jtAnoLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtExtensoLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtValorLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtExtensoCompLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtPortadorLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtCidadeLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtDiaLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(jtAnoLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel16))
                            .addComponent(jtMesLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 102)), "Coordenadas das Colunas"));

        jLabel18.setText("Extenso");

        jLabel19.setText("Extenso");

        jLabel20.setText("Portador");

        jLabel21.setText("Cidade");

        jLabel22.setText("Dia");

        jLabel23.setText("Mes");

        jLabel24.setText("Ano");

        jLabel25.setText("Valor");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(3, 3, 3))
                    .addComponent(jtValorColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jtExtensoColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jtExtensoCompColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jtPortadorColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jtCidadeColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jtDiaColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtMesColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtAnoColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jtValorColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel25)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtExtensoColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtExtensoCompColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jtPortadorColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtCidadeColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel23)
                        .addComponent(jLabel24))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtMesColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtAnoColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtDiaColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout abaCompensarCheque1Layout = new javax.swing.GroupLayout(abaCompensarCheque1);
        abaCompensarCheque1.setLayout(abaCompensarCheque1Layout);
        abaCompensarCheque1Layout.setHorizontalGroup(
            abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(22, 22, 22)
                                .addComponent(labelTitular, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(66, 66, 66))
        );
        abaCompensarCheque1Layout.setVerticalGroup(
            abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarCheque1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelTitular, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abaCompensarCheque1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abaCompensarCheque1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        abaCompensarCheque1.getAccessibleContext().setAccessibleName("abaEstorno");

        jTabbedPane1.addTab("Layout  de Cheques", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        //limpar tela de estorno
        comboConta.setSelectedItem(null);
        labelTitular.setText("");
        jtAnoColuna.setText("");
        jtAnoLinha.setText("");
        jtValorColuna.setText("");
        jtDiaColuna.setText("");
        jtDiaLinha.setText("");
        jtExtensoColuna.setText("");
        jtExtensoCompColuna.setText("");
        jtExtensoLinha.setText("");
        jtExtensoCompLinha.setText("");
        jtValorLinha.setText("");
        jtMesColuna.setText("");
        jtMesLinha.setText("");
        jtPortadorColuna.setText("");
        jtPortadorLinha.setText("");
        jtCidadeColuna.setText("");
        jtCidadeLinha.setText("");

        botaoSalvar.setEnabled(true);
        botaoAlterar.setEnabled(false);


    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboConta.getSelectedItem() == null) {
            comboConta.requestFocus();
            return false;
        }

        if (campoCodigo.getText().isEmpty()) {

            exibeMensagemAviso("Erro ao gerar ID Layout", "Layout Cheques");

            return false;
        }




        return true;
    }

private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed

    String valorCombo = null;

    if (comboConta.getSelectedItem() != null) {
        valorCombo = comboConta.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            if (validaCampos()) {

                LayoutChequeModel tab = new LayoutChequeModel();
                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(organizacaoModel);
                tab.getPk().setId(PempecKeyGenerator.generateKey());


                if (comboConta.getSelectedItem() != null && ((ContaBancariaModel) comboConta.getSelectedItem()).getPk() != null) {

                    tab.setConta(new ContaBancariaModel());
                    tab.getConta().setPk(new PKModel());
                    tab.getConta().getPk().setId(((ContaBancariaModel) comboConta.getSelectedItem()).getPk().getId());

                }

                tab.setAnoColuna(Integer.parseInt(jtAnoColuna.getText()));
                tab.setAnoLinha(Integer.parseInt(jtAnoLinha.getText()));
                tab.setMesColuna(Integer.parseInt(jtMesColuna.getText()));
                tab.setMesLinha(Integer.parseInt(jtMesLinha.getText()));
                tab.setDiaColuna(Integer.parseInt(jtDiaColuna.getText()));
                tab.setDiaLinha(Integer.parseInt(jtDiaLinha.getText()));
                tab.setValorColuna(Integer.parseInt(jtValorColuna.getText()));
                tab.setValorLinha(Integer.parseInt(jtValorLinha.getText()));
                tab.setPortadorColuna(Integer.parseInt(jtPortadorColuna.getText()));
                tab.setPortadorLinha(Integer.parseInt(jtPortadorLinha.getText()));
                tab.setCidadeColuna(Integer.parseInt(jtCidadeColuna.getText()));
                tab.setCidadeLinha(Integer.parseInt(jtCidadeLinha.getText()));
                tab.setExtensoColuna(Integer.parseInt(jtExtensoColuna.getText()));
                tab.setExtensoLinha(Integer.parseInt(jtExtensoLinha.getText()));
                tab.setExtensoCompColuna(Integer.parseInt(jtExtensoCompColuna.getText()));
                tab.setExtensoCompLinha(Integer.parseInt(jtExtensoCompLinha.getText()));

                tab.setMovimentoDiarioModel(registroMovimento("Inserir", tab.getPk().getId(), "Layout de cheque da conta : " + valorCombo, new Double(0), "Inserido"));


                layoutChequeBO.inserir(tab);


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
        exibeMensagemAviso("Campo descrição obrigatório.", null);
    }

}//GEN-LAST:event_botaoSalvarActionPerformed
//if metodo
private void comboContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContaActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;


    if (comboConta.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {


        try {

            LayoutChequeModel tab = new LayoutChequeModel();
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(organizacaoModel);

            ContaBancariaModel conta = new ContaBancariaModel();
            conta.setPk(new PKModel());
            conta.getPk().setOrganizacao(organizacaoModel);
            conta.setConta(comboConta.getSelectedItem().toString());

            conta = contaBancariaBO.consultarPorTemplate(conta);

            tab.setConta(conta);

            tab = layoutChequeBO.consultarPorTemplate(tab);


            if (conta != null && !conta.getTitular().isEmpty()) {
                labelTitular.setText(conta.getTitular());
            }

            //if (tab != null && tab.getPk() != null) {
            if (tab != null && !tab.getPk().getId().isEmpty()) {
                botaoSalvar.setEnabled(false);
                botaoAlterar.setEnabled(true);

                campoCodigo.setText(tab.getPk().getId());
                jtValorColuna.setText(String.valueOf(tab.getValorColuna()));
                jtValorLinha.setText(String.valueOf(tab.getValorLinha()));
                jtAnoColuna.setText(String.valueOf(tab.getAnoColuna()));
                jtAnoLinha.setText(String.valueOf(tab.getAnoLinha()));
                jtDiaColuna.setText(String.valueOf(tab.getDiaColuna()));
                jtDiaLinha.setText(String.valueOf(tab.getDiaLinha()));
                jtMesColuna.setText(String.valueOf(tab.getMesColuna()));
                jtMesLinha.setText(String.valueOf(tab.getMesLinha()));
                jtExtensoColuna.setText(String.valueOf(tab.getExtensoColuna()));
                jtExtensoLinha.setText(String.valueOf(tab.getExtensoLinha()));
                jtExtensoCompColuna.setText(String.valueOf(tab.getExtensoCompColuna()));
                jtExtensoCompLinha.setText(String.valueOf(tab.getExtensoCompLinha()));
                jtPortadorColuna.setText(String.valueOf(tab.getPortadorColuna()));
                jtPortadorLinha.setText(String.valueOf(tab.getPortadorLinha()));
                jtCidadeColuna.setText(String.valueOf(tab.getCidadeColuna()));
                jtCidadeLinha.setText(String.valueOf(tab.getCidadeLinha()));


            } else {


                botaoSalvar.setEnabled(true);
                botaoAlterar.setEnabled(false);


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

}//GEN-LAST:event_comboContaActionPerformed

private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

    String valorCombo = null;

    if (comboConta.getSelectedItem() != null) {
        valorCombo = comboConta.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {

                LayoutChequeModel tab = new LayoutChequeModel();
                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(organizacaoModel);
                tab.getPk().setId(campoCodigo.getText());


                if (comboConta.getSelectedItem() != null && ((ContaBancariaModel) comboConta.getSelectedItem()).getPk() != null) {

                    tab.setConta(new ContaBancariaModel());
                    tab.getConta().setPk(new PKModel());
                    tab.getConta().getPk().setId(((ContaBancariaModel) comboConta.getSelectedItem()).getPk().getId());

                }

                tab.setAnoColuna(Integer.parseInt(jtAnoColuna.getText()));
                tab.setAnoLinha(Integer.parseInt(jtAnoLinha.getText()));
                tab.setMesColuna(Integer.parseInt(jtMesColuna.getText()));
                tab.setMesLinha(Integer.parseInt(jtMesLinha.getText()));
                tab.setDiaColuna(Integer.parseInt(jtDiaColuna.getText()));
                tab.setDiaLinha(Integer.parseInt(jtDiaLinha.getText()));
                tab.setValorColuna(Integer.parseInt(jtValorColuna.getText()));
                tab.setValorLinha(Integer.parseInt(jtValorLinha.getText()));
                tab.setPortadorColuna(Integer.parseInt(jtPortadorColuna.getText()));
                tab.setPortadorLinha(Integer.parseInt(jtPortadorLinha.getText()));
                tab.setCidadeColuna(Integer.parseInt(jtCidadeColuna.getText()));
                tab.setCidadeLinha(Integer.parseInt(jtCidadeLinha.getText()));
                tab.setExtensoColuna(Integer.parseInt(jtExtensoColuna.getText()));
                tab.setExtensoLinha(Integer.parseInt(jtExtensoLinha.getText()));
                tab.setExtensoCompColuna(Integer.parseInt(jtExtensoCompColuna.getText()));
                tab.setExtensoCompLinha(Integer.parseInt(jtExtensoCompLinha.getText()));

                tab.setMovimentoDiarioModel(registroMovimento("Alterar", tab.getPk().getId(), "Layout de cheque da conta  : " + valorCombo, new Double(0), "Alterado"));


                layoutChequeBO.alterar(tab);


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
        exibeMensagemAviso("Campo descrição obrigatório.", null);
    }


}//GEN-LAST:event_botaoAlterarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque1;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JComboBox comboConta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jtAnoColuna;
    private javax.swing.JTextField jtAnoLinha;
    private javax.swing.JTextField jtCidadeColuna;
    private javax.swing.JTextField jtCidadeLinha;
    private javax.swing.JTextField jtDiaColuna;
    private javax.swing.JTextField jtDiaLinha;
    private javax.swing.JTextField jtExtensoColuna;
    private javax.swing.JTextField jtExtensoCompColuna;
    private javax.swing.JTextField jtExtensoCompLinha;
    private javax.swing.JTextField jtExtensoLinha;
    private javax.swing.JTextField jtMesColuna;
    private javax.swing.JTextField jtMesLinha;
    private javax.swing.JTextField jtPortadorColuna;
    private javax.swing.JTextField jtPortadorLinha;
    private javax.swing.JTextField jtValorColuna;
    private javax.swing.JTextField jtValorLinha;
    private javax.swing.JLabel labelTitular;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
}
