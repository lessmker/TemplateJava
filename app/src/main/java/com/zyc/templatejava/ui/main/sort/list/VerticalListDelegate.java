package com.zyc.templatejava.ui.main.sort.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zyc.core.delegates.KerDelegate;
import com.zyc.core.net.RestClient;
import com.zyc.core.net.callback.ISuccess;
import com.zyc.core.ui.recycler.MultipleItemEntity;
import com.zyc.templatejava.R;
import com.zyc.templatejava.R2;
import com.zyc.templatejava.ui.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 22:57
 * @Description: 分类菜单页
 */
public class VerticalListDelegate extends KerDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        mRecyclerView = $(R.id.rv_vertical_menu_list);
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getData();
    }

    private void getData() {
        RestClient.builder()
                .url("sort_list")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
