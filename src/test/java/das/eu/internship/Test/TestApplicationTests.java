package das.eu.internship.Test;

import das.eu.internship.Test.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.BodyInserters;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient(timeout = "PT1M")//30 seconds
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class TestApplicationTests {

	private String serverURL;
	@LocalServerPort
	private int port;

	private final WebTestClient webTestClient;

	@Mock
	private HttpServletRequest request;

	@BeforeAll
	public void setUp(){
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		serverURL = String.format("%s:%s", "localhost", port);

	}

	@Test
	void saveValidPerson(){
		PersonDTO personDTO = new PersonDTO();
		personDTO.setName("Alex");
		personDTO.setAddress(26L);

		//act
		PersonDTO savedPerson = this.webTestClient
				.post()
				.uri(serverURL + "/api/service/person/save")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.body(BodyInserters.fromValue(personDTO))
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(PersonDTO.class)
				.returnResult()
				.getResponseBody();

		assertNotNull(savedPerson);

		HttpStatus deletePerson = this.webTestClient
				.delete()
				.uri(serverURL + "/api/service/person/deleteById/" + savedPerson.getId())
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(HttpStatus.class)
				.returnResult()
				.getResponseBody();
	}


	@Test
	void getRequest(){
		PersonDTO personDTO = new PersonDTO();
		personDTO.setName("Alex");
		personDTO.setAge(26L);
		Long id = 1L;

		PersonDTO savedPerson = this.webTestClient
				.post()
				.uri(serverURL + "/api/service/person/save")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.body(BodyInserters.fromValue(personDTO))
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(PersonDTO.class)
				.returnResult()
				.getResponseBody();

		assertNotNull(savedPerson);

		PersonDTO getPerson = this.webTestClient
				.get()
				.uri(serverURL + "/api/service/person/getById/" + savedPerson.getId())
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(PersonDTO.class)
				.returnResult()
				.getResponseBody();

		HttpStatus deletePerson = this.webTestClient
				.delete()
				.uri(serverURL + "/api/service/person/deleteById/" + savedPerson.getId())
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(HttpStatus.class)
				.returnResult()
				.getResponseBody();


		Assertions.assertNotNull(getPerson);
		Assertions.assertEquals(savedPerson.getId(), getPerson.getId());
		Assertions.assertEquals(savedPerson.getName(), getPerson.getName());
		Assertions.assertEquals(savedPerson.getId(), getPerson.getId());
	}

	@Test
	void deleterRequest(){


		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(1L);
		personDTO.setName("Alex");
		personDTO.setAge(26L);

		PersonDTO savedPerson = this.webTestClient
				.post()
				.uri(serverURL + "/api/service/person/save")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.body(BodyInserters.fromValue(personDTO))
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(PersonDTO.class)
				.returnResult()
				.getResponseBody();


		HttpStatus deletePerson = this.webTestClient
				.delete()
				.uri(serverURL + "/api/service/person/deleteById/" + savedPerson.getId())
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(HttpStatus.class)
				.returnResult()
				.getResponseBody();


		Assertions.assertNotNull(savedPerson);
	}
	@Test
	void updateRequest(){


		PersonDTO saveDTO = new PersonDTO();
		saveDTO.setName("Alex");
		saveDTO.setAge(26L);

		PersonDTO savedPerson = this.webTestClient
				.post()
				.uri(serverURL + "/api/service/person/save")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.body(BodyInserters.fromValue(saveDTO))
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(PersonDTO.class)
				.returnResult()
				.getResponseBody();

		assertNotNull(savedPerson);
		assertNotNull(savedPerson.getId());

		PersonDTO updateDto = new PersonDTO();
		updateDto.setId(savedPerson.getId());
		updateDto.setName("Sasha");
		updateDto.setAge(18L);


		PersonDTO updatePerson = this.webTestClient
				.put()
				.uri(serverURL + "/api/service/person/update")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.body(BodyInserters.fromValue(updateDto))
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(PersonDTO.class)
				.returnResult()
				.getResponseBody();

		assertNotNull(updatePerson);

		HttpStatus deletePerson = this.webTestClient
				.delete()
				.uri(serverURL + "/api/service/person/deleteById/" + savedPerson.getId())
				.accept(APPLICATION_JSON)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(HttpStatus.class)
				.returnResult()
				.getResponseBody();


		Assertions.assertNotNull(savedPerson);
		Assertions.assertEquals(updateDto.getId(), updatePerson.getId());
		Assertions.assertEquals(updateDto.getName(), updatePerson.getName());
	}


	private static List<PersonDTO> getValidPerson(){

		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(1L);
		personDTO.setName("Alex");
		personDTO.setAge(26L);

		PersonDTO personDTO1 = new PersonDTO();
		personDTO1.setId(2L);
		personDTO1.setName("Alex");
		personDTO1.setAge(26L);

		PersonDTO personDTO2 = new PersonDTO();
		personDTO2.setId(3L);
		personDTO2.setName("Alex");
		personDTO2.setAge(26L);

		PersonDTO personDTO3 = new PersonDTO();
		personDTO3.setId(4L);
		personDTO3.setName("Alex");
		personDTO3.setAge(26L);

		List<PersonDTO> personDTOList = new ArrayList<>();
		personDTOList.add(personDTO);
		personDTOList.add(personDTO1);
		personDTOList.add(personDTO2);
		personDTOList.add(personDTO3);

		return personDTOList;
	}

}