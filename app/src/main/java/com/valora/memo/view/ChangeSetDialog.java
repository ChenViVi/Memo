package com.valora.memo.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.valora.memo.App;
import com.valora.memo.R;
import com.valora.memo.model.DaoSession;
import com.valora.memo.model.Set;
import com.valora.memo.Tool;
import com.valora.memo.model.SetDao;

public abstract class ChangeSetDialog extends Dialog {

    private Activity activity;
    private Set set;
    private boolean isUpdate = false;

    public ChangeSetDialog(@NonNull Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public ChangeSetDialog(@NonNull Activity activity, Set set) {
        super(activity);
        this.activity = activity;
        this.set = set;
        isUpdate = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_set);
        final EditText etName = findViewById(R.id.etName);
        TextView tvChange = findViewById(R.id.tvChange);
        TextView tvEditSet = findViewById(R.id.tvEditSet);
        if (isUpdate) {
            etName.setText(set.getName());
            tvEditSet.setText(R.string.ttEditSet);
        }
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                if (Tool.isEmpty(name))
                    Toast.makeText(getContext(), "名称不可为空", Toast.LENGTH_SHORT).show();
                else {
                    DaoSession daoSession = ((App) activity.getApplication()).getDaoSession();
                    SetDao setDao = daoSession.getSetDao();
                    if (isUpdate) {
                        set.setName(name);
                        setDao.update(set);
                    }
                    else {
                        Set set = new Set();
                        set.setName(name);
                        setDao.insert(set);
                    }
                    dismiss();
                    onChanged();
                }
            }
        });
    }

    protected abstract void onChanged();
}
