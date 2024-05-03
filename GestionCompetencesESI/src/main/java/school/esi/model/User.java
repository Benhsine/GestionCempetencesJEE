package school.esi.model;

public class User {
    private int id; // Nouvel attribut id
    private String nom;
    private String email;
    private String motDePasse;

    // Constructeur par défaut
    public User() {
    }

    // Constructeur avec paramètres
    public User(int id, String nom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // Getters et Setters pour l'attribut id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters et Setters pour l'attribut nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getters et Setters pour l'attribut email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters et Setters pour l'attribut motDePasse
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
