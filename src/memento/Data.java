package memento;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class Data {

    private String id;
    private String name;
    private Element element;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
