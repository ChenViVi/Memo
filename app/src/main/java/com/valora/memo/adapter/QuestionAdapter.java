package com.valora.memo.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.valora.memo.R;
import com.valora.memo.model.Question;
import com.valora.memo.model.Set;

import java.util.List;

public class QuestionAdapter extends BaseItemDraggableAdapter<Question, BaseViewHolder> {
    public QuestionAdapter(List<Question> data) {
        super(R.layout.item_question, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Question item) {
        helper.setText(R.id.tvName, item.getContent());
    }
}
