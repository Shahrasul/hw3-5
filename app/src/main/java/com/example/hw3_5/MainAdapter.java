package com.example.hw3_5;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    List<Title> list;
    OnItemClickListener onItemClickListener;
    Context context;

    public MainAdapter(List<Title> list, Context context,OnItemClickListener onItemClickListener) {
        this.list = list;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }
    public void addApplication(Title title){
        list.add(title);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.onBind(list.get(position));
        holder.textMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(context,holder.textMenu);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_save:
                                Toast.makeText(context,"Saved",Toast.LENGTH_LONG).show();
                                break;

                            case R.id.menu_delete:
                                list.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtLastName;
        TextView txtAge;
        TextView txtGroup;
        TextView textMenu;
        ImageView imageView;
        Title title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName= itemView.findViewById(R.id.txtName);
            txtLastName= itemView.findViewById(R.id.txtLastName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtGroup = itemView.findViewById(R.id.txtGroup);
            imageView = itemView.findViewById(R.id.image);
            textMenu = itemView.findViewById(R.id.txtOptionMenu);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });

        }
        public void onBind(Title title){
            this.title = title;
            txtName.setText(title.name);
            txtLastName.setText(title.lastName);
            txtAge.setText(title.age);
            txtGroup.setText(title.group);
            imageView.setImageURI(Uri.parse(title.imageView));
        }
    }
}

