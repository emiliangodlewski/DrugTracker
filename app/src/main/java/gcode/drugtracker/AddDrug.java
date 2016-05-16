package gcode.drugtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddDrug extends AppCompatActivity {

    EditText formName;
    EditText formDescription;
    String formNameText;
    String formDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);
    }

    public void addDrug(View view) {
        formName = (EditText)findViewById(R.id.formName);
        formDescription = (EditText)findViewById(R.id.formDescription);

        formNameText = formName.getText().toString();
        formDescriptionText = formDescription.getText().toString();

        Drug drug = new Drug(formNameText, formDescriptionText);
        drug.save();

        Intent myIntent = new Intent(AddDrug.this, Dashboard.class);
        AddDrug.this.startActivity(myIntent);
    }
}
