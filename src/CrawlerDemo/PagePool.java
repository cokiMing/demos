package CrawlerDemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wuyiming on 2017/6/27.
 */
public class PagePool {
    private List<String> container = new LinkedList<>();

    public void addPageSource(String page){
        container.add(page);
    }

    public synchronized String takePageSource(){
        if (container.size() > 0){
            String content = container.get(container.size() - 1);
            container.remove(container.size() - 1);
            return content;
        }
        return null;
    }
}
