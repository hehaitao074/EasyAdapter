package com.github.mzule.easyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class ViewSupplier<T> {
    private Context context;
    private View view;

    ViewSupplier<T> inflateView(Context context) {
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(getLayoutResourceId(), null, false);
        return this;
    }

    protected Context getContext() {
        return context;
    }

    @SuppressWarnings("unchecked")
    protected <V> V findViewById(int id) {
        return (V) view.findViewById(id);
    }

    public View getView() {
        return view;
    }

    protected abstract int getLayoutResourceId();

    protected abstract void bind();

    public abstract void render(int position, T data);
}