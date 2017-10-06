package com.example.hydry;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ColesActivity extends Activity {
    private MobileServiceClient mClient;
    private MobileServiceTable<Items> mItemstable;
    private String[] itemsnames;
    private String[] itemsdescription;
    private String[] itemprefered;
    private ArrayList<Items> mitemlist = new ArrayList<Items>();
    private int[] imageids = { R.drawable.coles_gippsland, R.drawable.coles_kangrooburger,
            R.drawable.coles_croissant, R.drawable.coles_honeyham };
    //ListView mListView = null;
    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        mClient = MainActivity.azureServicesAdapter.getClient();
        mItemstable = mClient.getTable(Items.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        //super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_coles);
        Getresultfromazure();
    }

    private List<Items> QueryItemsFromItemsTable() throws ExecutionException, InterruptedException, MobileServiceException {
        //String musernamestr=musernametext.getText().toString();
        Log.d("query","running");
        Log.d("result",Integer.toString(mItemstable.where().field("company").eq("coles").execute().get().size()));
        return mItemstable.where().field("company").eq("coles").execute().get();
    }
    //@Override

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
                            int lengh = itemsnames.length;
                            for (int j=0;j<res.size();j++){
                                Items items = new Items(itemsnames[j],itemsdescription[j],itemprefered[j],imageids[j]);
                                mitemlist.add(items);
                            }
                            ItemAdapter mitemadapter = new ItemAdapter(ColesActivity.this,R.layout.colesitem,mitemlist);
                            ListView listView = (ListView) findViewById(R.id.colesitemlist);
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
