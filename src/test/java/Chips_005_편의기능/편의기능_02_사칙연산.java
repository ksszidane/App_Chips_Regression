package Chips_005_편의기능;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 편의기능_02_사칙연산 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0548")
	public void TC_0548_Chips_사칙연산_도메인안내_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 계산해줘 - 발화");
		util.SWFsendPost("계산해줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "계산이 필요할 때, 칠 더하기 팔은 뭐야? 와 같이 말씀해 주세요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0549")
	public void TC_0549_Chips_사칙연산_더하기_도메인안내_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 더하기- 발화");
		util.SWFsendPost("더하기", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "계산이 필요할 때, 칠 더하기 팔은 뭐야? 와 같이 말씀해 주세요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0550")
	public void TC_0550_Chips_사칙연산_빼기_도메인안내_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 빼기 - 발화");
		util.SWFsendPost("빼기", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "계산이 필요할 때, 칠 더하기 팔은 뭐야? 와 같이 말씀해 주세요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0551")
	public void TC_0551_Chips_사칙연산_곱하기_도메인안내_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 곱하기 - 발화");
		util.SWFsendPost("곱하기", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "계산이 필요할 때, 칠 더하기 팔은 뭐야? 와 같이 말씀해 주세요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0552")
	public void TC_0552_Chips_사칙연산_나누기_도메인안내_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 나누기 - 발화");
		util.SWFsendPost("나누기", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "계산이 필요할 때, 칠 더하기 팔은 뭐야? 와 같이 말씀해 주세요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0553")
	public void TC_0553_Chips_사칙연산_덧셈1_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 15 더하기 94 - 발화");
		util.SWFsendPost("15 더하기 94", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "15 더하기 94는, 109 입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0554")
	public void TC_0554_Chips_사칙연산_덧셈2_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 12 플러스 3은? - 발화");
		util.SWFsendPost("12 플러스 3은?", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "12 더하기 3은, 15 입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0555")
	public void TC_0555_Chips_사칙연산_뺄셈1_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 85 빼기 13 - 발화");
		util.SWFsendPost("85 빼기 13", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "85 빼기 13은, 72 입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0556")
	public void TC_0556_Chips_사칙연산_뺄셈2_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 12 마이너스 3은? - 발화");
		util.SWFsendPost("12 마이너스 3은?", ServerName, AccessToken);

		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "12 빼기 3은, 9 입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0557")
	public void TC_0557_Chips_사칙연산_나눗셈1_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 10 나누기 3 - 발화");
		util.SWFsendPost("10 나누기 3", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "10 나누기 3은, 3.33333 입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0558")
	public void TC_0558_Chips_사칙연산_나눗셈2_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 10 나누기 0 - 발화");
		util.SWFsendPost("10 나누기 0", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "10 나누기 0은, 무한대 입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0559")
	public void TC_0559_Chips_사칙연산_곱셈1_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 14 곱하기 14 - 발화");
		util.SWFsendPost("14 곱하기 14", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "14 곱하기 14는, 196 입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0560")
	public void TC_0560_Chips_사칙연산_곱셈2_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 5 곱하기 0은? - 발화");
		util.SWFsendPost("5 곱하기 0은?", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 사용 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "5 곱하기 0은, 0 입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0561")
	public void TC_0561_Chips_사칙연산_미연산값_분수형태_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 5분의 1 더하기 3은? - 발화");
		util.SWFsendPost("5분의 1 더하기 3은?", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 미연산값 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "일~경, 미만의 자연수만 말씀해 주세요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0562")
	public void TC_0562_Chips_사칙연산_미연산값_마이너스값_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 마이너스5 더하기 3은? - 발화");
		util.SWFsendPost("마이너스5 더하기 3은?", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 미연산값 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "일~경, 미만의 자연수만 말씀해 주세요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0563")
	public void TC_0563_Chips_사칙연산_미연산값_3항이상연산자두개_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 3 더하기 2 곱하기 7은? - 발화");
		util.SWFsendPost("3 더하기 2 곱하기 7은?", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 미연산값 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "두 개의 숫자만, 계산할 수 있어요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0564")
	public void TC_0564_Chips_사칙연산_미연산값_3항이상연산자두개2_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 2 곱하기 3 곱하기 마이너스9는? - 발화");
		util.SWFsendPost("2 곱하기 3 곱하기 마이너스9는?", ServerName, AccessToken);
		
		test.log(Status.INFO, "사칙연산 미연산값 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "두 개의 숫자만, 계산할 수 있어요."));
	}

}
