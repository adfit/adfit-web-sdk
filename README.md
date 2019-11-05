### AdFit Web SDK Guide
이 가이드는 웹에 광고를 노출하기 위한 광고 스크립트 설치 방법을 설명합니다.<br>
사이트/앱 운영정책에 위반되는 경우 적립금 지급이 거절 될 수 있으니 유의하시기 바랍니다.

* 문의 고객센터 [https://cs.daum.net/question/256.html](https://cs.daum.net/question/256.html)
* 사이트/앱 운영 정책 [http://adfit.biz.daum.net/html/use.html](http://adfit.biz.daum.net/html/use.html)

#### 1. 환경 설정
##### 1.1. 기본적인 광고 스크립트 설정
광고를 노출할 페이지에 다음과 같은 광고 설정 스크립트를 삽입합니다.

광고 스크립트 안에 담긴 광고단위 정보는 변경해서 설치해서는 안됩니다.

코드를 수정해서 설치할 경우 광고 요청에 실패하거나 잘못된 광고 요청으로 처리될 수 있습니다.

<pre class="prettyprint lang-html">
<code>&lt;ins class="kakao_ddn_area" style="display:none;width:100%;"
 data-ad-unit    = "광고단위ID"
 data-ad-width   = "광고단위 가로 사이즈"
 data-ad-height  = "광고단위 세로 사이즈"&gt;&lt;/ins&gt;
&lt;script async type="text/javascript" src="//t1.daumcdn.net/kas/static/ba.min.js"&gt;&lt;/script&gt;
</code></pre>

##### 1.2. NO-AD 콜백 설정하기
광고 요청이 실패하거나 노출할 광고가 없는 경우에 이를 제어하도록 NO-AD 콜백 함수를 설정할 수 있습니다.

AdFit에서 발급받은 코드에 아래와 같이 Callback 함수 요소를 추가하여 설정할 수 있습니다.

<pre class="prettyprint lang-html">
<code>&lt;ins ...
 data-ad-onfail   = "callBackFunc" // NO-AD시 실행될 Callback 함수명
 ... &gt;&lt;/ins&gt;

&lt;script type="text/javascript"&gt;
 function callBackFunc(arg1) {
   /*
    * TODO: 기능 구현
    */
 }
&lt;/script&gt;
</code></pre>

Callback 함수 실행시 ins 태그 객체를 첫 번째 인자(arg1)로 전달 받습니다.
또한 한 페이지 내 2개 이상의 광고단위 설치 시 Callback 메서드 명을 달리 해야 합니다.

#### 2. 스크립트 설치

스크립트 설치는 페이지 내에 다른 리소스에 영향을 주지 않도록 페이지 맨 하단에 `</body>` 부분 바로 위에 설치하는 것이 좋습니다.

이때, 설치하고자 하는 페이지에 따라 설정 방법이 달라질 수 있습니다.

##### 2.1. 구현 예제 - 외부 광고 스크립트를 삽입하는 경우

외부 광고 스크립트를 삽입하는 경우 동일한 사이즈의 외부 광고를 사용하여야 합니다.

(예시. 구글 AdSense 코드. 외부 광고 코드 유형은 반드시 “비동기” 방식을 사용하여야 합니다.)

<pre class="prettyprint lang-html">
<code>&lt;html&gt;
&lt;head&gt;
&lt;script async src="http://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"&gt;&lt;/script&gt;
   
&lt;script type="text/javascript"&gt;
function callBackFunc(elm) {  
    text = [];  
    text.push('&lt;ins class="adsbygoogle"');  
    text.push('style="display:inline-block;width:250px;height:250px"');  
    text.push('data-ad-client="ca-pub-7893835816116192"');  
    text.push('data-ad-slot="5918517081"&gt;&lt;/ins&gt;');  
   
    elm.innerHTML = text.join(' ');  
    (adsbygoogle = window.adsbygoogle || []).push({});  
}  
&lt;/script&gt;
&lt;/head&gt;
&lt;body&gt;
    &lt;ins class="daum_ddn_area" style="display:none;"  
            data-ad-unit="ADUNIT_ID"  //AdFit에서 발급 받은 광고단위코드 값  
            data-ad-width="250"  // 광고단위 가로 사이즈
            data-ad-height="250"  // 광고단위 세로 사이즈
            data-ad-onfail="callBackFunc"&lt;/ins&gt;
   
    &lt;script async type="text/javascript" src="//t1.daumcdn.net/adfit/static/ad.min.js"&gt;
    &lt;/script&gt;
&lt;/body&gt;
&lt;/html&gt;
</code></pre>

이 문서는 Kakao 신디케이션 제휴 당사자에 한해 제공되는 자료로 가이드 라인을 포함한 모든 자료의 지적재산권은 주식회사 카카오가 보유합니다.

Copyright © Kakao Corp. All rights reserved.
