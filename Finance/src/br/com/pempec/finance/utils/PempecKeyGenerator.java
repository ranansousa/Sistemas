/**
 *
 */
package br.com.pempec.finance.utils;

import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaTransferenciaBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TesourariaCreditoBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import java.security.SecureRandom;
import java.util.Random;

import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloReceberModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public final class PempecKeyGenerator {
    //Nao permite instancia

    private PempecKeyGenerator() {
    }
    private static Random secureRandom;
    private static String nodeStr;
    private static int clockSequence;
    private static long lastTime = 0;
    private static Collection<MovimentoDiarioModel> collMovimentos;
    private static Collection<TituloPagarModel> collTitulosPagar;
    private static Collection<TituloReceberModel> collTitulosReceber;
    private static Collection<ContaBancariaDebitoModel> collContaDebito;
    private static Collection<ContaBancariaCreditoModel> collContaCredito;
    private static Collection<TesourariaDebitoModel> collTesourariaDebito;
    private static Collection<TesourariaCreditoModel> collTesourariaCredito;
    private static Collection<ContaBancariaTransferenciaModel> collTransferencia;

    static {
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (Exception e) {
            secureRandom = new Random();
        }

        nodeStr = getNodeHexValue();
        clockSequence = getClockSequence();

        OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

        Date date = PempecParse.dateToDate(Controller.getDataServidorBD());

        collMovimentos = new MovimentoDiarioBO().obterTodosPorData(organizacaoModel, date);

        collTitulosPagar = new TituloPagarBO().obterTodosPorData(organizacaoModel, date);

        collTitulosReceber = new TituloReceberBO().obterTodosPorData(organizacaoModel, date);

        collContaDebito = new ContaBancariaDebitoBO().obterTodosPorData(organizacaoModel, date);

        collContaCredito = new ContaBancariaCreditoBO().obterTodosPorData(organizacaoModel, date);

        collTransferencia = new ContaBancariaTransferenciaBO().obterTodosPorData(organizacaoModel, date);

        collTesourariaDebito = new TesourariaDebitoBO().obterTodosPorData(organizacaoModel, date);

        collTesourariaCredito = new TesourariaCreditoBO().obterTodosPorData(organizacaoModel, date);


    }

    private static synchronized String getNodeHexValue() {
        long node = 0;
        long nodeValue = 0;
        while ((node = getBitsValue(nodeValue, 47, 47)) == 0) {
            nodeValue = secureRandom.nextLong();
        }
        node = node | 0x800000000000L;
        return leftZeroPadString(Long.toHexString(node), 12);
    }

    private static synchronized int getClockSequence() {
        return secureRandom.nextInt(16384);
    }

    private static synchronized long getBitsValue(long value, int startBit, int bitLen) {
        return ((value << (64 - startBit)) >>> (64 - bitLen));
    }

    private static synchronized final String leftZeroPadString(String bitString, int len) {
        if (bitString.length() < len) {
            int nbExtraZeros = len - bitString.length();
            StringBuilder extraZeros = new StringBuilder();
            for (int i = 0; i < nbExtraZeros; i++) {
                extraZeros.append("0");
            }
            extraZeros.append(bitString);
            bitString = extraZeros.toString();
        }
        return bitString;
    }

    public static final synchronized String generateKey() {

        long time = System.currentTimeMillis();

        long timestamp = time * 10000;
        timestamp += 0x01b21dd2L << 32;
        timestamp += 0x13814000;

        if (time - lastTime <= 0) {
            clockSequence = ((clockSequence + 1) & 16383);
        }
        lastTime = time;

        long timeLow = getBitsValue(timestamp, 32, 32);
        long timeMid = getBitsValue(timestamp, 48, 16);
        long timeHi = getBitsValue(timestamp, 64, 16) | 0x1000;

        long clockSeqLow = getBitsValue(clockSequence, 8, 8);
        long clockSeqHi = getBitsValue(clockSequence, 16, 8) | 0x80;

        String timeLowStr = leftZeroPadString(Long.toHexString(timeLow), 8);
        String timeMidStr = leftZeroPadString(Long.toHexString(timeMid), 4);
        String timeHiStr = leftZeroPadString(Long.toHexString(timeHi), 4);

        String clockSeqHiStr = leftZeroPadString(Long.toHexString(clockSeqHi), 2);
        String clockSeqLowStr = leftZeroPadString(Long.toHexString(clockSeqLow), 2);

        StringBuffer result = new StringBuffer(36);
        result.append(timeLowStr).append("-");
        result.append(timeMidStr).append("-");
        result.append(timeHiStr).append("-");
        result.append(clockSeqHiStr).append(clockSeqLowStr);
        result.append("-").append(nodeStr);

        return result.toString();

    }

    public static final synchronized Long generateKeyLong() {

        Long time = 1l;

        try {
            Thread.sleep(15l);
        } catch (Exception e) {
        }

        synchronized (time) {
            time = System.nanoTime();
        }

        StringBuilder result = new StringBuilder();

        result.append(time);

        return Math.abs(Long.valueOf(result.toString().substring(9)));

    }

    public static final synchronized List generateNumeroDocumentoTituloPagar(Long numeroMovimento, int parc) {

        List numerosValidos = new ArrayList();

        try {

            collTitulosPagar = new TituloPagarBO().obterTodos(Controller.getOrganizacao());

            String lote = numeroMovimento.toString();

            TituloPagarModel model = new TituloPagarModel();

            model.setNumeroDocumento(lote);

            collTitulosPagar.add(model);


            while (parc > 0) {

                Long numPesquisa = numeroMovimento;


                for (TituloPagarModel tituloPagarModel : collTitulosPagar) {

                    if (tituloPagarModel.getNumeroDocumento().equalsIgnoreCase(String.valueOf(numeroMovimento))) {

                        numeroMovimento++;

                        lote = numeroMovimento.toString();

                    }

                }



                if (numeroMovimento == numPesquisa) {

                    numerosValidos.add(lote);

                    model = new TituloPagarModel();

                    model.setNumeroDocumento(lote);

                    collTitulosPagar.add(model);

                    parc--;
                }

            }


        } catch (ApplicationException ex) {

            ex.printStackTrace();

        } catch (SystemException ex) {

            ex.printStackTrace();

        }


        return numerosValidos;

    }

    public static final synchronized List generateNumeroDocumentoTituloReceber(Long numeroMovimento, int parc) {

        List numerosValidos = new ArrayList();

        try {

            collTitulosReceber = new TituloReceberBO().obterTodos(Controller.getOrganizacao());

            String lote = numeroMovimento.toString();

            TituloReceberModel model = new TituloReceberModel();

            model.setNumeroDocumento(lote);

            collTitulosReceber.add(model);


            while (parc > 0) {

                Long numPesquisa = numeroMovimento;


                for (TituloReceberModel tituloReceberModel : collTitulosReceber) {

                    if (tituloReceberModel.getNumeroDocumento().equalsIgnoreCase(String.valueOf(numeroMovimento))) {

                        numeroMovimento++;

                        lote = numeroMovimento.toString();

                    }

                }



                if (numeroMovimento == numPesquisa) {

                    numerosValidos.add(lote);

                    model = new TituloReceberModel();

                    model.setNumeroDocumento(lote);

                    collTitulosReceber.add(model);

                    parc--;
                }

            }


        } catch (ApplicationException ex) {

            ex.printStackTrace();

        } catch (SystemException ex) {

            ex.printStackTrace();

        }


        return numerosValidos;

    }

    public static final synchronized String generateNumeroDocumentoTituloPagar() {

        Long numeroMovimento = 0L;

        String lote = null;

        String dataHoje = PempecParse.dateToString(Controller.getDataServidorBD());
        String[] aux = dataHoje.split("/");

        String dia = aux[0];
        String mes = aux[1];
        String ano = aux[2];


        lote = dia + mes + ano.substring(2) + 0;


        numeroMovimento = PempecParse.stringToLong(lote);

        long numeroUltimo = 0;


        if (collTitulosPagar == null || collTitulosPagar.isEmpty()) {

            TituloPagarModel model = new TituloPagarModel();

            model.setNumeroDocumento(numeroMovimento.toString());

            collTitulosPagar = new ArrayList<TituloPagarModel>();

            collTitulosPagar.add(model);
        }


        for (TituloPagarModel tituloPagarModel : collTitulosPagar) {

            if (tituloPagarModel.getNumeroDocumento() == null || PempecParse.stringToLong(tituloPagarModel.getNumeroDocumento()) == 0) {

                numeroUltimo = numeroMovimento;

            } else {

                numeroUltimo = PempecParse.stringToLong(tituloPagarModel.getNumeroDocumento());
            }

            if (numeroUltimo == numeroMovimento) {

                numeroMovimento = numeroUltimo;

                numeroMovimento++;

            }

        }


        TituloPagarModel model = new TituloPagarModel();

        model.setNumeroDocumento(numeroMovimento.toString());

        collTitulosPagar.add(model);


        return numeroMovimento.toString();

    }

    public static final synchronized String generateNumeroDocumentoTituloReceber() {

        Long numeroMovimento = 0L;

        String lote = null;

        String dataHoje = PempecParse.dateToString(Controller.getDataServidorBD());
        String[] aux = dataHoje.split("/");

        //collTitulosReceber

        String dia = aux[0];
        String mes = aux[1];
        String ano = aux[2];

        lote = dia + mes + ano.substring(2) + 0;

        numeroMovimento = PempecParse.stringToLong(lote);

        long numeroUltimo = 0;

        if (collTitulosReceber == null || collTitulosReceber.isEmpty()) {

            TituloReceberModel model = new TituloReceberModel();

            model.setNumeroDocumento(numeroMovimento.toString());

            collTitulosReceber = new ArrayList<TituloReceberModel>();

            collTitulosReceber.add(model);
        }


        for (TituloReceberModel tituloReceberModel : collTitulosReceber) {

            if (tituloReceberModel.getNumeroDocumento() == null || PempecParse.stringToLong(tituloReceberModel.getNumeroDocumento()) == 0) {

                numeroUltimo = numeroMovimento;

            } else {

                numeroUltimo = PempecParse.stringToLong(tituloReceberModel.getNumeroDocumento());
            }

            if (numeroUltimo == numeroMovimento) {

                numeroMovimento = numeroUltimo;

                numeroMovimento++;

            }

        }


        TituloReceberModel model = new TituloReceberModel();

        model.setNumeroDocumento(numeroMovimento.toString());

        collTitulosReceber.add(model);

        return numeroMovimento.toString();

    }

    public static final synchronized String generateNumeroDocumentoContaBancariaCredito() {

        Long numeroMovimento = 0L;

        String lote = null;

        String dataHoje = PempecParse.dateToString(Controller.getDataServidorBD());
        String[] aux = dataHoje.split("/");

        String dia = aux[0];
        String mes = aux[1];
        String ano = aux[2];

        lote =
                dia + mes + ano.substring(2) + 0;

        numeroMovimento =
                PempecParse.stringToLong(lote);

        long numeroUltimo = 0;

        if (collContaCredito == null || collContaCredito.isEmpty()) {
            ContaBancariaCreditoModel model = new ContaBancariaCreditoModel();
            model.setIdentificacao(numeroMovimento.toString());
            collContaCredito =
                    new ArrayList<ContaBancariaCreditoModel>();
            collContaCredito.add(model);
        }

        if (collContaCredito.size() > 0) {

            for (ContaBancariaCreditoModel model : collContaCredito) {

                if (model.getIdentificacao() == null || PempecParse.stringToLong(model.getIdentificacao()) == 0) {

                    numeroUltimo = numeroMovimento;

                } else {

                    numeroUltimo = PempecParse.stringToLong(model.getIdentificacao());

                }

                if (numeroUltimo >= numeroMovimento) {
                    numeroMovimento = numeroUltimo + 1;
                }

            }

            ContaBancariaCreditoModel model = new ContaBancariaCreditoModel();

            model.setIdentificacao(numeroMovimento.toString());

            collContaCredito.add(model);

        }

        return numeroMovimento.toString();

    }

    public static final synchronized String generateNumeroDocumentoContaBancariaDebito() {

        Long numeroMovimento = 0L;

        String lote = null;

        String dataHoje = PempecParse.dateToString(Controller.getDataServidorBD());
        String[] aux = dataHoje.split("/");

        String dia = aux[0];
        String mes = aux[1];
        String ano = aux[2];

        lote =
                dia + mes + ano.substring(2) + 0;

        numeroMovimento =
                PempecParse.stringToLong(lote);

        long numeroUltimo = 0;

        if (collContaDebito == null || collContaDebito.isEmpty()) {
            ContaBancariaDebitoModel model = new ContaBancariaDebitoModel();
            model.setIdentificacao(numeroMovimento.toString());
            collContaDebito =
                    new ArrayList<ContaBancariaDebitoModel>();
            collContaDebito.add(model);
        }

        if (collContaDebito.size() > 0) {

            for (ContaBancariaDebitoModel model : collContaDebito) {

                if (model.getIdentificacao() == null || PempecParse.stringToLong(model.getIdentificacao()) == 0) {

                    numeroUltimo = numeroMovimento;

                } else {

                    numeroUltimo = PempecParse.stringToLong(model.getIdentificacao());

                }

                if (numeroUltimo >= numeroMovimento) {
                    numeroMovimento = numeroUltimo + 1;
                }

            }

            ContaBancariaDebitoModel model = new ContaBancariaDebitoModel();

            model.setIdentificacao(numeroMovimento.toString());

            collContaDebito.add(model);

        }

        return numeroMovimento.toString();

    }

    public static final synchronized String generateNumeroDocumentoTesourariaCredito() {

        Long numeroMovimento = 0L;

        String lote = null;

        String dataHoje = PempecParse.dateToString(Controller.getDataServidorBD());
        String[] aux = dataHoje.split("/");

        String dia = aux[0];
        String mes = aux[1];
        String ano = aux[2];

        lote =
                dia + mes + ano.substring(2) + 0;

        numeroMovimento =
                PempecParse.stringToLong(lote);

        long numeroUltimo = 0;

        if (collTesourariaCredito == null || collTesourariaCredito.isEmpty()) {
            TesourariaCreditoModel model = new TesourariaCreditoModel();
            model.setNumeroDocumento(numeroMovimento.toString());
            collTesourariaCredito =
                    new ArrayList<TesourariaCreditoModel>();
            collTesourariaCredito.add(model);
        }

        if (collTesourariaCredito.size() > 0) {

            for (TesourariaCreditoModel model : collTesourariaCredito) {

                if (model.getNumeroDocumento() == null || PempecParse.stringToLong(model.getNumeroDocumento()) == 0) {

                    numeroUltimo = numeroMovimento;

                } else {

                    numeroUltimo = PempecParse.stringToLong(model.getNumeroDocumento());

                }

                if (numeroUltimo >= numeroMovimento) {
                    numeroMovimento = numeroUltimo + 1;
                }

            }

            TesourariaCreditoModel model = new TesourariaCreditoModel();

            model.setNumeroDocumento(numeroMovimento.toString());

            collTesourariaCredito.add(model);

        }

        return numeroMovimento.toString();

    }

    public static final synchronized String generateNumeroDocumentoTesourariaDebito() {

        Long numeroMovimento = 0L;

        String lote = null;

        String dataHoje = PempecParse.dateToString(Controller.getDataServidorBD());
        String[] aux = dataHoje.split("/");

        String dia = aux[0];
        String mes = aux[1];
        String ano = aux[2];

        lote =
                dia + mes + ano.substring(2) + 0;

        numeroMovimento =
                PempecParse.stringToLong(lote);

        long numeroUltimo = 0;

        if (collTesourariaDebito == null || collTesourariaDebito.isEmpty()) {
            TesourariaDebitoModel model = new TesourariaDebitoModel();
            model.setNumeroDocumento(numeroMovimento.toString());
            collTesourariaDebito =
                    new ArrayList<TesourariaDebitoModel>();
            collTesourariaDebito.add(model);
        }

        if (collTesourariaDebito.size() > 0) {

            for (TesourariaDebitoModel model : collTesourariaDebito) {

                if (model.getNumeroDocumento() == null || PempecParse.stringToLong(model.getNumeroDocumento()) == 0) {

                    numeroUltimo = numeroMovimento;

                } else {

                    numeroUltimo = PempecParse.stringToLong(model.getNumeroDocumento());

                }

                if (numeroUltimo >= numeroMovimento) {
                    numeroMovimento = numeroUltimo + 1;
                }

            }

            TesourariaDebitoModel model = new TesourariaDebitoModel();

            model.setNumeroDocumento(numeroMovimento.toString());

            collTesourariaDebito.add(model);

        }

        return numeroMovimento.toString();

    }

    public static final synchronized String generateNumeroDocumentoTransferencia() {

        Long numeroMovimento = 0L;

        String lote = null;

        String dataHoje = PempecParse.dateToString(Controller.getDataServidorBD());
        String[] aux = dataHoje.split("/");

        String dia = aux[0];
        String mes = aux[1];
        String ano = aux[2];

        lote =
                dia + mes + ano.substring(2) + 0;

        numeroMovimento =
                PempecParse.stringToLong(lote);

        long numeroUltimo = 0;

        if (collTransferencia == null || collTransferencia.isEmpty()) {
            ContaBancariaTransferenciaModel model = new ContaBancariaTransferenciaModel();
            model.setIdentificacao(numeroMovimento.toString());
            collTransferencia =
                    new ArrayList<ContaBancariaTransferenciaModel>();
            collTransferencia.add(model);
        }

        if (collTransferencia.size() > 0) {

            for (ContaBancariaTransferenciaModel model : collTransferencia) {

                if (model.getIdentificacao() == null || PempecParse.stringToLong(model.getIdentificacao()) == 0) {

                    numeroUltimo = numeroMovimento;

                } else {

                    numeroUltimo = PempecParse.stringToLong(model.getIdentificacao());

                }

                if (numeroUltimo >= numeroMovimento) {
                    numeroMovimento = numeroUltimo + 1;
                }

            }

            ContaBancariaTransferenciaModel model = new ContaBancariaTransferenciaModel();

            model.setIdentificacao(numeroMovimento.toString());

            collTransferencia.add(model);

        }

        return numeroMovimento.toString();

    }

    public static final synchronized String generateKeyOrganizacao(
            OrganizacaoModel model) {

        StringBuilder result = new StringBuilder();

        long time = System.nanoTime();

        result.append(time);

        return result.toString();

    }

    public static final synchronized Long generateNumeroMovimentoDiario() {

        Long numeroMovimento = 0L;

        String lote = null;

        String dataHoje = PempecParse.dateToString(Controller.getDataServidorBD());
        String[] aux = dataHoje.split("/");

        /**
         * Tratamento para multiplos usuários. Seta o ID do usuário antes da o
         * ultimo numero para evitar conflitos de valores. Ou seja, casa usuário
         * terá sua sequencia. A busca tambem possue o filtro por usuário.
         */
        lote =
                aux[0] + aux[1] + aux[2] + Controller.getUsuarioLogado().getId() + "0" + 0;

        numeroMovimento = PempecParse.stringToLong(lote);

        long numeroUltimo = 0;

        if (collMovimentos == null || collMovimentos.isEmpty()) {
            collMovimentos = new ArrayList<MovimentoDiarioModel>();
            collMovimentos.add(new MovimentoDiarioModel(numeroMovimento));
        }

        if (collMovimentos.size() > 0) {

            for (MovimentoDiarioModel movimentoDiarioModel : collMovimentos) {

                if (movimentoDiarioModel.getNumeroMovimento() == null || movimentoDiarioModel.getNumeroMovimento() == 0) {

                    numeroUltimo = numeroMovimento;

                } else {

                    numeroUltimo = movimentoDiarioModel.getNumeroMovimento();

                }

                if (numeroUltimo >= numeroMovimento) {

                    numeroMovimento = numeroUltimo + 1;

                }

            }

            collMovimentos.add(new MovimentoDiarioModel(numeroMovimento));

        }

        Integer qtdMovimentosHoje = collMovimentos.size();

        Controller.setQtdMovimentosHoje(qtdMovimentosHoje);

        return numeroMovimento;

    }

    public static final synchronized String generateIdUsuario() {

        StringBuilder result = new StringBuilder();

        long time = System.nanoTime();

        time =
                (time / 10000000);

        time =
                (time / 10000);

        result.append(time);

        return result.toString();

    }
}
