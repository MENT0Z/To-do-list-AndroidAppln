package com.mruraza.to_do;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    FloatingActionButton floatingActionButton;

    newDB databases = new newDB(this);
    ArrayList<String >tasklist,idlist;

    useradaptor adptr;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            recreate();
        }
        if (requestCode == 2 ) {

            recreate();

        }
    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recyclerview_id_mainactvt);
        floatingActionButton = findViewById(R.id.add_task_float_btn);

      //  databases.addtask("well guys we gonna add some sort of task here");
       // idddd = new ArrayList<>();
        tasklist = new ArrayList<>();
        idlist = new ArrayList<>();
       // statuslist = new ArrayList<>();
        atoredatainarray();
        adptr = new useradaptor( MainActivity.this,MainActivity.this,tasklist,idlist);
        recyclerView.setAdapter(adptr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      //edited atorewala



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog( MainActivity.this);
                dialog.setContentView(R.layout.add_task_button);
                EditText gettask  = dialog.findViewById(R.id.addtaskinnewin);
                Button btntoadd = dialog.findViewById(R.id.idtoaddbtn);
                btntoadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String taskk = "";
                        if(!gettask.getText().toString().equals("")){
                            taskk = gettask.getText().toString();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please enter task first !", Toast.LENGTH_SHORT).show();
                            gettask.setError("This field cant be empty!");
                        }
                        if(!gettask.getText().toString().equals("")){

                            databases.addtask(taskk);
                            tasklist.add(taskk);

                            adptr.notifyItemInserted(tasklist.size()-1);

                            recyclerView.scrollToPosition(tasklist.size()-1);
                            dialog.dismiss();

                        }
                    }
                });
                dialog.show();
            }
        });

    }
    void atoredatainarray() {
        Cursor cursor = databases.Readalldata();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "There are no tasks", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                tasklist.add(cursor.getString(1));  // Task is at index 1
                idlist.add(cursor.getString(0));
            }
        }
        cursor.close();

    }
}

