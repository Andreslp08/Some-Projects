using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Juego.forms
{
    class Obstaculo: PictureBox
    {
        private const int W = 100, H = 100;
        private int posX, posY, posX_Inicial, posX_Final, velocidad;

       public Obstaculo()
        {
            this.Load(@"..\..\img\obstaculo.png");
            this.SizeMode = PictureBoxSizeMode.StretchImage;
            posX = 0;
            posY = 0;
            Size = new Size(W, H);
        }

        public void setPosicionInicial( int posX, int posY )
        {
            posX_Inicial = posX;
            this.posX = posX;
            this.posY = posY;
            Location = new Point(this.posX, this.posY);
        }

        public void mover()
        {  
            Boolean movCompleto = false;
            velocidad = 5;
            posX_Final = -W;
 
                Location = new Point(posX, posY);
                    posX-=velocidad;
                if( posX <= posX_Final)
                {
                    Location = new Point(posX, posY);
                posX = posX_Inicial;
                    movCompleto = true;
                }

        }

    }
}
