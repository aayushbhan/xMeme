package crioproject.xmeme.Controller;

import crioproject.xmeme.Model.Meme;
import crioproject.xmeme.Service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class MemesController {

    @Autowired
    private final MemeService memeService;

    public MemesController(MemeService memeService) {
        this.memeService = memeService;
    }

    //gets the top 100 memes from the database and  returns a list of memes
    @GetMapping("/memes")
    public ResponseEntity<List<Meme>> getMemes(){
        List<Meme> result = memeService.getTopMemes();
        return(ResponseEntity.ok(result));
    }


    //adds a new meme to the database and returns the id of the meme
    @PostMapping(value="/memes",produces = "application/json")
    public ResponseEntity<String> postMeme(@RequestBody Meme meme){
        Long memeId = memeService.createMeme(meme);
        return(ResponseEntity.ok("{ \"id\" : "+ memeId.toString()+"}"));
    }

    //gets the details of the meme with the given id, throws 404 if meme doesn't exist
    @GetMapping("/memes/{id}")
    public ResponseEntity<Meme> getMemeFromId(@PathVariable("id") long id ){
            Meme meme = memeService.getMemeById(id);
            if(meme==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Id Not Found");
            }
            return(ResponseEntity.ok(meme));
    }


    //updates the details of the meme with the given id, throws 404 if meme doesn't exist
    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateMeme(@PathVariable("id") long id,@RequestBody Meme meme) {
        if(memeService.getMemeById(id)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Id Not Found");
        }
        memeService.updateMeme(meme.getCaption(),meme.getUrl(),id);
        return ResponseEntity.ok("Updated");
    }

    }