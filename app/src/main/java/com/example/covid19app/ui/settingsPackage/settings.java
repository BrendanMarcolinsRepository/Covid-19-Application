package com.example.covid19app.ui.settingsPackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.MainActivity;
import com.example.covid19app.R;
import com.example.covid19app.ui.vac.VacInfoModel;
import com.google.firebase.auth.FirebaseAuth;

public class settings extends Fragment
{
    private settingsModel settingsmodel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        settingsmodel =
                new ViewModelProvider(this).get(settingsModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        // Logout Feature
        Button mButton = root.findViewById(R.id.logout1);

        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent mainActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(mainActivity);
            }
        });



        return root;
    }
}
