package com.github.mzule.easyadapter.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.mzule.easyadapter.ViewType;
import com.github.mzule.easyadapter.recycler.SingleTypeAdapter;
import com.github.mzule.easyadapter.sample.viewtypes.TipViewType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CaoDongping on 3/17/16.
 */
public class SingleTypeListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_type_list);

        PlainAdapter adapter = new PlainAdapter(this);

        RecyclerView listView = (RecyclerView) findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);

        List<String> fake = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fake.add(UUID.randomUUID().toString());
        }
        adapter.addAndNotify(fake);
    }
}

class PlainAdapter extends SingleTypeAdapter<String> {

    public PlainAdapter(Context context) {
        super(context);
    }

    @Override
    protected Class<? extends ViewType> singleViewType() {
        return TipViewType.class;
    }
}
