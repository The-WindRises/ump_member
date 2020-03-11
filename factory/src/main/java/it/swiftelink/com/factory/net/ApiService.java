package it.swiftelink.com.factory.net;


import java.util.List;

import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.factory.common.BooleanResModel;
import it.swiftelink.com.factory.common.UploadResModel;
import it.swiftelink.com.factory.model.InquiryReport.CofirmRecipeOrderModel;
import it.swiftelink.com.factory.model.InquiryReport.CofirmRecipeOrderResModel;
import it.swiftelink.com.factory.model.InquiryReport.InquiryReportResModel;
import it.swiftelink.com.factory.model.account.AddressDetailResModel;
import it.swiftelink.com.factory.model.account.AddressListResModel;
import it.swiftelink.com.factory.model.account.AgrreementResModel;
import it.swiftelink.com.factory.model.account.BalanceListResModel;
import it.swiftelink.com.factory.model.account.ChangePsdResModel;
import it.swiftelink.com.factory.model.account.GetPhoneCodeModel;
import it.swiftelink.com.factory.model.account.LoginByPhoneModel;
import it.swiftelink.com.factory.model.account.LoginModel;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.model.account.RegisterModel;
import it.swiftelink.com.factory.model.account.RegisterResModel;
import it.swiftelink.com.factory.model.account.SaveAddressModel;
import it.swiftelink.com.factory.model.appointment.CheckPayPasswordResmodel;
import it.swiftelink.com.factory.model.appointment.ForeseeInquiryImageResModel;
import it.swiftelink.com.factory.model.appointment.PackConfirmModel;
import it.swiftelink.com.factory.model.appointment.PackageOrderConfirmInfoResModel;
import it.swiftelink.com.factory.model.appointment.ShareH5MsgResModel;
import it.swiftelink.com.factory.model.card.CardListResModel;
import it.swiftelink.com.factory.model.card.FreePickPackageModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.doctor.CollectDoctorResModel;
import it.swiftelink.com.factory.model.doctor.DiagnosisDoctorResModel;
import it.swiftelink.com.factory.model.doctor.DoctorInfoResModel;
import it.swiftelink.com.factory.model.health.EditHealthDataModel;
import it.swiftelink.com.factory.model.health.EditHealthReportModel;
import it.swiftelink.com.factory.model.health.HealthDepartmentsResModel;
import it.swiftelink.com.factory.model.health.HealthHistoryListResModel;
import it.swiftelink.com.factory.model.health.HealthReportListResModel;
import it.swiftelink.com.factory.model.health.HistoryHealthDataListResModel;
import it.swiftelink.com.factory.model.health.RecordListResmodel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryResModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryTagResModel;
import it.swiftelink.com.factory.model.inquiry.InquiryDetailResModel;
import it.swiftelink.com.factory.model.inquiry.InquiryListResModel;
import it.swiftelink.com.factory.model.inquiry.InquiryReportListResModel;
import it.swiftelink.com.factory.model.inquiry.ToInquiryResModel;
import it.swiftelink.com.factory.model.message.MessageListResModel;
import it.swiftelink.com.factory.model.message.MessageRemindCountResModel;
import it.swiftelink.com.factory.model.mian.BinnerListResModel;
import it.swiftelink.com.factory.model.mian.HelpListResModel;
import it.swiftelink.com.factory.model.mian.MarkWordsResModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.model.mian.VersionResModel;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.EvaluateListResModel;
import it.swiftelink.com.factory.model.order.EvaluateModel;
import it.swiftelink.com.factory.model.order.IsHKResModel;
import it.swiftelink.com.factory.model.order.LogisticsResModel;
import it.swiftelink.com.factory.model.order.PackageInfoResModel;
import it.swiftelink.com.factory.model.order.PackageOrderListResModel;
import it.swiftelink.com.factory.model.order.ReceiveCardResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;
import it.swiftelink.com.factory.model.order.WeixinPayConfigResModel;
import it.swiftelink.com.factory.model.order.ZhifubaoPayConfigResModel;
import it.swiftelink.com.factory.model.recipe.OrderInfoFromOrderDetailResModel;
import it.swiftelink.com.factory.model.recipe.OrderInfoFromOrderIdResModel;
import it.swiftelink.com.factory.model.recipe.RecipeInfoResModel;
import it.swiftelink.com.factory.model.recipe.RecipeListResModel;
import it.swiftelink.com.factory.model.recipe.RecipeOrderListResModel;
import it.swiftelink.com.factory.model.user.EditUserInfoModel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;
import it.swiftelink.com.factory.model.videoChat.TrtcConfigResModel;
import it.swiftelink.com.factory.model.videoChat.VideoChatConfigResModel;
import it.swiftelink.com.factory.model.wechat.WXAccessTokenEntity;
import it.swiftelink.com.factory.model.wechat.WXUserInfo;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 会员端api
 */
