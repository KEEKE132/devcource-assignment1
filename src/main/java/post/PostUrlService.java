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

    public boolean checkWrite(UrlData urlData) throws IOException {
        String first = urlData.getPath().get(0);
        if (first.equals("add")) {
            System.out.println("게시글을 작성합니다.");
            postList.add(writePost());
            System.out.println("게시글이 작성되었습니다.");
            return true;
        }
        return false;
    }

    private Post writePost() throws IOException {
        System.out.print("제목을 입력해 주십시오. : ");
        String title = br.readLine();
        System.out.print("내용을 입력해 주십시오. : ");
        String content = br.readLine();

        return new Post(title, content);
    }

    public boolean checkRead(UrlData urlData) throws NoExistPostException, NoExistParameterException, InvalidValueException {
        try {
            String first = urlData.getPath().get(0);
            if (first.equals("view")) {
                Long postId = Long.parseLong(urlData.getParameter("postId"));
                readPost(postId);
                return true;
            }
            return false;
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

    public boolean checkDelete(UrlData urlData) throws InvalidValueException, NoExistParameterException {
        String first = urlData.getPath().get(0);
        try {
            if (first.equals("remove")) {
                Long postId = Long.parseLong(urlData.getParameter("postId"));
                deletePost(postId);
                System.out.println("게시글이 삭제되었습니다.");
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            throw new InvalidValueException("postId", e);
        }
    }

    private void deletePost(Long id) {
        postList.remove(id);
    }

    public boolean checkUpdate(UrlData urlData) throws IOException, NoExistPostException, InvalidValueException, NoExistParameterException {
        String first = urlData.getPath().get(0);
        try {
            if (first.equals("edit")) {
                Long postId = Long.parseLong(urlData.getParameter("postId"));
                updatePost(postId);
                System.out.println("게시글이 수정되었습니다.");
                return true;
            }
            return false;
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
