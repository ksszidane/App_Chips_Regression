package Chips_004_생활정보;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_09_날씨_오존농도 extends Chips_TestCase {
	
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
        if (id == "nuguqa001@sk.com") {
        	util.click(By.xpath(xPath.간편로그인_1st));
        } else {
        	util.click(By.xpath(xPath.간편로그인_2st));
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0767")
	public void TC_0767_Chips_날씨_오존지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재위치 정보값 저장");
		String str = util.getText(By.id("location"));
		String[] array = str.split(" ");
		String 현재위치 = array[2];
		
		test.log(Status.INFO, "W, 오늘 오존 어때? - 발화");
		util.SWFsendPost("오늘 오존 어때?", ServerName, AccessToken);
		
		test.log(Status.INFO, "오존 지수 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("오늘 " + 현재위치 + " 오존 농도는"));
		Assert.assertTrue(tts.contains("ppm"));
			
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0768")
	public void TC_0768_Chips_날씨_지역_오존지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 오늘 부산 오존 정보 - 발화");
		util.SWFsendPost("오늘 부산 오존 정보", ServerName, AccessToken);

		test.log(Status.INFO, "오존 지수 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("오늘 부산 오존 농도는"));
		Assert.assertTrue(tts.contains("ppm"));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0769")
	public void TC_0769_Chips_날씨_내일_오존지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재위치 정보값 저장");
		String str = util.getText(By.id("location"));
		String[] array = str.split(" ");
		String 현재위치 = array[2];
		
		test.log(Status.INFO, "W, 내일 오존 알려줘 - 발화");
		util.SWFsendPost("내일 오존 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "자외선 지수 A구간[4월16일~10월14일] | B구간[10월15일~12월31일] | C구간[01월01일~04월15일] 체크");
		String 오존시간대 = util.Month1015to90415();
		
		if (오존시간대.equals("A구간")) {
			test.log(Status.INFO, "[4월16일~10월14일] 오존 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
			Assert.assertTrue(tts.contains("내일 " + 현재위치 + " 오존 농도는"));
		} else if (오존시간대.equals("B구간")) {
			test.log(Status.INFO, "[10월15일~4월15일] 오존 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
			Assert.assertTrue(tts.contains("10월 15일부터 내년 4월 15일까지는 오늘의 오존 농도만 제공이 가능해요. 오늘의 오존 농도를 물어봐 주세요."));
		} else if (오존시간대.equals("C구간")) {
			test.log(Status.INFO, "[01월01일~04월15일] 오존 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
			Assert.assertTrue(tts.contains("10월 15일부터 내년 4월 15일까지는 오늘의 오존 농도만 제공이 가능해요. 오늘의 오존 농도를 물어봐 주세요."));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0770")
	public void TC_0770_Chips_날씨_내일지역_오존지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 내일 부산 오존 알려줘 - 발화");
		util.SWFsendPost("내일 부산 오존 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "자외선 지수 A구간[4월16일~10월14일] | B구간[10월15일~12월31일] | C구간[01월01일~04월15일] 체크");
		String 오존시간대 = util.Month1015to90415();
		
		if (오존시간대.equals("A구간")) {
			test.log(Status.INFO, "[4월16일~10월14일] 오존 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
			Assert.assertTrue(tts.contains("내일 부산 오존 농도는"));
		} else if (오존시간대.equals("B구간")) {
			test.log(Status.INFO, "[10월15일~4월15일] 오존 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
			Assert.assertTrue(tts.contains("10월 15일부터 내년 4월 15일까지는 오늘의 오존 농도만 제공이 가능해요. 오늘의 오존 농도를 물어봐 주세요."));
		} else if (오존시간대.equals("C구간")) {
			test.log(Status.INFO, "[01월01일~04월15일] 오존 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
			Assert.assertTrue(tts.contains("10월 15일부터 내년 4월 15일까지는 오늘의 오존 농도만 제공이 가능해요. 오늘의 오존 농도를 물어봐 주세요."));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0771")
	public void TC_0771_Chips_날씨_오존지수부재_어제_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 어제 오존 알려줘 - 발화");
		util.SWFsendPost("어제 오존 알려줘", ServerName, AccessToken);

		test.log(Status.INFO, "어제 오존지수 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("저는 오늘과 내일의 오존 정보만 가지고 있어요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0764")
	public void TC_0772_Chips_날씨_오존지수부재_특정일_확인(Method method) throws Exception {
	    
	    test.log(Status.INFO, "그제 날짜 계산");
		String 그제날짜 = util.getChangePreviousDate(-2);
		
		test.log(Status.INFO, "W, "+ 그제날짜 +" 오존 정보 알려줘 - 발화");
		util.SWFsendPost(그제날짜 + " 오존 정보 알려줘", ServerName, AccessToken);

		test.log(Status.INFO, 그제날짜 + " 오존지수 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("저는 오늘과 내일의 오존 정보만 가지고 있어요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0765")
	public void TC_0773Chips_날씨_오존지수부재_지역외_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 로스앤젤레스 오존 알려줘 - 발화");
		util.SWFsendPost("로스앤젤레스 오존 알려줘", ServerName, AccessToken);

		test.log(Status.INFO, "지역외 오존지수 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("말씀하신 지역의 오존 정보는 가지고 있지 않습니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0766")
	public void TC_0774_Chips_날씨_오존지수부재_지역아님_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 하이마트 오존 어때? - 발화");
		util.SWFsendPost("하이마트 오존 어때?", ServerName, AccessToken);

		test.log(Status.INFO, "지역아닌곳 오존지수 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("말씀하신 지역의 오존 정보는 가지고 있지 않습니다."));
	}

}
