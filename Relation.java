import java.util.*;

public class Relation {
    String nom;
    Attribut[] attributs;
    List<Object[]> individu;

    public Relation(String nom, Attribut[] attributs) {
        this.nom = nom;
        this.attributs = attributs;
        this.individu = new ArrayList<>(200);
    }

    public int getAttributLength() {
        return attributs.length;
    }

    public void add(Object[] nuplet) {
        int nbAtr = this.getAttributLength();

        Object[] toAdd = new Object[nbAtr];

        for (int i = 0; i < nbAtr; i++) {
            String atrName = this.attributs[i].getDomaine().getName();
            String nupletType = nuplet[i].getClass().getSimpleName();
            Object currentUplet = nuplet[i];

            if (nupletType.equalsIgnoreCase("String")) {
                if (atrName.equalsIgnoreCase("enum")) {
                    Object[] choices = this.attributs[i].getDomaine().getType();

                    for (int j = 0; j < choices.length; j++) {
                        if (currentUplet.equals(choices[j])) {
                            toAdd[i] = currentUplet;
                        }
                    }
                } else if (atrName.equalsIgnoreCase("varchar")) {
                    int tailleMax = (int) this.attributs[i].getDomaine().getReferenceType();
                    String stringNuplet = (String) currentUplet;
                    if (stringNuplet.length() <= tailleMax) {
                        toAdd[i] = stringNuplet;
                    }
                }
            } else {
                String atrType = this.attributs[i].getDomaine().getReferenceType().getClass().getSimpleName();
                if (atrType.equalsIgnoreCase(nupletType)) {
                    if (atrName.equalsIgnoreCase("date")) {
                        Date dateNuplet = (Date) currentUplet;
                        String dateFormat = dateNuplet.toString();
                        toAdd[i] = dateFormat;
                    } else {
                        toAdd[i] = currentUplet;
                    }
                }
            }
        }

        this.individu.add(toAdd);

    }

    public void selectEtoile() {
        int nbIndividu = this.individu.size();
        int nbAtr = this.getAttributLength();

        for (int i = 0; i < nbIndividu; i++) {
            Object[] currentIndividu = this.individu.get(i);
            for (int j = 0; j < nbAtr; j++) {
                String separateur = " / ";
                System.out.print(String.valueOf(currentIndividu[j]) + separateur);
            }
            System.out.println("\n");
        }
    }
}
