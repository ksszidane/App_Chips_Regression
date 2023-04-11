package Chips_008_생활정보_etc;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_01_메뉴추천 extends Chips_TestCase {
	
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
	    String tid = util.TransactionID_JsonParsing(nuguqa001, Chips_001, ServerName, Service);
	    
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
		util.switchToNative();
	    
		//test.log(Status.INFO, "Chips 업데이트 팝업 확인");
	    //util.chips_update_check(ServerName);
		
		test.log(Status.INFO, "접근권한 허용 버튼 클릭");
		util.click(By.xpath(xPath.접근권한허용버튼));
	    
	    test.log(Status.INFO, "NUGU CHIPS 빠른 설정 가이드 [X]버튼 닫기 "); 
	    util.click(By.id("btn_close"));
	    Thread.sleep(5000);
	    
	    test.log(Status.INFO, "세션 만료 후 로그인 시도"); 
	    util.click(By.id("loginButton"));
	    Thread.sleep(5000);
	    
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
	    util.switchToNative();
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0697")
	public void TC_0697_Chips_시간대별_메뉴추천_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 점심 메뉴 추천해줘 - 발화");
		util.SWFsendPost("점심 메뉴 추천해줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "campaign Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign"));
		
		test.log(Status.INFO, "campaign.menurecommendations intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign.menurecommendations"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0698")
	public void TC_0698_Chips_제철메뉴_메뉴추천_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 제철 메뉴 추천해줘 - 발화");
		util.SWFsendPost("제철 메뉴 추천해줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "campaign Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign"));
		
		test.log(Status.INFO, "campaign.menurecommendations intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign.menurecommendations"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0699")
	public void TC_0699_Chips_메뉴추천_테마1_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 술안주 추천 - 발화");
		util.SWFsendPost("술안주 추천", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "campaign Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign"));
		
		test.log(Status.INFO, "campaign.menurecommendations intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign.menurecommendations"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0700")
	public void TC_0700_Chips_메뉴추천_테마2_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 술안주 추천 - 발화");
		util.SWFsendPost("술안주 추천", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "campaign Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign"));
		
		test.log(Status.INFO, "campaign.menurecommendations intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign.menurecommendations"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0701")
	public void TC_0701_Chips_메뉴추천_특정이벤트_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 크리스마스 메뉴 추천해줘 - 발화");
		util.SWFsendPost("크리스마스 메뉴 추천해줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "메뉴추천 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "크리스마스"));
		
		test.log(Status.INFO, "campaign Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign"));
		
		test.log(Status.INFO, "campaign.menurecommendations intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "campaign.menurecommendations"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0702")
	public void TC_0702_Chips_메뉴추천_테마테마_미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 매콤한 볶음 요리 추천해줘 - 발화");
		util.SWFsendPost("매콤한 볶음 요리 추천해줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "테마+테마 메뉴추천 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.OOS_set));
		
		test.log(Status.INFO, "oos action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "oos"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0703")
	public void TC_0703_Chips_메뉴추천_테마테마_미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 점심에 먹을 간단한 혼밥 메뉴 추천해줘 - 발화");
		util.SWFsendPost("점심에 먹을 간단한 혼밥 메뉴 추천해줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "beep action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "beep"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0704")
	public void TC_0704_Chips_메뉴추천_TTS재생중_중지_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 점심 메뉴 추천해줘 - 발화");
		util.SWFsendPost("점심 메뉴 추천해줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "W, 그만 - 발화");
		util.SWFsendPost("그만", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "general Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "general"));
		
		test.log(Status.INFO, "stop intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "stop"));
		
		test.log(Status.INFO, "stop action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "stop"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0705")
	public void TC_0705_Chips_메뉴추천_닫아_미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 메뉴추천 닫아 - 발화");
		util.SWFsendPost("메뉴추천 닫아", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "메뉴추천 닫아 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.OOS_set));
		
		test.log(Status.INFO, "oos action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "oos"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0706")
	public void TC_0706_Chips_메뉴추천_다시_미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 메뉴추천 다시 - 발화");
		util.SWFsendPost("메뉴추천 다시", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "메뉴추천 다시 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.OOS_set));
		
		test.log(Status.INFO, "oos action_type 확인");
		String usd = util.action_type_JsonParsing(nuguqa001, Chips_001, ServerName, Service);
		Assert.assertTrue(usd.contains("oos"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0707")
	public void TC_0707_Chips_다른메뉴추천_미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 다른 메뉴 추천해줘 - 발화");
		util.SWFsendPost("다른 메뉴 추천해줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "다른 메뉴 추천해줘 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.OOS_set));
		
		test.log(Status.INFO, "oos action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "oos"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0708")
	public void TC_0708_Chips_메뉴추천_TTS재생중_이거뭐야_미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 점심 메뉴 추천해줘 - 발화");
		util.SWFsendPost("점심 메뉴 추천해줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(3000);
		
		test.log(Status.INFO, "W, 이거 뭐야 - 발화");
		util.SWFsendPost("이거 뭐야", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "날짜 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "재생 정보가 없습니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0709")
	public void TC_0709_Chips_메뉴추천_도움말_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 메뉴추천 도움말 - 발화");
		util.SWFsendPost("메뉴추천 도움말", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "메뉴추천 도움말 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.메뉴추천도움말_set));
	}

}
