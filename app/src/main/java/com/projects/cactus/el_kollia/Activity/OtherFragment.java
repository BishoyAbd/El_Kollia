package com.projects.cactus.el_kollia.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.projects.cactus.el_kollia.R;
import com.projects.cactus.el_kollia.customviews.CustomLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by el on 4/15/2017.
 */

public class OtherFragment extends Fragment {


    private LinearLayout mainLayout;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstance){

        final View view=inflater.inflate(R.layout.other_fragment,container,false);

        CustomLayout customLayout= (CustomLayout) view.findViewById(R.id.other_linearLayout_id);
        return view;

    }



    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

    }
}
