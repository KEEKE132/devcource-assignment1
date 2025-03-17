package post;

import customException.InvalidValueException;
import customException.NoExistParameterException;
import customException.NoExistPostException;
import url.UrlData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostUrlService {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private PostList postList = new PostList();

    private Post findPost(Long id) throws NoExistPostException {
        Post post = postList.get(id);
        if (post == null) {
            throw new NoExistPostException();
        }
        return post;
    }

    public void checkWrite(UrlData urlData) throws IOException {
        System.out.println("게시글을 작성합니다.");
        postList.add(writePost());
        System.out.println("게시글이 작성되었습니다.");
    }

    private Post writePost() throws IOException {
        System.out.print("제목을 입력해 주십시오. : ");
        String title = br.readLine();
        System.out.print("내용을 입력해 주십시오. : ");
        String content = br.readLine();

        return new Post(title, content);
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
        } catch (NumberFormatException e) {
            throw new InvalidValueException("postId", e);
        }
    }

    private void deletePost(Long id) {
        postList.remove(id);
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
        post.setTitle(title);
        post.setContent(content);
    }
}
