package crioproject.xmeme.Repository;

import crioproject.xmeme.Model.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface MemeRepository extends JpaRepository<Meme, Long>{
    @Transactional
    @Modifying
    @Query(value = "UPDATE memes m SET m.caption = :caption, m.url = :url where m.id = :id  ",
            nativeQuery = true)
    void updateMeme(@Param("caption") String caption, @Param("url") String url, @Param("id") long id);
}
