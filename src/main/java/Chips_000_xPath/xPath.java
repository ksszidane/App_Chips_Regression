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
	public static String FLO좋아요 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.Button[6]";
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
	public static String 좋아요Off_web = "//button[@class='btn favorite']";
	public static String 전체반복_web = "//button[@class='btn repeat all']";
	public static String 한곡반복_web = "//button[@class='btn repeat one']";
	public static String 반복해제_web = "//button[@class='btn repeat']";
	public static String 랜덤재생On_web = "//button[@class='btn random on']";
	public static String 랜덤재생Off_web = "//button[@class='btn random']";

	//url
	public static String Webview_URL = "rtg-template.sktnugu.com/view";
	
	public static String 이미지커버src = "//div[@class='player__detail__cover asmr']/img";
	
	//라디오카드
	public static String 라디오타이틀 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[1]/android.view.View";
	public static String 라디오채널목록 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.ListView";
	public static String 라디오재생버튼_1st ="//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.ListView/android.view.View[1]/android.widget.Button";
	
	//라디오카드_web (즐겨찾기 없을때 xpath)
	public static String 라디오타이틀_web = "//span[@class='txt']";
	public static String 라디오리스트_web = "//ul[@class='player__list']";
	public static String sbs파워FM_타이틀_web = "//ul[@class='player__list']/li[1]/div/div[1]/img[@alt='SBS']";
	public static String sbs파워FM_일시정지_web = "//ul[@class='player__list']/li[1]/div/div[2]/button[@class='btn pause']";
	public static String sbs파워FM_재생_web = "//ul[@class='player__list']/li[1]/div/div[2]/button[@class='btn play']";
	public static String tbn강원_타이틀_web = "//ul[@class='player__list']/li[2]/div/div[1]/img[@alt='TBN']";
	public static String tbn강원_일시정지_web = "//ul[@class='player__list']/li[2]/div/div[2]/button[@class='btn pause']";
	public static String tbn강원_재생_web = "//ul[@class='player__list']/li[2]/div/div[2]/button[@class='btn play']";
	public static String tbn경남_타이틀_web = "//ul[@class='player__list']/li[3]/div/div[1]/img[@alt='TBN']";
	public static String tbn경남_일시정지_web = "//ul[@class='player__list']/li[3]/div/div[2]/button[@class='btn pause']";
	public static String tbn경남_재생_web = "//ul[@class='player__list']/li[3]/div/div[2]/button[@class='btn play']";
	public static String tbn경북_타이틀_web = "//ul[@class='player__list']/li[4]/div/div[1]/img[@alt='TBN']";
	public static String tbn경북_일시정지_web = "//ul[@class='player__list']/li[4]/div/div[2]/button[@class='btn pause']";
	public static String tbn경북_재생_web = "//ul[@class='player__list']/li[4]/div/div[2]/button[@class='btn play']";
	public static String tbn경인_타이틀_web = "//ul[@class='player__list']/li[5]/div/div[1]/img[@alt='TBN']";
	public static String tbn경인_일시정지_web = "//ul[@class='player__list']/li[5]/div/div[2]/button[@class='btn pause']";
	public static String tbn경인_재생_web = "//ul[@class='player__list']/li[5]/div/div[2]/button[@class='btn play']";
	public static String tbn광주_타이틀_web = "//ul[@class='player__list']/li[6]/div/div[1]/img[@alt='TBN']";
	public static String tbn광주_일시정지_web = "//ul[@class='player__list']/li[6]/div/div[2]/button[@class='btn pause']";
	public static String tbn광주_재생_web = "//ul[@class='player__list']/li[6]/div/div[2]/button[@class='btn play']";
	public static String tbn대구_타이틀_web = "//ul[@class='player__list']/li[7]/div/div[1]/img[@alt='TBN']";
	public static String tbn대구_일시정지_web = "//ul[@class='player__list']/li[7]/div/div[2]/button[@class='btn pause']";
	public static String tbn대구_재생_web = "//ul[@class='player__list']/li[7]/div/div[2]/button[@class='btn play']";
	public static String tbn대전_타이틀_web = "//ul[@class='player__list']/li[8]/div/div[1]/img[@alt='TBN']";
	public static String tbn대전_일시정지_web = "//ul[@class='player__list']/li[8]/div/div[2]/button[@class='btn pause']";
	public static String tbn대전_재생_web = "//ul[@class='player__list']/li[8]/div/div[2]/button[@class='btn play']";
	public static String tbn부산_타이틀_web = "//ul[@class='player__list']/li[9]/div/div[1]/img[@alt='TBN']";
	public static String tbn부산_일시정지_web = "//ul[@class='player__list']/li[9]/div/div[2]/button[@class='btn pause']";
	public static String tbn부산_재생_web = "//ul[@class='player__list']/li[9]/div/div[2]/button[@class='btn play']";
	public static String tbn울산_타이틀_web = "//ul[@class='player__list']/li[10]/div/div[1]/img[@alt='TBN']";
	public static String tbn울산_일시정지_web = "//ul[@class='player__list']/li[10]/div/div[2]/button[@class='btn pause']";
	public static String tbn울산_재생_web = "//ul[@class='player__list']/li[10]/div/div[2]/button[@class='btn play']";
	public static String tbn전북_타이틀_web = "//ul[@class='player__list']/li[11]/div/div[1]/img[@alt='TBN']";
	public static String tbn전북_일시정지_web = "//ul[@class='player__list']/li[11]/div/div[2]/button[@class='btn pause']";
	public static String tbn전북_재생_web = "//ul[@class='player__list']/li[11]/div/div[2]/button[@class='btn play']";
	public static String tbn제주_타이틀_web = "//ul[@class='player__list']/li[12]/div/div[1]/img[@alt='TBN']";
	public static String tbn제주_일시정지_web = "//ul[@class='player__list']/li[12]/div/div[2]/button[@class='btn pause']";
	public static String tbn제주_재생_web = "//ul[@class='player__list']/li[12]/div/div[2]/button[@class='btn play']";
	public static String TBSeFM_타이틀_web = "//ul[@class='player__list']/li[13]/div/div[1]/img[@alt='TBS']";
	public static String TBSeFM_일시정지_web = "//ul[@class='player__list']/li[13]/div/div[2]/button[@class='btn pause']";
	public static String TBSeFM_재생_web = "//ul[@class='player__list']/li[13]/div/div[2]/button[@class='btn play']";
	public static String TBSFM_타이틀_web = "//ul[@class='player__list']/li[14]/div/div[1]/img[@alt='TBS']";
	public static String TBSFM_일시정지_web = "//ul[@class='player__list']/li[14]/div/div[2]/button[@class='btn pause']";
	public static String TBSFM_재생_web = "//ul[@class='player__list']/li[14]/div/div[2]/button[@class='btn play']";
	public static String 극동방송_타이틀_web = "//ul[@class='player__list']/li[15]/div/div[1]/img[@alt='극동방송']";
	public static String 극동방송_일시정지_web = "//ul[@class='player__list']/li[15]/div/div[2]/button[@class='btn pause']";
	public static String 극동방송_재생_web = "//ul[@class='player__list']/li[15]/div/div[2]/button[@class='btn play']";
	public static String 아리랑FM_타이틀_web = "//ul[@class='player__list']/li[16]/div/div[1]/img[@alt='Arirang']";
	public static String 아리랑FM_일시정지_web = "//ul[@class='player__list']/li[16]/div/div[2]/button[@class='btn pause']";
	public static String 아리랑FM_재생_web = "//ul[@class='player__list']/li[16]/div/div[2]/button[@class='btn play']";
	public static String 불교방송_타이틀_web = "//ul[@class='player__list']/li[17]/div/div[1]/img[@alt='BBS 불교방송']";
	public static String 불교방송_일시정지_web = "//ul[@class='player__list']/li[17]/div/div[2]/button[@class='btn pause']";
	public static String 불교방송_재생_web = "//ul[@class='player__list']/li[17]/div/div[2]/button[@class='btn play']";
	public static String CBS음악FM_타이틀_web = "//ul[@class='player__list']/li[18]/div/div[1]/img[@alt='CBS']";
	public static String CBS음악FM_일시정지_web = "//ul[@class='player__list']/li[18]/div/div[2]/button[@class='btn pause']";
	public static String CBS음악FM_재생_web = "//ul[@class='player__list']/li[18]/div/div[2]/button[@class='btn play']";
	public static String CBS표준FM_타이틀_web = "//ul[@class='player__list']/li[19]/div/div[1]/img[@alt='CBS']";
	public static String CBS표준FM_일시정지_web = "//ul[@class='player__list']/li[19]/div/div[2]/button[@class='btn pause']";
	public static String CBS표준FM_재생_web = "//ul[@class='player__list']/li[19]/div/div[2]/button[@class='btn play']";
	public static String KBS1_타이틀_web = "//ul[@class='player__list']/li[20]/div/div[1]/img[@alt='KBS']";
	public static String KBS1_일시정지_web = "//ul[@class='player__list']/li[20]/div/div[2]/button[@class='btn pause']";
	public static String KBS1_재생_web = "//ul[@class='player__list']/li[20]/div/div[2]/button[@class='btn play']";
	public static String KBS2_타이틀_web = "//ul[@class='player__list']/li[21]/div/div[1]/img[@alt='KBS']";
	public static String KBS2_일시정지_web = "//ul[@class='player__list']/li[21]/div/div[2]/button[@class='btn pause']";
	public static String KBS2_재생_web = "//ul[@class='player__list']/li[21]/div/div[2]/button[@class='btn play']";
	public static String KBS3_타이틀_web = "//ul[@class='player__list']/li[22]/div/div[1]/img[@alt='KBS']";
	public static String KBS3_일시정지_web = "//ul[@class='player__list']/li[22]/div/div[2]/button[@class='btn pause']";
	public static String KBS3_재생_web = "//ul[@class='player__list']/li[22]/div/div[2]/button[@class='btn play']";
	public static String KBS클래식_타이틀_web = "//ul[@class='player__list']/li[23]/div/div[1]/img[@alt='KBS']";
	public static String KBS클래식_일시정지_web = "//ul[@class='player__list']/li[23]/div/div[2]/button[@class='btn pause']";
	public static String KBS클래식_재생_web = "//ul[@class='player__list']/li[23]/div/div[2]/button[@class='btn play']";
	public static String KBScool_타이틀_web = "//ul[@class='player__list']/li[24]/div/div[1]/img[@alt='KBS']";
	public static String KBScool_일시정지_web = "//ul[@class='player__list']/li[24]/div/div[2]/button[@class='btn pause']";
	public static String KBScool_재생_web = "//ul[@class='player__list']/li[24]/div/div[2]/button[@class='btn play']";
	public static String KBSworld_타이틀_web = "//ul[@class='player__list']/li[25]/div/div[1]/img[@alt='KBS']";
	public static String KBSworld_일시정지_web = "//ul[@class='player__list']/li[25]/div/div[2]/button[@class='btn pause']";
	public static String KBSworld_재생_web = "//ul[@class='player__list']/li[25]/div/div[2]/button[@class='btn play']";
	public static String KBS한민족_타이틀_web = "//ul[@class='player__list']/li[26]/div/div[1]/img[@alt='KBS']";
	public static String KBS한민족_일시정지_web = "//ul[@class='player__list']/li[26]/div/div[2]/button[@class='btn pause']";
	public static String KBS한민족_재생_web = "//ul[@class='player__list']/li[26]/div/div[2]/button[@class='btn play']";
	public static String SBS러브FM_타이틀_web = "//ul[@class='player__list']/li[27]/div/div[1]/img[@alt='SBS']";
	public static String SBS러브FM_일시정지_web = "//ul[@class='player__list']/li[27]/div/div[2]/button[@class='btn pause']";
	public static String SBS러브FM_재생_web = "//ul[@class='player__list']/li[27]/div/div[2]/button[@class='btn play']";
	
	//라디오 즐겨찾기 있을때
	public static String 라디오1st_일시정지_web = "//ul[@class='player__list']/li[1]/div/div[2]/button[@class='btn pause']";
	public static String 라디오1st_재생_web = "//ul[@class='player__list']/li[1]/div/div[2]/button[@class='btn play']";
	public static String 라디오2st_일시정지_web = "//ul[@class='player__list']/li[2]/div/div[2]/button[@class='btn pause']";
	public static String 라디오2st_재생_web = "//ul[@class='player__list']/li[2]/div/div[2]/button[@class='btn play']";
	public static String 라디오3st_일시정지_web = "//ul[@class='player__list']/li[3]/div/div[2]/button[@class='btn pause']";
	public static String 라디오3st_재생_web = "//ul[@class='player__list']/li[3]/div/div[2]/button[@class='btn play']";
	
	//팟캐스트 web
	public static String 팟캐스트구독on_web = "//button[@class='btn subscribe on']";
	public static String 팟캐스트구독off_web = "//button[@class='btn subscribe']";
	public static String 팟캐스트구독버튼 = "//android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.widget.Button[4]";
	public static String 팟캐스트재생시간_web = "//span[@class='current']"; 
	
	//날씨 web (오늘/내일)
	public static String 날씨타이틀_web = "//span[@class='txt']";
	public static String 날씨위치정보_web = "//span[@class='location']";
	public static String 현재날씨Text_web = "//div[@class='status']";
	public static String 날씨아이콘_web = "//div[@class='summary']/div[@class='icon']";
	public static String 현재온도_web = "//div[@class='temperature']/strong[@class='current']";
	public static String 최저온도_web = "//span[@class='min']";
	public static String 최고온도_web = "//span[@class='max']";
	public static String 날씨부가정보_web = "//p[@class='text']";
	public static String 시간대별날씨_web = "//ul[@class='weather__daily__addition__timeline']";
	public static String 시간대별날씨_1st_time_web = "//ul[@class='weather__daily__addition__timeline']/li[1]/div[1]";
	public static String 시간대별날씨_1st_img_web = "//ul[@class='weather__daily__addition__timeline']/li[1]/div[2]";
	public static String 시간대별날씨_2st_time_web = "//ul[@class='weather__daily__addition__timeline']/li[2]/div[1]";
	public static String 시간대별날씨_2st_img_web = "//ul[@class='weather__daily__addition__timeline']/li[2]/div[2]";
	public static String 시간대별날씨_3st_time_web = "//ul[@class='weather__daily__addition__timeline']/li[3]/div[1]";
	public static String 시간대별날씨_3st_img_web = "//ul[@class='weather__daily__addition__timeline']/li[3]/div[2]";
	public static String 시간대별날씨_4st_time_web = "//ul[@class='weather__daily__addition__timeline']/li[4]/div[1]";
	public static String 시간대별날씨_5st_img_web = "//ul[@class='weather__daily__addition__timeline']/li[4]/div[2]";
	
	//날씨 web (주간)
	public static String 주간날씨영역_web = "//div[@class='weather__weekly']";
	public static String 주간날씨내일마킹_web = "//ul[@class='weather__weekly__info']/li[2]/div[1]/span";
	public static String 주간날씨리스트_1st요일_web = "//ul[@class='weather__weekly__info']/li[1]/div[1]";
	public static String 주간날씨리스트_1st아이콘_web = "//ul[@class='weather__weekly__info']/li[1]/div[2][@class='img']";
	public static String 주간날씨리스트_1st최저기온_web = "//ul[@class='weather__weekly__info']/li[1]/div[3]/div[1]";
	public static String 주간날씨리스트_1st최고기온_web = "//ul[@class='weather__weekly__info']/li[1]/div[3]/div[2]";
	public static String 주간날씨리스트_2st요일_web = "//ul[@class='weather__weekly__info']/li[2]/div[1]";
	public static String 주간날씨리스트_2st아이콘_web = "//ul[@class='weather__weekly__info']/li[2]/div[2][@class='img']";
	public static String 주간날씨리스트_2st최저기온_web = "//ul[@class='weather__weekly__info']/li[2]/div[3]/div[1]";
	public static String 주간날씨리스트_2st최고기온_web = "//ul[@class='weather__weekly__info']/li[2]/div[3]/div[2]";
	public static String 주간날씨리스트_3st요일_web = "//ul[@class='weather__weekly__info']/li[3]/div[1]";
	public static String 주간날씨리스트_3st아이콘_web = "//ul[@class='weather__weekly__info']/li[3]/div[2][@class='img']";
	public static String 주간날씨리스트_3st최저기온_web = "//ul[@class='weather__weekly__info']/li[3]/div[3]/div[1]";
	public static String 주간날씨리스트_3st최고기온_web = "//ul[@class='weather__weekly__info']/li[3]/div[3]/div[2]";
	public static String 주간날씨리스트_4st요일_web = "//ul[@class='weather__weekly__info']/li[4]/div[1]";
	public static String 주간날씨리스트_4st아이콘_web = "//ul[@class='weather__weekly__info']/li[4]/div[2][@class='img']";
	public static String 주간날씨리스트_4st최저기온_web = "//ul[@class='weather__weekly__info']/li[4]/div[3]/div[1]";
	public static String 주간날씨리스트_4st최고기온_web = "//ul[@class='weather__weekly__info']/li[4]/div[3]/div[2]";
	public static String 주간날씨리스트_5st요일_web = "//ul[@class='weather__weekly__info']/li[5]/div[1]";
	public static String 주간날씨리스트_5st아이콘_web = "//ul[@class='weather__weekly__info']/li[5]/div[2][@class='img']";
	public static String 주간날씨리스트_5st최저기온_web = "//ul[@class='weather__weekly__info']/li[5]/div[3]/div[1]";
	public static String 주간날씨리스트_5st최고기온_web = "//ul[@class='weather__weekly__info']/li[5]/div[3]/div[2]";
	public static String 주간날씨리스트_6st요일_web = "//ul[@class='weather__weekly__info']/li[6]/div[1]";
	public static String 주간날씨리스트_6st아이콘_web = "//ul[@class='weather__weekly__info']/li[6]/div[2]";
	public static String 주간날씨리스트_6st최저기온_web = "//ul[@class='weather__weekly__info']/li[6]/div[3]/div[1]";
	public static String 주간날씨리스트_6st최고기온_web = "//ul[@class='weather__weekly__info']/li[6]/div[3]/div[2]";
	public static String 주간날씨리스트_7st요일_web = "//ul[@class='weather__weekly__info']/li[7]/div[1]";
	public static String 주간날씨리스트_7st아이콘_web = "//ul[@class='weather__weekly__info']/li[7]/div[2]";
	public static String 주간날씨리스트_7st최저기온_web = "//ul[@class='weather__weekly__info']/li[7]/div[3]/div[1]";
	public static String 주간날씨리스트_7st최고기온_web = "//ul[@class='weather__weekly__info']/li[7]/div[3]/div[2]";
	
	//날씨 web (미세먼지)
	public static String 메세먼지상태Text_web = "//div[@class='status']";
	public static String 메세먼지아이콘_web = "//div[@class='weather__dust__info']/div[@class='icon']";
	public static String 메세먼지농도_web = "//div[@class='weather__dust__info']/div[@class='value']";
	public static String 초미세먼지_web = "//div[@class='weather__dust__detail']/ul/li[1]/div[1]";
	public static String 초미세먼지아이콘_web = "//div[@class='weather__dust__detail']/ul/li[1]/div[2]";
	public static String 초미세먼지상태_web = "//div[@class='weather__dust__detail']/ul/li[1]/div[3]";
	public static String 초미세먼지농도_web = "//div[@class='weather__dust__detail']/ul/li[1]/div[4]";
	public static String 통합대기지수_web = "//div[@class='weather__dust__detail']/ul/li[2]/div[1]";
	public static String 통합대기지수아이콘_web = "//div[@class='weather__dust__detail']/ul/li[2]/div[2]";
	public static String 통합대기지수상태_web = "//div[@class='weather__dust__detail']/ul/li[2]/div[3]";
	public static String 통합대기지수농도_web = "//div[@class='weather__dust__detail']/ul/li[2]/div[4]";
	public static String 오존_web = "//div[@class='weather__dust__detail']/ul/li[3]/div[1]";
	public static String 오존아이콘_web = "//div[@class='weather__dust__detail']/ul/li[3]/div[2]";
	public static String 오존상태_web = "//div[@class='weather__dust__detail']/ul/li[3]/div[3]";
	public static String 오존농도_web = "//div[@class='weather__dust__detail']/ul/li[3]/div[4]";
	
	//날씨 web (내일 미세먼지)
	public static String 내일미세먼지_web = "//ul[@class='weather__dust__tomorrow__info']/li[1]/span[1]";
	public static String 내일미세먼지상태_web = "//ul[@class='weather__dust__tomorrow__info']/li[1]/span[2]";
	public static String 내일미세먼지아이콘_web = "//ul[@class='weather__dust__tomorrow__info']/li[1]/div";
	public static String 내일초미세먼지_web = "//ul[@class='weather__dust__tomorrow__info']/li[2]/span[1]";
	public static String 내일초미세먼지상태_web = "//ul[@class='weather__dust__tomorrow__info']/li[2]/span[2]";
	public static String 내일초미세먼지아이콘_web = "//ul[@class='weather__dust__tomorrow__info']/li[2]/div";


}
