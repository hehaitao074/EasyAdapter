package com.github.mzule.easyadapter.sample.viewsupplier;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mzule.easyadapter.ViewSupplier;
import com.github.mzule.easyadapter.sample.R;
import com.github.mzule.easyadapter.sample.po.Post;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class PostViewSupplier extends ViewSupplier<Post> {
    private TextView nameView;
    private TextView contentView;
    private ImageView avatarView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_post;
    }

    @Override
    protected void bind() {
        nameView = findViewById(R.id.nameView);
        contentView = findViewById(R.id.contentView);
        avatarView = findViewById(R.id.avatarView);
    }

    @Override
    public void render(int position, Post post) {
        nameView.setText(post.getName());
        contentView.setText(post.getContent());
        Glide.with(getContext()).load(post.getAvatar()).centerCrop().placeholder(R.drawable.placeholder).into(avatarView);
    }
}
