package com.anurag.grocerydelivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddressFragment extends Fragment {

    private Button save;
    EditText name,address,state,country;
    String edtname,edtaddress,edtstate,edtcountry;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view= inflater.inflate(R.layout.fragment_address,container,false);
        name=view.findViewById(R.id.addressName);
        address=view.findViewById(R.id.address);
        state= view.findViewById(R.id.addressState);
        country=view.findViewById(R.id.addressCountry);
        save=view.findViewById(R.id.save);

        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        String string1=sharedPreferences.getString("NAME",edtname);
        name.setText(string1);
        String string2=sharedPreferences.getString("ADDRESS",edtaddress);
        address.setText(string2);
        String string3=sharedPreferences.getString("STATE",edtstate);
        state.setText(string3);
        String string4=sharedPreferences.getString("COUNTRY",edtcountry);
        country.setText(string4);


save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {





        if (name.getText().toString().equals("") ||
                address.getText().toString().equals("") ||
                state.getText().toString().equals("") ||
                country.getText().toString().equals("")) {
            Toast.makeText(getContext(), "Cannot Save Empty Details", Toast.LENGTH_SHORT).show();
        } else {
            edtname = name.getText().toString();
            edtaddress = address.getText().toString();
            edtstate = state.getText().toString();
            edtcountry = country.getText().toString();

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("NAME", edtname);
            editor.putString("ADDRESS", edtaddress);
            editor.putString("STATE", edtstate);
            editor.putString("COUNTRY", edtcountry);
            editor.apply();
            Toast.makeText(getContext(), "Details Saved", Toast.LENGTH_LONG).show();
        }

    }
});


        return view;

}
    }
