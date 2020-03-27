package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", columnDefinition = "INT UNSIGNED", nullable = false)
    private long userId;
    
    @Column(name = "username", nullable = false, length = 25)
    private String userName;
    
    @Column(name = "email_id", nullable = false, length = 100)
    private String emailId;
    
    @Column(name = "gender", nullable = false)
    private boolean gender;
    
    @Column(name = "birth_year", nullable = false, columnDefinition = "SMALLINT(4) UNSIGNED")
    private int birthYear;
    
    @Column(name = "password", length = 300, nullable = false)
    private String password;
    
    public User() {
        
    }
    
    public User(String userName, String emailId, boolean gender, int birthYear, String password) {
        this.userName = userName;
        this.emailId = emailId;
        this.gender = gender;
        this.birthYear = birthYear;
        this.password = password;
    }

    public User(long userId, String userName, String emailId, boolean gender, int birthYear, String password) {
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
        this.gender = gender;
        this.birthYear = birthYear;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
