using System;
using System.Threading;

namespace Juego.Logica
{

    class Jugador
    {
        int x = 0, y = 0;

        Thread hilo;
        public Jugador()
        {
            hilo = new Thread(mover);
        }


        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }

        public void setX(int x)
        {
            this.x = x;
        }

        public void setY(int y)
        {
            this.y = y;
        }

        public void mover()
        {
            Console.WriteLine("xd");
            Thread.Sleep(1000);
        }
    }
}
