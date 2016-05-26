package in.co.goshopper.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager lm;
    LocationListener locationListener;

    private List<ProductDetails> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    // private ProductsAdapter mAdapter;

    double lat , longi;

    private class MyLocationListener implements LocationListener{

        TextView place;


        @Override
        public void onLocationChanged(Location loc){

             lat = loc.getLatitude();
             longi =loc.getLongitude();
                if (loc != null){
                    Toast.makeText(getBaseContext(),"Location changed, Lat :"+lat+
                            "Lng:"+longi,Toast.LENGTH_SHORT).show();

                }
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            String statusString = "";
            switch (status) {
                case LocationProvider.AVAILABLE:
                    statusString = "available";
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    statusString = "Out of Service";
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    statusString = "Temporaily Unavailable";
            }
            Toast.makeText(getBaseContext(),
                    provider + " " + statusString,
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getBaseContext(),
                    "Provider :"+provider+
                    "Enabled",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String s) {
            Toast.makeText(getBaseContext(),
                    "Provider :"+s+
                            "Disabled",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView place=(TextView) findViewById(R.id.place);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);


       // mAdapter = new ProductsAdapter(productList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(mAdapter);

        prepareProductData();
        initializeAdapter();


        Context context=null;
        Geocoder gcd = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addressList = gcd.getFromLocation(
                    lat, longi, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                }
                sb.append(address.getLocality());



            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        //you can hard-code the lat & long if you have issues with getting it
        //remove the below if-condition and use the following couple of lines
        //double latitude = 37.422005;
        //double longitude = -122.084095




        if ( place!= null) {

            locationAddress locationAddress = new locationAddress();
            locationAddress.getAddressFromLocation(lat, longi,
                    getApplicationContext(), new GeocoderHandler());
        } else {
            showSettingsAlert();


        }






       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void prepareProductData() {
        ProductDetails details = new ProductDetails("Mad Max: Fury Road", "Action & Adventure", "2015","2.5");
        productList.add(details);

        details = new ProductDetails("Inside Out", "Animation, Kids & Family", "2015","");
        productList.add(details);

        details = new ProductDetails("Star Wars: Episode VII - The Force Awakens", "Action", "2015","");
        productList.add(details);

        details = new ProductDetails("Shaun the Sheep", "Animation", "2015","");
        productList.add(details);

        details = new ProductDetails("The Martian", "Science Fiction & Fantasy", "2015","");
        productList.add(details);

        details = new ProductDetails("Mission: Impossible Rogue Nation", "Action", "2015","");
        productList.add(details);

        details = new ProductDetails("Up", "Animation", "2009","");
        productList.add(details);

        details = new ProductDetails("Star Trek", "Science Fiction", "2009","");
        productList.add(details);




    }

    private void initializeAdapter(){
        ProductsAdapter adapter = new ProductsAdapter(productList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_near:
                Intent prodD = new Intent(MainActivity.this,ProductDetails.class);
                startActivity(prodD);
                break;
            case R.id.search_product:
                Intent prod = new Intent(MainActivity.this,ProductDetails.class);
                startActivity(prod);
                break;
            case R.id.action_settings:
                Intent setting = new Intent(MainActivity.this,setting.class);
                startActivity(setting);
                break;
            case R.id.search_shop:
                Intent shop = new Intent(MainActivity.this,shop.class);
                startActivity(shop);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        alertDialog.setTitle("Setting");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MainActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

}
