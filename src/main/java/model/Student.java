package model;

public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private String language;

    public Student() {
    }

    public Student(Integer id, String name, Integer age, String language) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public Student setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Student setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", language='" + language + '\'' +
                '}';
    }
}
