package com.example.star;



import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.star.adapter.StarAdapter;
import com.example.star.beans.Star;
import com.example.star.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarService service;
    private StarAdapter starAdapter = null;
    private Toolbar myToolbar;

    public ListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        stars = service.findAll();
        starAdapter = new StarAdapter(this, stars);
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void filterList(String s) {
        List<Star> filtredList = new ArrayList<>();
        for (Star star : stars) {
            if (star.getName().toLowerCase().startsWith(s.toLowerCase().trim())) {
                filtredList.add(star);
            }

            starAdapter.setFiltredList(filtredList);
        }
    }

    public void init() {
        service.create(new Star("kate bosworth", R.mipmap.kate, 3.5f));
        service.create(new Star("george clooney", R.mipmap.george, 3));
        service.create(new Star("michelle rodriguez",
                R.mipmap.michelle, 5));
        service.create(new Star("Ilyass bennane", R.mipmap.ilyass, 5));
        service.create(new Star("Youssef erasekh", R.mipmap.youssef, 2));
        service.create(new Star("Fat mizzo", R.mipmap.fat, 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        return true;
    }


}