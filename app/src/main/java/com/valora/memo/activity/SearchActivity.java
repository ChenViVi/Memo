package com.valora.memo.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.valora.memo.R;
import com.valora.memo.adapter.QuestionAdapter;
import com.valora.memo.model.Question;
import com.valora.memo.model.QuestionDao;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {

    private List<Question> questions = new ArrayList<>();
    private QuestionAdapter adapter;
    private RecyclerView rvQuestion;
    private  String query;

    @Override
    protected int onBindView() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackBtn(true);
        query = getIntent().getStringExtra("query");
        adapter = new QuestionAdapter(questions);
        rvQuestion = findViewById(R.id.rvQuestion);
        rvQuestion.setLayoutManager(new LinearLayoutManager(activity));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(activity, EditQuestionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("question", questions.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rvQuestion);
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
                getDaoSession().getQuestionDao().delete(questions.get(pos));
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
        rvQuestion.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        questions.clear();
        questions.addAll(getDaoSession().getQuestionDao().queryBuilder().where(QuestionDao.Properties.Content.like("%" + query + "%")).list());
        adapter.notifyDataSetChanged();
    }
}
