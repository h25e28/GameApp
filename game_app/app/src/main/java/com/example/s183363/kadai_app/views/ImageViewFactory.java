package com.example.s183363.kadai_app.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.s183363.kadai_app.R;

import java.util.LinkedList;

class ImageViewFactory extends BaseView {
    private Context context;
    private RelativeLayout layout;

    private LinkedList<ImageView> imageViews = new LinkedList<>();
    private int counter = 0;

    public ImageViewFactory(Context context) {
        super(context);
        this.context = context;
        this.layout = activity.findViewById(R.id.relativeLayout);
    }

    public void clear() {
        counter = 0;
        for (ImageView imageView : imageViews) {
            imageView.setVisibility(GONE);
        }
    }

    public ImageView getImageView() {
        if (counter >= imageViews.size()) {
            addView();
        }
        ImageView imageView=imageViews.get(counter);
        imageView.setVisibility(VISIBLE);
        counter++;
        return imageView;
    }

    private void addView() {
        ImageView imageView = new ImageView(context);
        layout.addView(imageView);
        imageViews.add(imageView);
    }
}
