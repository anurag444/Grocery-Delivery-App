package com.anurag.grocerydelivery;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.P;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textView;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    GroceryAdapter adapter;
    List<Grocery> groceryList;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout= findViewById(R.id.drawer_layout);

        NavigationView navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        groceryList= new ArrayList<>();
        recyclerView=findViewById(R.id.reccyclerview);

            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groceryList.add(new Grocery("Chakki Fresh Atta","10kg",R.drawable.atta,"Rs.250"));
        groceryList.add(new Grocery("Sunsilk Shampoo","250ml",R.drawable.sunsilkshampoo,"Rs.149"));

        groceryList.add(new Grocery("Colgate Max Fresh","300g",R.drawable.maxfresh,"Rs.90"));
        groceryList.add(new Grocery("Tata Garam Masala","100g",R.drawable.garammasala,"Rs.72"));
        groceryList.add(new Grocery("SaveMore Dishwash Gel","500ml",R.drawable.dishwashgel,"Rs.60"));
        groceryList.add(new Grocery("Beer Shampoo","190ml",R.drawable.beershampoo,"Rs.99"));
        groceryList.add(new Grocery("HaveMore Atta Cookie","200g",R.drawable.attacookie,"Rs.75"));
        groceryList.add(new Grocery("Bru Coffee","100g",R.drawable.brucoffee,"Rs.132"));
        groceryList.add(new Grocery("Detergent Powder","5kg",R.drawable.detergentpowder,"Rs.200"));


        adapter=new GroceryAdapter(this,groceryList);
        recyclerView.setAdapter(adapter);

        exit=findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit");
                builder.setMessage("Do you want to exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });


        adapter.setOnItemClickListener(new GroceryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Grocery tappedView =groceryList.get(position);
                Intent intent= new Intent(MainActivity.this,SelectedItem.class);
                intent.putExtra("NAME",tappedView.getTitle());
                intent.putExtra("QUANTITY",tappedView.getQuantity());
                intent.putExtra("PRICE",tappedView.getPrice());

                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),tappedView.getImage());
                intent.setClass(MainActivity.this,SelectedItem.class);
                intent.putExtra("BITMAP",bitmap);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();

                break;
            case R.id.nav_address:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddressFragment()).commit();
                break;
            case R.id.nav_order:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OrderFragment()).commit();
                break;
            case R.id.nav_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ShareFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
