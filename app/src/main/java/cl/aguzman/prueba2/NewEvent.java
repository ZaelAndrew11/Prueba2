package cl.aguzman.prueba2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cl.aguzman.prueba2.models.Gift;

public class NewEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gift);

        //Views
        nameEditText = (EditText) findViewById(R.id.namePersonEdit);
        descriptionEditText = (EditText) findViewById(R.id.descriptionGiftEdit);
        priceEditText = (EditText) findViewById(R.id.priceGiftEdit);
        priorityOptions = (Spinner) findViewById(R.id.priorityOptions);

        Integer[] items = new Integer[]{1,2,3};
        ArrayAdapter<Integer> adaptaer = new ArrayAdapter<Integer>(this, android.R.layout.select_dialog_item, items);
        priorityOptions.setAdapter(adaptaer);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String name = nameEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        if(name.length() > 0 && description.length() > 0){
            String priceString = priceEditText.getText().toString().trim();
            int price = Integer.parseInt(priceString);
            int priority = priorityOptions.getSelectedItem().hashCode();
            Gift newData = new Gift(name, description, price, priority, false);
            newData.save();
            Toast.makeText(this, "Guardado exitosamente.", Toast.LENGTH_SHORT).show();
        }
    }

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText priceEditText;
    private  Spinner priorityOptions;
}
