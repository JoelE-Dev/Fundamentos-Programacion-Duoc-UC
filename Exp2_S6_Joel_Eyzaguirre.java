/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */




package sistemaventasteatromoro;

/**
 *
 * @author j0el9
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

enum TipoTicket {
  VIP(15000), PALCO(10000), GENERAL(5000);

  final double precio;

  TipoTicket(double precio) {
    this.precio = precio;
  }
}

enum EstadoTicket {
  DISPONIBLE, RESERVADO, VENDIDO
}

enum TipoDescuento {
  NINGUNO(0), ESTUDIANTE(0.2), TERCERA_EDAD(0.3);

  final double valor;

  TipoDescuento(double valor) {
    this.valor = valor;
  }
}

class Ticket {
  TipoTicket tipo;
  EstadoTicket estado;
  long tiempoReserva;
  TipoDescuento descuento;
  double precioFinal;

  public Ticket(TipoTicket tipo) {
    this.tipo = tipo;
    this.estado = EstadoTicket.DISPONIBLE;
    this.descuento = TipoDescuento.NINGUNO;
    this.precioFinal = 0;
  }

}

public class exp2_S6_Joel_Eyzaguirre {
  private List<Ticket> tickets = new ArrayList<>();
  private final int TIEMPO_RESERVA = 10; // segundos

  public exp2_S6_Joel_Eyzaguirre() {
    for (int i = 0; i < 100; i++) {
      if (i < 20)
        tickets.add(new Ticket(TipoTicket.VIP));
      else if (i < 50)
        tickets.add(new Ticket(TipoTicket.PALCO));
      else
        tickets.add(new Ticket(TipoTicket.GENERAL));
    }
  }

  public static void main(String[] args) {
    exp2_S6_Joel_Eyzaguirre sistema = new exp2_S6_Joel_Eyzaguirre();
    sistema.menuPrincipal();
  }

  private void menuPrincipal() {
    Scanner sc = new Scanner(System.in);
    int opcion;

    do {
      mostrarEstadisticas();
      System.out.println("\n*** Teatro Moro ***");
      System.out.println("1. Reservar entrada");
      System.out.println("2. Comprar entrada");
      System.out.println("3. Modificar entrada");
      System.out.println("4. Ver boleta");
      System.out.println("5. Salir");
      System.out.print("Seleccione opción: ");

      // Punto de Depuración
      opcion = Integer.parseInt(sc.nextLine());

      switch (opcion) {
        case 1:
          reservarEntrada(sc);
          break;
        case 2:
          comprarEntrada(sc);
          break;
        case 3:
          modificarEntrada(sc);
          break;
        case 4:
          mostrarBoleta(sc);
          break;
        case 5:
          System.out.println("Saliendo del sistema...");
          break;
        default:
          System.out.println("Opción inválida");
      }

    } while (opcion != 5);

    mostrarEstadisticas();
    sc.close();
  }

  private Ticket seleccionarTicketPorNumero(Scanner sc, String mensaje) {
    System.out.print(mensaje + " (0 para cancelar): ");
    int num = Integer.parseInt(sc.nextLine());

    // Punto de Depuración
    if (num == 0) {
      System.out.println("Operación cancelada.");
      return null;
    }

    if (num < 1 || num > tickets.size()) {
      System.out.println("Número de ticket inválido.");
      return null;
    }

    return tickets.get(num - 1);
  }

  private void reservarEntrada(Scanner sc) {
    actualizarReservas();
    mostrarTickets(EstadoTicket.DISPONIBLE);

    Ticket ticket = seleccionarTicketPorNumero(sc, "Ingrese número de ticket a reservar");
    if (ticket == null)
      return;

    // Punto de Depuración
    if (ticket.estado == EstadoTicket.DISPONIBLE) {
      ticket.estado = EstadoTicket.RESERVADO;
      ticket.tiempoReserva = System.currentTimeMillis();
      System.out.println("¡Reserva exitosa! Tienes " + TIEMPO_RESERVA + " segundos para completar la compra");
    } else {
      System.out.println("El ticket no está disponible");
    }
  }

  private void comprarEntrada(Scanner sc) {
    actualizarReservas();
    mostrarTickets(EstadoTicket.DISPONIBLE);

    Ticket ticket = seleccionarTicketPorNumero(sc, "Ingrese número de ticket a comprar");
    if (ticket == null)
      return;

    int num = tickets.indexOf(ticket) + 1;

    if (ticket.estado != EstadoTicket.DISPONIBLE) {
      System.out.println("El ticket no está disponible");
      return;
    }

    System.out.println("Seleccione descuento:");
    System.out.println("1. Estudiante (20%)");
    System.out.println("2. Tercera edad (30%)");
    System.out.println("3. Ninguno");
    int opcion = Integer.parseInt(sc.nextLine());

    switch (opcion) {
      case 1:
        ticket.descuento = TipoDescuento.ESTUDIANTE;
        break;
      case 2:
        ticket.descuento = TipoDescuento.TERCERA_EDAD;
        break;
      default:
        ticket.descuento = TipoDescuento.NINGUNO;
    }

    // Punto de Depuración
    double precioCalculado = ticket.tipo.precio * (1 - ticket.descuento.valor);
    ticket.precioFinal = precioCalculado;
    ticket.estado = EstadoTicket.VENDIDO;

    System.out.println("¡Compra exitosa!");
    mostrarBoleta(num, ticket);
  }

  private void modificarEntrada(Scanner sc) {
    actualizarReservas();
    mostrarTickets(EstadoTicket.RESERVADO, EstadoTicket.VENDIDO);

    Ticket ticket = seleccionarTicketPorNumero(sc, "Ingrese número de ticket a modificar");
    if (ticket == null)
      return;

    System.out.println("Seleccione nuevo estado (1.Disponible, 2.Reservado, 3.Vendido): ");
    int opcion = Integer.parseInt(sc.nextLine());

    // PUNTO DE DEPURACIÓN: Transición de estado del ticket durante modificación
    if (opcion >= 1 && opcion <= 3) {
      EstadoTicket[] estados = EstadoTicket.values();
      ticket.estado = estados[opcion - 1];
    } else {
      System.out.println("Opción inválida");
      return;
    }

    if (ticket.estado == EstadoTicket.VENDIDO) {
      System.out.println("Seleccione descuento:");
      System.out.println("1. Estudiante (20%)");
      System.out.println("2. Tercera edad (30%)");
      System.out.println("3. Ninguno");
      opcion = Integer.parseInt(sc.nextLine());

      switch (opcion) {
        case 1:
          ticket.descuento = TipoDescuento.ESTUDIANTE;
          break;
        case 2:
          ticket.descuento = TipoDescuento.TERCERA_EDAD;
          break;
        default:
          ticket.descuento = TipoDescuento.NINGUNO;
      }

      double precioCalculado = ticket.tipo.precio * (1 - ticket.descuento.valor);
      ticket.precioFinal = precioCalculado;

    } else {
      ticket.precioFinal = 0;
      ticket.descuento = TipoDescuento.NINGUNO;
      if (ticket.estado == EstadoTicket.RESERVADO) {
        ticket.tiempoReserva = System.currentTimeMillis();
      } else {
        ticket.tiempoReserva = 0;
      }
    }

    System.out.println("Estado actualizado correctamente");
  }

  private void mostrarBoleta(Scanner sc) {
    mostrarTickets(EstadoTicket.VENDIDO);

    Ticket ticket = seleccionarTicketPorNumero(sc, "Ingrese número de ticket para ver boleta");
    if (ticket == null)
      return;

    int num = tickets.indexOf(ticket) + 1;

    // Punto de Depuración: Verificacion del ticket.
    // boleta
    if (ticket.estado == EstadoTicket.VENDIDO) {
      mostrarBoleta(num, ticket);
    } else {
      System.out.println("El ticket no está vendido");
    }
  }

  private void mostrarBoleta(int num, Ticket ticket) {
    // Punto de Depuración
    System.out.println("\n=== BOLETA ===");
    System.out.println("Número: " + num);
    System.out.println("Tipo: " + ticket.tipo);
    System.out.println("Precio original: $" + ticket.tipo.precio);
    System.out.println("Descuento aplicado: " + ticket.descuento + " (" + (ticket.descuento.valor * 100) + "%)");
    System.out.println("Total a pagar: $" + ticket.precioFinal);
    System.out.println("================");
  }

  private void actualizarReservas() {
    long ahora = System.currentTimeMillis();
    for (Ticket ticket : tickets) {
      // Punto de Depuración
      if (ticket.estado == EstadoTicket.RESERVADO) {
        long diferencia = TimeUnit.MILLISECONDS.toSeconds(ahora - ticket.tiempoReserva);
        if (diferencia > TIEMPO_RESERVA) {
          ticket.estado = EstadoTicket.DISPONIBLE;
        }
      }
    }
  }

  private void mostrarTickets(EstadoTicket... estados) {
    System.out.println("\nTickets:");
    for (int i = 0; i < tickets.size(); i++) {
      Ticket ticket = tickets.get(i);
      for (EstadoTicket estado : estados) {
        if (ticket.estado == estado) {
          System.out.print((i + 1) + ". " + ticket.tipo + " (" + ticket.estado + ")  ");
          if ((i + 1) % 5 == 0)
            System.out.println();
          break;
        }
      }
    }
    System.out.println();
  }

  private void mostrarEstadisticas() {
    int vendidos = 0;
    double total = 0;

    // Punto de Depuración
    for (Ticket ticket : tickets) {
      if (ticket.estado == EstadoTicket.VENDIDO) {
        vendidos++;
        total += ticket.precioFinal;
      }
    }

    System.out.println("Tickets vendidos: " + vendidos);
    System.out.println("Total recaudado: $" + total);
    System.out.println("************************");
  }
}