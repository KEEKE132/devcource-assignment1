import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import url.Request;

class UrlParserImplImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"/board", "/boards/", "/boards?test1=1&test2=2", "/boards/?test1=1&test2=2"})
    void parsingTest(String str) {
        try {
            Request request = new Request(str);
            System.out.println(request.getPath());
            System.out.println(request.getParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}