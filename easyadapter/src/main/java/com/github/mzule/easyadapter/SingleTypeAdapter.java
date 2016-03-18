package com.github.mzule.easyadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoDongping on 1/4/16.
 */
public abstract class SingleTypeAdapter<T> extends BaseAdapter {
    private List<T> items;
    private Context context;

    public SingleTypeAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<T>();
    }

    @Override
    public int getCount() {
        return items.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewSupplier<T> viewSupplier = createViewSupplier(context);
            convertView = viewSupplier.getView();
            convertView.setTag(viewSupplier);
        }
        @SuppressWarnings("unchecked")
        ViewSupplier<T> viewSupplier = (ViewSupplier<T>) convertView.getTag();
        viewSupplier.render(position, getItem(position));
        return convertView;
    }

    public Context getContext() {
        return context;
    }

    public void add(List<T> data) {
        items.addAll(data);
    }

    public void addAndNotify(List<T> data) {
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

    protected abstract ViewSupplier<T> createViewSupplier(Context context);
}
