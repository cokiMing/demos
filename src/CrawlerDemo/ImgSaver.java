package CrawlerDemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wuyiming on 2017/6/28.
 */
public class ImgSaver {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void saveImg(List<String> URLs){
        for(String url : URLs){
            save(url);
        }
    }

    private static void save(String url){

        Thread thread = new Thread(){
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            int flag = 0;
            @Override
            public void run() {
                try{
                    Random random = new Random();
                    int i = random.nextInt(20);
                    URL imgURL = new URL(url);
                    HttpURLConnection urlConnection = (HttpURLConnection) imgURL.openConnection();

                    if(urlConnection.getResponseCode() != 200){
                        System.out.println("[DOWNLOAD ERROR "+ urlConnection.getResponseCode()+"] " + url);
                        flag = 1;
                        return;
                    }
                    inputStream = urlConnection.getInputStream();
                    String suffix = url.substring(url.lastIndexOf("."));
                    if(suffix.length() > 5){
                        suffix = ".jpg";
                    }
                    String fileName = "pic" + System.currentTimeMillis() + i + suffix;
                    System.out.println(fileName + ":" + url);
                    File file = new File("/Users/chen/Desktop/workspace/test/src/CrawlerDemo/img",fileName);
                    int len = 0;
                    byte[] buff = new byte[1024];
                    fileOutputStream = new FileOutputStream(file);
                    while((len = inputStream.read(buff)) != -1){
                        fileOutputStream.write(buff,0,len);
                    }
                    fileOutputStream.flush();
                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(flag == 0){
                            inputStream.close();
                            fileOutputStream.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        try{
            executorService.execute(thread);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
