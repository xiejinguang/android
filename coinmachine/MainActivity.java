package cn.dawnrise.android.coinmachine;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    private static final String logTag = "Coin";
     static String[] mobiletype = {"NOAIND01","D5288CT","CT06","Sun005","20170605Q","KINGSUN-F9","KINGSUN-EF36","MP1709","MP1701","1713-A01","1707-A01","1801-A01","koobeeS12","koobeeF1","ASUS_X018DC","ASUS_X00KD","HL2001","TCL Y660","GIONEE F6","GIONEE S11","GIONEE GN5006","GIONEE F109N","GIONEE F109","GIONEE S10C","GIONEE F100SD","GIONEE M7","GIONEE GN5007","GIONEE S10B","GIONEE S10","HLTE212T","HLTE500T","HLTE300T","Hisense F23","HLTE200T","Hisense E77","MEC7S","MDE6S","MDE6","SM-W2018","COL-AL10","CLT-AL01","CLT-AL00","NEO-AL00","EML-AL00","HWI-AL00","BLA-AL00","ALP-AL00","Y71A","Y85A","X21 UD A","X21A","X20 Plus UD","Y75A","Y79A","X20A","X20 Plus A","PADM00","PACM00","PAAM00","OPPOA83","OPPOA79K","R11s Plus","R11s"};
     static String[] androidVersion={"Linux; Android 5.0","Linux; Android 5.1","Linux; Android 6.0","Linux; Android 6.1","Linux; Android 7.0","Linux; Android 7.1.1","Linux; Android 8.0"};
    Thread at;
    private TextView mTextMessage;
    private String mUrl = "http://1749257_3881505.btvxu.cn./xvGs58y2m/W7VpBkN/QJyNHigvDL?h7WR=fDrip4Nt&73YVfDMD8=F7BGye&gkTki8w=T2fYEqY";
    private String[] mUrls= {
            "http://1749257_3873020.lcq9.cn./SANfQaG/pd2T/X3m7nV/a9nbE/YGaK.jsp?g8eb3vAHX=eiSsxaaJ&7rhTB=KKjn3yi&rjgK5KYki=8gYt&9erMibBfT=KG6iMe&GF9hFWbR=M85FwDBBFh",
            "http://1750528_3877969.0vswi.cn./NJFd9Ef7T/fSbdW3dF4/mkG6A7Nqp/x79yeX/Gk92S.htm?rM8epFiR=Y7tYWYpp&j4NPi=gLep&8LdJ3yL=F8qkHHT&eLDmtdKK=FPSL&73xDSx=62kkj&from=singlemessage",
            "http://1750528_3874854.78inst.cn./3pskF/5n74/24TmytbJK.jsp?qySkiErAj4=tgJjHqps&QjTk45g4rY=qqYB&wV6Hi6=vdhy&from=singlemessage",
            "http://1750528_3875167.gxxie.com.cn./SGyhBv/9yJGSj4E7/wqPvp.htm?qW3bt=XHev&4yLehN3=r5mx4s&9bFRvea=qW3a6KmLb&from=singlemessage",
            "http://1750528_3873685.6wkxt.cn./vVKS?3W7RS9ain=tswKG&from=singlemessage",
    };
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



       final WebView webView= findViewById(R.id.webView);
       //final WebView webView = new WebView(this);
        initWebView(webView);

        webView.loadUrl("http://m.cqvfr34.cn./3872250_1749257/pelroo/3321667/kbuqivaigf/791057.htm?hfofnyh=qspwakxzhi&feurint=fwbs&ysrxpuhhr=01674&tkfu=ggmnnx#");
        Log.i(logTag,"访问："+mUrl);

        Switch sw = findViewById(R.id.airplaneswitch);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//设置飞行模式开关监听器
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MobileData.setAirplaneModeOn(b);

            }
        });

        Button button = findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {//开始线程
            @Override
            public void onClick(View view) {
                if(at==null){
                    at = new AccessThread(webView,1000,MainActivity.this,mUrls);
                }
                at.start();

            }
        });

        button = findViewById(R.id.stop_button);

        button.setOnClickListener(new View.OnClickListener() {//关闭线程
            @Override
            public void onClick(View view) {
                if(at!=null && !at.isInterrupted()){
                    at.interrupt();
                }
            }
        });





    }
//初始化配置WebView
    public  void initWebView(WebView displayWebview) {

        displayWebview.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        displayWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        displayWebview.getSettings().setSupportMultipleWindows(true);

        displayWebview.getSettings().setSupportZoom(false);//是否可以缩放，默认true
        displayWebview.getSettings().setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
        displayWebview.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        displayWebview.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        displayWebview.getSettings().setAppCacheEnabled(true);//是否使用缓存
        displayWebview.getSettings().setDomStorageEnabled(true);//DOM Storage
        displayWebview.getSettings().setAllowFileAccess(true);
        displayWebview.getSettings().setLoadsImagesAutomatically(true);
        displayWebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//加载https和http混合模式





        displayWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        displayWebview.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);

