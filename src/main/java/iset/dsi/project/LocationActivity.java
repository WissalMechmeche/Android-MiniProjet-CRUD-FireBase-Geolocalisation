package iset.dsi.project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {

    Button btn ;
    TextView txt1,txt2,txt3,txt4,txt5;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        btn=findViewById(R.id.location_btn);
        txt1=findViewById(R.id.TV1);
        txt2=findViewById(R.id.TV2);
        txt3=findViewById(R.id.TV3);
        txt4=findViewById(R.id.TV4);
        txt5=findViewById(R.id.TV5);

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(LocationActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                {
                    getLocation();
                }
                else {
                    ActivityCompat.requestPermissions(LocationActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }

            }
        });

    }

    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location= task.getResult();
                if(location!=null)
                {
                    try {
                    Geocoder geocoder= new Geocoder(LocationActivity.this, Locale.getDefault());

                        List<Address> addresses= geocoder.getFromLocation(
                                location.getLatitude(),location.getLongitude(),1);
                        //Set Latitude
                        txt1.setText(Html.fromHtml("<font ><b>Latitude : </b><br></font>"
                        +addresses.get(0).getLatitude()));
                        //Set Longitude
                        txt2.setText(Html.fromHtml("<font ><b>Longitude : </b><br></font>"
                                +addresses.get(0).getLongitude()));
                        //Set Country Name
                        txt3.setText(Html.fromHtml("<font ><b>Country Name : </b><br></font>"
                                +addresses.get(0).getCountryName()));
                        //Set Locality
                        txt4.setText(Html.fromHtml("<font ><b>Locality : </b><br></font>"
                                +addresses.get(0).getLocality()));
                        //Set address
                        txt5.setText(Html.fromHtml("<font ><b>Address : </b><br></font>"
                                +addresses.get(0).getAddressLine(0)));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_item:
                Intent k = new Intent(this, MainActivity.class);
                startActivity(k);
                return true;
            case R.id.add_item:
                Intent i = new Intent(this,AddProduitActivity.class);
                startActivity(i);
                return true;
            case R.id.location_item:
                Intent f = new Intent(this,LocationActivity.class);
                startActivity(f);
                return true;
            case R.id.exit_item:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
