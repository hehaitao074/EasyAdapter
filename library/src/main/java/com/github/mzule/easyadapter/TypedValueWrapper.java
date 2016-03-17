package com.github.mzule.easyadapter;

/**
 * Created by CaoDongping on 3/17/16.
 */
public abstract class TypedValueWrapper implements TypedValue {
    private Object raw;

    public TypedValueWrapper(Object raw) {
        this.raw = raw;
    }

    public Object getRaw() {
        return raw;
    }
}
