package com.diegomtassis.trials.bytebuddy;

public class DummyPersistenceProxyHandler implements PersistenceProxyHandler {

    private final Object wrapped;
    private Boolean dirty = Boolean.FALSE;

    public DummyPersistenceProxyHandler(Object wrapped) {
        this.wrapped = wrapped;
    }

    public void save() {
        this.dirty = Boolean.FALSE;
    }

    public void markDirty() {
        this.dirty = Boolean.TRUE;
    }

    public Boolean isDirty() {
        return this.dirty;
    }
}
