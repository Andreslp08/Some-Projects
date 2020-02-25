using Form2.Forms;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace Form2
{
    public partial class ventanaPrincipal : Form
    {
        private PictureBox panel;
        private Botones[] botones;
        private Botones[] botonesMenu;
        private String[] textoBotones = { "X", "-" };
        private String[] textoBotonesM = { "Reloj", "Cronometro", "Temporizador" };
        private int posXboton, posYboton;
        private Graphics circulo;
        private Icon icono;
        private Image imagen;
        private GuiCronometro guiCronometro;
        private GuiReloj guiReloj;

        public ventanaPrincipal()
        {
            //formulario
            this.StartPosition = FormStartPosition.CenterScreen;
            this.SetClientSizeCore(500, 400);
            this.ControlBox = false;
            this.FormBorderStyle = FormBorderStyle.None;
            this.BackColor = Color.FromArgb(255, 10, 10, 10);
            //this.Opacity = .95f;
            //FormBorderStyle borde = FormBorderStyle:

            //////panel
            panel = new PictureBox();
            panel.SetBounds(0, 0, this.Width, this.Height);
            this.Controls.Add(panel);
            //Fondo
            panel.Image = Image.FromFile(@"..\..\img\fondo.png");
            panel.SizeMode = PictureBoxSizeMode.StretchImage;
            //Botones
            posXboton = this.Width - 30;
            posYboton = 0;
            botones = new Botones[2];
            for (int i = 0; i < botones.Length; i++)
            {
                Botones boton = botones[i];
                boton = new Botones("X", posXboton, posYboton, 30, 30, Botones.TIPO.BOTON_PRINCIPAL, "Arial", 10f);
                boton.Font = new Font(boton.Font, FontStyle.Bold);
                boton.Text = textoBotones[i];
                panel.Controls.Add(boton);
                posXboton = posXboton - 30;
                boton.MouseUp += new MouseEventHandler(accionBotones);
            }
            //Botones menu
            int posXbotonM = 0;
            int posYbotonM = 30;
            botonesMenu = new Botones[3];
            for (int i = 0; i < botonesMenu.Length; i++)
            {
                botonesMenu[i] = new Botones("X", posXbotonM, posYbotonM, 167, 50, Botones.TIPO.BOTON_MENU, "Arial Narrow", 10f);
                botonesMenu[i].Font = new Font(botonesMenu[i].Font, FontStyle.Bold);
                botonesMenu[i].Text = textoBotonesM[i];
                panel.Controls.Add(botonesMenu[i]);
                posXbotonM = posXbotonM + 167;
                botonesMenu[i].MouseUp += new MouseEventHandler(accionBotonesMenu);
            }
            // gui cronometro
            guiCronometro = new GuiCronometro(this);
            panel.Controls.Add(guiCronometro);
            // gui reloj
            guiReloj = new GuiReloj(this);
            panel.Controls.Add(guiReloj);
            //grafico
            //Paint += new PaintEventHandler(paint);

        }


        //void paint( Object sender, PaintEventArgs e)
        //{

        //    Graphics g = e.Graphics;
        //    Pen lapiz = new Pen(Color.Aqua);
        //    g.FillRectangle(Brushes.SkyBlue, 52, 61, 52, 55 );
        //    g.FillPie(Brushes.SkyBlue, 50, 45, 55, 50, 400, 400 );
        //    g.Clear(setColor(0, 0, 0, 0));
        //}

        private void accionBotones(Object sender, EventArgs e)
        {
            Control objeto = (Control)sender;
            switch (objeto.Text)
            {
                case "X":
                    Application.Exit();
                    break;
                case "-":
                    this.WindowState = FormWindowState.Minimized;
                    break;
            }
        }
        private void accionBotonesMenu(Object sender, EventArgs e)
        {
            Control objeto = (Control)sender;
            for (int i = 0; i < botonesMenu.Length; i++)
            {
                botonesMenu[i].BackColor = Color.FromArgb(255, 18, 18, 18);
            }
            switch (objeto.Text)
            {
                case "Cronometro":
                    objeto.BackColor = Color.FromArgb(255, 25, 25, 25);
                    guiCronometro.Visible = true;
                    guiReloj.Visible = false;
                    break;
                case "Reloj":
                    objeto.BackColor = Color.FromArgb(255, 25, 25, 25);
                    guiReloj.Visible = true;
                    guiCronometro.Visible = false;
                    break;
                case "Temporizador":
                    objeto.BackColor = Color.FromArgb(255, 25, 25, 25);
                    guiCronometro.Visible = false;
                    guiReloj.Visible = false;
                    break;
            }
        }

        public static Color setColor(Int32 a, Int32 r, Int32 g, Int32 b)
        {
            Color color = Color.FromArgb((int)a, (int)r, (int)g, (int)b);
            return color;
        }

        private void InitializeComponent()
        {
            this.SuspendLayout();
            // 
            // ventanaPrincipal
            // 
            this.ClientSize = new System.Drawing.Size(282, 253);
            this.Name = "ventanaPrincipal";
            this.ResumeLayout(false);

        }

        public Form getThis()
        {
            return this;
        }
    }


}
