package com.diegomtassis.trials.bytebuddy;

/**
 * Handles a persisted object.
 */
public interface PersistenceProxyHandler extends ProxyHandler {

    /**
     * Saves the state of the proxied entity.
     */
    void save();

    /**
     * Marks the handler to be in a dirty state.
     */
    void markDirty();

    /**
     * @return <code>true</code> if the handler is dirty, <code>false</code> otherwise.
     */
    Boolean isDirty();
}
