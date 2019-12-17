import java.util.Random;
public class Incendio extends AC{

		int[][] Maux2=new int[Imagen.numCells][Imagen.numCells];
		int[][] MauxCopia=new int [Imagen.numCells][Imagen.numCells];
		int[][] CopiaM =new int [Imagen.numCells][Imagen.numCells];
	 
   	/*
   	*Metodo que pinta una matriz de Blanco y le da valores aleatorios a las casillas.
   	*
   	*/
	 @Override
    public int[][] getAutomata() {
    	int aux1;
    	for (int i=0;i<Maux2.length ;i++ ) {
    		for (int j=0;j<Maux2.length ;j++ ) {
    		Maux2[i][j]=2;
    		MauxCopia[i][j]=2;
    		}
    	}
 		   	//Maux2[2][1]=1;  Elementos que use como prueba del automata
 		   	//Maux2[2][2]=1;   Trazando una linea vertical en la matriz
 		   	//Maux2[2][3]=1;     para crear un Parpadeador o ´blinker´.
		

		//Modifico cada valor de la matriz Maux de forma aleatoria.
			for (int i=0;i<Maux2.length;i++) {
				for (int j=0;j<Maux2.length;j++) {
					aux1 = (int) ( Math.random() * 9 + 1);
					if (aux1<=3) {
						Maux2[i][j] = 2;
					}else if (aux1>3 && aux1<=6) {
						Maux2[i][j] = 5;
					}else {
						Maux2[i][j] = 4;
					}
				}
			}
    	return Maux2;
    }
    /*
    *Metodo para evolucionar el automata.
	*
	*/
	 @Override
	public void evoluciona(){
		int ProbaP;
		int ProbaF;
		//Se crea una matriz copia para reemplazar los Valores.
		//CopiaM[][]=new int [Imagen.numCells][Imagen.numCells];
	    //System.out.println("entre"); //SOP que ayuda a verificar cuando se efectuaba un evoluciona.


		// super.estado++; // Operacion que aumentaba el contador en una unidad.
		
		int arbolArdiendo; //Contador de casillas vecindad con arboles ardiendo.

		//For que escanea toda la matriz.
		for (int i=0;i<Maux2.length;i++) { 
			for (int j=0;j<Maux2.length;j++) {
				arbolArdiendo=0; //Reiniciar contador de arboles ardiendo.	

				//System.out.println("Revisando " + i  + ","  + j  ); //SOP que ayuda a checar que se realize correctamente el for.


				for (int k=i-1;k<=i+1;k++) {
					for (int l=j-1;l<=j+1;l++) {
						//Analisis de casillas vecindad.
						if (k>=0&&l>=0&&k<Maux2.length&&l<Maux2.length&&(k!=i|| l!=j)) {
							//System.out.println("    Analizando " + k  + ","  + l  + "  --> " + Maux2[k][l]     ); //SOP que ayuda a checar los for.
							if ( Maux2[k][l] == 4) { arbolArdiendo ++;} //Aumentamos contador de ardiendo.
						}
					}
				}

				//System.out.println("Hay "+arbolArdiendo+ "arboles ardiendo en esta casilla");

				ProbaP = (int) ( Math.random() * 1000 + 1); //Solo valores de 1 a 1000
				//System.out.println("Mi ProbaP es "+ProbaP+ " ");

				ProbaF = (int) ( Math.random() * 100000 + 1); //Solo valores de 1 a 100000
				
				//System.out.println("Mi ProbaF es "+ProbaF+ " ");



				switch (Maux2[i][j]) {
					case 2: //si esta vacia vivira por probabilidad.
							if (ProbaP ==23) { MauxCopia[i][j]=5; 
								//System.out.println("la casilla "+i+","+j+ "Estaba Vacia y por probabilidad vivira.");
							}
							else {MauxCopia[i][j]=2;
								//System.out.println("la casilla "+i+","+j+ "Permanecera vacia");
							}
							break;
					case 5: //si esta viva...
							if (arbolArdiendo>0) {MauxCopia [i][j]=4;
								//System.out.println("la casilla "+i+","+j+ "esta viva y tiene un vecino ardiendo y se prendera.");
							}else
							if	(ProbaF==23){MauxCopia [i][j]=4;
								//System.out.println("la casilla "+i+","+j+ "Por probabilidad se encendera.");
							}else {
								MauxCopia [i][j]=5;
							}
							break;
					case 4: //Si esta en llamas se apagara
							MauxCopia[i][j]=2;
							break;
				}
			}
		}
		//System.out.println("Termine");//SOP que ayuda a saber cuando acaba una evolucion.
	}
	
	/**
	*Metodo que regresa la matriz con cada evolucion.
	*
	*/
    public int[][] getAutomata2() {
    	for (int i =0;i<Maux2.length;i++ ) {
    		for (int j =0;j<Maux2.length;j++ ) {
    		Maux2[i][j]=MauxCopia[i][j];
    		}
    	}
 		return Maux2;
    }
}