public interface ApiService {

//    String BaseUrl = "http://192.168.0.133:8080/";
//    String BaseUrl = "http://192.168.0.116:8080";
//    String BaseUrl = "http://192.168.0.152:8080";
//    String BaseUrl = "http://192.168.0.169:8080";


//        String BaseUrl = "https://vcare.umpmedical.com:8899/";
//    String BaseUrl = "https://vcare.umpmedical.com:8844/";
//    String BaseUrl = "https://vcare.umpmedical.com:8822";

    String BaseUrlTrtc = "https://www.umpmedical.com:8855/";
    String BaseUrlWeixin = "https://api.weixin.qq.com/";
    String BaseUploadUrl = "https://www.umpmedical.com:8877/";

    @Headers("url_name:trtc")
    @GET("rtc/config-data")
    Observable<TrtcConfigResModel> getTrtcConfig(@Query("uuid") String uuid, @Query("userId") String userId);

    @Headers("url_name:weixin")
    @GET("sns/oauth2/access_token")
    Observable<WXAccessTokenEntity> accreditWeixin(@Query("appid") String appid, @Query("secret") String secret,
                                                   @Query("code") String code, @Query("grant_type") String grant_type);

    @Headers("url_name:weixin")
    @GET("sns/userinfo")
    Observable<WXUserInfo> getWeixinUserInfo(@Query("access_token") String access_token, @Query("openid") String openid);

    //文件上传
    @Headers("url_name:upload")
    @Multipart
    @POST("file/upload")
    Observable<UploadResModel> uploadImage(@Part MultipartBody.Part part);

    @Headers("url_name:upload")
    @Multipart
    @POST("file/upload")
    Observable<UploadResModel> uploadImageList(@Part List<MultipartBody.Part> partList);

    @POST("api/login/patientByWechat")
    Observable<LoginResModel> weixinLogin(@Body LoginModel model);

    @GET("api/member/vip")
    Observable<BooleanResModel> getIsMember();

    @POST("api/login/patientbytel")
    Observable<LoginResModel> login(@Body LoginModel model);

    @POST("/api/login/logintel")
    Observable<LoginResModel> loginByPhone(@Body LoginByPhoneModel model);

    @POST("api/diagnosis/save")
    Observable<EditInquiryResModel> EditInquiry(@Body EditInquiryModel model);

    @GET("api/diagnosis/symptomDescription/{currPage}/{pageSize}")
    Observable<EditInquiryTagResModel> getEditInquiryTag(@Path("currPage") int currPage, @Path("pageSize") int pageSize,@Query("language") String language);

    @GET("api/diagnosis/draft")
    Observable<ToInquiryResModel> ToInquiry();

    @GET("api/diagnosis/list/{pageSize}/{currPage}")
    Observable<InquiryListResModel> getInquiryList(@Path("pageSize") int pageSize, @Path("currPage") int currPage,
                                                   @Query("status") String status, @Query("appType") String appType);


    @GET("api/diagnosis/list/{pageSize}/{currPage}")
    Observable<InquiryListResModel> getInquiryListByTime(@Path("pageSize") int pageSize, @Path("currPage") int currPage,
                                                   @Query("status") String status, @Query("appType") String appType,@Query("yearTime") String year,@Query("monthTime") String month);

    @GET("api/diagnosis/report/list/{pageSize}/{currPage}")
    Observable<InquiryReportListResModel> getInquiryReportList(@Path("pageSize") int pageSize, @Path("currPage") int currPage);

    @GET("api/evaluation/find/list/{currPage}/{pageSize}")
    Observable<EvaluateListResModel> getEvaluateList(@Path("currPage") int currPage, @Path("pageSize") int pageSize,
                                                     @Query("doctorStatus") String doctorStatus, @Query("patientStatus") String patientStatus,
                                                     @Query("doctorId") String doctorId);

    @POST("api/address/save")
    Observable<BaseResModel> saveAddress(@Body SaveAddressModel model);

    @DELETE("api/address/del")
    Observable<BaseResModel> deleteAddress(@Query("id") String id);

    @GET("api/address/info")
    Observable<AddressDetailResModel> getAddressDetail(@Query("id") String id);

    @GET("api/address/list/{currPage}/{pageSize}")
    Observable<AddressListResModel> getAddressList(@Path("currPage") int currPage, @Path("pageSize") int pageSize);

    @PUT("api/address/default")
    Observable<BaseResModel> setAddressDefault(@Query("id") String id);

