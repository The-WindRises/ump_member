package it.swiftelink.com.factory.presenter.inquiry;


import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.inquiry.InquiryListResModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;


public class GetInquiryListPresenter extends BasePresenter<GetInquiryListContract.View>
        implements GetInquiryListContract.Presenter {


    public GetInquiryListPresenter(GetInquiryListContract.View view) {
        super(view);
    }

    @Override
    public void getInquiryList(String status, int pageSize, int currPage) {

        if(currPage==1){
            start();
        }

        Observable<InquiryListResModel> observable = NetWork.getRequest().getInquiryList(pageSize, currPage, status, Common.CommonStr.TYPE1);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<InquiryListResModel>() {
            @Override
            public void onError(ApiException e) {

                mView.showError(Common.UrlApi.GET_INQUIRY_LIST, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(InquiryListResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getInquiryListSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_INQUIRY_LIST, resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getInquiryListByTime(String status, int pageSize, int currPage, String year, String month) {
        Observable<InquiryListResModel> observable = NetWork.getRequest().getInquiryListByTime(pageSize, currPage, status, Common.CommonStr.TYPE1,year,month);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<InquiryListResModel>() {
            @Override
            public void onError(ApiException e) {

                mView.showError(Common.UrlApi.GET_INQUIRY_LIST, e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(InquiryListResModel resModel) {

                if (resModel.isSuccess()) {
                    mView.getInquiryListSuccess(resModel);
                } else {
                    mView.showError(Common.UrlApi.GET_INQUIRY_LIST, resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getInquiryValidPackageCard() {
        Observable<InquiryValidCardResModel> observable=NetWork.getRequest().getInquiryValidCard();
        NetWork.getInstance().baseNetRequest(observable, new NetWork.NetCallback<InquiryValidCardResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_INQUIRYVALID_CARD, e.getCode(), e.getMessage());
            }

            @Override
            public void onSuccess(InquiryValidCardResModel inquiryValidCardResModel) {

                mView.getInquiryValidPackageCardSuccess(inquiryValidCardResModel);
            }
        });



    }


}
