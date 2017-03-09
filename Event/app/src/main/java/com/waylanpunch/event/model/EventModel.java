package com.waylanpunch.event.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pc on 2017/2/23.
 */

public class EventModel implements Parcelable {

    public String nickname;
    public String title;

    public EventModel() {
    }

    public EventModel(String title, String detail) {
        this.nickname = title;
        this.title = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nickname);
        dest.writeString(this.title);
    }

    protected EventModel(Parcel in) {
        this.nickname = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<EventModel> CREATOR = new Parcelable.Creator<EventModel>() {
        public EventModel createFromParcel(Parcel source) {
            return new EventModel(source);
        }

        public EventModel[] newArray(int size) {
            return new EventModel[size];
        }
    };
}
