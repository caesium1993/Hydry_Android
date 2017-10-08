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
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.List;
import java.util.concurrent.ExecutionException;
 /*
 * Activity of login page
 */
public class LoginActivity extends Activity {

    private MobileServiceClient mClient;
    private MobileServiceTable<Users> mUsertable;
    private EditText musernametext;
    private EditText mpasswordtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            //AzureServicesAdapter.Initialize(this);
            //AzureServicesAdapter azureServicesAdapter=AzureServicesAdapter.getInstance();
            mClient=MainActivity.azureServicesAdapter.getClient();
            mUsertable=mClient.getTable(Users.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        musernametext = (EditText) findViewById(R.id.usernametext);
        mpasswordtext = (EditText) findViewById(R.id.passwordtext);
        //mpassword = mpasswordtext.getText().toString();
    }

    public void Login (View view){
        if (mClient == null) {
            Toast.makeText(getApplicationContext(),"Client is null",Toast.LENGTH_SHORT).show();
            return;
        }

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            protected Void doInBackground(Void... params) {
                try {
                    final List<Users> queryres= QueryItemsFromUsersTable();
                   // Toast.makeText(getApplicationContext(),"running",Toast.LENGTH_SHORT).show();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            boolean flag=false;
                            for (Users user : queryres) {
                                if (user.getPassword().equals(mpasswordtext.getText().toString())){
                                    flag=true;
                                    musernametext.setText("");
                                    mpasswordtext.setText("");
                                    Intent intent=new Intent();
                                    intent.setClass(LoginActivity.this, MainMenuActivity.class);
                                    startActivity(intent);
                                }
                            }
                            if(flag==false){
                                musernametext.setText("");
                                mpasswordtext.setText("");
                                Toast.makeText(getApplicationContext(),"Please try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (MobileServiceException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        runAsyncTask(task);
    }

    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    private List<Users> QueryItemsFromUsersTable() throws ExecutionException, InterruptedException, MobileServiceException {
        String musernamestr=musernametext.getText().toString();
        return mUsertable.where().field("username").eq(musernamestr).execute().get();//query from Azure
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
}
