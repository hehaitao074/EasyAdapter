package com.github.mzule.easyadapter.sample.viewtypes;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mzule.easyadapter.ViewType;
import com.github.mzule.easyadapter.sample.R;
import com.github.mzule.easyadapter.sample.po.Article;

/**
 * Created by CaoDongping on 3/22/16.
 */
public class ArticleFullViewType extends ViewType<Article> {
    protected ImageView imageView;
    protected TextView textView;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_article_full);
        this.imageView = findViewById(R.id.imageView);
        this.textView = findViewById(R.id.textView);
    }

    @Override
    public void onRender(int position, Article data) {
        Glide.with(getContext()).load(data.getImageUrl()).centerCrop().placeholder(R.color.gray).into(imageView);
        textView.setText(data.getText());
    }
}
