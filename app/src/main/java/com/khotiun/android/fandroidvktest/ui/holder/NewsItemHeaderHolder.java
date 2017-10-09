package com.khotiun.android.fandroidvktest.ui.holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.model.view.NewsItemHeaderViewModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hotun on 09.10.2017.
 * будет создавать и управлять полями хэдэра
 */

public class NewsItemHeaderHolder extends BaseViewHolder<NewsItemHeaderViewModel> {

    //для аватара имени пользователя
    private CircleImageView civProfileImage;
    //лля имени аватара
    private TextView tvName;
    private ImageView ivRepostedIcon;
    private TextView tvRepostedProfileName;

    public NewsItemHeaderHolder(View itemView) {
        super(itemView);

        civProfileImage = (CircleImageView) itemView.findViewById(R.id.civ_profile_image);
        tvName = (TextView) itemView.findViewById(R.id.tv_profile_name);
        ivRepostedIcon = (ImageView) itemView.findViewById(R.id.iv_resposted_icon);
        tvRepostedProfileName = (TextView) itemView.findViewById(R.id.tv_reposted_profile_name);
    }

    @Override
    public void bindViewHolder(NewsItemHeaderViewModel item) {
        //получаем контекст
        Context context = itemView.getContext();
        Log.d("qqqqqqq", "" + item.toString());

        //с помощью библиотеки glade подгружаем и устанавливаем аватар отправителя
        Glide.with(context)
                .load(item.getProfilePhoto())
                .into(civProfileImage);
        tvName.setText(item.getProfileName());

        //если это репост показываем иконку репоста и имя отправителя иначе скрываем и очищаем текст
        if (item.isRepost()) {
            ivRepostedIcon.setVisibility(View.VISIBLE);
            tvRepostedProfileName.setText(item.getRepostProfileName());
        } else {
            ivRepostedIcon.setVisibility(View.GONE);
            tvRepostedProfileName.setText(null);
        }
    }

    //очищаем аватарки и текстовые поля
    @Override
    public void unbindViewHolder() {
        civProfileImage.setImageBitmap(null);
        tvName.setText(null);
        tvRepostedProfileName.setText(null);
    }
}
