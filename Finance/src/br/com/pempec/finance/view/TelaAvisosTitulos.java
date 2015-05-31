package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.utils.tables.ColumnModelTelaAvisosTitulosPagar;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.tables.ColumnModelTelaAvisosTitulosReceber;
import br.com.pempec.finance.utils.tables.TableModelTelaAvisosTitulosPagar;
import br.com.pempec.finance.utils.tables.TableModelTelaAvisosTitulosReceber;
import java.awt.FontMetrics;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author EQUIPE PEMPEC
 */
public class TelaAvisosTitulos extends FinanceInternalFrame implements IRepopulador {

    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private TituloReceberBO tituloReceberBO = new TituloReceberBO();
    private SimpleDateFormat format = new SimpleDateFormat();
    private SimpleDateFormat formatAux = new SimpleDateFormat();

    private String NameObject() {

        return (String) "Títulos a Pagar";

    }

    private String NameObject2() {

        return (String) "Títulos a Receber";

    }

    public TelaAvisosTitulos() throws SystemException {

        initComponents();

        this.repopularCombos();
    }

    public void repopularCombos() {

        try {

            auxTituloPagar = new ArrayList<TituloPagarModel>();

            auxTituloReceber = new ArrayList<TituloReceberModel>();

            Date dataAtual = Controller.getDataServidorBD();

            List<TituloPagarModel> collTitulosPagar = tituloPagarBO.obterTodosAPagar(organizacaoModel);

            List<TituloReceberModel> collTituloReceber = tituloReceberBO.obterTodosAReceber(organizacaoModel);

            if (Controller.verificaParametroAtivo(Parametro.TA002.getCodigo())) {

                if (Controller.verificaParametroAtivo(Parametro.TA004.getCodigo())) {
                    auxTituloPagar.addAll(collTitulosPagar);
                } else {
                    if (Controller.verificaParametroAtivo(Parametro.TA005.getCodigo())) {
                        for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                            if (tituloPagarModel.getDataVencimento().after(dataAtual)) {
                                auxTituloPagar.add(tituloPagarModel);
                            }
                        }
                    } else {
                        if (Controller.verificaParametroAtivo(Parametro.TA006.getCodigo())) {
                            for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                if (tituloPagarModel.getDataVencimento().before(dataAtual)) {
                                    auxTituloPagar.add(tituloPagarModel);
                                }
                            }
                        } else {
                            if (Controller.verificaParametroAtivo(Parametro.TA007.getCodigo())) {
                                for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                    if (tituloPagarModel.getDataVencimento().compareTo(dataAtual) <= 0) {
                                        auxTituloPagar.add(tituloPagarModel);
                                    }
                                }
                            } else {
                                if (Controller.verificaParametroAtivo(Parametro.TA008.getCodigo())) {

                                    Date data = PempecParse.dateToDate((Controller.findByCodigo(Parametro.TA008.getCodigo())).getData());

                                    for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                        if (tituloPagarModel.getDataVencimento().compareTo(data) <= 0) {
                                            auxTituloPagar.add(tituloPagarModel);
                                        }
                                    }
                                } else {
                                    if (Controller.verificaParametroAtivo(Parametro.TA009.getCodigo())) {

                                        format.applyPattern("MM");

                                        String mes = format.format(dataAtual);

                                        formatAux.applyPattern("yyyy");

                                        String ano = formatAux.format(dataAtual);

                                        for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                            if (format.format(tituloPagarModel.getDataVencimento()).equals(mes)
                                                    && formatAux.format(tituloPagarModel.getDataVencimento()).equals(ano)) {
                                                auxTituloPagar.add(tituloPagarModel);
                                            }
                                        }
                                    } else {

                                        format.applyPattern("yyyy");

                                        String ano = format.format(dataAtual);

                                        for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                            if (format.format(tituloPagarModel.getDataVencimento()).equals(ano)) {
                                                auxTituloPagar.add(tituloPagarModel);
                                            }
                                        }

                                    }

                                }
                            }
                        }
                    }

                }

            } else {

                if (Controller.verificaParametroAtivo(Parametro.TA003.getCodigo())) {

                    if (Controller.verificaParametroAtivo(Parametro.TA004.getCodigo())) {
                        auxTituloReceber.addAll(collTituloReceber);
                    } else {
                        if (Controller.verificaParametroAtivo(Parametro.TA005.getCodigo())) {
                            for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                if (tituloReceberModel.getDataVencimento().after(dataAtual)) {
                                    auxTituloReceber.add(tituloReceberModel);
                                }
                            }
                        } else {
                            if (Controller.verificaParametroAtivo(Parametro.TA006.getCodigo())) {
                                for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                    if (tituloReceberModel.getDataVencimento().before(dataAtual)) {
                                        auxTituloReceber.add(tituloReceberModel);
                                    }
                                }
                            } else {
                                if (Controller.verificaParametroAtivo(Parametro.TA007.getCodigo())) {
                                    for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                        if (tituloReceberModel.getDataVencimento().compareTo(dataAtual) <= 0) {
                                            auxTituloReceber.add(tituloReceberModel);
                                        }
                                    }
                                } else {
                                    if (Controller.verificaParametroAtivo(Parametro.TA008.getCodigo())) {

                                        Date data = PempecParse.dateToDate((Controller.findByCodigo(Parametro.TA008.getCodigo())).getData());

                                        for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                            if (tituloReceberModel.getDataVencimento().compareTo(data) <= 0) {
                                                auxTituloReceber.add(tituloReceberModel);
                                            }
                                        }
                                    } else {
                                        if (Controller.verificaParametroAtivo(Parametro.TA009.getCodigo())) {

                                            format.applyPattern("MM");

                                            String mes = format.format(dataAtual);

                                            formatAux.applyPattern("yyyy");

                                            String ano = formatAux.format(dataAtual);

                                            for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                                if (format.format(tituloReceberModel.getDataVencimento()).equals(mes)
                                                        && formatAux.format(tituloReceberModel.getDataVencimento()).equals(ano)) {
                                                    auxTituloReceber.add(tituloReceberModel);
                                                }
                                            }

                                        } else {

                                            format.applyPattern("yyyy");

                                            String ano = format.format(dataAtual);

                                            for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                                if (format.format(tituloReceberModel.getDataVencimento()).equals(ano)) {
                                                    auxTituloReceber.add(tituloReceberModel);
                                                }
                                            }

                                        }

                                    }
                                }
                            }
                        }
                    }

                } else {

                    if (Controller.verificaParametroAtivo(Parametro.TA004.getCodigo())) {
                        auxTituloPagar.addAll(collTitulosPagar);
                        auxTituloReceber.addAll(collTituloReceber);
                    } else {
                        if (Controller.verificaParametroAtivo(Parametro.TA005.getCodigo())) {
                            for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                if (tituloPagarModel.getDataVencimento().after(dataAtual)) {
                                    auxTituloPagar.add(tituloPagarModel);
                                }
                            }
                            for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                if (tituloReceberModel.getDataVencimento().after(dataAtual)) {
                                    auxTituloReceber.add(tituloReceberModel);
                                }
                            }
                        } else {
                            if (Controller.verificaParametroAtivo(Parametro.TA006.getCodigo())) {
                                for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                    if (tituloPagarModel.getDataVencimento().before(dataAtual)) {
                                        auxTituloPagar.add(tituloPagarModel);
                                    }
                                }
                                for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                    if (tituloReceberModel.getDataVencimento().before(dataAtual)) {
                                        auxTituloReceber.add(tituloReceberModel);
                                    }
                                }
                            } else {
                                if (Controller.verificaParametroAtivo(Parametro.TA007.getCodigo())) {
                                    for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                        if (tituloPagarModel.getDataVencimento().compareTo(dataAtual) <= 0) {
                                            auxTituloPagar.add(tituloPagarModel);
                                        }
                                    }
                                    for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                        if (tituloReceberModel.getDataVencimento().compareTo(dataAtual) <= 0) {
                                            auxTituloReceber.add(tituloReceberModel);
                                        }
                                    }
                                } else {
                                    if (Controller.verificaParametroAtivo(Parametro.TA008.getCodigo())) {

                                        Date data = PempecParse.dateToDate((Controller.findByCodigo(Parametro.TA008.getCodigo())).getData());

                                        for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                            if (tituloPagarModel.getDataVencimento().compareTo(data) <= 0) {
                                                auxTituloPagar.add(tituloPagarModel);
                                            }
                                        }
                                        for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                            if (tituloReceberModel.getDataVencimento().compareTo(data) <= 0) {
                                                auxTituloReceber.add(tituloReceberModel);
                                            }
                                        }
                                    } else {
                                        if (Controller.verificaParametroAtivo(Parametro.TA009.getCodigo())) {

                                            format.applyPattern("MM");

                                            String mes = format.format(dataAtual);

                                            formatAux.applyPattern("yyyy");

                                            String ano = formatAux.format(dataAtual);

                                            for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                                if (format.format(tituloPagarModel.getDataVencimento()).equals(mes)
                                                        && formatAux.format(tituloPagarModel.getDataVencimento()).equals(ano)) {
                                                    auxTituloPagar.add(tituloPagarModel);
                                                }
                                            }

                                            for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                                if (format.format(tituloReceberModel.getDataVencimento()).equals(mes)
                                                        && formatAux.format(tituloReceberModel.getDataVencimento()).equals(ano)) {
                                                    auxTituloReceber.add(tituloReceberModel);
                                                }
                                            }
                                        } else {

                                            format.applyPattern("yyyy");

                                            String ano = format.format(dataAtual);

                                            for (TituloPagarModel tituloPagarModel : collTitulosPagar) {
                                                if (format.format(tituloPagarModel.getDataVencimento()).equals(ano)) {
                                                    auxTituloPagar.add(tituloPagarModel);
                                                }
                                            }
                                            for (TituloReceberModel tituloReceberModel : collTituloReceber) {
                                                if (format.format(tituloReceberModel.getDataVencimento()).equals(ano)) {
                                                    auxTituloReceber.add(tituloReceberModel);
                                                }
                                            }

                                        }

                                    }
                                }
                            }
                        }
                    }

                }

            }

            this.preencheTabelaTituloPagar();

            this.preencheTabelaTituloReceber();

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

        jPanel1 = new javax.swing.JPanel();
        panelAcrescimo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableTituloPagar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        panelAcrescimo1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableTituloReceber = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - FLUXO DE CAIXA");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(918, 280));

        panelAcrescimo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)), NameObject(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 153, 255)));
        panelAcrescimo.setForeground(new java.awt.Color(153, 153, 153));

        tableTituloPagar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableTituloPagar);

        javax.swing.GroupLayout panelAcrescimoLayout = new javax.swing.GroupLayout(panelAcrescimo);
        panelAcrescimo.setLayout(panelAcrescimoLayout);
        panelAcrescimoLayout.setHorizontalGroup(
            panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
            .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAcrescimoLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelAcrescimoLayout.setVerticalGroup(
            panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
            .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAcrescimoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAcrescimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAcrescimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(918, 280));

        panelAcrescimo1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), NameObject2(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 153, 255)));
        panelAcrescimo1.setForeground(new java.awt.Color(153, 153, 153));

        tableTituloReceber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableTituloReceber);

        javax.swing.GroupLayout panelAcrescimo1Layout = new javax.swing.GroupLayout(panelAcrescimo1);
        panelAcrescimo1.setLayout(panelAcrescimo1Layout);
        panelAcrescimo1Layout.setHorizontalGroup(
            panelAcrescimo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 884, Short.MAX_VALUE)
            .addGroup(panelAcrescimo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAcrescimo1Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelAcrescimo1Layout.setVerticalGroup(
            panelAcrescimo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
            .addGroup(panelAcrescimo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAcrescimo1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAcrescimo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAcrescimo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel panelAcrescimo;
    private javax.swing.JPanel panelAcrescimo1;
    private javax.swing.JTable tableTituloPagar;
    private javax.swing.JTable tableTituloReceber;
    // End of variables declaration//GEN-END:variables
    private ArrayList<TituloPagarModel> auxTituloPagar;
    private ArrayList<TituloReceberModel> auxTituloReceber;

    private void preencheTabelaTituloPagar() {

        tableTituloPagar.setAutoCreateColumnsFromModel(false);
        tableTituloPagar.setModel(new TableModelTelaAvisosTitulosPagar(auxTituloPagar));
        FontMetrics fm = tableTituloPagar.getFontMetrics(tableTituloPagar.getFont());
        tableTituloPagar.setColumnModel(new ColumnModelTelaAvisosTitulosPagar(fm));
        tableTituloPagar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableTituloPagar.getTableHeader().setReorderingAllowed(false);


    }

    private void preencheTabelaTituloReceber() {

        tableTituloReceber.setAutoCreateColumnsFromModel(false);
        tableTituloReceber.setModel(new TableModelTelaAvisosTitulosReceber(auxTituloReceber));
        FontMetrics fm = tableTituloReceber.getFontMetrics(tableTituloReceber.getFont());
        tableTituloReceber.setColumnModel(new ColumnModelTelaAvisosTitulosReceber(fm));
        tableTituloReceber.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableTituloReceber.getTableHeader().setReorderingAllowed(false);

    }
}
