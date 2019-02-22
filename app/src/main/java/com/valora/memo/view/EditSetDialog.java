package com.valora.memo.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.valora.memo.R;
import com.valora.memo.activity.BaseActivity;
import com.valora.memo.model.DaoSession;
import com.valora.memo.model.Set;
import com.valora.memo.Tool;
import com.valora.memo.model.SetDao;

public abstract class EditSetDialog extends Dialog {

    private boolean isEdit = false;
    private BaseActivity activity;
    private Set set;

    public EditSetDialog(@NonNull BaseActivity activity) {
        super(activity);
        this.activity = activity;
    }

    public EditSetDialog(@NonNull BaseActivity activity, Set set) {
        super(activity);
        this.activity = activity;
        this.set = set;
        isEdit = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_set);
        final EditText etName = findViewById(R.id.etName);
        TextView tvEdit = findViewById(R.id.tvEdit);
        TextView tvEditSet = findViewById(R.id.tvEditSet);
        if (isEdit) {
            etName.setText(set.getName());
            tvEditSet.setText(R.string.ttEditSet);
        }
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                if (Tool.isEmpty(name))
                    Toast.makeText(getContext(), activity.getString(R.string.tsEmpty), Toast.LENGTH_SHORT).show();
                else {
                    DaoSession daoSession =  activity.getDaoSession();
                    SetDao setDao = daoSession.getSetDao();
                    if (isEdit) {
                        set.setName(name);
                        setDao.update(set);
                    }
                    else {
                        Set set = new Set();
                        set.setName(name);
                        setDao.insert(set);
                    }
                    Toast.makeText(getContext(), activity.getString(R.string.tsAdd), Toast.LENGTH_SHORT).show();
                    dismiss();
                    onEdited();
                }
            }
        });
    }

    protected abstract void onEdited();
}
