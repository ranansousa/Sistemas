package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import java.util.Collection;

/**
 * @author PEMPEC
 */
public interface MegaContabilDAOIf {

    /**
     * Método que irá criar o LOTE_CONTABIL e atualizar todas as tabelas setando
     * seu ID.
     *
     * @param collTitulosPagar
     * @param collBaixasTitulosPagar
     * @param collTitulosReceber
     * @param collBaixasTitulosReceber
     * @param collTesourariaDebito
     * @param collTesourariaCredito
     * @param collContaBancariaDebito
     * @param collContaBancariaCredito
     * * @param collContaBancariaTransferencia
     * @throws br.com.pempec.finance.exceptions.SystemException
     */
    public void gerarLote(Collection<TituloPagarModel> collTitulosPagar,
            Collection<TituloPagarBaixaModel> collBaixasTitulosPagar,
            Collection<TituloReceberModel> collTitulosReceber,
            Collection<TituloReceberBaixaModel> collBaixasTitulosReceber,
            Collection<TesourariaDebitoModel> collTesourariaDebito,
            Collection<TesourariaCreditoModel> collTesourariaCredito,
            Collection<ContaBancariaDebitoModel> collContaBancariaDebito,
            Collection<ContaBancariaCreditoModel> collContaBancariaCredito,
            Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia,
            LoteContabilModel loteContabil) throws SystemException;

    public void removerLote(Collection<TituloPagarModel> collTitulosPagar,
            Collection<TituloPagarBaixaModel> collBaixasTitulosPagar,
            Collection<TituloReceberModel> collTitulosReceber,
            Collection<TituloReceberBaixaModel> collBaixasTitulosReceber,
            Collection<TesourariaDebitoModel> collTesourariaDebito,
            Collection<TesourariaCreditoModel> collTesourariaCredito,
            Collection<ContaBancariaDebitoModel> collContaBancariaDebito,
            Collection<ContaBancariaCreditoModel> collContaBancariaCredito,
            Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia,
            LoteContabilModel loteContabil) throws SystemException;
}
