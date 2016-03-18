package com.github.mzule.easyadapter.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.singleTypeButton).setOnClickListener(this);
        findViewById(R.id.multiTypeButton).setOnClickListener(this);
        findViewById(R.id.typePerEntityButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.singleTypeButton:
                intent.setClass(this, SingleTypeListActivity.class);
                break;
            case R.id.multiTypeButton:
                intent.setClass(this, SingleTypeListActivity.class);
                break;
            case R.id.typePerEntityButton:
                intent.setClass(this, TypePerEntityListActivity.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}
