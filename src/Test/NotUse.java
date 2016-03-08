package Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NotUse {

	
	private void getCurrentPageNews(String url) throws Exception
	{
		String moreUrl ="https://news.google.com" + url;
		
		System.out.println("Current Page Url : "+ moreUrl);
		
		Document doc = Jsoup.connect(moreUrl).get();
		Elements url_Elem = doc.select("body div#main-wrapper div.story-page-main h2.title a[href]");
		System.out.println("the number of news in this page :"+url_Elem.size());
		
		Elements title_Elem = doc.select("body div#main-wrapper div.story-page-main div.topic h2.title a span.titletext");
		System.out.println("the number of titles in this page :"+title_Elem.size());
		
		/*for(Element e : url_Elem){
			String pageUrl = e.attr("href").trim();
			//String pageUrl = e.attr("span").trim();
			System.out.println(pageUrl);	
		}*/
		for(int i=0; i<url_Elem.size(); i++)
		{
			String pageUrl = url_Elem.get(i).attr("href").trim();
			System.out.println("url : " + pageUrl);
			
			System.out.println("title : " + title_Elem.get(i).text().trim());	
		}
		
		
	}
	
	private void getAllPagesNews(String url) throws Exception 
	{
		getCurrentPageNews(url);
		
		String firstUrl ="https://news.google.com" + url;
		
		//System.out.println("First Page Url : "+ moreUrl);
		
		//ArrayList<String> allPagesUrl = new ArrayList<String>();
		//Thread.sleep(1000);
		
		Document doc = Jsoup.connect(firstUrl).get();
		Elements term = doc.select("body div#main-wrapper div#pagination td a[href]");
		System.out.println("the number of pages is :"+ ( term.size()-1) );
		
		for(int i=0; i<term.size()-1; i++){
			
			String pageUrl = term.get(i).attr("href").trim();
			
			getCurrentPageNews(pageUrl);
			
			//Thread.sleep(1000);
				
		}

	}
	
}
