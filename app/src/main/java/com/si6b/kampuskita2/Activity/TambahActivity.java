package com.si6b.kampuskita2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.si6b.kampuskita2.API.APIRequestsData;
import com.si6b.kampuskita2.API.RetroServer;
import com.si6b.kampuskita2.Model.ModelResponse;
import com.si6b.kampuskita2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etKota, etAlamat;
    private Button btnTambah;
    private String nama, kota, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etKota = findViewById(R.id.et_kota);
        etAlamat = findViewById(R.id.et_alamat);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                kota = etKota.getText().toString();
                alamat = etAlamat.getText().toString();

                if(nama.trim().isEmpty()){
                    etNama.setError("Nama Tidak Boleh Kosong");
                } else if (kota.trim().isEmpty()) {
                    etKota.setError("Kota Tidak Boleh Kosong");

                } else if (alamat.trim().isEmpty()){
                    etAlamat.setError("Alamat Tidak Boleh Kosong");
                }
                else{
                    tambahaKampus();
                }
            }
        });
    }

    private void tambahaKampus(){
        APIRequestsData API = RetroServer.konekRetrofit().create(APIRequestsData.class);
        Call<ModelResponse> proses = API.ardCreate(nama, kota, alamat);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode, pesan;
                kode = response.body().getKode();
                pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("DISINI", "Errornya itu: " + t.getMessage());
            }
        });
    }
}