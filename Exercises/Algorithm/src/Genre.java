import java.util.Objects;

public class Genre {
    private Genre father;
    private String name;

    public Genre(Genre father, String name){
        this.father = father;
        this.name = name;
    }

    public Genre(String name){
        this.father = null;
        this.name = name;
    }

    public Genre getFather() {
        return father;
    }

    public void setFather(Genre father) {
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compare(String name){
        return this.name.compareTo(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if(obj != null && this.getClass() == obj.getClass()){
            Genre genre = (Genre) obj;
            result = this == genre &&
                    Objects.equals(this.name, genre.name);
        }
        return result;
    }

    @Override
    public String toString() {
        String result = this.name;
        Genre father = this.father;
        while(father != null){
            result += " " + father.getName();
            father = father.getFather();
        }
        return result;
    }
}
