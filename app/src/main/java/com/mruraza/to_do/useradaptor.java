package com.mruraza.to_do;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class useradaptor extends RecyclerView.Adapter<useradaptor.ViewHolder> {
    Context context;
    ArrayList<String> arrlist=new ArrayList<>(),idlist=new ArrayList<>(),statuslist=new ArrayList<>();
    private Activity activity;
   // Activity act = (Activity) context;



    public useradaptor(Activity activity, Context context, ArrayList<String> arrlist,ArrayList<String>idlist) {
        this.context = context;
        this.arrlist = arrlist;
        this.idlist = idlist;
        this.activity = activity;
       // this.statuslist=statuslist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_for_todo, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder,  int position) {
        holder.txt.setText(String.valueOf(arrlist.get(position)));



        holder.txt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                newDB dbb = new newDB(context);
                if (isChecked) {
                    holder.txt.setChecked(true);
                    Toast.makeText(context, "Congrats, you finished your Job", Toast.LENGTH_SHORT).show();
                    holder.edttxt.setText("COMPLETED");
                    /*statuslist.add("completed");
                    dbb.updatestatus(statuslist.get(holder.getAdapterPosition()));*/
                }else {
                    holder.edttxt.setText("INCOMPLETE");
                }
            }
        });

        holder.txt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //  Toast.makeText(context, "The button is long pressed", Toast.LENGTH_SHORT).show();
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.on_long_pressed);
                Button btn1 = dialog.findViewById(R.id.aditbtn);
                Button btn2 = dialog.findViewById(R.id.deletebtn);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(context, "edited", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,updateactivity.class);
                        intent.putExtra("taskk",String.valueOf(arrlist.get(holder.getAdapterPosition())));
                        intent.putExtra("idd",String.valueOf(idlist.get(holder.getAdapterPosition())));
                      //  context.startActivity(intent);
                        activity.startActivityForResult(intent,1);
                        dialog.dismiss();
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        //Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Delete");
                        builder.setMessage("Are you sure want to delete");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                newDB dppp = new newDB(context);
                                dppp.delete_task(idlist.get(holder.getAdapterPosition()));
                                arrlist.remove(holder.getAdapterPosition());





                              notifyItemRemoved(holder.getAdapterPosition());
                              notifyItemRangeChanged(holder.getAdapterPosition(), arrlist.size());




                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                       builder.create().show();
                        dialog.dismiss();
                    }
                });


                dialog.show();
                return false;

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox txt;
        CardView cardView;
        TextView edttxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.task_list);
            cardView = itemView.findViewById(R.id.cardview_id);
            edttxt = itemView.findViewById(R.id.completionstatus);


        }
    }
}


