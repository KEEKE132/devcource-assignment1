import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Program {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private Post post;

    public void run(){
        System.out.println("프로그램을 시작합니다");
        try {
            readCommand();
        } catch (IOException e) {
            System.out.println("IO 에러로 종료합니다.");
        }
    }

    private void readCommand() throws IOException {
        System.out.print("명령어 > ");
        String command = br.readLine();
        if (checkExit(command)) return;
        else if (checkWrite(command));
        else if (checkRead(command));
        else if (checkDelete(command));
        else if (checkUpdate(command));
        else System.out.println("존재하지 않는 명령어입니다.");
        readCommand();
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
            if(post!=null) {
                System.out.println("이미 게시글이 존재합니다.");
                return true;
            }
            System.out.println("게시글을 작성합니다.");
            writePost();
            System.out.println("게시글이 작성되었습니다.");
            return true;
        }
        return false;
    }

    private void writePost() throws IOException {
        System.out.print("제목을 입력해 주십시오. : ");
        String title = br.readLine();
        System.out.print("내용을 입력해 주십시오. : ");
        String content = br.readLine();

        post = new Post();
        post.setTitle(title);
        post.setContent(content);
    }

    private boolean checkRead(String command) {
        if(command.equals("조회")){
            if(post==null) {
                System.out.println("작성된 게시글이 없습니다.");
                return true;
            }
            readPost();
            return true;
        }
        return false;
    }

    private void readPost() {
        StringBuilder sb = new StringBuilder();
        sb.append("제목: ").append(post.getTitle()).append("\n");
        sb.append("내용: ").append(post.getContent()).append("\n");
        System.out.println(sb);
    }

    private boolean checkDelete(String command) {
        if(command.equals("삭제")){
            if(post==null) {
                System.out.println("작성된 게시글이 없습니다.");
                return true;
            }
            deletePost();
            System.out.println("게시글이 삭제되었습니다.");
            return true;
        }
        return false;
    }

    private void deletePost() {
        post = null;
    }

    private boolean checkUpdate(String command) throws IOException {
        if(command.equals("수정")){
            if(post==null){
                System.out.println("작성된 게시글이 없습니다.");
                return true;
            }
            updatePost();
            System.out.println("게시글이 수정되었습니다.");
            return true;
        }
        return false;
    }

    private void updatePost() throws IOException {
        writePost();
    }
}
