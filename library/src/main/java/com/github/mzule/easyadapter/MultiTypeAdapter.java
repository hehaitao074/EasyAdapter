package com.github.mzule.easyadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CaoDongping on 3/17/16.
 */
public abstract class MultiTypeAdapter extends BaseAdapter {
    private List<TypedValue> all;
    private List<String> types;
    private Context context;

    public MultiTypeAdapter(Context context) {
        this.context = context;
        this.all = new ArrayList<>();
        this.types = new ArrayList<>();
        registerTypes();
    }

    protected void registerType(String... type) {
        this.types.addAll(Arrays.asList(type));
    }

    @Override
    public int getCount() {
        return all.size();
    }

    @Override
    public int getItemViewType(int position) {
        return types.indexOf(all.get(position).getType());
    }

    @Override
    public int getViewTypeCount() {
        return types.size();
    }

    @Override
    public TypedValue getItem(int position) {
        return all.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            ViewSupplier<TypedValue> viewSupplier = createViewSupplier(context, position, parent, all.get(position).getType());
            convertView = viewSupplier.getView();
            convertView.setTag(viewSupplier);
        }
        ViewSupplier<TypedValue> viewSupplier = (ViewSupplier<TypedValue>) convertView.getTag();
        viewSupplier.render(position, getItem(position));
        return convertView;
    }


    public void add(List<? extends Object> data, TypedValueConverter converter) {
        List<TypedValue> typedValues = new ArrayList<>(data.size());
        for (Object obj : data) {
            typedValues.add(converter.convert(obj));
        }
        add(typedValues);
    }

    public void addAndNotify(List<? extends Object> data, TypedValueConverter converter) {
        add(data, converter);
        notifyDataSetChanged();
    }

    public void add(List<TypedValue> data) {
        all.addAll(data);
    }

    public void addAndNotify(List<TypedValue> data) {
        add(data);
        notifyDataSetChanged();
    }

    public void clear() {
        all.clear();
    }

    public void clearAndNotify() {
        clear();
        notifyDataSetChanged();
    }

    protected abstract void registerTypes();

    protected abstract ViewSupplier<TypedValue> createViewSupplier(Context context, int position, ViewGroup parent, String type);
}
