package com.example.rememberourstory;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PopUp {

    public static void managePopup(View v, Context context) {
        TextView txtClose;
        Button closeBtn;
        Dialog popUpDialog = new Dialog(context);
        popUpDialog.setContentView(R.layout.pop_up);
        txtClose = (TextView) popUpDialog.findViewById(R.id.txtclose);
        txtClose.setText("X");
        closeBtn = (Button) popUpDialog.findViewById(R.id.closeBtn);
        txtClose.setOnClickListener(v1 -> popUpDialog.dismiss());
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog.dismiss();
            }
        });

        popUpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        popUpDialog.show();
    }
}
