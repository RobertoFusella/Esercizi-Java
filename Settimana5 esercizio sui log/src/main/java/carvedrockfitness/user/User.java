package carvedrockfitness.user;

import java.time.LocalDateTime;

public class User {
    private long id;
    private String userName;
    private String email;
    private LocalDateTime dateCreated;
    private UserStatus userStatus;

    public User() {
    }

    public User(long id, String userName, String email, LocalDateTime created, UserStatus userStatus) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.dateCreated = created;
        this.userStatus = userStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime created) {
        this.dateCreated = created;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString(){
        return User.class.getName() + " - name: " + userName + ", id: " + id + ", email: " + email;
    }
}
