package com.github.mzule.easyadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by CaoDongping on 3/17/16.
 */
public abstract class MultiTypeAdapter<T> extends BaseAdapter {
    private List<T> data;
    private List<Class<? extends ViewType>> viewTypes;
    private Context context;

    public MultiTypeAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<T>();
        this.viewTypes = new ArrayList<Class<? extends ViewType>>();
        registerViewTypes();
    }

    protected void registerViewType(Class<? extends ViewType> cls) {
        this.viewTypes.add(cls);
    }

    public List<Class<? extends ViewType>> getViewTypes() {
        return viewTypes;
    }

    /**
     * Retrieve the raw viewType (value returned by `getItemViewType(int)`) at adapter.
     *
     * @param viewType ViewType class
     * @return 0, 1, 2... The raw viewType value.
     */
    public int getRawViewType(Class<? extends ViewType> viewType) {
        return viewTypes.indexOf(viewType);
    }

    public List<T> getData() {
        return Collections.unmodifiableList(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        Class<? extends ViewType> cls = getViewType(position, getItem(position));
        int type = viewTypes.indexOf(cls);
        if (type < 0) {
            throw new TypeRegisteException(String.format("ViewType not registered [%s]", cls.toString()));
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return viewTypes.size();
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
            ViewType<? extends T> viewType = createViewType(getViewType(position, getItem(position)));
            viewType.with(context).onCreate();
            convertView = viewType.getView();
            convertView.setTag(viewType);
        }
        ViewType<T> viewType = (ViewType<T>) convertView.getTag();
        viewType.onRender(position, getItem(position));
        return convertView;
    }

    @SuppressWarnings("unchecked")
    private ViewType<? extends T> createViewType(Class<? extends ViewType> cls) {
        ViewType<? extends T> viewType;
        try {
            viewType = cls.newInstance();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException("Error on instantiation class [" + cls.toString() + "], please make sure the class is `public` for others.");
        }
        return viewType;
    }

    public void add(List<? extends T> data) {
        if (data != null) {
            this.data.addAll(data);
        }
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

    protected abstract void registerViewTypes();

    protected abstract Class<? extends ViewType> getViewType(int position, T data);
}
