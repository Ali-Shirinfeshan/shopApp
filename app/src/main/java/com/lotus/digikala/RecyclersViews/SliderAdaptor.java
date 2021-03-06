package com.lotus.digikala.RecyclersViews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lotus.digikala.R;
import com.lotus.digikala.activities.ProductDetailActivity;
import com.lotus.digikala.model.WoocommerceBody;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdaptor extends SliderViewAdapter<SliderAdaptor.SliderProductViewHolder> {
    private List<WoocommerceBody> productList;
    private WoocommerceBody singleProduct;
    private Context mContext;

    public SliderAdaptor(List<WoocommerceBody> woocommerceBodies, Context context) {
        productList = woocommerceBodies;
        mContext = context;
    }

    public SliderAdaptor(WoocommerceBody woocommerceBody, Context context) {
        singleProduct = woocommerceBody;
        mContext = context;
    }

    @Override
    public SliderProductViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SliderProductViewHolder(LayoutInflater.from(mContext).inflate(R.layout.image_view,
                parent, false));
    }

    @Override
    public void onBindViewHolder(SliderProductViewHolder viewHolder, int position) {
        if (productList != null)
            viewHolder.bind(productList.get(position));
        else
            viewHolder.bind(singleProduct,position);
    }

    @Override
    public int getCount() {
        if (productList != null)
            return productList.size();
        else
            return singleProduct.getImages().size();
    }

    public class SliderProductViewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView imageViewBackground;
        private WoocommerceBody mWoocommerceBody;
        public SliderProductViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.image);
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
            Picasso.with(mContext).load(woocommerceBody.getImages().get(0).getSrc()).placeholder(R.drawable.digikala)
                    .into(imageViewBackground);
        }
        void bind(WoocommerceBody woocommerceBody,int position) {
            mWoocommerceBody=woocommerceBody;
            Picasso.with(mContext).load(woocommerceBody.getImages().get(position).getSrc()).placeholder(R.drawable.digikala)
                    .into(imageViewBackground);
        }

    }
}
