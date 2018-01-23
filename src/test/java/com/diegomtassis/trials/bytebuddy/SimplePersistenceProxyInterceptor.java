package com.diegomtassis.trials.bytebuddy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bytebuddy.asm.Advice.AllArguments;
import net.bytebuddy.asm.Advice.Origin;
import net.bytebuddy.asm.Advice.This;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

public class SimplePersistenceProxyInterceptor {

    private static final String SETTER_PREFIX = "set";

    @RuntimeType
    public Object intercept(@AllArguments Object[] allArguments, @This PersistenceProxiedObject target,
            @Origin Method method) {

        try {
            method.invoke(target, allArguments);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        
        if (this.isMutator(method)) {
            target.getProxyHandler().markDirty();
        }

        return null;
    }

    private Boolean isMutator(Method method) {
        return method.getName().startsWith(SETTER_PREFIX);
    }
}
