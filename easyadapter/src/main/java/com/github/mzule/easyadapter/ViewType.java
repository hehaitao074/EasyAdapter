package com.github.mzule.easyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class ViewType<T> {
    private Context context;
    private View view;

    protected void setContentView(int layoutId) {
        setContentView(LayoutInflater.from(context).inflate(layoutId, null, false));
    }

    protected void setContentView(View view) {
        this.view = view;
    }

    ViewType<T> with(Context context) {
        this.context = context;
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

    public abstract void onCreate();

    public abstract void onRender(int position, T data);
}