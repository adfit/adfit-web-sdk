<link rel="stylesheet" type="text/css" href="http://cdnjs.cloudflare.com/ajax/libs/prettify/r224/prettify.min.css">
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/prettify/r224/prettify.min.js"></script>

### AdFit(Ad@m) Mobile Web SDK Guide
이 가이드는 Mobile Web에 모바일 광고를 노출하기 위한 광고 스크립트 설치 방법을 설명합니다.
<br />사이트/앱 운영정책에 어긋나는 경우 적립금 지급이 거절 될 수 있으니 유의하시기 바랍니다.

* 문의 고객센터 [https://cs.daum.net/question/256.html](https://cs.daum.net/question/256.html)
* 사이트/앱 운영 정책 [http://adfit.biz.daum.net/html/use.html](http://adfit.biz.daum.net/html/use.html)

#### 1. 환경 설정
##### 1.1. 띠배너100 환경 설정
광고를 삽입할 페이지에 다음과 같은 광고 설정 스크립트를 넣습니다.

* AdFit(Ad@m) 띠배너100은 화면 중간 노출만 지원하고 있습니다. <br />
화면 상/하단 노출을 설정할 수 없으며, 화면 중간 노출을 위해 광고를 보여줄 div 영역을 필수로 지정해야 합니다.

<pre class="prettyprint lang-html"><code>&lt;html&gt;
&lt;body&gt;
&lt;!-- 광고를 보여줄 영역 --&gt;
&lt;div id="MobileadAreaDiv"&gt;&lt;/div&gt;

&lt;!-- 광고 스크립트 실행 코드 --&gt;
&lt;!-- 광고 스크립트 환경 설정 --&gt;
&lt;script type="text/javascript"&gt;
    new AdamLargeMobileBanner({ 
        // 광고 삽입 스크립트에 있는 광고단위ID를 이 곳에 넣으세요 
        client: '광고단위ID', 
        bannerDivId: 'MobileadAreaDiv' 
    }).init(); 
&lt;/script&gt;
&lt;/body&gt;
&lt;/html&gt;
</code></pre>
##### 1.2. 띠배너50 환경 설정

**1.1.1. 화면 중간에 노출시키고자 할 경우**

광고를 삽입할 페이지에 다음과 같은 광고 설정 스크립트를 넣습니다.

<pre class="prettyprint lang-html"><code>&lt;html&gt;
&lt;body&gt;
&lt;!-- 광고를 보여줄 영역 --&gt;
&lt;div id="MobileadAreaDiv"&gt;&lt;/div&gt;

&lt;!-- 광고 스크립트 환경 설정 --&gt;
&lt;script type="text/javascript"&gt;
	var daum_adam_vars = {
	  // 광고 삽입 스크립트에 있는 광고단위ID를 이 곳에 넣으세요
	  client : '광고단위ID',

	  // TOP (화면상단) | BOTTOM (화면 하단) | MIDDLE (화면 중간 삽입. bannerDivId 지정 필요)
	  position : 'MIDDLE',

	  // position : 'MIDDLE' 인 경우, 광고를 삽입할 DIV 태그의 ID 값.
	  bannerDivId : 'MobileadAreaDiv'
  };
&lt;/script&gt;
&lt;!-- 광고 스크립트 실행 코드 --&gt;
&lt;/body&gt;
&lt;/html&gt;</code></pre>

**1.1.2. 화면 상단 또는 하단에 노출시키고자 할 경우**

광고를 삽입할 페이지에 다음과 같은 광고 설정 스크립트를 넣습니다.

이때, 광고를 화면 상단(TOP)이나 화만 하단(BOTTOM)에 위치할 경우에는 1.1에서의 `<!-- 광고를 보여줄 영역-->`을 따로 설정하지 않아도 됩니다.

<pre class="prettyprint lang-html"><code>&lt;html&gt;
&lt;body&gt;
&lt;!-- 광고 스크립트 환경 설정 --&gt;
&lt;script type="text/javascript"&gt;
	var daum_adam_vars = {
	  // 광고 삽입 스크립트에 있는 광고단위ID를 이 곳에 넣으세요
	  client : '광고단위ID',

	  // TOP (화면상단) | BOTTOM (화면 하단) | MIDDLE (화면 중간 삽입. bannerDivId 지정 필요)
	  position : 'TOP'
  };
&lt;/script&gt;
&lt;!-- 광고 스크립트 실행 코드  --&gt;
&lt;/body&gt;
&lt;/html&gt;</code></pre>

##### 1.3. NO-AD 콜백 설정하기
광고 요청이 실패할 경우를 제어하기 위해 NO-AD 콜백을 설정할 수 있다.

예를 들어, 광고 요청이 실패한 경우에 광고 영역을 숨기는 등의 작업이 필요하다면 이 기능을 사용하면 되겠다.  

아래 `광고 스크립트 환경 설정` 부분을 보면 `callbackAdFailed` 라는 이름으로 광고 실패시 실행할 함수를 지정할 수 있다.

<pre class="prettyprint lang-html"><code>&lt;html&gt;
&lt;body&gt;
&lt;!-- 광고를 보여줄 영역 --&gt;
&lt;div id="MobileadAreaDiv"&gt;&lt;/div&gt;

&lt;!-- 광고 스크립트 환경 설정 --&gt;
&lt;script type="text/javascript"&gt;
	var daum_adam_vars = {
		// 광고 삽입 스크립트에 있는 광고단위ID를 이 곳에 넣으세요
		client : '광고단위ID',

		// TOP (화면상단) | BOTTOM (화면 하단) | MIDDLE (화면 중간 삽입. bannerDivId 지정 필요)
		position : 'MIDDLE',

		// position : 'MIDDLE' 인 경우, 광고를 삽입할 DIV 태그의 ID 값.
		bannerDivId : 'MobileadAreaDiv',

		// 광고 요청 실패시에 실행할 함수(NO-AD Callback)
		callbackAdFailed: adFailed
	};

	// 광고 요청 실패시에 실행할 함수
	function adFailed(client, message) {
		// 광고 요청이 실패할 경우,
		// 요청이 실패한 광고단위ID와 실패 Message를 내려준다.
	}
&lt;/script&gt;
&lt;!-- 광고 스크립트 실행 코드  --&gt;

&lt;/body&gt;
&lt;/html&gt;</code></pre>

**광고 스크립트 환경 설정시 주의할 사항**

1. 광고를 화면 상단(TOP)이나 화만 하단(BOTTOM)에 위치할 경우에는 `<!-- 광고를 보여줄 영역-->`을 따로 설정하지 않아도 됩니다.<br />'띠배너100' 광고는 화면의 중간(MIDDLE) 노출만 허용하기 때문에 `<!-- 광고를 보여줄 영역-->`을 꼭 설정하셔야 합니다.
2. AdFit Mobile Web 광고는 동일 지면내 동일한 사이즈의 광고는 한 개만 허용합니다.   

#### 2. 스크립트 설치

앞의 환경 설정이 완료되었을 경우에는 아래 스크립트를 추가로 넣어주어야 광고가 정상적으로 노출되게 됩니다.

스크립트 설치는 페이지 내에 다른 리소스에 영향을 주지 않도록 페이지 맨 하단에 `</body>` 부분 바로 위에 설치하는 것이 좋습니다.

이때, 설치하고자 하는 페이지에 따라 설정 방법이 달라질 수 있습니다.

##### 2.1. 간편 설치

가장 빠르게 광고 스크립트를 설치할 수 있는 방법입니다. 광고 노출 시점을 제어할 필요가 없다면, 간편 설치 방법을 사용하면 되겠습니다.

[1. 환경 설정]의 `<!-- 광고 스크립트 실행 코드 -->` 부분을 아래 코드로 대체합니다.

<pre class="prettyprint lang-html"><code>&lt;!-- 광고 스크립트 실행 - 간편설치 --&gt;
&lt;script type="text/javascript" src="http://m1.daumcdn.net/adtc/js/mobilead.js" async&gt;&lt;/script&gt;
&lt;/body&gt;
&lt;/html&gt;</code></pre>

##### 2.2. 페이지 onload 이벤트를 이용한 설치

*페이지의 컨텐츠를 모두 불러온 후에 광고를 노출 시키고 싶을 때 사용하는 방법*으로, 이는 개발 방법에 따라 달라질 수 있으나 크게 아래 세 가지 유형으로 압축해볼 수 있습니다.

**2.2.1. 페이지 내에서 onload 함수를 사용하는 경우**

body 테그 내에 onload 속성 값으로 특정 함수를 실행하는 페이지의 경우를 말합니다.

이 경우에는 onload 시점에 실행하는 함수가 종료되기 직전에 광고 스크립트를 불러오도록 해야합니다.

<pre class="prettyprint lang-html" style="word-wrap: break-word;white-space: pre-wrap;white-space: -moz-pre-wrap;white-space: -pre-wrap;white-space: -o-pre-wrap;word-break:break-all;"><code>&lt;html&gt;

&lt;!-- onload 시점에 init 함수를 실행한다. --&gt;
&lt;body onload="init();"&gt;

&lt;!-- 광고를 보여줄 영역 --&gt;
&lt;div id="MobileadAreaDiv"&gt;&lt;/div&gt;

&lt;!-- 광고 스크립트 환경 설정 --&gt;
&lt;script type="text/javascript"&gt;
	var daum_adam_vars = {
	  // 광고 삽입 스크립트에 있는 광고단위ID를 이 곳에 넣으세요
	  client : '광고단위ID',

	  // TOP (화면상단) | BOTTOM (화면 하단) | MIDDLE (화면 중간 삽입. bannerDivId 지정 필요)
	  position : 'MIDDLE',

	  // position : 'MIDDLE' 인 경우, 광고를 삽입할 DIV 태그의 ID 값.
	  bannerDivId : 'MobileadAreaDiv'
  };
&lt;/script&gt;

&lt;script type="text/javascript"&gt;
    function init() {
        // ...
        // (기존에 정의된 함수 내용)
        // ...

        // 페이지에 모든 작업이 완료된 후에 광고 스크립트를 설치한다.
        InstallAdamWebScript();
    }
&lt;/script&gt;

&lt;!-- 광고 스크립트 실행 코드  --&gt;
&lt;script type="text/javascript"&gt;
function InstallAdamWebScript() {
    (function(a,b,c,d,e,f){e=a.createElement(b);f=a.getElementsByTagName(b)[0];e.async=1;e.src=c;f.parentNode.insertBefore(e,f);e.onload=e.onreadystatechange=function(){(typeof(d)+'')[0]=='f'&&(!this.readyState||this.readyState=='complete')?(d()):0}})(document,'script','http://m1.daumcdn.net/adtc/js/mobilead.js');
}
&lt;/script&gt;
&lt;/body&gt;
&lt;/html&gt;</code></pre>

**2.2.2. 페이지 내에서 `window.onload` 또는 `document.body.onload` 에 함수를 할당해서 사용하는 경우**

페이지 내에서 onload 시점을 따로 정의해서 사용하는 경우를 말합니다.

이 경우 역시 [2.2.1. 페이지 내에서 onload 함수를 사용하는 경우]와 동일한 방법으로, 함수가 종료되기 직전에 광고 스크립트를 불러오도록 해야합니다.

<pre class="prettyprint lang-html" style="word-wrap: break-word;white-space: pre-wrap;white-space: -moz-pre-wrap;white-space: -pre-wrap;white-space: -o-pre-wrap;word-break:break-all;"><code>&lt;html&gt;

&lt;!-- onload 시점에 init 함수를 실행한다. --&gt;
&lt;body&gt;

&lt;!-- 광고를 보여줄 영역 --&gt;
&lt;div id="MobileadAreaDiv"&gt;&lt;/div&gt;

&lt;!-- 광고 스크립트 환경 설정 --&gt;
&lt;script type="text/javascript"&gt;
	var daum_adam_vars = {
	  // 광고 삽입 스크립트에 있는 광고단위ID를 이 곳에 넣으세요
	  client : '광고단위ID',

	  // TOP (화면상단) | BOTTOM (화면 하단) | MIDDLE (화면 중간 삽입. bannerDivId 지정 필요)
	  position : 'MIDDLE',

	  // position : 'MIDDLE' 인 경우, 광고를 삽입할 DIV 태그의 ID 값.
	  bannerDivId : 'MobileadAreaDiv'
  };
&lt;/script&gt;

&lt;script type="text/javascript"&gt;
    window.onload = function () {
        // ...
        // (기존에 정의된 함수 내용)
        // ...

        // 페이지에 모든 작업이 완료된 후에 광고 스크립트를 설치한다.
        installAdamWebScript();
    };
&lt;/script&gt;

&lt;!-- 광고 스크립트 실행 코드  --&gt;
&lt;script type="text/javascript"&gt;
function InstallAdamWebScript() {
    (function(a,b,c,d,e,f){e=a.createElement(b);f=a.getElementsByTagName(b)[0];e.async=1;e.src=c;f.parentNode.insertBefore(e,f);e.onload=e.onreadystatechange=function(){(typeof(d)+'')[0]=='f'&&(!this.readyState||this.readyState=='complete')?(d()):0}})(document,'script','http://m1.daumcdn.net/adtc/js/mobilead.js');
}
&lt;/script&gt;
&lt;/body&gt;
&lt;/html&gt;</code></pre>

**2.2.3. 페이지 내에서 `document.addEventListener` 이벤트를 사용하는 경우**

페이지 내에서 onload 이벤트 리스너를 정의해서 사용하는 경우를 말합니다.

하지만 페이지 내에서 onload 관련해서 [2.2.1]나 [2.2.2]와 같이 별도로 `onload` 이벤트를 제어하는 경우가 아닌 페이지라도 사용할 수 있습니다.

설치는 [1. 환경 설정]의 `<!-- 광고 스크립트 실행 코드 -->` 부분을 아래 코드로 대체하면 됩니다.
<pre class="prettyprint lang-html" style="word-wrap: break-word;white-space: pre-wrap;white-space: -moz-pre-wrap;white-space: -pre-wrap;white-space: -o-pre-wrap;word-break:break-all;"><code>&lt;!-- 광고 스크립트 실행 - document.addEventListener를 사용하는 경우 --&gt;
&lt;script type="text/javascript"&gt;
(function(c,d,e,f,g,h,i){(function(a,b){d.addEventListener?c.addEventListener(a,b,!!0):c.attachEvent("on"+a,b)})('load',function(){h=d.createElement(e);i=d.getElementsByTagName(e)[0];h.async=1;h.src=f;i.parentNode.insertBefore(h,i);h.onload=h.onreadystatechange=function(){(typeof(g)+'')[0]=='f'&&(!this.readyState||this.readyState=='complete')?(g()):0}},false)})(window,document,'script','http://m1.daumcdn.net/adtc/js/mobilead.js');
&lt;/script&gt;
</code></pre>

<script type="text/javascript">prettyPrint();</script>


이 문서는 Kakao 신디케이션 제휴 당사자에 한해 제공되는 자료로 가이드 라인을 포함한 모든 자료의 지적재산권은 주식회사 카카오가 보유합니다.

Copyright © Kakao Corp. All rights reserved.
