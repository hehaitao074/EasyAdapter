package com.github.mzule.easyadapter.sample.viewtypes;

import android.widget.TextView;

import com.github.mzule.easyadapter.ViewType;
import com.github.mzule.easyadapter.sample.R;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class TipViewType extends ViewType<String> {
    private TextView tipView;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_tip);
        this.tipView = findViewById(R.id.tip);
    }

    @Override
    public void onRender(int position, String tip) {
        tipView.setText(tip);
    }
}
