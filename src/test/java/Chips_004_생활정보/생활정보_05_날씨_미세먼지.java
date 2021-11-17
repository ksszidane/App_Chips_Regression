package Chips_004_생활정보;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_05_날씨_미세먼지 extends Chips_TestCase {
	
	String AccessToken;
	String 현재위치 = "";
	
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
	    String tid = util.TransactionID_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
	    
	    test.log(Status.INFO, "acceesToken 얻기"); 
	    String actn = util.acceesToken_JsonParsing(ServerName, Place, tid);
	    
	    test.log(Status.INFO, "acceesToken : " + actn); 
	    System.out.println("토큰 값 : " + actn);
	    AccessToken = actn;
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0000")
	public void TC_0000_앱실행과AccessToken얻기(Method method) throws Exception {

		test.log(Status.INFO, "퍼미션 오디오 녹음 권한 허용 (ADB MiC permission On)");
		adb.ChipsApp_permission_MIC_On(udid);
		
		test.log(Status.INFO, "퍼미션 위치 권한 허용 (ADB LOCATION permission On)");
		adb.ChipsApp_permission_LOCATION_On(udid);
		
		test.log(Status.INFO, "AppActivity으로 화면 확인");
		util.context("NATIVE_APP");
	    
		//test.log(Status.INFO, "Chips 업데이트 팝업 확인");
	    //util.chips_update_check(ServerName);
		
		test.log(Status.INFO, "접근권한 허용 버튼 클릭");
		util.click(By.xpath(xPath.접근권한허용버튼));
	    
	    test.log(Status.INFO, "NUGU CHIPS 빠른 설정 가이드 [X]버튼 닫기 "); 
	    util.click(By.id("btn_close"));
	    Thread.sleep(5000);
	    
	    test.log(Status.INFO, "세션 만료 후 로그인 시도"); 
	    util.click(By.id("loginButton"));
	    Thread.sleep(1000);
	    
	    test.log(Status.INFO, "WEBVIEW로 화면 전환");
        util.switchContext("WEBVIEW");
        
        test.log(Status.INFO, "저장된 간편로그인 유효성 체크 및 클릭");
        String id = util.getText(By.xpath("//ul[@class='account-list']/li[1]/a/span[1]"));
        if (id.equals("nuguqa001@sk.com")) {
        	util.click(By.xpath(xPath.간편로그인_1st));
        	System.out.println("[일치] 로그인id : nuguqa001@sk.com");
        } else {
        	util.click(By.xpath(xPath.간편로그인_2st));
        	System.out.println("[불일치] 로그인id : nuguqa001@sk.com");
        }
	    
	    test.log(Status.INFO, "NATIVE로 화면 확인");
	    util.context("NATIVE_APP");
	    util.ProgressBar_Loading();
	    
	    test.log(Status.INFO, "위치 서비스 사용 설정 안내 팝업 확인"); 
		boolean coach_mark = util.isElementPresent(By.id("negativeButton"));
		if(coach_mark == true) {
			util.click(By.id("negativeButton"));
			test.log(Status.INFO, "위치 서비스 사용 설정 안내 팝업 취소 클릭"); 
			System.out.println("위치 서비스 사용 설정 안내 팝업 취소 클릭");
		} else { 
			Thread.sleep(1000);
		}
	    
	    accessToken얻기();
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0743")
	public void TC_0743_Chips_날씨_오늘미세먼지_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "현재위치 정보값 저장");
		String str = util.getText(By.id("location"));
		String[] array = str.split(" ");
		현재위치 = array[2];
		
		test.log(Status.INFO, "W, 미세먼지 - 발화");
		util.SWFsendPost("미세먼지", ServerName, AccessToken);
		
		test.log(Status.INFO, "날씨 카드 타이틀 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		String txt = util.getText(By.xpath(xPath.날씨타이틀_web));
		String 오늘날짜 = util.getKoreaDate();
		Assert.assertEquals(txt, 오늘날짜 + " 미세먼지");
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
	    test.log(Status.INFO, "W, 미세먼지 - 발화");
		util.SWFsendPost("미세먼지", ServerName, AccessToken);
	    
		test.log(Status.INFO, "미세먼지 위치정보 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		String 위치정보 = util.getText(By.xpath(xPath.날씨위치정보_web));
		Assert.assertTrue(위치정보.contains(현재위치));
		
		test.log(Status.INFO, "미세먼지 상태 문구 확인");
		boolean 미세먼지문구 = util.isElementPresent(By.xpath(xPath.메세먼지상태Text_web));
		Assert.assertTrue(미세먼지문구);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
	    test.log(Status.INFO, "W, 미세먼지 - 발화");
		util.SWFsendPost("미세먼지", ServerName, AccessToken);
		
		test.log(Status.INFO, "미세먼지 아이콘 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 미세먼지아이콘 = util.isElementPresent(By.xpath(xPath.메세먼지아이콘_web));
		Assert.assertTrue(미세먼지아이콘);
		
		test.log(Status.INFO, "미세먼지 농도  확인");
		boolean 미세먼지농도 = util.isElementPresent(By.xpath(xPath.메세먼지농도_web));
		Assert.assertTrue(미세먼지농도);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "W, 미세먼지 - 발화");
		util.SWFsendPost("미세먼지", ServerName, AccessToken);
		
		test.log(Status.INFO, "초미세먼지 영역 항목 노출 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 초미세먼지 = util.isElementPresent(By.xpath(xPath.초미세먼지_web));
		Assert.assertTrue(초미세먼지);
		boolean 초미세먼지아이콘 = util.isElementPresent(By.xpath(xPath.초미세먼지아이콘_web));
		Assert.assertTrue(초미세먼지아이콘);
		boolean 초미세먼지상태= util.isElementPresent(By.xpath(xPath.초미세먼지상태_web));
		Assert.assertTrue(초미세먼지상태);
		boolean 초미세먼지농도= util.isElementPresent(By.xpath(xPath.초미세먼지농도_web));
		Assert.assertTrue(초미세먼지농도);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "W, 미세먼지 - 발화");
		util.SWFsendPost("미세먼지", ServerName, AccessToken);
		
		test.log(Status.INFO, "통합대기지수 영역 항목 노출 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 통합대기지수 = util.isElementPresent(By.xpath(xPath.통합대기지수_web));
		Assert.assertTrue(통합대기지수);
		boolean 통합대기지수아이콘 = util.isElementPresent(By.xpath(xPath.통합대기지수아이콘_web));
		Assert.assertTrue(통합대기지수아이콘);
		boolean 통합대기지수상태= util.isElementPresent(By.xpath(xPath.통합대기지수상태_web));
		Assert.assertTrue(통합대기지수상태);
		boolean 통합대기지수농도= util.isElementPresent(By.xpath(xPath.통합대기지수농도_web));
		Assert.assertTrue(통합대기지수농도);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "W, 미세먼지 - 발화");
		util.SWFsendPost("미세먼지", ServerName, AccessToken);
		
		test.log(Status.INFO, "오존 영역 항목 노출 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 오존 = util.isElementPresent(By.xpath(xPath.오존_web));
		Assert.assertTrue(오존);
		boolean 오존아이콘 = util.isElementPresent(By.xpath(xPath.오존아이콘_web));
		Assert.assertTrue(오존아이콘);
		boolean 오존상태= util.isElementPresent(By.xpath(xPath.오존상태_web));
		Assert.assertTrue(오존상태);
		boolean 오존농도= util.isElementPresent(By.xpath(xPath.오존농도_web));
		Assert.assertTrue(오존농도);
		
		test.log(Status.INFO, "오늘날씨 TTS 확인");
		String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("미세먼지 지수"));
		Assert.assertTrue(tts.contains("초미세먼지 지수"));
		Assert.assertTrue(tts.contains("현재 " + 현재위치));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0744")
	public void TC_0744_Chips_날씨_내일미세먼지_확인(Method method) throws Exception {
		 
		//test.log(Status.INFO, "현재위치 정보값 저장");
		//String str = util.getText(By.id("location"));
		//String[] array = str.split(" ");
		//String 현재위치 = array[2];
		
		test.log(Status.INFO, "W, 내일 미세먼지 상태 알려줘 - 발화");
		util.SWFsendPost("내일 미세먼지 상태 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "날씨 카드 타이틀 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		String txt = util.getText(By.xpath(xPath.날씨타이틀_web));
		String 내일날짜 = util.getChangePreviousDate(1);
		System.out.println("화면 : " + txt);
		System.out.println("getChangePreviousDate : "+내일날짜);
		Assert.assertEquals(txt, 내일날짜 + " 미세먼지");
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "W, 내일 미세먼지 상태 알려줘 - 발화");
		util.SWFsendPost("내일 미세먼지 상태 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "미세먼지 위치정보 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		String 위치정보 = util.getText(By.xpath(xPath.날씨위치정보_web));
		Assert.assertTrue(위치정보.contains(현재위치));
		
		test.log(Status.INFO, "미세먼지 타이틀 확인");
		boolean 미세먼지문구 = util.isElementPresent(By.xpath(xPath.내일미세먼지_web));
		Assert.assertTrue(미세먼지문구);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "W, 내일 미세먼지 상태 알려줘 - 발화");
		util.SWFsendPost("내일 미세먼지 상태 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "미세먼지 상태 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 미세먼지상태 = util.isElementPresent(By.xpath(xPath.내일미세먼지상태_web));
		Assert.assertTrue(미세먼지상태);
		
		test.log(Status.INFO, "미세먼지 아이콘 확인");
		boolean 미세먼지아이콘 = util.isElementPresent(By.xpath(xPath.내일미세먼지아이콘_web));
		Assert.assertTrue(미세먼지아이콘);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "W, 내일 미세먼지 상태 알려줘 - 발화");
		util.SWFsendPost("내일 미세먼지 상태 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "초미세먼지 타이틀 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 초미세먼지문구 = util.isElementPresent(By.xpath(xPath.내일초미세먼지_web));
		Assert.assertTrue(초미세먼지문구);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "W, 내일 미세먼지 상태 알려줘 - 발화");
		util.SWFsendPost("내일 미세먼지 상태 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "초미세먼지 상태 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 초미세먼지상태 = util.isElementPresent(By.xpath(xPath.내일초미세먼지상태_web));
		Assert.assertTrue(초미세먼지상태);
		
		test.log(Status.INFO, "미세먼지 아이콘 확인");
		boolean 초미세먼지아이콘 = util.isElementPresent(By.xpath(xPath.내일초미세먼지아이콘_web));
		Assert.assertTrue(초미세먼지아이콘);
		
		test.log(Status.INFO, "오늘날씨 TTS 확인");
		String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("미세먼지 지수"));
		Assert.assertTrue(tts.contains("초미세먼지 지수"));
		Assert.assertTrue(tts.contains("내일 " + 현재위치));
		
	}

}
