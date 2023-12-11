package com.example.moonbecafe.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonbecafe.Model.Menu.DataMenu;
import com.example.moonbecafe.Model.Request.OrderItem;
import com.example.moonbecafe.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolder> {
    private List<DataMenu> menus;
    private List<OrderItem> orderItems;
    private Context context;

    public AdapterMenu(List<DataMenu> menus, Context context) {
        this.menus = menus;
        this.context  = context;
        orderItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View hasil = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyecleviewdashmenu, parent, false);
        return new ViewHolder(hasil);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataMenu dataMenu = menus.get(position);
        holder.nama.setText(dataMenu.getName());
        holder.harga.setText("Rp."+dataMenu.getPrice());
        holder.idmenu.setText(dataMenu.getMenuid());
        Picasso.get()
                .load("http://192.168.62.149/MoonBeCafe-master/storage/app/public/menu/"+dataMenu.getImage())
                .into(holder.gambar);
        holder.textViewQuantity.setText("0");
        holder.pesan.setOnCheckedChangeListener(null);
        holder.pesan.setChecked(isItemSelected(dataMenu));
        holder.pesan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int jumlah = Integer.parseInt(holder.textViewQuantity.getText().toString());
                            jumlah++;
                            holder.textViewQuantity.setText(String.valueOf(jumlah));
                            int id = Integer.parseInt(dataMenu.getMenuid());
                            OrderItem orderItem = new OrderItem(id, jumlah);
                            orderItems.add(orderItem);
                        }
                    });

                    holder.buttonSubtract.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int jumlah = Integer.parseInt(holder.textViewQuantity.getText().toString());
                            if (jumlah > 0){
                                jumlah--;
                            }
                            holder.textViewQuantity.setText(String.valueOf(jumlah));
                            if (jumlah >= 1){
                                int id = Integer.parseInt(dataMenu.getMenuid());
                                OrderItem orderItem = new OrderItem(id,jumlah);
                                orderItems.add(orderItem);
                            }
                        }
                    });

                }else{
                    OrderItem orderItem = findOrderItem(Integer.parseInt(dataMenu.getMenuid()));
                    if(orderItem != null){
                        orderItems.remove(orderItem);
                    }
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView nama , harga, idmenu, jumlah, textViewQuantity;
        ImageButton addbtn, removebtn;
        EditText editjmlh;
        CheckBox pesan;
        Button buttonAdd, buttonSubtract, btnpesan;
        public ViewHolder(@NonNull View hasil) {
            super(hasil);
            gambar = hasil.findViewById(R.id.gambarmenu);
            nama = hasil.findViewById(R.id.tittlemenugambar);
            harga = hasil.findViewById(R.id.titleharga);
            idmenu = hasil.findViewById(R.id.idmenu);
            jumlah = hasil.findViewById(R.id.jumlah);
            buttonAdd = hasil.findViewById(R.id.buttonAdd);
            buttonSubtract = hasil.findViewById(R.id.buttonSubtract);
            textViewQuantity = hasil.findViewById(R.id.textViewQuantity);
            btnpesan = hasil.findViewById(R.id.btnpesan);
            pesan = hasil.findViewById(R.id.add);
        }
    }
    public List<OrderItem> getOrderItems(){
        return orderItems;
    }

    private boolean isItemSelected(DataMenu dataMenu) {
        for (OrderItem orderItem : orderItems){
            int id = Integer.parseInt(dataMenu.getMenuid());
            if (orderItem.getMenu() == id){
                return  true;
            }
        }
        return false;
    }

    private OrderItem findOrderItem(int menuid) {
        for(OrderItem orderItem : orderItems){
            if (orderItem.getMenu() == menuid){
                return orderItem;
            }
        }
        return null;
    }
}

