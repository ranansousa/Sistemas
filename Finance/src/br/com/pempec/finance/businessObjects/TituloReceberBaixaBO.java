package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TituloReceberBaixaBO {

    public void salvar(TituloReceberBaixaModel model, TituloReceberModel titulo) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaDAO().salvar(model, titulo);
    }

    public TituloReceberBaixaModel consultarPorPk(TituloReceberBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDAO().consultarPorPk(
                model);
    }

    public void excluir(TituloReceberBaixaModel model, TituloReceberModel titulo)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaDAO().excluir(model, titulo);
    }

    public TituloReceberBaixaModel consultarPorTemplate(TituloReceberBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDAO().consultarPorTemplate(model);
    }

    public TituloReceberBaixaModel consultarPorTitulo(TituloReceberModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDAO().consultarPorTitulo(model);
    }

    public List<TituloReceberBaixaModel> obterBaixasExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDAO().obterBaixasExportacao(
                model, dataInicial, dataFinal);
    }

    public List<TituloReceberBaixaModel> obterBaixasExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDAO().obterBaixasExportacaoRelatorio(
                model, dataInicial, dataFinal);
    }

    public List<TituloReceberBaixaModel> obterPorFiltro(TituloReceberBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TituloReceberBaixaModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaDAO().excluirTodos(coll);
    }

    public List<TituloReceberBaixaModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDAO().obterTodos(model);
    }

    public List<TituloReceberBaixaModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDAO().obterTitulosExportados(model);
    }

    public void salvarLote(LoteRecebimentoTituloModel lote, Collection<TituloReceberBaixaModel> collBaixa, Collection<TituloReceberModel> collTitulo) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaDAO().salvarLote(lote, collBaixa, collTitulo);
    }
}
