package com.valora.memo.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.valora.memo.App;
import com.valora.memo.DaoSession;
import com.valora.memo.R;
import com.valora.memo.model.Set;
import com.valora.memo.adapter.SetAdapter;
import com.valora.memo.SetDao;
import com.valora.memo.view.ChangeSetDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView rvSet;
    private SetAdapter adapter;
    private SetDao setDao;
    private List<Set> sets = new ArrayList<>();

    @Override
    protected int onBindView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvSet = findViewById(R.id.rvSet);
        findViewById(R.id.btnAdd);
        rvSet.setLayoutManager(new LinearLayoutManager(activity));
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        setDao = daoSession.getSetDao();
        sets.addAll(setDao.loadAll());
        adapter = new SetAdapter(sets);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rvSet);
        adapter.enableSwipeItem();
        adapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                setDao.delete(sets.get(pos));
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                new ChangeSetDialog(activity, sets.get(position)) {
                    @Override
                    protected void onChanged() {
                        sets.clear();
                        sets.addAll(setDao.loadAll());
                        adapter.notifyDataSetChanged();
                    }
                }.show();
                return false;
            }
        });
        rvSet.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnAdd:
                new ChangeSetDialog(activity) {
                    @Override
                    protected void onChanged() {
                        sets.clear();
                        sets.addAll(setDao.loadAll());
                        adapter.notifyDataSetChanged();
                    }
                }.show();
                break;
        }
    }
}
