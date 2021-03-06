# EasyAdapter

一种简单的 Adapter 解决方案，支持多种 ViewType，轻松创建 `ViewHolder` 模式 Adapter. 支持 `ListView` 和 `RecyclerView`.

## 安装
仅支持 `ListView`

``` groovy
dependencies {
    compile 'com.github.mzule.easyadapter:easyadapter:1.1.3'
}
```
需要支持 `RecyclerView`
``` groovy
dependencies {
    compile 'com.github.mzule.easyadapter:easyadapter:1.1.3'
    compile 'com.github.mzule.easyadapter:easyadapterrecycler:1.1.3'
}
```


## 使用步骤

项目包含四个类, `ViewType`, `SingleTypeAdapter`, `MultiTypeAdapter`, `TypePerEntityAdapter`, 其中 `ViewType` 负责创建、绑定( Hold )、渲染View； `SingleTypeAdapter` 支持单独一种样式类型的Adapter, `MultiTypeAdapter`, `TypePerEntityAdapter` 支持多种样式类型的Adapter；`TypePerEntityAdapter` 是 `MultiTypeAdapter` 的子类。

### 1. 编写`ViewType`(s)

`ViewType` 负责创建、绑定、渲染View，每个 `ViewType` 对应传统模式下的一个 `ViewHolder`，一个典型的 `ViewType` 实现如下：

``` java
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
`ViewType` 提供了一个 `findViewById(int)` 方法，可以根据声明的类型进行强制转换。

1. `onCreate` 可以通过调用 `setContentView(int)` 或者 `setContentView(View)` 创建 View，初始化成员变量;
2. `onRender(int, T)` 负责渲染UI，绑定数据.

`ViewType` 还提供了一个 `getAdapter()` 方法直接直接操作 Adapter.以及一个 `isEditMode()` 检查当前是否在编辑模式.

### 2. 选择合适的 `Adapter`

项目为 `ListView` , `RecyclerVIew` 个提供了 3 个 `Adapter` 基类，名字一样，只是包名略作区分，分别是 `com.github.mzule.easyadapter` , `com.github.mzule.easyadapter.recycler` 。下面一一说明。

#### 1. SingleTypeAdapter

`SingleTypeAdapter` 适合仅有一种类型View的 `ListView` ，典型实现如下：

``` java
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

顾名思义，`MultiTypeAdapter` 适用于需要在 `ListView` 上显示多种类型 View 的时候，比如说微博客户端，一堆微博之间，夹杂几个广告，正好适用。典型实现：

``` java
class ArticleAdapter extends MultiTypeAdapter<Article> {

    public ArticleAdapter(Context context) {
        super(context);
    }

    @Override
    protected void registerViewTypes() {
        registerViewType(ArticleBriefViewType.class);
        registerViewType(ArticleFullViewType.class);
    }

    @Override
    protected Class<? extends ViewType> getViewType(int position, Article data) {
        switch (data.getStyle()) {
            case Article.STYLE_FULL:
                return ArticleFullViewType.class;
            case Article.STYLE_BRIEF:
                return ArticleBriefViewType.class;
        }
        return null;
    }
}
```

#### 3.TypePerEntityAdapter

`TypePerEntityAdapter` 是 `MultiTypeAdapter` 的子类，适用于每个数据实体 class 都对应不同的 `ViewType` 实现，例如:

``` java
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

### 3. 应用 `Adapter`
通过 `ListView#setAdapter(Adapter)` 使用 Adapter ,通过 `add(List)` / `addAndNotify(List)` / `clear()` / `clearAndNotify()` 添加或修改Adapter内的数据。`add(List)` 和 `addAndNotify(List)` 的区别在于是否自动调用 `notifyDataSetChanged()` , `clear` 亦然。

``` java
ListView listView = (ListView) findViewById(R.id.listView);
listView.setAdapter(adapter);
List<String> fake = new ArrayList<>();
for (int i = 0; i < 100; i++) {
    fake.add(UUID.randomUUID().toString());
}
adapter.addAndNotify(fake);
```

## 尽情享用
