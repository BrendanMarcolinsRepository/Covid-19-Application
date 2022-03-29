package com.example.covid19app.ui.qrscan;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QRScanViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private Button scanButton;

    public QRScanViewModel() {
        mText = new MutableLiveData<>();

        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}