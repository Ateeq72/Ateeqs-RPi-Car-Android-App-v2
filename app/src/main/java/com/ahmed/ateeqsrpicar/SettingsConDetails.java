package com.ahmed.ateeqsrpicar;

import android.os.Bundle;
import android.app.Activity;

public class SettingsConDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_con_details);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
