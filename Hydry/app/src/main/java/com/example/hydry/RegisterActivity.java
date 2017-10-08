package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;
import com.squareup.okhttp.OkHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends Activity {

    private MobileServiceClient mClient;
    private MobileServiceTable<Users> mUsertable;
    private EditText musernametext;
    private EditText mpasswordtext;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        //AzureServicesAdapter.Initialize(this);
        //AzureServicesAdapter azureServicesAdapter=AzureServicesAdapter.getInstance();
        mClient=MainActivity.azureServicesAdapter.getClient();

        mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
            @Override
            public OkHttpClient createOkHttpClient() {
                OkHttpClient client = new OkHttpClient();
                client.setReadTimeout(20, TimeUnit.SECONDS);
                client.setWriteTimeout(20, TimeUnit.SECONDS);
                return client;
            }
        });
        mUsertable=mClient.getTable(Users.class);
        //initLocalStore().get();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

    }
    public void Register(View view){

        Toast.makeText(RegisterActivity.this, "registering...", Toast.LENGTH_LONG);

        if (mClient == null) {
            Toast.makeText(getApplicationContext(),"Client is null",Toast.LENGTH_SHORT).show();
            return;
        }
        musernametext = (EditText) findViewById(R.id.usernametext);
        mpasswordtext = (EditText) findViewById(R.id.passwordtext);
        final Users user = new Users();
        user.setUsername(musernametext.getText().toString());
        user.setPassword(mpasswordtext.getText().toString());

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    //mUsertable.insert(user);
                    //Toast.makeText(getApplicationContext(),user.getUsername(),Toast.LENGTH_SHORT).show();
                    //final List<Users> queryres= QueryItemsFromUsersTable();
                    //Toast.makeText(getApplicationContext(),queryres.size(),Toast.LENGTH_SHORT).show();
                    final Users entity = addItemInTable(user);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent();
                            intent.setClass(RegisterActivity.this, MainMenuActivity.class);
                            startActivity(intent);
                    }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }
        };
        runAsyncTask(task);
        musernametext.setText("");
        mpasswordtext.setText("");
    }

    public Users addItemInTable(Users user) throws ExecutionException, InterruptedException {
        Users entity = mUsertable.insert(user).get();
        //Toast.makeText(getApplicationContext(),user.getUsername(),Toast.LENGTH_SHORT).show();
        return entity;
    }

    private AsyncTask<Void, Void, Void> initLocalStore() throws MobileServiceLocalStoreException, ExecutionException, InterruptedException {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    MobileServiceSyncContext syncContext = mClient.getSyncContext();

                    if (syncContext.isInitialized())
                        return null;

                    SQLiteLocalStore localStore = new SQLiteLocalStore(mClient.getContext(), "OfflineStore", null, 1);

                    Map<String, ColumnDataType> tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("username", ColumnDataType.String);
                    tableDefinition.put("password", ColumnDataType.String);

                    localStore.defineTable("Users", tableDefinition);

                    SimpleSyncHandler handler = new SimpleSyncHandler();

                    syncContext.initialize(localStore, handler).get();

                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }
        };
        return runAsyncTask(task);
    }
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    private void createAndShowDialogFromTask(final Exception exception, String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAndShowDialog(exception, "Error");
            }
        });
    }

    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if(exception.getCause() != null){
            ex = exception.getCause();
        }
        createAndShowDialog(exception, title);
    }

    private List<Users> QueryItemsFromUsersTable() throws ExecutionException, InterruptedException {
        return mUsertable.where().field("username").
                eq(musernametext.toString()).execute().get();
    }


}
