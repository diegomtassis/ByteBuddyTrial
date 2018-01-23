package com.diegomtassis.trials.bytebuddy;

import org.junit.Assert;
import org.junit.Test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class PersistenceHandlerTest {

    @Test
    public void testProxyUsingHandler() throws InstantiationException, IllegalAccessException {

        // setup
        DummyEntity entity = new DummyEntity();
        Class<?> dynamicType = new ByteBuddy() //
                .subclass(entity.getClass()) //
                .implement(PersistenceProxiedObject.class) //
                .defineProperty("proxyHandler", PersistenceProxyHandler.class) //
                .method(ElementMatchers.isDeclaredBy(entity.getClass())) //
                .intercept(MethodDelegation.to(new SimplePersistenceProxyInterceptor())) //
                .make().load(getClass().getClassLoader()).getLoaded();

        // exercise
        DummyEntity proxiedEntity = (DummyEntity) dynamicType.newInstance();
        PersistenceProxiedObject proxy = (PersistenceProxiedObject) proxiedEntity;
        PersistenceProxyHandler proxyHandler = new DummyPersistenceProxyHandler(entity);
        proxy.setProxyHandler(proxyHandler);

        // verify
        Assert.assertFalse(proxyHandler.isDirty());
        proxiedEntity.setFoo("foo");
        Assert.assertTrue(proxyHandler.isDirty());
        proxyHandler.save();
        Assert.assertFalse(proxyHandler.isDirty());
    }
}
