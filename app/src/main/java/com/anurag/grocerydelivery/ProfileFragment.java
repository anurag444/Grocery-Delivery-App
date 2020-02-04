package com.anurag.grocerydelivery;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Context;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    EditText personName,personAge;
    Button save;
    String name;
    String age;
    ImageView profile;
    private static final int PICK_IMAGE=1;
    Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile,container,false);

        personName=view.findViewById(R.id.personName);
        personAge=view.findViewById(R.id.personAge);
        save = view.findViewById(R.id.saveDetails);
        profile=view.findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery= new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"Select Picture"),PICK_IMAGE);
            }
        });

        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        String string1=sharedPreferences.getString("NAME",name);
        personName.setText(string1);
        String string2=sharedPreferences.getString("AGE",age);
        personAge.setText(string2);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personAge.getText().toString().equals("")||
                personName.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Cannot Save Empty Details", Toast.LENGTH_SHORT).show();
                }else {
                    name = personName.getText().toString();
                    age = personAge.getText().toString();

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("NAME", name);
                    editor.putString("AGE", age);
                    editor.apply();
                    Toast.makeText(getContext(), "Details Saved", Toast.LENGTH_LONG).show();

                }

            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    if (requestCode==PICK_IMAGE&&resultCode==RESULT_OK){
        imageUri=data.getData();
        try{
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),imageUri);
            profile.setImageBitmap(bitmap);
        }catch (Exception e){

            e.printStackTrace();
        }
    }
    }
}
