import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenreList extends ArrayList<Genre> {

    public Optional<Genre> get(String genre){
        return this.stream().filter(gen -> gen.getName().equals(genre)).findFirst();
    }

    public void addSorted(Genre genre){
        int index = 0;
        while(index < this.size() && this.get(index).compare(genre.getName()) < 0){
            index++;
        }

        this.add(index, genre);

    }

    @Override
    public String toString() {
        String result = "";
        for(Genre gen : this){
            result += gen.toString() +"\n";
        }
        return result;
    }

    private static GenreList createAndSortGenres(List<String> genres){
        GenreList genreLeaves = new GenreList();
        GenreList allGenre = new GenreList();

        for(String genrePair : genres){
            String[] split = genrePair.split(" ");
            Optional<Genre> fatherOpt = allGenre.get(split[0]);
            Genre father;
            if(fatherOpt.isPresent()){
                father = fatherOpt.get();
            }else{
                father = new Genre(split[0]);
            }
            genreLeaves.remove(father);
            Genre son = new Genre(father, split[1]);

            allGenre.add(father);
            allGenre.add(son);
            genreLeaves.addSorted(son);
        }

        return genreLeaves;
    }

    public static GenreList createAndSortGenresFromFile(String fileName){
        GenreList result = null;

        try {
            List<String> genresFormFile = Files.lines(Paths.get(fileName))
                    .map(s -> s.trim())
                    .filter(s -> !s.isEmpty()).collect(Collectors.toList());

            result = GenreList.createAndSortGenres(genresFormFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
