package com.example.covid19app.organisations.organisationsInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class organisationInfoModel extends ViewModel {

    private MutableLiveData<String> mText;

    public organisationInfoModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}