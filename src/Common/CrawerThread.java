
package Common;


public class CrawerThread implements Runnable{
	
	private Parser parser = null;	
	public CrawerThread(Object... args){
		parser = new Parser();
	}
	
	public void run(){	
		
	//	while (true) {
			try {	
				this.parser.Parser();				
				System.out.println("the project is sleeping!");
				//Thread.sleep(1000000);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}		
		}
	//}
	
	/*public void run(){		
		while (true) {
			try {	
				
				Thread thread = new Thread(new XinhuaThread());
				thread.start();
				System.out.println("Xinhua thread");
				
				Thread thread2 = new Thread(new WangyiThread());
				thread2.start();
				System.out.println("Wangyi thread");
				
				
				Thread thread3 = new Thread(new SohuThread());
				thread3.start();
				System.out.println("Xinhua thread");
				
				Thread thread4 = new Thread(new QQThread());
				thread4.start();
				System.out.println("QQ Thread");
				
				Thread thread5 = new Thread(new SinaThread());
				thread5.start();
				System.out.println("Sina Thread");
				
				Thread.sleep(1000000);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	}*/
	
	public static void main(String[] args){

		Thread thread = new Thread(new CrawerThread());
		thread.start();
		//new ParserRssThread().run();
		System.out.println("ok@");
	}
}
