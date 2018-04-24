package com.magarex.practicalquiz;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView content;
    private Button thanks;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = findViewById(R.id.textView1);
        email = findViewById(R.id.textView2);
        content = findViewById(R.id.textView3);

        thanks = findViewById(R.id.button);

        preferences = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        name.setText(preferences.getString("name", null));
        email.setText(preferences.getString("email", null));
        content.setText(preferences.getString("content", null));

    }
}
