package Chips_000_xPath;

public class xPath {
	
	//메인화면 보이스크롬 상단의 Chips
	public static String chips_1st = "//android.support.v7.widget.RecyclerView/android.view.ViewGroup";
	
	//실행 후 첫번째 화면 접근권한허용버튼
	public static String 접근권한허용버튼 = "//android.widget.Button[@text='접근 권한 허용']";
	
	//간편로그인 첫번째, 두번째
	public static String 간편로그인_1st = "//ul[@class='account-list']/li[1]";
	public static String 간편로그인_2st = "//ul[@class='account-list']/li[2]";
	
	//최초 보이스크롬의 코치버튼
	public static String 코치마크문구 = "//android.support.v4.widget.g/android.view.ViewGroup/android.view.ViewGroup"
			+ "/android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.TextView";
	
	//두번쨰카드닫기버튼
	public static String 두번쨰카드닫기 = "//android.support.v7.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView";
	
	//플로카드
	public static String FLO카드타이틀 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View/android.view.View";
	public static String FLO카드앨범아트 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.view.View[1]";
	public static String FLO미리듣기뱃지_web = "//span[@class='badge']";
	public static String FLO제목 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.view.View[1]";
	public static String FLO아티스트 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.view.View[2]";
	public static String FLO프로그레스바 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.view.View[3]";
	public static String FLO반복버튼 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.Button[4]";
	public static String FLO랜덤버튼 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.Button[5]";
	public static String FLO현재재생시간 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.TextView[2]";
	public static String FLO재생곡전체시간 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.TextView[3]";
	public static String FLO재생버튼 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.Button[2]"; //재생버튼 focuse false
	public static String FLO일시정지버튼 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.Button[2]"; //일시정지버튼 focuse true
	public static String FLO이전버튼 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.Button[1]";
	public static String FLO다음버튼 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.Button[3]";
	
	//날씨카드 
	public static String 날씨카드타이틀 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View/android.view.View";
	
	//미디어컨트롤러 
	public static String 재생버튼_web = "//button[@class='btn play']";
	public static String 일시정지버튼_web = "//button[@class='btn pause']";
	public static String 이전버튼_web = "//button[@class='btn prev']";
	public static String 다음버튼_web = "//button[@class='btn next']";
	
	//webview
	public static String 좋아요On_web = "//button[@class='btn favorite on']";
	public static String 좋아요Off_web = "//button[@class='btn favorite on']";
	public static String 전체반복_web = "//button[@class='btn repeat all']";
	public static String 한곡반복_web = "//button[@class='btn repeat one']";
	public static String 반복해제_web = "//button[@class='btn repeat']";
	public static String 랜덤재생On_web = "//button[@class='btn random on']";
	public static String 랜덤재생Off_web = "//button[@class='btn random']";

}
