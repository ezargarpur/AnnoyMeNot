package com.annoymenot.annoymenot;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.annoymenot.logic.Call_Manager;
import com.annoymenot.logic.Contact;
import com.annoymenot.logic.Contact_Group;
import com.annoymenot.logic.Filter;
import com.annoymenot.logic.Text_Manager;
import com.annoymenot.utils.TimeInterval;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Set;


public class defaultActivity extends ActionBarActivity
{
    //public Call_Manager callManager;
    //public Text_Manager textManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        Filter filter = Filter.getInstance();
        //callManager = new Call_Manager(filter);
        //textManager = new Text_Manager(filter);
        Contact_Group cg = new Contact_Group();
        cg.addContact(new Contact("7035096146"));
        TimeInterval ti = new TimeInterval();
        GregorianCalendar gc = new GregorianCalendar(Locale.US);
        gc.add(GregorianCalendar.SECOND, 32);
        ti.setEnd(gc);
        cg.setTimeInterval(ti);
        filter.addGroup(cg);
        //filter.addNumber("7033032158");
        displayContacts();
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

    //Method to retrieve the contact list
    private void displayContacts() {

        Hashtable<String, ArrayList<String>> contactInfo = new Hashtable<String, ArrayList<String>>();

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    contactInfo.put(name, new ArrayList<String> ());
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String rawNumber = phoneNo.replaceAll("[^0-9]", "");
                        contactInfo.get(name).add(rawNumber);
                    }
                    pCur.close();
                }
            }
        }

        Set<String> keys = contactInfo.keySet();
        Log.d("contact test", "Number of contacts: " + keys.size());
        for(String key : keys)
        {
            Log.d("contact test", "Name: " + key + ", Phone No: " + contactInfo.get(key));
        }
    }
}
