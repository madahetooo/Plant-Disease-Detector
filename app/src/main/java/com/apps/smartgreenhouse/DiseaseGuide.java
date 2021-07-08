package com.apps.smartgreenhouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DiseaseGuide extends Fragment  {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_disease_guide, container, false);

        TextView pacterial_leaf_spot =v.findViewById(R.id.pacterial_leaf_spot);
        TextView curling_leaves =v.findViewById(R.id.curling_leaves);
        TextView yellowing_leaves =v.findViewById(R.id.yellowing_leaves);
        pacterial_leaf_spot.setClickable(true);
        curling_leaves.setClickable(true);
        yellowing_leaves.setClickable(true);

        pacterial_leaf_spot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pacterial_leaf_spot();
            }
        });
        curling_leaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curling_leaves();
            }
        });
        yellowing_leaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yellowing_leaves();
            }
        });

        //pacterial_leaf_spot
//        pacterial_leaf_spot.setMovementMethod(LinkMovementMethod.getInstance());
//        String text = "<a href='https://youtu.be/kqHFwLrf9J0'> pacterial leaf spot </a>";
//        pacterial_leaf_spot.setText(Html.fromHtml(text));
//
//        //curling_leaves
//        curling_leaves.setMovementMethod(LinkMovementMethod.getInstance());
//        String text2 = "<a href='https://youtu.be/pMOhUhO8Bqo'> curling leaves </a>";
//        curling_leaves.setText(Html.fromHtml(text2));
//        //yellowing_leaves
//        yellowing_leaves.setMovementMethod(LinkMovementMethod.getInstance());
//        String text3 = "<a href='https://youtu.be/2AvxIVDL090'> yellowing leaves </a>";
//        yellowing_leaves.setText(Html.fromHtml(text3));
        return v;
    }
    public void pacterial_leaf_spot(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/kqHFwLrf9J0"));
        startActivity(browserIntent);
    }
    public void curling_leaves(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/pMOhUhO8Bqo"));
        startActivity(browserIntent);
    }
    public void yellowing_leaves(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/2AvxIVDL090"));
        startActivity(browserIntent);
    }
}