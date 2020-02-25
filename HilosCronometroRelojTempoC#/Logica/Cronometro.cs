using System;
using System.Threading;
using System.Windows.Forms;

namespace Form2.Logica
{
    class Cronometro
    {
        private Thread hilo;
        private Control control;
        private int nanosegundo, milisegundo, segundo, minuto, hora, dia;
        private String ceroS = " ", ceroM = " ", ceroH = " ";
        public Cronometro(Control control)
        {

            this.control = control;
            hilo = new Thread(new ThreadStart(algoritmoCronometro));
            hilo.IsBackground = true;
        }

        public void startHilo()
        {
            if (!hilo.IsAlive)
            {
                hilo = new Thread(new ThreadStart(algoritmoCronometro));
                hilo.IsBackground = true;
                hilo.Start();
            }

        }
        public void reanudarHilo()
        {
            try
            {
                hilo.Resume();
                hilo.IsBackground = true;
            }
            catch (Exception ex)
            {

            }
        }
        public void pausarHilo()
        {
            try
            {
                hilo.Suspend();
                hilo.IsBackground = true;
            }
            catch (Exception ex)
            {

            }
        }
        public void restablecerHilo()
        {
            control.Text = "00:00:00:00";
            try
            {       
                    hilo.Abort();
                    hilo.IsBackground = true;            
            }
            catch (Exception ex)
            {
  

            }
        }
        public Control getPanel()
        {
            return control;
        }
        public void algoritmoCronometro()
        {
            for (hora = 0; hora <= 24; hora++)
            {
                for (minuto = 0; minuto <= 60; minuto++)
                {
                    for (segundo = 0; segundo <= 60; segundo++)
                    {
                        for (milisegundo = 0; milisegundo <= 10; milisegundo++)
                        {
                            for (nanosegundo = 0; nanosegundo <= 9; nanosegundo++)
                            {
                                if (nanosegundo == 9)
                                {
                                    nanosegundo = 0;
                                    milisegundo = milisegundo + 1;
                                }
                                if (milisegundo == 10)
                                {

                                    milisegundo = 0;
                                    segundo = segundo + 1;
                                }
                                if (segundo == 60)
                                {
                                    segundo = 0;
                                    ceroS = "0";
                                    minuto = minuto + 1;
                                }
                                if (minuto == 60)
                                {
                                    minuto = 0;
                                    ceroM = "0";
                                    hora = hora + 1;
                                }
                                if (hora == 24)
                                {
                                    hora = 0;
                                    ceroH = "0";
                                }
                                if (segundo <= 9)
                                {
                                    ceroS = "0";
                                }
                                else if (segundo > 9)
                                {
                                    ceroS = "";
                                }
                                if (minuto <= 9)
                                {
                                    ceroM = "0";
                                }
                                else if (minuto > 9)
                                {
                                    ceroM = "";
                                }
                                if (hora <= 9)
                                {
                                    ceroH = "0";
                                }
                                else if (hora > 9)
                                {
                                    ceroH = "";
                                }

                                control.Text = ceroH + hora + ":" + ceroM + minuto + ":" + ceroS + segundo + ":" + milisegundo + nanosegundo;
                                control.Update();
                                Thread.Sleep(10);


                            }
                        }
                    }
                }
            }
        }


    }
}
