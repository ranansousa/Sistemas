package br.com.pempec.finance.hibernate;

import br.com.pempec.finance.exceptions.ApplicationException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import br.com.pempec.finance.exceptions.SystemException;
import java.sql.SQLException;

public abstract class HibernateInterceptor implements MethodInterceptor {

    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws ApplicationException, SystemException {
        Object result = null;
        if (isTransactional(object, method)) {
            HibernateUtil.beginTransaction();
        }
        try {

            result = methodProxy.invokeSuper(object, args);

            HibernateUtil.commitTransaction();

        } catch (Exception e) {

            e.getLocalizedMessage();

            String message;
            HibernateUtil.rollbackTransaction();

            if (e.getCause() instanceof SQLException) {
                switch (((SQLException) e.getCause()).getErrorCode()) {
                    case 547:
                        message = "Não é possível excluir este Registro \nAssociações Existentes!";
                        throw new ApplicationException(message);
                    case 2627:
                        message = "Não é possível inserir este Registro \nPK violada!";
                        break;
                    case 208:
                        message = "Tabela da Base não encontrada!";
                        break;
                    case 335544344:
                        message = "Base de Dados não localizada!";
                        break;
                    case 335544665:
                        message = "Ocorreu uma tentativa de violação de PK.\nOperação inválida.\nComunique ao suporte imediatamente!";
                        throw new ApplicationException(message);
                    case 335544466:
                        message = "Não foi possível conectar com o banco de dados.\nComunique ao suporte imediatamente!";
                        break;
                    default:
                        message = "Ocorreu um erro desconhecido!\n Contate o suporte imediatamente!\n " + e.getMessage();
                        break;
                }

            } else {
                message = e.getMessage();
            }

            throw new SystemException(message);

        } catch (Throwable e) {
            HibernateUtil.rollbackTransaction();
            throw new SystemException(e.getMessage());

        }

        return result;
    }

    public abstract boolean isTransactional(Object object, Method method) throws SystemException;
}
