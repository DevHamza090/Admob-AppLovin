package com.example.multirecyclerviewads.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.multirecyclerviewads.databinding.ActivityJavaMainBinding;

import java.util.ArrayList;
import java.util.Date;

public class JavaMainActivity extends AppCompatActivity {
    ActivityJavaMainBinding binding;
    private JavaAdapter javaAdapter;
    private ArrayList<JavaDataClass> javaArrayList;
    private RecyclerView rvJavaMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJavaMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        javaArrayList = new ArrayList<>();

        javaArrayList.add(new JavaDataClass("Tech360Zone", new Date()));
        javaArrayList.add(new JavaDataClass("Do SUBSCRIBE", new Date()));
        javaArrayList.add(new JavaDataClass("DO LIKE", new Date()));
        javaArrayList.add(new JavaDataClass("DO Comment", new Date()));
        javaArrayList.add(new JavaDataClass("Do Share", new Date()));
        javaArrayList.add(new JavaDataClass("Tech360Zone", new Date()));
        javaArrayList.add(new JavaDataClass("Do SUBSCRIBE", new Date()));
        javaArrayList.add(new JavaDataClass("DO LIKE", new Date()));
        javaArrayList.add(new JavaDataClass("DO Comment", new Date()));
        javaArrayList.add(new JavaDataClass("Do Share", new Date()));
        javaArrayList.add(new JavaDataClass("Tech360Zone", new Date()));
        javaArrayList.add(new JavaDataClass("Do SUBSCRIBE", new Date()));
        javaArrayList.add(new JavaDataClass("DO LIKE", new Date()));
        javaArrayList.add(new JavaDataClass("DO Comment", new Date()));
        javaArrayList.add(new JavaDataClass("Do Share", new Date()));
        javaArrayList.add(new JavaDataClass("Tech360Zone", new Date()));
        javaArrayList.add(new JavaDataClass("Do SUBSCRIBE", new Date()));
        javaArrayList.add(new JavaDataClass("DO LIKE", new Date()));
        javaArrayList.add(new JavaDataClass("DO Comment", new Date()));
        javaArrayList.add(new JavaDataClass("Do Share", new Date()));
        javaArrayList.add(new JavaDataClass("Tech360Zone", new Date()));
        javaArrayList.add(new JavaDataClass("Do SUBSCRIBE", new Date()));
        javaArrayList.add(new JavaDataClass("DO LIKE", new Date()));
        javaArrayList.add(new JavaDataClass("DO Comment", new Date()));
        javaArrayList.add(new JavaDataClass("Do Share", new Date()));
        javaArrayList.add(new JavaDataClass("Tech360Zone", new Date()));
        javaArrayList.add(new JavaDataClass("Do SUBSCRIBE", new Date()));
        javaArrayList.add(new JavaDataClass("DO LIKE", new Date()));
        javaArrayList.add(new JavaDataClass("DO Comment", new Date()));
        javaArrayList.add(new JavaDataClass("Do Share", new Date()));

        rvJavaMain = binding.rvJavaMain;

        rvJavaMain.setHasFixedSize(true);
        rvJavaMain.setLayoutManager(new GridLayoutManager(this,1));
        javaAdapter = new JavaAdapter(this, javaArrayList);
        rvJavaMain.setAdapter(javaAdapter);
    }

    public final GridLayoutManager getJavaAdjustedGridLayoutManager(Context context) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int position) {
                byte value;
                switch (javaAdapter.getItemViewType(position)) {
                    case JavaAdapter.ITEM_VIEW:
                        value = 1;
                        break;
                    case JavaAdapter.AD_VIEW:
                        value = 2;
                        break;
                    default:
                        value = 2;
                }

                return value;
            }
        });
        return layoutManager;
    }
}