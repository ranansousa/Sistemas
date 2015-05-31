package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LoteRecebimentoTituloModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface LoteRecebimentoTituloDAOIf {

    public void inserir(LoteRecebimentoTituloModel model) throws SystemException;

    public void alterar(LoteRecebimentoTituloModel model) throws SystemException;

    public LoteRecebimentoTituloModel consultarPorPk(LoteRecebimentoTituloModel model)
            throws SystemException;

    public List<LoteRecebimentoTituloModel> obterPorFiltro(LoteRecebimentoTituloModel model)
            throws SystemException;

    public List<LoteRecebimentoTituloModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(LoteRecebimentoTituloModel model) throws SystemException;

    public LoteRecebimentoTituloModel consultarPorTemplate(LoteRecebimentoTituloModel model)
            throws SystemException;

    public void excluirTodos(Collection<LoteRecebimentoTituloModel> coll)
            throws SystemException;

    public Boolean validaExclusao(LoteRecebimentoTituloModel model)
            throws SystemException, ApplicationException;
}
