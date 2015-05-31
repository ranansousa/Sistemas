package br.com.pempec.finance.utils;

import java.io.Serializable;
import java.math.BigInteger;

//Equipe PEMPEC
public class ExtensoCemUnidade implements Serializable {

    public static String ZERO = "zero";
    public static String[] unidades = {"", "um", "dois", "tr�s", "quatro",
        "cinco", "seis", "sete", "oito", "nove", "dez", "onze", "doze",
        "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito",
        "dezenove"};
    public static String[] dezenas = {"", "", "vinte", "trinta", "quarenta",
        "cinq�enta", "sessenta", "setenta", "oitenta", "noventa", "cem"};
    public static String[] centenas = {"", "cento", "duzentos", "trezentos",
        "quatrocentos", "quinhentos", "seiscentos", "setecentos",
        "oitocentos", "novecentos"};
    public static String SEPARADOR_MENOR = " e ";
    public static String SEPARADOR_MAIOR = " , "; // 2 espacos para ficar
    // igual ao " e "
    public static String[] ordensSingular = {"", "mil", "milh�o", "bilh�o",
        "trilh�o", "quatrilh�o", "quintilh�o", "sextilh�o", "setilh�o",
        "octilh�o", "nonilh�o", "decilh�o", "undecilh�o", "dodecilh�o",
        "tredecilh�o", "quatordecilh�o", "quindecilh�o", "sedecilh�o",
        "septendecilh�o"};
    public static String[] ordensPlural = {"", "mil", "milh�es", "bilh�es",
        "trilh�es", "quatrilh�es", "quintilh�es", "sextilh�es",
        "setilh�es", "octilh�es", "decilh�es", "undecilh�es",
        "dodecilh�es", "tredecilh�es", "quatordecilh�es", "quindecilh�es",
        "sedecilh�es", "septendecilh�es"};
    public static BigInteger CEM = BigInteger.valueOf(1000);
    public static BigInteger NUMERO_MAXIMO = new BigInteger(
            "999999999999999999999999999999999999999999999999999999");

    private static String unidades(int numero) {
        if (numero == 0) {
            return "";
        }
        return SEPARADOR_MENOR + unidades[numero];
    }

    private static String dezenas(int numero) {
        if (numero == 0) {
            return "";
        }
        if (numero < 20) {
            return unidades(numero);
        }
        return SEPARADOR_MENOR + dezenas[numero / 10] + unidades(numero % 10);
    }

    private static String centenas(int numero) {
        if (numero == 0) {
            return "";
        }
        if (numero <= 100) {
            return dezenas(numero);
        }
        return SEPARADOR_MAIOR + centenas[numero / 100] + dezenas(numero % 100);
    }

    private static String ordens(long numero, int grandeza) {
        if (numero == 0) {
            return "";
        }
        if (numero < 1000) {
            return centenas((int) numero);
        }

        int menor = (int) (numero % 1000);
        long maior = numero / 1000;
        int proximoMenor = (int) (maior % 1000);

        if (proximoMenor == 0) {
            return ordens(maior, grandeza + 1) + centenas(menor);
        }
        if (proximoMenor == 1) {
            return ordens(maior, grandeza + 1) + " " + ordensSingular[grandeza]
                    + centenas(menor);
        }
        return ordens(maior, grandeza + 1) + " " + ordensPlural[grandeza]
                + centenas(menor);
    }

    private static String ordens(BigInteger numero, int grandeza) {
        if (numero.equals(BigInteger.ZERO)) {
            return "";
        }
        if (numero.compareTo(CEM) == -1) {
            return centenas((int) numero.longValue());
        }

        BigInteger[] resultado = numero.divideAndRemainder(CEM);

        int menor = (int) resultado[1].longValue();
        BigInteger maior = resultado[0];
        int proximoMenor = (int) maior.remainder(CEM).longValue();

        if (proximoMenor == 0) {
            return ordens(maior, grandeza + 1) + centenas(menor);
        }
        if (proximoMenor == 1) {
            return ordens(maior, grandeza + 1) + " " + ordensSingular[grandeza]
                    + centenas(menor);
        }
        return ordens(maior, grandeza + 1) + " " + ordensPlural[grandeza]
                + centenas(menor);
    }

    public static String converte(long numero) {
        if (numero == 0) {
            return ZERO;
        }
        return ordens(numero, 1).substring(3).replaceAll(" ,", ","); // tira
        // o
        // espaco
        // extra
    }

    public static String converte(BigInteger numero) {
        if (numero.equals(BigInteger.ZERO)) {
            return ZERO;
        }
        if (NUMERO_MAXIMO.compareTo(numero) < 0) {
            throw new RuntimeException("Numero acima do permitido");
        }
        return ordens(numero, 1).substring(3).replaceAll(" ,", ","); // tira
        // o
        // espaco
        // extra
    }
}// fim da class
