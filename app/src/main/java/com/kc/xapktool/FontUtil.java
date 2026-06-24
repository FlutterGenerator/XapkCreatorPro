package com.kc.xapktool;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontUtil {

	private static Typeface customTypeface;

	// Загружает шрифт один раз и кэширует его
	public static Typeface getTypeface(Context context) {
		if (customTypeface == null) {
			customTypeface = androidx.core.content.res.ResourcesCompat.getFont(context, R.font.my_font);
		}
		return customTypeface;
	}

	// Рекурсивно применяет шрифт ко всем TextView, EditText, Button и т.д. внутри View
	public static void applyFont(Context context, View view) {
		Typeface typeface = getTypeface(context);
		if (typeface == null || view == null) return;
		applyFontRecursively(view, typeface);
	}

	private static void applyFontRecursively(View view, Typeface typeface) {
		if (view instanceof TextView) {
			// Покрывает TextView, Button, EditText, CheckBox, RadioButton, AppCompat-варианты и т.д.
			((TextView) view).setTypeface(typeface);
		}

		if (view instanceof ViewGroup) {
			ViewGroup group = (ViewGroup) view;
			for (int i = 0; i < group.getChildCount(); i++) {
				applyFontRecursively(group.getChildAt(i), typeface);
			}
		}
	}
}
