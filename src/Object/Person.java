package Object;

public class Person {
    // attributes

    public String id;
    protected String name;
    protected int age;
    protected String gender;
    protected String address;
    protected String phone;
    // get,set

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // constructor
    public Person() {
        this.id = "None";
        this.name = "None";
        this.age = 0;
        this.gender = "None";
        this.address = "None";
        this.phone = "None";
    }

    public Person(String id, String name, int age, String gender, String address, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    // method 
    @Override
    public String toString() {
        return  id + ", " + name + ", " + age + ", " + gender + ", " + address + ", " + this.phone;
    }

}
