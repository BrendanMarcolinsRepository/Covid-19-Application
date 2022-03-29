package com.example.covid19app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapt extends ArrayAdapter<String>
{
    private final Activity activity;
    private final String[] venueName;
    private final Integer[] venueImage;

    //constructor to set the data field
    public ListAdapt(Activity activity, String[] venueName,
                     Integer[] venueImage)
    {

        super(activity, R.layout.contact_list_items, venueName);
        // TODO Auto-generated constructor stub

        this.activity = activity;
        this.venueImage = venueImage;
        this.venueName = venueName;
    }
    // used to set the view
    public View getView(int position, View view, ViewGroup parent) {
        //in flats the listview with a layout
        LayoutInflater inflat = activity.getLayoutInflater();
        View view1 = inflat.inflate(R.layout.contact_list_items, null, true);

        //gets image and text
        TextView ListViewItems = (TextView) view1.findViewById(R.id.imageCont);
        ImageView ListViewImage = (ImageView) view1.findViewById(R.id.ContText);

        //places the imaage and text in the correct position
        ListViewItems.setText(venueName[position]);
        ListViewImage.setImageResource(venueImage[position]);
        return view1;

    };
}
