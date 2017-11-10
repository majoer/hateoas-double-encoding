package no.mats.hateoasdoubleencoding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HateoasDoubleEncoding.class)
@WebAppConfiguration
public class EncodingControllerTest {

    private final String weaponOfChoice = "skarp_ljå";
    private final String cheatCode = "fjærkre";

    @Test
    public void should_encode_whole_url_using_ControllerLinkBuilder_toUriComponentsBuilder() {
        String href = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(AwesomeController.class)
                .challengeLionsDenTestEndpoint(this.weaponOfChoice, this.cheatCode))
                .toUriComponentsBuilder()
                .build()
                .toUriString();

        assertEquals("http://localhost/l%C3%B8vens/hule/skarp_lj%C3%A5?cheatCode=fj%C3%A6rkre", href);
        //-> Instead we get    http://localhost/l%C3%B8vens/hule/skarp_lj%25C3%25A5?cheatCode=fj%25C3%25A6rkre
    }

    @Test
    public void should_encode_whole_url_using_ControllerLinkBuilder_withSelfRel() {
        String href = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(AwesomeController.class)
                .challengeLionsDenTestEndpoint(this.weaponOfChoice, this.cheatCode))
                .withSelfRel()
                .getHref();

        assertEquals("http://localhost/l%C3%B8vens/hule/skarp_lj%C3%A5?cheatCode=fj%C3%A6rkre", href);
        //-> Instead we get    http://localhost/løvens/hule/skarp_lj%C3%A5?cheatCode=fj%C3%A6rkre
    }
}