    @GET("api/coupon/list/{currPage}/{pageSize}")
    Observable<CouponListResModel> getCouponsList(@Path("currPage") int currPage, @Path("pageSize") int pageSize,
                                                  @Query("status") String status, @Query("orderAmount") String orderAmount,
                                                  @Query("language") String language);

    @GET("api/home/banner")
    Observable<BinnerListResModel> getBinnerList(@Query("language") String language, @Query("type") String tye);

    @GET("api/package/list/{currPage}/{pageSize}")
    Observable<SetMealListResModel> getSetMealList(@Path("currPage") int currPage, @Path("pageSize") int pageSize,
                                                   @Query("language") String language);


    @GET("/api/package/myPackageCard")
    Observable<CardListResModel> getMyPackageCard(@Query("currentPage") int currentPage,
                                                @Query("pageSize") int pageSize, @Query("validFlag") String validFlag);

    @POST("api/app/smsCode")
    Observable<BaseResModel> getPhoneCode(@Body GetPhoneCodeModel model);

    @POST("api/register/patientbytel")
    Observable<RegisterResModel> register(@Body RegisterModel model);

    @GET("api/prescription/list/{pageSize}/{currPage}")
    Observable<RecipeListResModel> getRecipeList(@Path("currPage") int currPage, @Path("pageSize") int pageSize);

    @GET("api/diagnosis/report/info")
    Observable<InquiryReportResModel> getInquiryReportInfo(@Query("id") String id);

    @GET("api/prescription/info")
    Observable<RecipeInfoResModel> getRecipeInfo(@Query("diagnosisId") String id);

    @GET("api/diagnosis/info")
    Observable<InquiryDetailResModel> getInquiryInfo(@Query("id") String id);

    @GET("api/favorite/list/{currPage}/{pageSize}")
    Observable<CollectDoctorResModel> getCollectDoctorList(@Path("currPage") int currPage, @Path("pageSize") int pageSize);

    @GET("api/message/list/{currPage}/{pageSize}")
    Observable<MessageListResModel> getMessageList(@Path("currPage") int currPage, @Path("pageSize") int pageSize);

    @GET("api/message/remind")
    Observable<MessageRemindCountResModel> getRemindMessageCount();

    @PUT("api/message/read")
    Observable<BaseResModel> readMessage(@Query("id") String id);

    @GET("api/member/info")
    Observable<UserInfoResModel> getUserInfo();

    @PUT("api/member/edit")
    Observable<BaseResModel> editUserInfo(@Query("id") String id, @Body EditUserInfoModel model);

    @POST("api/evaluation/update")
    Observable<BaseResModel> toEvaluate(@Body EvaluateModel model);

    @POST("api/evaluation/save")
    Observable<BaseResModel> saveEvaluate(@Body SaveEvaluateModel model);


    @GET("api/order/prescription/info")
    Observable<OrderInfoFromOrderDetailResModel> getOrderInfoFromDetail(@Query("prescriptionId") String prescriptionId,
                                                                        @Query("prescriptionDrugIds") String prescriptionDrugIds);


    @GET("api/order/prescription/detail")
    Observable<OrderInfoFromOrderIdResModel> getOrderInfoFromOrderId(@Query("orderId") String orderId);

    @POST("api/evaluation/list")
    Observable<EvaluateLabelResModel> getEvaluateLabel(@Body EvaluateLabelModel model);


    @GET("api/order/prescription/list/{pageSize}/{currPage}")
    Observable<RecipeOrderListResModel> getRecipeOrderList(@Path("currPage") int currPage, @Path("pageSize") int pageSize,
                                                           @Query("status") String status);

    @GET("api/medicalhistory/list")
    Observable<HealthHistoryListResModel> getHealthHistoryList(@Query("type") String type);


    @GET("api/patientFile/list")
    Observable<HealthReportListResModel> getHealthReportList(@Query("type") String type, @Query("id") String id);

    @GET("api/order/packagelist/list/{pageSize}/{currPage}")
    Observable<PackageOrderListResModel> getPackegeOrderList(@Path("currPage") int currPage, @Path("pageSize") int pageSize,
                                                             @Query("status") String status,
                                                             @Query("type") String type);

    @POST("api/patientFile/save")
    Observable<BaseResModel> editHealthReport(@Body EditHealthReportModel model);


    @GET("/api/package/packageCardDetail/{id}")
    Observable<PackageInfoResModel> getPackageInfoDetail(@Path("id") String id,@Query("language") String language);


    @GET("api/patientVitalSings/list/{currPage}/{pageSize}")
    Observable<HistoryHealthDataListResModel> getHealthHistoryDataList(@Path("currPage") int currPage, @Path("pageSize") int pageSize);


    @POST("api/patientVitalSings/save")
    Observable<BaseResModel> editVitalSigns(@Body EditHealthDataModel model);

