package com.zyc.templatejava.ui.main.index;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.zyc.core.delegates.bottom.BottomItemDelegate;
import com.zyc.core.ui.recycler.BaseDecoration;
import com.zyc.core.ui.refesh.RefreshHandler;
import com.zyc.templatejava.R;
import com.zyc.templatejava.R2;
import com.zyc.templatejava.ui.main.MainBottomDelegate;

import butterknife.BindView;

/**
 * @Author: zyc
 * @CreateDate: 2019/8/6 17:06
 * @Description: 首页
 */
public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,
                mRecyclerView, new IndexDataConverter());
//        getIndexData();
    }

    @SuppressLint("HandlerLeak")
    private void getIndexData() {
        index_data indexData = new index_data("index_data");
        indexData.dataHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.arg1 == 0) {
                    final IndexDataConverter converter = new IndexDataConverter();
                    converter.setJsonData((String) msg.obj);
                    converter.convert();
                } else {
                    Toast.makeText(getContext(), "数据访问失败", Toast.LENGTH_LONG).show();
                }
            }
        };
        indexData.RunDataAsync();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        final Context context = getContext();
        mRecyclerView.setLayoutManager(manager);
        if (context != null) {
            mRecyclerView.addItemDecoration
                    (BaseDecoration.create(ContextCompat.getColor(context, R.color.app_background), 5));
        }
        final MainBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index_data");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
