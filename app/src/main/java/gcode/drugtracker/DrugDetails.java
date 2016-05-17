package gcode.drugtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

public class DrugDetails extends AppCompatActivity {

    String drugName;
    TextView drugTitle;
    TextView drugAbout;
    Drug drug;

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

        drugTitle = (TextView)findViewById(R.id.drugTitle);
        drugAbout = (TextView)findViewById(R.id.drugAbout);

        drugTitle.setText(drug.name);
        drugAbout.setText(drug.description);
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
