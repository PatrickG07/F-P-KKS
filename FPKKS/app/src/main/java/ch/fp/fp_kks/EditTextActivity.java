package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTextActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    String newEntry1, newEntry2;

    EditText etQuestion, etAncer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        mDatabaseHelper = new DatabaseHelper(this);

        etQuestion = (EditText) findViewById(R.id.etQuestion2);
        etAncer = (EditText) findViewById(R.id.etAncer2);

        Button btnDone = (Button) findViewById(R.id.btnDone2);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEntry1 = String.valueOf(etQuestion.getText());
                newEntry2 = String.valueOf(etAncer.getText());

                mDatabaseHelper.getUpdate(newEntry1, newEntry1, Background.ids);

                Intent intent = new Intent(EditTextActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Update();
    }

    private void Update(){


        System.out.println("Text12345678dsalfsdlafjlsdajf6löasdjflöasjflasjdflökjas6dfölkj");


        Cursor data = mDatabaseHelper.getEditData(Background.ids);

        while (data.moveToNext()) {
            etQuestion.setText(data.getString(1));
            etAncer.setText(data.getString(2));
        }
    }
}
