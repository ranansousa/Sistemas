package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloPagarBaixaDeducaoDAOIf {

    public void inserir(TituloPagarBaixaDeducaoModel model) throws SystemException;

    public void alterar(TituloPagarBaixaDeducaoModel model) throws SystemException;

    public TituloPagarBaixaDeducaoModel consultarPorPk(TituloPagarBaixaDeducaoModel model) throws SystemException;

    public List<TituloPagarBaixaDeducaoModel> obterPorFiltro(TituloPagarBaixaDeducaoModel model)
            throws SystemException;

    public List<TituloPagarBaixaDeducaoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloPagarBaixaDeducaoModel> coll)
            throws SystemException;

    public TituloPagarBaixaDeducaoModel consultarPorTemplate(TituloPagarBaixaDeducaoModel model)
            throws SystemException;

    public void excluir(TituloPagarBaixaDeducaoModel model) throws SystemException;
}
