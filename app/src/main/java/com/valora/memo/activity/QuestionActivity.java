package com.valora.memo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.valora.memo.R;
import com.valora.memo.model.Set;

public class QuestionActivity extends BaseActivity {

    private Set set;

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
    }
}
