package modelos;

import java.util.ArrayList;



public class Paciente {
    public String nombrePaciente;
    public int iden;

    public int persionArterial;
    public int frecuenciaCardiaca;
    public int temperatura;
    public int nivelOxigeno;
    public int prioridad;

    public ArrayList<Examen> Exmenes = new ArrayList<>();

    

    public Paciente(String nombrePaciente, int iden, int persionArterial, int frecuenciaCardiaca, int temperatura, int nivelOxigeno,
            int prioridad) {
        this.nombrePaciente = nombrePaciente;
        this.iden = iden;
        this.persionArterial = persionArterial;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.temperatura = temperatura;
        this.nivelOxigeno = nivelOxigeno;
        this.prioridad = prioridad;
        this.Exmenes = new ArrayList<>();
    }

    public void addExamen(Examen examen) {
        this.Exmenes.add(examen);
    }

}
