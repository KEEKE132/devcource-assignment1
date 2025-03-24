package me.feel5n.program;

import me.feel5n.SingletonContainer;
import me.feel5n.account.AccountUrlController;
import me.feel5n.board.BoardUrlController;
import me.feel5n.board.Controller;
import me.feel5n.customException.InvalidUrlException;
import me.feel5n.customException.InvalidValueException;
import me.feel5n.customException.NoExistAccountException;
import me.feel5n.customException.NoExistBoardException;
import me.feel5n.customException.NoExistParameterException;
import me.feel5n.customException.NoExistPostException;
import me.feel5n.customException.NotAllowedAuthorityException;
import me.feel5n.customException.SignException;
import me.feel5n.filter.Filter;
import me.feel5n.post.PostUrlController;
import me.feel5n.url.Request;
import me.feel5n.url.Response;
import me.feel5n.url.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UrlProgram implements Program {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Session session;

    public UrlProgram() {
        session = new Session();
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
            } catch (NotAllowedAuthorityException e) {
                System.out.println("권한이 없습니다.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("--------------------");
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private boolean readUrl() throws Exception {
        try {
            System.out.print("a");
            System.out.print("(" + (session.isSigned() ? session.getNickname() : "none") + ")");
            String url = br.readLine();
            Request request = new Request(url, session);
            if (checkExit(request)) return false;

            Response response;
            Filter filter = new Filter(request);
            Controller controller;
            if (filter.isValidRequest()) {
                controller = getController(request.getPath().get(0));
            } else {
                throw new NotAllowedAuthorityException();
            }
            if (controller == null) {
                throw new InvalidUrlException();
            } else {
                response = controller.enter(request);
            }
            if (response.hasSession()) {
                session = response.getSession();
            }
            return true;

        } catch (InvalidUrlException e) {
            System.out.println("URL이 올바르지 않습니다.");
            return true;
        }
    }

    private Controller getController(String code) {
        return switch (code) {
            case "posts" -> SingletonContainer.get(PostUrlController.class);
            case "boards" -> SingletonContainer.get(BoardUrlController.class);
            case "accounts" -> SingletonContainer.get(AccountUrlController.class);
            default -> null;
        };

    }

    private boolean checkExit(Request request) {
        String first = request.getPath().get(0);
        if (first.equals("exit")) {
            return true;
        }
        return false;
    }


}
