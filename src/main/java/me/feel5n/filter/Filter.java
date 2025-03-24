package me.feel5n.filter;

import me.feel5n.SingletonContainer;
import me.feel5n.account.AccountRepository;
import me.feel5n.account.AccountType;
import me.feel5n.url.Request;
import me.feel5n.url.Session;

public class Filter {

    private final Request request;
    private final AccountRepository accountRepository;

    public Filter(Request request) {
        this.request = request;
        this.accountRepository = SingletonContainer.get(AccountRepository.class);
    }

    public boolean isValidRequest() {
        String path = request.pathToString();
        UrlType urlType = sorting(path);
        if (urlType == UrlType.PERMITTED) {
            return true;
        }
        Session session = request.getSession();
        if (urlType == UrlType.AUTHENTICATED) {
            if (session.isSigned()) {
                return true;
            }
            return false;
        }
        if (urlType == UrlType.ANONYMOUS) {
            if (!session.isSigned()) {
                return true;
            }
            return false;
        }
        if (urlType == UrlType.ADMIN) {
            if (session.getAccountType() == AccountType.ADMIN) {
                return true;
            }
            return false;
        }
        return false;
    }

    private UrlType sorting(String path) {
        if (isAnonymous(path)) {
            return UrlType.ANONYMOUS;
        }
        if (isAuthenticated(path)) {
            return UrlType.AUTHENTICATED;
        }
        if (isAdmin(path)) {
            return UrlType.ADMIN;
        }
        return UrlType.PERMITTED;
    }

    private boolean isAnonymous(String path) {
        for (String url : AccessConfig.anonymous) {
            if (path.startsWith(url)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAuthenticated(String path) {
        for (String url : AccessConfig.user) {
            if (path.startsWith(url)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdmin(String path) {
        for (String url : AccessConfig.admin) {
            if (path.startsWith(url)) {
                return true;
            }
        }
        return false;
    }
}
