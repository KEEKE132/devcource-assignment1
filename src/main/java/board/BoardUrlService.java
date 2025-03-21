package board;

import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import post.Post;
import post.PostRepository;
import url.Request;
import url.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoardUrlService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public BoardUrlService(BoardRepository boardRepository, PostRepository postRepository) {
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
    }

    private Board findBoard(Long id) throws NoExistBoardException {
        Board board = boardRepository.get(id);
        if (board == null) {
            throw new NoExistBoardException();
        }
        return board;
    }

    private Board findBoard(String name) throws NoExistBoardException {
        Board board = boardRepository.get(name);
        if (board == null) {
            throw new NoExistBoardException();
        }
        return board;
    }

    public Response add(Request request) throws IOException, InvalidValueException {
        System.out.println("게시판을 생성합니다.");
        System.out.print("게시판 이름을 입력해 주십시오. : ");
        String title = br.readLine();
        Board board = new Board(title);
        boardRepository.add(board);
        System.out.println("게시판이 생성되었습니다.");
        return Response.of();
    }

    public Response view(Request request) throws NoExistBoardException, NoExistParameterException, InvalidValueException {
        try {
            String boardName = request.getParameter("boardName");
            Long boardId = boardRepository.get(boardName).getId();
            StringBuilder sb = new StringBuilder();
            sb.append(boardId).append("번 게시판\n");
            sb.append("글번호\t글 제목\t작성일").append("\n");
            for (Post p : postRepository.getPostsByBoardId(boardId)) {
                sb.append(p.getId()).append("\t").append(p.getTitle()).append("\t").append(p.getCreatedAt().toLocalDate()).append("\n");
            }
            System.out.println(sb);

            return Response.of();
        } catch (NumberFormatException e) {
            throw new InvalidValueException("boardId", e);
        }
    }

    public Response remove(Request request) throws InvalidValueException, NoExistParameterException {
        try {
            Long boardId = Long.parseLong(request.getParameter("boardId"));
            boardRepository.remove(boardId);
            postRepository.removeByBoardId(boardId);
            System.out.println("게시판이 삭제되었습니다.");
            return Response.of();
        } catch (NumberFormatException e) {
            throw new InvalidValueException("boardId", e);
        }
    }

    public Response edit(Request request) throws IOException, NoExistBoardException, InvalidValueException, NoExistParameterException {
        try {
            Long boardId = Long.parseLong(request.getParameter("boardId"));
            Board board = findBoard(boardId);
            System.out.print("게시판 이름을 입력해 주십시오. : ");
            String title = br.readLine();
            board.setTitle(title);
            System.out.println("게시판이 수정되었습니다.");

            return Response.of();
        } catch (NumberFormatException e) {
            throw new InvalidValueException("boardId", e);
        }
    }
}
