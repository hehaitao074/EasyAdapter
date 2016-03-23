package com.github.mzule.easyadapter.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.mzule.easyadapter.recycler.TypePerEntityAdapter;
import com.github.mzule.easyadapter.sample.po.Ad;
import com.github.mzule.easyadapter.sample.po.Post;
import com.github.mzule.easyadapter.sample.po.Recommend;
import com.github.mzule.easyadapter.sample.po.Repost;
import com.github.mzule.easyadapter.sample.viewtypes.AdViewType;
import com.github.mzule.easyadapter.sample.viewtypes.PostViewType;
import com.github.mzule.easyadapter.sample.viewtypes.RecommendViewType;
import com.github.mzule.easyadapter.sample.viewtypes.RepostViewType;
import com.github.mzule.easyadapter.sample.viewtypes.TipViewType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by CaoDongping on 3/17/16.
 */
public class TypePerEntityListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_per_entity_list);

        RecyclerView listView = (RecyclerView) findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this));

        TimelineAdapter adapter = new TimelineAdapter(this);
        listView.setAdapter(adapter);

        adapter.addAndNotify(makeFakeData());
    }

    private List<Object> makeFakeData() {
        List<Object> data = new ArrayList<>();
        data.add(new Post("不狂能叫小青年@", "http://t.cn/RGKNTpx", "今天做了一个新项目，感觉真好。"));
        data.add(new Post("对你的依赖早已成（习惯", "http://t.cn/RGru8qh", "四月霸权番（雾）《甲铁城的卡巴内瑞》最新预告公布！讲述在虚构的岛国“日之本”，少年少女和僵尸作战谋生的故事。这真的不是【战国+蒸汽朋克】版的巨人吗"));
        data.add(new Post("奈何桥上吻别", "http://t.cn/RG6bKdx", "来上海出差，出于好奇租了个BYD的秦玩玩，结果到酒店发现，根本没法充电，不过也好，正好感觉一下他的混动系统的工作效率～"));
        data.add(new Post("脸皮厚", "http://t.cn/RGruRtE", "【微软“三屏一云”战略下的Xbox野心】微软依然在推行“三屏一云”的构想，但似乎仍有许多用户不理解，认为微软正在“搬起石头砸自己的脚”，用PC抢占了Xbox的地盘。其实微软根本没有让PC占据Xbox一丝一毫的领地，并且在Xbox擅长的领域，微软正准备利用这台主机实现更大的野心。"));
        data.add(new Post("狐媚", "http://t.cn/RGruEjF", "路人女主的养成方法第二季2017年4月开始播送，圣人惠正宫不可避！"));
        data.add(new Repost("正如其美", "http://t.cn/RGr3b0j", "23333333", (Post) data.get(1)));
        data.add(new Post("籹", "http://t.cn/RGruEFf", "之前周博讲12月机器学习班第10次课"));
        data.add(new Post("星辰", "http://t.cn/RGruntl", "第一个是真的！！第一个！！认准第一个！有认证的那个是山寨的……"));
        data.add(new Post("要疯", "http://t.cn/RGrumcS", "厉害 这都能找到原因 "));
        data.add(new Repost("快到钱里来", "http://t.cn/RGr3cLy", "送你100个赞", (Post) data.get(3)));
        data.add(new Post("赏你", "http://t.cn/RGrumus", "除了下面所述的3.20第20期卷积神经网络之外，3.19第19期：程博士分享矩阵基础  →"));
        data.add(new Post("五指山", "http://t.cn/RGruufV", "本周日上午10点公开课第20期：卷积神经网络。想听加Q群：151888952"));
        data.add(new Post("﹌夏落", "http://t.cn/RGruuE5", "看起来是很体面的道歉，而截图是你删掉原博客。伪君子地，我希望你的公司将来会成功。坦诚地，对于你之前的暴行，我心里由衷希望你的公司走不远。公司名字是：Artand。大家敬而远之吧。"));
        data.add(new Repost("正如其美", "http://t.cn/RGr3b0j", "23333333", (Post) data.get(1)));
        data.add(new Post("卧龙", "http://t.cn/RGru3mj", "「399 美元的售价，是目前已公布售价的 VR 设备里最便宜的，相对 Oculus 的 599 刀和 HTC vive 的 799 刀，索尼的定价可谓业界良心，"));
        data.add(new Post("拿命宠自己", "http://t.cn/RGru1b8", "前一个微博是欢乐一下。其实，我们是多才多艺的。第二张是第一张的临摹。"));
        data.add(new Post("︶ㄣ撕吢", "http://t.cn/RGru1Q4", "前两天BAT中某一家电面题"));
        data.add(new Repost("正如其美", "http://t.cn/RGr3b0j", "23333333", (Post) data.get(1)));
        data.add(new Post("不伦不类", "http://t.cn/RGruBG4", "近日，朝鲜最高法院以阴谋颠覆国家罪，判处美国大学生瓦姆比尔15年劳动教养，不得上诉。据悉，瓦姆比尔于今年元旦参团来平壤旅游，在平壤羊角岛饭店住宿期间，将一幅标语布擅自撤下扔在地上，从而犯下阴谋颠覆国家罪。该犯在听到宣判后当庭大哭，精神接近崩溃。"));
        data.add(new Repost("正如其美", "http://t.cn/RGr3b0j", "23333333", (Post) data.get(1)));
        data.add(new Post("硬汉", "http://t.cn/RGruBTV", "崩下卡拉卡，明晚看小李子的荒野猎人"));
        data.add(new Post("GO awy", "http://t.cn/RGrurZi", "今天群里斗图了…"));
        data.add(new Post("o後", "http://t.cn/RGrurC1", "【美国 1929 年经济危机的根本原因是什么？】Kshir Sagar：大萧条的基本原因是信贷紧缩(credit tightening)，也就是去杠杆(deleverage)。杠杆是万恶之源，贷款的扩张不可能是永恒的，因为世上的信用是有限的"));
        data.add(new Repost("正如其美", "http://t.cn/RGr3b0j", "23333333", (Post) data.get(1)));
        data.add(new Post("莪永遠", "http://t.cn/RGrureb", "我居然被《萝卜报告》的共重号给调戏了…"));
        data.add(new Post("冲动°", "http://t.cn/RGrud0J", "现在的漫画，主角名字都好明显啊"));
        Collections.reverse(data);
        data.add(2, new Ad("http://t.cn/RGre4fv"));
        data.add(6, new Ad("http://t.cn/RGreaLK"));
        data.add(4, new Recommend(new Recommend.User[]{
                new Recommend.User("胡歌", "http://t.cn/RGrkHos"),
                new Recommend.User("王自如", "http://t.cn/RGrkQHN"),
                new Recommend.User("陈凯歌", "http://t.cn/RGrk83D"),
                new Recommend.User("胡军", "http://t.cn/RGrkRRf"),
                new Recommend.User("孙凯", "http://t.cn/RGrkEX7")
        }));
        data.add(12, "上次看到这里");
        return data;
    }
}

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
