package com.example.covid19app.ui.vaccinecertification;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class vaccineCertificationModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Button scanButton;

    public vaccineCertificationModel() {
        mText = new MutableLiveData<>();

        mText.setValue("Vac Info");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
