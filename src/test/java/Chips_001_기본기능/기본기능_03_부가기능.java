package Chips_001_기본기능;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 기본기능_03_부가기능 extends Chips_TestCase {
	
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
	    String tid = util.TransactionID_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
	    
	    test.log(Status.INFO, "acceesToken 얻기"); 
	    String actn = util.acceesToken_JsonParsing(ServerName, Place, tid);
	    
	    test.log(Status.INFO, "acceesToken : " + actn); 
	    System.out.println(actn);
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0145")
	public void TC_0145_미디어재생중_infoPlay실행(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 노래 들려줘 - 발화");
		util.SWFsendPost("FLO에서 Daft Punk 노래 들려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "FLO 미디어 실행 TTS 확인");
		String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 이용권 안내 배너 닫기");
		boolean closeSnackbar = util.isElementPresent(By.id("closeSnackbar"));
		if(closeSnackbar == true) {
			util.click(By.id("closeSnackbar"));
		} else { 
			Thread.sleep(1000);
		}
        
		test.log(Status.INFO, "FLO 카드 노출 확인"); 
		String FLO = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLO, "FLO");
		
		Thread.sleep(3000);
		test.log(Status.INFO,  "미디어 재생 중 W, 날씨 알려줘 - 발화");
		util.SWFsendPost("날씨 알려줘", ServerName, AccessToken);
		Thread.sleep(1000);
		
		test.log(Status.INFO, "날씨 카드 노출 확인"); 
		String 날씨 = util.getText(By.xpath(xPath.날씨카드타이틀));
		Assert.assertTrue(날씨.contains("날씨"));
		
		test.log(Status.INFO, "첫번쨰 FLO카드의 미디어바 pause 확인"); 
		util.fastSwipe(131, 850, 960, 850);
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		System.out.println(util.allWindwosIndexCount());
		util.switchToWindwosIndex(1);
		boolean 재생버튼 = util.isElementPresent(By.xpath(xPath.재생버튼_web));
		Assert.assertTrue(재생버튼);
		
		test.log(Status.INFO, "두번째 날씨카드의 이동 후 닫기"); 
		util.fastSwipe(960, 850, 131, 850);
		Thread.sleep(500);
		util.context("NATIVE_APP");
		util.click(By.xpath(xPath.두번쨰카드닫기));
		
		test.log(Status.INFO, "미디어 재생 확인"); 
		Thread.sleep(500);
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		System.out.println(util.allWindwosIndexCount());
		util.switchToWindwosIndex(1);
		boolean 재생중확인 = util.isElementPresent(By.xpath(xPath.일시정지버튼_web));
		Assert.assertTrue(재생중확인);
		
		
	}

}
