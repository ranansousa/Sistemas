package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloPagarBaixaInternetDAOIf {

    public void inserir(TituloPagarBaixaInternetModel model) throws SystemException;

    public void alterar(TituloPagarBaixaInternetModel model) throws SystemException;

    public TituloPagarBaixaInternetModel consultarPorPk(TituloPagarBaixaInternetModel model) throws SystemException;

    public List<TituloPagarBaixaInternetModel> obterPorFiltro(TituloPagarBaixaInternetModel model)
            throws SystemException;

    public List<TituloPagarBaixaInternetModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloPagarBaixaInternetModel> coll)
            throws SystemException;

    public TituloPagarBaixaInternetModel consultarPorTemplate(TituloPagarBaixaInternetModel model)
            throws SystemException;

    public List<TituloPagarBaixaInternetModel> obterPorTituloPagarBaixa(TituloPagarBaixaModel model)
            throws SystemException;

    public void excluir(TituloPagarBaixaInternetModel model) throws SystemException;
}
