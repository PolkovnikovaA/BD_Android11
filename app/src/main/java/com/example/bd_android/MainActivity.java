package com.example.bd_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Connection connection;
    String ConnectionResult="";
    private String Img;

    @SuppressLint("MissingInflatedId") /*all*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    SimpleAdapter ad;
    public void GetList(View v)
    {
        ListView listView=(ListView) findViewById(R.id.listView1);
        List<Map<String,String>> Mydatalist=null;
        ListItem MyData = new ListItem();
        Mydatalist = MyData.getlist();

        String[] From={"ID", "Familiya", "Dolzhnost"};
        int[] Tow={R.id.ID, R.id.Familiya,R.id.Dolzhnost};
        ad = new SimpleAdapter(MainActivity.this, Mydatalist, R.layout.listlayout, From, Tow);
        listView.setAdapter(ad);
    }

    public void BD(View v)
    {
        setContentView(R.layout.avtoriz);
    }

    public void naz(View v)
    {
        setContentView(R.layout.avtoriz);
    }

    public void Ad(View v)
    {
        setContentView(R.layout.add);
    }

    public void ADD(View v)
    {
        TextView ID = (TextView) findViewById(R.id.id);
        TextView Fam = (TextView) findViewById(R.id.fam);
        TextView Dol = (TextView) findViewById(R.id.dol);
        Button btninsert = (Button) findViewById(R.id.btnadd);

        btninsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.connectionClass();
                try {
                    if(connection != null){
                        String query = "Insert into Sotrudnik values ('" + ID.getText().toString() + "','"+Fam.getText().toString()+"','"+Dol.getText().toString()+"')";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                    }

                }
                catch (Exception ex) {
                    Log.e("Error",ex.getMessage());
                }
            }
        });
    }

    public void RED(View v)
    {
        TextView ID = (TextView) findViewById(R.id.id);
        TextView Fam = (TextView) findViewById(R.id.fam);
        TextView Dol = (TextView) findViewById(R.id.dol);
        Button btnupdate = (Button) findViewById(R.id.btnup);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.connectionClass();
                try {
                    if (connection != null) {

                        String query = "update Sotrudnik set  Familiya ='" + Fam.getText().toString() + "', Dolzhnost ='" + Dol.getText().toString() + "' where ID ='" + ID.getText().toString() +"'";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.getMessage());
                }
            }
        });
    }

    public void DEL(View v)
    {
        TextView ID = (TextView) findViewById(R.id.id);
        Button btmdel = (Button) findViewById(R.id.btmdel);

        btmdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.connectionClass();
                try {
                    if (connection != null) {

                        String query = "DELETE Sotrudnik WHERE ID ='" + ID.getText().toString() + "'";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.getMessage());
                }
            }
        });
    }




}