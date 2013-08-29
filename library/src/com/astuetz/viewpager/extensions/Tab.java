package com.astuetz.viewpager.extensions;

import android.graphics.drawable.Drawable;
import android.view.View;

public final class Tab {
  public static final int TYPE_TITLE = 0;
  public static final int TYPE_DRAWABLE = 1;
  public static final int TYPE_VIEW = 2;

  private final int type;

  private CharSequence title;
  private Drawable drawable;
  private View view;

  public static Tab from(final CharSequence title) {
    final Tab tab = new Tab(TYPE_TITLE);
    tab.title = title;
    return tab;
  }

  public static Tab from(final Drawable drawable) {
    final Tab tab = new Tab(TYPE_DRAWABLE);
    tab.drawable = drawable;
    return tab;
  }

  public static Tab from(final View view) {
    final Tab tab = new Tab(TYPE_VIEW);
    tab.view = view;
    return tab;
  }

  public Drawable getDrawable() {
    return drawable;
  }

  public CharSequence getTitle() {
    return title;
  }

  public View getView() {
    return view;
  }

  public int getType() {
    return type;
  }

  private Tab(final int type) {
    this.type = type;
  }
}
