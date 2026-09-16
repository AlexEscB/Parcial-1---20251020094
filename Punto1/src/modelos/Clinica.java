package modelos;


import java.util.ArrayList;
import java.util.List;

public class Clinica implements ClinicaSucject {

    private List<MedicoObserver> medicoObservers = new ArrayList<>();
    private Paciente paciente;


    @Override
    public void notificarObservadores() {
        for (MedicoObserver medico : medicoObservers){
            medico.actulizacion(paciente);

        }
    }

    @Override
    public void suscribirObservador(MedicoObserver medico) {
        medicoObservers.add(medico);
    }

    @Override
    public void deSuscribirObservador(MedicoObserver medico) {
        medicoObservers.remove(medico);
    }




    public void setPresionArterial(Paciente p, int nuevaPersionArterial) {
        p.persionArterial = nuevaPersionArterial;
        notificarObservadores();

    }

    public void registrarPaciente(Paciente nuevoPaciente) {
        this.paciente = nuevoPaciente;
        notificarObservadores();
    }
    
} 
