Author Macias Gomez Jorge
compile : javac VentanaPrincipal.java
run : java Ventana Principal

to change the Cellular automaton modify line 54 of class VentanaPrincipal
as it follows
AutomataCelular a = new GliderGun();
AutomataCelular a = new JuegoVida();
AutomataCelular a = new Incendio();