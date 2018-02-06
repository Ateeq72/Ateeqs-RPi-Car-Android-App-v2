package com.ahmed.ateeqsrpicar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmed.ateeqsrpicar.DatabaseHandler;

import java.util.List;

public class SettingsConDetails extends Activity {
    SQLiteDatabase dbn  ;
    final DatabaseHandler db = new DatabaseHandler(this);

    private void getData()
    {
        List<DataClass> data = db.getAllData();
        for (DataClass cn : data) {
            String log = "Id: " + cn.getID() + " ,IP: " + cn.getIpHost() + " ,LPORT: " + cn.getlPorta() + " ,PORT " + cn.getPort();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_con_details);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText edLporta = (EditText) findViewById(R.id.editTextLocalPort);
        final EditText edIP = (EditText) findViewById(R.id.editTextIp);
        final EditText edPorta = (EditText) findViewById(R.id.editTextPorta);
        final Button set = (Button) findViewById(R.id.set_button);


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = edIP.getText().toString();
                int port = Integer.parseInt(edPorta.getText().toString());
                int lport = Integer.parseInt(edLporta.getText().toString());
                db.addData(new DataClass(ip, lport, port));
                getData();
                startActivity(new Intent(getBaseContext(), AteeqRPiActivity.class));

            }
        });
    }

}
