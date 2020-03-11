package it.swiftelink.com.common.app;

/**
 * @author qiujuer
 */

public class Common {

    /**
     * 网络请求的状态
     */
    public interface NetState {
        int STATE_EMPTY = 0x001;
        int STATE_RETRY = 0x002;
        int STATE_CONTENT = 0x003;
        int STATE_LOADING = 0x004;
    }

    /**
     * 本地sp存储
     */
    public interface SPApi {
        String TOKEN = "accessToken";
        String ID = "id";
        String USER_NAME = "userName";
        String USER_HEADER_IMAGE = "userHeaderImage";
        String LOGINID = "login_id";


        String PATIENT_NAME = "patientName";
        String DOCTOR_NAME = "doctor_name";
        String DOCTOR_ID = "doctor_id";
        String DOCTOR_HEADER_IMAGE = "doctor_header_image";

        String USER_ID = "user_id";
        String UUID = "uuid";

        String USER_TOKEN = "userToken";
        String SDK_APP_ID = "sdkappid";
        String ROOM_ID = "room_id";

        String SELECT_LANGUAGE = "select_language";

        String IS_MEMBER = "is_member";

        String VIDEO_END_TYPE = "video_end_type";
        String IS_FIRST = "is_first";

        String IM_USERID = "im_userid";
        String IM_USER_TOKEN = "im_user_token";

        String HONG_KONG = "territory";
    }


    public interface CommonStr {
        String LANGUAGE1 = "zh_CN";
        String LANGUAGE2 = "zh_TW";
        String LANGUAGE3 = "en_US";


        String INQUIRY_TYPE1 = "DRAFT";
        String INQUIRY_TYPE2 = "FINISH";
        String INQUIRY_TYPE3 = "ALL";
        String INQUIRY_TYPE4 = "WAITING";
        String INQUIRY_TYPE5 = "UNCONNECTED";


        String EVALUATE_TYPE1 = "Not_Evaluation";
        String EVALUATE_TYPE2 = "Evaluation";
        String EVALUATE_TYPE3 = "All";

        String TYPE1 = "Patient";
        String TYPE2 = "Doctor";

        String RECIPE_ORDER_TYPE1 = "PAYMENT";
        String RECIPE_ORDER_TYPE2 = "DELIVER";
        String RECIPE_ORDER_TYPE3 = "RECEIVING";
        String RECIPE_ORDER_TYPE4 = "FINISH";
        String RECIPE_ORDER_TYPE5 = "ALL";


        String SET_MEAL_TYPE1 = "PAYMENT";
        String SET_MEAL_TYPE2 = "FINISH";
        String SET_MEAL_TYPE3 = "ALL";
        String SET_MEAL_TYPE4 = "CANCLE";


        String HEALTH_HISTORY_TYPE1 = "ALLERGIES";
        String HEALTH_HISTORY_TYPE2 = "PAST";
        String HEALTH_HISTORY_TYPE3 = "FAMILY";

        String HEALTH_REPORT_TYPE1 = "1";
        String HEALTH_REPORT_TYPE2 = "2";
        String HEALTH_REPORT_TYPE3 = "3";

        String COUPON_TYPE1 = "Not_Use";
        String COUPON_TYPE2 = "Used";
        String COUPON_TYPE3 = "Expired";

        String RECIPE_INFO_TYPE1 = "NORMAL";
        String RECIPE_INFO_TYPE2 = "ISORDER";
        String RECIPE_INFO_TYPE3 = "INVALID";

        String ALL_MINE_CARD_TYPE="ALL_CARD";
        String USE_MINE_CARD_TYPE="USED_CARD";
        String LAPSE_MINE_CARD_TYPE="LAPSE_CARD";


        String PAY_SUCCESS = "PAY_SUCCESS";
        String PAY_ERROR = "PAY_ERROR";

        String GENDER_TYPE1 = "MALE";
        String GENDER_TYPE2 = "FEMALE";

        String Pay_PSD_TYPE1 = "set_pwd";
        String Pay_PSD_TYPE2 = "pay_pwd";

        String ENTER_FROM_MAIN = "enter_from_main";
        String ENTER_FROM_FORESEE_INQUIRY = "enter_from_foresee_inquiry";
        String ENTER_FROM_INQUIRY = "enter_from_inquiry";

        String JOB_TITLE1 = "RESIDENTSRESIDENTS";
        //        String JOB_TITLE1 ="RESIDENTS";
        String JOB_TITLE2 = "ATTENDING";
        String JOB_TITLE3 = "DEPUTYCHIEFPHYSICIAN";
        String JOB_TITLE4 = "CHIEFPHYSICIAN";


        String TEMPERATURE_TYPE1 = "ORALCAVITY";
        String TEMPERATURE_TYPE2 = "UNDERARM";
        String TEMPERATURE_TYPE3 = "ANUS";


        String BLOODSUGAR_TYPE1 = "FASTING";
        String BLOODSUGAR_TYPE2 = "AFTERMEAL";
        String BLOODSUGAR_TYPE3 = "RANDOM";

        String PACKAGE_CARD_ID="package_id";


    }


