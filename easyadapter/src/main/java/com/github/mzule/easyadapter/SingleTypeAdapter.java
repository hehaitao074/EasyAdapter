package com.github.mzule.easyadapter;

import android.content.Context;

/**
 * Created by CaoDongping on 1/4/16.
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
