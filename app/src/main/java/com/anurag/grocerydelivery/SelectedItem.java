package com.anurag.grocerydelivery;

import android.app.Person;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectedItem extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        Bitmap bitmap= this.getIntent().getParcelableExtra("BITMAP");
        ImageView imageView=findViewById(R.id.selelctedImage);
        imageView.setImageBitmap(bitmap);

        String itemName= getIntent().getStringExtra("NAME");
        setTitle(itemName);

        TextView Name=findViewById(R.id.itemName);
        Name.setText(itemName);

        String itemQuantity=getIntent().getStringExtra("QUANTITY");
        TextView Quantity= findViewById(R.id.itemQuantity);
        Quantity.setText(itemQuantity);

        String itemPrice=getIntent().getStringExtra("PRICE");
        TextView Price= findViewById(R.id.itemPrice);
        Price.setText(itemPrice);
        Details.price=itemPrice;
        Details.name=itemName;

        Button buy= findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SelectedItem.this,EnterAddress.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });





    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
