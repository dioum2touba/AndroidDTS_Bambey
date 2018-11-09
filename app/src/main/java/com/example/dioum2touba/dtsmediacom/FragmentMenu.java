package com.example.dioum2touba.dtsmediacom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentMenu extends Fragment {

    CardView card1;
    CardView card2;
    CardView card3;
    CardView card4;
    View view;

    public FragmentMenu() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.menu_fragment,container,false);

        card2=view.findViewById(R.id.card2);
        card3=view.findViewById(R.id.card3);
        card4=view.findViewById(R.id.card4);
        card1=view.findViewById(R.id.card1);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

       card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Fonctionnalité non prête");
                pDialog.setCanceledOnTouchOutside(true);
                pDialog.show();
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Fonctionnalité non prête");
                pDialog.setCanceledOnTouchOutside(true);
                pDialog.show();
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Fonctionnalité non prête");
                pDialog.setCanceledOnTouchOutside(true);
                pDialog.show();
            }
        });

        return view;
    }
}
