package com.khotiun.android.fandroidvktest.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.khotiun.android.fandroidvktest.model.view.BaseViewModel;

/**
 * Created by hotun on 08.10.2017.
 */

public abstract class BaseViewHolder<Item extends BaseViewModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    //будет использоваться для заполнения макетов данными с модели Item
    public abstract void bindViewHolder(Item item);
    //для разгрузки макета когда он не используется
    public abstract void unbindViewHolder();

}
