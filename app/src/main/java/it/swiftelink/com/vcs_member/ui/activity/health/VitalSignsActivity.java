package it.swiftelink.com.vcs_member.ui.activity.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.health.EditHealthDataModel;
import it.swiftelink.com.factory.presenter.health.HealthDataContract;
import it.swiftelink.com.factory.presenter.health.HealthDataPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;

public class VitalSignsActivity extends BasePresenterActivity<HealthDataContract.Presenter> implements HealthDataContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.et_height)
    EditText etHeight;
    @BindView(R.id.et_weight)
    EditText etWeight;
    @BindView(R.id.et_animal_heat)
    EditText etAnimalHeat;
    @BindView(R.id.et_systolic_pressure)
    EditText etSystolicPressure;
    @BindView(R.id.et_low_tension)
    EditText etLowTension;
    @BindView(R.id.et_pulse)
    EditText etPulse;
    @BindView(R.id.et_blood_glucose_level)
    EditText etBloodGlucoseLevel;
    @BindView(R.id.et_head_circumference)
    EditText etHeadCircumference;
    @BindView(R.id.et_allergic_history)
    EditText etAllergicHistory;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.spinner_temperature)
    Spinner spinnerTemperature;
    @BindView(R.id.spinner_blood_glucose)
    Spinner spinnerBloodGlucose;


    private static int TEMPERATURE_TYPE1 = 0x001;
    private static int TEMPERATURE_TYPE2 = 0x002;
    private static int TEMPERATURE_TYPE3 = 0x003;


    private static int BLOODGLUCOSE_TYPE1 = 0x001;
    private static int BLOODGLUCOSE_TYPE2 = 0x002;
    private static int BLOODGLUCOSE_TYPE3 = 0x003;

    private int temperatureType = TEMPERATURE_TYPE1;
    private int bloodGlucoseType = BLOODGLUCOSE_TYPE1;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, VitalSignsActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vital_signs;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_vital_signs));


        spinnerTemperature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    temperatureType = TEMPERATURE_TYPE1;
                }
                if (position == 1) {
                    temperatureType = TEMPERATURE_TYPE2;
                }
                if (position == 2) {
                    temperatureType = TEMPERATURE_TYPE3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                temperatureType = TEMPERATURE_TYPE1;
            }
        });

        spinnerBloodGlucose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    bloodGlucoseType = BLOODGLUCOSE_TYPE1;
                }
                if (position == 1) {
                    bloodGlucoseType = BLOODGLUCOSE_TYPE2;
                }
                if (position == 2) {
                    bloodGlucoseType = BLOODGLUCOSE_TYPE3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                bloodGlucoseType = BLOODGLUCOSE_TYPE1;

            }
        });

    }

    @Override
    protected HealthDataContract.Presenter initPresenter() {
        return new HealthDataPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_submit:
                saveData();
                break;
        }
    }

    private void saveData() {

        int inputCouont = 0;

        String height = etHeight.getText().toString().trim();
        String weight = etWeight.getText().toString().trim();
        String animalHeat = etAnimalHeat.getText().toString().trim();
        String systolicPressure = etSystolicPressure.getText().toString().trim();
        String lowTension = etLowTension.getText().toString().trim();

        String pulse = etPulse.getText().toString().trim();


        String bloodGlucoseLevel = etBloodGlucoseLevel.getText().toString().trim();

        String headCircumference = etHeadCircumference.getText().toString().trim();

        String allergicHistory = etAllergicHistory.getText().toString().trim();


        if (!TextUtils.isEmpty(height)) {

            inputCouont++;
        }

        if (!TextUtils.isEmpty(weight)) {
            inputCouont++;
        }

        if (!TextUtils.isEmpty(animalHeat)) {
            inputCouont++;
        }

        if (!TextUtils.isEmpty(systolicPressure)) {
            inputCouont++;
        }

        if (!TextUtils.isEmpty(lowTension)) {
            inputCouont++;
        }

        if (!TextUtils.isEmpty(pulse)) {
            inputCouont++;
        }

        if (!TextUtils.isEmpty(bloodGlucoseLevel)) {
            inputCouont++;
        }
        if (!TextUtils.isEmpty(headCircumference)) {
            inputCouont++;
        }
        if(!TextUtils.isEmpty(allergicHistory)){
            inputCouont++;
        }


        if (inputCouont > 0) {
            EditHealthDataModel dataModel = new EditHealthDataModel();

            if (temperatureType == TEMPERATURE_TYPE1) {
                dataModel.setTemperatureUnderarm(animalHeat);
            }

            if (temperatureType == TEMPERATURE_TYPE2) {
                dataModel.setTemperatureOralCavity(animalHeat);
            }
            if (temperatureType == TEMPERATURE_TYPE3) {
                dataModel.setTemperatureAnus(animalHeat);
            }


            if (bloodGlucoseType == BLOODGLUCOSE_TYPE1) {
                dataModel.setBloodSugarFasting(bloodGlucoseLevel);
            }

            if (bloodGlucoseType == BLOODGLUCOSE_TYPE2) {
                dataModel.setBloodSugarAfterMeal(bloodGlucoseLevel);
            }

            if (bloodGlucoseType == BLOODGLUCOSE_TYPE3) {
                dataModel.setBloodSugarRandom(bloodGlucoseLevel);
            }


            if(!TextUtils.isEmpty(systolicPressure)&&TextUtils.isEmpty(lowTension)){
                App.showToast(R.string.tips_please_input_low_prssure1);
                return;

            }
            if(TextUtils.isEmpty(systolicPressure)&&!TextUtils.isEmpty(lowTension)){
                App.showToast(R.string.tips_input_height_pressure1);
                return;
            }

//            if(!TextUtils.isEmpty(height)&&TextUtils.isEmpty(weight)){
//                App.showToast(R.string.tips_please_input_weight);
//                return;
//            }
//
//            if(TextUtils.isEmpty(height)&&!TextUtils.isEmpty(weight)){
//                App.showToast(R.string.tips_please_input_weight);
//                return;
//            }
            dataModel.setCreationDate(new Date().getTime() + "");
            dataModel.setHeight(height);
            dataModel.setWeight(weight);
            dataModel.setBloodPressureHigh(systolicPressure);
            dataModel.setBloodPressureLow(lowTension);
            dataModel.setPulse(pulse);
            dataModel.setHeadCircumference(headCircumference);
            dataModel.setAllergies(allergicHistory);

            mPresenter.editHealthData("22", dataModel);
        } else {
            App.showToast(R.string.msg_fill_in_at_least_two);
        }


    }

    @Override
    public void editHealthDataSuccess() {

        showContent();
        App.showToast(R.string.msg_save_success);
        finish();
    }


    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {

        App.showToast(errorMsg);
    }
}
