package com.waylanpunch.event.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pc on 2017/2/23.
 */

public class PostModel implements Parcelable {

    private String nickname;
    private String title;
    private String description;

    public PostModel() {
    }

    public PostModel(String nickname, String title, String description) {
        this.nickname = nickname;
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nickname);
        dest.writeString(this.title);
        dest.writeString(this.description);
    }

    protected PostModel(Parcel in) {
        this.nickname = in.readString();
        this.title = in.readString();
        this.description = in.readString();
    }

    public static final Creator<PostModel> CREATOR = new Creator<PostModel>() {
        public PostModel createFromParcel(Parcel source) {
            return new PostModel(source);
        }

        public PostModel[] newArray(int size) {
            return new PostModel[size];
        }
    };
}
