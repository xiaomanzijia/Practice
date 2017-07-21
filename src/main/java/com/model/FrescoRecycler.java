package com.model;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by licheng on 16/1/16.
 */
public class FrescoRecycler {
    private String text;
    private Uri uri;

    public FrescoRecycler(String text, Uri uri) {
        this.text = text;
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
