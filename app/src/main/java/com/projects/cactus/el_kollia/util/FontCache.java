package com.projects.cactus.el_kollia.util;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by el on 2/1/2017.
 */

public class FontCache {

    private static  Map<String,Typeface> fontCache =new HashMap<>();
    private static   String fontPath="fonts/";
    public static Typeface getTypeFace(Context context,String fontName){
        Typeface typeface=fontCache.get(fontName);

        if (typeface==null){
            try {
                typeface=Typeface.createFromAsset(context.getAssets(),fontPath+fontName);
            }
            catch (Exception e){
                return null;
            }

            fontCache.put(fontName,typeface);
        }

        return typeface;
    }



}
