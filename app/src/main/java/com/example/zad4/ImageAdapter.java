package com.example.zad4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private final Context _context;
    private final int[] Zdj = {
            R.drawable.pic01, R.drawable.pic02, R.drawable.pic03, R.drawable.pic04, R.drawable.pic05,
            R.drawable.pic06, R.drawable.pic07, R.drawable.pic08, R.drawable.pic09
    };

    ImageAdapter(Context c) {
        _context = c;
    }

    @Override
    public int getCount() {
        return Zdj.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0L;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView foto;

        if(view == null) {
            foto = new ImageView(_context);
            foto.setLayoutParams(new GridView.LayoutParams(250, 250));
            foto.setScaleType(ImageView.ScaleType.CENTER_CROP);
            foto.setPadding(8,8,8,8);
        } else {
            foto = (ImageView) view;
        }
        foto.setImageResource(Zdj[i]);

        return foto;
    }
}
