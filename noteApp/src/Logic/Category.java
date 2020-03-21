package Logic;

import java.io.Serializable;

public class Category implements Serializable {
    private String name;


    public Category(String name) {
        this.name = name;

    }

    public static Category returnCategory(String argument) {
        return new Category(argument);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        Category cat = (Category) obj;

        return cat.getName().equals(this.name);
    }

    public String getName() {
        return this.name;
    }


    @Override
    public String toString() {
        return name;
    }

}
