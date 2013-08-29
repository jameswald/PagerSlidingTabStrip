package com.astuetz.viewpager.extensions.sample;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;
import com.astuetz.viewpager.extensions.Tab;

import java.util.Arrays;
import java.util.List;

public class QuickContactFragment extends DialogFragment {

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private ContactPagerAdapter adapter;

	public static QuickContactFragment newInstance() {
		return new QuickContactFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.fragment_quick_contact, container, false);

		tabs = (PagerSlidingTabStrip) root.findViewById(R.id.tabs);
		pager = (ViewPager) root.findViewById(R.id.pager);
		adapter = new ContactPagerAdapter();

		pager.setAdapter(adapter);

		tabs.setViewPager(pager);

		return root;
	}

	@Override
	public void onStart() {
		super.onStart();

		// change dialog width
		if (getDialog() != null) {

			final int fullWidth;

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				Display display = getActivity().getWindowManager().getDefaultDisplay();
				Point size = new Point();
				display.getSize(size);
				fullWidth = size.x;
			} else {
				Display display = getActivity().getWindowManager().getDefaultDisplay();
				fullWidth = display.getWidth();
			}

			final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
					.getDisplayMetrics());

			int w = fullWidth - padding;
			int h = getDialog().getWindow().getAttributes().height;

			getDialog().getWindow().setLayout(w, h);
		}
	}

	private class ContactPagerAdapter extends PagerAdapter implements PagerSlidingTabStrip.TabProvider {

		private final List<Tab> tabs;

		public ContactPagerAdapter() {
			super();

      final Resources res = getResources();
      tabs = Arrays.asList(
          Tab.from(res.getDrawable(R.drawable.ic_launcher_gplus)),
          Tab.from(res.getDrawable(R.drawable.ic_launcher_gmail)),
          Tab.from(res.getDrawable(R.drawable.ic_launcher_gmaps)),
          Tab.from(res.getDrawable(R.drawable.ic_launcher_chrome)));
		}

		@Override
		public int getCount() {
			return tabs.size();
		}

    @Override
    public Tab getTabForPosition(int position) {
      return tabs.get(position);
    }

    @Override
		public Object instantiateItem(ViewGroup container, int position) {
			// looks a little bit messy here
			TextView v = new TextView(getActivity());
			v.setBackgroundResource(R.color.background_window);
			v.setText("PAGE " + (position + 1));
			final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources()
					.getDisplayMetrics());
			v.setPadding(padding, padding, padding, padding);
			v.setGravity(Gravity.CENTER);
			container.addView(v, 0);
			return v;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object view) {
			container.removeView((View) view);
		}

		@Override
		public boolean isViewFromObject(View v, Object o) {
			return v == o;
		}
	}
}
