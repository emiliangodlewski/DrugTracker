package gcode.drugtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.orm.query.Select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrugDetails extends AppCompatActivity {

    private String drugName;
    private TextView drugTitle;
    private TextView drugAbout;
    private Drug drug;
    private ListView timeListView ;
    private ArrayAdapter<String> listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            drugName = extras.getString("drug_name");
        }

        List<Drug> drugList = Drug.find(Drug.class, "name = ?", drugName);
        drug = drugList.get(0);

        timeListView = (ListView) findViewById( R.id.timesList );

        drugTitle = (TextView)findViewById(R.id.drugTitle);
        drugAbout = (TextView)findViewById(R.id.drugAbout);

        drugTitle.setText(drug.name);
        drugAbout.setText(drug.description);

        ArrayList<String> timesList = new ArrayList<String>();
        List<DrugDoseTime> times = drug.getDoses();

        listAdapter = new ArrayAdapter<String>(this, R.layout.row, timesList);

        for (DrugDoseTime time: times) {
            listAdapter.add( Integer.toString(time.doseHour) + ':' + Integer.toString(time.doseMinutes) + " every " + Integer.toString(time.dayOfWeek) + " days ");
        }


        timeListView.setAdapter( listAdapter );
    }

    public void deleteDrug(View view) {
        drug.delete();
        Intent myIntent = new Intent(DrugDetails.this, Dashboard.class);
        DrugDetails.this.startActivity(myIntent);
    }

    public void addTimeActivity(View view) {
        Intent myIntent = new Intent(DrugDetails.this, AddTime.class);
        myIntent.putExtra("drug_name", drug.name);
        DrugDetails.this.startActivity(myIntent);
    }
}
