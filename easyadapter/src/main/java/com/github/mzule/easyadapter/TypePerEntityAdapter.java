package com.github.mzule.easyadapter;

import android.content.Context;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CaoDongping on 3/18/16.
 */
public abstract class TypePerEntityAdapter<T> extends MultiTypeAdapter<T> {
    private Map<Class, Class<? extends ViewSupplier>> map;

    public TypePerEntityAdapter(Context context) {
        super(context);
    }

    @Override
    protected final void registerTypes() {
        map = new HashMap<Class, Class<? extends ViewSupplier>>();
        registerEntityViewSupplierTypes();
        Collection<Class<? extends ViewSupplier>> values = map.values();
        for (Class<? extends ViewSupplier> c : values) {
            registerViewSupplierType(c);
        }
    }

    protected final void registerType(Class<? extends T> entityType, Class<? extends ViewSupplier> viewSupplierType) {
        map.put(entityType, viewSupplierType);
    }

    protected abstract void registerEntityViewSupplierTypes();

    @Override
    protected final Class<? extends ViewSupplier> getViewSupplierType(int position, T data) {
        return map.get(data.getClass());
    }
}
