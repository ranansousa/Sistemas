package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.BackupDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.BackupModel;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pempec
 */
public class BackupDAO implements BackupDAOIf {

    @HibernateTransaction
    public void inserir(Collection<BackupModel> backups) throws SystemException {


        for (BackupModel backupModel : backups) {

            HibernateUtil.getCurrentSession().save(backupModel);


            if (backupModel.getMovimentoDiarioModel() != null) {

                MovimentoDiarioModel mov = backupModel.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }



        }

    }

    @HibernateTransaction
    public void inserir(BackupModel model) throws SystemException {


        if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().save(model);


    }

    @HibernateTransaction
    public void alterar(BackupModel model) throws SystemException {


        if (model.getMovimentoDiarioModel() != null && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }


        HibernateUtil.getCurrentSession().update(model);

    }

    public List<BackupModel> obterPorFiltro(BackupModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BackupModel.class);

        if (model.getNomeFile() != null && !model.getNomeFile().isEmpty()) {
            criteria.add(Restrictions.like("nomeFile", model.getNomeFile(),
                    MatchMode.START));
        }



        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("dataBackup"));
        criteria.addOrder(Order.asc("nomeFile"));

        return criteria.list();

    }

    public List<BackupModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BackupModel.class);


        criteria.addOrder(Order.asc("dataBackup"));
        criteria.addOrder(Order.asc("nomeFile"));


        return criteria.list();
    }

    @HibernateTransaction
    public void excluir(BackupModel model)
            throws SystemException {


        if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);
        }


        HibernateUtil.getCurrentSession().update(model);


    }

    public BackupModel consultarPorPk(BackupModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BackupModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (BackupModel) criteria.uniqueResult();

    }

    public BackupModel consultarPorTemplate(BackupModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BackupModel.class);

        if (model.getNomeFile() != null && !model.getNomeFile().isEmpty()) {
            criteria.add(Restrictions.eq("nomeFile", model.getNomeFile()));
        }

        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (BackupModel) criteria.uniqueResult();

    }

    public List<BackupModel> obterTodosPorPeriodo(BackupModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BackupModel.class);


        criteria.add(Restrictions.between("dataBackup", dataInicial, dataFinal));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("usuario", FetchMode.JOIN);


        criteria.addOrder(Order.asc("dataBackup"));
        criteria.addOrder(Order.asc("nomeFile"));


        return criteria.list();

    }

    public List<BackupModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BackupModel.class);

        criteria.add(Restrictions.eq("dataBackup", dataInicial));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));


        criteria.addOrder(Order.asc("nomeFile"));


        return criteria.list();

    }
}//fim

