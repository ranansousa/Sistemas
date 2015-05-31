/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import br.com.pempec.finance.businessObjects.OrganizacaoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.io.File;
import java.util.Date;
import javax.swing.SwingUtilities;

/**
 * Classe responsavel por validar se a copia do sistema é licenciada parametros
 * padrao
 *
 * serial_hd = 1 serial_cliente = 1 host_name = servidor host_ip = 10.0.0.0
 * licenca = bravo socket_web = http://www.pempec.com.br codigo_web = 1 codinome
 * = mangalarga integracao = sim system = mega table = organizacao
 *
 * @author Pempec
 */
public class ValidaLicencaFinanceANALISE extends FinanceInternalFrame {

    private OrganizacaoBO organizacaoBO = new OrganizacaoBO();
    Date dataMaxima = PempecParse.stringToDate("01/01/2011");

    public boolean validar2(OrganizacaoModel organizacao) {

        boolean result = false;

        if (organizacao != null) {
            //verifica o serial HD
            result = validaSerialHD(organizacao.getSerial_hd());

            if (result) {
                //verifica serial_cliente
                result = validaSerialCliente(organizacao.getSerial_cliente());
            }

            if (result) {
                //verifica  host_name
                result = validaHostName(organizacao.getHost_name());

            }
            if (result) {
                // verifica host_ip
                result = validaHostIp(organizacao.getHost_ip());

            }
            if (result) {
                // verifica licenca

                result = validaLicenca(organizacao.getLicenca());
            }
//            if (result) {
//                // verifica socket_web
//
//                result = validaSocketWeb(organizacao.getSocket_web());
//            }
            if (result) {
                // verifica codigo_web

                result = validaCodigoWeb(organizacao.getCodigo_web());
            }
            if (result) {
                // verifica codinome

                result = validaCodiNome(organizacao.getCodinome());
            }
            if (result) {
                // verifica integracao

                result = validaIntegracao(organizacao.getSistemaContabil());

            }
            if (result) {
                // verifica system

                result = validaSystem(organizacao.getSistemaContabil());


            }

        }


//return result
        return true;

    }

