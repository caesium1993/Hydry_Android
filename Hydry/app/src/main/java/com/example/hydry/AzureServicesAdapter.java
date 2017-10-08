package com.example.hydry;

import android.content.Context;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;

/**
 * Created by lenovo on 2017/10/1.
 * Initialize the connection between Android and Azure
 */

public class AzureServicesAdapter {
    private String mMobileBackendUrl = "https://hydry.azurewebsites.net";
    private Context mContext;
    private MobileServiceClient mClient;
    private static AzureServicesAdapter mInstance = null;

    private AzureServicesAdapter(Context context) throws MalformedURLException {
        mContext = context;
        mClient = new MobileServiceClient(mMobileBackendUrl, mContext);
    }

    public static void Initialize(Context context) throws MalformedURLException {
        if (mInstance == null) {
            mInstance = new AzureServicesAdapter (context);
        } else {
            throw new IllegalStateException("AzureServiceAdapter is already initialized");
        }
    }

    public static AzureServicesAdapter getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("AzureServiceAdapter is not initialized");
        }
        return mInstance;
    }
    //return mClient for activities that need connection to Azure
    public MobileServiceClient getClient() {
        return mClient;
    }

}
