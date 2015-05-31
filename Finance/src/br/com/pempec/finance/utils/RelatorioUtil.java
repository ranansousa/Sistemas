package br.com.pempec.finance.utils;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe responsável por exportar Relatório para os formatos: -> View
 * (Visualizar com o JasperView). -> PDF -> XLS
 *
 * @author Pempec
 *
 */
public class RelatorioUtil {

    private final String PATH_RELATORIO = "br/com/pempec/finance/reports/";

    public void imprimirImpressoraCollection(String nomeArquivo, Map<String, Object> parametros, Collection coll) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, new JRBeanCollectionDataSource(coll));

            JasperPrintManager.printPages(print, 0, coll.size() - 1, true);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SystemException("Erro ao gerar relatório!\n" + ex.getMessage() + "\n" + ex.getLocalizedMessage());

        }

    }

    public void imprimirImpressora(String nomeArquivo, Map<String, Object> parametros, Collection coll) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, new JRBeanCollectionDataSource(coll));

            JasperPrintManager.printPage(print, 0, true);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SystemException("Erro ao gerar relatório!\n" + ex.getMessage() + "\n" + ex.getLocalizedMessage());

        }

    }

    public void visualizar(String nomeArquivo, Map<String, Object> parametros, Collection coll) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, new JRBeanCollectionDataSource(coll));

            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SystemException("Erro ao gerar relatório!\n" + ex.getMessage() + "\n" + ex.getLocalizedMessage());

        }

    }

    public void visualizar(String nomeArquivo, Map<String, Object> parametros) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros);

            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SystemException("Erro ao gerar relatório!" + ex.getLocalizedMessage());

        }

    }

    public void visualizar(String nomeArquivo, Map<String, Object> parametros, Connection connection) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, connection);

            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SystemException("Erro ao gerar relatório!" + ex.getLocalizedMessage());

        }

    }

    public void exportarPDF(String nomeArquivo, Map<String, Object> parametros, Collection collection) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, new JRBeanCollectionDataSource(collection));

            JFileChooser jfc = new JFileChooser();

            jfc.setMultiSelectionEnabled(false);

            jfc.setAcceptAllFileFilterUsed(false);

            FileFilter flf = new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return "Arquivos PDF";
                }
            };

            jfc.addChoosableFileFilter(flf);

            if (jfc.showSaveDialog(null) == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File arquivo = jfc.getSelectedFile();

            JasperExportManager.exportReportToPdfFile(print, arquivo.getPath() + ".pdf");

        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }

    }

    public void exportarPDF(String nomeArquivo, Map<String, Object> parametros) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros);

            JFileChooser jfc = new JFileChooser();

            jfc.setMultiSelectionEnabled(false);

            jfc.setAcceptAllFileFilterUsed(false);

            FileFilter flf = new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return "Arquivos PDF";
                }
            };

            jfc.addChoosableFileFilter(flf);

            if (jfc.showSaveDialog(null) == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File arquivo = jfc.getSelectedFile();

            JasperExportManager.exportReportToPdfFile(print, arquivo.getPath() + ".pdf");

        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }

    }

    public void exportarPDF(String nomeArquivo, Map<String, Object> parametros, Connection connection) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, connection);

            JFileChooser jfc = new JFileChooser();

            jfc.setMultiSelectionEnabled(false);

            jfc.setAcceptAllFileFilterUsed(false);

            FileFilter flf = new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return "Arquivos PDF";
                }
            };

            jfc.addChoosableFileFilter(flf);

            if (jfc.showSaveDialog(null) == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File arquivo = jfc.getSelectedFile();

            JasperExportManager.exportReportToPdfFile(print, arquivo.getPath() + ".pdf");

        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }

    }

    public File exportarPDFtoFile(String nomeArquivo, Map<String, Object> parametros) throws SystemException, ApplicationException {

        if (Controller.verificaParametroAtivo(Parametro.G003.getCodigo())) {
            throw new ApplicationException(Controller.sendMessageParametro(Parametro.G003.getCodigo()));
        } else {
            if (Controller.verificaParametroAtivo(Parametro.G004.getCodigo())) {
                throw new ApplicationException(Controller.sendMessageParametro(Parametro.G004.getCodigo()));
            }
        }

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros);

            JasperExportManager.exportReportToPdfFile(print, System.getProperty("user.dir") + "\\temp\\" + nomeArquivo.replaceAll("jasper", "pdf"));

            return new File(System.getProperty("user.dir") + "\\temp\\" + nomeArquivo.replaceAll("jasper", "pdf"));

        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }

    }

    public File exportarPDFtoFile(String nomeArquivo, Map<String, Object> parametros, Connection connection) throws SystemException, ApplicationException {

        if (Controller.verificaParametroAtivo(Parametro.G003.getCodigo())) {
            throw new ApplicationException(Controller.sendMessageParametro(Parametro.G003.getCodigo()));
        } else {
            if (Controller.verificaParametroAtivo(Parametro.G004.getCodigo())) {
                throw new ApplicationException(Controller.sendMessageParametro(Parametro.G004.getCodigo()));
            }
        }

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, connection);

            JasperExportManager.exportReportToPdfFile(print, System.getProperty("user.dir") + "\\temp\\" + nomeArquivo.replaceAll("jasper", "pdf"));

            return new File(System.getProperty("user.dir") + "\\temp\\" + nomeArquivo.replaceAll("jasper", "pdf"));

        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }

    }

    public File exportarPDFtoFile(String nomeArquivo, Map<String, Object> parametros, Collection collection) throws SystemException, ApplicationException {

        if (Controller.verificaParametroAtivo(Parametro.G003.getCodigo())) {
            throw new ApplicationException(Controller.sendMessageParametro(Parametro.G003.getCodigo()));
        } else {
            if (Controller.verificaParametroAtivo(Parametro.G004.getCodigo())) {
                throw new ApplicationException(Controller.sendMessageParametro(Parametro.G004.getCodigo()));
            }
        }

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, new JRBeanCollectionDataSource(collection));

            JasperExportManager.exportReportToPdfFile(print, System.getProperty("user.dir") + "\\temp\\" + nomeArquivo.replaceAll("jasper", "pdf"));

            return new File(System.getProperty("user.dir") + "\\temp\\" + nomeArquivo.replaceAll("jasper", "pdf"));

        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }

    }

    public void exportarXLS(String nomeArquivo, Map<String, Object> parametros, Collection collection) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, new JRBeanCollectionDataSource(collection));

            JFileChooser jfc = new JFileChooser();

            jfc.setMultiSelectionEnabled(false);

            jfc.setAcceptAllFileFilterUsed(false);

            FileFilter flf = new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return "Arquivos Excel";
                }
            };

            jfc.addChoosableFileFilter(flf);

            if (jfc.showSaveDialog(null) == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File arquivo = jfc.getSelectedFile();

            JExcelApiExporter exporter = new JExcelApiExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, arquivo.getPath() + ".xls");
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);

            exporter.exportReport();

        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }

    }

    public void exportarXLS(String nomeArquivo, Map<String, Object> parametros) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros);

            JFileChooser jfc = new JFileChooser();

            jfc.setMultiSelectionEnabled(false);

            jfc.setAcceptAllFileFilterUsed(false);

            FileFilter flf = new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return "Arquivos Excel";
                }
            };

            jfc.addChoosableFileFilter(flf);

            if (jfc.showSaveDialog(null) == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File arquivo = jfc.getSelectedFile();

            JExcelApiExporter exporter = new JExcelApiExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, arquivo.getPath() + ".xls");
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);

            exporter.exportReport();

        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }

    }

    public void exportarXLS(String nomeArquivo, Map<String, Object> parametros, Connection connection) throws SystemException {

        JasperPrint print;

        try {

            InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO + nomeArquivo);

            parametros.put("PATH_RELATORIO", PATH_RELATORIO);

            print = JasperFillManager.fillReport(stream, parametros, connection);

            JFileChooser jfc = new JFileChooser();

            jfc.setMultiSelectionEnabled(false);

            jfc.setAcceptAllFileFilterUsed(false);

            FileFilter flf = new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return "Arquivos Excel";
                }
            };

            jfc.addChoosableFileFilter(flf);

            if (jfc.showSaveDialog(null) == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File arquivo = jfc.getSelectedFile();

            JExcelApiExporter exporter = new JExcelApiExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, arquivo.getPath() + ".xls");
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);

            exporter.exportReport();

        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }

    }
}
