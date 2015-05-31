package br.com.pempec.finance.utils;

import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.LayoutChequeModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Date;

/**
 *
 * @author Equipe Pempec
 */
public class ImpressaoChequeMatricial implements Printable {

    ContaBancariaChequeModel chequeModel;
    LayoutChequeModel layoutChequeModel;

    public ImpressaoChequeMatricial(ContaBancariaChequeModel cheque, LayoutChequeModel layout) {

        this.chequeModel = cheque;
        this.layoutChequeModel = layout;

    }

    public int print(Graphics g, PageFormat pf, int page) throws
            PrinterException {

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
        int indice = 10;

        int colValor = layoutChequeModel.getValorColuna() * indice;
        int linValor = layoutChequeModel.getValorLinha() * indice;

        int colExtenso = layoutChequeModel.getExtensoColuna() * indice;
        int linExtenso = layoutChequeModel.getExtensoLinha() * indice;

        int colExtensoComp = layoutChequeModel.getExtensoCompColuna() * indice;
        int linExtensoComp = layoutChequeModel.getExtensoCompLinha() * indice;

        int colPortador = layoutChequeModel.getPortadorColuna() * indice;
        int linPortador = layoutChequeModel.getPortadorLinha() * indice;

        int colCidade = layoutChequeModel.getCidadeColuna() * indice;
        int linCidade = layoutChequeModel.getCidadeLinha() * indice;

        int colDia = layoutChequeModel.getDiaColuna() * indice;
        int linDia = layoutChequeModel.getDiaLinha() * indice;

        int colMes = layoutChequeModel.getMesColuna() * indice;
        int linMes = layoutChequeModel.getMesLinha() * indice;

        int colAno = layoutChequeModel.getAnoColuna() * indice;
        int linAno = layoutChequeModel.getAnoLinha() * indice;

        OrganizacaoModel organizacaoModel = Controller.getOrganizacao();


        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g.setFont(new java.awt.Font("Arial", 10, 10));


        g.drawString("# " + PempecUtil.formataValor(chequeModel.getValor()) + " #", colValor, linValor);
        g.drawString("( " + new Extenso(chequeModel.getValor()).toString().toUpperCase() + " )", colExtenso, linExtenso);
        g.drawString("------------------------------------", colExtensoComp, linExtensoComp);
        g.drawString(chequeModel.getPortador(), colPortador, linPortador);
        g.drawString(organizacaoModel.getCidade().getDescricao().toUpperCase(), colCidade, linCidade);
        g.drawString(PempecParse.dateToString(chequeModel.getDataEmissao()).substring(0, 2), colDia, linDia);
        g.drawString(escreveMes(chequeModel.getDataEmissao()), colMes, linMes);
        g.drawString(PempecParse.dateToString(chequeModel.getDataEmissao()).substring(6, 10), colAno, linAno);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;


    }

    public String escreveMes(Date m) {



        String mes = PempecParse.dateToString(m).substring(3, 5);

        String mesExt = mes;

        if (mes.equals("01")) {
            mesExt = "Janeiro";
        }

        if (mes.equals("02")) {
            mesExt = "Fevereiro";
        }
        if (mes.equals("03")) {
            mesExt = "Marco";
        }
        if (mes.equals("04")) {
            mesExt = "Abril";
        }
        if (mes.equals("05")) {
            mesExt = "Maio";
        }
        if (mes.equals("06")) {
            mesExt = "Junho";
        }
        if (mes.equals("07")) {
            mesExt = "Julho";
        }
        if (mes.equals("08")) {
            mesExt = "Agosto";
        }
        if (mes.equals("09")) {
            mesExt = "Setembro";
        }
        if (mes.equals("10")) {
            mesExt = "Outubro";
        }
        if (mes.equals("11")) {
            mesExt = "Novembro";
        }
        if (mes.equals("12")) {
            mesExt = "Dezembro";
        }


        return mesExt.toUpperCase();

    }
}
