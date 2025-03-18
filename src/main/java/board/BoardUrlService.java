package board;

import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import post.Post;
import post.PostList;
import url.UrlData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoardUrlService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BoardList boardList;
    private final PostList postList;

    public BoardUrlService(BoardList boardList, PostList postList) {
        this.boardList = boardList;
        this.postList = postList;
    }

    private Board findBoard(Long id) throws NoExistBoardException {
        Board board = boardList.get(id);
        if (board == null) {
            throw new NoExistBoardException();
        }
        return board;
    }

    private Board findBoard(String name) throws NoExistBoardException {
        Board board = boardList.get(name);
        if (board == null) {
            throw new NoExistBoardException();
        }
        return board;
    }

    public void checkWrite(UrlData urlData) throws IOException, InvalidValueException {
        System.out.println("게시판을 생성합니다.");
        writeBoard();
        System.out.println("게시판이 생성되었습니다.");

    }

    private void writeBoard() throws IOException, InvalidValueException {
        System.out.print("게시판 이름을 입력해 주십시오. : ");
        String title = br.readLine();

        boardList.add(new Board(title));
    }

    public void checkRead(UrlData urlData) throws NoExistBoardException, NoExistParameterException, InvalidValueException {
        try {
            String boardName = urlData.getParameter("boardName");
            readBoard(boardName);
        } catch (NumberFormatException e) {
            throw new InvalidValueException("boardId", e);
        }
    }

//    public boolean checkReadAll(UrlData urlData) {
//        String first = urlData.getPath().get(0);
//        if (command.equals("목록")) {
//            System.out.println("전체 게시글 목록입니다.");
//            System.out.println("--------------------");
//            boardList.printAll();
//            return true;
//        }
//        return false;
//    }

    private void readBoard(Long id) throws NoExistBoardException {
    }

    private void readBoard(String name) throws NoExistBoardException {
        Long boardId = boardList.get(name).getId();
        StringBuilder sb = new StringBuilder();
        sb.append(boardId).append("번 게시판\n");
        sb.append("글 번호\t글 제목\t작성일").append("\n");
        for (Post p : postList.getPostsByBoardId(boardId)) {
            sb.append(p.getId()).append("\t").append(p.getTitle()).append("\t").append(p.getCreatedAt()).append("\n");
        }
        System.out.println(sb);
    }

    public void checkDelete(UrlData urlData) throws InvalidValueException, NoExistParameterException {
        try {
            Long boardId = Long.parseLong(urlData.getParameter("boardId"));
            deleteBoard(boardId);
            System.out.println("게시판이 삭제되었습니다.");
        } catch (NumberFormatException e) {
            throw new InvalidValueException("boardId", e);
        }
    }

    private void deleteBoard(Long id) {
        boardList.remove(id);
        postList.removeByBoardId(id);
    }

    public void checkUpdate(UrlData urlData) throws IOException, NoExistBoardException, InvalidValueException, NoExistParameterException {
        try {
            Long boardId = Long.parseLong(urlData.getParameter("boardId"));
            updateBoard(boardId);
            System.out.println("게시판이 수정되었습니다.");
        } catch (NumberFormatException e) {
            throw new InvalidValueException("boardId", e);
        }
    }

    private void updateBoard(Long id) throws IOException, NoExistBoardException {
        Board board = findBoard(id);
        System.out.print("게시판 이름을 입력해 주십시오. : ");
        String title = br.readLine();
        board.setTitle(title);
    }
}
