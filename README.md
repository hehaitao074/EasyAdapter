# EasyAdapter

一种简单的Adapter解决方案，支持多种ViewType，轻松创建可复用的ListView.

## 安装

```
dependencies {
}
```

## 说明

项目包含四个类, `ViewSupplier`, `SingleTypeAdapter`, `MultiTypeAdapter`, `TypePerEntityAdapter`, 其中`ViewSupplier`负责创建、绑定(Hold)、渲染View；`SingleTypeAdapter`支持单独一种样式类型的Adapter, `MultiTypeAdapter`, `TypePerEntityAdapter`支持多种样式类型的Adapter；`TypePerEntityAdapter`是`MultiTypeAdapter`的子类。

### 1. ViewSupplier

`ViewSupplier`负责创建、绑定、渲染View，每个`ViewSupplier`对应传统模式下的一个`ViewHolder`，一个典型的`ViewSupplier`实现如下：

```
public class TipViewSupplier extends ViewSupplier<Tip> {
    private TextView tipView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_tip;
    }

    @Override
    protected void bind() {
        tipView = findViewById(R.id.tip);
    }

    @Override
    public void render(int position, Tip tip) {
        tipView.setText(tip.getTip());
    }
}
```
`ViewSupplier`提供了一个`findViewById(int)`方法，可以根据声明的类型进行强制转换。

1. `getLayoutResourceId()`负责提供布局文件;
2. `bind()`负责绑定View, 结合ButterKnife使用，效果更好，例`ButterKnife.bind(this, getView())`;
3. `render(int, T)`负责渲染UI.

### 2. SingleTypeAdapter

`SingleTypeAdapter`适合仅有一种类型View的`ListView`，典型实现如下：

```
public class PlainAdapter extends SingleTypeAdapter<String> {

    public PlainAdapter(Context context) {
        super(context);
    }

    @Override
    protected ViewSupplier<String> createViewSupplier(Context context) {
        // return your ViewSupplier instance here.
    }
}
```

### 3. MultiTypeAdapter

顾名思义，`MultiTypeAdapter`适用于需要在`ListView`上显示多种类型View的时候，比如说微博客户端，一堆微博之间，夹杂几个广告，正好适用。典型实现：

```

```

### 4.TypePerEntityAdapter

`TypePerEntityAdapter`是`MultiTypeAdapter`的子类，适用于每个数据实体class都对应不同的ViewSupplier实现，例如:

```
public class TimelineAdapter extends TypePerEntityAdapter<Object> {

    public TimelineAdapter(Context context) {
        super(context);
    }

    @Override
    protected void registerEntityViewSupplierTypes() {
        registerType(Post.class, PostViewSupplier.class);
        registerType(Repost.class, RepostViewSupplier.class);
        registerType(Tip.class, TipViewSupplier.class);
        registerType(Recommend.class, RecommendViewSupplier.class);
        registerType(Ad.class, AdViewSupplier.class);
    }
}
```

## [English]()

## Demo截图

![image](http://7sbl54.com1.z0.glb.clouddn.com/blog_2.pic.jpg?imageView2/0/w/360/)