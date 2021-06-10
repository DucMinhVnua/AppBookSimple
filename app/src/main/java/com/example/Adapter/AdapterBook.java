package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.appbook.MainActivity;
import com.example.appbook.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterBook extends RecyclerView.Adapter<AdapterBook.BookViewHolder>{

    MainActivity context;
    List<Book> bookList;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public AdapterBook(MainActivity context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @NotNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itembook_adapter, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterBook.BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        if(book == null) {
            return;
        }

        holder.id.setText(String.valueOf(book.getId()));
        holder.tenSach.setText(book.getTenSach());
        holder.tenTacGia.setText(book.getTenTacGia());
        holder.soTrang.setText(String.valueOf(book.getSoTrang()));
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(book.getId()));

        // xoa item
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogDelete(book.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bookList != null) {
            return bookList.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        TextView id, tenSach, tenTacGia, soTrang;
        SwipeRevealLayout swipeRevealLayout;
        LinearLayout linearLayout;

        public BookViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            tenSach = itemView.findViewById(R.id.tenSach);
            tenTacGia = itemView.findViewById(R.id.tenTacGia);
            soTrang = itemView.findViewById(R.id.soTrang);
            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
            linearLayout = itemView.findViewById(R.id.linearlayout_delete);
        }
    }

}
