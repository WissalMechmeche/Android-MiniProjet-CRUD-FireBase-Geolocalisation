package iset.dsi.project;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOProduit
{
    private DatabaseReference databaseReference ;

    public DAOProduit() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Produit.class.getSimpleName());
    }

    public Task<Void> add(Produit prod)
    {
        //if(prod ==null )
        return databaseReference.push().setValue(prod);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap)
    {
        //if(prod ==null )
        return  databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key)
    {
        return  databaseReference.child(key).removeValue();
    }

}
