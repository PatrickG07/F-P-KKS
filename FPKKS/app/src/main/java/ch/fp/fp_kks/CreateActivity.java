package ch.fp.fp_kks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText tvText1;
    EditText tvText2;
    DatabaseHelper mDatabaseHelper;

    String newEntry1, newEntry2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mDatabaseHelper = new DatabaseHelper(this);

        tvText1 = (EditText) findViewById(R.id.tvText1);
        tvText2 = (EditText) findViewById(R.id.tvText2);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnNew = (Button) findViewById(R.id.btnNew);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEntry1 = String.valueOf(tvText1.getText());
                newEntry2 = String.valueOf(tvText2.getText());
                mDatabaseHelper.addData(newEntry1, newEntry2);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.sSpinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Item1");
        categories.add("Item2");
        categories.add("Item3");
        categories.add("Item4");
        categories.add("Item5");
        categories.add("Item6");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
