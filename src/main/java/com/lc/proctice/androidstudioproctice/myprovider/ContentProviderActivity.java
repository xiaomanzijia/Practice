package com.lc.proctice.androidstudioproctice.myprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

/**
 * Created by licheng on 11/6/16.
 */
public class ContentProviderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        insertData();

        String columns[] = new String[] {MyContentProvider.COLUMN_ID,MyContentProvider.COLUMN_NAME,MyContentProvider.COLUMN_SEX,MyContentProvider.COLUMN_COMPANYNAME,
        MyContentProvider.COLUMN_ACCOUNT,MyContentProvider.COLUMN_CREATETIME};
        Cursor c = getContentResolver().query(MyContentProvider.URI,columns,null,null,null);
        if(c.moveToFirst()){
            String id = null, name = null, sex = null, companyname = null, account = null, createtime = null;
            do{
                id = c.getString(c.getColumnIndex(MyContentProvider.COLUMN_ID));
                name = c.getString(c.getColumnIndex(MyContentProvider.COLUMN_NAME));
                sex = c.getString(c.getColumnIndex(MyContentProvider.COLUMN_SEX));
                companyname = c.getString(c.getColumnIndex(MyContentProvider.COLUMN_COMPANYNAME));
                account = c.getString(c.getColumnIndex(MyContentProvider.COLUMN_ACCOUNT));
                createtime = c.getString(c.getColumnIndex(MyContentProvider.COLUMN_CREATETIME));
                Logger.i(id + "  " + name + " " + sex
                        + " " + companyname + " " + account + " " + createtime);
            }while (c.moveToNext());
        }

        delete();

        update();

    }

    private void delete() {
        getContentResolver().delete(MyContentProvider.URI,"name = ?",new String[]{"lichenger11"});
    }

    private void update() {
        ContentValues cv = new ContentValues();
        cv.put(MyContentProvider.COLUMN_NAME,"licheng55");
        getContentResolver().update(MyContentProvider.URI,cv,"name = ?",new String[]{"lichenger5"});
    }

    private void insertData() {
        for (int i = 0; i < 10; i++) {
            ContentValues values = new ContentValues();
            values.put(MyContentProvider.COLUMN_NAME,"lichenger"+i);
            values.put(MyContentProvider.COLUMN_SEX,"boy");
            values.put(MyContentProvider.COLUMN_COMPANYNAME,"baomai");
            values.put(MyContentProvider.COLUMN_ACCOUNT,10000+i+"");
            values.put(MyContentProvider.COLUMN_CREATETIME,System.currentTimeMillis()+"");
            getContentResolver().insert(MyContentProvider.URI,values);
        }
    }
}
