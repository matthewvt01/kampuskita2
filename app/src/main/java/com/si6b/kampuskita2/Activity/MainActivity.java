package com.si6b.kampuskita2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.si6b.kampuskita2.API.APIRequestsData;
import com.si6b.kampuskita2.API.RetroServer;
import com.si6b.kampuskita2.Adapter.AdapterKampus;
import com.si6b.kampuskita2.Model.ModelKampus;
import com.si6b.kampuskita2.Model.ModelResponse;
import com.si6b.kampuskita2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvKampus;
    private FloatingActionButton fabTambah;
    private ProgressBar pbKampus;
    private RecyclerView.Adapter adKampus;
    private RecyclerView.LayoutManager lmKampus;
    private List<ModelKampus> listkampus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvKampus = findViewById(R.id.rv_kampus);
        fabTambah = findViewById(R.id.fab_tambah);
        pbKampus = findViewById(R.id.pb_kampus);

        lmKampus = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvKampus.setLayoutManager(lmKampus);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveKampus();
    }

    public void retrieveKampus(){
        pbKampus.setVisibility(View.VISIBLE);

        APIRequestsData API = RetroServer.konekRetrofit().create(APIRequestsData.class);
        Call<ModelResponse> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listkampus = response.body().getData();

                adKampus = new AdapterKampus(MainActivity.this, listkampus);
                rvKampus.setAdapter(adKampus);
                adKampus.notifyDataSetChanged();

                pbKampus.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbKampus.setVisibility(View.GONE);

            }
        });

    }
}