package Common;




import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Parser {
	
	 long  cluster_ID = 0;
	 String dir = "C:\\Users\\qjp\\Desktop\\UMAB\\dataset\\GoogleNew01042016";
	 long news_ID = 0;
	 
	 HashMap<String,String> newsTemp = new HashMap<String, String>();
	 
	 ArrayList<String> newsSource = new ArrayList<String>();
	 ArrayList<String> newsCata = new ArrayList<String>();
	//private  ArrayList<String> NewsPage = new ArrayList<String>();

	public Parser()
	{
		newsSource.add("https://news.google.com/news/section?pz=1&cf=all&topic=w"); //world
		newsCata.add("world");
		//newsSource.add("https://news.google.com/news/section?pz=1&cf=all&topic=n"); //USA
		newsSource.add("https://news.google.com/news/section?pz=1&cf=all&topic=b"); //Business
		newsCata.add("Business");
		newsSource.add("https://news.google.com/news/section?pz=1&cf=all&topic=tc"); //technology
		newsCata.add("technology");
		newsSource.add("https://news.google.com/news/section?pz=1&cf=all&topic=e"); //entertainment
		newsCata.add("entertainment");
		newsSource.add("https://news.google.com/news/section?pz=1&cf=all&topic=s"); //sports
		newsCata.add("sports");
		newsSource.add("https://news.google.com/news/section?pz=1&cf=all&topic=snc"); //science
		newsCata.add("science");
		newsSource.add("https://news.google.com/news/section?pz=1&cf=all&topic=m"); //health
		newsCata.add("health");
		//newsSource.add("https://news.google.com/news/section?pz=1&cf=all&topic=ir"); //spotlinght
		
	}
	
	
	public void Parser(Object...args)
	{
		String qqTitle = null ;
 		//DirectoryProcess dir = new DirectoryProcess();
					
		try {
			
			int topic = 0;
			for(int i=0; i<newsSource.size(); i++)
			{
				String subDir = dir + "\\" + newsCata.get(i)+"\\" ;
	
				File file = new File(subDir);
				
			   file.mkdir();
			    
				getCluster(newsSource.get(i),subDir);
				topic++;
			}
			
			//getCluster("https://news.google.com/news/section?cf=all&topic=w&ned=us&siidp=f16b569e41b741e88971708dd4f2c46606c7&ict=ln");
			} catch (Exception e) 
			{
					e.printStackTrace();
			}
	
	}
	
	public void addTemp()
	{
		
		newsTemp.put("ABC News", "body div#storyText  p");
		newsTemp.put("Arirang News", "body div.aNV_news");
		
		newsTemp.put("Best Daily", "body div#article-body");
		newsTemp.put("Bustle", "body div.article-page-body-content");
		
		newsTemp.put("CBC.ca", "body div.story-content p p");
		newsTemp.put("Christian Science Monitor", "body div#story-body p");
		newsTemp.put("Coconuts Manila", "body div.fck");
		newsTemp.put("CTV News", "body div.articleBody p");
		
		newsTemp.put("Focus News", "body div.inside-body-content.jstf");
		newsTemp.put("FOX News Radio", "body div#content-area p");
		
		newsTemp.put("GMA News", "body div.text_body");
		
		newsTemp.put("Hilton Head Island Packet", "body div[class=entry-content story_body]");
		newsTemp.put("Hong Kong Standard", "body span.bodyCopy");
		newsTemp.put("Huffington Post", "body div.content");
		
		newsTemp.put("Irish Times", "body section[property=articleBody] p");
		
		newsTemp.put("KABC-TV", "body div.body-text");
		newsTemp.put("KGO-TV", "body div.body-text");
		newsTemp.put("Korea Times", "body div.view_page_news_article_wrapper");
		
		newsTemp.put("Los Angeles Times", "body section.trb_mainContent p");
		
		newsTemp.put("MaltaToday", "body div.content");
		newsTemp.put("MyFox Washington DC", "body div#WNStoryBody");
		
		newsTemp.put("New Kerala", "body div.entry-content");
		newsTemp.put("Newsweek", "body div.article-body p");
		
		newsTemp.put("Panorama.am", "body div.news-body p");
		newsTemp.put("People Magazine", "body div#articleBody");
		newsTemp.put("Prensa Latina", "body table.contentpaneopen");
		
		newsTemp.put("Reveal", "body div.article_body");
		
		newsTemp.put("Sky News Australia", "body div[class=col-md-12 content]");
		
		newsTemp.put("The Daily Star", "body div#divDetails p");
		newsTemp.put("Times of Oman", "body div#articlebox");
		newsTemp.put("Toronto Star", "body div.article-story-body p");
		
		newsTemp.put("WLS-TV", "body div.body-text");
		
		newsTemp.put("Voice of America", "body div.zoomMe p");
		
		newsTemp.put("Ynetnews", "body span#article_content");
		newsTemp.put("yourcentralvalley.com", "body div.story-body");
	
		////////////////////////////////////////////////
		//newsTemp.put("Hilton Head Island Packet", "body div[class=entry-content story_body]");
		//
		
		//Wall Street Journal
	
	}
	
	
	private String getNewsContent(String url, String source)
	{
		String newsContent = "";
		try
		{
			Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(200000).get();
			String temp = "";
			
			if(newsTemp.containsKey(source))
			{
				System.out.println("####" + source + "####");
				temp = newsTemp.get(source);
			}else
			{
				temp = "body p";
			}
			
			Elements url_Elem = doc.select(temp);
			
			newsContent = url_Elem.text().trim();
			//System.out.println(url_Elem.text().trim());
			//return newsContent;
		}catch(Exception e)
		{
			e.printStackTrace();
			//getNewsContent(url,source);
		}
		
		return newsContent;
		
	}
	
	private void getCurrentPageNews(String url, String path) throws Exception
	{
		String moreUrl ="https://news.google.com" + url;
		
		System.out.println("Current Page Url : "+ moreUrl);
		
		
		Document doc = Jsoup.connect(moreUrl).get();
		Elements url_Elem = doc.select("body div#main-wrapper div.story-page-main h2.title a[href]");
		//System.out.println("the number of news in this page :"+url_Elem.size());
		
		Elements title_Elem = doc.select("body div#main-wrapper div.story-page-main div.topic h2.title a span.titletext");
		//System.out.println("the number of titles in this page :"+title_Elem.size());
		
		Elements source_Elem = doc.select("body div#main-wrapper div.story-page-main div.topic div.sub-title span.source");
		
		Elements snippet_Elem = doc.select("body div#main-wrapper div.story-page-main div.topic div.snippet");
		/*for(Element e : url_Elem){
			String pageUrl = e.attr("href").trim();
			//String pageUrl = e.attr("span").trim();
			System.out.println(pageUrl);	
		}*/
		
		
		//java.util.Date date= new java.util.Date();
		
		for(int i=0; i<url_Elem.size(); i++)
		{
			
			String pageUrl = url_Elem.get(i).attr("href").trim();
			//System.out.println(i + "->" + "url : " + pageUrl);
			
			String title = title_Elem.get(i).text().trim();
			System.out.println("title : " + title );	
			
			String source = source_Elem.get(i).text().trim();
			
			//String snippet = snippet_Elem.get(i).text().trim();
			
			//System.out.println(snippet);
			//String time = new Timestamp(date.getTime()).toString();
			String newsContent = getNewsContent(pageUrl, source);
			if(newsContent.length()<200)
				continue;
			//if(source.equals("New York Times"))
				//continue;
			String fileName = path + "\\" + news_ID + ".txt";
			news_ID++;
			//System.out.println(fileName);
			File newsFile = new File(fileName);
			 
			// if file doesnt exists, then create it
			if (!newsFile.exists()) {
				newsFile.createNewFile();
			}
 
			
			//if(newsCon)
			FileWriter fw = new FileWriter(newsFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			//bw.write(source);
			//bw.newLine();
			//bw.write(pageUrl);
			//bw.newLine();
			bw.write( title );
			bw.newLine();
			//bw.write("<snippet>" + snippet + "<snippet>");
			
			//bw.newLine();
			
			//System.out.println(pageUrl);
			//System.out.println("--"+source+"--");
			
			
			bw.write(newsContent);
			bw.close();
		}
		
		
	}
	
	private void getAllPagesNews(String url,String path) 
	{
		//getCurrentPageNews(url);
		try
		{
			while(!url.equals(""))
			{
				getCurrentPageNews(url, path);
				String currentUrl ="https://news.google.com" + url;
				Document doc = Jsoup.connect(currentUrl).get();
				Elements term = doc.select("body div#main-wrapper div#pagination td.next a[href]");
				//System.out.println("the number of pages is :"+ ( term.size()-1) );
				if(term.size()==0)
					return;
				String pageUrl = term.get(0).attr("href").trim();
				
				//System.out.println(pageUrl);
				
				url = "";
				getAllPagesNews(pageUrl, path);
				
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	private  void getMoreNews(String soureUrl, String path) throws Exception{
		
		
		String url ="https://news.google.com" + soureUrl;
		
		System.out.println("moreNewsUrl: " + url);
		
		Document doc = Jsoup.connect(url).get();
		Element term = doc.select("body div#main-wrapper div.content-pane-container td.lt-col div.section-stream-content  a[href]").last();
		//System.out.println("term`s size is :"+term.size());
		//for(Element e : term){
		
		String subDir = path + "\\" + cluster_ID+"\\" ;
		
		//File file = new File(subDir);
		
		//file.mkdir();
		
		cluster_ID++;
		File file = new File(subDir);
		
	    if (file.mkdir()){
	       // System.out.println(subDir + " File is created!");
	     }else{
	       // System.out.println(subDir + " File already exists.");
	     }
	    
			String moreUrl = term.attr("href").trim();
			
			//System.out.println(moreUrl);
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			//System.out.println("moreNewsUrl: " + moreUrl);
			getAllPagesNews(moreUrl,subDir);
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				
		//}
		//System.out.println("----------------------------------");
		
	}
	
	private  void getMoreNews2(String soureUrl, String path, int id) throws Exception{
		
		
	//	String url ="https://news.google.com" + soureUrl;
		String url = soureUrl;
		System.out.println("moreNewsUrl: " + url);
		
		Document doc = Jsoup.connect(url).get();
		Element term = doc.select("body div#main-wrapper div.content-pane-container td.lt-col div.section-stream-content  a[href]").last();
		//System.out.println("term`s size is :"+term.size());
		//for(Element e : term){
		
		String subDir = path + "\\" + id+"\\" ;
		
		//File file = new File(subDir);
		
		//file.mkdir();
		
		cluster_ID++;
		File file = new File(subDir);
		
	    if (file.mkdir()){
	       // System.out.println(subDir + " File is created!");
	     }else{
	       // System.out.println(subDir + " File already exists.");
	     }
	    
			String moreUrl = term.attr("href").trim();
			
			//System.out.println(moreUrl);
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			//System.out.println("moreNewsUrl: " + moreUrl);
			getAllPagesNews(moreUrl,subDir);
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				
		//}
		//System.out.println("----------------------------------");
		
	}

	private  void getCluster(String url, String path){
		//NewsPage.clear();
		try{
			//NewsPage.add(url);
			System.out.println("----------------------------------------------------");
			System.out.println(url);
			Document doc = Jsoup.connect(url).get();
			//Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(200000).get();
			Elements term = doc.select("body div#main-wrapper div.content-pane-container td.esc-layout-thumbnail-cell  a[href]");
			int i=0;
			//System.out.println("cluster`s size is :"+term.size());
			for(Element e : term){
				String pageUrl = e.attr("href").trim();
				if(!pageUrl.subSequence(0, 4).equals("http"))
				{
					getMoreNews(pageUrl, path);
					//break;
				}
				
				if((i++)==10)
					break;
				
					
			}
			System.out.println("----------------------------------------------------");
			//for(int i = 0;i < NewsPage.size();i ++){
				//System.out.println(NewsPage.get(i));
			//}
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println("in the ParserNews`s getNewsPage function :"+e);
		}
	}

	public void oneTime()
	{
		try
		{
			String urlPath = "/news/story?cf=all&hl=en&pz=1&ned=us&cf=all&ncl=d4fZInSxSNW6w1Mt8ZRHGtJIcGs0M&topic=e&scoring=n";
			
			String filePath = "C:\\Users\\qjp\\Desktop\\UMAB\\dataset\\GoogleNewsBoth\\5\\";
			getAllPagesNews(urlPath, filePath);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		Parser p = new Parser();
		p.oneTime();
		//p.addTemp();
		//p.Parser();		
	}
}

