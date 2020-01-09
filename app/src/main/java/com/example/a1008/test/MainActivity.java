package com.example.a1008.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a1008.test.Helper.DatabaseBuilder;
import com.example.a1008.test.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.example.a1008.test.Helper.QueryMode;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnCheckDB);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseBuilder queryAllUsers = new DatabaseBuilder(QueryMode.WRITE,
                        "DELETE FROM Users WHERE id = 3");

                queryAllUsers.execute(new OnDatabaseBuilderQueryExecuteListener() {
                    @Override
                    public void OnGetResultHandler(Object resultSet) {
                        /**
                         *
                         *  Cast resultSet to { ResultSet } if QueryMode == QueryMode.READ
                         *  Cast resultSet to { Integer } if QueryMode == QueryMode.WRITE
                         *
                         *  Cast example:
                         *   ResultSet result = (ResultSet) resultSet;
                         *   Integer rowsAffected = (Integer) resultSet;
                         *
                         *  Verify if Update, Delete or Insert were successful. For that, just check if length of rowsAffected > 0
                         *
                         *
                         *  Retrieve data from ResultSet Object
                         *
                         *      --- Single row example ---
                         *      String username, email;
                         *         while(resultSet.next()) {
                         *             username = resultSet("user") --> "user" is the name of column.
                         *             email = resultSet("email") --> "email" is the name of column.
                         *         }
                         *
                         *
                         *
                         *      --- Rows example ---
                         *      List<User> users = new ArrayList<>();
                         *
                         *         while(resultSet.next()) {
                         *
                         *              User _user = new User();
                         *              user.setUsername(resultSet("user")); --> "user" is the name of column.
                         *              user.setEmail(resultSet("email")); --> "email" is the name of column.
                         *
                         *              users.add(_user);
                         *         }
                         */

                        Log.d("ResultSet", resultSet.toString()); // ignore this
                    }
                });
            }
        });
    }
}
