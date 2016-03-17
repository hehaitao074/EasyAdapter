package com.github.mzule.easyadapter.sample.po;

import com.github.mzule.easyadapter.TypedValue;

import java.io.Serializable;

/**
 * Created by CaoDongping on 3/17/16.
 */
public class Tip implements Serializable, TypedValue {
    private String tip;

    public Tip(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public Object getType() {
        return getClass();
    }
}
