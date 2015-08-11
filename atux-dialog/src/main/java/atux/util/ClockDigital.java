package atux.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class ClockDigital extends JComponent
{
    private Timer timer;
    private Color fondo;
    private JLabel horaActual;

    public ClockDigital()
    {
        Timer tim = new Timer(1);
        initComponents();
        setHoraPrevia();
        tim.start();
    }

    public void setForeground(Color color)
    {
        super.setForeground(color);
        this.horaActual.setForeground(color);
    }

    public void setFont(Font font)
    {
        super.setFont(font);
        this.horaActual.setFont(font);
    }

    public Font getFont()
    {
        return this.horaActual.getFont();
    }

    private void setHoraPrevia()
    {
        int hora;
        Date actual = new Date();
        int horas = actual.getHours();
        int minutos = actual.getMinutes();
        int segundos = actual.getSeconds();

        String tiempo = "";

        if (horas > 12)
            hora = horas - 12;
        else
            hora = horas;
        if (hora < 10)
            tiempo = tiempo + "0";
        tiempo = tiempo + hora;
        tiempo = tiempo + ":";

        if (minutos < 10)
            tiempo = tiempo + "0";
        tiempo = tiempo + minutos + ":";

        if (segundos < 10)
            tiempo = tiempo + "0";
        tiempo = tiempo + segundos;

        if (horas >= 12)
            tiempo = tiempo + " pm";
        else {
            tiempo = tiempo + " am";
        }
        this.horaActual.setText(tiempo);
    }

    public void timerIntervalo(Timer t) {
        setHoraPrevia();
    }

    public void timerArrancado(Timer t)
    {
    }

    public void timerParado(Timer t)
    {
    }

    public void timerMuerto(Timer t)
    {
    }

    private void initComponents()
    {
        this.horaActual = new JLabel();

        setLayout(new BorderLayout());

        this.horaActual.setFont(new Font("Microsoft Sans Serif", 1, 14));
        this.horaActual.setHorizontalAlignment(0);
        this.horaActual.setText("La Hora");
        add(this.horaActual, "Center");
    }
}