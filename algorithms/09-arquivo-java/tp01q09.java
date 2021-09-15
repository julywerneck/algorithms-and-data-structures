import java.io.*; 
public class tp01q09 {
	public static void main(String[] args) throws Exception{
		MyIO.setCharset("UTF-8");		
		int quant = MyIO.readInt();
		RandomAccessFile raf = new RandomAccessFile("exemplo.txt", "rw");  

		for(int i = 0; i < quant; i++) {
			raf.writeDouble(MyIO.readDouble()); 
		}	 

		double tam = raf.length();
		raf.seek((int)tam); 
		double aux = 0; 

		for(double i = tam/8; i > 0; i--) 
		{
			raf.seek((int)tam-8);
			aux = raf.readDouble(); 
			if(aux == (int)aux) {
				MyIO.println((int)aux);
			} else {
				MyIO.println(aux);
			}

			tam -= 8;
		}	

		raf.close(); 		
	}
}
