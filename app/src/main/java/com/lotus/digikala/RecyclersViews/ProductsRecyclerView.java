package com.lotus.digikala.RecyclersViews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lotus.digikala.R;
import com.lotus.digikala.activities.ProductDetailActivity;
import com.lotus.digikala.model.WoocommerceBody;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsRecyclerView extends RecyclerView.Adapter {
    private List<WoocommerceBody> productList;
    private Context mContext;
    private View view;

    public ProductsRecyclerView(List<WoocommerceBody> toDoList, Context context) {
        productList = toDoList;
        mContext = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.list_newest_products, parent, false);
        ProductHolder productHolder = new ProductHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHolder productHolder = (ProductHolder) holder;
        productHolder.bind(productList.get(position));
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    private class ProductHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private ImageView mImageView;
        private TextView mRegularPriceTextView;
        private TextView mBudgetPriceTextView;
        private WoocommerceBody mWoocommerceBody;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.list_newest_products_text_view);
            mImageView = itemView.findViewById(R.id.list_newest_product_image_view);
            mRegularPriceTextView = itemView.findViewById(R.id.regular_price);
            mBudgetPriceTextView=itemView.findViewById(R.id.budget_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= ProductDetailActivity.newIntent(mContext,mWoocommerceBody.getId(),mWoocommerceBody.getName());
                    mContext.startActivity(intent);
                }
            });
        }

        void bind(WoocommerceBody woocommerceBody) {
            mWoocommerceBody=woocommerceBody;
            if (!woocommerceBody.getName().equalsIgnoreCase("?????????? ???????? ???????????????? ???? ?????????? ???????? ????????????????!!!!!"))
                mTextView.setText(woocommerceBody.getName());
            if(!woocommerceBody.getPrice().equals(woocommerceBody.getRegularPrice())) {
                mRegularPriceTextView.setText(woocommerceBody.getPrice() + " " + "??????????");
                mBudgetPriceTextView.setText(woocommerceBody.getRegularPrice() + " " + "??????????");
            }else
            {
                mRegularPriceTextView.setText(woocommerceBody.getPrice() + " " + "??????????");
            }
            if(woocommerceBody.getImages().size()>0)
            Picasso.with(mContext).load(woocommerceBody.getImages().get(0).getSrc()).placeholder(R.drawable.digikala)
                    .into(mImageView);
        }
    }
}






