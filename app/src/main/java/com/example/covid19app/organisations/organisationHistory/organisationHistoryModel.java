package com.example.covid19app.organisations.organisationHistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class organisationHistoryModel extends ViewModel {

    private MutableLiveData<String> mText;

    public organisationHistoryModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}