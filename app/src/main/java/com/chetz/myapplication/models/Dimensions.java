
package com.chetz.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dimensions implements Parcelable {

    @SerializedName("length")
    @Expose
    private float length;
    @SerializedName("width")
    @Expose
    private float width;
    @SerializedName("height")
    @Expose
    private Double height;

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.length);
        dest.writeFloat(this.width);
        dest.writeValue(this.height);
    }

    public Dimensions() {
    }

    protected Dimensions(Parcel in) {
        this.length = in.readFloat();
        this.width = in.readFloat();
        this.height = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Dimensions> CREATOR = new Parcelable.Creator<Dimensions>() {
        @Override
        public Dimensions createFromParcel(Parcel source) {
            return new Dimensions(source);
        }

        @Override
        public Dimensions[] newArray(int size) {
            return new Dimensions[size];
        }
    };
}
