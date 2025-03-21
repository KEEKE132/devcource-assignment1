package account;

import java.time.LocalDateTime;

public class Account {
    private static Long idCounter = 1L;

    private final Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Account(String username, String password, String email, String nickname) {
        id = idCounter++;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void update(String email, String password) {
        if (email != null && email != "") {
            this.email = email;
        }
        if (password != null && password != "") {
            this.password = password;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("닉네임: ").append(nickname).append("\n");
        sb.append("이메일: ").append(email).append("\n");
        sb.append("가입일: ").append(createdAt).append("\n");
        System.out.println(sb);
    }
}
