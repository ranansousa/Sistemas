package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.BackupModel;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public class BackupBO {

    public void inserir(Collection<BackupModel> backups) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getBackupDAO().inserir(backups);
    }

    public void inserir(BackupModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getBackupDAO().inserir(model);
    }

    public void alterar(BackupModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getBackupDAO().alterar(model);
    }

    public BackupModel consultarPorPk(BackupModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBackupDAO().consultarPorPk(
                model);
    }

    public void excluir(BackupModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getBackupDAO().excluir(model);
    }

    public BackupModel consultarPorTemplate(BackupModel model)
            throws SystemException, ApplicationException {

        BackupModel retorno = DAOFactory.getInstance().getBackupDAO().consultarPorTemplate(model);

        return retorno;
    }

    public List<BackupModel> obterPorFiltro(BackupModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBackupDAO().obterPorFiltro(
                model);
    }

    public List<BackupModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBackupDAO().obterTodos(model);
    }

    public List<BackupModel> obterTodosPorPeriodo(BackupModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBackupDAO().obterTodosPorPeriodo(model, dataInicial, dataFinal);
    }

    public List<BackupModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBackupDAO().obterTodosPorData(model, dataInicial);

    }
}//fim

