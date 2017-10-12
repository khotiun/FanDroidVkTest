package com.khotiun.android.fandroidvktest.common;

import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.khotiun.android.fandroidvktest.model.view.BaseViewModel;
import com.khotiun.android.fandroidvktest.ui.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hotun on 08.10.2017.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>> {

    //содержит все добавленные в адаптер переменные, отсюда берутся данные для заполнения макета в onBindViewHolder
    private List<BaseViewModel> list = new ArrayList<>();
    //ключ тип модели и макета, а значение сама модель, нужен для того что бы создавать ViewHolder конкретного типа
    private ArrayMap<Integer, BaseViewModel> mTypeInstance = new ArrayMap<>();


    @Override
    public BaseViewHolder<BaseViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTypeInstance.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<BaseViewModel> holder, int position) {

        holder.bindViewHolder(getItem(position));
    }

    @Override
    public void onViewRecycled(BaseViewHolder<BaseViewModel> holder) {
        super.onViewRecycled(holder);
        holder.unbindViewHolder();
    }

    @Override
    public int getItemViewType(int position) {
         return getItem(position).getType().getValue();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //для возврата item по позиции
    public BaseViewModel getItem(int position) {
        return list.get(position);
    }

    //для добавления элементов в адаптер, при каждом добавлении нужно регестрировать тип лайоута
    public void registerTypeInstance(BaseViewModel item) {
        //если список типов не содержит такой же тип то дабовляем наш тип
        if (!mTypeInstance.containsKey(item.getType().getValue())) {
            mTypeInstance.put(item.getType().getValue(), item);
        }
    }

    //для добовления элементов в список
    public void addItems (List<? extends BaseViewModel> newItems) {
        for (BaseViewModel newItem : newItems) {
            registerTypeInstance(newItem );
        }

        list.addAll(newItems);

        notifyDataSetChanged();
    }

    //метод для замены элементов
    public void setItems(List<BaseViewModel> items) {
        clearList();
        addItems(items);
    }

    private void clearList() {
        list.clear();
    }

    //будет перебирать все элементы списка, проверять является ли элемент реальным и возвращать реальное количество элементов
    public int getRealItemCount() {
        int count = 0;
        for (int i = 0; i < getItemCount(); i++) {
            if (!getItem(i) .isItemDecorator()) {
                count += 1;
            }
        }
        return count;
    }
}
