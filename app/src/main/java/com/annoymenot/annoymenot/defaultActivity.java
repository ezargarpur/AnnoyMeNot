package com.annoymenot.annoymenot;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.annoymenot.logic.Call_Manager;
import com.annoymenot.logic.Filter;
import com.annoymenot.logic.Text_Manager;


public class defaultActivity extends ActionBarActivity
{
    //public Call_Manager callManager;
    //public Text_Manager textManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        Filter filter = new Filter();
        //callManager = new Call_Manager(filter);
        //textManager = new Text_Manager(filter);
        filter.addNumber("7033032158");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
