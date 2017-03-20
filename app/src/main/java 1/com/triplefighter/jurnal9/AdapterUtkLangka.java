package com.triplefighter.jurnal9;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterUtkLangka extends ArrayAdapter {
    private int mbackgroundColor;

    public AdapterUtkLangka(Context konteks, List<Hewan> object, int backgroundColor) {
        super(konteks, 0, object);
        mbackgroundColor = backgroundColor;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Hewan current = (Hewan) getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);

        imageView.setImageResource(current.getImageResource());
        imageView.setVisibility(View.GONE);

        LinearLayout words = (LinearLayout) convertView.findViewById(R.id.words);
        words.setBackgroundColor(ContextCompat.getColor(getContext(), mbackgroundColor));

        TextView miwokWord = (TextView) convertView.findViewById(R.id.miwok_word);
        miwokWord.setText(current.getNamaHewan());

        TextView defaultWord = (TextView) convertView.findViewById(R.id.default_word);
        defaultWord.setText(current.getNamaLatin());

        return convertView;
    }

}
