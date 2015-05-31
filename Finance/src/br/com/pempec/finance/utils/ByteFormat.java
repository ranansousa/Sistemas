package br.com.pempec.finance.utils;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.Format;

/**
 * Formata um byte em KiloByte, MegaByte, GigaByte e TeraByte
 *
 * @author EQUIPE PEMPEC
 */
public class ByteFormat extends Format {

    public static final int BYTE = 0;
    public static final int KILOBYTE = 1;
    public static final int MEGABYTE = 2;
    public static final int GIGABYTE = 3;
    public static final int TERABYTE = 4;
    public static final String STRBYTE = "Bytes";
    public static final String STRKILOBYTE = "KB";
    public static final String STRMEGABYTE = "MB";
    public static final String STRGIGABYTE = "GB";
    public static final String STRTERABYTE = "TB";
    public static final long UMKILOBYTE = 1024;
    public static final long UMMEGABYTE = UMKILOBYTE * 1024;
    public static final long UMGIGABYTE = UMMEGABYTE * 1024;
    public static final long UMTERABYTE = UMGIGABYTE * 1024;

    /**
     * @see java.text.Format#parseObject(java.lang.String,
     * java.text.ParsePosition)
     */
    public Object parseObject(String source, ParsePosition pos) {
        int tipo = 0;

        if (source.indexOf(STRBYTE) >= 0) {
            tipo = BYTE;
        }
        if (source.indexOf(STRKILOBYTE) >= 0) {
            tipo = KILOBYTE;
        }
        if (source.indexOf(STRMEGABYTE) >= 0) {
            tipo = MEGABYTE;
        }
        if (source.indexOf(STRGIGABYTE) >= 0) {
            tipo = GIGABYTE;
        }
        if (source.indexOf(STRTERABYTE) >= 0) {
            tipo = TERABYTE;
        }

        String temp = source
                .replaceAll(STRBYTE, "")
                .replaceAll(STRKILOBYTE, "")
                .replaceAll(STRMEGABYTE, "")
                .replaceAll(STRGIGABYTE, "")
                .replaceAll(STRTERABYTE, "");

        Double qtd = new Double(temp);

        switch (tipo) {
            case BYTE:
                return new Long(qtd.longValue());
            case KILOBYTE:
                return new Long(qtd.longValue() * UMKILOBYTE);
            case MEGABYTE:
                return new Long(qtd.longValue() * UMMEGABYTE);
            case GIGABYTE:
                return new Long(qtd.longValue() * UMGIGABYTE);
            case TERABYTE:
                return new Long(qtd.longValue() * UMTERABYTE);
            default:
                return qtd;
        }
    }

    public StringBuffer format(
            Object obj,
            StringBuffer toAppendTo,
            FieldPosition pos) {

        Long bytes = (Long) obj;

        pos.setBeginIndex(toAppendTo.length());
        if (bytes != null) {

            String formatado;

            if (bytes.longValue() < UMKILOBYTE) {
                formatado = formatInBytes(bytes);
            } else if (bytes.longValue() < UMMEGABYTE) {
                formatado = formatInKiloBytes(bytes);
            } else if (bytes.longValue() < UMGIGABYTE) {
                formatado = formatInMegaBytes(bytes);
            } else if (bytes.longValue() < UMTERABYTE) {
                formatado = formatInGigaBytes(bytes);
            } else {
                formatado = formatInTeraBytes(bytes);
            }

            toAppendTo.append(formatado);
            pos.setEndIndex(toAppendTo.length() + formatado.length());
        } else {
            pos.setEndIndex(toAppendTo.length());
        }

        return toAppendTo;
    }

    /**
     * Formata uma quantidade de bytes em bytes.
     *
     * @param Quantidade de bytes
     * @return Número formatado.
     */
    public String formatInBytes(Long bytes) {
        return NumberFormat.getNumberInstance().format(bytes.doubleValue()) + " " + STRBYTE;
    }

    //retorna o valor em Long
    public Long convertBytes(Long bytes) {

        String convertido = NumberFormat.getNumberInstance().format(bytes.doubleValue());

        return PempecParse.stringToLong(convertido);
    }

    /**
     * Formata uma quantidade de bytes em KiloBytes.
     *
     * @param Quantidade de bytes
     * @return Número formatado.
     */
    public String formatInKiloBytes(Long bytes) {
        return NumberFormat.getNumberInstance().format(bytes.doubleValue() / UMKILOBYTE) + " " + STRKILOBYTE;
    }

    /**
     * Formata uma quantidade de bytes em MegaBytes.
     *
     * @param Quantidade de bytes
     * @return Número formatado.
     */
    public String formatInMegaBytes(Long bytes) {
        return NumberFormat.getNumberInstance().format(bytes.doubleValue() / UMMEGABYTE) + " " + STRMEGABYTE;
    }

    public Long convertMegaBytes(Long bytes) {

        String convertido = NumberFormat.getNumberInstance().format(bytes.doubleValue() / UMMEGABYTE);

        return PempecParse.stringToLong(convertido);

    }

    /**
     * Formata uma quantidade de bytes em GigaBytes.
     *
     * @param Quantidade de bytes
     * @return Número formatado.
     */
    public String formatInGigaBytes(Long bytes) {
        return NumberFormat.getNumberInstance().format(bytes.doubleValue() / UMGIGABYTE) + " " + STRGIGABYTE;
    }

    public Long convertGigaBytes(Long bytes) {

        String convertido = NumberFormat.getNumberInstance().format(bytes.doubleValue() / UMGIGABYTE);

        return PempecParse.stringToLong(convertido);

    }

    /**
     * Formata uma quantidade de bytes em TeraBytes.
     *
     * @param Quantidade de bytes
     * @return Número formatado.
     */
    public String formatInTeraBytes(Long bytes) {
        return NumberFormat.getNumberInstance().format(bytes.doubleValue() / UMTERABYTE) + " " + STRTERABYTE;
    }

    public Long convertTeraBytes(Long bytes) {

        String convertido = NumberFormat.getNumberInstance().format(bytes.doubleValue() / UMTERABYTE);

        return PempecParse.stringToLong(convertido);

    }
}//fim da classe
