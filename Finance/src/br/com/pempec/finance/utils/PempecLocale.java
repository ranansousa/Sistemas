package br.com.pempec.finance.utils;

import java.util.Locale;

public final class PempecLocale {

    //Impossivel instanciar a classe
    private PempecLocale() {
    }
    private static Locale locale;

    static {
        //Locale Default Portugu�s - Brasil
        locale = new Locale("pt", "BR");
    }

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale newLocale) {
        locale = newLocale;
    }
}
