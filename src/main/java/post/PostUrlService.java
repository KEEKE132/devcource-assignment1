package post;

import board.Board;
import board.BoardRepository;
import customException.InvalidValueException;
import customException.NoExistBoardException;
import customException.NoExistParameterException;
import customException.NoExistPostException;
import url.UrlData;

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

    public void checkWrite(UrlData urlData) throws IOException, NoExistParameterException, InvalidValueException, NoExistBoardException {
        System.out.println("게시글을 작성합니다.");
        writePost(urlData);
        System.out.println("게시글이 작성되었습니다.");
    }

    private void writePost(UrlData urlData) throws IOException, NoExistParameterException, InvalidValueException, NoExistBoardException {
        try {
            Long boardId = Long.parseLong(urlData.getParameter("boardId"));
            Board board = boardRepository.get(boardId);
            System.out.print("제목을 입력해 주십시오. : ");
            String title = br.readLine();
            System.out.print("내용을 입력해 주십시오. : ");
            String content = br.readLine();

            Post post = new Post(title, content, boardId);
            if (board == null) {
                throw new NoExistBoardException();
            }
            postRepository.add(post);
            board.addPost(post.getId());

        } catch (NumberFormatException e) {
            throw new InvalidValueException("boardId");
        }

    }

    public void checkRead(UrlData urlData) throws NoExistPostException, NoExistParameterException, InvalidValueException {
        try {
            Long postId = Long.parseLong(urlData.getParameter("postId"));
            readPost(postId);
        } catch (NumberFormatException e) {
            throw new InvalidValueException("postId", e);
        }
    }

//    public boolean checkReadAll(UrlData urlData) {
//        String first = urlData.getPath().get(0);
//        if (command.equals("목록")) {
//            System.out.println("전체 게시글 목록입니다.");
//            System.out.println("--------------------");
//            postList.printAll();
//            return true;
//        }
//        return false;
//    }

    private void readPost(Long id) throws NoExistPostException {
        Post post = findPost(id);
        post.print();
    }

    public void checkDelete(UrlData urlData) throws InvalidValueException, NoExistParameterException {
        try {
            Long postId = Long.parseLong(urlData.getParameter("postId"));
            deletePost(postId);
            System.out.println("게시글이 삭제되었습니다.");
        } catch (NumberFormatException | NoExistPostException e) {
            throw new InvalidValueException("postId", e);
        }
    }

    private void deletePost(Long id) throws NoExistPostException {
        Long boardId = findPost(id).getBoardId();
        postRepository.remove(id);
    }

    public void checkUpdate(UrlData urlData) throws IOException, NoExistPostException, InvalidValueException, NoExistParameterException {
        try {
            Long postId = Long.parseLong(urlData.getParameter("postId"));
            updatePost(postId);
            System.out.println("게시글이 수정되었습니다.");
        } catch (NumberFormatException e) {
            throw new InvalidValueException("postId", e);
        }
    }

    private void updatePost(Long id) throws IOException, NoExistPostException {
        Post post = findPost(id);
        System.out.print("제목을 입력해 주십시오. : ");
        String title = br.readLine();
        System.out.print("내용을 입력해 주십시오. : ");
        String content = br.readLine();
        post.update(title, content);
    }
}
