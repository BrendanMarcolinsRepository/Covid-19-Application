package com.example.covid19app.businessUI.ContractTracing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19app.Business;
import com.example.covid19app.BusinessDatabase;
import com.example.covid19app.HomePage;
import com.example.covid19app.Organisation;
import com.example.covid19app.R;
import com.example.covid19app.businessUI.siteinfo.siteInfoModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.MultiFormatOneDReader;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class ContactTracing extends Fragment
{
    private ContractTracingModel contractTracingModel;
    private Button qrGenerate, qrSaver;
    private BusinessDatabase businessDatabase;
    private Organisation  organisation;
    private ImageView qr;
    private String type;
    private Business business;
    private HomePage homePage;
    private View root;
    private ConstraintLayout layout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        contractTracingModel =
                new ViewModelProvider(this).get(ContractTracingModel.class);
        root = inflater.inflate(R.layout.fragment_business_contract_tracing, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        qrGenerate = root.findViewById(R.id.generateQR);
        qrSaver = root.findViewById(R.id.saveQR);
        qr = root.findViewById(R.id.qrImage);
        homePage = (HomePage) getActivity();
        type = homePage.getUserType();
        layout = root.findViewById(R.id.qrFragement);


        qrGenerate.setOnClickListener(qrGeneratorMethod);
        qrSaver.setOnClickListener(qrSaverMethod);
        contractTracingModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        return root;
    }

    private View.OnClickListener qrGeneratorMethod = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            MultiFormatWriter write = new MultiFormatWriter();
            try
            {

                if(type.matches("Business"))
                {

                    business = homePage.getBusiness();
                    BitMatrix matrix = write.encode(business.getBusiness_address(), BarcodeFormat.QR_CODE, 350, 350);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    qr.setImageBitmap(bitmap);
                }

                if(type.matches("Organisation"))
                {

                    organisation = homePage.getOrganisation();
                    BitMatrix matrix = write.encode(organisation.getOrganisation_address(), BarcodeFormat.QR_CODE, 350, 350);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    qr.setImageBitmap(bitmap);
                }
            }
            catch (WriterException e)
            {
                e.printStackTrace();
            }

        }
    };

    private View.OnClickListener qrSaverMethod = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if(qr != null)
            {
                qr.setDrawingCacheEnabled(true);
                Bitmap b = qr.getDrawingCache();
                MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), b,"Qr Code", "QR saved to image gallery");
            }
            else
            {
                Snackbar.make(layout,"Contacts Have Been Saved ", Snackbar.LENGTH_LONG).show();
            }

        }
    };

}
