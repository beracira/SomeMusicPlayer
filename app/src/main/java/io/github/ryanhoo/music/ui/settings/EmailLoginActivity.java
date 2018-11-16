package io.github.ryanhoo.music.ui.settings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ryanhoo.music.R;

public class EmailLoginActivity extends AppCompatActivity {

    @BindView(R.id.input_email) EditText emailText;
    @BindView(R.id.input_password) EditText passwordText;
    @BindView(R.id.btn_login) Button loginButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        ButterKnife.bind(this);

        loginButton.setOnClickListener(view -> {
            login();
        });
    }

    private void login() {
        Log.d("Email Login", "Login button clicked");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        loginButton.setEnabled(false);

        setResult(AppCompatActivity.RESULT_OK, new Intent());

        final ProgressDialog progressDialog = new ProgressDialog(EmailLoginActivity.this,
                R.style.MP_Theme_Widget_AlertDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getText(R.string.email_login_authenticating));
        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(() -> {
//            onLoginSuccess();
            onLoginFailed();
            progressDialog.dismiss();
        }, 3000);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        setResult(AppCompatActivity.RESULT_OK, new Intent());
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), R.string.fragment_setting_login_failed, Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError(getText(R.string.email_login_valid_email));
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 7) {
            passwordText.setError(getText(R.string.email_login_password_too_short));
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

}
