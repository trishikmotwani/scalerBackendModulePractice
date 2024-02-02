import com.javabasics.JSONPlaceholderApiInterface;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class JPApiTest {

    @Test
    public void testJPPhotosApi() throws IOException {

        var jpApi = JSONPlaceholderApiInterface.getJsonPlaceholderBaseUrl();
        var photos = jpApi.getPhotos().execute().body();

        assertNotNull(photos);
        assertEquals(5000, photos.size(), "There should be 5000 photos");

        var photo = photos.get(0);
        assertEquals(1, photo.getId());
    }
}
