package cl.aguzman.prueba2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cl.aguzman.prueba2.models.Gift;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        long idGift = getIntent().getLongExtra("gift",0);
        Gift rowId = Gift.findById(Gift.class, idGift);

        TextView nameDescriptionTv = (TextView) findViewById(R.id.nameContentTv);
        TextView priceDescriptionTv = (TextView) findViewById(R.id.priceContenttv);
        TextView priorityDescriptionTv = (TextView) findViewById(R.id.priorityContentTv);
        TextView descriptionContentTv = (TextView) findViewById(R.id.descriptionContentTv);

        nameDescriptionTv.setText(rowId.getName());
        priceDescriptionTv.setText("$ "+String.valueOf(rowId.getPrice()));
        priorityDescriptionTv.setText(String.valueOf(rowId.getPriority()));
        descriptionContentTv.setText(rowId.getDescription());
        getSupportActionBar().setTitle(rowId.getName());
    }
}
