package sample;

import java.util.EnumSet;

public enum Tag {
    FastFood("#FastFood "),
    Pizza("#Pizza" ),
    Chinois("#Chinois "),
    Sushi("#Sushi "),
    Japonais("#Japonais "),
    Italien("#Italien "),
    Francais("#Français "),
    Fromage("#Fromage "),
    Crepe("#Crèpe "),
    Glace("#Glace "),
    Burger("#Burger "),
    Healthy("#Healthy "),
    Kebab("#Kebab "),
    Sandwich("#Sandwich "),
    Salade("#Salade "),
    Commerce("#Commerce ");

    private final String name;

    Tag(String s) {
        this.name=s;
    }

    public static Tag toTag(String newFiltre) {
        for(Tag tag: Tag.getFullList()){
            if(newFiltre.equals(tag.name))
                return tag;
        }
        return null;
    }

    public String getName(){
        return this.name;
    }

    public static EnumSet<Tag> getFullList(){
        return EnumSet.allOf(Tag.class);
    }

}
