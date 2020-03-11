package it.swiftelink.com.vcs_member.ui.activity.appointment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.tabs.TabLayout;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.appointment.ForeseeInquiryImageResModel;
import it.swiftelink.com.factory.model.appointment.ShareH5MsgResModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.presenter.inquiry.ForeseeInquiryContract;
import it.swiftelink.com.factory.presenter.inquiry.ForeseeInquiryPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.base.Image;
import it.swiftelink.com.vcs_member.ui.activity.account.LoginActivity;
import it.swiftelink.com.vcs_member.ui.activity.inquiry.EditInquiryActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.SelectPackageCardActivity;
import it.swiftelink.com.vcs_member.wxapi.WechatUtils;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * 预问诊详情
 */
public class ForeseeInquiryDetailActivity extends BasePresenterActivity<ForeseeInquiryContract.Presenter> implements ForeseeInquiryContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rvc_foresee)
    RecyclerView rvc_foresee;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.tb_foresee_inquiry)
    TabLayout tbForeseeInquiry;
    @BindView(R.id.btn_right)
    ImageView btnRight;
//    private boolean isMember = false;

    private BaseRecyclerAdapter<Image> mAdapter;
    Bitmap imageBitmap = null;

    private ShareH5MsgResModel.DataBean shareResData;

    private boolean isDialogShow = false;
    private int useCardType;
    private List<EvaluateLabelResModel.DataBean.DataListBean> dataList;
    private TagFlowLayout fl_dialog_evaluate;


    public static void show(Activity activity) {

        Intent intent = new Intent(activity, ForeseeInquiryDetailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_foresee_inquiry_detail;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_profile_details));
        btnRight.setVisibility(View.GONE);

        rvc_foresee.setLayoutManager(new LinearLayoutManager(this));

        rvc_foresee.setAdapter(mAdapter = new BaseRecyclerAdapter<Image>() {
            @Override
            public int getViewType(int pos) {
                if (pos == 0) {
                    return R.layout.item_header_foresee_inquiry;
                } else {
                    return R.layout.layout_vp_item_foresee_inquiry;
                }

            }

            @Override
            protected ViewHolder<Image> createViewHolder(View root, int viewType) {
                if (viewType == R.layout.item_header_foresee_inquiry) {
                    return new MyHearderViewHolder(root);
                } else {
                    return new MyViewHolder(root);
                }

            }

            @Override
            public void onUpdate(Image integer, ViewHolder<Image> viewHolder) {

            }
        });

        tbForeseeInquiry.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        scrollTo(0);
                        break;
                    case 1:
                        scrollTo(1);
                        break;
                    case 2:
                        scrollTo(2);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        rvc_foresee.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = layoutManager.findFirstVisibleItemPosition();
                if (position > 0) {
                    tbForeseeInquiry.setVisibility(View.VISIBLE);
                } else {
                    tbForeseeInquiry.setVisibility(View.GONE);
                }


            }
        });
    }

    //滑动到指定位置
    public void scrollTo(int position) {
        switch (position) {
            case 0:
                rvc_foresee.smoothScrollToPosition(1);
                break;
            case 1:
                rvc_foresee.smoothScrollToPosition(2);
                break;
            case 2:
                rvc_foresee.smoothScrollToPosition(3);
                break;
            default:
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getForeseeInquiryImage(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1));

//        mPresenter.getShareH5Msg("wzInfo", App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mPresenter.getIsMember();
    }

    @Override
    protected ForeseeInquiryContract.Presenter initPresenter() {
        return new ForeseeInquiryPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }


    @OnClick({R.id.btn_back, R.id.tv_inquiry_now, R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_inquiry_now:
//                if (isMember) {
//                    EditInquiryActivity.show(this, Common.CommonStr.ENTER_FROM_FORESEE_INQUIRY);
//                } else {
//                    MemberBuyActivity.show(this);
//                }
                String token = App.getSPUtils().getString(Common.SPApi.TOKEN);
                if (TextUtils.isEmpty(token)) {
                    LoginActivity.show(this);
                } else {

                    mPresenter.getInquiryValidPackageCard();
                }


                break;
            case R.id.btn_right:
                if (shareResData != null && imageBitmap != null) {
                    WechatUtils.getInstance(this).sendToWeiXin(shareResData.getTitle(), shareResData.getLink(), shareResData.getDescribe(), imageBitmap, 0);
                }
                break;
        }
    }


