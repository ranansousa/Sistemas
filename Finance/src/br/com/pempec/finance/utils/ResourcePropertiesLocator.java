/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Adm
 */
public final class ResourcePropertiesLocator {

    private static Properties properties;
    private static Properties propertiesAbout;

    private ResourcePropertiesLocator() {
    }

    static {

        properties = new Properties();

        propertiesAbout = new Properties();


        try {

            File pastaConfig = new File(System.getProperty("user.dir") + "\\config");

            if (!pastaConfig.exists() || !pastaConfig.isDirectory()) {

                new FinanceInternalFrame().exibeMensagemAviso("Não foi possível localizar o arquivo de configuração. "
                        + "\nNão será possível iniciar o sistema.\nEntre em contato com o suporte.", "Acesso");

                System.exit(0);
            }

        } catch (Exception e) {

            new FinanceInternalFrame().exibeMensagemAviso("Não foi possível localizar o arquivo de configuração. "
                    + "\nNão será possível iniciar o sistema.\nEntre em contato com o suporte.", "Acesso");

            System.exit(0);
        }

        try {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream fileAbout = loader.getResourceAsStream("about.properties");

            propertiesAbout.load(fileAbout);

            FileInputStream configProperties = new FileInputStream(System.getProperty("user.dir") + "\\config\\config.properties");

            if (configProperties != null) {

                properties.load(configProperties);

            }


        } catch (Exception e) {

            new FinanceInternalFrame().exibeMensagemAviso("Não foi possível localizar o arquivo de configuração. "
                    + "\nNão será possível iniciar o sistema.\nEntre em contato com o suporte.", "Acesso");


            // e.printStackTrace();

        }

    }

    public static String getMessage(String key) {

        return properties.getProperty(key);
    }

    public static String getMessageAbout(String key) {
        return propertiesAbout.getProperty(key);
    }

    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
