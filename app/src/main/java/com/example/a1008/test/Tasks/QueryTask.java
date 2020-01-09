package com.example.a1008.test.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.a1008.test.Helper.DatabaseBuilder;
import com.example.a1008.test.Helper.IDatabaseBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryTask extends AsyncTask<Void, Void, Void> {

    private String query;
    private ResultSet resultSet;
    private IDatabaseBuilder iDatabaseBuilder;

    public QueryTask(String query, IDatabaseBuilder iDatabaseBuilder) {
        this.query = query;
        this.iDatabaseBuilder = iDatabaseBuilder;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Connection connection = DatabaseBuilder.getInstance();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
            }
            catch (SQLException ex) {
                throw new Error(ex.getMessage());
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        iDatabaseBuilder.OnDatabaseResultHandler(resultSet);
    }
}
