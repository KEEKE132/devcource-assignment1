import java.util.ArrayList;
import java.util.List;

public class PostList {
    private List<Post> posts = new ArrayList<>();

    public void add(Post post){
        posts.add(post);
    }

    public Post get(Long id){
        for(Post post : posts){
            if(post.getId().equals(id)) return post;
        }
        return null;
    }
    public boolean remove(Long id){
        return posts.removeIf(post -> post.getId().equals(id));
    }

    public boolean replace(Long id, Post post){
        for(int i=0; i<posts.size(); i++){
            if(posts.get(i).getId().equals(id)){
                posts.set(i, post);
                return true;
            }
        }
        return false;
    }

    public void printAll(){
        for(Post post : posts){
            post.print();
        }
    }

}
