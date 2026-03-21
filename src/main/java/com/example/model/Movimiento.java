package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento {
    
    @Id
    private String codigo;

    @Column(name = "cuenta", length = 4)
    private String cuenta;

    @Column(name = "monto", precision = 2)
    private double monto;

    @Column(name = "procesado")
    private boolean isProcesado;

    @Column(name = "fecha")
    private String fecha;
}
