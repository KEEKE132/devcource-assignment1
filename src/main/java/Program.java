import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
        System.out.println("존재하지 않는 명령어입니다.");
        readCommand();
    }

    private boolean checkExit(String command) {
        if(command.equals("exit")|| command.equals("종료")){
            System.out.println("프로그램이 종료됩니다.");
            return true;
        }
        return false;
    }
}
