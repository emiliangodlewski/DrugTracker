package gcode.drugtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;

import java.lang.reflect.Array;
import java.util.List;

public class DrugDetails extends AppCompatActivity {

    String drugName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            drugName = extras.getString("drug_name");
        }

        List<Drug> drugList = Drug.find(Drug.class, "name = ?", drugName);
        Drug drug = drugList.get(0);
    }

    public void deleteDrug(View view) {
        Intent myIntent = new Intent(DrugDetails.this, Dashboard.class);
        DrugDetails.this.startActivity(myIntent);
    }
}
