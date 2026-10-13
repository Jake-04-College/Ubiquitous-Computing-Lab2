package com.example.sendimplicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private int generateCode() {
        Random RandomGen = new Random();
        int randomInt = RandomGen.nextInt(9999 - 1000 + 1) + 1000;
        return randomInt;
    }

    private void sendEmail(String name, String email, int securityCode){
        String EmailSubject = "Your Security Code";
        String EmailBody = "Hello, " + name + ".\n" + "Your Security Code is: " + securityCode + ".";
        Uri EmailContent = Uri.parse("mailto:" + Uri.encode(email) + "?subject=" + Uri.encode(EmailSubject) +
                "?body?=" + Uri.encode(EmailBody));

        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(EmailContent);
        startActivity(sendIntent);
    }

    public void onSubmit(View view) {
        TextView tvName = (TextView) findViewById(R.id.editName);
        //TextView tvPassword = (TextView) findViewById(R.id.editPassword);
        //TextView tvPhoneNumber = (TextView) findViewById(R.id.editPhoneNumber);
        TextView tvEmailAddress = (TextView) findViewById(R.id.editEmailAddress);

        String name = tvName.getText().toString();
        String email = tvEmailAddress.getText().toString();

        int securityCode = generateCode();

        sendEmail(name, email, securityCode);

        Log.d("Return", "onSubmit() returned: " + securityCode);
        Log.d("Return", "onSubmit() returned: " + name);
        Log.d("Return", "onSubmit() returned: " + email);

    }
}