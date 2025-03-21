package program;

import account.Account;
import account.AccountRepository;
import account.AccountUrlController;
import account.AccountUrlService;
import board.Board;
import board.BoardRepository;
import board.BoardUrlController;
import board.BoardUrlService;
import customException.InvalidUrlException;
import customException.InvalidValueException;
import customException.NoExistAccountException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import customException.NoExistPostException;
import customException.SignException;
import post.Post;
import post.PostRepository;
import post.PostUrlController;
import post.PostUrlService;
import url.Response;
import url.UrlData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UrlProgram implements Program {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final PostUrlController postUrlController;
    private final BoardUrlController boardUrlController;
    private final AccountUrlController accountUrlController;
    private String sessionId = null;

    public UrlProgram() {
        PostRepository postRepository = new PostRepository();
        BoardRepository boardRepository = new BoardRepository();
        AccountRepository accountRepository = new AccountRepository();
        PostUrlService postUrlService = new PostUrlService(postRepository, boardRepository);
        BoardUrlService boardUrlService = new BoardUrlService(boardRepository, postRepository);
        AccountUrlService accountUrlService = new AccountUrlService(accountRepository);
        this.accountUrlController = new AccountUrlController(accountUrlService);
        this.postUrlController = new PostUrlController(postUrlService);
        this.boardUrlController = new BoardUrlController(boardUrlService);

        try {
            boardRepository.add(new Board("1"));
            boardRepository.add(new Board("2"));
            postRepository.add(new Post("11", "11", 1L));
            postRepository.add(new Post("11", "11", 1L));
            accountRepository.add(new Account("123", "123", "1@1", "keeke"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println("프로그램을 시작합니다");
        while (true) {
            try {
                if (!readUrl()) {
                    break;
                }
            } catch (IOException e) {
                System.out.println("IO 에러로 종료합니다.");
                break;
            } catch (NoExistPostException e) {
                System.out.println("존재하지 않는 게시글 입니다.");
            } catch (NoExistParameterException e) {
                System.out.println("필요한 파라미터가 없습니다.: " + e.getMessage());
            } catch (InvalidValueException e) {
                System.out.println("값이 올바르지 않습니다.: " + e.getMessage());
            } catch (NoExistBoardException e) {
                System.out.println("존재하지 않는 게시판 입니다.");
            } catch (NoExistAccountException e) {
                System.out.println("존재하지 않는 계정입니다.");
            } catch (SignException e) {
                System.out.println("로그인에 문제가 발생했습니다.");
                if (e.getMessage() != null) {
                    System.out.println(e.getMessage());
                }
            } finally {
                System.out.println("--------------------");
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private boolean readUrl() throws IOException, InvalidValueException, NoExistPostException, NoExistParameterException, NoExistBoardException, NoExistAccountException, SignException {
        try {
            System.out.print("a");
            String url = br.readLine();
            UrlData urlData = new UrlData(url);
            urlData.addParameter("sessionId", sessionId);
            if (checkExit(urlData)) return false;

            if (postUrlController.checkPath(urlData)) {
                Response response = postUrlController.enter(urlData);
                return true;
            } else if (boardUrlController.checkPath(urlData)) {
                Response response = boardUrlController.enter(urlData);
                return true;
            } else if (accountUrlController.checkPath(urlData)) {
                Response response = accountUrlController.enter(urlData);
                if (response.hasParameter("signinId")) this.sessionId = response.getParameter("signinId");
                else if (response.hasParameter("signoutId")) this.sessionId = null;
                return true;
            }

            throw new InvalidUrlException();
        } catch (InvalidUrlException e) {
            System.out.println("URL이 올바르지 않습니다.");
            return true;
        }
    }

    private boolean checkExit(UrlData urlData) {
        String first = urlData.getPath().get(0);
        if (first.equals("exit")) {
            return true;
        }
        return false;
    }


}
