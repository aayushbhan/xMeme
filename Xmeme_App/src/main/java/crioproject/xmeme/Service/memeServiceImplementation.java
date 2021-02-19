package crioproject.xmeme.Service;

import crioproject.xmeme.Model.Meme;
import crioproject.xmeme.Repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class memeServiceImplementation implements MemeService {

    @Autowired
    private MemeRepository memeRepository;

    //return 100 most recent memes
    @Override
    public List<Meme> getTopMemes() {

        List<Meme> memeList = memeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return memeList.stream().filter( m -> m.getId() <= 100 ).collect(Collectors.toList());
    }

    //saves the meme into the database
    @Override
    public long createMeme(Meme meme) {
        this.memeRepository.save(meme);
        return(meme.getId());
    }

    //returns the meme which has the given id
    @Override
    public Meme getMemeById(long id) {
        Optional<Meme> memeOptional = memeRepository.findById(id);
        if(memeOptional.isPresent()){
            return memeOptional.get();
        }
        else{
            return null;
        }
    }

    //updates the meme
    @Override
    public void updateMeme(String caption, String url, long id) {
        memeRepository.updateMeme(caption,url,id);
    }

}
