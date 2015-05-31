package br.com.pempec.finance.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Adm
 */
public final class MaskUtils {

    public final static DefaultFormatterFactory mascaras(String mascara) {

        MaskFormatter mask = null;

        try {

            mask = new MaskFormatter(mascara);
            mask.setValidCharacters("0123456789");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(mask, mask);
        return fac;

    }

    public final static DefaultFormatterFactory mascaraCnpj() {

        MaskFormatter cnpj = null;

        try {

            cnpj = new MaskFormatter("##.###.###/####-##");
            cnpj.setValidCharacters("0123456789");
            cnpj.setPlaceholder(" ");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(cnpj, cnpj);
        return fac;
    }

    public final static DefaultFormatterFactory mascaraIpAdress() {

        MaskFormatter ip = null;

        try {

            ip = new MaskFormatter("###.###.###.###");
            ip.setValidCharacters("0123456789");
            ip.setPlaceholder(" ");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(ip, ip);

        return fac;
    }

    public final static DefaultFormatterFactory mascaraCpf() {

        MaskFormatter aux = null;

        try {

            aux = new MaskFormatter("###.###.###-##");
            aux.setValidCharacters("0123456789");
            aux.setPlaceholder(" ");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(aux, aux);
        return fac;
    }

    public final static DefaultFormatterFactory mascaraCep() {

        MaskFormatter aux = null;

        try {

            aux = new MaskFormatter("##.###-###");
            aux.setValidCharacters("0123456789");
            aux.setPlaceholder(" ");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(aux, aux);
        return fac;
    }

    public final static DefaultFormatterFactory mascaraTelefone() {

        MaskFormatter aux = null;

        try {

            aux = new MaskFormatter("####-####");
            aux.setValidCharacters("0123456789");
            aux.setPlaceholder(" ");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(aux, aux);
        return fac;
    }

    public final static DefaultFormatterFactory mascaraData() {

        MaskFormatter aux = null;

        try {

            aux = new MaskFormatter("##/##/####");
            aux.setValidCharacters("0123456789");
            aux.setPlaceholder(" ");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(aux, aux);
        return fac;
    }

    public final static DefaultFormatterFactory mascaraCodigoBarras() {

        MaskFormatter aux = null;

        try {
            //Levantar mascara de codigo de barras
            aux = new MaskFormatter("#####.#####.#####.###### #####.###### # ##############");
            aux.setValidCharacters("0123456789");
            aux.setPlaceholder(" ");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(aux, aux);
        return fac;
    }

    public final static DefaultFormatterFactory mascaraContaContabil() {

        MaskFormatter conta = null;

        try {

            conta = new MaskFormatter("#.#.#.#.#");
            conta.setValidCharacters("0123456789");
            conta.setPlaceholder(" ");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(conta, conta);
        return fac;
    }

    public final static DefaultFormatterFactory mascara(String mascara) {

        MaskFormatter conta = null;

        try {

            conta = new MaskFormatter(mascara);
            conta.setValidCharacters("0123456789");

        } catch (ParseException e) {

            e.printStackTrace();

        }

        DefaultFormatterFactory fac = new DefaultFormatterFactory(conta, conta);
        return fac;
    }
}
