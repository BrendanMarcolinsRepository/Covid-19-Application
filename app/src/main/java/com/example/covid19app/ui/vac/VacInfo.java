package com.example.covid19app.ui.vac;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.R;


public class VacInfo extends Fragment {

    private VacInfoModel vacInfoModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        vacInfoModel =
                new ViewModelProvider(this).get(VacInfoModel.class);
        View root = inflater.inflate(R.layout.fragment_vac_info, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);

        return root;
    }
}