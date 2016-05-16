package gcode.drugtracker;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    private ListView drugListView ;
    private ArrayAdapter<String> listAdapter ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drugListView = (ListView) findViewById( R.id.drugList );

        ArrayList<String> drugList = new ArrayList<String>();
        Select drugQuery = Select.from(Drug.class);
        List<Drug> drugs = drugQuery.list();

        listAdapter = new ArrayAdapter<String>(this, R.layout.row, drugList);

        for (Drug drug: drugs) {
            listAdapter.add( drug.name );
        }


        drugListView.setAdapter( listAdapter );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void goToAddDrugActivity(View view) {
        Intent myIntent = new Intent(Dashboard.this, AddDrug.class);
        Dashboard.this.startActivity(myIntent);
    }
}
