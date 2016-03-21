# EasyAdapter

一种简单的Adapter解决方案，支持多种ViewType，轻松创建`ViewHolder`模式ListView.

## 安装

```
repositories {
    maven {
        url 'https://dl.bintray.com/mzule/maven/'
    }
}

dependencies {
    compile 'com.github.mzule.easyadapter:easyadapter:0.1.2'
}
```

## 说明

项目包含四个类, `ViewType`, `SingleTypeAdapter`, `MultiTypeAdapter`, `TypePerEntityAdapter`, 其中`ViewType`负责创建、绑定(Hold)、渲染View；`SingleTypeAdapter`支持单独一种样式类型的Adapter, `MultiTypeAdapter`, `TypePerEntityAdapter`支持多种样式类型的Adapter；`TypePerEntityAdapter`是`MultiTypeAdapter`的子类。

### 1. ViewType

`ViewType`负责创建、绑定、渲染View，每个`ViewType`对应传统模式下的一个`ViewHolder`，一个典型的`ViewType`实现如下：

```
public class TipViewType extends ViewType<Tip> {
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
`ViewType`提供了一个`findViewById(int)`方法，可以根据声明的类型进行强制转换。

1. `getLayoutResourceId()`负责提供布局文件;
2. `bind()`负责绑定View, 结合`ButterKnife`使用，效果更好，例`ButterKnife.bind(this, getView())`;
3. `render(int, T)`负责渲染UI.

### 2. Adapters

项目提供了3个`Adapter`基类，每个基类都包含了`add(List<T>)`/`addAndNotify(List<T>)`/`clear()`/`clearAndNotify()`四个方法添加和清除`adapter`内的数据，可选是否`notifyDataSetChanged`

#### 1. SingleTypeAdapter

`SingleTypeAdapter`适合仅有一种类型View的`ListView`，典型实现如下：

```
class PlainAdapter extends SingleTypeAdapter<String> {

    public PlainAdapter(Context context) {
        super(context);
    }

    @Override
    protected Class<? extends ViewType> singleViewType() {
        return TipViewType.class;
    }
}
```

#### 2. MultiTypeAdapter

顾名思义，`MultiTypeAdapter`适用于需要在`ListView`上显示多种类型View的时候，比如说微博客户端，一堆微博之间，夹杂几个广告，正好适用。典型实现：

```
// TODO
```

#### 3.TypePerEntityAdapter

`TypePerEntityAdapter`是`MultiTypeAdapter`的子类，适用于每个数据实体class都对应不同的`ViewType`实现，例如:

```
class TimelineAdapter extends TypePerEntityAdapter<Object> {

    public TimelineAdapter(Context context) {
        super(context);
    }

    @Override
    protected void mapEntityViewTypes() {
        mapEntityViewType(Post.class, PostViewType.class);
        mapEntityViewType(Repost.class, RepostViewType.class);
        mapEntityViewType(String.class, TipViewType.class);
        mapEntityViewType(Recommend.class, RecommendViewType.class);
        mapEntityViewType(Ad.class, AdViewType.class);
    }
}
```

## [English]()

## Demo截图

![image](http://7sbl54.com1.z0.glb.clouddn.com/blog_2.pic.jpg?imageView2/0/w/360/)