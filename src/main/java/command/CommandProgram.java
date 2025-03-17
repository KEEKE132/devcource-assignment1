package command;

import customException.NoExistPostException;
import program.Program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandProgram implements Program {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final PostCommandService postService = new PostCommandService();

    public void run() {
        System.out.println("프로그램을 시작합니다");
        while (true) {
            try {
                if (!readCommand()) {
                    break;
                }
            } catch (IOException e) {
                System.out.println("IO 에러로 종료합니다.");
                break;
            } catch (NoExistPostException e) {
                System.out.println("존재하지 않는 게시글 입니다.");
            } finally {
                System.out.println("--------------------");
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private boolean readCommand() throws IOException, NoExistPostException {
        System.out.print("명령어 > ");
        String command = br.readLine();
        if (checkExit(command)) return false;
        else if (postService.checkWrite(command)) ;
        else if (postService.checkRead(command)) ;
        else if (postService.checkDelete(command)) ;
        else if (postService.checkUpdate(command)) ;
        else if (postService.checkReadAll(command)) ;
        else System.out.println("존재하지 않는 명령어입니다.");
        return true;
    }

    private boolean checkExit(String command) {
        if (command.equals("exit") || command.equals("종료")) {
            return true;
        }
        return false;
    }
}
