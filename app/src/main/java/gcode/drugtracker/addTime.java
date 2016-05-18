package gcode.drugtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.DateTime;
import java.util.Calendar;
import java.util.List;

public class AddTime extends AppCompatActivity {
    AlarmManager alarmMgr;
    PendingIntent alarmIntent;
    Drug drug;
    String drugName;
    TextView drugTitleText;
    EditText formHours;
    EditText formMinutes;
    EditText formInterval;
    int hours;
    int minutes;
    int interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            drugName = extras.getString("drug_name");
        }

        List<Drug> drugList = Drug.find(Drug.class, "name = ?", drugName);
        drug = drugList.get(0);

        drugTitleText = (TextView)findViewById(R.id.drugTitleText);
        drugTitleText.setText(drugName);
    }

    public void addDose(View view) {
        formHours = (EditText)findViewById(R.id.formHours);
        formMinutes = (EditText)findViewById(R.id.formMinutes);
        formInterval = (EditText)findViewById(R.id.formInterval);

        hours = Integer.parseInt(formHours.getText().toString());
        minutes = Integer.parseInt(formMinutes.getText().toString());
        interval = Integer.parseInt(formInterval.getText().toString());

        DrugDoseTime dose = new DrugDoseTime(interval,  hours, minutes, drug);
        dose.save();

        Context context = getApplicationContext();
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 60 * 24 * interval, alarmIntent);

        Intent myIntent = new Intent(AddTime.this, Dashboard.class);
        myIntent.putExtra("drug_name", drugName);
        AddTime.this.startActivity(myIntent);
    }
}
