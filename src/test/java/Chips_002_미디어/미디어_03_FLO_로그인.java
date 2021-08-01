package Chips_002_미디어;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_03_FLO_로그인 extends Chips_TestCase {
	
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
	    util.click(By.xpath(xPath.간편로그인_1st));
	    
	    test.log(Status.INFO, "NATIVE로 화면 확인");
	    util.context("NATIVE_APP");
	    util.ProgressBar_Loading();
	    
	    accessToken얻기();
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0220")
	public void TC_0220_Chips_FLO로그인_아티스트노래_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W,  빅뱅 노래 틀어줘 - 발화");
		util.SWFsendPost("빅뱅 노래 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "아티스트 노래 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("BIGBANG의"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "FLO");
		
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0221")
	public void TC_0221_Chips_FLO로그인_아티스트노래_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W,  서태지 음악 재생 - 발화");
		util.SWFsendPost("서태지 음악 재생", ServerName, AccessToken);
		
		test.log(Status.INFO, "아티스트 노래 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("서태지"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "FLO");
		
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0222")
	public void TC_0222_Chips_FLO로그인_최신음악_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 뮤직메이트에서 최신 곡 틀어줘- 발화");
		util.SWFsendPost("뮤직메이트에서 최신 곡 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "최신음악 노래 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("최신 음악을"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "최신 음악");
		
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0223")
	public void TC_0223_Chips_FLO로그인_실시간차트_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 인기 플로 노래 틀어줘 - 발화");
		util.SWFsendPost("인기 플로 노래 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "인기음악 노래 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("Flo 실시간 차트를"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "실시간 차트");
		
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0224")
	public void TC_0224_Chips_FLO로그인_가수곡_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, jeebanoff의 LEAVE ME 틀어줘 - 발화");
		util.SWFsendPost("jeebanoff의 LEAVE ME 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "가수+곡 노래 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("jeebanoff (지바노프)의 LEAVE ME. "));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "FLO");
		
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0225")
	public void TC_0225_Chips_FLO로그인_좋아요노래_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 좋아요 음악 틀어 - 발화");
		util.SWFsendPost("좋아요 음악 틀어", ServerName, AccessToken);
		
		test.log(Status.INFO, " 좋아요 노래 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("당신이 Flo에서 좋아요 하신 음악을"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "좋아요 한 음악");
		
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
		
		test.log(Status.INFO, "좋아요 버튼 활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 좋아요버튼활성화 = util.isElementPresent(By.xpath(xPath.좋아요On_web));
		Assert.assertTrue(좋아요버튼활성화);
		util.context("NATIVE_APP");
		
		test.log(Status.INFO, "재생시간 확인");
		boolean 재생시간 = util.isElementPresent(By.xpath(xPath.FLO재생곡전체시간));
		Assert.assertTrue(재생시간);
		
		boolean chipListView = util.isElementPresent(By.id("chipListView"));
		Assert.assertTrue(chipListView);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0226")
	public void TC_0226_Chips_FLO로그인_플레이리스트_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 플레이리스트 틀어줘 - 발화");
		util.SWFsendPost("플레이리스트 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "최신음악 노래 재생 TTS 확인");
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0227")
	public void TC_0227_Chips_FLO로그인_최근들은음악_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 최근에 들은 음악 틀어줘 - 발화");
		util.SWFsendPost("최근에 들은 음악 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "최신음악 노래 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("최근에 Flo에서 들은 음악을"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "최근들은 음악");
		
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0228")
	public void TC_0228_Chips_FLO로그인_셔플해줘_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, jeebanoff 노래 들려줘 - 발화");
		util.SWFsendPost("jeebanoff 노래 들려줘", ServerName, AccessToken);
		Thread.sleep(10000);
		
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0229")
	public void TC_0229_Chips_FLO로그인_셔플해제_확인(Method method) throws Exception {
		
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

}
