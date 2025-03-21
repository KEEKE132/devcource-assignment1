package account;

import customException.InvalidValueException;
import customException.NoExistAccountException;
import customException.NoExistParameterException;
import customException.SignException;
import url.Request;
import url.Response;
import url.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountUrlService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final AccountRepository accountRepository;

    public AccountUrlService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Response signUp(Request request) throws InvalidValueException, IOException {
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
        return Response.of(request.getSession());
    }

    public Response signIn(Request request) throws SignException, IOException, NoExistParameterException {
        if (request.getSession().isSigned()) {
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
                return Response.of(new Session(account.getId()));
            }
            throw new SignException("wrong password");
        }
        throw new SignException(new NoExistAccountException());
    }

    public Response signOut(Request request) throws SignException, NoExistParameterException {
        if (!request.getSession().isSigned()) {
            throw new SignException("already signed out");
        }
        Response response = Response.of(new Session());
        System.out.println("로그아웃");
        return response;
    }

    public Response detail(Request request) throws NoExistParameterException, NoExistAccountException {
        Long id = Long.parseLong(request.getParameter("accountId"));
        Account account = accountRepository.get(id);
        if (account == null) {
            throw new NoExistAccountException();
        }
        System.out.println(id + "번 계정");
        account.print();
        return Response.of(request.getSession());
    }

    public Response edit(Request request) throws NoExistParameterException, IOException {
        Long id = Long.parseLong(request.getParameter("accountId"));
        System.out.print("새 비밀번호: ");
        String password = br.readLine();
        System.out.print("새 이메일: ");
        String email = br.readLine();
        accountRepository.get(id).setPassword(password);
        accountRepository.get(id).setEmail(email);
        return Response.of(request.getSession());
    }

    public Response remove(Request request) throws NoExistParameterException, InvalidValueException, NoExistAccountException {
        Long id = Long.parseLong(request.getParameter("accountId"));
        accountRepository.remove(id);
        return Response.of(request.getSession());
    }
}
