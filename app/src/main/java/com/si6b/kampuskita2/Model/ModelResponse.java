package com.si6b.kampuskita2.Model;

import java.util.List;

public class ModelResponse {
    private String kode, pesan;
    private List<ModelKampus> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelKampus> getData() {
        return data;
    }
}
