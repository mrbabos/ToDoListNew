package io.github.mat3e;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
class Lang {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private String welcomeMSG;
    private String code;

    // Hibernate needs it
    @SuppressWarnings("unused")
    Lang(){
    }
    public Lang(Integer id, String welcomeMsg, String code) {
        this.id = id;
        this.welcomeMSG = welcomeMsg;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }


    public String getWelcomeMSG() {
        return welcomeMSG;
    }

    public void setWelcomeMSG(String welcomeMSG) {
        this.welcomeMSG = welcomeMSG;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
