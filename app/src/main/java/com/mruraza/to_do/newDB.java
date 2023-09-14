    package com.mruraza.to_do;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.widget.Toast;


    public class newDB extends SQLiteOpenHelper {
       // Context context;
        public static final String DATABASE_NAME = "TO_DO";
        public static final int DATABASE_VERSION = 1;
        public static final String TABLE_TODOO ="tasks";
        public static final String KEY_ID="id";
        public static final String TASKSS = "task";

      //  public  static  final String STATUSS = "statuss";

        private Context context;
        public newDB(Context context) {
            super(context, DATABASE_NAME , null, DATABASE_VERSION);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_TODOO + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + TASKSS + " TEXT)");
                    //+ STATUSS + "TEXT)");
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TODOO);
        onCreate(db);

        }

        public void addtask(String task) {

            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TASKSS,task);
            db.insert(TABLE_TODOO,null,values);

        }
       /*
        public  void updatestatus(String status)
        {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(STATUSS,status);
            database.insert(TABLE_TODOO,null,values);
        }

        */




//        public  void dindntupdatestatus()
//        {
//            SQLiteDatabase database = this.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put(STATUSS,"INCOMPLETE");
//            database.insert(TABLE_TODOO,null,values);
//        }
        public void update_task(String idd, String taskk) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(TASKSS, taskk);
            int rowsAffected = db.update(TABLE_TODOO, cv, "id=?", new String[]{idd});

            if (rowsAffected > 0) {

                Toast.makeText(context, "Task updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to update task", Toast.LENGTH_SHORT).show();
            }
        }
        public  void delete_task(String id)
        {
            SQLiteDatabase database = this.getWritableDatabase();
            long result = database.delete(TABLE_TODOO,"id=?",new String[]{id});
            if(result == -1){
                Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            }
        }


        Cursor Readalldata(){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = " SELECT * FROM " + TABLE_TODOO;
            Cursor cursor= null;
            if(db!=null)
            {
                cursor = db.rawQuery(query,null);
            }
            return cursor;

        }
    }
