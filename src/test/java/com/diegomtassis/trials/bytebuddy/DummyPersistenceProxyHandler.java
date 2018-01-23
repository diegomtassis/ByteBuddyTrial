package com.diegomtassis.trials.bytebuddy;

public class DummyPersistenceProxyHandler implements PersistenceProxyHandler {

    private final Object wrapped;
    private Boolean dirty = Boolean.FALSE;

    public DummyPersistenceProxyHandler(Object wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void save() {
        this.dirty = Boolean.FALSE;
    }

    @Override
    public void markDirty() {
        this.dirty = Boolean.TRUE;
    }

    @Override
    public Boolean isDirty() {
        return this.dirty;
    }

    @Override
    public Object proxied() {
        return this.wrapped;
    }
}
