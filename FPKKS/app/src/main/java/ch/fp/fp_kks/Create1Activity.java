package ch.fp.fp_kks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Create1Activity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    String newEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create1);

        mDatabaseHelper = new DatabaseHelper(this);

        Button btnNext = (Button) findViewById(R.id.btnNext);

        final EditText etName = (EditText) findViewById(R.id.etName);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newEntry = String.valueOf(etName.getText());
                mDatabaseHelper.addDataKartei(newEntry);

                System.out.println("Data Insertet");
                System.out.println("Data = " + newEntry);
                mDatabaseHelper.getSavedKartei(newEntry);

                System.out.println("ID = " + Background.ids);
                Intent intent = new Intent(Create1Activity.this, Create2Activity.class);
                startActivity(intent);
            }
        });
    }
}
