package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
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
public class MegaContabilBO {

    public void gerarLote(Collection<TituloPagarModel> collTitulosPagar,
            Collection<TituloPagarBaixaModel> collBaixasTitulosPagar,
            Collection<TituloReceberModel> collTitulosReceber,
            Collection<TituloReceberBaixaModel> collBaixasTitulosReceber,
            Collection<TesourariaDebitoModel> collTesourariaDebito,
            Collection<TesourariaCreditoModel> collTesourariaCredito,
            Collection<ContaBancariaDebitoModel> collContaBancariaDebito,
            Collection<ContaBancariaCreditoModel> collContaBancariaCredito,
            Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia,
            LoteContabilModel loteContabil) throws SystemException,
            ApplicationException {

        DAOFactory.getInstance().getMegaContabilDAO().gerarLote(collTitulosPagar, collBaixasTitulosPagar, collTitulosReceber, collBaixasTitulosReceber, collTesourariaDebito, collTesourariaCredito, collContaBancariaDebito, collContaBancariaCredito, collContaBancariaTransferencia, loteContabil);

    }

    public void removerLote(Collection<TituloPagarModel> collTitulosPagar,
            Collection<TituloPagarBaixaModel> collBaixasTitulosPagar,
            Collection<TituloReceberModel> collTitulosReceber,
            Collection<TituloReceberBaixaModel> collBaixasTitulosReceber,
            Collection<TesourariaDebitoModel> collTesourariaDebito,
            Collection<TesourariaCreditoModel> collTesourariaCredito,
            Collection<ContaBancariaDebitoModel> collContaBancariaDebito,
            Collection<ContaBancariaCreditoModel> collContaBancariaCredito,
            Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia,
            LoteContabilModel loteContabil) throws SystemException,
            ApplicationException {

        DAOFactory.getInstance().getMegaContabilDAO().removerLote(collTitulosPagar, collBaixasTitulosPagar, collTitulosReceber, collBaixasTitulosReceber, collTesourariaDebito, collTesourariaCredito, collContaBancariaDebito, collContaBancariaCredito, collContaBancariaTransferencia, loteContabil);

    }
}
