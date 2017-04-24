package org.yyf.springBootDemo.propDemo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Created by tobi on 2017/4/6.
 */
@ConfigurationProperties(prefix = "t")
public class TestProperties {
    private Boolean good;
    private String name;
    private Integer age;
    private Map<String,String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public static class Inner{
       private Integer level;

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return "Inner{" +
                    "level=" + level +
                    '}';
        }
    }
    public Inner getInner(){
        return inner;
    }
    private final Inner inner = new Inner();


    public Boolean getGood() {
        return good;
    }

    public void setGood(Boolean good) {
        this.good = good;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestProperties{" +
                "good=" + good +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
