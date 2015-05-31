package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.BackupModel;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface BackupDAOIf {

    public void inserir(Collection<BackupModel> backups) throws SystemException;

    public void inserir(BackupModel model) throws SystemException;

    public void alterar(BackupModel model) throws SystemException;

    public BackupModel consultarPorPk(BackupModel model)
            throws SystemException;

    public List<BackupModel> obterPorFiltro(BackupModel model)
            throws SystemException;

    public List<BackupModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(BackupModel model) throws SystemException;

    public BackupModel consultarPorTemplate(BackupModel model)
            throws SystemException;

    public List<BackupModel> obterTodosPorPeriodo(BackupModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<BackupModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException;
}//fim
