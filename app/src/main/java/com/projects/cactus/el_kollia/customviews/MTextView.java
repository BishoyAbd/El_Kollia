package com.projects.cactus.el_kollia.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.projects.cactus.el_kollia.util.FontCache;


/**
 * Created by el on 2/1/2017.
 */

public class MTextView extends TextView {
    public MTextView(Context context) {
        super(context);
        applyFont(context);
    }

    public MTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyFont(context);
    }

    public MTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyFont(context);
    }
@android.support.annotation.RequiresApi(api = android.os.Build.VERSION_CODES.LOLLIPOP)
    public MTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
         applyFont(context);
    }

    public void applyFont(Context context){
        Typeface typeface= FontCache.getTypeFace(context,"arabict.otf");
        setTypeface(typeface);

    }


}
