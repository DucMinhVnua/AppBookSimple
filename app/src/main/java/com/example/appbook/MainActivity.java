package com.example.appbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.Adapter.AdapterBook;
import com.example.Adapter.Book;
import com.example.DataBase.BookDataBase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BookDataBase bookDataBase;
    ArrayList<Book> bookArrayList;
    AdapterBook adapterBook;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rcv);

        // Khoi tao database
        bookDataBase = new BookDataBase(this, "BookDataBase.sqlite", null, 1);

        // Tao bang table book
            bookDataBase.QueryData("CREATE TABLE IF NOT EXISTS TableBook (ID INTEGER PRIMARY KEY AUTOINCREMENT, TenSach VARCHAR(200), TenTacGia VARCHAR(200), SoTrang INTEGER(1000))");

        // Insert data cho Table Book
//        bookDataBase.QueryData("INSERT INTO TableBook VALUES (null, 'Conan', 'KimDong', 134)");
//        bookDataBase.QueryData("INSERT INTO TableBook VALUES (null, 'Conan', 'KimDong', 134)");
//        bookDataBase.QueryData("INSERT INTO TableBook VALUES (null, 'Conan', 'KimDong', 134)");
//        bookDataBase.QueryData("INSERT INTO TableBook VALUES (null, 'Conan', 'KimDong', 134)");
//        bookDataBase.QueryData("INSERT INTO TableBook VALUES (null, 'Conan', 'KimDong', 134)");

        bookArrayList = new ArrayList<>();

        adapterBook = new AdapterBook(this, bookArrayList);
        recyclerView.setAdapter(adapterBook);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        AddBookArrayList();
    }

    private void AddBookArrayList() {

        // select data table book
        Cursor cursor = bookDataBase.GetData("SELECT * FROM TableBook");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenSach = cursor.getString(1);
            String tenTacGia = cursor.getString(2);
            int soTrang = cursor.getInt(3);

            bookArrayList.add(new Book(id,tenSach, tenTacGia, soTrang));
        }

        adapterBook.notifyDataSetChanged();
    }

    public void DialogDelete(int id, int position) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_delete);
        dialog.setCancelable(false);

        Button buttonDeleteYes = (Button) dialog.findViewById(R.id.btn_yes);
        Button buttonDeleteNo = (Button) dialog.findViewById(R.id.btn_no);

        buttonDeleteNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonDeleteYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookDataBase.QueryData("DELETE FROM TableBook WHERE ID = '"+ id +"'");
                dialog.dismiss();

                // cập nhật sau khi xóa
                bookArrayList.remove(position);
                recyclerView.removeViewAt(position);
                adapterBook.notifyItemRemoved(position);
                adapterBook.notifyItemRangeChanged(position, bookArrayList.size());
            }
        });
        dialog.show();
    }
}