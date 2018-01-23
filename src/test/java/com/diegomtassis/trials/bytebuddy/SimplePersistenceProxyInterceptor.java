package com.diegomtassis.trials.bytebuddy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

public class SimplePersistenceProxyInterceptor {

    private static final String SETTER_PREFIX = "set";

    @RuntimeType
    public Object intercept(@AllArguments Object[] allArguments, @This Object target, @Origin Method method) {

        if (!(target instanceof PersistenceProxiedObject)) {
            throw new IllegalArgumentException("only " + PersistenceProxiedObject.class + " can be adviced");
        }

        PersistenceProxyHandler proxyHandler = ((PersistenceProxiedObject) target).getProxyHandler();
        try {
            method.invoke(proxyHandler.proxied(), allArguments);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        if (this.isMutator(method)) {
            ((PersistenceProxiedObject) target).getProxyHandler().markDirty();
        }

        return null;
    }

    private Boolean isMutator(Method method) {
        return method.getName().startsWith(SETTER_PREFIX);
    }
}
