package guessGame;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownloadHTMLImagesThread extends Thread {
	private ArrayList<Challenge> challenges;
	private static final String FEED_URL = "http://floorsix.blogspot.com/2007_11_01_archive.html";

	public DownloadHTMLImagesThread(ArrayList<Challenge> challenges) {
		this.challenges = challenges;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			
			Document jsoupDoc = Jsoup.connect(FEED_URL).userAgent("Google").get();
			 //doc.select("ul.myClass > li > a > img")
			Elements zoomedPics = jsoupDoc.getElementsByAttributeValue("class","post-body entry-content");//getElementsByAttributeValue("itemprop", "description articleBody > img");//select("img[src$=.png]");
			for(Element p:zoomedPics){
				Elements images =p.getElementsByTag("img");
				String upCloseImage = images.first().attr("src");
				if(upCloseImage.contains("\n")){
					upCloseImage = cleanUpUrl(upCloseImage,"\n");
				}
				String regularImage = images.last().attr("src");
				if(regularImage.contains("\n")){
					regularImage= cleanUpUrl(regularImage,"\n");
				}
				
				String o =p.text();
				
				Element innerDiv = p.getElementsByTag("p").last();
				//p.select("img.style)
				System.out.println(o);
				System.out.println(upCloseImage);
				System.out.println(regularImage);
				TaskType taskType = null;
				if(upCloseImage.endsWith(".jpeg") || upCloseImage.endsWith(".jpg")){
					taskType = TaskType.JPEG;
				}else if(upCloseImage.endsWith(".gif") ){
					taskType = TaskType.GIF;
				}else if(upCloseImage.endsWith(".png") ){
					taskType = TaskType.PNG;
				}
				
				challenges.add(new Task(taskType,upCloseImage,o));
			}
	
			
			
		}catch(IOException e){
			
		}
	}

	private String cleanUpUrl(String url, String regex) {

		return url = url.replace(regex, "");

	}

}
