public class Main {

    public static void main(String[] args) {
        String fileName = args[0];
        GenreList genreLeaves = GenreList.createAndSortGenresFromFile(fileName);
        System.out.println(genreLeaves.toString());
    }
}
