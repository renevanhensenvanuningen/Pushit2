package com.example.reneu.pushit2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.reneu.pushit2.Constants.TABLE_NAME;
import static com.example.reneu.pushit2.Constants.TIME;
import static com.example.reneu.pushit2.Constants.VALUE;

import java.sql.Time;

/**
 * Created by reneu on 29-9-2017.
 */

public class PushUpFragment extends Fragment implements View.OnClickListener{

    private PushupData pushup;
    private Button btn;
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.pushup_fragment, container, false);
        btn = (Button) view.findViewById(R.id.btnPushUpSubmit);
        btn.setOnClickListener(this);
        tv = (TextView) view.findViewById(R.id.tvPushUp);
        return view;

    }

    @Override
    public void onClick(View v) {
        pushup = new PushupData(this.getContext());
        SQLiteDatabase db =  pushup.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        String valStr = tv.getText().toString();
        Integer val = Integer.decode(valStr);
        values.put(VALUE, val.intValue());
        long rowsaffected = db.insert(TABLE_NAME, null, values);
        Toast t = new Toast(this.getContext());
      //  t.setText(new Long(rowsaffected).toString());
      //  t.show();

    }
}
