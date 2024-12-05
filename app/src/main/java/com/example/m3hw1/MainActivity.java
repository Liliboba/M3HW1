package com.example.m3hw1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m3hw1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText messageEditText = binding.messageEditText;
        Button sendMessageButton = binding.sendMessageButton;

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageEditText.getText().toString();

                sendEmail(message);

                openSecondActivity(message);
            }
        });
    }

    private void sendEmail(String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Тема письма");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Выберите приложение для отправки"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Нет приложений для отправки почты", Toast.LENGTH_SHORT).show();
        }
    }


    private void openSecondActivity(String message) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("MESSAGE_KEY", message);
        startActivity(intent);
    }
}
