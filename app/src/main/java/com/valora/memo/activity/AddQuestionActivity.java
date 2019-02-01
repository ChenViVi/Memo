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

public class AddQuestionActivity extends BaseActivity {

    private Set set;
    private EditText etContent;
    private EditText etAnswer;

    @Override
    protected int onBindView() {
        return R.layout.activity_add_question;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        set = (Set) getIntent().getSerializableExtra("set");
        etContent = findViewById(R.id.etContent);
        etAnswer = findViewById(R.id.etAnswer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_question, menu);
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
                Question question = new Question();
                question.setSetId(set.getId());
                question.setContent(content);
                question.setAnswer(answer);
                getDaoSession().getQuestionDao().insert(question);
                toast(R.string.tsAddSuccess);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
