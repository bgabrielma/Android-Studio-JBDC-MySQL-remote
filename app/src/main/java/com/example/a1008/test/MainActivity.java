package com.example.a1008.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.a1008.test.Helper.DatabaseBuilder;
import com.example.a1008.test.Helper.IDatabaseBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnCheckDB);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseBuilder queryAllUsers = new DatabaseBuilder("SELECT * FROM Users");
                queryAllUsers.execute(new IDatabaseBuilder() {
                    @Override
                    public void OnDatabaseResultHandler(ResultSet resultSet) {
                        try {
                            while (resultSet.next()) {
                                Log.d("Campo user", resultSet.getString("user"));
                                Log.d("Campo email", resultSet.getString("email"));
                            }
                        } catch(SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
