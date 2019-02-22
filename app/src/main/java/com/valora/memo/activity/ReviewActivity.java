package com.valora.memo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.valora.memo.R;
import com.valora.memo.model.Question;
import com.valora.memo.model.QuestionDao;

import java.util.List;

public class ReviewActivity extends BaseActivity {

    private int count = 0;
    private List<Question> questions;
    private TextView tvContent;
    private TextView tvShowAnswer;
    private TextView tvAnswer;
    private Question question;
    private Long setId;

    @Override
    protected int onBindView() {
        return R.layout.activity_review;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackBtn(true);
        questions = (List<Question>) getIntent().getSerializableExtra("questions");
        setId = questions.get(0).getSetId();
        tvContent = findViewById(R.id.tvContent);
        tvShowAnswer = findViewById(R.id.tvShowAnswer);
        tvAnswer = findViewById(R.id.tvAnswer);
        findViewById(R.id.btnLevel0);
        findViewById(R.id.btnLevel1);
        findViewById(R.id.btnLevel2);
        findViewById(R.id.btnLevel3);
        count++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        questions = getDaoSession().getQuestionDao().queryBuilder().where(QuestionDao.Properties.SetId.eq(setId)).list();
        showQuestion();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tvShowAnswer:
                showAnswer(true);
                break;
            case R.id.btnLevel0:
                count++;
                showQuestion();
                break;
            case R.id.btnLevel1:
                count++;
                showQuestion();
                break;
            case R.id.btnLevel2:
                count++;
                showQuestion();
                break;
            case R.id.btnLevel3:
                count++;
                showQuestion();
                break;
        }
    }

    private void showQuestion(){
        if (count < questions.size() + 1) {
            question = questions.get(count - 1);
            tvContent.setText(question.getContent());
            tvAnswer.setText(question.getAnswer());
            showAnswer(false);
        }
        else toast(R.string.tsLast);
    }

    private void showAnswer(boolean enable){
        if (enable) {
            tvAnswer.setVisibility(View.VISIBLE);
            tvShowAnswer.setVisibility(View.GONE);
        }
        else {
            tvAnswer.setVisibility(View.GONE);
            tvShowAnswer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(activity, EditQuestionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("question", question);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.action_delete) {
            getDaoSession().getQuestionDao().delete(question);
            questions = getDaoSession().getQuestionDao().queryBuilder().where(QuestionDao.Properties.SetId.eq(setId)).list();
            if (questions.size() == 0) {
                toast(R.string.tsNoneQuestion);
                finish();
            }
            else {
                if (count > 0) count--;
                question = questions.get(count);
                showQuestion();
                toast(R.string.tsDelete);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
