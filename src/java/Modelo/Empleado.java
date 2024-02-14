package Modelo;

public class Empleado {
    int id;
    String dni;
    String nom;
    String tel;
    String estado;
    String user;
    String contra;
    int estado1;
    
    public Empleado() {
    }

    public Empleado(int id, String dni, String nom, String tel, String estado, String user, String contra,int estado1) {
        this.id = id;
        this.dni = dni;
        this.nom = nom;
        this.tel = tel;
        this.estado = estado;
        this.user = user;
        this.contra = contra;
        this.estado1 = estado1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContra1() {
        int num;
        num = contra.length();
        char []con = new char [num];
        for(int i=0; i<contra.length();i++){
            con[i] = '*';
        }
        String contra1 = String.valueOf(con);
        return contra1;
    }
    public String getContra(){
        return contra;
    }
    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getEstado1() {
        return estado1;
    }

    public void setEstado1(int estado1) {
        this.estado1 = estado1;
    }
    
}
