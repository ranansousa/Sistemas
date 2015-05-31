/**
 *
 */
package br.com.pempec.finance.utils;

import java.text.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Luis Alexandre
 *
 */
public final class PempecUtil {

    private static Locale locale = new Locale("pt", "BR");
    private static char[] acentos = new char[]{'�', '�', '�', '�', '�',
        '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�',
        '�', '�', '�', '�', '�', '�'};
    private static char[] semAcentos = new char[]{'a', 'a', 'a', 'a',
        'a', 'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i', 'o', 'o', 'o', 'o',
        'o', 'u', 'u', 'u', 'u', 'n', 'y'};
    private static Pattern pattern;
    private static Matcher matcher;

    public static String formataCpf(String cpf) {

        StringBuilder builder = new StringBuilder(cpf);
        builder.insert(3, '.');
        builder.insert(7, '.');
        builder.insert(11, '-');

        return builder.toString();
    }

    public static String geraNumeroLote() {
        String lote = null;

        String dataHoje = PempecParse.dateToString(new Date());
        String[] aux = dataHoje.split("/");

        //lote vai ser dia mes e ano
        //é possivel que o lote ja exista.
        //Entao a aplicacao vai verificar isso
        // caso exista lote, o novo numero deve ter final 01
        // se for o segundo e assim por diante
        lote = aux[0] + aux[1] + aux[2];

        return lote;
    }

    public static String geraNumeroLoteDeposito() {
        String lote = null;

        String dataHoje = PempecParse.dateToString(new Date());
        String[] aux = dataHoje.split("/");

        StringBuilder result = new StringBuilder();

        long time = System.nanoTime();

        time = (time / 1000000000);

        result.append(time);

        //lote vai ser dia mes e ano
        //é possivel que o lote ja exista.
        //Entao a aplicacao vai verificar isso
        // caso exista lote, o novo numero deve ter final 01
        // se for o segundo e assim por diante
        lote = aux[0] + aux[1] + aux[2];

        return lote + result;
    }

    public static String desFormataCpf(String cpf) {

        StringBuilder builder = new StringBuilder(cpf);
        builder.deleteCharAt(3);
        builder.deleteCharAt(7);
        builder.deleteCharAt(11);

        return builder.toString();
    }

    public static String formataCnpj(String cnpj) {

        StringBuilder builder = new StringBuilder(cnpj);
        builder.insert(2, '.');
        builder.insert(6, '.');
        builder.insert(10, '/');
        builder.insert(15, '-');

        return builder.toString();
    }

    public static String desFormataCnpj(String cnpj) {

        StringBuilder builder = new StringBuilder(cnpj);
        builder.deleteCharAt(2);
        builder.deleteCharAt(5);
        builder.deleteCharAt(8);
        builder.deleteCharAt(12);

        return builder.toString();
    }

    public static String formataCep(String cep) {

        StringBuilder builder = new StringBuilder(cep);
        builder.insert(2, '.');
        builder.insert(6, '-');

        return builder.toString();
    }

    public static String desFormataCep(String cep) {

        StringBuilder builder = new StringBuilder(cep);
        builder.deleteCharAt(2);
        builder.deleteCharAt(5);

        return builder.toString();
    }

    public static String formataData(String data) {

        StringBuilder builder = new StringBuilder(data);
        builder.insert(2, '/');
        builder.insert(5, '/');

        return builder.toString();
    }

    public static String desFormataData(String data) {

        StringBuilder builder = new StringBuilder(data);
        builder.deleteCharAt(2);
        builder.deleteCharAt(4);

        return builder.toString();
    }

    public static String converteDataDDMMYYYY(String data) {

        String[] format = data.split("/");

        return format[0] + format[1] + format[2];

    }

    public static String converteDataMMDDYYYY(String data) {

        String[] format = data.split("/");

        return format[1] + format[0] + format[2];

    }

    public static String converteDataYYYYMMDD(String data) {

        String[] format = data.split("/");

        return format[2] + format[1] + format[0];

    }

    public static String converteDataYYYYMMDDTracos(String data) {

        String[] format = data.split("/");

        return format[2] + "-" + format[1] + "-" + format[0];

    }

    public static String converteDataYYYYMMDDTracosHorasMinima(String data) {

        String[] format = data.split("/");

        return format[2] + "-" + format[1] + "-" + format[0] + " 00:00:00";

    }

