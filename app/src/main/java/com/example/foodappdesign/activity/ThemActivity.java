package com.example.foodappdesign.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodappdesign.R;
import com.example.foodappdesign.modal.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.InternalHelpers;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public  class ThemActivity extends AppCompatActivity {
   EditText ten,giam,anh,gia,noidung,so,soluong,user,delete;
    Button btnThem;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_them);
        ten=findViewById(R.id.description);
        giam=findViewById(R.id.discount);
        anh=findViewById(R.id.image);
        gia=findViewById(R.id.price);
        noidung=findViewById(R.id.title);
        so=findViewById(R.id.ed_id);
        btnThem=findViewById(R.id.butThem);
       soluong=findViewById(R.id.amountBuy2);
        user=findViewById(R.id.userId2);
        delete=findViewById(R.id.idDelete2);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(so.getText().toString().trim());
               String description=ten.getText().toString().trim();
                int discount=Integer.parseInt(giam.getText().toString().trim());
               String title=noidung.getText().toString().trim();
               String image=anh.getText().toString().trim();
                int price=Integer.parseInt(gia.getText().toString().trim());
                int amountBuy=Integer.parseInt(soluong.getText().toString().trim());
                int userId=Integer.parseInt(user.getText().toString().trim());
                int idDelete=Integer.parseInt(delete.getText().toString().trim());
              Food food=new Food(id,title,image,description,discount,price,amountBuy,userId,idDelete);
                push(food);


    }
    public void push(Food food) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list food");
        String object=String.valueOf(food.getId());
        myRef.child(object).setValue(food,new DatabaseReference.CompletionListener(){

            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(ThemActivity.this, "ffff", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void delete(){

    }
});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","onDestroy");
    }
}
