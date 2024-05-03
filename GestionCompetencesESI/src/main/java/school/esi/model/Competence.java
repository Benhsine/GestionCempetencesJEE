package school.esi.model;

public class Competence {
    private int id;
    private String nom;
    private String description;
    private String niveauRequis;
    private String domaine;

    // Constructeur
    public Competence(String nom, String description, String niveauRequis, String domaine) {
        this.nom = nom;
        this.description = description;
        this.niveauRequis = niveauRequis;
        this.domaine = domaine;
    }
    public Competence() {
        
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNiveauRequis() {
        return niveauRequis;
    }

    public void setNiveauRequis(String niveauRequis) {
        this.niveauRequis = niveauRequis;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
}
