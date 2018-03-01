package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Create1 extends AppCompatActivity {

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

                Cursor newEntry2 = mDatabaseHelper.getSavedKartei(newEntry);
                //Background.ids = newEntry2.moveToLast();

                Intent intent = new Intent(Create1.this, Create2.class);
                startActivity(intent);
            }
        });
    }
}
