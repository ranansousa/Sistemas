package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.SacadoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class SacadoDAO implements SacadoDAOIf {

    @HibernateTransaction
    public void inserir(SacadoModel model) throws SystemException {

        if (model.getEndereco() != null) {

            EnderecoModel enderecoModel = model.getEndereco();

            enderecoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().save(enderecoModel);

        }

        if (model.getContato() != null) {

            ContatoModel contatoModel = model.getContato();

            contatoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().save(contatoModel);

        }

        if (model.getContaContabil() != null) {

            ContaContabilModel contaContabil = model.getContaContabil();

            if (contaContabil.getPk() == null && contaContabil.getPk().getId().isEmpty()) {

                contaContabil.setPk(new PKModel());

                contaContabil.getPk().setId(PempecKeyGenerator.generateKey());

                contaContabil.getPk().setOrganizacao(model.getPk().getOrganizacao());

            }


            HibernateUtil.getCurrentSession().save(contaContabil);

        }

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().save(model);
        
       //Repopulador.repopulador(Tela.TELA_PARAMETROS_SACADOS, model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(SacadoModel model) throws SystemException {

        if (model.getEndereco() != null) {

            EnderecoModel enderecoModel = model.getEndereco();

            enderecoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (model.getEndereco().getPk().getId().isEmpty()) {

                enderecoModel.getPk().setId(PempecKeyGenerator.generateKey());

                HibernateUtil.getCurrentSession().save(enderecoModel);

                model.getEndereco().getPk().setId(enderecoModel.getPk().getId());

            } else {

                HibernateUtil.getCurrentSession().update(enderecoModel);
            }

        }

        if (model.getContato() != null) {

            ContatoModel contatoModel = model.getContato();

            contatoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (model.getContato().getPk().getId().isEmpty()) {

                contatoModel.getPk().setId(PempecKeyGenerator.generateKey());

                HibernateUtil.getCurrentSession().save(contatoModel);

                model.getContato().getPk().setId(contatoModel.getPk().getId());

            } else {

                HibernateUtil.getCurrentSession().update(contatoModel);
            }
        }

        if (model.getContaContabil() != null) {

            ContaContabilModel contaContabil = model.getContaContabil();

            contaContabil.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (model.getContaContabil().getPk().getId().isEmpty()) {

                contaContabil.getPk().setId(PempecKeyGenerator.generateKey());

                HibernateUtil.getCurrentSession().save(contaContabil);

                model.getContaContabil().getPk().setId(contaContabil.getPk().getId());

            } else {

                HibernateUtil.getCurrentSession().update(contaContabil);
            }
        }

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }


        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();

    }

    public SacadoModel consultarPorPk(SacadoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                SacadoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("banco", FetchMode.JOIN);
        criteria.setFetchMode("tipoSacado", FetchMode.JOIN);
        criteria.setFetchMode("contato", FetchMode.JOIN);
        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("endereco", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("bairro", FetchMode.JOIN);

        criteria.setMaxResults(1);

        return (SacadoModel) criteria.uniqueResult();

    }

    public SacadoModel consultarPorContaContabil(ContaContabilModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                SacadoModel.class);

        criteria.add(Restrictions.eq("contaContabil.pk.id", model.getPk().getId()));

        criteria.setFetchMode("banco", FetchMode.JOIN);
        criteria.setFetchMode("tipoSacado", FetchMode.JOIN);
        criteria.setFetchMode("contato", FetchMode.JOIN);
        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("endereco", CriteriaSpecification.LEFT_JOIN);
        subCriteria.setFetchMode("bairro", FetchMode.JOIN);

        criteria.setMaxResults(1);

        return (SacadoModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(SacadoModel model)
            throws SystemException {

        if (model.getEndereco() != null) {

            EnderecoModel enderecoModel = model.getEndereco();

            enderecoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().delete(enderecoModel);

        }

        if (model.getContato() != null) {

            ContatoModel contatoModel = model.getContato();

            contatoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().delete(contatoModel);

        }

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();

    }

    public SacadoModel consultarPorTemplate(SacadoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                SacadoModel.class);

        if (model.getNome() != null && !model.getNome().isEmpty()) {
            criteria.add(Restrictions.like("nome", model.getNome(),
                    MatchMode.START));
        }

        criteria.setFetchMode("banco", FetchMode.JOIN);
        criteria.setFetchMode("tipoSacado", FetchMode.JOIN);
        criteria.setFetchMode("contato", FetchMode.JOIN);
        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("endereco", CriteriaSpecification.LEFT_JOIN);
        subCriteria.setFetchMode("bairro", FetchMode.JOIN);
        subCriteria.setFetchMode("cidade", FetchMode.JOIN);
        subCriteria.setFetchMode("estado", FetchMode.JOIN);



        criteria.setMaxResults(1);

        return (SacadoModel) criteria.uniqueResult();

    }

    public List<SacadoModel> obterPorFiltro(SacadoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                SacadoModel.class);

        if (model.getNome() != null && !model.getNome().isEmpty()) {
            criteria.add(Restrictions.eq("nome", model.getNome()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("nome"));

        return criteria.list();

    }

    public List<SacadoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                SacadoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.addOrder(Order.asc("nome"));

        return criteria.list();

    }

    @HibernateTransaction
    public void excluirTodos(Collection<SacadoModel> coll)
            throws SystemException {

        for (SacadoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }
}
