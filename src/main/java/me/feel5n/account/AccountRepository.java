package me.feel5n.account;

import me.feel5n.customException.DuplicateAccountNameException;
import me.feel5n.customException.InvalidValueException;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private List<Account> accounts = new ArrayList<>();

    public void add(Account account) throws InvalidValueException {
        if (get(account.getUsername()) != null) {
            throw new InvalidValueException("username", new DuplicateAccountNameException("username"));
        }
        accounts.add(account);
    }

    public Account get(Long id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) return account;
        }
        return null;
    }

    public Account get(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) return account;
        }
        return null;
    }

    public boolean remove(Long id) {
        return accounts.removeIf(account -> account.getId().equals(id));
    }

    public boolean replace(Long id, Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId().equals(id)) {
                accounts.set(i, account);
                return true;
            }
        }
        return false;
    }
}
