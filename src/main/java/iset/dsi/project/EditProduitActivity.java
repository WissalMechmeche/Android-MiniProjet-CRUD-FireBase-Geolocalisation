package iset.dsi.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class EditProduitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_produit);
        // initializing all our variables.
        final EditText name = findViewById(R.id.name);
        final EditText price = findViewById(R.id.price);
        Button EditProduitBtn = findViewById(R.id.edit_btn);
        DAOProduit dao = new DAOProduit();

        EditProduitBtn.setOnClickListener(v -> {
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("name",name.getText().toString());
            hashMap.put("price",price.getText().toString());
            dao.update("-MsSE6jwsv8ARP_zXziu",hashMap)
                    .addOnSuccessListener(suc -> {
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                        Intent j = new Intent(this, MainActivity.class);
                        startActivity(j);
                    })
                    .addOnFailureListener(er ->
                    {Toast.makeText(getApplicationContext(), er.getMessage(), Toast.LENGTH_LONG).show(); });
        });
    }
}
