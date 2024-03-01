package com.prueba.dev.domain.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_transaccion")
public class Transaction {
	
	@Id
    private long id_transaccion;
	@ManyToOne
	@JoinColumn(name = "id_tarjeta")
	private Tarjeta id_tarjeta;
	@Column
	private Date fecha;
	@Column
	private long valor;
	@Column
	private Boolean anulada;

	public long getId_transaccion() {
		return id_transaccion;
	}

	public void setId_transaccion(long id_transaccion) {
		this.id_transaccion = id_transaccion;
	}

	public Tarjeta getId_tarjeta() {
		return id_tarjeta;
	}

	public void setId_tarjeta(Tarjeta id_tarjeta) {
		this.id_tarjeta = id_tarjeta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}

	public Boolean getAnulada() {
		return anulada;
	}

	public void setAnulada(Boolean anulada) {
		this.anulada = anulada;
	}

	public Transaction() {
		super();
	}

}
