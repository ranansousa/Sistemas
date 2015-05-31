package br.com.pempec.finance.hibernate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


import br.com.pempec.finance.exceptions.SystemException;

public class HibernateInterceptorAnnotation extends HibernateInterceptor {

    public boolean isTransactional(Object object, Method method) throws SystemException {
        Annotation annotation = method.getAnnotation(HibernateTransaction.class);
        return annotation == null ? false : true;
    }
}
