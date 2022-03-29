package com.example.covid19app.ui.qrscan;

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

import com.example.covid19app.QRManual;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.example.covid19app.R;

public class QRScan extends Fragment {

    private QRScanViewModel scanViewModel;
    private Button scanButton;
    private TextView manual;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        System.out.println("worked");


        scanViewModel =
                new ViewModelProvider(this).get(QRScanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_qrscan, container, false);
        scanButton = root.findViewById(R.id.scanButton);
        manual = root.findViewById(R.id.manualTextview);

        scanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                IntentIntegrator.forSupportFragment(QRScan.this).initiateScan();

            }
        });



        manual.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent openManualActivity = new Intent(getActivity(), QRManual.class);
                startActivity(openManualActivity);

            }
        });
        scanViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s)
            {
                //textView.setText(s);
            }
        });
        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String barcode = result.getContents();
    }




}