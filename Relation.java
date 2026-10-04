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

    public void NoDoublon() {
        int nbrIndividu = this.individu.size();

        for (int i = 0; i < nbrIndividu; i++) {

            Object[] toCompare = this.individu.get(i);
            for (int j = i + 1; j < nbrIndividu; j++) {
                Object[] toMe = this.individu.get(j);
                if (Arrays.equals(toCompare, toMe)) {
                    this.individu.remove(j);
                    nbrIndividu--;
                    j--;
                }
            }
        }

    }

    public Relation project(Attribut[] toproject) {
        Relation toreturn = new Relation("default", toproject);

        int nbrIndividu = this.individu.size();
        int nbrAttribut = this.attributs.length;
        int[] index = new int[toproject.length];

        for (int j = 0; j < toproject.length; j++) {
            Attribut projection = toproject[j];
            for (int i = 0; i < nbrAttribut; i++) {
                Attribut attribut = this.attributs[i];
                if (attribut.equals(projection)) {
                    index[j] = i;
                    break;
                }
            }
        }

        for (int k = 0; k < nbrIndividu; k++) {
            Object[] concern = this.individu.get(k);
            Object[] ajout = new Object[toproject.length];
            int b = 0;
            for (int o = 0; o < toproject.length; o++) {
                ajout[b] = concern[index[o]];
                System.out.print(String.valueOf(ajout[b]) + " / ");
                if (b == toproject.length - 1) {
                    System.out.println("\n");
                }
                b++;
            }
            toreturn.add(ajout);
        }
        toreturn.NoDoublon();
        return toreturn;
    }
}
