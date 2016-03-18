package com.github.mzule.easyadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by CaoDongping on 3/17/16.
 */
public abstract class MultiTypeAdapter extends BaseAdapter {
    private List<TypedValue> all;
    private Map<Object, Class<? extends ViewSupplier>> types;
    private Context context;

    public MultiTypeAdapter(Context context) {
        this.context = context;
        this.all = new ArrayList<>();
        this.types = new HashMap<>();
        registerTypes();
    }

    protected void registerType(Object type, Class<? extends ViewSupplier> viewSupplier) {
        this.types.put(type, viewSupplier);
    }

    @Override
    public int getCount() {
        return all.size();
    }

    @Override
    public int getItemViewType(int position) {
        Set<Object> keys = types.keySet();
        int i = 0;
        Object type = getItem(position).getType();
        for (Object key : keys) {
            if (key.equals(type)) {
                return i;
            }
            i++;
        }
        throw new IllegalAccessError(String.format("type not registered [%s]", type.toString()));
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
            ViewSupplier<? extends TypedValue> viewSupplier = getViewSupplier(context, all.get(position).getType());
            convertView = viewSupplier.getView();
            convertView.setTag(viewSupplier);
        }
        ViewSupplier<TypedValue> viewSupplier = (ViewSupplier<TypedValue>) convertView.getTag();
        viewSupplier.render(position, getItem(position));
        return convertView;
    }

    @SuppressWarnings("unchecked")
    private ViewSupplier<? extends TypedValue> getViewSupplier(Context context, Object type) {
        Class<? extends ViewSupplier> cls = types.get(type);
        ViewSupplier<? extends TypedValue> viewSupplier;
        try {
            viewSupplier = cls.newInstance();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IllegalAccessError("error on instantiation class " + cls.toString());
        }
        viewSupplier.inflateView(context).bind();
        return viewSupplier;
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
}
