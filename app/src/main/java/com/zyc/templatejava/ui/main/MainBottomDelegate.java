package com.zyc.templatejava.ui.main;

import android.graphics.Color;

import com.zyc.core.delegates.bottom.BaseBottomDelegate;
import com.zyc.core.delegates.bottom.BottomItemDelegate;
import com.zyc.core.delegates.bottom.BottomTabBean;
import com.zyc.core.delegates.bottom.ItemBuilder;
import com.zyc.templatejava.ui.main.cart.ShopCartDelegate;
import com.zyc.templatejava.ui.main.discover.DiscoverDelegate;
import com.zyc.templatejava.ui.main.index.IndexDelegate;
import com.zyc.templatejava.ui.main.personal.PersonalDelegate;
import com.zyc.templatejava.ui.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 17:03
 * @Description: 主页
 */
public class MainBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
//        return Color.parseColor("#ffff8800");
        return Color.BLUE;
    }
}
