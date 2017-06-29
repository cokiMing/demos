package CrawlerDemo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

/**
 * Created by wuyiming on 2017/6/27.
 */
public class MainPage{

    private String engine;

    private String keyWord;

    public MainPage(String engine,String keyWord) {
        this.engine = engine;
        this.keyWord = keyWord;
    }

    public String getSourcePage() {
        InputStream in = null;
        try{
            URL url = new URL(engine + "&word=" + keyWord);
            URLConnection urlConnection = url.openConnection();
            in = urlConnection.getInputStream();
            byte[] buff = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();
            int len = 0;
            while ((len = in.read(buff)) != -1){
                String str = new String(buff,0,len);
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try{
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
