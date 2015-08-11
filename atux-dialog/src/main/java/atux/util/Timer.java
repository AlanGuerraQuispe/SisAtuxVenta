package atux.util;

import java.util.Date;

class Timer
        implements Runnable
{
    private Date inicio;
    private Date parada;
    private Thread thread = null;
    private int duracion = 0;
    private int duracionAnt = 0;
    private int intervalo = 1000;
    private boolean repeticion = false;
    private boolean enEjecucion = false;
    private Temporizador handler = null;

    Timer() { }

    Timer(int tiempo) {
        setDuracion(tiempo);
    }

    Timer(Temporizador Handler) {
        setHandler(Handler);
    }

    Timer(int tiempo, Temporizador Handler) {
        setDuracion(tiempo);
        setHandler(Handler);
    }

    public void setDuracion(int tiempo) {
        this.duracion = tiempo;
    }

    public void setHandler(Temporizador Handler) {
        this.handler = Handler;
    }

    public void setIntervalo(int Intervalo)
    {
        this.intervalo = Intervalo;
    }

    public int getDuration() {
        return this.duracion;
    }

    public Temporizador getHandler() {
        return this.handler;
    }

    public int getIntervalo() {
        return this.intervalo;
    }

    public int getElapsed()
    {
        return calculaLapso(new Date());
    }

    public void resetDuracion()
    {
        this.duracion = this.duracionAnt;
    }

    public void start()
    {
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void stop()
    {
        this.enEjecucion = false;
        this.parada = new Date();
        if (this.handler != null)
            this.handler.timerParado(this);
    }

    public void run()
    {
        this.enEjecucion = true;
        this.duracionAnt = this.duracion;

        this.inicio = new Date();
        if (this.handler != null) {
            this.handler.timerArrancado(this);
        }
        while (this.enEjecucion)
        {
            try {
                esperar(this.intervalo);
            } catch (InterruptedException e) {
                return;
            }

            if (this.handler != null) {
                this.handler.timerIntervalo(this);
            }
            if ((this.duracion <= 0) ||
                    (!(estaMuerto())))
                continue;
            if (this.handler != null) {
                this.handler.timerMuerto(this);
            }
            if (this.repeticion)
            {
                this.enEjecucion = true;
                this.inicio = new Date();
                if (this.handler != null);
                this.handler.timerArrancado(this);
            }

            this.enEjecucion = false;
        }
    }

    public boolean estaCorriendo()
    {
        return this.enEjecucion;
    }

    public boolean estaParado() {
        return (!(this.enEjecucion));
    }

    public boolean estaMuerto()
    {
        int segundos = 0;

        segundos = calculaLapso(new Date());

        return (segundos >= this.duracion);
    }

    private int calculaLapso(Date actual)
    {
        Date dfinal;
        int segundos = 0;

        if (this.enEjecucion)
            dfinal = actual;
        else {
            dfinal = this.parada;
        }
        segundos += (dfinal.getHours() - this.inicio.getHours()) * 3600;
        segundos += (dfinal.getMinutes() - this.inicio.getMinutes()) * 60;
        segundos += dfinal.getSeconds() - this.inicio.getSeconds();
        return segundos;
    }

    private synchronized void esperar(int lapso) throws InterruptedException
    {
        super.wait(lapso);
    }
}