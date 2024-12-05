package com.example.m3hw1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.m3hw1.databinding.ActivityMainBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView messageTextView = findViewById(R.id.messageTextView);

        String message = getIntent().getStringExtra("MESSAGE_KEY");
        if (message != null) {
            messageTextView.setText(message);
        }
    }
}
