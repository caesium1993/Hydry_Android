package com.example.hydry;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WorthActivity extends Activity {
    private MobileServiceClient mClient;
    private MobileServiceTable<Items> mItemstable;
    private String[] itemsnames;
    private String[] itemsdescription;
    private String[] itemprefered;
    private ArrayList<Items> mitemlist = new ArrayList<Items>();
    private int[] imageids = { R.drawable.woolworth_roastchicken, R.drawable.woolworth_pancake,
            R.drawable.woolworth_lachang, R.drawable.woolworth_chashao };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            mClient = MainActivity.azureServicesAdapter.getClient();
            mItemstable = mClient.getTable(Items.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worth);
        Getresultfromazure();
    }

    private List<Items> QueryItemsFromItemsTable() throws ExecutionException, InterruptedException, MobileServiceException {
        return mItemstable.where().field("company").eq("worth").execute().get();
    }

    public void Getresultfromazure(){
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                try {
                    final List<Items> res = QueryItemsFromItemsTable();
                    int length = res.size();
                    itemsnames = new String[length];
                    itemsdescription = new String[length];
                    itemprefered = new String[length];
                    runOnUiThread(new Runnable() {
                        public void run() {
                            int i = 0;
                            for (Items item : res) {
                                itemsnames[i] = item.getItemname();
                                itemsdescription[i] = item.getItemdescription();
                                itemprefered[i] = item.getPrefered();
                                //mListView = getListView();
                                i++;
                            }
                            //int lengh = itemsnames.length;
                            for (int j=0;j<res.size();j++){
                                Items items = new Items(itemsnames[j],itemsdescription[j],itemprefered[j],imageids[j]);
                                mitemlist.add(items);
                            }
                            WorthItemAdapter mitemadapter = new WorthItemAdapter(WorthActivity.this,R.layout.worthitem,mitemlist);
                            ListView listView = (ListView) findViewById(R.id.worthitemlist);
                            listView.setAdapter(mitemadapter);
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
}
