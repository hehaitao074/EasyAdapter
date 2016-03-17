package com.github.mzule.easyadapter.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mzule.easyadapter.MultiTypeAdapter;
import com.github.mzule.easyadapter.TypedValue;
import com.github.mzule.easyadapter.TypedValueConverter;
import com.github.mzule.easyadapter.TypedValueWrapper;
import com.github.mzule.easyadapter.ViewSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CaoDongping on 3/17/16.
 */
public class MultiTypeListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type_list);

        ListView listView = (ListView) findViewById(R.id.listView);

        MultiAdapter adapter = new MultiAdapter(this);
        listView.setAdapter(adapter);

        List<String> fake = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fake.add(UUID.randomUUID().toString());
        }

        adapter.addAndNotify(fake, new TypedValueConverter() {
            @Override
            public TypedValueWrapper convert(final Object obj) {
                return new TypedValueWrapper(obj) {
                    @Override
                    public String getType() {
                        String raw = (String) obj;
                        char c = raw.charAt(0);
                        return c >= '0' && c <= '9' ? "number" : "alphabet";
                    }
                };
            }
        });
    }
}

class MultiAdapter extends MultiTypeAdapter {

    public MultiAdapter(Context context) {
        super(context);
    }

    @Override
    protected void registerTypes() {
        registerType("number", "alphabet");
    }

    @Override
    protected ViewSupplier<TypedValue> createViewSupplier(Context context, int position, ViewGroup parent, String type) {
        switch (type) {
            case "number":
                return new ViewSupplier<TypedValue>(context) {
                    private TextView textView;

                    @Override
                    protected int getLayoutResourceId() {
                        return R.layout.item_number;
                    }

                    @Override
                    protected void bind() {
                        this.textView = findViewById(R.id.textView);
                    }

                    @Override
                    public void render(int position, TypedValue data) {
                        textView.setText(((TypedValueWrapper) data).getRaw().toString());
                    }
                };
            case "alphabet":
                return new ViewSupplier<TypedValue>(context) {
                    private TextView textView;

                    @Override
                    protected int getLayoutResourceId() {
                        return R.layout.item_alphabet;
                    }

                    @Override
                    protected void bind() {
                        this.textView = findViewById(R.id.textView);
                    }

                    @Override
                    public void render(int position, TypedValue data) {
                        textView.setText(((TypedValueWrapper) data).getRaw().toString());
                    }
                };
            default:
                break;
        }
        return null;
    }
}
