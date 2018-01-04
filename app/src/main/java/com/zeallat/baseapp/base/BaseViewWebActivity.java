package com.zeallat.baseapp.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.google.common.base.Splitter;
import com.zeallat.baseapp.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static android.text.TextUtils.isEmpty;

/**
 * Created by HoJunLee on 2017-10-29.
 */

public abstract class BaseViewWebActivity<T extends BasePresenter> extends BaseViewActivity<T> implements BaseView<T> {

    private static final String EXTRA_URL = "a1e63c94-d332-4b99-9606-1efec800a3ec";

    @BindView(R.id.webView) WebView webView;
    @BindView(R.id.webViewContainer) FrameLayout mWebViewContainer;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessages = null;
    private static final int REQUEST_CODE_CHOOSE_FILE = 58868;

    private String mUrl;

    @NonNull
    protected abstract String getUrl();

    protected WebView getWebView() {
        return webView;
    }

    /**
     * @param view
     * @param url
     * @return True when consume url load, otherwise false.
     */
    protected abstract boolean shouldOverrideUrlLoading(WebView view, String url, Uri uri, String scheme, String host, Map<String, String> parameters);

    protected abstract void onPageFinished(WebView view, String url);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(EXTRA_URL)) {
                mUrl = getIntent().getExtras().getString(EXTRA_URL);
            }
        }

        initWebView(webView);
        webView.loadUrl(isEmpty(mUrl) ? getUrl() : mUrl);
    }

    private void initWebView(WebView view) {
        view.getSettings().setDatabaseEnabled(true);
        view.getSettings().setDomStorageEnabled(true);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setSupportZoom(false);
        view.getSettings().setUseWideViewPort(true);
        view.getSettings().setLoadWithOverviewMode(true);
        view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        view.getSettings().setSupportMultipleWindows(true);
        view.setWebViewClient(new WebClient());
        view.setWebChromeClient(new ChromeClient());
    }

    private class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("WebClient", url);
            Uri uri = Uri.parse(url);

            if (URLUtil.isNetworkUrl(url)) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            if (BaseViewWebActivity.this.shouldOverrideUrlLoading(view, url, uri, uri.getScheme(), uri.getHost(), getParameters(url)))
                return true;

            // Otherwise allow the OS to handle it
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            BaseViewWebActivity.this.onPageFinished(view, url);
        }
    }

    private Map<String, String> getParameters(String uri) {
        Map<String, String> map = new HashMap<>();
        if (!uri.contains("?")) return map;
        String query = uri.split("\\?")[1];
        map = Splitter.on('&').trimResults().withKeyValueSeparator("=").split(query);
        return map;
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "File Chooser"), REQUEST_CODE_CHOOSE_FILE);
    }

    private class ChromeClient extends WebChromeClient {

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView newWebView = new WebView(view.getContext());
            initWebView(newWebView);
            mWebViewContainer.addView(newWebView);    // 화면에 보여질 수 있도록 add view

            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(newWebView);
            resultMsg.sendToTarget();
            return true;
        }

        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
            mWebViewContainer.removeView(window);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
            new AlertDialog.Builder(BaseViewWebActivity.this).setMessage(message)
                    .setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    }).setCancelable(false).create().show();

            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final android.webkit.JsResult result) {
            new AlertDialog.Builder(BaseViewWebActivity.this).setMessage(message)
                    .setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    }).setNegativeButton(android.R.string.no, new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    result.cancel();
                }
            }).setCancelable(false).create().show();
            return true;
        }

        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            uploadMessage = uploadMsg;
            showFileChooser();

        }

        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            uploadMessage = uploadMsg;
            showFileChooser();
        }

        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            uploadMessage = uploadMsg;
            showFileChooser();

        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            uploadMessages = filePathCallback;
            showFileChooser();
            return true;
        }
    }

    //
    public void sendData(int resultCode, Intent data) {
        Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
        if (uploadMessage != null) {
            uploadMessage.onReceiveValue(result);
            uploadMessage = null;
        }

        if (uploadMessages != null) {
            if (data != null) {
                Uri[] results = null;
                String dataString = data.getDataString();

                if (dataString != null) {
                    results = new Uri[]{Uri.parse(dataString)};
                }

                uploadMessages.onReceiveValue(results);

            } else {
                uploadMessages.onReceiveValue(null);
            }

            uploadMessages = null;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CHOOSE_FILE) {
            sendData(resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebViewContainer.getChildCount() <= 1) {
            /*
            webView가 1개 이하일 경우.
             */
            if (webView.canGoBack()) {
                webView.goBack();
                return;
            }
            super.onBackPressed();
        } else {
            /*
            webView가 2개 이상일 경우, 가장 마지막의 webView핸들링.
             */
            WebView webViewChild = (WebView) mWebViewContainer.getChildAt(mWebViewContainer.getChildCount() - 1);
            if (webViewChild.canGoBack()) {
                webViewChild.goBack();
            } else {
                mWebViewContainer.removeView(webViewChild);
            }
        }
    }
}
