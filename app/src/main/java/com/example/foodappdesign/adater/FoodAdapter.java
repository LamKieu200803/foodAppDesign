package com.example.foodappdesign.adater;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodappdesign.R;
import com.example.foodappdesign.modal.Food;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{
    List<Food> list;
    View view;

    public IclickDetail clickItem;
    public   interface  IclickDetail{
        void sua(Food food);
        void delete(Food food);
        void detailFood(Food food);
    }
    public FoodAdapter(IclickDetail clickItem) {

        this.clickItem = clickItem;
    }
    public  void setData(List<Food>foodList){
        this.list=foodList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final Food food=list.get(position);
        if(food==null)
            return;
        holder.tv_Price.setText(food.getPrice()+"$");
        holder.tv_Title.setText(food.getTitle());
        Picasso.get().load(food.getImage()).into(holder.img_Food);
        int discount=food.getDiscount();
        if(discount!=0){
            holder.tv_Discount.setVisibility(View.VISIBLE);
            holder.tv_Discount.setText("Giảm"+" "+food.getDiscount()+"%");
            holder.tv_Price_Discount.setVisibility(View.VISIBLE);
            holder.tv_Price_Discount.setText(food.totalMoney()+"");
            holder.tv_Price.setPaintFlags(holder.tv_Price.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tv_Price.setTextColor(view.getResources().getColor(R.color.del_text));
            holder.tv_Price_Discount.setTextSize((int)16);
            holder.tv_Price.setTextSize((int)12);
            holder.tv_Price_Discount.setTextColor(view.getResources().getColor(R.color.no_del));


        }
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.sua(food);
            }
        });
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.delete(food);
            }

            ;});
        holder.img_Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.detailFood(food);
            }
        });

    }

    public void delete(Food food) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list food");

        myRef.child(String.valueOf(food.getId())).removeValue(new DatabaseReference.CompletionListener(){
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(view.getContext(), "ffff", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    public  class FoodViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Title,tv_Price,tv_Discount,tv_Price_Discount;
        Button btndelete,btnSua;
        ImageView img_Food;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Title=itemView.findViewById(R.id.tv_title);
            tv_Price=itemView.findViewById(R.id.tv_price_root);
            img_Food=itemView.findViewById(R.id.img_food);
            tv_Discount=itemView.findViewById(R.id.tv_discount);
            tv_Price_Discount=itemView.findViewById(R.id.tv_price_discount);
            btndelete=view.findViewById(R.id.btnXoa);
            btnSua=view.findViewById(R.id.btnSua);
        }
    }
}