    @GET("api/diagnosis/poll")
    Observable<VideoChatConfigResModel> getVideoChatConfig();

    @POST("api/diagnosis/cancle")
    Observable<BaseResModel> cancelInquiry(@Query("diagnosisId") String diagnosisId, @Query("status") String status);

    @POST("api/diagnosis/end")
    Observable<BaseResModel> endVideoInquiry(@Query("diagnosisId") String diagnosisId, @Query("isPassive") boolean isPassive);

    @GET("api/favorite/info")
    Observable<DoctorInfoResModel> getDoctorInfo(@Query("doctorId") String doctorId);

    @POST("api/favorite/add")
    Observable<BaseResModel> collectDoctor(@Query("doctorId") String doctorId);

    @POST("api/favorite/cancel")
    Observable<BaseResModel> cancelCollectDoctor(@Query("doctorId") String doctorId);

    @PUT("api/login/updatePwdByDoctorAndPatient")
    Observable<ChangePsdResModel> changePsd(@Query("type") String type, @Query("oldpwd") String oldpwd, @Query("newpwd") String newpwd);

    @POST("api/login/updatePwd")
    Observable<ChangePsdResModel> forgetPsd(@Body RegisterModel model);

    @GET("api/purse/patient/balance/list/{currPage}/{pageSize}")
    Observable<BalanceListResModel> getBalanceList(@Path("currPage") int currPage, @Path("pageSize") int pageSize);

    @GET("api/help/list")
    Observable<HelpListResModel> getHelpList(@Query("language") String language);

    @POST("api/order/wxpay/payment")
    Observable<WeixinPayConfigResModel> payWeixin(@Query("orderId") String orderId);

    @POST("api/order/alipay/payment")
    Observable<ZhifubaoPayConfigResModel> payZhifubao(@Query("orderId") String orderId);


    @GET("api/diagonsisGuid/list")
    Observable<ForeseeInquiryImageResModel> getForeseeInquiryImage(@Query("language") String language);

    @GET("api/sectionoffice/list/{language}")
    Observable<HealthDepartmentsResModel> getDepartmentsList(@Path("language") String language);

    @POST("api/order/packagelist/confirm")
    Observable<PackageOrderConfirmInfoResModel> getPackConfirmOrderInfo(@Body PackConfirmModel model);

    @POST("api/order/prescription/receiving")
    Observable<BaseResModel> confirmOrderRecived(@Query("orderId") String orderId);

    @POST("api/order/prescription/refund")
    Observable<BaseResModel> refundOrder(@Query("orderId") String orderId);

    @GET("api/order/prescription/logistics")
    Observable<LogisticsResModel> getLogistics(@Query("orderId") String orderId);

    @POST("api/order/packagelist/topay")
    Observable<BaseResModel> payByAmount(@Query("id") String orderId, @Query("password") String password);


    @GET("/api/member/payment/checkpassword")
    Observable<CheckPayPasswordResmodel> checkPassword();

    @POST("api/member/payment/setpassword")
    Observable<BaseResModel> setPayPsd(@Query("password") String password, @Query("mobile") String mobile,
                                       @Query("smsCode") String smsCode);

    @GET("api/diagnosis/report/recordList/{pageSize}/{currPage}")
    Observable<RecordListResmodel> getRecordList(@Path("pageSize") int pageSize, @Path("currPage") int currPage);

    @POST("api/order/prescription/confirm")
    Observable<CofirmRecipeOrderResModel> confirmRecipeOrder(@Body CofirmRecipeOrderModel model);

    @GET("api/Agreement/Agreement")
    Observable<AgrreementResModel> getAgrreement(@Query("language") String language, @Query("type") String type);

    //版本更新
    @GET("api/app/log/check/version")
    Observable<VersionResModel> checkVersion(@Query("device") String device, @Query("type") String type);

    @GET("api/share/findShareConfiguration")
    Observable<ShareH5MsgResModel> getShareH5(@Query("type") String device, @Query("language") String language);

    @GET("api/home/news")
    Observable<MarkWordsResModel> getMarkWords();

    @POST("api/order/packagelist/receivePackageCard")
    Observable<ReceiveCardResModel> postFreePick(@Body FreePickPackageModel freePickPackageModel);

    @GET("api/diagnosis/inquiryValidPackageCard")
    Observable<InquiryValidCardResModel> getInquiryValidCard();

    @GET("api/favorite/diagnosis/list")
    Observable<DiagnosisDoctorResModel> getDiagnosisDoctorList(@Query("pageSize") int pageSize, @Query("currentPage") int currPage);

    @POST("api/diagnosis/check/ishk")
    Observable<IsHKResModel> getIshk(@Query("region") String region);
}
