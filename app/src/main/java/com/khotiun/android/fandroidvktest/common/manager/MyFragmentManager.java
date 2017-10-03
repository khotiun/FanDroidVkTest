package com.khotiun.android.fandroidvktest.common.manager;



import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;

import com.khotiun.android.fandroidvktest.ui.activity.BaseActivity;
import com.khotiun.android.fandroidvktest.ui.fragment.BaseFragment;

import java.util.Stack;

/**
 * Created by hotun on 02.10.2017.
 */

public class MyFragmentManager {

    //на экране и в стеке всегда должен оставаться как минимум один фрагмент
    private static final int EMPTY_FRAGMENT_STACK_SIZE = 1;

    //переменная для стэка
    private Stack<BaseFragment> mFragmentStack = new Stack<>();
    //переменная для текущего фрагмента
    private BaseFragment mCurrentFragment;

    //будет устанавливать корневой фрагмент, отображается основное содержимое окна,
    //будут меняться заголовки тул бара и видимость кнопки FAB
    //@IdRes - переменная будет из класса R.id
    public void setFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
        //не существует ли данный фрагмент в стеке
        if (activity != null && !activity.isFinishing() && !isAlreadyContains(fragment)) {
            FragmentTransaction transaction = createAddTransaction(activity, fragment, false);
            transaction.replace(containerId, fragment);
            commitAddTransaction(activity, fragment, transaction, false);
        }
    }

    //метод который будет добавлять фрагменты поверх корневого, в них будут открываться окна из пунктов меню навигации
    public void addFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
        if (activity != null && !activity.isFinishing() && !isAlreadyContains(fragment)) {
            FragmentTransaction transaction = createAddTransaction(activity, fragment, true);
            transaction.add(containerId, fragment);
            commitAddTransaction(activity, fragment, transaction, true);
        }
    }


    //метод для добавления фрагмента в бекстек
    private FragmentTransaction createAddTransaction(BaseActivity activity, BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        if(addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }

        return fragmentTransaction;
    }

    //метод, который будит комитить транзакцию фрагментов
    private void commitAddTransaction(BaseActivity activity, BaseFragment fragment, FragmentTransaction transaction,
                                      boolean addToBackStack) {
        if (transaction != null) {
            mCurrentFragment = fragment;

            //если фрагмент не добавляется в бек стек очищаем бекстек
            if (!addToBackStack) {
                mFragmentStack = new Stack<>();
            }

            mFragmentStack.add(fragment);

            commitTransaction(activity, transaction);
        }
    }

    //удаление текущего фрагмента
    public boolean removeCurrentFragment(BaseActivity activity) {
        return removeFragment(activity, mCurrentFragment);
    }

    //метод для удаления фрагмента
    public boolean removeFragment(BaseActivity activity, BaseFragment fragment) {
        //фрагмент не равен нулл и размер стека больше чем минимальный
        boolean canRemove = fragment != null && mFragmentStack.size() > EMPTY_FRAGMENT_STACK_SIZE;

        if (canRemove) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

            //pop - удаление последнего элемента из списка
            mFragmentStack.pop();
            //присваиваем последний после удаления элемент mCurrentFragment
            mCurrentFragment = mFragmentStack.lastElement();

            transaction.remove(fragment);
            commitTransaction(activity, transaction);
        }

        return canRemove;
    }

    //метод будет комитить любые транзакции не зависимо от того добавляються фрагменты или удаляются
    private void commitTransaction(BaseActivity activity, FragmentTransaction transaction) {
        transaction.commit();

        activity.fragmentOnScreen(mCurrentFragment);
    }

    //метод проверки существует ли в стеке текущий фрагмент
    public boolean isAlreadyContains(BaseFragment baseFragment) {
        if (baseFragment == null) {
            return false;
        }

        //проверка по названию класса
        return mCurrentFragment != null && mCurrentFragment.getClass().getName().equals(baseFragment.getClass().getName());
    }
}
