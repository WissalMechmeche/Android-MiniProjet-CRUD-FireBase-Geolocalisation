package iset.dsi.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddProduitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_produit);
        final EditText name = findViewById(R.id.name);
        final EditText price = findViewById(R.id.price);
        //final EditText image = findViewById(R.id.image);
        Button addProduitBtn = findViewById(R.id.add_btn);

        DAOProduit dao = new DAOProduit();
        addProduitBtn.setOnClickListener(v -> {
            Produit prod = new Produit(name.getText().toString(), price.getText().toString());
            dao.add(prod)
                    .addOnSuccessListener(suc -> {
                        Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(this, MainActivity.class);
                        startActivity(i);

                    }).addOnFailureListener(er -> {
                        Toast.makeText(getApplicationContext(), er.getMessage(), Toast.LENGTH_LONG).show();
                    });
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