//    @Subscribe
//    public void Event(ObjectEvent objectEvent) {
//        switch (objectEvent.getType()) {
//            case Common.EventbusType.END_VIDEO_INQUIRY:
//                String videoEndType = App.getSPUtils().getString(Common.SPApi.VIDEO_END_TYPE);
//                useCardType = objectEvent.getCardType();
//                if (Common.CommonStr.ENTER_FROM_MAIN.equals(videoEndType)) {
//                    SaveEvaluateModel evaluateModel = (SaveEvaluateModel) objectEvent.getObj();
//                    startEvaluate(evaluateModel);
//                }
//                break;
//            default:
//                break;
//        }
//    }


//    private void startEvaluate(final SaveEvaluateModel evaluateModel) {
//        if (!isDialogShow) {
//            CustomDialog.
//                    newInstance(R.layout.dialog_evaluate)
//                    .setViewCreateListner(new CustomDialog.ViewCreateListener() {
//                        @Override
//                        public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {
//
//                            ImageView iv_doctor_header = viewGroup.findViewById(R.id.doctorIconIv);
//                            TextView tv_doctor_name = viewGroup.findViewById(R.id.doctorNameTv);
//                            final MaterialRatingBar rb_grade = viewGroup.findViewById(R.id.rb_grade);
//                            fl_dialog_evaluate = viewGroup.findViewById(R.id.tabLayout);
//                            Button btn_confirm_evaluate = viewGroup.findViewById(R.id.submit);
//                            LinearLayout iv_close = viewGroup.findViewById(R.id.closeIv);
//
//                            tv_doctor_name.setText(App.getSPUtils().getString(Common.SPApi.DOCTOR_NAME, ""));
//
//                            GlideLoadUtils.getInstance().glideLoadCircle(ForeseeInquiryDetailActivity.this,
//                                    App.getSPUtils().getString(Common.SPApi.DOCTOR_HEADER_IMAGE), iv_doctor_header, R.mipmap.icon_dc_man);
//
//                            getEvaluateLabelList(rb_grade.getProgress());
//                            rb_grade.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//                                @Override
//                                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                                    getEvaluateLabelList(rating);
//                                }
//                            });
//
//                            btn_confirm_evaluate.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    SaveEvaluateModel saveEvaluateModel = new SaveEvaluateModel();
//
//                                    saveEvaluateModel.setDiagnosisId(evaluateModel.getDiagnosisId());
//                                    saveEvaluateModel.setInitialDiagnosis(evaluateModel.getInitialDiagnosis());
//                                    saveEvaluateModel.setPatientScore(rb_grade.getProgress());
//
//                                    Set<Integer> selectedList = fl_dialog_evaluate.getSelectedList();
//                                    StringBuffer buffer = new StringBuffer();
//                                    for (Integer selectPos : selectedList) {
//                                        buffer.append(dataList.get(selectPos).getName() + ",");
//                                    }
//                                    if (buffer.toString().equals("")) {
//                                        App.showToast(R.string.plase_sel_tag);
//                                        return;
//                                    }
//                                    saveEvaluateModel.setPatientEvaluation(buffer.toString());
//                                    saveEvaluateModel.setDoctorStatus(Common.CommonStr.EVALUATE_TYPE1);
//                                    saveEvaluateModel.setPatientStatus(Common.CommonStr.EVALUATE_TYPE2);
//                                    mPresenter.saveEvaluate(saveEvaluateModel);
//                                    isDialogShow = false;
//                                    dialog.dismiss();
//
//                                }
//                            });
//
//                            iv_close.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    SaveEvaluateModel saveEvaluateModel = new SaveEvaluateModel();
//
//                                    saveEvaluateModel.setDiagnosisId(evaluateModel.getDiagnosisId());
//                                    saveEvaluateModel.setInitialDiagnosis(evaluateModel.getInitialDiagnosis());
//
//                                    saveEvaluateModel.setDoctorStatus(Common.CommonStr.EVALUATE_TYPE1);
//                                    saveEvaluateModel.setPatientStatus(Common.CommonStr.EVALUATE_TYPE1);
//                                    mPresenter.saveEvaluate(saveEvaluateModel);
//                                    App.getSPUtils().put("IS_DIALOG",false);
//                                    dialog.dismiss();
//
//                                }
//                            });
//
//                        }
//                    })
//                    .setMargin(30)
//                    .setOutCancel(false)
//                    .show(getSupportFragmentManager());
//            isDialogShow = true;
//        }
//
//    }

    private void getEvaluateLabelList(float score) {
        EvaluateLabelModel labelModel = new EvaluateLabelModel();
        labelModel.setType("Member");
        labelModel.setLanguage(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1));
        labelModel.setScore((int) score);

        mPresenter.getEvaluateLabel(labelModel);
    }

