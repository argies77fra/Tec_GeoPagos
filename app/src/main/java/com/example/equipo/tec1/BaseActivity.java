package com.example.equipo.tec1;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity implements IView {

    ProgressDialog progressBar;


    @Override
    public void showProgress(String message) {

        if (progressBar != null && progressBar.isShowing()) progressBar.dismiss();

            progressBar = ProgressDialog.show(this, "", message, true);
            progressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.logo));
    }

    @Override
    public void hideProgress() {
        if (progressBar != null && progressBar.isShowing()) {
            progressBar.cancel();
        }


    }

    @Override
    public void showToast(String message) {
        if (!message.equals(""))
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
