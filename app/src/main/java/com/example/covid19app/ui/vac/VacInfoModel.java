package com.example.covid19app.ui.vac;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VacInfoModel extends ViewModel
{

    private MutableLiveData<String> mText;
    private Button scanButton;

    public VacInfoModel() {
        mText = new MutableLiveData<>();

        mText.setValue("Vac Info");
    }

    public LiveData<String> getText() {
        return mText;
    }
}