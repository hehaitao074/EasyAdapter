package com.github.mzule.easyadapter.sample.viewtypes;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mzule.easyadapter.ViewType;
import com.github.mzule.easyadapter.sample.R;
import com.github.mzule.easyadapter.sample.po.Repost;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class RepostViewType extends ViewType<Repost> {
    private TextView nameView;
    private TextView contentView;
    private ImageView avatarView;
    private TextView quoteView;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_repost);
        this.nameView = findViewById(R.id.nameView);
        this.contentView = findViewById(R.id.contentView);
        this.avatarView = findViewById(R.id.avatarView);
        this.quoteView = findViewById(R.id.quoteView);
    }

    @Override
    public void onRender(int position, Repost repost) {
        nameView.setText(repost.getName());
        contentView.setText(repost.getContent());
        quoteView.setText(String.format("%s: %s", repost.getPost().getName(), repost.getPost().getContent()));
        Glide.with(getContext()).load(repost.getAvatar()).centerCrop().placeholder(R.drawable.placeholder).into(avatarView);
    }
}
