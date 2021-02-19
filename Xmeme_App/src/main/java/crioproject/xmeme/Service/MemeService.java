package crioproject.xmeme.Service;

import crioproject.xmeme.Model.Meme;

import java.util.List;

public interface MemeService {
    List<Meme> getTopMemes();
    long createMeme(Meme meme);
    Meme getMemeById(long id);
    void updateMeme(String caption, String url, long id);
}
