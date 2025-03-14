import CustomException.NoExistPostException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private PostList postList = new PostList();

    private Long recentId = 0L;

    public void run(){
        System.out.println("프로그램을 시작합니다");
        try {
            readCommand();
        } catch (IOException e) {
            System.out.println("IO 에러로 종료합니다.");
        }
    }

    private void readCommand() throws IOException {
        while(true) {
            try {
                System.out.print("명령어 > ");
                String command = br.readLine();
                if (checkExit(command)) break;
                else if (checkWrite(command)) ;
                else if (checkRead(command)) ;
                else if (checkDelete(command)) ;
                else if (checkUpdate(command)) ;
                else if (checkReadAll(command)) ;
                else System.out.println("존재하지 않는 명령어입니다.");
            } catch (NoExistPostException e) {
                System.out.println("존재하지 않는 게시글 입니다.");
            } finally {
                System.out.println("--------------------");
            }
        }
    }
    private Post findPost(Long id) throws IOException, NoExistPostException {
        Post post = postList.get(id);
        if(post==null) {
            throw new NoExistPostException();
        }
        return post;
    }

    private Long inputNumber() throws IOException {
        System.out.println("게시글 번호를 입력해 주십시오: ");
        Long number = Long.parseLong(br.readLine());
        return number;
    }

    private boolean checkExit(String command) {
        if(command.equals("exit")|| command.equals("종료")){
            System.out.println("프로그램이 종료됩니다.");
            return true;
        }
        return false;
    }

    private boolean checkWrite(String command) throws IOException {
        if(command.equals("작성")){
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

    private boolean checkRead(String command) throws IOException, NoExistPostException {
        if(command.equals("조회")){
            readPost(inputNumber());
            return true;
        }
        return false;
    }

    private boolean checkReadAll(String command){
        if(command.equals("목록")){
            System.out.println("전체 게시글 목록입니다.");
            System.out.println("--------------------");
            postList.printAll();
            return true;
        }
        return false;
    }

    private void readPost(Long id) throws IOException, NoExistPostException {
        Post post = findPost(id);
        post.print();
    }

    private boolean checkDelete(String command) throws IOException {
        if(command.equals("삭제")){
            deletePost(inputNumber());
            System.out.println("게시글이 삭제되었습니다.");
            return true;
        }
        return false;
    }

    private void deletePost(Long id) {
        postList.remove(id);
    }

    private boolean checkUpdate(String command) throws IOException, NoExistPostException {
        if(command.equals("수정")){
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
