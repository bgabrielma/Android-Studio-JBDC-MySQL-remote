package com.example.a1008.test.Tasks;

import android.os.AsyncTask;

import com.example.a1008.test.Helper.OnDatabaseBuilderQueryExecuteListener;
import com.example.a1008.test.Helper.DatabaseBuilder;
import com.example.a1008.test.Helper.QueryMode;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class QueryTask extends AsyncTask<Void, Void, Void> {

    private String query;
    private QueryMode queryMode;
    private Object result;

    private OnDatabaseBuilderQueryExecuteListener onDatabaseBuilderQueryExecuteListener;

    public QueryTask(QueryMode queryMode, String query, OnDatabaseBuilderQueryExecuteListener onDatabaseBuilderQueryExecuteListener) {
        this.queryMode = queryMode;
        this.query = query;
        this.onDatabaseBuilderQueryExecuteListener = onDatabaseBuilderQueryExecuteListener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Connection connection = DatabaseBuilder.getInstance();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                switch (queryMode) {
                    case READ: {
                        result = statement.executeQuery(query);
                        break;
                    }
                    case WRITE: {
                        result = statement.executeUpdate(query);
                    }
                }
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
        onDatabaseBuilderQueryExecuteListener.OnGetResultHandler(result);
    }
}
