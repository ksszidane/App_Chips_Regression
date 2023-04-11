package Chips_010_금융;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 금융_01_삼성증권 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0953")
	public void TC_0953_Chips_삼성증권_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 삼성증권 도움말 - 발화");
		util.SWFsendPost("삼성증권 도움말", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "삼성증권 도움말 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.삼성증권도움말_set));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0954")
	public void TC_0954_Chips_삼성증권_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 삼성증권 도움말 - 발화");
		util.SWFsendPost("주식서비스 도움말", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "삼성증권 도움말 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.삼성증권도움말_set));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0955")
	public void TC_0955_Chips_삼성증권_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 증권 도움말 - 발화");
		util.SWFsendPost("증권 도움말", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "삼성증권 도움말 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.삼성증권도움말_set));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0956")
	public void TC_0956_Chips_삼성증권_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 삼성증권 - 발화");
		util.SWFsendPost("삼성증권", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "삼성증권 도움말 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.삼성증권도움말_set));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0957")
	public void TC_0957_Chips_삼성증권_TmapPOI발화_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 현대카드 - 발화");
		util.SWFsendPost("현대카드", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "삼성증권 USD 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.CHIPS_USD_set));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0958")
	public void TC_0958_Chips_삼성증권_TmapPOI발화_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 미래에셋 대우 - 발화");
		util.SWFsendPost("미래에셋 대우", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "삼성증권 USD 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Service, data.CHIPS_USD_set));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0959")
	public void TC_0959_1_Chips_삼성증권_종합주가지수_장전_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "W, 현재 주가지수 알려줘 - 발화");
			util.SWFsendPost("현재 주가지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "W, 현재 주가지수 알려줘 - 발화");
			util.SWFsendPost("현재 주가지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제는 증시 휴장일이었어요"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장전상태 Test Skip");
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장전상태 Test Skip");
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0959")
	public void TC_0959_2_Chips_삼성증권_종합주가지수_장중_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "W, 현재 주가지수 알려줘 - 발화");
			util.SWFsendPost("현재 주가지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장중 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "현재 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트를 기록 중 입니다."));
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장중상태 Test Skip");
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0959")
	public void TC_0959_3_Chips_삼성증권_종합주가지수_장종_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장종상태 Test Skip");
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "W, 현재 주가지수 알려줘 - 발화");
			util.SWFsendPost("현재 주가지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장종 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0960")
	public void TC_0960_1_Chips_삼성증권_종합주가지수_장전_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "W, 이 시각 주가지수 얼마야? - 발화");
			util.SWFsendPost("이 시각 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Service);
			Assert.assertTrue(tts.contains("어제 코스피는"));
			Assert.assertTrue(tts.contains("코스닥은"));
			Assert.assertTrue(tts.contains("포인트로 마감되었습니다."));
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "W, 이 시각 주가지수 얼마야? - 발화");
			util.SWFsendPost("이 시각 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제는 증시 휴장일이었어요"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장전상태 Test Skip");
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장전상태 Test Skip");
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0960")
	public void TC_0960_2_Chips_삼성증권_종합주가지수_장중_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "W, 이 시각 주가지수 얼마야? - 발화");
			util.SWFsendPost("이 시각 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장중 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "현재 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트를 기록 중 입니다."));
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장중상태 Test Skip");
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0960")
	public void TC_0960_3_Chips_삼성증권_종합주가지수_장종_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장종상태 Test Skip");
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "W, 이 시각 주가지수 얼마야? - 발화");
			util.SWFsendPost("이 시각 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장종 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0961")
	public void TC_0961_1_Chips_삼성증권_종합주가지수_장전_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "오늘날짜 계산");
		String 오늘날짜 = util.getKoreaDate();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "W, "+ 오늘날짜 +" 국내 주가지수 얼마야?- 발화");
			util.SWFsendPost(오늘날짜 +" 국내 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "W, "+ 오늘날짜 +" 국내 주가지수 얼마야?- 발화");
			util.SWFsendPost(오늘날짜 +" 국내 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제는 증시 휴장일이었어요"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장전상태 Test Skip");
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장전상태 Test Skip");
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0961")
	public void TC_0961_2_Chips_삼성증권_종합주가지수_장중_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "오늘날짜 계산");
		String 오늘날짜 = util.getKoreaDate();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "W, "+ 오늘날짜 +" 국내 주가지수 얼마야?- 발화");
			util.SWFsendPost(오늘날짜 +" 국내 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장중 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "현재 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트를 기록 중 입니다."));
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장중상태 Test Skip");
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0961")
	public void TC_0961_3_Chips_삼성증권_종합주가지수_장종_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "오늘날짜 계산");
		String 오늘날짜 = util.getKoreaDate();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장종상태 Test Skip");
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "W, "+ 오늘날짜 +" 국내 주가지수 얼마야?- 발화");
			util.SWFsendPost(오늘날짜 +" 국내 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장종 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0961")
	public void TC_0962_1_Chips_삼성증권_종합주가지수_장전_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "W, 코스피 지수 알려줘 - 발화");
			util.SWFsendPost("코스피 지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "W, 코스피 지수 알려줘 - 발화");
			util.SWFsendPost("코스피 지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제는 증시 휴장일이었어요"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장전상태 Test Skip");
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장전상태 Test Skip");
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0961")
	public void TC_0962_2_Chips_삼성증권_종합주가지수_장중_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "W, 코스피 지수 알려줘 - 발화");
			util.SWFsendPost("코스피 지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장중 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "현재 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트를 기록 중 입니다."));
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장중상태 Test Skip");
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0961")
	public void TC_0962_3_Chips_삼성증권_종합주가지수_장종_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장종상태 Test Skip");
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "W, 코스피 지수 알려줘 - 발화");
			util.SWFsendPost("코스피 지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "장종 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0963")
	public void TC_0963_Chips_삼성증권_종합주가지수_어제_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "오늘날짜 계산");
		String 어제요일 = util.getDayOfWeek(-1);
		
		if (어제요일 == "일") {
			test.log(Status.INFO, "W, 어제 종합주가지수 알려줘 - 발화");
			util.SWFsendPost("어제 종합주가지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "어제 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제는 증시 휴장일이었어요"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다"));
		} else {
			test.log(Status.INFO, "W, 어제 종합주가지수 알려줘 - 발화");
			util.SWFsendPost("어제 종합주가지수 알려줘", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "어제 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다"));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0963")
	public void TC_0964_Chips_삼성증권_종합주가지수_어제_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "어제날짜 계산");
		String 어제요일 = util.getDayOfWeek(-1);
		
		test.log(Status.INFO, "어제날짜 계산");
		String 어제날짜 = util.getChangePreviousDate(-1);
		
		if (어제요일 == "일") {
			test.log(Status.INFO, "W, "+ 어제날짜 +" 국내 주가지수 얼마야?- 발화");
			util.SWFsendPost(어제날짜 +" 국내 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "어제 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제는 증시 휴장일이었어요"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다"));
		} else {
			test.log(Status.INFO, "W, "+ 어제날짜 +" 국내 주가지수 얼마야?- 발화");
			util.SWFsendPost(어제날짜 +" 국내 주가지수 얼마야", Chips_001, ServerName, AccessToken);
			
			test.log(Status.INFO, "어제 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제 코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다"));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0965")
	public void TC_0965_Chips_삼성증권_종합주가지수_코스닥_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "W, 코스닥 지수 - 발화");
		util.SWFsendPost("코스닥 지수", Chips_001, ServerName, AccessToken);
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제 코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "장전 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제는 증시 휴장일이었어요"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "장중 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "현재 코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트를 기록 중 입니다."));
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "장종 주가지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘 코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다."));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0966")
	public void TC_0966_Chips_삼성증권_종합주가지수_코스닥_확인(Method method) throws Exception {
			
		test.log(Status.INFO, "어제날짜 계산");
		String 어제요일 = util.getDayOfWeek(-1);
		
		test.log(Status.INFO, "W, 어제 코스닥 지수 - 발화");
		util.SWFsendPost("어제 코스닥 지수", Chips_001, ServerName, AccessToken);
		
		if (어제요일 == "일") {
			test.log(Status.INFO, "어제 코스닥 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제는 증시 휴장일이었어요"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다"));
		} else {
			test.log(Status.INFO, "어제 코스닥 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "어제 코스닥은"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "코스피는"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트로 마감되었습니다"));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0969")
	public void TC_0969_Chips_삼성증권_미지원기간_과거_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 그저께 주가지수 알려줘 - 발화");
		util.SWFsendPost("그저께 주가지수 알려줘", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "가장 최근의 종합주가지수 정보만 알려드릴 수 있어요"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0970")
	public void TC_0970_Chips_삼성증권_미지원기간_과거_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 1월 3일 주가지수 알려줘 - 발화");
		util.SWFsendPost("1월 3일 주가지수 알려줘", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "가장 최근의 종합주가지수 정보만 알려드릴 수 있어요"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0971")
	public void TC_0971_Chips_삼성증권_미지원기간_과거_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 지난주 종합주가지수 얼마였지? - 발화");
		util.SWFsendPost("지난주 종합주가지수 얼마였지?", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "가장 최근의 종합주가지수 정보만 알려드릴 수 있어요"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0972")
	public void TC_0972_Chips_삼성증권_미지원기간_미래_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 내일 종합주가지수 얼마였지? - 발화");
		util.SWFsendPost("내일 종합주가지수 얼마였지?", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "가장 최근의 종합주가지수 정보만 알려드릴 수 있어요"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0973")
	public void TC_0973_Chips_삼성증권_미지원기간_미래_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 다음주 금요일 종합주가지수 얼마였지? - 발화");
		util.SWFsendPost("다음주 금요일 종합주가지수 얼마였지?", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "가장 최근의 종합주가지수 정보만 알려드릴 수 있어요"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0973")
	public void TC_0974_Chips_삼성증권_미지원기능_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 기관이 많이 거래한 종목 알려줘 - 발화");
		util.SWFsendPost("기관이 많이 거래한 종목 알려줘", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "외국인, 기관등의 매입, 매도를 구분해서 확인하면 종목을 알려 드릴수 있어요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0975")
	public void TC_0975_Chips_삼성증권_미지원기능_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 외국인이 많이 거래한 종목 알려줘 - 발화");
		util.SWFsendPost("외국인이 많이 거래한 종목 알려줘 ", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "외국인, 기관등의 매입, 매도를 구분해서 확인하면 종목을 알려 드릴수 있어요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0976")
	public void TC_0976_Chips_삼성증권_국외지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 해외 시황 알려줘. - 발화");
		util.SWFsendPost("해외 시황 알려줘", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "미국 다우지수는"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "나스닥은"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "일본 니케이지수는"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "중국 상해종합지수는"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "퍼센트"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트"));

		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0977")
	public void TC_0977_Chips_삼성증권_정국가지수_미국지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 미국 지수 알려줘 - 발화");
		util.SWFsendPost("미국 지수 알려줘", Chips_001, ServerName, AccessToken);
	
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "미국 다우지수는"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "나스닥은"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "퍼센트"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트"));
		
	}
	
	
	@Test(description = "칩스 리그레이션 TC : 실행_0978")
	public void TC_0978_Chips_삼성증권_특정국가지수_홍콩지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 홍콩 항셍지수 알려줘 - 발화");
		util.SWFsendPost("홍콩 항셍지수 알려줘", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "홍콩 항셍지수는"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "퍼센트"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0979")
	public void TC_0979_Chips_삼성증권_특정국가지수_일본지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 일본 지수 알려줘 - 발화");
		util.SWFsendPost("일본 지수 알려줘", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "일본 니케이 지수는"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "퍼센트"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0980")
	public void TC_0980_Chips_삼성증권_특정국가지수_대만지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 대만 시황 알려줘 - 발화");
		util.SWFsendPost("대만 시황 알려줘", Chips_001, ServerName, AccessToken);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "대만 가권 지수는"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "퍼센트"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "포인트"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0981")
	public void TC_0981_Chips_삼성증권_종목별주가_장중_상승_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "상승 종목 확인");
		String 상승종목 = util.Naver_Stock_Crawler_rise("상승");
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "W, " + 상승종목 + " 주식시세 알려줘 - 발화");
			util.SWFsendPost(상승종목 + " 주식시세 알려줘", Chips_001, ServerName, AccessToken);
			
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "현재 "+상승종목));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "퍼센트 상승한"));
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장중상태 Test Skip");
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0982")
	public void TC_0982_Chips_삼성증권_종목별주가_장중_하락_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "하락 종목 확인");
		String 하락종목 = util.Naver_Stock_Crawler_rise("하락");
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "W, " + 하락종목 + " 주식시세 알려줘 - 발화");
			util.SWFsendPost(하락종목 + " 주식시세 알려줘", Chips_001, ServerName, AccessToken);
			
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "현재 "+하락종목));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "퍼센트 하락한"));
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장중상태 Test Skip");
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0983")
	public void TC_0983_Chips_삼성증권_종목별주가_장중_보합_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "보합 종목 확인");
		String 보합종목 = util.Naver_Stock_Crawler_rise("보합");
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "W, " + 보합종목 + " 주식시세 알려줘 - 발화");
			util.SWFsendPost(보합종목 + " 주식시세 알려줘", Chips_001, ServerName, AccessToken);
			
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "현재 "+보합종목));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "주가는 보합인"));
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장중상태 Test Skip");
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0984")
	public void TC_0984_Chips_삼성증권_종목별주가_장중_정지_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "정지 종목 확인");
		String 정지종목 = util.Naver_Stock_Crawler_rise("정지");
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장중상태 Test Skip");
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "W, " + 정지종목 + " 주식시세 알려줘 - 발화");
			util.SWFsendPost(정지종목 + " 주식시세 알려줘", Chips_001, ServerName, AccessToken);

			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, 정지종목));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "거래정지 종목으로"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "직전 종가는"));
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장중상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장중상태 Test Skip");
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0985")
	public void TC_0985_Chips_삼성증권_종목별주가_장종_상승_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "상승 종목 확인");
		String 상승종목 = util.Naver_Stock_Crawler_rise("상승");
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태  Test Skip");
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태  Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장종상태  Test Skip");
			throw new SkipException("현재 [장중]상태로 장종상태 Test Skip");
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "W, " + 상승종목 + " 주식시세 알려줘 - 발화");
			util.SWFsendPost(상승종목 + " 주식시세 알려줘", Chips_001, ServerName, AccessToken);
			
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘 "+상승종목));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "퍼센트 상승한"));
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0986")
	public void TC_0986_Chips_삼성증권_종목별주가_장종_하락_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "하락 종목 확인");
		String 하락종목 = util.Naver_Stock_Crawler_rise("하락");
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장종상태 Test Skip");
	
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "W, " + 하락종목 + " 주식시세 알려줘 - 발화");
			util.SWFsendPost(하락종목 + " 주식시세 알려줘", Chips_001, ServerName, AccessToken);
		
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘 "+하락종목));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "퍼센트 하락한"));
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0987")
	public void TC_0987_Chips_삼성증권_종목별주가_장종_보합_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "보합 종목 확인");
		String 보합종목 = util.Naver_Stock_Crawler_rise("보합");
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장종상태 Test Skip");
		
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "W, " + 보합종목 + " 주식시세 알려줘 - 발화");
			util.SWFsendPost(보합종목 + " 주식시세 알려줘", Chips_001, ServerName, AccessToken);
			
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘 "+보합종목));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "보합"));
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0988")
	public void TC_0988_Chips_삼성증권_종목별주가_장종_정지_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		test.log(Status.INFO, "정지 종목 확인");
		String 정지종목 = util.Naver_Stock_Crawler_rise("정지");
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장종상태 Test Skip");
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장중]상태로 장종상태 Test Skip");
			throw new SkipException("현재 [장중]상태로 장종상태 Test Skip");
			
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "W, " + 정지종목 + " 주식시세 알려줘 - 발화");
			util.SWFsendPost(정지종목 + " 주식시세 알려줘", Chips_001, ServerName, AccessToken);
			
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, 정지종목));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "거래정지 종목으로"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "직전 종가는"));
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0989")
	public void TC_0989_Chips_삼성증권_종목별주가_장전_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "W, SK텔레콤 주식시세 알려줘 - 발화");
			util.SWFsendPost("SK텔레콤 주식시세 알려줘", Chips_001, ServerName, AccessToken);

			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "SK텔레콤의 어제 종가는"));
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "현재 [장전]상태로 장전-전일휴일 확인 Test Skip");
			throw new SkipException("현재 [장전]상태로 장전-전일휴일 확인 Test Skip");
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장전]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장전상태 Test Skip");
			
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장전상태 Test Skip");
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0990")
	public void TC_0990_Chips_삼성증권_종목별주가_장전_전일휴일_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재시간 기준 장전/장중/장종 확인");
		String 장상태 = util.StockTimeTable();
		
		if (장상태 == "장전") {
			test.log(Status.INFO, "현재 [장전]상태로 장전-전일평일 확인 Test Skip");
			throw new SkipException("현재 [장전]상태로 장전-전일평일 확인 Test Skip");
			
		} else if (장상태 == "장전" && util.getDayOfWeek(0) == "월") {
			test.log(Status.INFO, "W, SK텔레콤 주식시세 알려줘 - 발화");
			util.SWFsendPost("SK텔레콤 주식시세 알려줘", Chips_001, ServerName, AccessToken);
	
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "금요일 기준"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "SK텔레콤의 종가는"));
		
		} else if (장상태 == "장중") {
			test.log(Status.INFO, "현재 [장전]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장전]상태로 장전상태 Test Skip");
			
		} else if (장상태 == "장종") {
			test.log(Status.INFO, "현재 [장종]상태로 장전상태 Test Skip");
			throw new SkipException("현재 [장종]상태로 장전상태 Test Skip");
		
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0991")
	public void TC_0991_Chips_삼성증권_종목별주가_휴장일_확인(Method method) throws Exception {
		
		if (util.getDayOfWeek(0) == "토") {
			test.log(Status.INFO, "W, SK텔레콤 주식시세 알려줘 - 발화");
			util.SWFsendPost("SK텔레콤 주식시세 알려줘", Chips_001, ServerName, AccessToken);
			
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘은 증시 휴장일입니다"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "금요일 기준"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "SK텔레콤의 종가는"));
			
		} else if (util.getDayOfWeek(0) == "일") {
			test.log(Status.INFO, "W, SK텔레콤 주식시세 알려줘 - 발화");
			util.SWFsendPost("SK텔레콤 주식시세 알려줘", Chips_001, ServerName, AccessToken);
			
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "오늘은 증시 휴장일입니다"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "금요일 기준"));
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "SK텔레콤의 종가는"));
		
		} else  {
			test.log(Status.INFO, "현재 휴장일 상태가 아님 Test Skip");
			throw new SkipException("현재 휴장일 상태가 아님 Test Skip");
		}
			
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0992")
	public void TC_0992_Chips_삼성증권_종목추천_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 삼성증권에서 오늘 추천하는 종목이 뭐야? - 발화");
		util.SWFsendPost("삼성증권에서 오늘 추천하는 종목이 뭐야", Chips_001, ServerName, AccessToken);
			
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "삼성증권에서"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "Premium List ten으로 선정한 종목중에"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "주식은 원금손실의 위험이 있으니, 투자 전 자세한 설명을 듣고 결정을 내리시기 바래요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0993")
	public void TC_0993_Chips_삼성증권_종목추천_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 주식 추천해줘 - 발화");
		util.SWFsendPost("주식 추천해줘", Chips_001, ServerName, AccessToken);
			
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "삼성증권에서"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "Premium List ten으로 선정한 종목중에"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "주식은 원금손실의 위험이 있으니, 투자 전 자세한 설명을 듣고 결정을 내리시기 바래요."));
		
	}

}
