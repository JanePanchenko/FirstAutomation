package restassured;

import bean.Order;
import bean.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestAssuredTest {

    private static final String DEFAULT_PET_NAME = "Zhuzha";
    private static final String DEFAULT_PET_STATUS = "available";
    private static final String PET_ENTITY_PATH = "/pet";
    private static final String ORDER_ENTITY_PATH = "/store/order";
    private static final String SLASH = "/";
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    
    private RequestSpecification requestSpecification;

    @BeforeClass
    public void setup() {
        requestSpecification =
                new RequestSpecBuilder()
                        .setBaseUri(BASE_URL)
                        .addHeader("Content-Type", "application/json")
                        .build();
    }

    @Test
    @SneakyThrows
    public void shouldCreatePet() {
        Pet petRequest = buildPet();
        String petId = sendRequestForPetCreation(petRequest);
        String jsonPetResponse = getPetById(petId);

        Pet petResponse = convertBeanToJson(jsonPetResponse, Pet.class);

        assertThat(petResponse).isEqualTo(petRequest);
    }

    @Test
    @SneakyThrows
    public void shouldPlaceAnOrderForPet() {
        Pet petRequest = buildPet();
        String petId = sendRequestForPetCreation(petRequest);
        Order order = buildOrder(petId, 7);
        String orderId = sendRequestForOrderPlacement(order);
        String jsonOrderResponse = getOrderById(orderId);

        Order orderResponse = convertBeanToJson(jsonOrderResponse, Order.class);

        assertThat(orderResponse.getPetId()).isEqualTo(order.getPetId());
        assertThat(orderResponse.getQuantity()).isEqualTo(order.getQuantity());
    }

    private Pet buildPet() {
        return Pet.builder()
                .status(DEFAULT_PET_STATUS)
                .name(DEFAULT_PET_NAME)
                .build();
    }

    @SneakyThrows
    private <T> T convertBeanToJson(String jsonData, Class<T> clazz) {
        return new ObjectMapper().readValue(jsonData, clazz);
    }

    @SneakyThrows
    private String sendRequestForPetCreation(Pet petRequest) {
        return RestAssured
                .given()
                .spec(requestSpecification)
                .body(new ObjectMapper().writeValueAsString(petRequest))
                .when()
                .post(PET_ENTITY_PATH)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("id");
    }

    @SneakyThrows
    private String getPetById(String petId) {
        return RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get(PET_ENTITY_PATH + SLASH + petId)
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .jsonPath()
                .prettify();
    }

    private Order buildOrder(String petId, int quantity) {
        return Order.builder()
                .petId(petId)
                .quantity(quantity)
                .build();
    }

    @SneakyThrows
    private String sendRequestForOrderPlacement(Order order) {
        return RestAssured
                .given()
                .spec(requestSpecification)
                .body(new ObjectMapper().writeValueAsString(order))
                .when()
                .post(ORDER_ENTITY_PATH)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("id");
    }

    @SneakyThrows
    private String getOrderById(String orderId) {
        return RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get(ORDER_ENTITY_PATH + SLASH + orderId)
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .jsonPath()
                .prettify();
    }
}
