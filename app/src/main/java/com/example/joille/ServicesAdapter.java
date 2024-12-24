package com.example.joille;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


import java.util.ArrayList;
public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    ArrayList<Services> services;
    Context context;
    public ServicesAdapter(ArrayList<Services> services) {
        this.services = services;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvServiceName;
        final TextView tvServiceDescription;
        final TextView tvServiceValue;
        final LinearLayout LayoutService;

        public ViewHolder(View view) {
            super(view);
            LayoutService = (LinearLayout) view.findViewById(R.id.LayoutService);
            tvServiceName = (TextView) view.findViewById(R.id.tvServiceName);
            tvServiceDescription = (TextView) view.findViewById(R.id.tvServiceDescription);
            tvServiceValue = (TextView) view.findViewById(R.id.tvServiceValue);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        if (view == null) {
            Log.e("RecyclerView", "Erro ao inflar o layout item_list");
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (services == null || services.isEmpty()) {
            // Se a lista estiver vazia ou nula, você pode exibir uma mensagem de erro ou uma tela de "sem dados"
            return;
        }

        context = holder.tvServiceName.getContext();
        Services service = services.get(position);
        holder.tvServiceName.setText(service.getName());
        holder.tvServiceDescription.setText(service.getDescription());
        holder.tvServiceValue.setText("R$ " + service.getValue());

        holder.LayoutService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                FragmentServicesDetail fragmentServicesDetail = FragmentServicesDetail.newInstance(service.getId());
                fragmentTransaction.replace(R.id.fragmentContainerView, fragmentServicesDetail);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }



    @Override
    public int getItemCount() {
        return services.size();
    }


}
