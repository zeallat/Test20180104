
package com.zeallat.prndtest.data.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;
import com.zeallat.prndtest.R;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

import static android.text.TextUtils.isEmpty;

/*
차량 데이터 클래스
 */
public class Car extends RealmObject implements Serializable {

    public enum Status {
        FOR_SALE("for_sale", R.color.all_status_forsale),
        ON_SALE("on_sale", R.color.all_status_onsale),
        SOLD_OUT("sold_out", R.color.all_status_soldout);
        private String code;
        private int colorResId;

        Status(String code, int colorResId) {
            this.code = code;
            this.colorResId = colorResId;
        }

        public String getCode() {
            return code;
        }

        public int getColorResId() {
            return colorResId;
        }

        public static Status findByCode(String code) {
            for (Status status : Status.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
            return null;
        }
    }

    @PrimaryKey
    @SerializedName("id")
    private int mId;
    @SerializedName("car_number")
    private String mCarNumber;
    @SerializedName("fuel")
    private String mFuel;
    @SerializedName("full_name")
    private String mFullName;
    @SerializedName("image_urls")
    private RealmList<String> mImageUrls;
    @SerializedName("initial_registration_date")
    private String mInitialRegistrationDate;
    @SerializedName("absolute_url")
    private String mAbsoluteUrl;
    @SerializedName("discounted_price")
    private Integer mDiscountedPrice;
    @SerializedName("grade_part_name")
    private String mGradePartName;
    @SerializedName("main_image_url")
    private String mMainImageUrl;
    @SerializedName("mileage")
    private int mMileage;
    @SerializedName("model_part_name")
    private String mModelPartName;
    @SerializedName("price")
    private int mPrice;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("status_display")
    private String mStatusDisplay;
    @SerializedName("year")
    private int mYear;
    @Ignore
    private Status mStatusEnum;

    public Car() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getCarNumber() {
        return mCarNumber;
    }

    public void setCarNumber(String carNumber) {
        mCarNumber = carNumber;
    }

    public String getFuel() {
        return mFuel;
    }

    public void setFuel(String fuel) {
        mFuel = fuel;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public RealmList<String> getImageUrls() {
        return mImageUrls;
    }

    public void setImageUrls(RealmList<String> imageUrls) {
        mImageUrls = imageUrls;
    }

    public String getInitialRegistrationDate() {
        return mInitialRegistrationDate;
    }

    public void setInitialRegistrationDate(String initialRegistrationDate) {
        mInitialRegistrationDate = initialRegistrationDate;
    }

    public String getAbsoluteUrl() {
        return mAbsoluteUrl;
    }

    public void setAbsoluteUrl(String absoluteUrl) {
        mAbsoluteUrl = absoluteUrl;
    }

    public Integer getDiscountedPrice() {
        return mDiscountedPrice;
    }

    public void setDiscountedPrice(Integer discountedPrice) {
        mDiscountedPrice = discountedPrice;
    }

    public String getGradePartName() {
        return mGradePartName;
    }

    public void setGradePartName(String gradePartName) {
        mGradePartName = gradePartName;
    }

    public String getMainImageUrl() {
        return mMainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        mMainImageUrl = mainImageUrl;
    }

    public int getMileage() {
        return mMileage;
    }

    public void setMileage(int mileage) {
        mMileage = mileage;
    }

    public String getModelPartName() {
        return mModelPartName;
    }

    public void setModelPartName(String modelPartName) {
        mModelPartName = modelPartName;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStatusDisplay() {
        return mStatusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        mStatusDisplay = statusDisplay;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public Status getStatusEnum() {
        if (mStatusEnum == null) mStatusEnum = Status.findByCode(mStatus);
        return mStatusEnum;
    }

    public void setStatusEnum(Status statusEnum) {
        mStatusEnum = statusEnum;
    }

    public void parseIdFromUrl() {
        if (!isEmpty(this.getAbsoluteUrl())) {
            this.setId(Integer.parseInt(Uri.parse(this.getAbsoluteUrl()).getLastPathSegment()));
        }
    }
}
