Algoritmo  Porcentaje_Ocupacion_Teatro_Moro
	Definir vendidos, total Como Entero;
	Definir porcentajeVendidos Como Real;
	
	total <- 1000;
	//Eleg� tener una cantidad total de asientos ya determinada.
	//De otro modo se deber�a tambi�n preguntar al usuario cual es el total de asientos, para poder hacer el calculo.
	
	Escribir "Bienvenido/a a Teatro Moro.";
	Escribir "Ingrese el n�mero actual de asientos vendidos:";
	Leer vendidos;
	
	porcentajeVendidos <- (vendidos / total) * 100;
	
	Escribir "El porcentaje de ocupaci�n del teatro es de: ", porcentajeVendidos, " %";
FinAlgoritmo
