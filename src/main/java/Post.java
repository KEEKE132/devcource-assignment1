public class Post {
    private Long id;
    private String title;
    private String content;

    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("번 게시글").append("\n");
        sb.append("제목: ").append(title).append("\n");
        sb.append("내용: ").append(content).append("\n");
        System.out.println(sb);
    }
}
