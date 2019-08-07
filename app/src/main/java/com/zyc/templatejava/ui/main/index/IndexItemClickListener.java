package com.zyc.templatejava.ui.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.zyc.core.delegates.KerDelegate;
import com.zyc.templatejava.ui.main.detail.GoodsDetailDelegate;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 22:31
 * @Description:
 */
public class IndexItemClickListener extends SimpleClickListener {
    private final KerDelegate DELEGATE;

    private IndexItemClickListener(KerDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(KerDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
//        final int goodsId = entity.getField(MultipleFields.ID);
//        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
//        DELEGATE.getSupportDelegate().start(delegate);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create();
        DELEGATE.start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}

