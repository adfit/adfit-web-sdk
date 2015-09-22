<link rel="stylesheet" type="text/css" href="http://cdnjs.cloudflare.com/ajax/libs/prettify/r224/prettify.min.css">
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/prettify/r224/prettify.min.js"></script>

## Android 하이브리드 앱에서 권장하는 WebView 설정

이 가이드는 Mobile Web 모바일 광고를 Android 하이브리드 앱에 노출하기 위한 WebView 설정 방법을 설명합니다.
<br />사이트/앱 운영정책에 어긋나는 경우 적립금 지급이 거절 될 수 있으니 유의하시기 바랍니다.

* 문의 고객센터 [https://cs.daum.net/question/256.html](https://cs.daum.net/question/256.html)
* 사이트/앱 운영 정책 [http://adfit.biz.daum.net/html/use.html](http://adfit.biz.daum.net/html/use.html)

원칙적으로 하이브리드 앱에 대한 기술적인 지원은 하고 있지 않으나, 일부 프리미엄 매체를 위한 가이드 라인 정도로 문서를 활용할 수 있습니다.

### 1. WebView 생성
XML에 WebView Id가 R.id.webview 로 되어 있다고 가정한다.

<pre class="prettyprint lang-java">
    WebView myWebView = (WebView) findViewById(R.id.webview);
</pre>

### 2. JavaScript 활성화
하이브리드 앱이라면 기본적으로 JavaScript 코드를 실행할 수 있도록 아래와 같은 코드가 반드시 있어야 한다.

<pre class="prettyprint lang-java">
    WebSettings settings = myWebView.getSettings();
    settings.setJavaScriptEnabled(true);
</pre>

### 3. HTML5 LocalStorage 활성화

<pre class="prettyprint lang-java">
    // SDK 2.1 부터 HTML5 LocalStorage를 사용할 수 있다.
    if ( Build.VERSION.SDK_INT >= 7 ) {
        settings.setDomStorageEnabled(true);
    }
</pre>

### 4. User-Agent
기본 User-Agent는 내장 브라우저와 동일하기 때문에, 웹앱과 브라우저를 구분하기 힘들다. 따라서 명시적으로 구분을 해주는 것이 좋다.

User-Agent에 App 정보가 있으면 서버측에서 앱 별로 요청 내역을 확인하기가 쉬워진다.

아래 코드를 넣으면 기본 User-Agent 문자열에 앱 이름과 버전을 붙여서 요청하게 된다.

<pre class="prettyprint lang-java">
	Context context = myWebView.getContext();
	PackageManager packageManager = context.getPackageManager();
	String appName = "";
	String appVersion = "";
	String userAgent = settings.getUserAgentString();

	try {
		// App 이름 추출
	    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
	    appName = packageManager.getApplicationLabel(applicationInfo).toString();

	    // App 버전 추출
	    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
	    appVersion = String.format("%s", ""+packageInfo.versionName);


	    // User-Agent에 App 이름과 버전을 붙여서 보내줌
		userAgent = String.format("%s %s %s", userAgent, appName, appVersion);


		// 변경된 User-Agent 반영
		settings.setUserAgentString(userAgent);

	} catch ( NameNotFoundException e ) {
	    // e.printStackTrace();
	}
</pre>

### 5. WebViewClient 사용
WebViewClient를 사용하면 페이지 내에서 링크를 클릭했을때 이를 제어할 수 있다.

기본적으로 Ad@m 모바일 웹 SDK는 기본 내장 브라우저 또는 크롬이나 오페라 등 브라우저 앱을 통해 노출되는 것을 가정하고 제작되었다. 

따라서, 단순 웹 컨텐츠를 노출시키는 하이브리드 앱에서는 마켓(market://)을 비롯해 앱으로 연결되는 앱 링크가 동작하지 않을 수 있기 때문에 브라우저에서와 동일하게 동작하도록 설정해야 할 필요가 있다.

<pre class="prettyprint lang-java">
    myWebView.setWebViewClient(new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if ( view == null || url == null) {
                // 처리하지 못함
                return false;
            }

            if ( url.contains("play.google.com") ) {
              // play.google.com 도메인이면서 App 링크인 경우에는 market:// 로 변경
              String[] params = url.split("details");
              if ( params.length > 1 ) {
                  url = "market://details" + params[1];
                  view.getContext().startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url) ));
                  return true;
              }
            }

            if ( url.startsWith("http:") || url.startsWith("https:") ) {
                // HTTP/HTTPS 요청은 내부에서 처리한다.
                view.loadUrl(url);
            } else {
                Intent intent;

                try {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                } catch (URISyntaxException e) {
                    // 처리하지 못함
                    return false;
                }

                try {
                    view.getContext().startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Intent Scheme인 경우, 앱이 설치되어 있지 않으면 Market으로 연결
                    if ( url.startsWith("intent:") && intent.getPackage() != null) {
                        url = "market://details?id=" + intent.getPackage();
                        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url) ));
                        return true;
                    } else {
                        // 처리하지 못함
                        return false;
                    }
                }
            }
            return true;
        }
    });</pre>

<script type="text/javascript">prettyPrint();</script>

이 문서는 KaKao 신디케이션 제휴 당사자에 한해 제공되는 자료로 가이드 라인을 포함한 모든 자료의 지적재산권은 주식회사 카카오가 보유합니다.

Copyright © Kakao Corp. All Rights Reserved.