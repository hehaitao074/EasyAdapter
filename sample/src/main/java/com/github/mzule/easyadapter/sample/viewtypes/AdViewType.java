package com.github.mzule.easyadapter.sample.viewtypes;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.mzule.easyadapter.ViewType;
import com.github.mzule.easyadapter.sample.R;
import com.github.mzule.easyadapter.sample.po.Ad;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class AdViewType extends ViewType<Ad> {
    private ImageView adView;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_ad);
        this.adView = findViewById(R.id.adView);
    }

    @Override
    public void onRender(int position, Ad ad) {
        Glide.with(getContext()).load(ad.getImageUrl()).centerCrop().into(adView);
    }
}
