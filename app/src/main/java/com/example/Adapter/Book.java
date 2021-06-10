package com.example.Adapter;

public class Book {
    private int id;
    private String tenSach;
    private String tenTacGia;
    private int soTrang;

    public Book(int id, String tenSach, String tenTacGia, int soTrang) {
        this.id = id;
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.soTrang = soTrang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }
}
