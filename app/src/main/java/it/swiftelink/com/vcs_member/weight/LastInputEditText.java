package it.swiftelink.com.vcs_member.weight;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/9/24 10:26
 */
public class LastInputEditText extends AppCompatEditText {
    public LastInputEditText(Context context) {
        super(context);
    }

    public LastInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后面
        if(selStart==selEnd){//防止不能多选
            setSelection(getText().length());
        }
    }
}
