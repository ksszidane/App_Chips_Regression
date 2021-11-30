package Chips_006_생활정보_날씨;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_04_날씨_강수확률 extends Chips_TestCase {
	
	String AccessToken;
	String 현재위치;
	
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
		util.switchToContextName("NATIVE_APP");
	    
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
	    util.switchToContextName("NATIVE_APP");
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0737")
	public void TC_0737_Chips_날씨_현재_강수확률_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 지금 비 와? - 발화");
		util.SWFsendPost("지금 비 와?", ServerName, AccessToken);
		
		test.log(Status.INFO, "지금 비 와? TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.지금강수확률_set));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0738")
	public void TC_0738_Chips_날씨_오늘_강수확률_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 오늘 강수확률 - 발화");
		util.SWFsendPost("오늘 강수확률", ServerName, AccessToken);
		
		test.log(Status.INFO, "지금 비 와? TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.오늘강수확률_set));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0739")
	public void TC_0739_Chips_날씨_내일_강수확률_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 내일 비와? - 발화");
		util.SWFsendPost("내일 비와?", ServerName, AccessToken);
		
		test.log(Status.INFO, "지금 비 와? TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.내일강수확률_set));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0740")
	public void TC_0740_Chips_날씨_모레_강수확률_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "오늘 기준 날짜 데이터 생성");
		String 모레날짜 = util.getChangePreviousDate(+2);
		
		test.log(Status.INFO, "모레 요일 구하기" + util.getDayOfWeek(2));
		String 모레요일 = util.getDayOfWeek(2);
		
		test.log(Status.INFO, "W, 모레 비와? - 발화");
		util.SWFsendPost("모레 비와?", ServerName, AccessToken);
		
		test.log(Status.INFO, "모레 비와? TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, 모레날짜 + " " + 모레요일+ "요일은"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.모레강수확률_set));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0741")
	public void TC_0741_Chips_날씨_이번주날씨_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
		
		util.switchToContextName("NATIVE_APP");
		test.log(Status.INFO, "현재위치 정보값 저장");
		String str = util.getText(By.id("location"));
		String[] array = str.split(" ");
		현재위치 = array[2];
		
		test.log(Status.INFO, "W, 이번 주 비 와? - 발화");
		util.SWFsendPost("이번 주 비 와?", ServerName, AccessToken);
		util.SWFsendPost("이번 주 비 와?", ServerName, AccessToken);
		
		test.log(Status.INFO, "날씨 카드 타이틀 확인");
		util.switchToContextName("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.날씨타이틀_web), "주간 날씨"));
		
		test.log(Status.INFO, "날씨위치정보 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.날씨위치정보_web)));
		
		util.switchToContextName("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
		
		test.log(Status.INFO, "W, 이번 주 비 와? - 발화");
		util.SWFsendPost("이번 주 비 와?", ServerName, AccessToken);
		
		test.log(Status.INFO, "주간날씨 리스트 확인");
		util.switchToContextName("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.주간날씨영역_web)));
	
		test.log(Status.INFO, "첫번쨰 날짜 기준 금일 요일 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.주간날씨리스트_1st요일_web), util.getDayOfWeek(0)));
		
		test.log(Status.INFO, "두번쨰 날짜 기준 내일 마킹확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.주간날씨내일마킹_web), "내일"));
		
		util.switchToContextName("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
		
		test.log(Status.INFO, "W, 이번 주 비 와? - 발화");
		util.SWFsendPost("이번 주 비 와?", ServerName, AccessToken);
		
		test.log(Status.INFO, "마지막 리스트 날짜 기준 금일 요일 확인");
		util.switchToContextName("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.주간날씨리스트_7st요일_web), util.getDayOfWeek(+6)));
		
		test.log(Status.INFO, "모레날씨 최저온도 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.주간날씨리스트_3st최저기온_web)));
		
		test.log(Status.INFO, "모레날씨 최고온도 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.주간날씨리스트_3st최고기온_web)));
		
		test.log(Status.INFO, "모레날씨 아이콘 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.주간날씨리스트_3st아이콘_web)));
		
		test.log(Status.INFO, "이번주 날씨 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "이번주 " + 현재위치));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0742")
	public void TC_0742_Chips_날씨_다음주날씨_확인(Method method) throws Exception {
		 
		util.switchToContextName("NATIVE_APP");
		//test.log(Status.INFO, "현재위치 정보값 저장");
		//String str = util.getText(By.id("location"));
		//String[] array = str.split(" ");
		//String 현재위치 = array[2];
		
		test.log(Status.INFO, "W, 다음주 비 와? - 발화");
		util.SWFsendPost("다음주 비 와?", ServerName, AccessToken);
		
		test.log(Status.INFO, "날씨 카드 타이틀 확인");
		util.switchToContextName("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.날씨타이틀_web), "주간 날씨"));
		
		test.log(Status.INFO, "날씨위치정보 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.날씨위치정보_web)));
		
		test.log(Status.INFO, "주간날씨 리스트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.주간날씨영역_web)));
		
		util.switchToContextName("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
		
		test.log(Status.INFO, "W, 다음주 비 와? - 발화");
		util.SWFsendPost("다음주 비 와?", ServerName, AccessToken);
		
		test.log(Status.INFO, "첫번쨰 날짜 기준 금일 요일 확인");
		util.switchToContextName("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.주간날씨리스트_1st요일_web), "월"));
		
		test.log(Status.INFO, "모레날씨 최저온도 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.주간날씨리스트_1st최저기온_web)));
		
		test.log(Status.INFO, "모레날씨 최고온도 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.주간날씨리스트_2st최고기온_web)));
		
		util.switchToContextName("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
		
	    test.log(Status.INFO, "W, 다음주 비 와? - 발화");
		util.SWFsendPost("다음주 비 와?", ServerName, AccessToken);
		
		test.log(Status.INFO, "모레날씨 아이콘 확인");
		util.switchToContextName("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.주간날씨리스트_3st아이콘_web)));
		
		test.log(Status.INFO, "다음주 날씨 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "다음주 " + 현재위치));
	}

}
