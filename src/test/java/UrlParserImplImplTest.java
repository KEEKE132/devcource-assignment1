import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import url.UrlData;

class UrlParserImplImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"/board", "/boards/", "/boards?test1=1&test2=2", "/boards/?test1=1&test2=2"})
    void parsingTest(String str) {
        try {
            UrlData urlData = new UrlData(str);
            System.out.println(urlData.getPath());
            System.out.println(urlData.getParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}