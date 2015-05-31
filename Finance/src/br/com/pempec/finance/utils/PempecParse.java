/**
 *
 */
package br.com.pempec.finance.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Luis Alexandre
 *
 */
public final class PempecParse {

    private static Locale locale = new Locale("pt", "BR");
    private static NumberFormat numFormat = NumberFormat.getNumberInstance(locale);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", locale);
    private static DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
    private static DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();

    public static String getMillisecondFromDate(Date dt) {
        return getFromDate(dt, "SSS");
    }

    public static String getHourFromDate(Date dt) {
        return getFromDate(dt, "HH");
    }

    public static String getMinuteFromDate(Date dt) {
        return getFromDate(dt, "mm");
    }

    public static String getSecondFromDate(Date dt) {
        return getFromDate(dt, "ss");
    }

    public static String getYearFromDate(Date dt) {
        return getFromDate(dt, "yyyy");
    }

    public static String getMonthFromDate(Date dt) {
        return getFromDate(dt, "MM");
    }

    public static String getDayFromDate(Date dt) {
        return getFromDate(dt, "dd");
    }

    public static String getFromDate(Date dt, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato, locale);
        String val = sdf.format(dt);
        return val.length() == 1 ? "0" + val : val;
    }

    public static Byte stringToByte(String valor) {
        Byte bt = null;
        try {
            if (valor != null) {
                bt = new Byte(numFormat.parse(valor).byteValue());
            }
        } catch (Exception e) {
        }
        return bt;
    }

    public static String numberToString(Number valor) {

        String number = null;

        if (valor != null) {
            number = numFormat.format(valor);
        } else {
            number = "";
        }
        return number;
    }

    public static String characterToString(Character valor) {
        if (valor == null) {
            return "";
        } else {
            return valor.toString();
        }
    }

    public static Character stringToCharacter(String valor) {
        if (valor == null) {
            return null;
        } else {
            return new Character(valor.charAt(0));
        }
    }

    public static Boolean stringToBoolean(String valor) {

        Boolean bool = null;

        if (valor == null || valor.trim().length() == 0) {
            bool = new Boolean(false);
        } else {
            bool = new Boolean(valor.trim().equals("0") ? false : true);
        }
        return bool;
    }

    public static String booleanToString(Boolean valor) {

        if (valor == null) {
            return "0";
        } else {
            return valor.booleanValue() ? "1" : "0";
        }
    }

    public static Short stringToShort(String valor) {

        Short it = null;
        try {
            if (valor != null) {
                it = new Short(numFormat.parse(valor.trim()).shortValue());
            }
        } catch (Exception e) {
        }
        return it;
    }

    public static Integer stringToInteger(String valor) {

        Integer it = null;
        try {
            if (valor != null) {
                it = new Integer(numFormat.parse(valor.trim()).intValue());
            }
        } catch (Exception e) {
        }
        return it;
    }

    public static Long stringToLong(String valor) {
        Long it = null;
        try {
            if (valor != null) {
                it = new Long(numFormat.parse(valor.trim()).longValue());
            }
        } catch (Exception e) {
        }

        return it;
    }

    public static Float stringToFloat(String valor) {
        Float it = null;
        try {
            if (valor != null) {
                it = new Float(numFormat.parse(valor.trim()).floatValue());
            }
        } catch (Exception e) {
        }
        return it;
    }

    public static Double stringToDouble(String valor) {
        Double it = null;
        try {
            if (valor != null) {
                it = new Double(numFormat.parse(valor).doubleValue());
            }
        } catch (Exception e) {
        }
        return it;
    }

    /**
     * Este metodo converte uma String em um objeto date do formato: dd/MM/yyyy
     *
     * @param data - String a ser convertida em um java.util.date
     * @return um java.util.date
     */
    public static Date stringToDate(String data) {

        Date date = null;

        if (data != null && data.trim().length() > 0) {
            try {
                date = simpleDateFormat.parse(data);
            } catch (Exception e) {
            }
        }
        return date;
    }

    /**
     * Este metodo converte uma String em um objeto date do formato: ddMMyyyy
     *
     * @param data - String a ser convertida em um java.util.date
     * @return um java.util.date
     */
    public static Date stringToDateWithoutBar(String data) {
        Date date = null;
        if (data != null && data.trim().length() > 0) {
            try {
                simpleDateFormat.applyPattern("ddMM");
                date = simpleDateFormat.parse(data);
            } catch (Exception e) {
            }
        }
        return date;
    }

    /**
     * Este metodo converte uma String em um objeto date do formato: dd/MM/yyyy
     * HH:mm:ss
     *
     * @param data - String a ser convertida em um java.util.date
     * @return um java.util.date
     */
    public static Date stringToDateHourSecond(String data) {
        Date date = null;
        if (data != null && data.trim().length() > 0) {
            try {
                simpleDateFormat.applyPattern("dd/MM/yyyy HH:mm:ss");
                date = simpleDateFormat.parse(data);
            } catch (Exception e) {
            }
        }
        return date;
    }

    /**
     * Este metodo converte uma Date em um objeto String do formato: dd/MM/yyyy
     * HH:mm
     *
     * @param data - Date a ser convertida em um java.util.String
     * @return um java.util.String
     */
    public static String dateHourToString(Date data) {
        simpleDateFormat.applyPattern("dd/MM/yyyy HH:mm:ss");
        return simpleDateFormat.format(data);

    }

    /**
     * Este metodo converte uma Date em um objeto String do formato: dd/MM/yyyy
     * HH:mm:ss
     *
     * @param data - Date a ser convertida em um java.util.String
     * @return um java.util.String
     */
    public static String dateHourSecondToString(Date data) {
        simpleDateFormat.applyPattern("dd/MM/yyyy HH:mm:ss");
        return simpleDateFormat.format(data);
    }

    /**
     * Este metodo converte uma String em um objeto date do formato: dd/MM/yyyy
     * HH:mm
     *
     * @param data - String a ser convertida em um java.util.date
     * @return um java.util.date
     */
    public static Date stringToDateHour(String data) {
        Date date = null;
        try {
            simpleDateFormat.applyPattern("dd/MM/yyyy HH:mm");
            date = simpleDateFormat.parse(data);
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * Este metodo converte uma Date em um objeto String do formato: dd/MM/yyyy
     *
     * @param data - Date a ser convertida em um java.util.String
     * @return um java.util.String
     */
    public static String dateToString(Date data) {
        if (data == null) {
            return "";
        }

        simpleDateFormat.applyPattern("dd/MM/yyyy");

        return simpleDateFormat.format(data);
    }

    /**
     * Este metodo converte uma Date em um objeto Date do formato: dd/MM/yyyy
     *
     * @param data - Date a ser convertida em um java.util.String
     * @return um java.util.String
     */
    public static Date dateToDate(Date data) {

        try {

            return simpleDateFormat.parse(simpleDateFormat.format(data));

        } catch (Exception e) {

            return null;
        }

    }

    /**
     * Este metodo converte uma Date em um objeto Date com horario maximo
     * formato: dd/MM/yyyy
     *
     * @param data - Date a ser convertida em um java.util.String
     * @return um java.util.String
     */
    public static Date dateToDateMaxTime(Date data) {

        try {

            Calendar calendar = Calendar.getInstance(locale);

            synchronized (calendar) {

                calendar.setTime(data);

                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MILLISECOND, 999);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);

                return calendar.getTime();

            }



        } catch (Exception e) {

            return null;
        }

    }

    /**
     * Este metodo converte uma Date em um objeto Date com horario minimo
     * formato: dd/MM/yyyy
     *
     * @param data - Date a ser convertida em um java.util.String
     * @return um java.util.String
     */
    public static Date dateToDateMinTime(Date data) {

        try {

            Calendar calendar = Calendar.getInstance(locale);

            synchronized (calendar) {

                calendar.setTime(data);

                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);

                return calendar.getTime();

            }

        } catch (Exception e) {

            return null;
        }

    }

    private static DecimalFormat monetario() {

        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        decimalFormat.applyPattern("###,##0.00");

        return decimalFormat;
    }

    private static DecimalFormat monetarioTresCasas() {

        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        decimalFormat.applyPattern("###,##0.000");

        return decimalFormat;
    }

    /* Esse metodo formata o valor que vem como Double e tranforma para a moeda brasileira*/
    private static DecimalFormat monetarioDouble() {

        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        decimalFormat.applyPattern("###,##0.00");

        return decimalFormat;
    }

    /* Esse metodo formata o valor que vem como Double e tranforma para a moeda brasileira*/
    private static DecimalFormat monetarioTresCasasDouble() {
        decimalFormat.setMinimumFractionDigits(3);
        decimalFormat.setMaximumFractionDigits(3);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        decimalFormat.applyPattern("###,##0.000");
        return decimalFormat;
    }

    public static String stringToMonetario(String value) {

        String retorno = null;

        if (value == null) {
            return null;
        }
        try {
            retorno = monetario().parse(value).toString();
        } catch (Exception e) {
        }

        return retorno;

    }

    public static String stringToMonetarioTresCasas(String value) {
        String retorno = null;
        if (value == null) {
            return null;
        }
        try {
            retorno = monetarioTresCasas().parse(value).toString();
        } catch (Exception e) {
        }

        return retorno;

    }

    public static String stringToMonetarioTresCasas(Double value) {

        String retorno = null;
        if (value == null) {
            return null;
        }
        try {
            retorno = monetarioTresCasasDouble().format(value.doubleValue());
        } catch (Exception e) {
        }

        return retorno;

    }

    public static Double stringToCurrency(String value) {
        Double retorno = null;

        if (value == null) {
            return null;
        }
        try {
            retorno = new Double(monetario().parse(value).doubleValue());
        } catch (Exception e) {
        }

        return retorno;
    }

    public static String stringToMonetario(Double value) {
        String retorno = null;
        if (value == null) {
            return null;
        }
        try {
            retorno = monetarioDouble().format(value.doubleValue());
        } catch (Exception e) {
        }

        return retorno;

    }

    /**
     * M�todo que verifica se o valor passado como par�metro est� null, caso
     * afirmativo, este valor passar� a ser zero.
     *
     * @param valor
     * @return
     */
    public static String doubleToZero(Double valor) {
        String retorno = null;
        if (valor == null) {
            retorno = stringToMonetario(new Double(0));
        } else {
            retorno = stringToMonetario(valor);
        }
        return retorno;
    }

    /**
     * M�todo que retorna a hora de uma determinada data fornecida como
     * par�metro formato: HH:mm
     *
     * @param data
     * @return
     */
    public static String getHourOfDate(Date data) {
        simpleDateFormat.applyPattern("HH:mm");
        return simpleDateFormat.format(data);
    }

    /**
     * M�todo que retorna a hora de uma determinada data fornecida como
     * par�metro formato: HH:mm:ss
     *
     * @param data
     * @return
     */
    public static String getHourOfDateWithSeconds(Date data) {
        simpleDateFormat.applyPattern("HH:mm:ss");
        return simpleDateFormat.format(data);
    }
}