    public static String converteDataYYYYMMDDTracosHorasMaxima(String data) {

        String[] format = data.split("/");

        return format[2] + "-" + format[1] + "-" + format[0] + " 23:59:59";

    }

    /**
     * M�todo que verifica se a data � v�lida
     *
     * @param data a ser verificada
     * @return true caso seja, falso caso contr�rio
     */
    public static Boolean validaData(String data) {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);

        try {

            dateFormat.setLenient(false);
            dateFormat.parse(data);

        } catch (ParseException pe) {

            return false;

        }

        return true;
    }

    public static Boolean validaAnoData(Date data) {

        Date dataRecebida = data;

        if (dataRecebida.getYear() < 1900) {
            return false;
        }

        return true;
    }

    /**
     * Valida se o CPF est� preenchido corretamente
     *
     * @param cpf
     * @return
     */
    public static Boolean validaPreenchimentoCpf(String cpf) {

        pattern = Pattern.compile("[0-9]{2,3}?\\.[0-9]{3}?\\.[0-9]{3}?\\-[0-9]{2}?");

        matcher = pattern.matcher(cpf);

        return matcher.matches();

    }

    /**
     * Valida se o CNPJ est� preenchido corretamente
     *
     * @param cnpj
     * @return
     */
    public static Boolean validaPreenchimentoCnpj(String cnpj) {

        pattern = Pattern.compile("[0-9]{2}?\\.[0-9]{3}?\\.[0-9]{3}?\\/[0-9]{4}?\\-[0-9]{2}?");

        matcher = pattern.matcher(cnpj);

        return matcher.matches();

    }

    /**
     * Valida se o CEP est� preenchido corretamente
     *
     * @param cep
     * @return
     */
    public static Boolean validaPreenchimentoCep(String cep) {

        pattern = Pattern.compile("[0-9]{2}?\\.[0-9]{3}-[0-9]{3}?");

        matcher = pattern.matcher(cep);

        return matcher.matches();

    }

    /**
     * Valida se a Data est� preenchido corretamente
     *
     * @param data
     * @return
     */
    public static Boolean validaPreenchimentoData(String data) {

        pattern = Pattern.compile("[0-9]{2}?\\/[0-9]{2}?\\/[0-9]{4}?");

        matcher = pattern.matcher(data);

        return matcher.matches();

    }

    /**
     * Valida se o RG est� preenchido corretamente
     *
     * @param rg
     * @return
     */
    public static Boolean validaPreenchimentoRG(String rg) {

        pattern = Pattern.compile("[0-9]{1}?\\.[0-9]{3}?\\.[0-9]{3}?");

        matcher = pattern.matcher(rg);

        return matcher.matches();

    }

    /**
     * Valida se o nome est� preenchido corretamente
     *
     * @param nome
     * @return
     */
    public static Boolean validaPreenchimentoNumero(String nome) {

        pattern = Pattern.compile("[0-9]+");

        matcher = pattern.matcher(nome);

        return matcher.matches();

    }

    /**
     * Valida se o nome est� preenchido corretamente
     *
     * @param nome
     * @return
     */
    public static Boolean validaPreenchimentoNome(String nome) {

        pattern = Pattern.compile("[a-zA-Z ]+");

        matcher = pattern.matcher(nome);

        return matcher.matches();

    }

    /**
     * Valida se o email est� preenchido corretamente
     *
     * @param email
     * @return
     */
    public static Boolean validaPreenchimentoEmail(String email) {

        pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9]+\\.[a-z]{2,}(\\.[a-zA-Z].){0,1}");

        matcher = pattern.matcher(email);

        return matcher.matches();

    }

    public static Date addDayDate(Date data, int qtd) {

        Calendar cal = Calendar.getInstance(locale);
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, qtd);
        return cal.getTime();

    }

    public static Date dimDayDate(Date data, int qtd) {

        Calendar cal = Calendar.getInstance(locale);
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, (qtd * (-1)));
        return cal.getTime();

    }

    public static Date addDayDate(String data, int qtd) {

        try {

            Calendar cal = Calendar.getInstance(locale);
            cal.setTime(PempecParse.stringToDate(data));
            cal.add(Calendar.DAY_OF_MONTH, qtd);
            return cal.getTime();


        } catch (Exception e) {

            return null;
        }

    }

    public static Date addMonthDate(Date data, int qtd) {

        Calendar cal = Calendar.getInstance(locale);
        cal.setTime(data);
        cal.add(Calendar.MONTH, qtd);
        return cal.getTime();

    }

    public static Date addMonthDate(String data, int qtd) {

        try {

            Calendar cal = Calendar.getInstance(locale);
            cal.setTime(PempecParse.stringToDate(data));
            cal.add(Calendar.MONTH, qtd);
            return cal.getTime();


        } catch (Exception e) {

            return null;
        }

    }

    public static Date addYearDate(Date data, int qtd) {

        Calendar cal = Calendar.getInstance(locale);
        cal.setTime(data);
        cal.add(Calendar.YEAR, qtd);
        return cal.getTime();

    }

    public static Date addYearDate(String data, int qtd) {

        try {

            Calendar cal = Calendar.getInstance(locale);
            cal.setTime(PempecParse.stringToDate(data));
            cal.add(Calendar.YEAR, qtd);
            return cal.getTime();

        } catch (Exception e) {

            return null;
        }

    }

    public static Date addHourOfDayDate(Date data, int qtd) {

        Calendar cal = Calendar.getInstance(locale);
        cal.setTime(data);
        cal.add(Calendar.HOUR_OF_DAY, qtd);
        return cal.getTime();

    }

    public static Date addHourOfDayDate(String data, int qtd) {

        try {

            Calendar cal = Calendar.getInstance(locale);
            cal.setTime(PempecParse.stringToDate(data));
            cal.add(Calendar.HOUR_OF_DAY, qtd);
            return cal.getTime();

        } catch (Exception e) {

            return null;
        }

    }

    public static Date addMinutesOfHourDate(Date data, int qtd) {

        Calendar cal = Calendar.getInstance(locale);
        cal.setTime(data);
        cal.add(Calendar.MINUTE, qtd);
        return cal.getTime();

    }

    public static Date addMinutesOfHourDate(String data, int qtd) {

        try {

            Calendar cal = Calendar.getInstance(locale);
            cal.setTime(PempecParse.stringToDate(data));
            cal.add(Calendar.MINUTE, qtd);
            return cal.getTime();

        } catch (Exception e) {

            return null;
        }

    }

    /**
     * M�todo que verifica se a dataInicio � maior que a dataFim, caso
     * afirmativo, retorna true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public Boolean isDataMenorIgual(Date dataInicio, Date dataFim) {
        return dataInicio.compareTo(dataFim) <= 0 ? true : false;
    }

    /**
     * M�todo que verifica se a dataInicio � menor que a dataFim, caso
     * afirmativo, retorna true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public Boolean isDataMenorIgual(String dataInicio, String dataFim) {
        return isDataMenorIgual(PempecParse.stringToDate(dataInicio), PempecParse.stringToDate(dataFim));
    }

    /**
     * M�todo que verifica se a dataInicio � maior que a dataFim, caso
     * afirmativo, retorna true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public Boolean isDataMaiorIgual(Date dataInicio, Date dataFim) {
        return dataInicio.compareTo(dataFim) >= 0 ? true : false;
    }

    /**
     * M�todo que verifica se a dataInicio � maior que a dataFim, caso
     * afirmativo, retorna true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public Boolean isDataMaiorIgual(String dataInicio, String dataFim) {
        return isDataMaiorIgual(PempecParse.stringToDate(dataInicio), PempecParse.stringToDate(dataFim));
    }

    /**
     * M�todo que verifica se a dataInicio � menor que a dataFim, caso
     * afirmativo, dever� retornar true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public boolean isDataMenor(String dataInicio, String dataFim) {
        return isDataMenor(PempecParse.stringToDate(dataInicio), PempecParse.stringToDate(dataFim));
    }

    /**
     * M�todo que verifica se a dataInicio � igual a dataFim, caso afirmativo,
     * retorna true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public boolean isDataIgual(Date dataInicio, Date dataFim) {
        return dataInicio.compareTo(dataFim) == 0 ? true : false;
    }

    /**
     * M�todo que verifica se a dataInicio � igual a dataFim, caso afirmativo,
     * dever� retornar true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public boolean isDataIgual(String dataInicio, String dataFim) {
        return isDataIgual(PempecParse.stringToDate(dataInicio), PempecParse.stringToDate(dataFim));
    }

    /**
     * M�todo que verifica se a dataInicio � maior que a dataFim, caso
     * afirmativo, retorna true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public boolean isDataMenor(Date dataInicio, Date dataFim) {
        return dataInicio.compareTo(dataFim) < 0 ? true : false;
    }

    /**
     * M�todo que verifica se a dataInicio � igual a dataFim, caso afirmativo,
     * retorna true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public boolean isDataMaior(Date dataInicio, Date dataFim) {
        return dataInicio.compareTo(dataFim) > 0 ? true : false;
    }

    /**
     * M�todo que verifica se a dataInicio � igual a dataFim, caso afirmativo,
     * dever� retornar true.
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public boolean isDataMaior(String dataInicio, String dataFim) {
        return isDataMaior(PempecParse.stringToDate(dataInicio), PempecParse.stringToDate(dataFim));
    }

    /**
     * Este m�todo remove as pontua��es e caracteres do texto obtido.
     *
     * @param texto
     * @return texto sem as pontua��es e caracteres
     */
    public String removePontuacoes(String texto) {

        if (texto == null && texto.trim().equals("")) {
            return texto;
        }

        for (int i = 0; i < acentos.length; i++) {

            texto = texto.replace(acentos[i], semAcentos[i]);
            texto = texto.replace(Character.toUpperCase(acentos[i]), Character.toUpperCase(semAcentos[i]));

        }

        return texto;

    }

    //metodo recebe um valor qualquer double
    //e retorna o mesmo valor formatado para o padrao local (BR)
    public static String formataValor(Double valor) {

        String numero = "";

        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        numero = nf.format(valor);

        return numero;
    }

    /**
     * Capitaliza uma String (deixa ela com a primeira letra maiuscula e as
     * outras letras minusculas);
     *
     * @param text O texto a ser capitalizado.
     * @param capitalizeAllWords Se <code>true</code>, cada palavra de uma frase
     * sera capitalizada, caso contrario apenas a primeira palavra sera
     * capitalizada.
     * @return A versao capitalizada do texto informado.
     */
    public static String capitalize(String text, Boolean capitalizeAllWords) {

        if (text == null || text.trim().equals("")) {
            return text;
        }

        String retorno = "";
        if (capitalizeAllWords) {

            String[] palavras = text.split("(\\s)+");
            for (int i = 0; i < palavras.length; i++) {
                if (palavras[i].length() >= 2) {
                    retorno += palavras[i].substring(0, 1).toUpperCase() + palavras[i].substring(1).toLowerCase();
                } else {
                    retorno += palavras[i].toUpperCase();
                }

                if (i < palavras.length - 1) {
                    retorno += " ";
                }
            }

        } else {

            if (text.length() >= 2) {
                retorno += text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
            } else {
                retorno += text.toUpperCase();
            }
        }

        return retorno;

    }

    public static String getMesExtenso(Date m) {

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

    public static boolean isDiaUtil(Date date) {
        //recebe a data, verifica se o dia nao é sabado ou domingo. se for, retorna false;
        // 0 - domingo 6-sabado

        if (date == null) {
            return false;
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        boolean isUtil = true;

        int dia = calendar.get(Calendar.DAY_OF_WEEK);

        if (dia == Calendar.SATURDAY || dia == Calendar.SUNDAY || Controller.verificaFeriado(date)) {
            isUtil = false;
        }

        return isUtil;

    }

    public static String concatenaComQuebraLinha(String... aux) {

        StringBuilder builder = new StringBuilder();

        for (String valor : aux) {
            if (valor != null && !valor.trim().isEmpty()) {
                builder.append(valor.trim()).append("\n");
            }
        }

        return builder.toString().trim().toUpperCase();

    }

    public static String concatenaComEspaco(String... aux) {

        StringBuilder builder = new StringBuilder();

        for (String valor : aux) {
            if (valor != null && !valor.trim().isEmpty()) {
                builder.append(valor.trim()).append(" ");
            }
        }

        return builder.toString().trim().toUpperCase();

    }

    public static String somenteNumero(String value) {

        try {

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < value.length(); i++) {

                char caracter = value.charAt(i);

                switch (caracter) {

                    case '0':
                        builder.append(caracter);
                        break;
                    case '1':
                        builder.append(caracter);
                        break;
                    case '2':
                        builder.append(caracter);
                        break;
                    case '3':
                        builder.append(caracter);
                        break;
                    case '4':
                        builder.append(caracter);
                        break;
                    case '5':
                        builder.append(caracter);
                        break;
                    case '6':
                        builder.append(caracter);
                        break;
                    case '7':
                        builder.append(caracter);
                        break;
                    case '8':
                        builder.append(caracter);
                        break;
                    case '9':
                        builder.append(caracter);
                        break;
                }

            }

            return builder.toString();

        } catch (Exception e) {
            return "";
        }

    }
}
