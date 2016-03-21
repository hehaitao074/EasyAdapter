package com.github.mzule.easyadapter.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.github.mzule.easyadapter.SingleTypeAdapter;
import com.github.mzule.easyadapter.ViewSupplier;
import com.github.mzule.easyadapter.sample.viewsupplier.TipViewSupplier;

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

        ListView listView = (ListView) findViewById(R.id.listView);
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
    protected Class<? extends ViewSupplier> singleViewSupplierType() {
        return TipViewSupplier.class;
    }
}
