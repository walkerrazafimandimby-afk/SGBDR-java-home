import java.util.*;

public class Domaine {
    String nom;
    Object[] type;

    public Domaine() {
    }

    public Domaine(String simple) {
        if (simple.equalsIgnoreCase("INTEGER") || simple.equalsIgnoreCase("INT")) {
            this.nom = simple;
            this.type = new Object[] { 1 };

        } else if (simple.equalsIgnoreCase("DOUBLE")) {
            this.nom = simple;
            this.type = new Object[] { 0.1 };
        } else if (simple.equalsIgnoreCase("DATE")) {
            this.nom = simple;
            this.type = new Object[] { new Date(1970, 01, 01) };

        }
    }

    public Domaine(String varchar, int taille) {
        if (varchar.equalsIgnoreCase("VARCHAR")) {
            this.nom = "VARCHAR";
            this.type = new Object[] { taille };

        } else {
            System.out.println("erreur : VARCHAR|varchar is the correct syntax");
        }
    }

    public Domaine(String enumeration, Object[] choix) {
        if (enumeration.equalsIgnoreCase("ENUM")) {
            this.nom = "ENUM";
            this.type = choix;
        } else {
            System.out.println("erreur de syntax");
        }
    }

    public String getName() {
        return this.nom;
    }

    public Object getReferenceType() {
        return this.type[0];
    }

    public Object[] getType() {
        return this.type;
    }
}
