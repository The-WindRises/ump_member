package it.swiftelink.com.vcs_member.ui.activity.account;

import android.content.Intent;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.weight.psdInput.KeyboardTouchListener;
import it.swiftelink.com.vcs_member.weight.psdInput.KeyboardUtil;
import it.swiftelink.com.vcs_member.weight.psdInput.PayPsdInputView;

public class InputPayPasswordActivity extends BaseActivity<BaseContract.Presenter> {

    @BindView(R.id.et_input_pay_pass)
    PayPsdInputView etInputPayPass;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.root_view)
    LinearLayout rootView;

    private KeyboardUtil keyboardUtil;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_input_pay_password;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        etInputPayPass.setCompareFinish(new PayPsdInputView.onPasswordListener() {
            @Override
            public void inputFinished(String inputPsd) {
                Intent intent = new Intent();
                intent.putExtra("inputPsd", inputPsd);
                setResult(Common.RequstCode.PAY_INPUT_PASSWORD_RES_CODE, intent);
                finish();
            }
        });
        showKeyboard();
    }

    /**
     * 显示软件键盘
     */
    private void showKeyboard() {
        if (keyboardUtil == null) {
            keyboardUtil = new KeyboardUtil(this, rootView, scrollView);
            keyboardUtil.setKeyBoardStateChangeListener(new KeyBoardStateListener());
            keyboardUtil.setInputOverListener(new inputOverListener());
        }
        KeyboardTouchListener touchListener = new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_NUM, -1);
        touchListener.onTouch(etInputPayPass, MotionEvent.ACTION_UP);
    }


    @Override
    public void finish() {
        if (null != keyboardUtil && keyboardUtil.isShow) {
            keyboardUtil.hideKeyboardLayout();
        }
        super.finish();
    }

    @OnClick(R.id.iv_close_input_psd)
    public void onViewClicked() {
        finish();
    }

    /**
     * 软键盘状态变化预留监听
     */
    class KeyBoardStateListener implements KeyboardUtil.KeyBoardStateChangeListener {

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {

        }
    }

    /**
     * 软键盘特殊案按键触发预留监听
     */
    class inputOverListener implements KeyboardUtil.InputFinishListener {

        @Override
        public void inputHasOver(int onclickType, EditText editText) {

        }
    }
}
