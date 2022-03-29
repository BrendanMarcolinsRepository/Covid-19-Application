package com.example.covid19app.businessUI.siteinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid19app.Business;
import com.example.covid19app.Users;

public class siteInfoModel extends ViewModel
{

    private MutableLiveData<String> mText;
    private Business business;

    public siteInfoModel()
    {


        //userGreeting.setText(String.format("Welcome %s %s", admin.getFirstName(),admin.getLastName()));
        mText = new MutableLiveData<>();


    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setUsers(Business business)
    {
        this.business = business;
        mText.setValue("Welcome : " +  business.getBusiness_name());
    }
}