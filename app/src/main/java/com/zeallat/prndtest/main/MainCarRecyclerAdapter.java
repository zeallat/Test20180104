package com.zeallat.prndtest.main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseRecyclerViewAdapter;
import com.zeallat.prndtest.base.BaseRecyclerViewHolder;
import com.zeallat.prndtest.data.model.Car;

import java.util.Locale;

import butterknife.BindView;

/**
 * Created by HoJunLee on 2018-01-06.
 */

public class MainCarRecyclerAdapter extends BaseRecyclerViewAdapter<Car> {

    /**
     * 뷰 타입
     */
    public enum ViewType {
        VERTICAL(1), HORIZONTAL(2);
        private int spanSize;

        ViewType(int spanSize) {
            this.spanSize = spanSize;
        }

        public int getSpanSize() {
            return spanSize;
        }

        public static ViewType valueOf(int position) {
            return (position % 5 == 0) ? ViewType.HORIZONTAL : ViewType.VERTICAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutResId;
        switch (ViewType.values()[viewType]) {
            case VERTICAL:
                layoutResId = R.layout.item_car_vertical;
                break;
            case HORIZONTAL:
                layoutResId = R.layout.item_car_horizontal;
                break;
            default:
                throw new IllegalArgumentException(String.format(Locale.KOREA, "not supported view type: %d", viewType));
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Car car = getItem(position);
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        Context context = viewHolder.itemView.getContext();

        //이미지 표시
        Glide.with(context).load(car.getMainImageUrl()).into(viewHolder.getImageViewContent());
        //텍스트 표시
        viewHolder.getTextViewModelName().setText(car.getModelPartName());
        viewHolder.getTextViewGradeName().setText(car.getGradePartName());
        viewHolder.getTextViewStatus().setText(car.getStatusDisplay());
        viewHolder.getTextViewYear().setText(String.valueOf(car.getYear()));
        viewHolder.getTextViewMileage().setText(String.format(Locale.KOREA, "%.1f만km", ((float) car.getMileage() / 10000.f)));
        viewHolder.getTextViewPrice()
                .setText(String.format(Locale.KOREA, "%,d만원",
                        car.getDiscountedPrice() != null ? car.getDiscountedPrice() : car.getPrice()));

        viewHolder.getTextViewStatus().setBackgroundColor(ContextCompat.getColor(context, car.getStatusEnum().getColorResId()));

        switch (ViewType.values()[holder.getItemViewType()]) {
            case VERTICAL:
                break;
            case HORIZONTAL:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return ViewType.valueOf(position).ordinal();
    }

    class ItemViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.imageViewContent)
        ImageView mImageViewContent;
        @BindView(R.id.textViewStatus)
        TextView mTextViewStatus;
        @BindView(R.id.textViewModelName)
        TextView mTextViewModelName;
        @BindView(R.id.textViewGradeName)
        TextView mTextViewGradeName;
        @BindView(R.id.textViewYear)
        TextView mTextViewYear;
        @BindView(R.id.textViewMileage)
        TextView mTextViewMileage;
        @BindView(R.id.textViewPrice)
        TextView mTextViewPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        public ImageView getImageViewContent() {
            return mImageViewContent;
        }

        public TextView getTextViewStatus() {
            return mTextViewStatus;
        }

        public TextView getTextViewModelName() {
            return mTextViewModelName;
        }

        public TextView getTextViewGradeName() {
            return mTextViewGradeName;
        }

        public TextView getTextViewYear() {
            return mTextViewYear;
        }

        public TextView getTextViewMileage() {
            return mTextViewMileage;
        }

        public TextView getTextViewPrice() {
            return mTextViewPrice;
        }
    }

}