//模拟微信浏览器

        displayWebview.getSettings().setUserAgentString("User-Agent:Mozilla/5.0 (Linux; Android 5.0; zh-cn; APL-100 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30 MicroMessenger/6.6.7");//设置用户代理，一般不用



      //  displayWebview.setWebChromeClient(new WebChromeClient());
     //   displayWebview.setWebViewClient(new WebViewClient());//不设置 下一步的网页就会在原生浏览器中打开

        displayWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
// TODO Auto-generated method stub
//返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                Log.i(logTag,"shouldOverrideUrlLoading"+url);
                view.loadUrl(url);
                return false;
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 不要使用super，否则有些手机访问不了，因为包含了一条 handler.cancel()
                super.onReceivedSslError(view, handler, error);
                // 接受所有网站的证书，忽略SSL错误，执行访问网页
                handler.proceed();
            }
        });
    }


    //更改IP访问链接的进程
    protected static class AccessThread extends Thread{
//        String url;
        WebView webView;
        int times;
        Random rd,rd2,rd3 ;
        Handler h;
        Context mContext;
        String[] mUrls;
        int c1 =10;
        int c2 =5;

/*        public AccessThread(String url, final WebView webView, int times, Context context) {
            this.url = url;
            this.webView = webView;
            this.times = times;
            this.rd = new Random(11);
            this.h = new android.os.Handler();
            this.mContext = context;
        }*/
        public AccessThread( WebView webView, int times, Context context,String... urls) {
            this.mUrls= urls;
            this.webView = webView;
            this.times = times;
            this.rd = new Random(56558998);
            this.h = new android.os.Handler();
            this.mContext = context;
            this.rd2 = new Random(26461576);
            this.rd3 = new Random(16198456);



        }

        @Override
        public void run() {
            super.run();
            for (int i = 0; i <times ; i++) {

                MobileData.setAirplaneModeOn(true);
                Log.i(logTag,"开启飞行模式");
                try {
                    sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                MobileData.setAirplaneModeOn(false);
                Log.i(logTag,"关闭飞行模式");

                try {
                    sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(logTag,"ip:"+MobileData.getIPAddress(mContext));

                //在UI线程调用WebView
               h.post(new Runnable() {
                   @Override
                   public void run() {
                       webView.clearHistory();
                       webView.clearCache(false);

                       String av  = androidVersion[rd.nextInt(androidVersion.length-1)];
                       String mt = mobiletype[rd.nextInt(mobiletype.length)-1];


                       //苹果：User-Agent:Mozilla/5.0 (iPhone; CPU iPhone OS 10_0_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Mobile/14A551 MicroMessenger/6.7.1 NetType/WIFI Language/zh_CN

                       //安卓
                       // User-Agent:Mozilla/5.0 (Linux; Android 7.1.1; MEIZU E3 Build/NGI77B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044113 Mobile Safari/537.36 MicroMessenger/6.6.7.1321(0x26060736) NetType/WIFI Language/zh_CN

                       String p= av+"; "+mt+"B"+rd.nextInt(200);

                       String mmv="MicroMessenger/6.6.7.1321(0x"+rd.nextInt(30000000)+")";

                       String android_ua="User-Agent:Mozilla/5.0 ("+p+") AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044113 Mobile Safari/537.36 "+mmv+" NetType/4G Language/zh_CN";
                       String ios_ua ="User-Agent:Mozilla/5.0 (iPhone; CPU iPhone OS 10_0_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Mobile/14A551 MicroMessenger/6.7.1 NetType/4 Language/zh_CN";

                       String f_ua = rd.nextBoolean()?android_ua:ios_ua;
                       if(rd.nextInt(10)>8){
                           webView.getSettings().setUserAgentString(ios_ua);
                       }else{
                           webView.getSettings().setUserAgentString(android_ua);
                       }

                       int t= rd2.nextInt(5);

                       for (int j = 0; j <t ; j++) {
                           String url = mUrls[rd.nextInt(mUrls.length-1)];
                           webView.loadUrl(url);
                           try {
                               sleep(rd3.nextInt(300*000)+20*000);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                           Log.i(logTag,"访问："+url);
                       }

                   }
               }) ;

                try {
                    switch(rd2.nextInt(30)) {

                        case 0:
                            sleep(rd.nextInt(600) * 1000 + 1200 * 1000);//中间隔20至30分钟

                        case 1:
                            sleep(rd.nextInt(3600) * 1000 + 1800 * 1000);//大间隔30分至1.5小时

                        default:
                            sleep(rd.nextInt(600) * 1000 + 60 * 1000);//小间隔

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    static class TaskProgress extends AsyncTask<String,String,String>{
        String url;
        WebView webView;
        int times;

        public TaskProgress(){

        }

        public TaskProgress(String url, WebView webView, int times) {
            this.url = url;
            this.webView = webView;
            this.times = times;
        }

        @Override
        protected String doInBackground(String... strings) {

            return null;

        }
    }

}
