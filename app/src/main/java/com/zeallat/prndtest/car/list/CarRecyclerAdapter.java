package com.zeallat.prndtest.car.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseRecyclerViewAdapter;
import com.zeallat.prndtest.base.BaseRecyclerViewHolder;
import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.util.GlideApp;

import java.text.DecimalFormat;
import java.util.Locale;

import butterknife.BindView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.zeallat.prndtest.util.StringUtil.formatPrice;

/**
 * 차량 목록 RecyclerAdapter
 */
public class CarRecyclerAdapter extends BaseRecyclerViewAdapter<Car, CarRecyclerAdapter
        .ItemViewHolder> {

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
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutResId;
        switch (ViewType.values()[viewType]) {
            case VERTICAL:
                layoutResId = R.layout.item_car_vertical;
                break;
            case HORIZONTAL:
                layoutResId = R.layout.item_car_horizontal;
                break;
            default:
                throw new IllegalArgumentException(String.format(Locale.KOREA, "not supported " +
                        "view type: %d", viewType));
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Car car = getItem(position);
        Context context = holder.itemView.getContext();

        //이미지 표시
        GlideApp.with(context).load(car.getMainImageUrl())
                .transition(withCrossFade())
                .thumbnail(0.1f)
                .into(holder.getImageViewContent());
        //텍스트 표시
        holder.getTextViewModelName().setText(car.getModelPartName());
        holder.getTextViewGradeName().setText(car.getGradePartName());
        holder.getTextViewStatus().setText(car.getStatusDisplay());
        holder.getTextViewYear().setText(String.valueOf(car.getYear()));
        holder.getTextViewMileage().setText(
                String.format(Locale.KOREA, context.getString(R.string.all_msg_mileage_2),
                        new DecimalFormat("#.#").format(((float) car.getMileage() / 10000.f))));
        holder.getTextViewPrice().setText(formatPrice(car.getRealPrice()));
        holder.getTextViewStatus().setBackgroundColor(ContextCompat.getColor(context, car
                .getStatusEnum().getColorResId()));
    }

    @Override
    public int getItemViewType(int position) {
        return ViewType.valueOf(position).ordinal();
    }

    /**
     * 차량 목록 아이템 뷰 홀더
     */
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
