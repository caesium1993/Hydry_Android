package com.example.hydry;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.Query;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOrder;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class MobileActivity extends Activity {
    private MobileServiceClient mClient;
    private MobileServiceTable<Telecommunication> mTeleTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mClient=MainActivity.azureServicesAdapter.getClient();
        mTeleTable=mClient.getTable(Telecommunication.class);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mobile);

        getMobileContent();
    }

    private void getMobileContent() {
        final TextView company_0 = (TextView) findViewById(R.id.text_company_0);
        final TextView prepaid_0 = (TextView) findViewById(R.id.text_prepaid_0);
        final TextView postpaid_0 = (TextView) findViewById(R.id.text_postpaid_0);
        final TextView company_1 = (TextView) findViewById(R.id.text_company_1);
        final TextView prepaid_1 = (TextView) findViewById(R.id.text_prepaid_1);
        final TextView postpaid_1 = (TextView) findViewById(R.id.text_postpaid_1);
        final TextView company_2 = (TextView) findViewById(R.id.text_company_2);
        final TextView prepaid_2 = (TextView) findViewById(R.id.text_prepaid_2);
        final TextView postpaid_2 = (TextView) findViewById(R.id.text_postpaid_2);

        if (mClient==null){
            Toast.makeText(getApplicationContext(),"Client is null", Toast.LENGTH_LONG);
            return;
        }

        AsyncTask<Void,Void,Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    final List<Telecommunication> telecommunications = mTeleTable.execute().get();
                    Log.d("Mobile Activity",telecommunications.get(0).getName());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Telecommunication t1 = telecommunications.get(0);
                            company_0.setText(t1.getName());
                            prepaid_0.setText(t1.getPrepaid());
                            postpaid_0.setText(t1.getPostpaid());

                            Telecommunication t2 = telecommunications.get(1);
                            company_1.setText(t2.getName());
                            prepaid_1.setText(t2.getPrepaid());
                            postpaid_1.setText(t2.getPostpaid());

                            Telecommunication t3 = telecommunications.get(2);
                            company_2.setText(t3.getName());
                            prepaid_2.setText(t3.getPrepaid());
                            postpaid_2.setText(t3.getPostpaid());

                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
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
}
