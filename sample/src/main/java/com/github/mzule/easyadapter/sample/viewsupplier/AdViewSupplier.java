package com.github.mzule.easyadapter.sample.viewsupplier;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.mzule.easyadapter.ViewSupplier;
import com.github.mzule.easyadapter.sample.R;
import com.github.mzule.easyadapter.sample.po.Ad;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class AdViewSupplier extends ViewSupplier<Ad> {
    private ImageView adView;

    public AdViewSupplier(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_ad;
    }

    @Override
    protected void bind() {
        adView = findViewById(R.id.adView);
    }

    @Override
    public void render(int position, Ad ad) {
        Glide.with(getContext()).load(ad.getImageUrl()).centerCrop().into(adView);
    }
}
