package account;

import customException.InvalidValueException;
import customException.NoExistAccountException;
import customException.NoExistParameterException;
import customException.SignException;
import url.Response;
import url.UrlData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountUrlService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final AccountRepository accountRepository;

    public AccountUrlService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Response signUp(UrlData urlData) throws InvalidValueException, IOException {
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
        return Response.of("accountId", account.getId().toString());
    }

    public Response signIn(UrlData urlData) throws SignException, IOException, NoExistParameterException {
        if (urlData.getParameter("sessionId") != null) {
            throw new SignException("Already signed in");
        }
        System.out.print("아이디: ");
        String username = br.readLine();
        System.out.print("비밀번호: ");
        String password = br.readLine();
        Account account = accountRepository.get(username);
        if (account != null) {
            if (account.getPassword().equals(password)) {
                System.out.println("로그인");
                return Response.of("signinId", account.getId().toString());
            }
            throw new SignException("wrong password");
        }
        throw new SignException(new NoExistAccountException());
    }

    public Response signOut(UrlData urlData) throws SignException, NoExistParameterException {
        if (urlData.getParameter("sessionId") == null) {
            throw new SignException("already signed out");
        }
        Response response = Response.of("signoutId", urlData.getParameter("sessionId"));
        System.out.println("로그아웃");
        return response;
    }

    public Response detail(UrlData urlData) throws NoExistParameterException, NoExistAccountException {
        Long id = Long.parseLong(urlData.getParameter("accountId"));
        Account account = accountRepository.get(id);
        if (account == null) {
            throw new NoExistAccountException();
        }
        System.out.println(id + "번 계정");
        account.print();
        return Response.of("accountId", id.toString());
    }

    public Response edit(UrlData urlData) throws NoExistParameterException, IOException {
        Long id = Long.parseLong(urlData.getParameter("accountId"));
        System.out.print("새 비밀번호: ");
        String password = br.readLine();
        System.out.print("새 이메일: ");
        String email = br.readLine();
        accountRepository.get(id).setPassword(password);
        accountRepository.get(id).setEmail(email);
        return Response.of("accountId", id.toString());
    }

    public Response remove(UrlData urlData) throws NoExistParameterException, InvalidValueException, NoExistAccountException {
        Long id = Long.parseLong(urlData.getParameter("accountId"));
        accountRepository.remove(id);
        return Response.of("accountId", id.toString());
    }
}
