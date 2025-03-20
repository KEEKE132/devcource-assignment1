package account;

import customException.InvalidValueException;
import customException.NoExistAccountException;
import customException.NoExistParameterException;
import customException.SignException;
import url.UrlData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountUrlService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final AccountRepository accountRepository;

    private Account signInAccount = null;

    public AccountUrlService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account signUp() throws InvalidValueException, IOException {
        System.out.print("아이디: ");
        String username = br.readLine();
        System.out.print("비밀번호: ");
        String password = br.readLine();
        System.out.print("이메일: ");
        String email = br.readLine();
        System.out.print("닉네임: ");
        String nickname = br.readLine();
        Account account = new Account(username, password, email, nickname);
        accountRepository.add(account);
        return account;
    }

    public Account signIn() throws SignException, IOException {
        if (signInAccount != null) {
            throw new SignException("Already signed in");
        }
        System.out.print("아이디: ");
        String username = br.readLine();
        System.out.print("비밀번호: ");
        String password = br.readLine();
        Account account = accountRepository.get(username);
        if (account != null) {
            if (account.getPassword().equals(password)) {
                signInAccount = account;
                System.out.println("로그인");
                return account;
            }
            throw new SignException("wrong password");
        }
        throw new SignException(new NoExistAccountException());
    }

    public void signOut() throws SignException {
        if (signInAccount == null) {
            throw new SignException("already signed out");
        }
        signInAccount = null;
        System.out.println("로그아웃");
    }

    public void detail(UrlData urlData) throws NoExistParameterException, NoExistAccountException {
        Long id = Long.parseLong(urlData.getParameter("accountId"));
        Account account = accountRepository.get(id);
        if (account == null) {
            throw new NoExistAccountException();
        }
        System.out.println(id + "번 계정");
        account.print();
    }

    public void edit(UrlData urlData) throws NoExistParameterException, IOException {
        Long id = Long.parseLong(urlData.getParameter("accountId"));
        System.out.print("새 비밀번호: ");
        String password = br.readLine();
        System.out.print("새 이메일: ");
        String email = br.readLine();
        accountRepository.get(id).setPassword(password);
        accountRepository.get(id).setEmail(email);
    }

    public void remove(UrlData urlData) throws NoExistParameterException, InvalidValueException, NoExistAccountException {
        Long id = Long.parseLong(urlData.getParameter("accountId"));
        accountRepository.remove(id);
    }
}
