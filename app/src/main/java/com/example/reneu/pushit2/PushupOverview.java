package com.example.reneu.pushit2;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.BaseColumns._ID;

import static com.example.reneu.pushit2.Constants.TABLE_NAME;
import static com.example.reneu.pushit2.Constants.TIME;
import static com.example.reneu.pushit2.Constants.VALUE;


/**
 * Created by reneu on 29-9-2017.
 */

public class PushupOverview extends Fragment {

    private PushupData pushup;

    private static String[] FROM = {_ID, TIME, VALUE};
    private static String ORDER_BY = TIME +" DESC";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.pushupoverview_fragment, container, false);
        pushup = new PushupData(this.getContext());
        Cursor c = getPushUps();
        showPushUps(c);
        return view;

    }

    public Cursor getPushUps()
    {
        SQLiteDatabase db = pushup.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
        getActivity().startManagingCursor(cursor);

        return cursor;
    }

    public void showPushUps(Cursor c)
    {
        StringBuilder sb = new StringBuilder("Saved pushups\n");
        while (c.moveToNext())
        {
            long id = c.getLong(0);
            long yourmilliseconds = c.getLong(1);

            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            Date resultdate = new Date(yourmilliseconds);

            long value = c.getInt(2);
            sb.append(id).append(": ");
            sb.append(sdf.format(resultdate)).append(": ");
            sb.append(value).append("\n");
            TextView text = (TextView) getActivity().findViewById(R.id.text);
            try {
                text.setText(sb.toString());
            }
            catch (Exception ex)
            {
                Log.e("exception", ex.getMessage());
            }


        }

    }
}
