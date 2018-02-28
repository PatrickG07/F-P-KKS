package ch.fp.fp_kks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mDatabaseHelper = new DatabaseHelper(this);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mDatabaseHelper.getUpdate();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mDatabaseHelper.deleteDate();
            }
        });
    }
}
