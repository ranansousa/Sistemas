/* Implementacao da parte de seguranca da licenca da copia
 * serao dois passos:
 *
 * primeiro os dados serao preenchidos diretamente no arquivo de properties = security.properties
 * que sera gravado na pasta config. o preenchimento devera ser feito manual
 *
 * segundo = as mesmas propriedades farao parte de uma tabela. os dados da referida tabela serao
 * inseridos via console do banco
 *
 * o sistema deve verificar se os valores do banco sao iguais aos valores do properties, caso nao seja,
 * o sistema nao deve iniciar, ficar sempre mandando a mensagem de copia nao licenciada.
 *
 * a tabela e o arquivo properties deverao conter os seguintes campos do tipo String com tamanho 60.
 *
 * a funcao que verifica a licenca deve ser chamada em varios locais do sistema
 *
 *
 * campo 1 = serial_hd
 * campo 2 = serial_cliente
 * campo 3 = host_name
 * campo 4 = host_ip
 * campo 5 = licenca (codigo hexadecimal gerado pelo sistema utilitario gerador de licenças)
 * campo 6 = socket_web
 * campo 7 = codigo_web
 * campo 8 = codinome
 * campo 9 = integracao
 * campo 10 = system
 *
 */
package br.com.pempec.finance.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Adm
 */
public final class ResourcePropertiesSecurity {

    private static Properties properties;
    private static Properties propertiesAbout;
    private static String msg = "Arquivo de segurança não localizado.\nO sistema será encerrado.";

    private ResourcePropertiesSecurity() {
    }

    static {

        properties = new Properties();

        propertiesAbout = new Properties();

        try {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream fileAbout = loader.getResourceAsStream("security.properties");

            if (fileAbout != null) {

                propertiesAbout.load(fileAbout);

            } else {

                JOptionPane.showMessageDialog(null, "Arquivo about de segurança nao localizado.\n" + msg);
                System.exit(0);
            }

            try {

                FileInputStream configFile = new FileInputStream(System.getProperty("user.dir") + "\\config\\security.properties");

                if (configFile != null) {

                    properties.load(configFile);

                } else {

                    JOptionPane.showMessageDialog(null, msg);

                    System.exit(0);

                }

            } catch (FileNotFoundException ex) {


//                MovimentoDiarioModel mov = new MovimentoDiarioModel();
//                mov.setAcao("Carregar copia");
//                mov.setCodigo(PempecKeyGenerator.generateKey());
//                mov.setDescricaoObjeto("Validar Licença");
//                mov.setObjeto("Licença");
//                mov.setStatusFinalObjeto("Copia não autorizada");
//                mov.setUsuario(null);
//                mov.setValorOperacao(0d);
//
//                new MovimentoDiarioBO().inserir(mov);


                JOptionPane.showMessageDialog(null, msg);


                System.exit(0);

            }





        } catch (Exception e) {

            e.toString();

            e.printStackTrace();

        }

    }

    public static String getMessage(String key) {
        return properties.getProperty(key);
    }

    public static String getMessageAbout(String key) {
        return propertiesAbout.getProperty(key);
    }
}
