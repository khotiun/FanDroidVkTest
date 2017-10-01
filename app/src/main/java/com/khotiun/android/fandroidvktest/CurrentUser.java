package com.khotiun.android.fandroidvktest;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

/**
 * Created by hotun on 01.10.2017.
 * для определения текущего юсера
 */

public class CurrentUser {

    //метод для возвращения токена доступа от сервера вк
    public static String getAccessToken() {
        if (VKAccessToken.currentToken() == null) {
            return null;
        }

        return VKAccessToken.currentToken().accessToken;
    }

    //возвращает идентификатор текущего пользователя если токен получен
    public static String getId() {
        if (VKAccessToken.currentToken() == null) {
            return null;
        }

        return VKAccessToken.currentToken().userId;
    }

    //если пользователь авторизован, если пользовательский токен не равен нулл и не устарел
    public static boolean isAuthorized() {
        return VKSdk.isLoggedIn()
                && VKAccessToken.currentToken() != null
                && VKAccessToken.currentToken().isExpired();
    }
}
