package com.suwonsmartapp.basicform.fragment;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.suwonsmartapp.basicform.R;
import com.suwonsmartapp.basicform.fragment.ItemFragment.OnListFragmentInteractionListener;
import com.suwonsmartapp.basicform.fragment.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vertical_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mContentView.setText(mValues.get(position).content);

        List<DummyItem> childData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            childData.add(new DummyItem(String.valueOf(i), "content " + i, "details " + i));
        }
        ImagePagerAdapter adapter = new ImagePagerAdapter(childData);

        holder.mViewPager.setAdapter(adapter);
        holder.mViewPager.setCurrentItem(mValues.get(position).position);
        holder.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mValues.get(position).position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
//        public final TextView mIdView;
        public final TextView mContentView;
        public final ViewPager mViewPager;
//        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.text_bottom);
            mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
//            int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20 * 2, view.getContext().getResources().getDisplayMetrics());
//            mViewPager.setPageMargin(-margin);
//            mViewPager.setOffscreenPageLimit(3);
        }

    }

    private class ImagePagerAdapter extends PagerAdapter {

        private final List<DummyItem> mData;

        public ImagePagerAdapter(List<DummyItem> data) {
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
}
