using System;
using System.Windows.Forms;

namespace Form2
{
    static class Program
    {
        /// <summary>
        /// Punto de entrada principal para la aplicación.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.Run(new ventanaPrincipal());
        }
    }
}
