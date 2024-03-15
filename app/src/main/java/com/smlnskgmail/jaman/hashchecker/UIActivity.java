package com.smlnskgmail.jaman.hashchecker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.stealien.avsuit.module.AVSuit;

import java.util.ArrayList;

public class UIActivity extends Activity {

    AVSuit avsuit = AVSuit.getInstance();
    ArrayList<String> malwares = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        malwares = avsuit.getMalwares();
    }

    @Override
    public void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        malwares = avsuit.getMalwares();
    }

    public void deleteAllPackage(View v) {
        int deleteRequestCodeAll = 0;
        boolean result = avsuit.deleteAllPackage(deleteRequestCodeAll);
    }

    public void deletePackage(View v) {
        if (malwares.size() > 0) {
            int deleteRequestCodeOne = 0;
            boolean result = avsuit.deletePackage(
                    malwares.get(0), deleteRequestCodeOne);
        }

    }

}
