package Chips_002_미디어_멜론FLO;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_05_FLO_재생컨트롤러 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0239")
	public void TC_0239_Chips_FLO_컨트롤러_음악재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 플레이리스트 틀어줘 - 발화");
		util.SWFsendPost("플레이리스트 틀어줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "플레이리스트 노래 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "Flo에서 ee 내 리스트"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Service, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "내 리스트"));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "곡명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "아티스트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO아티스트)));
		
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
		util.switchToNative();
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0240")
	public void TC_0240_Chips_FLO_컨트롤러_일시정지_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost("일시정지", Chips_001, ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.재생버튼_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0241")
	public void TC_0241_Chips_FLO_컨트롤러_일시정지취소_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 일시정지 취소 - 발화");
		util.SWFsendPost("일시정지 취소", Chips_001, ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.일시정지버튼_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0242")
	public void TC_0242_Chips_FLO_컨트롤러_일시정지_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost("일시정지", Chips_001, ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.재생버튼_web)));


	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0243")
	public void TC_0243_Chips_FLO_컨트롤러_음악재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 내 리스트 재생 - 발화");
		util.SWFsendPost("내 리스트 재생", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "플레이리스트 노래 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "Flo에서 ee 내 리스트"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Service, data.음악시작_set));
		
		util.switchToNative();
		test.log(Status.INFO, "FLO 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "내 리스트"));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "곡명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "아티스트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO아티스트)));
		
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
		util.switchToNative();
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0244")
	public void TC_0244_Chips_FLO_컨트롤러_좋아요_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 좋아요 해줘 - 발화");
		util.SWFsendPost_playStatus("좋아요 해줘", Chips_001, ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "좋아요 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "당신의 Flo 좋아요 리스트에 담았습니다."));
		
		test.log(Status.INFO, "좋아요 버튼 활성화 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.좋아요On_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0245")
	public void TC_0245_Chips_FLO_컨트롤러_좋아요취소_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 좋아요 삭제 - 발화");
		util.SWFsendPost_playStatus("좋아요 삭제", Chips_001, ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "좋아요한 취소 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "Flo 좋아요 리스트에서 삭제했습니다."));
		
		test.log(Status.INFO, "좋아요 버튼 활성화 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.좋아요Off_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0246")
	public void TC_0246_Chips_FLO_컨트롤러_다음_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 다음 곡 - 발화");
		util.SWFsendPost_playStatus("다음 곡", Chips_001, ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO제목), "거리의 악사"));
		
		test.log(Status.INFO, "가수 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "봄여름가을겨울"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0247")
	public void TC_0247_Chips_FLO_컨트롤러_이전_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 이전 곡 - 발화");
		util.SWFsendPost_playStatus("이전 곡", Chips_001, ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO제목), "Get Lucky"));
		
		test.log(Status.INFO, "가수 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "Daft Punk"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0248")
	public void TC_0248_Chips_FLO_컨트롤러_셔플_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "셔플 버튼 클릭");
		util.click(By.xpath(xPath.FLO랜덤버튼));
		
		test.log(Status.INFO, "셔플 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "셔플할게요."));
		
		test.log(Status.INFO, "랜덤 버튼 활성화 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.랜덤재생On_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0249")
	public void TC_0249_Chips_FLO_컨트롤러_다음_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 다음 곡 - 발화");
		util.SWFsendPost_playStatus("다음 곡", Chips_001, ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO제목), "거리의 악사"));
		
		test.log(Status.INFO, "가수 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "봄여름가을겨울"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0250")
	public void TC_0250_Chips_FLO_컨트롤러_이전_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 이전 곡 - 발화");
		util.SWFsendPost_playStatus("이전 곡", Chips_001, ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO제목), "Get Lucky"));
		
		test.log(Status.INFO, "가수 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "Daft Punk"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0251")
	public void TC_0251_Chips_FLO로그인_셔플해제_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "셔플 버튼 클릭");
		util.click(By.xpath(xPath.FLO랜덤버튼));
		
		test.log(Status.INFO, "셔플 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "셔플을 해제했어요"));
		
		test.log(Status.INFO, "랜덤 버튼 활성화 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.랜덤재생Off_web)));
		util.switchToNative();
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0252")
	public void TC_0252_Chips_FLO_컨트롤러_다음_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 다음 곡 - 발화");
		util.SWFsendPost_playStatus("다음 곡", Chips_001, ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO제목), "거리의 악사"));
		
		test.log(Status.INFO, "가수 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "봄여름가을겨울"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0253")
	public void TC_0253_Chips_FLO_컨트롤러_20초뒤로_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 20초 뒤로 이동 - 발화");
		util.SWFsendPost_playStatus("20초 뒤로 이동", Chips_001, ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "20초뒤로 이동 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "뮤직 서비스에서는 지원하지 않는 기능이에요."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0254")
	public void TC_0254_Chips_FLO_컨트롤러_15초앞으로_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 15초 앞으로 이동 - 발화");
		util.SWFsendPost_playStatus("15초 앞으로 이동", Chips_001, ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "15초 앞으로 이동 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "뮤직 서비스에서는 지원하지 않는 기능이에요."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0255")
	public void TC_0255_Chips_FLO_컨트롤러_종료_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 음악 종료 - 발화");
		util.SWFsendPost_playStatus("노래 꺼줘", Chips_001, ServerName, AccessToken, "music");
		test.log(Status.INFO, "W, 음악 종료 - 발화");
		util.SWFsendPost_playStatus("노래 꺼줘", Chips_001, ServerName, AccessToken, "music");
		Thread.sleep(5000);
		
		util.switchToNative();
		test.log(Status.INFO, "음악카드 종료되고 메인화면 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));
	}
	
	

}
