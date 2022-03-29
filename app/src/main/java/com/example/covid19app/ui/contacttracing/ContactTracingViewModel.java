package com.example.covid19app.ui.contacttracing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactTracingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContactTracingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}