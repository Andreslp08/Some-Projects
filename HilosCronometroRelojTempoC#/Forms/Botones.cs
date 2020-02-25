using System;
using System.Drawing;
using System.Windows.Forms;

namespace Form2
{
    class Botones : Label
    {

        public enum TIPO { BOTON_PRINCIPAL = 0, BOTON_NORMAL = 1, BOTON_MENU = 2 };
        private String fuente = "Arial";
        private float tamaño = 10.0f;
        private float tamañoOrginal = 0f;
        public Botones(String texto, int x, int y, int w, int h, TIPO tipo, String fuente, float tamaño)
        {
            this.tamañoOrginal = tamaño;
            this.fuente = fuente;
            this.Text = texto;
            this.SetBounds(x, y, w, h);
            this.ForeColor = setColor(255, 255, 255, 255);
            this.BackColor = setColor(255, 255, 200, 0);
            this.Font = Font = new Font(fuente, tamaño, FontStyle.Bold);
            this.TextAlign = ContentAlignment.MiddleCenter;
            switch (tipo)
            {
                case TIPO.BOTON_NORMAL:
                    this.BackColor = setColor(255, 253, 160, 0);
                    this.MouseLeave += new EventHandler(mouseAfueraNormal);
                    this.MouseEnter += new EventHandler(mouseAdentro);
                    this.MouseDown += new MouseEventHandler(mousePresionado);
                    this.MouseUp += new MouseEventHandler(mouseSuelto);
                    break;
                case TIPO.BOTON_PRINCIPAL:
                    this.BackColor = setColor(0, 255, 200, 0);
                    this.MouseLeave += new EventHandler(mouseAfueraPrincipal);
                    this.MouseEnter += new EventHandler(mouseAdentro);
                    this.MouseDown += new MouseEventHandler(mousePresionado);
                    this.MouseUp += new MouseEventHandler(mouseSuelto);
                    break;
                case TIPO.BOTON_MENU:
                    this.BackColor = setColor(255, 18, 18, 18);
                    this.MouseLeave += new EventHandler(mouseAfueraMenu);
                    this.MouseEnter += new EventHandler(mouseAdentroMenu);
                    this.MouseDown += new MouseEventHandler(mousePresionadoMenu);
                    this.MouseUp += new MouseEventHandler(mouseSueltoMenu);
                    break;
            }
        }

        //BOTONES
        private void mouseAdentro(Object sender, EventArgs e)
        {
            this.BackColor = setColor(255, 253, 110, 0);
            tamaño = tamañoOrginal + 2f;
            this.Font = Font = new Font(fuente, tamaño, FontStyle.Regular);
        }
        private void mouseAfueraPrincipal(Object sender, EventArgs e)
        {
            this.BackColor = setColor(0, 253, 160, 0);
            this.Font = Font = new Font(fuente, tamañoOrginal, FontStyle.Regular);
        }
        private void mouseAfueraNormal(Object sender, EventArgs e)
        {
            this.BackColor = setColor(255, 253, 160, 0);
            this.Font = Font = new Font(fuente, tamañoOrginal, FontStyle.Regular);
        }
        private void mousePresionado(Object sender, EventArgs e)
        {
            this.BackColor = setColor(255, 220, 80, 0);
            tamaño = tamaño + 5;
            this.Font = Font = new Font(fuente, tamaño, FontStyle.Regular);
        }
        private void mouseSuelto(Object sender, EventArgs e)
        {
            this.BackColor = setColor(255, 253, 110, 0);
            tamaño = tamañoOrginal + 2;
            this.Font = Font = new Font(fuente, tamaño, FontStyle.Regular);
        }
        //MENU
        private void mouseAdentroMenu(Object sender, EventArgs e)
        {
            this.ForeColor = setColor(255, 253, 160, 0);
            tamaño = tamañoOrginal + 2;
            this.Font = Font = new Font(fuente, tamaño + 5, FontStyle.Bold);
        }
        private void mouseAfueraMenu(Object sender, EventArgs e)
        {
            this.ForeColor = setColor(255, 255, 255, 255);
            this.Font = Font = new Font(fuente, tamañoOrginal, FontStyle.Bold);
        }
        private void mousePresionadoMenu(Object sender, EventArgs e)
        {
            this.ForeColor = setColor(255, 253, 110, 0);
            tamaño = tamaño + 6;
            this.Font = Font = new Font(fuente, tamaño, FontStyle.Bold);
        }
        private void mouseSueltoMenu(Object sender, EventArgs e)
        {
            this.ForeColor = setColor(255, 253, 110, 0);
            tamaño = tamañoOrginal + 2;
            this.Font = Font = new Font(fuente, tamaño, FontStyle.Bold);
        }

        // ESTABLECR COLOR
        private static Color setColor(Int32 a, Int32 r, Int32 g, Int32 b)
        {
            Color color = Color.FromArgb((int)a, (int)r, (int)g, (int)b);
            return color;
        }
    }
}
