import java.util.Date;
import java.util.List.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("SGBDR de Walker, fork Misaina parce qu il pouvait pas venir en cours");
        Attribut nom = new Attribut("nom", new Domaine("varchar", 100));
        Attribut prenom = new Attribut("prenom", new Domaine("varchar", 100));
        Attribut date_naissance = new Attribut("date_naissance", new Domaine("date"));
        Attribut sexe = new Attribut("sexe", new Domaine("enum", new Object[]{"m", "f"}));

        Relation etudiant = new Relation("etudiant", new Attribut[]{nom, prenom, date_naissance, sexe});
        etudiant.add(new Object[]{"RAKOTONIRINA", "Vahatra Ny Aina", new Date(2008, 11, 3), "m"});
        etudiant.add(new Object[]{"RATSIRESY", "Aromitia Fandresena", new Date(2007, 7, 16), "f"});
        etudiant.add(new Object[]{"RAFIDIMANANTSOA", "Malalaniaina Eliana", new Date(2007, 4, 3), "f"});
        etudiant.add(new Object[]{"ROBSON RADO", "Misaina Henintsoa", new Date(2008, 1, 3), "m"});

        etudiant.selectEtoile();

        etudiant.project(new Attribut[]{nom, prenom});
    }
}
