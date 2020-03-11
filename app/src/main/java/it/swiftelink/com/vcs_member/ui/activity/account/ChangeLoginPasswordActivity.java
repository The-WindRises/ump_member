package it.swiftelink.com.vcs_member.ui.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.utils.TxtUtils;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.ChangePsdResModel;
import it.swiftelink.com.factory.presenter.account.ChangePsdContract;
import it.swiftelink.com.factory.presenter.account.ChangePsdPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;

public class ChangeLoginPasswordActivity extends BasePresenterActivity<ChangePsdContract.Presenter> implements ChangePsdContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_old_psd)
    EditText etOldPsd;
    @BindView(R.id.et_new_psd)
    EditText etNewPsd;
    @BindView(R.id.et_confirm_psd)
    EditText etConfirmPsd;
    @BindView(R.id.stateView)
    StateView stateView;


    public static void show(Activity activity){
        activity.startActivity(new Intent(activity,ChangeLoginPasswordActivity.class));
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_login_password;
    }

    @OnClick({R.id.btn_back, R.id.confirm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.confirm_btn:
                confirm();
                break;
        }
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_change_psd));
    }

    private void confirm() {

        String oldPsd = etOldPsd.getText().toString().trim();
        String newPsd = etNewPsd.getText().toString().trim();
        String confirmPsd = etConfirmPsd.getText().toString().trim();

        if(TextUtils.isEmpty(oldPsd)){
            App.showToast(R.string.please_input_old_psd);
            return;
        }

        if(TextUtils.isEmpty(newPsd)){
            App.showToast(R.string.label_input_new_psd);
            return;
        }

        if(!TxtUtils.chickedPassword(newPsd)){
            App.showToast("密码必须8-14数字，字母或者字符");
            return;
        }

        if(TextUtils.isEmpty(confirmPsd)){
            App.showToast(R.string.label_please_input_new_psd_again);
            return;
        }

        if(!newPsd.equals(confirmPsd)){
            App.showToast(R.string.label_two_new_passwords_do_not_match);
            return;
        }

        mPresenter.changePsd(Common.CommonStr.TYPE1,oldPsd,newPsd);

    }


    @Override
    protected ChangePsdContract.Presenter initPresenter() {
        return new ChangePsdPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @Override
    public void changePsdSuccess(ChangePsdResModel resModel) {

        if(resModel.getData()!=null){
            App.getSPUtils().put(Common.SPApi.USER_TOKEN,resModel.getData().getAccessToken());
        }
        App.showToast(R.string.msg_change_psd_success);
        showContent();
        finish();
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type,errorCode,errorMsg);
        showContent();
        App.showToast(errorMsg);
    }
}
