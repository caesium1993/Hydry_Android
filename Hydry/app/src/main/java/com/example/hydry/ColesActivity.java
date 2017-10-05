package com.example.hydry;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ColesActivity extends ListActivity {
    private MobileServiceClient mClient;
    private MobileServiceTable<Items> mItemstable;
    private String[] itemsnames;
    private String[] itemsdescription;
    private String[] itemprefered;
    private int[] imageids = { R.drawable.coles_kangrooburger, R.drawable.coles_croissant,
            R.drawable.coles_gippsland, R.drawable.coles_honeyham };
    ListView mListView = null;
    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mClient = MainActivity.azureServicesAdapter.getClient();
        mItemstable = mClient.getTable(Items.class);
        super.onCreate(savedInstanceState);
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_coles);
        Getresultfromazure();
    }

    private List<Items> QueryItemsFromItemsTable() throws ExecutionException, InterruptedException, MobileServiceException {
        //String musernamestr=musernametext.getText().toString();
        Log.d("query","running");
        Log.d("result",Integer.toString(mItemstable.where().field("company").eq("coles").execute().get().size()));
        return mItemstable.where().field("company").eq("coles").execute().get();

        // Log.d("musernamestr",musernamestr);
        //Toast.makeText(getApplicationContext(),musernamestr,Toast.LENGTH_SHORT).show();
        // Log.d("query",Integer.toString(mUsertable.where().field("username").eq(musernamestr).execute().get().size()));
    }
    //@Override
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coles);
    }*/

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
                                mListView = getListView();
                                i++;
                            }
                            int lengh = itemsnames.length;
                            for(int j =0; j < lengh; j++) {
                                Map<String,Object> items = new HashMap<String,Object>();
                                items.put("image", imageids[j]);
                                items.put("title", itemsnames[j]);
                                items.put("text",itemsdescription[j]+"\n"+itemprefered[j]);
                                mData.add(items);
                            }
                            SimpleAdapter adapter = new SimpleAdapter(ColesActivity.this,mData,R.layout.activity_coles,
                                    new String[]{"image","title","text"},new int[]{R.id.image,R.id.title,R.id.text});
                            setListAdapter(adapter);
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
