package board;

public class Board {
    private static Long idCounter = 1L;

    private final Long id;
    private String title;

    public Board(String title) {
        this.id = idCounter++;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void view() {
        StringBuilder sb = new StringBuilder();
        sb.append("글 번호\t글 제목\t작성일").append("\n");
        System.out.println(sb);
    }
}
