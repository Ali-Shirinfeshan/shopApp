package com.lotus.digikala.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.lotus.digikala.R;
import com.lotus.digikala.model.WoocommerceBody;
import com.lotus.digikala.network.WooCommerce;

import java.io.IOException;
import java.util.List;

import Woo.Repository.Repository;

public class SplashActivity extends AppCompatActivity {
    private Handler handler;
    private WooCommerce mWooCommerce = new WooCommerce();
    List<WoocommerceBody> mWoocommerceBodies;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        InitProductsAsynceTask initProductsAsynceTask = new InitProductsAsynceTask();
        initProductsAsynceTask.execute();
    }

    private class InitProductsAsynceTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Repository.getInstance().setNewestProducts(mWooCommerce.productRecentPhotosSync());
                Repository.getInstance().setRatedProducts(mWooCommerce.productRatedSync());
                Repository.getInstance().setPopularProducts(mWooCommerce.productPopularitySync());
                Repository.getInstance().setCategoriesItems(mWooCommerce.productCategoriesSync());
            } catch (IOException e) {
//                Intent intent = MainActivity.newIntent(SplashActivity.this, 0);
//                Log.d("tag", "checkNetwork" + "0");
//                startActivity(intent);
//                finish();
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(!Repository.getInstance().isRepositoryNull()) {
                Intent intent = MainActivity.newIntent(SplashActivity.this, 1);
                Log.d("tag", "checkNetwork");
                startActivity(intent);
                finish();
            }else {
                Intent intent = MainActivity.newIntent(SplashActivity.this, 0);
                Log.d("tag", "checkNetwork" + "0");
                startActivity(intent);
                finish();
            }
        }
    }
}
