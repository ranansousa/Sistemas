package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.EstadoModel;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface EstadoDAOIf {

    public void inserir(EstadoModel model) throws SystemException;

    public void alterar(EstadoModel model) throws SystemException;

    public EstadoModel consultarPorPk(EstadoModel model)
            throws SystemException;

    public List<EstadoModel> obterPorFiltro(EstadoModel model)
            throws SystemException;

    public void excluir(EstadoModel model) throws SystemException;

    public EstadoModel consultarPorTemplate(EstadoModel model)
            throws SystemException;

    public List<EstadoModel> obterTodos() throws SystemException;

    public void excluirTodos(String[] array) throws SystemException;
}
