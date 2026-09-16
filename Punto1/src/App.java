import modelos.Clinica;
import modelos.Medico;
import modelos.Paciente;

public class App {
    
    public static void main(String[] args) throws Exception {

        // Se crea la cooperativa (el Sujeto/Observable)
        Clinica clinica = new Clinica();

        // Se crean los médicos (nombre e identificación)
        Medico elena = new Medico("Elena Ramos", "medicina intensiva",1020304050);
        Medico andres = new Medico("Andrés Suárez", "Enfermeria",1030405060);
        Medico Marta = new Medico("Marta Gómez", "Bactereologia",1040506070);

        // Suscripción a la cooperativa
        clinica.suscribirObservador(elena);
        clinica.suscribirObservador(andres);
        clinica.suscribirObservador(Marta);

        // Se registra un nuevo lote -> dispara la notificación a todos los suscritos
        Paciente p1 = new Paciente("Juan Pérez", 123456789, 120, 80, 36, 95, 1);
        clinica.registrarPaciente(p1);

        // Un barista se desuscribe
        clinica.deSuscribirObservador(elena);

        // Se registra otro lote -> Carlos ya NO debe recibir esta notificación
        Paciente p2 = new Paciente("María López", 987654321, 110, 70, 34, 92, 2);
        clinica.registrarPaciente(p2);

        clinica.setPresionArterial(p2, 130);
    }
}


