package com.github.mzule.easyadapter.recycler;

import android.content.Context;

import com.github.mzule.easyadapter.ViewType;

/**
 * Created by CaoDongping on 3/22/16.
 */
public abstract class SingleTypeAdapter<T> extends MultiTypeAdapter<T> {
    public SingleTypeAdapter(Context context) {
        super(context);
    }

    @Override
    protected final void registerViewTypes() {
        registerViewType(singleViewType());
    }

    protected abstract Class<? extends ViewType> singleViewType();

    @Override
    protected final Class<? extends ViewType> getViewType(int position, T data) {
        return getViewTypes().get(0);
    }
}
