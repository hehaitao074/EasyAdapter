package com.github.mzule.easyadapter.sample.viewsupplier;

import android.widget.TextView;

import com.github.mzule.easyadapter.ViewSupplier;
import com.github.mzule.easyadapter.sample.R;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class TipViewSupplier extends ViewSupplier<String> {
    private TextView tipView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_tip;
    }

    @Override
    protected void bind() {
        tipView = findViewById(R.id.tip);
    }

    @Override
    public void render(int position, String tip) {
        tipView.setText(tip);
    }
}
