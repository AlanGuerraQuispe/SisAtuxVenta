package atux.util;

/**
 * Created by user on 10/02/2015.
 */
public abstract interface Temporizador
{
    public abstract void timerArrancado(Timer paramTimer);

    public abstract void timerParado(Timer paramTimer);

    public abstract void timerMuerto(Timer paramTimer);

    public abstract void timerIntervalo(Timer paramTimer);
}