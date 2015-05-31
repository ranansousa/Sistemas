package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloPagarBaixaAcrescimoDAOIf {

    public void inserir(TituloPagarBaixaAcrescimoModel model) throws SystemException;

    public void alterar(TituloPagarBaixaAcrescimoModel model) throws SystemException;

    public TituloPagarBaixaAcrescimoModel consultarPorPk(TituloPagarBaixaAcrescimoModel model) throws SystemException;

    public List<TituloPagarBaixaAcrescimoModel> obterPorFiltro(TituloPagarBaixaAcrescimoModel model)
            throws SystemException;

    public List<TituloPagarBaixaAcrescimoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloPagarBaixaAcrescimoModel> coll)
            throws SystemException;

    public TituloPagarBaixaAcrescimoModel consultarPorTemplate(TituloPagarBaixaAcrescimoModel model)
            throws SystemException;

    public void excluir(TituloPagarBaixaAcrescimoModel model) throws SystemException;
}
