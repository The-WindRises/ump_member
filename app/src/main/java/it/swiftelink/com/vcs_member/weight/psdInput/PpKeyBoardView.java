package it.swiftelink.com.vcs_member.weight.psdInput;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import java.util.List;

import it.swiftelink.com.vcs_member.R;

/**
 * @author lpp
 */
public class PpKeyBoardView extends KeyboardView {
    private Context mContext;
    // 右下角
    private int rightType = 1;
    private int heightPixels;
    private float density;
    private static Keyboard mKeyBoard;

    public PpKeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        heightPixels = mContext.getResources().getDisplayMetrics().heightPixels;
        density = mContext.getResources().getDisplayMetrics().density;
    }

    public PpKeyBoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        heightPixels = mContext.getResources().getDisplayMetrics().heightPixels;
        density = mContext.getResources().getDisplayMetrics().density;
    }

    /**
     * 重新画一些按键
     */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mKeyBoard = KeyboardUtil.getKeyBoardType();
        List<Key> keys = mKeyBoard.getKeys();

        for (Key key : keys) {
            // 数字键盘的处理
            if (mKeyBoard.equals(KeyboardUtil.numKeyboard)) {
                initRightType(key);
                drawNumSpecialKey(key, canvas);
            }
        }
    }

    //数字键盘
    private void drawNumSpecialKey(Key key, Canvas canvas) {
        if (key.codes[0] == -5) {

            drawKeyBackground(R.drawable.btn_keyboard_key_num_delete, canvas, key);
        }

        // 顶部按键
        if (key.codes[0] == -3 && key.label == null) {
            drawKeyBackground(R.drawable.btn_keyboard_key_pull, canvas, key);
            drawText(canvas, key);
        }

        // 右下角的按键
        if (key.codes[0] == 0
                || key.codes[0] == 741741
                || key.codes[0] == 88
                || (key.codes[0] == -4 && key.label != null)
                || key.codes[0] == 46) {
            drawKeyBackground(R.drawable.btn_keyboard_key2, canvas, key);
            drawText(canvas, key);
        }
    }


    private void drawKeyBackground(int drawableId, Canvas canvas, Key key) {
        Drawable npd = (Drawable) mContext.getResources().getDrawable(
                drawableId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            npd.setState(drawableState);
        }
        npd.setBounds(key.x, key.y, key.x + key.width, key.y
                + key.height);
        npd.draw(canvas);
    }

    private void initRightType(Key key) {
        if (key.codes[0] == 0) {
            rightType = 1;//0
        } else if (key.codes[0] == 88) {
            rightType = 2;//X
        } else if (key.codes[0] == 46) {
            rightType = 3; //点
        } else if (key.codes[0] == -4 && key.label.equals("完成")) {
            rightType = 4; //完成
        } else if (key.codes[0] == -4 && key.label.equals("下一项")) {
            rightType = 5; //next
        }
    }

    public int getRightType() {
        return this.rightType;
    }

    private void drawText(Canvas canvas, Key key) {
        Rect bounds = new Rect();
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        if (key.codes[0] == 46) {
            paint.setTextSize(70);
        } else {
            paint.setTextSize(40);
        }
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        if (mKeyBoard.equals(KeyboardUtil.numKeyboard)) {
            if (key.label != null) {
                paint.getTextBounds(key.label.toString(), 0, key.label.toString()
                        .length(), bounds);
                canvas.drawText(key.label.toString(), key.x + (key.width / 2),
                        (key.y + key.height / 2) + bounds.height() / 2, paint);
            } else if (key.codes[0] == -3) {
                key.icon.setBounds(key.x + 9 * key.width / 20, key.y + 3
                        * key.height / 8, key.x + 11 * key.width / 20, key.y + 5
                        * key.height / 8);
                key.icon.draw(canvas);
            } else if (key.codes[0] == -5) {
                key.icon.setBounds(key.x + (int) (0.4 * key.width), key.y + (int) (0.328
                        * key.height), key.x + (int) (0.6 * key.width), key.y + (int) (0.672
                        * key.height));
                key.icon.draw(canvas);
            }
        }
    }
}
