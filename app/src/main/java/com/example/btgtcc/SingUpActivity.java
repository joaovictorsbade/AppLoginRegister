package com.example.btgtcc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SingUpActivity extends AppCompatActivity {

    public static final String TAG = "sing Up Activity";
    private EditText mEditTextUserName, mEditTextEmail, mEditTextPassword, mEditTextPassword2;

    private Button mButtonSingUp;

    private TextView mTextViewAlreadyLogin;

    private ProgressBar mProgressBar;

    private String mStringName, mStringEmail, mStringPassword, mStringPassword2;

    private boolean isRequired() {
        if (TextUtils.isEmpty(mEditTextEmail.getText()) ||
                TextUtils.isEmpty(mEditTextUserName.getText()) ||
                TextUtils.isEmpty(mEditTextPassword.getText()) ||
                TextUtils.isEmpty(mEditTextPassword2.getText())
        ) {
            return true;
        } else {
            return false;
        }

    }

    private boolean isSamePassword() {
        String mPass1 = mEditTextPassword.getText().toString().trim();
        String mPass2 = mEditTextPassword2.getText().toString().trim();

        return mPass1.equals(mPass2);
    }

    private void performActivityLogin() {
        Intent mIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(mIntent);
        finish();
    }

    private void postData() {
        if (isRequired()) {
            //Toast.makeText(this, "Mandatory information", Toast.LENGTH_SHORT).show();
            String mTextMessage = getString(R.string.text_error_all_fields_required);
            Toast.makeText(getApplicationContext(), mTextMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isSamePassword()) {
            String mTextMessage = getString(R.string.text_password_are_not_same);
            Toast.makeText(getApplicationContext(), mTextMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        mStringName = String.valueOf(mEditTextUserName.getText());
        mStringEmail = String.valueOf(mEditTextEmail.getText()).toLowerCase(Locale.ROOT);
        mStringPassword = String.valueOf(mEditTextPassword.getText()).toString().trim();
        mStringPassword2 = String.valueOf(mEditTextPassword2.getText()).trim();

        mProgressBar.setVisibility(View.VISIBLE);

        User mUser = new User(mStringName , mStringPassword, mStringEmail);

//        public User(int id, String userName, String password, String email) {
//            mId = id;
//            mUserName = userName;
//            mPassword = password;
//            mEmail = email;
//        }

        int vResult = UserDao.insertUser(mUser, getApplicationContext());

        String mTextMessage;
        mProgressBar.setVisibility(View.GONE);

        if (vResult <= 0) {
            mTextMessage = getString(R.string.text_insert_error);
        } else {
            mTextMessage = getString(R.string.text_insert_success);
        }

        Intent mIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(mIntent);
        finish();

    }

    public class ClickButtonSignUp implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            postData();
        }
    }

    public class ClickTextViewAlreadyLogin implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            performActivityLogin();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //https://stackoverflow.com/questions/9732761/prevent-the-keyboard-from-displaying-on-activity-start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mEditTextEmail = findViewById(R.id.editText_email);
        mEditTextUserName = findViewById(R.id.editText_user_name);
        mEditTextPassword = findViewById(R.id.editText_password_sign_up);
        mEditTextPassword2 = findViewById(R.id.editText_password_sign_up_2);

        mTextViewAlreadyLogin = findViewById(R.id.textView_already);
        mTextViewAlreadyLogin.setOnClickListener(new ClickTextViewAlreadyLogin());

        mProgressBar = findViewById(R.id.progressBarSignUp);

        mButtonSingUp = findViewById(R.id.button_sign_up);
        mButtonSingUp.setOnClickListener(new ClickButtonSignUp());

    }


}