    /**
     * 网络请求
     */
    public interface UrlApi {
        int GET_LOGIN = 0x001;
        int EDIT_INQUIRY = 0x002;
        int TO_INQUIRY = 0x003;
        int GET_INQUIRY_LIST = 0x004;
        int GET_INQUIRY_REPORT_LIST = 0x005;
        int GET_EVALUATION_LIST = 0x006;
        int SAVE_ADDRESS = 0x007;
        int DELETE_ADDRESS = 0x008;
        int GET_ADDRESS_DETAIL = 0x009;
        int GET_ADDRESS_LIST = 0x010;
        int SET_ADDRESS_DEFAULT = 0x011;
        int GET_COUPON_LIST = 0x012;
        int GET_SETMEAL_LIST = 0x013;
        int GET_BINNER_LIST = 0x014;
        int GET_PHONE_CODE = 0x015;
        int POST_REGISTER = 0x016;
        int GET_RECIPE_LIST = 0x017;
        int GET_INQUIRY_REPORT_INFO = 0x018;
        int GET_COLLECT_DOCTOR_LIST = 0x019;
        int GET_RECIPE_INFO = 0x020;
        int GET_INQUIRY_INFO = 0x021;
        int GET_REMIND_MESSAGE_COUNT = 0x022;
        int GET_MSG_LIST = 0x023;
        int MSG_READ = 0x024;
        int GET_TRTC_CONFIG = 0x025;
        int GET_USER_INFO = 0x26;
        int TO_EVALUATE = 0x27;
        int SAVE_EVALUATE = 0x28;
        int GET_ORDERINFO_FROM_DETAIL = 0x29;
        int GET_EVALUATION_LABEL = 0x30;
        int RECIPE_ORDER_LIST = 0x31;
        int GET_HEALTH_HISTORY_LIST = 0x32;
        int GET_HEALTH_REPORT_LIST = 0x33;
        int GET_PACKAGE_ORDER_LIST = 0x34;
        int EDIT_HEALTH_REPORT = 0x35;
        int GET_HEALTH_HISTORY_DATA = 0x36;
        int EDIT_HEALTH_DATA = 0x37;
        int GET_VIDEO_CHAT_CONFIG = 0x38;
        int CANCEL_INQUIRY = 0x39;
        int END_INQUIRY = 0x40;
        int GET_DOCTOR_INFO = 0x41;
        int COLLECT_DOCTOR = 0x42;
        int CANCEL_COLLECT_DOCTOR = 0x43;
        int CHANGE_PSD = 0x44;
        int FORGET_PSD = 0x45;
        int GET_ORDERINFO_FROM_ORDERID = 0x46;
        int EDIT_USER_INFO = 0x47;
        int GET_BALANCE_LIST = 0x48;
        int GET_HELP_LIST = 0x49;
        int BIND_PHONE = 0x50;
        int GET_ISMEMBER = 0x51;
        int GET_FORESEEINQUIRY_IMAGE = 0x52;
        int GET_WEIXINPAY_CONFIG = 0x53;
        int GET_ZHIFUBOPAY_CONFIG = 0x54;
        int GET_DEPARTMENTS = 0x55;
        int GET_PACKAGEORDER_CONFIRMINFO = 0x56;
        int CONFIRM_ORDER_RECEIVED = 0x57;
        int REFUND_ORDER = 0x58;
        int GET_UPLOAD = 0x59;
        int GET_LOGISTICS = 0x60;
        int PAY_AMOUNT = 0x61;
        int CHECK_PASSWORD = 0x62;
        int SET_PAYPSD = 0x63;
        int GET_RECORD_LIST = 0x64;
        int CONFIRM_RECIPEORDER = 0x65;
        int GET_AGEERRMENT = 0x66;
        int GET_EDIT_INQUIRY_TAG = 0x67;
        int LOGIN_BY_PHONE = 0x68;
        int GET_SHARE_H5 = 0x69;
        int GET_MARKEDWORDS = 0x70;
        int GET_MY_PKCKAGECARD=0x71;
        int POST_RECEIVIE_CARD=0x72;
        int GET_INQUIRYVALID_CARD=0x73;}

    public interface RequstCode {
        int SELECT_ADDRESS = 0x001;
        int COMPEILE_ADDRESS = 0x002;
        int PAY_INPUT_PASSWORD_RES_CODE = 0x003;
        int SELECT_COUPON = 0x004;
        //不强制更新
        int ISFORCENO = 0;
        //强制更新
        int ISFORCE = 1;

    }


    public interface EventbusType {
        int EVENT_PAY_WX_RESULT = 0X001;
        int LOGIN_WX = 0x002;
        int EVALUATE_SUCCESS = 0x003;
        int PAY_FINISH = 0x004;
        int END_VIDEO_INQUIRY = 0x005;
        int VIDEO_FLOAT_DISMISS = 0x006;
        int TO_MESSAGE_CHAT = 0x007;
    }

    public interface PackageType{
        int CARD_NORMAL=1;
        int CARD_EXPERIENCE=2;
        int CARD_SECONDARY=3;
    }


}
