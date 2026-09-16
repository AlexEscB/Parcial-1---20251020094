package modelos;

public class Medico implements MedicoObserver {
    private String nombreMedico;
    private String especialidad;
    public int identificacionMed;
    
    /*     Metodo constructor de la clase Medico.
    *   @param nombreMedico: Nombre del medico.
    *   @param especialidad: Especialidad del medico.
    *   @param identificacionMed: Identificacion del medico.
    */
    public Medico(String nombreMedico, String especialidad, int identificacionMed) {
        this.nombreMedico = nombreMedico;
        this.especialidad = especialidad;
        this.identificacionMed = identificacionMed;
}


    @Override
    public void actulizacion(Paciente p) {
                System.out.println(" ");
        System.out.println("==================================================");
        System.out.println("Estimado Suscriptor1: " + this.nombreMedico + " de la especialidad: " + this.especialidad);
        System.out.println("identificado con: " + this.identificacionMed);
        System.out.println("--------Se le notifaca que su paciente ----------");
        System.out.println("Nombre del paciente: " + p.nombrePaciente);
        System.out.println("Identificacion del paciente: " + p.iden);
        
        if (this.especialidad.equals("medicina intensiva")) {
            System.out.println("--------------- ha registrado cambios en sus signos vitales ---------");
            System.out.println("Presion Arterial: " + p.persionArterial);
            System.out.println("Frecuencia Cardiaca: " + p.frecuenciaCardiaca);
            System.out.println("Temperatura: " + p.temperatura);
            System.out.println("Nivel de Oxigeno: " + p.nivelOxigeno);
            System.out.println("--------------------------------------------------------------------------");

        }else if (this.especialidad.equals("Enfermeria")) {
            System.out.println("--------------- ha registrado cambios en su prioridad ---------");
            System.out.println("Prioridad: " + p.prioridad);

            System.out.println("--------------------------------------------------------------------------");

        } else if (this.especialidad.equals("Bacteriologia")) {
            System.out.println("--------------- ha registrado cambios en los exmanes para el paciente ---------");
            for (Examen examen : p.Exmenes) {
                System.out.println("Nombre del examen: " + examen.nombreExamen);
                System.out.println("Numero de orden: " + examen.numeroDeOrden);
                System.out.println("Estado del examen: " + examen.Estado);
                System.out.println("--------------------------------------------------------------------------");
            }
            
        }

    

    }



}