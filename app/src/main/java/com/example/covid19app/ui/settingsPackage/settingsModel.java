package com.example.covid19app.ui.settingsPackage;

import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class settingsModel extends ViewModel
{
    private MutableLiveData<String> mText;
    private Button scanButton;

    public settingsModel() {
        mText = new MutableLiveData<>();

        mText.setValue("Vac Info");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
