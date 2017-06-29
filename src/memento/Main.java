package memento;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class Main {

    public static void main(String args[]){
        Data data = new Data();
        Element element = new Element();
        element.setName("element");
        element.setStatus("YES");

        data.setId("123");
        data.setName("data");
        data.setElement(element);

        Memento memento = new Memento();
        memento.setBackup(data);

        data.setName("data change");
        data.getElement().setName("element change");

        System.out.println();
    }
}
