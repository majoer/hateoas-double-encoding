package no.mats.hateoasdoubleencoding;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AwesomeController {

    @RequestMapping(value = "løvens/hule/{weapon}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity challengeLionsDenTestEndpoint(
            @PathVariable("weapon") String weaponOfChoice,
            @RequestParam("cheatCode") String cheatCode) {

        boolean survived = "Bazooka".equals(weaponOfChoice) || "Super Saiyan".equals(cheatCode);

        return ResponseEntity.ok()
                .body("{ returnedInNeatCoffin: " + survived + " }");
    }
}
