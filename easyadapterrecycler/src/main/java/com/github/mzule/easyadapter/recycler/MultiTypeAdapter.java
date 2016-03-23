package com.github.mzule.easyadapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.mzule.easyadapter.EasyAdapter;
import com.github.mzule.easyadapter.TypeRegisteException;
import com.github.mzule.easyadapter.ViewType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by CaoDongping on 3/22/16.
 */
public abstract class MultiTypeAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements EasyAdapter<T> {
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
        return Collections.unmodifiableList(data);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        ViewType<T> viewType = createViewType(viewTypes.get(type));
        viewType.with(context).onCreate();
        return new ViewTypeHolder<T>(viewType);
    }

    @Override
    public int getItemViewType(int position) {
        Class<? extends ViewType> cls = getViewType(position, data.get(position));
        int type = viewTypes.indexOf(cls);
        if (type < 0) {
            throw new TypeRegisteException(String.format("ViewType not registered [%s]", cls.toString()));
        }
        return type;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewTypeHolder<T> viewTypeHolder = (ViewTypeHolder<T>) holder;
        viewTypeHolder.viewType.onRender(position, data.get(position));

    }

    @SuppressWarnings("unchecked")
    private ViewType<T> createViewType(Class<? extends ViewType> cls) {
        ViewType<T> viewType;
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

    protected abstract void registerViewTypes();

    protected abstract Class<? extends ViewType> getViewType(int position, T data);
}
