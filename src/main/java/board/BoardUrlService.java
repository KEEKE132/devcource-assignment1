package board;

import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import url.UrlData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoardUrlService {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private BoardList boardList = new BoardList();

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
        boardList.add(writeBoard());
        System.out.println("게시판이 생성되었습니다.");

    }

    private Board writeBoard() throws IOException {
        System.out.print("게시판 이름을 입력해 주십시오. : ");
        String title = br.readLine();

        return new Board(title);
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
        Board board = findBoard(id);
        board.view();
    }

    private void readBoard(String name) throws NoExistBoardException {
        Board board = findBoard(name);
        board.view();
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
