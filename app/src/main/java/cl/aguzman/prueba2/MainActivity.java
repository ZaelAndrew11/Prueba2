package cl.aguzman.prueba2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cl.aguzman.prueba2.adapters.ClickGift;
import cl.aguzman.prueba2.adapters.GiftsAdapter;
import cl.aguzman.prueba2.data.Queries;
import cl.aguzman.prueba2.models.Gift;

public class MainActivity extends AppCompatActivity implements ClickGift{

    public List<Gift> listGifts;
    public GiftsAdapter adapter;
    private RecyclerView giftsRv;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_bought) {
            filter("1");
            getSupportActionBar().setTitle(R.string.action_bought);
        }else if(id == R.id.action_pending){
            filter("0");
            getSupportActionBar().setTitle(R.string.action_pending);
        }

        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOverflowIcon(ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_filter_list_white_24dp, null));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewEvent.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(R.string.app_name);
        listGifts = new Queries().gifts();
        giftsRv = (RecyclerView) findViewById(R.id.listGiftsRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        giftsRv.setLayoutManager(linearLayoutManager);
        giftsRv.setHasFixedSize(true);
        adapter = new GiftsAdapter(listGifts, this);
        giftsRv.setAdapter(adapter);
    }


    //Method Interface
    @Override
    public void clickContent(long id) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("gift", id);
        startActivity(intent);
    }

    @Override
    public void removeGift(long id) {
        Gift listGiftsChange = Gift.findById(Gift.class, id);
        if(listGiftsChange.isBought() == false){
            listGiftsChange.setBought(true);
            Toast.makeText(this, R.string.gift_move_buy, Toast.LENGTH_SHORT).show();
        }else{
            listGiftsChange.setBought(false);
            Toast.makeText(this, R.string.gift_move_pendings, Toast.LENGTH_SHORT).show();
        }

        listGiftsChange.save();
        giftsRv.scrollToPosition(listGifts.size()-1);
    }
    //Method Interface


    public  void filter(String doneVal){
        List<Gift> listBought = new  Queries().giftsbought(doneVal);
        GiftsAdapter adapter = new GiftsAdapter(listBought, this);
        giftsRv.setAdapter(adapter);
        giftsRv.scrollToPosition(listBought.size()-1);
    }

}
