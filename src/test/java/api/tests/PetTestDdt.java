package api.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.PetEndpoint;
import api.payloads.Pet;
import api.utilities.ProviderData;
import io.restassured.response.Response;

public class PetTestDdt {
    private Pet petpayload;
    private Pet.Tag tagOne;
    private List<String> photoUrls;
    private List<Pet.Tag> tags;

    @Test(priority = 1, dataProvider = "petData", dataProviderClass = ProviderData.class)
    public void testAddPet(String id, String cid, String cname, String name, String url, String tid, String tname, String status){
        petpayload = new Pet();
        photoUrls = new ArrayList<String>();
        tags = new ArrayList<Pet.Tag>();
        //System.out.println("Value : " + cname);
        tagOne = new Pet.Tag(Integer.parseInt(tid), tname);
        photoUrls.add(url);
        tags.add(tagOne);


        petpayload.setId(Integer.parseInt(id));
        petpayload.setCategory(new Pet.Category(Integer.parseInt(cid), cname));
        petpayload.setName(name);
        petpayload.setPhotoUrls(photoUrls);
        petpayload.setTags(tags);
        petpayload.setStatus(status);
        
        Response response = PetEndpoint.addPet(petpayload);
        // validation section
        Assert.assertEquals(response.getStatusCode(), 200 );
    }
}
