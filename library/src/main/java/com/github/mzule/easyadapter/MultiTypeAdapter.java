package com.github.mzule.easyadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoDongping on 3/17/16.
 */
public abstract class MultiTypeAdapter<T> extends BaseAdapter {
    private List<T> data;
    private List<Class<? extends ViewSupplier>> viewSupplierTypes;
    private Context context;

    public MultiTypeAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
        this.viewSupplierTypes = new ArrayList<>();
        registerTypes();
    }

    protected void registerViewSupplierType(Class<? extends ViewSupplier> cls) {
        this.viewSupplierTypes.add(cls);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        Class<? extends ViewSupplier> cls = getViewSupplierType(position, getItem(position));
        int type = viewSupplierTypes.indexOf(cls);
        if (type < 0) {
            throw new IllegalAccessError(String.format("type not registered [%s]", cls.toString()));
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return viewSupplierTypes.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewSupplier<? extends T> viewSupplier = getViewSupplier(context, getViewSupplierType(position, getItem(position)));
            convertView = viewSupplier.getView();
            convertView.setTag(viewSupplier);
        }
        ViewSupplier<T> viewSupplier = (ViewSupplier<T>) convertView.getTag();
        viewSupplier.render(position, getItem(position));
        return convertView;
    }

    @SuppressWarnings("unchecked")
    private ViewSupplier<? extends T> getViewSupplier(Context context, Class<? extends ViewSupplier> cls) {
        ViewSupplier<? extends T> viewSupplier;
        try {
            viewSupplier = cls.getConstructor(Context.class).newInstance(context);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IllegalAccessError("error on instantiation class " + cls.toString());
        }
        viewSupplier.inflateView(context).bind();
        return viewSupplier;
    }

    public void add(List<? extends T> data) {
        this.data.addAll(data);
    }

    public void addAndNotify(List<? extends T> data) {
        add(data);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
    }

    public void clearAndNotify() {
        clear();
        notifyDataSetChanged();
    }

    protected abstract void registerTypes();

    protected abstract Class<? extends ViewSupplier> getViewSupplierType(int position, T data);
}
