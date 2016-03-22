package com.github.mzule.easyadapter.sample.viewtypes;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mzule.easyadapter.ViewType;
import com.github.mzule.easyadapter.sample.R;
import com.github.mzule.easyadapter.sample.po.Post;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class PostViewType extends ViewType<Post> {
    private TextView nameView;
    private TextView contentView;
    private ImageView avatarView;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_post);
        nameView = findViewById(R.id.nameView);
        contentView = findViewById(R.id.contentView);
        avatarView = findViewById(R.id.avatarView);
    }

    @Override
    public void onRender(int position, Post post) {
        nameView.setText(post.getName());
        contentView.setText(post.getContent());
        Glide.with(getContext()).load(post.getAvatar()).centerCrop().placeholder(R.drawable.placeholder).into(avatarView);
    }
}
