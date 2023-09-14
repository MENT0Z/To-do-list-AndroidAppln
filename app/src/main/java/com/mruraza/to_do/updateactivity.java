package com.mruraza.to_do;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class updateactivity extends AppCompatActivity {

    EditText updatetaskkk;
    Button btnforupadate;
    String taskk,idd,deletedId;
    Context context ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateactivity);
        updatetaskkk = findViewById(R.id.addtaskinnewin22);
        btnforupadate = findViewById(R.id.idtoaddbtn22);
        getandsetintentdata();

        btnforupadate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               newDB nndb = new newDB(updateactivity.this);

               taskk = updatetaskkk.getText().toString().trim();
               nndb.update_task(idd,taskk);


                finish();
            }
        });

    }


    void getandsetintentdata()
    {
        taskk = getIntent().getStringExtra("taskk");
        idd = getIntent().getStringExtra("idd");
      //  deletedId = getIntent().getStringExtra("deletedId");
        updatetaskkk.setText(taskk);

    }
}