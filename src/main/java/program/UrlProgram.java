package program;

import board.BoardRepository;
import board.BoardUrlController;
import board.BoardUrlService;
import customException.InvalidUrlException;
import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import customException.NoExistPostException;
import post.PostRepository;
import post.PostUrlController;
import post.PostUrlService;
import url.UrlData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UrlProgram implements Program {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final PostUrlController postUrlController;
    private final BoardUrlController boardUrlController;

    public UrlProgram() {
        PostRepository postRepository = new PostRepository();
        BoardRepository boardRepository = new BoardRepository();
        PostUrlService postUrlService = new PostUrlService(postRepository, boardRepository);
        BoardUrlService boardUrlService = new BoardUrlService(boardRepository, postRepository);
        this.postUrlController = new PostUrlController(postUrlService);
        this.boardUrlController = new BoardUrlController(boardUrlService);
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
            } finally {
                System.out.println("--------------------");
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private boolean readUrl() throws IOException, InvalidValueException, NoExistPostException, NoExistParameterException, NoExistBoardException {
        try {
            System.out.print("a");
            String url = br.readLine();
            UrlData urlData = new UrlData(url);
            if (checkExit(urlData)) return false;

            if (postUrlController.checkPath(urlData)) return true;
            else if (boardUrlController.checkPath(urlData)) return true;
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
