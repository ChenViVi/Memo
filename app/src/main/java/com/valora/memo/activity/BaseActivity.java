package com.valora.memo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.valora.memo.App;
import com.valora.memo.R;
import com.valora.memo.model.DaoSession;
import com.valora.memo.model.Question;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected BaseActivity activity;
    protected View rootView;
    protected Toolbar toolbar;

    /**
     *  设置布局文件的 ResourceId
     */
    protected abstract int onBindView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        rootView = View.inflate(activity, onBindView(), null);
        toolbar = findViewById(R.id.toolbar);
        setContentView(rootView);
        setSupportActionBar(toolbar);
    }

    /**
     *  设置 ToolBar 上显示的标题
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (toolbar != null){
            toolbar.setTitle(title);
        }
    }

    /**
     *  使用 string.xml 设置 ToolBar 上显示的标题
     */
    @Override
    public void setTitle(int title) {
        super.setTitle(title);
        if (toolbar != null){
            toolbar.setTitle(title);
        }
    }

    /**
     * ToolBar 上是否有返回按钮
     */
    protected void enableBackBtn(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }

    protected void toast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int id) {
        Toast.makeText(activity, getResources().getString(id), Toast.LENGTH_SHORT).show();
    }

    protected void debug() {
        Toast.makeText(activity, "debug", Toast.LENGTH_SHORT).show();
    }

    /**
     *  不带任何参数跳转到另一个 activity 界面
     */
    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(activity, cls));
    }

    /**
     * 这里重写 findViewById 使得 所有 view 的 点击事件都绑定到 Activity 的 onClick 方法中
     */
    public  <T extends View> T  findViewById(int id) {
        T view = rootView.findViewById(id);
        if (!(rootView.findViewById(id) instanceof AdapterView)) {
            view.setOnClickListener(this);
        }
        return view;
    }

    public void genQuestionsBySetId(long setId){
        ((App)getApplication()).genQuestionsBySetId(setId);
    }

    public List<Question> getQuestions(){
        return ((App)getApplication()).getQuestions();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public DaoSession getDaoSession() {
        return ((App) getApplication()).getDaoSession();
    }
}
