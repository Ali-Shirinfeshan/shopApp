package com.lotus.digikala.network;

import com.lotus.digikala.model.WoocommerceBody;
import com.lotus.digikala.model.categoriesmodel.CategoriesBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class WooCommerce {
    public static final String TAG = "FlickrFetcher";
    public static final String FLICKR_REST_PATH = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_7c028a04c9faf616410b09e2ab90b1884c875d01";
    public static final String CONSUMER_SECRET = "cs_8c39f626780f01d135719f64214fd092b848f4aa";
    private Map<String, String> mQueries = new HashMap<String, String>() {
        {
            put("consumer_key", CONSUMER_KEY);
            put("consumer_secret", CONSUMER_SECRET);

        }
    };

    public Map<String, String> getQueries() {
        return mQueries;
    }

    private WoocommerceService mWoocommerceApi = RetrofitInstance.getInstance(FLICKR_REST_PATH)
            .getRetrofit()
            .create(WoocommerceService.class);

    public WoocommerceService getWoocommerceApi() {
        return mWoocommerceApi;
    }

    public List<WoocommerceBody> productRecentPhotosSync() throws IOException {
        mQueries.put("orderby", "date");
//        mQueries.put("page", "2");
         Call<List<WoocommerceBody>> call = mWoocommerceApi.getWooCommerceBody(mQueries);
        return call.execute().body();
    }

    public List<WoocommerceBody> productPopularitySync() throws IOException {
        mQueries.put("orderby", "popularity");
//        mQueries.put("page", "2");
        Call<List<WoocommerceBody>> call = mWoocommerceApi.getWooCommerceBody(mQueries);
        return call.execute().body();
    }

    public List<WoocommerceBody> productRatedSync() throws IOException {
        mQueries.put("orderby", "rating");
//        mQueries.put("page", "2");
        Call<List<WoocommerceBody>> call = mWoocommerceApi.getWooCommerceBody(mQueries);
        return call.execute().body();
    }
    public List<WoocommerceBody> searchInProducts(String searchQuery) throws IOException {
        Call<List<WoocommerceBody>> call = mWoocommerceApi.searchProducts(searchQuery,mQueries);
        return call.execute().body();
    }

    public List<CategoriesBody> productCategoriesSync() throws IOException {

//        mQueries.put("page", "2");
        Call<List<CategoriesBody>> call = mWoocommerceApi.getCategories();
        return call.execute().body();
    }
    public WoocommerceBody getProductById(int id) throws IOException {
        Call<WoocommerceBody> call = mWoocommerceApi.getProductById(String.valueOf(id),mQueries);
        return call.execute().body();
    }
    public List<WoocommerceBody> getRelatedProducts(String[] id) throws IOException {
        Call<List<WoocommerceBody>> call = mWoocommerceApi.getReleatedProducts(mQueries,id);
        return call.execute().body();
    }
//    public void searchPhotosAsync(String query) {
//        mQueries.put("method", SEARCH_METHOD);
//        mQueries.put("text", query);
//
//        Call<WoocommerceBody> call = mWoocommerceApi.getWooCommerceBody(mQueries);
//        call.enqueue(getRetrofitCallback());
//    }


//    public interface FinishSplash {
//        boolean onCheckBackground(boolean check);
//    }
}
