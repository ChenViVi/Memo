package com.valora.memo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.valora.memo.R;
import com.valora.memo.Tool;
import com.valora.memo.model.Question;
import com.valora.memo.model.QuestionDao;

import java.util.List;

public class ReviewActivity extends BaseActivity {

    private int count = 0;
    /*private List<Question> questions;*/
    private TextView tvContent;
    private TextView tvShowAnswer;
    private TextView tvAnswer;
    private TextView tvFinish;
    private ScrollView svReview;
    private LinearLayout llBottom;
    private Question question;


    @Override
    protected int onBindView() {
        return R.layout.activity_review;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackBtn(true);
        /*questions = (List<Question>) getIntent().getSerializableExtra("questions");*/
        tvContent = findViewById(R.id.tvContent);
        tvShowAnswer = findViewById(R.id.tvShowAnswer);
        tvAnswer = findViewById(R.id.tvAnswer);
        tvFinish = findViewById(R.id.tvFinish);
        svReview = findViewById(R.id.svReview);
        llBottom = findViewById(R.id.llBottom);
        findViewById(R.id.btnLevel0);
        findViewById(R.id.btnLevel1);
        findViewById(R.id.btnLevel2);
        findViewById(R.id.btnLevel3);
        findViewById(R.id.btnLevel4);
        findViewById(R.id.btnLevel5);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //questions = getDaoSession().getQuestionDao().queryBuilder().where(QuestionDao.Properties.SetId.eq(setId)).orderDesc(QuestionDao.Properties.Frequency).list();
        //questions = getDaoSession().getQuestionDao().queryBuilder().where(QuestionDao.Properties.SetId.eq(setId)).list();
        question = getQuestions().get(count);
        /*Question tmpQuestion = getDaoSession().getQuestionDao().queryBuilder().where(QuestionDao.Properties.Id.eq(question.getId())).unique();
        question.setContent(tmpQuestion.getContent());
        question.setAnswer(tmpQuestion.getAnswer());*/
        //tvContent.setText(question.getContent() + " " + question.getFrequency());
        tvContent.setText(question.getContent());
        tvAnswer.setText(question.getAnswer());
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvShowAnswer:
                showAnswer(true);
                break;
            case R.id.btnLevel0:
                showNext(0);
                break;
            case R.id.btnLevel1:
                showNext(1);
                break;
            case R.id.btnLevel2:
                showNext(2);
                break;
            case R.id.btnLevel3:
                showNext(3);
                break;
            case R.id.btnLevel4:
                showNext(4);
                break;
            case R.id.btnLevel5:
                showNext(5);
                break;
            case R.id.tvFinish:
                /*count = 0;
                //questions = getDaoSession().getQuestionDao().queryBuilder().where(QuestionDao.Properties.SetId.eq(setId)).orderDesc(QuestionDao.Properties.Frequency).list();
                questions = getDaoSession().getQuestionDao().queryBuilder().where(QuestionDao.Properties.SetId.eq(setId)).list();
                question = questions.get(count);
                //tvContent.setText(question.getContent() + " " + question.getFrequency());
                tvContent.setText(question.getContent());
                tvAnswer.setText(question.getAnswer());
                showFinish(false);*/
                break;
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
            /*questions = getDaoSession().getQuestionDao().queryBuilder().where(QuestionDao.Properties.SetId.eq(setId)).list();*/
            getQuestions().remove(question);
            if (getQuestions().size() == 0) {
                toast(R.string.tsDelete);
                finish();
            }
            else {
                if (count == getQuestions().size())
                    question = getQuestions().get(--count);
                else if (count > 0)
                    question = getQuestions().get(++count);
                else if (count == 0)
                    question = getQuestions().get(0);
                //tvContent.setText(question.getContent() + " " + question.getFrequency());
                tvContent.setText(question.getContent());
                tvAnswer.setText(question.getAnswer());
                toast(R.string.tsDelete);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAnswer(boolean enable){
        if (enable) {
            tvAnswer.setVisibility(View.VISIBLE);
            tvShowAnswer.setVisibility(View.GONE);
            llBottom.setVisibility(View.VISIBLE);
        }
        else {
            tvAnswer.setVisibility(View.GONE);
            tvShowAnswer.setVisibility(View.VISIBLE);
            llBottom.setVisibility(View.GONE);
        }
    }

    private void showFinish(boolean enable){
        if (enable) {
            tvFinish.setVisibility(View.VISIBLE);
            llBottom.setVisibility(View.GONE);
            svReview.setVisibility(View.GONE);
        }
        else {
            tvFinish.setVisibility(View.GONE);
            llBottom.setVisibility(View.VISIBLE);
            svReview.setVisibility(View.VISIBLE);
        }
    }

    private void showNext(int q){
        question = Tool.calculateI(question, q);
        getDaoSession().getQuestionDao().update(question);
        if (count >= getQuestions().size() - 1) {
            toast(R.string.tsCompleteQuestion);
            finish();
        }
        else {
            count++;
            //question.setFrequency(question.getFrequency() - frequency);
            question = getQuestions().get(count);
            //tvContent.setText(question.getContent() + " " + question.getFrequency());
            tvContent.setText(question.getContent());
            tvAnswer.setText(question.getAnswer());
            showAnswer(false);
        }
    }
}