//    @Override
//    public void getIsMemberSuccess(BooleanResModel model) {
//        isMember = true;
//
//    }

    @Override
    public void getForeseeInquiryImageSuccess(ForeseeInquiryImageResModel resModel) {

        if (resModel.getData() != null) {

            ForeseeInquiryImageResModel.DataBean data = resModel.getData();
            setListImage(data);
        }

    }

    @Override
    public void saveEvaluateSuccess() {
        showContent();
        if (useCardType == 2) {
            com.kongzue.dialog.v2.CustomDialog.build(this, R.layout.dialog_buy_tip, (dialog, rootView) -> {

                rootView.findViewById(R.id.confirm_cancel_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.doDismiss();
                    }
                });
                rootView.findViewById(R.id.confirm_buy_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SelectPackageCardActivity.show(ForeseeInquiryDetailActivity.this);
                        dialog.doDismiss();
                    }
                });
            }).setCanCancel(true).showDialog();

        }
    }

    @Override
    public void getEvaluateLabelSuccess(EvaluateLabelResModel model) {
        showContent();
        dataList = model.getData().getDataList();

        final LayoutInflater mInflater = LayoutInflater.from(this);
        if (dataList != null && dataList.size() > 0) {
            fl_dialog_evaluate.setAdapter(new TagAdapter<EvaluateLabelResModel.DataBean.DataListBean>(dataList) {
                @Override
                public View getView(FlowLayout parent, int position, EvaluateLabelResModel.DataBean.DataListBean dataListBean1) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.item_flow_tv,
                            fl_dialog_evaluate, false);
                    tv.setText(dataListBean1.getName());
                    return tv;
                }
            });
        }
    }

    @Override
    public void getShareH5MsgSuccess(ShareH5MsgResModel resModel) {

    }
