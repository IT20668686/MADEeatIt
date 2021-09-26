package com.example.myapplication.ui.discount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DiscountHelperClass;
import com.example.myapplication.EditDiscount;
import com.example.myapplication.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDiscountBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DiscountFragment extends Fragment {

    private DiscountViewModel discountViewModel;
    private FragmentDiscountBinding binding;
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    private Button btnEdit;
    ArrayList<DiscountHelperClass> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDiscountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       recyclerView = root.findViewById(R.id.userList);
       database= FirebaseDatabase.getInstance("https://mad-eatit-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("discount");
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       list=new ArrayList<>();
       myAdapter=new MyAdapter(this,list);
       recyclerView.setAdapter(myAdapter);
       database.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                  DiscountHelperClass helperClass=dataSnapshot.getValue(DiscountHelperClass.class);
                  list.add(helperClass);
               }
               myAdapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

           }
       });
      /* btnEdit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //Intent intent = new Intent(DiscountFragment.this,EditDiscount.class);
               //startActivity(intent);
           }
       });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}