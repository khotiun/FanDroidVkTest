package com.khotiun.android.fandroidvktest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.khotiun.android.fandroidvktest.model.view.BaseViewModel;

import java.util.List;

/**
 * Created by hotun on 12.10.2017.
 * будет отвечать за отображение списков стена, комментарии и т.д.
 */

public interface BaseFeedView extends MvpView {

    //для показа анимации загрузки, когда обновляем список по свайпу вниз
    void showRefreshing();
    //скрытие анимации загрузки
    void hideRefreshing();
    //показ анимации загрузки в центре когда список отображается на экране, запуск приложения с последующим отображением стены
    //при открытии коментариев и при переходе вкладок новигатион дровер
    void showListProgress();
    void hideListProgress();
    //для отображения ошибки
    void showError(String message);
    //замена существующего списка новым
    void setItems(List<BaseViewModel> items);
    //добавления новых элементов списка в конец существующих
    void addItems(List<BaseViewModel> items);
}
