using System;
using System.Windows.Forms;

namespace Juego
{
    static class Program
    {
        /// <summary>
        /// Punto de entrada principal para la aplicación.
        /// </summary>
        static VentanaPrincipal ventanaPrincipal;
        [STAThread]

        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.iniciarHilo();
            Application.Run(ventanaPrincipal);
        }
    }
}
