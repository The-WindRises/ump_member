package it.swiftelink.com.vcs_member.ui.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.utils.TxtUtils;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.AddressDetailResModel;
import it.swiftelink.com.factory.model.account.SaveAddressModel;
import it.swiftelink.com.factory.presenter.account.EditAddressContract;
import it.swiftelink.com.factory.presenter.account.EditAddressPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.weight.addressSelector.AddressSelector;
import it.swiftelink.com.vcs_member.weight.addressSelector.City;
import it.swiftelink.com.vcs_member.weight.addressSelector.CityInterface;
import it.swiftelink.com.vcs_member.weight.addressSelector.OnItemClickListener;
import it.swiftelink.com.vcs_member.weight.addressSelector.ProviceDataBean;

public class CompileAddressActivity extends BasePresenterActivity<EditAddressContract.Presenter> implements EditAddressContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_address_detail)
    EditText etAddressDetail;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.tv_district)
    TextView tvDistrict;
    @BindView(R.id.btn_delete_address)
    Button btnDeleteAddress;
    @BindView(R.id.tv_select_phone_type)
    TextView tvSelectPhoneType;
    private String addressId;
    private ProviceDataBean proviceDataBean;

    private static final String TAG = "CompileAddressActivity";
    private AddressDetailResModel.DataBean data;

    public static void show(Activity activity, String addressId) {
        Intent intent = new Intent(activity, CompileAddressActivity.class);

        intent.putExtra("addressId", addressId);
        activity.startActivityForResult(intent, Common.RequstCode.COMPEILE_ADDRESS);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_compile_addrees;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_shipping_address));
    }

    @Override
    protected void initData() {
        super.initData();


        String data = TxtUtils.get(App.getInstance(), R.raw.province);

        Log.i(TAG, "initData: data" + data);


        Gson gson = new Gson();

        proviceDataBean = gson.fromJson(data, ProviceDataBean.class);

        Log.i(TAG, "initData: proviceDataBean" + proviceDataBean);

        Intent intent = getIntent();
        if (intent != null) {
            addressId = intent.getStringExtra("addressId");

            if (!TextUtils.isEmpty(addressId)) {
                mPresenter.getAddressDetail(addressId);
            } else {
                btnDeleteAddress.setVisibility(View.GONE);
            }
        } else {
            btnDeleteAddress.setVisibility(View.GONE);
        }
    }


    @Override
    protected EditAddressContract.Presenter initPresenter() {
        return new EditAddressPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }


    @OnClick({R.id.btn_back, R.id.btn_save_address, R.id.btn_delete_address, R.id.tv_district
            , R.id.tv_select_phone_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_save_address:
                saveAddress();
                break;
            case R.id.btn_delete_address:
                mPresenter.deleteAddress(addressId);
                break;

            case R.id.tv_district:
                selectDistrict();
                break;

            case R.id.tv_select_phone_type:
                selectPhoneType();
                break;
        }
    }

    private void saveAddress() {

        String userName = etUsername.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String addressDetail = etAddressDetail.getText().toString().trim();
        String district = tvDistrict.getText().toString().trim();


        if (TextUtils.isEmpty(userName)) {
            App.showToast(R.string.msg_please_input_name);
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            App.showToast(R.string.please_input_phone);
            return;
        }

        if (!TxtUtils.isPhoneLegal(phone)) {
            App.showToast(R.string.please_input_phone_right);
            return;
        }

        if (TextUtils.isEmpty(district)) {
            App.showToast(R.string.please_input_area);
            return;
        }

        if (TextUtils.isEmpty(addressDetail)) {
            App.showToast(R.string.please_input_detail_address);
            return;
        }

        SaveAddressModel saveAddressModel = new SaveAddressModel();

        saveAddressModel.setName(userName);
        saveAddressModel.setMobile(phone);

        if (TextUtils.isEmpty(selectedProvince) && data != null) {
            saveAddressModel.setProvince(data.getProvince());
            saveAddressModel.setCity(data.getCity());
            saveAddressModel.setCounty(data.getCounty());
        }else{
            saveAddressModel.setProvince(selectedProvince);
            saveAddressModel.setCity(selectedCity);
            saveAddressModel.setCounty(selectedCounty);
        }
        saveAddressModel.setTown("");
        saveAddressModel.setId(addressId);
        saveAddressModel.setAddressDetail(addressDetail);

        mPresenter.saveAddress(saveAddressModel);
    }


    private String selectedProvince;
    private String selectedCity;
    private String selectedCounty;

    private int pos1 = -1;
    private int pos2 = -1;

    private void selectDistrict() {
        CustomDialog.
                newInstance(R.layout.dialog_select_address)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {


                        AddressSelector addressSelector = viewGroup.findViewById(R.id.address);

                        addressSelector.setTabAmount(3);
                        addressSelector.setCities(getCityList(-1, -1));

                        addressSelector.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void itemClick(AddressSelector addressSelector, CityInterface city, int tabPosition) {
                                switch (tabPosition) {
                                    case 0:
                                        pos1 = city.getPos();
                                        addressSelector.setCities(getCityList(pos1, -1));
                                        selectedProvince = city.getCityName();
                                        break;
                                    case 1:
                                        pos2 = city.getPos();
                                        addressSelector.setCities(getCityList(pos1, pos2));
                                        selectedCity = city.getCityName();
                                        break;
                                    case 2:
                                        selectedCounty = city.getCityName();
                                        tvDistrict.setText(selectedProvince + " " + selectedCity + " " + selectedCounty);
                                        dialog.dismiss();
                                        break;
                                }
                            }

                        });
                        addressSelector.setOnTabSelectedListener(new AddressSelector.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(AddressSelector addressSelector, AddressSelector.Tab tab) {
                                switch (tab.getIndex()) {
                                    case 0:
                                        addressSelector.setCities(getCityList(-1, -1));
                                        break;
                                }
                            }

                            @Override
                            public void onTabReselected(AddressSelector addressSelector, AddressSelector.Tab tab) {

                            }
                        });


                    }
                })
                .setOutCancel(false)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private ArrayList<City> cityList;

    private ArrayList<City> getCityList(int pos1, int pos2) {

        if (cityList == null) {
            cityList = new ArrayList<>();
        } else {
            cityList.clear();
        }


        List<ProviceDataBean.ProviceListBean> proviceList = proviceDataBean.getProviceList();

        Log.i(TAG, "getCityList: proviceList" + proviceList);
        if (pos1 == -1 && pos2 == -1) {
            for (int i = 0; i < proviceList.size(); i++) {
                ProviceDataBean.ProviceListBean proviceListBean = proviceList.get(i);
                City city = new City();
                city.setName(proviceListBean.getName());
                city.setPos(i);

                cityList.add(city);
            }

            return cityList;
        }


        if (pos1 != -1 && pos2 == -1) {
            ProviceDataBean.ProviceListBean proviceListBean = proviceList.get(pos1);

            List<ProviceDataBean.ProviceListBean.CityBean> cityBeanList = proviceListBean.getCity();

            for (int i = 0; i < cityBeanList.size(); i++) {

                ProviceDataBean.ProviceListBean.CityBean cityBean = cityBeanList.get(i);
                City city1 = new City();
                city1.setName(cityBean.getName());
                city1.setPos(i);

                cityList.add(city1);
            }

            return cityList;
        }

        if (pos1 != -1 && pos2 != -1) {
            ProviceDataBean.ProviceListBean proviceListBean = proviceList.get(pos1);

            List<ProviceDataBean.ProviceListBean.CityBean> cityBeanList = proviceListBean.getCity();

            ProviceDataBean.ProviceListBean.CityBean cityBean = cityBeanList.get(pos2);

            List<String> areaList = cityBean.getArea();

            for (int i = 0; i < areaList.size(); i++) {
                String areaBean = areaList.get(i);

                City city2 = new City();
                city2.setName(areaBean);
                city2.setPos(i);
                cityList.add(city2);
            }
        }
        return cityList;

    }

    @Override
    public void getAddressDetailSuccess(AddressDetailResModel model) {

        showContent();
        data = model.getData();
        etUsername.setText(data.getName());
        etPhone.setText(data.getMobile());
        etAddressDetail.setText(data.getAddressDetail());
        tvDistrict.setText(data.getProvince() + " " + data.getCity() + " " + data.getCounty() + " " + data.getTown());
    }

    @Override
    public void saveAddressSuccess(BaseResModel model) {
        App.showToast(R.string.msg_address_success);
        showContent();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void deleteAddressSuccess() {
        showContent();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type, errorCode,errorMsg);
        showContent();
        App.showToast(errorMsg);
    }

    private void selectPhoneType() {
        CustomDialog.
                newInstance(R.layout.dialog_select_phone_type)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {

                        final LinearLayout ll_phone_type1 = viewGroup.findViewById(R.id.ll_phone_type1);
                        final LinearLayout ll_phone_type2 = viewGroup.findViewById(R.id.ll_phone_type2);
                        final LinearLayout ll_phone_type3 = viewGroup.findViewById(R.id.ll_phone_type3);

                        ll_phone_type1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvSelectPhoneType.setText("+86");
                                dialog.dismiss();

                            }
                        });

                        ll_phone_type2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvSelectPhoneType.setText("+852");
                                dialog.dismiss();
                            }
                        });
                        ll_phone_type3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvSelectPhoneType.setText("+853");
                                dialog.dismiss();
                            }
                        });

                    }
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

}
