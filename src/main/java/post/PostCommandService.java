package post;

import customException.NoExistPostException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostCommandService {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private PostList postList = new PostList();
    private Long recentId = 0L;

    private Post findPost(Long id) throws NoExistPostException {
        Post post = postList.get(id);
        if (post == null) {
            throw new NoExistPostException();
        }
        return post;
    }

    private Long inputNumber() throws IOException {
        System.out.println("게시글 번호를 입력해 주십시오: ");
        Long number = Long.parseLong(br.readLine());
        return number;
    }

    public boolean checkWrite(String command) throws IOException {
        if (command.equals("작성")) {
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

        return new Post(++recentId, title, content);
    }

    public boolean checkRead(String command) throws IOException, NoExistPostException {
        if (command.equals("조회")) {
            readPost(inputNumber());
            return true;
        }
        return false;
    }

    public boolean checkReadAll(String command) {
        if (command.equals("목록")) {
            System.out.println("전체 게시글 목록입니다.");
            System.out.println("--------------------");
            postList.printAll();
            return true;
        }
        return false;
    }

    private void readPost(Long id) throws NoExistPostException {
        Post post = findPost(id);
        post.print();
    }

    public boolean checkDelete(String command) throws IOException {
        if (command.equals("삭제")) {
            deletePost(inputNumber());
            System.out.println("게시글이 삭제되었습니다.");
            return true;
        }
        return false;
    }

    private void deletePost(Long id) {
        postList.remove(id);
    }

    public boolean checkUpdate(String command) throws IOException, NoExistPostException {
        if (command.equals("수정")) {
            updatePost(inputNumber());
            System.out.println("게시글이 수정되었습니다.");
            return true;
        }
        return false;
    }

    private void updatePost(Long id) throws IOException, NoExistPostException {
        Post post = findPost(id);
        System.out.print("제목을 입력해 주십시오. : ");
        String title = br.readLine();
        System.out.print("내용을 입력해 주십시오. : ");
        String content = br.readLine();
        post.setTitle(title);
        post.setContent(content);

        postList.replace(post.getId(), post);
    }
}
