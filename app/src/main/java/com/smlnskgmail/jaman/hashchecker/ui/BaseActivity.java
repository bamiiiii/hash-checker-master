package com.smlnskgmail.jaman.hashchecker.ui;

import android.app.Application;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smlnskgmail.jaman.hashchecker.App;
import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.locale.api.LanguageConfig;
import com.smlnskgmail.jaman.hashchecker.components.theme.api.ThemeConfig;
import com.stealien.avsuit.module.AVSuit;
import com.stealien.avsuit.module.DetectionInfo;

public abstract class BaseActivity extends AppCompatActivity {

    AVSuit avsuit = AVSuit.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        langHelper().applyLanguage(this);
        super.onCreate(savedInstanceState);
        avsuit.setNotifications(true, true);
        avsuit.setOnDetectListener(new AVSuit.onDetectListener() {
            @Override
            public void onFinishDetect(DetectionInfo[] detectionInfos) {
                //탐지 후 수행할 동작
                avsuit.AVSuitListener(detectionInfos);
                for (DetectionInfo i : detectionInfos) {
                    Log.e("AVSuit Print", i.getPackageName());
                }
            }
        });
        create();
    }

    @NonNull
    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        theme.applyStyle(themeHelper().currentTheme().getThemeResId(), true);
        return theme;
    }

    @NonNull
    protected abstract LanguageConfig langHelper();

    @NonNull
    protected abstract ThemeConfig themeHelper();

    protected abstract void create();

}
