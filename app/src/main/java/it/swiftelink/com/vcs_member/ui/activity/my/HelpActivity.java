package it.swiftelink.com.vcs_member.ui.activity.my;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.widget.dialog.ConfirmDialog;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.mian.HelpListResModel;
import it.swiftelink.com.factory.presenter.main.HelpContract;
import it.swiftelink.com.factory.presenter.main.HelpPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.weight.exrecyclerview.ExtendedHolder;
import it.swiftelink.com.vcs_member.weight.exrecyclerview.ExtendedHolderFactory;
import it.swiftelink.com.vcs_member.weight.exrecyclerview.ExtendedNode;
import it.swiftelink.com.vcs_member.weight.exrecyclerview.ExtendedRecyclerViewBuilder;
import it.swiftelink.com.vcs_member.weight.exrecyclerview.ExtendedRecyclerViewHelper;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class HelpActivity extends BasePresenterActivity<HelpContract.Presenter> implements HelpContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcv_help_list)
    RecyclerView rcvHelpList;
    @BindView(R.id.stateView)
    StateView stateView;

    private boolean isAllowPermissions;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity,HelpActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    protected HelpContract.Presenter initPresenter() {
        return new HelpPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.help_center));
    }

    @Override
    protected void initData() {
        super.initData();

        mPresenter.getHelpList(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE,Common.CommonStr.LANGUAGE1));
    }

    @Override
    public void getHelpListSuccess(HelpListResModel model) {

        if (model.getData().size() > 0) {
            showContent();
            setHelpData(model.getData());

        } else {
            showEmpty();
        }

    }


    private void setHelpData(List<HelpListResModel.DataBean> dataList) {

        ArrayList<ExtendedNode> root = new ArrayList<>();
        for (HelpListResModel.DataBean dataListBean : dataList) {
            ExtendedNode parentNode = new ExtendedNode(dataListBean, false);

            List<HelpListResModel.DataBean.HelpListBean> helpListBeans = dataListBean.getHelpList();

            for (HelpListResModel.DataBean.HelpListBean helpListBean : helpListBeans) {

                ExtendedNode sonNode = new ExtendedNode(helpListBean);

                parentNode.addSon(sonNode);
            }

            root.add(parentNode);
        }

        ExtendedRecyclerViewBuilder.build(rcvHelpList)
                .init(root, new ExtendedHolderFactory() {
                    @Override
                    public ExtendedHolder getHolder(ExtendedRecyclerViewHelper helper, ViewGroup parent, int viewType) {
                        switch (viewType) {
                            case 0:
                                return new HelpHolder1(helper, LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rvc_help1, parent, false));
                            case 1:
                                return new HelpHolder2(helper, LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rvc_help2, parent, false));
                        }
                        return null;
                    }
                })
                .setEnableExtended(true)
                .complete();
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type,int errorCode , String errorMsg) {
       super.showError(type,errorCode ,errorMsg);
        showContent();
        App.showToast(errorMsg);
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

                    HelpActivityPermissionsDispatcher.toCallWithPermissionCheck(this);
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

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HelpActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
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

    class HelpHolder1 extends ExtendedHolder<HelpListResModel.DataBean> {

        private final TextView tv_help_item;

        public HelpHolder1(ExtendedRecyclerViewHelper helper, View itemView) {
            super(helper, itemView);

            tv_help_item = itemView.findViewById(R.id.tv_help_item);
        }

        @Override
        public void setData(ExtendedNode<HelpListResModel.DataBean> node) {

            HelpListResModel.DataBean listBean = node.data;

            tv_help_item.setText(listBean.getName());
        }

        @Override
        protected View getExtendedClickView() {
            return itemView;
        }
    }

    class HelpHolder2 extends ExtendedHolder<HelpListResModel.DataBean.HelpListBean> {
        private final TextView tv_help_item;

        public HelpHolder2(ExtendedRecyclerViewHelper helper, View itemView) {
            super(helper, itemView);
            tv_help_item = itemView.findViewById(R.id.tv_help_item);
        }

        @Override
        public void setData(ExtendedNode<HelpListResModel.DataBean.HelpListBean> node) {
            final HelpListResModel.DataBean.HelpListBean listBean = node.data;
            tv_help_item.setText(listBean.getTitle());

            tv_help_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HelpContentActivity.show(HelpActivity.this,listBean.getContent(),listBean.getTitle());
                }
            });
        }

        @Override
        protected View getExtendedClickView() {
            return itemView;
        }
    }
}
