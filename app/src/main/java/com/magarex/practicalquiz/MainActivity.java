package com.magarex.practicalquiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView lblusername;
    private TextView lblemail;
    private TextView lblcontent;
    private SharedPreferences preferences;
    private Button btnchanger;
    private ImageView image;
    private Dialog mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblusername = findViewById(R.id.lblUsername);
        lblemail = findViewById(R.id.lblEmail);
        lblcontent = findViewById(R.id.lblContent);
        image = findViewById(R.id.image);

        btnchanger = findViewById(R.id.btnChanger);

        mydialog = new Dialog(this);

        btnchanger.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                String lable = btnchanger.getText().toString().trim();

                if (lable.equals("Enter Details")) {
                    showPopup();
                } else if (lable.equals("Next")) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                    Pair[] pairs = new Pair[4];
                    pairs[0] = new Pair<View, String>(image, "imageTransition");
                    pairs[1] = new Pair<View, String>(lblusername, "nameTransition");
                    pairs[2] = new Pair<View, String>(lblemail, "emailTransition");
                    pairs[3] = new Pair<View, String>(lblcontent, "contentTransition");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);

                    startActivity(intent, options.toBundle());
                }
            }
        });

    }

    private void showPopup() {
        final EditText txtusername;
        final EditText txtemail;
        final EditText txtcontent;
        Button btnsave;

        mydialog.setContentView(R.layout.custompopup);
        txtusername = mydialog.findViewById(R.id.txtUsername);
        txtemail = mydialog.findViewById(R.id.txtEmail);
        txtcontent = mydialog.findViewById(R.id.txtContent);
        btnsave = mydialog.findViewById(R.id.btnSave);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                final String name = txtusername.getText().toString().trim();
                final String mail = txtemail.getText().toString().trim();
                final String contents = txtcontent.getText().toString().trim();

                preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", name);
                editor.putString("email", mail);
                editor.putString("content", contents);
                editor.apply();

                lblusername.setText("- "+name);
                lblemail.setText("- "+mail);
                if (contents.length() > 40)
                    lblcontent.setText("- "+contents.substring(0, 40) + ".....");
                else
                    lblcontent.setText("- "+contents);

                btnchanger.setText("Next");

                mydialog.dismiss();

            }
        });

        Objects.requireNonNull(mydialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mydialog.show();

    }
}
