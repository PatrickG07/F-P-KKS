package ch.fp.fp_kks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(etName.getText().toString().matches("")){
                    Toast.makeText(Create1Activity.this, "Definieren Sie einen Karteinamen!", Toast.LENGTH_SHORT).show();
                }else{
                    newEntry = String.valueOf(etName.getText());
                    mDatabaseHelper.addDataKartei(newEntry);
                    mDatabaseHelper.getSavedKartei(newEntry);

                    Background.text2 = false;
                    Intent intent = new Intent(Create1Activity.this, Create2Activity.class);
                    startActivity(intent);
                }

            }
        });
    }

    public void ceck(){

    }
}
