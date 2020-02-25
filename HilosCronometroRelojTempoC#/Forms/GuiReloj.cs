using System;
using System.Drawing;
using System.Globalization;
using System.Threading;
using System.Windows.Forms;

namespace Form2.Forms
{
    class GuiReloj : Label
    {
        private PictureBox fondo;
        private Font font;
        private String diaFormato = DateTime.Now.ToString("dddd");
        private String mes = DateTime.Now.ToString("MMMMM");
        private String numDia = DateTime.Now.ToString("dd");
        private String anio = DateTime.Now.ToString("yyyy");
        private String hora = DateTime.Now.ToString("h:mm:ss");
        private String am_pm = DateTime.Now.ToString("tt", CultureInfo.InvariantCulture);
        private Label infoHora;
        private Label infoDia;
        private Thread hilo;
        private String infoGeneral;

        public GuiReloj(Control control)
        {
            this.SetBounds(20, 95, control.Width - 40, control.Height - 115);
            this.BackColor = Color.FromArgb(200, 15, 15, 15);
            this.Visible = false;
            ////fondo
            fondo = new PictureBox();
            fondo.SetBounds(0, 0, this.Width, this.Height);
            fondo.Image = Image.FromFile(@"..\..\img\timeZone.jpg");
            fondo.SizeMode = PictureBoxSizeMode.StretchImage;
             this.Controls.Add(fondo);
            //Info hora
            infoHora = new Label();
            infoHora.SetBounds((fondo.Width-350)/2, (fondo.Height-100)/2, 350, 100 );
            infoHora.TextAlign = ContentAlignment.MiddleCenter;
            infoHora.Font = new Font("Arial", 40f, FontStyle.Bold);
            infoHora.BackColor = Color.FromArgb(100, 25, 25, 25);
            infoHora.ForeColor = Color.White;
            fondo.Controls.Add(infoHora);
            //Info dia
            infoDia = new Label();
            infoDia.SetBounds((fondo.Width - 350) / 2, 80 / 2, 350, 50);
            infoDia.TextAlign = ContentAlignment.MiddleCenter;
            infoDia.Font = new Font("Arial", 20f, FontStyle.Regular);
            infoDia.BackColor = Color.FromArgb(100, 25, 25, 25);
            infoDia.ForeColor = Color.White;
            fondo.Controls.Add(infoDia);
            //hilo
            hilo = new Thread(new ThreadStart(actualizarInfo));
            hilo.IsBackground = true;
            hilo.Start();
        }


        public void empezarHilo()
        {
            try
            {
                if (!hilo.IsAlive)
                {
                    hilo = new Thread(new ThreadStart(actualizarInfo));
                    hilo.Start();
                    hilo.IsBackground = true;
                }
            }
            catch (Exception ex)
            {

            }
        }

        public void finalizarHilo()
        {
            try
            {
                hilo.Abort();
            }
            catch (Exception ex)
            {

            }
        }
        public void actualizarInfo()
        {
            for (; ; )
            {
                diaFormato = DateTime.Now.ToString("dddd");
                mes = DateTime.Now.ToString("MMMMM");
                numDia = DateTime.Now.ToString("dd");
                anio = DateTime.Now.ToString("yyyy");
                hora = DateTime.Now.ToString("h:mm:ss");
                String am_pm = DateTime.Now.ToString("tt", CultureInfo.InvariantCulture);
                infoHora.Text = hora + " "+am_pm.ToLower()+".";
                infoDia.Text = diaFormato + ", " + mes + " " + numDia + " de " + anio;
                Thread.Sleep(1);
            }
           
        }
    }
}
