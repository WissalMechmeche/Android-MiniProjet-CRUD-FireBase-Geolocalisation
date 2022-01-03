package iset.dsi.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class DeleteProduitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializing all our variables.
        final EditText name = findViewById(R.id.name);
        final EditText price = findViewById(R.id.price);
        //final EditText image = findViewById(R.id.image);
        Button DeleteProduitBtn = findViewById(R.id.delete_btn);
        DAOProduit dao = new DAOProduit();

        DeleteProduitBtn.setOnClickListener(v -> {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", name.getText().toString());
            hashMap.put("price", price.getText().toString());
            dao.remove("-MsMAEup7x25ikMPMhd2")
                    .addOnSuccessListener(suc -> {
                        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(this, MainActivity.class);
                        startActivity(i);
                    })
                    .addOnFailureListener(er ->
                    {
                        Toast.makeText(getApplicationContext(), er.getMessage(), Toast.LENGTH_LONG).show();
                    });
        });
    }
}
