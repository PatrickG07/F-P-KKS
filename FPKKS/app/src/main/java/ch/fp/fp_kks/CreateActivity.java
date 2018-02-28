package ch.fp.fp_kks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateActivity extends AppCompatActivity {

    EditText tvText1;
    EditText tvText2;
    DatabaseHelper mDatabaseHelper;

    String newEntry1, newEntry2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mDatabaseHelper = new DatabaseHelper(this);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnNew = (Button) findViewById(R.id.btnNew);

        tvText1 = (EditText) findViewById(R.id.tvText1);
        tvText2 = (EditText) findViewById(R.id.tvText2);

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
    }
}
