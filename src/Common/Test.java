package Common;

import java.util.ArrayList;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test {

	private void getNewsContent(String url)
	{
		String newsContent = "";
		try
		{
			
			long timeout = 1111;
			Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").timeout(100000).get();  
			
			
				
			//Document doc = Jsoup.connect(url).timeout(100000).get();  
			
			//System.out.println(doc.text().toString());
			Elements url_Elem = doc.select("body p");
			
			System.out.println(url_Elem.text().trim());
			//return newsContent;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void test()
	{
		//string p= "http://www.voanews.com/content/pakistan-shock-and-sorrow-at-death-of-hostages/2732781.html";
		getNewsContent("https://www.cars.com/articles/recall-alert-2012-2014-ford-fiesta-2013-2014-ford-fusion-lincoln-mkz-1420680378552/");
		//getNewsContent("http://www.washingtonpost.com/world/europe/on-centennial-of-horrific-massacres-turkey-reaches-out-to-armenians-but-rejects-the-term-genocide/2015/04/24/5aa976fa-eaa5-11e4-9a6a-c1ab95a0600b_story.html");
	}
	
	 public int commonArray(ArrayList<Integer> arr1, ArrayList<Integer> arr2)
	    {
	    	int count = 0;
	    	for(int i=0; i<arr1.size(); i++)
	    		if(arr2.contains(arr1.get(i)))
	    			count++;
	    	
	    	return count;
	    }
	    
	 
	 public void nmi()
	 {
		 ArrayList<Integer> label = new ArrayList<Integer>();
			
			label.add(1);
			label.add(1);
			label.add(2);
			label.add(2);
			
			int z[] = new int[]{1,1,2,2};
			ArrayList<ArrayList<Integer>> textLabel = new ArrayList<ArrayList<Integer>>();
	    	
	    	ArrayList<Integer> labelId = new ArrayList<Integer>();
	    	
	    	for(int i=0; i< label.size(); i++)
	    	{
	    		int id = label.get(i);
	    		
	    		if(labelId.contains(id))
	    		{
	    			int index = labelId.indexOf(id);
	    			
	    			textLabel.get(index).add(i);
	    		}else
	    		{
	    			ArrayList<Integer> subLabel = new ArrayList<Integer>();
	    			subLabel.add(i);
	    			labelId.add(id);
	    			textLabel.add(subLabel);
	    		}
	    	}
	    	
	    	ArrayList<ArrayList<Integer>> clusterLabel = new ArrayList<ArrayList<Integer>>();
	    	
	    	ArrayList<Integer> clusterlId = new ArrayList<Integer>();
	    	
	    	for(int i=0; i<z.length ; i++)
	    	{
	    		int id = z[i];
	    		
	    		if(clusterlId.contains(id))
	    		{
	    			int index = clusterlId.indexOf(id);
	    			
	    			clusterLabel.get(index).add(i);
	    		}else
	    		{
	    			ArrayList<Integer> subLabel = new ArrayList<Integer>();
	    			subLabel.add(i);
	    			clusterlId.add(id);
	    			clusterLabel.add(subLabel);
	    		}
	    	}
	    	
	    	double comRes = 0;
	    	
	    	for(int i=0; i<textLabel.size(); i++)
	    	{
	    		for(int j=0; j<clusterLabel.size(); j++)
	    		{
	    			int common = commonArray(textLabel.get(i),clusterLabel.get(j));
	    			
	    			if(common!=0)
	    				comRes += (double)common*Math.log((double)z.length*common/(textLabel.get(i).size()*clusterLabel.get(j).size()));
	    		}	
	    	}
	    	
	    	double comL = 0;
	    	for(int i=0; i<textLabel.size(); i++)
	    	{
	    		comL += (double)textLabel.get(i).size()*Math.log((double)textLabel.get(i).size()/z.length);
	    	}
	    	
	    	double comC = 0;
	    	for(int j=0; j<clusterLabel.size(); j++)
	    		comC += (double)clusterLabel.get(j).size()*Math.log((double)clusterLabel.get(j).size()/z.length);
	    	
	    	System.out.println(comRes + " " + comL + " "+ comC);
	    	
	    	comRes /= Math.sqrt(comL*comC);
	    	
	    	System.out.println(comRes);
	 }
	 
	
	public static void main(String []args)
	{
		//new Test().nmi();
		
		double d = 1e-20;
		
		if(Double.isNaN(d))
    	    System.out.println(Math.log(0.5));
	}
}
