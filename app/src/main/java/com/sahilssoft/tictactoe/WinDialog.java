package com.sahilssoft.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class WinDialog extends Dialog {
    private final String message;

    public WinDialog(@NonNull Context context, String message) {
        super(context);
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_dialog_layout);

        TextView messageTxt = findViewById(R.id.messageTxt);
        Button startAgain = findViewById(R.id.startAgain);

        messageTxt.setText(message);
        startAgain.setOnClickListener(view -> {
            dismiss();
        });
    }
}
