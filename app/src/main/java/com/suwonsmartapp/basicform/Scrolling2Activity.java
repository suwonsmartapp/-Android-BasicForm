package com.suwonsmartapp.basicform;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.suwonsmartapp.basicform.fragment.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class Scrolling2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<DummyContent.DummyItem> data = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            data.add(new DummyContent.DummyItem(String.valueOf(i), "content " + i, "details " + i));
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ImagePagerAdapter(data));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class ImagePagerAdapter extends PagerAdapter {

        private final List<DummyContent.DummyItem> mData;

        public ImagePagerAdapter(List<DummyContent.DummyItem> data) {
            this.mData = data;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = container.getContext();
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Glide.with(context)
                    .load("http://suwonsmartapp.iptime.org/test/ojs/image.jpg")
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.drawable.pic)
                    .into(imageView);

            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling2, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
