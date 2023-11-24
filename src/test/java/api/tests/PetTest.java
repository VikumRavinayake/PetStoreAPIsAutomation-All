package api.tests;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.PetEndpoint;
import api.payloads.Pet;
import io.restassured.response.Response;

public class PetTest {
    private Pet payload;
    private Faker fake;
    private Pet.Category category;
    private Pet.Tag tagOne;
    private List<String> photoUrls;
    private List<Pet.Tag> tags;

    @BeforeClass
    public void setup(){
        fake = new Faker();
        payload = new Pet();
        category = new Pet.Category(fake.idNumber().hashCode(), fake.name().firstName());
        tagOne = new Pet.Tag(fake.idNumber().hashCode(), fake.name().nameWithMiddle());
        photoUrls = new ArrayList<String>();
        tags = new ArrayList<Pet.Tag>();
        photoUrls.add("https://www.youtube.com/watch?v=Typ9U6k6g5s");
        tags.add(tagOne);


        payload.setId(fake.idNumber().hashCode());
        payload.setCategory(category);
        payload.setName(fake.name().username());
        payload.setPhotoUrls(photoUrls);
        payload.setTags(tags);
        payload.setStatus("available");
        System.out.println(payload.getTags());
        System.out.println(payload.getId());

    }
    // test - add new pet
    @Test(priority=1)
    public void testAddPet(){
        Response response = PetEndpoint.addPet(payload);
        // validation section
        Assert.assertEquals(response.getStatusCode(), 200 );
    }
}
