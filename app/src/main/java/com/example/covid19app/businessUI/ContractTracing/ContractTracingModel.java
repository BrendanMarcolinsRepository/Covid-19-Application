package com.example.covid19app.businessUI.ContractTracing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid19app.Users;

public class ContractTracingModel extends ViewModel
{
    private MutableLiveData<String> mText;
    private Users users;

    public ContractTracingModel()
    {
        //userGreeting.setText(String.format("Welcome %s %s", admin.getFirstName(),admin.getLastName()));
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setUsers(Users users)
    {
        this.users = users;
        mText.setValue("Welcome: " +  users.getFirstName());
    }
}
