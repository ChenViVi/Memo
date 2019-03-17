package com.valora.memo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.valora.memo.R;
import com.valora.memo.Tool;
import com.valora.memo.model.Question;
import com.valora.memo.model.Set;

public class EditQuestionActivity extends BaseActivity {

    private boolean isEdit;
    private Set set;
    private Question question;
    private EditText etContent;
    private EditText etAnswer;

    @Override
    protected int onBindView() {
        return R.layout.activity_edit_question;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackBtn(true);
        etContent = findViewById(R.id.etContent);
        etAnswer = findViewById(R.id.etAnswer);
        //int count = getIntent().getIntExtra("count", -1);
        question = (Question) getIntent().getSerializableExtra("question");
        /*if (count != -1) {
            isEdit = true;
            question = getQuestions().get(count);
            etContent.setText(question.getContent());
            etAnswer.setText(question.getAnswer());
        }*/
        if (question != null) {
            isEdit = true;
            etContent.setText(question.getContent());
            etAnswer.setText(question.getAnswer());
            setTitle(R.string.ttEditQuestion);
        }
        else {
            set = (Set) getIntent().getSerializableExtra("set");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_confirm) {
            String content = etContent.getText().toString();
            String answer = etAnswer.getText().toString();
            if (Tool.isEmpty(content) || Tool.isEmpty(answer)) {
                toast(R.string.tsEmpty);
            }
            else {
                if (isEdit) {
                    question.setContent(content);
                    question.setAnswer(answer);
                    getDaoSession().getQuestionDao().update(question);
                    toast(R.string.tsEdit);
                }
                else {
                    Question question = new Question();
                    question.setSetId(set.getId());
                    question.setContent(content);
                    question.setAnswer(answer);
                    getDaoSession().getQuestionDao().insert(question);
                    toast(R.string.tsAdd);
                }
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
