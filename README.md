# EasyAdapter

一种简单的Adapter解决方案，支持多种ViewType，轻松创建`ViewHolder`模式Adapter.

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

## 使用步骤

项目包含四个类, `ViewType`, `SingleTypeAdapter`, `MultiTypeAdapter`, `TypePerEntityAdapter`, 其中`ViewType`负责创建、绑定(Hold)、渲染View；`SingleTypeAdapter`支持单独一种样式类型的Adapter, `MultiTypeAdapter`, `TypePerEntityAdapter`支持多种样式类型的Adapter；`TypePerEntityAdapter`是`MultiTypeAdapter`的子类。

### 1. 编写`ViewType`(s)

`ViewType`负责创建、绑定、渲染View，每个`ViewType`对应传统模式下的一个`ViewHolder`，一个典型的`ViewType`实现如下：

```
public class TipViewType extends ViewType<String> {
    private TextView tipView;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_tip);
        this.tipView = findViewById(R.id.tip);
    }

    @Override
    public void onRender(int position, String tip) {
        tipView.setText(tip);
    }
}
```
`ViewType`提供了一个`findViewById(int)`方法，可以根据声明的类型进行强制转换。

1. `onCreate`可以通过调用`setContentView(int)`或者`setContentView(View)`创建View，初始化成员变量;
2. `onRender(int, T)`负责渲染UI，绑定数据.

### 2. 选择合适的`Adapter`

项目提供了3个`Adapter`基类，下面一一说明。

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

### 3. 应用`Adapter`
通过`ListView#setAdapter(Adapter)`使用Adapter,通过`add(List)`/`addAndNotify(List)`/`clear()`/`clearAndNotify()`添加或修改Adapter内的数据。`add(List)`和`addAndNotify(List)`的区别在于是否自动调用`notifyDataSetChanged()`, `clear`亦然。

```
ListView listView = (ListView) findViewById(R.id.listView);
listView.setAdapter(adapter);
List<String> fake = new ArrayList<>();
for (int i = 0; i < 100; i++) {
    fake.add(UUID.randomUUID().toString());
}
adapter.addAndNotify(fake);
```

## [English]()

## Demo截图

![image](http://7sbl54.com1.z0.glb.clouddn.com/blog_2.pic.jpg?imageView2/0/w/360/)