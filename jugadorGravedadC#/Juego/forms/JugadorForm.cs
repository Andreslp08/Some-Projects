using Juego.Logica;
using System;
using System.Drawing;
using System.Threading;
using System.Windows.Forms;

namespace Juego
{


    class JugadorForm : PictureBox
    {
        private Image imagen;
        private Icon icono;
        private const int W = 80, H = 80;
        private Jugador jugador;
        int posY;
        int velocidad;
        int gravedad;
        public int posInicial;
        int posFinal;
        Boolean arriba = false;
        Boolean salto = false;
        public Thread hilo;
        public JugadorForm()
        {
            jugador = new Jugador();
            this.Size = new Size(W, H);
            this.Text = "Jugador";
            this.Load(@"..\..\img\jugador.png");
            this.SizeMode = PictureBoxSizeMode.StretchImage;
            //hilo = new Thread(run);
            //hilo.IsBackground = true;
            //hilo.Start();
            
        }

        public void keySaltar(Object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar == Convert.ToChar(Keys.Space)  )
            {
  
                saltar();
            }
        }

        public void saltar()
        {
            velocidad = 35;
            gravedad = 2;
            posFinal = 0;
            posInicial = posInicial;
            posY = posInicial;
            arriba = false;
            salto = true;
            while (salto == true)
            {
                if (arriba == false)
                {
                    Location = new Point(0, posY);
                    posY -= velocidad;
                    velocidad -= gravedad;
                    if (velocidad <= 0)
                    {
                        velocidad = 1;
                        arriba = true;
                    }
                }
                if (arriba == true)
                {

                    Location = new Point(0, posY);
                    posY += velocidad;
                    velocidad += gravedad;
                    if (velocidad >= 30)
                    {
                        velocidad = 30;
                    }
                    if (posY >= posInicial)
                    {
                        posY = posInicial;
                        Location = new Point(0, posY);
                        salto = false;
                    }
                }
                Thread.Sleep((int)VentanaPrincipal.APS);
            }
        }

        public int getH()
        {
            return H;
        }
        public void posicionInicial(int posInicial)
        {
            salto = false;
            this.posInicial = posInicial;
            posY = posInicial;

        }

        //public void run()
        //{
        //    while( true)
        //    {
        //        saltar();
        //    }

        //}

    }




}

