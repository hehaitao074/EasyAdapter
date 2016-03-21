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
    private List<T> items;
    private List<Class<? extends ViewType>> viewTypes;
    private Context context;

    public MultiTypeAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<T>();
        this.viewTypes = new ArrayList<Class<? extends ViewType>>();
        registerViewTypes();
    }

    protected void registerViewType(Class<? extends ViewType> cls) {
        this.viewTypes.add(cls);
    }

    public List<Class<? extends ViewType>> getViewTypes() {
        return viewTypes;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Class<? extends ViewType> cls = getViewType(position, getItem(position));
        int type = viewTypes.indexOf(cls);
        if (type < 0) {
            throw new IllegalAccessError(String.format("type not registered [%s]", cls.toString()));
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return viewTypes.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewType<? extends T> viewType = createViewType(getViewType(position, getItem(position)));
            viewType.inflateView(context).bind();
            convertView = viewType.getView();
            convertView.setTag(viewType);
        }
        ViewType<T> viewType = (ViewType<T>) convertView.getTag();
        viewType.render(position, getItem(position));
        return convertView;
    }

    @SuppressWarnings("unchecked")
    private ViewType<? extends T> createViewType(Class<? extends ViewType> cls) {
        ViewType<? extends T> viewType;
        try {
            viewType = cls.newInstance();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IllegalAccessError("error on instantiation class " + cls.toString());
        }
        return viewType;
    }

    public void add(List<? extends T> data) {
        this.items.addAll(data);
    }

    public void addAndNotify(List<? extends T> data) {
        add(data);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
    }

    public void clearAndNotify() {
        clear();
        notifyDataSetChanged();
    }

    protected abstract void registerViewTypes();

    protected abstract Class<? extends ViewType> getViewType(int position, T data);
}
