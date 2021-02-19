package crioproject.xmeme.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import crioproject.xmeme.Model.Meme;
import crioproject.xmeme.Service.MemeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UiController {

    private final MemeService memeService;

    public UiController(MemeService memeService) {
        this.memeService = memeService;
    }

    //Displays the list of memes on front-end and calls /memes api
    @GetMapping("/home")
    public String viewMemePage(Model model){
        String url = "http://xmeme-env.eba-chunymmw.us-east-1.elasticbeanstalk.com/memes";
        RestTemplate restTemplate = new RestTemplate();
        Meme[] memes = restTemplate.getForObject(url, Meme[].class);

        model.addAttribute("listOfMemes",memes);
        return "design";
    }

    //Displays the form for creation/submission of a meme
    @GetMapping("/create")
    public String createMemeForm(Model model){
        Meme meme = new Meme();
        model.addAttribute("meme",meme);
        return "submissionDesign";
    }

    //This calls the backend /memes api to add a new meme to the database
    @PostMapping("/save")
    public String submitMeme(@ModelAttribute("meme") Meme meme) throws JsonProcessingException {
        String url = "http://xmeme-env.eba-chunymmw.us-east-1.elasticbeanstalk.com/memes";
        return getString(meme, url);
    }

    //Displays the update form
    @GetMapping("/showUpdateForm/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        Meme meme = memeService.getMemeById(id);

        model.addAttribute("meme", meme);
        return "updatedMeme";
    }

    //This calls the backend /update api to update the said meme in the database
    @PostMapping("/updateMeme")
    public String updateShowMeme(@ModelAttribute("meme") Meme meme) throws JsonProcessingException {
        String url = "http://xmeme-env.eba-chunymmw.us-east-1.elasticbeanstalk.com/update/"+meme.getId();
        return getString(meme, url);
    }

    //Utility function
    private String getString(@ModelAttribute("meme") Meme meme, String url) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String meme_request = objectMapper.writeValueAsString(meme);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(meme_request, headers);
        restTemplate.postForEntity(url, request, String.class);

        return "redirect:/home";
    }
}
