package me.feel5n.url;

import me.feel5n.account.Account;
import me.feel5n.account.AccountType;

public class Session {
    private Long sessionId = null;
    private String nickname = null;
    private AccountType accountType = null;

    public Session() {
    }

    public Session(Account account) {
        this.sessionId = account.getId();
        this.nickname = account.getNickname();
        this.accountType = account.getAccountType();
    }

    public boolean isSigned() {
        return sessionId != null;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getNickname() {
        return nickname;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
