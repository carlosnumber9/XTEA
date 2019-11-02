package org.h2.security;

import org.h2.security.XTEA;

public class XTEAPrueba {

public XTEAPrueba(){}


	/**
	 * Calculates Hamming distance between two strings
	 */
	public static int calculateHammingDistance(String uno, String dos){
		if (uno.length() != dos.length()){
			return -1;
		}

		int resultado = 0;

		for (int i = 0; i < uno.length(); i++){
			if (uno.charAt(i) != dos.charAt(i)) {
				resultado++;
			}
		}
		return resultado;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] mensajes = new String[2];
		mensajes[0] = "abcdefghabcdefgh";
		mensajes[1] = mensajes[0];

		for(int i=0; i<mensajes.length;i++){
			System.out.println("Mensaje " + (i+1) + ": " + mensajes[i]);
		}

		byte[][] mensajesB = {mensajes[0].getBytes(), mensajes[0].getBytes()};
		mensajesB[1][mensajesB[1].length-1] = (byte)(mensajesB[0][mensajesB[0].length-1]+1);
		for(int i=0;i<mensajes.length;i++){
			mensajesB[i] = mensajes[i].getBytes();
		}

		XTEA xtea = new XTEA();
		byte[] key = new byte[16];

		for(int i=0;i<key.length;i++){
			key[i] = (byte)Math.random();
		}

		xtea.setKey(key);

		byte[][] criptoB = mensajesB;
		xtea.encrypt(criptoB[0], 0, criptoB[0].length);


/*
		key[15] = (byte)(key[15]+1);

		xtea.setKey(key);
		*/
		
		xtea.k31 = (byte)(xtea.k31+1);

		xtea.encrypt(criptoB[1], 0, criptoB[1].length);
		String [] criptogramas = {new String(criptoB[0]), new String(criptoB[1])};
		for(int i=0;i<criptoB.length;i++){
			criptogramas[i] = new String(criptoB[i]);
		}
		for(int i=0; i<criptogramas.length;i++){
			System.out.println("Criptograma " + (i+1) + ": " + criptogramas[i]);
		}
		System.out.println("Distancia de Hamming para los criptogramas = " + calculateHammingDistance(criptogramas[0],criptogramas[1]));

		/*
		byte[] mensajeDescB = criptoB;

		xtea.decrypt(mensajeDescB, 0, mensajeDescB.length);

		String mensajeDesc = new String(mensajeDescB);

		System.out.println("Si desciframos dicho criptograma obtenemos '" + mensajeDesc + "'");
		 */

	}
}
