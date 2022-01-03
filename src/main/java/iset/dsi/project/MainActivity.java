package iset.dsi.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter myAdapter;
    ArrayList<Produit> list;
    Button btn_edit , btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.produitList);
        btn_edit=findViewById(R.id.edit_btn);
        btn_delete=findViewById(R.id.delete_btn);

        databaseReference=FirebaseDatabase.getInstance().getReference("Produit");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list= new ArrayList<>();
        myAdapter= new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Produit prod =dataSnapshot.getValue(Produit.class);
                    list.add(prod);
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floc: {
                Intent i = new Intent(this,LocationActivity.class);
                startActivity(i);
                break;
            }
            case R.id.fadd: {
                Intent i = new Intent(this,AddProduitActivity.class);
                startActivity(i);
                break;
            }
            case R.id.edit_btn: {
                Intent i = new Intent(this,EditProduitActivity.class);
                startActivity(i);
                break;
            }

        }
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