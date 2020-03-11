package it.swiftelink.com.factory.presenter.inquiry;


import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.inquiry.InquiryReportListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;


public class GetInquiryReportListPresenter extends BasePresenter<GetInquiryListReportContract.View>
        implements GetInquiryListReportContract.Presenter {


    public GetInquiryReportListPresenter(GetInquiryListReportContract.View view) {
        super(view);
    }

    @Override
    public void getInquiryReportList(int pageSize, int currPage) {
        start();

        Observable<InquiryReportListResModel> observable = NetWork.getRequest().getInquiryReportList(pageSize, currPage);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<InquiryReportListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_INQUIRY_REPORT_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(InquiryReportListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getInquiryReportListSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_INQUIRY_REPORT_LIST,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }
}
