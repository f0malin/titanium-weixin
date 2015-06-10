# titanium-weixin
A Weixin payment module for Titanium. Now only support android. I will add iOS support soon.

## Usage

```shell
git clone https://github.com/formalin14/titanium-weixin.git
cd titanium-weixin
cd android
ant
unzip -o dist/com.biddecor.weixin-android-1.0.0.zip -d ~/Library/Application\ Support/Titanium/
```

Add following code when you app startup:
```javascript
var weixin = require('com.biddecor.weixin');
// appId, partnerId, partnerKey
weixin.initSetup("wx47a221a1768b1770", "1246048102", "PZNLcRRv2NNcy4Ifnjg7WLQT9sgnlvTB");
```

When you want to start a Weixin payment, call:
```javascript
Ti.UI.currentWindow.activity.onStart = function() {    // weak up hook
  console.log("result code: " + Ti.App.Properties.getInt("wxpay_result"));   // get result
  // do something according to the result
  Ti.UI.currentWindow.activity.onStart = null;   // remove hook
};
weixin.pay("wx20150610134733ed6bd417f90412261612");   // call pay with prepayId
```

Enjoy :) !
