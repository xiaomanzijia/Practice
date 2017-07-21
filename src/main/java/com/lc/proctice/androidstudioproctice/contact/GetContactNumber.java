package com.lc.proctice.androidstudioproctice.contact;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * Created by licheng on 17/8/15.
 */
public class GetContactNumber {
    public static String getNumber(Context context){
        Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI,null,null,null,null);
        String phonenumber;
        while (cursor.moveToNext()){
            phonenumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
            System.out.println(phonenumber);
        }
        return null;
    }
}
