import java.util.Random;
public class GliderGun extends AC {

	int[][] Maux2=new int[Imagen.numCells][Imagen.numCells];
	int[][] MauxCopia=new int [Imagen.numCells][Imagen.numCells];
	
	/**
	*Metodo que regresa la matriz con cada evolucion.
	*
	*/
    public int[][] getAutomata2() {
 		return Maux2;
    }
	 
   	/*
   	*Metodo que pinta una matriz de Blanco y le da valores aleatorios a las casillas.
   	*
   	*/
	 @Override
    public int[][] getAutomata() {
    	for (int i=0;i<Maux2.length ;i++ ) {
    		for (int j=0;j<Maux2.length ;j++ ) {
    		Maux2[i][j]=2;
    		}
    	}
 		   	//Maux2[2][1]=1;  Elementos que use como prueba del automata
 		   	//Maux2[2][2]=1;   Trazando una linea vertical en la matriz
 		   	//Maux2[2][3]=1;     para crear un Parpadeador o ´blinker´.
		

		//Modifico cada valor de la matriz Maux de forma aleatoria.
		//Descomente este For l(33-37) para jugar el juego de la vida original.
		//	for (int i=0;i<Maux2.length;i++) {
		//		for (int j=0;j<Maux2.length;j++) {
		//			Maux2[i][j] = (int) ( Math.random() * 2 + 1);
		//		}
		//} 
		//Hasta aqui descomentar para jugar el juego de la vida original.
		//Comentar desde linea 40 hasta linea 75 para jugar el juego de la vida original.
		Maux2[1][4]=1;
		Maux2[1][5]=1;
		Maux2[2][4]=1;
		Maux2[2][5]=1;
		Maux2[11][4]=1;
		Maux2[11][5]=1;
		Maux2[11][6]=1;
		Maux2[12][3]=1;
		Maux2[12][7]=1;
		Maux2[13][2]=1;
		Maux2[13][8]=1;
		Maux2[14][2]=1;
		Maux2[14][8]=1;
		Maux2[15][5]=1;
		Maux2[16][3]=1;
		Maux2[16][7]=1;
		Maux2[17][4]=1;
		Maux2[17][5]=1;
		Maux2[17][6]=1;
		Maux2[18][5]=1;
		Maux2[21][4]=1;
		Maux2[22][4]=1;
		Maux2[21][3]=1;
		Maux2[22][3]=1;
		Maux2[21][2]=1;
		Maux2[22][2]=1;
		Maux2[23][1]=1;
		Maux2[23][5]=1;
		Maux2[25][0]=1;
		Maux2[25][1]=1;
		Maux2[25][5]=1;
		Maux2[25][6]=1;
		Maux2[36][3]=1;
		Maux2[35][3]=1;
		Maux2[36][2]=1;
		Maux2[35][2]=1;
    	return Maux2;
    }
    /*
    *Metodo para evolucionar el automata.
	*
	*/
	 @Override
	public void evoluciona(){

		//Se crea una matriz copia para reemplazar los Valores.
		int[][] CopiaM=new int [Imagen.numCells][Imagen.numCells];
	    //System.out.println("entre"); SOP que ayuda a verificar cuando se efectuaba un evoluciona.


		// super.estado++; // Operacion que aumentaba el contador en una unidad.
		int vivos; //Contador de casillas vecindad vivas.
		int muertos; //Contador de casillas vecindad muertas.

		//For que escanea toda la matriz.
		for (int i=0;i<Maux2.length;i++) { 
			for (int j=0;j<Maux2.length;j++) {
				vivos=0; //Reiniciar contador de vivos.
				muertos=0; //Reiniciar contador de muertos.	

				//System.out.println("Revisando " + i  + ","  + j  ); SOP que ayuda a checar que se realize correctamente el for.
				for (int k=i-1;k<=i+1;k++) {
					for (int l=j-1;l<=j+1;l++) {
						//Analisis de casillas vecindad.
						if (k>=0 && l>=0 && k<Maux2.length && l<Maux2.length && (k!=i|| l!=j)) {
							//System.out.println("    Analizando " + k  + ","  + l  + "  --> " + Maux2[k][l]     ); SOP que ayuda a checar los for.
							if ( Maux2[k][l] == 1) { vivos++; } else { muertos++; }
						}
					}
				}
				if(  Maux2[i][j] == 1 ){           //Si la casilla esta viva, 
					if ( vivos==2 || vivos==3  ){  //Y tiene dos o tres vecinos vivos  
					 CopiaM[i][j]=1;				//entonces la casilla vivira.
					}else {
					 	CopiaM[i][j]=2;				//De otra forma muere.
						}			
				}
				else{ 							//Si la casilla esta muerta,
					if ( vivos == 3 ) {			//Y tiene 3 vecinos vivos
						CopiaM[i][j]=1; 		//La casilla muerta vivira.
					} 
					else {
						CopiaM[i][j]=2;			//De otro modo seguira muerta.
					}
				}							
				//SOP que cuenta las casillas vecinas muertas y vivas y dice como cambiara el estado de la casilla.
				//System.out.println("      Muertos " + muertos + "  Vivos-> " + vivos   + " -----> " + CopiaM[i][j]   );
			}
		}
		for (int i=0;i<Maux2.length;i++) { 			//Fors que arreglan la matriz a regresar en la copia.
			for (int j=0;j<Maux2.length;j++) {
				Maux2[i][j]=CopiaM[i][j];
			}
		}
		//System.out.println("Termine");//SOP que ayuda a saber cuando acaba una evolucion.
	}
}