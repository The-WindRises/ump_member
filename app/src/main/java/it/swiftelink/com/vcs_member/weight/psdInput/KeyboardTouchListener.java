package it.swiftelink.com.vcs_member.weight.psdInput;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by xuanweijian on 2016/3/31.
 */
public class KeyboardTouchListener {
    private KeyboardUtil keyboardUtil;
    private int keyboardType = 1;
    private int scrollTo = -1;

    public KeyboardTouchListener(KeyboardUtil util, int keyboardType, int scrollTo) {
        this.keyboardUtil = util;
        this.keyboardType = keyboardType;
        this.scrollTo = scrollTo;
    }

    public boolean onTouch(View v, int event) {
        if (event == MotionEvent.ACTION_UP) {
            if (keyboardUtil != null && keyboardUtil.getEd() != null && v.getId() != keyboardUtil.getEd().getId()) {
                keyboardUtil.showKeyBoardLayout((EditText) v, keyboardType, scrollTo);
            } else if (keyboardUtil != null && keyboardUtil.getEd() == null) {
                keyboardUtil.showKeyBoardLayout((EditText) v, keyboardType, scrollTo);
            } else {
                if (keyboardUtil != null) {
                    keyboardUtil.setKeyBoardCursorNew((EditText) v);
                }
            }
        }
        return false;
    }
}
