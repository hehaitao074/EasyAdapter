package com.github.mzule.easyadapter.sample.viewsupplier;

import android.widget.TextView;

import com.github.mzule.easyadapter.ViewSupplier;
import com.github.mzule.easyadapter.sample.R;
import com.github.mzule.easyadapter.sample.po.Tip;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class TipViewSupplier extends ViewSupplier<Tip> {
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
    public void render(int position, Tip tip) {
        tipView.setText(tip.getTip());
    }
}
