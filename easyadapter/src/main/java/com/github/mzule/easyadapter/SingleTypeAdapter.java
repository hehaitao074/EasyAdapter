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
    protected final void registerTypes() {
        registerViewSupplierType(singleViewSupplierType());
    }

    protected abstract Class<? extends ViewSupplier> singleViewSupplierType();

    @Override
    protected Class<? extends ViewSupplier> getViewSupplierType(int position, T data) {
        return getViewSupplierTypes().get(0);
    }
}