    public boolean genuineFinance2() {

        boolean result = false;
        String retorno = "8";

        try {

            if (organizacaoModel != null) {

                retorno = (String) Controller.sendToServer(organizacaoModel);

                //19/05/2012         //  Controller.sendMailInterno("genuine Finance", " genuineFinance " + retorno + " " + Controller.getSerialOrganizacao(), null);

            }

            if (retorno != null && !retorno.isEmpty()) {

                organizacaoModel.setCodigo_web(retorno);

                int op = Integer.parseInt(retorno);

                //criar campo data_atualizacao em ORGANIZACAO

                switch (op) {

                    case 0: //problemas com dados informados
                        // ("Regra 2: Todos os campos da organização informada deve coincidir com o cadastrado na base!");
                        //("Regra 1: NAO existir na base de dados do serviço a organização informada, pelo campo ID!");
                        //("Regra 4: A licença Inativa ou Vencida!");
                        //
                        organizacaoModel.setDataAtualizacao(PempecUtil.addDayDate(Controller.getDataServidorBD(), 3));


                        if (organizacaoModel.getDataAtualizacao().after(dataMaxima)) {

                            organizacaoModel.setCodinome("genuine");

                            organizacaoModel.setHost_ip(" ");

                            organizacaoModel.setHost_name(" ");

                            organizacaoModel.setIpServidor(" ");

                            organizacaoModel.setSistema("");

                        }
                        organizacaoBO.alterar(organizacaoModel);

                        break;

                    case 1: // ("Regra 1: NAO existir na base de dados do serviço a organização informada, pelo campo ID!");
                        // colocar o parametro(campo ultima_atualizacao em ORGANIZACAO) para fazer nova validacao em 22 dias
                        //alterar o model organizacao com a nova data e com o retorno
                        organizacaoModel.setDataAtualizacao(PempecUtil.addDayDate(Controller.getDataServidorBD(), 22));
                        organizacaoBO.alterar(organizacaoModel);
                        break;


                    case 8: //O metodo Controller.ssendToServer(Object object) retornou erro, o sistema nao conseguiu acessar o site para validarLicenca
                        //ver o que fazer

                        organizacaoModel.setDataAtualizacao(PempecUtil.addDayDate(Controller.getDataServidorBD(), 1));
/*
                        if (organizacaoModel.getDataAtualizacao().after(dataMaxima)) {

                            organizacaoModel.setLicenca("00000000");
                            organizacaoModel.setCodinome("genuine");
                            organizacaoModel.setHost_ip(" ");
                            organizacaoModel.setHost_name(" ");
                            organizacaoModel.setIpServidor(" ");
                            organizacaoModel.setRazaoSocial("PEMPEC ENTERPRISE FINANCE");
                            organizacaoModel.setCnpj("11.111.111/0001-00");
                            organizacaoModel.setComplemento("Software nao autorizado a funcionar");
                            organizacaoModel.setFantasia(organizacaoModel.getFantasia() + "PEMPEC ");
                            organizacaoModel.setBairro(null);
                            organizacaoModel.setCidade(null);
                            organizacaoModel.setEstado(null);
                            organizacaoModel.setSistemaContabil(null);
                            organizacaoModel.setSistema("");

                        }*/

                        organizacaoBO.alterar(organizacaoModel);

                        break;

                    case 9: //("Regra 3: A chave informada deve existir e ser igual a cadastrada na base de dados para a organização informada, tabela LICENCA, campo CHAVE!");
                        //("Regra 5: A data atual de validação é inferior ao calculo DATA_ULTIMA_VALIDACAO + 3 dias!");
                        organizacaoModel.setDataAtualizacao(PempecUtil.addDayDate(Controller.getDataServidorBD(), 1));

                        organizacaoBO.alterar(organizacaoModel);
                }


            }
        } catch (ApplicationException ex) {

            ex.printStackTrace();


        } catch (final SystemException ex) {

            ex.printStackTrace();

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });



        }


        result = true;
        return result;//  19/05/2012



    }

    private static boolean validaSerialHD(String serial) {

        boolean result = false;

        String serialHD = ResourcePropertiesSecurity.getMessage("serial_hd");

        if (serial.equalsIgnoreCase(serialHD)) {

            result = true;
        }

        return result;
    }

    private static boolean validaSerialCliente(String serial) {

        boolean result = false;

        String serialCliente = ResourcePropertiesSecurity.getMessage("serial_cliente");

        if (serial.equalsIgnoreCase(serialCliente)) {

            result = true;
        }

        return result;
    }

    private static boolean validaHostName(String host) {

        boolean result = false;

        String hostName = ResourcePropertiesSecurity.getMessage("host_name");

        if (host.equalsIgnoreCase(hostName)) {

            result = true;
        }

        return result;
    }

    private static boolean validaHostIp(String ip) {

        boolean result = false;

        String hostIp = ResourcePropertiesSecurity.getMessage("host_ip");

        if (ip.equalsIgnoreCase(hostIp)) {

            result = true;
        }

        return result;
    }

    private static boolean validaLicenca(String licenca) {

        boolean result = false;

        String lic = ResourcePropertiesSecurity.getMessage("licenca");

        if (licenca.equalsIgnoreCase(lic)) {

            result = true;
        }
        return result;
    }

    private static boolean validaSocketWeb(String socket_web) {
        boolean result = false;

        String socket = ResourcePropertiesSecurity.getMessage("socket_web");

        if (socket.equalsIgnoreCase(socket_web)) {

            result = true;
        }

        return result;
    }

    private static boolean validaCodigoWeb(String codigo_web) {
        boolean result = false;

        String codigo = ResourcePropertiesSecurity.getMessage("codigo_web");

        if (codigo.equalsIgnoreCase(codigo_web)) {

            result = true;
        }

        return result;
    }

    private static boolean validaCodiNome(String codinome) {
        boolean result = false;

        String codigo = ResourcePropertiesSecurity.getMessage("codinome");

        if (codigo.equalsIgnoreCase(codinome)) {

            result = true;
        }

        return result;
    }

    private static boolean validaIntegracao(String integracao) {
        boolean result = false;

        String codigo = ResourcePropertiesSecurity.getMessage("integracao");

        if (codigo.equalsIgnoreCase(integracao)) {

            result = true;
        }

        return result;
    }

    private static boolean validaSystem(String system) {
        boolean result = false;

        if (system.equalsIgnoreCase("mega") || system.equalsIgnoreCase("megacontabil")) {

            result = true;
        } else {

            System.out.println("***********************  ");
            System.out.print("system " + "FALHOU");
        }


        return result;
    }
}
