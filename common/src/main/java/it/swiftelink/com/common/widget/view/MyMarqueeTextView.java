package it.swiftelink.com.common.widget.view;

import android.content.Context;
import android.util.AttributeSet;

public class MyMarqueeTextView extends androidx.appcompat.widget.AppCompatTextView {
    public MyMarqueeTextView(Context context) {
        super(context);
    }
    public MyMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyMarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    // 焦点
    @Override
    public boolean isFocused() {
        return true;
    }
}
