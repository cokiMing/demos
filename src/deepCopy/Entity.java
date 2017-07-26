package deepCopy;

import java.util.List;

/**
 * Created by wuyiming on 2017/7/25.
 */
public class Entity {
    private String name;
    private int age;
    private List<Address> addressList;
    private String[] nums;
    private int[] intNums;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public String[] getNums() {
        return nums;
    }

    public void setNums(String[] nums) {
        this.nums = nums;
    }

    public int[] getIntNums() {
        return intNums;
    }

    public void setIntNums(int[] intNums) {
        this.intNums = intNums;
    }
}
