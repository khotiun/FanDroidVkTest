package com.khotiun.android.fandroidvktest.model.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.ui.holder.BaseViewHolder;

/**
 * Created by hotun on 08.10.2017.
 * когда мы получаем ответ с сервера на уровне макета нам нужны не все данные, а некоторые данные нуждаются в обработке
 * вообщем класс будет ответственный за преобразования данных и создания новых вью холдеров
 */

public abstract class BaseViewModel {

    //для того чтобы отличать модели разного типа
    public abstract LayoutTypes getType();

    public BaseViewHolder createViewHolder(ViewGroup parent) {
        return onCreateViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(getType().getValue(), parent, false));
    }

    //для того что бы переложить ответственность создания ViewHolder на дочерние классы
    protected abstract BaseViewHolder onCreateViewHolder(View view);

    public enum LayoutTypes {
        NewsFeedItemHeader(R.layout.item_news_header),
        NewsFeedItemBody(R.layout.item_news_body),
        NewsFeedItemFooter(R.layout.item_news_footer);

        private final int id;

        LayoutTypes(int resId) {
            this.id = resId;
        }

        @LayoutRes
        public int getValue() {
            return id;
        }
    }

}
