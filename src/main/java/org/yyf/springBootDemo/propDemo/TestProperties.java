package org.yyf.springBootDemo.propDemo;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

import java.util.Map;

/**
 * Created by tobi on 2017/4/6.
 * adf.
 */
@ConfigurationProperties(prefix = "t")
public class TestProperties {
    /**
     * desc for good. will be generated in spring-configuration-metadata.json description
     */
    private Boolean good;
    @NotEmpty
    private String name;
    @NotNull
    private Integer age;
    //desc for map.
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
