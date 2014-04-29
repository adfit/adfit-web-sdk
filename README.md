<link rel="stylesheet" type="text/css" href="http://cdnjs.cloudflare.com/ajax/libs/prettify/r224/prettify.min.css">
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/prettify/r224/prettify.min.js"></script>

## Ad@m Mobile Web Publisher SDK Guide

이 가이드는 Mobile Web에 모바일 광고를 노출하기 위한 광고 스크립트 설치 방법을 설명합니다.

사이트/앱 운영정책에 어긋나는 경우 적립금 지급이 거절 될 수 있으니 유의하시기 바랍니다.

* 문의 고객센터 [http://cs.daum.net/mail/form/256.html](http://cs.daum.net/mail/form/256.html)
* 사이트/앱 운영 정책 [http://mobile.biz.daum.net/guide/guide_siteapp_policy.jsp](http://mobile.biz.daum.net/guide/guide_siteapp_policy.jsp)

### 광고 스크립트 설치
설치하고자 하는 페이지에 아래 코드를 적절한 위치에 넣어서 사용합니다.

<pre class="prettyprint lang-html">
&lt;html&gt;
&lt;body&gt;
&lt;!-- 광고를 보여줄 영역 --&gt;
&lt;div id="MobileadAreaDiv"&gt;&lt;/div&gt;

&lt;!-- 광고 스크립트 환경 설정 --&gt;
&lt;script type="text/javascript"&gt;
	var daum_adam_vars = {
	  // 광고 삽입 Code에 있는 ClientId를 이 곳에 넣으세요
	  client : 'TestClientId', 

	  // TOP (화면상단) | BOTTOM (화면 하단) | MIDDLE (화면 중간 삽입. bannerDivId 지정 필요)
	  position : 'MIDDLE',

	  // position : 'MIDDLE' 인 경우, 광고를 삽입할 DIV 태그의 ID 값.
	  bannerDivId : 'MobileadAreaDiv',
  };
&lt;/script&gt;

&lt;!-- 광고 스크립트 실행 --&gt;
&lt;script type="text/javascript"&gt;
(function(c,d,e,f,g,h,i){(function(a,b){d.addEventListener?c.addEventListener(a,b,!!0):c.attachEvent("on"+a,b)})('load',function(){h=d.createElement(e);i=d.getElementsByTagName(e)[0];h.async=1;h.src=f;i.parentNode.insertBefore(h,i);h.onload=h.onreadystatechange=function(){(typeof(g)+'')[0]=='f'&&(!this.readyState||this.readyState=='complete')?(g()):0}},false)})(window,document,'script','http://m1.daumcdn.net/adtc/js/mobilead.js');
&lt;/script&gt;

&lt;/body&gt;
&lt;/html&gt;
</pre>

<script type="text/javascript">prettyPrint();</script>

실제 적용 가이드는 [셈플 페이지](http://mobilead.github.io/mobilead-mweb-sdk/)를 참고하시기 바랍니다.

이 문서는 Daum 신디케이션 제휴 당사자에 한해 제공되는 자료로 가이드 라인을 포함한 모든 자료의 지적재산권은 주식회사 다음커뮤니케이션이 보유합니다.

Copyright © Daum Communications. All Rights Reserved.