package com.example.joille;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InitialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InitialFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    ArrayList<Services> services = new ArrayList<Services>();
    private RecyclerView rvService;
    private ServicesAdapter servicesAdapter;


    // TODO: Rename and change types of parameters

    public InitialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment InitialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InitialFragment newInstance() {
        InitialFragment fragment = new InitialFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_initial, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        rvService = getActivity().findViewById(R.id.rvService);
        servicesAdapter = new ServicesAdapter(services);
        RecyclerView.LayoutManager layout =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvService.setLayoutManager(layout);
        rvService.setAdapter(servicesAdapter);

        getServices();
    }

    private void getServices() {
        Call<List<Services>> call = RetrofitClient.getInstance().getMyApi().getServices();
        call.enqueue(new Callback<List<Services>>() {
            @Override
            public void onResponse(Call<List<Services>> call, Response<List<Services>> response) {
                services.clear();
                services.addAll(response.body());
                servicesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Services>> call, Throwable t) {
                Toast.makeText(getContext(), "Falha na comunicação com o servidor: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("test", "onFailure: "+ t.toString());
            }
        });
    }


        }