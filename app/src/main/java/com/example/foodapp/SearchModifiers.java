package com.example.foodapp;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchModifiers implements Parcelable {
    public String ingredients;
    public String NiR;
    public boolean treenutfree;
    public boolean peanutfree;
    public boolean vegetarian;
    public boolean vegan;
    public boolean alcoholfree;

    protected SearchModifiers(Parcel in) {
        ingredients = in.readString();
        NiR = in.readString();
        treenutfree = in.readByte() != 0;
        peanutfree = in.readByte() != 0;
        vegetarian = in.readByte() != 0;
        vegan = in.readByte() != 0;
        alcoholfree = in.readByte() != 0;
    }

    public SearchModifiers() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ingredients);
        dest.writeString(NiR);
        dest.writeByte((byte) (treenutfree ? 1 : 0));
        dest.writeByte((byte) (peanutfree ? 1 : 0));
        dest.writeByte((byte) (vegetarian ? 1 : 0));
        dest.writeByte((byte) (vegan ? 1 : 0));
        dest.writeByte((byte) (alcoholfree ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchModifiers> CREATOR = new Creator<SearchModifiers>() {
        @Override
        public SearchModifiers createFromParcel(Parcel in) {
            return new SearchModifiers(in);
        }

        @Override
        public SearchModifiers[] newArray(int size) {
            return new SearchModifiers[size];
        }
    };
}
