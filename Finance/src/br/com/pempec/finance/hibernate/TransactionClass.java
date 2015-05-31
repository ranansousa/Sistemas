package br.com.pempec.finance.hibernate;

import br.com.pempec.finance.exceptions.SystemException;
import net.sf.cglib.proxy.Enhancer;

//EQUIPE PEMPEC
public final class TransactionClass {

    public static Object create(Class beanClass, Class interceptorClass) throws SystemException {
        HibernateInterceptor interceptor;
        Object object = null;
        try {

            interceptor = (HibernateInterceptor) interceptorClass.newInstance();

            object = Enhancer.create(beanClass, interceptor);

        } catch (InstantiationException e) {
            throw new SystemException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new SystemException(e.getMessage());
        }

        return object;
    }
}