//
//    @Override
//    public void getShareH5MsgSuccess(ShareH5MsgResModel resModel) {
//        this.shareResData = resModel.getData();
//
//        Observable.create(new Observable.OnSubscribe<Bitmap>() {
//
//            @Override
//            public void call(final Subscriber<? super Bitmap> subscriber) {
//
//                Glide.with(ForeseeInquiryDetailActivity.this).asBitmap().load(shareResData.getImgUrl()).into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        subscriber.onNext(resource);
//                    }
//                });
//
//            }
//
//        }).subscribe(new Action1<Bitmap>() {
//
//            @Override
//            public void call(final Bitmap bitmap) {
//
//                imageBitmap = bitmap;
//            }
//        });
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200 && resultCode==1){
            this.finish();
        }
    }

    @Override
    public void getInquiryValidPackageCardSuccess(InquiryValidCardResModel resModel) {
        if (null == resModel.getData()) {
            return;
        }
        if (resModel.getData().isSuccessX()) {
            //当前可问诊
//            EditInquiryActivity.show(this, Common.CommonStr.ENTER_FROM_MAIN);

            Intent intent = new Intent(this, EditInquiryActivity.class);
            intent.putExtra("enterType", Common.CommonStr.ENTER_FROM_MAIN);
            this.startActivityForResult(intent,200);


        } else {

            //当前不可问诊
//            int type=resModel.getData().getType();
            //1:当前用户没有套餐卡
            //2：当前时间不符合套餐卡使用条件
//            if(type==1) {
            SelectPackageCardActivity.show(this);
//            }else if(type==2){
//                Toast.makeText(this,"当前时间不符合套餐卡使用条件",Toast.LENGTH_SHORT).show();
//            }
        }

    }

    private List<Image> mImageList;

    private void setListImage(ForeseeInquiryImageResModel.DataBean data) {
        final List<String> picList = new ArrayList<>();
        picList.add(0, data.getDetailsImg());
        picList.add(1, data.getProductAdvantageImg());
        picList.add(2, data.getUsageNoticeImg());
        picList.add(3, data.getCommonProblemImg());

        if (mImageList == null) {
            mImageList = new LinkedList<>();
        } else {
            mImageList.clear();
        }


        Observable.create(new Observable.OnSubscribe<List<Image>>() {

            @Override
            public void call(final Subscriber<? super List<Image>> subscriber) {

                for (int i = 0; i < picList.size(); i++) {

                    String imageUrl = picList.get(i);
                    final int finalI = i;
                    if (!isDestroy(ForeseeInquiryDetailActivity.this)) {
                        Glide.with(ForeseeInquiryDetailActivity.this).download(imageUrl).into(new SimpleTarget<File>() {
                            @Override
                            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {

                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = true;// 这个方式不会在内存创建一张图片，
                                BitmapFactory.decodeFile(resource.getAbsolutePath(), options);
                                mImageList.add(new Image(resource, options.outWidth, options.outHeight, finalI));
                                subscriber.onNext(mImageList);
                            }
                        });

                    }
                }

                showContent();

            }

        }).subscribe(new Action1<List<Image>>() {

            @Override
            public void call(final List<Image> imageList) {
                if (imageList.size() == picList.size()) {
                    Collections.sort(imageList);
                    mAdapter.replice(imageList);
                }

            }
        });


    }


    public  boolean isDestroy(Activity mActivity) {
        if (mActivity == null || mActivity.isFinishing() || mActivity.isDestroyed()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void retry(int type) {
        if (Common.UrlApi.GET_FORESEEINQUIRY_IMAGE == type) {
            mPresenter.getForeseeInquiryImage(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE
                    , Common.CommonStr.LANGUAGE1));
        }

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {

        if (Common.UrlApi.GET_ISMEMBER == type) {
            showContent();
//            isMember = false;`
        } else if (Common.UrlApi.GET_FORESEEINQUIRY_IMAGE == type) {
            showRetry(type);
        } else {
            showContent();
        }

    }

    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<Image> {

        @BindView(R.id.iv_vp)
        LargeImageView ivVp;

        public MyViewHolder(View itemView) {
            super(itemView);

//            LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_vp_item_foresee_inquiry, null);
        }

        @Override
        protected void onBind(Image data) {

            ivVp.setEnabled(false);
            int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
            int height = width * data.height / data.width;
            ViewGroup.LayoutParams layoutParams = ivVp.getLayoutParams();
            layoutParams.height = height;
            ivVp.setLayoutParams(layoutParams);
            ivVp.setImage(new FileBitmapDecoderFactory(data.file));

        }
    }


    class MyHearderViewHolder extends BaseRecyclerAdapter.ViewHolder<Image> {

        @BindView(R.id.tv_top)
        LargeImageView ivTop;
        @BindView(R.id.tb_foresee_inquiry)
        TabLayout tbForeseeInquiry;

        public MyHearderViewHolder(View itemView) {
            super(itemView);
//            LayoutInflater.from(getBaseContext()).inflate(R.layout.item_header_foresee_inquiry, null);
        }

        @Override
        protected void onBind(Image data) {

            ivTop.setEnabled(false);
            int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
            int height = width * data.height / data.width;
            ViewGroup.LayoutParams layoutParams = ivTop.getLayoutParams();
            layoutParams.height = height;
            ivTop.setLayoutParams(layoutParams);
            ivTop.setImage(new FileBitmapDecoderFactory(data.file));

            tbForeseeInquiry.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    int position = tab.getPosition();
                    switch (position) {
                        case 0:
                            scrollTo(0);
                            break;
                        case 1:
                            scrollTo(1);
                            break;
                        case 2:
                            scrollTo(2);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }
    }


}
