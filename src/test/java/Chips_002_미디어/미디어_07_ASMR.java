package Chips_002_미디어;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_07_ASMR extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0315")
	public void TC_0315_Chips_ASMR_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, ASMR 플레이 - 발화");
		util.SWFsendPost("ASMR 플레이", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "ASMR"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "W, ASMR 플레이 - 발화");
		util.SWFsendPost("ASMR 플레이", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "ASMR"));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "곡명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
		test.log(Status.INFO, "반복 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO반복버튼)));
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		util.context("NATIVE_APP");
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0316")
	public void TC_0316_Chips_ASMR_과자먹는소리재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 과자 먹는 소리 틀어줘 - 발화");
		util.SWFsendPost("과자 먹는 소리 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "과자 먹는 소리"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "W, 과자 먹는 소리 틀어줘 - 발화");
		util.SWFsendPost("과자 먹는 소리 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "ASMR"));
		
		test.log(Status.INFO, "곡명 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "과자 먹는 소리"));
		
		test.log(Status.INFO, "과자먹는 소리 이미지커버 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.이미지커버src), "src", "30_thumbnail.png"));
		
		test.log(Status.INFO, "한곡 반복 버튼 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.한곡반복_web)));
		util.context("NATIVE_APP");
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0317")
	public void TC_0317_Chips_ASMR_빗소리_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 빗소리 들려줘 - 발화");
		util.SWFsendPost("빗소리 들려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "빗소리"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "ASMR"));
		
		test.log(Status.INFO, "곡명 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO제목), "빗소리"));
		
		test.log(Status.INFO, "빗소리 이미지커버 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.이미지커버src), "src", "01_thumbnail.png"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0318")
	public void TC_0318_Chips_ASMR_힐링사운드_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 힐링 사운드 재생 - 발화");
		util.SWFsendPost("힐링 사운드 재생", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "힐링사운드"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "ASMR"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0319")
	public void TC_0319_Chips_ASMR_자연의소리_반복확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 자연의 소리 반복해서 틀어줘 - 발화");
		util.SWFsendPost("자연의 소리 반복해서 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "자연의 소리 반복해드릴게요."));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "ASMR"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0319")
	public void TC_0320_Chips_ASMR_이노래뭐야_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "곡명 확인");
		String 곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 이 노래 뭐야 - 발화");
		util.SWFsendPost_playStatus("이 노래 뭐야", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "이 노래 뭐야 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, 곡명));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0321")
	public void TC_0321_Chips_ASMR_다음ASMR_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "곡명 확인");
		String 이전곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 다음거 - 발화");
		util.SWFsendPost_playStatus("다음 곡", ServerName, AccessToken, "music");
		Thread.sleep(5000);

		test.log(Status.INFO, "곡명 확인");
		String 다음곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertFalse(이전곡명.contains(다음곡명));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0322")
	public void TC_0322_Chips_ASMR_이전ASMR_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "곡명 확인");
		String 이전곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 이전거 - 발화");
		util.SWFsendPost_playStatus("이전 곡", ServerName, AccessToken, "music");
		Thread.sleep(5000);

		test.log(Status.INFO, "곡명 확인");
		String 다음곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertFalse(이전곡명.contains(다음곡명));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0324")
	public void TC_0324_Chips_ASMR_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 자연의 소리 틀어줘 - 발화");
		util.SWFsendPost("자연의 소리 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "자연의 소리 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "자연의소리"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "ASMR"));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "곡명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
		test.log(Status.INFO, "반복 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO반복버튼)));
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		util.context("NATIVE_APP");
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0325")
	public void TC_0325_Chips_ASMR_일시정지_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지", ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.재생버튼_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0326")
	public void TC_0326_Chips_ASMR_일시정지취소_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 일시정지 취소 - 발화");
		util.SWFsendPost_playStatus("일시정지 취소", ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.일시정지버튼_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0327")
	public void TC_0327_Chips_ASMR_일시정지_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지", ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.재생버튼_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0328")
	public void TC_0328_Chips_ASMR_재생_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 자연의 소리 틀어줘 - 발화");
		util.SWFsendPost("자연의 소리 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "자연의 소리 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "자연의소리"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "ASMR"));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "곡명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
		test.log(Status.INFO, "반복 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO반복버튼)));
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		util.context("NATIVE_APP");
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0329")
	public void TC_0329_Chips_ASMR_좋아요_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 좋아요 해줘 - 발화");
		util.SWFsendPost_playStatus("좋아요 해줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "좋아요 해줘 TTS 확인");
		String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("좋아요 기능을 지원하지 않는 곡이에요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0329")
	public void TC_0330_Chips_ASMR_좋아요취소_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 좋아요 취소 - 발화");
		util.SWFsendPost_playStatus("좋아요 취소", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "좋아요 해줘 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "좋아요 기능을 지원하지 않는 곡이에요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0331")
	public void TC_0331_Chips_ASMR_다음ASMR_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "곡명 확인");
		String 이전곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 다음거 - 발화");
		util.SWFsendPost_playStatus("다음 곡", ServerName, AccessToken, "music");
		Thread.sleep(3000);

		test.log(Status.INFO, "곡명 확인");
		String 다음곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertFalse(이전곡명.contains(다음곡명));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0332")
	public void TC_0332_Chips_ASMR_이전ASMR_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "곡명 확인");
		String 이전곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 이전거 - 발화");
		util.SWFsendPost_playStatus("이전 곡", ServerName, AccessToken, "music");
		Thread.sleep(3000);

		test.log(Status.INFO, "곡명 확인");
		String 다음곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertFalse(이전곡명.contains(다음곡명));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0333")
	public void TC_0333_Chips_ASMR_셔플_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "셔플 버튼 클릭");
		util.click(By.xpath(xPath.FLO랜덤버튼));
		
		test.log(Status.INFO, "셔플 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "셔플할게요"));
		
		test.log(Status.INFO, "랜덤 버튼 활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.랜덤재생On_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0334")
	public void TC_0334_Chips_ASMR_다음ASMR_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "곡명 확인");
		String 이전곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 다음거 - 발화");
		util.SWFsendPost_playStatus("다음 곡", ServerName, AccessToken, "music");
		Thread.sleep(3000);

		test.log(Status.INFO, "곡명 확인");
		String 다음곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertFalse(이전곡명.contains(다음곡명));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0335")
	public void TC_0335_Chips_ASMR_이전ASMR_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "곡명 확인");
		String 이전곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 이전거 - 발화");
		util.SWFsendPost_playStatus("이전 곡", ServerName, AccessToken, "music");
		Thread.sleep(3000);

		test.log(Status.INFO, "곡명 확인");
		String 다음곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertFalse(이전곡명.contains(다음곡명));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0336")
	public void TC_0336_Chips_ASMR_셔플해제_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "셔플 버튼 클릭");
		util.click(By.xpath(xPath.FLO랜덤버튼));
		
		test.log(Status.INFO, "셔플 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "셔플을 해제했어요"));
		Thread.sleep(3000);
		
		test.log(Status.INFO, "랜덤 버튼 활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.랜덤재생Off_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0337")
	public void TC_0337_Chips_ASMR_다음ASMR_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "곡명 확인");
		String 이전곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 다음거 - 발화");
		util.SWFsendPost_playStatus("다음 곡", ServerName, AccessToken, "music");
		Thread.sleep(3000);

		test.log(Status.INFO, "곡명 확인");
		String 다음곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertFalse(이전곡명.contains(다음곡명));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0312")
	public void TC_0338_Chips_ASMR_20초뒤로_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 20초 뒤로 이동 - 발화");
		util.SWFsendPost_playStatus("20초 뒤로 이동", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "20초뒤로 이동 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "뮤직 서비스에서는 지원하지 않는 기능이에요."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0313")
	public void TC_0339_Chips_ASMR_15초앞으로_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 15초 앞으로 이동 - 발화");
		util.SWFsendPost_playStatus("15초 앞으로 이동", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "15초 앞으로 이동 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "뮤직 서비스에서는 지원하지 않는 기능이에요."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0340")
	public void TC_0340_Chips_ASMR_종료_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, ASMR 꺼줘 - 발화");
		util.SWFsendPost("꺼줘", ServerName, AccessToken);
		Thread.sleep(3000);
		
		test.log(Status.INFO, "음악카드 종료되고 메인화면 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));
	}

}
