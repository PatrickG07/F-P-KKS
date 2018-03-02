package ch.fp.fp_kks;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditTextActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        mDatabaseHelper = new DatabaseHelper(this);

        FloatingActionButton btnBack = (FloatingActionButton) findViewById(R.id.btnBack);
        Button btnEdit = (Button) findViewById(R.id.btnEdit);

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
                Intent intent = new Intent(EditActivity.this, EditTextActivity.class);
                startActivity(intent);
            }
        });
    }
}
