package Chips_004_생활정보;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_13_운세 extends Chips_TestCase {
	
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
	    String tid = util.TransactionID_JsonParsing(ksszidane10, Chips10_did, ServerName, Place);
	    
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
	    util.click(By.xpath(xPath.간편로그인_2st));
	    
	    test.log(Status.INFO, "NATIVE로 화면 확인");
	    util.context("NATIVE_APP");
	    util.ProgressBar_Loading();
	    
	    test.log(Status.INFO, "위치 서비스 사용 설정 안내 팝업 확인"); 
		boolean coach_mark = util.isElementPresent(By.id("negativeButton"));
		if(coach_mark == true) {
			util.click(By.id("negativeButton"));
		} else { 
			Thread.sleep(1000);
		}
	    
	    accessToken얻기();
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0801")
	public void TC_0801_Chips_운세_생년월일미등록_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 오늘의 운세 알려줘 - 발화");
		util.SWFsendPost("오늘의 운세 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "운세 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane10, Chips10_did, ServerName, Place);
		Assert.assertTrue(tts.contains("몇 년, 몇 월 며칠 생 운세를 알려 드릴까요?"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0802")
	public void TC_0802_Chips_운세_생년월일등록_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "앱 재 실행");
		util.resetApp();
		
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
		
		test.log(Status.INFO, "W, 운세 - 발화");
		util.SWFsendPost("운세", ServerName, AccessToken);
		
		test.log(Status.INFO, "운세 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("당신의 오늘의 운세를 알려드릴게요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0803")
	public void TC_0803_Chips_운세_띠별운세_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 양띠 운세 알려줘 - 발화");
		util.SWFsendPost("양띠 운세 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "양띠 운세 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("오늘의 양띠 운세를 알려드릴게요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0804")
	public void TC_0804_Chips_운세_띠별운세_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 쥐띠 내일 운세 알려줘 - 발화");
		util.SWFsendPost("쥐띠 내일 운세 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "양띠 운세 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("내일의 쥐띠 운세를 알려드릴게요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0805")
	public void TC_0805_Chips_운세_생년별운세_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 1979년 1월 4일 운세 알려줘 - 발화");
		util.SWFsendPost("1979년 1월 4일 운세 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "생년 운세 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("79년 1월 4일생, 오늘의 운세를 알려드릴게요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0806")
	public void TC_0806_Chips_운세_생년별운세_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 음력 81년 8월 21일 운세 - 발화");
		util.SWFsendPost("음력 81년 8월 21일 운세", ServerName, AccessToken);
		
		test.log(Status.INFO, "생년 운세 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("음력 81년 8월 21일생, 오늘의 운세를 알려드릴게요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0807")
	public void TC_0807_Chips_운세_생년별운세_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 01년 1월 4일 오늘의 운세 - 발화");
		util.SWFsendPost("01년 1월 4일 오늘의 운세", ServerName, AccessToken);
		
		test.log(Status.INFO, "생년 운세 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("01년 1월 4일생, 오늘의 운세를 알려드릴게요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0807")
	public void TC_0807_Chips_운세_이번주_미지원_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 이번주 운세 봐줘 - 발화");
		util.SWFsendPost("이번주 운세 봐줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "A구간[4월~11월] | B구간[12월~3월]  체크");
		String 미지원기간 = util.Month4to11();
		
		if (미지원기간.equals("A구간")) {
			test.log(Status.INFO, "[4월~11월] 구간 이번주 운세 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
			Assert.assertTrue(tts.contains("지금은 오늘과 내일의 운세만 알려드릴 수 있어요."));
		} else if (미지원기간.equals("B구간")) {
			test.log(Status.INFO, "[12월~3월] 구간 이번주 운세 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
			Assert.assertTrue(tts.contains("저는 오늘과 내일의 운세만 알려드릴 수 있어요."));
		} 
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0808")
	public void TC_0808_Chips_운세_다음주_미지원_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 다음주 운세 봐줘 - 발화");
		util.SWFsendPost("다음주 운세 봐줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "A구간[4월~11월] | B구간[12월~3월]  체크");
		String 미지원기간 = util.Month4to11();
		
		if (미지원기간.equals("A구간")) {
			test.log(Status.INFO, "[4월~11월] 구간 다음주 운세 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
			Assert.assertTrue(tts.contains("지금은 오늘과 내일의 운세만 알려드릴 수 있어요."));
		} else if (미지원기간.equals("B구간")) {
			test.log(Status.INFO, "[12월~3월] 구간 다음주 운세 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
			Assert.assertTrue(tts.contains("저는 오늘과 내일의 운세만 알려드릴 수 있어요."));
		} 
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0809")
	public void TC_0809_Chips_운세_다음달_미지원_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 다음달 운세 봐줘 - 발화");
		util.SWFsendPost("다음달 운세 봐줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "A구간[4월~11월] | B구간[12월~3월]  체크");
		String 미지원기간 = util.Month4to11();
		
		if (미지원기간.equals("A구간")) {
			test.log(Status.INFO, "[4월~11월] 구간 다음달 운세 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
			Assert.assertTrue(tts.contains("지금은 오늘과 내일의 운세만 알려드릴 수 있어요."));
		} else if (미지원기간.equals("B구간")) {
			test.log(Status.INFO, "[12월~3월] 구간 다음달 운세 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
			Assert.assertTrue(tts.contains("저는 오늘과 내일의 운세만 알려드릴 수 있어요."));
		} 
		
	}

}
