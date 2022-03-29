package com.example.covid19app.organisations.rolloutPackage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class rolloutModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public rolloutModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
