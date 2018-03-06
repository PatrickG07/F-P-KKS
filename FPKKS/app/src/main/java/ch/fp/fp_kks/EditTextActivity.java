package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity for changing the Text in the Database
 */
public class EditTextActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    String newEntry1, newEntry2;

    EditText etQuestion, etAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        mDatabaseHelper = new DatabaseHelper(this);

        etQuestion = (EditText) findViewById(R.id.etQuestion2);
        etAnswer = (EditText) findViewById(R.id.etAncer2);

        Button btnDone = (Button) findViewById(R.id.btnDone2);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEntry1 = String.valueOf(etQuestion.getText());
                newEntry2 = String.valueOf(etAnswer.getText());

                mDatabaseHelper.getUpdate(newEntry1, newEntry1, Background.ids);

                Intent intent = new Intent(EditTextActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        Update();
    }

    /**
     * updating the Data in the database with the ID XXX
     */
    private void Update() {
        Cursor data = mDatabaseHelper.getEditData(Background.ids);

        while (data.moveToNext()) {
            etQuestion.setText(data.getString(1));
            etAnswer.setText(data.getString(2));
        }
    }
}
