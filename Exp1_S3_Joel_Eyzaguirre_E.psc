Algoritmo Sistema_Gestion_Venta_Entradas
	
	// No logr� entender la actividad ni como escribir la soluci�n, p�ngame la nota que ud considere.
	// Tampoco pude ver la clase de reforzamiento. He andado con poco tiempo.
	// Ya podr� mejorar para la pr�xima.
	
	// Declaraci�n de variables
	Definir Opci�n, TipoEntrada, Cantidad, Total, i Como Entero;
	Definir ValorTotal, Dinero Como Real;
	
	// Arreglos
	Dimension TipoEntradas[8];
	Dimension Precio[8];
	Dimension CarritoCompras[8];
	
	Definir TipoEntradas Como Caracter;
	Definir Precio Como Real;
	Definir CarritoCompras Como Entero;
	
	TipoEntradas[0] <- "VIP";
	TipoEntradas[1] <- "VIP (Estudiante)";
	TipoEntradas[2] <- "Platea baja";
	TipoEntradas[3] <- "Platea baja (Estudiante)";
	TipoEntradas[4] <- "Platea alta";
	TipoEntradas[5] <- "Platea alta (Estudiante)";
	TipoEntradas[6] <- "Palcos";
	TipoEntradas[7] <- "Palcos (Estudiante)";
	
	// Debe haber una soluci�n para verificar que quien est� comprando la entrada efectivamente sea estudiante.
	// Intent� dar opciones para el tipo de comprador, p�blico general o estudiante, pero a�n no consigo aprender totalmente la sintaxis.
	
	// Precios
	Precio[0] <- 30000;
	Precio[1] <- 20000;
	Precio[2] <- 15000;
	Precio[3] <- 10000;
	Precio[4] <- 18000;
	Precio[5] <- 9000;
	Precio[6] <- 13000;
	Precio[7] <- 6500;
	
	// Carrito vac�o
	Para i <- 0 Hasta 7 Hacer
		CarritoCompras[i] <- 0;
	FinPara
	
	// Men� que ver� el usuario.
	Repetir
		Escribir " ";
		Escribir " ";
		Escribir "******************************";
		Escribir "**       Teatro Moro        **";
		Escribir "******************************";
	// Hacer este dise�o s� que es opcional. Lo hice para que se viera m�s bonito y llamativo para el usuario.
		Escribir "1. Ver lista de entradas disponibles";
		Escribir "2. Agregar una entrada al carrito. Si ud es estudiante debe elegir la tarifa correspondiente";
		Escribir "3. Ver carrito de compras";
		Escribir "4. Pagar";
		Escribir "5. Salir";
		Escribir "Elija una opci�n: ";
		Leer opcion;
		
		Segun Opcion Hacer
			1:
				Escribir "Entradas disponibles: ";
				Para i <- 0 Hasta 7 Hacer
					Escribir i + 1, ". ", TipoEntradas[i], "    * Precio: $", Precio[i];
				FinPara
				
			2:
				Escribir "Ingrese el tipo de entrada que desea comprar: ";
				// Se agrega la entrada al carrito.
				Leer TipoEntrada;
				TipoEntrada <- TipoEntrada - 1;
				
				Si TipoEntrada >= 0 y TipoEntrada < 8 Entonces
					Escribir "Ingrese la cantidad que desea comprar (m�ximo 1): ";
					Leer Cantidad;
					
					Si Cantidad > 0 Entonces
					    CarritoCompras[TipoEntrada] <- CarritoCompras[TipoEntrada] + Cantidad;
					    Escribir "Entrada agregada al carrito con �xito.";
					SiNo
						Escribir "Cantidad no v�lida. S�lo puede comprar 1 entrada.";
					FinSi
				SiNo
					Escribir "Producto no v�lido.";
				FinSi
				
			3:
				Escribir "Carrito de compras: ";
				Total <- 0;
				Para i <- 0 Hasta 7 Hacer
					Si CarritoCompras[i] > 0 Entonces
					    CarritoCompras[TipoEntrada] <- CarritoCompras[TipoEntrada] + Cantidad;
					    Escribir TipoEntradas[i], "    Cantidad: ", CarritoCompras[i], "    Subtotal: $", CarritoCompras[i] * Precio[i];
						Total <- Total + (CarritoCompras[i] * Precio[i]);
					FinSi
				FinPara
				Escribir "Total a pagar: $", Total;
				
			4:
				ValorTotal <- 0;
				// Pago
				Para i <- 0 Hasta 7 Hacer
					ValorTotal <- ValorTotal + (CarritoCompras[i] * Precio[i]);
				FinPara
				
				Si ValorTotal = 0 Entonces
					Escribir "Carrito vac�o.";
				SiNo
					Escribir "El total a pagar es: $", ValorTotal;
					Escribir "Ingrese con que cantidad de dinero va a pagar: $";
					Leer Dinero;
					
					Si Dinero >= ValorTotal Entonces
						Escribir "El pago ha sido exitoso. Su cambio es de: $", Dinero - ValorTotal;
						Escribir "Gracias por su compra, disfrute la funci�n.";
						Para i <- 0 Hasta 3 Hacer
							CarritoCompras[i] <- 0;
						FinPara
					SiNo
						Escribir "Dinero insuficiente.";
					FinSi
				FinSi
				
			5: 
				Escribir "Saliendo... ";
				
			De Otro Modo:
				Escribir "Elija una opci�n v�lida.";
		FinSegun
	Hasta Que opcion = 5
	
FinAlgoritmo
