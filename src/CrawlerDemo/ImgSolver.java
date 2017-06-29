package CrawlerDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wuyiming on 2017/6/27.
 */
public class ImgSolver {

    private static String picHeader = "objURL";

    private static List<String> imgUrls = new ArrayList<>();

    public  List<String> imgHandler(String pageSource){
        getUrlIndex(pageSource);
        return imgUrls;
    }

    private void getUrlIndex(String pageSource){
        int newIndex = pageSource.indexOf(picHeader, 0);
        if(newIndex < 0){
            return;
        }

        int imgEnd = 0;
        int jpgEnd = pageSource.indexOf(".jpg", newIndex)+4;
        int gifEnd = pageSource.indexOf(".gif", newIndex)+4;
        int jpegEnd = pageSource.indexOf(".jpeg", newIndex)+5;
        int pngEnd = pageSource.indexOf(".png", newIndex)+4;
        List<Integer> ends = new ArrayList<>();
        ends.add(jpgEnd);
        ends.add(gifEnd);
        ends.add(jpegEnd);
        ends.add(pngEnd);

        imgEnd = getMinEnd(ends);
        if (imgEnd < 0){
            return;
        }
        int imgStart = pageSource.indexOf("http", newIndex);
        if (imgEnd > imgStart){
            String imgStr = pageSource.substring(imgStart,imgEnd);
            if(imgStr.length() > 150){
                int len = imgStr.indexOf(",");
                imgStr = imgStr.substring(0,imgStr.indexOf(",")-1);
            }
            imgUrls.add(imgStr);
        }

        pageSource = pageSource.substring(imgEnd);
        getUrlIndex(pageSource);
    }

    private static int getMinEnd(List<Integer> ends){
        Collections.sort(ends);
        for(int end : ends){
            if(end > 5){
                return end;
            }
        }
        return -1;
    }

}
