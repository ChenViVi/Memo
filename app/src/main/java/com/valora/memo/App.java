package com.valora.memo;

import android.app.Application;

import com.valora.memo.model.DaoMaster;
import com.valora.memo.model.DaoSession;
import com.valora.memo.model.Question;
import com.valora.memo.model.QuestionDao;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class App extends Application {

    private DaoSession daoSession;
    private List<Question> questions;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "memo");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public List<Question> genQuestionsBySetId(long setId) {
        questions = daoSession.getQuestionDao().queryBuilder().where(QuestionDao.Properties.SetId.eq(setId)).list();
        return questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
