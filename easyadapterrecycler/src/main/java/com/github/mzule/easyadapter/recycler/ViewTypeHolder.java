package com.github.mzule.easyadapter.recycler;

import android.support.v7.widget.RecyclerView;

import com.github.mzule.easyadapter.ViewType;

/**
 * Created by CaoDongping on 3/22/16.
 */
class ViewTypeHolder<T> extends RecyclerView.ViewHolder {
    ViewType<T> viewType;

    public ViewTypeHolder(ViewType<T> viewType) {
        super(viewType.getView());
        this.viewType = viewType;
    }
}
