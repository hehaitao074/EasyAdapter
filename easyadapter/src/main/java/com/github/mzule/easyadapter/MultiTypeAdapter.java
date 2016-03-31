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
public abstract class MultiTypeAdapter<T> extends BaseAdapter implements EasyAdapter<T> {
    private List<T> data;
    private List<Class<? extends ViewType>> viewTypes;
    private Context context;
    private boolean editMode;

    public MultiTypeAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<T>();
        this.viewTypes = new ArrayList<Class<? extends ViewType>>();
        registerViewTypes();
    }

    protected void registerViewType(Class<? extends ViewType> cls) {
        this.viewTypes.add(cls);
    }

    @Override
    public List<Class<? extends ViewType>> getViewTypes() {
        return viewTypes;
    }

    @Override
    public int getRawViewType(Class<? extends ViewType> viewType) {
        return viewTypes.indexOf(viewType);
    }

    @Override
    public List<T> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        Class<? extends ViewType> cls = getViewType(position, getItem(position));
        if (cls == null) {
            throw new TypeRegisteException("No ViewType bind for [" + getItem(position).getClass() + "] at position " + position);
        }
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
            viewType.setAdapter(this);
            convertView = viewType.getView();
            convertView.setTag(viewType);
        }
        ViewType<T> viewType = (ViewType<T>) convertView.getTag();
        viewType.onRender(position, getItem(position));
        viewType.setEditMode(isEditMode());
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

    @Override
    public void add(List<? extends T> data) {
        if (data != null) {
            this.data.addAll(data);
        }
    }

    @Override
    public void addAndNotify(List<? extends T> data) {
        add(data);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        data.clear();
    }


    @Override
    public void clearAndNotify() {
        clear();
        notifyDataSetChanged();
    }

    @Override
    public void remove(int position) {
        data.remove(position);
    }

    @Override
    public void removeAndNotify(int position) {
        remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T item) {
        data.remove(item);
    }

    @Override
    public void removeAndNotify(T item) {
        remove(item);
        notifyDataSetChanged();
    }

    @Override
    public boolean isEditMode() {
        return editMode;
    }

    @Override
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    protected abstract void registerViewTypes();

    protected abstract Class<? extends ViewType> getViewType(int position, T data);
}
