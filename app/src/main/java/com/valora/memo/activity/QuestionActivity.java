package com.valora.memo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.valora.memo.R;
import com.valora.memo.Tool;
import com.valora.memo.model.Question;
import com.valora.memo.model.QuestionDao;
import com.valora.memo.model.Set;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Iterator;

public class QuestionActivity extends BaseActivity {

    private Set set;
    private TextView tvCount;
    private TextView tvReview;

    @Override
    protected int onBindView() {
        return R.layout.activity_question;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackBtn(true);
        set = (Set) getIntent().getSerializableExtra("set");
        setTitle(set.getName());
        tvCount = findViewById(R.id.tvCount);
        tvReview = findViewById(R.id.tvReview);
        findViewById(R.id.btnStart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        genQuestionsBySetId(set.getId());
        Iterator<Question> iterator = getQuestions().iterator();
        Long todayZeroPointTimestamps = Tool.getTodayZeroPointTimestamps();
        while(iterator.hasNext()){
            Question question = iterator.next();
            if(question.getNextTime() > todayZeroPointTimestamps) {
                iterator.remove();
            }
        }
        int unFinishCount = getQuestions().size();
        int finishCount = getDaoSession().getQuestionDao().queryBuilder()
                .where(QuestionDao.Properties.ReviewTime.eq(todayZeroPointTimestamps),QuestionDao.Properties.SetId.eq(set.getId())).list().size();
        tvCount.setText(String.valueOf(unFinishCount + finishCount));
        tvReview.setText(String.valueOf(finishCount));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if(view.getId() == R.id.btnStart) {
            if (getQuestions().size() == 0)
                toast(R.string.tsNoneQuestion);
            else {
                Intent intent = new Intent(activity, ReviewActivity.class);
                intent.putExtra("setId",set.getId());
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(activity, EditQuestionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("set", set);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
