package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface LotePagamentoTituloDAOIf {

    public void inserir(LotePagamentoTituloModel model) throws SystemException;

    public void alterar(LotePagamentoTituloModel model) throws SystemException;

    public LotePagamentoTituloModel consultarPorPk(LotePagamentoTituloModel model)
            throws SystemException;

    public List<LotePagamentoTituloModel> obterPorFiltro(LotePagamentoTituloModel model)
            throws SystemException;

    public List<LotePagamentoTituloModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(LotePagamentoTituloModel model) throws SystemException;

    public LotePagamentoTituloModel consultarPorTemplate(LotePagamentoTituloModel model)
            throws SystemException;

    public void excluirTodos(Collection<LotePagamentoTituloModel> coll)
            throws SystemException;

    public Boolean validaExclusao(LotePagamentoTituloModel model)
            throws SystemException, ApplicationException;
}
