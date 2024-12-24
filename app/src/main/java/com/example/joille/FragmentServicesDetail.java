package com.example.joille;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentServicesDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentServicesDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";

    // TODO: Rename and change types of parameters


    private int id;
    Services service;
    private View fragmentView;
    public FragmentServicesDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentServicesDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentServicesDetail newInstance(int id) {
        FragmentServicesDetail fragment = new FragmentServicesDetail();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);  // Corrigir para passar o valor do id
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_services_detail, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderServices();
    }

    private void renderServices() {
        Call<List<Services>> call = RetrofitClient.getInstance().getMyApi().getServicesDescription(id);
        call.enqueue(new Callback<List<Services>>() {
            @Override
            public void onResponse(Call<List<Services>> call, Response<List<Services>> response) {

                    List<Services> services = response.body();
                    Services service = services.get(0);

                    TextView tvName = fragmentView.findViewById(R.id.tvServiceName);
                    TextView tvDescription = fragmentView.findViewById(R.id.tvServiceDescription);
                    TextView tvValue = fragmentView.findViewById(R.id.tvServiceValue);

                    tvName.setText(service.getName());
                    tvDescription.setText(service.getDescription());
                    tvValue.setText("R$ " + service.getValue());
                }

            @Override
            public void onFailure(Call<List<Services>> call, Throwable t) {

            }

        });
}

    private void deleteServices() {
        Call<Services> call = RetrofitClient.getInstance().getMyApi().deleteService(id);
        call.enqueue(new Callback<Services>() {
            @Override
            public void onResponse(Call<Services> call, Response<Services> response) {
                if(response.isSuccessful()){
                    service = response.body();
                    Toast.makeText(requireContext(), "Sucesso ao deletar. Redirecionando...", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    InitialFragment initialFragment = InitialFragment.newInstance();
                    fragmentTransaction.replace(R.id.fragmentContainerView, initialFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else{
                    Toast.makeText(requireContext(), "Erro deletar...", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Services> call, Throwable t) {

            }
        });
        {

        }


    }


}