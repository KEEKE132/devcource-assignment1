package post;

import board.Board;
import board.BoardRepository;
import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import customException.NoExistPostException;
import url.Request;
import url.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostUrlService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public PostUrlService(PostRepository postRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
    }

    private Post findPost(Long id) throws NoExistPostException {
        Post post = postRepository.get(id);
        if (post == null) {
            throw new NoExistPostException();
        }
        return post;
    }

    public Response add(Request request) throws IOException, NoExistParameterException, InvalidValueException, NoExistBoardException {
        try {
            System.out.println("게시글을 작성합니다.");
            Long boardId = Long.parseLong(request.getParameter("boardId"));
            Board board = boardRepository.get(boardId);
            if (board == null) {
                throw new NoExistBoardException();
            }
            System.out.print("제목을 입력해 주십시오. : ");
            String title = br.readLine();
            System.out.print("내용을 입력해 주십시오. : ");
            String content = br.readLine();

            Post post = new Post(title, content, boardId);

            postRepository.add(post);
            board.addPost(post.getId());
            System.out.println("게시글이 작성되었습니다.");

            return Response.of(request.getSession());
        } catch (NumberFormatException e) {
            throw new InvalidValueException("boardId");
        }
    }

    public Response view(Request request) throws NoExistPostException, NoExistParameterException, InvalidValueException {
        try {
            Long postId = Long.parseLong(request.getParameter("postId"));
            Post post = findPost(postId);
            post.print();

            return Response.of(request.getSession());
        } catch (NumberFormatException e) {
            throw new InvalidValueException("postId", e);
        }
    }

    public Response remove(Request request) throws InvalidValueException, NoExistParameterException {
        try {
            Long postId = Long.parseLong(request.getParameter("postId"));
            Long boardId = findPost(postId).getBoardId();
            postRepository.remove(postId);
            Board board = boardRepository.get(boardId);
            board.removePost(postId);
            System.out.println("게시글이 삭제되었습니다.");

            return Response.of(request.getSession());
        } catch (NumberFormatException | NoExistPostException e) {
            throw new InvalidValueException("postId", e);
        }
    }

    public Response edit(Request request) throws IOException, NoExistPostException, InvalidValueException, NoExistParameterException {
        try {
            Long postId = Long.parseLong(request.getParameter("postId"));
            Post post = findPost(postId);
            System.out.print("제목을 입력해 주십시오. : ");
            String title = br.readLine();
            System.out.print("내용을 입력해 주십시오. : ");
            String content = br.readLine();
            post.update(title, content);
            System.out.println("게시글이 수정되었습니다.");

            return Response.of(request.getSession());
        } catch (NumberFormatException e) {
            throw new InvalidValueException("postId", e);
        }
    }
}
