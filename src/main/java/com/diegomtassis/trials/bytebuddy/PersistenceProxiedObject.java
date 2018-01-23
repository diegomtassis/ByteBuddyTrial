package com.diegomtassis.trials.bytebuddy;

/**
 * Entity proxied for persistence.
 */
public interface PersistenceProxiedObject extends ProxiedObject {

    /**
     * Sets the {@link PersistenceProxyHandler} handling the persistence of the entity.
     * 
     * @param proxyHandler
     */
    void setProxyHandler(PersistenceProxyHandler proxyHandler);

    /**
     * @return the {@link PersistenceProxyHandler} handling the persistence of the entity.
     */
    PersistenceProxyHandler getProxyHandler();

}
