package CrawlerDemo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wuyiming on 2017/6/27.
 */
public class Main {

    private static PagePool pagePool = new PagePool();

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String args[]){
        String engine = "http://image.baidu.com/search/index?tn=baiduimage";
        String keyWord = "以撒的结合";

        MainPage mainPage = new MainPage(engine,keyWord);
        String sourcePage = mainPage.getSourcePage();

        ImgSolver imgSolver = new ImgSolver();
        List<String> strings = imgSolver.imgHandler(sourcePage);

        ImgSaver.saveImg(strings);

    }
}
