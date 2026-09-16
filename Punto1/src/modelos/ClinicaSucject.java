package modelos;

public interface ClinicaSucject {

    public void suscribirObservador(MedicoObserver medico);
    public void deSuscribirObservador(MedicoObserver medico);
    public void notificarObservadores();


}
