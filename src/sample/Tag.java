package sample;

import java.util.EnumSet;

public enum Tag {
    FastFood,
    Pizza,
    Chinois,
    Sushi,
    Japonais,
    Italien,
    Francais,
    Fromage,
    Crepe,
    Glace,
    Burger,
    Healthy,
    Kebab;

    public static EnumSet<Tag> getFullList(){
        return EnumSet.allOf(Tag.class);
    }
}
