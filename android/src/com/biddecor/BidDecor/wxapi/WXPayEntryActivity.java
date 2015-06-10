package com.biddecor.BidDecor.wxapi;

import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import android.app.Activity;
import android.util.Log;
import android.os.Bundle;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import android.content.Intent;
import com.biddecor.weixin.Constants;
import com.tencent.mm.sdk.modelpay.*;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiProperties;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    private final static String LCAT = "WeixinModule";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.appId);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.d(LCAT, "onReq");
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d(LCAT, "onResp " + Constants.appId);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.d(LCAT, "pay result: " + resp.errCode);
            TiApplication tiapp = TiApplication.getInstance();
            TiProperties props = tiapp.getAppProperties();
            Log.d(LCAT, "appkey: " + props.getString("appkey", "default"));
            props.setInt("wxpay_result", resp.errCode);
            this.finish();
        }
    }
}
