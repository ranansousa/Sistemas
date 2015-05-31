package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.reports.FilterReportMovimentoDiario;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface MovimentoDiarioDAOIf {

    public void inserir(Collection<MovimentoDiarioModel> titulos) throws SystemException;

    public void inserir(MovimentoDiarioModel model) throws SystemException;

    public void alterar(MovimentoDiarioModel model) throws SystemException;

    public MovimentoDiarioModel consultarPorPk(MovimentoDiarioModel model)
            throws SystemException;

    public List<MovimentoDiarioModel> obterPorFiltro(MovimentoDiarioModel model)
            throws SystemException;

    public List<MovimentoDiarioModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<MovimentoDiarioModel> obterRelatorio(FilterReportMovimentoDiario model)
            throws SystemException;

    public void excluir(MovimentoDiarioModel model) throws SystemException;

    public MovimentoDiarioModel consultarPorTemplate(MovimentoDiarioModel model)
            throws SystemException;

    public void excluirTodos(Collection<MovimentoDiarioModel> coll)
            throws SystemException;

    public List<MovimentoDiarioModel> obterTodosPorPeriodo(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<MovimentoDiarioModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException;
}//fim
