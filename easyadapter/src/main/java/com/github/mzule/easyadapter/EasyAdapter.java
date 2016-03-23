package com.github.mzule.easyadapter;

import java.util.List;

/**
 * Created by CaoDongping on 3/22/16.
 */
public interface EasyAdapter<T> {

    void add(List<? extends T> data);

    void addAndNotify(List<? extends T> data);

    void clear();

    void clearAndNotify();

    List<T> getData();

    /**
     * Retrieve the raw viewType (value returned by `getItemViewType(int)`) at adapter.
     *
     * @param viewType ViewType class
     * @return 0, 1, 2... The raw viewType value.
     */
    int getRawViewType(Class<? extends ViewType> viewType);

    List<Class<? extends ViewType>> getViewTypes();
}
