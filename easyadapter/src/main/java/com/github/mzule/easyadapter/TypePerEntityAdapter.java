package com.github.mzule.easyadapter;

import android.content.Context;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CaoDongping on 3/18/16.
 */
public abstract class TypePerEntityAdapter<T> extends MultiTypeAdapter<T> {
    private Map<Class, Class<? extends ViewType>> map;

    public TypePerEntityAdapter(Context context) {
        super(context);
    }

    @Override
    protected final void registerViewTypes() {
        map = new HashMap<Class, Class<? extends ViewType>>();
        mapEntityViewTypes();
        Collection<Class<? extends ViewType>> values = map.values();
        for (Class<? extends ViewType> c : values) {
            registerViewType(c);
        }
    }

    protected final void mapEntityViewType(Class<? extends T> entityType, Class<? extends ViewType> viewTypes) {
        map.put(entityType, viewTypes);
    }

    protected abstract void mapEntityViewTypes();

    @Override
    protected final Class<? extends ViewType> getViewType(int position, T data) {
        return map.get(data.getClass());
    }
}
