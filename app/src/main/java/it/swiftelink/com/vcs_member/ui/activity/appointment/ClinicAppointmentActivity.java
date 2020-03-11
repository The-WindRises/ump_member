package it.swiftelink.com.vcs_member.ui.activity.appointment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.widget.dialog.ConfirmDialog;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ClinicAppointmentActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;

    private boolean isAllowPermissions;


    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, ClinicAppointmentActivity.class));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_clinic_appointment;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_clinic_appointment));


    }


    @OnClick({R.id.btn_back, R.id.ll_to_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_to_call:
                if (isAllowPermissions) {
                    toCall();
                } else {
                    ClinicAppointmentActivityPermissionsDispatcher.toCallWithPermissionCheck(this);
                }
                break;
        }
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void toCall() {
        isAllowPermissions = true;
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "400-680-8069");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ClinicAppointmentActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void onShow(final PermissionRequest request) {

        ConfirmDialog.newInstance(getString(R.string.label_need_to_enable_phone_access),
                getString(R.string.cancel), getString(R.string.confirm),true)
                .setConfirmSelect(new ConfirmDialog.ConfirmSelect() {
                    @Override
                    public void onClickCancel() {
                        request.cancel();
                    }

                    @Override
                    public void onClickQuery() {
                        request.proceed();
                    }
                }).setMargin(50)
                .setOutCancel(true)
                .show(getSupportFragmentManager());

    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void onPermissionDenied() {
        App.showToast(R.string.msg_permission_to_call_has_been_denied);
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void onNerverAskAgain() {
        App.showToast(R.string.label_permission_denied_and_eject_disabled);
        App.toSelfSetting(this);
    }




}
