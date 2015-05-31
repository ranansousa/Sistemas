package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoAcrescimoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TipoAcrescimoDAOIf {

    public void inserir(TipoAcrescimoModel model) throws SystemException;

    public void alterar(TipoAcrescimoModel model) throws SystemException;

    public TipoAcrescimoModel consultarPorPk(TipoAcrescimoModel model)
            throws SystemException;

    public List<TipoAcrescimoModel> obterPorFiltro(TipoAcrescimoModel model)
            throws SystemException;

    public List<TipoAcrescimoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(TipoAcrescimoModel model) throws SystemException;

    public TipoAcrescimoModel consultarPorTemplate(TipoAcrescimoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TipoAcrescimoModel> coll)
            throws SystemException;
}
