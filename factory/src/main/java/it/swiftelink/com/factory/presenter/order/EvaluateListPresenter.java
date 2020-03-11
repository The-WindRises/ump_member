package it.swiftelink.com.factory.presenter.order;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BasePresenter;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.EvaluateListResModel;
import it.swiftelink.com.factory.model.order.EvaluateModel;
import it.swiftelink.com.factory.net.NetWork;
import rx.Observable;

public class EvaluateListPresenter extends BasePresenter<EvaluateListContract.View>
        implements EvaluateListContract.Presenter {
    public EvaluateListPresenter(EvaluateListContract.View view) {
        super(view);
    }

    @Override
    public void getEvaluateList(int currPage ,int pageSize ,String patientStatus ,String doctorId ) {

        if(currPage==0){
            start();
        }

        Observable<EvaluateListResModel> observable = NetWork.getRequest().getEvaluateList(currPage,pageSize,"",patientStatus,doctorId);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<EvaluateListResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_EVALUATION_LIST,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(EvaluateListResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getEvaluateListSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_EVALUATION_LIST,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void toEvaluate(EvaluateModel model) {

        start();

        Observable<BaseResModel> observable = NetWork.getRequest().toEvaluate(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<BaseResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.TO_EVALUATE,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(BaseResModel resModel) {

                if(resModel.isSuccess()){
                    mView.toEvaluateSuccess();
                }else {
                    mView.showError(Common.UrlApi.TO_EVALUATE,resModel.getCode(),resModel.getMessage());
                }
            }
        });
    }

    @Override
    public void getEvaluateLabel(final EvaluateLabelModel model) {


        Observable<EvaluateLabelResModel> observable = NetWork.getRequest().getEvaluateLabel(model);

        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<EvaluateLabelResModel>() {
            @Override
            public void onError(ApiException e) {
                mView.showError(Common.UrlApi.GET_EVALUATION_LABEL,e.getCode(),e.getMessage());
            }

            @Override
            public void onSuccess(EvaluateLabelResModel resModel) {

                if(resModel.isSuccess()){
                    mView.getEvaluateLabelSuccess(resModel);
                }else{
                    mView.showError(Common.UrlApi.GET_EVALUATION_LABEL,resModel.getCode(),resModel.getMessage());
                }

            }
        });
    }
}
