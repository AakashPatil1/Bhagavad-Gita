package com.example.bhagavadgita;

import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingDialog {

    private Dialog dialog;
    private ProgressBar progressBar;
    private TextView loadingText;

    public LoadingDialog(Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);
        progressBar = dialog.findViewById(R.id.progressBar);
        loadingText = dialog.findViewById(R.id.loadingText);
    }

    public void setMessage(String message) {
        loadingText.setText(message);
    }

    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
