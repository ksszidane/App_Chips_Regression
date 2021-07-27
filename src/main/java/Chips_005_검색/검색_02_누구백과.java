package Chips_005_검색;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 검색_02_누구백과 extends Chips_TestCase {
	
	String AccessToken;
	
	public void accessToken얻기() throws Exception {
		
		test.log(Status.INFO, "coach_mark_close 클릭"); 
		boolean coach_mark = util.isElementPresent(By.id("coach_mark_close"));
		if(coach_mark == true) {
			util.click(By.id("coach_mark_close"));
		} else { 
			Thread.sleep(1000);
		}
		
		test.log(Status.INFO, "chips 선택"); 
	    util.click(By.xpath(xPath.chips_1st));
	    
	    util.ProgressBar_Loading();
	  
	    test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    
	    test.log(Status.INFO, "transaction id 얻기"); 
	    String tid = util.TransactionID_JsonParsing(ksszidane, Chips_did, ServerName, Place);
	    
	    test.log(Status.INFO, "acceesToken 얻기"); 
	    String actn = util.acceesToken_JsonParsing(ServerName, Place, tid);
	    
	    test.log(Status.INFO, "acceesToken : " + actn); 
	    System.out.println("토큰 값 : " + actn);
	    AccessToken = actn;
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0000")
	public void TC_0000_앱실행과AccessToken얻기(Method method) throws Exception {

		test.log(Status.INFO, "AppActivity으로 화면 확인");
		util.context("NATIVE_APP");
	    
	    //test.log(Status.INFO, "Chips 업데이트 팝업 확인");
	    //util.chips_update_check(ServerName);
		
		test.log(Status.INFO, "접근권한 허용 버튼 클릭");
		util.click(By.xpath(xPath.접근권한허용버튼));
		
		test.log(Status.INFO, "퍼미션 오디오 녹음 권한 허용 "); 
	    util.switchTo().alert().accept();
	    
	    test.log(Status.INFO, "퍼미션 위치 권한 허용 "); 
	    util.switchTo().alert().accept();
	    
	    test.log(Status.INFO, "NUGU CHIPS 빠른 설정 가이드 [X]버튼 닫기 "); 
	    util.click(By.id("btn_close"));
	    Thread.sleep(5000);
	    
	    test.log(Status.INFO, "세션 만료 후 로그인 시도"); 
	    util.click(By.id("loginButton"));
	    Thread.sleep(1000);
	    
	    test.log(Status.INFO, "WEBVIEW로 화면 전환");
        util.switchContext("WEBVIEW");
        
        test.log(Status.INFO, "저장된 간편로그인 유효성 체크 및 클릭");
	    util.click(By.xpath(xPath.간편로그인_1st));
	    
	    test.log(Status.INFO, "NATIVE로 화면 확인");
	    util.context("NATIVE_APP");
	    util.ProgressBar_Loading();
	    
	    accessToken얻기();
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0909")
	public void TC_0909_Chips_누구백과_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 누구 백과 찾아줘 - 발화");
		util.SWFsendPost("누구 백과 찾아줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Contains(tts, data.백과도움말_set));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0910")
	public void TC_0910_Chips_누구백과_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 위키 알려줘 - 발화");
		util.SWFsendPost("위키 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Contains(tts, data.백과도움말_set));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0911")
	public void TC_0911_Chips_누구백과_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 위키에서 찾아줘 - 발화");
		util.SWFsendPost("위키에서 찾아줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Contains(tts, data.백과도움말_set));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0912")
	public void TC_0912_Chips_누구백과_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 누구백과 도움말 - 발화");
		util.SWFsendPost("누구백과 도움말", ServerName, AccessToken);
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Contains(tts, data.백과도움말_set));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0913")
	public void TC_0913_Chips_누구백과_위키에서_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 위키에서 인도 수도 찾아줘 - 발화");
		util.SWFsendPost("위키에서 인도 수도 찾아줘", ServerName, AccessToken);
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "누구백과 카드 타이틀 확인");
		String 타이틀 = util.getText(By.xpath(xPath.백과카드타이틀_web));
		Assert.assertTrue(타이틀.contains("NUGU 백과"));
		
		test.log(Status.INFO, "누구백과 카드 원문 노출 확인");
		String 한글소스 = util.getText(By.xpath(xPath.백과타이틀_web));
		Assert.assertTrue(한글소스.contains("인도"));
		
		test.log(Status.INFO, "누구백과 원문 노출 확인");
		String 영문 = util.getText(By.xpath(xPath.백과내용_web));
		Assert.assertTrue(영문.contains("인도의 수도는 뉴델리입니다."));
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("인도의 수도는 뉴델리입니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0914")
	public void TC_0914_Chips_누구백과_위키에서_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 위키에서 서울의 면적 찾아줘 - 발화");
		util.SWFsendPost("위키에서 서울의 면적 찾아줘", ServerName, AccessToken);
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "누구백과 카드 타이틀 확인");
		String 타이틀 = util.getText(By.xpath(xPath.백과카드타이틀_web));
		Assert.assertTrue(타이틀.contains("NUGU 백과"));
		
		test.log(Status.INFO, "누구백과 카드 원문 노출 확인");
		String 한글소스 = util.getText(By.xpath(xPath.백과타이틀_web));
		Assert.assertTrue(한글소스.contains("서울특별시"));
		
		test.log(Status.INFO, "누구백과 원문 노출 확인");
		String 영문 = util.getText(By.xpath(xPath.백과내용_web));
		Assert.assertTrue(영문.contains("서울특별시의 면적은 605.20 ㎢입니다."));
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("서울특별시의 면적은 605.20 ㎢입니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0915")
	public void TC_0915_Chips_누구백과_누구백과에서_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 누구백과에서 인도 수도 찾아줘 - 발화");
		util.SWFsendPost("누구백과에서 인도 수도 찾아줘", ServerName, AccessToken);
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "누구백과 카드 타이틀 확인");
		String 타이틀 = util.getText(By.xpath(xPath.백과카드타이틀_web));
		Assert.assertTrue(타이틀.contains("NUGU 백과"));
		
		test.log(Status.INFO, "누구백과 카드 원문 노출 확인");
		String 한글소스 = util.getText(By.xpath(xPath.백과타이틀_web));
		Assert.assertTrue(한글소스.contains("인도"));
		
		test.log(Status.INFO, "누구백과 원문 노출 확인");
		String 영문 = util.getText(By.xpath(xPath.백과내용_web));
		Assert.assertTrue(영문.contains("인도의 수도는 뉴델리입니다."));
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("인도의 수도는 뉴델리입니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0916")
	public void TC_0916_Chips_누구백과_누구백과에서_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 누구백과에서 서울의 면적 찾아줘 - 발화");
		util.SWFsendPost("누구백과에서 서울의 면적 찾아줘", ServerName, AccessToken);
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "누구백과 카드 타이틀 확인");
		String 타이틀 = util.getText(By.xpath(xPath.백과카드타이틀_web));
		Assert.assertTrue(타이틀.contains("NUGU 백과"));
		
		test.log(Status.INFO, "누구백과 카드 원문 노출 확인");
		String 한글소스 = util.getText(By.xpath(xPath.백과타이틀_web));
		Assert.assertTrue(한글소스.contains("서울특별시"));
		
		test.log(Status.INFO, "누구백과 원문 노출 확인");
		String 영문 = util.getText(By.xpath(xPath.백과내용_web));
		Assert.assertTrue(영문.contains("서울특별시의 면적은 605.20 ㎢입니다."));
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("서울특별시의 면적은 605.20 ㎢입니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0917")
	public void TC_0917_Chips_누구백과_호출어생략_키워드_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 이순신이 누구야 - 발화");
		util.SWFsendPost("이순신이 누구야", ServerName, AccessToken);
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "누구백과 카드 타이틀 확인");
		String 타이틀 = util.getText(By.xpath(xPath.백과카드타이틀_web));
		Assert.assertTrue(타이틀.contains("NUGU 백과"));
		
		test.log(Status.INFO, "누구백과 카드 원문 노출 확인");
		String 한글소스 = util.getText(By.xpath(xPath.백과타이틀_web));
		Assert.assertTrue(한글소스.contains("이순신"));
		
		test.log(Status.INFO, "누구백과 원문 노출 확인");
		String 영문 = util.getText(By.xpath(xPath.백과내용_web));
		Assert.assertTrue(영문.contains("이순신은 조선의 장군입니다"));
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("이순신은 조선의 장군입니다"));
		
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0918")
	public void TC_0918_Chips_누구백과_호출어생략_키워드_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 고진감래가 무슨 뜻이야 - 발화");
		util.SWFsendPost("고진감래가 무슨 뜻이야", ServerName, AccessToken);
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "누구백과 카드 타이틀 확인");
		String 타이틀 = util.getText(By.xpath(xPath.백과카드타이틀_web));
		Assert.assertTrue(타이틀.contains("NUGU 백과"));
		
		test.log(Status.INFO, "누구백과 카드 원문 노출 확인");
		String 한글소스 = util.getText(By.xpath(xPath.백과타이틀_web));
		Assert.assertTrue(한글소스.contains("고진감래"));
		
		test.log(Status.INFO, "누구백과 원문 노출 확인");
		String 영문 = util.getText(By.xpath(xPath.백과내용_web));
		Assert.assertTrue(영문.contains("고진감래는, 고생 끝에 낙이 찾아온다는 뜻"));
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("고진감래는, 고생 끝에 낙이 찾아온다는 뜻"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0919")
	public void TC_0919_Chips_누구백과_호출어생략_속성검색_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 아이유의 본명을 알려줘 - 발화");
		util.SWFsendPost("아이유의 본명을 알려줘", ServerName, AccessToken);
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "누구백과 카드 타이틀 확인");
		String 타이틀 = util.getText(By.xpath(xPath.백과카드타이틀_web));
		Assert.assertTrue(타이틀.contains("NUGU 백과"));
		
		test.log(Status.INFO, "누구백과 카드 원문 노출 확인");
		String 한글소스 = util.getText(By.xpath(xPath.백과타이틀_web));
		Assert.assertTrue(한글소스.contains("아이유"));
		
		test.log(Status.INFO, "누구백과 원문 노출 확인");
		String 영문 = util.getText(By.xpath(xPath.백과내용_web));
		Assert.assertTrue(영문.contains("가수 아이유의 본명은 이지은입니다."));
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("가수 아이유의 본명은 이지은입니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0920")
	public void TC_0920_Chips_누구백과_호출어생략_속성검색_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 인터스텔라 개봉일 찾아줘 - 발화");
		util.SWFsendPost("인터스텔라 개봉일 찾아줘", ServerName, AccessToken);
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "누구백과 카드 타이틀 확인");
		String 타이틀 = util.getText(By.xpath(xPath.백과카드타이틀_web));
		Assert.assertTrue(타이틀.contains("NUGU 백과"));
		
		test.log(Status.INFO, "누구백과 카드 원문 노출 확인");
		String 한글소스 = util.getText(By.xpath(xPath.백과타이틀_web));
		Assert.assertTrue(한글소스.contains("인터스텔라"));
		
		test.log(Status.INFO, "누구백과 원문 노출 확인");
		String 영문 = util.getText(By.xpath(xPath.백과내용_web));
		Assert.assertTrue(영문.contains("영화 인터스텔라의 개봉일은 2014년 10월 26일입니다."));
		
		test.log(Status.INFO, "누구 백과 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("영화 인터스텔라의 개봉일은 2014년 10월 26일입니다."));
		
	}

}
