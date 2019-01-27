package com.valora.memo.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.valora.memo.R;
import com.valora.memo.model.Set;

import java.util.List;

public class SetAdapter extends BaseItemDraggableAdapter<Set, BaseViewHolder> {
    public SetAdapter(List<Set> data) {
        super(R.layout.item_set, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Set item) {
        helper.setText(R.id.tvName, item.getName());
    }
}
