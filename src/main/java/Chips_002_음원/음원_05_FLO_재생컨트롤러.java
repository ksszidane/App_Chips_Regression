package Chips_002_음원;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 음원_05_FLO_재생컨트롤러 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0239")
	public void TC_0239_Chips_FLO_컨트롤러_음악재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 플레이리스트 틀어줘 - 발화");
		util.SWFsendPost("플레이리스트 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "플레이리스트 노래 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("Flo에서 테스트 1 내 리스트"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "내 리스트");
		
		test.log(Status.INFO, "앨범아트 확인");
		boolean 앨범아트 = util.isElementPresent(By.xpath(xPath.FLO카드앨범아트));
		Assert.assertTrue(앨범아트);
		
		test.log(Status.INFO, "곡명 확인");
		boolean 곡명 = util.isElementPresent(By.xpath(xPath.FLO제목));
		Assert.assertTrue(곡명);
		
		test.log(Status.INFO, "아티스트 확인");
		boolean 아티스트 = util.isElementPresent(By.xpath(xPath.FLO아티스트));
		Assert.assertTrue(아티스트);
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		String 일시정지 = util.getText(By.xpath(xPath.FLO일시정지버튼));
		Assert.assertEquals(일시정지, "재생/일시정지");
		
		boolean 이전 = util.isElementPresent(By.xpath(xPath.FLO이전버튼));
		Assert.assertTrue(이전);

		boolean 다음 = util.isElementPresent(By.xpath(xPath.FLO다음버튼));
		Assert.assertTrue(다음);
	
		test.log(Status.INFO, "프로그래스 바 확인");
		boolean progress = util.isElementPresent(By.xpath(xPath.FLO프로그레스바));
		Assert.assertTrue(progress);
		
		test.log(Status.INFO, "반복 버튼 확인");
		boolean 반복 = util.isElementPresent(By.xpath(xPath.FLO반복버튼));
		Assert.assertTrue(반복);
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		boolean 랜덤 = util.isElementPresent(By.xpath(xPath.FLO랜덤버튼));
		Assert.assertTrue(랜덤);
		
		test.log(Status.INFO, "재생시간 확인");
		boolean 재생시간 = util.isElementPresent(By.xpath(xPath.FLO재생곡전체시간));
		Assert.assertTrue(재생시간);
		util.context("NATIVE_APP");
		
		boolean chipListView = util.isElementPresent(By.id("chipListView"));
		Assert.assertTrue(chipListView);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0240")
	public void TC_0240_Chips_FLO_컨트롤러_일시정지_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost("일시정지", ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 재생버튼 = util.isElementPresent(By.xpath(xPath.재생버튼_web));
		Assert.assertTrue(재생버튼);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0241")
	public void TC_0241_Chips_FLO_컨트롤러_일시정지취소_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 일시정지 취소 - 발화");
		util.SWFsendPost("일시정지 취소", ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 재생버튼 = util.isElementPresent(By.xpath(xPath.일시정지버튼_web));
		Assert.assertTrue(재생버튼);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0242")
	public void TC_0242_Chips_FLO_컨트롤러_일시정지_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost("일시정지", ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 재생버튼 = util.isElementPresent(By.xpath(xPath.재생버튼_web));
		Assert.assertTrue(재생버튼);


	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0243")
	public void TC_0243_Chips_FLO_컨트롤러_음악재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 내 리스트 재생 - 발화");
		util.SWFsendPost("내 리스트 재생", ServerName, AccessToken);
		
		test.log(Status.INFO, "플레이리스트 노래 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("Flo에서 테스트 1 내 리스트"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "내 리스트");
		
		test.log(Status.INFO, "앨범아트 확인");
		boolean 앨범아트 = util.isElementPresent(By.xpath(xPath.FLO카드앨범아트));
		Assert.assertTrue(앨범아트);
		
		test.log(Status.INFO, "곡명 확인");
		boolean 곡명 = util.isElementPresent(By.xpath(xPath.FLO제목));
		Assert.assertTrue(곡명);
		
		test.log(Status.INFO, "아티스트 확인");
		boolean 아티스트 = util.isElementPresent(By.xpath(xPath.FLO아티스트));
		Assert.assertTrue(아티스트);
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		String 일시정지 = util.getText(By.xpath(xPath.FLO일시정지버튼));
		Assert.assertEquals(일시정지, "재생/일시정지");
		
		boolean 이전 = util.isElementPresent(By.xpath(xPath.FLO이전버튼));
		Assert.assertTrue(이전);

		boolean 다음 = util.isElementPresent(By.xpath(xPath.FLO다음버튼));
		Assert.assertTrue(다음);
	
		test.log(Status.INFO, "프로그래스 바 확인");
		boolean progress = util.isElementPresent(By.xpath(xPath.FLO프로그레스바));
		Assert.assertTrue(progress);
		
		test.log(Status.INFO, "반복 버튼 확인");
		boolean 반복 = util.isElementPresent(By.xpath(xPath.FLO반복버튼));
		Assert.assertTrue(반복);
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		boolean 랜덤 = util.isElementPresent(By.xpath(xPath.FLO랜덤버튼));
		Assert.assertTrue(랜덤);
		
		test.log(Status.INFO, "재생시간 확인");
		boolean 재생시간 = util.isElementPresent(By.xpath(xPath.FLO재생곡전체시간));
		Assert.assertTrue(재생시간);
		util.context("NATIVE_APP");
		
		boolean chipListView = util.isElementPresent(By.id("chipListView"));
		Assert.assertTrue(chipListView);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0244")
	public void TC_0244_Chips_FLO_컨트롤러_좋아요_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "좋아요 버튼 클릭 (미디어재생 상태 체크 불가로 발화테스트는 불가)");
		util.context("NATIVE_APP");
		util.click(By.xpath(xPath.FLO좋아요));
		
		test.log(Status.INFO, "좋아요 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("당신의 Flo 좋아요 리스트에 담았습니다."));
		
		test.log(Status.INFO, "좋아요 버튼 활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		boolean 좋아요버튼활성화 = util.isElementPresent(By.xpath(xPath.좋아요On_web));
		Assert.assertTrue(좋아요버튼활성화);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0245")
	public void TC_0245_Chips_FLO_컨트롤러_좋아요취소_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "좋아요 버튼 클릭 (미디어재생 상태 체크 불가로 발화테스트는 불가)");
		util.context("NATIVE_APP");
		util.click(By.xpath(xPath.FLO좋아요));
		
		test.log(Status.INFO, "좋아요한 취소 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("Flo 좋아요 리스트에서 삭제했습니다."));
		
		test.log(Status.INFO, "좋아요 버튼 활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 좋아요버튼비활성화 = util.isElementPresent(By.xpath(xPath.좋아요Off_web));
		Assert.assertTrue(좋아요버튼비활성화);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0246")
	public void TC_0246_Chips_FLO_컨트롤러_다음_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 다음 곡 - 발화");
		util.SWFsendPost("다음 곡", ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		String 제목 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertTrue(제목.contains("거리의 악사"));
		
		test.log(Status.INFO, "가수 확인");
		String 가수 = util.getText(By.xpath(xPath.FLO아티스트));
		Assert.assertTrue(가수.contains("봄여름가을겨울"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0247")
	public void TC_0247_Chips_FLO_컨트롤러_이전_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 이전 곡 - 발화");
		util.SWFsendPost("이전 곡", ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		String 제목 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertTrue(제목.contains("Get Lucky"));
		
		test.log(Status.INFO, "가수 확인");
		String 가수 = util.getText(By.xpath(xPath.FLO아티스트));
		Assert.assertTrue(가수.contains("Daft Punk"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0248")
	public void TC_0248_Chips_FLO_컨트롤러_셔플_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "셔플 버튼 클릭");
		util.click(By.xpath(xPath.FLO랜덤버튼));
		
		test.log(Status.INFO, "셔플 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("셔플할게요."));
		
		test.log(Status.INFO, "랜덤 버튼 활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 랜덤재생 = util.isElementPresent(By.xpath(xPath.랜덤재생On_web));
		Assert.assertTrue(랜덤재생);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0249")
	public void TC_0249_Chips_FLO_컨트롤러_다음_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 다음 곡 - 발화");
		util.SWFsendPost("다음 곡", ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		String 제목 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertTrue(제목.contains("거리의 악사"));
		
		test.log(Status.INFO, "가수 확인");
		String 가수 = util.getText(By.xpath(xPath.FLO아티스트));
		Assert.assertTrue(가수.contains("봄여름가을겨울"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0250")
	public void TC_0250_Chips_FLO_컨트롤러_이전_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 이전 곡 - 발화");
		util.SWFsendPost("이전 곡", ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		String 제목 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertTrue(제목.contains("Get Lucky"));
		
		test.log(Status.INFO, "가수 확인");
		String 가수 = util.getText(By.xpath(xPath.FLO아티스트));
		Assert.assertTrue(가수.contains("Daft Punk"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0251")
	public void TC_0251_Chips_FLO로그인_셔플해제_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "셔플 버튼 클릭");
		util.click(By.xpath(xPath.FLO랜덤버튼));
		
		test.log(Status.INFO, "셔플 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("셔플을 해제했어요"));
		
		test.log(Status.INFO, "랜덤 버튼 활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 랜덤재생해제 = util.isElementPresent(By.xpath(xPath.랜덤재생Off_web));
		Assert.assertTrue(랜덤재생해제);
		util.context("NATIVE_APP");
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0252")
	public void TC_0252_Chips_FLO_컨트롤러_다음_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 다음 곡 - 발화");
		util.SWFsendPost("다음 곡", ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "제목 확인");
		String 제목 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertTrue(제목.contains("거리의 악사"));
		
		test.log(Status.INFO, "가수 확인");
		String 가수 = util.getText(By.xpath(xPath.FLO아티스트));
		Assert.assertTrue(가수.contains("봄여름가을겨울"));

	}
	
	

}
