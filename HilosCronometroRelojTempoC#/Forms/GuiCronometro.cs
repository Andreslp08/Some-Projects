using Form2.Logica;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace Form2.Forms
{
    class GuiCronometro : Label
    {
        private PictureBox fondo;
        private const int WG = 200, HG = 200;
        private Label labelCronometro;
        private Cronometro cronometro;
        private Botones[] botones;
        private String[] textoBotones = { "Iniciar", "Pausar", "Reanudar", "Parar" };
        private int posX, posY;
        private const int WB = 106, HB = 40;
        public GuiCronometro(Control control)
        {
            this.SetBounds(20, 95, control.Width - 40, control.Height - 115);
            this.BackColor = Color.FromArgb(200, 15, 15, 15);
            //fondo
            fondo = new PictureBox();
            fondo.SetBounds(0, -20, this.Width, this.Height + 20);
            fondo.Image = Image.FromFile(@"..\..\img\fondo2.png");
            fondo.SizeMode = PictureBoxSizeMode.StretchImage;
            this.Controls.Add(fondo);
            this.Visible = false;
            //Label cronometro
            labelCronometro = new Label();
            labelCronometro.Text = "00:00:00:00";
            labelCronometro.SetBounds(((fondo.Width - WG) / 2) + 40, (fondo.Height - 70) / 2, WG, 40);
            labelCronometro.ForeColor = Color.White;
            labelCronometro.Font = new Font("Arial Narrow", 20f, FontStyle.Bold);
            labelCronometro.BackColor = Color.FromArgb(0, 0, 0, 0);
            //153 and 285
            fondo.Controls.Add(labelCronometro);
            //Cronometro
            cronometro = new Cronometro(labelCronometro);
            //Botones
            botones = new Botones[4];
            posX = 10;
            posY = fondo.Height - 55;
            for (int i = 0; i < botones.Length; i++)
            {
                Botones boton = botones[i];
                boton = new Botones(textoBotones[i], posX, posY, WB, HB, Botones.TIPO.BOTON_NORMAL, "Impact", 10f);
                fondo.Controls.Add(boton);
                posX = posX + WB + 5;
                boton.MouseUp += new MouseEventHandler(accionBotones);
            }
            //grafico
            Paint += new PaintEventHandler(paint);
            fondo.Paint += new PaintEventHandler(paint2);

        }

        public void accionBotones(Object sender, EventArgs e)
        {
            Control objeto = (Control)sender;
            switch (objeto.Text)
            {
                case "Iniciar":
                    cronometro.startHilo();
                    break;
                case "Pausar":
                    cronometro.pausarHilo();
                    break;
                case "Reanudar":
                    cronometro.reanudarHilo();
                    break;
                case "Parar":
                    cronometro.restablecerHilo();
                    break;

            }
        }
        void paint(Object sender, PaintEventArgs e)
        {

            Graphics g = e.Graphics;        
            g.FillPie(Brushes.Black, (this.Width - WG) / 2, 10, WG, HG, 400, 400);
            Pen lapiz = new Pen(Color.DarkGray, 5);
        }

        void paint2(Object sender, PaintEventArgs e)
        {
            Pen lapiz = new Pen(Color.DarkGray, 10);
            Graphics g = e.Graphics;
            g.DrawArc(lapiz, (this.Width - WG) / 2, 30, WG, HG, 400, 400);    
        }
    }
}
