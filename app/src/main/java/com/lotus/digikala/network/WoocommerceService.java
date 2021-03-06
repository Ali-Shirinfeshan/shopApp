package com.lotus.digikala.network;

import com.lotus.digikala.model.WoocommerceBody;
import com.lotus.digikala.model.categoriesmodel.CategoriesBody;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WoocommerceService {

    @GET("products")
    Call<List<WoocommerceBody>> getWooCommerceBody(@QueryMap Map<String, String> queries);

    @GET("products/categories")
    Call<List<CategoriesBody>> getCategories();

    @GET("products/{id}")
    Call<WoocommerceBody> getProductById(@Path("id") String productId,@QueryMap Map<String, String> queries);

    @GET("products")
    Call<List<WoocommerceBody>> getReleatedProducts(@QueryMap Map<String, String> queries,@Query("include") String...releateds);
    @GET("products")
    Call<List<WoocommerceBody>> getSortedBaseProducts(@QueryMap Map<String, String> queries);

    @GET("products")
    Call<List<WoocommerceBody>> searchProducts(@Query("search") String productName,@QueryMap Map<String, String> queries);

}
