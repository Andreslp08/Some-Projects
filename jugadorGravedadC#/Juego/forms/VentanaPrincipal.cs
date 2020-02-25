using Juego.forms;
using System;
using System.Drawing;
using System.Threading;
using System.Windows.Forms;

namespace Juego
{
    public partial class VentanaPrincipal : Form
    {
        Panel fondo;
        private JugadorForm jugadorForm;
       // private Obstaculo obstaculo;
        const int W = 500, H = 500;
        private Thread hiloPrincipal;


        public const double APS = 1000 / 60;
        private Button boton;
        public VentanaPrincipal()
        {
            InitializeComponent();
            this.Text = "hola";
            //ControlBox = false;
            //FormBorderStyle = FormBorderStyle.None;
            StartPosition = FormStartPosition.CenterScreen;
            //fondo
            fondo = new Panel();
            fondo.BackColor = Color.FromArgb(255, 1, 110, 188);
            this.Size = new Size(W, H);
            fondo.Width = this.Width - 15;
            fondo.Height = this.Height - 39;
            Controls.Add(fondo);
            // jugador
            jugadorForm = new JugadorForm();
            jugadorForm.Location = new Point(0, fondo.Height - jugadorForm.Height);
            fondo.Controls.Add(jugadorForm);
            //Eventos del jugador             
            this.KeyPress += new KeyPressEventHandler(jugadorForm.keySaltar);
            //obstaculo
            /*obstaculo = new Obstaculo();
            obstaculo.setPosicionInicial( fondo.Width, fondo.Height - obstaculo.Height);
            fondo.Controls.Add(obstaculo);*/
            //Hilo principal
            hiloPrincipal = new Thread(run);
            //boton
            //boton = new Button();
            //boton.Text = "Pausar";
            //boton.Location = new Point(0, 0);
            //boton.Size = new Size(100, 30);
            //fondo.Controls.Add(boton);
            //boton.MouseUp += new MouseEventHandler(pausarJuego);
        }

        public void pausarJuego( Object sender, MouseEventArgs e)
        {
            if (boton.Text.Equals("Pausar"))
            {
                hiloPrincipal.Suspend();
                boton.Text = "Continuar";
            }
            else
            {
                if( boton.Text.Equals("Continuar" ))
                {
                    hiloPrincipal.Resume();
                    boton.Text = "Pausar";
                }
            }
        }

        public void iniciarHilo()
        {
            hiloPrincipal.IsBackground = true;
            hiloPrincipal.Start();
        }

        public void detenerHilo()
        {
            hiloPrincipal.Join();
        }


        private void run()
        {
            Boolean corriendo = true;
            jugadorForm.posicionInicial(fondo.Height - jugadorForm.Height);
            while (corriendo == true )
            {
              // obstaculo.mover();
                Thread.Sleep((int)APS);
            }

        }

    }

}
