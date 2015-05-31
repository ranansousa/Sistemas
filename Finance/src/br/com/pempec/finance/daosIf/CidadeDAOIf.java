package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CidadeModel;
import br.com.pempec.finance.models.EstadoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface CidadeDAOIf {

    public void inserir(CidadeModel model) throws SystemException;

    public void alterar(CidadeModel model) throws SystemException;

    public CidadeModel consultarPorPk(CidadeModel model)
            throws SystemException;

    public List<CidadeModel> obterPorFiltro(CidadeModel model)
            throws SystemException;

    public List<CidadeModel> obterTodos()
            throws SystemException;

    public List<CidadeModel> obterPorEstado(EstadoModel model)
            throws SystemException;

    public void excluir(CidadeModel model) throws SystemException;

    public CidadeModel consultarPorTemplate(CidadeModel model)
            throws SystemException;

    public void excluirTodos(Collection<CidadeModel> coll)
            throws SystemException;
}
