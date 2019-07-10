package com.logic.tsg.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.logic.tsg.R;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail,editTextPassword;
    Button login;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    public void init(){
        editTextEmail=findViewById(R.id.username);
        editTextPassword=findViewById(R.id.password);
        login=findViewById(R.id.login);
        textView=findViewById(R.id.forgotPassword);
    }
}
