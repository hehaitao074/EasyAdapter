package com.github.mzule.easyadapter.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.github.mzule.easyadapter.MultiTypeAdapter;
import com.github.mzule.easyadapter.ViewType;
import com.github.mzule.easyadapter.sample.po.Article;
import com.github.mzule.easyadapter.sample.viewtypes.ArticleBriefViewType;
import com.github.mzule.easyadapter.sample.viewtypes.ArticleFullViewType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoDongping on 3/18/16.
 */
public class MultiTypeListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type_list);

        ArticleAdapter adapter = new ArticleAdapter(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        adapter.addAndNotify(makeFakeData());
    }

    @NonNull
    private List<Article> makeFakeData() {
        List<Article> fake = new ArrayList<>();
        fake.add(new Article("http://t.cn/RGk1oGN", "中国电商巨人打算进击印度？先听听他们的总裁怎么说\n" +
                "\n" +
                "他们选择印度，是因为“商业基因”相同？", Article.STYLE_BRIEF));
        fake.add(new Article("http://t.cn/RGk1RjJ", "备忘录：新增密码保护、条件排序和印象笔记导入功能\n" +
                "\n" +
                "本次 OS X 10.11.4 中，备忘录增加了密码保护功能。用户能够在备忘录中设置密码来保护内部信息。同时，它也增加了按照字母、创建日期、修改日期等条件排序更能，用户寻找起来会更加方便。", Article.STYLE_FULL));
        fake.add(new Article("http://t.cn/RGk1K6n", "有人说，苹果新品会叫 iPad mini Pro Plus | 一周范评\n" +
                "\n" +
                "一周评论精华汇总，不看真的会后悔。", Article.STYLE_BRIEF));
        fake.add(new Article("http://t.cn/RGk19JQ", "无人机“差点”撞上大飞机，这些人是怎么做到的\n" +
                "\n" +
                "“聪明”的人们总会通过各种方式让自己爽，但是等待着他们的或许是法律的制裁", Article.STYLE_BRIEF));
        fake.add(new Article("http://t.cn/RGk1mA3", "口袋妖怪 Go》由 The Pokémon Company, 任天堂以及 Niantic 联手推出，是一款基于 GPS 的增强现实游戏，去年披露后就被寄予厚望。它将于 2016 年内登陆 iOS 和 Android 端，可免费下载，但有应用内购买项目。\n" +
                "\n" +
                "目前，测试玩家限定于日本用户，因此注册需要使用具有日本 IP 的 VPN。注册网址是：http://beta-access.com/pokemongobeta/。", Article.STYLE_FULL));
        fake.add(new Article("http://t.cn/RGk1NJl", "机器人要来抢你的饭碗了？赶紧穿上这套装备击退它们\n" +
                "\n" +
                "但是，人类未必要和机器人站在水火不容的对立面上，两者相辅相成或者还会形成一种新的生产效果。", Article.STYLE_BRIEF));
        fake.add(new Article("http://t.cn/RGk1pLk", "我一周戴三个表，告诉你 Apple Watch 和专业运动手表有什么区别\n" +
                "\n" +
                "春天到了，是时候为身上的赘肉担忧了。", Article.STYLE_BRIEF));
        fake.add(new Article("http://t.cn/RGk13Yw", "F.lux 是一款多平台、免费的软件，特点在于到了晚上，它会自动帮你调整电脑屏幕的颜色，减少屏幕对眼睛的刺激，帮助人改善睡眠质量。\n" +
                "\n" +
                "一直以来，F.lux 寻求它的 iOS app 可以正常地在 App Store 上架的机会。实际上，如果去搜索社交网络，也会看到大量用户呼吁 F.lux 可以尽快发布 iOS 版本。\n" +
                "\n" +
                "的确让人感到奇怪，iOS 都已经发布那么久了，以 F.lux 团队的技术实力，拖再久，iOS 版的 app 至少也该有眉目了吧。\n" +
                "\n" +
                "在 2015 年 11 月，F.lux 最终利用苹果的开发工具 Xcode 的一个 API 接口，实现发布 iOS 版本的目标。然而，和其它的软件不一样的是，团队要求客户下载 Xcode 才可以安装试用版。\n" +
                "\n" +
                "得被逼到什么程度，开发者才会用非正常地方式，来邀请用户试用自己的产品？\n" +
                "\n" +
                "但更让人难以想象的是，苹果是会如此地“关照”F.lux——第二天，苹果强制 F.lux 下架，理由是公司非法利用了 Xcode 的接口。", Article.STYLE_FULL));
        fake.add(new Article("http://t.cn/RGk104s", "这款 2048 里，你的任务是盖起一座城市 - City 2048 #iOS #Android\n" +
                "\n" +
                "它在原本 2048 的基础上加入了很多新的游戏元素，你化身成为一个市长，任务则是建立越来越多的住房和越来越高的高楼。", Article.STYLE_BRIEF));
        fake.add(new Article("http://t.cn/RGk1r45", "爱范儿在 3 月 14 日报道过，小米将会联合骑记 iRiding 推出一款公路自行车 QiCYCLE。今天，这款公路自行车正式在小米公司官方微博、骑记 iRiding 官方微博中亮相，同时也公布了它的具体配置和价格。", Article.STYLE_FULL));
        return fake;
    }
}

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
