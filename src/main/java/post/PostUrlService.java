package post;

import account.AccountType;
import board.Board;
import board.BoardRepository;
import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import customException.NoExistPostException;
import customException.NotAllowedAuthorityException;
import url.Request;
import url.Response;
import url.Session;

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

    public Response add(Request request) throws IOException, NoExistParameterException, InvalidValueException, NoExistBoardException, NotAllowedAuthorityException {
        try {
            if (!request.getSession().isSigned()) {
                throw new NotAllowedAuthorityException();
            }
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

            Post post = new Post(title, content, boardId, request.getSession().getSessionId());

            postRepository.add(post);
            board.addPost(post.getId());
            System.out.println("게시글이 작성되었습니다.");

            return Response.of();
        } catch (NumberFormatException e) {
            throw new InvalidValueException("boardId");
        }
    }

    public Response view(Request request) throws NoExistPostException, NoExistParameterException, InvalidValueException {
        try {
            Long postId = Long.parseLong(request.getParameter("postId"));
            Post post = findPost(postId);
            post.print();

            return Response.of();
        } catch (NumberFormatException e) {
            throw new InvalidValueException("postId", e);
        }
    }

    public Response remove(Request request) throws InvalidValueException, NoExistParameterException, NotAllowedAuthorityException {
        try {
            Long postId = Long.parseLong(request.getParameter("postId"));
            Post post = findPost(postId);
            if (hasAuthority(request)) {
                throw new NotAllowedAuthorityException();
            }

            Long boardId = post.getBoardId();
            postRepository.remove(postId);
            Board board = boardRepository.get(boardId);
            board.removePost(postId);
            System.out.println("게시글이 삭제되었습니다.");

            return Response.of();
        } catch (NumberFormatException | NoExistPostException e) {
            throw new InvalidValueException("postId", e);
        }
    }

    public Response edit(Request request) throws IOException, NoExistPostException, InvalidValueException, NoExistParameterException, NotAllowedAuthorityException {
        try {
            Long postId = Long.parseLong(request.getParameter("postId"));
            Post post = findPost(postId);
            if (hasAuthority(request)) {
                throw new NotAllowedAuthorityException();
            }
            System.out.print("제목을 입력해 주십시오. : ");
            String title = br.readLine();
            System.out.print("내용을 입력해 주십시오. : ");
            String content = br.readLine();
            post.update(title, content);
            System.out.println("게시글이 수정되었습니다.");

            return Response.of();
        } catch (NumberFormatException e) {
            throw new InvalidValueException("postId", e);
        }
    }

    private boolean hasAuthority(Request request) throws NoExistParameterException, NoExistPostException {
        Session session = request.getSession();
        Post post = findPost(Long.parseLong(request.getParameter("postId")));
        if (!session.isSigned()) return false;
        if (!session.getAccountType().equals(AccountType.ADMIN) && !session.getSessionId().equals(post.getWriterId()))
            return false;

        return true;
    }
}
